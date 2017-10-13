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

import static org.onap.cli.fw.conf.Constants.ATTRIBUTES;
import static org.onap.cli.fw.conf.Constants.AUTH;
import static org.onap.cli.fw.conf.Constants.AUTH_VALUES;
import static org.onap.cli.fw.conf.Constants.BODY;
import static org.onap.cli.fw.conf.Constants.COMMAND_TYPE_VALUES;
import static org.onap.cli.fw.conf.Constants.DEAFULT_PARAMETER_PASSWORD;
import static org.onap.cli.fw.conf.Constants.DEAFULT_PARAMETER_USERNAME;
import static org.onap.cli.fw.conf.Constants.DEFAULT_PARAMETER_FILE_NAME;
import static org.onap.cli.fw.conf.Constants.DEFAULT_PARAMETER_HTTP_FILE_NAME;
import static org.onap.cli.fw.conf.Constants.DEFAULT_PARAMETER_NO_AUTH;
import static org.onap.cli.fw.conf.Constants.DEFAULT_VALUE;
import static org.onap.cli.fw.conf.Constants.DESCRIPTION;
import static org.onap.cli.fw.conf.Constants.DIRECTION;
import static org.onap.cli.fw.conf.Constants.HEADERS;
import static org.onap.cli.fw.conf.Constants.HTTP;
import static org.onap.cli.fw.conf.Constants.HTTP_MANDATORY_SECTIONS;
import static org.onap.cli.fw.conf.Constants.HTTP_SECTIONS;
import static org.onap.cli.fw.conf.Constants.INFO;
import static org.onap.cli.fw.conf.Constants.INFO_AUTHOR;
import static org.onap.cli.fw.conf.Constants.INFO_PARAMS_LIST;
import static org.onap.cli.fw.conf.Constants.INFO_PARAMS_MANDATORY_LIST;
import static org.onap.cli.fw.conf.Constants.INFO_PRODUCT;
import static org.onap.cli.fw.conf.Constants.INFO_SERVICE;
import static org.onap.cli.fw.conf.Constants.INFO_TYPE;
import static org.onap.cli.fw.conf.Constants.INPUT_PARAMS_LIST;
import static org.onap.cli.fw.conf.Constants.INPUT_PARAMS_MANDATORY_LIST;
import static org.onap.cli.fw.conf.Constants.IS_INCLUDE;
import static org.onap.cli.fw.conf.Constants.IS_OPTIONAL;
import static org.onap.cli.fw.conf.Constants.IS_SECURED;
import static org.onap.cli.fw.conf.Constants.LONG_OPTION;
import static org.onap.cli.fw.conf.Constants.METHOD_TYPE;
import static org.onap.cli.fw.conf.Constants.MODE;
import static org.onap.cli.fw.conf.Constants.MODE_VALUES;
import static org.onap.cli.fw.conf.Constants.MULTIPART_ENTITY_NAME;
import static org.onap.cli.fw.conf.Constants.NAME;
import static org.onap.cli.fw.conf.Constants.OPEN_CLI_SCHEMA_VERSION;
import static org.onap.cli.fw.conf.Constants.PARAMETERS;
import static org.onap.cli.fw.conf.Constants.QUERIES;
import static org.onap.cli.fw.conf.Constants.REQUEST;
import static org.onap.cli.fw.conf.Constants.RESULTS;
import static org.onap.cli.fw.conf.Constants.RESULT_MAP;
import static org.onap.cli.fw.conf.Constants.RESULT_PARAMS_LIST;
import static org.onap.cli.fw.conf.Constants.RESULT_PARAMS_MANDATORY_LIST;
import static org.onap.cli.fw.conf.Constants.SAMPLE_RESPONSE;
import static org.onap.cli.fw.conf.Constants.SCHEMA_FILE_NOT_EXIST;
import static org.onap.cli.fw.conf.Constants.SCHEMA_FILE_WRONG_EXTN;
import static org.onap.cli.fw.conf.Constants.SCHEMA_PATH_PATERN;
import static org.onap.cli.fw.conf.Constants.SCOPE;
import static org.onap.cli.fw.conf.Constants.SERVICE;
import static org.onap.cli.fw.conf.Constants.SERVICE_PARAMS_LIST;
import static org.onap.cli.fw.conf.Constants.SERVICE_PARAMS_MANDATORY_LIST;
import static org.onap.cli.fw.conf.Constants.SHORT_OPTION;
import static org.onap.cli.fw.conf.Constants.SUCCESS_CODES;
import static org.onap.cli.fw.conf.Constants.TOP_LEVEL_MANDATORY_LIST;
import static org.onap.cli.fw.conf.Constants.TOP_LEVEL_PARAMS_LIST;
import static org.onap.cli.fw.conf.Constants.TYPE;
import static org.onap.cli.fw.conf.Constants.URI;
import static org.onap.cli.fw.conf.Constants.VERSION;

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

