.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2018 Huawei Technologies Co., Ltd.


.. toctree::
   :maxdepth: 2


.. _cli_cmd_help_onap_beijing:


onap-beijing
==============

[1] basic-login
-----------------------------------------------


usage: oclip basic-login

ONAP basic login auth command

Product: onap-beijing
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


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[2] basic-logout
-----------------------------------------------


usage: oclip basic-logout

ONAP basic logout auth command

Product: onap-beijing
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


usage: oclip catalog

ONAP catalog command to find the base path for service.

Product: onap-beijing
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


usage: oclip cloud-create

Create a cloud region in Onap

Product: onap-beijing
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


usage: oclip cloud-delete

Delete a cloud region from Onap

Product: onap-beijing
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


usage: oclip cloud-list

List the configured clouds and Onap service subscriptions

Product: onap-beijing
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


usage: oclip customer-create

Create a customer in Onap

Product: onap-beijing
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


usage: oclip customer-delete

Delete a customer from Onap

Product: onap-beijing
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


usage: oclip customer-list

Lists the registered customers in Onap

Product: onap-beijing
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


usage: oclip customer-show

Retrieves the given registered customer in Onap

Product: onap-beijing
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


usage: oclip ems-list

List the configured ems

Product: onap-beijing
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


usage: oclip ems-register

Register a EMS in Onap

Product: onap-beijing
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


usage: oclip ems-show

Show the details of configured ems

Product: onap-beijing
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


usage: oclip ems-unregister

Un-register a EMS in Onap

Product: onap-beijing
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


usage: oclip ep-create

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


usage: oclip ep-show

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



[17] generic-search
-----------------------------------------------


usage: oclip generic-search

generic-search

Product: onap-beijing
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-r | --start-node-type] [-C | --no-catalog]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-k | --key] [-z | --value] [-s | --long]
 [-D | --context] [-e | --depth] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -r | --start-node-type   start-node-type. It is of type STRING. By
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
 -k | --key               key. It is of type STRING. By default, it is .
 -z | --value             value. It is of type STRING. By default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -e | --depth             search depth. It is of type DIGIT. By default, it
                          is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 resource-type  Onap result-type and is of type STRING.
 resource-link  Onap resource-link and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[18] license-group-create
-----------------------------------------------


usage: oclip license-group-create

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



[19] license-group-show
-----------------------------------------------


usage: oclip license-group-show

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



[20] license-model-create
-----------------------------------------------


usage: oclip license-model-create

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



[21] license-model-show
-----------------------------------------------


usage: oclip license-model-show

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



[22] logic-link-create
-----------------------------------------------


usage: oclip logic-link-create

Create a logic-link

Product: onap-beijing
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-g | --prov-status] [-C | --no-catalog]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-V | --verify] [-v | --version] [-d | --debug]
 [-w | --in-maint] [-s | --long] [-D | --context]
 [-n | --name] [-j | --link-role] [-x | --speed-units]
 [-u | --host-username] [-r | --relationship] [-a | --no-auth]
 [-p | --host-password] [-i | --link-type] [-q | --speed-value]
 [-z | --link-id]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -g | --prov-status     Prov Status of this device (not under canopi
                        control) Valid values [PREPROV/NVTPROV/PROV]. It
                        is of type STRING. It is optional. By default, it
                        is .
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
 -w | --in-maint        Used to indicate whether or not this object is in
                        maintenance mode (maintenance mode = true). It is
                        of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -n | --name            logic-link-name. It is of type STRING. By
                        default, it is .
 -j | --link-role       link-role. It is of type STRING. It is optional.
                        By default, it is .
 -x | --speed-units     speed-units. It is of type STRING. It is
                        optional. By default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -r | --relationship    relationship. It is of type JSON. It's
                        recommended to input the complete path of the
                        file, which is having the value for it. It is
                        optional.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -i | --link-type       link-type. It is of type STRING. It is optional.
                        By default, it is .
 -q | --speed-value     speed-value. It is of type STRING. It is
                        optional. By default, it is .
 -z | --link-id         link-id. It is of type STRING. It is optional. By
                        default, it is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[23] logic-link-delete
