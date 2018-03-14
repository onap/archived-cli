/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onap.cli.main;

import jline.TerminalFactory;
import jline.console.ConsoleReader;
import org.apache.commons.io.IOUtils;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandInvalidSample;
import org.onap.cli.fw.error.OnapCommandWarning;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.output.OnapCommandPrintDirection;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.output.OnapCommandResultAttributeScope;
import org.onap.cli.fw.output.OnapCommandResultType;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;
import org.onap.cli.fw.utils.OnapCommandDiscoveryUtils;
import org.onap.cli.main.conf.OnapCliConstants;
import org.onap.cli.main.interactive.StringCompleter;
import org.onap.cli.main.utils.OnapCliArgsParser;
import org.onap.cli.sample.yaml.SampleYamlGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * Oclip Command Line Interface (CLI).
 *
 */
public class OnapCli {

    private static Logger LOG = LoggerFactory.getLogger(OnapCli.class);

    private List<String> args = new ArrayList<>();

    private String product = null;

    private int exitCode = -1;

    public OnapCli(String[] args) {
        this.args.addAll(Arrays.asList(args));
    }

    public OnapCli(String product, String[] args) {
        this(args);
        this.product = product;
    }

    private void exitSuccessfully() {
        this.exitCode = OnapCliConstants.EXIT_SUCCESS;
    }

    private void exitFailure() {
        this.exitCode = OnapCliConstants.EXIT_FAILURE;
    }

    protected void print(String msg) {
        System.out.println(msg);
    }

    private void print(Throwable throwable) {
        this.print(throwable.getMessage());
        LOG.error(throwable.getMessage(), throwable);
    }

    private String getShortOption(String opt) {
        return OnapCommandParameter.printShortOption(opt);
    }

    private String getLongOption(String opt) {
        return OnapCommandParameter.printLongOption(opt);
    }

    public int getExitCode() {
        return this.exitCode;
    }

