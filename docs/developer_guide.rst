.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.

.. _cli_developer_guide:

CLI developer guide
===================

OCLI provides following approaches for developing CLI:

As a Plug-in
------------
The plug-in approach is useful for implementing commands for products that do not support REST APIs.
It uses this approach to provide the commands for its platform-related operations and provides the following commands as plug-ins:

* Schema-validate : To validate the OCS YAML
* Schema-refresh: To enable the newly added commands

It also offers the flexibility to implement any kind of command. For example, a specific plug-in command is provided in the CLI to handle
HTTP commands, which helps to develop commands by **No coding, just by texting**

Follow the steps below to implement new plug-in commands for ONAP:

#. Create a new mvn module (ex: demo) under plugins project.

#. Use the plugins/sample project as a reference and update the new module demo project pom.xml file.

#. Add new command YAML with a unique name under 'src/main/resources/open-cli-schema'.

#. To implement a command as a plug-in, create a new plug-in class as follows: ::

    package org.onap.cli.sample;

    import java.util.Map;

    import org.onap.cli.fw.OnapCommand;
    import org.onap.cli.fw.OnapCommandSchema;
    import org.onap.cli.fw.error.OnapCommandException;
    import org.onap.cli.fw.input.OnapCommandParameter;

    /**
     * Hello world.
     */
    @OnapCommandSchema(name = "hello-world", version = "sample-1.0", schema = "hello-world.yaml")
    public class OnapHelloWorldCommand extends OnapCommand {

        @Override
        protected void run() throws OnapCommandException {
            //Read the input arguments
            Map<String, OnapCommandParameter> paramMap = getParametersMap();
            OnapCommandParameter nameP = paramMap.get("name");
            String name = String.valueOf(nameP.getValue());

            //Process command
            String output = "Hello " + name;

            //Populate outputs
            this.getResult().getRecordsMap().get("output").getValues().add(output);
       }
    }

Note the following points:

* 'org.onap.cli.sample.OnapHelloWorldCommand' extends 'OnapCommand' and is annotated with OnapCommandSchema, which carries the command name, product version and schema file name, and is placed under the 'src/main/resources/onap-cli-schema' folder.

* getParametersMap() helps to get the arguments value given for a command before executing it

* getResult().getRecordsMap() helps to set the attributes values of command after executing it

#. Add the new plug-in class package path into the 'src/main/resources/META-INF/services/org.onap.cli.fw.OnapCommand' file

#. Now the command is ready. Build the project by running 'mvn clean install'. OCLIP framework automatically delivers these commands as part of the OCLIP zip file as part of this build.

#. To test this command, run the command 'oclip hello-world --name amsterdam'

As a YAML
---------

All OCLIP commands can be implemented as YAML using HTTP profile.

Follow the steps below to implement new commands for ONAP using YAML:

#. Install the latest OCLIP using the guide :ref:`cli_installation_guide`.

#. Under the open-cli-schema folder, add a new YAML file by referring to :ref:`open_cli_schema_version_1_0`.

#. Use the command 'oclip schema-validate' to validate the YAML before testing its functionality.

#. Run 'oclip schema-refresh' command to take the new YAML file. We recommended validating the YAML before running this command.

#. To test this command, run the command 'oclip CMD-NAME --help'.

Sample YAML
~~~~~~~~~~~~

Find more details about YAML specification at :ref:`open_cli_schema_version_1_0`.

Sample hello-world YAML ::

    open_cli_schema_version: 1.0

    name: hello-world-http

    description: First cmd hello world using http running under lighttpd in cli at http://<cli-ip>:8080/version.json

    version: sample-1.0

    parameters:
        - name: name
          description: name of the person
          long_option: name
          short_option: b
          default_value: ${DEMO_NAME}
          type: string
          is_optional: false

    results:
        direction: landscape
        attributes:
          - name: output
            description: hello world output
            type: string
            scope: short

    http:
        request:
            uri: /version.json
            method: GET

        service:
            name: sample-service
            version: v1
            auth: none
            mode: direct

        success_codes:
            - 200
            - 201

        result_map:
            output: Hello ${name}, You are running on $b{$.name} $b{$.version}
