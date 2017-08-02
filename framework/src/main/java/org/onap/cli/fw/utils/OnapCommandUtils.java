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

package org.onap.cli.fw.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.ad.OnapCredentials;
import org.onap.cli.fw.ad.OnapService;
import org.onap.cli.fw.cmd.OnapHttpCommand;
import org.onap.cli.fw.cmd.OnapSwaggerCommand;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.error.OnapCommandDiscoveryFailed;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandHttpHeaderNotFound;
import org.onap.cli.fw.error.OnapCommandHttpInvalidResponseBody;
import org.onap.cli.fw.error.OnapCommandInvalidDefaultParameter;
import org.onap.cli.fw.error.OnapCommandInvalidParameterType;
import org.onap.cli.fw.error.OnapCommandInvalidParameterValue;
import org.onap.cli.fw.error.OnapCommandInvalidPrintDirection;
import org.onap.cli.fw.error.OnapCommandInvalidResultAttributeScope;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.error.OnapCommandInvalidSchemaVersion;
import org.onap.cli.fw.error.OnapCommandParameterNameConflict;
import org.onap.cli.fw.error.OnapCommandParameterNotFound;
import org.onap.cli.fw.error.OnapCommandParameterOptionConflict;
import org.onap.cli.fw.error.OnapCommandResultEmpty;
import org.onap.cli.fw.error.OnapCommandResultMapProcessingFailed;
import org.onap.cli.fw.error.OnapCommandSchemaNotFound;
import org.onap.cli.fw.http.HttpInput;
import org.onap.cli.fw.http.HttpResult;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.ParameterType;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.output.OnapCommandResultAttributeScope;
import org.onap.cli.fw.output.PrintDirection;
import org.onap.cli.fw.output.ResultType;
import org.onap.cli.fw.run.OnapCommandExecutor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Provides helper method to parse Yaml files and produce required objects.
 *
 */
public class OnapCommandUtils {

    /**
     * Private constructor.
     */
    private OnapCommandUtils() {

    }

    /**
     * Validates schema version.
     *
     * @param schemaName schema name
     * @param version    schema version
     * @return map
     * @throws OnapCommandInvalidSchemaVersion invalid schema version exception
     * @throws OnapCommandInvalidSchema        invalid schema
     * @throws OnapCommandSchemaNotFound       schema not found
     */
    public static Map<String, ?> validateSchemaVersion(String schemaName, String version) throws OnapCommandException {
        InputStream inputStream = OnapCommandUtils.class.getClassLoader().getResourceAsStream(schemaName);

        try {
            Resource resource = getExternalResource(schemaName, Constants.EXTERNAL_SCHEMA_PATH_PATERN);

            if (resource != null) {
                inputStream = resource.getInputStream();
            }

        } catch (IOException e) {
            throw new OnapCommandSchemaNotFound(schemaName, e);
        }
        if (inputStream == null) {
            throw new OnapCommandSchemaNotFound(schemaName);
        }

        Map<String, ?> values = null;
        try {
            values = (Map<String, ?>) new Yaml().load(inputStream);
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(schemaName, e);
        }
        String schemaVersion = "";
        if (values.keySet().contains(Constants.ONAP_CMD_SCHEMA_VERSION)) {
            Object obj = values.get(Constants.ONAP_CMD_SCHEMA_VERSION);
            schemaVersion = obj.toString();
        }

        if (!version.equals(schemaVersion)) {
            throw new OnapCommandInvalidSchemaVersion(schemaVersion);
        }

        return values;
    }

    /**
     * Retrieve OnapCommand from schema.
     *
     * @param cmd            OnapCommand
     * @param schemaName     schema name
     * @param includeDefault include if default
     * @throws OnapCommandParameterNameConflict       param name conflict exception
     * @throws OnapCommandParameterOptionConflict     param option conflict exception
     * @throws OnapCommandInvalidParameterType        invalid param type exception
     * @throws OnapCommandInvalidPrintDirection       invalid print direction exception
     * @throws OnapCommandInvalidResultAttributeScope invalid scope exception
     * @throws OnapCommandSchemaNotFound              schema not found
     * @throws OnapCommandInvalidSchema               invalid schema
     * @throws OnapCommandInvalidSchemaVersion        invalid schema version
     */
    public static void loadSchema(OnapCommand cmd, String schemaName, boolean includeDefault)
            throws OnapCommandException {
        try {
            Map<String, ?> defaultParameterMap = includeDefault ?
                    validateSchemaVersion(Constants.DEFAULT_PARAMETER_FILE_NAME, cmd.getSchemaVersion()) : new HashMap<>();
            Map<String, List<Map<String, String>>> commandYamlMap = (Map<String, List<Map<String, String>>>)validateSchemaVersion(schemaName, cmd.getSchemaVersion());

            List<String> defParams = new ArrayList<>();

            if (includeDefault) {
                if (commandYamlMap.get(Constants.PARAMETERS) == null) {
                    commandYamlMap.put(Constants.PARAMETERS, (List<Map<String, String>>) defaultParameterMap.get(Constants.PARAMETERS));
                } else {
                    commandYamlMap.get(Constants.PARAMETERS).addAll((List<Map<String, String>>) defaultParameterMap.get(Constants.PARAMETERS));
                }
                defParams = ((List<Map<String, String>>) defaultParameterMap.get(Constants.PARAMETERS)).stream()
                        .map(p -> p.get(Constants.NAME)).collect(Collectors.toList());
            }

            parseSchema(cmd, commandYamlMap, defParams);
        } catch (OnapCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(schemaName, e);
        }
    }

