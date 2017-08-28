Onap Command-line interface (CLI).

One Command to command whole Onap !! It provides the unified commands
to access and operate Onap functionalities. To use this CLI, please
configure the following environment variables:

1. ONAP_HOST_URL - Onap Micro service bus(MSB) URL or a service URL
2. ONAP_USERNAME - Onap user name
3. ONAP_PASSWORD - Onap user password

To know the CLI version, type onap [-v|--version]
To know the CLI usage, type onap [-h|--help]
To know the usage of sub commands, type onap <command> [-h|--help]

To run in debug mode, set following environment variables:
1. ONAP_CLI_DEBUG - By default its false, otherwise Set to true
2. ONAP_CLI_DEBUG_PORT - By default it is 5005, otherwise set to new TCP port number

To know more, please refer the Onap wiki https://wiki.onap.org