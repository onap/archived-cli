.. _cmd_help:
.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.


.. toctree::
   :maxdepth: 2


onap-1.1
==========================


microservice-create
-----------------------------------------------


usage: onap microservice-create

Register microservice into Onap

Onap service: msb v1

Options::

 [-x | --service-name] [-y | --service-version] [-r | --service-url]
 <node-ip> <node-port> <create-or-update>
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title]

where::

 -x | --service-name      Onap service name. It is of type STRING.
 -y | --service-version   Onap service version. It is of type STRING.
 -r | --service-url       Onap service base url. It is of type URL.
 node-ip                  Onap service running node IP. It is of type
                          STRING.
 node-port                Onap service running node port. It is of type
                          STRING.
 create-or-update         Onap service create or update. It is of type
                          BOOL. By default, it is false.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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


Results::

 name     Onap service name and is of type STRING.
 version  Onap service version and is of type STRING.
 url      Onap service base url and is of type URL.
 status   Onap service status and is of type DIGIT.
 nodes    Onap service running nodes and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



microservice-show
-----------------------------------------------


usage: onap microservice-show

Details the registered microservice in Onap

Onap service: msb v1

Options::

 [-x | --service-name] [-y | --service-version] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]

where::

 -x | --service-name      Onap service name. It is of type STRING.
 -y | --service-version   Onap service version. It is of type STRING.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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


Results::

 name     Onap service name and is of type STRING.
 version  Onap service version and is of type STRING.
 url      Onap service base url and is of type URL.
 status   Onap service status and is of type DIGIT.
 nodes    Onap service running nodes and is of type JSON.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



microservice-list
-----------------------------------------------


usage: onap microservice-list

Lists the registetred micro services in Onap

Onap service: msb v1

Options::

 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title]

where::

 -m | --host-url  Onap host url. It is of type URL. By default, it
                  is read from environment variable ONAP_HOST_URL.
 -h | --help      Onap command help message. It is of type STRING.
                  By default, it is false.
 -v | --version   Onap command service version. It is of type
                  STRING. By default, it is false.
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

 name     Onap service name and is of type STRING.
 version  Onap service version and is of type STRING.
 url      Onap service base url and is of type URL.
 status   Onap service status and is of type DIGIT.
 nodes    Onap service running nodes and is of type JSON.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



microservice-delete
-----------------------------------------------


usage: onap microservice-delete

Deletes the micro service from Onap

Onap service: msb v1

Options::

 [-x | --service-name] [-y | --service-version] [-i | --node-ip]
 [-r | --node-port] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title]

where::

 -x | --service-name      Onap service name. It is of type STRING.
 -y | --service-version   Onap service version. It is of type STRING.
 -i | --node-ip           Onap service running node IP. It is of type
                          STRING.
 -r | --node-port         Onap service running node port. It is of type
                          STRING.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vsp-checkout
-----------------------------------------------


usage: onap vsp-checkout

Checkout Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



cloud-create
-----------------------------------------------


usage: onap cloud-create

Create a cloud region in Onap

Onap service: aai v11

Options::

 [-x | --cloud-name] [-y | --region-name] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --cloud-name      Onap cloud name. It is of type STRING.
 -y | --region-name     Onap  region name. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



customer-create
-----------------------------------------------


usage: onap customer-create

Create a customer in Onap

Onap service: aai v11

Options::

 [-x | --customer-name] [-y | --subscriber-name] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --customer-name     Onap customer name. It is of type STRING.
 -y | --subscriber-name   Onap subscriber name. It is of type STRING.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



customer-delete
-----------------------------------------------


usage: onap customer-delete

Delete a customer from Onap

Onap service: aai v11

