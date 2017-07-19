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

package org.onap.cli.fw.schema;

import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.utils.OnapCommandUtils;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.parser.ParserException;

import static org.onap.cli.fw.conf.Constants.*;

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
import java.util.Set;

/**
 * Abstract schema validation class.
 *
 */
public abstract class AbstractSchemaValidate implements SchemaValidate {

    /**
     * Supported schema types.
     *
     */
    protected enum SchemaType {
        HTTP, BASIC
    }

    protected List<String> schemaErrors = new ArrayList<>();
    protected Map<String, Object> yamlMap = new HashMap<>();
    protected Map<String, Object> defaultYamlMap = new HashMap<>();

    protected static final List<String> HTTP_SCHEMA_LIST = Arrays.asList(ONAP_CMD_SCHEMA_VERSION, NAME, DESCRIPTION,
            SERVICE, PARAMETERS, RESULTS, HTTP);

    protected static final List<String> HTTP_SCHEMA_MANDATORY_LIST = Arrays.asList(ONAP_CMD_SCHEMA_VERSION, NAME,
            DESCRIPTION, SERVICE, HTTP);
    protected static final List<String> BASIC_SCHEMA_LIST = Arrays.asList(ONAP_CMD_SCHEMA_VERSION, NAME, DESCRIPTION,
            PARAMETERS, RESULTS);

    protected static final List<String> BASIC_SCHEMA_MANDATORY_LIST = Arrays.asList(ONAP_CMD_SCHEMA_VERSION, NAME,
            DESCRIPTION, PARAMETERS);

    protected static final List<String> TOP_LEVEL_PARAMS_LIST = Arrays.asList(ONAP_CMD_SCHEMA_VERSION, NAME,
            DESCRIPTION);

    protected static final List<String> TOP_LEVEL_MANDATORY_LIST = Arrays.asList(ONAP_CMD_SCHEMA_VERSION, NAME,
            DESCRIPTION);

    protected static final List<String> SERVICE_PARAMS_LIST = Arrays.asList(NAME, VERSION, NO_AUTH);

    protected static final List<String> SERVICE_PARAMS_MANDATORY_LIST = Arrays.asList(NAME, VERSION);

    protected static final List<String> INPUT_PARAMS_LIST = Arrays.asList(NAME, DESCRIPTION, TYPE, SHORT_OPTION,
            LONG_OPTION, IS_OPTIONAL, DEFAULT_VALUE, IS_SECURED);

    protected static final List<String> INPUT_PARAMS_MANDATORY_LIST = Arrays.asList(NAME, DESCRIPTION, TYPE);

    protected static final List<String> PARAMETER_TYPES = Arrays.asList(PARAMETER_TYPE_JSON, PARAMETER_TYPE_YAML,
            PARAMETER_TYPE_STRING, PARAMETER_TYPE_LONG, PARAMETER_TYPE_URL, PARAMETER_TYPE_BOOL, PARAMETER_TYPE_ARRAY,
            PARAMETER_TYPE_MAP, PARAMETER_TYPE_BINARY);

    protected static final List<String> RESULT_PARAMS_LIST = Arrays.asList(NAME, DESCRIPTION, TYPE, SHORT_OPTION,
            LONG_OPTION, IS_OPTIONAL, DEFAULT_VALUE, IS_SECURED);

    protected static final List<String> RESULT_PARAMS_MANDATORY_LIST = Arrays.asList(NAME, DESCRIPTION, TYPE);

    protected static final List<String> HTTP_PARAMS_LIST = Arrays.asList(URI, METHOD, BODY, HEADERS, QUERIES);

    protected static final List<String> HTTP_PARAMS_MANDATORY_LIST = Arrays.asList(URI, METHOD, BODY, HEADERS, QUERIES);

    protected static final List<String> HTTP_MANDATORY_SECTIONS = Arrays.asList(REQUEST, SUCCESS_CODES);

    protected static final List<String> HTTP_SECTIONS = Arrays.asList(REQUEST, SUCCESS_CODES, RESULT_MAP,
            SAMPLE_RESPONSE);

    protected static final List<String> HTTP_REQUEST_MANDATORY_PARAMS = Arrays.asList(URI, METHOD);

    protected static final List<String> HTTP_REQUEST_PARAMS = Arrays.asList(URI, METHOD, BODY, HEADERS, QUERIES);

    protected static final List<String> BOOLEAN_VALUES = Arrays.asList(BOOLEAN_TRUE, BOOLEAN_FALSE);
    protected static final List<String> DIRECTIONS = Arrays.asList(DIRECTION_PORTRAIT, DIRECTION_LANDSCAPE);
    protected static final List<String> RESULT_SCOPES = Arrays.asList(RESULT_SCOPE_SHORT, RESULT_SCOPE_LONG);

