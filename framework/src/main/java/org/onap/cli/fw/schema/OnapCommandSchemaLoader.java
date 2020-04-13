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

import static org.onap.cli.fw.conf.OnapCommandConstants.ATTRIBUTES;
import static org.onap.cli.fw.conf.OnapCommandConstants.BOOLEAN_TRUE;
import static org.onap.cli.fw.conf.OnapCommandConstants.COMMAND_TYPE_VALUES;
import static org.onap.cli.fw.conf.OnapCommandConstants.DEFAULT_PARAMETER_FILE_NAME;
import static org.onap.cli.fw.conf.OnapCommandConstants.DEFAULT_VALUE;
import static org.onap.cli.fw.conf.OnapCommandConstants.DESCRIPTION;
import static org.onap.cli.fw.conf.OnapCommandConstants.DIRECTION;
import static org.onap.cli.fw.conf.OnapCommandConstants.INFO;
import static org.onap.cli.fw.conf.OnapCommandConstants.INFO_AUTHOR;
import static org.onap.cli.fw.conf.OnapCommandConstants.INFO_IGNORE;
import static org.onap.cli.fw.conf.OnapCommandConstants.INFO_PARAMS_LIST;
import static org.onap.cli.fw.conf.OnapCommandConstants.INFO_PARAMS_MANDATORY_LIST;
import static org.onap.cli.fw.conf.OnapCommandConstants.INFO_PRODUCT;
import static org.onap.cli.fw.conf.OnapCommandConstants.INFO_SERVICE;
import static org.onap.cli.fw.conf.OnapCommandConstants.INFO_STATE;
import static org.onap.cli.fw.conf.OnapCommandConstants.INFO_TYPE;
import static org.onap.cli.fw.conf.OnapCommandConstants.INPUT_PARAMS_LIST;
import static org.onap.cli.fw.conf.OnapCommandConstants.INPUT_PARAMS_MANDATORY_LIST;
import static org.onap.cli.fw.conf.OnapCommandConstants.IS_DEFAULT_ATTR;
import static org.onap.cli.fw.conf.OnapCommandConstants.IS_DEFAULT_PARAM;
import static org.onap.cli.fw.conf.OnapCommandConstants.IS_INCLUDE;
import static org.onap.cli.fw.conf.OnapCommandConstants.IS_OPTIONAL;
import static org.onap.cli.fw.conf.OnapCommandConstants.IS_SECURED;
import static org.onap.cli.fw.conf.OnapCommandConstants.LONG_OPTION;
import static org.onap.cli.fw.conf.OnapCommandConstants.NAME;
import static org.onap.cli.fw.conf.OnapCommandConstants.OPEN_CLI_SCHEMA_VERSION;
import static org.onap.cli.fw.conf.OnapCommandConstants.PARAMETERS;
import static org.onap.cli.fw.conf.OnapCommandConstants.RESULTS;
import static org.onap.cli.fw.conf.OnapCommandConstants.RESULT_PARAMS_LIST;
import static org.onap.cli.fw.conf.OnapCommandConstants.RESULT_PARAMS_MANDATORY_LIST;
import static org.onap.cli.fw.conf.OnapCommandConstants.SCHEMA_FILE_NOT_EXIST;
import static org.onap.cli.fw.conf.OnapCommandConstants.SCHEMA_FILE_WRONG_EXTN;
import static org.onap.cli.fw.conf.OnapCommandConstants.SCHEMA_PATH_PATERN;
import static org.onap.cli.fw.conf.OnapCommandConstants.SCOPE;
import static org.onap.cli.fw.conf.OnapCommandConstants.SHORT_OPTION;
import static org.onap.cli.fw.conf.OnapCommandConstants.TOP_LEVEL_MANDATORY_LIST;
import static org.onap.cli.fw.conf.OnapCommandConstants.TOP_LEVEL_PARAMS_LIST;
import static org.onap.cli.fw.conf.OnapCommandConstants.TYPE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.cmd.OnapCommandType;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.error.OnapCommandInvalidSchemaVersion;
import org.onap.cli.fw.error.OnapCommandParameterNameConflict;
import org.onap.cli.fw.error.OnapCommandParameterOptionConflict;
import org.onap.cli.fw.error.OnapCommandSchemaNotFound;
import org.onap.cli.fw.info.OnapCommandInfo;
import org.onap.cli.fw.info.OnapCommandState;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.OnapCommandParameterType;
import org.onap.cli.fw.output.OnapCommandPrintDirection;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.output.OnapCommandResultAttributeScope;
import org.onap.cli.fw.utils.OnapCommandDiscoveryUtils;
import org.onap.cli.fw.utils.OnapCommandUtils;
import org.springframework.core.io.Resource;
import java.io.InputStreamReader;
import com.esotericsoftware.yamlbeans.YamlReader;

