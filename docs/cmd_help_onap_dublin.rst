.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2018 Huawei Technologies Co., Ltd.


.. toctree::
   :maxdepth: 2


.. _cli_cmd_help_onap_dublin:



onap-dublin
==========================


[1] basic-login
-----------------------------------------------



usage: oclip basic-login

ONAP basic login auth command

Product: onap-dublin
Service: basic-auth
Author: ONAP CLI Team onap-discuss@lists.onap.org

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

 Authorization    Authorization and is of type STRING.
 X-TransactionId  X-TransactionId and is of type STRING.
 X-FromAppId      X-FromAppId and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[2] basic-logout
-----------------------------------------------



usage: oclip basic-logout

ONAP basic logout auth command

Product: onap-dublin
Service: basic-auth
Author: ONAP CLI Team onap-discuss@lists.onap.org

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

ONAP catalog command to find the base path for service.

Product: onap-dublin
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-l | --catalog-service-name]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-i | --catalog-service-versions] [-s | --long] [-D | --context]

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
 -i | --catalog-service-versions  service version registered in catalog service. It
                                  is of type STRING. By default, it is .
 -s | --long                      whether to print all attributes or only short
                                  attributes. It is of type BOOL.
 -D | --context                   command context. It is of type MAP. It is
                                  optional.


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

Product: onap-dublin
Service: aai
Author: Intel ONAP HPA integration team (itohan.ukponmwan@intel.com)

Options::

 [-e | --esr-id] [-V | --verify] [-f | --format]
 [-h | --help] [-t | --no-title] [-v | --version]
 [-c | --cloud-domain] [-s | --long] [-b | --user-name]
 [-r | --owner-type] [-S | --sriov-automation] [-I | --extra-info]
 [-x | --cloud-owner] [-Q | --system-type] [-y | --region-name]
 [-j | --password] [-a | --no-auth] [-w | --cloud-region-version]
 [-p | --host-password] [-m | --host-url] [-C | --no-catalog]
 [-i | --identity-url] [-d | --debug] [-g | --cloud-zone]
 [-l | --default-tenant] [-url | --service-url] [-n | --complex-name]
 [-q | --cloud-type] [-D | --context] [-z | --ssl-insecure]
 [-k | --system-status] [-u | --host-username]

where::

 -e | --esr-id                id for esr system (arbitrary UUID e.g
                              5c85ce1f-aa78-4ebf-8d6f-4b62773e9bc8). It is of
                              type UUID.
 -V | --verify                Helps to verify the command using samples
                              provides under open-cli-samples directory. By
                              default, it goes with mock.To enable the
                              verification of samples in real time, set
                              DISABLE_MOCKING=true in the context parameter. It
                              is of type BOOL. It is optional.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -h | --help                  print help message. It is of type BOOL.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -v | --version               print service version. It is of type BOOL.
 -c | --cloud-domain          cloud domain, default is Default. It is of type
                              STRING. By default, it is .
 -s | --long                  whether to print all attributes or only short
                              attributes. It is of type BOOL.
 -b | --user-name             cloud user name. It is of type STRING. By
                              default, it is .
 -r | --owner-type            owner defined type. It is of type STRING. By
                              default, it is .
 -S | --sriov-automation      sriov automation, default is false. It is of type
                              BOOL. It is optional.
 -I | --extra-info            extra info to register cloud , generally string
                              with region id e.g
                              {\"openstack-region-id\":\"region-id\"}, enter as
                              {\\"openstack-region-id\\":\\"ONAP-POD-01-Rail-07\
                              \"} with CLI. It is of type STRING. By default,
                              it is .
 -x | --cloud-owner           Onap cloud owner. It is of type STRING. By
                              default, it is .
 -Q | --system-type           system type for cloud e.g VIM. It is of type
                              STRING. By default, it is .
 -y | --region-name           Onap region name. It is of type STRING. By
                              default, it is .
 -j | --password              cloud password. It is of type STRING. By default,
                              it is .
 -a | --no-auth               Whether to authenticate user or not. It is of
                              type BOOL.
 -w | --cloud-region-version  cloud region version e.g titanium_cloud. It is of
                              type STRING. By default, it is .
 -p | --host-password         Host user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url              host url in http(s). It is of type URL. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_URL.
 -C | --no-catalog            Whether to use given host-url directly or
                              discover it from catalog, it will override the
                              service->mode. It is of type BOOL. It is
                              optional.
 -i | --identity-url          Onap identity-url, updated by multicloud if
                              multicloud is used. It is of type STRING. By
                              default, it is .
 -d | --debug                 Enable debug output. It is of type BOOL.
 -g | --cloud-zone            Onap cloud zone. It is of type STRING. By
                              default, it is .
 -l | --default-tenant        default cloud tenant to use. It is of type
                              STRING. By default, it is .
 -url | --service-url         service-url i.e keystone url for openstack. It is
                              of type STRING. By default, it is .
 -n | --complex-name          Onap complex-name. It is of type STRING. By
                              default, it is .
 -q | --cloud-type            Cloud type e.g openstack. It is of type STRING.
                              By default, it is .
 -D | --context               command context. It is of type MAP. It is
                              optional.
 -z | --ssl-insecure          to use ssl insecure or not, default is true. It
                              is of type BOOL.
 -k | --system-status         status of the cloud, default is active. It is of
                              type STRING. By default, it is .
 -u | --host-username         Host user name. It is of type STRING. By default,
                              it is read from environment variable
                              OPEN_CLI_HOST_USERNAME.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[5] cloud-delete
-----------------------------------------------



usage: oclip cloud-delete

Delete a cloud region from Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --cloud-name]
 [-z | --resource-version] [-s | --long] [-D | --context]
 [-y | --region-name] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
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
 -a | --no-auth           Whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[6] cloud-flavor-list
-----------------------------------------------



usage: oclip cloud-flavor-list

List the flavors in a cloud region and its capabilities including HPA with -s option

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-y | --cloud-owner] [-u | --host-username]
 [-a | --no-auth] [-x | --name] [-p | --host-password]

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
 -y | --cloud-owner     Name of cloud owner. It is of type STRING. By
                        default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -x | --name            Name of cloud region. It is of type STRING. By
                        default, it is .
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 flavor             Name of Flavor and is of type STRING.
 resource-version   Resource version of the Flavor and is of type
                    STRING.
 vcpus              Number of VCPUs and is of type STRING.
 ram-size           RAM Size for flavor and is of type STRING.
 disk-size          Disk size for flavor and is of type STRING.
 ID                 flavor ID in cloud and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[7] cloud-list
-----------------------------------------------



usage: oclip cloud-list

List the configured clouds and Onap service subscriptions

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
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



[8] complex-associate
-----------------------------------------------



usage: oclip complex-associate

Associate a cloud region with a cloud complex

Product: onap-dublin
Service: aai
Author: Intel ONAP HPA integration team (itohan.ukponmwan@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-y | --cloud-region] [-x | --complex-name]
 [-z | --cloud-owner] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

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
 -y | --cloud-region    name of registered cloud-region. It is of type
                        STRING. By default, it is .
 -x | --complex-name    name of cloud complex. It is of type STRING. By
                        default, it is .
 -z | --cloud-owner     name of cloud-owner. It is of type STRING. By
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



[9] complex-create
-----------------------------------------------



usage: oclip complex-create

Create a cloud complex in Onap

Product: onap-dublin
Service: aai
Author: Intel ONAP HPA integration team (itohan.ukponmwan@intel.com)

Options::

 [-V | --verify] [-f | --format] [-h | --help]
 [-t | --no-title] [-j | --street2] [-v | --version]
 [-r | --physical-location-type] [-s | --long] [-lt | --latitude]
 [-x | --physical-location-id] [-y | --data-center-code] [-a | --no-auth]
 [-l | --region] [-p | --host-password] [-m | --host-url]
 [-C | --no-catalog] [-i | --street1] [-lo | --longitude]
 [-d | --debug] [-S | --state] [-la | --lata]
 [-D | --context] [-g | --city] [-w | --postal-code]
 [-z | --complex-name] [-k | --country] [-o | --elevation]
 [-u | --host-username] [-q | --identity-url]

where::

 -V | --verify                  Helps to verify the command using samples
                                provides under open-cli-samples directory. By
                                default, it goes with mock.To enable the
                                verification of samples in real time, set
                                DISABLE_MOCKING=true in the context parameter. It
                                is of type BOOL. It is optional.
 -f | --format                  Output formats, supported formats such as table,
                                csv, json, yaml. It is of type STRING.
 -h | --help                    print help message. It is of type BOOL.
 -t | --no-title                whether to print title or not. It is of type BOOL.
 -j | --street2                 name of street 2 for complex. It is of type
                                STRING. By default, it is .
 -v | --version                 print service version. It is of type BOOL.
 -r | --physical-location-type  complex physical location type. It is of type
                                STRING. By default, it is .
 -s | --long                    whether to print all attributes or only short
                                attributes. It is of type BOOL.
 -lt | --latitude               latitude of complex location. It is of type
                                STRING. By default, it is .
 -x | --physical-location-id    id of physical location. It is of type STRING. By
                                default, it is .
 -y | --data-center-code        datacenter name. It is of type STRING. By
                                default, it is .
 -a | --no-auth                 Whether to authenticate user or not. It is of
                                type BOOL.
 -l | --region                  region complex is located. It is of type STRING.
                                By default, it is .
 -p | --host-password           Host user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                host url in http(s). It is of type URL. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_URL.
 -C | --no-catalog              Whether to use given host-url directly or
                                discover it from catalog, it will override the
                                service->mode. It is of type BOOL. It is
                                optional.
 -i | --street1                 name of street 1 for complex. It is of type
                                STRING. By default, it is .
 -lo | --longitude              longitude of complex location. It is of type
                                STRING. By default, it is .
 -d | --debug                   Enable debug output. It is of type BOOL.
 -S | --state                   state complex is located in. It is of type
                                STRING. By default, it is .
 -la | --lata                   lata of complex. It is of type STRING. By
                                default, it is .
 -D | --context                 command context. It is of type MAP. It is
                                optional.
 -g | --city                    city complex is located in. It is of type STRING.
                                By default, it is .
 -w | --postal-code             postal code for complex. It is of type STRING. By
                                default, it is .
 -z | --complex-name            complex name. It is of type STRING. By default,
                                it is .
 -k | --country                 country complex is located. It is of type STRING.
                                By default, it is .
 -o | --elevation               elevation of complex location. It is of type
                                STRING. By default, it is .
 -u | --host-username           Host user name. It is of type STRING. By default,
                                it is read from environment variable
                                OPEN_CLI_HOST_USERNAME.
 -q | --identity-url            identity url for complex. It is of type STRING.
                                By default, it is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[10] complex-delete
-----------------------------------------------



usage: oclip complex-delete

Delete a complex from Onap

Product: onap-dublin
Service: aai
Author: ONAP HPA Integration Team (itohan.ukponmwan@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-y | --resource-version]
 [-s | --long] [-D | --context] [-x | --complex-name]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -y | --resource-version  Onap complex region version. It is of type UUID.
                          It is optional.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -x | --complex-name      Onap complex name. It is of type STRING. By
                          default, it is .
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



[11] complex-list
-----------------------------------------------



usage: oclip complex-list

List the configured complexes

Product: onap-dublin
Service: aai
Author: ONAP HPA Integration Team (itohan.ukponmwan@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 complex-name             onap complex and is of type STRING.
 physical-location-id     onap complex location id and is of type STRING.
 data-center-code         data center code and is of type STRING.
 identity-url             identity url and is of type STRING.
 resource-version         complex resource version and is of type STRING.
 physical-location-type   physical location type and is of type STRING.
 street1                  onap complex street1 and is of type STRING.
 street2                  onap complex street2 and is of type STRING.
 city                     onap complex city and is of type STRING.
 state                    onap complex state and is of type STRING.
 postal-code              onap complex postal code and is of type STRING.
 country                  onap complex country and is of type STRING.
 region                   onap complex region and is of type STRING.
 latitude                 onap complex latitude and is of type STRING.
 longitude                onap complex longitude and is of type STRING.
 elevation                onap complex elevation and is of type STRING.
 lata                     onap complex lata and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[12] complex-update
-----------------------------------------------



usage: oclip complex-update

Update a cloud complex in Onap

Product: onap-dublin
Service: aai
Author: Intel ONAP HPA integration team (itohan.ukponmwan@intel.com)

Options::

 [-V | --verify] [-f | --format] [-h | --help]
 [-t | --no-title] [-j | --street2] [-v | --version]
 [-r | --physical-location-type] [-R | --resource-version] [-s | --long]
 [-lt | --latitude] [-x | --physical-location-id] [-y | --data-center-code]
 [-a | --no-auth] [-l | --region] [-p | --host-password]
 [-m | --host-url] [-C | --no-catalog] [-i | --street1]
 [-lo | --longitude] [-d | --debug] [-S | --state]
 [-la | --lata] [-D | --context] [-g | --city]
 [-w | --postal-code] [-z | --complex-name] [-k | --country]
 [-o | --elevation] [-u | --host-username] [-q | --identity-url]

where::

 -V | --verify                  Helps to verify the command using samples
                                provides under open-cli-samples directory. By
                                default, it goes with mock.To enable the
                                verification of samples in real time, set
                                DISABLE_MOCKING=true in the context parameter. It
                                is of type BOOL. It is optional.
 -f | --format                  Output formats, supported formats such as table,
                                csv, json, yaml. It is of type STRING.
 -h | --help                    print help message. It is of type BOOL.
 -t | --no-title                whether to print title or not. It is of type BOOL.
 -j | --street2                 name of street 2 for complex. It is of type
                                STRING. By default, it is .
 -v | --version                 print service version. It is of type BOOL.
 -r | --physical-location-type  complex physical location type. It is of type
                                STRING. By default, it is .
 -R | --resource-version        resource version of complex to be updated. It is
                                of type UUID.
 -s | --long                    whether to print all attributes or only short
                                attributes. It is of type BOOL.
 -lt | --latitude               latitude of complex location. It is of type
                                STRING. By default, it is .
 -x | --physical-location-id    id of physical location. It is of type STRING. By
                                default, it is .
 -y | --data-center-code        datacenter name. It is of type STRING. By
                                default, it is .
 -a | --no-auth                 Whether to authenticate user or not. It is of
                                type BOOL.
 -l | --region                  region complex is located. It is of type STRING.
                                By default, it is .
 -p | --host-password           Host user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                host url in http(s). It is of type URL. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_URL.
 -C | --no-catalog              Whether to use given host-url directly or
                                discover it from catalog, it will override the
                                service->mode. It is of type BOOL. It is
                                optional.
 -i | --street1                 name of street 1 for complex. It is of type
                                STRING. By default, it is .
 -lo | --longitude              longitude of complex location. It is of type
                                STRING. By default, it is .
 -d | --debug                   Enable debug output. It is of type BOOL.
 -S | --state                   state complex is located in. It is of type
                                STRING. By default, it is .
 -la | --lata                   lata of complex. It is of type STRING. By
                                default, it is .
 -D | --context                 command context. It is of type MAP. It is
                                optional.
 -g | --city                    city complex is located in. It is of type STRING.
                                By default, it is .
 -w | --postal-code             postal code for complex. It is of type STRING. By
                                default, it is .
 -z | --complex-name            complex name. It is of type STRING. By default,
                                it is .
 -k | --country                 country complex is located. It is of type STRING.
                                By default, it is .
 -o | --elevation               elevation of complex location. It is of type
                                STRING. By default, it is .
 -u | --host-username           Host user name. It is of type STRING. By default,
                                it is read from environment variable
                                OPEN_CLI_HOST_USERNAME.
 -q | --identity-url            identity url for complex. It is of type STRING.
                                By default, it is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[13] csar-create
-----------------------------------------------



usage: oclip csar-create

Uploads the CSARs in marketplace

Product: onap-dublin
Service: marketplace
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --csar-file]
 [-s | --long] [-D | --context]

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
 -x | --csar-file   CSAR File path. It is of type BINARY. By default,
                    it is .
 -s | --long        whether to print all attributes or only short
                    attributes. It is of type BOOL.
 -D | --context     command context. It is of type MAP. It is
                    optional.