    private static void parseSchema(OnapCommand cmd,
                                    final Map<String, ?> values,
                                    final List<String> defaultParamNames) throws OnapCommandException {

        List<String> shortOptions = new ArrayList<>();
        List<String> longOptions = new ArrayList<>();
        List<String> names = new ArrayList<>();
        Set<String> filteredDefaultParams = new HashSet<>();

        List<String> sections = Arrays.asList(Constants.NAME, Constants.DESCRIPTION, Constants.SERVICE,
                Constants.DEFAULT_PARAMETERS, Constants.PARAMETERS, Constants.RESULTS);

        for (String key : sections) {

            CommandSchemaSectionType sectionType = CommandSchemaSectionType.valueOf(key.toUpperCase());

            switch (sectionType) {
                case ONAP_CMD_SCHEMA_VERSION:
                    break;

                case NAME:
                    Object value = values.get(key);
                    if (value != null) {
                        cmd.setName(value.toString());
                    }
                    break;

                case DESCRIPTION:
                    Object val = values.get(key);
                    if (val != null) {
                        cmd.setDescription(val.toString());
                    }
                    break;

                case SERVICE:
                    Map<String, String> serviceMap = (Map<String, String>) values.get(key);
                    if (serviceMap != null) {
                        OnapService srv = new OnapService();

                        for (Map.Entry<String, String> entry1 : serviceMap.entrySet()) {
                            String key1 = entry1.getKey();

                            CommandSchemaSectionType.ServiceAttr serviceAttr = CommandSchemaSectionType.ServiceAttr
                                    .valueOf(key1.toUpperCase().replace("-","_"));

                            switch (serviceAttr) {
                                case NAME:
                                    srv.setName(serviceMap.get(key1));
                                    break;
                                case VERSION:
                                    srv.setVersion(serviceMap.get(key1));
                                    break;
                                case NO_AUTH:
                                    Object obj = serviceMap.get(key1);
                                    srv.setNoAuth("true".equalsIgnoreCase(obj.toString()));
                                    break;
                            }
                        }

                        cmd.setService(srv);
                    }
                    break;

                case DEFAULT_PARAMETERS:
                    Map<String, List<String>> defParameters = (Map) values.get(Constants.DEFAULT_PARAMETERS);

                    if (values.containsKey(Constants.DEFAULT_PARAMETERS) && defParameters == null) {
                        // if default parameter section is available then it must have either include
                        // or exclude sub-section.
                        throw new OnapCommandInvalidSchema(Constants.SCHEMA_INVALID_DEFAULT_PARAMS_SECTION);
                    }

                    if (defParameters != null) {
                        // validate default parameters
                        List<String> includeParams = defParameters.containsKey(Constants.DEFAULT_PARAMETERS_INCLUDE) ?
                                defParameters.get(Constants.DEFAULT_PARAMETERS_INCLUDE) : new ArrayList<>();

                        List<String> invInclude = includeParams.stream()
                                .filter(p -> !defaultParamNames.contains(p))
                                .collect(Collectors.toList());

                        List<String> excludeParams = defParameters.containsKey(Constants.DEFAULT_PARAMETERS_EXCLUDE) ?
                                defParameters.get(Constants.DEFAULT_PARAMETERS_EXCLUDE) : new ArrayList<>();

                        List<String> invExclude = excludeParams.stream()
                                .filter(p -> !defaultParamNames.contains(p))
                                .collect(Collectors.toList());

                        if (!invExclude.isEmpty() || !invInclude.isEmpty()) {
                            throw new OnapCommandInvalidDefaultParameter(Stream.concat(invInclude.stream(), invExclude.stream())
                                    .collect(Collectors.toList()));
                        }

                        if (!includeParams.isEmpty()) {
                            filteredDefaultParams.addAll(includeParams);
                        } else if (!excludeParams.isEmpty()) {
                            defaultParamNames.stream().filter(p -> !excludeParams.contains(p))
                                    .forEach(filteredDefaultParams::add);
                        }
                    } else {
                        filteredDefaultParams.addAll(defaultParamNames);

                    }
                    break;

                case PARAMETERS:
                    List<Map<String, String>> parameters = (List) values.get(key);

                    if (parameters != null) {
                        for (Map<String, String> map : parameters) {
                            OnapCommandParameter param = new OnapCommandParameter();

                            for (Map.Entry<String, String> entry1 : map.entrySet()) {
                                String key2 = entry1.getKey();

                                CommandSchemaSectionType.ParameterAttr parameterAttr = CommandSchemaSectionType
                                        .ParameterAttr.valueOf(key2.toUpperCase());

                                switch (parameterAttr) {

                                    case NAME:
                                        if (names.contains(map.get(key2))) {
                                            throw new OnapCommandParameterNameConflict(map.get(key2));
                                        }
                                        names.add(map.get(key2));
                                        param.setName(map.get(key2));
                                        break;
                                    case DESCRIPTION:
                                        param.setDescription(map.get(key2));
                                        break;
                                    case SHORT_OPTION:
                                        if (shortOptions.contains(map.get(key2))) {
                                            throw new OnapCommandParameterOptionConflict(map.get(key2));
                                        }
                                        shortOptions.add(map.get(key2));
                                        param.setShortOption(map.get(key2));
                                        break;
                                    case LONG_OPTION:
                                        if (longOptions.contains(map.get(key2))) {
                                            throw new OnapCommandParameterOptionConflict(map.get(key2));
                                        }
                                        longOptions.add(map.get(key2));
                                        param.setLongOption(map.get(key2));
                                        break;
                                    case DEFAULT_VALUE:
                                        Object obj = map.get(key2);
                                        param.setDefaultValue(obj.toString());
                                        break;
                                    case TYPE:
                                        param.setParameterType(ParameterType.get(map.get(key2)));
                                        break;
                                    case IS_OPTIONAL:
                                        if ("true".equalsIgnoreCase(String.valueOf(map.get(key2)))) {
                                            param.setOptional(true);
                                        } else {
                                            param.setOptional(false);
                                        }
                                        break;
                                    case IS_SECURED:
                                        if ("true".equalsIgnoreCase(String.valueOf(map.get(key2)))) {
                                            param.setSecured(true);
                                        } else {
                                            param.setSecured(false);
                                        }
                                        break;
                                }
                            }
                            // Add the element to command :
                            // 1. if parameter is available in filtered parameter list.
                            // 2. otherwise, parameter p is available in command yaml file.
                            if (filteredDefaultParams.contains(param.getName()) || !defaultParamNames.contains(param.getName())) {
                                cmd.getParameters().add(param);
                            }
                        }
                    }
                    break;

                case RESULTS:
                    Map<String, ?> valueMap = (Map<String, ?>) values.get(key);
                    if (valueMap != null) {
                        OnapCommandResult result = new OnapCommandResult();
                        for (Map.Entry<String, ?> entry1 : valueMap.entrySet()) {
                            String key3 = entry1.getKey();

                            CommandSchemaSectionType.ResultAttr resultAttr = CommandSchemaSectionType.ResultAttr
                                    .valueOf(key3.toUpperCase());

                            switch (resultAttr) {

                                case DIRECTION:
                                    result.setPrintDirection(PrintDirection.get((String) valueMap.get(key3)));
                                    break;
                                case ATTRIBUTES:
                                    List<Map<String, String>> attrs = (ArrayList) valueMap.get(key3);

                                    for (Map<String, String> map : attrs) {
                                        OnapCommandResultAttribute attr = new OnapCommandResultAttribute();
                                        for (Map.Entry<String, String> entry4 : map.entrySet()) {
                                            String key4 = entry4.getKey();
                                            CommandSchemaSectionType.ResultAttr.ResutAttrType resultAttrType
                                                    = CommandSchemaSectionType.ResultAttr.ResutAttrType.valueOf(
                                                            entry4.getKey().toUpperCase());

                                            switch (resultAttrType) {

                                                case NAME:
                                                    attr.setName(map.get(key4));
                                                    break;
                                                case DESCRIPTION:
                                                    attr.setDescription(map.get(key4));
                                                    break;
                                                case SCOPE:
                                                    attr.setScope(OnapCommandResultAttributeScope.get(map.get(key4)));
                                                    break;
                                                case TYPE:
                                                    attr.setType(ParameterType.get(map.get(key4)));
                                                    break;
                                                case IS_SECURED:
                                                    if ("true".equals(String.valueOf(map.get(key4)))) {
                                                        attr.setSecured(true);
                                                    } else {
                                                        attr.setSecured(false);
                                                    }
                                                    break;
                                            }
                                        }
                                        result.getRecords().add(attr);
                                    }
                                    break;
                            }
                        }
                        cmd.setResult(result);
                    }
                    break;
            }
        }
    }

