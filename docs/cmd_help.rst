.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.


.. toctree::
   :maxdepth: 2


.. _cli_cmd_help:

open-cli
==========================
[1] schema-refresh
-----------------------------------------------


usage: oclip schema-refresh

Oclip command to refresh schemas stored in open-cli-schema folders.

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

Oclip command to validate schema

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
