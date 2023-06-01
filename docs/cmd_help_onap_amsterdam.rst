.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2018 Huawei Technologies Co., Ltd.

:orphan:

.. toctree::
   :maxdepth: 2


.. _cli_cmd_help_onap_amsterdam:

onap-amsterdam
==========================

NOTE: EOL 01-Aug-2018


ONAP Amsterdam is the first release of ONAP product after merging open-o and open-ecomp.
It provides platform to create and maintain the network services for VoLTE and vCPE types.
CLI provides following commands for operating ONAP with this support.

[1] basic-login
-----------------------------------------------


usage: onap basic-login

ONAP basic login auth command

Product: onap-amsterdam
Service: basic-auth
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-u | --host-username] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will overridee the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 Authorization    Authorization and is of type STRING.
 X-TransactionId  X-TransactionId and is of type STRING.
 X-FromAppId      X-FromAppId and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[2] basic-logout
-----------------------------------------------


usage: onap basic-logout

ONAP basic logout auth command

Product: onap-amsterdam
Service: basic-auth
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context]

where::

 -m | --host-url    host url in http(s). It is of type URL. By
                    default, it is read from environment variable
                    OPEN_CLI_HOST_URL.
 -C | --no-catalog  whether to use given host-url directly without
                    looking into catalog, it will override the
                    service->mode. It is of type BOOL. It is
                    optional.
 -h | --help        print help message. It is of type BOOL.
 -f | --format      Output formats, supported formats such as table,
                    csv, json, yaml. It is of type STRING.
 -t | --no-title    whether to print title or not. It is of type BOOL.
 -V | --verify      verify the command using available command sample
                    file and mocking file. By default it goes with
                    mock style. To enable the verification in real
                    time, set DISABLE_MOCKING=true in the context
                    param. It is of type BOOL. It is optional.
 -v | --version     print service version. It is of type BOOL.
 -d | --debug       Enable debug output. It is of type BOOL.
 -s | --long        whether to print all attributes or only short
                    attributes. It is of type BOOL.
 -D | --context     command context. It is of type MAP. It is
                    optional.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[3] catalog
-----------------------------------------------


usage: onap catalog

ONAP catalog command to find the base path for service.

Product: onap-amsterdam
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-l | --catalog-service-name]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-s | --long] [-D | --context] [-i | --catalog-service-version]

where::

 -m | --host-url                  host url in http(s). It is of type URL. By
                                  default, it is read from environment variable
                                  OPEN_CLI_HOST_URL.
 -C | --no-catalog                whether to use given host-url directly without
                                  looking into catalog, it will override the
                                  service->mode. It is of type BOOL. It is
                                  optional.
 -l | --catalog-service-name      service name registered in catalog service. It is
                                  of type STRING. By default, it is .
 -h | --help                      print help message. It is of type BOOL.
 -f | --format                    Output formats, supported formats such as table,
                                  csv, json, yaml. It is of type STRING.
 -t | --no-title                  whether to print title or not. It is of type BOOL.
 -V | --verify                    verify the command using available command sample
                                  file and mocking file. By default it goes with
                                  mock style. To enable the verification in real
                                  time, set DISABLE_MOCKING=true in the context
                                  param. It is of type BOOL. It is optional.
 -v | --version                   print service version. It is of type BOOL.
 -d | --debug                     Enable debug output. It is of type BOOL.
 -s | --long                      whether to print all attributes or only short
                                  attributes. It is of type BOOL.
 -D | --context                   command context. It is of type MAP. It is
                                  optional.
 -i | --catalog-service-version   service version registered in catalog service. It
                                  is of type STRING. By default, it is .


Results::

 catalog-service-host-url   Service connection url and is of type STRING.
 catalog-service-base-path  service base path, to append with host-url for
                            connecting the service. and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[4] cloud-create
-----------------------------------------------


usage: onap cloud-create

Create a cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --cloud-name]
 [-s | --long] [-D | --context] [-y | --region-name]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --cloud-name      Onap cloud name. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -y | --region-name     Onap region name. It is of type STRING. By
                        default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[5] cloud-delete
-----------------------------------------------


usage: onap cloud-delete

Delete a cloud region from Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --cloud-name]
 [-z | --resource-version] [-s | --long] [-D | --context]
 [-y | --region-name] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -x | --cloud-name        Onap cloud name. It is of type STRING. By
                          default, it is .
 -z | --resource-version  Onap cloud region version. It is of type UUID. It
                          is optional.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -y | --region-name       Onap cloud region name. It is of type STRING. By
                          default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[6] cloud-list
-----------------------------------------------


usage: onap cloud-list

List the configured clouds and Onap service subscriptions

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 cloud              Onap cloud and is of type STRING.
 region             Onap cloud region and is of type STRING.
 tenant             Onap cloud tenat and is of type STRING.
 tenant-id          Onap cloud tenat id and is of type STRING.
 customer           Onap cloud customer and is of type STRING.
 service            Onap cloud service and is of type STRING.
 resource-version   Onap cloud resource version and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[7] customer-create
-----------------------------------------------


usage: onap customer-create

Create a customer in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-y | --subscriber-name] [-C | --no-catalog]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-x | --customer-name] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -y | --subscriber-name   Onap subscriber name. It is of type STRING. By
                          default, it is .
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -x | --customer-name     Onap customer name. It is of type STRING. By
                          default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[8] customer-delete
-----------------------------------------------


usage: onap customer-delete

Delete a customer from Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --customer-name]
 [-y | --resource-version] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -x | --customer-name     Onap customer name. It is of type STRING. By
                          default, it is .
 -y | --resource-version  Onap customer resource version. It is of type
                          UUID. It is optional.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[9] customer-list
-----------------------------------------------


usage: onap customer-list

Lists the registered customers in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 name               Onap customer name and is of type STRING.
 resource-version   Onap customer resource version and is of type
                    STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[10] customer-show
-----------------------------------------------


usage: onap customer-show

Retrieves the given registered customer in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --customer-name]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --customer-name   Onap customer name. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 name               Onap customer name and is of type STRING.
 subscriber-name    Onap subscriber name and is of type STRING.
 resource-version   Onap subscriber resource version and is of type
                    STRING.
 subscriber-type    Onap subscriber type and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[11] ems-list
-----------------------------------------------


usage: onap ems-list

List the configured ems

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ems-id             Onap ems id and is of type STRING.
 resource-version   Onap ems resource version and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[12] ems-register
-----------------------------------------------


usage: onap ems-register

Register a EMS in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-e | --vendor] [-v | --version] [-d | --debug]
 [-c | --type] [-s | --long] [-D | --context]
 [-q | --ems-version] [-b | --name] [-i | --username]
 [-j | --password] [-u | --host-username] [-g | --url]
 [-x | --remote-path] [-a | --no-auth] [-z | --ems-id]
 [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -e | --vendor          Onap EMS vendor. It is of type STRING. By
                        default, it is .
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -c | --type            Onap EMS type. It is of type STRING. By default,
                        it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -q | --ems-version     Onap EMS version. It is of type STRING. By
                        default, it is .
 -b | --name            Onap EMS name. It is of type STRING. By default,
                        it is .
 -i | --username        Onap EMS username. It is of type STRING. By
                        default, it is .
 -j | --password        Onap EMS password. It is of type STRING. By
                        default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -g | --url             Onap EMS URL. It is of type STRING. By default,
                        it is .
 -x | --remote-path     Onap EMS remote-path. It is of type STRING. By
                        default, it is .
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -z | --ems-id          Onap EMS unique id. It is of type UUID.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[13] ems-show
-----------------------------------------------


usage: onap ems-show

Show the details of configured ems

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --ems-id]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --ems-id          Onap EMS unique id. It is of type UUID.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 name       Onap ems name and is of type STRING.
 type       Onap ems type and is of type STRING.
 vendor     Onap ems vendor and is of type STRING.
 version    Onap ems version and is of type STRING.
 url        Onap ems url and is of type STRING.
 username   Onap ems username and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[14] ems-unregister
-----------------------------------------------


usage: onap ems-unregister

Un-register a EMS in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-y | --resource-version]
 [-x | --ems-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -y | --resource-version  Onap vim resource version. It is of type STRING.
                          It is optional. By default, it is .
 -x | --ems-id            Onap EMS unique id. It is of type UUID.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[15] ep-create
-----------------------------------------------


usage: onap ep-create

Create Entitlement Pool

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-b | --threshold-unit]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-x | --ep-name] [-k | --reference-number] [-g | --operational-scope]
 [-c | --entitlement-metric] [-z | --ep-description] [-s | --long]
 [-D | --context] [-e | --aggregation-function] [-y | --license-model-id]
 [-u | --host-username] [-a | --no-auth] [-q | --threshold-value]
 [-p | --host-password]

where::

 -m | --host-url              host url in http(s). It is of type URL. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_URL.
 -C | --no-catalog            whether to use given host-url directly without
                              looking into catalog, it will override the
                              service->mode. It is of type BOOL. It is
                              optional.
 -b | --threshold-unit        threshold. It is of type STRING. By default, it
                              is .
 -h | --help                  print help message. It is of type BOOL.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -V | --verify                verify the command using available command sample
                              file and mocking file. By default it goes with
                              mock style. To enable the verification in real
                              time, set DISABLE_MOCKING=true in the context
                              param. It is of type BOOL. It is optional.
 -v | --version               print service version. It is of type BOOL.
 -d | --debug                 Enable debug output. It is of type BOOL.
 -x | --ep-name               Entitlement Pool name. It is of type STRING. By
                              default, it is .
 -k | --reference-number      Reference Number. It is of type STRING. By
                              default, it is .
 -g | --operational-scope     Operational Scope. It is of type STRING. By
                              default, it is .
 -c | --entitlement-metric    Entitlement Metric. It is of type STRING. By
                              default, it is .
 -z | --ep-description        Description for Entitlement Pool. It is of type
                              STRING. By default, it is .
 -s | --long                  whether to print all attributes or only short
                              attributes. It is of type BOOL.
 -D | --context               command context. It is of type MAP. It is
                              optional.
 -e | --aggregation-function  Aggregation Function. It is of type STRING. By
                              default, it is .
 -y | --license-model-id      License Model ID. It is of type STRING. By
                              default, it is .
 -u | --host-username         Host user name. It is of type STRING. By default,
                              it is read from environment variable
                              OPEN_CLI_HOST_USERNAME.
 -a | --no-auth               whether to authenticate user or not. It is of
                              type BOOL.
 -q | --threshold-value       threshold. It is of type STRING. By default, it
                              is .
 -p | --host-password         Host user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID   Entitlement Pool ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[16] ep-show