    /**
     * Load the schema.
     *
     * @param cmd
     *            OnapSwaggerBasedCommand
     * @param schemaName
     *            schema name
     * @throws OnapCommandParameterNameConflict
     *             param name conflict exception
     * @throws OnapCommandParameterOptionConflict
     *             param option conflict exception
     * @throws OnapCommandInvalidParameterType
     *             invalid param type exception
     * @throws OnapCommandInvalidPrintDirection
     *             invalid print direction exception
     * @throws OnapCommandInvalidResultAttributeScope
     *             invalid scope exception
     * @throws OnapCommandSchemaNotFound
     *             schema not found
     * @throws OnapCommandInvalidSchema
     *             invalid schema
     * @throws OnapCommandInvalidSchemaVersion
     *             invalid schema version
     */
    public static void loadSchema(OnapSwaggerCommand cmd, String schemaName) throws OnapCommandException {
        try {
            Map<String, ?> values = (Map<String, ?>) validateSchemaVersion(schemaName, cmd.getSchemaVersion());
            Map<String, String> valueMap = (Map<String, String>) values.get(Constants.EXECUTOR);
            OnapCommandExecutor exec = new OnapCommandExecutor();

            for (Map.Entry<String, String> entry1 : valueMap.entrySet()) {
                String key1 = entry1.getKey();

                if (Constants.API.equals(key1)) {
                    exec.setApi(valueMap.get(key1));
                } else if (Constants.CLIENT.equals(key1)) {
                    exec.setClient(valueMap.get(key1));
                } else if (Constants.ENTITY.equals(key1)) {
                    exec.setEntity(valueMap.get(key1));
                } else if (Constants.EXCEPTION.equals(key1)) {
                    exec.setException(valueMap.get(key1));
                } else if (Constants.METHOD.equals(key1)) {
                    exec.setMethod(valueMap.get(key1));
                }
            }

            cmd.setExecutor(exec);
        } catch (OnapCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(schemaName, e);
        }
    }

