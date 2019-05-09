.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017-18 Huawei Technologies Co., Ltd.


.. toctree::
   :maxdepth: 2


.. _cli_cmd_help:

open-cli
==========================


[1] basic-login
-----------------------------------------------



usage: oclip basic-login

Provides HTTP basic authorization support.


Product: open-cli
Service: basic-auth
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-u | --host-username] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      Whether to use given host-url directly or
                        discover it from catalog, it will override the
                        service->mode. It is of type BOOL. It is
                        optional.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -h | --help            print help message. It is of type BOOL.
 -V | --verify          Helps to verify the command using samples
                        provides under open-cli-samples directory. By
                        default, it goes with mock.To enable the
                        verification of samples in real time, set
                        DISABLE_MOCKING=true in the context parameter. It
                        is of type BOOL. It is optional.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -v | --version         print service version. It is of type BOOL.
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

 Authorization  Authorization and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[2] basic-logout
-----------------------------------------------



usage: oclip basic-logout

Provides HTTP basic authorization support. As part of logout, it invalidate authoirzation key generated while login.


Product: open-cli
Service: basic-auth
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context]

where::

 -m | --host-url    host url in http(s). It is of type URL. By
                    default, it is read from environment variable
                    OPEN_CLI_HOST_URL.
 -C | --no-catalog  Whether to use given host-url directly or
                    discover it from catalog, it will override the
                    service->mode. It is of type BOOL. It is
                    optional.
 -f | --format      Output formats, supported formats such as table,
                    csv, json, yaml. It is of type STRING.
 -h | --help        print help message. It is of type BOOL.
 -V | --verify      Helps to verify the command using samples
                    provides under open-cli-samples directory. By
                    default, it goes with mock.To enable the
                    verification of samples in real time, set
                    DISABLE_MOCKING=true in the context parameter. It
                    is of type BOOL. It is optional.
 -t | --no-title    whether to print title or not. It is of type BOOL.
 -d | --debug       Enable debug output. It is of type BOOL.
 -v | --version     print service version. It is of type BOOL.
 -s | --long        whether to print all attributes or only short
                    attributes. It is of type BOOL.
 -D | --context     command context. It is of type MAP. It is
                    optional.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[3] catalog
-----------------------------------------------



usage: oclip catalog

Provides catalog discovery support to find the base api path for given service from product's catalog service.
Any product want to support catalog, it could derive from this command. Currently ONAP uses it and create
derived command by using ONAP MSB service.


Product: open-cli
Service: catalog
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-m | --host-url] [-C | --no-catalog] [-l | --catalog-service-name]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-s | --long] [-D | --context] [-i | --catalog-service-version]

where::

 -m | --host-url                  host url in http(s). It is of type URL. By
                                  default, it is read from environment variable
                                  OPEN_CLI_HOST_URL.
 -C | --no-catalog                Whether to use given host-url directly or
                                  discover it from catalog, it will override the
                                  service->mode. It is of type BOOL. It is
                                  optional.
 -l | --catalog-service-name      service name registered in catalog service. It is
                                  of type STRING. By default, it is .
 -f | --format                    Output formats, supported formats such as table,
                                  csv, json, yaml. It is of type STRING.
 -h | --help                      print help message. It is of type BOOL.
 -V | --verify                    Helps to verify the command using samples
                                  provides under open-cli-samples directory. By
                                  default, it goes with mock.To enable the
                                  verification of samples in real time, set
                                  DISABLE_MOCKING=true in the context parameter. It
                                  is of type BOOL. It is optional.
 -t | --no-title                  whether to print title or not. It is of type BOOL.
 -d | --debug                     Enable debug output. It is of type BOOL.
 -v | --version                   print service version. It is of type BOOL.
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



[4] execution-list
-----------------------------------------------



usage: oclip execution-list

List the executions of the given command so far

Product: open-cli
Service: execution
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-e | --request-id] [-b | --service] [-f | --format]
 [-h | --help] [-t | --no-title] [-d | --debug]
 [-x | --start-time] [-v | --version] [-n | --command]
 [-l | --product] [-y | --end-time] [-s | --long]
 [-D | --context] [-c | --profile]

