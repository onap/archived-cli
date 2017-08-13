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
import net.minidev.json.JSONObject;
import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.ad.OnapCredentials;
import org.onap.cli.fw.ad.OnapService;
import org.onap.cli.fw.cmd.OnapHttpCommand;
import org.onap.cli.fw.cmd.OnapSwaggerCommand;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.conf.OnapCommandConfg;
import org.onap.cli.fw.error.OnapCommandDiscoveryFailed;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandHttpHeaderNotFound;
import org.onap.cli.fw.error.OnapCommandHttpInvalidResponseBody;
import org.onap.cli.fw.error.OnapCommandHttpInvalidResultMap;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import static org.onap.cli.fw.conf.Constants.API;
import static org.onap.cli.fw.conf.Constants.ATTRIBUTES;
import static org.onap.cli.fw.conf.Constants.AUTH;
import static org.onap.cli.fw.conf.Constants.BODY;
import static org.onap.cli.fw.conf.Constants.BOOLEAN_FALSE;
import static org.onap.cli.fw.conf.Constants.BOOLEAN_TRUE;
import static org.onap.cli.fw.conf.Constants.CLIENT;
import static org.onap.cli.fw.conf.Constants.DEAFULT_PARAMETER_HOST_URL;
import static org.onap.cli.fw.conf.Constants.DEAFULT_PARAMETER_PASS_WORD;
import static org.onap.cli.fw.conf.Constants.DEAFULT_PARAMETER_USERNAME;
import static org.onap.cli.fw.conf.Constants.DEFAULT_PARAMETERS;
import static org.onap.cli.fw.conf.Constants.DEFAULT_PARAMETERS_EXCLUDE;
import static org.onap.cli.fw.conf.Constants.DEFAULT_PARAMETERS_INCLUDE;
import static org.onap.cli.fw.conf.Constants.DEFAULT_PARAMETER_FILE_NAME;
import static org.onap.cli.fw.conf.Constants.DEFAULT_VALUE;
import static org.onap.cli.fw.conf.Constants.DELETE;
import static org.onap.cli.fw.conf.Constants.DESCRIPTION;
import static org.onap.cli.fw.conf.Constants.DIRECTION;
import static org.onap.cli.fw.conf.Constants.ENTITY;
import static org.onap.cli.fw.conf.Constants.EXCEPTION;
import static org.onap.cli.fw.conf.Constants.EXECUTOR;
import static org.onap.cli.fw.conf.Constants.EXTERNAL_DISCOVERY_DIRECTORY;
import static org.onap.cli.fw.conf.Constants.EXTERNAL_DISCOVERY_DIRECTORY_PATTERN;
import static org.onap.cli.fw.conf.Constants.EXTERNAL_DISCOVERY_FILE;
import static org.onap.cli.fw.conf.Constants.EXTERNAL_SCHEMA_DIRECTORY;
import static org.onap.cli.fw.conf.Constants.EXTERNAL_SCHEMA_PATH_PATERN;
import static org.onap.cli.fw.conf.Constants.GET;
import static org.onap.cli.fw.conf.Constants.HEAD;
import static org.onap.cli.fw.conf.Constants.HEADERS;
import static org.onap.cli.fw.conf.Constants.HTTP;
import static org.onap.cli.fw.conf.Constants.HTTP_BODY_FAILED_PARSING;
import static org.onap.cli.fw.conf.Constants.HTTP_BODY_JSON_EMPTY;
import static org.onap.cli.fw.conf.Constants.HTTP_SUCCESS_CODE_INVALID;
import static org.onap.cli.fw.conf.Constants.IS_OPTIONAL;
import static org.onap.cli.fw.conf.Constants.IS_SECURED;
import static org.onap.cli.fw.conf.Constants.LONG_OPTION;
import static org.onap.cli.fw.conf.Constants.MERHOD;
import static org.onap.cli.fw.conf.Constants.METHOD;
import static org.onap.cli.fw.conf.Constants.NAME;
import static org.onap.cli.fw.conf.Constants.ONAP_CMD_SCHEMA_VERSION;
import static org.onap.cli.fw.conf.Constants.PARAMETERS;
import static org.onap.cli.fw.conf.Constants.POST;
import static org.onap.cli.fw.conf.Constants.PUT;
import static org.onap.cli.fw.conf.Constants.QUERIES;
import static org.onap.cli.fw.conf.Constants.REQUEST;
import static org.onap.cli.fw.conf.Constants.RESULTS;
import static org.onap.cli.fw.conf.Constants.RESULT_MAP;
import static org.onap.cli.fw.conf.Constants.SAMPLE_RESPONSE;
import static org.onap.cli.fw.conf.Constants.SCHEMA_FILE_NOT_EXIST;
import static org.onap.cli.fw.conf.Constants.SCHEMA_FILE_WRONG_EXTN;
import static org.onap.cli.fw.conf.Constants.SCHEMA_INVALID_DEFAULT_PARAMS_SECTION;
import static org.onap.cli.fw.conf.Constants.SCOPE;
import static org.onap.cli.fw.conf.Constants.SERVICE;
import static org.onap.cli.fw.conf.Constants.SHORT_OPTION;
import static org.onap.cli.fw.conf.Constants.SUCCESS_CODES;
import static org.onap.cli.fw.conf.Constants.TYPE;
import static org.onap.cli.fw.conf.Constants.URI;
import static org.onap.cli.fw.conf.Constants.VERSION;

