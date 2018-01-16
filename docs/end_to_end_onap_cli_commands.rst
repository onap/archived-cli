.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.

.. _end_to_end_onap_cli_commands:


End-to-End command guide for working with ONAP
==============================================

This document demonstrate end to end working with ONAP using cli.

In current state of ONAP, it is getting initialized by robot scritp (`demo.sh init`),
which requires programming knowledge to modify the scripts as per the requirement.
Cli provides an interface to communicate with different services in ONAP and it can
be used to configure ONAP as per requirement without any programming knowledge.

Following operations are involved to deploy a VNF.

CLI communicate with:

- SDC to
    - Create Vendor License Model (VLM)
    - Create Vendor Software Product (VSP), VF and Service
    - Distribute service

- AAI to create
    - Cloud
    - Customer
    - Service-type

- SO to create
    - Service-instance
    - VF
    - VF module (deploy)


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

  onap>vlm-create -x htipl-vendor -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +----------+----------------------------------+
  |property  |value                             |
  +----------+----------------------------------+
  |ID        |d6da25894a1a470fa8357c15681b7a66  |
  +----------+----------------------------------+

Create license entitlement pool
-------------------------------

Run following command to create license entitlement pool.

::

  onap>vlm-entitlement-pool-create -x htipl-pool -y d6da25894a1a470fa8357c15681b7a66 -e 0.1 -z htipl-pool -k htipl-vendor -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +----------+----------------------------------+
  |property  |value                             |
  +----------+----------------------------------+
  |ID        |44743b88b7254d3aa8829d5fcb5a4b47  |
  +----------+----------------------------------+

Create license key group
------------------------

Run following command to crate license key group.

::

  onap>vlm-key-group-create -c d6da25894a1a470fa8357c15681b7a66 -e 0.1 -x htipl-key-grp -y Universal -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +----------+----------------------------------+
  |property  |value                             |
  +----------+----------------------------------+
  |ID        |c8f9ba23d0414650907099ecbf960f3d  |
  +----------+----------------------------------+


Create license feature group
----------------------------

Run following command to create license feature group.

::

  onap>vlm-feature-group-create -x htipl-feature-grp -y d6da25894a1a470fa8357c15681b7a66 -e 0.1 -z htipl-feature-grp -g c8f9ba23d0414650907099ecbf960f3d -b 44743b88b7254d3aa8829d5fcb5a4b47 -c htipl-part -k htipl-vendor -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +----------+----------------------------------+
  |property  |value                             |
  +----------+----------------------------------+
  |ID        |0ef91147b9904568942d4d3f5a8bbc96  |
  +----------+----------------------------------+


Create license agreement
------------------------

Run following command to create license agreement.

::

  onap>vlm-aggreement-create -x htipl-aggrement -y d6da25894a1a470fa8357c15681b7a66 -e 0.1 -z htipl-aggrement -g 0ef91147b9904568942d4d3f5a8bbc96 -u cs0008 -p demo123456\! -m http://192.168.17.5:8080
  vlm-aggreement-create -x htipl-aggrement -y d6da25894a1a470fa8357c15681b7a66 -e 0.1 -z htipl-aggrement -g 0ef91147b9904568942d4d3f5a8bbc96 -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +----------+----------------------------------+
  |property  |value                             |
  +----------+----------------------------------+
  |ID        |1909ee429dcd459aa0eb33e812981973  |
  +----------+----------------------------------+


Now, VLM is ready to submit.

Checkin and submin vlm
----------------------

Run following command to check-in and submit created VLM.

::

  vlm-checkin -x d6da25894a1a470fa8357c15681b7a66 -y 0.1 -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  vlm-submit -x d6da25894a1a470fa8357c15681b7a66 -y 0.1 -u cs0008 -p demo123456\! -m http://192.168.17.5:8080


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

  onap>vsp-create -x htipl-vsp -c 0.1 -g d6da25894a1a470fa8357c15681b7a66 -e htipl-vendor  -i 1909ee429dcd459aa0eb33e812981973  -j 0ef91147b9904568942d4d3f5a8bbc96 -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +----------+----------------------------------+
  |property  |value                             |
  +----------+----------------------------------+
  |ID        |044e3269503d4579b4bdd60d0231e4e6  |
  +----------+----------------------------------+


Add artifact to VSP
-------------------

This command will associate artifacts to VSP.

::

  onap>vsp-add-artifact -x 044e3269503d4579b4bdd60d0231e4e6 -y 0.1 -z vFW_100_20170608.zip -u cs0008 -p demo123456! -m http://192.168.17.5:8080

Validate VSP
------------

