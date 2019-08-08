.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.

.. _end_to_end_onap_cli_commands:


End-to-End command guide for working with ONAP
==============================================

This document demonstrates end to end working with ONAP using cli.

Currently, in ONAP, it is getting initialized by robot script (`demo.sh init`),
which requires programming knowledge to modify the scripts as per the requirement.
Cli provides an interface to communicate with different services in ONAP and it can
be used to configure ONAP as per requirement without any programming knowledge.

Following operations are involved to deploy a VNF.

CLI communicate with:

- SDC to
    - Create Vendor License Model (VLM)
    - Create Vendor Software Product (VSP), VF and Service
    - Distribute service

- Policy to
    - Put HPA policies

- AAI to create
    - Complex
    - Cloud
    - Customer
    - Service-type
	- Add customer subscription

- Multicloud to
    - register cloud with multicloud to discover HPA

- SO to create
    - Service-instance
    - VNF Instance
    - VF module (deploys stack)

- SDNC to
    - Preload VF module parameters



Creating Vendor License Model (VLM)
===================================

For creating a VLM we need to define in SDC using CLI:
- entitlement pool
- key group
- feature group
- license agreement


Create license
--------------

Run following command to create license.

::

  onap>vlm-create -x ${vendor-name} -u cs0008 -p demo123456! -m ${sdc-onboarding-url}

**Output**: vlm-id, vlm-version

Create license entitlement pool
-------------------------------

Run following command to create license entitlement pool.

::

  onap>vlm-entitlement-pool-create -x ${entitlement-pool-name} -y ${vlm-id} -e ${vlm-version} -z ${entitlement-pool-description} -k ${vendor-name} -g {license-expiry-date} -l {license-start-date} -u cs0008 -p demo123456! -m ${sdc-onboarding-url}

**Output**: vlm-entitlement-pool-id

Create license key group
------------------------

Run following command to crate license key group.

::

  onap>vlm-key-group-create -c ${vlm-id} -e ${vlm-version} -x {key-group-name} -y {key-group-type} -u cs0008 -p demo123456! -m ${sdc-onboarding-url}

**Output**: key-group-id

Create license feature group
----------------------------

Run following command to create license feature group.

::

  onap>vlm-feature-group-create -x ${feature-group-name} -y ${vlm-id} -e {vlm-version} -z {feature-grp-description} -g ${key-group-id} -b ${vlm-entitlement-pool-id} -c ${part-no} -u cs0008 -p demo123456! -m ${sdc-onboarding-url}

**Output**: feature-group-id


Create license agreement
------------------------

Run following command to create license agreement.

::

  onap>vlm-aggreement-create -x ${aggreement-name} -y ${vlm-id} -e ${vlm-version} -z ${agreement-description} -g ${feature-group-id} -u cs0008 -p demo123456! -m ${sdc-onboarding-url}

**Output**: agreement-id


Now, VLM is ready to submit.

Checkin and submin vlm
----------------------

Run following command to check-in and submit created VLM.

::


  vlm-submit -x ${vlm-id} -y ${vlm-version} -u cs0008 -p demo123456! -m ${sdc-onboarding-url}


Now, VLM is available in catalog to be used for VSP.


Create and distribute Vendor Software Product (VSP)
===================================================

Service distribution involves following steps:
- Create, validate and submit VSP
- Creation and certificaion of VF
- Creation and certification of Service

Create VSP
----------

Run following command to create VSP.

::

  onap>vsp-create -j ${feature-group-id} -o ${onboarding-method} -e ${vendor-name} -x ${vsp-name} -y ${vsp-description} -i ${aggreement-id} -c ${vlm-version} -g ${vlm-id} -u cs0008 -p demo123456! -m ${sdc-onboarding-url}


**Output**: vsp-id, vsp-version


Add artifact to VSP
-------------------

This command associates artifacts to VSP.

