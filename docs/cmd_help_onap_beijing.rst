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
