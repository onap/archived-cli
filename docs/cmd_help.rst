.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.


.. toctree::
   :maxdepth: 2


.. _cli_cmd_help:

onap-1.1
==========================


<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
[1] microservice-delete
=======
[1] microservice-show
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



[2] microservice-create
-----------------------------------------------


usage: onap microservice-create

Register microservice into Onap

Onap service: msb v1

Options::

 [-x | --service-name] [-y | --service-version] [-r | --service-url]
 [-b | --enable-ssl] [-c | --path] <node-ip>
 <node-port> <create-or-update> [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]

where::

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
                          BOOL. By default, it is false.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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



[3] microservice-delete
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
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
                          is read from environment variable OPEN_HOST_URL.
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



[2] microservice-list
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
                  is read from environment variable OPEN_HOST_URL.
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



[3] microservice-show
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
                          is read from environment variable OPEN_HOST_URL.
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



[4] microservice-create
-----------------------------------------------


usage: onap microservice-create

Register microservice into Onap

Onap service: msb v1

Options::

 [-x | --service-name] [-y | --service-version] [-r | --service-url]
 [-b | --enable-ssl] [-c | --path] <node-ip>
 <node-port> <create-or-update> [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]

where::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 -x | --service-name      Onap service name. It is of type STRING.
 -y | --service-version   Onap service version. It is of type STRING.
 -r | --service-url       Onap service base url. It is of type URL.
 -b | --enable-ssl        Onap service is enabled with https or not. It is
                          of type STRING. It is optional.
 -c | --path              Onap service path. It is of type STRING. It is
=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -b | --service-model-id  Service model ID. It is of type STRING. It is
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
                          optional.
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
<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7


Results::

 name         Onap service name and is of type STRING.
 version      Onap service version and is of type STRING.
 url          Onap service base url and is of type URL.
 status       Onap service status and is of type DIGIT.
 nodes        Onap service running nodes and is of type STRING.
 enable-ssl   Onap service is enabled with https or not and is
              of type STRING.
 path         Onap service path and is of type STRING.
=======
 -a | --no-auth           whether to authenticate user or not. It is of
                          type BOOL. By default, it is false.
=======
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[5] vlm-checkin
-----------------------------------------------


usage: onap vlm-checkin

Checkin Vendor License Model

Onap service: sdc v1.0

