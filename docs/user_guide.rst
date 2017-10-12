.. _user_guide:
.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.

CLI user guide
==============

**One Command to command whole Onap !!**

Provides unified commands to operate ONAP from Linux console.
Configure the following environment variables, before using it:

1. **ONAP_HOST_URL** - Onap Micro service bus(MSB) URL or a service URL
2. **ONAP_USERNAME** - Onap user name
3. **ONAP_PASSWORD** - Onap user password

To Run a command
-----------------
Type **onap <command>** from linux console.

To Run in Interactive mode
--------------------------
Type **onap** from linux/web console.

And use the directive set to set the values for following parameters:

1. **host-url** - Onap Micro service bus(MSB) URL or a service URL
2. **onap-username** - Onap user name
3. **onap-password** - Onap user password

Set the product version
------------------------
CLI framework is enhanced to handle multiple product versions at same
time. so to choose the product version, set evironment variable
**CLI_PROUDCT_VERSION**.

NOTE: In interactive mode, product version can be selected using
typing **use <product-version>**

Run *onap [-v|--version]* to see the CLI and available product version details

Set the parameter values
------------------------
Use the directive 'set' for setting the values for parameters and 'unset' for un-seting the values.

Help
----
*onap [-h|--help]*
*onap <command> [-h|--help]*

Debug Mode
----------
To run in debug mode, set following environment variables:
1. ONAP_CLI_DEBUG - By default its false, otherwise Set to true
2. ONAP_CLI_DEBUG_PORT - By default it is 5005, otherwise set to new TCP port number

More details
-------------
https://wiki.onap.org

Command usage guide
-------------------
Following document provides the help message for list of command's supported in this release.

.. toctree::
   :maxdepth: 1

   cmd_help.rst
