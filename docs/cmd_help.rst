.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.


.. toctree::
   :maxdepth: 2


.. _cli_cmd_help:

open-cli
==========================


[1] basic-logout
-----------------------------------------------


usage: oclip basic-logout

basic logout auth command

Product: open-cli
Service: basic-auth
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-m | --host-url]

where::

 -h | --help      print help message. It is of type STRING. By
                  default, it is false.
 -v | --version   print service version. It is of type STRING. By
                  default, it is false.
 -d | --debug     Enable debug output. It is of type BOOL. By
                  default, it is false.
 -f | --format    Output formats, supported formats such as table,
                  csv, json, yaml. It is of type STRING. By
                  default, it is table.
 -s | --long      whether to print all attributes or only short
                  attributes. It is of type BOOL. By default, it is
                  false.
 -t | --no-title  whether to print title or not. It is of type
                  BOOL. By default, it is false.
 -m | --host-url  host url in http(s). It is of type URL. By
                  default, it is read from environment variable
                  OPEN_CLI_HOST_URL.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[2] catalog
-----------------------------------------------


usage: oclip catalog

cli catalog command to find the base path for service.

Product: open-cli
Service: catalog
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-l | --catalog-service-name] [-i | --catalog-service-version] [-m | --host-url]

where::

 -h | --help                      print help message. It is of type STRING. By
                                  default, it is false.
 -v | --version                   print service version. It is of type STRING. By
                                  default, it is false.
 -d | --debug                     Enable debug output. It is of type BOOL. By
                                  default, it is false.
 -f | --format                    Output formats, supported formats such as table,
                                  csv, json, yaml. It is of type STRING. By
                                  default, it is table.
 -s | --long                      whether to print all attributes or only short
                                  attributes. It is of type BOOL. By default, it is
                                  false.
 -t | --no-title                  whether to print title or not. It is of type
                                  BOOL. By default, it is false.
 -l | --catalog-service-name      service name registered in catalog service. It is
                                  of type STRING.
 -i | --catalog-service-version   service version registered in catalog service. It
                                  is of type STRING.
 -m | --host-url                  host url in http(s). It is of type URL. By
                                  default, it is read from environment variable
                                  OPEN_CLI_HOST_URL.


Results::

 catalog-service-host-url   Service connection url and is of type STRING.
 catalog-service-base-path  service base path, to append with host-url for
                            connecting the service. and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[3] schema-refresh
-----------------------------------------------


usage: oclip schema-refresh

Oclip command to refresh schemas stored in open-cli-schema folders.

Product: open-cli
Service: schema
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]

where::

 -h | --help      print help message. It is of type STRING. By
                  default, it is false.
 -v | --version   print service version. It is of type STRING. By
                  default, it is false.
 -d | --debug     Enable debug output. It is of type BOOL. By
                  default, it is false.
 -f | --format    Output formats, supported formats such as table,
                  csv, json, yaml. It is of type STRING. By
                  default, it is table.
 -s | --long      whether to print all attributes or only short
                  attributes. It is of type BOOL. By default, it is
                  false.
 -t | --no-title  whether to print title or not. It is of type
                  BOOL. By default, it is false.


Results::

 sr.no        Serial Number and is of type STRING.
 command      Command name and is of type STRING.
 product      Command product version and is of type STRING.
 schema       Schema name and is of type STRING.
 ocs-version  Schema version and is of type STRING.
 type         Command type and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[4] schema-validate
-----------------------------------------------


usage: oclip schema-validate

Oclip command to validate schema

Product: open-cli
Service: schema
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-l | --schema-location] [-i | --internal-schema] [-b | --ocs-version]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -l | --schema-location   Schema file location. It is of type URL.
 -i | --internal-schema   Validate existing schema file. It is of type
                          BOOL. By default, it is false.
 -b | --ocs-version       OCS version. It is of type STRING. It is
                          optional. By default, it is 1.0.


Results::

 sl-no  Serial Number of error and is of type STRING.
 error  Schema validation error and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[5] basic-login
-----------------------------------------------


usage: oclip basic-login

basic login auth command

Product: open-cli
Service: basic-auth
Author: Kanagaraj Manickam kanagaraj.manickam@huawei.com

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 Authorization  Authorization and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



onap-amsterdam
==========================


[1] catalog
-----------------------------------------------


usage: oclip catalog

ONAP catalog command to find the base path for service.

Product: onap-amsterdam
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-l | --catalog-service-name] [-i | --catalog-service-version] [-m | --host-url]

where::

 -h | --help                      print help message. It is of type STRING. By
                                  default, it is false.
 -v | --version                   print service version. It is of type STRING. By
                                  default, it is false.
 -d | --debug                     Enable debug output. It is of type BOOL. By
                                  default, it is false.
 -f | --format                    Output formats, supported formats such as table,
                                  csv, json, yaml. It is of type STRING. By
                                  default, it is table.
 -s | --long                      whether to print all attributes or only short
                                  attributes. It is of type BOOL. By default, it is
                                  false.
 -t | --no-title                  whether to print title or not. It is of type
                                  BOOL. By default, it is false.
 -l | --catalog-service-name      service name registered in catalog service. It is
                                  of type STRING.
 -i | --catalog-service-version   service version registered in catalog service. It
                                  is of type STRING.
 -m | --host-url                  host url in http(s). It is of type URL. By
                                  default, it is read from environment variable
                                  OPEN_CLI_HOST_URL.


Results::

 catalog-service-host-url   Service connection url and is of type STRING.
 catalog-service-base-path  service base path, to append with host-url for
                            connecting the service. and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[2] sdc-basic-login
-----------------------------------------------


usage: oclip sdc-basic-login

ONAP basic login auth command

Product: onap-amsterdam
Service: sdc-basic-auth
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 Authorization    Authorization and is of type STRING.
 X-TransactionId  X-TransactionId and is of type STRING.
 X-FromAppId      X-FromAppId and is of type STRING.
 USER_ID          USER_ID for sdc and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[3] basic-login
-----------------------------------------------


usage: oclip basic-login

ONAP basic login auth command

Product: onap-amsterdam
Service: basic-auth
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 Authorization    Authorization and is of type STRING.
 X-TransactionId  X-TransactionId and is of type STRING.
 X-FromAppId      X-FromAppId and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[4] basic-logout
-----------------------------------------------


usage: oclip basic-logout

ONAP basic logout auth command

Product: onap-amsterdam
Service: basic-auth
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-m | --host-url]

where::

 -h | --help      print help message. It is of type STRING. By
                  default, it is false.
 -v | --version   print service version. It is of type STRING. By
                  default, it is false.
 -d | --debug     Enable debug output. It is of type BOOL. By
                  default, it is false.
 -f | --format    Output formats, supported formats such as table,
                  csv, json, yaml. It is of type STRING. By
                  default, it is table.
 -s | --long      whether to print all attributes or only short
                  attributes. It is of type BOOL. By default, it is
                  false.
 -t | --no-title  whether to print title or not. It is of type
                  BOOL. By default, it is false.
 -m | --host-url  host url in http(s). It is of type URL. By
                  default, it is read from environment variable
                  OPEN_CLI_HOST_URL.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[5] service-delete
-----------------------------------------------


usage: oclip service-delete

Delete service instance (experimental)

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --service-instace-id] [-b | --model-invariant-id] [-i | --model-uuid]
 [-e | --model-name] [-g | --model-version] [-c | --customer-name]
 [-j | --cloud-region] [-k | --tenant-id] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help                print help message. It is of type STRING. By
                            default, it is false.
 -v | --version             print service version. It is of type STRING. By
                            default, it is false.
 -d | --debug               Enable debug output. It is of type BOOL. By
                            default, it is false.
 -f | --format              Output formats, supported formats such as table,
                            csv, json, yaml. It is of type STRING. By
                            default, it is table.
 -s | --long                whether to print all attributes or only short
                            attributes. It is of type BOOL. By default, it is
                            false.
 -t | --no-title            whether to print title or not. It is of type
                            BOOL. By default, it is false.
 -x | --service-instace-id  unique id for service instance. It is of type
                            STRING.
 -b | --model-invariant-id  model invariant id for service in SDC catalog. It
                            is of type STRING.
 -i | --model-uuid          model uuid for service in SDC catalog. It is of
                            type STRING.
 -e | --model-name          model name available in SDC catalog. It is of
                            type STRING.
 -g | --model-version       model version of service (eg. 1.0). It is of type
                            STRING.
 -c | --customer-name       unique id for customer. It is of type STRING.
 -j | --cloud-region        cloud region id. It is of type STRING.
 -k | --tenant-id           tenant id. It is of type STRING.
 -u | --host-username       Host user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_CLI_HOST_USERNAME.
 -p | --host-password       Host user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url            host url in http(s). It is of type URL. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_URL.
 -a | --no-auth             whether to authenticate user or not. It is of
                            type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[6] vf-module-create