-----------------------------------------------


usage: oclip logic-link-delete

Delete logic-link-delete from Onap

Product: onap-beijing
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-n | --logic-link-name]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password] [-b | --resource-version]

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
 -n | --logic-link-name   Onap logic-link name. It is of type STRING. By
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
 -b | --resource-version  Onap logic-link-name resource version. It is of
                          type UUID.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[24] logic-link-list
-----------------------------------------------


usage: oclip logic-link-list

show detail for one single logic-link

Product: onap-beijing
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-n | --name]
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
 -n | --name            logic-link-name. It is of type STRING. By
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

 logic-link-name    Onap logic-link name and is of type STRING.
 logic-link-id      Onap logic-link id and is of type STRING.
 in-maint           Onap in-maint and is of type STRING.
 speed-value        Onap speed-value and is of type STRING.
 speed-units        Onap speed-units and is of type STRING.
 prov-status        Onap prov-status and is of type STRING.
 link-role          Onap link-role and is of type STRING.
 resource-version   Onap resource-version and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[25] logic-link-list
-----------------------------------------------


usage: oclip logic-link-list

show detail for one single logic-link

Product: onap-beijing
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-n | --name]
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
 -n | --name            logic-link-name. It is of type STRING. By
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

 logic-link-name    Onap logic-link name and is of type STRING.
 logic-link-id      Onap logic-link id and is of type STRING.
 in-maint           Onap in-maint and is of type STRING.
 speed-value        Onap speed-value and is of type STRING.
 speed-units        Onap speed-units and is of type STRING.
 prov-status        Onap prov-status and is of type STRING.
 link-role          Onap link-role and is of type STRING.
 resource-version   Onap resource-version and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>

tenant-create
----------------------------------------------------

usage: oclip tenant-create

Create a tenant under given cloud region in Onap

Product: onap-beijing
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
 -C | --no-catalog      Whether to use given host-url directly or
                        discover it from catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          Helps to verify the command using samples
                        provides under open-cli-samples directory. By
                        default, it goes with mock.To enable the
                        verification of samples in real time, set
                        DISABLE_MOCKING=true in the context parameter. It
                        is of type BOOL. It is optional.
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>


vf-create
----------------------------------------------------

usage: oclip vf-create

Create a VF

Product: onap-beijing
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
 -V | --verify                      Helps to verify the command using samples
                                    provides under open-cli-samples directory. By
                                    default, it goes with mock.To enable the
                                    verification of samples in real time, set
                                    DISABLE_MOCKING=true in the context parameter. It
                                    is of type BOOL. It is optional.
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
 -a | --no-auth                     Whether to authenticate user or not. It is of
                                    type BOOL.
 -n | --service-model-version       service model version. It is of type STRING. By
                                    default, it is .
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -C | --no-catalog                  Whether to use given host-url directly or
                                    discover it from catalog, it will override the
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


vf-list
----------------------------------------------------

usage: oclip vf-list

List created VF instances for a service instance

Product: onap-beijing
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
 -C | --no-catalog      Whether to use given host-url directly or
                        discover it from catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          Helps to verify the command using samples
                        provides under open-cli-samples directory. By
                        default, it goes with mock.To enable the
                        verification of samples in real time, set
                        DISABLE_MOCKING=true in the context parameter. It
                        is of type BOOL. It is optional.
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 vf-id    VF ID for the given service and is of type STRING.
 vf-name  VF name and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>


vf-model-create
----------------------------------------------------

usage: oclip vf-model-create

Create Virtual function from Vendor Software Product

