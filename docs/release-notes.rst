.. This work is licensed under a Creative Commons Attribution 4.0 International License.

CLI Release Notes
=================

Version: 1.1.0
--------------

:Release Date: 2017-11-16

ONAP CLI helps user to perform any operations over ONAP from command line, Linux console and web-console.

NOTE: ONAP portal does not support following features, so user is recommended to use them over CLI:

* Customer and subscription management
* VNF Cloud on-boarding
* VNF tenant on-boarding

**New Features**

* ONAP CLI available as nexus artifacts and docker images.
  For more details, :ref:`cli_installation_guide`.
* It is delivered as part of portal installation during HEAT based installation. Also its supported in OOM as well.
  For more details, :ref:`OOM_and_HEAT_based_deployment`.
* Released first version of **Open Command Line Interface Platform (OCLIP)**, industry first CLI platform for implementing CLI **just by texting (YAML) and no coding** .
  For more details, :ref:`OCLIP`.
* It provides **Open Command Specification (OCS) 1.0** for defining the commands in YAML format, similar to Open API Specification (API).
  For more details, :ref:`open_cli_schema_version_1_0`.
* All Onap commands are developed using OCLIP by authoring set of YAML files.
  For more details, :ref:`cli_cmd_help`.
* All commands are tested completely and samples also provided for reference.
  For more details, :ref:`_cli_cmd_sample`.
* User could develop new commands on-the-fly and deploy it and use.
  For more details, :ref:`cli_developer_guide`.

**Know Issues**

* VF customizationUUID is not available over SDC REST API, so user is recommended to refer it from SDC portal
* CLI impersonate VID as source while creating service, vf and vf-modules, due to SO limitation (SO-272)
* vsp-add-artifact command sometime fails to upload the file, in that case, please use option -d to see the debug information