-----------------------------------------------


usage: oclip vf-module-create

Create a VF Module

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-l | --lcp-cloudregion-id] [-w | --tenant-id] [-y | --vnf-instace-id]
 [-x | --service-instace-id] [-z | --parenet-service-model] [-r | --supress-rollback]
 [-i | --instance-name] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help                    print help message. It is of type STRING. By
                                default, it is false.
 -v | --version                 print service version. It is of type STRING. By
                                default, it is false.
 -d | --debug                   Enable debug output. It is of type BOOL. By
                                default, it is false.
 -f | --format                  Output formats, supported formats such as table,
                                csv, json, yaml. It is of type STRING. By
                                default, it is table.
 -s | --long                    whether to print all attributes or only short
                                attributes. It is of type BOOL. By default, it is
                                false.
 -t | --no-title                whether to print title or not. It is of type
                                BOOL. By default, it is false.
 -l | --lcp-cloudregion-id      AIC LCP node location identifier. It is of type
                                STRING.
 -w | --tenant-id               openstack tenant id (uuid). It is of type STRING.
 -y | --vnf-instace-id          vnf instance id. It is of type STRING.
 -x | --service-instace-id      unique id for service instance. It is of type
                                STRING.
 -z | --parenet-service-model   parent service model name. It is of type STRING.
 -r | --supress-rollback        rollback changes if instantiation fails. It is of
                                type BOOL. It is optional. By default, it is
                                false.
 -i | --instance-name           service instance name. It is of type STRING.
 -u | --host-username           Host user name. It is of type STRING. By default,
                                it is read from environment variable
                                OPEN_CLI_HOST_USERNAME.
 -p | --host-password           Host user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                host url in http(s). It is of type URL. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_URL.
 -a | --no-auth                 whether to authenticate user or not. It is of
                                type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[7] vfmodule-delete
-----------------------------------------------


usage: oclip vfmodule-delete

delete a VF module (experimental)

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vfmodule-id] [-j | --service-id] [-y | --vf-id]
 [-k | --vf-model-invariant-id] [-e | --vfmodule-name] [-g | --vfmodule-version]
 [-l | --cloud-region] [-z | --tenant-id] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help                    print help message. It is of type STRING. By
                                default, it is false.
 -v | --version                 print service version. It is of type STRING. By
                                default, it is false.
 -d | --debug                   Enable debug output. It is of type BOOL. By
                                default, it is false.
 -f | --format                  Output formats, supported formats such as table,
                                csv, json, yaml. It is of type STRING. By
                                default, it is table.
 -s | --long                    whether to print all attributes or only short
                                attributes. It is of type BOOL. By default, it is
                                false.
 -t | --no-title                whether to print title or not. It is of type
                                BOOL. By default, it is false.
 -x | --vfmodule-id             VF module Id. It is of type STRING.
 -j | --service-id              unique id for service. It is of type STRING.
 -y | --vf-id                   unique id for related VF. It is of type STRING.
 -k | --vf-model-invariant-id   vf model invariant id. It is of type STRING.
 -e | --vfmodule-name           vfmodule model name. It is of type STRING.
 -g | --vfmodule-version        vf module version. It is of type STRING.
 -l | --cloud-region            cloud region identifier. It is of type STRING.
 -z | --tenant-id               openstack tenant id. It is of type STRING.
 -u | --host-username           Host user name. It is of type STRING. By default,
                                it is read from environment variable
                                OPEN_CLI_HOST_USERNAME.
 -p | --host-password           Host user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                host url in http(s). It is of type URL. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_URL.
 -a | --no-auth                 whether to authenticate user or not. It is of
                                type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[8] vfmodule-create
-----------------------------------------------


usage: oclip vfmodule-create

Create a VF module

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-l | --cloud-region] [-B | --tenant-id] [-b | --vfmodule-invariant-id]
 [-c | --vfmodule-uuid] [-e | --vfmodule-name] [-g | --vfmodule-version]
 [-i | --module-name] [-j | --service-id] [-y | --vf-id]
 [-k | --vf-model-invariant-id] [-A | --vf-model-uuid] [-n | --vf-model-name]
 [-o | --vf-model-version] [-q | --vf-model-customization-id] [-r | --service-model-invariant-id]
 [-w | --service-model-uuid] [-x | --service-model-name] [-z | --service-model-version]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help                        print help message. It is of type STRING. By
                                    default, it is false.
 -v | --version                     print service version. It is of type STRING. By
                                    default, it is false.
 -d | --debug                       Enable debug output. It is of type BOOL. By
                                    default, it is false.
 -f | --format                      Output formats, supported formats such as table,
                                    csv, json, yaml. It is of type STRING. By
                                    default, it is table.
 -s | --long                        whether to print all attributes or only short
                                    attributes. It is of type BOOL. By default, it is
                                    false.
 -t | --no-title                    whether to print title or not. It is of type
                                    BOOL. By default, it is false.
 -l | --cloud-region                cloud region identifier. It is of type STRING.
 -B | --tenant-id                   openstack tenant id. It is of type STRING.
 -b | --vfmodule-invariant-id       vfmodule model invariant id. It is of type STRING.
 -c | --vfmodule-uuid               model uuid for vfmodule. It is of type STRING.
 -e | --vfmodule-name               vfmodule model name. It is of type STRING.
 -g | --vfmodule-version            vf module version. It is of type STRING.
 -i | --module-name                 vfmodule name. It is of type STRING.
 -j | --service-id                  unique id for service. It is of type STRING.
 -y | --vf-id                       unique id for related VF. It is of type STRING.
 -k | --vf-model-invariant-id       vf model invariant id. It is of type STRING.
 -A | --vf-model-uuid               model uuid for vf. It is of type STRING.
 -n | --vf-model-name               vf model name. It is of type STRING.
 -o | --vf-model-version            vf model version. It is of type STRING.
 -q | --vf-model-customization-id   vf model customization id. This can be obtained
                                    using SDC portal (Home -> service:xxx ->
                                    Composition -> click on VF for customization id).
                                    It is of type STRING.
 -r | --service-model-invariant-id  model invariant id. It is of type STRING.
 -w | --service-model-uuid          model name version id. It is of type STRING.
 -x | --service-model-name          service model name. It is of type STRING.
 -z | --service-model-version       service model version. It is of type STRING.
 -u | --host-username               Host user name. It is of type STRING. By default,
                                    it is read from environment variable
                                    OPEN_CLI_HOST_USERNAME.
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -a | --no-auth                     whether to authenticate user or not. It is of
                                    type BOOL. By default, it is false.


Results::

 vfmodule-id  id for the created vnf and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[9] vf-create
-----------------------------------------------


usage: oclip vf-create

