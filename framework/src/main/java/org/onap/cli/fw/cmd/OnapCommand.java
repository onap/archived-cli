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

package org.onap.cli.fw.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandNotInitialized;
import org.onap.cli.fw.info.OnapCommandInfo;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.OnapCommandParameterType;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.output.OnapCommandResultAttributeScope;
import org.onap.cli.fw.output.OnapCommandResultType;
import org.onap.cli.fw.schema.OnapCommandSchemaInfo;
import org.onap.cli.fw.schema.OnapCommandSchemaLoader;
import org.onap.cli.fw.schema.OnapCommandSchemaMerger;
import org.onap.cli.fw.store.OnapCommandArtifactStore;
import org.onap.cli.fw.store.OnapCommandArtifactStore.Artifact;
import org.onap.cli.fw.store.OnapCommandExecutionStore.ExecutionStoreContext;
import org.onap.cli.fw.utils.OnapCommandHelperUtils;
import org.onap.cli.fw.utils.OnapCommandUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Oclip Command.
 *
 */
public abstract class OnapCommand {

    private static Logger log = LoggerFactory.getLogger(OnapCommand.class);
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    private String cmdDescription;

    private String cmdName;

    private String cmdSchemaName;

    private OnapCommandInfo info = new OnapCommandInfo();

    private Set<OnapCommandParameter> cmdParameters = new HashSet<>();

    private OnapCommandResult cmdResult = new OnapCommandResult();

    private List<String> defaultSchemas = new ArrayList<>();

    protected boolean isInitialzied = false;

    private  boolean isRpc = false;

    private ExecutionStoreContext executionStoreContext;

    public boolean isRpc() {
        return isRpc;
    }

    public void setRpc(boolean isRpc) {
        this.isRpc = isRpc;
    }

    protected OnapCommand() {
        this.addDefaultSchemas(OnapCommandConstants.DEFAULT_PARAMETER_FILE_NAME);
    }

    public List<String> getSchemas() {
        List<String> schemas = new ArrayList<>();
        schemas.addAll(this.defaultSchemas);
        schemas.add(this.getSchemaName());
        return schemas;
    }

    public String getSchemaVersion() {
        return OnapCommandConstants.OPEN_CLI_SCHEMA_VERSION_VALUE_1_0;
    }

    public String getDescription() {
        return this.cmdDescription;
    }

    public void setDescription(String description) {
        this.cmdDescription = description;
    }

    public String getName() {
        return this.cmdName;
    }

    public void setName(String name) {
        this.cmdName = name;
    }

    public OnapCommandInfo getInfo() {
        return info;
    }

    public void setInfo(OnapCommandInfo info) {
        this.info = info;
    }

    public void setParameters(Set<OnapCommandParameter> parameters) {
        this.cmdParameters = parameters;
    }

    public Set<OnapCommandParameter> getParameters() {
        return this.cmdParameters;
    }

    public Map<String, OnapCommandParameter> getParametersMap() {
        return OnapCommandUtils.getInputMap(this.getParameters());
    }

    public OnapCommandResult getResult() {
        return this.cmdResult;
    }

    public void setResult(OnapCommandResult result) {
        this.cmdResult = result;
    }

    public String getSchemaName() {
        return cmdSchemaName;
    }

    protected void setSchemaName(String schemaName) {
        this.cmdSchemaName = schemaName;
    }

    protected void addDefaultSchemas(String schema) {
        this.defaultSchemas.add(schema);
    }

    public List<String> getDefaultSchema() {
        return this.defaultSchemas;
    }

    public List<String> getArgs() {
        List <String> args = new ArrayList<>();
        for (OnapCommandParameter param: this.getParameters()) {
            args.add(OnapCommandParameter.printLongOption(param.getName()));
            args.add(param.getValue().toString());
        }

        return args;
    }

    public String getArgsJson(boolean ignoreDefaults) {
        Map <String, String> args = new HashMap<>();

        for (OnapCommandParameter param: this.getParameters()) {
            if (ignoreDefaults && param.isDefaultParam())
                continue;

            args.put(param.getName(), param.getValue().toString());
        }

        try {
            return gson.toJson(args);
        } catch (Exception e) { // NOSONAR
            log.error("exception occured {}", e.getMessage());
            return "{}";
        }
    }

    /**
     * Initialize this command from command schema and assumes schema is already validated.
     *
     * @throws OnapCommandException
     *
     * @return List of error strings
     */
    public List<String> initializeSchema(String schema) throws OnapCommandException {
        return this.initializeSchema(schema, false);
    }

    public List<String> initializeSchema(OnapCommandSchemaInfo schema) throws OnapCommandException {
        return this.initializeSchema(schema.getSchemaName(), false);
    }