Results::

 csarId   CSAR id and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[14] csar-delete
-----------------------------------------------



usage: oclip csar-delete

Delete CSARs in marketplace

Product: onap-dublin
Service: marketplace
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --csar-id]
 [-s | --long] [-D | --context]

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
 -x | --csar-id     Onap CSAR id. It is of type STRING. By default,
                    it is .
 -s | --long        whether to print all attributes or only short
                    attributes. It is of type BOOL.
 -D | --context     command context. It is of type MAP. It is
                    optional.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[15] csar-list
-----------------------------------------------



usage: oclip csar-list

Lists the CSARs in marketplace

Product: onap-dublin
Service: marketplace
Author: ONAP CLI Team onap-discuss@lists.onap.org

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


Results::

 csarId       CSAR Id and is of type STRING.
 name         CSAR Name and is of type STRING.
 size         CSAR size and is of type URL.
 downloadUri  CSAR download URL and is of type URL.
 provider     CSAR Provider and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[16] csar-show
-----------------------------------------------



usage: oclip csar-show

Show details of the CSARs in marketplace

Product: onap-dublin
Service: marketplace
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --csar-id]
 [-s | --long] [-D | --context]

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
 -x | --csar-id     Onap CSAR id. It is of type STRING. By default,
                    it is .
 -s | --long        whether to print all attributes or only short
                    attributes. It is of type BOOL.
 -D | --context     command context. It is of type MAP. It is
                    optional.


Results::

 name         CSAR Name and is of type STRING.
 size         CSAR size and is of type URL.
 downloadUri  CSAR download URL and is of type URL.
 provider     CSAR Provider and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[17] customer-create
-----------------------------------------------



usage: oclip customer-create

Create a customer in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-y | --subscriber-name] [-C | --no-catalog]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-x | --customer-name] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -y | --subscriber-name   Onap subscriber name. It is of type STRING. By
                          default, it is .
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -x | --customer-name     Onap customer name. It is of type STRING. By
                          default, it is .
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



[18] customer-delete
-----------------------------------------------



usage: oclip customer-delete

Delete a customer from Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-y | --resource-version]
 [-s | --long] [-D | --context] [-x | --customer-id]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -y | --resource-version  Onap customer resource version. It is of type
                          UUID.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -x | --customer-id       Onap customer id. It is of type STRING. By
                          default, it is .
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



[19] customer-list
-----------------------------------------------



usage: oclip customer-list

Lists the registered customers in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
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



[20] customer-show
-----------------------------------------------



usage: oclip customer-show

Retrieves the given registered customer in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --customer-name]
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
 -x | --customer-name   Onap customer name. It is of type STRING. By
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

 name               Onap customer name and is of type STRING.
 subscriber-name    Onap subscriber name and is of type STRING.
 resource-version   Onap subscriber resource version and is of type
                    STRING.
 subscriber-type    Onap subscriber type and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[21] ems-create
-----------------------------------------------



usage: oclip ems-create

Register a EMS in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-e | --vendor] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-c | --type] [-s | --long] [-D | --context]
 [-q | --ems-version] [-j | --password] [-b | --name]
 [-i | --username] [-u | --host-username] [-g | --url]
 [-x | --remote-path] [-a | --no-auth] [-z | --ems-id]
 [-p | --host-password]

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
 -e | --vendor          Onap EMS vendor. It is of type STRING. By
                        default, it is .
 -V | --verify          Helps to verify the command using samples
                        provides under open-cli-samples directory. By
                        default, it goes with mock.To enable the
                        verification of samples in real time, set
                        DISABLE_MOCKING=true in the context parameter. It
                        is of type BOOL. It is optional.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -d | --debug           Enable debug output. It is of type BOOL.
 -v | --version         print service version. It is of type BOOL.
 -c | --type            Onap EMS type. It is of type STRING. By default,
                        it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -q | --ems-version     Onap EMS version. It is of type STRING. By
                        default, it is .
 -j | --password        Onap EMS password. It is of type STRING. By
                        default, it is .
 -b | --name            Onap EMS name. It is of type STRING. By default,
                        it is .
 -i | --username        Onap EMS username. It is of type STRING. By
                        default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -g | --url             Onap EMS URL. It is of type STRING. By default,
                        it is .
 -x | --remote-path     Onap EMS remote-path. It is of type STRING. By
                        default, it is .
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -z | --ems-id          Onap EMS unique id. It is of type UUID.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[22] ems-delete
-----------------------------------------------



usage: oclip ems-delete

Un-register a EMS in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-y | --resource-version]
 [-x | --ems-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -y | --resource-version  Onap EMS resource version. It is of type STRING.
                          It is optional. By default, it is .
 -x | --ems-id            Onap EMS unique id. It is of type UUID.
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



[23] ems-list
-----------------------------------------------



usage: oclip ems-list

List the configured ems

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ems-id             Onap ems id and is of type STRING.
 resource-version   Onap ems resource version and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[24] ems-show
-----------------------------------------------



usage: oclip ems-show

Show the details of configured ems

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --ems-id]
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
 -x | --ems-id          Onap EMS unique id. It is of type UUID.
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

 name       Onap ems name and is of type STRING.
 type       Onap ems type and is of type STRING.
 vendor     Onap ems vendor and is of type STRING.
 version    Onap ems version and is of type STRING.
 url        Onap ems url and is of type STRING.
 username   Onap ems username and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[25] get-resource-module-name
-----------------------------------------------



usage: oclip get-resource-module-name

Get resource module name

Product: onap-dublin
Service: sdc
Author: ONAP HPA Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 service-model-vsp      name of vsp in service model and is of type
                        STRING.
 resource-module-name   system name of vsp and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[26] microservice-create
-----------------------------------------------



usage: oclip microservice-create

Register microservice into Onap

Product: onap-dublin
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] <create-or-update> [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-c | --path] [-y | --service-version] <node-ip>
 <node-port> [-s | --long] [-D | --context]
 [-x | --service-name] [-r | --service-url] [-b | --enable-ssl]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 create-or-update         Onap service create or update. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -c | --path              Onap service path. It is of type STRING. It is
                          optional. By default, it is .
 -y | --service-version   Onap service version. It is of type STRING. By
                          default, it is .
 node-ip                  Onap service running node IP. It is of type
                          STRING. By default, it is .
 node-port                Onap service running node port. It is of type
                          STRING. By default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
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



[27] microservice-delete
-----------------------------------------------



usage: oclip microservice-delete

Deletes the micro service from Onap

Product: onap-dublin
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-y | --service-version]
 [-s | --long] [-D | --context] [-x | --service-name]
 [-i | --node-ip] [-r | --node-port]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
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



[28] microservice-list
-----------------------------------------------



usage: oclip microservice-list

Lists the registetred micro services in Onap

Product: onap-dublin
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

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



[29] microservice-show
-----------------------------------------------



usage: oclip microservice-show

Details the registered microservice in Onap

Product: onap-dublin
Service: msb
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-y | --service-version]
 [-s | --long] [-D | --context] [-x | --service-name]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
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



[30] multicloud-cloud-delete
-----------------------------------------------



usage: oclip multicloud-cloud-delete

Deletes a cloud region, can be used to directly delete cloud referenced by other resources

Product: onap-dublin
Service: multicloud
Author: ONAP HPA Integration Team (itohan.ukponmwan@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-y | --cloud-owner] [-x | --cloud-region]

where::

 -m | --host-url      host url in http(s). It is of type URL. By
                      default, it is read from environment variable
                      OPEN_CLI_HOST_URL.
 -C | --no-catalog    Whether to use given host-url directly or
                      discover it from catalog, it will override the
                      service->mode. It is of type BOOL. It is
                      optional.
 -f | --format        Output formats, supported formats such as table,
                      csv, json, yaml. It is of type STRING.
 -h | --help          print help message. It is of type BOOL.
 -V | --verify        Helps to verify the command using samples
                      provides under open-cli-samples directory. By
                      default, it goes with mock.To enable the
                      verification of samples in real time, set
                      DISABLE_MOCKING=true in the context parameter. It
                      is of type BOOL. It is optional.
 -t | --no-title      whether to print title or not. It is of type BOOL.
 -d | --debug         Enable debug output. It is of type BOOL.
 -v | --version       print service version. It is of type BOOL.
 -s | --long          whether to print all attributes or only short
                      attributes. It is of type BOOL.
 -D | --context       command context. It is of type MAP. It is
                      optional.
 -y | --cloud-owner   Onap Cloud Owner name. It is of type STRING. By
                      default, it is .
 -x | --cloud-region  Onap Cloud region name. It is of type STRING. By
                      default, it is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[31] multicloud-register-cloud
-----------------------------------------------



usage: oclip multicloud-register-cloud

Register a cloud region with multicloud plugin

Product: onap-dublin
Service: multicloud
Author: ONAP HPA Integration Team (itohan.ukponmwan@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-y | --cloud-owner] [-x | --cloud-region]

where::

 -m | --host-url      host url in http(s). It is of type URL. By
                      default, it is read from environment variable
                      OPEN_CLI_HOST_URL.
 -C | --no-catalog    Whether to use given host-url directly or
                      discover it from catalog, it will override the
                      service->mode. It is of type BOOL. It is
                      optional.
 -f | --format        Output formats, supported formats such as table,
                      csv, json, yaml. It is of type STRING.
 -h | --help          print help message. It is of type BOOL.
 -V | --verify        Helps to verify the command using samples
                      provides under open-cli-samples directory. By
                      default, it goes with mock.To enable the
                      verification of samples in real time, set
                      DISABLE_MOCKING=true in the context parameter. It
                      is of type BOOL. It is optional.
 -t | --no-title      whether to print title or not. It is of type BOOL.
 -d | --debug         Enable debug output. It is of type BOOL.
 -v | --version       print service version. It is of type BOOL.
 -s | --long          whether to print all attributes or only short
                      attributes. It is of type BOOL.
 -D | --context       command context. It is of type MAP. It is
                      optional.
 -y | --cloud-owner   Onap Cloud Owner name. It is of type STRING. By
                      default, it is .
 -x | --cloud-region  Onap Cloud region name. It is of type STRING. By
                      default, it is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[32] owning-entity-list
-----------------------------------------------



usage: oclip owning-entity-list

Lists the Owning Entities in Onap

Product: onap-dublin
Service: aai
Author: ONAP HPA Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 name               Owning entity name and is of type STRING.
 Id                 Owning entity Id and is of type STRING.
 resource-version   Owning entity resource version and is of type
                    STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[33] policy-basic-login
-----------------------------------------------



usage: oclip policy-basic-login

ONAP basic login auth command

Product: onap-dublin
Service: policy-basic-auth
Author: ONAP CLI Team onap-discuss@lists.onap.org

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
 Environment    Environment and is of type STRING.
 ClientAuth     Client Auth and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[34] policy-create-outdated
-----------------------------------------------



usage: oclip policy-create-outdated

Create a policy in PAP

Product: onap-dublin
Service: policy
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-T | --policy--config-type]
 [-b | --policy-config-body] [-s | --long] [-D | --context]
 [-u | --host-username] [-x | --policy-name] [-a | --no-auth]
 [-S | --policy-scope] [-o | --onap-name] [-p | --host-password]