Create a VF

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-l | --cloud-region] [-z | --tenant-id] [-w | --product-family]
 [-o | --instance-name] [-y | --service-instance-id] [-b | --vf-model-invariant-id]
 [-c | --vf-model-uuid] [-e | --vf-model-name] [-g | --vf-model-version]
 [-i | --vf-model-customization-id] [-j | --service-model-invariant-id] [-k | --service-model-uuid]
 [-q | --service-model-name] [-n | --service-model-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help                        print help message. It is of type STRING. By
                                    default, it is false.
 -v | --version                     print service version. It is of type STRING. By
                                    default, it is false.
 -d | --debug                       Enable debug output. It is of type BOOL. By
                                    default, it is false.
 -f | --format                      Output formats, supported formats such as table,
                                    csv, json, yaml. It is of type STRING. By
                                    default, it is table.
 -s | --long                        whether to print all attributes or only short
                                    attributes. It is of type BOOL. By default, it is
                                    false.
 -t | --no-title                    whether to print title or not. It is of type
                                    BOOL. By default, it is false.
 -l | --cloud-region                cloud region identifier. It is of type STRING.
 -z | --tenant-id                   openstack tenant id. It is of type STRING.
 -w | --product-family              service type for serivce (e.g. vLB). It is of
                                    type STRING.
 -o | --instance-name               service instance name. It is of type STRING.
 -y | --service-instance-id         unique id for service instance. It is of type
                                    STRING.
 -b | --vf-model-invariant-id       vf model invariant id. It is of type STRING.
 -c | --vf-model-uuid               model uuid for vf. It is of type STRING.
 -e | --vf-model-name               vf model name. It is of type STRING.
 -g | --vf-model-version            vf model version. It is of type STRING.
 -i | --vf-model-customization-id   vf model customization id. It is of type STRING.
 -j | --service-model-invariant-id  model invariant id. It is of type STRING.
 -k | --service-model-uuid          model name version id. It is of type STRING.
 -q | --service-model-name          service model name. It is of type STRING.
 -n | --service-model-version       service model version. It is of type STRING.
 -u | --host-username               Host user name. It is of type STRING. By default,
                                    it is read from environment variable
                                    OPEN_CLI_HOST_USERNAME.
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -a | --no-auth                     whether to authenticate user or not. It is of
                                    type BOOL. By default, it is false.


Results::

 vf-id  id for the created vnf and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[10] service-create
-----------------------------------------------


usage: oclip service-create

Create a service instance using MSO

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-l | --cloud-region] [-b | --tenant-id] [-e | --model-invariant-id]
 [-g | --model-uuid] [-i | --model-name] [-j | --model-version]
 [-c | --customer] [-k | --instance-name] [-r | --supress-rollback]
 [-w | --service-type] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help                print help message. It is of type STRING. By
                            default, it is false.
 -v | --version             print service version. It is of type STRING. By
                            default, it is false.
 -d | --debug               Enable debug output. It is of type BOOL. By
                            default, it is false.
 -f | --format              Output formats, supported formats such as table,
                            csv, json, yaml. It is of type STRING. By
                            default, it is table.
 -s | --long                whether to print all attributes or only short
                            attributes. It is of type BOOL. By default, it is
                            false.
 -t | --no-title            whether to print title or not. It is of type
                            BOOL. By default, it is false.
 -l | --cloud-region        cloud region identifier. It is of type STRING.
 -b | --tenant-id           tenant id. It is of type STRING.
 -e | --model-invariant-id  model invariant id generated by ASDC. It is of
                            type STRING.
 -g | --model-uuid          model uuid generated by ASDC. It is of type
                            STRING.
 -i | --model-name          model name as provided in ASDC design time. It is
                            of type STRING.
 -j | --model-version       model-version. It is of type STRING.
 -c | --customer            unique id for customer. It is of type STRING.
 -k | --instance-name       service instance name. It is of type STRING.
 -r | --supress-rollback    rollback changes if instantiation fails. It is of
                            type BOOL. It is optional. By default, it is
                            false.
 -w | --service-type        subscription service type. It is of type STRING.
 -u | --host-username       Host user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_CLI_HOST_USERNAME.
 -p | --host-password       Host user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url            host url in http(s). It is of type URL. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_URL.
 -a | --no-auth             whether to authenticate user or not. It is of
                            type BOOL. By default, it is false.


Results::

 service-id   instance id for the created service. and is of
              type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[11] vf-delete
-----------------------------------------------


usage: oclip vf-delete

delete a VF (experimental)

Product: onap-amsterdam
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vf-id] [-y | --service-instance-id] [-l | --cloud-region]
 [-z | --tenant-id] [-b | --model-invariant-id] [-c | --model-uuid]
 [-e | --model-name] [-g | --model-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help                  print help message. It is of type STRING. By
                              default, it is false.
 -v | --version               print service version. It is of type STRING. By
                              default, it is false.
 -d | --debug                 Enable debug output. It is of type BOOL. By
                              default, it is false.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING. By
                              default, it is table.
 -s | --long                  whether to print all attributes or only short
                              attributes. It is of type BOOL. By default, it is
                              false.
 -t | --no-title              whether to print title or not. It is of type
                              BOOL. By default, it is false.
 -x | --vf-id                 id for vnf. It is of type STRING.
 -y | --service-instance-id   unique id for service instance. It is of type
                              STRING.
 -l | --cloud-region          Cloud region ID. It is of type STRING.
 -z | --tenant-id             openstack tenant id (uuid). It is of type STRING.
 -b | --model-invariant-id    model invariant id for service in SDC catalog. It
                              is of type STRING.
 -c | --model-uuid            model uuid for service in SDC catalog. It is of
                              type STRING.
 -e | --model-name            model name available in SDC catalog. It is of
                              type STRING.
 -g | --model-version         model version of service (eg. 1.0). It is of type
                              STRING.
 -u | --host-username         Host user name. It is of type STRING. By default,
                              it is read from environment variable
                              OPEN_CLI_HOST_USERNAME.
 -p | --host-password         Host user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url              host url in http(s). It is of type URL. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_URL.
 -a | --no-auth               whether to authenticate user or not. It is of
                              type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[12] service-model-add-vf
-----------------------------------------------


usage: oclip service-model-add-vf

Helps to add VF into service models in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --service-model-id] [-y | --vf-id] [-z | --vf-name]
 [-b | --vf-version] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --service-model-id  Onap Service Name. It is of type STRING.
 -y | --vf-id             VF ID. It is of type STRING. It is optional.
 -z | --vf-name           VF ID. It is of type STRING. It is optional.
 -b | --vf-version        VF version. It is of type STRING. It is optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Results::

 ID   Service ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[13] vsp-revert
-----------------------------------------------


usage: oclip vsp-revert

Revert Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vsp-id] [-y | --vsp-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[14] vsp-validate
-----------------------------------------------


usage: oclip vsp-validate

Validated the uploaded Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vsp-id] [-y | --vsp-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 status   Validation status and is of type STRING.
 errors   Validation messages and is of type JSON.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[15] service-model-approve
-----------------------------------------------


usage: oclip service-model-approve

Approves the Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --service-model-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[16] service-model-certify-start
-----------------------------------------------


usage: oclip service-model-certify-start

Starts the certification of Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --service-model-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[17] service-model-checkin
-----------------------------------------------


usage: oclip service-model-checkin

Checkin Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --service-model-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[18] service-model-create
-----------------------------------------------


usage: oclip service-model-create

Create Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --name] [-y | --description] [-z | --project-code]
 [-b | --category] [-c | --category-display-name] [-e | --icon-id]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help                    print help message. It is of type STRING. By
                                default, it is false.
 -v | --version                 print service version. It is of type STRING. By
                                default, it is false.
 -d | --debug                   Enable debug output. It is of type BOOL. By
                                default, it is false.
 -f | --format                  Output formats, supported formats such as table,
                                csv, json, yaml. It is of type STRING. By
                                default, it is table.
 -s | --long                    whether to print all attributes or only short
                                attributes. It is of type BOOL. By default, it is
                                false.
 -t | --no-title                whether to print title or not. It is of type
                                BOOL. By default, it is false.
 -x | --name                    Onap Service Name. It is of type STRING.
 -y | --description             Description for Service. It is of type STRING. It
                                is optional.
 -z | --project-code            Project code. It is of type STRING. It is
                                optional.
 -b | --category                Service category. It is of type STRING. It is
                                optional. By default, it is network l1-3.
 -c | --category-display-name   Service category display name. It is of type
                                STRING. It is optional. By default, it is Network
                                L1-3.
 -e | --icon-id                 Service Icon id. It is of type STRING. It is
                                optional. By default, it is defaulticon.
 -u | --host-username           Host user name. It is of type STRING. By default,
                                it is read from environment variable
                                OPEN_CLI_HOST_USERNAME.
 -p | --host-password           Host user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                host url in http(s). It is of type URL. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_URL.
 -a | --no-auth                 whether to authenticate user or not. It is of
                                type BOOL. By default, it is false.


Results::

 ID   Service ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[19] service-model-checkout
-----------------------------------------------


usage: oclip service-model-checkout

Checkout Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --service-model-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[20] service-model-certify-request
-----------------------------------------------


usage: oclip service-model-certify-request

Request the certification of Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --service-model-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[21] service-model-revert
-----------------------------------------------


usage: oclip service-model-revert

Checkout Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --service-model-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[22] service-model-distribute
-----------------------------------------------


usage: oclip service-model-distribute

Distributes the Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --service-model-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[23] service-model-list
-----------------------------------------------


usage: oclip service-model-list

List the service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 uuid                 UUID and is of type STRING.
 invariant-uuid       Invariant UUID and is of type STRING.
 name                 Name and is of type STRING.
 version              version and is of type STRING.
 status               status and is of type STRING.
 distribution-status  status and is of type STRING.
 description          description and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[24] service2vf-model-list