Options::

 [-x | --vlm-id] [-y | --vlm-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vlm-id          Onap VLM ID. It is of type STRING.
 -y | --vlm-version     VLM version. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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



[6] vlm-key-group-create
-----------------------------------------------


usage: onap vlm-key-group-create

Create License Key Group

Onap service: sdc v1.0

Options::

 [-c | --vlm-id] [-e | --vlm-version] [-x | --name]
 [-y | --type] [-z | --description] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 -c | --vlm-id          License Model Id. It is of type STRING.
 -e | --vlm-version     License Model version. It is of type STRING.
 -x | --name            name. It is of type STRING.
 -y | --type            type of group (Universal, unique, one-time). It
                        is of type STRING. By default, it is Universal.
 -z | --description     Description for License Key Group. It is of type
                        STRING. It is optional.
=======
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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



[9] vlm-checkout
-----------------------------------------------


usage: onap vlm-checkout

Checkout Vendor License Model

Onap service: sdc v1.0

Options::

 [-x | --vlm-id] [-y | --vlm-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -x | --vlm-id          Onap VLM ID. It is of type STRING.
 -y | --vlm-version     VLM version. It is of type STRING.
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
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
=======
 -x | --customer-name     Onap customer name. It is of type STRING.
 -y | --resource-version  Onap customer resource version. It is of type
                          UUID. It is optional. By default, it is
                          0f4899c3-771c-462e-9dc8-ffa2de5532f6.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Results::

 ID   License Model ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[7] vlm-aggreement-list
-----------------------------------------------


usage: onap vlm-aggreement-list

List license aggreement

Onap service: sdc v1.0

Options::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 [-x | --vlm-id] [-y | --vlm-version] [-u | --onap-username]
=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 [-x | --vendor-name] [-y | --description] [-u | --onap-username]
=======
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
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vlm-id          License Model ID. It is of type STRING.
 -y | --vlm-version     License Model version. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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

 ID     aggreement ID and is of type STRING.
 name   aggreement name and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[8] vlm-key-group-list
-----------------------------------------------


usage: onap vlm-key-group-list

key group list in a license model

Onap service: sdc v1.0

Options::

 [-x | --vlm-id] [-y | --vlm-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 -x | --vlm-id          License Model ID. It is of type STRING.
 -y | --vlm-version     License Model version. It is of type STRING.
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
=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -x | --name                          Entitlement Pool name. It is of type STRING.
 -y | --vlm-id                        License Model ID. It is of type STRING.
 -e | --vlm-version                   License Model version. It is of type STRING.
 -z | --description                   Description for Entitlement Pool. It is of type
                                      STRING.
 -g | --operational-scope             Operational Scope. It is of type STRING. By
                                      default, it is Availability_Zone.
 -k | --manufacture-reference-number  Manufature Reference Number. It is of type STRING.
 -u | --onap-username                 Onap user name. It is of type STRING. By default,
                                      it is read from environment variable
                                      ONAP_USERNAME.
 -p | --onap-password                 Onap user password. It is of type STRING. By
                                      default, it is read from environment variable
                                      ONAP_PASSWORD. Secured.
 -m | --host-url                      Onap host url. It is of type URL. By default, it
                                      is read from environment variable ONAP_HOST_URL.
 -h | --help                          Onap command help message. It is of type STRING.
                                      By default, it is false.
 -v | --version                       Onap command service version. It is of type
                                      STRING. By default, it is false.
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
 -a | --no-auth                       whether to authenticate user or not. It is of
                                      type BOOL. By default, it is false.
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Results::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 ID     Feature group ID and is of type STRING.
 name   Feature group name and is of type STRING.
=======
 ID   Entitlement Pool ID and is of type STRING.
=======
 -x | --cloud-name        Onap cloud name. It is of type STRING.
 -y | --region-name       Onap cloud region name. It is of type STRING.
 -z | --resource-version  Onap cloud region version. It is of type UUID. It
                          is optional. By default, it is
                          93230cdb-aae3-4b87-a38a-fc4529e8aac9.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[9] vlm-create
-----------------------------------------------


usage: onap vlm-create

Create License Model

Onap service: sdc v1.0

Options::

 [-x | --vendor-name] [-y | --description] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vendor-name     vendor name. It is of type STRING.
 -y | --description     Description for License Model. It is of type
                        STRING. It is optional.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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

 ID   License Model ID and is of type UUID.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[10] vlm-entitlement-pool-create
-----------------------------------------------


usage: onap vlm-entitlement-pool-create

Create Entitlement Pool

Onap service: sdc v1.0

Options::

 [-x | --name] [-y | --vlm-id] [-e | --vlm-version]
 [-z | --description] [-g | --operational-scope] [-k | --manufacture-reference-number]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -x | --name                          Entitlement Pool name. It is of type STRING.
 -y | --vlm-id                        License Model ID. It is of type STRING.
 -e | --vlm-version                   License Model version. It is of type STRING.
 -z | --description                   Description for Entitlement Pool. It is of type
                                      STRING.
 -g | --operational-scope             Operational Scope. It is of type STRING. By
                                      default, it is Availability_Zone.
 -k | --manufacture-reference-number  Manufature Reference Number. It is of type STRING.
 -u | --onap-username                 Onap user name. It is of type STRING. By default,
                                      it is read from environment variable
                                      ONAP_USERNAME.
 -p | --onap-password                 Onap user password. It is of type STRING. By
                                      default, it is read from environment variable
                                      ONAP_PASSWORD. Secured.
 -m | --host-url                      Onap host url. It is of type URL. By default, it
                                      is read from environment variable ONAP_HOST_URL.
 -h | --help                          Onap command help message. It is of type STRING.
                                      By default, it is false.
 -v | --version                       Onap command service version. It is of type
                                      STRING. By default, it is false.
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
 -a | --no-auth                       whether to authenticate user or not. It is of
                                      type BOOL. By default, it is false.


Results::

 ID   Entitlement Pool ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[11] vsp-package
-----------------------------------------------


usage: onap vsp-package

Package Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-y | --vsp-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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



[12] vsp-submit
-----------------------------------------------


usage: onap vsp-submit

Submit Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-y | --vsp-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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



[13] vsp-checkout
-----------------------------------------------


usage: onap vsp-checkout

Checkout Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-y | --vsp-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -x | --vlm-id          License Model ID. It is of type STRING.
 -y | --vlm-version     License Model version. It is of type STRING.
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
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


<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
=======
Results::

 ID     Feature group ID and is of type STRING.
 name   Feature group name and is of type STRING.
=======
 -x | --cloud-name        Onap cloud name. It is of type STRING.
 -y | --region-name       Onap  region name. It is of type STRING.
 -z | --vim-id            Onap VIM unique id. It is of type UUID. By
                          default, it is
                          65c6f4af-893f-4130-aa79-9158fcaed040.
 -b | --resource-version  Onap vim resource version. It is of type STRING.
                          It is optional.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[14] vsp-show
-----------------------------------------------


usage: onap vsp-show

Details of the Vendor Software Product

Onap service: sdc v1.0

Options::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 [-x | --vsp-id] [-y | --vsp-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]
=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
=======
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
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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

>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_

where::

 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     Onap VSP version. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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
 vendor-name  Vendor name and is of type STRING.
 vendor-id    Vendor id and is of type STRING.
 version      Version and is of type STRING.
 status       status and is of type STRING.
 license-id   license aggreement and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[15] vsp-create
-----------------------------------------------


usage: onap vsp-create

Create Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-name] [-y | --vsp-description] [-z | --vsp-category]
 [-b | --vsp-subcategory] [-c | --vlm-version] [-e | --vlm-vendor]
 [-g | --vlm-id] [-i | --vlm-agreement-id] [-j | --vlm-feature-group-id]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
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
=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -x | --vlm-id          Onap VLM ID. It is of type STRING.
 -y | --vlm-version     VLM version. It is of type STRING.
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
=======
 -x | --service-type      Onap service type. It is of type STRING.
 -y | --service-type-id   Onap service type uuid. It is of type UUID. It is
                          optional. By default, it is
                          3def24ae-701e-4c98-91a6-859c0f206759.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[19] vlm-submit
-----------------------------------------------


usage: onap vlm-submit

Submit Vendor License Model

Onap service: sdc v1.0

Options::
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Results::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 ID   VSP ID and is of type STRING.
=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -x | --vlm-id          Onap VLM ID. It is of type STRING.
 -y | --vlm-version     VLM version. It is of type STRING.
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
=======
 -x | --service-type-id   Onap service type uuid. It is of type UUID. It is
                          optional. By default, it is
                          04ebd209-7800-43f4-bca4-aaf61e15783e.
 -y | --resource-version  Onap service resource version. It is of type
                          UUID. It is optional. By default, it is
                          7b301a17-5eb2-413b-b214-fd1659fdf049.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[16] vsp-add-artifact
-----------------------------------------------


usage: onap vsp-add-artifact

Upload the CSAR file to VSP

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-y | --vsp-version] [-z | --vsp-file]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     Onap VSP version. It is of type STRING.
 -z | --vsp-file        CSAR File path. It is of type BINARY.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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



[17] vsp-revert
-----------------------------------------------


usage: onap vsp-revert

Revert Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-y | --vsp-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
=======
 -x | --customer-name     Onap customer name. It is of type STRING.
 -z | --cloud-name        Onap cloud name. It is of type STRING.
 -r | --cloud-region      Onap cloud region. It is of type STRING.
 -c | --cloud-tenant-id   Onap cloud tenant id. It is of type STRING.
 -e | --service-type      Onap service type. It is of type STRING.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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

>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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



[18] vsp-checkout
-----------------------------------------------


usage: onap vsp-checkout

Checkout Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-y | --vsp-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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



[19] vsp-validate
-----------------------------------------------


usage: onap vsp-validate

Validated the uploaded Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-y | --vsp-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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


<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
=======
Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[24] vsp-create
-----------------------------------------------


usage: onap vsp-create

Create Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-name] [-y | --vsp-description] [-z | --vsp-category]
 [-b | --vsp-subcategory] [-c | --vsp-license-version] [-e | --vsp-vendor-name]
 [-g | --vsp-vendor-id] [-i | --license-agreement-id] [-j | --feature-group-id]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -x | --vsp-name              Onap VSP Name. It is of type STRING.
 -y | --vsp-description       Description for VSP. It is of type STRING. It is
                              optional.
 -z | --vsp-category          Category of the VSP. It is of type STRING. It is
                              optional. By default, it is
                              resourceNewCategory.generic.
 -b | --vsp-subcategory       Sub Category of VSP. It is of type STRING. It is
                              optional. By default, it is
                              resourceNewCategory.generic.abstract.
 -c | --vsp-license-version   License version. It is of type STRING. It is
                              optional. By default, it is 1.0.
 -e | --vsp-vendor-name       License Model Name. It is of type STRING.
 -g | --vsp-vendor-id         License Model ID. It is of type STRING.
 -i | --license-agreement-id  License Agreement ID. It is of type STRING.
 -j | --feature-group-id      Feature Group ID. It is of type STRING.
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
=======
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
Results::

 status   Validation status and is of type STRING.
 errors   Validation messages and is of type JSON.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[20] vsp-list
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
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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

 ID               VSP ID and is of type STRING.
 name             VSP Name and is of type STRING.
 vendor-name      Vendor name and is of type STRING.
 version          Version and is of type STRING.
 status           status and is of type STRING.
 license-id       license aggreement and is of type STRING.
 license-version  license version and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[21] vsp-checkin