::

  onap>vsp-add-artifact -x ${vsp-id} -y ${vsp-version}  -z ${csar-file} -u cs0008 -p demo123456! -m ${sdc-onboarding-url}

Validate VSP
------------

After uploading artifacts to VSP you need to validate the VSP using following command.

::

  onap>vsp-validate -x ${vsp-id} -y ${vsp-version} -u cs0008 -p demo123456! -m ${sdc-onboarding-url}



NOTE: In most cases `warning` is reported for VSP validation, this can be ignored.

Submit VSP
----------------------

After VSP validation it can be checked in and submit. Run following command to
complete check-in and VSP submission.

::

  onap>vsp-submit -x ${vsp-id} -y ${vsp-version} -u cs0008 -p demo123456! -m ${sdc-onboarding-url}

To list created VSPs, run the following command;

::

  onap>vsp-list -u cs0008 -p demo123456! -m ${sdc-onboarding-url}


package VSP
-----------

After submitting VSP you require to generate CSAR artifact which can be done using
following command.

::

  onap>vsp-package -x ${vsp-id} -y ${vsp-version} -u cs0008 -p demo123456! -m ${sdc-onboarding-url}

Now, you can proceed for defining VF.

Create vf model for VSP
-----------------------

Run following command to create VF by specifying correct VSP id.

::

  onap> vf-model-create -x ${vf-model-name} -y ${vf-model-description} -g ${vsp-version} -z ${vlm-vendor-name} -b ${vsp-id} -u cs0008 -p demo123456! -m ${sdc-catalog-url}

Output: vf-model-id

To view newly created vf;
::

 onap> vf-model-list -u cs0008 -p demo123456! -m ${sdc-catalog-url}

Save the **vf-unique-id** , it will be used for certification

Now, VF is ready for certification.


Certify VF
----------

Certification will be conducted by tester(jm0007), tester can use following command
to mark VF certification start and complete.

::

  onap> vf-model-certify -b ${vf-unique-id} -r ${vf-remarks} -u cs0008 -p demo123456! -m ${sdc-catalog-url}

After certifications, the id values change so run the above vf-model-list command to see the new values which will be used in the next steps.
After successful VF creation you can proceed for service model creation.

Create service model
--------------------

Run following command to create service model.

::

  onap>service-model-create -x ${service-model-name} -y ${service-model-description} -z ${project-code} -e ${icon-id} -c ${category-display} -b ${category} -u cs0008 -p demo123456! -m ${sdc-catalog-url}

**project code can be a random string**

**Output**: service-model-id

Add VF to service model
-----------------------

Associate VF to service model using following command.

::

  onap>service-model-add-vf -x ${service-model-id} -b ${vf-version} -y ${vf-model-unique-id} -z ${vf-model-name} -u cs0008 -p demo123456! -m ${sdc-catalog-url}


Submit Service for Testing
--------------------------------

Now, service is ready for Testing. Use following command
to initiate service test request.

::

  onap>service-model-test-request -b ${service-model-id} -u cs0008 -p demo123456! -m ${sdc-catalog-url}

Test service
---------------

Now, tester(jm0007) can see service in his dashboard and he can start testing. Tester can use following command to start test and complete test

::

  onap>service-model-test-start -b ${service-model-id} -u jm0007 -p demo123456! -m ${sdc-catalog-url}

  onap>service-model-test-accept -b ${service-model-id} -r ${test-remarks} -u jm0007 -p demo123456! -m ${sdc-catalog-url}

  onap>service-model-list -u cs0008 -p demo123456! -m ${sdc-catalog-url}

Run the above list command to get the service-model-unique-id, it will be used to approve the service model


Approve service model
---------------------

After successful Testing, service will be submitted to governor(gv0001) to
approve service model.

::

  onap>service-model-approve -b ${service-model-unique-id} -r ${approval-remarks} -u gv0001 -p demo123456! -m ${sdc-catalog-url}

  onap>service-model-list -u cs0008 -p demo123456! -m ${sdc-catalog-url}


