.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.

:orphan:

.. _open_cli_schema_version_1_0:

Open Command Line Interface (CLI) Schema Version (OCS) 1.0
==========================================================

Open CLI Platform (OCLIP) provides model based framework to implement
Linux Commands for any given software products, by using YAML template
based on the schematics defined in this document. In version 1.0,
following aspects of commands are modeled as YAML schematics:

* Software product/service information

* Command line arguments

* Command outputs

* Command execution details like HTTP, SNMP (profiles)

* Command usage and samples

open_cli_schema_version
-----------------------
OCLIP considers any YAML file with first line having the following entry
as OCS template.

    open_cli_schema_version: 1.0

Here, 1.0 version is first version of this schema. In future, this version
number will get incremented based on addition of new schematics or update of
existing schematics.

name
----
*name* entry allows to set the command name and it is recommended to use the
following format:

    <entity>-<action>

    entity - Resource or a feature, for which command is provided

    action - Functionality performed on the entity

    For example, to implement a command to *start* a given *service*.
    set the name as:

    **name** : **service-start**

*CAUTION*: name should not have any space character in it.

description
-----------
*description* entry allows to write detailed usage of the command. It could be
a line or a paragraph as given example here.

**a line**

    description: Start the given service

**a paragraph**

    description: |
        Start the given service. To see the available services in the system
        use the command *service-list*

info
-------
product
~~~~~~~~
*product* entry allows to tag the command template with the software product
name and version, for which command is implemented and is recommended to use
the following format:

    <product>-<version>

    product - Short name of the product

    action - Version of the product

    For example, to implement a command for Open Network Automation Platform
    (onap) version amsterdam, set the version as:

    **product** : **onap-amsterdam**

*CAUTION*: product should not have any space character in it.


parameters
----------
Every command has set of arguments to provide the input values and *parameters*
section allows to add the required arguments details such as name, description,
etc as list of entries.

name
~~~~
*name* entry uniquely identifies the given argument. It can be of any
alpha-numerical characters and dash(-). For example to provide the http port of
an service, the parameter could be:

    parameters:
      \- **name: service-port**

description
~~~~~~~~~~~
*description* entry allows to provide the details of the parameter. Its
supported in similar approach with command *description* defined in above
section. For example service-port could be described as:

    parameters:
      \- name: service-port

      **description: Service HTTP port.**

is_optional
~~~~~~~~~~~
*is_optional* entry allows to set the parameter is mandatory or not. By default,
this entry is false. For example service-port could be made as as optional:

    parameters:
      \- name: service-port

      description: Service HTTP port.

      **is_optional: true**

is_secured
~~~~~~~~~~~
*is_secured* entry allows to set the parameter is secured or not. By default,
this entry is false. This is very useful for password kind of parameters.

For example service-port could be made as in-secured:

    parameters:
      \- name: service-port

      description: Service HTTP port.

      is_optional: true

      **is_secured: false**

default_value
~~~~~~~~~~~~~
*default_value* entry helps to provide the default value for the given parameter
when that parameter is not provided during command execution.

Based on the *type* of parameter, default values are assigned as:

+---------------+------------------------------------------------------------+
|       Type    |              Default value                                 |
+===============+============================================================+
| bool          | false                                                      |
+---------------+------------------------------------------------------------+
| uuid          | Auto-generated uuid-4 string                               |
+---------------+------------------------------------------------------------+
| string        | Blank. Also it can be set default values from the system   |
|               | environment variable by mentioning it in the form of :     |
|               |                                                            |
|               | parameters:                                                |
|               |     - default_value: ${ENV-VARIABLE-NAME}                  |
+---------------+------------------------------------------------------------+

For example to provide the http port of an service, the parameter could be:

    parameters:
      \- name: service-port

      description: Service HTTP port.

      is_optional: true

      is_secured: false

      **default_value: 8080**


type
~~~~
*type* entry allows to set the type of parameter such as boolean, integer, etc.
For example to provide the http port of an service, the parameter type could be:

    parameters:
      \- name: service-port

      description: Service HTTP port.

      is_optional: true

      is_secured: false

      default_value: 8080

      **type: long**

Platform supports following types of parameter:

string
^^^^^^
Any parameter value having a work or a line, string type is appropriate one. By
default it is set to blank.

digit
^^^^^^
Any parameter value having digit such as integers or floating values. For this
type of parameter, platform does not set any default value. so while writing
the parameter schematics, author should set the *default_value* if needed.

json
^^^^
To set the value of parameter as JSON. Platform allows to input the JSON values
either as direct one line string for simple json or complete file path for
providing the complex json value. While user execute the command, based on the
value of the JSON parameter, it could given as string or file path.

File path could start in the form of file://, http://, ftp://.

