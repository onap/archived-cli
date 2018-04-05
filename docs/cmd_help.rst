.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017-18 Huawei Technologies Co., Ltd.


.. toctree::
   :maxdepth: 2


.. _cli_cmd_help:

open-cli
=========

Open CLI is the OCLIP platform facilitates creating the commands from YAMLs. Currently it supports commands
for refreshing the existing command YAMLs and validating the YAMLs.

[1] schema-refresh
-----------------------------------------------


usage: oclip schema-refresh

OCLIP command to refresh schemas stored in open-cli-schema folders.

Product: open-cli
Service: schema
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-s | --long] [-D | --context] [-h | --help]
 [-f | --format] [-t | --no-title] [-v | --version]
 [-d | --debug]

where::

 -s | --long      whether to print all attributes or only short
                  attributes. It is of type BOOL.
 -D | --context   command context. It is of type MAP. It is
                  optional.
 -h | --help      print help message. It is of type BOOL.
 -f | --format    Output formats, supported formats such as table,
                  csv, json, yaml. It is of type STRING.
 -t | --no-title  whether to print title or not. It is of type BOOL.
 -v | --version   print service version. It is of type BOOL.
 -d | --debug     Enable debug output. It is of type BOOL.


Results::

 sr.no        Serial Number and is of type STRING.
 command      Command name and is of type STRING.
 product      Command product version and is of type STRING.
 schema       Schema name and is of type STRING.
 ocs-version  Schema version and is of type STRING.
 type         Command type and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[2] schema-validate
-----------------------------------------------


usage: oclip schema-validate

OCLIP command to validate schema

Product: open-cli
Service: schema
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-s | --long] [-D | --context] [-h | --help]
 [-f | --format] [-t | --no-title] [-b | --ocs-version]
 [-v | --version] [-d | --debug] [-l | --schema-location]
 [-i | --internal-schema]

where::

 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -h | --help              print help message. It is of type BOOL.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -b | --ocs-version       OCS version. It is of type STRING. It is optional.
 -v | --version           print service version. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -l | --schema-location   Schema file location. It is of type URL. By
                          default, it is .
 -i | --internal-schema   Validate existing schema file. It is of type BOOL.


Results::

 sl-no  Serial Number of error and is of type STRING.
 error  Schema validation error and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>





[65] subscription-create
-----------------------------------------------


usage: oclip subscription-create

Create a subscription of a customer for given service in specific cloud region in Onap

Product: onap-beijing
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
                          looking into catalog, it will overrid the
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



[66] subscription-create-with-template
-----------------------------------------------


usage: oclip subscription-create-with-template

Create a subscription of a customer with template

Product: onap-beijing
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-h | --help]
 [-f | --format] [-t | --no-title] [-V | --verify]
 [-v | --version] [-d | --debug] [-x | --customer-name]
 [-r | --template] [-s | --long] [-D | --context]
 [-e | --service-type] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      whether to use given host-url directly without
                        looking into catalog, it will overrid the
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
 -r | --template        template. It is of type JSON. It's recommended to
                        input the complete path of the file, which is
                        having the value for it.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -e | --service-type    Onap service type. It is of type STRING. By
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



[67] subscription-delete
-----------------------------------------------


usage: oclip subscription-delete

Delete the subscription for a given customer in Onap

Product: onap-beijing
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
                          looking into catalog, it will overrid the
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



[68] subscription-list
-----------------------------------------------


usage: oclip subscription-list

Lists the subscription for a given customer in Onap

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
                        looking into catalog, it will overrid the
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