    /**
     * Handles help. --help or -h
     */
    public void handleHelp() {
        try {
            if ((args.size() == 1) && (this.getLongOption(OnapCliConstants.PARAM_HELP_LOGN).equals(args.get(0))
                    || this.getShortOption(OnapCliConstants.PARAM_HELP_SHORT).equals(args.get(0)))) {
                this.print(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("oclip-readme.txt")));
                String help = OnapCommandRegistrar.getRegistrar().getHelp();
                this.print(help);
                this.exitSuccessfully();
            }
        } catch (Exception e) {
            this.print(e);
            this.exitFailure();
        }
    }

    /**
     * Handles version. --version or -v
     */
    public void handleVersion() {
        try {
            if ((args.size() == 1) && (this.getLongOption(OnapCliConstants.PARAM_VERSION_LONG).equals(args.get(0))
                    || this.getShortOption(OnapCliConstants.PARAM_VERSION_SHORT).equals(args.get(0)))) {
                String version = OnapCommandRegistrar.getRegistrar().getVersion();
                this.print(version);
                this.exitSuccessfully();
            }
        } catch (Exception e) {
            this.print(e);
            this.exitFailure();
        }
    }


    /**
     * Handles profile. --profile or -c
     */
    public void handleProfile() {
        try {
            if ((this.args.size() >= 2) && (this.getLongOption(OnapCliConstants.PARAM_PROFILE_LONG).equals(this.args.get(0))
                    || this.getShortOption(OnapCliConstants.PARAM_PROFILE_SHORT).equals(this.args.get(0)))) {

                OnapCommandRegistrar.getRegistrar().setProfile(
                        this.args.get(1),
                        new ArrayList<String>(),
                        new ArrayList<String>());
                //Make space of interactive mode/command mode
                this.args.remove(0); //--profile or -c
                this.args.remove(0); //profile name
            }
        } catch (Exception e) {
            this.print(e);
            this.exitFailure();
        }
    }

    /**
     * Handles batch command. --param-file or -p
     * CAUTION: This option should be passed after --profile always.
     */
    public void handleBatchCommand() {
        try {
            if ((this.args.size() >= 3) && (this.getLongOption(OnapCliConstants.PARAM_PARAM_FILE_LONG).equals(this.args.get(0))
                    || this.getShortOption(OnapCliConstants.PARAM_PARAM_FILE_SHORT).equals(this.args.get(0)))) {

                String paramFilePath = this.args.get(1);

                //Make space of interactive mode/command mode
                this.args.remove(0); //--param-file or -p
                this.args.remove(0); //file name

                //Read YAML and loop thru it
                // one
                // - param-long-option-1: value
                // - param-long-option-1: value
                // - positional-arg1
                // - positional-arg2
                // two
                // - param-long-option-1: value
                // - param-long-option-1: value
                // - positional-arg1
                // - positional-arg2
                try {
                    Map<String, Object> values = (Map<String, Object>) OnapCommandDiscoveryUtils.loadYaml(paramFilePath);

                    for (Entry<String, Object> cmdsParam: values.entrySet()) {
                        List<String> args = new ArrayList<>();
                        args.add(this.args.get(0));
                        for (Object param: (List)cmdsParam.getValue()) {
                            if (param instanceof Map) { //optional args
                                Map <String, String> paramMap = (Map<String, String>) param;
                                String paramName = paramMap.keySet().iterator().next();
                                Object paramValue = paramMap.get(paramName);
                                args.add("--" + paramName);
                                args.add(paramValue.toString());
                            } else { //positional args
                                args.add(param.toString());
                            }
                        }

                        this.handleCommand(args);
                    }

                } catch (Exception e) { // NOSONAR
                    this.print("Failed to read param file " + paramFilePath);
                    this.print(e);
                }
            }
        } catch (Exception e) {
            this.print(e);
            this.exitFailure();
        }
    }

    public void verifyCommand(OnapCommand cmd) throws OnapCommandException {
        List<Map<String, ?>> testSuite = OnapCommandRegistrar.getRegistrar().getTestSuite(cmd.getName());

        OnapCommandResult testSuiteResult = new OnapCommandResult();
        testSuiteResult.setType(OnapCommandResultType.TABLE);
        testSuiteResult.setPrintDirection(OnapCommandPrintDirection.LANDSCAPE);
        testSuiteResult.setIncludeTitle(true);

        OnapCommandResultAttribute sampleFileAtt = new OnapCommandResultAttribute();
        OnapCommandResultAttribute sampleIdAtt = new OnapCommandResultAttribute();
        OnapCommandResultAttribute resultAtt = new OnapCommandResultAttribute();

        sampleFileAtt.setName("Test");
        sampleIdAtt.setName("SampleId");
        resultAtt.setName("Result");

        testSuiteResult.setRecords(Arrays.asList(sampleFileAtt,
                sampleIdAtt,
                resultAtt));

        for (Map<String, ?> sampleTest : testSuite) {

            sampleFileAtt.getValues().add((String) sampleTest.get(OnapCommandConstants.VERIFY_SAMPLE_FILE_ID));
            sampleIdAtt.getValues().add((String) sampleTest.get(OnapCommandConstants.VERIFY_SAMPLE_ID));

            cmd = OnapCommandRegistrar.getRegistrar().get(args.get(0));
            OnapCliArgsParser.populateParams(cmd.getParameters(), (List<String>) sampleTest.get(OnapCommandConstants.VERIFY_INPUT));


            Optional<OnapCommandParameter> contextOpt = cmd.getParameters().stream()
                    .filter(e -> e.getName().equals(OnapCommandConstants.VERIFY_CONTEXT_PARAM))
                    .findFirst();

            if (contextOpt.isPresent()) {
                HashMap map = new HashMap();
                map.put(OnapCommandConstants.VERIFY_MOCO, sampleTest.get(OnapCommandConstants.VERIFY_MOCO));
                contextOpt.get().setValue(map);
            }

            OnapCommandResult testResult = cmd.execute();
            String actualOutput = testResult.print().trim();
            String expectedOutput = (String) sampleTest.get(OnapCommandConstants.VERIFY_OUPUT);
            expectedOutput = expectedOutput == null ? "" : expectedOutput.trim();

            if (actualOutput.equals(expectedOutput)) {
                resultAtt.getValues().add(OnapCommandConstants.VERIFY_RESULT_PASS);
            } else {
                resultAtt.getValues().add(OnapCommandConstants.VERIFY_RESULT_FAIL);
            }
        }
        this.print(testSuiteResult.print());
    }
    /**
     * Handles Interactive Mode.
     */
    public void handleInteractive() { // NOSONAR
        if (args.isEmpty()) {
            ConsoleReader console = null;
            try {
                OnapCommandRegistrar.getRegistrar().setInteractiveMode(true);
                console = createConsoleReader();
                String line = null;

                while ((line = console.readLine()) != null) {
                    line = line.trim();
                    if (OnapCliConstants.PARAM_INTERACTIVE_EXIT.equalsIgnoreCase(line)) {
                        break;
                    } else if (OnapCliConstants.PARAM_INTERACTIVE_CLEAR.equalsIgnoreCase(line)) {
                        console.clearScreen();
                        continue;
                    }
                    this.args.clear();
                    this.args.addAll(Arrays.asList(line.split(OnapCliConstants.PARAM_INTERACTIVE_ARG_SPLIT_PATTERN)));

                    if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_USE)) {
                        if (args.size() == 1) {
                            this.print("Please use it in the form of use <product-version>.\nSupported versions: " +
                                    OnapCommandRegistrar.getRegistrar().getAvailableProductVersions());
                        } else {
                            try {
                                OnapCommandRegistrar.getRegistrar().setEnabledProductVersion(args.get(1));
                                console = createConsoleReader();
                            } catch (OnapCommandException e) {
                                this.print(e);
                            }
                        }

                    } else if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_HELP)) {
                        try {
                            this.print(OnapCommandRegistrar.getRegistrar().getHelpForEnabledProductVersion());
                            this.print(OnapCli.getDirectiveHelp());
                        } catch (OnapCommandException e) {
                            this.print(e);
                        }

                    } else if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_VERSION)) {
                        this.args = Arrays.asList(new String [] {this.getLongOption(OnapCliConstants.PARAM_VERSION_LONG)});
                        handleVersion();

                    } else if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_PROFILE)) {
                        if (args.size() == 1) {
                            this.print("Please use it in the form of 'profile <profile-name>'\n");
                            this.print("Available profiles: ");
                            this.print(OnapCommandRegistrar.getRegistrar().getUserProfiles().toString());
                        } else {
                            this.args.set(0, this.getLongOption(OnapCliConstants.PARAM_PROFILE_LONG));
                            handleProfile();
                        }

                    } else if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_SET)) {
                        if (args.size() > 1) {
                            String [] paramEntry = args.get(1).trim().split("=", 2);
                            if (paramEntry.length == 2) {
                                OnapCommandRegistrar.getRegistrar().addParamCache(paramEntry[0].trim(), paramEntry[1].trim());
                            } else {
                                this.print("Please use it in the form of 'set <param-name>=<param-value>'");
                            }
                        } else {
                            this.print(OnapCommandRegistrar.getRegistrar().getParamCache().toString());
                        }

                    } else if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_UNSET)) {
                        if (args.size() > 1) {
                            for (int i = 1; i <args.size(); i++) {
                                OnapCommandRegistrar.getRegistrar().removeParamCache(args.get(i));
                            }
                        }
                    } else {
                        if (args.size() == 1 && args.get(0).trim().isEmpty()) {
                            //Ignore blanks // NOSONAR
                            continue;
                        }

                        handleCommand(new ArrayList<>());
                    }
                }
            } catch (IOException e) { // NOSONAR
                this.print("Failed to read console, " + e.getMessage());
            } catch (OnapCommandException e) {
                this.print(e);
                this.exitFailure();
            } finally {
                try {
                    TerminalFactory.get().restore();
                } catch (Exception e) { // NOSONAR
                }
                this.exitSuccessfully();
            }
        }
    }

    /**
     * Handles command.
     */
    public void handleCommand(List<String> params) {
        OnapCommand cmd;
        if (!args.isEmpty()) {
            try {
                if (this.product != null) {
                    cmd = OnapCommandRegistrar.getRegistrar().get(args.get(0), this.product);
                } else {
                    cmd = OnapCommandRegistrar.getRegistrar().get(args.get(0));
                }
            } catch (Exception e) {
                this.print(e);
                this.exitFailure();
                return;
            }

            try {

                // verify
                if(args.contains(OnapCommandConstants.VERIFY_LONG_OPTION)
                        || args.contains(OnapCommandConstants.VERIFY_SHORT_OPTION)) {
                    verifyCommand(cmd);
                    this.exitSuccessfully();
                    return;
                }
                // check for help or version
                if (args.size() == 2) {
                    if (this.getLongOption(OnapCliConstants.PARAM_HELP_LOGN).equals(args.get(1))
                            || this.getShortOption(OnapCliConstants.PARAM_HELP_SHORT).equals(args.get(1))) {
                        String help = cmd.printHelp();
                        this.print(help);
                        this.exitSuccessfully();
                        return;
                    } else if (this.getLongOption(OnapCliConstants.PARAM_VERSION_LONG).equals(args.get(1))
                            || this.getShortOption(OnapCliConstants.PARAM_VERSION_SHORT).equals(args.get(1))) {
                        String version = cmd.printVersion();
                        this.print(version);
                        this.exitSuccessfully();
                        return;
                    }
                }

                //refer params from profile
                for (OnapCommandParameter param: cmd.getParameters()) {
                    if (OnapCommandRegistrar.getRegistrar().getParamCache().containsKey(
                            cmd.getInfo().getService() + ":" + param.getLongOption())) {
                        param.setValue(OnapCommandRegistrar.getRegistrar().getParamCache().get(
                                cmd.getInfo().getService() + ":" + param.getLongOption()));
                    } else if (OnapCommandRegistrar.getRegistrar().getParamCache().containsKey(param.getLongOption())) {
                        param.setValue(OnapCommandRegistrar.getRegistrar().getParamCache().get(param.getLongOption()));
                    }
                }

                //load the parameters value from the map read from param-file
                if (params != null && !params.isEmpty()) {
                    OnapCliArgsParser.populateParams(cmd.getParameters(), params);
                }

                OnapCliArgsParser.populateParams(cmd.getParameters(), args);

                OnapCommandResult result = cmd.execute();

                this.print(result.getDebugInfo());
                this.print(result.print());
                this.exitSuccessfully();

                generateSmapleYaml(cmd);
            } catch (OnapCommandWarning w) {
                this.print(w);
                this.print(cmd.getResult().getDebugInfo());
                this.exitSuccessfully();
            } catch (Exception e) {
                this.print(e);
                this.print(cmd.getResult().getDebugInfo());
                this.exitFailure();
            }
        }
    }

    /**
     * Handles all client input.
     */
    public void handle() {
        this.handleHelp();

        if (this.exitCode == -1) {
            this.handleVersion();
        }

        if (this.exitCode == -1) {
            this.handleProfile();
        }

        if (this.exitCode == -1) {
            this.handleBatchCommand();
        }

        if (this.exitCode == -1) {
            this.handleInteractive();
        }

        if (this.exitCode == -1) {
            this.handleCommand(new ArrayList<>());
        }
    }

    public static String getDirectiveHelp() throws OnapCommandHelpFailed {
        OnapCommandResult help = new OnapCommandResult();
        help.setType(OnapCommandResultType.TABLE);
        help.setPrintDirection(OnapCommandPrintDirection.LANDSCAPE);

        OnapCommandResultAttribute attr = new OnapCommandResultAttribute();
        attr.setName(OnapCommandConstants.NAME.toUpperCase());
        attr.setDescription(OnapCommandConstants.DESCRIPTION);
        attr.setScope(OnapCommandResultAttributeScope.SHORT);
        help.getRecords().add(attr);

        OnapCommandResultAttribute attrDesc = new OnapCommandResultAttribute();
        attrDesc.setName(OnapCommandConstants.DESCRIPTION.toUpperCase());
        attrDesc.setDescription(OnapCommandConstants.DESCRIPTION);
        attrDesc.setScope(OnapCommandResultAttributeScope.SHORT);
        help.getRecords().add(attrDesc);

        attr.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_CLEAR);
        attrDesc.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_CLEAR_MSG);

        attr.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_EXIT);
        attrDesc.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_EXIT_MSG);

        attr.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_VERSION);
        attrDesc.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_VERSION_MSG);

        attr.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_USE);
        attrDesc.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_USE_MSG);

        attr.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_SET);
        attrDesc.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_SET_MSG);

        attr.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_UNSET);
        attrDesc.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_UNSET_MSG);

        attr.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_HELP);
        attrDesc.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_HELP_MSG);

        attr.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_PROFILE);
        attrDesc.getValues().add(OnapCliConstants.PARAM_INTERACTIVE_PROFILE_MSG);
        try {
            return "\n\nDirectives:\n" + help.print();
        } catch (OnapCommandException e) {
            throw new OnapCommandHelpFailed(e);
        }
    }

    /**
     * Creates console reader object.
     *
     * @return ConsoleReader
     * @throws IOException
     *             exception
     */
    private ConsoleReader createConsoleReader() throws IOException {
        ConsoleReader console = new ConsoleReader(); // NOSONAR
        try {
            StringCompleter strCompleter = new StringCompleter(OnapCommandRegistrar.getRegistrar().listCommandsForEnabledProductVersion());
            strCompleter.add(OnapCliConstants.PARAM_INTERACTIVE_EXIT,
                    OnapCliConstants.PARAM_INTERACTIVE_CLEAR,
                    OnapCliConstants.PARAM_INTERACTIVE_USE,
                    OnapCliConstants.PARAM_INTERACTIVE_HELP,
                    OnapCliConstants.PARAM_INTERACTIVE_VERSION,
                    OnapCliConstants.PARAM_INTERACTIVE_SET,
                    OnapCliConstants.PARAM_INTERACTIVE_UNSET,
                    OnapCliConstants.PARAM_INTERACTIVE_PROFILE);
            console.addCompleter(strCompleter);
            console.setPrompt(OnapCliConstants.PARAM_INTERACTIVE_PROMPT + ":" + OnapCommandRegistrar.getRegistrar().getEnabledProductVersion() + ">");
        } catch (OnapCommandException e) { // NOSONAR
            this.print("Failed to load oclip commands," + e.getMessage());
        }

        return console;
    }


    private void generateSmapleYaml(OnapCommand cmd) throws OnapCommandException {
        if (Boolean.parseBoolean(OnapCommandConfig.getPropertyValue(OnapCommandConstants.SAMPLE_GEN_ENABLED)) && this.getExitCode() == OnapCliConstants.EXIT_SUCCESS) {
            try {
                SampleYamlGenerator.generateSampleYaml(args, cmd.getResult().print(),
                        OnapCommandRegistrar.getRegistrar().getEnabledProductVersion(),
                        OnapCommandConfig.getPropertyValue(OnapCommandConstants.SAMPLE_GEN_TARGET_FOLDER) + "/" + cmd.getSchemaName().replaceAll(".yaml", "") + "-sample.yaml",
                        cmd.getResult().isDebug());
            } catch (IOException error) {
                throw new OnapCommandInvalidSample(args.get(0), error);
            }
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            array
     */
    public static void main(String[] args) {
        OnapCli cli = new OnapCli(args);
        cli.handle();
        System.exit(cli.getExitCode());
    }

}