where::

 -m | --host-url              host url in http(s). It is of type URL. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_URL.
 -C | --no-catalog            Whether to use given host-url directly or
                              discover it from catalog, it will override the
                              service->mode. It is of type BOOL. It is
                              optional.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -h | --help                  print help message. It is of type BOOL.
 -V | --verify                Helps to verify the command using samples
                              provides under open-cli-samples directory. By
                              default, it goes with mock.To enable the
                              verification of samples in real time, set
                              DISABLE_MOCKING=true in the context parameter. It
                              is of type BOOL. It is optional.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -d | --debug                 Enable debug output. It is of type BOOL.
 -v | --version               print service version. It is of type BOOL.
 -T | --policy--config-type   Policy config type. It is of type STRING. By
                              default, it is .
 -b | --policy-config-body    Policy config body. It is of type STRING. By
                              default, it is .
 -s | --long                  whether to print all attributes or only short
                              attributes. It is of type BOOL.
 -D | --context               command context. It is of type MAP. It is
                              optional.
 -u | --host-username         Host user name. It is of type STRING. By default,
                              it is read from environment variable
                              OPEN_CLI_HOST_USERNAME.
 -x | --policy-name           Onap policy Name. It is of type STRING. By
                              default, it is .
 -a | --no-auth               Whether to authenticate user or not. It is of
                              type BOOL.
 -S | --policy-scope          Policy scope. It is of type STRING. By default,
                              it is .
 -o | --onap-name             Onap name. It is of type STRING. By default, it
                              is .
 -p | --host-password         Host user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[35] policy-delete-pap
-----------------------------------------------



usage: oclip policy-delete-pap

Delete config policy in PAP

Product: onap-dublin
Service: policy
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-c | --delete-condition]
 [-s | --long] [-D | --context] [-y | --policy-component]
 [-u | --host-username] [-x | --policy-name] [-a | --no-auth]
 [-b | --policy-type] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -c | --delete-condition  Delete condition. It is of type STRING. By
                          default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -y | --policy-component  Component of the policy. It is of type STRING. By
                          default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -x | --policy-name       Onap policy Name. It is of type STRING. By
                          default, it is .
 -a | --no-auth           Whether to authenticate user or not. It is of
                          type BOOL.
 -b | --policy-type       Policy config type. It is of type STRING. By
                          default, it is .
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[36] policy-delete-pdp
-----------------------------------------------



usage: oclip policy-delete-pdp

Delete config policy in PDP

Product: onap-dublin
Service: policy
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-y | --policy-component] [-u | --host-username]
 [-x | --policy-name] [-a | --no-auth] [-b | --policy-type]
 [-g | --pdp-group] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -y | --policy-component  Component of the policy. It is of type STRING. By
                          default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -x | --policy-name       Onap policy Name. It is of type STRING. By
                          default, it is .
 -a | --no-auth           Whether to authenticate user or not. It is of
                          type BOOL.
 -b | --policy-type       Policy config type. It is of type STRING. By
                          default, it is .
 -g | --pdp-group         PDP group name. It is of type STRING. By default,
                          it is .
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[37] policy-delete-type
-----------------------------------------------



usage: oclip policy-delete-type

Delete all versions of a policy type

Product: onap-dublin
Service: policy
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-x | --policy-type-id] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

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
 -x | --policy-type-id  Policy type ID. It is of type STRING. By default,
                        it is .
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



[38] policy-delete-type-with-version
-----------------------------------------------



usage: oclip policy-delete-type-with-version

Delete one version of a policy type

Product: onap-dublin
Service: policy
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-y | --version-id]
 [-s | --long] [-D | --context] [-x | --policy-type-id]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

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
 -y | --version-id      Policy type version ID. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -x | --policy-type-id  Policy type ID. It is of type STRING. By default,
                        it is .
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



[39] policy-list-outdated
-----------------------------------------------



usage: oclip policy-list-outdated

List policies

Product: onap-dublin
Service: policy
Author: ONAP HPA Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-A | --config-attributes]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-x | --policy-name] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url            host url in http(s). It is of type URL. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_URL.
 -C | --no-catalog          Whether to use given host-url directly or
                            discover it from catalog, it will override the
                            service->mode. It is of type BOOL. It is
                            optional.
 -f | --format              Output formats, supported formats such as table,
                            csv, json, yaml. It is of type STRING.
 -h | --help                print help message. It is of type BOOL.
 -V | --verify              Helps to verify the command using samples
                            provides under open-cli-samples directory. By
                            default, it goes with mock.To enable the
                            verification of samples in real time, set
                            DISABLE_MOCKING=true in the context parameter. It
                            is of type BOOL. It is optional.
 -t | --no-title            whether to print title or not. It is of type BOOL.
 -d | --debug               Enable debug output. It is of type BOOL.
 -v | --version             print service version. It is of type BOOL.
 -A | --config-attributes   Configuration Attributes. It is of type JSON.
                            It's recommended to input the complete path of
                            the file, which is having the value for it. It is
                            optional.
 -s | --long                whether to print all attributes or only short
                            attributes. It is of type BOOL.
 -D | --context             command context. It is of type MAP. It is
                            optional.
 -u | --host-username       Host user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_CLI_HOST_USERNAME.
 -x | --policy-name         ONAP policy name. It is of type STRING.
 -a | --no-auth             Whether to authenticate user or not. It is of
                            type BOOL.
 -p | --host-password       Host user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 name     Policy Name and is of type STRING.
 type     Policy type and is of type STRING.
 config   Policy configuration and is of type JSON.
 version  Policy version and is of type STRING.
 rules    Policy rules and is of type JSON.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[40] policy-push-outdated
-----------------------------------------------



usage: oclip policy-push-outdated

Push a policy to PDP

Product: onap-dublin
Service: policy
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-c | --policy-group] [-u | --host-username]
 [-x | --policy-name] [-a | --no-auth] [-b | --policy-type]
 [-p | --host-password]

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
 -c | --policy-group    Policy pdp group. It is of type STRING. By
                        default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -x | --policy-name     Onap policy Name. It is of type STRING. By
                        default, it is .
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -b | --policy-type     Policy type. It is of type STRING. By default, it
                        is .
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[41] policy-type-create
-----------------------------------------------



usage: oclip policy-type-create

Create a policy type

Product: onap-dublin
Service: policy
Author: ONAP HPA Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --policy-type-body]
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
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -x | --policy-type-body  Configuration of the new policy type. It is of
                          type JSON. It's recommended to input the complete
                          path of the file, which is having the value for
                          it.
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


Results::

 Response   Response message and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[42] policy-type-list
-----------------------------------------------



usage: oclip policy-type-list

Retrieve existing policy types

Product: onap-dublin
Service: policy
Author: ONAP HPA Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] <policy-type-id> [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

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
 policy-type-id         Policy type ID. It is of type STRING. By default,
                        it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 Type properties  Policy type properties and is of type JSON.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[43] policy-type-list-all
-----------------------------------------------



usage: oclip policy-type-list-all

Retrieve existing policy types

Product: onap-dublin
Service: policy
Author: ONAP HPA Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 Type properties  Policy type properties and is of type JSON.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[44] policy-type-list-with-version
-----------------------------------------------



usage: oclip policy-type-list-with-version

Retrieve one particular version of a policy type

Product: onap-dublin
Service: policy
Author: ONAP HPA Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] <version-id>
 [-s | --long] [-D | --context] <policy-type-id>
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

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
 version-id             Policy type version ID. It is of type STRING. By
                        default, it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 policy-type-id         Policy type ID. It is of type STRING. By default,
                        it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 Type properties  Policy type properties and is of type JSON.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[45] policy-update-outdated
-----------------------------------------------



usage: oclip policy-update-outdated

Update a policy in PAP

Product: onap-dublin
Service: policy
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-T | --policy--config-type]
 [-b | --policy-config-body] [-s | --long] [-D | --context]
 [-u | --host-username] [-x | --policy-name] [-a | --no-auth]
 [-S | --policy-scope] [-o | --onap-name] [-p | --host-password]

where::

 -m | --host-url              host url in http(s). It is of type URL. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_URL.
 -C | --no-catalog            Whether to use given host-url directly or
                              discover it from catalog, it will override the
                              service->mode. It is of type BOOL. It is
                              optional.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -h | --help                  print help message. It is of type BOOL.
 -V | --verify                Helps to verify the command using samples
                              provides under open-cli-samples directory. By
                              default, it goes with mock.To enable the
                              verification of samples in real time, set
                              DISABLE_MOCKING=true in the context parameter. It
                              is of type BOOL. It is optional.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -d | --debug                 Enable debug output. It is of type BOOL.
 -v | --version               print service version. It is of type BOOL.
 -T | --policy--config-type   Policy config type. It is of type STRING. By
                              default, it is .
 -b | --policy-config-body    Policy config body. It is of type STRING. By
                              default, it is .
 -s | --long                  whether to print all attributes or only short
                              attributes. It is of type BOOL.
 -D | --context               command context. It is of type MAP. It is
                              optional.
 -u | --host-username         Host user name. It is of type STRING. By default,
                              it is read from environment variable
                              OPEN_CLI_HOST_USERNAME.
 -x | --policy-name           Onap policy Name. It is of type STRING. By
                              default, it is .
 -a | --no-auth               Whether to authenticate user or not. It is of
                              type BOOL.
 -S | --policy-scope          Policy scope. It is of type STRING. By default,
                              it is .
 -o | --onap-name             Onap name. It is of type STRING. By default, it
                              is .
 -p | --host-password         Host user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[46] sdc-basic-login
-----------------------------------------------



usage: oclip sdc-basic-login

ONAP basic login auth command

Product: onap-dublin
Service: sdc-basic-auth
Author: ONAP CLI Team onap-discuss@lists.onap.org

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

 Authorization    Authorization and is of type STRING.
 X-TransactionId  X-TransactionId and is of type STRING.
 X-FromAppId      X-FromAppId and is of type STRING.
 USER_ID          USER_ID for sdc and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[47] sdnc-create
-----------------------------------------------



usage: oclip sdnc-create

Register a SDNC in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-g | --ip-address] [-e | --vendor] [-V | --verify]
 [-f | --format] [-h | --help] [-t | --no-title]
 [-v | --version] [-y | --sdnc-id] [-s | --long]
 [-j | --password] [-b | --name] [-a | --no-auth]
 [-p | --host-password] [-m | --host-url] [-C | --no-catalog]
 [-d | --debug] [-k | --port] [-c | --type]
 [-q | --sdnc-version] [-D | --context] [-r | --protocal]
 [-z | --product-name] [-i | --username] [-u | --host-username]
 [-x | --location]

where::

 -g | --ip-address      Onap SDNC ip address. It is of type STRING. By
                        default, it is .
 -e | --vendor          Onap SDNC vendor. It is of type STRING. By
                        default, it is .
 -V | --verify          Helps to verify the command using samples
                        provides under open-cli-samples directory. By
                        default, it goes with mock.To enable the
                        verification of samples in real time, set
                        DISABLE_MOCKING=true in the context parameter. It
                        is of type BOOL. It is optional.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -h | --help            print help message. It is of type BOOL.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -v | --version         print service version. It is of type BOOL.
 -y | --sdnc-id         Onap SDNC unique id. It is of type UUID.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -j | --password        Onap SDNC password. It is of type STRING. By
                        default, it is .
 -b | --name            Onap SDNC name. It is of type STRING. By default,
                        it is .
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -C | --no-catalog      Whether to use given host-url directly or
                        discover it from catalog, it will override the
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
 -x | --location        Onap SDNC unique id. It is of type UUID.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[48] sdnc-delete
-----------------------------------------------



usage: oclip sdnc-delete

Un-register a VNFM in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --sdnc-id]
 [-r | --resource-version] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -x | --sdnc-id           Onap sdnc unique id. It is of type STRING. By
                          default, it is .
 -r | --resource-version  Onap resource version. It is of type UUID. It is
                          optional.
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



[49] sdnc-list
-----------------------------------------------



usage: oclip sdnc-list

List the configured sdnc

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 sdnc-id            Onap sdnc id and is of type STRING.
 resource-version   Onap sdnc resource version and is of type STRING.
 location           Onap sdnc location and is of type STRING.
 product-name       Onap sdnc product name and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[50] service-create
-----------------------------------------------


0x7006::In service-create-schema-dublin.yaml, Parameter option t is in conflict, only one option is allowed with given name


[51] service-create-vcpe
-----------------------------------------------


0x7006::In service-create-vcpe-schema-dublin.yaml, Parameter option t is in conflict, only one option is allowed with given name


[52] service-delete
-----------------------------------------------



usage: oclip service-delete

Delete a service instance using SO

Product: onap-dublin
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-e | --model-name] [-C | --no-catalog]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-b | --model-invariant-id] [-j | --cloud-region] [-x | --service-instace-id]
 [-r | --requestor-id] [-i | --model-uuid] [-c | --customer-name]
 [-s | --long] [-D | --context] [-g | --model-version]
 [-k | --tenant-id] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url            host url in http(s). It is of type URL. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_URL.
 -e | --model-name          model name available in SDC catalog. It is of
                            type STRING. By default, it is .
 -C | --no-catalog          Whether to use given host-url directly or
                            discover it from catalog, it will override the
                            service->mode. It is of type BOOL. It is
                            optional.
 -f | --format              Output formats, supported formats such as table,
                            csv, json, yaml. It is of type STRING.
 -h | --help                print help message. It is of type BOOL.
 -V | --verify              Helps to verify the command using samples
                            provides under open-cli-samples directory. By
                            default, it goes with mock.To enable the
                            verification of samples in real time, set
                            DISABLE_MOCKING=true in the context parameter. It
                            is of type BOOL. It is optional.
 -t | --no-title            whether to print title or not. It is of type BOOL.
 -d | --debug               Enable debug output. It is of type BOOL.
 -v | --version             print service version. It is of type BOOL.
 -b | --model-invariant-id  model invariant id for service in SDC catalog. It
                            is of type STRING. By default, it is .
 -j | --cloud-region        cloud region id. It is of type STRING. By
                            default, it is .
 -x | --service-instace-id  unique id for service instance. It is of type
                            STRING. By default, it is .
 -r | --requestor-id        requestor ID. It is of type STRING. By default,
                            it is .
 -i | --model-uuid          model uuid for service in SDC catalog. It is of
                            type STRING. By default, it is .
 -c | --customer-name       unique id for customer. It is of type STRING. By
                            default, it is .
 -s | --long                whether to print all attributes or only short
                            attributes. It is of type BOOL.
 -D | --context             command context. It is of type MAP. It is
                            optional.
 -g | --model-version       model version of service (eg. 1.0). It is of type
                            STRING. By default, it is .
 -k | --tenant-id           tenant id. It is of type STRING. By default, it
                            is .
 -u | --host-username       Host user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_CLI_HOST_USERNAME.
 -a | --no-auth             Whether to authenticate user or not. It is of
                            type BOOL.
 -p | --host-password       Host user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[53] service-list
