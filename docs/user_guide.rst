.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.

.. _cli_user_guide:

CLI user guide
==============

**One Command to command whole Onap !!**

Provides unified commands to operate any products from Linux console and web command console.
Configure the following environment variables, before using it:

1. **OPEN_CLI_HOST_URL** - Catalog or service URL
2. **OPEN_CLI_HOST_USERNAME** - Service user name
3. **OPEN_CLI_HOST_PASSWORD** - Service user password

To Run a command
-----------------
Type **oclip <command>** from linux console.

To Run in Interactive mode
--------------------------
Type **oclip** from linux/web console.

And use the directive set to set the values for following parameters:

1. **host-url** - Catalog or service URL
2. **host-username** - Service user name
3. **host-password** - Service user password

Set the product version
------------------------
CLI framework is enhanced to handle multiple product versions at same
time. so to choose the product version, set environment variable
**OPEN_CLI_PROUDCT_IN_USE**.

NOTE: In interactive mode, product version can be selected using
typing **use <product-version>**

Run *oclip [-v|--version]* to see the CLI and available product version details

Profiling arguments
-------------------
In interactive mode, user can use profiles for for “set arguments value once and use it ever”.

Please follow the steps given below for setting profiles:

1.    Open the CLI interactive console

2.    Create the profile, say ‘onap-deployment-1’ by running ‘profile onap-deployment-1’
There is no limit in number of profiles. OCLIP will create a profile if not exist already,
otherwise on typing 'profile' on the console, corresponding profiles will be effective.
So you can switch across profiles by using this directive 'profile'.

3.    For example, to add the credentials, Run following steps:

a.    set host-url=<aai-url>

b.    set host-username=AAI

c.    set host-pasword=AAI

d.    Type 'set' to print all these arguments stored so far.

NOTE: Profiles are not limited to set only credentials, you can set any frequently using
common arguments, which are listed in command help.

NOTE: For setting service specific parameters, use in the form of <service-name>:<param-name>=<param-value>.
For example, following setting will be used only for aai service:

a.    set aai:host-url=<aai-url>

b.    set aai:host-username=AAI

c.    set aai:host-pasword=AAI


4.    You can override these stored arguments by providing them as arguments directly while
running the CLI.

5.    Profile information is persisted, so when you disconnect and connect again the CLI console,
you can start use the same profile again.

NOTE: Use the directive 'set' for setting the values for parameters and 'unset' for resetting the values.

In Beijing release, new default profile named as 'onap-beijing' is delivered with all default ONAP service
credentials. so user could use this profile to avoid setting the credentials every time.

Batching support & Parameter file
---------------------------------
For a given scenario where, user wants to run a command with different set of inputs, this feature could be used.

For example, in ONAP, user wants to register multiple clouds at a given time, so user can create a parameter file
as given below and capture inputs for one or more clouds:

parameter file name: /usr/mrkanag/cloud-list.yaml

cloud-1:
  - name: cloud-region-1
  - description: Provides the test cloud environment
cloud-2:
  - name: cloud-region-2
  - description: Provides the production cloud environment

To register all these clouds in ONAP execute the cloud-register command with parameter file in inputs as below:

onap -p  /usr/mrkanag/cloud-list.yaml cloud-register

Now OCLIP will iterate the command cloud-register for every cloud mentioned in the parameter file /usr/mrkanag/cloud-list.yaml.
When user use this approach to execute the command with multiple entries in parameter file is called as *batching*.

Help
----
*oclip [-h|--help]*
*oclip <command> [-h|--help]*

Debug Mode
----------
To run in debug mode, set following environment variables:

1. OPEN_CLI_CLI_DEBUG - By default its false, otherwise Set to true

2. OPEN_CLI_CLI_DEBUG_PORT - By default it is 5005, otherwise set to new TCP port number

More details
-------------
https://wiki.onap.org

Command usage guide
-------------------
Following documents provide the usage for each commands supported in different releases.

NOTE: Command marked with EXPERIMENTAL is only for testing purpose. Its not recommended to use in production environment

.. toctree::
   :maxdepth: 1

   cmd_help.rst
   cmd_help_onap_dublin.rst

Command Samples
---------------
Following document provides the samples for every commands supported.

.. toctree::
   :maxdepth: 1

   cmd_sample.rst

End to end service creation tutorial
------------------------------------
Following document provides the tutorial for service creation using CLI.

.. toctree::
   :maxdepth: 1

   user_guide_end_to_end_onap_cli_commands.rst

PNF/VNF on-boarding tutorial
------------------------------------
Following document provides the tutorial for PNF/VNF on-boarding tutorial using CLI.

.. toctree::
   :maxdepth: 1

   user_guide_pnf_on_boarding.rst

CLI verification program
------------------------
OCLIP provides option to write test suite with set of test cases by authoring required
samples and moco templates. Currently HTTP profile is enabled with this program.


.. toctree::
   :maxdepth: 1

   user_guide_verification_program.rst