import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.ad.OnapService;
import org.onap.cli.fw.cmd.CommandType;
import org.onap.cli.fw.cmd.OnapHttpCommand;
import org.onap.cli.fw.conf.OnapCommandConfg;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.error.OnapCommandInvalidSchemaVersion;
import org.onap.cli.fw.error.OnapCommandParameterNameConflict;
import org.onap.cli.fw.error.OnapCommandParameterOptionConflict;
import org.onap.cli.fw.error.OnapCommandSchemaNotFound;
import org.onap.cli.fw.info.OnapCommandInfo;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.ParameterType;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.output.OnapCommandResultAttributeScope;
import org.onap.cli.fw.output.PrintDirection;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

public class OnapCommandSchemaLoaderUtils {

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
            Resource resource = OnapCommandDiscoveryUtils.findResource(schemaName, SCHEMA_PATH_PATERN);

            if (resource != null) {
                inputStream = resource.getInputStream();
            }

        } catch (IOException e) {
            throw new OnapCommandSchemaNotFound(schemaName, e);
        }
        if (inputStream == null) {
            inputStream = OnapCommandSchemaLoaderUtils.loadSchemaFromFile(schemaName);
        }

        Map<String, ?> values = null;
        try {
            values = (Map<String, ?>) new Yaml().load(inputStream);
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(schemaName, e);
        }
        String schemaVersion = "";
        if (values.keySet().contains(OPEN_CLI_SCHEMA_VERSION)) {
            Object obj = values.get(OPEN_CLI_SCHEMA_VERSION);
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
     * @param validateSchema flag to represent validation
     * @throws OnapCommandException  on error
     */
    public static List<String> loadSchema(OnapCommand cmd, String schemaName, boolean includeDefault,
                                          boolean validateSchema) throws OnapCommandException {
        try {
            List<String> errors = new ArrayList<>();
            if (includeDefault) {
                Map<String, ?> defaultParameterMap = includeDefault ?
                        validateSchemaVersion(DEFAULT_PARAMETER_FILE_NAME, cmd.getSchemaVersion()) : new HashMap<>();
                errors.addAll(OnapCommandSchemaLoaderUtils.parseSchema(cmd, defaultParameterMap, validateSchema));
            }

            Map<String, List<Map<String, String>>> commandYamlMap =
                    (Map<String, List<Map<String, String>>>)validateSchemaVersion(schemaName, cmd.getSchemaVersion());

            errors.addAll(OnapCommandSchemaLoaderUtils.parseSchema(cmd, commandYamlMap, validateSchema));

            return errors;
        } catch (OnapCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(schemaName, e);
        }
    }

    public static List<String> loadHttpSchema(OnapHttpCommand cmd, String schemaName, boolean includeDefault,
                                          boolean validateSchema) throws OnapCommandException {
        try {
            List<String> errors = new ArrayList<>();
            if (includeDefault) {
                Map<String, ?> defaultParameterMap = includeDefault ?
                        validateSchemaVersion(DEFAULT_PARAMETER_HTTP_FILE_NAME, cmd.getSchemaVersion()) : new HashMap<>();
                errors.addAll(OnapCommandSchemaLoaderUtils.parseSchema(cmd, defaultParameterMap, validateSchema));
            }

            Map<String, List<Map<String, String>>> commandYamlMap =
                    (Map<String, List<Map<String, String>>>)validateSchemaVersion(schemaName, cmd.getSchemaVersion());

            errors.addAll(OnapCommandSchemaLoaderUtils.parseHttpSchema(cmd, commandYamlMap, validateSchema));

            return errors;

        } catch (OnapCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(schemaName, e);
        }
    }

    static List<String> parseSchema(OnapCommand cmd,
                                            final Map<String, ?> values,
                                            boolean validate) throws OnapCommandException {

        List<String> exceptionList = new ArrayList<>();
        List<String> shortOptions = new ArrayList<>();
        List<String> longOptions = new ArrayList<>();

        if (validate) {
            OnapCommandUtils.validateTags(exceptionList, (Map<String, Object>) values, OnapCommandConfg.getSchemaAttrInfo(TOP_LEVEL_PARAMS_LIST),
                    OnapCommandConfg.getSchemaAttrInfo(TOP_LEVEL_MANDATORY_LIST), "root level");
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
                                    OnapCommandConfg.getSchemaAttrInfo(INFO_PARAMS_LIST),
                                    OnapCommandConfg.getSchemaAttrInfo(INFO_PARAMS_MANDATORY_LIST), INFO);

                            HashMap<String, String> validationMap = new HashMap<>();
                            validationMap.put(INFO_TYPE, COMMAND_TYPE_VALUES);

                            for (String secKey : validationMap.keySet()) {
                                if (infoMap.containsKey(secKey)) {
                                    Object obj = infoMap.get(secKey);
                                    if (obj == null) {
                                        exceptionList.add("Attribute '" + secKey + "' under '" + INFO + "' is empty");
                                    } else {
                                        String value = String.valueOf(obj);
                                        if (!OnapCommandConfg.getSchemaAttrInfo(validationMap.get(secKey)).contains(value)) {
                                            exceptionList.add("Attribute '" + secKey + "' contains invalid value. Valide values are "
                                                    + OnapCommandConfg.getSchemaAttrInfo(validationMap.get(key))); //
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
                                    info.setService(infoMap.get(key1).toString());
                                    break;

                                case INFO_TYPE:
                                    Object obj = infoMap.get(key1);
                                    info.setCommandType(CommandType.get(obj.toString()));
                                    break;

                                case INFO_AUTHOR:
                                    Object mode = infoMap.get(key1);
                                    info.setAuthor(mode.toString());
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

                        //To support overriding of the parameters, if command is already
                        //having the same named parameters, means same parameter is
                        //Overridden from included template into current template
                        Set<String> existingParamNames =  cmd.getParametersMap().keySet();

                        for (Map<String, String> parameter : parameters) {
                            boolean isOverriding = false;
                            OnapCommandParameter param = new OnapCommandParameter();

                            //Override the parameters from its base such as default parameters list
                            if (existingParamNames.contains(parameter.getOrDefault(NAME, ""))) {
                                param = cmd.getParametersMap().get(parameter.getOrDefault(NAME, ""));
                                isOverriding = true;
                            }

                            if (validate) {
                                OnapCommandUtils.validateTags(exceptionList, parameter, OnapCommandConfg.getSchemaAttrInfo(INPUT_PARAMS_LIST),
                                        OnapCommandConfg.getSchemaAttrInfo(INPUT_PARAMS_MANDATORY_LIST), PARAMETERS);
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
                                            OnapCommandUtils.throwOrCollect(new OnapCommandParameterOptionConflict(parameter.get(key2)), exceptionList, validate);
                                        }
                                        shortOptions.add(parameter.get(key2));
                                        param.setShortOption(parameter.get(key2));
                                        break;

                                    case LONG_OPTION:
                                        if (longOptions.contains(parameter.get(key2))) {
                                            OnapCommandUtils.throwOrCollect(new OnapCommandParameterOptionConflict(parameter.get(key2)), exceptionList, validate);
                                        }
                                        longOptions.add(parameter.get(key2));
                                        param.setLongOption(parameter.get(key2));
                                        break;

                                    case DEFAULT_VALUE:
                                        Object obj = parameter.get(key2);
                                        param.setDefaultValue(obj.toString());
                                        break;

                                    case TYPE:
                                        try {
                                            param.setParameterType(ParameterType.get(parameter.get(key2)));
                                        } catch (OnapCommandException ex) {
                                            OnapCommandUtils.throwOrCollect(ex, exceptionList, validate);
                                        }
                                        break;

                                    case IS_OPTIONAL:
                                        if (validate) {
                                            if (!OnapCommandUtils.validateBoolean(String.valueOf(parameter.get(key2)))) {
                                                exceptionList.add(OnapCommandUtils.invalidBooleanValueMessage(parameter.get(NAME),
                                                        IS_SECURED, parameter.get(key2)));
                                            }
                                        }
                                        if ("true".equalsIgnoreCase(String.valueOf(parameter.get(key2)))) {
                                            param.setOptional(true);
                                        } else {
                                            param.setOptional(false);
                                        }
                                        break;

                                    case IS_SECURED:
                                        if (validate) {
                                            if (!OnapCommandUtils.validateBoolean(String.valueOf(parameter.get(key2)))) {
                                                exceptionList.add(OnapCommandUtils.invalidBooleanValueMessage(parameter.get(NAME),
                                                        IS_SECURED, parameter.get(key2)));
                                            }
                                        }

                                        if ("true".equalsIgnoreCase(String.valueOf(parameter.get(key2)))) {
                                            param.setSecured(true);
                                        } else {
                                            param.setSecured(false);
                                        }
                                        break;

                                    case IS_INCLUDE:
                                        if (validate) {
                                            if (!OnapCommandUtils.validateBoolean(String.valueOf(parameter.get(key2)))) {
                                                exceptionList.add(OnapCommandUtils.invalidBooleanValueMessage(parameter.get(NAME),
                                                        IS_INCLUDE, parameter.get(key2)));
                                            }
                                        }

                                        if ("true".equalsIgnoreCase(String.valueOf(parameter.get(key2)))) {
                                            param.setInclude(true);
                                        } else {
                                            param.setInclude(false);
                                        }
                                        break;
                                }
                            }

                            if ( !isOverriding) {
                                cmd.getParameters().add(param);
                            } else {
                                cmd.getParametersMap().replace(param.getName(), param);
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

                            switch (key3) {
                                case DIRECTION:
                                    try {
                                        result.setPrintDirection(PrintDirection.get((String) valueMap.get(key3)));
                                    } catch (OnapCommandException ex) {
                                        OnapCommandUtils.throwOrCollect(ex, exceptionList, validate);
                                    }
                                    break;

                                case ATTRIBUTES:
                                    List<Map<String, String>> attrs = (ArrayList) valueMap.get(key3);

                                    for (Map<String, String> map : attrs) {
                                        OnapCommandResultAttribute attr = new OnapCommandResultAttribute();
                                        if (validate) {
                                            OnapCommandUtils.validateTags(exceptionList, map, OnapCommandConfg.getSchemaAttrInfo(RESULT_PARAMS_LIST),
                                                    OnapCommandConfg.getSchemaAttrInfo(RESULT_PARAMS_MANDATORY_LIST), ATTRIBUTES);
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
                                                        attr.setType(ParameterType.get(map.get(key4)));
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
                                                                    IS_SECURED, map.get(key4)));
                                                        }
                                                    }
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
        return exceptionList;
    }

    /**
     * Load the schema.
     *
     * @param cmd
     *            OnapHttpCommand
     * @param schemaName
     *            schema name
     * @throws OnapCommandException
     *             on error
     */
    static ArrayList<String> parseHttpSchema(OnapHttpCommand cmd,
                                                    final Map<String, ?> values,
                                                    boolean validate) throws OnapCommandException {
        ArrayList<String> errorList = new ArrayList<>();
        try {
            Map<String, ?> valMap = (Map<String, ?>) values.get(HTTP);

            if (valMap != null) {
                if (validate) {
                    OnapCommandUtils.validateTags(errorList, valMap, OnapCommandConfg.getSchemaAttrInfo(HTTP_SECTIONS),
                            OnapCommandConfg.getSchemaAttrInfo(HTTP_MANDATORY_SECTIONS), PARAMETERS);
                    errorList.addAll(OnapCommandUtils.validateHttpSchemaSection(values));
                }
                for (Map.Entry<String, ?> entry1 : valMap.entrySet()) {
                    String key1 = entry1.getKey();

                    switch (key1) {
                        case REQUEST:
                            Map<String, ?> map = (Map<String, ?>) valMap.get(key1);

                            for (Map.Entry<String, ?> entry2 : map.entrySet()) {
                                try {
                                    String key2 = entry2.getKey();

                                    switch (key2) {
                                        case URI:
                                            Object obj = map.get(key2);
                                            cmd.getInput().setUri(obj.toString());
                                            break;
                                        case METHOD_TYPE:
                                            Object method = map.get(key2);
                                            cmd.getInput().setMethod(method.toString());
                                            break;
                                        case BODY:
                                            Object body = map.get(key2);
                                            cmd.getInput().setBody(body.toString());
                                            break;
                                        case HEADERS:
                                            Map<String, String> head = (Map<String, String>) map.get(key2);
                                            cmd.getInput().setReqHeaders(head);
                                            break;
                                        case QUERIES:
                                            Map<String, String> query = (Map<String, String>) map.get(key2);

                                            cmd.getInput().setReqQueries(query);
                                            break;
                                        case MULTIPART_ENTITY_NAME:
                                            Object multipartEntityName = map.get(key2);
                                            cmd.getInput().setMultipartEntityName(multipartEntityName.toString());
                                            break;
                                    }
                                }catch (Exception ex) {
                                    OnapCommandUtils.throwOrCollect(new OnapCommandInvalidSchema(cmd.getSchemaName(), ex), errorList, validate);
                                }
                            }
                            break;

                        case SERVICE:
                            Map<String, String> serviceMap = (Map<String, String>) valMap.get(key1);

                            if (serviceMap != null) {
                                if (validate) {
                                    OnapCommandUtils.validateTags(errorList, (Map<String, Object>) valMap.get(key1),
                                            OnapCommandConfg.getSchemaAttrInfo(SERVICE_PARAMS_LIST),
                                            OnapCommandConfg.getSchemaAttrInfo(SERVICE_PARAMS_MANDATORY_LIST), SERVICE);

                                    HashMap<String, String> validationMap = new HashMap<>();
                                    validationMap.put(AUTH, AUTH_VALUES);
                                    validationMap.put(MODE, MODE_VALUES);

                                    for (String secKey : validationMap.keySet()) {
                                        if (serviceMap.containsKey(secKey)) {
                                            Object obj = serviceMap.get(secKey);
                                            if (obj == null) {
                                                errorList.add("Attribute '" + secKey + "' under '" + SERVICE + "' is empty");
                                            } else {
                                                String value = String.valueOf(obj);
                                                if (!OnapCommandConfg.getSchemaAttrInfo(validationMap.get(secKey)).contains(value)) {
                                                    errorList.add("Attribute '" + secKey + "' contains invalid value. Valide values are "
                                                            + OnapCommandConfg.getSchemaAttrInfo(validationMap.get(key1))); //
                                                }
                                            }
                                        }
                                    }
                                }

                                OnapService srv = new OnapService();

                                for (Map.Entry<String, String> entry : serviceMap.entrySet()) {
                                    String key = entry.getKey();

                                    switch (key) {
                                        case NAME:
                                            srv.setName(serviceMap.get(key));
                                            break;

                                        case VERSION:
                                            srv.setVersion(serviceMap.get(key).toString());
                                            break;

                                        case AUTH:
                                            Object obj = serviceMap.get(key);
                                            srv.setAuthType(obj.toString());

                                            //On None type, username, password and no_auth are invalid
                                            if (srv.isNoAuth()) {
                                                cmd.getParametersMap().get(DEAFULT_PARAMETER_USERNAME).setInclude(false);
                                                cmd.getParametersMap().get(DEAFULT_PARAMETER_PASSWORD).setInclude(false);
                                                cmd.getParametersMap().get(DEFAULT_PARAMETER_NO_AUTH).setInclude(false);
                                            }
                                            break;

                                        case MODE:
                                            Object mode = serviceMap.get(key);
                                            srv.setMode(mode.toString());
                                            break;
                                    }
                                }
                                cmd.setService(srv);
                            }
                            break;

                        case SUCCESS_CODES:
                            if (validate) {
                                OnapCommandUtils.validateHttpSccessCodes(errorList, (List<Object>) valMap.get(key1));
                            }
                            cmd.setSuccessStatusCodes((ArrayList) valMap.get(key1));
                            break;

                        case RESULT_MAP:
                            if (validate) {
                                OnapCommandUtils.validateHttpResultMap(errorList, values);
                            }
                            cmd.setResultMap((Map<String, String>) valMap.get(key1));
                            break;

                        case SAMPLE_RESPONSE:
                            // (mrkanag) implement sample response handling
                            break;
                    }
                }
            }
        }catch (OnapCommandException e) {
            OnapCommandUtils.throwOrCollect(e, errorList, validate);
        }
        return errorList;
    }

    static InputStream loadSchemaFromFile(String schemaLocation) throws OnapCommandInvalidSchema {
        File schemaFile = new File(schemaLocation);
        try {
            FileInputStream inputFileStream = new FileInputStream(schemaFile);
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

}
