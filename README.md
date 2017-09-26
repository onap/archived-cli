OPEN Command-line interface (CLI)
==================================

Provides unified commands to operate any cloud enabled software
products from Linux/Web console. Configure the following environment
variables, before using it:

1. **HOST_URL** - Catalog service URL or a service URL
2. **HOST_USERNAME** - Service user name
3. **HOST_PASSWORD** - Service password

To Run a command
-----------------
Type **onap <command>** from linux console.

To Run in Interactive mode
--------------------------
Type **onap** from linux console.

And use the directive set to set the values for following parameters:

1. **host-url** - Catalog service URL or a service URL 
2. **host-username** - Service user name
3. **host-password** - Service password

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
1. CLI_DEBUG - By default its false, otherwise Set to true
2. CLI_DEBUG_PORT - By default it is 5005, otherwise set to new TCP port number

More details
-------------
https://wiki.onap.org
