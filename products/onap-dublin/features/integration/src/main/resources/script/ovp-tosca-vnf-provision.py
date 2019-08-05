#!/usr/bin/python

import json
import os
import time
import argparse
import sys
import requests
import __main__

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
    complex_create_string = "oclip complex-create -j {} -r {} -x {} -y {} -lt {} -l {} -i {} -lo {} \
                         -S {} -la {} -g {} -w {} -z {} -k {} -o {} -q {} -m {} -u {} -p {}".format(parameters["street2"], \
                          parameters["physical_location"], parameters["complex_name"], \
                          parameters["data_center_code"], parameters["latitude"], parameters["region"], \
                          parameters["street1"], parameters["longitude"], parameters["state"], \
                          parameters["lata"], parameters["city"], parameters["postal-code"], \
                          parameters["complex_name"], parameters["country"], parameters["elevation"], \
                          parameters["identity_url"], parameters["aai_url"], parameters["aai_username"], \
                          parameters["aai_password"])

    os.system(complex_create_string)

def create_vlm(parameters):
    vlm_create_string = "oclip vlm-create -x {} -u {} -p {} -m {}".format(parameters["vendor-name"], \
      parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_onboarding_url"])
    command_out = (os.popen(vlm_create_string)).read()
    out_list = get_out_helper(command_out)
    vlm_id = out_list[3]
    vlm_version = out_list[5]

    entitlement_string = "oclip vlm-entitlement-pool-create -x {} -y {} -e {} -z {} -k {} -g {} -l {} -u {} -p {} -m {}".format( \
      parameters["entitlement-pool-name"], vlm_id, vlm_version, parameters["entitlement-description"], parameters["vendor-name"], \
      parameters["expiry-date"], parameters["start-date"],  parameters["sdc_creator"], parameters["sdc_password"], \
      parameters["sdc_onboarding_url"])
    command_out = (os.popen(entitlement_string)).read()
    entitlement_id = (get_out_helper(command_out))[3]


    key_group_string = "oclip vlm-key-group-create -c {} -e {} -x {} -y {} -u {} -p {} -m {}".format(vlm_id, vlm_version, \
      parameters["key-group-name"], parameters["key-group-type"],  parameters["sdc_creator"], parameters["sdc_password"], \
      parameters["sdc_onboarding_url"])
    command_out = (os.popen(key_group_string)).read()
    key_group_id = (get_out_helper(command_out))[3]

    feature_group_string = "oclip vlm-feature-group-create -x {} -y {} -e {} -z {} -g {} -b {} -c {} -u {} -p {} -m {}".format(
      parameters["feature-grp-name"], vlm_id, vlm_version, parameters["feature-grp-desc"], key_group_id, entitlement_id, \
      parameters["part-no"], parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_onboarding_url"])
    command_out = (os.popen(feature_group_string)).read()
    feature_group_id = (get_out_helper(command_out))[3]

    agreement_string = "oclip vlm-aggreement-create -x {} -y {} -e {} -z {} -g {} -u {} -p {} -m {}".format(parameters["agreement-name"], \
      vlm_id, vlm_version, parameters["agreement-desc"], feature_group_id, parameters["sdc_creator"], parameters["sdc_password"], \
      parameters["sdc_onboarding_url"])
    command_out = (os.popen(agreement_string)).read()
    agreement_id = (get_out_helper(command_out))[3]

    submit_string = "oclip vlm-submit -x {} -y {} -u {} -p {} -m {}".format(vlm_id, vlm_version, parameters["sdc_creator"], \
      parameters["sdc_password"], parameters["sdc_onboarding_url"])
    os.system(submit_string)

    output = [feature_group_id, agreement_id, vlm_version, vlm_id ]
    return output

def create_vsp(parameters, in_list):
    vnfs = parameters["vnf"]
    vsp_dict = {}
    for name, value in vnfs.iteritems():
        create_string = "oclip vsp-create -j {} -o {} -e {} -x {} -y {} -i {} -c {} -g {} -u {} -p {} -m {}".format( in_list[0], \
          parameters["onboarding-method"], parameters["vendor-name" ], value.get("vsp-name"), value.get("vsp-desc"), in_list[1], \
          in_list[2], in_list[3], parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_onboarding_url"] )
        command_out = (os.popen(create_string)).read()
        out_list = get_out_helper(command_out)
        vsp_id = out_list[3]
        vsp_version = out_list[5]

        os.system("oclip vsp-add-artifact -x {} -y {} -z {} -u {} -p {} -m {}".format(vsp_id, vsp_version, value.get("path"), \
          parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_onboarding_url"]))

        os.system("oclip vsp-validate -x {} -y {} -u {} -p {} -m {}".format(vsp_id, vsp_version, parameters["sdc_creator"], \
          parameters["sdc_password"], parameters["sdc_onboarding_url"]))

        os.system("oclip vsp-submit -x {} -y {} -u {} -p {} -m {}".format(vsp_id, vsp_version, parameters["sdc_creator"], \
          parameters["sdc_password"], parameters["sdc_onboarding_url"]))

        os.system("oclip vsp-package -x {} -y {} -u {} -p {} -m {}".format(vsp_id, vsp_version, parameters["sdc_creator"], \
          parameters["sdc_password"], parameters["sdc_onboarding_url"]))

        vsp_ids[name] = vsp_id
    return vsp_ids

def create_vf_model(parameters, vsp_dict):
    vnfs = parameters["vnfs"]
    vf_dict = {}
    for name, value in vnfs.iteritems():
        create_string = "oclip vf-model-create -y {} -g {} -x {} -z {} -b {} -u {} -p {} -m {}".format(value.get("vf-description"), \
          value.get("vsp-version"), value.get("vf-name"), parameters["vendor-name"], vsp_dict[name], \
          parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_catalog_url"])
        os.system(create_string)

        output = (os.popen("oclip vf-model-list -m {} -u {} -p {} | grep {}".format(parameters["sdc_catalog_url"], \
          parameters["sdc_creator"], parameters["sdc_password"], value.get("vf-name")))).read()
        output = (get_out_helper_2(output))

        vf_unique_id = output[1]

        # checkout
        output = (os.popen("oclip vf-model-checkout --host-url {} --host-username {} --host-password {} --vf-model-uuid {} --format json".format(
             parameters["sdc_catalog_url"], parameters["sdc_creator"], parameters["sdc_password"], vf_unique_id))).read()
        vf_unique_id = (json.loads(output))['ID']

        # add consumer, if already exist , just ignore, below command would fail simply !!
        os.system("oclip sdc-consumer-create --consumer-name {}".format(parameters["sdc_consumer"]))

        def get_vnf_name(vnf_csar_path):
            return vnf_csar_path.replace("_"," ").replace(".", " ").replace("-"," ")

        # add artifact
        os.system("oclip vf-model-add-artifact --host-url {} --host-username {} --host-password {} --vf-model-uuid {} --artifact {} --artifact-name {}".format(
             parameters["sdc_catalog_url"], parameters["sdc_consumer"], parameters["sdc_password"], vf_unique_id, vnf_csar, get_vnf_name(vnf_csar)))

        # check-in
        os.system("oclip vf-model-checkin --host-url {} --host-username {} --host-password {} --vf-model-uuid {}".format(
             parameters["sdc_catalog_url"], parameters["sdc_creator"], parameters["sdc_password"], vf_unique_id))

        os.system("oclip vf-model-certify -b {} -r {} -u {} -p {} -m {}".format(vf_unique_id, value.get("vf-remarks"), \
          parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_catalog_url"]))

        #Check for new parameters after certification
        output = (os.popen("oclip vf-model-list -m {} -u {} -p {} | grep {}".format(parameters["sdc_catalog_url"], \
                              parameters["sdc_creator"], parameters["sdc_password"], value.get("vf-name")))).read()
        output = (get_out_helper_2(output))

        vf_dict[name] = output[1]

    return vf_dict


def create_service_model(parameters, vf_dict):
    vnfs = parameters["vnfs"]

    create_string = "oclip service-model-create -z {} -y {} -e {} -x {} -c {} -b {} -u {} -p {} -m {} |grep ID".format(parameters["project-code"], \
    parameters["service-model-desc"], parameters["icon-id"], parameters["service-model-name"], parameters["category-display"], \
    parameters["category"],parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_catalog_url"])

    service_model_id = (get_out_helper_2((os.popen(create_string)).read()))[1]

    # checkout
    output = (os.popen("oclip service-model-checkout --host-url {} --host-username {} --host-password {} --service-model-uuid {} --format json".format(
         parameters["sdc_catalog_url"], parameters["sdc_creator"], parameters["sdc_password"], service_model_id))).read()
    service_model_id = (json.loads(output))['ID']

    # add consumer, if already exist , just ignore, below command would fail simply !!
    os.system("oclip sdc-consumer-create --consumer-name {}".format(parameters["sdc_consumer"]))

    for name, value in vnfs.iteritems():
        output = (os.popen("oclip service-model-add-vf -x {} -b {} -y {} -z {} -u {} -p {} -m {}".format(service_model_id, \
                   parameters["vf-version"], vf_dict[name], value.get("vf-name"), parameters["sdc_creator"], \
                   parameters["sdc_password"], parameters["sdc_catalog_url"] ))).read()
        vf_unique_id = (json.loads(output))['ID']
        vf_uuid = (json.loads(output))['UUID']

        def get_ns_name(ns_csar_path):
            return vnf_csar_path.replace("_"," ").replace(".", " ").replace("-"," ")

        # add artifact
        os.system("oclip service-model-add-artifact --host-url {} --host-username {} --host-password {} --service-uniqueId {} --artifact {} --artifact-name {}".format(
             parameters["sdc_catalog_url"], parameters["sdc_consumer"], parameters["sdc_password"], vf_unique_id, ns_csar, get_vnf_name(ns_csar)))

        # set properties
        os.system("oclip service-model-set-property --host-url {} --host-username {} --host-password {} --service-uniqueId {} --resource-uuid {} --resource-uniqueId {} --property-name nf_type --property-value {}".format(
            parameters["sdc_catalog_url"], parameters["sdc_consumer"], parameters["sdc_password"], service_model_id , vf_unique_id, vf_uuid, vnfm_driver))

    # check-in
    os.system("oclip service-model-checkin --host-url {} --host-username {} --host-password {} --service-model-uuid {}".format(
         parameters["sdc_catalog_url"], parameters["sdc_creator"], parameters["sdc_password"], service_model_id))


    os.system("oclip service-model-test-request -b {} -r {} -u {} -p {} -m {}".format(service_model_id, parameters["service-test-remarks"], \
    parameters["sdc_creator"], parameters["sdc_password"], parameters["sdc_catalog_url"]))

    os.system("oclip service-model-test-start -b {} -u {} -p {} -m {}".format(service_model_id, parameters["sdc_tester"], \
    parameters["sdc_password"], parameters["sdc_catalog_url"]))

    os.system("oclip service-model-test-accept -b {} -r {} -u {} -p {} -m {}".format(service_model_id, parameters["service-accept-remarks"], \
    parameters["sdc_tester"], parameters["sdc_password"], parameters["sdc_catalog_url"]))

    #Get uniqueId for the service model
    service_model_values = (os.popen("oclip service-model-list -u {} -p {} -m {} |grep {}".format(parameters["sdc_creator"], \
      parameters["sdc_password"], parameters["sdc_catalog_url"], parameters["service-model-name"]))).read()
    service_model_values = get_out_helper_2(service_model_values)
    service_model_uniqueId = (service_model_values)[1]

    os.system("oclip service-model-approve -b {} -r {} -u {} -p {} -m {}".format(service_model_uniqueId, parameters["service-approve-remarks"], \
    parameters["sdc_governor"], parameters["sdc_password"], parameters["sdc_catalog_url"]))

    os.system("oclip service-model-distribute -b {} -u {} -p {} -m {}".format(service_model_uniqueId, parameters["sdc_operator"], \
    parameters["sdc_password"], parameters["sdc_catalog_url"]))

    return service_model_values

def register_cloud_helper(cloud_region, values, parameters):
    #Create Cloud
    cloud_create_string = 'oclip cloud-create -e {} -b {} -I {{\\\\\\"openstack-region-id\\\\\\":\\\\\\"{}\\\\\\"}} \
    -x {} -y {} -j {} -w {} -l {} -url {} -n {} -q {} -r {} -Q {} -i {} -g {} -z {} -k {} -c {} -m {} -u {} -p {}'.format(
      values.get("esr-system-info-id"), values.get("user-name"), cloud_region, parameters["cloud-owner"], \
      cloud_region, values.get("password"), values.get("cloud-region-version"), values.get("default-tenant"), \
      values.get("service-url"), parameters["complex_name"], values.get("cloud-type"), parameters["owner-defined-type"], \
      values.get("system-type"), values.get("identity-url"), parameters["cloud-zone"], values.get("ssl-insecure"), \
      values.get("system-status"), values.get("cloud-domain"), parameters["aai_url"], parameters["aai_username"], \
      parameters["aai_password"])


    os.system(cloud_create_string)

    #Associate Cloud with complex
    complex_associate_string = "oclip complex-associate -x {} -y {} -z {} -m {} -u {} -p {}".format(parameters["complex_name"], \
      cloud_region, parameters["cloud-owner"], parameters["aai_url"], parameters["aai_username"], parameters["aai_password"])
    os.system(complex_associate_string)

    #Register Cloud with Multicloud
    multicloud_register_string = "oclip multicloud-register-cloud -y {} -x {} -m {}".format(parameters["cloud-owner"], \
      cloud_region, parameters["multicloud_url"])
    os.system(multicloud_register_string)

def register_all_clouds(parameters):
    cloud_dictionary = parameters["cloud_region_data"]
    for cloud_region, cloud_region_values in cloud_dictionary.iteritems():
        register_cloud_helper(cloud_region, cloud_region_values, parameters)

def create_service_type(parameters):
    create_string = "oclip service-type-create -x {} -m {} -u {} -p {}".format( parameters["service_name"], \
      parameters["aai_url"], parameters["aai_username"], parameters["aai_password"])
    os.system(create_string)

def create_customer(parameters):
    create_string = "oclip customer-create -x {} -y {} -m {} -u {} -p {}".format( parameters["customer_name"], \
    parameters["subscriber_name"], parameters["aai_url"], parameters["aai_username"], parameters["aai_password"])
    os.system(create_string)

def add_customer_subscription(parameters):
    subscription_check = 0
    for cloud_region, cloud_region_values in (parameters["cloud_region_data"]).iteritems():
      if subscription_check == 0 :
        subscription_string = "oclip subscription-create -x {} -c {} -z {} -e {} -y {} -r {} -m {} -u {} -p {}".format(\
          parameters["customer_name"], cloud_region_values.get("tenant-id"), parameters["cloud-owner"], parameters["service_name"],\
          cloud_region_values.get("default-tenant"), cloud_region, parameters["aai_url"], parameters["aai_username"], parameters["aai_password"] )
      else:
        subscription_string = "oclip subscription-cloud-add -x {} -c {} -z {} -e {} -y {} -r {} -m {} -u {} -p {}".format(\
          parameters["customer_name"], cloud_region_values.get("tenant-id"), parameters["cloud-owner"], parameters["service_name"],\
          cloud_region_values.get("default-tenant"), cloud_region, parameters["aai_url"], parameters["aai_username"], parameters["aai_password"] )
      os.system(subscription_string)
      subscription_check+=1

def register_vnfm_helper(vnfm_key, values, parameters):
    #Create vnfm
    vnfm_create_string = 'oclip vnfm-create -b {} -c {} -e {} -v {} -g {} -x {} -i {} -j {} -q {} \
    -m {} -u {} -p {}'.format(vnfm_key, values.get("type"), values.get("vendor"), \
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
        vnf_onboard_string = 'oclip vfc-catalog-onboard-vnf -c {}'.format(value.get("csar-id"))
        vnf_onboard_outs[key] = (os.popen(ns_onboard_string)).read()
    return vnf_onboard_outputs

def onboard_ns(parameters):
    ns_onboard_string = 'oclip vfc-catalog-onboard-ns -c {}'.format(parameters["ns-csar-id"])
    ns_onboard_out = (os.popen(ns_onboard_string)).read()
    return ns_onboard_out

def create_ns(parameters, csar_id):
    ns = parameters["ns"]
    ns_create_string = 'oclip vfc-nslcm-create -m {} -c {} -n {} -q {} -S {}'.format(parameters["vfc-url"], \
       csar_id, ns.get("name"), parameters["customer_name"], parameters["service_name"])
    print ns_create_string
    ns_create_out = (os.popen(ns_create_string)).read()
    print ns_create_out
    ns_instance_id = (get_out_helper_2(ns_create_out))[3]
    return ns_instance_id

def instantiate_ns(parameters, ns_instance_id):
    ns_instantiate_string = 'oclip vfc-nslcm-instantiate -m {} -i {} -c {} -n {}'.format(parameters["vfc-url"], \
        ns_instance_id, parameters["location"], parameters["sdc-controller-id"])
    print ns_instantiate_string

    ns_instantiate_out = (os.popen(ns_instantiate_string)).read()
    return ns_instantiate_out

def create_ns_package(parameters):
    ns = parameters["ns"]
    create_ns_string = 'oclip vfc-catalog-create-ns -m {} -c {} -e {}'.format(parameters["vfc-url"], \
      ns.get("key"), ns.get("value"))
    cmd_out = (os.popen(create_ns_string)).read()
    out_list =  get_out_helper_2(cmd_out)
    return out_list[4]

def create_vnf_package(parameters):
    vnfs = parameters["vnfs"]
    outputs = {}

    for vnf_key, vnf_values in vnfs.iteritems():
        create_vnf_string = 'oclip vfc-catalog-create-vnf -m {} -c {} -e {}'.format(parameters["vfc-url"], \
          vnf_values.get("key"), vnf_values.get("value"))
        cmd_out = (os.popen(create_vnf_string)).read()
        out_list =  get_out_helper_2(cmd_out)
        outputs[vnf_key] = out_list[4]

    return outputs

def upload_ns_package(parameters, ns_package_output):
    ns = parameters["ns"]
    ns_upload_string = '{}/api/nsd/v1/ns_descriptors/{}/nsd_content'.format(parameters["vfc-url"], ns_package_output)
    print ns_upload_string
    print ns.get("path")
    resp = requests.put(ns_upload_string, files={'file': open(ns.get("path"), 'rb')})
    return resp

def upload_vnf_package(parameters, vnf_package_output):
    vnfs = parameters["vnfs"]
    for vnf_key, vnf_values in vnfs.iteritems():
        vnf_upload_str = '{}/api/vnfpkgm/v1/vnf_packages/{}/package_content'.format(parameters["vfc-url"], \
          vnf_package_output[vnf_key], vnf_package_output[vnf_key])
        resp = requests.put(vnf_upload_str, files={'file': open(vnf_values.get("path"), 'rb')})
    return resp


def provision_vnf():

    result_json = {}
    result_json['vnf_id'] = ''
    result_json['vnf_status'] = ''
    result_json['ns_id'] = ''
    result_json['ns_status'] = ''

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

    # 8. create NS
    ns_instance_id = create_ns(parameters, ns_package_output)
    print ns_instance_id

    instantiate_ns_output = instantiate_ns(parameters, ns_instance_id)
    print instantiate_ns_output

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

#Retrive the params
args = parser.parse_args()
print args

config_file = open(args.config_file_path)
vsp = args.vsp
vnf_csar = args.vnf_csar
ns_csar = args.ns_csar
vnfm_driver = args.vnfm_driver
result_json_path = args.result_json_path

# setup CLI
parameters = get_parameters(config_file)
set_open_cli_env(parameters)

output = provision_vnf()

persist_result(result_json_path, output)

print "Provision is completed !"