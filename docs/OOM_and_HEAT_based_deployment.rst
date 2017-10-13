.. _OOM_and_HEAT_based_deployment:
.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.

CLI deployment using OOM and HEAT
=================================

ONAP CLI is integrated with OOM and HEAT based ONAP deployment.

HEAT based deployment
----------------------

As part of HEAT based deployment, ONAL CLI docker image is integrated into ONAP Portal and when portal_vm is
successfully installed, it will be available at http://portal_vm-ip:8080

To troubleshoot any issues in CLI, please login to portal_vm and CLI docker runs with image onap/cli


OOM based deployment
--------------------
ONAL CLI docker image is integrated with OOM and is available under name 'cli'