Product: onap-beijing
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
 -C | --no-catalog      Whether to use given host-url directly or
                        discover it from catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          Helps to verify the command using samples
                        provides under open-cli-samples directory. By
                        default, it goes with mock.To enable the
                        verification of samples in real time, set
                        DISABLE_MOCKING=true in the context parameter. It
                        is of type BOOL. It is optional.
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
 -a | --no-auth         Whether to authenticate user or not. It is of
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


vfmodule-create
----------------------------------------------------

usage: oclip vfmodule-create

Create a VF module

Product: onap-beijing
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
 -V | --verify                      Helps to verify the command using samples
                                    provides under open-cli-samples directory. By
                                    default, it goes with mock.To enable the
                                    verification of samples in real time, set
                                    DISABLE_MOCKING=true in the context parameter. It
                                    is of type BOOL. It is optional.
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
 -a | --no-auth                     Whether to authenticate user or not. It is of
                                    type BOOL.
 -w | --service-model-uuid          model name version id. It is of type STRING. By
                                    default, it is .
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -C | --no-catalog                  Whether to use given host-url directly or
                                    discover it from catalog, it will override the
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


vlm-aggreement-create
----------------------------------------------------

usage: oclip vlm-aggreement-create

Create license aggreement

Product: onap-beijing
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
 -C | --no-catalog            Whether to use given host-url directly or
                              discover it from catalog, it will override the
                              service->mode. It is of type BOOL. It is
                              optional.
 -h | --help                  print help message. It is of type BOOL.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -V | --verify                Helps to verify the command using samples
                              provides under open-cli-samples directory. By
                              default, it goes with mock.To enable the
                              verification of samples in real time, set
                              DISABLE_MOCKING=true in the context parameter. It
                              is of type BOOL. It is optional.
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
 -a | --no-auth               Whether to authenticate user or not. It is of
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


vlm-entitlement-pool-create
----------------------------------------------------

usage: oclip vlm-entitlement-pool-create

Create Entitlement Pool

Product: onap-beijing
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
 -C | --no-catalog                    Whether to use given host-url directly or
                                      discover it from catalog, it will override the
                                      service->mode. It is of type BOOL. It is
                                      optional.
 -h | --help                          print help message. It is of type BOOL.
 -f | --format                        Output formats, supported formats such as table,
                                      csv, json, yaml. It is of type STRING.
 -t | --no-title                      whether to print title or not. It is of type BOOL.
 -V | --verify                        Helps to verify the command using samples
                                      provides under open-cli-samples directory. By
                                      default, it goes with mock.To enable the
                                      verification of samples in real time, set
                                      DISABLE_MOCKING=true in the context parameter. It
                                      is of type BOOL. It is optional.
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
 -a | --no-auth                       Whether to authenticate user or not. It is of
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


vlm-key-group-list
----------------------------------------------------

usage: oclip vlm-key-group-list

key group list in a license model

Product: onap-beijing
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
 -C | --no-catalog      Whether to use given host-url directly or
                        discover it from catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -y | --vlm-version     License Model version. It is of type STRING. By
                        default, it is .
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          Helps to verify the command using samples
                        provides under open-cli-samples directory. By
                        default, it goes with mock.To enable the
                        verification of samples in real time, set
                        DISABLE_MOCKING=true in the context parameter. It
                        is of type BOOL. It is optional.
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID     Feature group ID and is of type STRING.
 name   Feature group name and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>


vnf-create-with-template
----------------------------------------------------

usage: oclip vnf-create-with-template

Create a pnf with a template. this is flexible approch to create VNF , all the parameters are in template json file