After uploading artifacts to VSP you need to validate the VSP using following command.

::

  onap>vsp-validate -x 044e3269503d4579b4bdd60d0231e4e6 -y 0.1 -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +----------+----------------------------------------------------+
  |property  |value                                               |
  +----------+----------------------------------------------------+
  |status    |Success                                             |
  +----------+----------------------------------------------------+
  |errors    |{base_vfw.yaml=[{"level":"WARNING","message":"WARN  |
  |          |ING: A resource is connected twice to the same      |
  |          |network role, Network Role [vpg_0], Resource ID [e  |
  |          |comp_private]"},{"level":"WARNING","message":"WARN  |
  |          |ING: Nova Server naming convention in image,        |
  |          |flavor and name properties is not consistent,       |
  |          |Resource ID                                         |
  |          |[vpg_0]"},{"level":"WARNING","message":"WARNING:    |
  |          |A resource is connected twice to the same network   |
  |          |role, Network Role [vsn_0], Resource ID [ecomp_pri  |
  |          |vate]"},{"level":"WARNING","message":"WARNING:      |
  |          |Nova Server naming convention in image, flavor      |
  |          |and name properties is not consistent, Resource     |
  |          |ID                                                  |
  |          |[vsn_0]"},{"level":"WARNING","message":"WARNING:    |
  |          |Port 'Fixed_IPS' Parameter Name not aligned with    |
  |          |Guidelines, Parameter Name                          |
  |          |[ecomp_private_subnet_id], Resource ID              |
  |          |[vfw_private_2_port]. As a result, VF\/VFC          |
  |          |Profile may miss this information"},{"level":"WARN  |
  |          |ING","message":"WARNING: Port 'Fixed_IPS'           |
  |          |Parameter Name not aligned with Guidelines,         |
  |          |Parameter Name [ecomp_private_subnet_id],           |
  |          |Resource ID [vpg_private_1_port]. As a result,      |
  |          |VF\/VFC Profile may miss this information"},{"leve  |
  |          |l":"WARNING","message":"WARNING: Port 'Fixed_IPS'   |
  |          |Parameter Name not aligned with Guidelines,         |
  |          |Parameter Name [ecomp_private_subnet_id],           |
  |          |Resource ID [vsn_private_1_port]. As a result,      |
  |          |VF\/VFC Profile may miss this information"}]}       |
  +----------+----------------------------------------------------+


NOTE: here `warning` is reported for VSP validation which we are ignoring.

Submit and checkin VSP
----------------------

After VSP validation it can be checked in and submit. Run following command to
complete check-in and VSP submission.

::

  onap>vsp-checkin -x 044e3269503d4579b4bdd60d0231e4e6 -y 0.1 -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  onap>vsp-submit -x 044e3269503d4579b4bdd60d0231e4e6 -y 0.1 -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  onap>vsp-list -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +----------------------------------+------------+----------+--------+
  |ID                                |name        |version   |status  |
  +----------------------------------+------------+----------+--------+
  |044e3269503d4579b4bdd60d0231e4e6  |htipl-vsp   |1.0       |Final   |
  +----------------------------------+------------+----------+--------+

package VSP
-----------

After submitting VSP you require to generate CSAR artifact which can be done using
following command.

::

  onap>vsp-package -x 044e3269503d4579b4bdd60d0231e4e6 -y 1.0 -u cs0008 -p demo123456! -m http://192.168.17.5:8080

Now, you can proceed for defining VF.

Create vf model for VSP
-----------------------

Run following command to create VF by specifying correct VSP id.

::

  onap>vf-model-create -x htipl-vf -y htipl-vf-desc -z htipl-vendor -b 044e3269503d4579b4bdd60d0231e4e6  -g 1.0 -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +----------+--------------------------------------+
  |property  |value                                 |
  +----------+--------------------------------------+
  |ID        |f1a75427-1379-4976-abca-91a00f8b118a  |
  +----------+--------------------------------------+

Now, VF is ready for certification.

Submit VF for certification
---------------------------

Initiate VF certification request using following command.

::

  onap>vf-model-certify-request -b f1a75427-1379-4976-abca-91a00f8b118a -u cs0008 -p demo123456! -m http://192.168.17.5:8080

Certify VF
----------

Certification will be conducted by tester(jm0007), tester can use following command
to mark VF certification start and complete.

::

  onap>vf-model-certify-start -b f1a75427-1379-4976-abca-91a00f8b118a -u jm0007 -p demo123456! -m http://192.168.17.5:8080

  onap>vf-model-certify-complete -b f1a75427-1379-4976-abca-91a00f8b118a -u jm0007 -p demo123456! -m http://192.168.17.5:8080

  onap>vf-model-list -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +--------------------------------------+--------------------------------------+--------------------------------------+--------------------------+----------+------------+
  |uuid                                  |uniqueid                              |invariant-uuid                        |name                      |version   |status  |
  +--------------------------------------+--------------------------------------+--------------------------------------+--------------------------+----------+------------+
  |328bd996-5edc-4677-92c4-f50a39411f13  |d4212714-2fd0-4396-bef6-3864e267be06  |b77ac5f7-f524-4076-995b-e3781fda1e7e  |e3b2cebf-c72b-4b0f-b6af   |1.0       |CERTIFIED  |
  +--------------------------------------+--------------------------------------+--------------------------------------+--------------------------+----------+------------+
  |83e79e9f-f021-4356-ab97-a0f03d926311  |389a8234-23ed-4396-a9b4-ea65669a0d30  |b21eff2e-aa99-4284-a59f-baea1dcf77c6  |fcffa69b-52ce-4139-92ba   |1.0       |CERTIFIED  |
  +--------------------------------------+--------------------------------------+--------------------------------------+--------------------------+----------+------------+
  |e4fa86fd-04ab-4faa-8351-3196e6b33c13  |0d9894d8-df48-4245-a52f-9437443908f0  |03f63ce4-dbb0-446c-87dc-fab9c5d09ec9  |cdb3ebd4-0c81-4e60-8184   |1.0       |CERTIFIED  |
  +--------------------------------------+--------------------------------------+--------------------------------------+--------------------------+----------+------------+
  |67c96cda-2035-413d-96de-9ea4aa206423  |c933ceb2-d68b-4bc1-962d-5e5073648fe5  |c3daf439-fa2a-4144-add5-9b543083d24e  |a146fd50-ed50-4444-a859   |1.0       |CERTIFIED  |
  +--------------------------------------+--------------------------------------+--------------------------------------+--------------------------+----------+------------+
  |b2a40a03-c85e-4b6c-8ade-f90e5f2ce2d4  |6d040eb4-9fc7-42a7-b1c0-7959418eca1d  |4982d10b-09af-46b9-8317-c92d2658ae68  |2932d051-6aad-4c0d-ac2d   |1.0       |CERTIFIED  |
  +--------------------------------------+--------------------------------------+--------------------------------------+--------------------------+----------+------------+
  |6bddc881-0538-4ca0-933d-bbf1abc83d07  |45ae036e-8b6b-4130-81c6-451ba020653e  |2398ce72-99c6-4995-b19c-ccbc9f57144c  |b032e3b2-5ab0-4b14-b88f   |1.0       |CERTIFIED  |
  +--------------------------------------+--------------------------------------+--------------------------------------+--------------------------+----------+------------+
  |848865b6-d9f0-4767-a5bf-c240fb832a46  |da9d92e5-ca04-4101-bad0-bdc17cf6f089  |6ae8e80c-c814-45fe-ba97-4d94d98c645f  |htipl-vf                  |1.0       |CERTIFIED  |
  +--------------------------------------+--------------------------------------+--------------------------------------+--------------------------+----------+------------+
  |b1b6e4d0-8043-49b4-917e-3d2a762b5a56  |017aa6b7-60c9-457a-b593-8cef623dbe3b  |7819f9f0-cbdb-40b6-acd0-edcdbdab119c  |21b8a269-6377-42f9-9a98   |1.0       |CERTIFIED  |
  +--------------------------------------+--------------------------------------+--------------------------------------+--------------------------+----------+------------+
  |c7131963-1bbf-474d-a773-4f201a3480d3  |cba595e5-3a0f-482b-ba74-078a15d96756  |1a15a0a0-b040-47b5-adbd-e0c3b2e52417  |fe631a9d-99c1-4b7c-8a06   |1.0       |CERTIFIED  |
  +--------------------------------------+--------------------------------------+--------------------------------------+--------------------------+----------+------------+

After successful VF creation you can proceed for service model creation.

Create service model
--------------------

Run following command to create service model.

::

  onap>service-model-create -x htipl-service -y htipl-serv-desc -z htipl-123456 -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +----------+--------------------------------------+
  |property  |value                                 |
  +----------+--------------------------------------+
  |ID        |3f8ff680-6b71-45e9-8bee-72628d7f443b  |
  +----------+--------------------------------------+

Add VF to service model
-----------------------

Associate VF to service model using following command.

::

  onap>service-model-add-vf -x 3f8ff680-6b71-45e9-8bee-72628d7f443b -y da9d92e5-ca04-4101-bad0-bdc17cf6f089 -z htipl-vf -b 1.0 -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +----------+----------------------------------------------------+
  |property  |value                                               |
  +----------+----------------------------------------------------+
  |ID        |3f8ff680-6b71-45e9-8bee-72628d7f443b.da9d92e5-ca04  |
  |          |-4101-bad0-bdc17cf6f089.htiplvf0                    |
  +----------+----------------------------------------------------+

Submit Service for certification
--------------------------------

Now, service is ready for initializing certification request. Use following command
to initiate service certification request.

::

  onap>service-model-certify-request -b 3f8ff680-6b71-45e9-8bee-72628d7f443b -u cs0008 -p demo123456! -m http://192.168.17.5:8080

Certify service
---------------

Now, tester(jm0007) can see service in his dashboard and he can start with service
certification. Tester can use following command to mark certification start and
complete.

::

  onap>service-model-certify-start -b 3f8ff680-6b71-45e9-8bee-72628d7f443b -u jm0007 -p demo123456! -m http://192.168.17.5:8080

  onap>service-model-certify-complete -b 3f8ff680-6b71-45e9-8bee-72628d7f443b -u jm0007 -p demo123456! -m http://192.168.17.5:8080

  onap>service-model-list -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +--------------------------------------+--------------------------------------+----------------+----------+------------+----------------------------+
  |uuid                                  |invariant-uuid                        |name            |version   |status      |distribution-status         |
  +--------------------------------------+--------------------------------------+----------------+----------+------------+----------------------------+
  |fc5e1e81-786d-49e5-acfd-e933e291b1a4  |02258f88-2d84-4503-bd8e-2e3988e65e54  |htipl-service   |1.0       |CERTIFIED   |DISTRIBUTION_NOT_APPROVED   |
  +--------------------------------------+--------------------------------------+----------------+----------+------------+----------------------------+

Approve service model
---------------------

After successful certification, service will be submitted to governor(gv001) to
approve service model.

::

  onap>service-model-approve -b fc5e1e81-786d-49e5-acfd-e933e291b1a4 -u gv0001 -p demo123456! -m http://192.168.17.5:8080

  onap>service-model-list -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +--------------------------------------+--------------------------------------+----------------+----------+------------+------------------------+
  |uuid                                  |invariant-uuid                        |name            |version   |status      |distribution-status     |
  +--------------------------------------+--------------------------------------+----------------+----------+------------+------------------------+
  |fc5e1e81-786d-49e5-acfd-e933e291b1a4  |02258f88-2d84-4503-bd8e-2e3988e65e54  |htipl-service   |1.0       |CERTIFIED   |DISTRIBUTION_APPROVED   |
  +--------------------------------------+--------------------------------------+----------------+----------+------------+------------------------+

Distribute service model
------------------------

Now operator can distribute the service and it will be available in VID dashboard.

::

  onap>service-model-distribute -b fc5e1e81-786d-49e5-acfd-e933e291b1a4 -u op0001 -p demo123456! -m http://192.168.17.5:8080

  onap>service-model-list -u cs0008 -p demo123456! -m http://192.168.17.5:8080

  +--------------------------------------+--------------------------------------+----------------+----------+------------+----------------------+
  |uuid                                  |invariant-uuid                        |name            |version   |status      |distribution-status   |
  +--------------------------------------+--------------------------------------+----------------+----------+------------+----------------------+
  |fc5e1e81-786d-49e5-acfd-e933e291b1a4  |02258f88-2d84-4503-bd8e-2e3988e65e54  |htipl-service   |1.0       |CERTIFIED   |DISTRIBUTED           |
  +--------------------------------------+--------------------------------------+----------------+----------+------------+----------------------+

Creating Cloud, Customer and Service-type
=========================================

It is required to specify the cloud configuration in AAI system before deploying
the service. Following are the steps to configure cloud and related information
for service deployment.

Create Cloud
------------

Use following command to create cloud and region in AAI system.

::

  onap>cloud-create -x htipl-cloud -y htipl-region -u AAI -p AAI -m https://192.168.17.13:8447


  onap>cloud-list -u AAI -p AAI -m https://192.168.17.13:8447

  +--------------+--------------+------------------+
  |cloud         |region        |resource-version  |
  +--------------+--------------+------------------+
  |htipl-cloud   |htipl-region  |1514263912528     |
  +--------------+--------------+------------------+
  |pod25dns      |RegionOne     |1513659267169     |
  +--------------+--------------+------------------+
  |pod25         |RegionOne     |1513659266151     |
  +--------------+--------------+------------------+

Create tenant
-------------

Create tenant within region. Following is command to create tenant.

::

  onap>tenant-create -x htipl-cloud -y htipl-region -z htipl-tenant -r htipl-tenant -u AAI -p AAI -m https://192.168.17.13:8447

  onap>tenant-list --cloud htipl-cloud --region htipl-region -u AAI -p AAI -m https://192.168.17.13:8447

  +--------------+--------------+------------------+
  |tenant-id     |tenant-name   |resource-version  |
  +--------------+--------------+------------------+
  |htipl-tenant  |htipl-tenant  |1514265496768     |
  +--------------+--------------+------------------+

Create Customer
---------------

A customer subscribes for the service. Use follwing command to create customer.

::

  onap>customer-create -x htipl-customer -y htipl-customer -u AAI -p AAI -m https://192.168.17.13:8447

  onap>customer-list -u AAI -p AAI -m https://192.168.17.13:8447

  +----------------+------------------+
  |name            |resource-version  |
  +----------------+------------------+
  |htipl-customer  |1514264928457     |
  +----------------+------------------+

Create service type
-------------------

Use following command to create service type.

::

  onap>service-type-create -x htipl-service -u AAI -p AAI -m https://192.168.17.13:8447

  onap>service-type-list -u AAI -p AAI -m https://192.168.17.13:8447

  +--------------------------------------+----------------+------------------+
  |service-type-id                       |service-type    |resource-version  |
  +--------------------------------------+----------------+------------------+
  |f96017b1-9fcb-4d3c-ae17-d9b1fdef4415  |htipl-service   |1514265655713     |
  +--------------------------------------+----------------+------------------+

Create subscription
-------------------

Use following command to create subscription for a customer. It requires cloud
information and service-type information.

::

  onap>subscription-create -x htipl-customer -z htipl-cloud -r htipl-region -c htipl-tenant -e htipl-service  -u AAI -p AAI -m https://192.168.17.13:8447

  onap>subscription-list --customer-name htipl-customer -u AAI -p AAI -m https://192.168.17.13:8447

  +----------------+------------------+
  |service-type    |resource-version  |
  +----------------+------------------+
  |htipl-service   |1514265923244     |
  +----------------+------------------+

Create Service-instance, VF and deploy VF
=========================================

Now, all the required configuration and artifacts are available to SO service to
create service-instance and VF.

Create service instance
-----------------------

Using following command you can create service-instance in specified cloud region.
Command requires service model identifier which can be obtained from previously
used CLIs for creating and listing service model.

::

  onap>service-create --cloud-region RegionOne --tenant-id 60817ff9baa547d5857a6b7ff0dc567b --model-invariant-id f812e618-3abf-4c7a-901e-f2820a73eb2e --model-uuid 84ad850a-3d0b-451d-b97b-083bbf39890d --model-name demoVLB --model-version 1.0 -c Demonstration --instance-name service-vlb-instance-1 --service-type vLB -u InfraPortalClient -p password1$ -m http://192.168.17.7:8080

  +------------+--------------------------------------+
  |property    |value                                 |
  +------------+--------------------------------------+
  |service-id  |1561a325-a64b-47ab-86f4-459e334292f0  |
  +------------+--------------------------------------+

Create VF
---------

Using following command you can create VF in specified cloud region.
Command requires VF model identifier which can be obtained from previously
used CLIs for creating and listing VF model.

::

  onap>vf-create --cloud-region RegionOne --tenant-id onap --product-family vLB --instance-name service-vlb-instance-1-vnf-1 --service-instance-id 1561a325-a64b-47ab-86f4-459e334292f0 --vf-model-invariant-id 4982d10b-09af-46b9-8317-c92d2658ae68 --vf-model-uuid b2a40a03-c85e-4b6c-8ade-f90e5f2ce2d4 --vf-model-name 2932d051-6aad-4c0d-ac2d --vf-model-version 1.0 --vf-model-customization-id 3741acf1-5929-42e6-b234-4b9051d09a54 --service-model-invariant-id f812e618-3abf-4c7a-901e-f2820a73eb2e --service-model-uuid 84ad850a-3d0b-451d-b97b-083bbf39890d --service-model-name demoVLB --service-model-version 1.0 -m http://192.168.17.7:8080 -u InfraPortalClient -p password1$

  +----------+--------------------------------------+
  |property  |value                                 |
  +----------+--------------------------------------+
  |vf-id     |addb80ab-8c95-49b3-a2b7-907a02553319  |
  +----------+--------------------------------------+

Create VF-module
----------------

This command is not yet verified with ONAP stack but you can go ahed by referring :ref:`vf-module-create` for vf module creation.
On success of above command you can see VNF is deployed in given cloud.


