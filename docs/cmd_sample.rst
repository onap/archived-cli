.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.

.. _cli_cmd_sample:

CLI Samples
==============



onap-1.1
========



cloud-delete
------------

input::

 --cloud-name huawei-cloud-test --region-name bangalore-test --resource-version 1509027982352


cloud-create
------------

input::

 --cloud-name huawei-cloud --region-name bangalore


cloud-list
----------


vnfm-list
---------


vnfm-unregister
---------------

input::

 --vnfm-id c0ecd788-22f4-49a4-a969-68051cd517e7 --resource-version 1509094328901


vnfm-show
---------

input::

 --vnfm-id c0ecd788-22f4-49a4-a969-68051cd517e7


vnfm-register
-------------

input::

 --vim-id acc5e14e-1320-4ab5-97fe-b7cc82ad03f2 --name vnfm1 --type OpenStack --vendor ONAP --vnfm-version 1.0 --url http://10.0.1.1 --username admin --password password


vim-list
--------

input::

 --cloud-name huawei-cloud --region-name bangalore --long


vim-register
------------

input::

 --cloud-name huawei-cloud --region-name bangalore --name vim1 --type OpenStack --vendor Devstack --vim-version newton --url http://192.168.16.149/identity --username onap --password onap --cloud-domain default --default-tenant onap


vim-unregister
--------------

input::

 --vim-id 810edb5a-42e9-462d-9587-96bc9272ac27 --cloud-name huawei-cloud --region-name bangalore --resource-version 1509093590932


ems-register
------------

input::

 --name ems1 --type ems --vendor boco --ems-version 1.0 --url http://10.0.0.1 --username admin --password password --remote-path sample


ems-unregister
--------------

input::

 --ems-id 51a480ed-649c-4c47-a84b-f966dc4f9554 --resource-version 1509095358658


ems-list
--------


ems-show
--------

input::

 --ems-id 51a480ed-649c-4c47-a84b-f966dc4f9554


subscription-delete
-------------------

input::

 --customer-name kanag --service-type vFW-kanag --resource-version 1509079144326


subscription-list
-----------------

input::

 --customer-name kanag


subscription-create
-------------------

input::

 --customer-name kanag --cloud-name huawei-cloud --cloud-region bangalore --cloud-tenant-id e18173e6-6a13-4614-a13c-3859e7321103 --service-type vFW-kanag


customer-delete
---------------

input::

 --customer-name test --resource-version 1509029022293


customer-show
-------------

input::

 --customer-name kanag --long


customer-create
---------------

input::

 --customer-name test --subscriber-name test


customer-list
-------------


vf-show
-------

input::

 --vf-id 1a667ce8-8b8b-4f59-ba5c-b162ae462fef -m https://192.168.17.111:8443 -u AAI -p AAI


service-list
------------

input::

 -x Demonstration -y vLB --long


vf-list
-------

input::

 -x Demonstration -y vLB --service-id 2ad87511-4289-4bd7-ab0b-0b29d3d4c8ee


service-type-list
-----------------


service-type-delete
-------------------

input::

 --service-type-id de1fa33f-f676-42e5-b79a-a39825c19b1d --resource-version 1509029708094


service-type-create
-------------------

input::

 --service-type test


microservice-create
-------------------

input::

 --service-name test --service-version v1 --service-url C:/Git/test --path C:/Git/test 10.0.0.1 8080


microservice-list
-----------------

input::

 --long


microservice-show
-----------------

input::

 --service-name test --service-version v1 --long


microservice-delete
-------------------

input::

 --service-name test --service-version v1 --host-url http://192.168.17.23:80 --node-ip 23.14.15.156 --node-port 80


vlm-feature-group-list
----------------------

input::

 --vlm-id cf2d907d998e44698ce3b4cded5f66a7 --vlm-version 2.0


vlm-submit
----------

input::

 --vlm-id cf2d907d998e44698ce3b4cded5f66a7 --vlm-version 1.1


vlm-revert
----------

input::

 --vlm-id cf2d907d998e44698ce3b4cded5f66a7 --vlm-version 0.1


vlm-entitlement-pool-list
-------------------------

input::

 --vlm-id cf2d907d998e44698ce3b4cded5f66a7 --vlm-version 2.0


vlm-checkout
------------

input::

 --vlm-id cf2d907d998e44698ce3b4cded5f66a7 --vlm-version 0.1


vlm-aggreement-create
---------------------

input::

 --name kanag-cli-la --description kanag cli la --vlm-feature-group-id 3a2fb75b52a54e9c8093e7c154210f9e --vlm-id cf2d907d998e44698ce3b4cded5f66a7 --vlm-version 1.1