    /**
     * Load the schema.
     *
     * @param cmd
     *            OnapHttpCommand
     * @param schemaName
     *            schema name
     * @throws OnapCommandParameterNameConflict
     *             param name conflict exception
     * @throws OnapCommandParameterOptionConflict
     *             param option conflict exception
     * @throws OnapCommandInvalidParameterType
     *             invalid param type exception
     * @throws OnapCommandInvalidPrintDirection
     *             invalid print direction exception
     * @throws OnapCommandInvalidResultAttributeScope
     *             invalid scope exception
     * @throws OnapCommandSchemaNotFound
     *             schema not found
     * @throws OnapCommandInvalidSchema
     *             invalid schema
     * @throws OnapCommandInvalidSchemaVersion
     *             invalid schema version
     */
    public static void loadSchema(OnapHttpCommand cmd, String schemaName) throws OnapCommandException {
        try {
            Map<String, ?> values = (Map<String, ?>) validateSchemaVersion(schemaName, cmd.getSchemaVersion());
            Map<String, ?> valMap = (Map<String, ?>) values.get(Constants.HTTP);

            for (Map.Entry<String, ?> entry1 : valMap.entrySet()) {
                String key1 = entry1.getKey();
                if (Constants.REQUEST.equals(key1)) {
                    Map<String, ?> map = (Map<String, ?>) valMap.get(key1);

                    for (Map.Entry<String, ?> entry2 : map.entrySet()) {
                        String key2 = entry2.getKey();

                        if (Constants.URI.equals(key2)) {
                            Object obj = map.get(key2);
                            cmd.getInput().setUri(obj.toString());
                        } else if (Constants.MERHOD.equals(key2)) {
                            Object obj = map.get(key2);
                            cmd.getInput().setMethod(obj.toString());
                        } else if (Constants.BODY.equals(key2)) {
                            Object obj = map.get(key2);
                            cmd.getInput().setBody(obj.toString());
                        } else if (Constants.HEADERS.equals(key2)) {
                            Map<String, String> head = (Map<String, String>) map.get(key2);
                            cmd.getInput().setReqHeaders(head);
                        } else if (Constants.QUERIES.equals(key2)) {
                            Map<String, String> query = (Map<String, String>) map.get(key2);

                            cmd.getInput().setReqQueries(query);
                        }
                    }
                } else if (Constants.SUCCESS_CODES.equals(key1)) {
                    cmd.setSuccessStatusCodes((ArrayList) valMap.get(key1));
                } else if (Constants.RESULT_MAP.equals(key1)) {
                    cmd.setResultMap((Map<String, String>) valMap.get(key1));
                } else if (Constants.SAMPLE_RESPONSE.equals(key1)) {
                    // (mrkanag) implement sample response handling
                }
            }

        } catch (OnapCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(schemaName, e);
        }
    }