-----------------------------------------------


usage: oclip service2vf-model-list

List the VF in a given service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-z | --service-model-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -z | --service-model-id  Service model uuid. It is of type STRING.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Results::

 vf-uuid                UUID and is of type STRING.
 vf-name                name and is of type STRING.
 vf-customization-uuid  customization UUID and is of type STRING.
 vf-version             version and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[25] vf-model-certify-complete
-----------------------------------------------


usage: oclip vf-model-certify-complete

Complete certifying Virtual function

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --vf-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -b | --vf-id           VF ID. It is of type STRING. It is optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[26] vf-model-certify-request
-----------------------------------------------


usage: oclip vf-model-certify-request

Request for certifying Virtual function

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --vf-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -b | --vf-id           VF ID. It is of type STRING. It is optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[27] vf-model-certify-start
-----------------------------------------------


usage: oclip vf-model-certify-start

Start certifying Virtual function

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --vf-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -b | --vf-id           VF ID. It is of type STRING. It is optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[28] vf-model-checkin
-----------------------------------------------


usage: oclip vf-model-checkin

Checkin Virtual function

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --vf-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -b | --vf-id           VF ID. It is of type STRING. It is optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[29] vf-model-create
-----------------------------------------------


usage: oclip vf-model-create

Create Virtual function from Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --name] [-y | --description] [-z | --vendor-name]
 [-b | --vsp-id] [-g | --vsp-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --name            Onap VF Name. It is of type STRING.
 -y | --description     Description for VF. It is of type STRING. It is
                        optional.
 -z | --vendor-name     Vendor name. It is of type STRING. It is optional.
 -b | --vsp-id          VSP ID. It is of type STRING. It is optional.
 -g | --vsp-version     VSP version. It is of type STRING. It is optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 ID   VF ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[30] vf-model-list
-----------------------------------------------


usage: oclip vf-model-list

List the VF resource model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


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

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[31] vf2vfmodule-model-list
-----------------------------------------------


usage: oclip vf2vfmodule-model-list

List the VF modules in a given VF model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-z | --vf-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -z | --vf-id           VF uuid. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 vsp-uuid               VSP uuid and is of type STRING.
 vsp-version            VSP version and is of type STRING.
 module-uuid            UUID and is of type STRING.
 module-invariant-uuid  Invariant UUID and is of type STRING.
 module-name            name and is of type STRING.
 module-version         version and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[32] ep-create
-----------------------------------------------


usage: oclip ep-create

Create Entitlement Pool

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --ep-name] [-y | --license-model-id] [-z | --ep-description]
 [-q | --threshold-value] [-b | --threshold-unit] [-c | --entitlement-metric]
 [-e | --aggregation-function] [-g | --operational-scope] [-k | --reference-number]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help                  print help message. It is of type STRING. By
                              default, it is false.
 -v | --version               print service version. It is of type STRING. By
                              default, it is false.
 -d | --debug                 Enable debug output. It is of type BOOL. By
                              default, it is false.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING. By
                              default, it is table.
 -s | --long                  whether to print all attributes or only short
                              attributes. It is of type BOOL. By default, it is
                              false.
 -t | --no-title              whether to print title or not. It is of type
                              BOOL. By default, it is false.
 -x | --ep-name               Entitlement Pool name. It is of type STRING.
 -y | --license-model-id      License Model ID. It is of type STRING.
 -z | --ep-description        Description for Entitlement Pool. It is of type
                              STRING.
 -q | --threshold-value       threshold. It is of type STRING.
 -b | --threshold-unit        threshold. It is of type STRING.
 -c | --entitlement-metric    Entitlement Metric. It is of type STRING.
 -e | --aggregation-function  Aggregation Function. It is of type STRING.
 -g | --operational-scope     Operational Scope. It is of type STRING.
 -k | --reference-number      Reference Number. It is of type STRING.
 -u | --host-username         Host user name. It is of type STRING. By default,
                              it is read from environment variable
                              OPEN_CLI_HOST_USERNAME.
 -p | --host-password         Host user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url              host url in http(s). It is of type URL. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_URL.
 -a | --no-auth               whether to authenticate user or not. It is of
                              type BOOL. By default, it is false.


Results::

 ID   Entitlement Pool ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[33] ep-show
-----------------------------------------------


usage: oclip ep-show

Details of the Entitlement Pool

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --license-model-id] [-y | --pool-id] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --license-model-id  Onap License Model ID. It is of type STRING.
 -y | --pool-id           Onap Entitlement Pool ID. It is of type STRING.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Results::

 name         Entitlement Pool Name and is of type STRING.
 ID           Entitlement Pool ID and is of type STRING.
 description  Description for the Entitlement Pool and is of
              type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[34] license-group-create
-----------------------------------------------


usage: oclip license-group-create

Create License Group

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --group-name] [-y | --license-model-id] [-z | --license-group-description]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help                        print help message. It is of type STRING. By
                                    default, it is false.
 -v | --version                     print service version. It is of type STRING. By
                                    default, it is false.
 -d | --debug                       Enable debug output. It is of type BOOL. By
                                    default, it is false.
 -f | --format                      Output formats, supported formats such as table,
                                    csv, json, yaml. It is of type STRING. By
                                    default, it is table.
 -s | --long                        whether to print all attributes or only short
                                    attributes. It is of type BOOL. By default, it is
                                    false.
 -t | --no-title                    whether to print title or not. It is of type
                                    BOOL. By default, it is false.
 -x | --group-name                  group name. It is of type STRING.
 -y | --license-model-id            License Model ID. It is of type STRING.
 -z | --license-group-description   Description for License Group. It is of type
                                    STRING. It is optional.
 -u | --host-username               Host user name. It is of type STRING. By default,
                                    it is read from environment variable
                                    OPEN_CLI_HOST_USERNAME.
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -a | --no-auth                     whether to authenticate user or not. It is of
                                    type BOOL. By default, it is false.


Results::

 ID   License Group ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[35] license-group-show
-----------------------------------------------


usage: oclip license-group-show

Details of the License Group

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --license-model-id] [-y | --license-group-id] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --license-model-id  Onap License Model ID. It is of type STRING.
 -y | --license-group-id  Onap License Group ID. It is of type STRING.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Results::

 name         License Group Name and is of type STRING.
 ID           License Group ID and is of type STRING.
 description  Description for the License Group and is of type
              STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[36] license-model-create
-----------------------------------------------


usage: oclip license-model-create

Create License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vendor-name] [-y | --license-model-description] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help                        print help message. It is of type STRING. By
                                    default, it is false.
 -v | --version                     print service version. It is of type STRING. By
                                    default, it is false.
 -d | --debug                       Enable debug output. It is of type BOOL. By
                                    default, it is false.
 -f | --format                      Output formats, supported formats such as table,
                                    csv, json, yaml. It is of type STRING. By
                                    default, it is table.
 -s | --long                        whether to print all attributes or only short
                                    attributes. It is of type BOOL. By default, it is
                                    false.
 -t | --no-title                    whether to print title or not. It is of type
                                    BOOL. By default, it is false.
 -x | --vendor-name                 vendor name. It is of type STRING.
 -y | --license-model-description   Description for License Model. It is of type
                                    STRING. It is optional.
 -u | --host-username               Host user name. It is of type STRING. By default,
                                    it is read from environment variable
                                    OPEN_CLI_HOST_USERNAME.
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -a | --no-auth                     whether to authenticate user or not. It is of
                                    type BOOL. By default, it is false.


Results::

 ID   License Model ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[37] vlm-aggreement-create
-----------------------------------------------


usage: oclip vlm-aggreement-create

Create license aggreement

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --name] [-y | --vlm-id] [-e | --vlm-version]
 [-z | --description] [-g | --vlm-feature-group-id] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help                  print help message. It is of type STRING. By
                              default, it is false.
 -v | --version               print service version. It is of type STRING. By
                              default, it is false.
 -d | --debug                 Enable debug output. It is of type BOOL. By
                              default, it is false.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING. By
                              default, it is table.
 -s | --long                  whether to print all attributes or only short
                              attributes. It is of type BOOL. By default, it is
                              false.
 -t | --no-title              whether to print title or not. It is of type
                              BOOL. By default, it is false.
 -x | --name                  aggreement name. It is of type STRING.
 -y | --vlm-id                License Model ID. It is of type STRING.
 -e | --vlm-version           License Model version. It is of type STRING.
 -z | --description           Description for aggreement. It is of type STRING.
 -g | --vlm-feature-group-id  VLM feature group. It is of type STRING.
 -u | --host-username         Host user name. It is of type STRING. By default,
                              it is read from environment variable
                              OPEN_CLI_HOST_USERNAME.
 -p | --host-password         Host user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url              host url in http(s). It is of type URL. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_URL.
 -a | --no-auth               whether to authenticate user or not. It is of
                              type BOOL. By default, it is false.


