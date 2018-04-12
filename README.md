[comment]: # (# Copyright 2018 Huawei Technologies Co., Ltd.)
[comment]: # (# )
[comment]: # (Licensed under the Apache License, Version 2.0 (the "License")
[comment]: # (# you may not use this file except in compliance with the License.)
[comment]: # (# You may obtain a copy of the License at)
[comment]: # (#)
[comment]: # (#     http://www.apache.org/licenses/LICENSE-2.0)
[comment]: # (#)
[comment]: # (# Unless required by applicable law or agreed to in writing, software)
[comment]: # (# distributed under the License is distributed on an "AS IS" BASIS,)
[comment]: # (# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.)
[comment]: # (# See the License for the specific language governing permissions and)
[comment]: # (# limitations under the License.)

Open Command-line interface Platform (OCLIP)
============================================

Provides unified commands to operate any cloud enabled software
products from Linux/Web console.

Set the following environment variable, before using OCLIP:

**OPEN_CLI_HOME** - Complete path of directory, where OCLIP is installed.

To Run a command
-----------------
Configure the following environment variables:

1. **OPEN_CLI_HOST_URL** - Catalog service URL or a service URL
2. **OPEN_CLI_HOST_USERNAME** - Service user name
3. **OPEN_CLI_HOST_PASSWORD** - Service password

Run **oclip <command>** from Linux console.

To Run in Interactive mode
--------------------------
Run **oclip** from Linux console.

And use the directive set to set the values for following parameters:

1. **host-url** - Catalog service URL or a service URL
2. **host-username** - Service user name
3. **host-password** - Service password

NOTE: This mode is available as Web command console when OCLIP is running from docker.

Select the product version
--------------------------
CLI framework is enhanced to handle multiple product versions at same
time. so to choose the product version, set evironment variable
**OPEN_CLI_PROUDCT_IN_USE**.

NOTE: In interactive mode, product version can be selected using
typing **use <product-version>**

Run *oclip [-v|--version]* to see the available product version details.

Set the parameter values
------------------------
Use the directive 'set' for setting the values for parameters and 'unset' for un-seting the values.

Help
----
*oclip [-h|--help]*
*oclip <command> [-h|--help]*

Debug Mode
----------
To run in debug mode, set following environment variables:

1. OPEN_CLI_DEBUG - By default its false, otherwise Set to true
2. OPEN_CLI_DEBUG_PORT - By default it is 5005, otherwise set to new TCP port number