-----------------------------------------------


usage: onap ep-show

Details of the Entitlement Pool

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-x | --license-model-id] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password] [-y | --pool-id]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -x | --license-model-id  Onap License Model ID. It is of type STRING. By
                          default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -y | --pool-id           Onap Entitlement Pool ID. It is of type STRING.
                          By default, it is .


Results::

 name         Entitlement Pool Name and is of type STRING.
 ID           Entitlement Pool ID and is of type STRING.
 description  Description for the Entitlement Pool and is of
              type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[17] license-group-create
-----------------------------------------------


usage: onap license-group-create

Create License Group

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-z | --license-group-description]
 [-x | --group-name] [-s | --long] [-D | --context]
 [-y | --license-model-id] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -C | --no-catalog                  whether to use given host-url directly without
                                    looking into catalog, it will override the
                                    service->mode. It is of type BOOL. It is
                                    optional.
 -h | --help                        print help message. It is of type BOOL.
 -f | --format                      Output formats, supported formats such as table,
                                    csv, json, yaml. It is of type STRING.
 -t | --no-title                    whether to print title or not. It is of type BOOL.
 -V | --verify                      verify the command using available command sample
                                    file and mocking file. By default it goes with
                                    mock style. To enable the verification in real
                                    time, set DISABLE_MOCKING=true in the context
                                    param. It is of type BOOL. It is optional.
 -v | --version                     print service version. It is of type BOOL.
 -d | --debug                       Enable debug output. It is of type BOOL.
 -z | --license-group-description   Description for License Group. It is of type
                                    STRING. It is optional. By default, it is .
 -x | --group-name                  group name. It is of type STRING. By default, it
                                    is .
 -s | --long                        whether to print all attributes or only short
                                    attributes. It is of type BOOL.
 -D | --context                     command context. It is of type MAP. It is
                                    optional.
 -y | --license-model-id            License Model ID. It is of type STRING. By
                                    default, it is .
 -u | --host-username               Host user name. It is of type STRING. By default,
                                    it is read from environment variable
                                    OPEN_CLI_HOST_USERNAME.
 -a | --no-auth                     whether to authenticate user or not. It is of
                                    type BOOL.
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID   License Group ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[18] license-group-show
-----------------------------------------------


usage: onap license-group-show

Details of the License Group

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-x | --license-model-id] [-y | --license-group-id]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -x | --license-model-id  Onap License Model ID. It is of type STRING. By
                          default, it is .
 -y | --license-group-id  Onap License Group ID. It is of type STRING. By
                          default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 name         License Group Name and is of type STRING.
 ID           License Group ID and is of type STRING.
 description  Description for the License Group and is of type
              STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[19] license-model-create
-----------------------------------------------


usage: onap license-model-create

Create License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-y | --license-model-description]
 [-s | --long] [-D | --context] [-x | --vendor-name]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -C | --no-catalog                  whether to use given host-url directly without
                                    looking into catalog, it will override the
                                    service->mode. It is of type BOOL. It is
                                    optional.
 -h | --help                        print help message. It is of type BOOL.
 -f | --format                      Output formats, supported formats such as table,
                                    csv, json, yaml. It is of type STRING.
 -t | --no-title                    whether to print title or not. It is of type BOOL.
 -V | --verify                      verify the command using available command sample
                                    file and mocking file. By default it goes with
                                    mock style. To enable the verification in real
                                    time, set DISABLE_MOCKING=true in the context
                                    param. It is of type BOOL. It is optional.
 -v | --version                     print service version. It is of type BOOL.
 -d | --debug                       Enable debug output. It is of type BOOL.
 -y | --license-model-description   Description for License Model. It is of type
                                    STRING. It is optional. By default, it is .
 -s | --long                        whether to print all attributes or only short
                                    attributes. It is of type BOOL.
 -D | --context                     command context. It is of type MAP. It is
                                    optional.
 -x | --vendor-name                 vendor name. It is of type STRING. By default, it
                                    is .
 -u | --host-username               Host user name. It is of type STRING. By default,
                                    it is read from environment variable
                                    OPEN_CLI_HOST_USERNAME.
 -a | --no-auth                     whether to authenticate user or not. It is of
                                    type BOOL.
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID   License Model ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[20] license-model-show
-----------------------------------------------


usage: onap license-model-show

Details of the License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-x | --license-model-id] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -x | --license-model-id  Onap License Model ID. It is of type STRING. By
                          default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 name         License Model  Name and is of type STRING.
 ID           License Model ID and is of type STRING.
 description  Description for the License Model and is of type
              STRING.
 status       Status of the License Model and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[21] microservice-create
-----------------------------------------------


usage: onap microservice-create

Register microservice into Onap

Product: onap-amsterdam
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 <create-or-update> [-v | --version] [-d | --debug]
 [-c | --path] [-y | --service-version] <node-ip>
 [-s | --long] [-D | --context] <node-port>
 [-x | --service-name] [-r | --service-url] [-b | --enable-ssl]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 create-or-update         Onap service create or update. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -c | --path              Onap service path. It is of type STRING. It is
                          optional. By default, it is .
 -y | --service-version   Onap service version. It is of type STRING. By
                          default, it is .
 node-ip                  Onap service running node IP. It is of type
                          STRING. By default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 node-port                Onap service running node port. It is of type
                          STRING. By default, it is .
 -x | --service-name      Onap service name. It is of type STRING. By
                          default, it is .
 -r | --service-url       Onap service base url. It is of type URL. By
                          default, it is .
 -b | --enable-ssl        Onap service is enabled with https or not. It is
                          of type STRING. It is optional. By default, it is
                          .


Results::

 name         Onap service name and is of type STRING.
 version      Onap service version and is of type STRING.
 url          Onap service base url and is of type URL.
 status       Onap service status and is of type DIGIT.
 nodes        Onap service running nodes and is of type STRING.
 enable-ssl   Onap service is enabled with https or not and is
              of type STRING.
 path         Onap service path and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[22] microservice-delete
-----------------------------------------------


usage: onap microservice-delete

Deletes the micro service from Onap

Product: onap-amsterdam
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-y | --service-version]
 [-s | --long] [-D | --context] [-x | --service-name]
 [-i | --node-ip] [-r | --node-port]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -y | --service-version   Onap service version. It is of type STRING. By
                          default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -x | --service-name      Onap service name. It is of type STRING. By
                          default, it is .
 -i | --node-ip           Onap service running node IP. It is of type
                          STRING. By default, it is .
 -r | --node-port         Onap service running node port. It is of type
                          STRING. By default, it is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[23] microservice-list
-----------------------------------------------


usage: onap microservice-list

Lists the registered micro services in Onap

Product: onap-amsterdam
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context]

where::

 -m | --host-url    host url in http(s). It is of type URL. By
                    default, it is read from environment variable
                    OPEN_CLI_HOST_URL.
 -C | --no-catalog  whether to use given host-url directly without
                    looking into catalog, it will override the
                    service->mode. It is of type BOOL. It is
                    optional.
 -h | --help        print help message. It is of type BOOL.
 -f | --format      Output formats, supported formats such as table,
                    csv, json, yaml. It is of type STRING.
 -t | --no-title    whether to print title or not. It is of type BOOL.
 -V | --verify      verify the command using available command sample
                    file and mocking file. By default it goes with
                    mock style. To enable the verification in real
                    time, set DISABLE_MOCKING=true in the context
                    param. It is of type BOOL. It is optional.
 -v | --version     print service version. It is of type BOOL.
 -d | --debug       Enable debug output. It is of type BOOL.
 -s | --long        whether to print all attributes or only short
                    attributes. It is of type BOOL.
 -D | --context     command context. It is of type MAP. It is
                    optional.


Results::

 name         Onap service name and is of type STRING.
 version      Onap service version and is of type STRING.
 url          Onap service base url and is of type URL.
 status       Onap service status and is of type DIGIT.
 nodes        Onap service running nodes and is of type JSON.
 enable-ssl   Onap service is enabled with https or not and is
              of type STRING.
 path         Onap service path and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[24] microservice-show
-----------------------------------------------


usage: onap microservice-show

Details the registered microservice in Onap

Product: onap-amsterdam
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-y | --service-version]
 [-s | --long] [-D | --context] [-x | --service-name]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -y | --service-version   Onap service version. It is of type STRING. By
                          default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -x | --service-name      Onap service name. It is of type STRING. By
                          default, it is .


Results::

 name         Onap service name and is of type STRING.
 version      Onap service version and is of type STRING.
 url          Onap service base url and is of type URL.
 status       Onap service status and is of type DIGIT.
 nodes        Onap service running nodes and is of type JSON.
 enable-ssl   Onap service is enabled with https or not and is
              of type STRING.
 path         Onap service path and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[25] sdc-basic-login
-----------------------------------------------


usage: onap sdc-basic-login

ONAP basic login auth command

