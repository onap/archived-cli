#!/usr/bin/python
# Copyright 2019 Huawei Technologies Co., Ltd.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# This script uses the ONAP CLI for providing the end-end service creation and termination.
# Used in devops, testing, certification and production
# NOTE: This feature is avaialble as ONAP CLI vnf-tosca-lcm
#
# Author: kanagaraj.manickam@huawei.com 
#

import json
import os
import argparse
import sys
import uuid
import subprocess
import platform
import datetime
import string
import random

from argparse import RawTextHelpFormatter

if platform.system() == 'Windows':
    CMD_NAME = 'oclip.cmd'
else:
    CMD_NAME = 'oclip.sh'

class OcompException(Exception):
    def __init__(self, code, message):
        super(OcompException, self).__init__()
        self.code = code;
        self.message = message;

class OCOMP:
    def __init__(self,
                 request_id = os.environ.get('OPEN_CLI_REQUEST_ID'),
                 debug = False,
                 format = 'json',
                 product = os.environ.get('OPEN_CLI_PRODUCT_IN_USE'),
                 profile = os.environ.get('OPEN_CLI_PROFILE')):
        if not request_id:
                request_id = str(uuid.uuid4())
        self.request_id = request_id
        self.debug = debug
        self.format = format
        self.product = product
        self.profile = profile

    def __str__(self):
        return  str(vars(self))

    def __repr__(self):
        return  str(self)

    @staticmethod
    def version():
        return os.popen('{} --version'.format(CMD_NAME)).read()

    def run(self, command, params={}, product=None,  profile=None, request_id=None):
        CMD = [CMD_NAME]

        if not request_id:
                request_id = self.request_id

        if request_id:
            CMD.append('--request-id')
            CMD.append(request_id)

        if not product:
            product = self.product

        if product:
            CMD.append('--product')
            CMD.append(product)

        if not profile:
            profile = self.profile

        if profile:
            CMD.append('--profile')
            CMD.append(profile)

        CMD.append(command)

        if self.debug:
            CMD.append('--debug')

        CMD.append('--format')
        CMD.append(self.format)

        for name, value in params.items():
            CMD.append('--{}'.format(name))
            CMD.append(value)

        cmd_string = ' '.join(CMD)
        print (cmd_string)

        try:
            res = subprocess.Popen(CMD, stdout=subprocess.PIPE)
            res.wait()
            result = res.stdout.read().strip()
            print (res.returncode, result)

            if res.returncode != 0:# and res.returncode != 1:
                raise OcompException(9999, result)
            if result:
                return json.loads(result)
            else:
                return {}
        except OSError as e:
            sys.stderr.write(str(e))
            msg = 'failed to executed the command {}'.format(cmd_string)
            print (msg)
            raise OcompException(9999, msg)