vlm-key-group-create
--------------------

input::

 --vlm-id cf2d907d998e44698ce3b4cded5f66a7 --name kanag-cli-kg --description Kanag CLI key group -d --vlm-version 0.1


vlm-feature-group-create
------------------------

input::

 --name kanag-cli-fg --description Kanag cli feature group --vlm-id cf2d907d998e44698ce3b4cded5f66a7 --vlm-version 0.1 --vlm-key-group-id c37a1f205f444161a573f55dfec5f170 --vlm-entitle-pool-id aa61080fd965455ba5edbf60f4e375ef --part-number 123455 --manufacture-reference-number mkr123456


vlm-create
----------

input::

 --vendor-name kanag-cli --description First License created from CLI


vlm-entitlement-pool-create
---------------------------

input::

 --name kanag-cli-ep --vlm-id cf2d907d998e44698ce3b4cded5f66a7 --description kanag vlm ep --manufacture-reference-number mkr123456 -d --vlm-version 0.1


vlm-checkin
-----------

input::

 --vlm-id cf2d907d998e44698ce3b4cded5f66a7 --vlm-version 1.1


vlm-key-group-list
------------------

input::

 --vlm-id cf2d907d998e44698ce3b4cded5f66a7 --vlm-version 2.0


vlm-aggreement-list
-------------------

input::

 --vlm-id cf2d907d998e44698ce3b4cded5f66a7 --vlm-version 2.0


vlm-list
--------

input::

 --long


vsp-show
--------

input::

 --vsp-id a8cd007fa101470e98516cd4549c568f --vsp-version 1.0 --long


vsp-checkin
-----------

input::

 --vsp-id f19cad8343794e93acb9cda2e4126281 --vsp-version 0.1


vsp-submit
----------

input::

 --vsp-id f19cad8343794e93acb9cda2e4126281 --vsp-version 0.1


vsp-create
----------

input::

 --vsp-name kanag-cli-VLB --vsp-description VLB created from CLI --vlm-agreement-id 77e151d0503b45ecb7e40f5f5f1a887e --vlm-version 2.0 --vlm-feature-group-id 3a2fb75b52a54e9c8093e7c154210f9e --vlm-id cf2d907d998e44698ce3b4cded5f66a7 --vlm-vendor Kanag-cli


vsp-list
--------


vsp-upload
----------

input::

 --vsp-id E563CB23A6BE49AF9A84CF579DAFB929  --vsp-file /home/user/vFW.zip


vsp-package
-----------

input::

 --vsp-id f19cad8343794e93acb9cda2e4126281 --vsp-version 0.2


vsp-checkout
------------

input::

 --onap-username cs0008 --onap-password demo123456!  --host-url http://localhost:8080 --vsp-id E563CB23A6BE49AF9A84CF579DAFB929


vsp-validate
------------

input::

 --vsp-id f19cad8343794e93acb9cda2e4126281 --vsp-version 0.1


vsp-revert
----------

input::

 --vsp-id e65baf44883e4868ba96f9faed9ba97a --vsp-version 0.2


vsp-checkout
------------

input::

 --vsp-id f19cad8343794e93acb9cda2e4126281 --vsp-version 0.1


service-model-checkin
---------------------

input::

 --service-model-id 7b427dbf-685b-4ba9-8838-a9b3b3c8e584


service-model-certify-request
-----------------------------

input::

 --service-model-id 7b427dbf-685b-4ba9-8838-a9b3b3c8e584


service2vf-model-list
---------------------

input::

 --service-model-id 0f4203a8-a314-47bb-9a7d-28157652cec4


service-model-certify-start
---------------------------

input::

 --service-model-id 7b427dbf-685b-4ba9-8838-a9b3b3c8e584


service-model-add-vf
--------------------

input::

 --service-model-id 7b427dbf-685b-4ba9-8838-a9b3b3c8e584 --vf-id 828be6cf-c11b-4759-ac37-b1b79f86a4b4 --vf-name kanag-cli-VLB --vf-version 1.0 -d


service-model-certify-complete
------------------------------

input::

 --service-model-id 7b427dbf-685b-4ba9-8838-a9b3b3c8e584


service-model-list
------------------


service-model-create
--------------------

input::

 --name kanag-cli-VLB --description VLB created from CLI --project-code kanag-123456


service-model-distribute
------------------------

input::

 --service-model-id 7b427dbf-685b-4ba9-8838-a9b3b3c8e584


vf2vfmodule-model-list
----------------------