    protected static final List<String> HTTP_METHODS = Arrays.asList(POST, GET, DELETE, PUT, HEAD);

    /**
     * Constructor.
     *
     * @param schemaFile
     *            schemafile
     * @throws OnapCommandInvalidSchema
     *             exception
     */
    public AbstractSchemaValidate(File schemaFile) throws OnapCommandInvalidSchema {
        loadYaml(schemaFile);
        loadDefaultYaml();
    }

    /**
     * Constructor.
     *
     * @param schemaFile
     *            resourceName
     * @throws OnapCommandInvalidSchema
     *             exception
     */
    public AbstractSchemaValidate(String schemaFile) throws OnapCommandInvalidSchema {

        try {
            Resource res = OnapCommandUtils.getExternalResource(schemaFile, EXTERNAL_SCHEMA_PATH_PATERN);
            InputStream inputStream;
            if (res == null) {
                inputStream = OnapCommandUtils.class.getClassLoader().getResourceAsStream(schemaFile);
            } else {
                inputStream = res.getInputStream();
            }

            if (inputStream != null) {
                loadYamlFromInputStream(schemaFile, inputStream);
            } else {
                throw new OnapCommandInvalidSchema(schemaFile, SCHEMA_FILE_NOT_EXIST);
            }

        } catch (IOException e) {
            throw new OnapCommandInvalidSchema(schemaFile, e);
        }
        loadDefaultYaml();
    }

    private final void loadYaml(File schemaFile) throws OnapCommandInvalidSchema {
        if (!schemaFile.isFile()) {
            throw new OnapCommandInvalidSchema(schemaFile.getName(), SCHEMA_FILE_NOT_EXIST);
        }
        String fileName = schemaFile.getName();

        if (!fileName.endsWith(".yaml")) {
            throw new OnapCommandInvalidSchema(fileName, SCHEMA_FILE_WRONG_EXTN);
        }

        try {
            InputStream inputStream = new FileInputStream(schemaFile);
            loadYamlFromInputStream(schemaFile.getName(), inputStream);
        } catch (FileNotFoundException e) {
            throw new OnapCommandInvalidSchema(fileName, e);
        }
    }

    @SuppressWarnings("unchecked")
    private final void loadYamlFromInputStream(String fileName, InputStream inputStream)
            throws OnapCommandInvalidSchema {
        try {
            yamlMap = (Map<String, Object>) new Yaml().load(inputStream);
        } catch (ParserException e) {
            throw new OnapCommandInvalidSchema(fileName, e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new OnapCommandInvalidSchema(fileName, e); // NOSONAR
                }
            }
        }