class ONAP:
    def __init__(self,
                 product,
                 profile,
                 conf,
                 request_id,
                 debug = False):
        self.conf = conf or {}
        self.ocomp = OCOMP(request_id, debug, product=product, profile=profile)
        self.preload()
        self.tag = 'Powered by Open Command Platform - OCOMP'

    def preload(self):
        if self.conf['ONAP']:
            for attr in self.conf['ONAP']:
                setattr(self, attr, self.conf['ONAP'][attr])
        else:
            self.conf['ONAP'] = {}

    def create_vlm(self):
        submit = False

        if not self.vlm_id and not self.vlm_version:
            output = self.ocomp.run(command='vlm-create',
                                        params={'vendor-name': self.conf['vnf']['vendor-name'],
                                                'description': self.tag})

            self.vlm_id = output['id']
            self.vlm_version = output['version']
            submit = True

        if not self.entitlement_id:
            output = self.ocomp.run(command='vlm-entitlement-pool-create',
                                    params={'name': '{} Entitlement Pool'.format(self.conf['vnf']['vendor-name']),
                                            'vlm-id': self.vlm_id,
                                            'vlm-version': self.vlm_version,
                                            'manufacture-reference-number': 'OCOMP',
                                            'start-date': datetime.datetime.strftime(datetime.datetime.today(),'%m/%d/%Y'),
                                            'expiry-date': datetime.datetime.strftime(datetime.datetime.today() + datetime.timedelta(1),'%m/%d/%Y')})
            self.entitlement_id = output['id']
            submit = True

        if not self.key_group_id:
            output = self.ocomp.run(command='vlm-key-group-create',
                                        params={'name': '{} Key goroup'.format(self.conf['vnf']['vendor-name']),
                                                'vlm-id': self.vlm_id,
                                                'vlm-version': self.vlm_version,
                                                'type': 'Universal'})

            self.key_group_id = output['id']
            submit = True

        if not self.feature_group_id:
            output = self.ocomp.run(command='vlm-feature-group-create',
                                        params={'name': '{} Feature group'.format(self.conf['vnf']['vendor-name']),
                                                'vlm-id': self.vlm_id,
                                                'vlm-version': self.vlm_version,
                                                'vlm-key-group-id': self.key_group_id,
                                                'vlm-entitle-pool-id': self.entitlement_id,
                                                'part-number': '100000'})

            self.feature_group_id = output['id']
            submit = True

        if not self.agreement_id:
            output = self.ocomp.run(command='vlm-aggreement-create',
                                        params={'name': '{} Agreement'.format(self.conf['vnf']['vendor-name']),
                                                'vlm-id': self.vlm_id,
                                                'vlm-version': self.vlm_version,
                                                'vlm-feature-group-id': self.feature_group_id})

            self.agreement_id = output['id']
            submit = True

        if submit:
            self.ocomp.run(command='vlm-submit',
                                    params={'vlm-id': self.vlm_id,
                                            'vlm-version': self.vlm_version})

    def create_vsp(self):
        if not self.vsp_id and not self.vsp_version and not self.vsp_version_id:
            output = self.ocomp.run(command='vsp-create',
                                    params={'vlm-id': self.vlm_id,
                                            'vlm-version': self.vlm_version,
                                            'vlm-vendor': self.conf['vnf']['vendor-name'],
                                            'vsp-name': self.conf['vnf']['name'],
                                            'vsp-description': self.tag,
                                            'vlm-agreement-id': self.agreement_id,
                                            'vlm-feature-group-id': self.feature_group_id})
            self.vsp_id = output['id']
            self.vsp_version_id = output['version-id']
            self.vsp_version = output['version']

            self.ocomp.run(command='vsp-add-artifact',
                                    params={'vsp-id': self.vsp_id,
                                            'vsp-version': self.vsp_version_id,
                                            'vsp-file': self.conf['vnf']['vsp-csar']})

            output = self.ocomp.run(command='vsp-validate',
                                    params={'vsp-id': self.vsp_id,
                                            'vsp-version': self.vsp_version_id})
            if not output['status'] == "Success":
                raise Exception("Invalid VSP package, please check it compliance using VTP")

            self.ocomp.run(command='vsp-commit',
                                    params={'vsp-id': self.vsp_id,
                                            'vsp-version': self.vsp_version_id,
                                            'remarks': self.tag})

            self.ocomp.run(command='vsp-submit',
                                    params={'vsp-id': self.vsp_id,
                                            'vsp-version': self.vsp_version_id})

            self.ocomp.run(command='vsp-package',
                                    params={'vsp-id': self.vsp_id,
                                            'vsp-version': self.vsp_version_id})

    def create_vf_model(self):
        if not self.vf_id and not self.vf_version:
            output = self.ocomp.run(command='vf-model-create',
                                    params={'name': '{} Vnf'.format(self.conf['vnf']['name']),
                                            'vendor-name': self.conf['vnf']['vendor-name'],
#                                             'vsp-id': self.vsp_id,
#                                             'vsp-version': self.vsp_version, # TODO: SDC fails to add VSP, check it
                                            'description': self.tag})

            vf_id = output['id']
            inputs = output['inputs'].replace('[', '').replace(']', '').split(',')
            # add artifact
            self.ocomp.run(command='vf-model-add-artifact',
                                    params={'vf-id': vf_id,
                                            'remarks': self.tag,
                                            'artifact': self.conf['vnf']['vnf-csar'],
                                            'artifact-name': 'tosca csar'})

            output = self.ocomp.run(command='vf-model-certify',
                                        params={'vf-id': vf_id,
                                                'remarks': self.tag})
            self.vf_id = output['id']
            self.vf_version = output['version']
            self.vf_uuid = output['uuid']
            self.vf_inputs = inputs

    def create_service_model(self):
        if not self.ns_id and not self.ns_version:
            output = self.ocomp.run(command='service-model-create',
                                    params={'service-name': '{} Service'.format(self.conf['vnf']['name']),
                                            'description': self.tag,
                                            'project-code':  'OCOMP',
                                            'category':  'network l1-3',
                                            'category-display-name':  'Network L1-3',
                                            'icon-id':  'network_l_1-3'})
            ns_id = output['id']

            if not self.ns_vf_resource_id:
                output = self.ocomp.run(command='service-model-add-vf',
                                        params={'service-id': ns_id,
                                                'vf-id': self.vf_id,
                                                'vf-version': self.vf_version,
                                                'vf-name': self.conf['vnf']['name']})
                self.ns_vf_resource_id = output['id']

            # add artifact
            self.ocomp.run(command='service-model-add-artifact',
                                    params={'service-id': ns_id,
                                            'remarks': self.tag,
                                            'artifact': self.conf['vnf']['ns-csar'],
                                            'artifact-name': 'tosca csar'})
            #set property vnfmdriver
            for input in self.vf_inputs:
                if input.endswith('.nf_type'):
                    tkns = input.strip().split('.')
                    input_uuid = tkns[0]
                    self.ocomp.run(command='service-model-set-property',
                                             params={'service-id': ns_id,
                                                     'vf-id': self.vf_id,
                                                     'vf-resource-id': self.ns_vf_resource_id,
                                                     'property-name': 'nf_type',
                                                     'property-value': self.conf['vnf']['vnfm-driver'],
                                                     'input-uuid': input_uuid})
                    break

            self.ocomp.run(command='service-model-test-request',
                                    params={'service-id': ns_id,
                                            'remarks': self.tag})

            self.ocomp.run(command='service-model-test-start',
                                    params={'service-id': ns_id})

        output = self.ocomp.run(command='service-model-test-accept',
                                params={'service-id': ns_id,
                                        'remarks': self.tag})
        self.ns_id = output['id']
        self.ns_version = output['version']
        self.ns_uuid = output['uuid']

        self.ocomp.run(command='service-model-approve',
                                params={'service-id': self.ns_id,
                                        'remarks': self.tag})

        self.ocomp.run(command='service-model-distribute',
                                params={'service-id': self.ns_id})

    def setup_cloud_and_subscription(self):
        associate = False
        if not self.location_id and not self.location_version:
            location_id = 'ocomp-region-{}'.format(self.conf['ONAP']['uid'])
            self.ocomp.run(command='complex-create',
                                    params={'physical-location-id': location_id,
                                            'data-center-code': 'ocomp',
                                            'complex-name': location_id,
                                            'identity-url': self.conf['cloud']['identity-url'],
                                            'physical-location-type': 'phy_type',
                                            'street1': 'ocomp-street1',
                                            'street2': 'ocomp-street2',
                                            'city': 'ocomp-city',
                                            'state': 'ocomp-state',
                                            'postal-code': '001481',
                                            'country': 'abc',
                                            'longitude': '1.0',
                                            'latitude': '1.0',
                                            'region': 'onap',
                                            'elevation': 'ocomp-elelation',
                                            'lata': 'ocomp-lata'})
            self.location_id = location_id
            associate = True

            output = self.ocomp.run(command='complex-list')

            for location in output:
                if location['complex-name'] == self.location_id:
                    self.location_version = location['resource-version']
                    break

        if not self.cloud_id and not self.cloud_version:
            cloud_id = 'OCOMP-{}'.format(self.conf['ONAP']['uid'])
            self.ocomp.run(command='cloud-create',
                                    params={'region-name': self.conf['cloud']['region'],
                                            'complex-name': self.location_id,
                                            'identity-url': self.conf['cloud']['identity-url'],
                                            'cloud-owner': cloud_id,
                                            'cloud-type': 'OpenStack',
                                            'owner-type': 'ocomp',
                                            'cloud-region-version': self.conf['cloud']['version'],
                                            'cloud-zone': 'az1',
                                            'esr-id': cloud_id,
                                            'service-url': self.conf['cloud']['identity-url'],
                                            'username': self.conf['cloud']['username'],
                                            'password': self.conf['cloud']['password'],
                                            'system-type': 'VIM',
                                            'ssl-insecure': 'true',
                                            'cloud-domain': 'Default',
                                            'default-tenant': self.conf['cloud']['tenant'],
                                            'system-status': "active"})
            self.cloud_id = cloud_id
            associate = True

            output = self.ocomp.run(command='cloud-list')

            for cloud in output:
                if cloud['cloud'] == self.cloud_id:
                    self.cloud_version = cloud['resource-version']
                    break

        if associate:
            self.ocomp.run(command='complex-associate',
                                    params={'complex-name': self.location_id,
                                            'cloud-region': self.conf['cloud']['region'],
                                            'cloud-owner': self.cloud_id})

        subscribe = False
        if not self.service_type_id and not self.service_type_version:
            service_type_id = '{}-{}'.format(self.conf['subscription']['service-type'], self.conf['ONAP']['uid'])
            self.ocomp.run(command='service-type-create',
                                params={'service-type': service_type_id,
                                        'service-type-id': service_type_id})
            self.service_type_id = service_type_id
            subscribe = True

            output = self.ocomp.run(command='service-type-list')

            for st in output:
                if st['service-type'] == self.service_type_id:
                    self.service_type_version = st['resource-version']
                    break

        if not self.customer_id and not self.customer_version:
            customer_id = '{}-{}'.format(self.conf['subscription']['customer-name'], self.ocomp.conf['ONAP']['random'])
            self.ocomp.run(command='customer-create',
                                params={'customer-name': customer_id,
                                        'subscriber-name': customer_id})
            self.customer_id = customer_id
            subscribe = True

            output = self.ocomp.run(command='customer-list')

            for customer in output:
                if customer['name'] == self.customer_id:
                    self.customer_version = customer['resource-version']
                    break

        if not self.tenant_id and not self.tenant_version:
            tenant_id = str(uuid.uuid4())
            self.ocomp.run(command='tenant-create',
                                params={'tenant-name': self.conf['cloud']['tenant'],
                                        'tenant-id': tenant_id,
                                        'cloud':self.cloud_id,
                                        'region': self.conf['cloud']['region']})
            self.tenant_id = tenant_id
            subscribe = True

            output = self.ocomp.run(command='tenant-list', params={
                'cloud': self.cloud_id,
                'region': self.conf['cloud']['region']
                })

            for tenant in output:
                if tenant['tenant-id'] == self.tenant_id:
                    self.tenant_version = tenant['resource-version']
                    break

        if subscribe:
            self.ocomp.run(command='subscription-create',
                                    params={'customer-name': self.customer_id,
                                            'cloud-owner': self.cloud_id,
                                            'cloud-region': self.conf['cloud']['region'],
                                            'cloud-tenant-id': self.tenant_id,
                                            'service-type': self.service_type_id,
                                            'tenant-name': self.conf['cloud']['tenant']})

        if not self.subscription_version:
            output = self.ocomp.run(command='subscription-list', params={
                    'customer-name': self.customer_id
                    })

            for subscription in output:
                if subscription['service-type'] == self.service_type_id:
                    self.subscription_version = subscription['resource-version']
                    break

        if not self.esr_vnfm_id and not self.esr_vnfm_version:
            vnfmdriver = self.conf['vnf']['vnfm-driver']

            esr_vnfm_id = str(uuid.uuid4())
            self.ocomp.run(command='vnfm-create',
                                    params={'vim-id': self.cloud_id,
                                            'vnfm-id': esr_vnfm_id,
                                            'name': 'OCOMP {}'.format(vnfmdriver),
                                            'type': vnfmdriver,
                                            'vendor': self.conf['vnf']['vendor-name'],
                                            'vnfm-version': self.conf['vnfm'][vnfmdriver]['version'],
                                            'url': self.conf['vnfm'][vnfmdriver]['url'],
                                            'username': self.conf['vnfm'][vnfmdriver]['username'] ,
                                            'password': self.conf['vnfm'][vnfmdriver]['password']})
            self.esr_vnfm_id = esr_vnfm_id

        output = self.ocomp.run(command='vnfm-list')

        for vnfm in output:
            if vnfm['vnfm-id'] == self.esr_vnfm_id:
                self.esr_vnfm_version = vnfm['resource-version']
                break