Product: onap-amsterdam
Service: sdc-basic-auth
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-u | --host-username] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 Authorization    Authorization and is of type STRING.
 X-TransactionId  X-TransactionId and is of type STRING.
 X-FromAppId      X-FromAppId and is of type STRING.
 USER_ID          USER_ID for sdc and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[26] sdnc-list
-----------------------------------------------


usage: onap sdnc-list

List the configured sdnc

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 sdnc-id            Onap sdnc id and is of type STRING.
 resource-version   Onap sdnc resource version and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[27] sdnc-register
-----------------------------------------------


usage: onap sdnc-register

Register a SDNC in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-g | --ip-address] [-h | --help] [-f | --format]
 [-t | --no-title] [-V | --verify] [-e | --vendor]
 [-v | --version] [-y | --sdnc-id] [-s | --long]
 [-b | --name] [-j | --password] [-a | --no-auth]
 [-p | --host-password] [-m | --host-url] [-C | --no-catalog]
 [-d | --debug] [-k | --port] [-c | --type]
 [-q | --sdnc-version] [-D | --context] [-r | --protocal]
 [-z | --product-name] [-i | --username] [-u | --host-username]
 [-x | --location]

where::

 -g | --ip-address      Onap SDNC ip address. It is of type STRING. By
                        default, it is .
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -e | --vendor          Onap SDNC vendor. It is of type STRING. By
                        default, it is .
 -v | --version         print service version. It is of type BOOL.
 -y | --sdnc-id         Onap SDNC unique id. It is of type UUID.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -b | --name            Onap SDNC name. It is of type STRING. By default,
                        it is .
 -j | --password        Onap SDNC password. It is of type STRING. By
                        default, it is .
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -d | --debug           Enable debug output. It is of type BOOL.
 -k | --port            Onap SDNC port. It is of type STRING. By default,
                        it is .
 -c | --type            Onap SDNC type. It is of type STRING. By default,
                        it is .
 -q | --sdnc-version    Onap SDNC version. It is of type STRING. By
                        default, it is .
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -r | --protocal        Onap SDNC protocal. It is of type STRING. By
                        default, it is .
 -z | --product-name    Onap SDNC product-name. It is of type STRING. By
                        default, it is .
 -i | --username        Onap SDNC username. It is of type STRING. By
                        default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -x | --location        Onap VIM unique id. It is of type UUID.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[28] sdnc-unregister
-----------------------------------------------


usage: onap sdnc-unregister

Un-register a VNFM in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --sdnc-id]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --sdnc-id         Onap VNFM unique id. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[29] service-create
-----------------------------------------------


usage: onap service-create

Create a service instance using MSO

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-e | --model-invariant-id]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-l | --cloud-region] [-w | --service-type] [-i | --model-name]
 [-c | --customer] [-s | --long] [-D | --context]
 [-b | --tenant-id] [-k | --instance-name] [-j | --model-version]
 [-u | --host-username] [-r | --supress-rollback] [-a | --no-auth]
 [-g | --model-uuid] [-p | --host-password]

where::

 -m | --host-url            host url in http(s). It is of type URL. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_URL.
 -C | --no-catalog          whether to use given host-url directly without
                            looking into catalog, it will override the
                            service->mode. It is of type BOOL. It is
                            optional.
 -e | --model-invariant-id  model invariant id generated by ASDC. It is of
                            type STRING. By default, it is .
 -h | --help                print help message. It is of type BOOL.
 -f | --format              Output formats, supported formats such as table,
                            csv, json, yaml. It is of type STRING.
 -t | --no-title            whether to print title or not. It is of type BOOL.
 -V | --verify              verify the command using available command sample
                            file and mocking file. By default it goes with
                            mock style. To enable the verification in real
                            time, set DISABLE_MOCKING=true in the context
                            param. It is of type BOOL. It is optional.
 -v | --version             print service version. It is of type BOOL.
 -d | --debug               Enable debug output. It is of type BOOL.
 -l | --cloud-region        cloud region identifier. It is of type STRING. By
                            default, it is .
 -w | --service-type        subscription service type. It is of type STRING.
                            By default, it is .
 -i | --model-name          model name as provided in ASDC design time. It is
                            of type STRING. By default, it is .
 -c | --customer            unique id for customer. It is of type STRING. By
                            default, it is .
 -s | --long                whether to print all attributes or only short
                            attributes. It is of type BOOL.
 -D | --context             command context. It is of type MAP. It is
                            optional.
 -b | --tenant-id           tenant id. It is of type STRING. By default, it
                            is .
 -k | --instance-name       service instance name. It is of type STRING. By
                            default, it is .
 -j | --model-version       model-version. It is of type STRING. By default,
                            it is .
 -u | --host-username       Host user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_CLI_HOST_USERNAME.
 -r | --supress-rollback    rollback changes if instantiation fails. It is of
                            type BOOL. It is optional.
 -a | --no-auth             whether to authenticate user or not. It is of
                            type BOOL.
 -g | --model-uuid          model uuid generated by ASDC. It is of type
                            STRING. By default, it is .
 -p | --host-password       Host user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 service-id   instance id for the created service. and is of
              type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[30] service-delete
-----------------------------------------------


usage: onap service-delete

Delete service instance (experimental)

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-e | --model-name] [-C | --no-catalog]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-b | --model-invariant-id] [-j | --cloud-region] [-x | --service-instace-id]
 [-i | --model-uuid] [-s | --long] [-D | --context]
 [-c | --customer-name] [-g | --model-version] [-k | --tenant-id]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url            host url in http(s). It is of type URL. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_URL.
 -e | --model-name          model name available in SDC catalog. It is of
                            type STRING. By default, it is .
 -C | --no-catalog          whether to use given host-url directly without
                            looking into catalog, it will override the
                            service->mode. It is of type BOOL. It is
                            optional.
 -h | --help                print help message. It is of type BOOL.
 -f | --format              Output formats, supported formats such as table,
                            csv, json, yaml. It is of type STRING.
 -t | --no-title            whether to print title or not. It is of type BOOL.
 -V | --verify              verify the command using available command sample
                            file and mocking file. By default it goes with
                            mock style. To enable the verification in real
                            time, set DISABLE_MOCKING=true in the context
                            param. It is of type BOOL. It is optional.
 -v | --version             print service version. It is of type BOOL.
 -d | --debug               Enable debug output. It is of type BOOL.
 -b | --model-invariant-id  model invariant id for service in SDC catalog. It
                            is of type STRING. By default, it is .
 -j | --cloud-region        cloud region id. It is of type STRING. By
                            default, it is .
 -x | --service-instace-id  unique id for service instance. It is of type
                            STRING. By default, it is .
 -i | --model-uuid          model uuid for service in SDC catalog. It is of
                            type STRING. By default, it is .
 -s | --long                whether to print all attributes or only short
                            attributes. It is of type BOOL.
 -D | --context             command context. It is of type MAP. It is
                            optional.
 -c | --customer-name       unique id for customer. It is of type STRING. By
                            default, it is .
 -g | --model-version       model version of service (eg. 1.0). It is of type
                            STRING. By default, it is .
 -k | --tenant-id           tenant id. It is of type STRING. By default, it
                            is .
 -u | --host-username       Host user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_CLI_HOST_USERNAME.
 -a | --no-auth             whether to authenticate user or not. It is of
                            type BOOL.
 -p | --host-password       Host user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[31] service-list
-----------------------------------------------


usage: onap service-list

List created service instance

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --customer-name]
 [-y | --service-type] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --customer-name   customer name. It is of type STRING. By default,
                        it is .
 -y | --service-type    service subscription type. It is of type STRING.
                        By default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 service-id           instance id of service and is of type STRING.
 service-name         Service name and is of type STRING.
 model-invariant-id   Model invariant id of service model and is of
                      type STRING.
 model-uuid           Model uuid for service model and is of type
                      STRING.
 description          service description and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[32] service-model-add-vf
-----------------------------------------------


usage: onap service-model-add-vf

Helps to add VF into service models in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --service-model-id]
 [-b | --vf-version] [-y | --vf-id] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-z | --vf-name] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -x | --service-model-id  Onap Service Name. It is of type STRING. By
                          default, it is .
 -b | --vf-version        VF version. It is of type STRING. It is optional.
                          By default, it is .
 -y | --vf-id             VF ID. It is of type STRING. It is optional. By
                          default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -z | --vf-name           VF ID. It is of type STRING. It is optional. By
                          default, it is .
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID   Service ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[33] service-model-approve
-----------------------------------------------


usage: onap service-model-approve

Approves the Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-b | --service-model-id] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional. By default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[34] service-model-certify-complete
-----------------------------------------------


usage: onap service-model-certify-complete

Completes the certification of Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-b | --service-model-id] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional. By default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[35] service-model-certify-request
-----------------------------------------------


usage: onap service-model-certify-request

Request the certification of Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-b | --service-model-id] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional. By default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[36] service-model-certify-start
-----------------------------------------------


usage: onap service-model-certify-start

Starts the certification of Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-b | --service-model-id] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional. By default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[37] service-model-checkin
-----------------------------------------------


usage: onap service-model-checkin

Checkin Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-b | --service-model-id] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional. By default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[38] service-model-checkout
-----------------------------------------------


usage: onap service-model-checkout

Checkout Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-b | --service-model-id] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional. By default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[39] service-model-create
-----------------------------------------------


usage: onap service-model-create

Create Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-z | --project-code]
 [-y | --description] [-e | --icon-id] [-x | --name]
 [-c | --category-display-name] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-b | --category]
 [-p | --host-password]

