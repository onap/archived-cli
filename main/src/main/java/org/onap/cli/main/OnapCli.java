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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

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
import org.onap.cli.fw.store.OnapCommandExecutionStore;
import org.onap.cli.fw.store.OnapCommandExecutionStore.ExecutionStoreContext;
import org.onap.cli.fw.utils.OnapCommandDiscoveryUtils;
import org.onap.cli.main.conf.OnapCliConstants;
import org.onap.cli.main.interactive.StringCompleter;
import org.onap.cli.main.utils.OnapCliArgsParser;
import org.onap.cli.sample.yaml.SampleYamlGenerator;
import org.open.infc.grpc.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jline.TerminalFactory;
import jline.console.ConsoleReader;

/**
 * OCLIP Command Line Interface (CLI).
 *
 */
public class OnapCli {

    private static Logger log = LoggerFactory.getLogger(OnapCli.class);

    private List<String> args = new ArrayList<>();

    private List<String> argsParamFile = new ArrayList<>();

    private String product = null;

    private String profile = null;

    private String paramFile = null;

    private String rpcHost = null;

    private String rpcPort = null;

    private boolean printHelp = false;

    private boolean printVersion = false;

    private String requestId = null;

    private String cmdName = null;

    private int exitCode = -1;

    public OnapCli(String[] args) {
        this.setArgs(args);
    }

    public OnapCli() {
    }

    public void resetExitCode() {
        this.exitCode = -1;
    }

    public void setArgs(String [] args) {
        //--help --version --requestId --rpc-host xxx --rpc-port xxx --product xxx --profile xxx --param-file xxx CMD blah blah

        int cmdIdx = 0; //index of CMD
        while(args.length > cmdIdx) {
            //no options given, directly command name invoked
            if (!args[cmdIdx].startsWith("-")) {
                break;
            }

            if (args[cmdIdx].equals(OnapCommandParameter.printLongOption(OnapCommandConstants.RPC_PRODUCT))) {
                this.product = args[++cmdIdx];
                cmdIdx++; //move to next option
            } else if (args[cmdIdx].equals(OnapCommandParameter.printLongOption(OnapCommandConstants.RPC_PROFILE))) {
                this.profile = args[++cmdIdx];
                cmdIdx++; //move to next option
            } else if (args[cmdIdx].equals(OnapCommandParameter.printLongOption(OnapCommandConstants.RPC_HOST))) {
                this.rpcHost = args[++cmdIdx];
                cmdIdx++; //move to next option
            } else if (args[cmdIdx].equals(OnapCommandParameter.printLongOption(OnapCommandConstants.RPC_PORT))) {
                this.rpcPort = args[++cmdIdx];
                cmdIdx++; //move to next option
            } else if (args[cmdIdx].equals(OnapCommandParameter.printLongOption(OnapCliConstants.PARAM_PARAM_FILE_LONG))) {
                this.paramFile = args[++cmdIdx];
                cmdIdx++; //move to next option
            } else if (args[cmdIdx].equals(OnapCommandParameter.printLongOption(OnapCommandConstants.RPC_REQID))) {
                this.requestId = args[++cmdIdx];
                cmdIdx++; //move to next option
            } else if (args[cmdIdx].equals(OnapCommandParameter.printLongOption(OnapCliConstants.PARAM_HELP_LOGN)) ||
                    args[cmdIdx].equals(OnapCommandParameter.printShortOption(OnapCliConstants.PARAM_HELP_SHORT))) {
                this.printHelp = true;
                cmdIdx++; //move to next option
            } else if (args[cmdIdx].equals(OnapCommandParameter.printLongOption(OnapCliConstants.PARAM_VERSION_LONG)) ||
                    args[cmdIdx].equals(OnapCommandParameter.printShortOption(OnapCliConstants.PARAM_VERSION_SHORT))) {
                this.printVersion = true;
                cmdIdx++; //move to next option
            }
        }

        if (args.length > cmdIdx) {
            this.cmdName = args[cmdIdx];
            cmdIdx ++;
        }

        this.args.clear();

        //add all args starting from the command name
        for (int i=cmdIdx; i<args.length; i++) {
            this.args.add(args[i]);
        }
    }