#         self.ocomp.run(command='multicloud-register-cloud',
#                                 params={'cloud-region': self.conf['cloud']['region'],
#                                         'cloud-owner': self.cloud_id})

    def create_vnf(self):
        self.ocomp.run(command='vfc-catalog-onboard-vnf',
                                params={'vnf-csar-uuid': self.vf_uuid})

        self.ocomp.run(command='vfc-catalog-onboard-ns',
                                params={'ns-csar-uuid': self.ns_uuid})

        output = self.ocomp.run(command='vfc-nslcm-create',
                                params={'ns-csar-uuid': self.ns_uuid,
                                        'ns-csar-name': '{} Service'.format(self.conf['vnf']['name']),
                                        'customer-name': self.customer_id,
                                        'service-type': self.service_type_id})

        self.ns_instance_id = output['ns-instance-id']

        vnfmdriver = self.conf['vnf']['vnfm-driver']
        self.ocomp.run(command='vfc-nslcm-instantiate',
                                params={'ns-instance-id': self.ns_instance_id,
                                        'location': self.cloud_id,
                                        'sdn-controller-id': self.esr_vnfm_id})

    def vnf_status_check(self):
        self.vnf_status = 'active'
        self.ns_instance_status = 'active'

    def cleanup(self):
        if self.ns_instance_id:
            self.ocomp.run(command='vfc-nslcm-terminate',
                              params={'ns-instance-id': self.ns_instance_id})
            self.ocomp.run(command='vfc-nslcm-delete',
                              params={'ns-instance-id': self.ns_instance_id})
            self.ns_instance_id = None

        if self.ns_id:
            self.ocomp.run(command='service-model-archive',
                              params={'service-id': self.ns_id})
            self.ns_id = self.ns_uuid = self.ns_version = self.ns_vf_resource_id = None

        if self.vf_id:
            self.ocomp.run(command='vf-model-archive',
                              params={'vf-id': self.vf_id})
            self.vf_id = self.vf_uuid = self.vf_inputs = self.vf_version = None

        if self.vsp_id:
            self.ocomp.run(command='vsp-archive',
                              params={'vsp-id': self.vsp_id})
            self.vsp_id = self.vsp_version_id = self.vsp_version = None

        if self.vlm_id:
            self.ocomp.run(command='vlm-archive',
                              params={'vlm-id': self.vlm_id})
            self.vlm_id = self.vlm_version = self.entitlement_id = self.key_group_id = self.feature_group_id = self.agreement_id = None

        if self.subscription_version and self.customer_id and self.service_type_id:
            self.ocomp.run(command='subscription-delete',
                              params={'customer-name': self.customer_id,
                                      'service-type': self.service_type_id,
                                      'resource-version': self.subscription_version})
            self.subscription_version = None

        if self.customer_id and self.customer_version:
            self.ocomp.run(command='customer-delete',
                              params={'customer-id': self.customer_id,
                                      'resource-version': self.customer_version})
            self.customer_id = self.customer_version = None

        if self.service_type_id and self.service_type_version:
            output = self.ocomp.run(command='service-type-list')

            for st in output:
                if st['service-type-id'] == self.service_type_id:
                    self.service_type_version = st['resource-version']
                    break

            self.ocomp.run(command='service-type-delete',
                              params={'service-type-id': self.service_type_id,
                                      'resource-version': self.service_type_version})
            self.service_type_id = self.service_type_version = None

        if self.tenant_id and self.tenant_version:
            self.ocomp.run(command='tenant-delete',
                              params={'cloud': self.cloud_id,
                                      'region': self.conf['cloud']['region'],
                                      'tenant-id': self.tenant_id,
                                      'resource-version': self.tenant_version})
            self.tenant_id = self.tenant_version = None

        if self.cloud_id and self.location_id:
            self.ocomp.run(command='complex-disassociate',
                              params={'cloud-owner': self.cloud_id,
                                      'cloud-region': self.conf['cloud']['region'],
                                      'complex-name': self.location_id})

        if self.cloud_id and self.cloud_version:
            output = self.ocomp.run(command='cloud-list')

            for c in output:
                if c['cloud'] == self.cloud_id and c['region'] == self.conf['cloud']['region']:
                    self.cloud_version = c['resource-version']
                    break

            self.ocomp.run(command='cloud-delete',
                              params={'cloud-name': self.cloud_id,
                                      'region-name': self.conf['cloud']['region'],
                                      'resource-version': self.cloud_version})
            self.cloud_id = self.cloud_version = None

        if self.location_id and self.location_version:
            self.ocomp.run(command='complex-delete',
                              params={'complex-name': self.location_id,
                                      'resource-version': self.location_version})
            self.location_id = self.location_version = None

        if self.esr_vnfm_id and self.esr_vnfm_version:
            self.ocomp.run(command='vnfm-delete',
                              params={'vnfm-id': self.esr_vnfm_id,
                                      'resource-version': self.esr_vnfm_version})
            self.esr_vnfm_id = self.esr_vnfm_version = None

    def __str__(self):
        return  str(vars(self))