Results::

 ID   aggreement ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[38] vlm-checkin
-----------------------------------------------


usage: oclip vlm-checkin

Checkin Vendor License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vlm-id] [-y | --vlm-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vlm-id          Onap VLM ID. It is of type STRING.
 -y | --vlm-version     VLM version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[39] vlm-create
-----------------------------------------------


usage: oclip vlm-create

Create License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vendor-name] [-y | --description] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vendor-name     vendor name. It is of type STRING.
 -y | --description     Description for License Model. It is of type
                        STRING. It is optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 ID   License Model ID and is of type UUID.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[40] vlm-entitlement-pool-list
-----------------------------------------------


usage: oclip vlm-entitlement-pool-list

entitlement pool list in a license model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vlm-id] [-y | --vlm-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vlm-id          License Model ID. It is of type STRING.
 -y | --vlm-version     License Model version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 ID     Feature group ID and is of type STRING.
 name   Feature group name and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[41] vlm-feature-group-create
-----------------------------------------------


usage: oclip vlm-feature-group-create

Create feature group Pool

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --name] [-y | --vlm-id] [-e | --vlm-version]
 [-z | --description] [-g | --vlm-key-group-id] [-b | --vlm-entitle-pool-id]
 [-c | --part-number] [-k | --manufacture-reference-number] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help                          print help message. It is of type STRING. By
                                      default, it is false.
 -v | --version                       print service version. It is of type STRING. By
                                      default, it is false.
 -d | --debug                         Enable debug output. It is of type BOOL. By
                                      default, it is false.
 -f | --format                        Output formats, supported formats such as table,
                                      csv, json, yaml. It is of type STRING. By
                                      default, it is table.
 -s | --long                          whether to print all attributes or only short
                                      attributes. It is of type BOOL. By default, it is
                                      false.
 -t | --no-title                      whether to print title or not. It is of type
                                      BOOL. By default, it is false.
 -x | --name                          Feature group name. It is of type STRING.
 -y | --vlm-id                        License Model ID. It is of type STRING.
 -e | --vlm-version                   License Model version. It is of type STRING.
 -z | --description                   Description for feature group. It is of type
                                      STRING.
 -g | --vlm-key-group-id              VLM keygroup. It is of type STRING.
 -b | --vlm-entitle-pool-id           VLM Entitlement pool. It is of type STRING.
 -c | --part-number                   Part number. It is of type STRING.
 -k | --manufacture-reference-number  Manufature Reference Number. It is of type STRING.
 -u | --host-username                 Host user name. It is of type STRING. By default,
                                      it is read from environment variable
                                      OPEN_CLI_HOST_USERNAME.
 -p | --host-password                 Host user password. It is of type STRING. By
                                      default, it is read from environment variable
                                      OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                      host url in http(s). It is of type URL. By
                                      default, it is read from environment variable
                                      OPEN_CLI_HOST_URL.
 -a | --no-auth                       whether to authenticate user or not. It is of
                                      type BOOL. By default, it is false.


Results::

 ID   Feature group ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[42] vlm-key-group-create
-----------------------------------------------


usage: oclip vlm-key-group-create

Create License Key Group

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-c | --vlm-id] [-e | --vlm-version] [-x | --name]
 [-y | --type] [-z | --description] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -c | --vlm-id          License Model Id. It is of type STRING.
 -e | --vlm-version     License Model version. It is of type STRING.
 -x | --name            name. It is of type STRING.
 -y | --type            type of group (Universal, unique, one-time). It
                        is of type STRING. By default, it is Universal.
 -z | --description     Description for License Key Group. It is of type
                        STRING. It is optional.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 ID   License Model ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[43] vlm-list
-----------------------------------------------


usage: oclip vlm-list

List License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 id           License ID and is of type UUID.
 vendor-name  Vendor  Name and is of type STRING.
 vlm-version  VLM version and is of type STRING.
 status       status and is of type STRING.
 description  License description and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[44] vlm-submit
-----------------------------------------------


usage: oclip vlm-submit

Submit Vendor License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vlm-id] [-y | --vlm-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vlm-id          Onap VLM ID. It is of type STRING.
 -y | --vlm-version     VLM version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[45] vsp-checkin
-----------------------------------------------


usage: oclip vsp-checkin

Checkin Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vsp-id] [-y | --vsp-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[46] vsp-create
-----------------------------------------------


usage: oclip vsp-create

Create Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vsp-name] [-y | --vsp-description] [-z | --vsp-category]
 [-b | --vsp-subcategory] [-c | --vlm-version] [-e | --vlm-vendor]
 [-g | --vlm-id] [-i | --vlm-agreement-id] [-j | --vlm-feature-group-id]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help                  print help message. It is of type STRING. By
                              default, it is false.
 -v | --version               print service version. It is of type STRING. By
                              default, it is false.
 -d | --debug                 Enable debug output. It is of type BOOL. By
                              default, it is false.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING. By
                              default, it is table.
 -s | --long                  whether to print all attributes or only short
                              attributes. It is of type BOOL. By default, it is
                              false.
 -t | --no-title              whether to print title or not. It is of type
                              BOOL. By default, it is false.
 -x | --vsp-name              Onap VSP Name. It is of type STRING.
 -y | --vsp-description       Description for VSP. It is of type STRING. It is
                              optional.
 -z | --vsp-category          Category of the VSP. It is of type STRING. It is
                              optional. By default, it is
                              resourceNewCategory.generic.
 -b | --vsp-subcategory       Sub Category of VSP. It is of type STRING. It is
                              optional. By default, it is
                              resourceNewCategory.generic.abstract.
 -c | --vlm-version           License version. It is of type STRING. It is
                              optional. By default, it is 1.0.
 -e | --vlm-vendor            License Model vendor. It is of type STRING.
 -g | --vlm-id                License Model ID. It is of type STRING.
 -i | --vlm-agreement-id      License Agreement ID. It is of type STRING.
 -j | --vlm-feature-group-id  Feature Group ID. It is of type STRING.
 -u | --host-username         Host user name. It is of type STRING. By default,
                              it is read from environment variable
                              OPEN_CLI_HOST_USERNAME.
 -p | --host-password         Host user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url              host url in http(s). It is of type URL. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_URL.
 -a | --no-auth               whether to authenticate user or not. It is of
                              type BOOL. By default, it is false.


Results::

 ID   VSP ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[47] vsp-package
-----------------------------------------------


usage: oclip vsp-package

Package Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vsp-id] [-y | --vsp-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[48] vsp-submit
-----------------------------------------------


usage: oclip vsp-submit

Submit Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vsp-id] [-y | --vsp-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[49] service-model-certify-complete
-----------------------------------------------


usage: oclip service-model-certify-complete

Completes the certification of Service model in SDC

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --service-model-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[50] license-model-show
-----------------------------------------------


usage: oclip license-model-show

Details of the License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --license-model-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --license-model-id  Onap License Model ID. It is of type STRING.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Results::

 name         License Model  Name and is of type STRING.
 ID           License Model ID and is of type STRING.
 description  Description for the License Model and is of type
              STRING.
 status       Status of the License Model and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[51] vlm-aggreement-list
-----------------------------------------------


usage: oclip vlm-aggreement-list

List license aggreement

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vlm-id] [-y | --vlm-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vlm-id          License Model ID. It is of type STRING.
 -y | --vlm-version     License Model version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 ID     aggreement ID and is of type STRING.
 name   aggreement name and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[52] vlm-checkout
-----------------------------------------------


usage: oclip vlm-checkout

Checkout Vendor License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vlm-id] [-y | --vlm-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vlm-id          Onap VLM ID. It is of type STRING.
 -y | --vlm-version     VLM version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[53] vlm-entitlement-pool-create
-----------------------------------------------


usage: oclip vlm-entitlement-pool-create

Create Entitlement Pool

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --name] [-y | --vlm-id] [-e | --vlm-version]
 [-z | --description] [-g | --operational-scope] [-k | --manufacture-reference-number]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help                          print help message. It is of type STRING. By
                                      default, it is false.
 -v | --version                       print service version. It is of type STRING. By
                                      default, it is false.
 -d | --debug                         Enable debug output. It is of type BOOL. By
                                      default, it is false.
 -f | --format                        Output formats, supported formats such as table,
                                      csv, json, yaml. It is of type STRING. By
                                      default, it is table.
 -s | --long                          whether to print all attributes or only short
                                      attributes. It is of type BOOL. By default, it is
                                      false.
 -t | --no-title                      whether to print title or not. It is of type
                                      BOOL. By default, it is false.
 -x | --name                          Entitlement Pool name. It is of type STRING.
 -y | --vlm-id                        License Model ID. It is of type STRING.
 -e | --vlm-version                   License Model version. It is of type STRING.
 -z | --description                   Description for Entitlement Pool. It is of type
                                      STRING.
 -g | --operational-scope             Operational Scope. It is of type STRING. By
                                      default, it is Availability_Zone.
 -k | --manufacture-reference-number  Manufature Reference Number. It is of type STRING.
 -u | --host-username                 Host user name. It is of type STRING. By default,
                                      it is read from environment variable
                                      OPEN_CLI_HOST_USERNAME.
 -p | --host-password                 Host user password. It is of type STRING. By
                                      default, it is read from environment variable
                                      OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                      host url in http(s). It is of type URL. By
                                      default, it is read from environment variable
                                      OPEN_CLI_HOST_URL.
 -a | --no-auth                       whether to authenticate user or not. It is of
                                      type BOOL. By default, it is false.


