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
Type **onap <command>** from linux console.

To Run in Interactive mode
--------------------------
Type **onap** from linux/web console.

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

Run *onap [-v|--version]* to see the CLI and available product version details

Profiling arguments
-------------------
In interactive mode, user can use profiles for for “set arguments value once and use it ever”.

Please follow the steps given below for setting profiles:

1.    Open the ONAP CLI interactive console

2.    Create the profile, say ‘test:aai’ by running ‘profile test:aai’
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

4.    You can override these stored arguments by providing them as arguments directly while
running the CLI.

5.    Profile information is persisted, so when you disconnect and connect again the CLI console,
you can start use the same profile again.

NOTE: Use the directive 'set' for setting the values for parameters and 'unset' for reseting
 the values.

Help
----
*onap [-h|--help]*
*onap <command> [-h|--help]*

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
Following document provides the help message for commands supported in this release.

.. toctree::
   :maxdepth: 1

   cmd_help.rst

Command Samples
---------------
Following document provides the samples for commands supported in this release.

.. toctree::
   :maxdepth: 1

   cmd_sample.rst

End to end service creation tutorial
------------------------------------
Following document provides the tutorial for service creation using CLI.

.. toctree::
   :maxdepth: 1

   end_to_end_onap_cli_commands.rst