    public List<String> initializeSchema(String schema, boolean validate) throws OnapCommandException {
        this.setSchemaName(schema);

        Map<String, ?> schemaMap = OnapCommandSchemaMerger.mergeSchemas(this);
        List<String> errors = OnapCommandSchemaLoader.parseSchema(this, schemaMap, validate);
        errors.addAll(this.initializeProfileSchema(schemaMap, validate));
        this.isInitialzied = true;

        return errors;
    }
    /**
     * Any additional profile based such as http schema could be initialized.
     */
    protected List<String> initializeProfileSchema(Map<String, ?> schemaMap, boolean validate) throws OnapCommandException {
        return new ArrayList<>();
    }

    /*
     * Validate input parameters. This can be overridden in derived commands
     */
    protected void validate() throws OnapCommandException {
        for (OnapCommandParameter param : this.getParameters()) {
             if (param.isInclude()) {
                 param.validate();
             }
         }
    }

    protected void preRun() throws OnapCommandException {
        log.debug(this.getName() + " PRE-RUN");
    }

    protected void postRun() throws OnapCommandException {
        log.debug(this.getName() + " POST-RUN");
    }

    /**
     * Oclip command execute with given parameters on service. Before calling this method, its mandatory to set all
     * parameters value.
     *
     * @throws OnapCommandException
     *             : General Command Exception
     */
    public OnapCommandResult execute() throws OnapCommandException {
        if (!this.isInitialzied) {
            throw new OnapCommandNotInitialized(this.getClass().getName());
        }

        log.info("CMD: " + this.getName());

        Map<String, OnapCommandParameter> paramMap = this.getParametersMap();

        log.info("INPUT: " + paramMap);

        // -h or --help is always higher precedence !, user can set this value to get help message
        if ((Boolean)(paramMap.get(OnapCommandConstants.DEFAULT_PARAMETER_HELP).getValue())) {
            this.cmdResult.setType(OnapCommandResultType.TEXT);
            this.cmdResult.setOutput(this.printHelp());
            return this.cmdResult;
        }

        // -v or --version is next higher precedence !, user can set this value to get help message
        if ((Boolean)(paramMap.get(OnapCommandConstants.DEFAULT_PARAMETER_VERSION).getValue())) {
            this.cmdResult.setType(OnapCommandResultType.TEXT);
            this.cmdResult.setOutput(this.printVersion());
            return this.cmdResult;
        }

        //set the artifact content path.
        for (OnapCommandParameter param: this.getParameters()) {
            if (!param.getParameterType().equals(OnapCommandParameterType.BINARY))
                continue;

            if (param.getValue().toString().matches("artifact://*:*")) {
                String categoryAndName = param.getValue().toString().replaceFirst("artifact://", "");
                String[] categoryAndNameTokens = categoryAndName.split(":");
                Artifact a = OnapCommandArtifactStore.getStore().getArtifact(categoryAndNameTokens[1], categoryAndNameTokens[0]);
                param.setValue(a.getPath());
            }
        }

        // validate
        this.validate();

        // -f or --format
        this.cmdResult.setType(
                OnapCommandResultType.get(paramMap.get(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_FORMAT).getValue().toString()));
        if ((Boolean)(paramMap.get(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_ATTR_LONG).getValue())) {
            this.cmdResult.setScope(OnapCommandResultAttributeScope.LONG);
        }
        // --no-title
        if ((Boolean)paramMap.get(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_NO_TITLE).getValue()) {
            this.cmdResult.setIncludeTitle(false);
        }

        // --debug
        if ((Boolean)(paramMap.get(OnapCommandConstants.DEFAULT_PARAMETER_DEBUG).getValue())) {
            this.cmdResult.setDebug(true);
        }

        //pre-process result attributes for spl entries and input parameters
        for (OnapCommandResultAttribute attr: this.cmdResult.getRecords()) {
            if (!attr.getDefaultValue().isEmpty()) {
                attr.setDefaultValue(OnapCommandUtils.replaceLineForSpecialValues(attr.getDefaultValue()));
                attr.setDefaultValue(OnapCommandUtils.replaceLineFromInputParameters(
                        attr.getDefaultValue(), this.getParametersMap()));
            }
        }

        preRun();

        this.run();

        log.info("OUTPUT: " + this.cmdResult.getRecords());

        postRun();


        return this.cmdResult;
    }

    /*
     * Each command implements run method to executing the command.
     *
     */
    protected abstract void run() throws OnapCommandException;

    /**
     * Returns the service service version it supports.
     *
     * @return version
     */
    public String printVersion() {
        return this.getInfo().getProduct() + "::" + this.getInfo().getService();
    }

    /**
     * Provides help message for this command.
     *
     * @return help message
     * @throws OnapCommandHelpFailed
     *             Failed to execute Help command.
     */
    public String printHelp() throws OnapCommandHelpFailed {
        return OnapCommandHelperUtils.help(this);
    }
    // (mrkanag) Add toString for all command, parameter, result, etc objects in JSON format

    public void setExecutionContext(ExecutionStoreContext executionStoreContext) {
        this.executionStoreContext = executionStoreContext;
    }

    public ExecutionStoreContext getExecutionContext() {
        return this.executionStoreContext;
    }
}
