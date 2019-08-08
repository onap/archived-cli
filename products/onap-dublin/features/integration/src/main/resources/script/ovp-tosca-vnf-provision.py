#!/usr/bin/python

import json
import os
import time
import argparse
import sys
import requests

def get_parameters(file):
    parameters = json.load(file)
    return parameters

def get_out_helper(in_string):
    out_list = (((in_string.replace('-','')).replace('|', '')).replace('+', '')).split()
    return out_list

def get_out_helper_2(in_string):
    out_list = ((in_string.replace('|', '')).replace('+', '')).split()
    return out_list

def set_open_cli_env(parameters):
    os.environ["OPEN_CLI_PRODUCT_IN_USE"] = parameters["open_cli_product"]
    os.environ["OPEN_CLI_HOME"] = parameters["open_cli_home"]

def create_complex(parameters):
    complex_create_string = "oclip --request-id {} complex-create --debug -j {} -r {} -x {} -y {} -lt {} -l {} -i {} -lo {} \
                         -S {} -la {} -g {} -w {} -z {} -k {} -o {} -q {} -m {} -u {} -p {}".format(request_id, parameters["street2"], \
                          parameters["physical_location"], parameters["complex_name"], \
                          parameters["data_center_code"], parameters["latitude"], parameters["region"], \
                          parameters["street1"], parameters["longitude"], parameters["state"], \
                          parameters["lata"], parameters["city"], parameters["postal-code"], \
                          parameters["complex_name"], parameters["country"], parameters["elevation"], \
                          parameters["identity_url"], parameters["aai_url"], parameters["aai_username"], \
                          parameters["aai_password"])

    os.system(complex_create_string)

