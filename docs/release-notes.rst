.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. _release_notes:


CLI Release Notes
=================

Version: 6.0.1
--------------

:Release Date: 2021-04-01

*New Features*

1. Metadata support is added in OCS YAML 


*Security Notes*

Fixed Security Issues

Known Security Issues

Known Vulnerabilities in Used Modules


Version: 6.0.0
--------------

:Release Date: 2020-11-04

*New Features*

1. Enabled auto discover and registration of products functionalities as commands

2. Profile managment commands are added

*Security Notes*

Fixed Security Issues

Known Security Issues

Known Vulnerabilities in Used Modules


Version: 5.0.4
--------------

:Release Date: 2020-04-07

**New Features**

1. Improved HTTP timeout

2. Enable Non-Root user in docker container

3. Improved SDC and VFC commands under onap-dublin product

4. CLI web portal is made to run HTTPS 443 port and Web consoles at HTTPS 9090

5. Vulnerability is improved from previous release by using secured 3rd-party libraries

**Security Notes**

*Fixed Security Issues*

*Known Security Issues*

*Known Vulnerabilities in Used Modules*


Version: 4.0.0
--------------

:Release Date: 2019-10-24

**New Features**

1. ONAP CLI is used to end-end automation of VNF service provision and termination for both HEAT and TOSCA based VNF service.

2. Multi-level orchestration capability is made available to use and user could use python, or similar scripting/workflow engine for the same

3. VNF Test Platform(VTP) has used the Open Command Platform (OCOMP) - part of ONAP CLI project, for VNF life cycle testing (create and delete)

4. ONAP CLI for ONAP Dublin version is stabilized to use it for both service provisioning and testing purpose

5. ONAP CLI for el alto is enabled as experimental (dev) mode

**Security Notes**

*Fixed Security Issues*

*Known Security Issues*

- In default deployment CLI (cli) exposes HTTP port 30260 outside of cluster. [`OJSI-129 <https://jira.onap.org/browse/OJSI-129>`_]
- In default deployment CLI (cli) exposes HTTP port 30271 outside of cluster. [`OJSI-135 <https://jira.onap.org/browse/OJSI-135>`_]
- CVE-2019-12130 - CLI exposes unprotected APIs/UIs on port 30271. [`OJSI-205 <https://jira.onap.org/browse/OJSI-205>`_]

*Known Vulnerabilities in Used Modules*

Version: 3.0.0
--------------

:Release Date: 2019-05-31

**New Features**

- Beijing support EOL
- Enhanced schema management
- Added execution support for capturing the details of every execution includes parameters, results. This feature is used in VTP.
- End-end service creation using ONAP commands are provided. [`More details <https://github.com/onap/integration/tree/master/test/hpa_automation>`_]

**Security Notes**

*Fixed Security Issues*

*Known Security Issues*

- In default deployment CLI (cli) exposes HTTP port 30260 outside of cluster. [`OJSI-129 <https://jira.onap.org/browse/OJSI-129>`_]
- In default deployment CLI (cli) exposes HTTP port 30271 outside of cluster. [`OJSI-135 <https://jira.onap.org/browse/OJSI-135>`_]
- CVE-2019-12130 - CLI exposes unprotected APIs/UIs on port 30271. [`OJSI-205 <https://jira.onap.org/browse/OJSI-205>`_]

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