text
^^^^
To set the value of parameter as text. Platform allows to input the text values
either as direct one line string for simple text or complete file path for
providing the complex text value. While user execute the command, based on the
value of the text parameter, it could given as string or file path.

File path could start in the form of file://, http://, ftp://.

yaml
^^^^
To set the value of parameter as yaml content. Platform allows to input the
yaml values as complete file path. While user execute the command, YAML file
needs to be created and provided that file's complete path as input value.

File path could start in the form of file://, http://, ftp://.

bool
^^^^
This type allows to set the parameter value to either true or false. By
default, its value is false, So, when user wants to input the boolean parameter
its sufficient to mention the parameter option with out mentioning 'true'.
For example, assume that command named 'login' defines the boolean input
parameter 'is_secure_connection' to set the service connection is secured or
not. For this command, while user input the value for parameter
'is_secure_connection', it is sufficient to mention the parameter without
passing value. Both of the following command will have same effect:

    login --is_secure_connection

    login --is_secure_connection true

uuid
^^^^
*uuid* type allows to make the parameter value as UUID. By default platform auto
generates uuid-4 formated string.

url
^^^
*url* type allows to make the parameter value of URL/URI. Platform does not
provide any default value for this type. so Author should provide the
*default_value*, if needed during the template is created.

binary
^^^^^^
*binary* type is very useful to pass the parameter as binary file and user
should pass the complete path of the file.

array
^^^^^^
To provide the same parameter multiple times array type helps. For example, when
the command 'rm' is used, multiple file paths could be provided to remove all of
them. In this kind of scenarios, array type supports and each parameter type
is *string*

map
^^^^
This is similar to *array* type and only differs the way the values are passed.
In this type, values should be in the form of
'<parameter-name>=<parameter-value>'


Optional and Positional parameters
----------------------------------
The input arguments for a given command usually provided with prefixing options
names or directly giving the value. Earlier case is called optional arguments
and later is called as positional arguments. OCLIP platform supports both the
type.

For optional arguments, two type of options are supported:

*short option*: option name is usually single character and when user input
the corresponding parameter, who will prefix with single dash(-).

*long option*: option name is usually more than one characters and when user
input the corresponding parameter, who will prefix with double dash(-).

For example, the service port could be defined as :

    parameters:
      \ - name: service-port

      description: Service HTTP port.

      is_optional: true

      is_secured: false

      default_value: 8080

      type: long

      **short_option: p**

      **long_option:  service-port**

When user inputs the service port, it could either of following formats

    --service-port 8080

    -p 8080

For postional arguments, author should not define both *short_option* and
*long_option* and when OCLIP process this template, it will consider as
positional arguments. There could be more than one positional arguments could
be defined for a command, and OCLIP treats the sequence of the positional
parameters defined under *parameters* section is consider as it's position. For
example, consider the below example:

    parameters:
        \- name: param1

        short_option: p1

        long_option: param1

        \- name: param2

        \- name: param3

        short_option: p3

        long_option: param3

        \- name: param4

        \- name: param5

        short_option: p5

        long_option: param5

In this case, param2 and param4 are positional arguments as they are defined
with out short and long options. so position of param2 is 1, for param4, it's 2.
When user inputs the value as :

    --param1 v1 -p3 v3 v2 -p5 v5 v4

OCLIP platform identifies the positions in sequence. so for param2, value v2
will be assigned and for param4, value v4 will be assigned.

*NOTE*: User should only concern on the sequence of positional arguments while
giving the values and no need to worry about the position at which value should
be provided. so all of below sequence will yield the same result.

    --param1 v1 -p3 v3 **v2** -p5 v5 **v4**

    **v2** --param1 v1 **v4** -p5 v5 -p3 v3

    --param1 v1 -p3 -p5 v5 v3 **v2** **v4**

default_parameters
------------------
OCLIP platform provides following default parameters for every command and
author is allowed to customize the inclusion or exclusion of these input
parameters for a given command.

name: host-username
~~~~~~~~~~~~~~~~~~~

    type: string

    description: Host user name

    short_option: u

    long_option: host-username

    default_value: ${OPEN_CLI_HOST_USERNAME}

    is_optional: false

name: host-password
~~~~~~~~~~~~~~~~~~~

    type: string

    description: Host user password

    short_option: p

    long_option: host-password

    default_value: ${OPEN_CLI_HOST_PASSWORD}

    is_secured: true

    is_optional: false

name: host-url
~~~~~~~~~~~~~~
    type: url

    description: Host url

    short_option: m

    long_option: host-url

    is_optional: false

    default_value: ${OPEN_CLI_HOST_URL}

name: help
~~~~~~~~~~
    type: string

    description: Command help message

    short_option: h

    long_option: help

    default_value: false