-----------------------------------------------



usage: oclip service-list

List all service instances from SO

Product: onap-dublin
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


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



[54] service-model-add-vf
-----------------------------------------------



usage: oclip service-model-add-vf

Helps to add VF into service models in SDC

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --service-model-id]
 [-b | --vf-version] [-y | --vf-id] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-z | --vf-name] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
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
 -a | --no-auth           Whether to authenticate user or not. It is of
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



[55] service-model-approve
-----------------------------------------------



usage: oclip service-model-approve

Approves the Service model in SDC

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-b | --service-model-uuid]
 [-r | --remarks] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url            host url in http(s). It is of type URL. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_URL.
 -C | --no-catalog          Whether to use given host-url directly or
                            discover it from catalog, it will override the
                            service->mode. It is of type BOOL. It is
                            optional.
 -f | --format              Output formats, supported formats such as table,
                            csv, json, yaml. It is of type STRING.
 -h | --help                print help message. It is of type BOOL.
 -V | --verify              Helps to verify the command using samples
                            provides under open-cli-samples directory. By
                            default, it goes with mock.To enable the
                            verification of samples in real time, set
                            DISABLE_MOCKING=true in the context parameter. It
                            is of type BOOL. It is optional.
 -t | --no-title            whether to print title or not. It is of type BOOL.
 -d | --debug               Enable debug output. It is of type BOOL.
 -v | --version             print service version. It is of type BOOL.
 -b | --service-model-uuid  Service model UUID (can be found by running
                            service-model-list). It is of type STRING. By
                            default, it is .
 -r | --remarks             approval remarks. It is of type STRING. By
                            default, it is .
 -s | --long                whether to print all attributes or only short
                            attributes. It is of type BOOL.
 -D | --context             command context. It is of type MAP. It is
                            optional.
 -u | --host-username       Host user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_CLI_HOST_USERNAME.
 -a | --no-auth             Whether to authenticate user or not. It is of
                            type BOOL.
 -p | --host-password       Host user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[56] service-model-checkin
-----------------------------------------------



usage: oclip service-model-checkin

Checkin Service model in SDC

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-b | --service-model-uuid]
 [-r | --remarks] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url            host url in http(s). It is of type URL. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_URL.
 -C | --no-catalog          Whether to use given host-url directly or
                            discover it from catalog, it will override the
                            service->mode. It is of type BOOL. It is
                            optional.
 -f | --format              Output formats, supported formats such as table,
                            csv, json, yaml. It is of type STRING.
 -h | --help                print help message. It is of type BOOL.
 -V | --verify              Helps to verify the command using samples
                            provides under open-cli-samples directory. By
                            default, it goes with mock.To enable the
                            verification of samples in real time, set
                            DISABLE_MOCKING=true in the context parameter. It
                            is of type BOOL. It is optional.
 -t | --no-title            whether to print title or not. It is of type BOOL.
 -d | --debug               Enable debug output. It is of type BOOL.
 -v | --version             print service version. It is of type BOOL.
 -b | --service-model-uuid  Service model UUID (can be found by running
                            service-model-list). It is of type STRING. By
                            default, it is .
 -r | --remarks             checkin remarks. It is of type STRING. By
                            default, it is .
 -s | --long                whether to print all attributes or only short
                            attributes. It is of type BOOL.
 -D | --context             command context. It is of type MAP. It is
                            optional.
 -u | --host-username       Host user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_CLI_HOST_USERNAME.
 -a | --no-auth             Whether to authenticate user or not. It is of
                            type BOOL.
 -p | --host-password       Host user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[57] service-model-checkout
-----------------------------------------------



usage: oclip service-model-checkout

Checkout Service model in SDC

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-b | --service-model-uuid]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url            host url in http(s). It is of type URL. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_URL.
 -C | --no-catalog          Whether to use given host-url directly or
                            discover it from catalog, it will override the
                            service->mode. It is of type BOOL. It is
                            optional.
 -f | --format              Output formats, supported formats such as table,
                            csv, json, yaml. It is of type STRING.
 -h | --help                print help message. It is of type BOOL.
 -V | --verify              Helps to verify the command using samples
                            provides under open-cli-samples directory. By
                            default, it goes with mock.To enable the
                            verification of samples in real time, set
                            DISABLE_MOCKING=true in the context parameter. It
                            is of type BOOL. It is optional.
 -t | --no-title            whether to print title or not. It is of type BOOL.
 -d | --debug               Enable debug output. It is of type BOOL.
 -v | --version             print service version. It is of type BOOL.
 -b | --service-model-uuid  Service model UUID (can be found from
                            service-model-list). It is of type STRING. It is
                            optional. By default, it is .
 -s | --long                whether to print all attributes or only short
                            attributes. It is of type BOOL.
 -D | --context             command context. It is of type MAP. It is
                            optional.
 -u | --host-username       Host user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_CLI_HOST_USERNAME.
 -a | --no-auth             Whether to authenticate user or not. It is of
                            type BOOL.
 -p | --host-password       Host user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[58] service-model-create
-----------------------------------------------



usage: oclip service-model-create

Create Service model in SDC

Product: onap-dublin
Service: sdc
Author: ONAP HPA Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-z | --project-code]
 [-y | --description] [-e | --icon-id] [-c | --category-display-name]
 [-s | --long] [-D | --context] [-x | --service-name]
 [-u | --host-username] [-a | --no-auth] [-b | --category]
 [-p | --host-password]

where::

 -m | --host-url                host url in http(s). It is of type URL. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_URL.
 -C | --no-catalog              Whether to use given host-url directly or
                                discover it from catalog, it will override the
                                service->mode. It is of type BOOL. It is
                                optional.
 -f | --format                  Output formats, supported formats such as table,
                                csv, json, yaml. It is of type STRING.
 -h | --help                    print help message. It is of type BOOL.
 -V | --verify                  Helps to verify the command using samples
                                provides under open-cli-samples directory. By
                                default, it goes with mock.To enable the
                                verification of samples in real time, set
                                DISABLE_MOCKING=true in the context parameter. It
                                is of type BOOL. It is optional.
 -t | --no-title                whether to print title or not. It is of type BOOL.
 -d | --debug                   Enable debug output. It is of type BOOL.
 -v | --version                 print service version. It is of type BOOL.
 -z | --project-code            Project code. It is of type STRING. It is
                                optional. By default, it is .
 -y | --description             Description for Service. It is of type STRING. It
                                is optional. By default, it is .
 -e | --icon-id                 Service Icon id (options are
                                "network_l_4","network_l_1-3"[use this icon for
                                "Network L1-3","E2E Service","Network
                                Service"],"mobility","call_controll"). It is of
                                type STRING. It is optional.
 -c | --category-display-name   Service category display name (Options are
                                "Network L4+","Network L1-3","E2E
                                Service","Network Service","Mobility","VoIP Call
                                Control"). It is of type STRING. It is optional.
 -s | --long                    whether to print all attributes or only short
                                attributes. It is of type BOOL.
 -D | --context                 command context. It is of type MAP. It is
                                optional.
 -x | --service-name            Onap Service Name. It is of type STRING. By
                                default, it is .
 -u | --host-username           Host user name. It is of type STRING. By default,
                                it is read from environment variable
                                OPEN_CLI_HOST_USERNAME.
 -a | --no-auth                 Whether to authenticate user or not. It is of
                                type BOOL.
 -b | --category                Service category (options are "network
                                l4+","network l1-3","e2e
                                service","mobility","network service","voip call
                                control"). It is of type STRING. It is optional.
 -p | --host-password           Host user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID   Service ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[59] service-model-distribute
-----------------------------------------------



usage: oclip service-model-distribute

Distributes the Service model in SDC

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-b | --service-model-uuid]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url            host url in http(s). It is of type URL. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_URL.
 -C | --no-catalog          Whether to use given host-url directly or
                            discover it from catalog, it will override the
                            service->mode. It is of type BOOL. It is
                            optional.
 -f | --format              Output formats, supported formats such as table,
                            csv, json, yaml. It is of type STRING.
 -h | --help                print help message. It is of type BOOL.
 -V | --verify              Helps to verify the command using samples
                            provides under open-cli-samples directory. By
                            default, it goes with mock.To enable the
                            verification of samples in real time, set
                            DISABLE_MOCKING=true in the context parameter. It
                            is of type BOOL. It is optional.
 -t | --no-title            whether to print title or not. It is of type BOOL.
 -d | --debug               Enable debug output. It is of type BOOL.
 -v | --version             print service version. It is of type BOOL.
 -b | --service-model-uuid  Service model UUID (can be found from
                            service-model-list). It is of type STRING. It is
                            optional. By default, it is .
 -s | --long                whether to print all attributes or only short
                            attributes. It is of type BOOL.
 -D | --context             command context. It is of type MAP. It is
                            optional.
 -u | --host-username       Host user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_CLI_HOST_USERNAME.
 -a | --no-auth             Whether to authenticate user or not. It is of
                            type BOOL.
 -p | --host-password       Host user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[60] service-model-list
-----------------------------------------------



usage: oclip service-model-list

List the service model in SDC

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
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



[61] service-model-test-accept
-----------------------------------------------



usage: oclip service-model-test-accept

Accepts the Service model test in SDC

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-r | --test-remark] [-b | --service-model-id]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -r | --test-remark       Remarks when accepting test. It is of type
                          STRING. By default, it is .
 -b | --service-model-id  Service model ID. It is of type STRING. By
                          default, it is .
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



[62] service-model-test-reject
-----------------------------------------------



usage: oclip service-model-test-reject

Rejects the Service model test in SDC

Product: onap-dublin
Service: sdc
Author: ONAP HPA Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-r | --test-remark] [-b | --service-model-id]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -r | --test-remark       Remarks when rejecting test. It is of type
                          STRING. By default, it is .
 -b | --service-model-id  Service model ID. It is of type STRING. By
                          default, it is .
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



[63] service-model-test-request
-----------------------------------------------



usage: oclip service-model-test-request

Request the certification of Service model in SDC

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-r | --remark]
 [-s | --long] [-D | --context] [-b | --service-model-id]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -r | --remark            Remarks when submitting for testing. It is of
                          type STRING. By default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional. By default, it is .
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



[64] service-model-test-start
-----------------------------------------------



usage: oclip service-model-test-start

Starts the testing of a Service model in SDC

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-b | --service-model-id] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional. By default, it is .
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



[65] service-model-undocheckout
-----------------------------------------------



usage: oclip service-model-undocheckout

Undo Checkout of Service model in SDC

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-b | --service-model-uuid]
 [-s | --long] [-D | --context] [-u | --host-username]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url            host url in http(s). It is of type URL. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_URL.
 -C | --no-catalog          Whether to use given host-url directly or
                            discover it from catalog, it will override the
                            service->mode. It is of type BOOL. It is
                            optional.
 -f | --format              Output formats, supported formats such as table,
                            csv, json, yaml. It is of type STRING.
 -h | --help                print help message. It is of type BOOL.
 -V | --verify              Helps to verify the command using samples
                            provides under open-cli-samples directory. By
                            default, it goes with mock.To enable the
                            verification of samples in real time, set
                            DISABLE_MOCKING=true in the context parameter. It
                            is of type BOOL. It is optional.
 -t | --no-title            whether to print title or not. It is of type BOOL.
 -d | --debug               Enable debug output. It is of type BOOL.
 -v | --version             print service version. It is of type BOOL.
 -b | --service-model-uuid  Service model UUIID (check service-model-list).
                            It is of type STRING. It is optional. By default,
                            it is .
 -s | --long                whether to print all attributes or only short
                            attributes. It is of type BOOL.
 -D | --context             command context. It is of type MAP. It is
                            optional.
 -u | --host-username       Host user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_CLI_HOST_USERNAME.
 -a | --no-auth             Whether to authenticate user or not. It is of
                            type BOOL.
 -p | --host-password       Host user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[66] service-show
-----------------------------------------------


0x7006::In service-show-schema-dublin.yaml, Parameter option s is in conflict, only one option is allowed with given name


[67] service-show-resource-module-name
-----------------------------------------------


0x7006::In service-show-resource-module-name-schema-dublin.yaml, Parameter option s is in conflict, only one option is allowed with given name


[68] service-type-create
-----------------------------------------------



usage: oclip service-type-create

Add a service type in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-x | --service-type]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-y | --service-type-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -x | --service-type      Onap service type. It is of type STRING. By
                          default, it is .
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -y | --service-type-id   Onap service type uuid. It is of type UUID. It is
                          optional.
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



[69] service-type-delete
-----------------------------------------------



usage: oclip service-type-delete

Delete a service type from Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-y | --resource-version]
 [-x | --service-type-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
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
 -a | --no-auth           Whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[70] service-type-list
-----------------------------------------------



usage: oclip service-type-list

List the service types configured in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
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



[71] service-update
-----------------------------------------------



usage: oclip service-update

Update a service instance using SO

Product: onap-dublin
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-V | --verify] [-f | --format] [-h | --help]
 [-t | --no-title] [-l | --cloud-region] [-w | --service-type]
 [-v | --version] [-i | --model-name] [-c | --customer]
 [-q | --requestor-id] [-s | --long] [-b | --tenant-id]
 [-k | --instance-name] [-r | --supress-rollback] [-a | --no-auth]
 [-n | --model-version-id] [-p | --host-password] [-m | --host-url]
 [-C | --no-catalog] [-e | --model-invariant-id] [-d | --debug]
 [-x | --service-instace-id] [-D | --context] [-j | --model-version]
 [-u | --host-username] [-g | --model-uuid]

