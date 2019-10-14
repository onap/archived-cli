.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2018 Huawei Technologies Co., Ltd.

.. _cli_cookbook:

CLI cook-book
=============

To Run OCLIP on local docker machine
---------------------------------------

1. Login to docker repository::

    docker login -u docker -p docker nexus3.onap.org:10001

2. Pull cli docker image and run locally::

    docker run -d --name oclip -e OPEN_CLI_MODE=daemon -p 9090:8080 -p 8080:80 nexus3.onap.org:10001/onap/cli:2.0-STAGING-latest

Access the CLI console
~~~~~~~~~~~~~~~~~~~~~~

1. Web command console::

    http://localhost:9090

2. Linux console::

    docker exec -it oclip oclip

How to run OCLIP on ubuntu server
---------------------------------

1. Download install script::

    wget -O ~/oclip-install.sh https://gerrit.onap.org/r/gitweb?p=cli.git;a=blob_plain;f=deployment/zip/installer/install-latest.sh;h=71488dae78a3ecbb27711c95475b4568883f799f;hb=refs/heads/master

2. Set execution mode and run installer::

    cd ~

    chmod +x oclip-install.sh

    ./oclip-install.sh

3. verify the installation, by running::

    oclip --version

    NOTE: Make sure OPEN_CLI_HOME is set to /opt/oclip, type

    echo $OPEN_CLI_HOME

Setup profile
-------------

1. Run oclip console::

    oclip

2. Create a profile::

    profile <name>

3. Add basic parameters in to profile::

    set <service-name>:<parameter-name>

    Example: To set the host url for service AAI in ONAP, type 'set AAI:host-url=https://192.168.17.35:30233'

4. For ONAP beijing version, OCLIP provides default profile named onap-beijing. To use this profile, type::

    profile onap-beijing

    set aai:host-url=<AAI service URL>

    set msb:host-url=<MSB service URL>

    set sdc:host-url=<SDC service URL>

    set so:host-url=<SO service URL>

    NOTE: This step is not applicable for other products, so it could be skipped.

4. Exit console::

    exit

Use the existing profile
------------------------

1. In console mode, type::

    profile <profile-name>


2. In scripting mode, type::

    oclip -c <profile-name> <command name>

    Example: To list the VNF cloud registered in the ONAP, type 'oclip -c onap-beijing cloud-list'.


How to configure OCLIP to use for given product version
-------------------------------------------------------

In console mode
~~~~~~~~~~~~~~~

1. Identify the available and current product version, type::

    version

    It will report as below:

    CLI version       : 2.0.0 [2018-03-21 11:04 UTC]
    Available products: [open-cli, onap-beijing, onap-amsterdam, sample-helloworld]
    Enabled product   : open-cli

2. To enable a product say onap-beijing, type::

    use onap-beijing

3. List available commands, type::

    help

In scripting mode
~~~~~~~~~~~~~~~~~

1. Identify the available and current product version, type::

    oclip --version

    It will report as below:

    CLI version       : 2.0.0 [2018-03-21 11:04 UTC]
    Available products: [open-cli, onap-beijing, onap-amsterdam, sample-helloworld]
    Enabled product   : open-cli

2. To enable a product say onap-beijing, set environment variable OPEN_CLI_PRODUCT_IN_USE::

    export OPEN_CLI_PRODUCT_IN_USE=onap-beijing

3. List available commands, type::

    oclip --help