name: version
~~~~~~~~~~~~~
    type: string

    description: Command service version

    short_option: v

    long_option: version

    default_value: false

name: debug
~~~~~~~~~~~
    type: bool

    description: Enable debug output

    short_option: d

    long_option: debug

    default_value: false

name: format
~~~~~~~~~~~~
    type: string

    description: Output formats, supported formats such as table, csv, json,
    yaml

    short_option: f

    long_option: format

    default_value: table

name: long
~~~~~~~~~~~
    type: bool

    description: whether to print all attributes or only short attributes

    short_option: s

    long_option: long

    default_value: false

name: no-title
~~~~~~~~~~~~~~
    type: bool

    description: whether to print title or not

    short_option: t

    long_option: no-title

    default_value: true

name: no-auth
~~~~~~~~~~~~~
    type: bool

    description: whether to authenticate user or not

    short_option: a

    long_option: no-auth

    default_value: false

*NOTE*: no-auth parameter is very helpful to by-pass the login and logout phase
of each commands. Please refer *service* section to find more details on login
and logout.

results
-------
Every command produces the output and *results* section helps to define the
details of command outputs such as list of output attributes, the direction in
which, result could be printed. More details are as follows.

direction
~~~~~~~~~
*direction* entity allows to configure the direction in which the results to be
printed. It can be:

* *portrait* : To print the results in two columns. First column is the name of
  the attribute and second column is the value of the attribute. It's more useful
  while command does operations like creation of resource, viewing of resources.

* *landscape* : To print the results row vise in landscape mode. It's more
  useful while command does operations like listing of resource.

attributes
~~~~~~~~~~
name
^^^^
*name* entry uniquely identifies the given attribute. It can be of any
alpha-numerical characters and dash(-). For example to print the status of an
service, the attribute could be:

    attributes:
      \- **name: service-status**

description
^^^^^^^^^^^
*description* entry allows to provide the details of the attribute. It's
supported in similar approach with command *description* defined in above
section. For example service-status could be described as:

    attributes:
      \- name: service-status

        **description: Service current status.**

type
^^^^
*type* entry allows to set the type of attribute such as string, digit, etc.
Similar to the parameter's type. currently it supports only string type.

For example, service-status could be:

 attributes:

 \- name: service-status

 description: Service current status.

 **type: string**

scope
^^^^^
When a given command produces many results, most of the time no need to print
all the attributes. SO OCLIP platform provides this *scope* entry to configure
the attribute is printed by default or user should request to print it. So
there are two scopes:

* *short* : attribute configured with this option will always printed by default

* *long* : attribute configured with this option will get printed only when
  user inputs the default parameter *long*, defined in *default_parameters*
  section. So to print all attributes of a command, user will input parameter:

    --long

A sample attribute for service-status could be:

  attributes:
      \- name: service-status

      description: Service current status.

      type: string

      **scope: short**

default_value
^^^^^^^^^^^^^
In some scenarios, author can set the default value to attribute which OCLIP assigns,
when the value for that attribute is not available from back-end service in product.


http
----
OCLIP is enhanced to support REST API based products and *http* section is
provided to capture all required details for performing http operation for the
given command.

service
-------
Whether its information technology(IT) domain or communication technology(CT)
domain, every software product is made of one or more service components. For
example, onap has different components like aai, msb, etc and these components
provides different kind of resources/features and functionalities.

*service* entry allows to mention the details of the given software product's
service. This is an section and is having entries defined in below sections.

name
~~~~
*name* entry allows to configure the service name. For example, to configure
service component 'aai' in onap-amsterdam product,

    service:
        name: aai

*CAUTION*: This entry is very signification to discover this service from the
service catalog and name should be matching with the service name registered
in the catalog.

version
~~~~~~~
*version* entry allows to mention the particular version of service for which
command is implemented. For example, the service 'aai' in the product
'onap-amsterdam' having versions like v11.

    service:
        version: v11

*CAUTION*: This entry is very signification to discover this service from the
service catalog and version should be matching with the service version
registered in the catalog.

mode
~~~~
Some software product provides catalog service , where all service of that
product could be discovered. While other product does not. OCLIP provides
support for both kind of these products to implement commands and *mode*
entry allows to configure this mode of operation.

CLIP supports in two different mode.

In *catalog* mode, OCLIP will discover the service details based on given
*name* and *version* from the configured *host-url* parameter. For example,
the product 'onap-amsterdam' provides the service 'msb' as the catalog service where
all other services will get registered. so OCLIP can discover the given
service such as 'aai' from the catalog service 'msb'. In this mode, *host-url*
will be configured with the *msb* service url. In this case:

    service:
        mode: catalog

*NOTE*: To see the details of *host-url*, refer the section default_parameters

