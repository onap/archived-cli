#!/bin/bash

export ONAP_CLI_HOME=/opt/onap/cli
export CLI_PRODUCT_VERSION=onap-1.1

cd $ONAP_CLI_HOME

if [ ! -d ./data ]; then mkdir ./data; fi
if [ ! -d ./onap-cli-schema ]; then mkdir ./onap-cli-schema; fi

chmod +x ./bin/onap.sh

#Make onap available in path
ln ./bin/onap.sh /usr/bin/onap

#Print the version
onap -v