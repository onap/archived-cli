.. This work is licensed under a Creative Commons Attribution 4.0 International License.


CLI Release Notes
=================


Version: 3.0.0
--------------

:Release Date: 2019-05-31

**New Features**

- Beijing support EOL
- Enhanced schema management
- Added execution support for capturing the details of every execution includes parameters, results.
- End-end service creation using ONAP commands are provided.
-

**Security Notes**

*Fixed Security Issues*

*Known Security Issues*

- In default deployment CLI (cli) exposes HTTP port 30260 outside of cluster. [`OJSI-129 <https://jira.onap.org/browse/OJSI-129>`_]

*Known Vulnerabilities in Used Modules*

Version: 2.0.5
--------------

:Release Date: 2018-11-30

**New Features**

- Amsterdam support EOL
- Added gRPC to run the ONAP commands remotely
- Added Command profile to support VTP (VNF Test Platform) requirements in VNFSDK and added new commands for supporting it
- VTP leverages OCLIP for providing the test center (discover the test cases) and test runner (run the test cases)

**Security Notes**

CLI code has been formally scanned during build time using NexusIQ and all Critical vulnerabilities
have been addressed, items that remain open have been assessed for risk and determined to be false
positive. The CLI open Critical security vulnerabilities and their risk assessment have been
documented as part of the `project <https://wiki.onap.org/pages/viewpage.action?pageId=45298770>`_.

Quick Links:
    - `CLI project page <https://wiki.onap.org/display/DW/Command+Line+Interface+Project>`_

    - `Passing Badge information for CLI <https://bestpractices.coreinfrastructure.org/en/projects/1540>`_

    - `Project Vulnerability Review Table for CLI <https://wiki.onap.org/pages/viewpage.action?pageId=45298770>`_


Version: 2.0.0
--------------

:Release Date: 2018-06-07

**New Features**

In this release, CLI provides all those features supported in previous release. In addition,
following features are enabled:

* Profile support (like Heat environment file)
* Batch support to run same command with different set of arguments feed from param-file)
* Policy management
* VNF/PNF management
* OCLIP SNMP profile support (get command)
* OCLIP CLI verification support (--verify)
* Portal CLI application is enabled for all users.

Similar to earlier release, user can perform customer, subscription, cloud and tenant management only
by using CLI as portal does not support.

In addition, it made following changes to OCS 1.0

* http profile is enabled with service section to capture micro-service catalog and auth information
and body section could be customized using context.

* New default parameters verify and context are added.

**Bug Fixes**

* `CLI-105 <https://jira.onap.org/browse/CLI-105>`_   Ignore those yamls which does not have open cli schema version
* `CLI-103 <https://jira.onap.org/browse/CLI-103>`_   Support HTTP delete with http body (SO, AAI requires)
* `CLI-101 <https://jira.onap.org/browse/CLI-101>`_   Add context parameter for ignoring empty json nodes in http body
* `CLI-99 <https://jira.onap.org/browse/CLI-99>`_    Optional parameter's default value is set to blank
* `CLI-95 <https://jira.onap.org/browse/CLI-95>`_    Add profile support for multiple sessions
* `CLI-94 <https://jira.onap.org/browse/CLI-94>`_    EOL openecomp CLIs
* `CLI-86 <https://jira.onap.org/browse/CLI-86>`_    Create documentation for end to end cli commands for onap
* `CLI-74 <https://jira.onap.org/browse/CLI-74>`_    Setup Mock environment for a command
* `CLI-61 <https://jira.onap.org/browse/CLI-61>`_    Add validation for results section in yaml

**Security Notes**

CLI code has been formally scanned during build time using NexusIQ and all Critical vulnerabilities
have been addressed, items that remain open have been assessed for risk and determined to be false
positive. The CLI open Critical security vulnerabilities and their risk assessment have been
documented as part of the `project <https://wiki.onap.org/pages/viewpage.action?pageId=28377287>`_.

Quick Links:
    - `CLI project page <https://wiki.onap.org/display/DW/Command+Line+Interface+Project>`_

    - `Passing Badge information for CLI <https://bestpractices.coreinfrastructure.org/en/projects/1540>`_

    - `Project Vulnerability Review Table for CLI <https://wiki.onap.org/pages/viewpage.action?pageId=28377287>`_

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
