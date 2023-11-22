.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.

:orphan:

.. _OCLIP:

Open CLI Platform (OCLIP)
=========================

**No coding, just texting !**

In Telco/IT world, Any software platform/platform with out CLI is considered as in-complete and as there is no platform exists
 today for implementing CLI, similar to Tomcat exists for HTTP applications, and followings are the main problems:

* Vendor/Community chooses some appropriate frameworks/libraries to develop CLI and it incurs large amount of efforts/time/money.
* For any bug-fixes/features, user needs to wait for (max. of one release cycle approx. 6 months)

To address these problems, in open-source ONAP community, Open CLI Platform (OCLIP) is developed and is successfully used for developing CLI for ONAP.
It helps to implement the commands just by writing YAML files for any cloud enabled software products and also provides plug-in mechanism to implement the commands which requires specific implementation.

Advantages
----------

* Saves lots of effort, time and money spend by communities and/or vendors, who have been implementing the commands by using some framework or libraries
* Make it simple, faster and easy to implement
* Helps Operator/user to create new commands/fix the existing commands just by modifying the YAML file, so User no need to wait for an release time to get the fix for a bug or new commands.
* Similar to Tomcat for deploying more than one webapps, on OCLIP, multiple's products commands could be deployed and executed.

This platform defines Open Command Specification (OCS) for CLI, which is similar to the Open API specification (OAS) defined by swagger for REST API. It is like : **API for developers, CLI for admins/users/operators !**
For more details of OCS :ref:`open_cli_schema_version_1_0`