input::

 --vf-id 66269482-0b27-40e3-9c4d-6a26fb67d9ff


vf-model-certify-request
------------------------

input::

 --vf-id 66269482-0b27-40e3-9c4d-6a26fb67d9ff


vf-model-create
---------------

input::

 --name kanag-cli-VLB --description VF created from CLI --vendor-name Kanag-cli --vsp-id f19cad8343794e93acb9cda2e4126281 --vsp-version 2.0


vf-model-certify-start
----------------------

input::

 --vf-id 66269482-0b27-40e3-9c4d-6a26fb67d9ff


vf-model-list
-------------


vf-model-certify-complete
-------------------------

input::

 --vf-id 66269482-0b27-40e3-9c4d-6a26fb67d9ff


vf-model-checkin
----------------

input::

 --vf-id 66269482-0b27-40e3-9c4d-6a26fb67d9ff


service-create
--------------

input::

 --cloud-region RegionOne --tenant-id onap --model-invariant-id 1de901ed-17af-4b03-bc1f-41659cfa27cb --model-uuid ace39141-09ec-4068-b06d-ac6b23bdc6e0 --model-name demoVLB --model-version 1.0 -c Demonstration --instance-name sample-service-onap-cli-13 --service-type vLB


vf-create
---------

input::

 --cloud-region RegionOne --tenant-id onap --product-family vLB --instance-name vlb-cli-sample-11 --service-instance-id 2ad87511-4289-4bd7-ab0b-0b29d3d4c8ee --vf-model-invariant-id cc34cd54-dd7c-44cd-8847-f9577c6f1a49 --vf-model-uuid 8b1f63f3-e0cc-4c27-8903-fafe2f25bfbe --vf-model-name 847cb26a-59a6-475a-94dd --vf-model-version 1.0 --vf-model-customization-id cf893f5a-1bb1-4e32-a92b-2456e12178f8 --service-model-invariant-id 1de901ed-17af-4b03-bc1f-41659cfa27cb --service-model-uuid ace39141-09ec-4068-b06d-ac6b23bdc6e0 --service-model-name demoVLB --service-model-version 1.0 -m http://192.168.17.121:8080 -u InfraPortalClient -p password1$

onap-1.0
========



vsp-create
----------

input::

 --onap-username cs0008 --onap-password demo123456! --host-url http://locahost:8080 --vsp-name demo-vsp2 --vsp-vendor-name 5aa8a88c --vsp-vendor-id DBB202617952486691C1E35C9    621CD4E --license-agreement-id E19DBDDB137B494385F9535325BFD585 --feature-group-id 94835F096DAF4ED78781A394A4F22AD1

output::

 +----------+----------------------------------+
 |property  |value                             |
 +----------+----------------------------------+
 |ID        |6897DFFF8E864F84AD17A34B7FB936A3  |
 +----------+----------------------------------+

vsp-submit
----------

input::

 --onap-username cs0008 --onap-password demo123456!  --host-url http://localhost:8080 --vsp-id E563CB23A6BE49AF9A84CF579DAFB929


vsp-list
--------

input::

 --onap-username cs0008 --onap-password demo123456!  --host-url http://localhost:8080

output::

 +----------------------------------+------------+
 |ID                                |name        |
 +----------------------------------+------------+
 |E563CB23A6BE49AF9A84CF579DAFB929  |demo-vsp2   |
 +----------------------------------+------------+
 |79E62AA736C34E359B869E757D3DDBBE  |demo-vsp1   |
 +----------------------------------+------------+
 |928B828EE3CC46B99B92D7B9A2AB2118  |demo-vsp    |
 +----------------------------------+------------+

vsp-checkin
-----------

input::

 --onap-username cs0008 --onap-password demo123456!  --host-url http://localhost:8080 --vsp-id E563CB23A6BE49AF9A84CF579DAFB929


vsp-upload
----------

input::

 --onap-username cs0008 --onap-password demo123456! --host-url http://localhost:8080 --vsp-id E563CB23A6BE49AF9A84CF579DAFB929  --vsp-file /home/user/vFW.zip


vsp-show
--------

input::

 --onap-username cs0008 --onap-password demo123456!  --host-url http://localhost:8080 --vsp-id 2DEB6CB4B082415BB7A697C9CD1273BA

output::

 +----------+----------------------------------+--------------------------+
 |name      |ID                                |description               |
 +----------+----------------------------------+--------------------------+
 |test-vsp  |2DEB6CB4B082415BB7A697C9CD1273BA  |vendor software product   |
 +----------+----------------------------------+--------------------------+