where::

 -e | --request-id  Request id. It is of type STRING. It is optional.
                    By default, it is .
 -b | --service     For a given service. It is of type STRING. It is
                    optional. By default, it is .
 -f | --format      Output formats, supported formats such as table,
                    csv, json, yaml. It is of type STRING.
 -h | --help        print help message. It is of type BOOL.
 -t | --no-title    whether to print title or not. It is of type BOOL.
 -d | --debug       Enable debug output. It is of type BOOL.
 -x | --start-time  From start time. It is of type STRING. It is
                    optional. By default, it is .
 -v | --version     print service version. It is of type BOOL.
 -n | --command     For a given command. It is of type STRING. It is
                    optional. By default, it is .
 -l | --product     For a given product version. It is of type
                    STRING. It is optional. By default, it is .
 -y | --end-time    Till end time. It is of type STRING. It is
                    optional. By default, it is .
 -s | --long        whether to print all attributes or only short
                    attributes. It is of type BOOL.
 -D | --context     command context. It is of type MAP. It is
                    optional.
 -c | --profile     For a given profile. It is of type STRING. It is
                    optional. By default, it is .


Results::

 request-id     Request id and is of type STRING.
 execution-id   Execution id and is of type STRING.
 product        Product and is of type STRING.
 service        service and is of type STRING.
 command        command and is of type STRING.
 profile        Profile and is of type STRING.
 status         Execution status and is of type STRING.
 start-time     Command execution starting Time and is of type
                STRING.
 end-time       Command execution finishing Time and is of type
                STRING.
 input          Input and is of type STRING.
 output         Output and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[5] execution-show
-----------------------------------------------



usage: oclip execution-show

Show the complete executions for the given request id

Product: open-cli
Service: execution
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-l | --execution-id] [-D | --context] [-s | --long]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-d | --debug] [-v | --version]

where::

 -l | --execution-id  Execution id. It is of type STRING. By default,
                      it is .
 -D | --context       command context. It is of type MAP. It is
                      optional.
 -s | --long          whether to print all attributes or only short
                      attributes. It is of type BOOL.
 -h | --help          print help message. It is of type BOOL.
 -f | --format        Output formats, supported formats such as table,
                      csv, json, yaml. It is of type STRING.
 -t | --no-title      whether to print title or not. It is of type BOOL.
 -d | --debug         Enable debug output. It is of type BOOL.
 -v | --version       print service version. It is of type BOOL.


Results::

 request-id   Request id and is of type STRING.
 product      Product and is of type STRING.
 service      service and is of type STRING.
 command      command and is of type STRING.
 profile      Profile and is of type STRING.
 input        Input and is of type STRING.
 status       Execution status and is of type STRING.
 start-time   Command execution starting Time and is of type
              STRING.
 end-time     Command execution finishing Time and is of type
              STRING.
 output       Output and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[6] product-list
-----------------------------------------------



usage: oclip product-list

List available products registered in OCLIP

Product: open-cli
Service: product
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-D | --context] [-s | --long] [-h | --help]
 [-f | --format] [-t | --no-title] [-d | --debug]
 [-v | --version]

where::

 -D | --context   command context. It is of type MAP. It is
                  optional.
 -s | --long      whether to print all attributes or only short
                  attributes. It is of type BOOL.
 -h | --help      print help message. It is of type BOOL.
 -f | --format    Output formats, supported formats such as table,
                  csv, json, yaml. It is of type STRING.
 -t | --no-title  whether to print title or not. It is of type BOOL.
 -d | --debug     Enable debug output. It is of type BOOL.
 -v | --version   print service version. It is of type BOOL.


Results::

 product      Product name and is of type STRING.
 description  Product description and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>


[8] schema-list
-----------------------------------------------



usage: oclip schema-list

OCLIP command to list available schema

Product: open-cli
Service: schema
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-l | --product] [-D | --context] [-s | --long]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-d | --debug] [-v | --version] [-n | --service]

where::

 -l | --product   For a given product version. It is of type
                  STRING. By default, it is .
 -D | --context   command context. It is of type MAP. It is
                  optional.
 -s | --long      whether to print all attributes or only short
                  attributes. It is of type BOOL.
 -h | --help      print help message. It is of type BOOL.
 -f | --format    Output formats, supported formats such as table,
                  csv, json, yaml. It is of type STRING.
 -t | --no-title  whether to print title or not. It is of type BOOL.
 -d | --debug     Enable debug output. It is of type BOOL.
 -v | --version   print service version. It is of type BOOL.
 -n | --service   For a given service in a product. It is of type
                  STRING. It is optional. By default, it is .


Results::

 command      Command name and is of type STRING.
 schema       Schema name and is of type STRING.
 service      Service name and is of type STRING.
 ocs-version  Schema version and is of type STRING.
 type         Command type and is of type STRING.
 enabled      Command is enabled or not and is of type STRING.
 rpc          Command is executed remotely and is of type
              STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[9] schema-refresh
-----------------------------------------------



usage: oclip schema-refresh

OCLIP command to refresh schemas stored in open-cli-schema folders.