    /**
     * Returns Help.
     *
     * @param cmd
     *            OnapCommand
     * @return help string
     * @throws OnapCommandHelpFailed
     *             help failed exception
     */
    public static String help(OnapCommand cmd) throws OnapCommandHelpFailed {
        String help = "usage: onap " + cmd.getName();

        // Add description
        help += "\n\n" + cmd.getDescription();

        // Add service
        help += "\n\nOnap service: " + cmd.getService();

        // Add whole command
        String commandOptions = "";

        // Add parameters
        OnapCommandResult paramTable = new OnapCommandResult();
        paramTable.setPrintDirection(PrintDirection.LANDSCAPE);
        paramTable.setType(ResultType.TABLE);
        paramTable.setIncludeTitle(false);
        paramTable.setIncludeSeparator(false);

        OnapCommandResultAttribute attrName = new OnapCommandResultAttribute();
        attrName.setName(Constants.NAME);
        attrName.setDescription(Constants.NAME);
        attrName.setScope(OnapCommandResultAttributeScope.SHORT);
        paramTable.getRecords().add(attrName);

        OnapCommandResultAttribute attrDescription = new OnapCommandResultAttribute();
        attrDescription.setName(Constants.DESCRIPTION);
        attrDescription.setDescription(Constants.DESCRIPTION);
        attrDescription.setScope(OnapCommandResultAttributeScope.SHORT);
        paramTable.getRecords().add(attrDescription);

        int newLineOptions = 0;
        for (OnapCommandParameter param : cmd.getParameters()) {
            // First column Option or positional args
            String optFirstCol;
            if (newLineOptions == 3) {
                newLineOptions = 0;
                commandOptions += "\n";
            }

            if (param.getShortOption() != null || param.getLongOption() != null) {
                optFirstCol = OnapCommandParameter.printShortOption(param.getShortOption()) + " | "
                        + OnapCommandParameter.printLongOption(param.getLongOption());
                commandOptions += "[" + optFirstCol + "] ";
            } else {
                optFirstCol = param.getName();
                commandOptions += "<" + optFirstCol + "> ";
            }

            newLineOptions++;

            attrName.getValues().add(optFirstCol);

            // Second column description
            String optSecondCol = param.getDescription().trim();
            if (!optSecondCol.endsWith(".")) {
                optSecondCol += ".";
            }
            optSecondCol += " It is of type " + param.getParameterType().name() + ".";

            if (param.getParameterType().equals(ParameterType.JSON)
                    || param.getParameterType().equals(ParameterType.YAML)) {
                optSecondCol += " It's recommended to input the complete path of the file, which is having the value for it.";
            }
            if (param.isOptional()) {
                optSecondCol += " It is optional.";
            }

            String defaultMsg = " By default, it is ";
            if (param.isDefaultValueAnEnv()) {
                optSecondCol += defaultMsg + "read from environment variable " + param.getEnvVarNameFromDefaultValue()
                        + ".";
            } else if (param.getDefaultValue() != null && !param.getDefaultValue().isEmpty()) {
                optSecondCol += defaultMsg + param.getDefaultValue() + ".";
            }

            if (param.isSecured()) {
                optSecondCol += " Secured.";
            }
            // (mrkanag) Add help msg for reading default value from env
            attrDescription.getValues().add(optSecondCol);
        }

        try {
            help += "\n\nOptions:\n" + commandOptions + "\nwhere,\n" + paramTable.print();
        } catch (OnapCommandException e) {
            throw new OnapCommandHelpFailed(e);
        }

        // Add results
        OnapCommandResult resultTable = new OnapCommandResult();
        resultTable.setPrintDirection(PrintDirection.PORTRAIT);
        resultTable.setType(ResultType.TABLE);
        resultTable.setIncludeTitle(false);
        resultTable.setIncludeSeparator(false);

        for (OnapCommandResultAttribute attr : cmd.getResult().getRecords()) {
            OnapCommandResultAttribute attrHelp = new OnapCommandResultAttribute();
            attrHelp.setName(attr.getName());
            attrHelp.setDescription(attr.getDescription());
            String msg = attr.getDescription() + " and is of type " + attr.getType().name() + ".";
            if (attr.isSecured()) {
                msg += " It is secured.";
            }
            attrHelp.getValues().add(msg);
            attrHelp.setType(attr.getType());
            resultTable.getRecords().add(attrHelp);
        }
        try {
            help += "\n\nResults:\n" + resultTable.print();
        } catch (OnapCommandException e) {
            throw new OnapCommandHelpFailed(e);
        }

        // Error
        help += "\n\nError:\nOn error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>\n";
        return help;
    }

    /**
     * Helps to create OnapCredentials from default params.
     *
     * @param params
     *            list of parameters
     * @return OnapCredentials
     * @throws OnapCommandInvalidParameterValue
     *             exception
     */
    public static OnapCredentials fromParameters(List<OnapCommandParameter> params)
            throws OnapCommandInvalidParameterValue {
        Map<String, String> paramMap = new HashMap<>();

        for (OnapCommandParameter param : params) {
            paramMap.put(param.getName(), param.getValue().toString());
        }

        return new OnapCredentials(paramMap.get(Constants.DEAFULT_PARAMETER_USERNAME),
                paramMap.get(Constants.DEAFULT_PARAMETER_PASS_WORD),
                paramMap.get(Constants.DEAFULT_PARAMETER_MSB_URL));
    }

    /**
     * Create Dict from list of Parameters.
     *
     * @param inputs
     *            list of parameters
     * @return map
     */
    public static Map<String, OnapCommandParameter> getInputMap(List<OnapCommandParameter> inputs) {
        Map<String, OnapCommandParameter> map = new HashMap<>();
        for (OnapCommandParameter param : inputs) {
            map.put(param.getName(), param);
        }
        return map;
    }