where::

 -V | --verify              Helps to verify the command using samples
                            provides under open-cli-samples directory. By
                            default, it goes with mock.To enable the
                            verification of samples in real time, set
                            DISABLE_MOCKING=true in the context parameter. It
                            is of type BOOL. It is optional.
 -f | --format              Output formats, supported formats such as table,
                            csv, json, yaml. It is of type STRING.
 -h | --help                print help message. It is of type BOOL.
 -t | --no-title            whether to print title or not. It is of type BOOL.
 -l | --cloud-region        cloud region identifier. It is of type STRING. By
                            default, it is .
 -w | --service-type        subscription service type. It is of type STRING.
                            By default, it is .
 -v | --version             print service version. It is of type BOOL.
 -i | --model-name          model name as provided in ASDC design time. It is
                            of type STRING. By default, it is .
 -c | --customer            unique id for customer. It is of type STRING. By
                            default, it is .
 -q | --requestor-id        requestor ID. It is of type STRING. By default,
                            it is .
 -s | --long                whether to print all attributes or only short
                            attributes. It is of type BOOL.
 -b | --tenant-id           tenant id. It is of type STRING. By default, it
                            is .
 -k | --instance-name       service instance name. It is of type STRING. By
                            default, it is .
 -r | --supress-rollback    rollback changes if instantiation fails. It is of
                            type BOOL. It is optional.
 -a | --no-auth             Whether to authenticate user or not. It is of
                            type BOOL.
 -n | --model-version-id    model-version-id. It is of type STRING. By
                            default, it is .
 -p | --host-password       Host user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url            host url in http(s). It is of type URL. By
                            default, it is read from environment variable
                            OPEN_CLI_HOST_URL.
 -C | --no-catalog          Whether to use given host-url directly or
                            discover it from catalog, it will override the
                            service->mode. It is of type BOOL. It is
                            optional.
 -e | --model-invariant-id  model invariant id generated by ASDC. It is of
                            type STRING. By default, it is .
 -d | --debug               Enable debug output. It is of type BOOL.
 -x | --service-instace-id  unique id for service instance. It is of type
                            STRING. By default, it is .
 -D | --context             command context. It is of type MAP. It is
                            optional.
 -j | --model-version       model-version. It is of type STRING. By default,
                            it is .
 -u | --host-username       Host user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_CLI_HOST_USERNAME.
 -g | --model-uuid          model uuid generated by ASDC. It is of type
                            STRING. By default, it is .


Results::

 service-id   instance id for the created service. and is of
              type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[72] service2vf-model-list
-----------------------------------------------



usage: oclip service2vf-model-list

List the VF in a given service model in SDC

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-z | --service-model-id]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
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
 -z | --service-model-id  Service model uuid. It is of type STRING. By
                          default, it is .
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
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


Results::

 vf-uuid                UUID and is of type STRING.
 vf-name                name and is of type STRING.
 vf-customization-uuid  customization UUID and is of type STRING.
 vf-version             version and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[73] subscription-cloud-add
-----------------------------------------------



usage: oclip subscription-cloud-add

Add a new cloud region to a customer subscription

Product: onap-dublin
Service: aai
Author: Intel ONAP HPA integration team (itohan.ukponmwan@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --customer-name]
 [-c | --cloud-tenant-id] [-s | --long] [-D | --context]
 [-z | --cloud-owner] [-e | --service-type] [-u | --host-username]
 [-a | --no-auth] [-y | --tenant-name] [-r | --cloud-region]
 [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -x | --customer-name     Onap customer name. It is of type STRING. By
                          default, it is .
 -c | --cloud-tenant-id   Onap cloud tenant id. It is of type STRING. By
                          default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -z | --cloud-owner       Onap cloud owner name. It is of type STRING. By
                          default, it is .
 -e | --service-type      Onap service type. It is of type STRING. By
                          default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           Whether to authenticate user or not. It is of
                          type BOOL.
 -y | --tenant-name       name of tenant to use in the cloud region. It is
                          of type STRING. By default, it is .
 -r | --cloud-region      Onap cloud region. It is of type STRING. By
                          default, it is .
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[74] subscription-cloud-delete
-----------------------------------------------



usage: oclip subscription-cloud-delete

Delete a cloud region from a customer subscription

Product: onap-dublin
Service: aai
Author: Intel ONAP HPA integration team (onap-discuss@lists.onap.org)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --customer-name]
 [-c | --cloud-tenant-id] [-s | --long] [-D | --context]
 [-z | --cloud-owner] [-e | --service-type] [-u | --host-username]
 [-a | --no-auth] [-y | --tenant-name] [-r | --cloud-region]
 [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -x | --customer-name     Onap customer name. It is of type STRING. By
                          default, it is .
 -c | --cloud-tenant-id   Onap cloud tenant id. It is of type STRING. By
                          default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -z | --cloud-owner       Onap cloud owner name. It is of type STRING. By
                          default, it is .
 -e | --service-type      Onap service type. It is of type STRING. By
                          default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           Whether to authenticate user or not. It is of
                          type BOOL.
 -y | --tenant-name       name of tenant to use in the cloud region. It is
                          of type STRING. By default, it is .
 -r | --cloud-region      Onap cloud region. It is of type STRING. By
                          default, it is .
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[75] subscription-create
-----------------------------------------------



usage: oclip subscription-create

Create a subscription of a customer for given service in specific cloud region in Onap

Product: onap-dublin
Service: aai
Author: Intel ONAP HPA integration team (itohan.ukponmwan@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --customer-name]
 [-c | --cloud-tenant-id] [-s | --long] [-D | --context]
 [-z | --cloud-owner] [-e | --service-type] [-u | --host-username]
 [-a | --no-auth] [-y | --tenant-name] [-r | --cloud-region]
 [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -x | --customer-name     Onap customer name. It is of type STRING. By
                          default, it is .
 -c | --cloud-tenant-id   Onap cloud tenant id. It is of type STRING. By
                          default, it is .
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -z | --cloud-owner       Onap cloud owner name. It is of type STRING. By
                          default, it is .
 -e | --service-type      Onap service type. It is of type STRING. By
                          default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -a | --no-auth           Whether to authenticate user or not. It is of
                          type BOOL.
 -y | --tenant-name       name of tenant to use in the cloud region. It is
                          of type STRING. By default, it is .
 -r | --cloud-region      Onap cloud region. It is of type STRING. By
                          default, it is .
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[76] subscription-delete
-----------------------------------------------



usage: oclip subscription-delete

Delete the subscription for a given customer in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --customer-name]
 [-y | --service-type] [-s | --long] [-D | --context]
 [-g | --resource-version] [-u | --host-username] [-a | --no-auth]
 [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
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
 -a | --no-auth           Whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[77] subscription-list
-----------------------------------------------



usage: oclip subscription-list

Lists the subscription for a given customer in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --customer-name]
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
 -x | --customer-name   Onap customer name. It is of type STRING. By
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

 service-type       Onap service type and is of type STRING.
 resource-version   Onap subscription resource version and is of type
                    STRING.
 tenant             Onap tenant name and is of type STRING.
 region             Onap region name and is of type STRING.
 cloud              Onap cloud name and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[78] tenant-create
-----------------------------------------------



usage: oclip tenant-create

Create a tenant under given cloud region in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --cloud]
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
 -x | --cloud           Onap cloud. It is of type STRING. By default, it
                        is .
 -z | --tenant-id       Onap cloud tenant id. It is of type UUID.
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



[79] tenant-delete
-----------------------------------------------



usage: oclip tenant-delete

Delete tenant under given cloud region in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --cloud]
 [-z | --tenant-id] [-r | --resource-version] [-y | --region]
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
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -x | --cloud             Onap cloud. It is of type STRING. By default, it
                          is .
 -z | --tenant-id         Onap cloud tenant id. It is of type STRING. By
                          default, it is .
 -r | --resource-version  Onap cloud tenant version. It is of type UUID. It
                          is optional.
 -y | --region            Onap cloud region. It is of type STRING. By
                          default, it is .
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



[80] tenant-list
-----------------------------------------------



usage: oclip tenant-list

Lists the tenants under given cloud region in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --cloud]
 [-y | --region] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

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
 -a | --no-auth         Whether to authenticate user or not. It is of
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



[81] vf-model-certify
-----------------------------------------------



usage: oclip vf-model-certify

Certify a Virtual function

Product: onap-dublin
Service: sdc
Author: ONAP HPA Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-b | --vf-id]
 [-r | --remarks] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

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
 -b | --vf-id           VF ID. It is of type STRING. By default, it is .
 -r | --remarks         certification remarks. It is of type STRING. It
                        is optional. By default, it is .
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



[82] vf-model-create
-----------------------------------------------



usage: oclip vf-model-create

Create Virtual function from Vendor Software Product

Product: onap-dublin
Service: sdc
Author: ONAP HPA Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-y | --description]
 [-g | --vsp-version] [-x | --name] [-s | --long]
 [-D | --context] [-z | --vendor-name] [-u | --host-username]
 [-a | --no-auth] [-b | --vsp-id] [-p | --host-password]

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
 -y | --description     Description for VF. It is of type STRING. It is
                        optional. By default, it is .
 -g | --vsp-version     VSP version. It is of type STRING. By default, it
                        is .
 -x | --name            Onap VF Name. It is of type STRING. By default,
                        it is .
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -z | --vendor-name     Vendor name. It is of type STRING. By default, it
                        is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -b | --vsp-id          VSP ID. It is of type STRING. By default, it is .
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID   VF ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[83] vf-model-list
-----------------------------------------------



usage: oclip vf-model-list

List the VF resource model in SDC

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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



[84] vf-module-create
-----------------------------------------------



usage: oclip vf-module-create

Create a VF Module

Product: onap-dublin
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-w | --tenant-id] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-y | --vnf-instace-id] [-z | --parenet-service-model] [-x | --service-instace-id]
 [-l | --lcp-cloudregion-id] [-s | --long] [-D | --context]
 [-i | --instance-name] [-u | --host-username] [-r | --supress-rollback]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url                host url in http(s). It is of type URL. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_URL.
 -C | --no-catalog              Whether to use given host-url directly or
                                discover it from catalog, it will override the
                                service->mode. It is of type BOOL. It is
                                optional.
 -f | --format                  Output formats, supported formats such as table,
                                csv, json, yaml. It is of type STRING.
 -w | --tenant-id               openstack tenant id (uuid). It is of type STRING.
                                By default, it is .
 -h | --help                    print help message. It is of type BOOL.
 -V | --verify                  Helps to verify the command using samples
                                provides under open-cli-samples directory. By
                                default, it goes with mock.To enable the
                                verification of samples in real time, set
                                DISABLE_MOCKING=true in the context parameter. It
                                is of type BOOL. It is optional.
 -t | --no-title                whether to print title or not. It is of type BOOL.
 -d | --debug                   Enable debug output. It is of type BOOL.
 -v | --version                 print service version. It is of type BOOL.
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
 -a | --no-auth                 Whether to authenticate user or not. It is of
                                type BOOL.
 -p | --host-password           Host user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[85] vf-module-list
-----------------------------------------------



usage: oclip vf-module-list

List all VF Modules

Product: onap-dublin
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-w | --tenant-id] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-y | --vnf-instace-id] [-z | --parenet-service-model] [-x | --service-instace-id]
 [-l | --lcp-cloudregion-id] [-s | --long] [-D | --context]
 [-i | --instance-name] [-u | --host-username] [-r | --supress-rollback]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url                host url in http(s). It is of type URL. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_URL.
 -C | --no-catalog              Whether to use given host-url directly or
                                discover it from catalog, it will override the
                                service->mode. It is of type BOOL. It is
                                optional.
 -f | --format                  Output formats, supported formats such as table,
                                csv, json, yaml. It is of type STRING.
 -w | --tenant-id               openstack tenant id (uuid). It is of type STRING.
                                By default, it is .
 -h | --help                    print help message. It is of type BOOL.
 -V | --verify                  Helps to verify the command using samples
                                provides under open-cli-samples directory. By
                                default, it goes with mock.To enable the
                                verification of samples in real time, set
                                DISABLE_MOCKING=true in the context parameter. It
                                is of type BOOL. It is optional.
 -t | --no-title                whether to print title or not. It is of type BOOL.
 -d | --debug                   Enable debug output. It is of type BOOL.
 -v | --version                 print service version. It is of type BOOL.
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
 -a | --no-auth                 Whether to authenticate user or not. It is of
                                type BOOL.
 -p | --host-password           Host user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[86] vf-module-show
-----------------------------------------------



usage: oclip vf-module-show

Show a VF Module

Product: onap-dublin
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


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



[87] vf-module-update
-----------------------------------------------



usage: oclip vf-module-update

Update a VF Module

Product: onap-dublin
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-w | --tenant-id] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-y | --vnf-instace-id] [-z | --parenet-service-model] [-x | --service-instace-id]
 [-l | --lcp-cloudregion-id] [-s | --long] [-D | --context]
 [-i | --instance-name] [-u | --host-username] [-r | --supress-rollback]
 [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url                host url in http(s). It is of type URL. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_URL.
 -C | --no-catalog              Whether to use given host-url directly or
                                discover it from catalog, it will override the
                                service->mode. It is of type BOOL. It is
                                optional.
 -f | --format                  Output formats, supported formats such as table,
                                csv, json, yaml. It is of type STRING.
 -w | --tenant-id               openstack tenant id (uuid). It is of type STRING.
                                By default, it is .
 -h | --help                    print help message. It is of type BOOL.
 -V | --verify                  Helps to verify the command using samples
                                provides under open-cli-samples directory. By
                                default, it goes with mock.To enable the
                                verification of samples in real time, set
                                DISABLE_MOCKING=true in the context parameter. It
                                is of type BOOL. It is optional.
 -t | --no-title                whether to print title or not. It is of type BOOL.
 -d | --debug                   Enable debug output. It is of type BOOL.
 -v | --version                 print service version. It is of type BOOL.
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
 -a | --no-auth                 Whether to authenticate user or not. It is of
                                type BOOL.
 -p | --host-password           Host user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[88] vf-preload
-----------------------------------------------



usage: oclip vf-preload

Preload SDNC with parameter values for a VF module in ONAP

Product: onap-dublin
Service: sdnc
Author: Intel ONAP HPA integration team (itohan.ukponmwan@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-y | --preload-file]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
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
 -y | --preload-file    File containing preload values. It is of type
                        TEXT. By default, it is .
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[89] vfc-catalog-delete-ns
-----------------------------------------------



usage: oclip vfc-catalog-delete-ns

vfc delete onboard ns from catalog of vfc

Product: onap-dublin
Service: vfc
Author: ONAP HPA Integration Team (haibin.huang@intel.com)

Options::

 [-m | --host-url] [-c | --ns-csar-uuid] [-C | --no-catalog]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-s | --long] [-D | --context]

