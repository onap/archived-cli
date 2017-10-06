Open Command-line interface Platform (OCLIP)
============================================

Provides unified commands to operate any cloud enabled software
products from Linux/Web console. Configure the following environment
variables, before using it:

1. **OPEN_CLI_HOST_URL** - Catalog service URL or a service URL
2. **OPEN_CLI_HOST_USERNAME** - Service user name
3. **OPEN_CLI_HOST_PASSWORD** - Service password

To Run a command
-----------------
Type **oclip <command>** from linux console.

To Run in Interactive mode
--------------------------
Type **oclip** from linux console.

And use the directive set to set the values for following parameters:

1. **host-url** - Catalog service URL or a service URL
2. **host-username** - Service user name
3. **host-password** - Service password

Set the product version
------------------------
CLI framework is enhanced to handle multiple product versions at same
time. so to choose the product version, set evironment variable
**OPEN_CLI_PROUDCT_IN_USE**.

NOTE: In interactive mode, product version can be selected using
typing **use <product-version>**

Run *oclip [-v|--version]* to see the CLI and available product version details

Set the parameter values
------------------------
Use the directive 'set' for setting the values for parameters and 'unset' for un-seting the values.

Help
----
*oclip [-h|--help]*
*oclip <command> [-h|--help]*

Debug Mode
----------
To run in debug mode, set following environment variables:

1. OPEN_CLI_DEBUG - By default its false, otherwise Set to true
2. OPEN_CLI_DEBUG_PORT - By default it is 5005, otherwise set to new TCP port number

<h2><a id="More_details_42"></a>More details</h2>
<p><a href="https://wiki.onap.org">https://wiki.onap.org</a></p>

<h2><a id="Download_42"></a>To download</h2>
<p style="color:red">Please download Open CLI Platform <a href="./open-cli.zip">here</a></p>