where::

 -m | --host-url                host url in http(s). It is of type URL. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_URL.
 -C | --no-catalog              whether to use given host-url directly without
                                looking into catalog, it will override the
                                service->mode. It is of type BOOL. It is
                                optional.
 -h | --help                    print help message. It is of type BOOL.
 -f | --format                  Output formats, supported formats such as table,
                                csv, json, yaml. It is of type STRING.
 -t | --no-title                whether to print title or not. It is of type BOOL.
 -V | --verify                  verify the command using available command sample
                                file and mocking file. By default it goes with
                                mock style. To enable the verification in real
                                time, set DISABLE_MOCKING=true in the context
                                param. It is of type BOOL. It is optional.
 -v | --version                 print service version. It is of type BOOL.
 -d | --debug                   Enable debug output. It is of type BOOL.
 -z | --project-code            Project code. It is of type STRING. It is
                                optional. By default, it is .
 -y | --description             Description for Service. It is of type STRING. It
                                is optional. By default, it is .
 -e | --icon-id                 Service Icon id. It is of type STRING. It is
                                optional.
 -x | --name                    Onap Service Name. It is of type STRING. By
                                default, it is .
 -c | --category-display-name   Service category display name. It is of type
                                STRING. It is optional.
 -s | --long                    whether to print all attributes or only short
                                attributes. It is of type BOOL.
 -D | --context                 command context. It is of type MAP. It is
                                optional.
 -u | --host-username           Host user name. It is of type STRING. By default,
                                it is read from environment variable
                                OPEN_CLI_HOST_USERNAME.
 -a | --no-auth                 whether to authenticate user or not. It is of
                                type BOOL.
 -b | --category                Service category. It is of type STRING. It is
                                optional.
 -p | --host-password           Host user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID   Service ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[40] service-model-distribute
-----------------------------------------------


usage: onap service-model-distribute

Distributes the Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-b | --service-model-id] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional. By default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[41] service-model-list
-----------------------------------------------


usage: onap service-model-list

List the service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 uuid                 UUID and is of type STRING.
 invariant-uuid       Invariant UUID and is of type STRING.
 name                 Name and is of type STRING.
 version              version and is of type STRING.
 status               status and is of type STRING.
 distribution-status  status and is of type STRING.
 description          description and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[42] service-model-revert
-----------------------------------------------


usage: onap service-model-revert

Checkout Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-b | --service-model-id] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional. By default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[43] service-type-create
-----------------------------------------------


usage: onap service-type-create

Add a service type in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-x | --service-type] [-v | --version] [-d | --debug]
 [-y | --service-type-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -x | --service-type      Onap service type. It is of type STRING. By
                          default, it is .
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -y | --service-type-id   Onap service type uuid. It is of type UUID. It is
                          optional.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[44] service-type-delete
-----------------------------------------------


usage: onap service-type-delete

Delete a service type from Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-y | --resource-version]
 [-x | --service-type-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -y | --resource-version  Onap service resource version. It is of type
                          UUID. It is optional.
 -x | --service-type-id   Onap service type uuid. It is of type UUID. It is
                          optional.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[45] service-type-list
-----------------------------------------------


usage: onap service-type-list

List the service types configured in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 service-type-id    Onap cloud service and is of type STRING.
 service-type       Onap cloud service and is of type STRING.
 resource-version   Onap cloud service resource version and is of
                    type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[46] service2vf-model-list
-----------------------------------------------


usage: onap service2vf-model-list

List the VF in a given service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-z | --service-model-id]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -z | --service-model-id  Service model uuid. It is of type STRING. By
                          default, it is .
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 vf-uuid                UUID and is of type STRING.
 vf-name                name and is of type STRING.
 vf-customization-uuid  customization UUID and is of type STRING.
 vf-version             version and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[47] subscription-create
-----------------------------------------------


usage: onap subscription-create

Create a subscription of a customer for given service in specific cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --customer-name]
 [-z | --cloud-name] [-c | --cloud-tenant-id] [-s | --long]
 [-D | --context] [-e | --service-type] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password] [-r | --cloud-region]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -x | --customer-name     Onap customer name. It is of type STRING. By
                          default, it is .
 -z | --cloud-name        Onap cloud name. It is of type STRING. By
                          default, it is .
 -c | --cloud-tenant-id   Onap cloud tenant id. It is of type STRING. By
                          default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -e | --service-type      Onap service type. It is of type STRING. By
                          default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -r | --cloud-region      Onap cloud region. It is of type STRING. By
                          default, it is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[48] subscription-delete
-----------------------------------------------


usage: onap subscription-delete

Delete the subscription for a given customer in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --customer-name]
 [-y | --service-type] [-s | --long] [-D | --context]
 [-g | --resource-version] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -x | --customer-name     Onap customer name. It is of type STRING. By
                          default, it is .
 -y | --service-type      Onap subscribtion id. It is of type STRING. By
                          default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -g | --resource-version  Onap subscription resource version. It is of type
                          STRING. It is optional. By default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[49] subscription-list
-----------------------------------------------


usage: onap subscription-list

Lists the subscription for a given customer in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --customer-name]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --customer-name   Onap customer name. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 service-type       Onap service type and is of type STRING.
 resource-version   Onap subscription resource version and is of type
                    STRING.
 tenant             Onap tenant name and is of type STRING.
 region             Onap region name and is of type STRING.
 cloud              Onap cloud name and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[50] tenant-create
-----------------------------------------------


usage: onap tenant-create

Create a tenant under given cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --cloud]
 [-z | --tenant-id] [-y | --region] [-s | --long]
 [-D | --context] [-r | --tenant-name] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --cloud           Onap cloud. It is of type STRING. By default, it
                        is .
 -z | --tenant-id       Onap cloud tenant id. It is of type STRING. By
                        default, it is .
 -y | --region          Onap cloud region. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -r | --tenant-name     Onap cloud tenant name. It is of type STRING. By
                        default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[51] tenant-delete
-----------------------------------------------


usage: onap tenant-delete

Delete tenant under given cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --cloud]
 [-z | --tenant-id] [-y | --region] [-r | --resource-version]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -x | --cloud             Onap cloud. It is of type STRING. By default, it
                          is .
 -z | --tenant-id         Onap cloud tenant id. It is of type STRING. By
                          default, it is .
 -y | --region            Onap cloud region. It is of type STRING. By
                          default, it is .
 -r | --resource-version  Onap cloud tenant version. It is of type UUID. It
                          is optional.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[52] tenant-list
-----------------------------------------------


usage: onap tenant-list

Lists the tenants under given cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --cloud]
 [-y | --region] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --cloud           Onap cloud. It is of type STRING. By default, it
                        is .
 -y | --region          Onap cloud region. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 tenant-id          Onap tenant-id and is of type STRING.
 tenant-name        Onap tenant name and is of type STRING.
 resource-version   Onap tenant resource version and is of type
                    STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[53] vf-create
-----------------------------------------------


usage: onap vf-create

Create a VF

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-j | --service-model-invariant-id] [-k | --service-model-uuid] [-q | --service-model-name]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-l | --cloud-region]
 [-y | --service-instance-id] [-z | --tenant-id] [-c | --vf-model-uuid]
 [-o | --instance-name] [-s | --long] [-e | --vf-model-name]
 [-g | --vf-model-version] [-b | --vf-model-invariant-id] [-a | --no-auth]
 [-n | --service-model-version] [-p | --host-password] [-m | --host-url]
 [-C | --no-catalog] [-i | --vf-model-customization-id] [-d | --debug]
 [-D | --context] [-w | --product-family] [-u | --host-username]

where::

 -j | --service-model-invariant-id  model invariant id. It is of type STRING. By
                                    default, it is .
 -k | --service-model-uuid          model name version id. It is of type STRING. By
                                    default, it is .
 -q | --service-model-name          service model name. It is of type STRING. By
                                    default, it is .
 -h | --help                        print help message. It is of type BOOL.
 -f | --format                      Output formats, supported formats such as table,
                                    csv, json, yaml. It is of type STRING.
 -t | --no-title                    whether to print title or not. It is of type BOOL.
 -V | --verify                      verify the command using available command sample
                                    file and mocking file. By default it goes with
                                    mock style. To enable the verification in real
                                    time, set DISABLE_MOCKING=true in the context
                                    param. It is of type BOOL. It is optional.
 -v | --version                     print service version. It is of type BOOL.
 -l | --cloud-region                cloud region identifier. It is of type STRING. By
                                    default, it is .
 -y | --service-instance-id         unique id for service instance. It is of type
                                    STRING. By default, it is .
 -z | --tenant-id                   openstack tenant id. It is of type STRING. By
                                    default, it is .
 -c | --vf-model-uuid               model uuid for vf. It is of type STRING. By
                                    default, it is .
 -o | --instance-name               service instance name. It is of type STRING. By
                                    default, it is .
 -s | --long                        whether to print all attributes or only short
                                    attributes. It is of type BOOL.
 -e | --vf-model-name               vf model name. It is of type STRING. By default,
                                    it is .
 -g | --vf-model-version            vf model version. It is of type STRING. By
                                    default, it is .
 -b | --vf-model-invariant-id       vf model invariant id. It is of type STRING. By
                                    default, it is .
 -a | --no-auth                     whether to authenticate user or not. It is of
                                    type BOOL.
 -n | --service-model-version       service model version. It is of type STRING. By
                                    default, it is .
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -C | --no-catalog                  whether to use given host-url directly without
                                    looking into catalog, it will override the
                                    service->mode. It is of type BOOL. It is
                                    optional.
 -i | --vf-model-customization-id   vf model customization id. It is of type STRING.
                                    By default, it is .
 -d | --debug                       Enable debug output. It is of type BOOL.
 -D | --context                     command context. It is of type MAP. It is
                                    optional.
 -w | --product-family              service type for serivce (e.g. vLB). It is of
                                    type STRING. By default, it is .
 -u | --host-username               Host user name. It is of type STRING. By default,
                                    it is read from environment variable
                                    OPEN_CLI_HOST_USERNAME.