/**
 * Provides helper method to parse Yaml files and produce required objects.
 *
 */
public class OnapCommandUtils {

    private static final List<String> HTTP_REQUEST_PARAMS = Arrays.asList(URI, METHOD, BODY, HEADERS, QUERIES);
    private static final List<String> HTTP_REQUEST_MANDATORY_PARAMS = Arrays.asList(URI, METHOD);
    private static final List<String> HTTP_METHODS = Arrays.asList(POST, GET, DELETE, PUT, HEAD);
    private static final List<String> HTTP_SECTIONS = Arrays.asList(REQUEST, SUCCESS_CODES, RESULT_MAP,
            SAMPLE_RESPONSE);
    private static final List<String> HTTP_MANDATORY_SECTIONS = Arrays.asList(REQUEST, SUCCESS_CODES);
    private static final List<String> RESULT_PARAMS_LIST = Arrays.asList(NAME, DESCRIPTION, TYPE, SHORT_OPTION,
            LONG_OPTION, IS_OPTIONAL, DEFAULT_VALUE, IS_SECURED);
    private static final List<String> RESULT_PARAMS_MANDATORY_LIST = Arrays.asList(NAME, DESCRIPTION, TYPE);
    private static final List<String> TOP_LEVEL_PARAMS_LIST = Arrays.asList(ONAP_CMD_SCHEMA_VERSION, NAME,
            DESCRIPTION);
    private static final List<String> TOP_LEVEL_MANDATORY_LIST = Arrays.asList(ONAP_CMD_SCHEMA_VERSION);
    private static final List<String> SERVICE_PARAMS_LIST = Arrays.asList(NAME, VERSION, AUTH);
    private static final List<String> SERVICE_PARAMS_MANDATORY_LIST = Arrays.asList(NAME, VERSION);

    private static final List<String> INPUT_PARAMS_LIST = Arrays.asList(NAME, DESCRIPTION, TYPE, SHORT_OPTION,
            LONG_OPTION, IS_OPTIONAL, DEFAULT_VALUE, IS_SECURED);
    private static final List<String> INPUT_PARAMS_MANDATORY_LIST = Arrays.asList(NAME, DESCRIPTION, TYPE);
    protected static final List<String> BOOLEAN_VALUES = Arrays.asList(BOOLEAN_TRUE, BOOLEAN_FALSE);



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
            Resource resource = getExternalResource(schemaName, EXTERNAL_SCHEMA_PATH_PATERN);