Results::

 ID   Entitlement Pool ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[54] vlm-feature-group-list
-----------------------------------------------


usage: oclip vlm-feature-group-list

Feature group list in a license model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vlm-id] [-y | --vlm-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vlm-id          License Model ID. It is of type STRING.
 -y | --vlm-version     License Model version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 ID     Feature group ID and is of type STRING.
 name   Feature group name and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[55] vlm-key-group-list
-----------------------------------------------


usage: oclip vlm-key-group-list

key group list in a license model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vlm-id] [-y | --vlm-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vlm-id          License Model ID. It is of type STRING.
 -y | --vlm-version     License Model version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 ID     Feature group ID and is of type STRING.
 name   Feature group name and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[56] vlm-revert
-----------------------------------------------


usage: oclip vlm-revert

Revert Vendor License Model

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vlm-id] [-y | --vlm-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vlm-id          Onap VLM ID. It is of type STRING.
 -y | --vlm-version     VLM version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[57] vsp-add-artifact
-----------------------------------------------


usage: oclip vsp-add-artifact

Upload the CSAR file to VSP

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vsp-id] [-y | --vsp-version] [-z | --vsp-file]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     Onap VSP version. It is of type STRING.
 -z | --vsp-file        CSAR File path. It is of type BINARY.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[58] vsp-checkout
-----------------------------------------------


usage: oclip vsp-checkout

Checkout Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vsp-id] [-y | --vsp-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[59] vsp-list
-----------------------------------------------


usage: oclip vsp-list

List of the Vendor Software Products

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 ID               VSP ID and is of type STRING.
 name             VSP Name and is of type STRING.
 vendor-name      Vendor name and is of type STRING.
 version          Version and is of type STRING.
 status           status and is of type STRING.
 license-id       license aggreement and is of type STRING.
 license-version  license version and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[60] vsp-show
-----------------------------------------------


usage: oclip vsp-show

Details of the Vendor Software Product

Product: onap-amsterdam
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vsp-id] [-y | --vsp-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     Onap VSP version. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


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

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[61] microservice-create
-----------------------------------------------


usage: oclip microservice-create

Register microservice into Onap

Product: onap-amsterdam
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --service-name] [-y | --service-version] [-r | --service-url]
 [-b | --enable-ssl] [-c | --path] <node-ip>
 <node-port> <create-or-update> [-m | --host-url]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --service-name      Onap service name. It is of type STRING.
 -y | --service-version   Onap service version. It is of type STRING.
 -r | --service-url       Onap service base url. It is of type URL.
 -b | --enable-ssl        Onap service is enabled with https or not. It is
                          of type STRING. It is optional.
 -c | --path              Onap service path. It is of type STRING. It is
                          optional.
 node-ip                  Onap service running node IP. It is of type
                          STRING.
 node-port                Onap service running node port. It is of type
                          STRING.
 create-or-update         Onap service create or update. It is of type
                          BOOL. By default, it is true.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.


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

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[62] microservice-delete
-----------------------------------------------


usage: oclip microservice-delete

Deletes the micro service from Onap

Product: onap-amsterdam
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --service-name] [-y | --service-version] [-i | --node-ip]
 [-r | --node-port] [-m | --host-url]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --service-name      Onap service name. It is of type STRING.
 -y | --service-version   Onap service version. It is of type STRING.
 -i | --node-ip           Onap service running node IP. It is of type
                          STRING.
 -r | --node-port         Onap service running node port. It is of type
                          STRING.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[63] microservice-list
-----------------------------------------------


usage: oclip microservice-list

Lists the registetred micro services in Onap

Product: onap-amsterdam
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-m | --host-url]

where::

 -h | --help      print help message. It is of type STRING. By
                  default, it is false.
 -v | --version   print service version. It is of type STRING. By
                  default, it is false.
 -d | --debug     Enable debug output. It is of type BOOL. By
                  default, it is false.
 -f | --format    Output formats, supported formats such as table,
                  csv, json, yaml. It is of type STRING. By
                  default, it is table.
 -s | --long      whether to print all attributes or only short
                  attributes. It is of type BOOL. By default, it is
                  false.
 -t | --no-title  whether to print title or not. It is of type
                  BOOL. By default, it is false.
 -m | --host-url  host url in http(s). It is of type URL. By
                  default, it is read from environment variable
                  OPEN_CLI_HOST_URL.


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

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[64] microservice-show
-----------------------------------------------


usage: oclip microservice-show

Details the registered microservice in Onap

Product: onap-amsterdam
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --service-name] [-y | --service-version] [-m | --host-url]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --service-name      Onap service name. It is of type STRING.
 -y | --service-version   Onap service version. It is of type STRING.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.


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

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[65] cloud-delete
-----------------------------------------------


usage: oclip cloud-delete

Delete a cloud region from Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --cloud-name] [-y | --region-name] [-z | --resource-version]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --cloud-name        Onap cloud name. It is of type STRING.
 -y | --region-name       Onap cloud region name. It is of type STRING.
 -z | --resource-version  Onap cloud region version. It is of type UUID. It
                          is optional. By default, it is
                          806a5679-06be-4efb-b420-b314c28efdeb.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[66] customer-delete
-----------------------------------------------


usage: oclip customer-delete

Delete a customer from Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --customer-name] [-y | --resource-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --customer-name     Onap customer name. It is of type STRING.
 -y | --resource-version  Onap customer resource version. It is of type
                          UUID. It is optional. By default, it is
                          0c453a65-431c-48f9-ad3b-ed0b4b5859b3.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[67] customer-list
-----------------------------------------------


usage: oclip customer-list

Lists the registetred customers in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 name               Onap customer name and is of type STRING.
 resource-version   Onap customer resource version and is of type
                    STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[68] customer-show
-----------------------------------------------


usage: oclip customer-show

Lists the registetred customers in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --customer-name] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --customer-name   Onap customer name. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 name               Onap customer name and is of type STRING.
 subscriber-name    Onap subscriber name and is of type STRING.
 resource-version   Onap subscriber resource version and is of type
                    STRING.
 subscriber-type    Onap subscriber type and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[69] ems-list
-----------------------------------------------


usage: oclip ems-list

List the configured ems

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 ems-id             Onap ems id and is of type STRING.
 resource-version   Onap ems resource version and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[70] sdnc-register
-----------------------------------------------


usage: oclip sdnc-register

