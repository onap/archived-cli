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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import org.onap.cli.main.conf.OnapCliConstants;
import org.onap.cli.main.interactive.StringCompleter;
import org.onap.cli.main.utils.OnapCliArgsParser;
import org.onap.cli.sample.yaml.SampleYamlGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jline.TerminalFactory;
import jline.console.ConsoleReader;

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
        this.args = Arrays.asList(args);
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
            if ((args.size() == 2) && (this.getLongOption(OnapCliConstants.PARAM_PROFILE_LONG).equals(args.get(0))
                        || this.getShortOption(OnapCliConstants.PARAM_PROFILE_SHORT).equals(args.get(0)))) {

                OnapCommandRegistrar.getRegistrar().setProfile(args.get(1));
                //Make space of interactive mode
                this.args = new ArrayList<>();
            }
        } catch (Exception e) {
            this.print(e);
            this.exitFailure();
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
                    this.args = Arrays.asList(line.split(OnapCliConstants.PARAM_INTERACTIVE_ARG_SPLIT_PATTERN));

                    if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_USE)) {
                        if (args.size() == 1) {
                            this.print("Please input the product version to use, supported versions: " +
                                    OnapCommandRegistrar.getRegistrar().getAvailableProductVersions());
                        } else {
                            try {
                                OnapCommandRegistrar.getRegistrar().setEnabledProductVersion(args.get(1));
                                console.close();
                                console = createConsoleReader();
                            } catch (OnapCommandException e) {
                                this.print(e);
                            }
                        }
                    } else if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_HELP)) {
                        try {
                            this.print(OnapCommandRegistrar.getRegistrar().getHelpForEnabledProductVersion());
                            this.print(this.getDirectiveHelp());
                        } catch (OnapCommandException e) {
                            this.print(e);
                        }
                    } else if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_VERSION)) {
                        this.args = Arrays.asList(new String [] {this.getLongOption(OnapCliConstants.PARAM_VERSION_LONG)});
                        handleVersion();
                    } else if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_PROFILE)) {
                        if (args.size() == 1) {
                            this.print("Please use it in the form of 'profile <profile-name>'");
                        } else {
                            this.args = Arrays.asList(new String [] {
                                    this.getLongOption(OnapCliConstants.PARAM_PROFILE_LONG),
                                    this.args.get(1)});
                            handleProfile();
                        }
                    } else if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_SET)) {
                        if (args.size() > 1) {
                            String [] paramEntry = args.get(1).trim().split("=");
                            if (paramEntry.length >= 2) {
                                OnapCommandRegistrar.getRegistrar().addParamCache(paramEntry[0].trim(), paramEntry[1].trim());
                            } else {
                                this.print("Please use it in the form of 'set param-name=param-value'");
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

                        handleCommand();
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
                if (console != null) {
                    console.close();
                }
                this.exitSuccessfully();
            }
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
        try(ConsoleReader console = new ConsoleReader()){
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
    }

    /**
     * Handles command.
     */
    public void handleCommand() {
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

                for (OnapCommandParameter param: cmd.getParameters()) {
                    if (OnapCommandRegistrar.getRegistrar().getParamCache().containsKey(
                            cmd.getInfo().getService() + ":" + param.getLongOption())) {
                        param.setValue(OnapCommandRegistrar.getRegistrar().getParamCache().get(
                                cmd.getInfo().getService() + ":" + param.getLongOption()));
                    } else if (OnapCommandRegistrar.getRegistrar().getParamCache().containsKey(param.getLongOption())) {
                        param.setValue(OnapCommandRegistrar.getRegistrar().getParamCache().get(param.getLongOption()));
                    }
                }

                OnapCliArgsParser.populateParams(cmd.getParameters(), args);
                OnapCommandResult result = cmd.execute();

                this.print(result.getDebugInfo());
                this.print(result.print());
                this.exitSuccessfully();

                generateSmapleYaml(cmd);
            } catch (OnapCommandWarning w) {
                this.print(cmd.getResult().getDebugInfo());
                this.exitSuccessfully();
            } catch (Exception e) {
                this.print(e);
                this.print(cmd.getResult().getDebugInfo());
                this.exitFailure();
            }
        }
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
            this.handleInteractive();
        }

        if (this.exitCode == -1) {
            this.handleCommand();
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