Options::

 [-x | --customer-name] [-y | --resource-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --customer-name     Onap customer name. It is of type STRING.
 -y | --resource-version  Onap customer resource version. It is of type
                          UUID. It is optional. By default, it is
                          0f4899c3-771c-462e-9dc8-ffa2de5532f6.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



tenant-delete
-----------------------------------------------


usage: onap tenant-delete

Delete tenant under given cloud region in Onap

Onap service: aai v11

Options::

 [-x | --cloud] [-y | --region] [-z | --tenant-id]
 [-r | --resource-version] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --cloud             Onap cloud. It is of type STRING.
 -y | --region            Onap cloud region. It is of type STRING.
 -z | --tenant-id         Onap cloud tenant id. It is of type STRING.
 -r | --resource-version  Onap cloud tenant version. It is of type UUID. It
                          is optional. By default, it is
                          adbbea15-de1a-442a-a0e3-793779ea25bb.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



tenant-list
-----------------------------------------------


usage: onap tenant-list

Lists the tenants under given cloud region in Onap

Onap service: aai v11

Options::

 [-x | --cloud] [-y | --region] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --cloud           Onap cloud. It is of type STRING.
 -y | --region          Onap cloud region. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 tenant-id          Onap tenant-id and is of type STRING.
 tenant-name        Onap tenant name and is of type STRING.
 resource-version   Onap tenant resource version and is of type
                    STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



cloud-delete
-----------------------------------------------


usage: onap cloud-delete

Delete a cloud region from Onap

Onap service: aai v11

Options::

 [-x | --cloud-name] [-y | --region-name] [-z | --resource-version]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -x | --cloud-name        Onap cloud name. It is of type STRING.
 -y | --region-name       Onap cloud region name. It is of type STRING.
 -z | --resource-version  Onap cloud region version. It is of type UUID. It
                          is optional. By default, it is
                          93230cdb-aae3-4b87-a38a-fc4529e8aac9.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



customer-list
-----------------------------------------------


usage: onap customer-list

Lists the registetred customers in Onap

Onap service: aai v11

Options::

 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 name               Onap customer name and is of type STRING.
 resource-version   Onap customer resource version and is of type
                    STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



ems-list
-----------------------------------------------


usage: onap ems-list

List the configured ems

Onap service: aai v11

Options::

 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 ems-id             Onap ems id and is of type STRING.
 resource-version   Onap ems resource version and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



sdnc-unregister
-----------------------------------------------


usage: onap sdnc-unregister

Un-register a VNFM in Onap

Onap service: aai v11

Options::

 [-x | --sdnc-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --sdnc-id         Onap VNFM unique id. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vim-unregister
-----------------------------------------------


usage: onap vim-unregister

Un-register a VIM under from cloud region in Onap

Onap service: aai v11

Options::

 [-x | --cloud-name] [-y | --region-name] [-z | --vim-id]
 [-b | --resource-version] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --cloud-name        Onap cloud name. It is of type STRING.
 -y | --region-name       Onap  region name. It is of type STRING.
 -z | --vim-id            Onap VIM unique id. It is of type UUID. By
                          default, it is
                          65c6f4af-893f-4130-aa79-9158fcaed040.
 -b | --resource-version  Onap vim resource version. It is of type STRING.
                          It is optional.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vnfm-register
-----------------------------------------------


usage: onap vnfm-register

Register a VNFM in Onap

Onap service: aai v11

Options::

 [-x | --vim-id] [-y | --vnfm-id] [-b | --name]
 [-c | --type] [-e | --vendor] [-q | --vnfm-version]
 [-g | --url] [-i | --username] [-j | --password]
 [-z | --certificate-url] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --vim-id            Onap VIM unique id. It is of type UUID. By
                          default, it is
                          e74f4b44-28ee-45e4-a1ac-7c3a0ed4a0b7.
 -y | --vnfm-id           Onap VNFM unique id. It is of type UUID. By
                          default, it is
                          7a74f262-5b96-4445-8a56-2d43b73f6f6c.
 -b | --name              Onap VNFM name. It is of type STRING.
 -c | --type              Onap VNFM type. It is of type STRING.
 -e | --vendor            Onap VNFM vendor. It is of type STRING.
 -q | --vnfm-version      Onap VNFM version. It is of type STRING.
 -g | --url               Onap VNFM URL. It is of type STRING.
 -i | --username          Onap VNFM username. It is of type STRING.
 -j | --password          Onap VNFM password. It is of type STRING.
 -z | --certificate-url   Onap VNFM certificate-url. It is of type STRING.
                          It is optional.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vnfm-list
-----------------------------------------------


usage: onap vnfm-list

List the configured vnfm

Onap service: aai v11

Options::

 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 vnfm-id            Onap vnfm id and is of type STRING.
 vim-id             Onap vnfm id and is of type STRING.
 certificate-url    Onap vnfm certificate-url and is of type STRING.
 resource-version   Onap vnfm resource version and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



service-type-create
-----------------------------------------------


usage: onap service-type-create

Add a service type in Onap

Onap service: aai v11

Options::

 [-x | --service-type] [-y | --service-type-id] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --service-type      Onap service type. It is of type STRING.
 -y | --service-type-id   Onap service type uuid. It is of type UUID. It is
                          optional. By default, it is
                          3def24ae-701e-4c98-91a6-859c0f206759.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



service-type-delete
-----------------------------------------------


usage: onap service-type-delete

Delete a service type from Onap

Onap service: aai v11

Options::

 [-x | --service-type-id] [-y | --resource-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --service-type-id   Onap service type uuid. It is of type UUID. It is
                          optional. By default, it is
                          04ebd209-7800-43f4-bca4-aaf61e15783e.
 -y | --resource-version  Onap service resource version. It is of type
                          UUID. It is optional. By default, it is
                          7b301a17-5eb2-413b-b214-fd1659fdf049.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



service-type-list
-----------------------------------------------


usage: onap service-type-list

List the service types configured in Onap

Onap service: aai v11

Options::

 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 service-type-id    Onap cloud service and is of type STRING.
 service-type       Onap cloud service and is of type STRING.
 resource-version   Onap cloud service resource version and is of
                    type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



subscription-create
-----------------------------------------------


usage: onap subscription-create

Create a subscription of a customer for given service in specific cloud region in Onap

Onap service: aai v11

Options::

 [-x | --customer-name] [-z | --cloud-name] [-r | --cloud-region]
 [-c | --cloud-tenant-id] [-e | --service-type] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --customer-name     Onap customer name. It is of type STRING.
 -z | --cloud-name        Onap cloud name. It is of type STRING.
 -r | --cloud-region      Onap cloud region. It is of type STRING.
 -c | --cloud-tenant-id   Onap cloud tenant id. It is of type STRING.
 -e | --service-type      Onap service type. It is of type STRING.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



cloud-list
-----------------------------------------------


usage: onap cloud-list

List the configured clouds and Onap service subscriptions

Onap service: aai v11

Options::

 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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



ems-register
-----------------------------------------------


usage: onap ems-register

Register a EMS in Onap

Onap service: aai v11

Options::

 [-z | --ems-id] [-b | --name] [-c | --type]
 [-e | --vendor] [-q | --ems-version] [-g | --url]
 [-i | --username] [-j | --password] [-x | --remote-path]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -z | --ems-id          Onap EMS unique id. It is of type UUID. By
                        default, it is
                        fe79d2e4-97d2-4921-8069-0934f0832d56.
 -b | --name            Onap EMS name. It is of type STRING.
 -c | --type            Onap EMS type. It is of type STRING.
 -e | --vendor          Onap EMS vendor. It is of type STRING.
 -q | --ems-version     Onap EMS version. It is of type STRING.
 -g | --url             Onap EMS URL. It is of type STRING.
 -i | --username        Onap EMS username. It is of type STRING.
 -j | --password        Onap EMS password. It is of type STRING.
 -x | --remote-path     Onap EMS remote-path. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



ems-show
-----------------------------------------------


usage: onap ems-show

Show the details of configured ems

Onap service: aai v11

Options::

 [-x | --ems-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --ems-id          Onap EMS unique id. It is of type UUID. By
                        default, it is
                        2b6c14a1-e661-46f7-8999-a05125b0e026.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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



sdnc-list
-----------------------------------------------


usage: onap sdnc-list

List the configured sdnc

Onap service: aai v11

Options::

 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 sdnc-id            Onap sdnc id and is of type STRING.
 resource-version   Onap sdnc resource version and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



customer-show
-----------------------------------------------


usage: onap customer-show

Lists the registetred customers in Onap

Onap service: aai v11

Options::

 [-x | --customer-name] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --customer-name   Onap customer name. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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



ems-unregister
-----------------------------------------------


usage: onap ems-unregister

Un-register a EMS in Onap

Onap service: aai v11

Options::

 [-x | --ems-id] [-y | --resource-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --ems-id            Onap EMS unique id. It is of type UUID. By
                          default, it is
                          efba894e-20f4-4658-9d43-1f8736188d3d.
 -y | --resource-version  Onap vim resource version. It is of type STRING.
                          It is optional.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



sdnc-register
-----------------------------------------------


usage: onap sdnc-register

Register a SDNC in Onap

Onap service: aai v11

Options::

 [-x | --location] [-y | --sdnc-id] [-b | --name]
 [-c | --type] [-e | --vendor] [-q | --sdnc-version]
 [-g | --ip-address] [-k | --port] [-r | --protocal]
 [-i | --username] [-j | --password] [-z | --product-name]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -x | --location        Onap VIM unique id. It is of type UUID. By
                        default, it is
                        0f283039-a3fd-4fe1-ac14-b8cdb3a20b26.
 -y | --sdnc-id         Onap SDNC unique id. It is of type UUID. By
                        default, it is
                        b112c385-90ad-4eb7-ae6d-1f263925e97e.
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
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vim-register
-----------------------------------------------


usage: onap vim-register

Register a VIM under a given cloud region in Onap

Onap service: aai v11

Options::

 [-x | --cloud-name] [-y | --region-name] [-z | --vim-id]
 [-b | --name] [-c | --type] [-e | --vendor]
 [-q | --vim-version] [-g | --url] [-i | --username]
 [-j | --password] [-k | --ssl-cacert] [-l | --ssl-insecure]
 [-n | --cloud-domain] [-o | --default-tenant] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --cloud-name      Onap cloud name. It is of type STRING.
 -y | --region-name     Onap  region name. It is of type STRING.
 -z | --vim-id          Onap VIM unique id. It is of type UUID. By
                        default, it is
                        e6cd00f7-8787-46cf-a4f2-4ceb779e8420.
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
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vim-list
-----------------------------------------------


usage: onap vim-list

List the configured vims

Onap service: aai v11

Options::

 [-x | --cloud-name] [-y | --region-name] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --cloud-name      Onap cloud name. It is of type STRING.
 -y | --region-name     Onap  region name. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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



vnfm-unregister
-----------------------------------------------


usage: onap vnfm-unregister

Un-register a VNFM in Onap

Onap service: aai v11

Options::

 [-x | --vnfm-id] [-y | --resource-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vnfm-id           Onap VNFM unique id. It is of type UUID. By
                          default, it is
                          d8a2726f-18a6-4bb1-904f-554a3fb70865.
 -y | --resource-version  Onap vim resource version. It is of type STRING.
                          It is optional.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vnfm-show
-----------------------------------------------


usage: onap vnfm-show

Show the VNFM in Onap

Onap service: aai v11

Options::

 [-x | --vnfm-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --vnfm-id         Onap VNFM unique id. It is of type UUID. By
                        default, it is
                        6d13f516-da67-4593-9284-15686ac518cd.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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



subscription-delete
-----------------------------------------------


usage: onap subscription-delete

Delete the subscription for a given customer in Onap

Onap service: aai v11

Options::

 [-x | --customer-name] [-y | --service-type] [-g | --resource-version]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -x | --customer-name     Onap customer name. It is of type STRING.
 -y | --service-type      Onap subscribtion id. It is of type STRING.
 -g | --resource-version  Onap subscription resource version. It is of type
                          STRING. It is optional.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



subscription-list
-----------------------------------------------


usage: onap subscription-list

Lists the subscription for a given customer in Onap

Onap service: aai v11

Options::

 [-x | --customer-name] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --customer-name   Onap customer name. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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



tenant-create
-----------------------------------------------


usage: onap tenant-create

Create a tenant under given cloud region in Onap

Onap service: aai v11

Options::

 [-x | --cloud] [-y | --region] [-z | --tenant-id]
 [-r | --tenant-name] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --cloud           Onap cloud. It is of type STRING.
 -y | --region          Onap cloud region. It is of type STRING.
 -z | --tenant-id       Onap cloud tenant id. It is of type STRING.
 -r | --tenant-name     Onap cloud tenant name. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



service-delete
-----------------------------------------------


usage: onap service-delete

Delete a service instance

Onap service: so v2

Options::

 [-x | --service-instace-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --service-instace-id  unique id for service instance. It is of type
                            STRING.
 -u | --onap-username       Onap user name. It is of type STRING. By default,
                            it is read from environment variable
                            ONAP_USERNAME.
 -p | --onap-password       Onap user password. It is of type STRING. By
                            default, it is read from environment variable
                            ONAP_PASSWORD. Secured.
 -m | --host-url            Onap host url. It is of type URL. By default, it
                            is read from environment variable ONAP_HOST_URL.
 -h | --help                Onap command help message. It is of type STRING.
                            By default, it is false.
 -v | --version             Onap command service version. It is of type
                            STRING. By default, it is false.
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
 -a | --no-auth             whether to authenticate user or not. It is of
                            type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vnf-create
-----------------------------------------------


usage: onap vnf-create

Create a VNF

Onap service: mso v2

Options::

 [-l | --lcp-cloudregion-id] [-z | --tenant-id] [-w | --product-family-id]
 [-i | --instance-name] [-r | --supress-rollback] [-y | --service-instance-id]
 [-x | --parenet-service-model] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -l | --lcp-cloudregion-id      AIC LCP node location identifier. It is of type
                                STRING.
 -z | --tenant-id               openstack tenant id (uuid). It is of type STRING.
 -w | --product-family-id       UUID for product family (named service id in
                                AAI). It is of type STRING.
 -i | --instance-name           service instance name. It is of type STRING.
 -r | --supress-rollback        rollback changes if instantiation fails. It is of
                                type BOOL. It is optional. By default, it is
                                false.
 -y | --service-instance-id     unique id for service instance. It is of type
                                STRING.
 -x | --parenet-service-model   parent service model name. It is of type STRING.
 -u | --onap-username           Onap user name. It is of type STRING. By default,
                                it is read from environment variable
                                ONAP_USERNAME.
 -p | --onap-password           Onap user password. It is of type STRING. By
                                default, it is read from environment variable
                                ONAP_PASSWORD. Secured.
 -m | --host-url                Onap host url. It is of type URL. By default, it
                                is read from environment variable ONAP_HOST_URL.
 -h | --help                    Onap command help message. It is of type STRING.
                                By default, it is false.
 -v | --version                 Onap command service version. It is of type
                                STRING. By default, it is false.
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
 -a | --no-auth                 whether to authenticate user or not. It is of
                                type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vnf-delete
-----------------------------------------------


usage: onap vnf-delete

delete a VNF

Onap service: mso v2

Options::

 [-x | --vnf-instace-id] [-y | --service-instace-id] [-l | --lcp-cloudregion-id]
 [-z | --tenant-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --vnf-instace-id      id for vnf. It is of type STRING.
 -y | --service-instace-id  unique id for service instance. It is of type
                            STRING.
 -l | --lcp-cloudregion-id  AIC LCP node location identifier. It is of type
                            STRING.
 -z | --tenant-id           openstack tenant id (uuid). It is of type STRING.
 -u | --onap-username       Onap user name. It is of type STRING. By default,
                            it is read from environment variable
                            ONAP_USERNAME.
 -p | --onap-password       Onap user password. It is of type STRING. By
                            default, it is read from environment variable
                            ONAP_PASSWORD. Secured.
 -m | --host-url            Onap host url. It is of type URL. By default, it
                            is read from environment variable ONAP_HOST_URL.
 -h | --help                Onap command help message. It is of type STRING.
                            By default, it is false.
 -v | --version             Onap command service version. It is of type
                            STRING. By default, it is false.
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
 -a | --no-auth             whether to authenticate user or not. It is of
                            type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



service-create
-----------------------------------------------


usage: onap service-create

Create a service instance using MSO

Onap service: mso v2

Options::

 [-c | --global-subscriber-id] [-b | --subscriber-name] [-i | --instance-name]
 [-r | --supress-rollback] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -c | --global-subscriber-id  unique id for customer. It is of type STRING.
 -b | --subscriber-name       subscriber name. It is of type STRING. It is
                              optional.
 -i | --instance-name         service instance name. It is of type STRING.
 -r | --supress-rollback      rollback changes if instantiation fails. It is of
                              type BOOL. It is optional. By default, it is
                              false.
 -u | --onap-username         Onap user name. It is of type STRING. By default,
                              it is read from environment variable
                              ONAP_USERNAME.
 -p | --onap-password         Onap user password. It is of type STRING. By
                              default, it is read from environment variable
                              ONAP_PASSWORD. Secured.
 -m | --host-url              Onap host url. It is of type URL. By default, it
                              is read from environment variable ONAP_HOST_URL.
 -h | --help                  Onap command help message. It is of type STRING.
                              By default, it is false.
 -v | --version               Onap command service version. It is of type
                              STRING. By default, it is false.
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
 -a | --no-auth               whether to authenticate user or not. It is of
                              type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vf-module-delete
-----------------------------------------------


usage: onap vf-module-delete

delete a VF module

Onap service: mso v2

Options::

 [-x | --vnf-instace-id] [-y | --service-instace-id] [-w | --vf-module-id]
 [-l | --lcp-cloudregion-id] [-z | --tenant-id] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vnf-instace-id      id for vnf. It is of type STRING.
 -y | --service-instace-id  unique id for service instance. It is of type
                            STRING.
 -w | --vf-module-id        vf module id. It is of type STRING.
 -l | --lcp-cloudregion-id  AIC LCP node location identifier. It is of type
                            STRING.
 -z | --tenant-id           openstack tenant id (uuid). It is of type STRING.
 -u | --onap-username       Onap user name. It is of type STRING. By default,
                            it is read from environment variable
                            ONAP_USERNAME.
 -p | --onap-password       Onap user password. It is of type STRING. By
                            default, it is read from environment variable
                            ONAP_PASSWORD. Secured.
 -m | --host-url            Onap host url. It is of type URL. By default, it
                            is read from environment variable ONAP_HOST_URL.
 -h | --help                Onap command help message. It is of type STRING.
                            By default, it is false.
 -v | --version             Onap command service version. It is of type
                            STRING. By default, it is false.
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
 -a | --no-auth             whether to authenticate user or not. It is of
                            type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vf-module-create
-----------------------------------------------


usage: onap vf-module-create

Create a VF Module

Onap service: mso v2

Options::

 [-l | --lcp-cloudregion-id] [-w | --tenant-id] [-y | --vnf-instace-id]
 [-x | --service-instace-id] [-z | --parenet-service-model] [-r | --supress-rollback]
 [-i | --instance-name] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

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
 -u | --onap-username           Onap user name. It is of type STRING. By default,
                                it is read from environment variable
                                ONAP_USERNAME.
 -p | --onap-password           Onap user password. It is of type STRING. By
                                default, it is read from environment variable
                                ONAP_PASSWORD. Secured.
 -m | --host-url                Onap host url. It is of type URL. By default, it
                                is read from environment variable ONAP_HOST_URL.
 -h | --help                    Onap command help message. It is of type STRING.
                                By default, it is false.
 -v | --version                 Onap command service version. It is of type
                                STRING. By default, it is false.
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
 -a | --no-auth                 whether to authenticate user or not. It is of
                                type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



onap-1.0
==========================


license-model-create
-----------------------------------------------


usage: onap license-model-create

Create License Model

Onap service: sdc v1.0

Options::

 [-x | --vendor-name] [-y | --license-model-description] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vendor-name                 vendor name. It is of type STRING.
 -y | --license-model-description   Description for License Model. It is of type
                                    STRING. It is optional.
 -u | --onap-username               Onap user name. It is of type STRING. By default,
                                    it is read from environment variable
                                    ONAP_USERNAME.
 -p | --onap-password               Onap user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    ONAP_PASSWORD. Secured.
 -m | --host-url                    Onap host url. It is of type URL. By default, it
                                    is read from environment variable ONAP_HOST_URL.
 -h | --help                        Onap command help message. It is of type STRING.
                                    By default, it is false.
 -v | --version                     Onap command service version. It is of type
                                    STRING. By default, it is false.
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
 -a | --no-auth                     whether to authenticate user or not. It is of
                                    type BOOL. By default, it is false.


Results::

 ID   License Model ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vsp-create
-----------------------------------------------


usage: onap vsp-create

Create Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-name] [-y | --vsp-description] [-null | --vsp-category]
 [-null | --vsp-subcategory] [-null | --vsp-license-version] [-null | --vsp-vendor-name]
 [-null | --vsp-vendor-id] [-null | --license-agreement-id] [-null | --feature-group-id]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -x | --vsp-name                  Onap VSP Name. It is of type STRING.
 -y | --vsp-description           Description for VSP. It is of type STRING. It is
                                  optional.
 -null | --vsp-category           Category of the VSP. It is of type STRING. It is
                                  optional. By default, it is
                                  resourceNewCategory.generic.
 -null | --vsp-subcategory        Sub Category of VSP. It is of type STRING. It is
                                  optional. By default, it is
                                  resourceNewCategory.generic.abstract.
 -null | --vsp-license-version    License version. It is of type STRING. It is
                                  optional. By default, it is 1.0.
 -null | --vsp-vendor-name        License Model Name. It is of type STRING.
 -null | --vsp-vendor-id          License Model ID. It is of type STRING.
 -null | --license-agreement-id   License Agreement ID. It is of type STRING.
 -null | --feature-group-id       Feature Group ID. It is of type STRING.
 -u | --onap-username             Onap user name. It is of type STRING. By default,
                                  it is read from environment variable
                                  ONAP_USERNAME.
 -p | --onap-password             Onap user password. It is of type STRING. By
                                  default, it is read from environment variable
                                  ONAP_PASSWORD. Secured.
 -m | --host-url                  Onap host url. It is of type URL. By default, it
                                  is read from environment variable ONAP_HOST_URL.
 -h | --help                      Onap command help message. It is of type STRING.
                                  By default, it is false.
 -v | --version                   Onap command service version. It is of type
                                  STRING. By default, it is false.
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
 -a | --no-auth                   whether to authenticate user or not. It is of
                                  type BOOL. By default, it is false.


Results::

 ID   VSP ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vsp-list
-----------------------------------------------


usage: onap vsp-list

List of the Vendor Software Products

Onap service: sdc v1.0

Options::

 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 ID     VSP ID and is of type STRING.
 name   VSP Name and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vsp-show
-----------------------------------------------


usage: onap vsp-show

Details of the Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 name         VSP Name and is of type STRING.
 ID           VSP ID and is of type STRING.
 description  Description for the VSP and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vsp-submit
-----------------------------------------------


usage: onap vsp-submit

Submit Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



vsp-upload
-----------------------------------------------


usage: onap vsp-upload

Upload the CSAR file to VSP

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-y | --vsp-file] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-file        CSAR File path. It is of type BINARY.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



license-model-show
-----------------------------------------------


usage: onap license-model-show

Details of the License Model

Onap service: sdc v1.0

Options::

 [-x | --license-model-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --license-model-id  Onap License Model ID. It is of type STRING.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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



vsp-checkin
-----------------------------------------------


usage: onap vsp-checkin

Checkin Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



customer-create
-----------------------------------------------


usage: onap customer-create

Create a customer in Onap

Onap service: aai v8

Options::

 [-x | --customer-name] [-y | --subscriber-name] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --customer-name     Onap customer name. It is of type STRING.
 -y | --subscriber-name   Onap subscriber name. It is of type STRING.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



customer-delete
-----------------------------------------------


usage: onap customer-delete

Delete a customer from Onap

Onap service: aai v8

Options::

 [-x | --customer-name] [-y | --resource-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --customer-name     Onap customer name. It is of type STRING.
 -y | --resource-version  Onap customer resource version. It is of type
                          UUID. It is optional. By default, it is
                          143d035b-d9ce-4082-bdad-6f1a8ce808b6.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



customer-show
-----------------------------------------------


usage: onap customer-show

Lists the registetred customers in Onap

Onap service: aai v8

Options::

 [-x | --customer-name] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --customer-name   Onap customer name. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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



subscription-list
-----------------------------------------------


usage: onap subscription-list

Lists the subscription for a given customer in Onap

Onap service: aai v8

Options::

 [-x | --customer-name] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --customer-name   Onap customer name. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 service-type   Onap service type and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



customer-list
-----------------------------------------------


usage: onap customer-list

Lists the registetred customers in Onap

Onap service: aai v8

Options::

 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 name               Onap customer name and is of type STRING.
 resource-version   Onap customer resource version and is of type
                    STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



cloud-list
-----------------------------------------------


usage: onap cloud-list

List the configured clouds and Onap service subscriptions

Onap service: aai v8

Options::

 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 cloud      Onap cloud and is of type STRING.
 region     Onap cloud region and is of type STRING.
 tenant     Onap cloud tenat and is of type STRING.
 tenant-id  Onap cloud tenat id and is of type STRING.
 customer   Onap cloud customer and is of type STRING.
 service    Onap cloud service and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



service-type-create
-----------------------------------------------


usage: onap service-type-create

Add a service type in Onap

Onap service: aai v8

Options::

 [-x | --service-type] [-y | --service-type-id] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --service-type      Onap service type. It is of type STRING.
 -y | --service-type-id   Onap service type uuid. It is of type UUID. It is
                          optional. By default, it is
                          e1d759b2-e62f-4c88-b613-5e545bfa3048.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



service-type-delete
-----------------------------------------------


usage: onap service-type-delete

Delete a service type from Onap

Onap service: aai v8

Options::

 [-x | --service-type-id] [-y | --resource-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --service-type-id   Onap service type uuid. It is of type UUID. It is
                          optional. By default, it is
                          c1321122-aa00-49ca-85ec-390b824ca531.
 -y | --resource-version  Onap service resource version. It is of type
                          UUID. It is optional. By default, it is
                          1739b97a-f987-4498-bab4-ae2026e627dc.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



service-type-list
-----------------------------------------------


usage: onap service-type-list

List the service types configured in Onap

Onap service: aai v8

Options::

 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        ONAP_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        ONAP_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable ONAP_HOST_URL.
 -h | --help            Onap command help message. It is of type STRING.
                        By default, it is false.
 -v | --version         Onap command service version. It is of type
                        STRING. By default, it is false.
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
 -a | --no-auth         whether to authenticate user or not. It is of
                        type BOOL. By default, it is false.


Results::

 service-type-id    Onap cloud service and is of type STRING.
 service-type       Onap cloud service and is of type STRING.
 resource-version   Onap cloud service resource version and is of
                    type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



subscription-create
-----------------------------------------------


usage: onap subscription-create

Create a subscription for a customer in Onap

Onap service: aai v8

Options::

 [-x | --customer-name] [-y | --subscriber-name] [-z | --cloud-type]
 [-r | --cloud-region] [-c | --cloud-tenant-id] [-e | --service-type]
 [-g | --resource-version] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --customer-name     Onap customer name. It is of type STRING.
 -y | --subscriber-name   Onap subscriber name. It is of type STRING.
 -z | --cloud-type        Onap cloud type (openstack, rackspace). It is of
                          type STRING.
 -r | --cloud-region      Onap cloud region. It is of type STRING.
 -c | --cloud-tenant-id   Onap cloud tenant id. It is of type STRING.
 -e | --service-type      Onap service type. It is of type STRING.
 -g | --resource-version  Onap subscription resource version. It is
                          mandatory for existing customer to create a new
                          subscription. It is of type STRING. It is
                          optional.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



cli-1.0
==========================


schema-validate
-----------------------------------------------


usage: onap schema-validate

Onap command to validate schema

Onap service: onap-cli 1.0.0

Options::

 [-l | --schema-location] [-i | --internal-schema] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title]

where::

 -l | --schema-location   Schema file location. It is of type URL.
 -i | --internal-schema   Validate existing schema file. It is of type
                          BOOL. By default, it is false.
 -h | --help              Onap command help message. It is of type STRING.
                          By default, it is false.
 -v | --version           Onap command service version. It is of type
                          STRING. By default, it is false.
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


Results::

 sl-no  Serial Number of error and is of type STRING.
 error  Schema validation error and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



schema-refresh
-----------------------------------------------


usage: onap schema-refresh

Onap command to refresh schemas stored in onap_cli_schema folders.

Onap service: onap-cli 1.0.0

Options::

 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]

where::

 -h | --help      Onap command help message. It is of type STRING.
                  By default, it is false.
 -v | --version   Onap command service version. It is of type
                  STRING. By default, it is false.
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

 sl-no            Serial Number and is of type STRING.
 command          Command name and is of type STRING.
 product-version  Command product version and is of type STRING.
 schema           Schema name and is of type STRING.
 version          Schema version and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>