    private void exitSuccessfully() {
        this.exitCode = OnapCliConstants.EXIT_SUCCESS;
    }

    private void exitFailure() {
        this.exitCode = OnapCliConstants.EXIT_FAILURE;
    }

    protected void print(String msg) {
        System.out.println(msg); //NOSONAR
    }

    protected void printerr(String msg) {
        System.err.println(msg); //NOSONAR
    }

    private void print(Throwable throwable) {
        String error = throwable.getMessage() != null ? throwable.getMessage() : "";
        this.print(error);
        log.error(error, throwable);
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
            if (this.printHelp) {
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
            if (this.printVersion) {
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
     * Handles profile. --profile
     */
    public void handleProfile() {
        try {
            if (this.profile != null) {
                OnapCommandRegistrar.getRegistrar().setProfile(
                        this.profile,
                        new ArrayList<>(),
                        new ArrayList<>());
            }
        } catch (Exception e) {
            this.print(e);
            this.exitFailure();
        }
    }

    /**
     * Handles batch command. --param-file
     */
    public void handleBatchCommand() {
        try {
            if (this.paramFile != null) {
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
                    Map<String, Object> values = (Map<String, Object>) OnapCommandDiscoveryUtils.loadYaml(this.paramFile);

                    for (Entry<String, Object> cmdsParam: values.entrySet()) {
                        for (Object param: (List)cmdsParam.getValue()) {
                            if (param instanceof Map) { //optional args
                                Map <String, String> paramMap = (Map<String, String>) param;
                                String paramName = paramMap.keySet().iterator().next();
                                Object paramValue = paramMap.get(paramName);
                                argsParamFile.add(this.getLongOption(paramName));
                                argsParamFile.add(paramValue.toString());
                            } else { //positional args
                                argsParamFile.add(param.toString());
                            }
                        }
                    }

                } catch (Exception e) { // NOSONAR
                    this.print("Failed to read param file " + this.paramFile);
                    this.print(e);
                }
            }
        } catch (Exception e) {
            this.print(e);
            this.exitFailure();
        }
    }

    public void verifyCommand(OnapCommand cmd) throws OnapCommandException {

        OnapCliArgsParser.populateParams(cmd.getParameters(), args);

        Optional<OnapCommandParameter> contextOptArg = cmd.getParameters().stream()
                .filter(e -> e.getName().equals(OnapCommandConstants.VERIFY_CONTEXT_PARAM))
                .findFirst();

        List<Map<String, Object>> testSuite = OnapCommandRegistrar.getRegistrar().getTestSuite(
                cmd.getName(),
                cmd.getInfo().getProduct());

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

            cmd = OnapCommandRegistrar.getRegistrar().get(cmd.getName(),
                    cmd.getInfo().getProduct());
            List<String> arguments = (List<String>) sampleTest.get(OnapCommandConstants.VERIFY_INPUT);

            OnapCliArgsParser.populateParams(cmd.getParameters(), arguments);
            this.print("\n***************Test Command: \n" + sampleTest.get(OnapCommandConstants.VERIFY_INPUT).toString());

            cmd.getParametersMap().get(OnapCommandConstants.DEFAULT_PARAMETER_DEBUG).setValue(Boolean.TRUE);

            Optional<OnapCommandParameter> contextOpt = cmd.getParameters().stream()
                    .filter(e -> e.getName().equals(OnapCommandConstants.VERIFY_CONTEXT_PARAM))
                    .findFirst();

            if (contextOpt.isPresent()) {
                HashMap<String, Object> map = new HashMap<>();

                Object moco = sampleTest.get(OnapCommandConstants.VERIFY_MOCO);
                if (moco == null) {
                    continue;
                }
                map.put(OnapCommandConstants.VERIFY_MOCO, moco);

                if (contextOptArg.isPresent()) {
                    OnapCommandParameter contextArg = contextOptArg.get();
                    map.putAll((Map) contextArg.getValue());
                }

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
            this.printerr(testResult.getDebugInfo());
            this.print("\n***************Expected Output: \n" + expectedOutput);
            this.print("\n***************Actual Output: \n" + actualOutput);
        }

        this.print(testSuiteResult.print());
    }

    /**
     * Handles Interactive Mode.
     */
    public void handleInteractive() { // NOSONAR
        if (this.cmdName == null) {
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
                    } else {
                        this.args = new ArrayList<>();
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
                        this.print(OnapCommandRegistrar.getRegistrar().getHelpForEnabledProductVersion());
                        this.print(OnapCli.getDirectiveHelp());
                        } else if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_VERSION)) {
                            this.printVersion = true;
                            handleVersion();

                        } else if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_PROFILE)) {
                            if (args.size() == 1) {
                                this.print("Please use it in the form of 'profile <profile-name>'\n");
                                this.print("Available profiles: ");
                                this.print(OnapCommandRegistrar.getRegistrar().getUserProfiles().toString());
                            } else {
                                this.profile = args.get(1);
                                handleProfile();
                            }

                        } else if (!args.isEmpty() && this.args.get(0).equals(OnapCliConstants.PARAM_INTERACTIVE_SET)) {
                            if (args.size() > 1) {
                                String[] paramEntry = args.get(1).trim().split("=", 2);
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
                                for (int i = 1; i < args.size(); i++) {
                                    OnapCommandRegistrar.getRegistrar().removeParamCache(args.get(i));
                                }
                            }
                        } else {
                            if (!(args.size() == 1 && args.get(0).trim().isEmpty())) {
                                this.setArgs(this.args.toArray(new String[]{}));
                                handleCommand();
                            }
                        }
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
    public void handleCommand() {
        OnapCommand cmd = null;
        if (this.cmdName != null) {
            try {
                if (this.product != null) {
                    cmd = OnapCommandRegistrar.getRegistrar().get(this.cmdName, this.product);
                } else {
                    cmd = OnapCommandRegistrar.getRegistrar().get(this.cmdName);
                }
            } catch (Exception e) {
                this.print(e);
                this.exitFailure();
                return;
            }

            ExecutionStoreContext executionStoreContext = null;

            try {
                //Registrar identified this command marked with rpc as true and it will make direct RPC command call...
                if (cmd.isRpc() && !this.cmdName.equals("schema-rpc")) {
                    this.handleRpcCommand(cmd);
                    return;
                }

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
                if (this.profile != null) {

                    Map<String, String> paramCache = new HashMap<>(); //NOSONAR
                    if (this.product == null)
                        paramCache = OnapCommandRegistrar.getRegistrar().getParamCache();
                    else
                        paramCache = OnapCommandRegistrar.getRegistrar().getParamCache(this.product);

                    for (OnapCommandParameter param: cmd.getParameters()) {
                        if (paramCache.containsKey(
                                cmd.getInfo().getService() + ":" + cmd.getName() + ":" + param.getLongOption())) {
                            param.setValue(paramCache.get(
                                    cmd.getInfo().getService() + ":" + cmd.getName() + ":" + param.getLongOption()));
                        } else if (paramCache.containsKey(
                                cmd.getInfo().getService() + ":" + param.getLongOption())) {
                            param.setValue(paramCache.get(
                                    cmd.getInfo().getService() + ":" + param.getLongOption()));
                        } else if (paramCache.containsKey(param.getLongOption())) {
                            param.setValue(paramCache.get(param.getLongOption()));
                        }
                    }
                }

                //load the parameters value from the map read from param-file
                if (!this.argsParamFile.isEmpty()) {
                    OnapCliArgsParser.populateParams(cmd.getParameters(), this.argsParamFile);
                }

                OnapCliArgsParser.populateParams(cmd.getParameters(), this.args);

                //start the execution
                    if (this.requestId != null && this.product != null && !this.requestId.isEmpty()&& !(this.product.equalsIgnoreCase("open-cli") &&
                            this.cmdName.equalsIgnoreCase("execution-list"))) {
                        String input = cmd.getArgsJson(true);
                        executionStoreContext = OnapCommandExecutionStore.getStore().storeExectutionStart(
                                this.requestId,
                                cmd.getInfo().getProduct(),
                                cmd.getInfo().getService(),
                                this.cmdName,
                                this.profile,
                                input);
                    }

                cmd.setExecutionContext(executionStoreContext);
                OnapCommandResult result = cmd.execute();

                this.handleTracking(cmd);

                if (result.isPassed()) {
                    this.exitSuccessfully();
                    generateSmapleYaml(cmd);
                } else {
                    this.exitFailure();
                }
            } catch (OnapCommandWarning w) {
                if (cmd.getExecutionContext() != null) {
                    OnapCommandExecutionStore.getStore().storeExectutionEnd(
                            cmd.getExecutionContext(),
                            w.getMessage(),
                            null,
                            cmd.getResult().getDebugInfo(),
                            cmd.getResult().isPassed());
                }

                this.print(w);
                this.printerr(cmd.getResult().getDebugInfo());
                this.exitSuccessfully();
            } catch (Exception e) {
                if (executionStoreContext != null) {
                    OnapCommandExecutionStore.getStore().storeExectutionEnd(
                            executionStoreContext,
                            null,
                            e.getMessage(),
                            cmd.getResult().getDebugInfo(),
                            false);
                }

                this.print(e);
                this.printerr(cmd.getResult().getDebugInfo());
                this.exitFailure();
            }
        }
    }

    public void handleTracking(OnapCommand cmd) throws OnapCommandException {
        if (cmd.getResult().isDebug())
            this.printerr(cmd.getResult().getDebugInfo());

        String printOut = cmd.getResult().print();
        this.print(printOut);

        if (cmd.getExecutionContext() != null) {
            OnapCommandExecutionStore.getStore().storeExectutionEnd(
                    cmd.getExecutionContext(),
                    printOut,
                    null,
                    cmd.getResult().getDebugInfo(),
                    cmd.getResult().isPassed());
        }
    }
    /**
     * When user invokes cli with RPC arguments...
     */
    public void handleRpc() {
        if (this.rpcHost != null && this.rpcPort != null && this.product != null) {
            try {
                OnapCommand cmd = OnapCommandRegistrar.getRegistrar().get("schema-rpc", "open-cli");
                cmd.getParametersMap().get(OnapCommandConstants.RPC_HOST).setValue(this.rpcHost);
                cmd.getParametersMap().get(OnapCommandConstants.RPC_PORT).setValue(this.rpcPort);
                cmd.getParametersMap().get(OnapCommandConstants.RPC_PRODUCT).setValue(this.product);
                cmd.getParametersMap().get(OnapCommandConstants.RPC_CMD).setValue(this.cmdName);

                this.handleRpcCommand(cmd);
            } catch (Exception e) {
                this.print(e);
                this.exitFailure();
            }
        }
    }

    private void handleRpcCommand(OnapCommand cmd) throws OnapCommandException {
        Map<String, List<String>> argsMap = new HashMap<>();
        argsMap.put(OnapCommandConstants.RPC_ARGS, this.args);
        if (this.profile != null )
            cmd.getParametersMap().get(OnapCommandConstants.RPC_PROFILE).setValue(this.profile);
        cmd.getParametersMap().get(OnapCommandConstants.RPC_REQID).setValue(this.requestId);
        cmd.getParametersMap().get(OnapCommandConstants.RPC_MODE).setValue(OnapCommandConstants.RPC_MODE_RUN_CLI);
        cmd.getParametersMap().get(OnapCommandConstants.RPC_ARGS).setValue(argsMap);

        OnapCommandResult result = cmd.execute();
        Result output = (Result) result.getOutput();

        this.exitCode = output.getExitCode();
        this.print(output.getOutput());
    }
    /**
     * Handles all client input.
     */
    public void handle() {
        this.handleRpc();

        if (this.exitCode == -1) {
            this.handleHelp();
        }

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
            this.handleCommand();
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
            //ignore system commands
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
                SampleYamlGenerator.generateSampleYaml(cmd.getName(), args, cmd.getResult().print(),
                        cmd.getInfo().getProduct(),
                        OnapCommandConfig.getPropertyValue(OnapCommandConstants.SAMPLE_GEN_TARGET_FOLDER) + "/" + cmd.getSchemaName().replaceAll(".yaml", "") + "-sample.yaml",
                        cmd.getResult().isDebug(),
                        OnapCommandConfig.getPropertyValue(OnapCommandConstants.SAMPLE_GEN_NAME));
            } catch (IOException error) {
                throw new OnapCommandInvalidSample(this.cmdName, error);
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
        OnapCli cli = new OnapCli(args); //NOSONAR
        cli.handle();
        System.exit(cli.getExitCode());
    }

}