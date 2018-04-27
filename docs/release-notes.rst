.. This work is licensed under a Creative Commons Attribution 4.0 International License.

CLI Release Notes
=================

Version: 2.0.0
--------------

:Release Date: 2018-05-24

**New Features**

In this release, CLI provides all those features supported in previous release. In addition,
following features are enabled:

* Policy management
* VNF/PNF management
* OCLIP SNMP profile support
* OCLIP CLI verification support

Similar to earlier release, user can perform customer, subscription, cloud and tenant management only
by using CLI as portal does not support.

In addition, it made following changes to OCS 1.0

* http profile is enabled with

-- service to capture micro-service catalog and auth information

-- body section could be customized using context

* New default parameters verify and context are added.

**Bug Fixes**

* CLI-105   Ignore those yamls which does not have open cli schema version
* CLI-103   Support HTTP delete with http body (SO, AAI requires)
* CLI-101   Add context parameter for ignoring empty json nodes in http body
* CLI-99    Optional parameter's default value is set to blank
* CLI-95    Add profile support for multiple sessions
* CLI-94    EOL openecomp CLIs
* CLI-86    Create documentation for end to end cli commands for onap
* CLI-74    Setup Mock environment for a command
* CLI-61    Add validation for results section in yaml

Version: 1.1.0
--------------

:Release Date: 2017-11-16

:ref:`cli_index` helps user to operate ONAP from Linux command console and web command console.

**New Features**

In ONAP Amsterdam release, CLI provides following ONAP operations as command:

* ONAP micro-service discovery
* External system on-boarding (VIM, VNFM, EMS & SDNC)
* Customer and Subscription management (Only available thru CLI)
* Cloud and Tenant on-boarding (Only available thru CLI)
* Design-time artifacts (VSP, VF, Service model) management
* Network-service (vFW, vLB, etc)  life-cycle management