-----------------------------------------------


usage: onap vsp-checkin

Checkin Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-y | --vsp-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
=======
 -x | --ems-id            Onap EMS unique id. It is of type UUID. By
                          default, it is
                          efba894e-20f4-4658-9d43-1f8736188d3d.
 -y | --resource-version  Onap vim resource version. It is of type STRING.
                          It is optional.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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



[22] service-model-checkin
-----------------------------------------------


usage: onap service-model-checkin

Checkin Service model in SDC

Onap service: sdc v1.0

Options::

 [-b | --service-model-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 -b | --service-model-id  Service model ID. It is of type STRING. It is
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
=======
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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



[28] vsp-show
-----------------------------------------------


usage: onap vsp-show

Details of the Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-id] [-y | --vsp-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     Onap VSP version. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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
 vendor-name  Vendor name and is of type STRING.
 vendor-id    Vendor id and is of type STRING.
 version      Version and is of type STRING.
 status       status and is of type STRING.
 license-id   license aggreement and is of type STRING.
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[23] service-model-distribute
-----------------------------------------------


usage: onap service-model-distribute

Distributes the Service model in SDC

Onap service: sdc v1.0

Options::

 [-b | --service-model-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          ONAP_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          ONAP_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable ONAP_HOST_URL.
=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-version     VSP version. It is of type STRING.
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
=======
 -x | --vnfm-id           Onap VNFM unique id. It is of type UUID. By
                          default, it is
                          d8a2726f-18a6-4bb1-904f-554a3fb70865.
 -y | --resource-version  Onap vim resource version. It is of type STRING.
                          It is optional.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
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
<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
=======
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[24] service-model-list
-----------------------------------------------


usage: onap service-model-list

List the service model in SDC

Onap service: sdc v1.0

Options::

 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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

 uuid                 UUID and is of type STRING.
 invariant-uuid       Invariant UUID and is of type STRING.
 name                 Name and is of type STRING.
 version              version and is of type STRING.
 status               status and is of type STRING.
 distribution-status  status and is of type STRING.
 description          description and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[25] service-model-certify-start
-----------------------------------------------


usage: onap service-model-certify-start

Starts the certification of Service model in SDC

Onap service: sdc v1.0

Options::

 [-b | --service-model-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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



[26] service-model-add-vf
-----------------------------------------------


usage: onap service-model-add-vf

Helps to add VF into service models in SDC

Onap service: sdc v1.0

Options::

 [-x | --service-model-id] [-y | --vf-id] [-z | --vf-name]
 [-b | --vf-version] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 -x | --service-model-id  Onap Service Name. It is of type STRING.
 -y | --vf-id             VF ID. It is of type STRING. It is optional.
 -z | --vf-name           VF ID. It is of type STRING. It is optional.
 -b | --vf-version        VF version. It is of type STRING. It is optional.
=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -b | --service-model-id  Service model ID. It is of type STRING. It is
                          optional.
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
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
=======
 -x | --customer-name   Onap customer name. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Results::

 ID   Service ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[27] service-model-certify-request
-----------------------------------------------


usage: onap service-model-certify-request

Request the certification of Service model in SDC

Onap service: sdc v1.0

Options::

 [-b | --service-model-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -b | --service-model-id  Service model ID. It is of type STRING. It is
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
=======
 -x | --cloud           Onap cloud. It is of type STRING.
 -y | --region          Onap cloud region. It is of type STRING.
 -z | --tenant-id       Onap cloud tenant id. It is of type STRING.
 -r | --tenant-name     Onap cloud tenant name. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
[28] service-model-revert
=======
[34] service-model-create
-----------------------------------------------


usage: onap service-model-create

Create Service model in SDC

Onap service: sdc v1.0

Options::

 [-x | --name] [-y | --description] [-z | --project-code]
 [-b | --category] [-c | --category-display-name] [-e | --icon-id]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
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
=======
 -x | --service-instace-id  unique id for service instance. It is of type
                            STRING.
 -u | --onap-username       Onap user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_USERNAME.
 -p | --onap-password       Onap user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_PASSWORD. Secured.
 -m | --host-url            Onap host url. It is of type URL. By default, it
                            is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
 -u | --onap-username           Onap user name. It is of type STRING. By default,
                                it is read from environment variable
                                OPEN_USERNAME.
 -p | --onap-password           Onap user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_PASSWORD. Secured.
 -m | --host-url                Onap host url. It is of type URL. By default, it
                                is read from environment variable OPEN_HOST_URL.
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


Results::

 ID   Service ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[35] service-model-distribute
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
-----------------------------------------------


usage: onap service-model-revert

Checkout Service model in SDC

Onap service: sdc v1.0

Options::

 [-b | --service-model-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -b | --service-model-id  Service model ID. It is of type STRING. It is
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
=======
 -x | --vnf-instace-id      id for vnf. It is of type STRING.
 -y | --service-instace-id  unique id for service instance. It is of type
                            STRING.
 -l | --lcp-cloudregion-id  AIC LCP node location identifier. It is of type
                            STRING.
 -z | --tenant-id           openstack tenant id (uuid). It is of type STRING.
 -u | --onap-username       Onap user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_USERNAME.
 -p | --onap-password       Onap user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_PASSWORD. Secured.
 -m | --host-url            Onap host url. It is of type URL. By default, it
                            is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[29] service-model-checkout
-----------------------------------------------


usage: onap service-model-checkout

Checkout Service model in SDC

Onap service: sdc v1.0

Options::

 [-b | --service-model-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 -b | --service-model-id  Service model ID. It is of type STRING. It is
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



[30] service-model-certify-complete
-----------------------------------------------


usage: onap service-model-certify-complete

Completes the certification of Service model in SDC

Onap service: sdc v1.0

Options::

 [-b | --service-model-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -b | --service-model-id  Service model ID. It is of type STRING. It is
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



[31] service2vf-model-list
-----------------------------------------------


usage: onap service2vf-model-list

List the VF in a given service model in SDC

Onap service: sdc v1.0

Options::

 [-z | --service-model-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
 -z | --service-model-id  Service model uuid. It is of type STRING.
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

 vf-uuid                UUID and is of type STRING.
 vf-name                name and is of type STRING.
 vf-customization-uuid  customization UUID and is of type STRING.
 vf-version             version and is of type STRING.
=======
 -c | --global-subscriber-id  unique id for customer. It is of type STRING.
 -b | --subscriber-name       subscriber name. It is of type STRING. It is
                              optional.
 -i | --instance-name         service instance name. It is of type STRING.
 -r | --supress-rollback      rollback changes if instantiation fails. It is of
                              type BOOL. It is optional. By default, it is
                              false.
 -u | --onap-username         Onap user name. It is of type STRING. By default,
                              it is read from environment variable
                              OPEN_USERNAME.
 -p | --onap-password         Onap user password. It is of type STRING. By
                              default, it is read from environment variable
                              OPEN_PASSWORD. Secured.
 -m | --host-url              Onap host url. It is of type URL. By default, it
                              is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[32] service-model-create
-----------------------------------------------


usage: onap service-model-create

Create Service model in SDC

Onap service: sdc v1.0

Options::

 [-x | --name] [-y | --description] [-z | --project-code]
 [-b | --category] [-c | --category-display-name] [-e | --icon-id]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

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


Results::

 ID   Service ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[33] service-model-approve
-----------------------------------------------


usage: onap service-model-approve

Approves the Service model in SDC

Onap service: sdc v1.0

Options::

 [-b | --service-model-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 -b | --service-model-id  Service model ID. It is of type STRING. It is
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



[34] vf-model-create
-----------------------------------------------


usage: onap vf-model-create

Create Virtual function from Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --name] [-y | --description] [-z | --vendor-name]
 [-b | --vsp-id] [-g | --vsp-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --name            Onap VF Name. It is of type STRING.
 -y | --description     Description for VF. It is of type STRING. It is
                        optional.
 -z | --vendor-name     Vendor name. It is of type STRING. It is optional.
 -b | --vsp-id          VSP ID. It is of type STRING. It is optional.
 -g | --vsp-version     VSP version. It is of type STRING. It is optional.
=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -b | --vf-id           VF ID. It is of type STRING. It is optional.
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
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
=======
 -x | --vnf-instace-id      id for vnf. It is of type STRING.
 -y | --service-instace-id  unique id for service instance. It is of type
                            STRING.
 -w | --vf-module-id        vf module id. It is of type STRING.
 -l | --lcp-cloudregion-id  AIC LCP node location identifier. It is of type
                            STRING.
 -z | --tenant-id           openstack tenant id (uuid). It is of type STRING.
 -u | --onap-username       Onap user name. It is of type STRING. By default,
                            it is read from environment variable
                            OPEN_USERNAME.
 -p | --onap-password       Onap user password. It is of type STRING. By
                            default, it is read from environment variable
                            OPEN_PASSWORD. Secured.
 -m | --host-url            Onap host url. It is of type URL. By default, it
                            is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Results::

 ID   VF ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[35] vf-model-checkin
-----------------------------------------------


usage: onap vf-model-checkin

Checkin Virtual function

Onap service: sdc v1.0

Options::

 [-b | --vf-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -b | --vf-id           VF ID. It is of type STRING. It is optional.
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
=======
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
                                OPEN_USERNAME.
 -p | --onap-password           Onap user password. It is of type STRING. By
                                default, it is read from environment variable
                                OPEN_PASSWORD. Secured.
 -m | --host-url                Onap host url. It is of type URL. By default, it
                                is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[36] vlm-revert
-----------------------------------------------


usage: onap vlm-revert

Revert Vendor License Model

Onap service: sdc v1.0

Options::

 [-x | --vlm-id] [-y | --vlm-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 -x | --vlm-id          Onap VLM ID. It is of type STRING.
 -y | --vlm-version     VLM version. It is of type STRING.
=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -b | --vf-id           VF ID. It is of type STRING. It is optional.
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
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
=======
 -x | --vendor-name                 vendor name. It is of type STRING.
 -y | --license-model-description   Description for License Model. It is of type
                                    STRING. It is optional.
 -u | --onap-username               Onap user name. It is of type STRING. By default,
                                    it is read from environment variable
                                    OPEN_USERNAME.
 -p | --onap-password               Onap user password. It is of type STRING. By
                                    default, it is read from environment variable
                                    OPEN_PASSWORD. Secured.
 -m | --host-url                    Onap host url. It is of type URL. By default, it
                                    is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[37] vlm-feature-group-create
-----------------------------------------------


usage: onap vlm-feature-group-create

Create feature group Pool

Onap service: sdc v1.0

Options::

 [-x | --name] [-y | --vlm-id] [-e | --vlm-version]
 [-z | --description] [-g | --vlm-key-group-id] [-b | --vlm-entitle-pool-id]
 [-c | --part-number] [-k | --manufacture-reference-number] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 -x | --name                          Feature group name. It is of type STRING.
 -y | --vlm-id                        License Model ID. It is of type STRING.
 -e | --vlm-version                   License Model version. It is of type STRING.
 -z | --description                   Description for feature group. It is of type
                                      STRING.
 -g | --vlm-key-group-id              VLM keygroup. It is of type STRING.
 -b | --vlm-entitle-pool-id           VLM Entitlement pool. It is of type STRING.
 -c | --part-number                   Part number. It is of type STRING.
 -k | --manufacture-reference-number  Manufature Reference Number. It is of type STRING.
 -u | --onap-username                 Onap user name. It is of type STRING. By default,
                                      it is read from environment variable
                                      ONAP_USERNAME.
 -p | --onap-password                 Onap user password. It is of type STRING. By
                                      default, it is read from environment variable
                                      ONAP_PASSWORD. Secured.
 -m | --host-url                      Onap host url. It is of type URL. By default, it
                                      is read from environment variable ONAP_HOST_URL.
 -h | --help                          Onap command help message. It is of type STRING.
                                      By default, it is false.
 -v | --version                       Onap command service version. It is of type
                                      STRING. By default, it is false.
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
 -a | --no-auth                       whether to authenticate user or not. It is of
                                      type BOOL. By default, it is false.


Results::

 ID   Feature group ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[38] vlm-submit
-----------------------------------------------


usage: onap vlm-submit

Submit Vendor License Model

Onap service: sdc v1.0

Options::

 [-x | --vlm-id] [-y | --vlm-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vlm-id          Onap VLM ID. It is of type STRING.
 -y | --vlm-version     VLM version. It is of type STRING.
=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 -b | --vf-id           VF ID. It is of type STRING. It is optional.
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
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
=======
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
                                  OPEN_USERNAME.
 -p | --onap-password             Onap user password. It is of type STRING. By
                                  default, it is read from environment variable
                                  OPEN_PASSWORD. Secured.
 -m | --host-url                  Onap host url. It is of type URL. By default, it
                                  is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[39] vf-model-certify-start
-----------------------------------------------


usage: onap vf-model-certify-start

Start certifying Virtual function

Onap service: sdc v1.0

Options::

 [-b | --vf-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -b | --vf-id           VF ID. It is of type STRING. It is optional.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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



[40] vf-model-list
-----------------------------------------------


usage: onap vf-model-list

List the VF resource model in SDC

Onap service: sdc v1.0

Options::

 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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



[41] vlm-entitlement-pool-list
-----------------------------------------------


usage: onap vlm-entitlement-pool-list

entitlement pool list in a license model

Onap service: sdc v1.0

Options::

 [-x | --vlm-id] [-y | --vlm-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vlm-id          License Model ID. It is of type STRING.
 -y | --vlm-version     License Model version. It is of type STRING.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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

 ID     Feature group ID and is of type STRING.
 name   Feature group name and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[42] vlm-feature-group-list
-----------------------------------------------


usage: onap vlm-feature-group-list

Feature group list in a license model

Onap service: sdc v1.0

Options::

 [-x | --vlm-id] [-y | --vlm-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vlm-id          License Model ID. It is of type STRING.
 -y | --vlm-version     License Model version. It is of type STRING.
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

 ID     Feature group ID and is of type STRING.
 name   Feature group name and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[43] vlm-checkout
-----------------------------------------------


usage: onap vlm-checkout

Checkout Vendor License Model

Onap service: sdc v1.0

Options::

 [-x | --vlm-id] [-y | --vlm-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vlm-id          Onap VLM ID. It is of type STRING.
 -y | --vlm-version     VLM version. It is of type STRING.
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



[44] vlm-list
-----------------------------------------------


usage: onap vlm-list

List License Model

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

 id           License ID and is of type UUID.
 vendor-name  Vendor  Name and is of type STRING.
 vlm-version  VLM version and is of type STRING.
 status       status and is of type STRING.
 description  License description and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[45] vlm-aggreement-create
-----------------------------------------------


usage: onap vlm-aggreement-create

Create license aggreement

Onap service: sdc v1.0

Options::

 [-x | --name] [-y | --vlm-id] [-e | --vlm-version]
 [-z | --description] [-g | --vlm-feature-group-id] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --name                  aggreement name. It is of type STRING.
 -y | --vlm-id                License Model ID. It is of type STRING.
 -e | --vlm-version           License Model version. It is of type STRING.
 -z | --description           Description for aggreement. It is of type STRING.
 -g | --vlm-feature-group-id  VLM feature group. It is of type STRING.
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


Results::

 ID   aggreement ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[46] vf-model-certify-request
-----------------------------------------------


usage: onap vf-model-certify-request

Request for certifying Virtual function

Onap service: sdc v1.0

Options::

 [-b | --vf-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -b | --vf-id           VF ID. It is of type STRING. It is optional.
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



[47] vf2vfmodule-model-list
-----------------------------------------------


usage: onap vf2vfmodule-model-list

List the VF modules in a given VF model in SDC

Onap service: sdc v1.0

Options::

 [-z | --vf-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -z | --vf-id           VF uuid. It is of type STRING.
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

 vsp-uuid               VSP uuid and is of type STRING.
 vsp-version            VSP version and is of type STRING.
 module-uuid            UUID and is of type STRING.
 module-invariant-uuid  Invariant UUID and is of type STRING.
 module-name            name and is of type STRING.
 module-version         version and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[48] vf-model-certify-complete
-----------------------------------------------


usage: onap vf-model-certify-complete

Complete certifying Virtual function

Onap service: sdc v1.0

Options::

 [-b | --vf-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -b | --vf-id           VF ID. It is of type STRING. It is optional.
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



[49] cloud-list
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



[50] sdnc-list
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



[51] sdnc-register
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
                        2821b55f-121c-47a8-8b47-6a555ce54e19.
 -y | --sdnc-id         Onap SDNC unique id. It is of type UUID. By
                        default, it is
                        d5575325-4d90-4ead-9656-8186504e9a88.
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



[52] sdnc-unregister
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



[53] vnfm-show
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
                        16d1e277-973c-409c-b221-5fd57cc7100a.
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



[54] vnfm-unregister
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
                          b6271696-01e3-43eb-a7f2-11d3b24746c4.
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



[55] vnfm-register
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
                          061e5c37-2a9a-4775-80e4-d15891a22ae4.
 -y | --vnfm-id           Onap VNFM unique id. It is of type UUID. By
                          default, it is
                          2e8957a6-75af-4508-9949-bf17eb7cf76a.
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



[56] vnfm-list
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



[57] vim-register
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
                        545935b3-ca9c-4cbf-bfb4-4a7297010aff.
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



[58] vim-unregister
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
                          0e129921-5700-48e2-ab9a-e4e4208d8b6b.
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



[59] vim-list
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



[60] ems-list
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



[61] ems-register
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
                        0f51b629-46dd-4c1d-bc23-c76d84e72bc2.
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



[62] ems-show
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
                        9b59cd0e-43c1-47d3-b943-4c9c23f49732.
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



[63] cloud-delete
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
                          efef37f1-f030-4bd2-b699-0a0e9c0ceb54.
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



[64] ems-unregister
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
                          3158d92f-e37b-450c-a86e-55c19309c3d3.
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



[65] subscription-list
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



[66] subscription-create
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



[67] subscription-delete
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



[68] customer-show
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



[69] customer-create
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



[70] customer-delete
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
                          99d76614-2e73-4aa2-9839-40cac60ccdd2.
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



[71] customer-list
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



[72] vf-list
-----------------------------------------------


usage: onap vf-list

List created VF instances for a service instance

Onap service: aai v11

Options::

 [-x | --customer-name] [-y | --service-type] [-z | --service-id]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -x | --customer-name   customer name. It is of type STRING.
 -y | --service-type    service subscription type. It is of type STRING.
 -z | --service-id      service ID. It is of type STRING.
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

 vf-id    VF ID for the given service and is of type STRING.
 vf-name  VF name and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[73] service-list
-----------------------------------------------


usage: onap service-list

List created service instance

Onap service: aai v11

Options::

 [-x | --customer-name] [-y | --service-type] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --customer-name   customer name. It is of type STRING.
 -y | --service-type    service subscription type. It is of type STRING.
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

 service-id           instance id of service and is of type STRING.
 service-name         Service name and is of type STRING.
 model-invariant-id   Model invariant id of service model and is of
                      type STRING.
 model-uuid           Model uuid for service model and is of type
                      STRING.
 description          service description and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[74] vf-show
-----------------------------------------------


usage: onap vf-show

Show details for VF

Onap service: aai v11

Options::

 [-x | --vf-id] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

 -x | --vf-id           VF identifier. It is of type STRING.
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



[75] tenant-create
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



[76] tenant-list
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



[77] tenant-delete
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
                          c22f376e-03fb-49f9-982c-9887b4e5da10.
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



[78] service-type-list
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



[79] service-type-create
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
                          d4445250-ef8d-4d78-a1b8-21719383acf7.
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



[80] service-type-delete
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
                          bb1938c7-1609-40fe-b4ba-75a3282ad3ad.
 -y | --resource-version  Onap service resource version. It is of type
                          UUID. It is optional. By default, it is
                          12b1a7c7-f8be-49cb-b320-dc103a24f00e.
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



[81] cloud-create
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



[82] service-delete
-----------------------------------------------


usage: onap service-delete

Delete service instance (experimental)

Onap service: so v3

Options::

 [-x | --service-instace-id] [-b | --model-invariant-id] [-i | --model-uuid]
 [-e | --model-name] [-g | --model-version] [-c | --customer-name]
 [-j | --cloud-region] [-k | --tenant-id] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

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



[83] vfmodule-delete
-----------------------------------------------


usage: onap vfmodule-delete

delete a VF module (experimental)

Onap service: so v3

Options::

 [-x | --vfmodule-id] [-j | --service-id] [-y | --vf-id]
 [-k | --vf-model-invariant-id] [-e | --vfmodule-name] [-g | --vfmodule-version]
 [-l | --cloud-region] [-z | --tenant-id] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --vfmodule-id             VF module Id. It is of type STRING.
 -j | --service-id              unique id for service. It is of type STRING.
 -y | --vf-id                   unique id for related VF. It is of type STRING.
 -k | --vf-model-invariant-id   vf model invariant id. It is of type STRING.
 -e | --vfmodule-name           vfmodule model name. It is of type STRING.
 -g | --vfmodule-version        vf module version. It is of type STRING.
 -l | --cloud-region            cloud region identifier. It is of type STRING.
 -z | --tenant-id               openstack tenant id. It is of type STRING.
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



[84] vfmodule-create
-----------------------------------------------


usage: onap vfmodule-create

Create a VF module

Onap service: so v3

Options::

 [-l | --cloud-region] [-B | --tenant-id] [-b | --vfmodule-invariant-id]
 [-c | --vfmodule-uuid] [-e | --vfmodule-name] [-g | --vfmodule-version]
 [-i | --module-name] [-j | --service-id] [-y | --vf-id]
 [-k | --vf-model-invariant-id] [-A | --vf-model-uuid] [-n | --vf-model-name]
 [-o | --vf-model-version] [-q | --vf-model-customization-id] [-r | --service-model-invariant-id]
 [-w | --service-model-uuid] [-x | --service-model-name] [-z | --service-model-version]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

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

 vfmodule-id  id for the created vnf and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[85] service-create
-----------------------------------------------


usage: onap service-create

Create a service instance using MSO

Onap service: so v3

Options::

 [-l | --cloud-region] [-b | --tenant-id] [-e | --model-invariant-id]
 [-g | --model-uuid] [-i | --model-name] [-j | --model-version]
 [-c | --customer] [-k | --instance-name] [-r | --supress-rollback]
 [-w | --service-type] [-u | --onap-username] [-p | --onap-password]
 [-m | --host-url] [-h | --help] [-v | --version]
 [-d | --debug] [-f | --format] [-s | --long]
 [-t | --no-title] [-a | --no-auth]

where::

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
 -w | --service-type     subscription service type. It is of type STRING.
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


Results::

 service-id   instance id for the created service. and is of
              type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[86] vf-delete
-----------------------------------------------


usage: onap vf-delete (experimental)

delete a VF

Onap service: so v3

Options::

 [-x | --vf-id] [-y | --service-instance-id] [-l | --cloud-region]
 [-z | --tenant-id] [-b | --model-invariant-id] [-c | --model-uuid]
 [-e | --model-name] [-g | --model-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

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



[87] vf-create
-----------------------------------------------


usage: onap vf-create

Create a VF

Onap service: so v3

Options::

 [-l | --cloud-region] [-z | --tenant-id] [-w | --product-family]
 [-o | --instance-name] [-y | --service-instance-id] [-b | --vf-model-invariant-id]
 [-c | --vf-model-uuid] [-e | --vf-model-name] [-g | --vf-model-version]
 [-i | --vf-model-customization-id] [-j | --service-model-invariant-id] [-k | --service-model-uuid]
 [-q | --service-model-name] [-n | --service-model-version] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

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

 vf-id  id for the created vnf and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



onap-1.0
==========================


[1] license-group-create
-----------------------------------------------


usage: onap license-group-create

Create License Group

Onap service: sdc v1.0

Options::

 [-x | --group-name] [-y | --license-model-id] [-z | --license-group-description]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -x | --group-name                  group name. It is of type STRING.
 -y | --license-model-id            License Model ID. It is of type STRING.
 -z | --license-group-description   Description for License Group. It is of type
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

 ID   License Group ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[2] ep-show
-----------------------------------------------


usage: onap ep-show

Details of the Entitlement Pool

Onap service: sdc v1.0

Options::

 [-x | --license-model-id] [-y | --pool-id] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --license-model-id  Onap License Model ID. It is of type STRING.
 -y | --pool-id           Onap Entitlement Pool ID. It is of type STRING.
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

 name         Entitlement Pool Name and is of type STRING.
 ID           Entitlement Pool ID and is of type STRING.
 description  Description for the Entitlement Pool and is of
              type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[3] license-model-create
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



[4] license-group-show
-----------------------------------------------


usage: onap license-group-show

Details of the License Group

Onap service: sdc v1.0

Options::

 [-x | --license-model-id] [-y | --license-group-id] [-u | --onap-username]
 [-p | --onap-password] [-m | --host-url] [-h | --help]
 [-v | --version] [-d | --debug] [-f | --format]
 [-s | --long] [-t | --no-title] [-a | --no-auth]

where::

 -x | --license-model-id  Onap License Model ID. It is of type STRING.
 -y | --license-group-id  Onap License Group ID. It is of type STRING.
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

 name         License Group Name and is of type STRING.
 ID           License Group ID and is of type STRING.
 description  Description for the License Group and is of type
              STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[5] vsp-upload
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



[6] vsp-create
-----------------------------------------------


usage: onap vsp-create

Create Vendor Software Product

Onap service: sdc v1.0

Options::

 [-x | --vsp-name] [-y | --vsp-description] [-b | --vsp-category]
 [-c | --vsp-subcategory] [-e | --vlm-version] [-g | --vlm-vendor]
 [-i | --vlm-id] [-j | --vlm-agreement-id] [-k | --vlm-feature-group-id]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

 -x | --vsp-name              Onap VSP Name. It is of type STRING.
 -y | --vsp-description       Description for VSP. It is of type STRING. It is
                              optional.
 -b | --vsp-category          Category of the VSP. It is of type STRING. It is
                              optional. By default, it is
                              resourceNewCategory.generic.
 -c | --vsp-subcategory       Sub Category of VSP. It is of type STRING. It is
                              optional. By default, it is
                              resourceNewCategory.generic.abstract.
 -e | --vlm-version           License version. It is of type STRING. It is
                              optional. By default, it is 1.0.
 -g | --vlm-vendor            License Model Name. It is of type STRING.
 -i | --vlm-id                License Model ID. It is of type STRING.
 -j | --vlm-agreement-id      License Agreement ID. It is of type STRING.
 -k | --vlm-feature-group-id  Feature Group ID. It is of type STRING.
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


Results::

 ID   VSP ID and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[7] vsp-list
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



[8] vsp-show
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



[9] vsp-submit
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



[10] vsp-checkin
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



[11] license-model-show
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



[12] ep-create
-----------------------------------------------


usage: onap ep-create

Create Entitlement Pool

Onap service: sdc v1.0

Options::

 [-x | --ep-name] [-y | --license-model-id] [-z | --ep-description]
 [-q | --threshold-value] [-b | --threshold-unit] [-c | --entitlement-metric]
 [-e | --aggregation-function] [-g | --operational-scope] [-k | --reference-number]
 [-u | --onap-username] [-p | --onap-password] [-m | --host-url]
 [-h | --help] [-v | --version] [-d | --debug]
 [-f | --format] [-s | --long] [-t | --no-title]
 [-a | --no-auth]

where::

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


Results::

<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
 ID   Entitlement Pool ID and is of type STRING.
=======
<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
 ID   License Model ID and is of type STRING.
=======
 -x | --vsp-id          Onap VSP ID. It is of type STRING.
 -y | --vsp-file        CSAR File path. It is of type BINARY.
 -u | --onap-username   Onap user name. It is of type STRING. By default,
                        it is read from environment variable
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



[13] subscription-list
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



[14] subscription-create
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
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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



[15] customer-show
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



[16] customer-list
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
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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



[17] customer-delete
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
                          6dc30068-de3c-483a-a9a8-7d483a39425d.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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



[18] customer-create
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
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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



<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
[19] service-type-create
=======
[16] customer-list
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
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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



[17] customer-show
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
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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

<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
=======
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
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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

>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
 name               Onap customer name and is of type STRING.
 subscriber-name    Onap subscriber name and is of type STRING.
 resource-version   Onap subscriber resource version and is of type
                    STRING.
 subscriber-type    Onap subscriber type and is of type STRING.


Error::

 On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>



<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
[18] service-type-create
=======
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
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
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
                          eb4a0d54-0e29-4174-8a3c-2c98e904a5f6.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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



[20] service-type-delete
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
                          31d3a8cb-d4ff-4b4e-a0d3-5f79f3062686.
 -y | --resource-version  Onap service resource version. It is of type
                          UUID. It is optional. By default, it is
                          b06576ca-74b8-42f2-815b-6f7b80f9941c.
 -u | --onap-username     Onap user name. It is of type STRING. By default,
                          it is read from environment variable
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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



[21] service-type-list
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
                        OPEN_USERNAME.
 -p | --onap-password   Onap user password. It is of type STRING. By
                        default, it is read from environment variable
                        OPEN_PASSWORD. Secured.
 -m | --host-url        Onap host url. It is of type URL. By default, it
                        is read from environment variable OPEN_HOST_URL.
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



<<<<<<< ab8942a839cc04dc8df1bf9cb19f375757f848b7
[22] cloud-list
=======
[21] subscription-create
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
                          OPEN_USERNAME.
 -p | --onap-password     Onap user password. It is of type STRING. By
                          default, it is read from environment variable
                          OPEN_PASSWORD. Secured.
 -m | --host-url          Onap host url. It is of type URL. By default, it
                          is read from environment variable OPEN_HOST_URL.
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



<<<<<<< 08468db7c00dd0594d6846c4c761f0582be9fc06
[22] subscription-list
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
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



cli-1.0
=======
open-cli
>>>>>>> Migrate docs to use OPEN_ instead of ONAP_
==========================


[1] schema-validate
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



[2] schema-refresh
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