Register a SDNC in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --location] [-y | --sdnc-id] [-b | --name]
 [-c | --type] [-e | --vendor] [-q | --sdnc-version]
 [-g | --ip-address] [-k | --port] [-r | --protocal]
 [-i | --username] [-j | --password] [-z | --product-name]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --location        Onap VIM unique id. It is of type UUID. By
                        default, it is
                        32df2939-2901-4908-ba4c-5ddaabf719c5.
 -y | --sdnc-id         Onap SDNC unique id. It is of type UUID. By
                        default, it is
                        ea5db6b0-e8e8-44a9-ae2a-fb9b0d5ab26d.
 -b | --name            Onap SDNC name. It is of type STRING.
 -c | --type            Onap SDNC type. It is of type STRING.
 -e | --vendor          Onap SDNC vendor. It is of type STRING.
 -q | --sdnc-version    Onap SDNC version. It is of type STRING.
 -g | --ip-address      Onap SDNC ip address. It is of type STRING.
 -k | --port            Onap SDNC port. It is of type STRING.
 -r | --protocal        Onap SDNC protocal. It is of type STRING.
 -i | --username        Onap SDNC username. It is of type STRING.
 -j | --password        Onap SDNC password. It is of type STRING.
 -z | --product-name    Onap SDNC product-name. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[71] sdnc-list
-----------------------------------------------


usage: oclip sdnc-list

List the configured sdnc

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 sdnc-id            Onap sdnc id and is of type STRING.
 resource-version   Onap sdnc resource version and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[72] cloud-create
-----------------------------------------------


usage: oclip cloud-create

Create a cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --cloud-name] [-y | --region-name] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --cloud-name      Onap cloud name. It is of type STRING.
 -y | --region-name     Onap  region name. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[73] ems-register
-----------------------------------------------


usage: oclip ems-register

Register a EMS in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-z | --ems-id] [-b | --name] [-c | --type]
 [-e | --vendor] [-q | --ems-version] [-g | --url]
 [-i | --username] [-j | --password] [-x | --remote-path]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -z | --ems-id          Onap EMS unique id. It is of type UUID. By
                        default, it is
                        5888ad8e-c90c-4120-8a7e-dbdfe567db1c.
 -b | --name            Onap EMS name. It is of type STRING.
 -c | --type            Onap EMS type. It is of type STRING.
 -e | --vendor          Onap EMS vendor. It is of type STRING.
 -q | --ems-version     Onap EMS version. It is of type STRING.
 -g | --url             Onap EMS URL. It is of type STRING.
 -i | --username        Onap EMS username. It is of type STRING.
 -j | --password        Onap EMS password. It is of type STRING.
 -x | --remote-path     Onap EMS remote-path. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[74] ems-unregister
-----------------------------------------------


usage: oclip ems-unregister

Un-register a EMS in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --ems-id] [-y | --resource-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --ems-id            Onap EMS unique id. It is of type UUID. By
                          default, it is
                          34953098-9d3f-4438-95e8-9d9ce430d7d3.
 -y | --resource-version  Onap vim resource version. It is of type STRING.
                          It is optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[75] ems-show
-----------------------------------------------


usage: oclip ems-show

Show the details of configured ems

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --ems-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --ems-id          Onap EMS unique id. It is of type UUID. By
                        default, it is
                        44b59f48-34cb-4f07-84fb-b8b1033570df.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 name       Onap ems name and is of type STRING.
 type       Onap ems type and is of type STRING.
 vendor     Onap ems vendor and is of type STRING.
 version    Onap ems version and is of type STRING.
 url        Onap ems url and is of type STRING.
 username   Onap ems username and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[76] sdnc-unregister
-----------------------------------------------


usage: oclip sdnc-unregister

Un-register a VNFM in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --sdnc-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --sdnc-id         Onap VNFM unique id. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[77] vim-register
-----------------------------------------------


usage: oclip vim-register

Register a VIM under a given cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --cloud-name] [-y | --region-name] [-z | --vim-id]
 [-b | --name] [-c | --type] [-e | --vendor]
 [-q | --vim-version] [-g | --url] [-i | --username]
 [-j | --password] [-k | --ssl-cacert] [-l | --ssl-insecure]
 [-n | --cloud-domain] [-o | --default-tenant] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --cloud-name      Onap cloud name. It is of type STRING.
 -y | --region-name     Onap  region name. It is of type STRING.
 -z | --vim-id          Onap VIM unique id. It is of type UUID. By
                        default, it is
                        b7eae86a-0e63-4804-93a9-4127a7c1d747.
 -b | --name            Onap VIM name. It is of type STRING.
 -c | --type            Onap VIM type. It is of type STRING.
 -e | --vendor          Onap VIM vendor. It is of type STRING.
 -q | --vim-version     Onap VIM version. It is of type STRING.
 -g | --url             Onap VIM URL. It is of type STRING.
 -i | --username        Onap VIM username. It is of type STRING.
 -j | --password        Onap VIM password. It is of type STRING.
 -k | --ssl-cacert      Onap VIM SSL certificate. It is of type TEXT. It
                        is optional.
 -l | --ssl-insecure    Onap VIM insecure. It is of type BOOL. It is
                        optional. By default, it is false.
 -n | --cloud-domain    Onap VIM cloud domain. It is of type STRING.
 -o | --default-tenant  Onap VIM default tenant. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[78] vim-unregister
-----------------------------------------------


usage: oclip vim-unregister

Un-register a VIM under from cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --cloud-name] [-y | --region-name] [-z | --vim-id]
 [-b | --resource-version] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --cloud-name        Onap cloud name. It is of type STRING.
 -y | --region-name       Onap  region name. It is of type STRING.
 -z | --vim-id            Onap VIM unique id. It is of type UUID. By
                          default, it is
                          98319f86-9d39-4d59-8a89-7902f2214056.
 -b | --resource-version  Onap vim resource version. It is of type STRING.
                          It is optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[79] vim-list
-----------------------------------------------


usage: oclip vim-list

List the configured vims

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --cloud-name] [-y | --region-name] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --cloud-name      Onap cloud name. It is of type STRING.
 -y | --region-name     Onap  region name. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


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

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[80] vnfm-register
-----------------------------------------------


usage: oclip vnfm-register

Register a VNFM in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vim-id] [-y | --vnfm-id] [-b | --name]
 [-c | --type] [-e | --vendor] [-q | --vnfm-version]
 [-g | --url] [-i | --username] [-j | --password]
 [-z | --certificate-url] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --vim-id            Onap VIM unique id. It is of type UUID. By
                          default, it is
                          f3e8293e-7315-4945-8f8e-a0dd13a880cc.
 -y | --vnfm-id           Onap VNFM unique id. It is of type UUID. By
                          default, it is
                          c35aee19-2416-4f13-9fd2-bc76e70db99c.
 -b | --name              Onap VNFM name. It is of type STRING.
 -c | --type              Onap VNFM type. It is of type STRING.
 -e | --vendor            Onap VNFM vendor. It is of type STRING.
 -q | --vnfm-version      Onap VNFM version. It is of type STRING.
 -g | --url               Onap VNFM URL. It is of type STRING.
 -i | --username          Onap VNFM username. It is of type STRING.
 -j | --password          Onap VNFM password. It is of type STRING.
 -z | --certificate-url   Onap VNFM certificate-url. It is of type STRING.
                          It is optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[81] vnfm-unregister
-----------------------------------------------


usage: oclip vnfm-unregister

Un-register a VNFM in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vnfm-id] [-y | --resource-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --vnfm-id           Onap VNFM unique id. It is of type UUID. By
                          default, it is
                          2cbe3dc0-97cb-41e9-b8db-411e6a0975e6.
 -y | --resource-version  Onap vim resource version. It is of type STRING.
                          It is optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[82] vnfm-list
-----------------------------------------------


usage: oclip vnfm-list

List the configured vnfm

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 vnfm-id            Onap vnfm id and is of type STRING.
 vim-id             Onap vnfm id and is of type STRING.
 certificate-url    Onap vnfm certificate-url and is of type STRING.
 resource-version   Onap vnfm resource version and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[83] vnfm-show
-----------------------------------------------


usage: oclip vnfm-show

Show the VNFM in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vnfm-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vnfm-id         Onap VNFM unique id. It is of type UUID. By
                        default, it is
                        472dea25-b892-4b6f-912f-e61430d258da.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 name       Onap vnfm name and is of type STRING.
 type       Onap vnfm type and is of type STRING.
 vendor     Onap vnfm vendor and is of type STRING.
 version    Onap vnfm version and is of type STRING.
 url        Onap vnfm url and is of type STRING.
 username   Onap vnfm username and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[84] service-list
-----------------------------------------------


usage: oclip service-list

List created service instance

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --customer-name] [-y | --service-type] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --customer-name   customer name. It is of type STRING.
 -y | --service-type    service subscription type. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 service-id           instance id of service and is of type STRING.
 service-name         Service name and is of type STRING.
 model-invariant-id   Model invariant id of service model and is of
                      type STRING.
 model-uuid           Model uuid for service model and is of type
                      STRING.
 description          service description and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[85] vf-list