        if (yamlMap == null) {
            throw new OnapCommandInvalidSchema(fileName, SCHEMA_FILE_EMPTY);
        }
    }

    @SuppressWarnings("unchecked")
    private final void loadDefaultYaml() throws OnapCommandInvalidSchema {
        InputStream inputStream = AbstractSchemaValidate.class.getClassLoader()
                .getResourceAsStream(DEFAULT_SCHEMA_FILE_NAME);
        try {
            defaultYamlMap = (Map<String, Object>) new Yaml().load(inputStream);
        } catch (ParserException e) {
            throw new OnapCommandInvalidSchema(DEFAULT_SCHEMA_FILE_NAME, e);
        }

        if (defaultYamlMap == null) {
            throw new OnapCommandInvalidSchema(DEFAULT_SCHEMA_FILE_NAME, SCHEMA_FILE_EMPTY);
        }
    }

    /*
     * Validate method.
     *
     * @throws OnapCommandInvalidSchema exception
     */
    @Override
    public List<String> validate() throws OnapCommandInvalidSchema {

        SchemaType type;
        Set<String> mainSections = yamlMap.keySet();
        if (mainSections.containsAll(HTTP_SCHEMA_MANDATORY_LIST)) {
            type = SchemaType.HTTP;
        } else if (mainSections.containsAll(BASIC_SCHEMA_MANDATORY_LIST)) {
            type = SchemaType.BASIC;
        } else {
            schemaErrors.add(SchemaValidate.invalidSections(mainSections, HTTP_SCHEMA_MANDATORY_LIST,
                    BASIC_SCHEMA_MANDATORY_LIST));
            return schemaErrors;
        }

        if (type.equals(SchemaType.BASIC)) {
            validateTopLevelAttributes();
            validateInputParameters();
            validateResultParameters();
        } else {
            validateTopLevelAttributes();
            validateServiceAttributes();
            validateInputParameters();
            validateResultParameters();
            validateSpecificSchema(SchemaType.HTTP);
        }
        return schemaErrors;
    }

    private void validateResultAttributes(List<Map<String, Object>> resultAttributes) {
        Set<String> resultParamNames = new HashSet<>();
        for (Map<String, Object> attribute : resultAttributes) {

            // Validate mandatory parameters
            validateMandatoryParams(attribute, RESULT_PARAMS_LIST, RESULT_PARAMS_MANDATORY_LIST, ATTRIBUTES);

            String name = String.valueOf(attribute.get(NAME));

            if (resultParamNames.contains(name)) {
                schemaErrors.add(SchemaValidate.attributeNameExist(name, ATTRIBUTES));
            } else {
                resultParamNames.add(name);
            }

            // Validate specific parameters
            Object type = attribute.get(TYPE);
            String value = String.valueOf(type);
            if (!PARAMETER_TYPES.contains(value.toLowerCase())) {
                schemaErrors.add(SchemaValidate.invalidType(ATTRIBUTES, name, PARAMETER_TYPES));
            }

            Object scope = attribute.get(SCOPE);
            if (scope == null) {
                schemaErrors.add(SchemaValidate.attributeScopeEmpty(name));
            } else if (!RESULT_SCOPES.contains(scope)) {
                schemaErrors.add(SchemaValidate.invalidAttributeScope(name, RESULT_SCOPES));
            }

            Object isSecured = attribute.get(IS_SECURED);
            if (isSecured != null) {
                String value2 = String.valueOf(isSecured);
                if (!validateBoolean(value2)) {
                    schemaErrors.add(SchemaValidate.invalidBooleanValueMessage(ATTRIBUTES, IS_SECURED, value2));
                }
            }
        }

    }

    private void validateResultParameters() {
        @SuppressWarnings("unchecked")
        Map<String, Object> resultParams = (Map<String, Object>) yamlMap.get(RESULTS);

        if (resultParams == null || resultParams.isEmpty()) {
            return;
        }

        Object direction = resultParams.get(DIRECTION);

        if (direction != null && !DIRECTIONS.contains(direction)) {
            schemaErrors.add(SchemaValidate.invalidType(PARAMETERS, DIRECTION, DIRECTIONS));
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultAttributes = (List<Map<String, Object>>) resultParams.get(ATTRIBUTES);
        validateResultAttributes(resultAttributes);
    }

    /**
     * Get all default short options.
     *
     * @return set
     */
    protected Set<String> getDefaultShortOptions() {

        Set<String> set = new HashSet<>();

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> inputParams = (List<Map<String, Object>>) defaultYamlMap.get(PARAMETERS);
        for (Map<String, Object> parameter : inputParams) {
            Object name = parameter.get(SHORT_OPTION);
            if (name != null && !String.valueOf(name).isEmpty() && !"null".equals(name)) {
                set.add(String.valueOf(name));
            }
        }

        return set;
    }

    /**
     * Get all default long options.
     *
     * @return set
     */
    protected Set<String> getDefaultLongOptions() {

        Set<String> set = new HashSet<>();

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> inputParams = (List<Map<String, Object>>) defaultYamlMap.get(PARAMETERS);
        for (Map<String, Object> parameter : inputParams) {
            Object name = parameter.get(LONG_OPTION);
            if (name != null && !String.valueOf(name).isEmpty() && !"null".equals(name)) {
                set.add(String.valueOf(name));
            }
        }

        return set;
    }

    private void validateTopLevelAttributes() {
        validateMandatoryParams(yamlMap, TOP_LEVEL_PARAMS_LIST, TOP_LEVEL_MANDATORY_LIST, "root level");
    }

    private void validateServiceAttributes() {

        @SuppressWarnings("unchecked")
        Map<String, Object> serviceMap = (Map<String, Object>) yamlMap.get(SERVICE);

        if (serviceMap == null) {
            schemaErrors.add(SchemaValidate.emptySection(SERVICE));
            return;
        }

        validateMandatoryParams(serviceMap, SERVICE_PARAMS_LIST, SERVICE_PARAMS_MANDATORY_LIST, SERVICE);

        // Validate specific parameters

        if (serviceMap.containsKey(NO_AUTH)) {
            Object obj = serviceMap.get(NO_AUTH);
            if (obj == null) {
                schemaErrors.add(SchemaValidate.emptyValue(SERVICE, NO_AUTH));
            } else {
                String value = String.valueOf(obj);
                if (!validateBoolean(value)) {
                    schemaErrors.add(SchemaValidate.invalidBooleanValueMessage(SERVICE, NO_AUTH, value));
                }
            }
        }

    }

    private void validateInputParameters() {

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> inputParams = (List<Map<String, Object>>) yamlMap.get(PARAMETERS);
        if (inputParams == null) {
            return;
        }
        validateInputAttributes(inputParams);
    }

    protected abstract void validateSpecificSchema(SchemaType type) throws OnapCommandInvalidSchema;

    private void validateInputAttributes(List<Map<String, Object>> inputParams) {
        Set<String> inputParamNames = new HashSet<>();
        Set<String> inputShortOptions = new HashSet<>();
        Set<String> inputLongOptions = new HashSet<>();

        Set<String> defaultShortOptions = getDefaultShortOptions();
        Set<String> defaultLongOptions = getDefaultLongOptions();

        for (Map<String, Object> parameter : inputParams) {

            // Validate mandatory parameters
            validateMandatoryParams(parameter, INPUT_PARAMS_LIST, INPUT_PARAMS_MANDATORY_LIST, PARAMETERS);

            // Validate specific parameters

            String name = String.valueOf(parameter.get(NAME));

            if (inputParamNames.contains(name)) {
                schemaErrors.add(SchemaValidate.nameExist(name, PARAMETERS));
            } else {
                inputParamNames.add(name);
            }

            String value = String.valueOf(parameter.get(TYPE));

            if (!PARAMETER_TYPES.contains(value.toLowerCase())) {
                schemaErrors.add(SchemaValidate.invalidAttrType(name, PARAMETERS, PARAMETER_TYPES));
            }

            Object isOptional = parameter.get(IS_OPTIONAL);
            if (isOptional != null) {
                String value1 = String.valueOf(isOptional);
                if (!validateBoolean(value1)) {
                    schemaErrors.add(SchemaValidate.invalidBooleanValueMessage(name, IS_OPTIONAL, value1));
                }
            }

            Object isSecured = parameter.get(IS_SECURED);
            if (isSecured != null) {
                String value2 = String.valueOf(isSecured);
                if (!validateBoolean(value2)) {
                    schemaErrors.add(SchemaValidate.invalidBooleanValueMessage(name, IS_SECURED, value2));
                }
            }

            String shortOption = String.valueOf(parameter.get(SHORT_OPTION));
            String longOption = String.valueOf(parameter.get(LONG_OPTION));

            if (inputShortOptions.contains(shortOption)) {
                schemaErrors.add(SchemaValidate.optionExist(SHORT_OPTION, shortOption, name));
            } else if (defaultShortOptions.contains(shortOption)) {

                schemaErrors
                        .add(SchemaValidate.optionDefaultExist(SHORT_OPTION, shortOption, name, defaultShortOptions));

            } else if (shortOption != null && !shortOption.isEmpty() && !"null".equals(shortOption)) {
                inputShortOptions.add(shortOption);
            }

            if (inputLongOptions.contains(longOption)) {
                schemaErrors.add(SchemaValidate.optionExist(LONG_OPTION, longOption, name));
            } else if (defaultLongOptions.contains(longOption)) {

                schemaErrors.add(SchemaValidate.optionDefaultExist(LONG_OPTION, longOption, name, defaultLongOptions));
            } else if (longOption != null && !longOption.isEmpty() && !"null".equals(shortOption)) {
                inputLongOptions.add(longOption);
            }

        }

    }

    /**
     * Validate mandatory parameters.
     *
     * @param yamlMap
     *            yaml map
     * @param totalParams
     *            list
     * @param mandatoryParams
     *            list
     * @param section
     *            section
     */
    protected void validateMandatoryParams(Map<String, Object> yamlMap, List<String> totalParams,
            List<String> mandatoryParams, String section) {

        for (String param : totalParams) {
            boolean isMandatory = mandatoryParams.contains(param);
            boolean isYamlContains = yamlMap.containsKey(param);
            if (isMandatory) {
                if (!isYamlContains) {
                    schemaErrors.add(SchemaValidate.mandatoryAttrMissing(param, section));
                } else {
                    String value = String.valueOf(yamlMap.get(param));
                    if (value == null || "".equals(value) || "null".equals(value)) {
                        schemaErrors.add(SchemaValidate.mandatoryAttrEmpty(param, section));
                    }
                }
            }
        }
    }

    /**
     * Load result attributes.
     *
     * @return set
     */
    @SuppressWarnings("unchecked")
    protected Set<String> getResultAttributes() {

        Set<String> set = new HashSet<>();

        List<Map<String, Object>> resultAttributes = yamlMap.get(RESULTS) != null
                ? (List<Map<String, Object>>) ((Map<String, Object>) yamlMap.get(RESULTS)).get(ATTRIBUTES)
                : Collections.emptyList();

        if (resultAttributes != null) {
            for (Map<String, Object> map : resultAttributes) {
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
     * Get request parameters.
     *
     * @return set
     */
    protected Set<String> getRequestParams() {

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
     * Validate Boolean.
     *
     * @param toValidate
     *            string
     * @return boolean
     */
    protected static boolean validateBoolean(String toValidate) {
        return BOOLEAN_VALUES.contains(toValidate.toLowerCase());
    }
}