public class OnapCommandSchemaLoader {

    private OnapCommandSchemaLoader() {
        // As per the java guidelines
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
        Map<String, ?> values = null;
        try {
            InputStream inputStream = OnapCommandUtils.class.getClassLoader().getResourceAsStream(schemaName);

            Resource resource = OnapCommandDiscoveryUtils.findResource(schemaName, SCHEMA_PATH_PATERN);

            if (resource != null) {
                inputStream = resource.getInputStream();
            }

            if (inputStream == null) {
                inputStream = loadSchemaFromFile(schemaName);
            }

            values = loadSchema(inputStream, schemaName);
            String schemaVersion = "";
            if (values.keySet().contains(OPEN_CLI_SCHEMA_VERSION)) {
                Object obj = values.get(OPEN_CLI_SCHEMA_VERSION);
                schemaVersion = obj.toString();
            }

            if (!version.equals(schemaVersion)) {
                throw new OnapCommandInvalidSchemaVersion(schemaVersion);
            }
            inputStream.close();
        } catch (IOException e) {
            throw new OnapCommandSchemaNotFound(schemaName, e);
        }
        return values;
    }

    /**
     * Retrieve OnapCommand from schema.
     *
     * @param cmd            OnapCommand
     * @param schemaName     schema name
     * @param includeDefault include if default
     * @param validateSchema flag to represent validation
     * @throws OnapCommandException  on error
     */
    public static List<String> loadSchema(OnapCommand cmd, String schemaName, boolean includeDefault,
                                          boolean validateSchema) throws OnapCommandException {
        try {
            List<String> errors = new ArrayList<>();
            if (includeDefault) {
                Map<String, ?> defaultParameterMap = validateSchemaVersion(DEFAULT_PARAMETER_FILE_NAME, cmd.getSchemaVersion());
                //mrkanag default_parameter is supported only for parameters.
                if (defaultParameterMap.containsKey(INFO)) {
                    defaultParameterMap.remove(OnapCommandConstants.INFO);
                }

                errors.addAll(parseSchema(cmd, defaultParameterMap, validateSchema));
            }

            Map<String, List<Map<String, String>>> commandYamlMap =
                    (Map<String, List<Map<String, String>>>)validateSchemaVersion(schemaName, cmd.getSchemaVersion());

            errors.addAll(parseSchema(cmd, commandYamlMap, validateSchema));

            return errors;
        } catch (OnapCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(schemaName, e);
        }
    }