-----------------------------------------------


usage: oclip vf-list

List created VF instances for a service instance

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --customer-name] [-y | --service-type] [-z | --service-id]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --customer-name   customer name. It is of type STRING.
 -y | --service-type    service subscription type. It is of type STRING.
 -z | --service-id      service ID. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 vf-id    VF ID for the given service and is of type STRING.
 vf-name  VF name and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[86] vf-show
-----------------------------------------------


usage: oclip vf-show

Show details for VF

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --vf-id] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --vf-id           VF identifier. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


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

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[87] service-type-create
-----------------------------------------------


usage: oclip service-type-create

Add a service type in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --service-type] [-y | --service-type-id] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --service-type      Onap service type. It is of type STRING.
 -y | --service-type-id   Onap service type uuid. It is of type UUID. It is
                          optional. By default, it is
                          a6c6f62a-19a3-4b7c-9add-e31ccd337510.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[88] service-type-delete
-----------------------------------------------


usage: oclip service-type-delete

Delete a service type from Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --service-type-id] [-y | --resource-version] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --service-type-id   Onap service type uuid. It is of type UUID. It is
                          optional. By default, it is
                          f610d2cb-710a-4603-9aed-b66db1675d20.
 -y | --resource-version  Onap service resource version. It is of type
                          UUID. It is optional. By default, it is
                          0d1f018e-6e47-48c7-95af-5d15d8a6e8ea.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[89] service-type-list
-----------------------------------------------


usage: oclip service-type-list

List the service types configured in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 service-type-id    Onap cloud service and is of type STRING.
 service-type       Onap cloud service and is of type STRING.
 resource-version   Onap cloud service resource version and is of
                    type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[90] subscription-create
-----------------------------------------------


usage: oclip subscription-create

Create a subscription of a customer for given service in specific cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --customer-name] [-z | --cloud-name] [-r | --cloud-region]
 [-c | --cloud-tenant-id] [-e | --service-type] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --customer-name     Onap customer name. It is of type STRING.
 -z | --cloud-name        Onap cloud name. It is of type STRING.
 -r | --cloud-region      Onap cloud region. It is of type STRING.
 -c | --cloud-tenant-id   Onap cloud tenant id. It is of type STRING.
 -e | --service-type      Onap service type. It is of type STRING.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[91] subscription-delete
-----------------------------------------------


usage: oclip subscription-delete

Delete the subscription for a given customer in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --customer-name] [-y | --service-type] [-g | --resource-version]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --customer-name     Onap customer name. It is of type STRING.
 -y | --service-type      Onap subscribtion id. It is of type STRING.
 -g | --resource-version  Onap subscription resource version. It is of type
                          STRING. It is optional.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[92] subscription-list
-----------------------------------------------


usage: oclip subscription-list

Lists the subscription for a given customer in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --customer-name] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --customer-name   Onap customer name. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 service-type       Onap service type and is of type STRING.
 resource-version   Onap subscription resource version and is of type
                    STRING.
 tenant             Onap tenant name and is of type STRING.
 region             Onap region name and is of type STRING.
 cloud              Onap cloud name and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[93] tenant-create
-----------------------------------------------


usage: oclip tenant-create

Create a tenant under given cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --cloud] [-y | --region] [-z | --tenant-id]
 [-r | --tenant-name] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --cloud           Onap cloud. It is of type STRING.
 -y | --region          Onap cloud region. It is of type STRING.
 -z | --tenant-id       Onap cloud tenant id. It is of type STRING.
 -r | --tenant-name     Onap cloud tenant name. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[94] tenant-delete
-----------------------------------------------


usage: oclip tenant-delete

Delete tenant under given cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --cloud] [-y | --region] [-z | --tenant-id]
 [-r | --resource-version] [-u | --host-username] [-p | --host-password]
 [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --cloud             Onap cloud. It is of type STRING.
 -y | --region            Onap cloud region. It is of type STRING.
 -z | --tenant-id         Onap cloud tenant id. It is of type STRING.
 -r | --resource-version  Onap cloud tenant version. It is of type UUID. It
                          is optional. By default, it is
                          b9ea4541-bff0-419b-80cb-0cabad805aad.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[95] tenant-list
-----------------------------------------------


usage: oclip tenant-list

Lists the tenants under given cloud region in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --cloud] [-y | --region] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -x | --cloud           Onap cloud. It is of type STRING.
 -y | --region          Onap cloud region. It is of type STRING.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 tenant-id          Onap tenant-id and is of type STRING.
 tenant-name        Onap tenant name and is of type STRING.
 resource-version   Onap tenant resource version and is of type
                    STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[96] cloud-list
-----------------------------------------------


usage: oclip cloud-list

List the configured clouds and Onap service subscriptions

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-u | --host-username] [-p | --host-password] [-m | --host-url]
 [-a | --no-auth]

where::

 -h | --help            print help message. It is of type STRING. By
                        default, it is false.
 -v | --version         print service version. It is of type STRING. By
                        default, it is false.
 -d | --debug           Enable debug output. It is of type BOOL. By
                        default, it is false.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING. By
                        default, it is table.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL. By default, it is
                        false.
 -t | --no-title        whether to print title or not. It is of type
                        BOOL. By default, it is false.
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 cloud              Onap cloud and is of type STRING.
 region             Onap cloud region and is of type STRING.
 tenant             Onap cloud tenat and is of type STRING.
 tenant-id          Onap cloud tenat id and is of type STRING.
 customer           Onap cloud customer and is of type STRING.
 service            Onap cloud service and is of type STRING.
 resource-version   Onap cloud resource version and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[97] customer-create
-----------------------------------------------


usage: oclip customer-create

Create a customer in Onap

Product: onap-amsterdam
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-x | --customer-name] [-y | --subscriber-name] [-u | --host-username]
 [-p | --host-password] [-m | --host-url] [-a | --no-auth]

where::

 -h | --help              print help message. It is of type STRING. By
                          default, it is false.
 -v | --version           print service version. It is of type STRING. By
                          default, it is false.
 -d | --debug             Enable debug output. It is of type BOOL. By
                          default, it is false.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING. By
                          default, it is table.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL. By default, it is
                          false.
 -t | --no-title          whether to print title or not. It is of type
                          BOOL. By default, it is false.
 -x | --customer-name     Onap customer name. It is of type STRING.
 -y | --subscriber-name   Onap subscriber name. It is of type STRING.
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



sample-helloworld
==========================


[1] hello-world-http
-----------------------------------------------


usage: oclip hello-world-http

First cmd hello world using http runing under
lighttpd in cli at http://<cli-ip>:8080/version.json


Product: sample-helloworld
Service: hello-world
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --name] [-m | --host-url]

where::

 -h | --help      print help message. It is of type STRING. By
                  default, it is false.
 -v | --version   print service version. It is of type STRING. By
                  default, it is false.
 -d | --debug     Enable debug output. It is of type BOOL. By
                  default, it is false.
 -f | --format    Output formats, supported formats such as table,
                  csv, json, yaml. It is of type STRING. By
                  default, it is table.
 -s | --long      whether to print all attributes or only short
                  attributes. It is of type BOOL. By default, it is
                  false.
 -t | --no-title  whether to print title or not. It is of type
                  BOOL. By default, it is false.
 -b | --name      name of the person. It is of type STRING. By
                  default, it is ${DEMO_NAME}.
 -m | --host-url  host url in http(s). It is of type URL. By
                  default, it is read from environment variable
                  OPEN_CLI_HOST_URL.


Results::

 output   hello world output and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[2] hello-world
-----------------------------------------------


usage: oclip hello-world

First cmd hello world

Product: sample-helloworld
Service: hello-world
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-b | --name]

where::

 -h | --help      print help message. It is of type STRING. By
                  default, it is false.
 -v | --version   print service version. It is of type STRING. By
                  default, it is false.
 -d | --debug     Enable debug output. It is of type BOOL. By
                  default, it is false.
 -f | --format    Output formats, supported formats such as table,
                  csv, json, yaml. It is of type STRING. By
                  default, it is table.
 -s | --long      whether to print all attributes or only short
                  attributes. It is of type BOOL. By default, it is
                  false.
 -t | --no-title  whether to print title or not. It is of type
                  BOOL. By default, it is false.
 -b | --name      name of the person. It is of type STRING. By
                  default, it is ${env:DEMO_NAME}.


Results::

 output   hello world output and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>