    /**
     * Discover the Onap commands.
     *
     * @return list
     */
    public static List<Class<OnapCommand>> findOnapCommands() {
        ServiceLoader<OnapCommand> loader = ServiceLoader.load(OnapCommand.class);
        List<Class<OnapCommand>> clss = new ArrayList<>();
        for (OnapCommand implClass : loader) {
            clss.add((Class<OnapCommand>) implClass.getClass());
        }

        return clss;
    }

    /**
     * sort the set.
     *
     * @param col
     *            set
     * @return list
     */
    public static List<String> sort(Set<String> col) {
        List<String> results = new ArrayList<>();
        results.addAll(col);
        Collections.sort(results);
        return results;
    }

    /**
     * Flatten the json list.
     *
     * @param jsons
     *            list json strings
     * @return list
     */
    public static List<String> jsonFlatten(List<String> jsons) {
        List<String> results = new ArrayList<>();
        for (String json : jsons) {
            try {
                results.add(JsonPath.parse(json).jsonString());
            } catch (Exception e) { // NOSONAR
                results.add(json);
            }
        }

        return results;
    }

    /**
     * Construct method name.
     *
     * @param name
     *            name
     * @param prefix
     *            prefix
     * @return string
     */
    public static String formMethodNameFromAttributeName(String name, String prefix) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        String methodName = prefix;
        for (String tk : name.split("-")) {
            methodName += Character.toString(tk.charAt(0)).toUpperCase();
            methodName += tk.substring(1);
        }
        return methodName;
    }

    private static String replaceLineFromInputParameters(String line, Map<String, OnapCommandParameter> params)
            throws OnapCommandException {
        String result = "";

        if (!line.contains("${")) {
            return line;
        }

        int currentIdx = 0;
        while (currentIdx < line.length()) {
            int idxS = line.indexOf("${", currentIdx);
            if (idxS == -1) {
                result += line.substring(currentIdx);
                break;
            }
            int idxE = line.indexOf("}", idxS);
            String paramName = line.substring(idxS + 2, idxE);
            paramName = paramName.trim();
            if (!params.containsKey(paramName)) {
                throw new OnapCommandParameterNotFound(paramName);
            }

            String value = params.get(paramName).getValue().toString();

            OnapCommandParameter param = params.get(paramName);
            if (ParameterType.ARRAY.equals(param.getParameterType())
                    || ParameterType.MAP.equals(param.getParameterType())
                    || ParameterType.JSON.equals(param.getParameterType())
                    || ParameterType.YAML.equals(param.getParameterType())) {
                // ignore the front and back double quotes in json body
                result += line.substring(currentIdx, idxS - 1) + value;
                currentIdx = idxE + 2;
            } else {
                result += line.substring(currentIdx, idxS) + value;
                currentIdx = idxE + 1;
            }
        }

        return result;
    }

    private static ArrayList<String> replaceLineFromOutputResults(String line, HttpResult resultHttp)
            throws OnapCommandHttpHeaderNotFound, OnapCommandHttpInvalidResponseBody,
            OnapCommandResultMapProcessingFailed, OnapCommandResultEmpty {
        String headerProcessedLine = "";

        ArrayList<String> result = new ArrayList<>();
        if (!line.contains("$b{") && !line.contains("$h{")) {
            result.add(line);
            return result;
        }

        /**
         * In case of empty response body [] or {}
         **/
        if (resultHttp.getBody().length() <= 2) {
            return result;
        }

        /**
         * Process headers macros : line: $h{abc}-$b{$.[*].xyz} , After processing line will be [abc's
         * value]-$b{$.[*].xyz}
         **/
        int currentIdx = 0;
        while (currentIdx < line.length()) {
            int idxS = line.indexOf("$h{", currentIdx);
            if (idxS == -1) {
                headerProcessedLine += line.substring(currentIdx);
                break;
            }
            int idxE = line.indexOf("}", idxS);
            String headerName = line.substring(idxS + 3, idxE);
            headerName = headerName.trim();
            if (!resultHttp.getRespHeaders().containsKey(headerName)) {
                throw new OnapCommandHttpHeaderNotFound(headerName);
            }
            String value = resultHttp.getRespHeaders().get(headerName);

            headerProcessedLine += line.substring(currentIdx, idxS) + value;
            currentIdx = idxE + 1;
        }

        // Process body jsonpath macros
        List<Object> values = new ArrayList<>();
        String bodyProcessedPattern = "";
        currentIdx = 0;
        int maxRows = 1; // in normal case, only one row will be there
        while (currentIdx < headerProcessedLine.length()) {
            int idxS = headerProcessedLine.indexOf("$b{", currentIdx);
            if (idxS == -1) {
                bodyProcessedPattern += headerProcessedLine.substring(currentIdx);
                break;
            }
            int idxE = headerProcessedLine.indexOf("}", idxS);
            String jsonPath = headerProcessedLine.substring(idxS + 3, idxE);
            jsonPath = jsonPath.trim();
            try {
                // JSONArray or String
                Object value = JsonPath.read(resultHttp.getBody(), jsonPath);
                if (value instanceof JSONArray) {
                    JSONArray arr = (JSONArray) value;
                    if (arr.size() > maxRows) {
                        maxRows = arr.size();
                    }
                }
                bodyProcessedPattern += headerProcessedLine.substring(currentIdx, idxS) + "%s";
                values.add(value);
                currentIdx = idxE + 1;
            } catch (Exception e) {
                throw new OnapCommandHttpInvalidResponseBody(jsonPath, e);
            }
        }

        if (bodyProcessedPattern.isEmpty()) {
            result.add(headerProcessedLine);
            return result;
        } else {
            for (int i = 0; i < maxRows; i++) {
                currentIdx = 0;
                String bodyProcessedLine = "";
                int positionalIdx = 0; // %s positional idx
                while (currentIdx < bodyProcessedPattern.length()) {
                    int idxS = bodyProcessedPattern.indexOf("%s", currentIdx);
                    if (idxS == -1) {
                        bodyProcessedLine += bodyProcessedPattern.substring(currentIdx);
                        break;
                    }
                    int idxE = idxS + 2; // %s
                    try {
                        Object value = values.get(positionalIdx);
                        String valueS = String.valueOf(value);
                        if (value instanceof JSONArray) {
                            JSONArray arr = (JSONArray) value;
                            if (!arr.isEmpty()) {
                                valueS = arr.get(i).toString();
                            } else {
                                throw new OnapCommandResultEmpty();
                            }
                        }

                        bodyProcessedLine += bodyProcessedPattern.substring(currentIdx, idxS) + valueS;
                        currentIdx = idxE;
                        positionalIdx++;
                    } catch (OnapCommandResultEmpty e) {
                        throw e;
                    } catch (Exception e) {
                        throw new OnapCommandResultMapProcessingFailed(line, e);
                    }
                }
                result.add(bodyProcessedLine);
            }

            return result;
        }
    }

    /**
     * Set argument to param value.
     *
     * @param params
     *            map
     * @param input
     *            HttpInput
     * @return HttpInput
     * @throws OnapCommandParameterNotFound
     *             exception
     * @throws OnapCommandInvalidParameterValue
     *             exception
     */
    public static HttpInput populateParameters(Map<String, OnapCommandParameter> params, HttpInput input)
            throws OnapCommandException {
        HttpInput inp = new HttpInput();
        for (OnapCommandParameter param : params.values()) {
            if (ParameterType.BINARY.equals(param.getParameterType())) {
                inp.setBinaryData(true);
                break;
            }
        }
        inp.setBody(replaceLineFromInputParameters(input.getBody(), params));
        inp.setUri(replaceLineFromInputParameters(input.getUri(), params));
        inp.setMethod(input.getMethod().toLowerCase());
        for (String h : input.getReqHeaders().keySet()) {
            String value = input.getReqHeaders().get(h);
            inp.getReqHeaders().put(h, replaceLineFromInputParameters(value, params));
        }

        for (String h : input.getReqQueries().keySet()) {
            String value = input.getReqQueries().get(h);
            inp.getReqQueries().put(h, replaceLineFromInputParameters(value, params));
        }

        return inp;
    }

    /**
     * Populate result.
     *
     * @param resultMap
     *            map
     * @param resultHttp
     *            HttpResult
     * @return map
     * @throws OnapCommandHttpHeaderNotFound
     *             header not found exception
     * @throws OnapCommandHttpInvalidResponseBody
     *             invalid response body exception
     * @throws OnapCommandResultMapProcessingFailed
     *             map processing failed exception
     */
    public static Map<String, ArrayList<String>> populateOutputs(Map<String, String> resultMap, HttpResult resultHttp)
            throws OnapCommandException {
        Map<String, ArrayList<String>> resultsProcessed = new HashMap<>();

        for (Entry<String, String> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            resultsProcessed.put(key, replaceLineFromOutputResults(resultMap.get(key), resultHttp));
        }

        return resultsProcessed;
    }

    /**
     * Find external schema files.
     *
     * @return list ExternalSchema
     * @throws OnapCommandDiscoveryFailed
     *             exception
     * @throws OnapCommandInvalidSchema
     *             exception
     */
    public static List<ExternalSchema> findAllExternalSchemas() throws OnapCommandException {
        List<ExternalSchema> extSchemas = new ArrayList<>();
        try {
            Resource[] res = getExternalResources(Constants.EXTERNAL_SCHEMA_PATH_PATERN);
            if (res != null && res.length > 0) {
                Map<String, ?> resourceMap;
                for (Resource resource : res) {
                    resourceMap = getExternalSchemaMap(resource);
                    if (resourceMap != null && resourceMap.size() > 0) {
                        ExternalSchema schema = new ExternalSchema();
                        schema.setSchemaName(resource.getFilename());
                        schema.setCmdName((String) resourceMap.get(Constants.NAME));
                        Object obj = resourceMap.get(Constants.ONAP_CMD_SCHEMA_VERSION);
                        schema.setVersion(obj.toString());
                        extSchemas.add(schema);
                    }
                }
            }
        } catch (IOException e) {
            throw new OnapCommandDiscoveryFailed(Constants.EXTERNAL_SCHEMA_DIRECTORY, e);
        }

        return extSchemas;
    }

    /**
     * Returns all resources available under certain directory in class-path.
     *
     * @param pattern
     *            search pattern
     * @return resources found resources
     * @throws IOException
     *             exception
     */
    public static Resource[] getExternalResources(String pattern) throws IOException {
        ClassLoader cl = OnapCommandUtils.class.getClassLoader();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
        return resolver.getResources("classpath*:" + pattern);
    }

    /**
     * Returns a resource available under certain directory in class-path.
     *
     * @param pattern
     *            search pattern
     * @return found resource
     * @throws IOException
     *             exception
     */
    public static Resource getExternalResource(String fileName, String pattern) throws IOException {
        Resource[] resources = getExternalResources(pattern);
        if (resources != null && resources.length > 0) {
            for (Resource res : resources) {
                if (res.getFilename().equals(fileName)) {
                    return res;
                }
            }
        }

        return null;
    }

    /**
     * Get schema map.
     *
     * @param resource
     *            resource obj
     * @return map
     * @throws OnapCommandInvalidSchema
     *             exception
     */
    public static Map<String, ?> getExternalSchemaMap(Resource resource) throws OnapCommandInvalidSchema {
        Map<String, ?> values = null;
        try {
            values = (Map<String, ?>) new Yaml().load(resource.getInputStream());
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(resource.getFilename(), e);
        }
        return values;
    }

    /**
     * Persist the external schema details.
     *
     * @param schemas
     *            list
     * @throws OnapCommandDiscoveryFailed
     *             exception
     */
    public static void persist(List<ExternalSchema> schemas) throws OnapCommandDiscoveryFailed {
        if (schemas != null) {
            try {
                Resource[] resources = getExternalResources(Constants.EXTERNAL_DISCOVERY_DIRECTORY);
                if (resources != null && resources.length == 1) {
                    String path = resources[0].getURI().getPath();
                    File file = new File(path + File.separator + Constants.EXTERNAL_DISCOVERY_FILE);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, schemas);
                }
            } catch (IOException e1) {
                throw new OnapCommandDiscoveryFailed(Constants.EXTERNAL_DISCOVERY_DIRECTORY,
                        Constants.EXTERNAL_DISCOVERY_FILE, e1);
            }
        }
    }

    /**
     * Check if json file discovered or not.
     *
     * @return boolean
     * @throws OnapCommandDiscoveryFailed
     *             exception
     */
    public static boolean isJsonFileDiscovered() throws OnapCommandDiscoveryFailed {
        Resource resource = null;
        try {
            resource = getExternalResource(Constants.EXTERNAL_DISCOVERY_FILE,
                    Constants.EXTERNAL_DISCOVERY_DIRECTORY_PATTERN);
            if (resource != null) {
                return true;
            }
        } catch (IOException e) {
            throw new OnapCommandDiscoveryFailed(Constants.EXTERNAL_DISCOVERY_DIRECTORY,
                    Constants.EXTERNAL_DISCOVERY_FILE, e);
        }

        return false;
    }

    /**
     * Load the previous discovered json file.
     *
     * @return list
     * @throws OnapCommandInvalidSchema
     *             exception
     * @throws OnapCommandDiscoveryFailed
     *             exception
     */
    public static List<ExternalSchema> loadExternalSchemasFromJson() throws OnapCommandException {
        List<ExternalSchema> schemas = new ArrayList<>();
        if (!isJsonFileDiscovered()) {
            schemas = findAllExternalSchemas();
            if (!schemas.isEmpty()) {
                persist(schemas);
            }
        } else {
            try {
                Resource resource = getExternalResource(Constants.EXTERNAL_DISCOVERY_FILE,
                        Constants.EXTERNAL_DISCOVERY_DIRECTORY_PATTERN);
                if (resource != null) {
                    File file = new File(resource.getURI().getPath());
                    ObjectMapper mapper = new ObjectMapper();
                    ExternalSchema[] list = mapper.readValue(file, ExternalSchema[].class);
                    schemas.addAll(Arrays.asList(list));
                }
            } catch (IOException e) {
                throw new OnapCommandDiscoveryFailed(Constants.EXTERNAL_DISCOVERY_DIRECTORY,
                        Constants.EXTERNAL_DISCOVERY_FILE, e);
            }
        }

        return schemas;
    }

    /**
     * Fetch a particular schema details.
     *
     * @param cmd
     *            command name
     * @return ExternalSchema obj
     * @throws OnapCommandInvalidSchema
     *             exception
     * @throws OnapCommandDiscoveryFailed
     *             exception
     */
    public static ExternalSchema loadExternalSchemaFromJson(String cmd) throws OnapCommandException {
        List<ExternalSchema> list = loadExternalSchemasFromJson();
        ExternalSchema schemaStr = null;
        if (list != null) {
            for (ExternalSchema schema : list) {
                if (cmd.equals(schema.getCmdName())) {
                    schemaStr = schema;
                    break;
                }
            }
        }
        return schemaStr;
    }
}