    public static List<String> parseSchema(OnapCommand cmd,
                                            final Map<String, ?> values,
                                            boolean validate) throws OnapCommandException {

        List<String> exceptionList = new ArrayList<>();
        List<String> shortOptions = new ArrayList<>();
        List<String> longOptions = new ArrayList<>();

        if (validate) {
            OnapCommandUtils.validateTags(exceptionList, (Map<String, Object>) values, OnapCommandConfig.getCommaSeparatedList(TOP_LEVEL_PARAMS_LIST),
                    OnapCommandConfig.getCommaSeparatedList(TOP_LEVEL_MANDATORY_LIST), "root level");
        }


        List<String> sections = Arrays.asList(NAME, DESCRIPTION, INFO, PARAMETERS, RESULTS);

        for (String key : sections) {

            switch (key) {
                case NAME:
                    Object val = values.get(key);
                    if (val != null) {
                        cmd.setName(val.toString());
                    }
                    break;

                case DESCRIPTION:
                    Object description = values.get(key);
                    if (description != null) {
                        cmd.setDescription(description.toString());
                    }
                    break;

                case INFO:
                    Map<String, String> infoMap = (Map<String, String>) values.get(key);

                    if (infoMap != null) {
                        if (validate) {
                            OnapCommandUtils.validateTags(exceptionList, (Map<String, Object>) values.get(key),
                                    OnapCommandConfig.getCommaSeparatedList(INFO_PARAMS_LIST),
                                    OnapCommandConfig.getCommaSeparatedList(INFO_PARAMS_MANDATORY_LIST), INFO);

                            HashMap<String, String> validationMap = new HashMap<>();
                            validationMap.put(INFO_TYPE, COMMAND_TYPE_VALUES);

                            for (String secKey : validationMap.keySet()) {
                                if (infoMap.containsKey(secKey)) {
                                    Object obj = infoMap.get(secKey);
                                    if (obj == null) {
                                        exceptionList.add("Attribute '" + secKey + "' under '" + INFO + "' is empty");
                                    } else {
                                        String value = String.valueOf(obj);
                                        if (!OnapCommandConfig.getCommaSeparatedList(validationMap.get(secKey)).contains(value)) {
                                            exceptionList.add("Attribute '" + secKey + "' contains invalid value. Valide values are "
                                                    + OnapCommandConfig.getCommaSeparatedList(validationMap.get(key))); //
                                        }
                                    }
                                }
                            }
                        }

                        OnapCommandInfo info = new OnapCommandInfo();

                        for (Map.Entry<String, String> entry1 : infoMap.entrySet()) {
                            String key1 = entry1.getKey();

                            switch (key1) {
                                case INFO_PRODUCT:
                                    info.setProduct(infoMap.get(key1));
                                    break;

                                case INFO_SERVICE:
                                    info.setService(infoMap.get(key1));
                                    break;

                                case INFO_TYPE:
                                    Object obj = infoMap.get(key1);
                                    info.setCommandType(OnapCommandType.get(obj.toString()));
                                    break;

                                case INFO_STATE:
                                    Object state = infoMap.get(key1);
                                    info.setState(OnapCommandState.get(state.toString()));
                                    break;

                                case INFO_AUTHOR:
                                    Object mode = infoMap.get(key1);
                                    info.setAuthor(mode.toString());
                                    break;

                                case INFO_IGNORE:
                                    Object ignore = infoMap.get(key1);
                                    info.setIgnore(ignore.toString().equalsIgnoreCase(OnapCommandConstants.BOOLEAN_TRUE));
                                    break;
                            }
                        }

                        cmd.setInfo(info);
                    }
                    break;

                case PARAMETERS:

                    List<Map<String, String>> parameters = (List) values.get(key);

                    if (parameters != null) {
                        Set<String> names = new HashSet<>();

                        for (Map<String, String> parameter : parameters) {
                            OnapCommandParameter param = new OnapCommandParameter();

                            if (validate) {
                                OnapCommandUtils.validateTags(exceptionList, parameter, OnapCommandConfig.getCommaSeparatedList(INPUT_PARAMS_LIST),
                                        OnapCommandConfig.getCommaSeparatedList(INPUT_PARAMS_MANDATORY_LIST), PARAMETERS);
                            }

                            for (Map.Entry<String, String> entry1 : parameter.entrySet()) {
                                String key2 = entry1.getKey();

                                switch (key2) {
                                    case NAME:
                                        if (names.contains(parameter.get(key2))) {
                                            OnapCommandUtils.throwOrCollect(new OnapCommandParameterNameConflict(parameter.get(key2)), exceptionList, validate);
                                        } else {
                                            names.add(parameter.get(key2));
                                        }

                                        param.setName(parameter.get(key2));
                                        break;

                                    case DESCRIPTION:
                                        param.setDescription(parameter.get(key2));
                                        break;

                                    case SHORT_OPTION:
                                        if (shortOptions.contains(parameter.get(key2))) {
                                            OnapCommandUtils.throwOrCollect(new OnapCommandParameterOptionConflict(
                                                    cmd.getSchemaName(),
                                                    parameter.get(key2)), exceptionList, validate);
                                        }
                                        shortOptions.add(parameter.get(key2));
                                        param.setShortOption(parameter.get(key2));
                                        break;

                                    case LONG_OPTION:
                                        if (longOptions.contains(parameter.get(key2))) {
                                            OnapCommandUtils.throwOrCollect(new OnapCommandParameterOptionConflict(
                                                    cmd.getSchemaName(),
                                                    parameter.get(key2)), exceptionList, validate);
                                        }
                                        longOptions.add(parameter.get(key2));
                                        param.setLongOption(parameter.get(key2));
                                        break;

                                    case DEFAULT_VALUE:
                                        Object obj = parameter.get(key2);
                                        param.setRawDefaultValue(obj.toString());
                                        break;

                                    case TYPE:
                                        try {
                                            param.setParameterType(OnapCommandParameterType.get(parameter.get(key2)));
                                        } catch (OnapCommandException ex) {
                                            OnapCommandUtils.throwOrCollect(ex, exceptionList, validate);
                                        }
                                        break;

                                    case IS_OPTIONAL:
                                        if (validate && !OnapCommandUtils.validateBoolean(String.valueOf(parameter.get(key2)))) {
                                            exceptionList.add(OnapCommandUtils.invalidBooleanValueMessage(parameter.get(NAME),
                                                    IS_OPTIONAL, String.valueOf(parameter.get(key2))));
                                        }

                                        if (BOOLEAN_TRUE.equalsIgnoreCase(String.valueOf(parameter.get(key2)))) {
                                            param.setOptional(true);
                                        } else {
                                            param.setOptional(false);
                                        }
                                        break;

                                    case IS_SECURED:
                                        if (validate) {
                                            if (!OnapCommandUtils.validateBoolean(String.valueOf(parameter.get(key2)))) {
                                                exceptionList.add(OnapCommandUtils.invalidBooleanValueMessage(parameter.get(NAME),
                                                        IS_SECURED, String.valueOf(parameter.get(key2))));
                                            }
                                        }

                                        if (BOOLEAN_TRUE.equalsIgnoreCase(String.valueOf(parameter.get(key2)))) {
                                            param.setSecured(true);
                                        } else {
                                            param.setSecured(false);
                                        }
                                        break;

                                    case IS_INCLUDE:
                                        if (validate && !OnapCommandUtils.validateBoolean(String.valueOf(parameter.get(key2)))) {
                                            exceptionList.add(OnapCommandUtils.invalidBooleanValueMessage(parameter.get(NAME),
                                                    IS_INCLUDE, String.valueOf(parameter.get(key2))));
                                        }

                                        if (BOOLEAN_TRUE.equalsIgnoreCase(String.valueOf(parameter.get(key2)))) {
                                            param.setInclude(true);
                                        } else {
                                            param.setInclude(false);
                                        }
                                        break;

                                    case IS_DEFAULT_PARAM:
                                        if (validate) {
                                            if (!OnapCommandUtils.validateBoolean(String.valueOf(parameter.get(key2)))) {
                                                exceptionList.add(OnapCommandUtils.invalidBooleanValueMessage(parameter.get(NAME),
                                                        IS_DEFAULT_PARAM, String.valueOf(parameter.get(key2))));
                                            }
                                        }

                                        if (BOOLEAN_TRUE.equalsIgnoreCase(String.valueOf(parameter.get(key2)))) {
                                            param.setDefaultParam(true);
                                        } else {
                                            param.setDefaultParam(false);
                                        }
                                        break;
                                }
                            }

                            cmd.getParameters().add(param);
                        }
                    }
                    break;

                case RESULTS:
                    Map<String, ?> valueMap = (Map<String, ?>) values.get(key);
                    if (valueMap != null) {
                        for (Map.Entry<String, ?> entry1 : valueMap.entrySet()) {
                            String key3 = entry1.getKey();

                            switch (key3) {
                                case DIRECTION:
                                    try {
                                        cmd.getResult().setPrintDirection(OnapCommandPrintDirection.get((String) valueMap.get(key3)));
                                    } catch (OnapCommandException ex) {
                                        OnapCommandUtils.throwOrCollect(ex, exceptionList, validate);
                                    }
                                    break;

                                case ATTRIBUTES:
                                    List<Map<String, String>> attrs = (ArrayList) valueMap.get(key3);

                                    for (Map<String, String> map : attrs) {
                                        OnapCommandResultAttribute attr = new OnapCommandResultAttribute();
                                        if (validate) {
                                            OnapCommandUtils.validateTags(exceptionList, map, OnapCommandConfig.getCommaSeparatedList(RESULT_PARAMS_LIST),
                                                    OnapCommandConfig.getCommaSeparatedList(RESULT_PARAMS_MANDATORY_LIST), ATTRIBUTES);
                                        }

                                        Set<String> resultParamNames = new HashSet<>();

                                        for (Map.Entry<String, String> entry4 : map.entrySet()) {
                                            String key4 = entry4.getKey();

                                            switch (key4) {
                                                case NAME:
                                                    if (resultParamNames.contains(map.get(key4))) {
                                                        exceptionList.add("Attribute name='" + map.get(key4) + "' under '"
                                                                + ATTRIBUTES + ":' is already used, Take different one.");

                                                    } else {
                                                        attr.setName(map.get(key4));
                                                        resultParamNames.add(map.get(key4));
                                                    }
                                                    break;

                                                case DESCRIPTION:
                                                    attr.setDescription(map.get(key4));
                                                    break;

                                                case SCOPE:
                                                    try {
                                                        attr.setScope(OnapCommandResultAttributeScope.get(map.get(key4)));
                                                    } catch (OnapCommandException ex) {
                                                        OnapCommandUtils.throwOrCollect(ex, exceptionList, validate);
                                                    }
                                                    break;

                                                case TYPE:
                                                    try {
                                                        attr.setType(OnapCommandParameterType.get(map.get(key4)));
                                                    } catch (OnapCommandException ex) {
                                                        OnapCommandUtils.throwOrCollect(ex, exceptionList, validate);
                                                    }
                                                    break;

                                                case DEFAULT_VALUE:
                                                    Object obj = map.get(key4);
                                                    attr.setDefaultValue(obj.toString());
                                                    break;

                                                case IS_SECURED:
                                                    if (validate) {
                                                        if (!OnapCommandUtils.validateBoolean(String.valueOf(map.get(key4)))) {
                                                            exceptionList.add(OnapCommandUtils.invalidBooleanValueMessage(ATTRIBUTES,
                                                                    IS_SECURED, String.valueOf(map.get(key4))));
                                                        }
                                                    }
                                                    if (BOOLEAN_TRUE.equals(String.valueOf(map.get(key4)))) {
                                                        attr.setSecured(true);
                                                    } else {
                                                        attr.setSecured(false);
                                                    }
                                                    break;

                                                case IS_DEFAULT_ATTR:
                                                    if (validate) {
                                                        if (!OnapCommandUtils.validateBoolean(String.valueOf(map.get(key4)))) {
                                                            exceptionList.add(OnapCommandUtils.invalidBooleanValueMessage(ATTRIBUTES,
                                                                    IS_DEFAULT_ATTR, String.valueOf(map.get(key4))));
                                                        }
                                                    }
                                                    if (BOOLEAN_TRUE.equals(String.valueOf(map.get(key4)))) {
                                                        attr.setDefaultAttr(true);
                                                    } else {
                                                        attr.setDefaultAttr(false);
                                                    }
                                                    break;
                                            }

                                        }
                                        cmd.getResult().getRecords().add(attr);
                                    }
                                    break;
                            }
                        }
                    }
                    break;
            }
        }

        return exceptionList;
    }