where::

 -m | --host-url      host url in http(s). It is of type URL. By
                      default, it is read from environment variable
                      OPEN_CLI_HOST_URL.
 -c | --ns-csar-uuid  ns's uuid of csar file. It is of type STRING. By
                      default, it is .
 -C | --no-catalog    Whether to use given host-url directly or
                      discover it from catalog, it will override the
                      service->mode. It is of type BOOL. It is
                      optional.
 -f | --format        Output formats, supported formats such as table,
                      csv, json, yaml. It is of type STRING.
 -h | --help          print help message. It is of type BOOL.
 -V | --verify        Helps to verify the command using samples
                      provides under open-cli-samples directory. By
                      default, it goes with mock.To enable the
                      verification of samples in real time, set
                      DISABLE_MOCKING=true in the context parameter. It
                      is of type BOOL. It is optional.
 -t | --no-title      whether to print title or not. It is of type BOOL.
 -d | --debug         Enable debug output. It is of type BOOL.
 -v | --version       print service version. It is of type BOOL.
 -s | --long          whether to print all attributes or only short
                      attributes. It is of type BOOL.
 -D | --context       command context. It is of type MAP. It is
                      optional.


Results::

 job-id   job-id and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[90] vfc-catalog-delete-vnf
-----------------------------------------------



usage: oclip vfc-catalog-delete-vnf

vfc delete onboard vnf to catalog of vfc

Product: onap-dublin
Service: vfc
Author: ONAP HPA Integration Team (haibin.huang@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-c | --vnf-csar-uuid]

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
 -c | --vnf-csar-uuid   vnf's uuid of csar file. It is of type STRING. By
                        default, it is .


Results::

 job-id   job-id and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[91] vfc-catalog-get-ns
-----------------------------------------------



usage: oclip vfc-catalog-get-ns

vfc get onboard ns status

Product: onap-dublin
Service: vfc
Author: ONAP HPA Integration Team (haibin.huang@intel.com)

Options::

 [-m | --host-url] [-c | --ns-csar-uuid] [-C | --no-catalog]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-s | --long] [-D | --context]

where::

 -m | --host-url      host url in http(s). It is of type URL. By
                      default, it is read from environment variable
                      OPEN_CLI_HOST_URL.
 -c | --ns-csar-uuid  ns's uuid of csar file. It is of type STRING. By
                      default, it is .
 -C | --no-catalog    Whether to use given host-url directly or
                      discover it from catalog, it will override the
                      service->mode. It is of type BOOL. It is
                      optional.
 -f | --format        Output formats, supported formats such as table,
                      csv, json, yaml. It is of type STRING.
 -h | --help          print help message. It is of type BOOL.
 -V | --verify        Helps to verify the command using samples
                      provides under open-cli-samples directory. By
                      default, it goes with mock.To enable the
                      verification of samples in real time, set
                      DISABLE_MOCKING=true in the context parameter. It
                      is of type BOOL. It is optional.
 -t | --no-title      whether to print title or not. It is of type BOOL.
 -d | --debug         Enable debug output. It is of type BOOL.
 -v | --version       print service version. It is of type BOOL.
 -s | --long          whether to print all attributes or only short
                      attributes. It is of type BOOL.
 -D | --context       command context. It is of type MAP. It is
                      optional.


Results::

 nsd-invariant-id   nsd-invariant-id and is of type STRING.
 ns-package-id      ns-package-id and is of type STRING.
 nsd-provider       nsd-provider and is of type STRING.
 nsd-id             nsd-id and is of type STRING.
 download-url       download-url and is of type STRING.
 csar-name          csar-name and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[92] vfc-catalog-get-vnf
-----------------------------------------------



usage: oclip vfc-catalog-get-vnf

vfc get onboard vnf status

Product: onap-dublin
Service: vfc
Author: ONAP HPA Integration Team (haibin.huang@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-c | --vnf-csar-uuid]

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
 -c | --vnf-csar-uuid   vnf's uuid of csar file. It is of type STRING. By
                        default, it is .


Results::

 vnfd-id          vnfd-id and is of type STRING.
 vnf-package-id   vnf-package-id and is of type STRING.
 vnfd-provider    vnfd-provider and is of type STRING.
 vnfd-version     vnfd-version and is of type STRING.
 vnf-version      vnf-version and is of type STRING.
 csar-name        csar-name and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[93] vfc-catalog-onboard-ns
-----------------------------------------------



usage: oclip vfc-catalog-onboard-ns

vfc onboard ns to catalog of vfc

Product: onap-dublin
Service: vfc
Author: ONAP HPA Integration Team (haibin.huang@intel.com)

Options::

 [-m | --host-url] [-c | --ns-csar-uuid] [-C | --no-catalog]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-s | --long] [-D | --context]

where::

 -m | --host-url      host url in http(s). It is of type URL. By
                      default, it is read from environment variable
                      OPEN_CLI_HOST_URL.
 -c | --ns-csar-uuid  ns's uuid of csar file. It is of type STRING. By
                      default, it is .
 -C | --no-catalog    Whether to use given host-url directly or
                      discover it from catalog, it will override the
                      service->mode. It is of type BOOL. It is
                      optional.
 -f | --format        Output formats, supported formats such as table,
                      csv, json, yaml. It is of type STRING.
 -h | --help          print help message. It is of type BOOL.
 -V | --verify        Helps to verify the command using samples
                      provides under open-cli-samples directory. By
                      default, it goes with mock.To enable the
                      verification of samples in real time, set
                      DISABLE_MOCKING=true in the context parameter. It
                      is of type BOOL. It is optional.
 -t | --no-title      whether to print title or not. It is of type BOOL.
 -d | --debug         Enable debug output. It is of type BOOL.
 -v | --version       print service version. It is of type BOOL.
 -s | --long          whether to print all attributes or only short
                      attributes. It is of type BOOL.
 -D | --context       command context. It is of type MAP. It is
                      optional.


Results::

 status       status and is of type STRING.
 status-desc  status-desc and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[94] vfc-catalog-onboard-vnf
-----------------------------------------------



usage: oclip vfc-catalog-onboard-vnf

vfc onboard vnf to catalog of vfc

Product: onap-dublin
Service: vfc
Author: ONAP HPA Integration Team (haibin.huang@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-c | --vnf-csar-uuid]

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
 -c | --vnf-csar-uuid   vnf's uuid of csar file. It is of type STRING. By
                        default, it is .


Results::

 job-id   job-id and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[95] vfc-nslcm-create
-----------------------------------------------



usage: oclip vfc-nslcm-create

vfc nslcm create ns

Product: onap-dublin
Service: vfc
Author: ONAP HPA Integration Team (haibin.huang@intel.com)

Options::

 [-n | --ns-csar-name] [-m | --host-url] [-c | --ns-csar-uuid]
 [-C | --no-catalog] [-f | --format] [-h | --help]
 [-V | --verify] [-t | --no-title] [-d | --debug]
 [-v | --version] [-s | --long] [-D | --context]

where::

 -n | --ns-csar-name  ns's name of csar. It is of type STRING. By
                      default, it is .
 -m | --host-url      host url in http(s). It is of type URL. By
                      default, it is read from environment variable
                      OPEN_CLI_HOST_URL.
 -c | --ns-csar-uuid  ns's uuid of csar file. It is of type STRING. By
                      default, it is .
 -C | --no-catalog    Whether to use given host-url directly or
                      discover it from catalog, it will override the
                      service->mode. It is of type BOOL. It is
                      optional.
 -f | --format        Output formats, supported formats such as table,
                      csv, json, yaml. It is of type STRING.
 -h | --help          print help message. It is of type BOOL.
 -V | --verify        Helps to verify the command using samples
                      provides under open-cli-samples directory. By
                      default, it goes with mock.To enable the
                      verification of samples in real time, set
                      DISABLE_MOCKING=true in the context parameter. It
                      is of type BOOL. It is optional.
 -t | --no-title      whether to print title or not. It is of type BOOL.
 -d | --debug         Enable debug output. It is of type BOOL.
 -v | --version       print service version. It is of type BOOL.
 -s | --long          whether to print all attributes or only short
                      attributes. It is of type BOOL.
 -D | --context       command context. It is of type MAP. It is
                      optional.


Results::

 ns-instance-id   ns-instance-id and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[96] vfc-nslcm-delete
-----------------------------------------------



usage: oclip vfc-nslcm-delete

vfc nslcm delete ns

Product: onap-dublin
Service: vfc
Author: ONAP HPA Integration Team (haibin.huang@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-c | --ns-instance-id]

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
 -c | --ns-instance-id  ns's instance id. It is of type STRING. By
                        default, it is .


Results::

 status   status and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[97] vfc-nslcm-get
-----------------------------------------------



usage: oclip vfc-nslcm-get

vfc nsclm get the status of creating ns

Product: onap-dublin
Service: vfc
Author: ONAP HPA Integration Team (haibin.huang@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
 [-D | --context] [-c | --ns-instance-id]

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
 -c | --ns-instance-id  ns's instance id. It is of type STRING. By
                        default, it is .


Results::

 ns-instance-id   ns-instance-id and is of type STRING.
 ns-name          ns-name and is of type STRING.
 description      description and is of type STRING.
 nsd-id           nsd-id and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[98] vfc-nslcm-instantiate
-----------------------------------------------



usage: oclip vfc-nslcm-instantiate

vfc nslcm instantiate ns

Product: onap-dublin
Service: vfc
Author: ONAP HPA Integration Team (haibin.huang@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-c | --location-constraints]
 [-s | --long] [-D | --context] [-i | --ns-instance-id]
 [-n | --sdn-controller-id]

where::

 -m | --host-url              host url in http(s). It is of type URL. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_URL.
 -C | --no-catalog            Whether to use given host-url directly or
                              discover it from catalog, it will override the
                              service->mode. It is of type BOOL. It is
                              optional.
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -h | --help                  print help message. It is of type BOOL.
 -V | --verify                Helps to verify the command using samples
                              provides under open-cli-samples directory. By
                              default, it goes with mock.To enable the
                              verification of samples in real time, set
                              DISABLE_MOCKING=true in the context parameter. It
                              is of type BOOL. It is optional.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -d | --debug                 Enable debug output. It is of type BOOL.
 -v | --version               print service version. It is of type BOOL.
 -c | --location-constraints  localtion constraints. It is of type JSON. It's
                              recommended to input the complete path of the
                              file, which is having the value for it.
 -s | --long                  whether to print all attributes or only short
                              attributes. It is of type BOOL.
 -D | --context               command context. It is of type MAP. It is
                              optional.
 -i | --ns-instance-id        ns's instance id. It is of type STRING. By
                              default, it is .
 -n | --sdn-controller-id     sdn controller id. It is of type STRING. By
                              default, it is .


Results::

 job-id   job id and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[99] vfmodule-delete
-----------------------------------------------



usage: oclip vfmodule-delete

delete a VF module (experimental)

Product: onap-dublin
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-l | --cloud-region]
 [-j | --service-id] [-z | --tenant-id] [-y | --vf-id]
 [-k | --vf-model-invariant-id] [-s | --long] [-D | --context]
 [-g | --vfmodule-version] [-x | --vfmodule-id] [-e | --vfmodule-name]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url                host url in http(s). It is of type URL. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_URL.
 -C | --no-catalog              Whether to use given host-url directly or
                                discover it from catalog, it will override the
                                service->mode. It is of type BOOL. It is
                                optional.
 -f | --format                  Output formats, supported formats such as table,
                                csv, json, yaml. It is of type STRING.
 -h | --help                    print help message. It is of type BOOL.
 -V | --verify                  Helps to verify the command using samples
                                provides under open-cli-samples directory. By
                                default, it goes with mock.To enable the
                                verification of samples in real time, set
                                DISABLE_MOCKING=true in the context parameter. It
                                is of type BOOL. It is optional.
 -t | --no-title                whether to print title or not. It is of type BOOL.
 -d | --debug                   Enable debug output. It is of type BOOL.
 -v | --version                 print service version. It is of type BOOL.
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
 -a | --no-auth                 Whether to authenticate user or not. It is of
                                type BOOL.
 -p | --host-password           Host user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[100] vfn-create
-----------------------------------------------



usage: oclip vfn-create

Create a VNF

Product: onap-dublin
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-j | --service-model-invariant-id] [-q | --service-model-name] [-k | --service-model-uuid]
 [-V | --verify] [-f | --format] [-h | --help]
 [-t | --no-title] [-l | --cloud-region] [-v | --version]
 [-y | --service-instance-id] [-z | --tenant-id] [-r | --requestor-id]
 [-c | --vf-model-uuid] [-o | --instance-name] [-s | --long]
 [-e | --vf-model-name] [-g | --vf-model-version] [-b | --vf-model-invariant-id]
 [-n | --service-model-version] [-a | --no-auth] [-p | --host-password]
 [-m | --host-url] [-i | --vf-model-customization-id] [-C | --no-catalog]
 [-d | --debug] [-D | --context] [-w | --product-family]
 [-u | --host-username]

