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
* Customer and subscription management (Only available thru CLI)
* Cloud and tenant on-boarding (Only available thru CLI)
* Design-time artifacts (VSP, VF, Service model) management
* Network-service (vFW, vLB, etc)  life-cycle management

Version: 2.0.0
--------------

:Release Date: 2018-05-24

**New Features**

In this release CLI provides all those features supported in previous release. In addition,

* Policy management
* VNF/PNF management

**Bug Fixes**

* CLI-105    Ignore those yamls which does not have open cli schema version
* CLI-103    Support HTTP delete with Body
* CLI-101    Add context param under http for ignoring empty json nodes
* CLI-99    Optional Parameter default value set to blank
* CLI-98    Debug option is not working
* CLI-97    schema-validate fails to validate http profile
* CLI-96    broken interactive console
* CLI-95    Add profile support for multiple sessions
* CLI-94    EOL openecomp CLIs
* CLI-87    Fix yaml for reported validation error
* CLI-86    Create documentation for end to end cli commands for onap
* CLI-82    Spelling - customer-list, customer-show in browser cli
* CLI-75    long option  need not be same as name
* CLI-74    Setup Mock environment for a command
* CLI-61    Add validation for results section in OCS in schema-validate

