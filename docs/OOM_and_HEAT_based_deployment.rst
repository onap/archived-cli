.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.

.. _OOM_and_HEAT_based_deployment:

CLI deployment using OOM and HEAT
=================================

ONAP CLI deployment is enabled with OOM and HEAT based ONAP installation.

OpenStack HEAT based deployment
-------------------------------

As part of HEAT based deployment, ONAL CLI docker image is integrated into ONAP Portal and when portal_vm is
successfully installed, it will be available at http://portal.api.simpledemo.openecomp.org:8080

To troubleshoot any issues in CLI, please login to portal_vm and CLI docker runs with image onap/cli


OOM based deployment
--------------------
ONAL CLI docker image is integrated with OOM and is available under name 'cli'

Once successfully installed, CLI is available under onap-cli name-space. So user can access the CLI at
http://<k8s-host>:30260