where::

 -j | --service-model-invariant-id  model invariant id. It is of type STRING. By
                                    default, it is .
 -q | --service-model-name          service model name. It is of type STRING. By
                                    default, it is .
 -k | --service-model-uuid          model name version id. It is of type STRING. By
                                    default, it is .
 -V | --verify                      Helps to verify the command using samples
                                    provides under open-cli-samples directory. By
                                    default, it goes with mock.To enable the
                                    verification of samples in real time, set
                                    DISABLE_MOCKING=true in the context parameter. It
                                    is of type BOOL. It is optional.
 -f | --format                      Output formats, supported formats such as table,
                                    csv, json, yaml. It is of type STRING.
 -h | --help                        print help message. It is of type BOOL.
 -t | --no-title                    whether to print title or not. It is of type BOOL.
 -l | --cloud-region                cloud region identifier. It is of type STRING. By
                                    default, it is .
 -v | --version                     print service version. It is of type BOOL.
 -y | --service-instance-id         unique id for service instance. It is of type
                                    STRING. By default, it is .
 -z | --tenant-id                   openstack tenant id. It is of type STRING. By
                                    default, it is .
 -r | --requestor-id                requestor ID. It is of type STRING. By default,
                                    it is .
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
 -n | --service-model-version       service model version. It is of type STRING. By
                                    default, it is .
 -a | --no-auth                     Whether to authenticate user or not. It is of
                                    type BOOL.
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -i | --vf-model-customization-id   vf model customization id. It is of type STRING.
                                    By default, it is .
 -C | --no-catalog                  Whether to use given host-url directly or
                                    discover it from catalog, it will override the
                                    service->mode. It is of type BOOL. It is
                                    optional.
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



[101] vim-create
-----------------------------------------------



usage: oclip vim-create

Register a VIM under a given cloud region in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-e | --vendor] [-V | --verify] [-f | --format]
 [-h | --help] [-t | --no-title] [-x | --cloud-name]
 [-v | --version] [-s | --long] [-k | --ssl-cacert]
 [-y | --region-name] [-j | --password] [-b | --name]
 [-a | --no-auth] [-n | --cloud-domain] [-z | --vim-id]
 [-p | --host-password] [-m | --host-url] [-C | --no-catalog]
 [-d | --debug] [-q | --vim-version] [-l | --ssl-insecure]
 [-c | --type] [-o | --default-tenant] [-D | --context]
 [-i | --username] [-u | --host-username] [-g | --url]

where::

 -e | --vendor          Onap VIM vendor. It is of type STRING. By
                        default, it is .
 -V | --verify          Helps to verify the command using samples
                        provides under open-cli-samples directory. By
                        default, it goes with mock.To enable the
                        verification of samples in real time, set
                        DISABLE_MOCKING=true in the context parameter. It
                        is of type BOOL. It is optional.
 -f | --format          Output formats, supported formats such as table,
                        csv, json, yaml. It is of type STRING.
 -h | --help            print help message. It is of type BOOL.
 -t | --no-title        whether to print title or not. It is of type BOOL.
 -x | --cloud-name      Onap cloud name. It is of type STRING. By
                        default, it is .
 -v | --version         print service version. It is of type BOOL.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -k | --ssl-cacert      Onap VIM SSL certificate. It is of type TEXT. It
                        is optional. By default, it is .
 -y | --region-name     Onap region name. It is of type STRING. By
                        default, it is .
 -j | --password        Onap VIM password. It is of type STRING. By
                        default, it is .
 -b | --name            Onap VIM name. It is of type STRING. By default,
                        it is .
 -a | --no-auth         Whether to authenticate user or not. It is of
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
 -C | --no-catalog      Whether to use given host-url directly or
                        discover it from catalog, it will override the
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



[102] vim-delete
-----------------------------------------------



usage: oclip vim-delete

Un-register a VIM under from cloud region in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --cloud-name]
 [-s | --long] [-D | --context] [-y | --region-name]
 [-u | --host-username] [-a | --no-auth] [-z | --vim-id]
 [-p | --host-password] [-b | --resource-version]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
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
 -a | --no-auth           Whether to authenticate user or not. It is of
                          type BOOL.
 -z | --vim-id            Onap VIM unique id. It is of type UUID.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.
 -b | --resource-version  Onap vim resource version. It is of type STRING.
                          It is optional. By default, it is .


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[103] vim-list
-----------------------------------------------



usage: oclip vim-list

List the configured vims

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --cloud-name]
 [-s | --long] [-D | --context] [-y | --region-name]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

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
 -a | --no-auth         Whether to authenticate user or not. It is of
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



[104] vlm-aggreement-create
-----------------------------------------------



usage: oclip vlm-aggreement-create

Create license aggreement

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-y | --vlm-id] [-C | --no-catalog]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
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
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -h | --help                  print help message. It is of type BOOL.
 -V | --verify                Helps to verify the command using samples
                              provides under open-cli-samples directory. By
                              default, it goes with mock.To enable the
                              verification of samples in real time, set
                              DISABLE_MOCKING=true in the context parameter. It
                              is of type BOOL. It is optional.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -d | --debug                 Enable debug output. It is of type BOOL.
 -v | --version               print service version. It is of type BOOL.
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



[105] vlm-create
-----------------------------------------------



usage: oclip vlm-create

Create License Model

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-y | --description]
 [-s | --long] [-D | --context] [-x | --vendor-name]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 ID       License Model ID and is of type UUID.
 version  License Model version and is of type UUID.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[106] vlm-delete
-----------------------------------------------



usage: oclip vlm-delete

Delete License Model (certified vlms cannot be deleted)

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-y | --vlm-id] [-C | --no-catalog]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-s | --long] [-D | --context] [-x | --vendor-name]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url        host url in http(s). It is of type URL. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_URL.
 -y | --vlm-id          vlm id. It is of type STRING. By default, it is .
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
 -x | --vendor-name     vendor name. It is of type STRING. By default, it
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



[107] vlm-entitlement-pool-create
-----------------------------------------------



usage: oclip vlm-entitlement-pool-create

Create Entitlement Pool

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-y | --vlm-id] [-C | --no-catalog]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-z | --description] [-k | --manufacture-reference-number] [-g | --expiryDate]
 [-x | --name] [-s | --long] [-D | --context]
 [-l | --startDate] [-u | --host-username] [-a | --no-auth]
 [-e | --vlm-version] [-p | --host-password]

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
 -f | --format                        Output formats, supported formats such as table,
                                      csv, json, yaml. It is of type STRING.
 -h | --help                          print help message. It is of type BOOL.
 -V | --verify                        Helps to verify the command using samples
                                      provides under open-cli-samples directory. By
                                      default, it goes with mock.To enable the
                                      verification of samples in real time, set
                                      DISABLE_MOCKING=true in the context parameter. It
                                      is of type BOOL. It is optional.
 -t | --no-title                      whether to print title or not. It is of type BOOL.
 -d | --debug                         Enable debug output. It is of type BOOL.
 -v | --version                       print service version. It is of type BOOL.
 -z | --description                   Description for Entitlement Pool. It is of type
                                      STRING. By default, it is .
 -k | --manufacture-reference-number  Manufature Reference Number. It is of type
                                      STRING. By default, it is .
 -g | --expiryDate                    License end date (MM/DD/YYYY). It is of type
                                      STRING. By default, it is .
 -x | --name                          Entitlement Pool name. It is of type STRING. By
                                      default, it is .
 -s | --long                          whether to print all attributes or only short
                                      attributes. It is of type BOOL.
 -D | --context                       command context. It is of type MAP. It is
                                      optional.
 -l | --startDate                     License start date (MM/DD/YYYY). It is of type
                                      STRING. By default, it is .
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



[108] vlm-feature-group-create
-----------------------------------------------



usage: oclip vlm-feature-group-create

Create feature group Pool

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-y | --vlm-id] [-C | --no-catalog]
 [-f | --format] [-h | --help] [-c | --part-number]
 [-V | --verify] [-t | --no-title] [-d | --debug]
 [-v | --version] [-z | --description] [-b | --vlm-entitle-pool-id]
 [-x | --name] [-g | --vlm-key-group-id] [-s | --long]
 [-D | --context] [-u | --host-username] [-a | --no-auth]
 [-e | --vlm-version] [-p | --host-password]

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
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -h | --help                  print help message. It is of type BOOL.
 -c | --part-number           Part number. It is of type STRING. By default, it
                              is .
 -V | --verify                Helps to verify the command using samples
                              provides under open-cli-samples directory. By
                              default, it goes with mock.To enable the
                              verification of samples in real time, set
                              DISABLE_MOCKING=true in the context parameter. It
                              is of type BOOL. It is optional.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -d | --debug                 Enable debug output. It is of type BOOL.
 -v | --version               print service version. It is of type BOOL.
 -z | --description           Description for feature group. It is of type
                              STRING. By default, it is .
 -b | --vlm-entitle-pool-id   VLM Entitlement pool. It is of type STRING. By
                              default, it is .
 -x | --name                  Feature group name. It is of type STRING. By
                              default, it is .
 -g | --vlm-key-group-id      VLM keygroup. It is of type STRING. By default,
                              it is .
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

 ID   Feature group ID and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[109] vlm-key-group-create
-----------------------------------------------



usage: oclip vlm-key-group-create

Create License Key Group

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-z | --description]
 [-x | --name] [-y | --type] [-s | --long]
 [-D | --context] [-c | --vlm-id] [-u | --host-username]
 [-a | --no-auth] [-e | --vlm-version] [-p | --host-password]

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
 -z | --description     Description for License Key Group. It is of type
                        STRING. It is optional. By default, it is .
 -x | --name            name. It is of type STRING. By default, it is .
 -y | --type            type of group (Universal, unique, one-time). It
                        is of type STRING.
 -s | --long            whether to print all attributes or only short
                        attributes. It is of type BOOL.
 -D | --context         command context. It is of type MAP. It is
                        optional.
 -c | --vlm-id          License Model Id. It is of type STRING. By
                        default, it is .
 -u | --host-username   Host user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_CLI_HOST_USERNAME.
 -a | --no-auth         Whether to authenticate user or not. It is of
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



[110] vlm-list
-----------------------------------------------



usage: oclip vlm-list

List the vendor license models in ONAP

Product: onap-dublin
Service: sdc
Author: ONAP HPA Integration Team (itohan.ukponmwan@intel.com)

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
                        type BOOL.
 -p | --host-password   Host user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_CLI_HOST_PASSWORD. Secured.


Results::

 vlm-name   name of the vendor license model and is of type
            STRING.
 vlm-id     ID of the vendor license model and is of type
            STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[111] vlm-submit
-----------------------------------------------



usage: oclip vlm-submit

Submit Vendor License Model

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-y | --vlm-version]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
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
 -y | --vlm-version     VLM version. It is of type STRING. By default, it
                        is .
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
 -x | --vlm-id          Onap VLM ID. It is of type STRING. By default, it
                        is .
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



[112] vnf-delete
-----------------------------------------------



usage: oclip vnf-delete

Delete a VNF

Product: onap-dublin
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-e | --model-name] [-C | --no-catalog]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-l | --cloud-region] [-b | --model-invariant-id] [-y | --service-instance-id]
 [-z | --tenant-id] [-s | --long] [-D | --context]
 [-g | --model-version] [-c | --model-uuid] [-u | --host-username]
 [-a | --no-auth] [-x | --vf-id] [-p | --host-password]

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
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -h | --help                  print help message. It is of type BOOL.
 -V | --verify                Helps to verify the command using samples
                              provides under open-cli-samples directory. By
                              default, it goes with mock.To enable the
                              verification of samples in real time, set
                              DISABLE_MOCKING=true in the context parameter. It
                              is of type BOOL. It is optional.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -d | --debug                 Enable debug output. It is of type BOOL.
 -v | --version               print service version. It is of type BOOL.
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
 -x | --vf-id                 id for vnf. It is of type STRING. By default, it
                              is .
 -p | --host-password         Host user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[113] vnf-list
-----------------------------------------------



usage: oclip vnf-list

List all VNFs

Product: onap-dublin
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-j | --service-model-invariant-id] [-q | --service-model-name] [-k | --service-model-uuid]
 [-V | --verify] [-f | --format] [-h | --help]
 [-t | --no-title] [-l | --cloud-region] [-v | --version]
 [-y | --service-instance-id] [-z | --tenant-id] [-r | --requestor-id]
 [-c | --vf-model-uuid] [-o | --instance-name] [-s | --long]
 [-e | --vf-model-name] [-g | --vf-model-version] [-b | --vf-model-invariant-id]
 [-n | --service-model-version] [-a | --no-auth] [-p | --host-password]
 [-m | --host-url] [-i | --vf-model-customization-id] [-C | --no-catalog]
 [-d | --debug] [-D | --context] [-w | --product-family]
 [-u | --host-username]

where::

 -j | --service-model-invariant-id  model invariant id. It is of type STRING. By
                                    default, it is .
 -q | --service-model-name          service model name. It is of type STRING. By
                                    default, it is .
 -k | --service-model-uuid          model name version id. It is of type STRING. By
                                    default, it is .
 -V | --verify                      Helps to verify the command using samples
                                    provides under open-cli-samples directory. By
                                    default, it goes with mock.To enable the
                                    verification of samples in real time, set
                                    DISABLE_MOCKING=true in the context parameter. It
                                    is of type BOOL. It is optional.
 -f | --format                      Output formats, supported formats such as table,
                                    csv, json, yaml. It is of type STRING.
 -h | --help                        print help message. It is of type BOOL.
 -t | --no-title                    whether to print title or not. It is of type BOOL.
 -l | --cloud-region                cloud region identifier. It is of type STRING. By
                                    default, it is .
 -v | --version                     print service version. It is of type BOOL.
 -y | --service-instance-id         unique id for service instance. It is of type
                                    STRING. By default, it is .
 -z | --tenant-id                   openstack tenant id. It is of type STRING. By
                                    default, it is .
 -r | --requestor-id                requestor ID. It is of type STRING. By default,
                                    it is .
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
 -n | --service-model-version       service model version. It is of type STRING. By
                                    default, it is .
 -a | --no-auth                     Whether to authenticate user or not. It is of
                                    type BOOL.
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -i | --vf-model-customization-id   vf model customization id. It is of type STRING.
                                    By default, it is .
 -C | --no-catalog                  Whether to use given host-url directly or
                                    discover it from catalog, it will override the
                                    service->mode. It is of type BOOL. It is
                                    optional.
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



