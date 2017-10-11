.. _developer_guide:
.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.

CLI developer guide
===================

ONAP CLI could be developed as by using plug-ins or YAML.

As a Plug-in
-------------
Plug-in approach is useful for implementing commands for those products does not support REST API.
It uses this approach to provide the commands for its platform related operations and provides following commands as plug-ins:

* Schema-validate : To validate the OCS YAML
* Schema-refresh: To enable the newly added commands

Also it offers flexibility to implement any kind of commands. For example, specific plug-in command is provided in CLI to handle
HTTP commands, which helps to develop commands by **No coding, just by texting**

How to implement
~~~~~~~~~~~~~~~~

Please follow the steps given below, to implement new commands in ONAP:

1. Create new mvn module (ex: demo) under plugins project

2. Use the plugins/sample project as reference and update new module demo project pom.xml

3. Add new command yaml with unique name under 'src/main/resources/onap-cli-schema'

3. For a command to implement as plug-in, create new plug-in class as follows::

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

* 'org.onap.cli.sample.OnapHelloWorldCommand' extends 'OnapCommand' and is annotated with OnapCommandSchema, which carries
the command name, product version and schema file name, which is placed under 'src/main/resources/onap-cli-schema' folder.

* getParametersMap() helps to get the arguments value given for a command before executing it

* getResult().getRecordsMap() helps to set the attributes values of command after executing it


4. Add the new plug-in class package path into 'src/main/resources/META-INF/services/org.onap.cli.fw.OnapCommand' file

5. Now command is ready. Build the project by running 'mvn clean install'. ONAP CLI framework automatically will deliver these
commands as part of ONAP CLI zip file as part of this build.

6. To test this command, run the command 'onap hello-world --name amesterdam'

As a YAML
---------

All of the ONAP commands could be implemented as YAML using HTTP profile. so to created a new command, please follow the instructions
given below:

1. Install the latest ONAP CLI using the guide installation_guide_.

2. under onap-cli-schema folder, add new YAML file by referring open_cli_schema_version_1_0_.

3. Run 'onap schema-refresh' command to take the new yaml. It is recommeded to validate the yaml before running this command.

3. To test this command, run the command 'onap CMD-NAME --help'


How to validate command schema
------------------------------

Please use the command 'onap schema-validate' to validate the YAML before testing its functionality.