Product: onap-beijing
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-r | --template]
 [-i | --vnf-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      Whether to use given host-url directly or
                        discover it from catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          Helps to verify the command using samples
                        provides under open-cli-samples directory. By
                        default, it goes with mock.To enable the
                        verification of samples in real time, set
                        DISABLE_MOCKING=true in the context parameter. It
                        is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -r | --template        template. It is of type JSON. It's recommended to
                        input the complete path of the file, which is
                        having the value for it.
 -i | --vnf-id          vnf-id. It is of type STRING. By default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>


vnfm-show
----------------------------------------------------

usage: oclip vnfm-show

Show the VNFM in Onap

Product: onap-beijing
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
 -C | --no-catalog      Whether to use given host-url directly or
                        discover it from catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          Helps to verify the command using samples
                        provides under open-cli-samples directory. By
                        default, it goes with mock.To enable the
                        verification of samples in real time, set
                        DISABLE_MOCKING=true in the context parameter. It
                        is of type BOOL. It is optional.
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
 -a | --no-auth         Whether to authenticate user or not. It is of
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


vsp-create
----------------------------------------------------

usage: oclip vsp-create

Create Vendor Software Product

Product: onap-beijing
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
 -C | --no-catalog            Whether to use given host-url directly or
                              discover it from catalog, it will override the
                              service->mode. It is of type BOOL. It is
                              optional.
 -h | --help                  print help message. It is of type BOOL.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -V | --verify                Helps to verify the command using samples
                              provides under open-cli-samples directory. By
                              default, it goes with mock.To enable the
                              verification of samples in real time, set
                              DISABLE_MOCKING=true in the context parameter. It
                              is of type BOOL. It is optional.
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
 -a | --no-auth               Whether to authenticate user or not. It is of
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


vsp-submit
----------------------------------------------------

usage: oclip vsp-submit

Submit Vendor Software Product

Product: onap-beijing
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
 -C | --no-catalog      Whether to use given host-url directly or
                        discover it from catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          Helps to verify the command using samples
                        provides under open-cli-samples directory. By
                        default, it goes with mock.To enable the
                        verification of samples in real time, set
                        DISABLE_MOCKING=true in the context parameter. It
                        is of type BOOL. It is optional.
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>


tenant-delete
----------------------------------------------------

usage: oclip tenant-delete

Delete tenant under given cloud region in Onap

Product: onap-beijing
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
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
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
 -a | --no-auth           Whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>


vf-delete
----------------------------------------------------

usage: oclip vf-delete

delete a VF (experimental)

Product: onap-beijing
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
 -C | --no-catalog            Whether to use given host-url directly or
                              discover it from catalog, it will override the
                              service->mode. It is of type BOOL. It is
                              optional.
 -h | --help                  print help message. It is of type BOOL.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -V | --verify                Helps to verify the command using samples
                              provides under open-cli-samples directory. By
                              default, it goes with mock.To enable the
                              verification of samples in real time, set
                              DISABLE_MOCKING=true in the context parameter. It
                              is of type BOOL. It is optional.
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
 -a | --no-auth               Whether to authenticate user or not. It is of
                              type BOOL.
 -p | --host-password         Host user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_PASSWORD. Secured.
 -x | --vf-id                 id for vnf. It is of type STRING. By default, it
                              is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>


vf-model-certify-complete
----------------------------------------------------

usage: oclip vf-model-certify-complete

Complete certifying Virtual function

Product: onap-beijing
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
 -C | --no-catalog      Whether to use given host-url directly or
                        discover it from catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          Helps to verify the command using samples
                        provides under open-cli-samples directory. By
                        default, it goes with mock.To enable the
                        verification of samples in real time, set
                        DISABLE_MOCKING=true in the context parameter. It
                        is of type BOOL. It is optional.
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>


vf-model-list
----------------------------------------------------

usage: oclip vf-model-list

List the VF resource model in SDC

Product: onap-beijing
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
 -C | --no-catalog      Whether to use given host-url directly or
                        discover it from catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -h | --help            print help message. It is of type BOOL.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -V | --verify          Helps to verify the command using samples
                        provides under open-cli-samples directory. By
                        default, it goes with mock.To enable the
                        verification of samples in real time, set
                        DISABLE_MOCKING=true in the context parameter. It
                        is of type BOOL. It is optional.
 -v | --version         print service version. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         Whether to authenticate user or not. It is of
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