#Main
if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="ONAP TOSCA VNF validation using ONAP CLI and Open Command Platform (OCOMP)", formatter_class=RawTextHelpFormatter)
    parser.add_argument('--product', action='store', dest='product', help='OCOMP product to use, default to onap-dublin',
                        default=os.environ.get('OPEN_CLI_PRODUCT_IN_USE'))
    parser.add_argument('--profile', action='store', dest='profile', help='OCOMP profile to use, default to onap-dublin',
                        default=os.environ.get('OPEN_CLI_PROFILE'))
    parser.add_argument('--request-id', action='store', dest='request_id',
                        help='Request Id to track the progress of running this script',
                        default=os.environ.get('OPEN_CLI_REQUEST_ID'))
    parser.add_argument('--conf', action='store', dest='config_file_path', help='Configuration file path')
    parser.add_argument('--vsp', action='store', dest='vsp', help='ONAP VSP file path')
    parser.add_argument('--vnf-csar', action='store', dest='vnf_csar', help='TOSCA VNF CSAR file path')
    parser.add_argument('--ns-csar', action='store', dest='ns_csar', help='TOSCA VNF CSAR file path')
    parser.add_argument('--vnfm-driver', action='store', dest='vnfm_driver', help='VNFM dirver type one of gvnfmdriver or hwvnfmdriver',
                        choices=('gvnfmdriver', 'hwvnfmdriver'))
    parser.add_argument('--vnf-name', action='store', dest='vnf_name', help='VNF Name')
    parser.add_argument('--vendor-name', action='store', dest='vendor_name', help='VNF Vendor name')
    parser.add_argument('--result-json', action='store', dest='result', help='Result json file. ' \
                                    '\nInstead of creating new ONAP objects while running this script \nand to use the existing ONAP object Ids, '\
                                    'use this \nresult json parameter. Object Id names are provided in configuration \nfile under ONAP section')
    parser.add_argument('--mode', action='store', dest='mode', help='Supports 5 mode.'\
                        '\nsetup - Create the required VLM, service type, cloud, customer and \nsubscription as given in conf file' \
                        '\nstandup - Create the VSP, VF Model, Service Model and provision\n the service using VFC'\
                        '\ncleanup - Remove the ONAP objects which are either created during \nsetup and standup phase or provided by the user in result-json file ' \
                                   '\nCAUTION: If required, do not provide the existing ONAP object ids \nin result-json while doing the cleanup, to avoid them getting deleted.'\
                        '\ncheckup - Check the deployment weather OCOMP is working properly or not' \
                        '\nprovision - Run thru setup -> standup' \
                        '\nvalidate -  run thru setup -> standup -> cleanup modes for end to end vnf validation',
                                   choices=('setup', 'standup', 'cleanup', 'checkup', 'provision', 'validate'))

    args = parser.parse_args()
    print (args)

    if not args.product:
        product = 'onap-dublin'
    else:
        product = args.product

    if not args.profile:
        profile = 'onap-dublin'
    else:
        profile = args.profile

    request_id = args.request_id
    if not request_id:
        request_id = str(uuid.uuid4())
    vsp_csar = args.vsp
    vnf_csar = args.vnf_csar
    ns_csar = args.ns_csar
    if args.mode:
        mode = args.mode
    else:
        mode = 'checkup'

    if args.vnfm_driver:
        vnfm_driver = args.vnfm_driver
    else:
        vnfm_driver = 'gvnfmdriver'

    if args.vnf_name:
        vnf_name = args.vnf_name
    else:
        vnf_name = None

    if args.vendor_name:
        vendor_name = args.vendor_name
    else:
        vendor_name = None

    conf = {}
    config_file = args.config_file_path
    with open(config_file) as json_file:
        conf = json.load(json_file)
        if not 'uid' in conf['ONAP']:
            conf['ONAP']['uid'] = ''.join(random.sample(string.ascii_lowercase,5))
        if vsp_csar:
            conf['vnf']['vsp-csar'] = vsp_csar
        if vnf_csar:
            conf['vnf']['vnf-csar'] = vnf_csar
        if ns_csar:
            conf['vnf']['ns-csar'] = vnf_csar
        if vnf_name:
            conf['vnf']['name'] = vnf_name
        conf['vnf']['name'] = '{}{}'.format(conf['vnf']['name'], conf['ONAP']['uid'])
        if vendor_name:
            conf['vnf']['vendor-name'] = vendor_name
        conf['vnf']['vendor-name'] = '{}-{}'.format(conf['vnf']['vendor-name'], conf['ONAP']['uid'])

    if args.result:
        result_file = args.result
        with open(result_file) as r_file:
            result_json = json.load(r_file)
            for r in result_json:
                if r in conf['ONAP']:
                    conf['ONAP'][r] = result_json[r]
    else:
        result_file = None

    print (OCOMP.version())

    onap = ONAP(product, profile, conf, request_id)

    def _setup():
        onap.create_vlm()
        onap.setup_cloud_and_subscription()

    def _standup():
        onap.create_vsp()
        onap.create_vf_model()
        onap.create_service_model()
        onap.create_vnf()
        onap.vnf_status_check()

    def _cleanup():
        onap.cleanup()

    try:
        if mode == 'setup':
            _setup()
        elif mode == 'standup':
            _standup()
        elif mode == 'cleanup':
            _cleanup()
        elif mode == 'checkup':
            onap.ocomp.product = 'open-cli'
            onap.ocomp.run(command='schema-list', params={'product': 'open-cli'})
        elif mode == 'provision':
           _setup()
           _standup()
        elif mode == 'validate':
           _setup()
           _standup()
           _cleanup()

        print ('Done')
    finally:
        onap_result = json.dumps(onap, default=lambda x: x.__dict__)
        print(onap_result)

        if result_file:
            #Remove conf and ocomp from the onap object
            for attr in ['ocomp', 'tag', 'conf']:
                delattr(onap, attr)

            with open(result_file, "w") as f:
                f.write(json.dumps(onap, default=lambda x: x.__dict__))