In *direct* mode, OCLIP will not perform the discovery operation and consider
the given *host-url* as the direct service url. In this case:

    service:
        mode: direct

*NOTE*: To see the details of *host-url*, refer the section default_parameters

--------------------

auth
~~~~
There are different kind of authentication and authorization approach exist and
for OCLIP provides support for following approaches. Based on the approach
configured in the template, OCLIP will login before executing the command and
logout afterwards.

none
^^^^^
In this approach, no login and logout will be performed. This is useful during
the development cycle, as well as some services are available in public
without authentication of user. In this approach, OCLIP ignores the given
*host-username* and *host-password*. So the none auth is defined by:

    service:
        auth: none

*NOTE*: To see the details of *host-username* and *host-password*, refer the
section default_parameters


basic
^^^^^
This is HTTP basic authentication approach and given *host-username* and
*host-password* values are used to find the hash and use it as Authentication
value. So the none auth is defined by:

    service:
        auth: basic

*NOTE*: To see the details of *host-username* and *host-password*, refer the
section default_parameters

--------------------

request
~~~~~~~
*request* section captures all HTTP request information as:

uri
^^^
*uri* entry allows to mention the REST API URI. Based on the *service mode*,
this entry will vary. * when the mode is 'direct', it should be configured with
out *host-url* portion in it. For example, if the REST API is
'<host-url>/v1/service1/resource1, in which

* /v1/service1 - base path
* /resource1 - service resource path.

then this entry will be:

    request:
        uri: /v1/service1/resource1

when the mode is 'catalog', OCLIP will discover the  base path from the
'catalog' service, so this entry need to be configured only with resource path
as:

    request:
        uri: /resource1

method
^^^^^^
*method* entry allows to configure the HTTP methods GET, PUT, POST, DELETE,
etc. For example, to get the resource1:

    request:
        uri: /resource1

        method: GET

body
^^^^
*body* entry allows to mention the request body in json format, by default.
And OCLIP adds 'application/json' header in the HTTP request. Otherwise, body
could have complete path to binary file, in case request body is binary and
*multipart_entity_name* should be configured with entity name provided by REST
API.

headers
^^^^^^^
*headers* entry allows to add REST API specific headers. By default OCLIP adds
'application/json' as content-type and accept, also it will adds authentication
headers such as 'Authentication' in case *auth* is of type 'basic'.

For example, to add the sample header :

    request:
        uri: /resource1

        method: GET

        headers:
            header1: value1

            header2: value2

queries
^^^^^^^
*queries* entry allows to add REST API specific queries. For example, to add
the sample queries :

    request:
        uri: /resource1

        method: GET

        queries:
            q1: value1

            q2: value2


context
^^^^^^^
*context* entry allows to customize the HTTP request and response processing.

Following are the supported customization parameters:

*remove_empty_node* : By default, OCLIP does not remove the empty json entries
  in request body. Otherwise set this entry to true as below.

    request:

        context:
            remove_empty_node: true

success_codes
~~~~~~~~~~~~~
Every REST API has set of success codes and OCLIP will treat the HTTP request
made based on the value configured in these http sections, only if
*success_codes* contains the HTTP response status code.

result_map
~~~~~~~~~~
This section allows to configure the require 'jpath' expression to retrieve the
values from the HTTP response body.

*NOTE*: Currently only http response body is supported only with json type.

For example, if a http response '{"service_status": "green"} then to retrieve
the service status and assign to result *attribute* service_status as :

    result_map:
        service_status: $b{$.service_status}

Here, $b is detailed in section 'macros' of this document. and
'$.service_status' is jpath expression.

macros
-------
OCLIP platform provides various marcos to fill *http* entries with the value
of *parameters*, *headers* , etc Every macro is in the form of <macro name>
followed by {<macro details>}Followings are the supported macros:

+------------------+------------------------------------------------------------+
|       Macro      |               Definitions                                  |
+==================+============================================================+
| ${param-name}    | To retrieve the value from parameter named 'param-name'    |
+------------------+------------------------------------------------------------+
| $s{env:env-name} | To retrieve the value from environment variable 'env-name' |
+------------------+------------------------------------------------------------+
| $s{uuid}         | To set the value in uuid4 format                           |
+------------------+------------------------------------------------------------+
| $h{header-name}  | To retrieve the value from header named 'header-name'      |
+------------------+------------------------------------------------------------+
| $q{query-name}   | To retrieve the value from query named 'query-name'        |
+------------------+------------------------------------------------------------+
| $b{jpath}        | To retrieve the value from response body using the 'jpath' |
|                  | expression.                                                |
+------------------+------------------------------------------------------------+

samples
-------

OCLIP provides way to setup and verify the OCS yaml by using the open_cli_sample_version 1.0 specification,
which provides options to capture the samples and expected out for the given moco environment.