    public static InputStream loadSchemaFromFile(String schemaLocation) throws OnapCommandInvalidSchema {
        File schemaFile = new File(schemaLocation);
        try {
            FileInputStream inputFileStream = new FileInputStream(schemaFile);  // NOSONAR
            if (!schemaFile.isFile()) {
                throw new OnapCommandInvalidSchema(schemaFile.getName(), SCHEMA_FILE_NOT_EXIST);
            }

            if (!schemaFile.getName().endsWith(".yaml")) {
                throw new OnapCommandInvalidSchema(schemaFile.getName(), SCHEMA_FILE_WRONG_EXTN);
            }
            return inputFileStream;
        }catch (FileNotFoundException e) {
            throw new OnapCommandInvalidSchema(schemaFile.getName(), e);
        }
    }

    /**
     * Get schema map.
     *
     * @param stream
     * @param schemaName
     * @return map
     * @throws OnapCommandInvalidSchema
     *             exception
     */
    public static Map<String, ?> loadSchema(InputStream stream, String schemaName) throws OnapCommandInvalidSchema  {
        Map<String, ?> values = null;
        try (InputStreamReader inputStreamReader = new InputStreamReader(stream);){
            YamlReader reader = new YamlReader(inputStreamReader);
            values = (Map<String, ?>) reader.read();
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(schemaName, e);
        }

        return values;
    }
}
