.. This work is licensed under a Creative Commons Attribution 4.0 International License.

CLI Release Notes
=================

Version: 1.1.0
--------------

:Release Date: 2017-11-16

:ref:`cli_index` helps user to operate ONAP from Linux command console and web command console.

**New Features**

In ONAP Amsterdam release, CLI provides following ONAP operations as command:

* ONAP service discovery
* External system on-boarding (VIM, VNFM, EMS & SDNC)
* Customer and subscription management ******
* Cloud and tenant on-boarding ******
* Design-time artifacts (VSP, VF, Service model) management
* Network-service (vFW, vLB, etc)  life-cycle management

****** : Only available thru CLI.

**Know Issues**

* VF customizationUUID is not available over SDC REST API, so user is recommended to refer it from SDC portal, while creating VF.
* CLI impersonate VID as source while creating service, VF and VF module, due to SO limitation ( SO-272_ )
* vsp-add-artifact command sometime fails to upload the file, in that case, please use option -d to see the debug information
* service-delete, vf-delete, vfmodule-create and vfmodule-delete commands are not verified ( CLI-63_ )

.. _SO-272: https://jira.onap.org/browse/SO-272
.. _CLI-63: https://jira.onap.org/browse/CLI-63