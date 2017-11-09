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
* service-delete, vf-delete and vfmodule-delete commands are not supported