Product: open-cli
Service: schema
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-D | --context] [-s | --long] [-h | --help]
 [-f | --format] [-t | --no-title] [-d | --debug]
 [-v | --version]

where::

 -D | --context   command context. It is of type MAP. It is
                  optional.
 -s | --long      whether to print all attributes or only short
                  attributes. It is of type BOOL.
 -h | --help      print help message. It is of type BOOL.
 -f | --format    Output formats, supported formats such as table,
                  csv, json, yaml. It is of type STRING.
 -t | --no-title  whether to print title or not. It is of type BOOL.
 -d | --debug     Enable debug output. It is of type BOOL.
 -v | --version   print service version. It is of type BOOL.


Results::

 command      Command name and is of type STRING.
 product      Command product version and is of type STRING.
 schema       Schema name and is of type STRING.
 ocs-version  Schema version and is of type STRING.
 type         Command type and is of type STRING.
 enabled      Command is enabled or not and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[10] schema-show
-----------------------------------------------



usage: oclip schema-show

OCLIP command to show available schema in JSON format

Product: open-cli
Service: schema
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-l | --product] [-D | --context] [-s | --long]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-d | --debug] [-x | --service] [-v | --version]
 [-n | --command]

where::

 -l | --product   For a given product version. It is of type
                  STRING. By default, it is .
 -D | --context   command context. It is of type MAP. It is
                  optional.
 -s | --long      whether to print all attributes or only short
                  attributes. It is of type BOOL.
 -h | --help      print help message. It is of type BOOL.
 -f | --format    Output formats, supported formats such as table,
                  csv, json, yaml. It is of type STRING.
 -t | --no-title  whether to print title or not. It is of type BOOL.
 -d | --debug     Enable debug output. It is of type BOOL.
 -x | --service   For a given service. It is of type STRING. It is
                  optional. By default, it is .
 -v | --version   print service version. It is of type BOOL.
 -n | --command   Schema details to fetch. It is of type STRING. By
                  default, it is .


Results::

 schema   Scheam json and is of type JSON.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[11] schema-switch
-----------------------------------------------



usage: oclip schema-switch

OCLIP command to switch the given command enable/disable

Product: open-cli
Service: schema
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-l | --name] [-i | --product] [-D | --context]
 [-s | --long] [-h | --help] [-f | --format]
 [-t | --no-title] [-d | --debug] [-v | --version]

where::

 -l | --name      Command name. It is of type STRING. By default,
                  it is .
 -i | --product   Product name. It is of type STRING. By default,
                  it is .
 -D | --context   command context. It is of type MAP. It is
                  optional.
 -s | --long      whether to print all attributes or only short
                  attributes. It is of type BOOL.
 -h | --help      print help message. It is of type BOOL.
 -f | --format    Output formats, supported formats such as table,
                  csv, json, yaml. It is of type STRING.
 -t | --no-title  whether to print title or not. It is of type BOOL.
 -d | --debug     Enable debug output. It is of type BOOL.
 -v | --version   print service version. It is of type BOOL.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[12] schema-validate
-----------------------------------------------



usage: oclip schema-validate

OCLIP command to validate schema

Product: open-cli
Service: schema
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-D | --context] [-s | --long] [-h | --help]
 [-b | --ocs-version] [-f | --format] [-t | --no-title]
 [-d | --debug] [-v | --version] [-l | --schema-location]
 [-i | --internal-schema]

where::

 -D | --context           command context. It is of type MAP. It is
                          optional.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -h | --help              print help message. It is of type BOOL.
 -b | --ocs-version       OCS version. It is of type STRING. It is optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -l | --schema-location   Schema file location. It is of type URL. By
                          default, it is .
 -i | --internal-schema   Validate existing schema file. It is of type BOOL.


Results::

 error  Schema validation error and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[13] service-list
-----------------------------------------------



usage: oclip service-list

List the services in given product registered in OCLIP

Product: open-cli
Service: product
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-l | --product] [-D | --context] [-s | --long]
 [-h | --help] [-f | --format] [-t | --no-title]
 [-d | --debug] [-v | --version]

where::

 -l | --product   For a given product version. It is of type
                  STRING. By default, it is .
 -D | --context   command context. It is of type MAP. It is
                  optional.
 -s | --long      whether to print all attributes or only short
                  attributes. It is of type BOOL.
 -h | --help      print help message. It is of type BOOL.
 -f | --format    Output formats, supported formats such as table,
                  csv, json, yaml. It is of type STRING.
 -t | --no-title  whether to print title or not. It is of type BOOL.
 -d | --debug     Enable debug output. It is of type BOOL.
 -v | --version   print service version. It is of type BOOL.


Results::

 service      Service name and is of type STRING.
 description  Product description and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>