Results::

 vf-id  id for the created vnf and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[54] vf-delete
-----------------------------------------------


usage: onap vf-delete

delete a VF (experimental)

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-e | --model-name] [-C | --no-catalog]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-l | --cloud-region] [-b | --model-invariant-id] [-y | --service-instance-id]
 [-z | --tenant-id] [-s | --long] [-D | --context]
 [-g | --model-version] [-c | --model-uuid] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password] [-x | --vf-id]

where::

 -m | --host-url              host url in http(s). It is of type URL. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_URL.
 -e | --model-name            model name available in SDC catalog. It is of
                              type STRING. By default, it is .
 -C | --no-catalog            whether to use given host-url directly without
                              looking into catalog, it will override the
                              service->mode. It is of type BOOL. It is
                              optional.
 -h | --help                  print help message. It is of type BOOL.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -V | --verify                verify the command using available command sample
                              file and mocking file. By default it goes with
                              mock style. To enable the verification in real
                              time, set DISABLE_MOCKING=true in the context
                              param. It is of type BOOL. It is optional.
 -v | --version               print service version. It is of type BOOL.
 -d | --debug                 Enable debug output. It is of type BOOL.
 -l | --cloud-region          Cloud region ID. It is of type STRING. By
                              default, it is .
 -b | --model-invariant-id    model invariant id for service in SDC catalog. It
                              is of type STRING. By default, it is .
 -y | --service-instance-id   unique id for service instance. It is of type
                              STRING. By default, it is .
 -z | --tenant-id             openstack tenant id (uuid). It is of type STRING.
                              By default, it is .
 -s | --long                  whether to print all attributes or only short
                              attributes. It is of type BOOL.
 -D | --context               command context. It is of type MAP. It is
                              optional.
 -g | --model-version         model version of service (eg. 1.0). It is of type
                              STRING. By default, it is .
 -c | --model-uuid            model uuid for service in SDC catalog. It is of
                              type STRING. By default, it is .
 -u | --host-username         Host user name. It is of type STRING. By default,
                              it is read from environment variable
                              OPEN_CLI_HOST_USERNAME.
 -a | --no-auth               whether to authenticate user or not. It is of
                              type BOOL.
 -p | --host-password         Host user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_PASSWORD. Secured.
 -x | --vf-id                 id for vnf. It is of type STRING. By default, it
                              is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[55] vf-list
-----------------------------------------------


usage: onap vf-list

List created VF instances for a service instance

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --customer-name]
 [-y | --service-type] [-s | --long] [-D | --context]
 [-z | --service-id] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --customer-name   customer name. It is of type STRING. By default,
                        it is .
 -y | --service-type    service subscription type. It is of type STRING.
                        By default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -z | --service-id      service ID. It is of type STRING. By default, it
                        is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 vf-id    VF ID for the given service and is of type STRING.
 vf-name  VF name and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[56] vf-model-certify-complete
-----------------------------------------------


usage: onap vf-model-certify-complete

Complete certifying Virtual function

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-b | --vf-id]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -b | --vf-id           VF ID. It is of type STRING. It is optional. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[57] vf-model-certify-request
-----------------------------------------------


usage: onap vf-model-certify-request

Request for certifying Virtual function

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-b | --vf-id]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -b | --vf-id           VF ID. It is of type STRING. It is optional. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[58] vf-model-certify-start
-----------------------------------------------


usage: onap vf-model-certify-start

Start certifying Virtual function

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-b | --vf-id]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -b | --vf-id           VF ID. It is of type STRING. It is optional. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[59] vf-model-checkin
-----------------------------------------------


usage: onap vf-model-checkin

Checkin Virtual function

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-b | --vf-id]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -b | --vf-id           VF ID. It is of type STRING. It is optional. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[60] vf-model-create
-----------------------------------------------


usage: onap vf-model-create

Create Virtual function from Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-y | --description]
 [-g | --vsp-version] [-x | --name] [-s | --long]
 [-D | --context] [-z | --vendor-name] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password] [-b | --vsp-id]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -y | --description     Description for VF. It is of type STRING. It is
                        optional. By default, it is .
 -g | --vsp-version     VSP version. It is of type STRING. It is
                        optional. By default, it is .
 -x | --name            Onap VF Name. It is of type STRING. By default,
                        it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -z | --vendor-name     Vendor name. It is of type STRING. It is
                        optional. By default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -b | --vsp-id          VSP ID. It is of type STRING. It is optional. By
                        default, it is .


Results::

 ID   VF ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[61] vf-model-list
-----------------------------------------------


usage: onap vf-model-list

List the VF resource model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 uuid             UUID and is of type STRING.
 uniqueid         UUID and is of type STRING.
 invariant-uuid   Invariant UUID and is of type STRING.
 name             Name and is of type STRING.
 version          version and is of type STRING.
 status           status and is of type STRING.
 description      description and is of type STRING.
 vsp-uuid         VSP uuid and is of type STRING.
 vsp-version      VSP version and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[62] vf-module-create
-----------------------------------------------


usage: onap vf-module-create

Create a VF Module

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-w | --tenant-id] [-v | --version] [-d | --debug]
 [-y | --vnf-instace-id] [-z | --parenet-service-model] [-x | --service-instace-id]
 [-l | --lcp-cloudregion-id] [-s | --long] [-D | --context]
 [-i | --instance-name] [-u | --host-username] [-r | --supress-rollback]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url                host url in http(s). It is of type URL. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_URL.
 -C | --no-catalog              whether to use given host-url directly without
                                looking into catalog, it will override the
                                service->mode. It is of type BOOL. It is
                                optional.
 -h | --help                    print help message. It is of type BOOL.
 -f | --format                  Output formats, supported formats such as table,
                                csv, json, yaml. It is of type STRING.
 -t | --no-title                whether to print title or not. It is of type BOOL.
 -V | --verify                  verify the command using available command sample
                                file and mocking file. By default it goes with
                                mock style. To enable the verification in real
                                time, set DISABLE_MOCKING=true in the context
                                param. It is of type BOOL. It is optional.
 -w | --tenant-id               openstack tenant id (uuid). It is of type STRING.
                                By default, it is .
 -v | --version                 print service version. It is of type BOOL.
 -d | --debug                   Enable debug output. It is of type BOOL.
 -y | --vnf-instace-id          vnf instance id. It is of type STRING. By
                                default, it is .
 -z | --parenet-service-model   parent service model name. It is of type STRING.
                                By default, it is .
 -x | --service-instace-id      unique id for service instance. It is of type
                                STRING. By default, it is .
 -l | --lcp-cloudregion-id      AIC LCP node location identifier. It is of type
                                STRING. By default, it is .
 -s | --long                    whether to print all attributes or only short
                                attributes. It is of type BOOL.
 -D | --context                 command context. It is of type MAP. It is
                                optional.
 -i | --instance-name           service instance name. It is of type STRING. By
                                default, it is .
 -u | --host-username           Host user name. It is of type STRING. By default,
                                it is read from environment variable
                                OPEN_CLI_HOST_USERNAME.
 -r | --supress-rollback        rollback changes if instantiation fails. It is of
                                type BOOL. It is optional.
 -a | --no-auth                 whether to authenticate user or not. It is of
                                type BOOL.
 -p | --host-password           Host user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[63] vf-show
-----------------------------------------------


usage: onap vf-show

Show details for VF

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password] [-x | --vf-id]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -x | --vf-id           VF identifier. It is of type STRING. By default,
                        it is .


Results::

 vf-id                    VF ID for the given service and is of type STRING.
 vf-name                  VF name and is of type STRING.
 vf-type                  VF name and is of type STRING.
 model-invariant-id       VF name and is of type STRING.
 model-uuid               model uuid and is of type STRING.
 model-customization-id   Model customization id and is of type STRING.
 service-id               Service ID of parent service and is of type
                          STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[64] vf2vfmodule-model-list
-----------------------------------------------


usage: onap vf2vfmodule-model-list

List the VF modules in a given VF model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-z | --vf-id]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -z | --vf-id           VF uuid. It is of type STRING. By default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 vsp-uuid               VSP uuid and is of type STRING.
 vsp-version            VSP version and is of type STRING.
 module-uuid            UUID and is of type STRING.
 module-invariant-uuid  Invariant UUID and is of type STRING.
 module-name            name and is of type STRING.
 module-version         version and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[65] vfmodule-create
-----------------------------------------------


usage: onap vfmodule-create

Create a VF module

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-l | --cloud-region]
 [-x | --service-model-name] [-j | --service-id] [-z | --service-model-version]
 [-i | --module-name] [-o | --vf-model-version] [-k | --vf-model-invariant-id]
 [-s | --long] [-g | --vfmodule-version] [-r | --service-model-invariant-id]
 [-n | --vf-model-name] [-a | --no-auth] [-w | --service-model-uuid]
 [-p | --host-password] [-m | --host-url] [-C | --no-catalog]
 [-d | --debug] [-b | --vfmodule-invariant-id] [-c | --vfmodule-uuid]
 [-A | --vf-model-uuid] [-y | --vf-id] [-D | --context]
 [-B | --tenant-id] [-e | --vfmodule-name] [-q | --vf-model-customization-id]
 [-u | --host-username]