Distribute service model
------------------------

Now operator can distribute the service and it will be available in VID dashboard.

::

  onap>service-model-distribute -b ${service-model-unique-id} -u op0001 -p demo123456! -m ${sdc-catalog-url}

  onap>service-model-list -u cs0008 -p demo123456! -m ${sdc-catalog-url}

Creating Policies (Only Required if HPA is being Used)
======================================================
Before Running these commands to create policies, you must have uploaded policy models. See Method 2 (Manual upload) `here <https://wiki.onap.org/display/DW/OOF+-+HPA+guide+for+integration+testing>`_

::

  onap> policy-create-outdated -m ${pdp-url} -u testpdp -p alpha123 -x ${policy-name} -S ${policy-scope} -T ${policy-config-type} -o ${policy-onap-name} -b ${policy}

  onap> policy-push-outdated -m ${pdp-url} -u testpdp -p alpha123 -x ${policy-name} -b ${policy-config-type} -c ${policy-pdp-group}

When creating policies, the resource-module-name of the vf-model is required. This can be gotten by running the following command;

::

  onap> get-resource-module-name -u cs0008 -p demo123456! -m ${sdc-catalog-url}

**Note:** See Step 16 in `vFW with HPA Tutorial: Setting Up and Configuration <https://onap.readthedocs.io/en/casablanca/submodules/integration.git/docs/docs_vfwHPA.html#docs-vfw-hpa>`_
for sample policies as well as example values for policy-scope, config-type ...etc.


Creating Cloud, Customer and Service-type
=========================================

It is required to specify the complex and cloud configuration in AAI system before deploying
the service. Following are the steps to configure cloud and related information
for service deployment.


Create Complex
--------------
::

  onap>complex-create -x ${complex-name} -r ${physical-location} -y ${data-center-code} -l ${region} -i ${street-1-name} -j ${street-2-name}  -lt ${latitude} -lo ${longitude} -S ${state}  -la ${lata} -g ${city} -w ${postal-code} -z ${complex-name} -k ${country} -o ${elevation} -q ${identity-url} -m ${aai-url} -u AAI -p AAI

Create Cloud
------------

Use following command to create cloud and region in AAI system.

::

  onap>cloud-create -x ${cloud-owner-name} -y ${cloud-region-name} -e ${esr-system-info-id} -b ${cloud-user-name} -j ${cloud-user-password} -I ${extra-info} -w ${cloud-region-version} -l ${default-tenant} -url ${keystone-url} -n ${complex-name} -q ${cloud-type} -r ${owner-defined-type} -Q ${system-type} -i ${identity-url} -g ${cloud-zone} -z ${ssl-insecure-boolean} -k ${system-status} -c ${cloud-domain} -m ${aai-url} -u AAI -p AAI

**Sample Command**
::

 onap> cloud-create -e 5c85ce1f-aa78-4ebf-8d6f-4b62773e9ade -b ${cloud-username} -I {\\"openstack-region-id\\":\\"ONAP-POD-01-Rail-06\\"} -x CloudOwner -y ONAP-POD-01-Rail-06 -j ${cloud-password} -w titanium_cloud -l Integration-HPA -url http://10.12.11.1:5000/v3 -n clli1 -q openstack -r t1 -Q VIM -i url -g CloudZone -z true -k active -c Default



Associate Cloud Region with complex
-----------------------------------
::

  onap> complex-associate -x ${complex-name} -y ${cloud-region} -z ${cloud-owner} -m ${aai-url} -u AAI -p AAI
  onap> cloud-list -u AAI -p AAI -m ${aai-url}


Register Cloud Region with Multicloud (only required for HPA)
-------------------------------------------------------------

::

  onap> multicloud-register-cloud -y ${cloud-owner} -x ${cloud-region} -m ${multicloud-url}


Create Customer
---------------

A customer subscribes for the service. Use follwing command to create customer.