def create_vlm(parameters):
    vlm_create_string = "oclip  --request-id {} vlm-create  --debug -x {} -u {} -p {} -m {}".format(request_id, parameters["vendor-name"], \
      parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_onboarding_url"])
    command_out = (os.popen(vlm_create_string)).read()
    out_list = get_out_helper(command_out)
    vlm_id = out_list[3]
    vlm_version = out_list[5]

    entitlement_string = "oclip  --request-id {} vlm-entitlement-pool-create  --debug -x {} -y {} -e {} -z {} -k {} -g {} -l {} -u {} -p {} -m {}".format(request_id,  \
      parameters["entitlement-pool-name"], vlm_id, vlm_version, parameters["entitlement-description"], parameters["vendor-name"], \
      parameters["expiry-date"], parameters["start-date"],  parameters["sdc_creator"], parameters["sdc_password"], \
      parameters["sdc_onboarding_url"])
    command_out = (os.popen(entitlement_string)).read()
    entitlement_id = (get_out_helper(command_out))[3]


    key_group_string = "oclip  --request-id {} vlm-key-group-create  --debug -c {} -e {} -x {} -y {} -u {} -p {} -m {}".format(request_id, vlm_id, vlm_version, \
      parameters["key-group-name"], parameters["key-group-type"],  parameters["sdc_creator"], parameters["sdc_password"], \
      parameters["sdc_onboarding_url"])
    command_out = (os.popen(key_group_string)).read()
    key_group_id = (get_out_helper(command_out))[3]

    feature_group_string = "oclip --request-id {} vlm-feature-group-create  --debug -x {} -y {} -e {} -z {} -g {} -b {} -c {} -u {} -p {} -m {}".format(request_id,
      parameters["feature-grp-name"], vlm_id, vlm_version, parameters["feature-grp-desc"], key_group_id, entitlement_id, \
      parameters["part-no"], parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_onboarding_url"])
    command_out = (os.popen(feature_group_string)).read()
    feature_group_id = (get_out_helper(command_out))[3]

    agreement_string = "oclip --request-id {} vlm-aggreement-create  --debug -x {} -y {} -e {} -z {} -g {} -u {} -p {} -m {}".format(request_id, parameters["agreement-name"], \
      vlm_id, vlm_version, parameters["agreement-desc"], feature_group_id, parameters["sdc_creator"], parameters["sdc_password"], \
      parameters["sdc_onboarding_url"])
    command_out = (os.popen(agreement_string)).read()
    agreement_id = (get_out_helper(command_out))[3]

    submit_string = "oclip --request-id {}  vlm-submit  --debug -x {} -y {} -u {} -p {} -m {}".format(request_id, vlm_id, vlm_version, parameters["sdc_creator"], \
      parameters["sdc_password"], parameters["sdc_onboarding_url"])
    os.system(submit_string)

    output = [feature_group_id, agreement_id, vlm_version, vlm_id ]
    return output

def create_vsp(parameters, in_list):
    vnfs = parameters["vnf"]
    vsp_dict = {}
    for name, value in vnfs.iteritems():
        create_string = "oclip --request-id {} vsp-create  --debug -j {} -o {} -e {} -x {} -y {} -i {} -c {} -g {} -u {} -p {} -m {}".format(request_id,  in_list[0], \
          parameters["onboarding-method"], parameters["vendor-name" ], value.get("vsp-name"), value.get("vsp-desc"), in_list[1], \
          in_list[2], in_list[3], parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_onboarding_url"] )
        command_out = (os.popen(create_string)).read()
        out_list = get_out_helper(command_out)
        vsp_id = out_list[3]
        vsp_version = out_list[5]

        os.system("oclip --request-id {} vsp-add-artifact  --debug -x {} -y {} -z {} -u {} -p {} -m {}".format(request_id, vsp_id, vsp_version, value.get("path"), \
          parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_onboarding_url"]))

        os.system("oclip --request-id {} vsp-validate  --debug -x {} -y {} -u {} -p {} -m {}".format(request_id, vsp_id, vsp_version, parameters["sdc_creator"], \
          parameters["sdc_password"], parameters["sdc_onboarding_url"]))

        os.system("oclip --request-id {} vsp-submit  --debug -x {} -y {} -u {} -p {} -m {}".format(request_id, vsp_id, vsp_version, parameters["sdc_creator"], \
          parameters["sdc_password"], parameters["sdc_onboarding_url"]))

        os.system("oclip --request-id {} vsp-package  --debug -x {} -y {} -u {} -p {} -m {}".format(request_id, vsp_id, vsp_version, parameters["sdc_creator"], \
          parameters["sdc_password"], parameters["sdc_onboarding_url"]))

        vsp_ids[name] = vsp_id
    return vsp_ids

def create_vf_model(parameters, vsp_dict):
    vnfs = parameters["vnfs"]
    vf_dict = {}
    for name, value in vnfs.iteritems():
        create_string = "oclip --request-id {} vf-model-create  --debug -y {} -g {} -x {} -z {} -b {} -u {} -p {} -m {}".format(request_id, value.get("vf-description"), \
          value.get("vsp-version"), value.get("vf-name"), parameters["vendor-name"], vsp_dict[name], \
          parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_catalog_url"])
        os.system(create_string)

        output = (os.popen("oclip --request-id {} vf-model-list --debug  -m {} -u {} -p {} | grep {}".format(request_id, parameters["sdc_catalog_url"], \
          parameters["sdc_creator"], parameters["sdc_password"], value.get("vf-name")))).read()
        output = (get_out_helper_2(output))

        vf_unique_id = output[1]

        # checkout
        output = (os.popen("oclip --request-id {} vf-model-checkout  --debug --host-url {} --host-username {} --host-password {} --vf-model-uuid {} --format json".format(request_id,
             parameters["sdc_catalog_url"], parameters["sdc_creator"], parameters["sdc_password"], vf_unique_id))).read()
        vf_unique_id = (json.loads(output))['ID']

        # add consumer, if already exist , just ignore, below command would fail simply !!
        os.system("oclip --request-id {} sdc-consumer-create  --debug --consumer-name {}".format(request_id, parameters["sdc_consumer"]))

        def get_vnf_name(vnf_csar_path):
            return vnf_csar_path.replace("_"," ").replace(".", " ").replace("-"," ")

        # add artifact
        os.system("oclip --request-id {} vf-model-add-artifact  --debug --host-url {} --host-username {} --host-password {} --vf-model-uuid {} --artifact {} --artifact-name {}".format(
             request_id, parameters["sdc_catalog_url"], parameters["sdc_consumer"], parameters["sdc_password"], vf_unique_id, vnf_csar, get_vnf_name(vnf_csar)))

        # check-in
        os.system("oclip --request-id {} vf-model-checkin  --debug --host-url {} --host-username {} --host-password {} --vf-model-uuid {}".format(
             request_id, parameters["sdc_catalog_url"], parameters["sdc_creator"], parameters["sdc_password"], vf_unique_id))

        os.system("oclip --request-id {} vf-model-certify  --debug -b {} -r {} -u {} -p {} -m {}".format(vf_unique_id, value.get("vf-remarks"), \
          request_id, parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_catalog_url"]))

        #Check for new parameters after certification
        output = (os.popen("oclip --request-id {} vf-model-list  --debug  -m {} -u {} -p {} | grep {}".format(request_id, parameters["sdc_catalog_url"], \
                              parameters["sdc_creator"], parameters["sdc_password"], value.get("vf-name")))).read()
        output = (get_out_helper_2(output))

        vf_dict[name] = output[1]

    return vf_dict


def create_service_model(parameters, vf_dict):
    vnfs = parameters["vnfs"]

    create_string = "oclip --request-id {} service-model-create  --debug -z {} -y {} -e {} -x {} -c {} -b {} -u {} -p {} -m {} |grep ID".format(request_id, parameters["project-code"], \
    parameters["service-model-desc"], parameters["icon-id"], parameters["service-model-name"], parameters["category-display"], \
    parameters["category"],parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_catalog_url"])

    service_model_id = (get_out_helper_2((os.popen(create_string)).read()))[1]

    # checkout
    output = (os.popen("oclip --request-id {} service-model-checkout --debug  --host-url {} --host-username {} --host-password {} --service-model-uuid {} --format json".format(request_id,
         parameters["sdc_catalog_url"], parameters["sdc_creator"], parameters["sdc_password"], service_model_id))).read()
    service_model_id = (json.loads(output))['ID']

    # add consumer, if already exist , just ignore, below command would fail simply !!
    os.system("oclip --request-id {} sdc-consumer-create - --debug -consumer-name {}".format(request_id, parameters["sdc_consumer"]))

    for name, value in vnfs.iteritems():
        output = (os.popen("oclip --request-id {} service-model-add-vf  --debug -x {} -b {} -y {} -z {} -u {} -p {} -m {}".format(request_id, service_model_id, \
                   parameters["vf-version"], vf_dict[name], value.get("vf-name"), parameters["sdc_creator"], \
                   parameters["sdc_password"], parameters["sdc_catalog_url"] ))).read()
        vf_unique_id = (json.loads(output))['ID']
        vf_uuid = (json.loads(output))['UUID']

        def get_ns_name(ns_csar_path):
            return vnf_csar_path.replace("_"," ").replace(".", " ").replace("-"," ")

        # add artifact
        os.system("oclip --request-id {} service-model-add-artifact  --debug --host-url {} --host-username {} --host-password {} --service-uniqueId {} --artifact {} --artifact-name {}".format(
             request_id, parameters["sdc_catalog_url"], parameters["sdc_consumer"], parameters["sdc_password"], vf_unique_id, ns_csar, get_vnf_name(ns_csar)))

        # set properties
        os.system("oclip --request-id {} service-model-set-property --debug  --host-url {} --host-username {} --host-password {} --service-uniqueId {} --resource-uuid {} --resource-uniqueId {} --property-name nf_type --property-value {}".format(
            request_id, parameters["sdc_catalog_url"], parameters["sdc_consumer"], parameters["sdc_password"], service_model_id , vf_unique_id, vf_uuid, vnfm_driver))

    # check-in
    os.system("oclip --request-id {} service-model-checkin  --debug --host-url {} --host-username {} --host-password {} --service-model-uuid {}".format(
         request_id, parameters["sdc_catalog_url"], parameters["sdc_creator"], parameters["sdc_password"], service_model_id))


    os.system("oclip --request-id {} service-model-test-request  --debug -b {} -r {} -u {} -p {} -m {}".format(service_model_id, parameters["service-test-remarks"], \
    request_id, parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_catalog_url"]))

    os.system("oclip --request-id {} service-model-test-start  --debug -b {} -u {} -p {} -m {}".format(service_model_id, parameters["sdc_tester"], \
    request_id, parameters["sdc_password"], parameters["sdc_catalog_url"]))

    os.system("oclip --request-id {} service-model-test-accept  --debug -b {} -r {} -u {} -p {} -m {}".format(service_model_id, parameters["service-accept-remarks"], \
    request_id, parameters["sdc_tester"], parameters["sdc_password"], parameters["sdc_catalog_url"]))

    #Get uniqueId for the service model
    service_model_values = (os.popen("oclip --request-id {} service-model-list  --debug -u {} -p {} -m {} |grep {}".format(request_id, parameters["sdc_creator"], \
      parameters["sdc_password"], parameters["sdc_catalog_url"], parameters["service-model-name"]))).read()
    service_model_values = get_out_helper_2(service_model_values)
    service_model_uniqueId = (service_model_values)[1]

    os.system("oclip --request-id {} service-model-approve --debug  -b {} -r {} -u {} -p {} -m {}".format(request_id, service_model_uniqueId, parameters["service-approve-remarks"], \
    parameters["sdc_governor"], parameters["sdc_password"], parameters["sdc_catalog_url"]))

    os.system("oclip --request-id {} service-model-distribute  --debug -b {} -u {} -p {} -m {}".format(request_id, service_model_uniqueId, parameters["sdc_operator"], \
    parameters["sdc_password"], parameters["sdc_catalog_url"]))

    return service_model_values

def register_cloud_helper(cloud_region, values, parameters):
    #Create Cloud
    cloud_create_string = 'oclip --request-id {} cloud-create  --debug -e {} -b {} -I {{\\\\\\"openstack-region-id\\\\\\":\\\\\\"{}\\\\\\"}} \
    -x {} -y {} -j {} -w {} -l {} -url {} -n {} -q {} -r {} -Q {} -i {} -g {} -z {} -k {} -c {} -m {} -u {} -p {}'.format(request_id,
      values.get("esr-system-info-id"), values.get("user-name"), cloud_region, parameters["cloud-owner"], \
      cloud_region, values.get("password"), values.get("cloud-region-version"), values.get("default-tenant"), \
      values.get("service-url"), parameters["complex_name"], values.get("cloud-type"), parameters["owner-defined-type"], \
      values.get("system-type"), values.get("identity-url"), parameters["cloud-zone"], values.get("ssl-insecure"), \
      values.get("system-status"), values.get("cloud-domain"), parameters["aai_url"], parameters["aai_username"], \
      parameters["aai_password"])


    os.system(cloud_create_string)

    #Associate Cloud with complex
    complex_associate_string = "oclip --request-id {} complex-associate  --debug -x {} -y {} -z {} -m {} -u {} -p {}".format(request_id, parameters["complex_name"], \
      cloud_region, parameters["cloud-owner"], parameters["aai_url"], parameters["aai_username"], parameters["aai_password"])
    os.system(complex_associate_string)

    #Register Cloud with Multicloud
    multicloud_register_string = "oclip --request-id {} multicloud-register-cloud  --debug -y {} -x {} -m {}".format(request_id, parameters["cloud-owner"], \
      cloud_region, parameters["multicloud_url"])
    os.system(multicloud_register_string)

def register_all_clouds(parameters):
    cloud_dictionary = parameters["cloud_region_data"]
    for cloud_region, cloud_region_values in cloud_dictionary.iteritems():
        register_cloud_helper(cloud_region, cloud_region_values, parameters)

def create_service_type(parameters):
    create_string = "oclip --request-id {} service-type-create  --debug -x {} -m {} -u {} -p {}".format(request_id,  parameters["service_name"], \
      parameters["aai_url"], parameters["aai_username"], parameters["aai_password"])
    os.system(create_string)

def create_customer(parameters):
    create_string = "oclip --request-id {} customer-create  --debug -x {} -y {} -m {} -u {} -p {}".format(request_id,  parameters["customer_name"], \
    parameters["subscriber_name"], parameters["aai_url"], parameters["aai_username"], parameters["aai_password"])
    os.system(create_string)

def add_customer_subscription(parameters):
    subscription_check = 0
    for cloud_region, cloud_region_values in (parameters["cloud_region_data"]).iteritems():
      if subscription_check == 0 :
        subscription_string = "oclip --request-id {} subscription-create  --debug -x {} -c {} -z {} -e {} -y {} -r {} -m {} -u {} -p {}".format(request_id, \
          parameters["customer_name"], cloud_region_values.get("tenant-id"), parameters["cloud-owner"], parameters["service_name"],\
          cloud_region_values.get("default-tenant"), cloud_region, parameters["aai_url"], parameters["aai_username"], parameters["aai_password"] )
      else:
        subscription_string = "oclip --request-id {} subscription-cloud-add  --debug -x {} -c {} -z {} -e {} -y {} -r {} -m {} -u {} -p {}".format(request_id, \
          parameters["customer_name"], cloud_region_values.get("tenant-id"), parameters["cloud-owner"], parameters["service_name"],\
          cloud_region_values.get("default-tenant"), cloud_region, parameters["aai_url"], parameters["aai_username"], parameters["aai_password"] )
      os.system(subscription_string)
      subscription_check+=1

def register_vnfm_helper(vnfm_key, values, parameters):
    #Create vnfm
    vnfm_create_string = 'oclip --request-id {} vnfm-create  --debug -b {} -c {} -e {} -v {} -g {} -x {} -i {} -j {} -q {} \
    -m {} -u {} -p {}'.format(request_id, vnfm_key, values.get("type"), values.get("vendor"), \
      values.get("version"), values.get("url"), values.get("vim-id"), \
      values.get("user-name"), values.get("user-password"), values.get("vnfm-version"), \
      parameters["aai_url"], parameters["aai_username"], parameters["aai_password"])

    os.system(vnfm_create_string)

def register_vnfm(parameters):
    vnfm_params = parameters["vnfm_params"]
    for vnfm_key, vnfm_values in vnfm_params.iteritems():
        register_vnfm_helper(vnfm_key, vnfm_values, parameters)


def onboard_vnf(parameters):
    vnfs = parameters["vnfs"]
    vnf_onboard_outputs = {}

    for key, value in vnfs.items():
        vnf_onboard_string = 'oclip --request-id {} vfc-catalog-onboard-vnf  --debug -c {}'.format(request_id, value.get("csar-id"))
        vnf_onboard_outs[key] = (os.popen(ns_onboard_string)).read()
    return vnf_onboard_outputs

def onboard_ns(parameters):
    ns_onboard_string = 'oclip --request-id {} vfc-catalog-onboard-ns  --debug -c {}'.format(request_id, parameters["ns-csar-id"])
    ns_onboard_out = (os.popen(ns_onboard_string)).read()
    return ns_onboard_out

def create_ns(parameters, csar_id):
    ns = parameters["ns"]
    ns_create_string = 'oclip --request-id {} vfc-nslcm-create --debug  -m {} -c {} -n {} -q {} -S {}'.format(request_id, parameters["vfc-url"], \
       csar_id, ns.get("name"), parameters["customer_name"], parameters["service_name"])
    print (ns_create_string)

    ns_create_out = (os.popen(ns_create_string)).read()

    print (ns_create_out)

    ns_instance_id = (get_out_helper_2(ns_create_out))[3]

    return ns_instance_id

def instantiate_ns(parameters, ns_instance_id):
    ns_instantiate_string = 'oclip --request-id {} vfc-nslcm-instantiate --debug  -m {} -i {} -c {} -n {}'.format(request_id, parameters["vfc-url"], \
        ns_instance_id, parameters["location"], parameters["sdc-controller-id"])
    print (ns_instantiate_string)

    ns_instantiate_out = (os.popen(ns_instantiate_string)).read()
    return ns_instantiate_out

def create_ns_package(parameters):
    ns = parameters["ns"]
    create_ns_string = 'oclip --request-id {} vfc-catalog-create-ns --debug  -m {} -c {} -e {}'.format(request_id, parameters["vfc-url"], \
      ns.get("key"), ns.get("value"))
    cmd_out = (os.popen(create_ns_string)).read()
    out_list =  get_out_helper_2(cmd_out)
    return out_list[4]

def create_vnf_package(parameters):
    vnfs = parameters["vnfs"]
    outputs = {}

    for vnf_key, vnf_values in vnfs.iteritems():
        create_vnf_string = 'oclip --request-id {} vfc-catalog-create-vnf  --debug -m {} -c {} -e {}'.format(request_id, parameters["vfc-url"], \
          vnf_values.get("key"), vnf_values.get("value"))
        cmd_out = (os.popen(create_vnf_string)).read()
        out_list =  get_out_helper_2(cmd_out)
        outputs[vnf_key] = out_list[4]

    return outputs

def provision_vnf():

    result_json = {}
    result_json['vnf_id'] = ''
    result_json['vnf_status'] = ''
    result_json['ns_id'] = ''
    result_json['ns_status'] = ''

    def _provision():
        # 1.Setup cloud and service
        #setup cloud and region
        create_complex(parameters)
        register_all_clouds(parameters)

        # setup subscription and customer
        create_service_type(parameters)
        create_customer(parameters)
        add_customer_subscription(parameters)

        # setup vnfm
        register_vnfm(parameters)

        # Setup License
        vlm_output = create_vlm(parameters)
        print "vlm parameters={}".format(vlm_output)

        # 2. on-board VSP
        vsp_id = create_vsp(parameters, vlm_output)
        print "vsp id={}".format(vsp_id)

        # 3. model VF
        vf_model_dict = create_vf_model(parameters, vsp_id)
        print "vf model parameters={}".format(vf_model_dict)
        vf_id = vf_model_dict["vf_id"]
        vf_unique_id = vf_model_dict["vf_unique_id"]

        # 4. model NS and 5. Approve and distribute
        service_model_list = create_service_model(parameters, vf_unique_id)
        print "service model parameters={}".format(service_model_list)

        # 6. onboard VNF
        vnf_onboard_output = onboard_vnf(parameters)
        print vnf_onboard_output

        # 7. onboard NS
        ns_onboard_out = onboard_ns(parameters)
        print ns_onboard_out

        #TODO is create vnf and ns pacakge required ??
        # 8. create NS
        ns_instance_id = create_ns(parameters, ns_package_output)
        print ns_instance_id

        instantiate_ns_output = instantiate_ns(parameters, ns_instance_id)
        print instantiate_ns_output

    _provision()

    return result_json

def persist_result(result_path, result):
    f = open(result_json_path, "w")

    result_json = {}
    result_json['vnf_id'] = result['vnf_id']
    result_json['vnf_status'] = result['vnf_status']
    result_json['ns_id'] = result['ns_id']
    result_json['ns_status'] = result['ns_status']

    f.write(json.dumps(result_json))

    print result_json

    f.close()

#Main
parser = argparse.ArgumentParser()
parser.add_argument('--config-json', action='store', dest='config_file_path', help='Config file path')
parser.add_argument('--vsp', action='store', dest='vsp', help='ONAP VSP')
parser.add_argument('--vnf-csar', action='store', dest='vnf_csar', help='VNF CSAR')
parser.add_argument('--ns-csar', action='store', dest='ns_csar', help='NS CSAR')
parser.add_argument('--vnfm-driver', action='store', dest='vnfm_driver', help='gVNFM or hwVNFM')
parser.add_argument('--result-json', action='store', dest='result_json_path', help='Output result json')
parser.add_argument('--request-id', action='store', dest='request_id', help='Request Id to track')

#Retrive the params
args = parser.parse_args()
print args

config_file = open(args.config_file_path)
vsp = args.vsp
vnf_csar = args.vnf_csar
ns_csar = args.ns_csar
vnfm_driver = args.vnfm_driver
result_json_path = args.result_json_path
request_id = args.request_id

# setup CLI
parameters = get_parameters(config_file)
set_open_cli_env(parameters)

output = provision_vnf()

persist_result(result_json_path, output)

print "Provision is completed !"