where::

 -h | --help                        print help message. It is of type BOOL.
 -f | --format                      Output formats, supported formats such as table,
                                    csv, json, yaml. It is of type STRING.
 -t | --no-title                    whether to print title or not. It is of type BOOL.
 -V | --verify                      verify the command using available command sample
                                    file and mocking file. By default it goes with
                                    mock style. To enable the verification in real
                                    time, set DISABLE_MOCKING=true in the context
                                    param. It is of type BOOL. It is optional.
 -v | --version                     print service version. It is of type BOOL.
 -l | --cloud-region                cloud region identifier. It is of type STRING. By
                                    default, it is .
 -x | --service-model-name          service model name. It is of type STRING. By
                                    default, it is .
 -j | --service-id                  unique id for service. It is of type STRING. By
                                    default, it is .
 -z | --service-model-version       service model version. It is of type STRING. By
                                    default, it is .
 -i | --module-name                 vfmodule name. It is of type STRING. By default,
                                    it is .
 -o | --vf-model-version            vf model version. It is of type STRING. By
                                    default, it is .
 -k | --vf-model-invariant-id       vf model invariant id. It is of type STRING. By
                                    default, it is .
 -s | --long                        whether to print all attributes or only short
                                    attributes. It is of type BOOL.
 -g | --vfmodule-version            vf module version. It is of type STRING. By
                                    default, it is .
 -r | --service-model-invariant-id  model invariant id. It is of type STRING. By
                                    default, it is .
 -n | --vf-model-name               vf model name. It is of type STRING. By default,
                                    it is .
 -a | --no-auth                     whether to authenticate user or not. It is of
                                    type BOOL.
 -w | --service-model-uuid          model name version id. It is of type STRING. By
                                    default, it is .
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -C | --no-catalog                  whether to use given host-url directly without
                                    looking into catalog, it will override the
                                    service->mode. It is of type BOOL. It is
                                    optional.
 -d | --debug                       Enable debug output. It is of type BOOL.
 -b | --vfmodule-invariant-id       vfmodule model invariant id. It is of type
                                    STRING. By default, it is .
 -c | --vfmodule-uuid               model uuid for vfmodule. It is of type STRING. By
                                    default, it is .
 -A | --vf-model-uuid               model uuid for vf. It is of type STRING. By
                                    default, it is .
 -y | --vf-id                       unique id for related VF. It is of type STRING.
                                    By default, it is .
 -D | --context                     command context. It is of type MAP. It is
                                    optional.
 -B | --tenant-id                   openstack tenant id. It is of type STRING. By
                                    default, it is .
 -e | --vfmodule-name               vfmodule model name. It is of type STRING. By
                                    default, it is .
 -q | --vf-model-customization-id   vf model customization id. This can be obtained
                                    using SDC portal (Home -> service:xxx ->
                                    Composition -> click on VF for customization id).
                                    It is of type STRING. By default, it is .
 -u | --host-username               Host user name. It is of type STRING. By default,
                                    it is read from environment variable
                                    OPEN_CLI_HOST_USERNAME.


Results::

 vfmodule-id  id for the created vnf and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[66] vfmodule-delete
-----------------------------------------------


usage: onap vfmodule-delete

delete a VF module (experimental)

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-l | --cloud-region]
 [-j | --service-id] [-z | --tenant-id] [-y | --vf-id]
 [-k | --vf-model-invariant-id] [-s | --long] [-D | --context]
 [-g | --vfmodule-version] [-x | --vfmodule-id] [-e | --vfmodule-name]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url                host url in http(s). It is of type URL. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_URL.
 -C | --no-catalog              whether to use given host-url directly without
                                looking into catalog, it will override the
                                service->mode. It is of type BOOL. It is
                                optional.
 -h | --help                    print help message. It is of type BOOL.
 -f | --format                  Output formats, supported formats such as table,
                                csv, json, yaml. It is of type STRING.
 -t | --no-title                whether to print title or not. It is of type BOOL.
 -V | --verify                  verify the command using available command sample
                                file and mocking file. By default it goes with
                                mock style. To enable the verification in real
                                time, set DISABLE_MOCKING=true in the context
                                param. It is of type BOOL. It is optional.
 -v | --version                 print service version. It is of type BOOL.
 -d | --debug                   Enable debug output. It is of type BOOL.
 -l | --cloud-region            cloud region identifier. It is of type STRING. By
                                default, it is .
 -j | --service-id              unique id for service. It is of type STRING. By
                                default, it is .
 -z | --tenant-id               openstack tenant id. It is of type STRING. By
                                default, it is .
 -y | --vf-id                   unique id for related VF. It is of type STRING.
                                By default, it is .
 -k | --vf-model-invariant-id   vf model invariant id. It is of type STRING. By
                                default, it is .
 -s | --long                    whether to print all attributes or only short
                                attributes. It is of type BOOL.
 -D | --context                 command context. It is of type MAP. It is
                                optional.
 -g | --vfmodule-version        vf module version. It is of type STRING. By
                                default, it is .
 -x | --vfmodule-id             VF module Id. It is of type STRING. By default,
                                it is .
 -e | --vfmodule-name           vfmodule model name. It is of type STRING. By
                                default, it is .
 -u | --host-username           Host user name. It is of type STRING. By default,
                                it is read from environment variable
                                OPEN_CLI_HOST_USERNAME.
 -a | --no-auth                 whether to authenticate user or not. It is of
                                type BOOL.
 -p | --host-password           Host user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[67] vim-list
-----------------------------------------------


usage: onap vim-list

List the configured vims

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --cloud-name]
 [-s | --long] [-D | --context] [-y | --region-name]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --cloud-name      Onap cloud name. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -y | --region-name     Onap region name. It is of type STRING. By
                        default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 vim-id             Onap vim id and is of type STRING.
 name               Onap vim name and is of type STRING.
 type               Onap vim type and is of type STRING.
 vendor             Onap vim vendor and is of type STRING.
 version            Onap vim version and is of type STRING.
 url                Onap vim url and is of type STRING.
 username           Onap vim username and is of type STRING.
 cloud-domain       Onap vim cloud domain and is of type STRING.
 default-tenant     Onap vim tenant and is of type STRING.
 resource-version   Onap vim resource version and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[68] vim-register
-----------------------------------------------


usage: onap vim-register

Register a VIM under a given cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-e | --vendor] [-v | --version]
 [-x | --cloud-name] [-s | --long] [-k | --ssl-cacert]
 [-y | --region-name] [-b | --name] [-j | --password]
 [-a | --no-auth] [-n | --cloud-domain] [-z | --vim-id]
 [-p | --host-password] [-m | --host-url] [-C | --no-catalog]
 [-d | --debug] [-q | --vim-version] [-l | --ssl-insecure]
 [-c | --type] [-o | --default-tenant] [-D | --context]
 [-i | --username] [-u | --host-username] [-g | --url]

where::

 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -e | --vendor          Onap VIM vendor. It is of type STRING. By
                        default, it is .
 -v | --version         print service version. It is of type BOOL.
 -x | --cloud-name      Onap cloud name. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -k | --ssl-cacert      Onap VIM SSL certificate. It is of type TEXT. It
                        is optional. By default, it is .
 -y | --region-name     Onap region name. It is of type STRING. By
                        default, it is .
 -b | --name            Onap VIM name. It is of type STRING. By default,
                        it is .
 -j | --password        Onap VIM password. It is of type STRING. By
                        default, it is .
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -n | --cloud-domain    Onap VIM cloud domain. It is of type STRING. By
                        default, it is .
 -z | --vim-id          Onap VIM unique id. It is of type UUID.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -d | --debug           Enable debug output. It is of type BOOL.
 -q | --vim-version     Onap VIM version. It is of type STRING. By
                        default, it is .
 -l | --ssl-insecure    Onap VIM insecure. It is of type BOOL. It is
                        optional.
 -c | --type            Onap VIM type. It is of type STRING. By default,
                        it is .
 -o | --default-tenant  Onap VIM default tenant. It is of type STRING. By
                        default, it is .
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -i | --username        Onap VIM username. It is of type STRING. By
                        default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -g | --url             Onap VIM URL. It is of type STRING. By default,
                        it is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[69] vim-unregister
-----------------------------------------------


usage: onap vim-unregister

Un-register a VIM under from cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --cloud-name]
 [-s | --long] [-D | --context] [-y | --region-name]
 [-u | --host-username] [-a | --no-auth] [-z | --vim-id]
 [-p | --host-password] [-b | --resource-version]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -x | --cloud-name        Onap cloud name. It is of type STRING. By
                          default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -y | --region-name       Onap region name. It is of type STRING. By
                          default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -z | --vim-id            Onap VIM unique id. It is of type UUID.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -b | --resource-version  Onap vim resource version. It is of type STRING.
                          It is optional. By default, it is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[70] vlm-aggreement-create
-----------------------------------------------


usage: onap vlm-aggreement-create

Create license aggreement

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-y | --vlm-id] [-C | --no-catalog]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-z | --description] [-x | --name] [-g | --vlm-feature-group-id]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-e | --vlm-version] [-p | --host-password]

where::

 -m | --host-url              host url in http(s). It is of type URL. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_URL.
 -y | --vlm-id                License Model ID. It is of type STRING. By
                              default, it is .
 -C | --no-catalog            whether to use given host-url directly without
                              looking into catalog, it will override the
                              service->mode. It is of type BOOL. It is
                              optional.
 -h | --help                  print help message. It is of type BOOL.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -V | --verify                verify the command using available command sample
                              file and mocking file. By default it goes with
                              mock style. To enable the verification in real
                              time, set DISABLE_MOCKING=true in the context
                              param. It is of type BOOL. It is optional.
 -v | --version               print service version. It is of type BOOL.
 -d | --debug                 Enable debug output. It is of type BOOL.
 -z | --description           Description for aggreement. It is of type STRING.
                              By default, it is .
 -x | --name                  aggreement name. It is of type STRING. By
                              default, it is .
 -g | --vlm-feature-group-id  VLM feature group. It is of type STRING. By
                              default, it is .
 -s | --long                  whether to print all attributes or only short
                              attributes. It is of type BOOL.
 -D | --context               command context. It is of type MAP. It is
                              optional.
 -u | --host-username         Host user name. It is of type STRING. By default,
                              it is read from environment variable
                              OPEN_CLI_HOST_USERNAME.
 -a | --no-auth               whether to authenticate user or not. It is of
                              type BOOL.
 -e | --vlm-version           License Model version. It is of type STRING. By
                              default, it is .
 -p | --host-password         Host user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID   aggreement ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[71] vlm-aggreement-list
