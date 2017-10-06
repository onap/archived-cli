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

package org.onap.cli.fw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.onap.cli.fw.ad.OnapService;
import org.onap.cli.fw.cmd.CommandType;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.conf.OnapCommandConfg;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandInvalidParameterType;
import org.onap.cli.fw.error.OnapCommandInvalidPrintDirection;
import org.onap.cli.fw.error.OnapCommandInvalidResultAttributeScope;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.error.OnapCommandInvalidSchemaVersion;
import org.onap.cli.fw.error.OnapCommandNotInitialized;
import org.onap.cli.fw.error.OnapCommandParameterMissing;
import org.onap.cli.fw.error.OnapCommandParameterNameConflict;
import org.onap.cli.fw.error.OnapCommandParameterOptionConflict;
import org.onap.cli.fw.error.OnapCommandRegistrationFailed;
import org.onap.cli.fw.error.OnapCommandSchemaNotFound;
import org.onap.cli.fw.info.OnapCommandInfo;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.output.OnapCommandResultAttributeScope;
import org.onap.cli.fw.output.ResultType;
import org.onap.cli.fw.utils.OnapCommandUtils;

/**
 * Oclip Command.
 *
 */
public abstract class OnapCommand {

    private String cmdDescription;

    private String cmdName;

    private String cmdSchemaName;

    private OnapCommandInfo info = new OnapCommandInfo();

    private List<OnapCommandParameter> cmdParameters = new ArrayList<>();

    private OnapCommandResult cmdResult = new OnapCommandResult();

    protected boolean isInitialzied = false;

    public String getSchemaVersion() {
        return Constants.OPEN_CLI_SCHEMA_VERSION_VALUE_1_0;
    }

    /**
     * Oclip command description, defined by derived command.
     */
    public String getDescription() {
        return this.cmdDescription;
    }

    public void setDescription(String description) {
        this.cmdDescription = description;
    }

    /*
     * Oclip command name like user-create, ns-list, etc , defined by derived command
     */
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

    public void setParameters(List<OnapCommandParameter> parameters) {
        this.cmdParameters = parameters;
    }

    /*
     * Oclip command input parameters, defined by derived command
     */
    public List<OnapCommandParameter> getParameters() {
        return this.cmdParameters;
    }

    /*
     * Oclip command input parameters, defined by derived command
     */
    public Map<String, OnapCommandParameter> getParametersMap() {
        return OnapCommandUtils.getInputMap(this.getParameters());
    }

    /*
     * Oclip command output results, defined by derived command
     */
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

    /**
     * Initialize this command from command schema.
     *
     * @throws OnapCommandRegistrationFailed
     *             Command Registration Exception
     * @throws OnapCommandInvalidResultAttributeScope
     *             InvalidResultAttribute Exception
     * @throws OnapCommandInvalidPrintDirection
     *             InvalidPrintDirection Exception
     * @throws OnapCommandInvalidParameterType
     *             InvalidParameterType Exception
     * @throws OnapCommandSchemaNotFound
     *             SchemaNotFound Exception
     * @throws OnapCommandInvalidSchema
     *             InvalidSchema Exception
     * @throws OnapCommandParameterOptionConflict
     *             ParameterOptionConflict Exception
     * @throws OnapCommandParameterNameConflict
     *             ParameterNameConflict Exception
     * @throws OnapCommandInvalidSchemaVersion
     *             InvalidSchemaVersion Exception
     */
    public void initializeSchema(String schema) throws OnapCommandException {
        this.setSchemaName(schema);
        OnapCommandUtils.loadSchema(this, schema, true, false);
        this.initializeProfileSchema();
        this.isInitialzied = true;
    }

    /**
     * Any additional profile based such as http/swagger schema could be initialized.
     */
    protected void initializeProfileSchema() throws OnapCommandException {
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

        Map<String, OnapCommandParameter> paramMap = this.getParametersMap();

        // -h or --help is always higher precedence !, user can set this value to get help message
        if ("true".equals(paramMap.get(Constants.DEFAULT_PARAMETER_HELP).getValue())) {
            OnapCommandResult result = new OnapCommandResult();
            result.setType(ResultType.TEXT);
            result.setOutput(this.printHelp());
            return result;
        }

        // -v or --version is next higher precedence !, user can set this value to get help message
        if ("true".equals(paramMap.get(Constants.DEFAULT_PARAMETER_VERSION).getValue())) {
            OnapCommandResult result = new OnapCommandResult();
            result.setType(ResultType.TEXT);
            result.setOutput(this.printVersion());
            return result;
        }

        // validate
        this.validate();

        // -f or --format
        this.cmdResult.setType(
                ResultType.get(paramMap.get(Constants.DEFAULT_PARAMETER_OUTPUT_FORMAT).getValue().toString()));
        if ("true".equals(paramMap.get(Constants.DEFAULT_PARAMETER_OUTPUT_ATTR_LONG).getValue())) {
            this.cmdResult.setScope(OnapCommandResultAttributeScope.LONG);
        }
        // --no-title
        if ("true".equals(paramMap.get(Constants.DEFAULT_PARAMETER_OUTPUT_NO_TITLE).getValue())) {
            this.cmdResult.setIncludeTitle(false);
        }

        // --debug
        if ("true".equals(paramMap.get(Constants.DEFAULT_PARAMETER_DEBUG).getValue())) {
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

        this.run();

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
        return this.getInfo().getService();
    }

    /**
     * Provides help message for this command.
     *
     * @return help message
     * @throws OnapCommandHelpFailed
     *             Failed to execute Help command.
     */
    public String printHelp() throws OnapCommandHelpFailed {
        return OnapCommandUtils.help(this);
    }

    // (mrkanag) Add toString for all command, parameter, result, etc objects in JSON format
}