            if (resource != null) {
                inputStream = resource.getInputStream();
            }

        } catch (IOException e) {
            throw new OnapCommandSchemaNotFound(schemaName, e);
        }
        if (inputStream == null) {
            inputStream = loadSchemaFromFile(schemaName);
        }

        Map<String, ?> values = null;
        try {
            values = (Map<String, ?>) new Yaml().load(inputStream);
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(schemaName, e);
        }
        String schemaVersion = "";
        if (values.keySet().contains(ONAP_CMD_SCHEMA_VERSION)) {
            Object obj = values.get(ONAP_CMD_SCHEMA_VERSION);
            schemaVersion = obj.toString();
        }

        if (!version.equals(schemaVersion)) {
            throw new OnapCommandInvalidSchemaVersion(schemaVersion);
        }

        return values;
    }

    private static InputStream loadSchemaFromFile(String schemaLocation) throws OnapCommandInvalidSchema {
        File schemaFile = new File(schemaLocation);
        if (!schemaFile.isFile()) {
            throw new OnapCommandInvalidSchema(schemaFile.getName(), SCHEMA_FILE_NOT_EXIST);
        }
        String fileName = schemaFile.getName();

        if (!fileName.endsWith(".yaml")) {
            throw new OnapCommandInvalidSchema(fileName, SCHEMA_FILE_WRONG_EXTN);
        }

        try {
            return new FileInputStream(schemaFile);
        } catch (FileNotFoundException e) {
            throw new OnapCommandInvalidSchema(fileName, e);
        }
    }

    /**
     * Retrieve OnapCommand from schema.
     *
     * @param cmd            OnapCommand
     * @param schemaName     schema name
     * @param includeDefault include if default
     * @param validateSchema flag to represent validation
     * @throws OnapCommandParameterNameConflict       param name conflict exception
     * @throws OnapCommandParameterOptionConflict     param option conflict exception
     * @throws OnapCommandInvalidParameterType        invalid param type exception
     * @throws OnapCommandInvalidPrintDirection       invalid print direction exception
     * @throws OnapCommandInvalidResultAttributeScope invalid scope exception
     * @throws OnapCommandSchemaNotFound              schema not found
     * @throws OnapCommandInvalidSchema               invalid schema
     * @throws OnapCommandInvalidSchemaVersion        invalid schema version
     */
    public static List<String> loadSchema(OnapCommand cmd, String schemaName, boolean includeDefault,
                                          boolean validateSchema) throws OnapCommandException {
        try {
            Map<String, ?> defaultParameterMap = includeDefault ?
                    validateSchemaVersion(DEFAULT_PARAMETER_FILE_NAME, cmd.getSchemaVersion()) : new HashMap<>();
            Map<String, List<Map<String, String>>> commandYamlMap = (Map<String, List<Map<String, String>>>)validateSchemaVersion(schemaName, cmd.getSchemaVersion());

            List<String> defParams = new ArrayList<>();

            if (includeDefault) {
                if (commandYamlMap.get(PARAMETERS) == null) {
                    commandYamlMap.put(PARAMETERS, (List<Map<String, String>>) defaultParameterMap.get(PARAMETERS));
                } else {
                    commandYamlMap.get(PARAMETERS).addAll((List<Map<String, String>>) defaultParameterMap.get(PARAMETERS));
                }
                defParams = ((List<Map<String, String>>) defaultParameterMap.get(PARAMETERS)).stream()
                        .map(p -> p.get(NAME)).collect(Collectors.toList());
            }

            return parseSchema(cmd, commandYamlMap, defParams, validateSchema);
        } catch (OnapCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(schemaName, e);
        }
    }

    private static void processNoAuth(Set<String> parameterSet, final OnapCommand cmd, final List<String> includeParams,
                                      final List<String> excludeParams) throws OnapCommandInvalidDefaultParameter {
        // processing for no-auth type
        if (cmd.getService() != null) {
            List<String> includeAuthParams = new ArrayList();
            List<String> excludeAuthParams = new ArrayList<>();
            boolean noAuth = cmd.getService().isNoAuth();

            if (cmd.isCommandInternal()) {
                excludeAuthParams.addAll(OnapCommandConfg.getExcludeParamsForInternalCmd());
            } else {
                if (noAuth) {
                    includeAuthParams.addAll(OnapCommandConfg.getIncludeParamsForNoAuthEnableExternalCmd());
                    excludeAuthParams.addAll(OnapCommandConfg.getExcludeParamsForNoAuthEnableExternalCmd());
                } else {
                    includeAuthParams.addAll(OnapCommandConfg.getIncludeParamsForNoAuthDisableExternalCmd());
                }
            }

            List<String> invalidExclude = excludeAuthParams.stream().filter(includeParams::contains)
                    .collect(Collectors.toList());

            List<String> invalidInclude = includeAuthParams.stream().filter(excludeParams::contains)
                    .filter(p->!includeParams.contains(p)).collect(Collectors.toList());

            if (!invalidExclude.isEmpty() || !invalidInclude.isEmpty()) {
                throw new OnapCommandInvalidDefaultParameter(Stream.concat(invalidExclude.stream(), invalidInclude.stream())
                        .collect(Collectors.toList()));
            }


            parameterSet.addAll(includeAuthParams);
            parameterSet.removeAll(excludeAuthParams);
        }
    }

    private static void throwOrCollect(OnapCommandException ex, List<String> list,
                                       boolean shouldCollectException) throws OnapCommandException {
        if (shouldCollectException) {
            list.add(ex.getMessage());
        } else {
            throw ex;
        }
    }

    private static void validateTags(List<String> schemaErrors, Map<String, ?> yamlMap,
                                             List<String> totalParams, List<String> mandatoryParams,
                                             String section) {
        for (String param : totalParams) {
            boolean isMandatory = mandatoryParams.contains(param);
            boolean isYamlContains = yamlMap.containsKey(param);
            if (isMandatory) {
                if (!isYamlContains) {
                    schemaErrors.add(mandatoryAttrMissing(param, section));
                } else {
                    String value = String.valueOf(yamlMap.get(param));
                    if (value == null || "".equals(value) || "null".equals(value)) {
                        schemaErrors.add(mandatoryAttrEmpty(param, section));
                    }
                }
            }
        }

    }

    /**
     * Validate Boolean.
     *
     * @param toValidate
     *            string
     * @return boolean
     */
    protected static boolean validateBoolean(String toValidate) {
        return BOOLEAN_VALUES.contains(toValidate.toLowerCase());
    }

    private static List<String> parseSchema(OnapCommand cmd,
                                            final Map<String, ?> values,
                                            final List<String> defaultParamNames,
                                            boolean validate) throws OnapCommandException {

        List<String> exceptionList = new ArrayList<>();
        List<String> shortOptions = new ArrayList<>();
        List<String> longOptions = new ArrayList<>();
        Set<String> filteredDefaultParams = new HashSet<>();

        if (validate) {
            validateTags(exceptionList, (Map<String, Object>) values, TOP_LEVEL_PARAMS_LIST, TOP_LEVEL_MANDATORY_LIST, "root level");
        }


        List<String> sections = Arrays.asList(NAME, DESCRIPTION, SERVICE,
                DEFAULT_PARAMETERS, PARAMETERS, RESULTS);

        for (String key : sections) {

            if (NAME.equals(key) && values.containsKey(key)) {
                Object val = values.get(key);
                if (val != null) {
                    cmd.setName(val.toString());
                }
            } else if (DESCRIPTION.equals(key) && values.containsKey(key)) {
                Object val = values.get(key);
                if (val != null) {
                    cmd.setDescription(val.toString());
                }
            } else if (SERVICE.equals(key) && values.containsKey(key)) {
                Map<String, String> map = (Map<String, String>) values.get(key);

                if (validate) {
                    // is it required to check wether schema contains Service sec
                    validateTags(exceptionList, (Map<String, Object>)values.get(key), SERVICE_PARAMS_LIST, SERVICE_PARAMS_MANDATORY_LIST, SERVICE);
                    if (map.containsKey(AUTH)) {
                        Object obj = map.get(AUTH);
                        if (obj == null) {
                            exceptionList.add(emptyValue(SERVICE, AUTH));
                        } else {
                            String value = String.valueOf(obj);
                            if (!validateBoolean(value)) {
                                exceptionList.add(invalidBooleanValueMessage(SERVICE, AUTH, value));
                            }
                        }
                    }
                }

                if (map != null) {
                    OnapService srv = new OnapService();

                    for (Map.Entry<String, String> entry1 : map.entrySet()) {
                        String key1 = entry1.getKey();

                        if (NAME.equals(key1)) {
                            srv.setName(map.get(key1));
                        } else if (VERSION.equals(key1)) {
                            srv.setVersion(map.get(key1));
                        } else if (AUTH.equals(key1)) {
                            Object obj = map.get(key1);
                            //TODO mrkanag Validate and raise exception for invalid case
                            srv.setAuthType(obj.toString());
                        } else if (Constants.MODE.equals(key1)) {
                            Object obj = map.get(key1);
                            //TODO mrkanag Validate and raise exception for invalid case
                            srv.setMode(obj.toString());
                        }
                    }

                    cmd.setService(srv);
                }
            } else if (DEFAULT_PARAMETERS.equals(key)) {

                Map<String, List<String>> defParameters = (Map) values.get(DEFAULT_PARAMETERS);
                List<String> includeParams = new ArrayList<>();
                List<String> excludeParams = new ArrayList<>();

                if (values.containsKey(DEFAULT_PARAMETERS) && defParameters == null) {
                    // if default parameter section is available then it must have either include
                    // or exclude sub-section.
                    throwOrCollect(new OnapCommandInvalidSchema(SCHEMA_INVALID_DEFAULT_PARAMS_SECTION),
                            exceptionList, validate);
                }


                if (defParameters != null) {
                    // validate default parameters
                    if (defParameters.containsKey(DEFAULT_PARAMETERS_INCLUDE)) {
                        includeParams = defParameters.get(DEFAULT_PARAMETERS_INCLUDE);
                    }

                    List<String> invInclude = includeParams.stream()
                            .filter(p -> !defaultParamNames.contains(p))
                            .collect(Collectors.toList());

                    if (defParameters.containsKey(DEFAULT_PARAMETERS_EXCLUDE)) {
                        excludeParams = defParameters.get(DEFAULT_PARAMETERS_EXCLUDE);
                    }

                    List<String> invExclude = excludeParams.stream().filter(p -> !defaultParamNames.contains(p))
                            .collect(Collectors.toList());


                    if (!invExclude.isEmpty() || !invInclude.isEmpty()) {

                        throwOrCollect(new OnapCommandInvalidDefaultParameter(Stream.concat(invInclude.stream(),
                                invExclude.stream()).collect(Collectors.toList())),
                                exceptionList, validate);
                    }

                    if (!includeParams.isEmpty()) {
                        filteredDefaultParams.addAll(includeParams);
                    } else if (!excludeParams.isEmpty()) {
                        List<String> finalExcludeParams = excludeParams;
                        defaultParamNames.stream().filter(p -> !finalExcludeParams.contains(p))
                                .forEach(filteredDefaultParams::add);
                    }
                } else {
                    filteredDefaultParams.addAll(defaultParamNames);
                }
                try {
                    processNoAuth(filteredDefaultParams, cmd, includeParams, excludeParams);
                } catch (OnapCommandException e) {
                    throwOrCollect(e, exceptionList, validate);
                }
            } else if (PARAMETERS.equals(key) && values.containsKey(key)) {

                List<Map<String, String>> parameters = (List) values.get(key);

                if (parameters != null) {
                    Set<String> names = new HashSet<>();
                    Set<String> inputShortOptions = new HashSet<>();
                    Set<String> inputLongOptions = new HashSet<>();

                    for (Map<String, String> map : parameters) {
                        OnapCommandParameter param = new OnapCommandParameter();

                        if (validate) {
                            validateTags(exceptionList, map, INPUT_PARAMS_LIST, INPUT_PARAMS_MANDATORY_LIST, PARAMETERS);
                        }

                        for (Map.Entry<String, String> entry1 : map.entrySet()) {
                            String key2 = entry1.getKey();

                            if (NAME.equals(key2)) {
                                if (names.contains(map.get(key2))) {
                                        throwOrCollect(new OnapCommandParameterNameConflict(map.get(key2)), exceptionList, validate);
                                }
                                names.add(map.get(key2));
                                param.setName(map.get(key2));
                            } else if (DESCRIPTION.equals(key2)) {
                                param.setDescription(map.get(key2));
                            } else if (SHORT_OPTION.equals(key2)) {
                                if (shortOptions.contains(map.get(key2))) {
                                        throwOrCollect(new OnapCommandParameterOptionConflict(map.get(key2)), exceptionList, validate);
                                }
                                shortOptions.add(map.get(key2));
                                param.setShortOption(map.get(key2));
                            } else if (LONG_OPTION.equals(key2)) {
                                if (longOptions.contains(map.get(key2))) {
                                        throwOrCollect(new OnapCommandParameterOptionConflict(map.get(key2)), exceptionList, validate);
                                }
                                longOptions.add(map.get(key2));
                                param.setLongOption(map.get(key2));
                            } else if (DEFAULT_VALUE.equals(key2)) {
                                Object obj = map.get(key2);
                                param.setDefaultValue(obj.toString());
                            } else if (TYPE.equals(key2)) {
                                try {
                                    param.setParameterType(ParameterType.get(map.get(key2)));
                                } catch (OnapCommandException ex) {
                                    throwOrCollect(ex, exceptionList, validate);
                                }
                            } else if (IS_OPTIONAL.equals(key2)) {
                                if (validate) {
                                    if (!validateBoolean(String.valueOf(map.get(key2)))) {
                                        exceptionList.add(invalidBooleanValueMessage(map.get(NAME),
                                                IS_SECURED, map.get(key2)));
                                    }
                                }
                                if ("true".equalsIgnoreCase(String.valueOf(map.get(key2)))) {
                                    param.setOptional(true);
                                } else {
                                    param.setOptional(false);
                                }
                            } else if (IS_SECURED.equals(key2)) {
                                if (validate) {
                                    if (!validateBoolean(String.valueOf(map.get(key2)))) {
                                        exceptionList.add(invalidBooleanValueMessage(map.get(NAME),
                                                IS_SECURED, map.get(key2)));
                                    }
                                }

                                if ("true".equalsIgnoreCase(String.valueOf(map.get(key2)))) {
                                    param.setSecured(true);
                                } else {
                                    param.setSecured(false);
                                }
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
            } else if (RESULTS.equals(key) && values.containsKey(key)) {
                Map<String, ?> valueMap = (Map<String, ?>) values.get(key);
                if (valueMap != null) {
                    OnapCommandResult result = new OnapCommandResult();
                    for (Map.Entry<String, ?> entry1 : valueMap.entrySet()) {
                        String key3 = entry1.getKey();

                        if (DIRECTION.equals(key3)) {
                            try {
                                result.setPrintDirection(PrintDirection.get((String) valueMap.get(key3)));
                            } catch (OnapCommandException ex) {
                                throwOrCollect(ex, exceptionList, validate);
                            }
                        } else if (ATTRIBUTES.equals(key3)) {
                            List<Map<String, String>> attrs = (ArrayList) valueMap.get(key3);

                            for (Map<String, String> map : attrs) {
                                OnapCommandResultAttribute attr = new OnapCommandResultAttribute();
                                if (validate) {
                                    validateTags(exceptionList, map, RESULT_PARAMS_LIST, RESULT_PARAMS_MANDATORY_LIST, ATTRIBUTES);
                                }

                                Set<String> resultParamNames = new HashSet<>();

                                for (Map.Entry<String, String> entry4 : map.entrySet()) {
                                    String key4 = entry4.getKey();

                                    if (NAME.equals(key4)) {
                                        if (resultParamNames.contains(map.get(key4))) {
                                            exceptionList.add(attributeNameExist(map.get(key4), ATTRIBUTES));
                                        } else {
                                            attr.setName(map.get(key4));
                                            resultParamNames.add(map.get(key4));
                                        }
                                    } else if (DESCRIPTION.equals(key4)) {
                                        attr.setDescription(map.get(key4));
                                    } else if (SCOPE.equals(key4)) {
                                        try {
                                            attr.setScope(OnapCommandResultAttributeScope.get(map.get(key4)));
                                        } catch (OnapCommandException ex) {
                                            throwOrCollect(ex, exceptionList, validate);
                                        }
                                    } else if (TYPE.equals(key4)) {
                                        try {
                                            attr.setType(ParameterType.get(map.get(key4)));
                                        } catch (OnapCommandException ex) {
                                            throwOrCollect(ex, exceptionList, validate);
                                        }
                                    } else if (IS_SECURED.equals(key4)) {
                                        if (validate) {
                                            if (!validateBoolean(String.valueOf(map.get(key4)))) {
                                                exceptionList.add(invalidBooleanValueMessage(ATTRIBUTES,
                                                        IS_SECURED, map.get(key4)));
                                            }
                                        }
                                        if ("true".equals(String.valueOf(map.get(key4)))) {
                                            attr.setSecured(true);
                                        } else {
                                            attr.setSecured(false);
                                        }
                                    }

                                }
                                result.getRecords().add(attr);
                            }
                        }
                    }
                    cmd.setResult(result);
                }
            }
        }
        return exceptionList;
    }

    private static String attributeNameExist(String name, String section) {
        return "Attribute name='" + name + "' under '" + section + ":' is already used, Take different one.";
    }

    private static String parameterNotMapped(String declaredParam) {
        return "The parameter '" + declaredParam
                + "' declared under 'parameters:' section is not mapped into request section.";
    }

    private static String mandatoryAttrEmpty(String param, String section) {

        return "Mandatory attribute '" + param + "' under '" + section + "' shouldn't be null or empty";
    }

    private static String mandatoryAttrMissing(String param, String section) {

        return "Mandatory attribute '" + param + "' is missing under '" + section + "'";
    }

    public static String emptySection(String section) {
        return "The section '" + section + ":' cann't be null or empty";
    }

    private static String emptyValue(String section, String attribute) {
        return "Attribute '" + attribute + "' under '" + section + "' is null or empty";
    }

    private static String invalidType(String section, String attribute, List<String> types) {
        return "Attribute '" + attribute + "' under '" + section + "' is invalid, correct types are "
                + types.toString();
    }

    private static String invalidRequestParam(String subSection, String attribute) {
        return "The http request '" + subSection + "' parameter '" + attribute
                + "' is not declared under 'parameters:' section";
    }

    private static String invalidBooleanValueMessage(String section, String attribute, String value) {
        return "The value '" + value + "' of '" + attribute + "' present under '" + section + "' should be boolean";
    }

    private static Set<String> validateHttpQueries(Map<String, Object> requestMap) {
        Map<String, Object> queries = (Map<String, Object>) requestMap.get(QUERIES);
        Set<String> queryParamNames = new HashSet<>();
        if (queries != null) {
            for (Entry<String, Object> entry : queries.entrySet()) {
                parseParameters(String.valueOf(entry.getValue()), queryParamNames);
            }
        }
        return queryParamNames;
    }


    private static Set<String> validateHttpHeaders(Map<String, Object> requestMap) {

        Map<String, Object> headers = (Map<String, Object>) requestMap.get(HEADERS);
        Set<String> headerParamNames = new HashSet<>();
        if (headers != null) {
            for (Entry<String, Object> entry : headers.entrySet()) {
                parseParameters(String.valueOf(entry.getValue()), headerParamNames);
            }
        }
        return headerParamNames;
    }

    private static Set<String> validateHttpBody(List<String> errorList, Map<String, Object> requestMap) {
        Set<String> bodyParamNames = new HashSet<>();
        Object bodyString = requestMap.get(BODY);
        if (bodyString == null) {
            return bodyParamNames;
        }

        String body = String.valueOf(bodyString);
        JSONObject obj = null;
        try {
            obj = new ObjectMapper().readValue(body, JSONObject.class);
        } catch (IOException e1) { // NOSONAR
            errorList.add(HTTP_BODY_FAILED_PARSING);
        }
        if (obj == null || "".equals(obj.toString())) {
            errorList.add(HTTP_BODY_JSON_EMPTY);
        }
        parseParameters(body, bodyParamNames);

        return bodyParamNames;
    }

    private static Set<String> validateHttpUri(List<String> errorList, Map<String, Object> requestMap) {
        Set<String> uriParamNames = new HashSet<>();
        String uri = (String) requestMap.get(URI);
        if (uri == null || uri.isEmpty()) {
            errorList.add(emptySection(URI));
            return uriParamNames;
        }
        parseParameters(uri, uriParamNames);
        return uriParamNames;
    }

    private static void parseParameters(String line, Set<String> paramNames) {

        int currentIdx = 0;
        while (currentIdx < line.length()) {
            int idxS = line.indexOf("${", currentIdx);
            if (idxS == -1) {
                break;
            }
            int idxE = line.indexOf("}", idxS);
            String paramName = line.substring(idxS + 2, idxE);
            paramNames.add(paramName.trim());

            currentIdx = idxE + 1;
        }

    }

    private static Set<String> getRequestParams(Map<String, ?> yamlMap) {

        Set<String> set = new HashSet<>();

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> inputParams = (List<Map<String, Object>>) yamlMap.get(PARAMETERS);

        if (inputParams != null) {
            for (Map<String, Object> map : inputParams) {
                for (Entry<String, Object> entry : map.entrySet()) {
                    Object key = entry.getKey();

                    if (NAME.equals(key)) {
                        set.add(String.valueOf(entry.getValue()));
                        break;
                    }
                }
            }
        }

        return set;
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
            Map<String, String> valueMap = (Map<String, String>) values.get(EXECUTOR);
            OnapCommandExecutor exec = new OnapCommandExecutor();

            for (Map.Entry<String, String> entry1 : valueMap.entrySet()) {
                String key1 = entry1.getKey();

                if (API.equals(key1)) {
                    exec.setApi(valueMap.get(key1));
                } else if (CLIENT.equals(key1)) {
                    exec.setClient(valueMap.get(key1));
                } else if (ENTITY.equals(key1)) {
                    exec.setEntity(valueMap.get(key1));
                } else if (EXCEPTION.equals(key1)) {
                    exec.setException(valueMap.get(key1));
                } else if (METHOD.equals(key1)) {
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
    public static ArrayList<String> loadHTTPSchemaSection(OnapHttpCommand cmd, String schemaName,
                                                          boolean validate) throws OnapCommandException {
        ArrayList<String> errorList = new ArrayList<>();
        try {
            Map<String, ?> values = (Map<String, ?>) validateSchemaVersion(schemaName, cmd.getSchemaVersion());
            Map<String, ?> valMap = (Map<String, ?>) values.get(HTTP);

            if (valMap != null) {
                if (validate) {
                    validateTags(errorList, valMap, HTTP_SECTIONS, HTTP_MANDATORY_SECTIONS, PARAMETERS);
                    errorList.addAll(validateHttpSchemaSection(values));
                }
                for (Map.Entry<String, ?> entry1 : valMap.entrySet()) {
                    String key1 = entry1.getKey();
                    if (REQUEST.equals(key1)) {
                        Map<String, ?> map = (Map<String, ?>) valMap.get(key1);

                        for (Map.Entry<String, ?> entry2 : map.entrySet()) {
                            try {
                                String key2 = entry2.getKey();
                                if (URI.equals(key2)) {
                                    Object obj = map.get(key2);
                                    cmd.getInput().setUri(obj.toString());
                                } else if (MERHOD.equals(key2)) {
                                    Object obj = map.get(key2);
                                    cmd.getInput().setMethod(obj.toString());
                                } else if (BODY.equals(key2)) {
                                    Object obj = map.get(key2);
                                    cmd.getInput().setBody(obj.toString());
                                } else if (HEADERS.equals(key2)) {
                                    Map<String, String> head = (Map<String, String>) map.get(key2);
                                    cmd.getInput().setReqHeaders(head);
                                } else if (QUERIES.equals(key2)) {
                                    Map<String, String> query = (Map<String, String>) map.get(key2);

                                    cmd.getInput().setReqQueries(query);
                                }
                            }catch (Exception ex) {
                                throwOrCollect(new OnapCommandInvalidSchema(schemaName, ex), errorList, validate);
                            }
                        }
                    } else if (SUCCESS_CODES.equals(key1)) {
                        if (validate) {
                            validateHttpSccessCodes(errorList, (List<Object>) valMap.get(key1));
                        }
                        cmd.setSuccessStatusCodes((ArrayList) valMap.get(key1));
                    } else if (RESULT_MAP.equals(key1)) {
                        if (validate) {
                            validateHttpResultMap(errorList, values);
                        }
                        cmd.setResultMap((Map<String, String>) valMap.get(key1));
                    } else if (SAMPLE_RESPONSE.equals(key1)) {
                        // (mrkanag) implement sample response handling
                    }
                }
            }
        }catch (OnapCommandException e) {
            throwOrCollect(e, errorList, validate);
        }
        return errorList;
    }

    private static void validateHttpResultMap(List<String> errorList, Map<String, ?> values) throws OnapCommandException {
        Map<String, ?> valMap = (Map<String, ?>) values.get(HTTP);
        List<Map<String, String>> attributes = (List<Map<String, String>>) ((Map<String, ?>)values.get(RESULTS)).get(ATTRIBUTES);
        Set<String> resultMapParams = ((Map<String, String>) valMap.get(RESULT_MAP)).keySet();

        Set<String> resultAttNames = attributes.stream().map(map -> map.get(NAME))
                .collect(Collectors.toSet());

        List<String> invaliResultMapParams = resultMapParams.stream()
                .filter(p -> !resultAttNames.contains(p)).collect(Collectors.toList());

        if (!invaliResultMapParams.isEmpty()) {
            throwOrCollect(new OnapCommandHttpInvalidResultMap(invaliResultMapParams), errorList, true);
        }
    }

    private static void validateHttpSccessCodes(List<String> errorList, List<Object> requestSuccessCodes) {

        if (requestSuccessCodes == null || requestSuccessCodes.isEmpty()) {
            errorList.add(HTTP_SUCCESS_CODE_INVALID);
            return;
        }

        for (Object successCode : requestSuccessCodes) {
            Integer code = (Integer) successCode;
            if (code < 200 || code >= 300) {
                errorList.add(HTTP_SUCCESS_CODE_INVALID);
            }
        }

    }


    private static ArrayList<String> validateHttpSchemaSection(Map<String, ?> values) {

        ArrayList<String> errorList = new ArrayList<>();
        Map<String, ?> map = (Map<String, ?>) values.get(HTTP);
        Map<String, Object> requestMap = (Map<String, Object>) map.get(REQUEST);

        if (requestMap != null && !requestMap.isEmpty()) {
            validateTags(errorList, requestMap, HTTP_REQUEST_PARAMS, HTTP_REQUEST_MANDATORY_PARAMS, REQUEST);
            String method = (String) requestMap.get(METHOD);
            if (method != null && !method.isEmpty()) {
                if (!HTTP_METHODS.contains(method.toLowerCase())) {
                    errorList.add(invalidType(REQUEST, METHOD, HTTP_METHODS));
                }
            } else {
                errorList.add("Http request method cann't be null or empty");
            }

            Set<String> requestParams = getRequestParams(values);

            Set<String> uriParams = validateHttpUri(errorList, requestMap);

            Set<String> bodyParams = validateHttpBody(errorList, requestMap);

            Set<String> headerParams = validateHttpHeaders(requestMap);

            Set<String> queryParams = validateHttpQueries(requestMap);

            HashSet<String> totoalParams = new HashSet<>(uriParams);
            totoalParams.addAll(bodyParams);
            totoalParams.addAll(headerParams);
            totoalParams.addAll(queryParams);

            List<String> nonDeclaredParams = totoalParams.stream().filter(param -> !requestParams.contains(param))
                    .collect(Collectors.toList());

            nonDeclaredParams.stream().forEach(p -> errorList.add(parameterNotMapped(p)));
        } else {
            errorList.add(emptySection(REQUEST));
        }
        return errorList;
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
        attrName.setName(NAME);
        attrName.setDescription(NAME);
        attrName.setScope(OnapCommandResultAttributeScope.SHORT);
        paramTable.getRecords().add(attrName);

        OnapCommandResultAttribute attrDescription = new OnapCommandResultAttribute();
        attrDescription.setName(DESCRIPTION);
        attrDescription.setDescription(DESCRIPTION);
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
            } else if (param.getDefaultValue() != null && !((String)param.getDefaultValue()).isEmpty()) {
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
        return new OnapCredentials(paramMap.get(DEAFULT_PARAMETER_USERNAME),
                paramMap.get(DEAFULT_PARAMETER_PASS_WORD),
                paramMap.get(DEAFULT_PARAMETER_HOST_URL));
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
            Resource[] res = getExternalResources(EXTERNAL_SCHEMA_PATH_PATERN);
            if (res != null && res.length > 0) {
                Map<String, ?> resourceMap;
                for (Resource resource : res) {
                    resourceMap = getExternalSchemaMap(resource);
                    if (resourceMap != null && resourceMap.size() > 0) {
                        ExternalSchema schema = new ExternalSchema();
                        schema.setSchemaName(resource.getFilename());
                        schema.setCmdName((String) resourceMap.get(NAME));
                        Object obj = resourceMap.get(ONAP_CMD_SCHEMA_VERSION);
                        schema.setVersion(obj.toString());
                        extSchemas.add(schema);
                    }
                }
            }
        } catch (IOException e) {
            throw new OnapCommandDiscoveryFailed(EXTERNAL_SCHEMA_DIRECTORY, e);
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
                Resource[] resources = getExternalResources(EXTERNAL_DISCOVERY_DIRECTORY);
                if (resources != null && resources.length == 1) {
                    String path = resources[0].getURI().getPath();
                    File file = new File(path + File.separator + EXTERNAL_DISCOVERY_FILE);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, schemas);
                }
            } catch (IOException e1) {
                throw new OnapCommandDiscoveryFailed(EXTERNAL_DISCOVERY_DIRECTORY,
                        EXTERNAL_DISCOVERY_FILE, e1);
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
            resource = getExternalResource(EXTERNAL_DISCOVERY_FILE,
                    EXTERNAL_DISCOVERY_DIRECTORY_PATTERN);
            if (resource != null) {
                return true;
            }
        } catch (IOException e) {
            throw new OnapCommandDiscoveryFailed(EXTERNAL_DISCOVERY_DIRECTORY,
                    EXTERNAL_DISCOVERY_FILE, e);
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
                Resource resource = getExternalResource(EXTERNAL_DISCOVERY_FILE,
                        EXTERNAL_DISCOVERY_DIRECTORY_PATTERN);
                if (resource != null) {
                    File file = new File(resource.getURI().getPath());
                    ObjectMapper mapper = new ObjectMapper();
                    ExternalSchema[] list = mapper.readValue(file, ExternalSchema[].class);
                    schemas.addAll(Arrays.asList(list));
                }
            } catch (IOException e) {
                throw new OnapCommandDiscoveryFailed(EXTERNAL_DISCOVERY_DIRECTORY,
                        EXTERNAL_DISCOVERY_FILE, e);
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