-----------------------------------------------


usage: onap vlm-aggreement-list

List license aggreement

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-y | --vlm-version]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-x | --vlm-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -y | --vlm-version     License Model version. It is of type STRING. By
                        default, it is .
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vlm-id          License Model ID. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID     aggreement ID and is of type STRING.
 name   aggreement name and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[72] vlm-checkin
-----------------------------------------------


usage: onap vlm-checkin

Checkin Vendor License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-y | --vlm-version]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-x | --vlm-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -y | --vlm-version     VLM version. It is of type STRING. By default, it
                        is .
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vlm-id          Onap VLM ID. It is of type STRING. By default, it
                        is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[73] vlm-checkout
-----------------------------------------------


usage: onap vlm-checkout

Checkout Vendor License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-y | --vlm-version]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-x | --vlm-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -y | --vlm-version     VLM version. It is of type STRING. By default, it
                        is .
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vlm-id          Onap VLM ID. It is of type STRING. By default, it
                        is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[74] vlm-create
-----------------------------------------------


usage: onap vlm-create

Create License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-y | --description]
 [-s | --long] [-D | --context] [-x | --vendor-name]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -y | --description     Description for License Model. It is of type
                        STRING. It is optional. By default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -x | --vendor-name     vendor name. It is of type STRING. By default, it
                        is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID   License Model ID and is of type UUID.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[75] vlm-entitlement-pool-create
-----------------------------------------------


usage: onap vlm-entitlement-pool-create

Create Entitlement Pool

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-y | --vlm-id] [-C | --no-catalog]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-z | --description] [-k | --manufacture-reference-number] [-x | --name]
 [-g | --operational-scope] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-e | --vlm-version]
 [-p | --host-password]

where::

 -m | --host-url                      host url in http(s). It is of type URL. By
                                      default, it is read from environment variable
                                      OPEN_CLI_HOST_URL.
 -y | --vlm-id                        License Model ID. It is of type STRING. By
                                      default, it is .
 -C | --no-catalog                    whether to use given host-url directly without
                                      looking into catalog, it will override the
                                      service->mode. It is of type BOOL. It is
                                      optional.
 -h | --help                          print help message. It is of type BOOL.
 -f | --format                        Output formats, supported formats such as table,
                                      csv, json, yaml. It is of type STRING.
 -t | --no-title                      whether to print title or not. It is of type BOOL.
 -V | --verify                        verify the command using available command sample
                                      file and mocking file. By default it goes with
                                      mock style. To enable the verification in real
                                      time, set DISABLE_MOCKING=true in the context
                                      param. It is of type BOOL. It is optional.
 -v | --version                       print service version. It is of type BOOL.
 -d | --debug                         Enable debug output. It is of type BOOL.
 -z | --description                   Description for Entitlement Pool. It is of type
                                      STRING. By default, it is .
 -k | --manufacture-reference-number  Manufature Reference Number. It is of type
                                      STRING. By default, it is .
 -x | --name                          Entitlement Pool name. It is of type STRING. By
                                      default, it is .
 -g | --operational-scope             Operational Scope. It is of type STRING.
 -s | --long                          whether to print all attributes or only short
                                      attributes. It is of type BOOL.
 -D | --context                       command context. It is of type MAP. It is
                                      optional.
 -u | --host-username                 Host user name. It is of type STRING. By default,
                                      it is read from environment variable
                                      OPEN_CLI_HOST_USERNAME.
 -a | --no-auth                       whether to authenticate user or not. It is of
                                      type BOOL.
 -e | --vlm-version                   License Model version. It is of type STRING. By
                                      default, it is .
 -p | --host-password                 Host user password. It is of type STRING. By
                                      default, it is read from environment variable
                                      OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID   Entitlement Pool ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[76] vlm-entitlement-pool-list
-----------------------------------------------


usage: onap vlm-entitlement-pool-list

entitlement pool list in a license model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-y | --vlm-version]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-x | --vlm-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -y | --vlm-version     License Model version. It is of type STRING. By
                        default, it is .
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vlm-id          License Model ID. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID     Feature group ID and is of type STRING.
 name   Feature group name and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[77] vlm-feature-group-create
-----------------------------------------------


usage: onap vlm-feature-group-create

Create feature group Pool

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-y | --vlm-id] [-C | --no-catalog]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-c | --part-number] [-v | --version]
 [-d | --debug] [-z | --description] [-b | --vlm-entitle-pool-id]
 [-k | --manufacture-reference-number] [-x | --name] [-g | --vlm-key-group-id]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-e | --vlm-version] [-p | --host-password]

where::

 -m | --host-url                      host url in http(s). It is of type URL. By
                                      default, it is read from environment variable
                                      OPEN_CLI_HOST_URL.
 -y | --vlm-id                        License Model ID. It is of type STRING. By
                                      default, it is .
 -C | --no-catalog                    whether to use given host-url directly without
                                      looking into catalog, it will override the
                                      service->mode. It is of type BOOL. It is
                                      optional.
 -h | --help                          print help message. It is of type BOOL.
 -f | --format                        Output formats, supported formats such as table,
                                      csv, json, yaml. It is of type STRING.
 -t | --no-title                      whether to print title or not. It is of type BOOL.
 -V | --verify                        verify the command using available command sample
                                      file and mocking file. By default it goes with
                                      mock style. To enable the verification in real
                                      time, set DISABLE_MOCKING=true in the context
                                      param. It is of type BOOL. It is optional.
 -c | --part-number                   Part number. It is of type STRING. By default, it
                                      is .
 -v | --version                       print service version. It is of type BOOL.
 -d | --debug                         Enable debug output. It is of type BOOL.
 -z | --description                   Description for feature group. It is of type
                                      STRING. By default, it is .
 -b | --vlm-entitle-pool-id           VLM Entitlement pool. It is of type STRING. By
                                      default, it is .
 -k | --manufacture-reference-number  Manufature Reference Number. It is of type
                                      STRING. By default, it is .
 -x | --name                          Feature group name. It is of type STRING. By
                                      default, it is .
 -g | --vlm-key-group-id              VLM keygroup. It is of type STRING. By default,
                                      it is .
 -s | --long                          whether to print all attributes or only short
                                      attributes. It is of type BOOL.
 -D | --context                       command context. It is of type MAP. It is
                                      optional.
 -u | --host-username                 Host user name. It is of type STRING. By default,
                                      it is read from environment variable
                                      OPEN_CLI_HOST_USERNAME.
 -a | --no-auth                       whether to authenticate user or not. It is of
                                      type BOOL.
 -e | --vlm-version                   License Model version. It is of type STRING. By
                                      default, it is .
 -p | --host-password                 Host user password. It is of type STRING. By
                                      default, it is read from environment variable
                                      OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID   Feature group ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[78] vlm-feature-group-list
-----------------------------------------------


usage: onap vlm-feature-group-list

Feature group list in a license model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-y | --vlm-version]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-x | --vlm-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -y | --vlm-version     License Model version. It is of type STRING. By
                        default, it is .
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vlm-id          License Model ID. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID     Feature group ID and is of type STRING.
 name   Feature group name and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[79] vlm-key-group-create
-----------------------------------------------


usage: onap vlm-key-group-create

Create License Key Group

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-z | --description]
 [-x | --name] [-s | --long] [-D | --context]
 [-y | --type] [-c | --vlm-id] [-u | --host-username]
 [-a | --no-auth] [-e | --vlm-version] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -z | --description     Description for License Key Group. It is of type
                        STRING. It is optional. By default, it is .
 -x | --name            name. It is of type STRING. By default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -y | --type            type of group (Universal, unique, one-time). It
                        is of type STRING.
 -c | --vlm-id          License Model Id. It is of type STRING. By
                        default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -e | --vlm-version     License Model version. It is of type STRING. By
                        default, it is .
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID   License Model ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[80] vlm-key-group-list
-----------------------------------------------


usage: onap vlm-key-group-list

key group list in a license model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-y | --vlm-version]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-x | --vlm-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -y | --vlm-version     License Model version. It is of type STRING. By
                        default, it is .
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vlm-id          License Model ID. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID     Feature group ID and is of type STRING.
 name   Feature group name and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[81] vlm-list
-----------------------------------------------


usage: onap vlm-list

List License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 id           License ID and is of type UUID.
 vendor-name  Vendor  Name and is of type STRING.
 vlm-version  VLM version and is of type STRING.
 status       status and is of type STRING.
 description  License description and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[82] vlm-revert
-----------------------------------------------


usage: onap vlm-revert

Revert Vendor License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-y | --vlm-version]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-x | --vlm-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -y | --vlm-version     VLM version. It is of type STRING. By default, it
                        is .
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vlm-id          Onap VLM ID. It is of type STRING. By default, it
                        is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[83] vlm-submit
-----------------------------------------------


usage: onap vlm-submit

Submit Vendor License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-y | --vlm-version]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-x | --vlm-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -y | --vlm-version     VLM version. It is of type STRING. By default, it
                        is .
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vlm-id          Onap VLM ID. It is of type STRING. By default, it
                        is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[84] vnfm-list
-----------------------------------------------


usage: onap vnfm-list

List the configured vnfm

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 vnfm-id            Onap vnfm id and is of type STRING.
 vim-id             Onap vnfm id and is of type STRING.
 certificate-url    Onap vnfm certificate-url and is of type STRING.
 resource-version   Onap vnfm resource version and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[85] vnfm-register