[114] vnf-show
-----------------------------------------------



usage: oclip vnf-show

Show a VNF

Product: onap-dublin
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-j | --service-model-invariant-id] [-q | --service-model-name] [-k | --service-model-uuid]
 [-V | --verify] [-f | --format] [-h | --help]
 [-t | --no-title] [-l | --cloud-region] [-v | --version]
 [-y | --service-instance-id] [-z | --tenant-id] [-r | --requestor-id]
 [-c | --vf-model-uuid] [-o | --instance-name] [-s | --long]
 [-e | --vf-model-name] [-g | --vf-model-version] [-b | --vf-model-invariant-id]
 [-n | --service-model-version] [-a | --no-auth] [-p | --host-password]
 [-m | --host-url] [-i | --vf-model-customization-id] [-C | --no-catalog]
 [-d | --debug] [-D | --context] [-w | --product-family]
 [-u | --host-username]

where::

 -j | --service-model-invariant-id  model invariant id. It is of type STRING. By
                                    default, it is .
 -q | --service-model-name          service model name. It is of type STRING. By
                                    default, it is .
 -k | --service-model-uuid          model name version id. It is of type STRING. By
                                    default, it is .
 -V | --verify                      Helps to verify the command using samples
                                    provides under open-cli-samples directory. By
                                    default, it goes with mock.To enable the
                                    verification of samples in real time, set
                                    DISABLE_MOCKING=true in the context parameter. It
                                    is of type BOOL. It is optional.
 -f | --format                      Output formats, supported formats such as table,
                                    csv, json, yaml. It is of type STRING.
 -h | --help                        print help message. It is of type BOOL.
 -t | --no-title                    whether to print title or not. It is of type BOOL.
 -l | --cloud-region                cloud region identifier. It is of type STRING. By
                                    default, it is .
 -v | --version                     print service version. It is of type BOOL.
 -y | --service-instance-id         unique id for service instance. It is of type
                                    STRING. By default, it is .
 -z | --tenant-id                   openstack tenant id. It is of type STRING. By
                                    default, it is .
 -r | --requestor-id                requestor ID. It is of type STRING. By default,
                                    it is .
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
 -n | --service-model-version       service model version. It is of type STRING. By
                                    default, it is .
 -a | --no-auth                     Whether to authenticate user or not. It is of
                                    type BOOL.
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -i | --vf-model-customization-id   vf model customization id. It is of type STRING.
                                    By default, it is .
 -C | --no-catalog                  Whether to use given host-url directly or
                                    discover it from catalog, it will override the
                                    service->mode. It is of type BOOL. It is
                                    optional.
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



[115] vnf-update
-----------------------------------------------



usage: oclip vnf-update

Update a VNF

Product: onap-dublin
Service: so
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-j | --service-model-invariant-id] [-q | --service-model-name] [-k | --service-model-uuid]
 [-V | --verify] [-f | --format] [-h | --help]
 [-t | --no-title] [-l | --cloud-region] [-v | --version]
 [-y | --service-instance-id] [-z | --tenant-id] [-r | --requestor-id]
 [-c | --vf-model-uuid] [-o | --instance-name] [-s | --long]
 [-e | --vf-model-name] [-g | --vf-model-version] [-b | --vf-model-invariant-id]
 [-n | --service-model-version] [-a | --no-auth] [-p | --host-password]
 [-m | --host-url] [-i | --vf-model-customization-id] [-C | --no-catalog]
 [-d | --debug] [-D | --context] [-w | --product-family]
 [-u | --host-username]

where::

 -j | --service-model-invariant-id  model invariant id. It is of type STRING. By
                                    default, it is .
 -q | --service-model-name          service model name. It is of type STRING. By
                                    default, it is .
 -k | --service-model-uuid          model name version id. It is of type STRING. By
                                    default, it is .
 -V | --verify                      Helps to verify the command using samples
                                    provides under open-cli-samples directory. By
                                    default, it goes with mock.To enable the
                                    verification of samples in real time, set
                                    DISABLE_MOCKING=true in the context parameter. It
                                    is of type BOOL. It is optional.
 -f | --format                      Output formats, supported formats such as table,
                                    csv, json, yaml. It is of type STRING.
 -h | --help                        print help message. It is of type BOOL.
 -t | --no-title                    whether to print title or not. It is of type BOOL.
 -l | --cloud-region                cloud region identifier. It is of type STRING. By
                                    default, it is .
 -v | --version                     print service version. It is of type BOOL.
 -y | --service-instance-id         unique id for service instance. It is of type
                                    STRING. By default, it is .
 -z | --tenant-id                   openstack tenant id. It is of type STRING. By
                                    default, it is .
 -r | --requestor-id                requestor ID. It is of type STRING. By default,
                                    it is .
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
 -n | --service-model-version       service model version. It is of type STRING. By
                                    default, it is .
 -a | --no-auth                     Whether to authenticate user or not. It is of
                                    type BOOL.
 -p | --host-password               Host user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_PASSWORD. Secured.
 -m | --host-url                    host url in http(s). It is of type URL. By
                                    default, it is read from environment variable
                                    OPEN_CLI_HOST_URL.
 -i | --vf-model-customization-id   vf model customization id. It is of type STRING.
                                    By default, it is .
 -C | --no-catalog                  Whether to use given host-url directly or
                                    discover it from catalog, it will override the
                                    service->mode. It is of type BOOL. It is
                                    optional.
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



[116] vnfm-create
-----------------------------------------------



usage: oclip vnfm-create

Register a VNFM in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-e | --vendor] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-y | --vnfm-id] [-c | --type] [-x | --vim-id]
 [-s | --long] [-D | --context] [-j | --password]
 [-b | --name] [-i | --username] [-u | --host-username]
 [-g | --url] [-a | --no-auth] [-q | --vnfm-version]
 [-z | --certificate-url] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -e | --vendor            Onap VNFM vendor. It is of type STRING. By
                          default, it is .
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
 -y | --vnfm-id           Onap VNFM unique id. It is of type UUID.
 -c | --type              Onap VNFM type. It is of type STRING. By default,
                          it is .
 -x | --vim-id            Onap VIM unique id. It is of type UUID.
 -s | --long              whether to print all attributes or only short
                          attributes. It is of type BOOL.
 -D | --context           command context. It is of type MAP. It is
                          optional.
 -j | --password          Onap VNFM password. It is of type STRING. By
                          default, it is .
 -b | --name              Onap VNFM name. It is of type STRING. By default,
                          it is .
 -i | --username          Onap VNFM username. It is of type STRING. By
                          default, it is .
 -u | --host-username     Host user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_CLI_HOST_USERNAME.
 -g | --url               Onap VNFM URL. It is of type STRING. By default,
                          it is .
 -a | --no-auth           Whether to authenticate user or not. It is of
                          type BOOL.
 -q | --vnfm-version      Onap VNFM version. It is of type STRING. By
                          default, it is .
 -z | --certificate-url   Onap VNFM certificate-url. It is of type STRING.
                          It is optional. By default, it is .
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[117] vnfm-delete
-----------------------------------------------



usage: oclip vnfm-delete

Un-register a VNFM in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-y | --resource-version]
 [-x | --vnfm-id] [-s | --long] [-D | --context]
 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

where::

 -m | --host-url          host url in http(s). It is of type URL. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_URL.
 -C | --no-catalog        Whether to use given host-url directly or
                          discover it from catalog, it will override the
                          service->mode. It is of type BOOL. It is
                          optional.
 -f | --format            Output formats, supported formats such as table,
                          csv, json, yaml. It is of type STRING.
 -h | --help              print help message. It is of type BOOL.
 -V | --verify            Helps to verify the command using samples
                          provides under open-cli-samples directory. By
                          default, it goes with mock.To enable the
                          verification of samples in real time, set
                          DISABLE_MOCKING=true in the context parameter. It
                          is of type BOOL. It is optional.
 -t | --no-title          whether to print title or not. It is of type BOOL.
 -d | --debug             Enable debug output. It is of type BOOL.
 -v | --version           print service version. It is of type BOOL.
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
 -a | --no-auth           Whether to authenticate user or not. It is of
                          type BOOL.
 -p | --host-password     Host user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_CLI_HOST_PASSWORD. Secured.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[118] vnfm-list
-----------------------------------------------



usage: oclip vnfm-list

List the configured vnfm

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
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



[119] vnfm-show
-----------------------------------------------



usage: oclip vnfm-show

Show the VNFM in Onap

Product: onap-dublin
Service: aai
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --vnfm-id]
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



[120] vnftest-list
-----------------------------------------------



usage: oclip vnftest-list

Lists the VNF test cases in VNF Test Platform (VTP)

Product: onap-dublin
Service: vtp
Author: ONAP CLI Team onap-discuss@lists.onap.org

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


Results::

 tescase    Testcase name and is of type STRING.
 testsuite  Test suite name and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[121] vnftest-run
-----------------------------------------------



usage: oclip vnftest-run

Runs the VNF test cases in VNF Test Platform (VTP)

Product: onap-dublin
Service: vtp
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-y | --param]
 [-x | --name] [-s | --long] [-D | --context]

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
 -y | --param       VNFtest case name params, and can be repeated. It
                    is of type MAP. It is optional.
 -x | --name        VNFtest case name. It is of type STRING. By
                    default, it is .
 -s | --long        whether to print all attributes or only short
                    attributes. It is of type BOOL.
 -D | --context     command context. It is of type MAP. It is
                    optional.


Results::

 executionId  Testcase execution Id and is of type STRING.
 status       Test status and is of type STRING.
 startTime    Testcase startTime and is of type STRING.
 endTime      Testcase endTime and is of type STRING.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[122] vsp-add-artifact
-----------------------------------------------



usage: oclip vsp-add-artifact

Upload the CSAR/ZIP file to VSP

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --vsp-id]
 [-s | --long] [-D | --context] [-y | --vsp-version]
 [-z | --vsp-file]

where::

 -m | --host-url      host url in http(s). It is of type URL. By
                      default, it is read from environment variable
                      OPEN_CLI_HOST_URL.
 -C | --no-catalog    Whether to use given host-url directly or
                      discover it from catalog, it will override the
                      service->mode. It is of type BOOL. It is
                      optional.
 -f | --format        Output formats, supported formats such as table,
                      csv, json, yaml. It is of type STRING.
 -h | --help          print help message. It is of type BOOL.
 -V | --verify        Helps to verify the command using samples
                      provides under open-cli-samples directory. By
                      default, it goes with mock.To enable the
                      verification of samples in real time, set
                      DISABLE_MOCKING=true in the context parameter. It
                      is of type BOOL. It is optional.
 -t | --no-title      whether to print title or not. It is of type BOOL.
 -d | --debug         Enable debug output. It is of type BOOL.
 -v | --version       print service version. It is of type BOOL.
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



[123] vsp-create
-----------------------------------------------



usage: oclip vsp-create

Create Vendor Software Product

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-j | --vlm-feature-group-id] [-C | --no-catalog]
 [-f | --format] [-h | --help] [-V | --verify]
 [-t | --no-title] [-d | --debug] [-v | --version]
 [-o | --onboarding-method] [-e | --vlm-vendor] [-x | --vsp-name]
 [-y | --vsp-description] [-s | --long] [-D | --context]
 [-i | --vlm-agreement-id] [-c | --vlm-version] [-u | --host-username]
 [-a | --no-auth] [-g | --vlm-id] [-p | --host-password]

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
 -f | --format                Output formats, supported formats such as table,
                              csv, json, yaml. It is of type STRING.
 -h | --help                  print help message. It is of type BOOL.
 -V | --verify                Helps to verify the command using samples
                              provides under open-cli-samples directory. By
                              default, it goes with mock.To enable the
                              verification of samples in real time, set
                              DISABLE_MOCKING=true in the context parameter. It
                              is of type BOOL. It is optional.
 -t | --no-title              whether to print title or not. It is of type BOOL.
 -d | --debug                 Enable debug output. It is of type BOOL.
 -v | --version               print service version. It is of type BOOL.
 -o | --onboarding-method     Method to be used for onboarding (Manual or
                              NetworkPackage). It is of type STRING. By
                              default, it is .
 -e | --vlm-vendor            License Model vendor. It is of type STRING. By
                              default, it is .
 -x | --vsp-name              Onap VSP Name. It is of type STRING. By default,
                              it is .
 -y | --vsp-description       Description for VSP. It is of type STRING. It is
                              optional. By default, it is .
 -s | --long                  whether to print all attributes or only short
                              attributes. It is of type BOOL.
 -D | --context               command context. It is of type MAP. It is
                              optional.
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

 ID       VSP ID and is of type STRING.
 version  VSP Model version and is of type UUID.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[124] vsp-list
-----------------------------------------------



usage: oclip vsp-list

List of the Vendor Software Products

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-s | --long]
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
 -a | --no-auth         Whether to authenticate user or not. It is of
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



[125] vsp-package
-----------------------------------------------



usage: oclip vsp-package

Package Vendor Software Product

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --vsp-id]
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



[126] vsp-submit
-----------------------------------------------



usage: oclip vsp-submit

Submit Vendor Software Product

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --vsp-id]
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



[127] vsp-validate
-----------------------------------------------



usage: oclip vsp-validate

Validate the uploaded Vendor Software Product

Product: onap-dublin
Service: sdc
Author: ONAP CLI Team onap-discuss@lists.onap.org

Options::

 [-m | --host-url] [-C | --no-catalog] [-f | --format]
 [-h | --help] [-V | --verify] [-t | --no-title]
 [-d | --debug] [-v | --version] [-x | --vsp-id]
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


Results::

 status   Validation status and is of type STRING.
 errors   Validation messages and is of type JSON.


Error::

 On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>