::

  onap>customer-create -x ${customer-name} -y ${subscriber-name} -u AAI -p AAI -m ${aai-url}

  onap>customer-list -u AAI -p AAI -m ${aai-url}


Create service type
-------------------

Use following command to create service type.

::

  onap>service-type-create -x ${service-name} -u AAI -p AAI -m ${aai-url}

  onap>service-type-list -u AAI -p AAI -m ${aai-url}

Create subscription
-------------------

Use the following command to create subscription for a customer.

::

  onap>subscription-create -x ${customer-name} -z ${cloud-owner} -c ${tenant-id} -e ${service-name} -y ${default-tenant} -r ${cloud-region} -m ${aai-url} -u AAI -p AAI


If the subscription has already been created, you can add more clouds to it using the following command;

::

  onap>subscription-cloud-add -x ${customer-name} -z ${cloud-owner} -c ${tenant-id} -e ${service-name} -y ${default-tenant} -r ${cloud-region} -m ${aai-url} -u AAI -p AAI


Create Service-instance, VNF Instance and deploy VFMODULE
=========================================================

Now, all the required configuration and artifacts are available for the SO service to
create a service-instance, VNF-Instance and VFMODULE.

Create service instance
-----------------------

Using following command you can create service-instance in specified cloud region.
Command requires service model identifier which can be obtained from previously
used CLIs for creating and listing service model.

::

  onap> service-create -w ${service-name} -la ${customer-latitude} -lo ${customer-longitude} -o ${orchestrator} -A {alacart-boolean} -i ${service-model-name} -y ${company-name} -x ${project-name} -q ${requestor-id} -O ${owning-entity-name} -k ${service-instance-name} -P ${test-api} -H ${homing-solution} -n ${service-model-uuid} -e ${service-model-invariant-uuid} -j ${service-model-version} -S ${subscriber-name} -g ${service-model-uuid} -z ${owning-entity-id} -c ${customer-name} -u InfraPortalClient -p password1$ -m ${so-url}

When Homing and HPA are not being used, you do not need to specify longitude, latitude, homing-solution, orchestator

**Sample service-create commands**

**Without Homing**
::

  onap> service-create -w vFW -o multicloud -A true -i vfw-demo-service -y some_company  -x Project-Demonstration -q demo -O OE-Demonstration -k rand-2 -P VNF_API -n 545bca3c-8cc0-4dac-b464-1720894e0213 -e 41d0ebba-4b89-4437-9b22-4d83d2183aaa -j 1.0 -S Demonstration -g 545bca3c-8cc0-4dac-b464-1720894e0213 -z d005274f-d295-4538-a6b0-a090a7807dae -c Demonstration -u InfraPortalClient -p password1$ -m http://192.168.1.147:30277

**With Homing**
::

  onap> service-create -w vFW -la 32.897480 -lo 97.040443 -o multicloud -A true -i vfw-demo-service -y some_company  -x Project-Demonstration -q demo -O OE-Demonstration -k cli-instance-new -P VNF_API -H oof -n 545bca3c-8cc0-4dac-b464-1720894e0213 -e 41d0ebba-4b89-4437-9b22-4d83d2183aaa -j 1.0 -S Demonstration -g 545bca3c-8cc0-4dac-b464-1720894e0213 -z d005274f-d295-4538-a6b0-a090a7807dae -c Demonstration -u InfraPortalClient -p password1$ -m http://192.168.1.147:30277

You can get the values for the owning entity by running the following command;
::

  onap> owning-entity-list -u AAI -p AAI -m ${aai-url}

Create VNF Instance
-------------------

Use the following command to create the VNF instance, Do a Preload and create the VFModule in a particular cloud region