-----------------------------------------------


usage: onap vnfm-register

Register a VNFM in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-e | --vendor] [-v | --version] [-d | --debug]
 [-y | --vnfm-id] [-c | --type] [-x | --vim-id]
 [-s | --long] [-D | --context] [-b | --name]
 [-i | --username] [-j | --password] [-u | --host-username]
 [-g | --url] [-a | --no-auth] [-q | --vnfm-version]
 [-p | --host-password] [-z | --certificate-url]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -e | --vendor            Onap VNFM vendor. It is of type STRING. By
                          default, it is .
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -y | --vnfm-id           Onap VNFM unique id. It is of type UUID.
 -c | --type              Onap VNFM type. It is of type STRING. By default,
                          it is .
 -x | --vim-id            Onap VIM unique id. It is of type UUID.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -b | --name              Onap VNFM name. It is of type STRING. By default,
                          it is .
 -i | --username          Onap VNFM username. It is of type STRING. By
                          default, it is .
 -j | --password          Onap VNFM password. It is of type STRING. By
                          default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -g | --url               Onap VNFM URL. It is of type STRING. By default,
                          it is .
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -q | --vnfm-version      Onap VNFM version. It is of type STRING. By
                          default, it is .
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -z | --certificate-url   Onap VNFM certificate-url. It is of type STRING.
                          It is optional. By default, it is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[86] vnfm-show
-----------------------------------------------


usage: onap vnfm-show

Show the VNFM in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --vnfm-id]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vnfm-id         Onap VNFM unique id. It is of type UUID.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 name       Onap vnfm name and is of type STRING.
 type       Onap vnfm type and is of type STRING.
 vendor     Onap vnfm vendor and is of type STRING.
 version    Onap vnfm version and is of type STRING.
 url        Onap vnfm url and is of type STRING.
 username   Onap vnfm username and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[87] vnfm-unregister
-----------------------------------------------


usage: onap vnfm-unregister

Un-register a VNFM in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-y | --resource-version]
 [-x | --vnfm-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        whether to use given host-url directly without
                          looking into catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            verify the command using available command sample
                          file and mocking file. By default it goes with
                          mock style. To enable the verification in real
                          time, set DISABLE_MOCKING=true in the context
                          param. It is of type BOOL. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -y | --resource-version  Onap vim resource version. It is of type STRING.
                          It is optional. By default, it is .
 -x | --vnfm-id           Onap VNFM unique id. It is of type UUID.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[88] vsp-add-artifact
-----------------------------------------------


usage: onap vsp-add-artifact

Upload the CSAR file to VSP

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --vsp-id]
 [-s | --long] [-D | --context] [-y | --vsp-version]
 [-z | --vsp-file]

where::

 -m | --host-url      host url in http(s). It is of type URL. By
                      default, it is read from environment variable
                      OPEN_CLI_HOST_URL.
 -C | --no-catalog    whether to use given host-url directly without
                      looking into catalog, it will override the
                      service->mode. It is of type BOOL. It is
                      optional.
 -h | --help          print help message. It is of type BOOL.
 -f | --format        Output formats, supported formats such as table,
                      csv, json, yaml. It is of type STRING.
 -t | --no-title      whether to print title or not. It is of type BOOL.
 -V | --verify        verify the command using available command sample
                      file and mocking file. By default it goes with
                      mock style. To enable the verification in real
                      time, set DISABLE_MOCKING=true in the context
                      param. It is of type BOOL. It is optional.
 -v | --version       print service version. It is of type BOOL.
 -d | --debug         Enable debug output. It is of type BOOL.
 -x | --vsp-id        Onap VSP ID. It is of type STRING. By default, it
                      is .
 -s | --long          whether to print all attributes or only short
                      attributes. It is of type BOOL.
 -D | --context       command context. It is of type MAP. It is
                      optional.
 -y | --vsp-version   Onap VSP version. It is of type STRING. By
                      default, it is .
 -z | --vsp-file      CSAR File path. It is of type BINARY. By default,
                      it is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[89] vsp-checkin
-----------------------------------------------


usage: onap vsp-checkin

Checkin Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --vsp-id]
 [-s | --long] [-D | --context] [-y | --vsp-version]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vsp-id          Onap VSP ID. It is of type STRING. By default, it
                        is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -y | --vsp-version     VSP version. It is of type STRING. By default, it
                        is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[90] vsp-checkout
-----------------------------------------------


usage: onap vsp-checkout

Checkout Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --vsp-id]
 [-s | --long] [-D | --context] [-y | --vsp-version]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vsp-id          Onap VSP ID. It is of type STRING. By default, it
                        is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -y | --vsp-version     VSP version. It is of type STRING. By default, it
                        is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[91] vsp-create
-----------------------------------------------


usage: onap vsp-create

Create Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-j | --vlm-feature-group-id] [-C | --no-catalog]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-e | --vlm-vendor] [-b | --vsp-subcategory] [-z | --vsp-category]
 [-x | --vsp-name] [-s | --long] [-D | --context]
 [-y | --vsp-description] [-i | --vlm-agreement-id] [-c | --vlm-version]
 [-u | --host-username] [-a | --no-auth] [-g | --vlm-id]
 [-p | --host-password]

where::

 -m | --host-url              host url in http(s). It is of type URL. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_URL.
 -j | --vlm-feature-group-id  Feature Group ID. It is of type STRING. By
                              default, it is .
 -C | --no-catalog            whether to use given host-url directly without
                              looking into catalog, it will override the
                              service->mode. It is of type BOOL. It is
                              optional.
 -h | --help                  print help message. It is of type BOOL.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -V | --verify                verify the command using available command sample
                              file and mocking file. By default it goes with
                              mock style. To enable the verification in real
                              time, set DISABLE_MOCKING=true in the context
                              param. It is of type BOOL. It is optional.
 -v | --version               print service version. It is of type BOOL.
 -d | --debug                 Enable debug output. It is of type BOOL.
 -e | --vlm-vendor            License Model vendor. It is of type STRING. By
                              default, it is .
 -b | --vsp-subcategory       Sub Category of VSP. It is of type STRING. It is
                              optional.
 -z | --vsp-category          Category of the VSP. It is of type STRING. It is
                              optional.
 -x | --vsp-name              Onap VSP Name. It is of type STRING. By default,
                              it is .
 -s | --long                  whether to print all attributes or only short
                              attributes. It is of type BOOL.
 -D | --context               command context. It is of type MAP. It is
                              optional.
 -y | --vsp-description       Description for VSP. It is of type STRING. It is
                              optional. By default, it is .
 -i | --vlm-agreement-id      License Agreement ID. It is of type STRING. By
                              default, it is .
 -c | --vlm-version           License version. It is of type STRING. It is
                              optional.
 -u | --host-username         Host user name. It is of type STRING. By default,
                              it is read from environment variable
                              OPEN_CLI_HOST_USERNAME.
 -a | --no-auth               whether to authenticate user or not. It is of
                              type BOOL.
 -g | --vlm-id                License Model ID. It is of type STRING. By
                              default, it is .
 -p | --host-password         Host user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID   VSP ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[92] vsp-list
-----------------------------------------------


usage: onap vsp-list

List of the Vendor Software Products

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID               VSP ID and is of type STRING.
 name             VSP Name and is of type STRING.
 vendor-name      Vendor name and is of type STRING.
 version          Version and is of type STRING.
 status           status and is of type STRING.
 license-id       license aggreement and is of type STRING.
 license-version  license version and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[93] vsp-package
-----------------------------------------------


usage: onap vsp-package

Package Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --vsp-id]
 [-s | --long] [-D | --context] [-y | --vsp-version]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vsp-id          Onap VSP ID. It is of type STRING. By default, it
                        is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -y | --vsp-version     VSP version. It is of type STRING. By default, it
                        is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[94] vsp-revert
-----------------------------------------------


usage: onap vsp-revert

Revert Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --vsp-id]
 [-s | --long] [-D | --context] [-y | --vsp-version]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vsp-id          Onap VSP ID. It is of type STRING. By default, it
                        is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -y | --vsp-version     VSP version. It is of type STRING. By default, it
                        is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[95] vsp-show
-----------------------------------------------


usage: onap vsp-show

Details of the Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --vsp-id]
 [-s | --long] [-D | --context] [-y | --vsp-version]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vsp-id          Onap VSP ID. It is of type STRING. By default, it
                        is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -y | --vsp-version     Onap VSP version. It is of type STRING. By
                        default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 name         VSP Name and is of type STRING.
 ID           VSP ID and is of type STRING.
 description  Description for the VSP and is of type STRING.
 vendor-name  Vendor name and is of type STRING.
 vendor-id    Vendor id and is of type STRING.
 version      Version and is of type STRING.
 status       status and is of type STRING.
 license-id   license aggreement and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[96] vsp-submit
-----------------------------------------------


usage: onap vsp-submit

Submit Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --vsp-id]
 [-s | --long] [-D | --context] [-y | --vsp-version]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vsp-id          Onap VSP ID. It is of type STRING. By default, it
                        is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -y | --vsp-version     VSP version. It is of type STRING. By default, it
                        is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[97] vsp-validate
-----------------------------------------------


usage: onap vsp-validate

Validated the uploaded Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --vsp-id]
 [-s | --long] [-D | --context] [-y | --vsp-version]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          verify the command using available command sample
                        file and mocking file. By default it goes with
                        mock style. To enable the verification in real
                        time, set DISABLE_MOCKING=true in the context
                        param. It is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -x | --vsp-id          Onap VSP ID. It is of type STRING. By default, it
                        is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -y | --vsp-version     VSP version. It is of type STRING. By default, it
                        is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 status   Validation status and is of type STRING.
 errors   Validation messages and is of type JSON.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>