::

   onap> vnf-create -j ${service-model-invariant-uuid} -q ${service-model-name} -k ${service-model-id} -l ${cloud-region} -y ${service-instance-id} -z ${tenant-id} -r ${requestor-id} -c ${vf-model-uuid} -o ${generic-vnf-name} -e ${vf-model-name} -g ${vf-model-version} -b ${vf-model-invariant-uuid} -n ${service-version} -i ${vf-model-customization-id} -vn ${vf-model-customization-name} -w ${service-name} -pn ${platform-name} -lob ${lob-name} -u InfraPortalClient -p password1$ -m ${so-url}

**Output:** vnf-id


**Sample vnf-create command**
::

  onap>  vnf-create -j 41d0ebba-4b89-4437-9b22-4d83d2183aaa -q vfw-demo-service -k 545bca3c-8cc0-4dac-b464-1720894e0213 -l cloud-two -y 43143a2c-f0e1-499d-b042-97fb0c05166b -z a0ea4cfd751e40e0a093848dd9e03e6c -r demo  -c 04ffd583-5625-4471-b20b-38394b513efd -o cli-vnf-test -e vfw-hpa-sriov_v2 -g 1.0 -b b7d993a3-3870-4096-a16c-90110d402349 -n 1.0 -i 123f896f-c6a4-4560-8786-7607e832bb6f -vn "vfw-hpa-sriov_v2 0" -w vFW -pn Platform-Demonstration -lob LOB-Demonstration -u InfraPortalClient -p password1$ -m http://192.168.1.147:30277


Preload VF-Module
-----------------
::

  onap> vf-preload -y ${preload-file} -u admin -p Kp8bJ4SXszM0WXlhak3eHlcse2gAw84vaoGGmJvUy2U -m ${sdnc-url}

See Step 20 of `vFW with HPA Tutorial: Setting Up and Configuration <https://onap.readthedocs.io/en/casablanca/submodules/integration.git/docs/docs_vfwHPA.html#docs-vfw-hpa>`_ for sample preload file.

Create VF-module
----------------
::

  onap>  vf-module-create -w ${tenant-id} -mn ${vf-model-customization-name} -x ${service-instance-id} -l ${cloud-region} -sv ${service-version} -vc ${vf-module-customization-id} -vm ${v-module-model-version} -mv ${vf-model-version} -i ${vf-module-name} -vf ${vf-model-name} -vi ${vf-module-model-invariant-id} -r ${suppress-rollback-boolean} -mc ${vf-model-customization-id} -api ${test-api} -mi ${vf-model-invariant-id} -vid ${vf-model-id} -y ${vnf-instance-id} -R ${requestor-id} -si ${service-uuid} -up ${use-preload} -sd ${service-invariat-id} -z ${service-model-name} -vn ${vf-module-model-name} -vv ${vf-module-model-version-id} -co ${cloud-owner} -u InfraPortalClient -p password1$ -m ${so-url}

**Sample vf-module-create command**
::

  onap> vf-module-create -w a0ea4cfd751e40e0a093848dd9e03e6c -mn "vfw-hpa-sriov_v2 0" -x d353ace3-52e9-4c79-b3c8-63c97e15cc29 -l cloud-two -sv 1.0 -vc 0f837829-0dbb-4768-88d4-3cdf01e073f6 -vm 1 -mv 1.0 -i vfw-sriov-cli -vf vfw-hpa-sriov_v2 -vi 8d3071e4-0d4e-4520-aa92-c01b9a019142 -r false -mc 123f896f-c6a4-4560-8786-7607e832bb6f -api VNF_API -mi b7d993a3-3870-4096-a16c-90110d402349 -vid 04ffd583-5625-4471-b20b-38394b513efd -y dad645a3-7e01-47cd-8e70-cdc8b5e880ec -R demo -si 545bca3c-8cc0-4dac-b464-1720894e0213 -up true -sd 41d0ebba-4b89-4437-9b22-4d83d2183aaa -z vfw-demo-service -vn VfwHpaSriovV2..base_vfw..module-0 -vv d5c83591-0f22-4543-ada2-24197847b7d2 -u InfraPortalClient -p password1$ -m http://192.168.1.147:30277
