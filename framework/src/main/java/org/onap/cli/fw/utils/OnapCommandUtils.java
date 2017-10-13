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
import static org.onap.cli.fw.conf.Constants.BODY;
import static org.onap.cli.fw.conf.Constants.BOOLEAN_VALUE;
import static org.onap.cli.fw.conf.Constants.DATA_DIRECTORY;
import static org.onap.cli.fw.conf.Constants.DATA_PATH_JSON_PATTERN;
import static org.onap.cli.fw.conf.Constants.DESCRIPTION;
import static org.onap.cli.fw.conf.Constants.DISCOVERY_FILE;
import static org.onap.cli.fw.conf.Constants.HEADERS;
import static org.onap.cli.fw.conf.Constants.HTTP;
import static org.onap.cli.fw.conf.Constants.HTTP_BODY_FAILED_PARSING;
import static org.onap.cli.fw.conf.Constants.HTTP_BODY_JSON_EMPTY;
import static org.onap.cli.fw.conf.Constants.HTTP_METHODS;
import static org.onap.cli.fw.conf.Constants.HTTP_REQUEST_MANDATORY_PARAMS;
import static org.onap.cli.fw.conf.Constants.HTTP_REQUEST_PARAMS;
import static org.onap.cli.fw.conf.Constants.HTTP_SUCCESS_CODE_INVALID;
import static org.onap.cli.fw.conf.Constants.METHOD;
import static org.onap.cli.fw.conf.Constants.NAME;
import static org.onap.cli.fw.conf.Constants.OPEN_CLI_SCHEMA_VERSION;
import static org.onap.cli.fw.conf.Constants.PARAMETERS;
import static org.onap.cli.fw.conf.Constants.QUERIES;
import static org.onap.cli.fw.conf.Constants.REQUEST;
import static org.onap.cli.fw.conf.Constants.RESULTS;
import static org.onap.cli.fw.conf.Constants.RESULT_MAP;
import static org.onap.cli.fw.conf.Constants.SCHEMA_DIRECTORY;
import static org.onap.cli.fw.conf.Constants.SCHEMA_FILE_NOT_EXIST;
import static org.onap.cli.fw.conf.Constants.SCHEMA_FILE_WRONG_EXTN;
import static org.onap.cli.fw.conf.Constants.SCHEMA_PATH_PATERN;
import static org.onap.cli.fw.conf.Constants.URI;

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
import java.util.UUID;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.cmd.OnapHttpCommand;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.conf.OnapCommandConfg;
import org.onap.cli.fw.error.OnapCommandDiscoveryFailed;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandHttpHeaderNotFound;
import org.onap.cli.fw.error.OnapCommandHttpInvalidResponseBody;
import org.onap.cli.fw.error.OnapCommandHttpInvalidResultMap;
import org.onap.cli.fw.error.OnapCommandInvalidParameterValue;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.error.OnapCommandLoadProfileFailed;
import org.onap.cli.fw.error.OnapCommandParameterNotFound;
import org.onap.cli.fw.error.OnapCommandPersistProfileFailed;
import org.onap.cli.fw.error.OnapCommandResultEmpty;
import org.onap.cli.fw.error.OnapCommandResultMapProcessingFailed;
import org.onap.cli.fw.http.HttpInput;
import org.onap.cli.fw.http.HttpResult;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.ParameterType;
import org.onap.cli.fw.input.cache.Param;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.output.OnapCommandResultAttributeScope;
import org.onap.cli.fw.output.PrintDirection;
import org.onap.cli.fw.output.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * Provides helper method to parse Yaml files and produce required objects.
 *
 */
public class OnapCommandUtils {

    private static Logger LOG = LoggerFactory.getLogger(OnapCommandUtils.class);
    /**
     * Private constructor.
     */
    private OnapCommandUtils() {

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

    static void throwOrCollect(OnapCommandException ex, List<String> list, boolean shouldCollectException)
            throws OnapCommandException {
        if (shouldCollectException) {
            list.add(ex.getMessage());
        } else {
            throw ex;
        }
    }

    static void validateTags(List<String> schemaErrors, Map<String, ?> yamlMap, List<String> totalParams,
            List<String> mandatoryParams, String section) {
        // mrkanag capture invalid entries as well
        for (String param : totalParams) {
            boolean isMandatory = mandatoryParams.contains(param);
            boolean isYamlContains = yamlMap.containsKey(param);
            if (isMandatory) {
                if (!isYamlContains) {
                    schemaErrors.add("Mandatory attribute '" + param + "' is missing under '" + section + "'");
                } else {
                    String value = String.valueOf(yamlMap.get(param));
                    if (value == null || value.isEmpty()) {
                        schemaErrors.add("Mandatory attribute '" + param + "' under '" + section
                                + "' shouldn't be null or empty");
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
    static boolean validateBoolean(String toValidate) {
        return OnapCommandConfg.getSchemaAttrInfo(BOOLEAN_VALUE).contains(toValidate.toLowerCase());
    }

    private static String emptySection(String section) {
        return "The section '" + section + ":' cann't be null or empty";
    }

    static String invalidBooleanValueMessage(String section, String attribute, String value) {
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

    static void validateHttpResultMap(List<String> errorList, Map<String, ?> values) throws OnapCommandException {
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

    static void validateHttpSccessCodes(List<String> errorList, List<Object> requestSuccessCodes) {

        if (requestSuccessCodes == null || requestSuccessCodes.isEmpty()) {
            errorList.add(HTTP_SUCCESS_CODE_INVALID);
            return;
        }

        for (Object successCode : requestSuccessCodes) {
            Integer code = (Integer) successCode;
            if (code < 200 || code >= 300) {
                if ( code != 404) {
                    errorList.add(HTTP_SUCCESS_CODE_INVALID);
                }
            }
        }

    }

    static ArrayList<String> validateHttpSchemaSection(Map<String, ?> values) {
        ArrayList<String> errorList = new ArrayList<>();
        Map<String, ?> map = (Map<String, ?>) values.get(HTTP);
        Map<String, Object> requestMap = (Map<String, Object>) map.get(REQUEST);

        if (requestMap != null && !requestMap.isEmpty()) {
            validateTags(errorList, requestMap, OnapCommandConfg.getSchemaAttrInfo(HTTP_REQUEST_PARAMS),
                    OnapCommandConfg.getSchemaAttrInfo(HTTP_REQUEST_MANDATORY_PARAMS), REQUEST);
            String method = (String) requestMap.get(METHOD);
            if (method != null && !method.isEmpty()) {
                if (!OnapCommandConfg.getSchemaAttrInfo(HTTP_METHODS).contains(method.toLowerCase())) {
                    errorList.add("Attribute '" + METHOD + "' under '" + REQUEST + "' is invalid, correct types are "
                            + OnapCommandConfg.getSchemaAttrInfo(HTTP_METHODS).toString());
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

            nonDeclaredParams.stream().forEach(p -> errorList.add("The parameter '" + p
                    + "' declared under 'parameters:' section is not mapped into request section."));
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

        String help = "usage: oclip " + cmd.getName();

        // Add description
        help += "\n\n" + cmd.getDescription();

        // Add service
        help += "\n\nService: " + cmd.getInfo().getService();

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
            if (!param.isInclude()) {
                continue;
            }

            // First column Option or positional args
            String optFirstCol;
            if (newLineOptions == 3) {
                newLineOptions = 0;
                commandOptions += "\n";
            }

            if (param.getShortOption() != null || param.getLongOption() != null) {
                optFirstCol = OnapCommandParameter.printShortOption(param.getShortOption()) + " | "
                        + OnapCommandParameter.printLongOption(param.getLongOption());
                commandOptions += " [" + optFirstCol + "]";
            } else {
                optFirstCol = param.getName();
                commandOptions += " <" + optFirstCol + ">";
            }

            newLineOptions++;

            attrName.getValues().add(" " + optFirstCol);

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
            if (param.isRawDefaultValueAnEnv()) {
                optSecondCol += defaultMsg + "read from environment variable " + param.getEnvVarNameFromrRawDefaultValue()
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
            help += "\n\nOptions::\n\n" + commandOptions + "\n\nwhere::\n\n" + paramTable.print();
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
            attrHelp.setName(" " + attr.getName());
            attrHelp.setDescription(attr.getDescription());
            String msg = attr.getDescription() + " and is of type " + attr.getType().name() + ".";
            if (attr.isSecured()) {
                msg += " It is secured.";
            }
            attrHelp.getValues().add(msg);
            attrHelp.setType(attr.getType());
            resultTable.getRecords().add(attrHelp);
        }

        if (cmd.getResult().getRecords().size() > 0) {
            try {
                help += "\n\nResults::\n\n" + resultTable.print();
            } catch (OnapCommandException e) {
                throw new OnapCommandHelpFailed(e);
            }
        }

        // Error
        help += "\n\nError::\n\n On error, it prints <HTTP STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>\n";
        return help;
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
     * Discover the Oclip commands.
     *
     * @return list
     */
    public static List<Class<OnapCommand>> discoverCommandPlugins() {
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

    /**
     * There are unique values like uuid is supported, so when input, output (default) values has
     * these special entries, then it will get replaced with it's value
     *
     * @param line
     * @return
     */
    public static String replaceLineForSpecialValues(String line) {
        String result = "";

        if (!line.contains("$s{")) {
            return line;
        }

        int currentIdx = 0;
        while (currentIdx < line.length()) {
            int idxS = line.indexOf("$s{", currentIdx);
            if (idxS == -1) {
                result += line.substring(currentIdx);
                break;
            }
            int idxE = line.indexOf("}", idxS);
            String splEntry = line.substring(idxS + 3, idxE);
            splEntry = splEntry.trim();

            String value = "";

            switch (splEntry) {
                case Constants.SPL_ENTRY_UUID:
                    value = UUID.randomUUID().toString();
                    break;

                default:

                    if (splEntry.startsWith(Constants.SPL_ENTRY_ENV)) {
                        //start to read after env:ENV_VAR_NAME
                        String envVarName = splEntry.substring(4);
                        value = System.getenv(envVarName);
                        if (value == null) {
                            //when env is not defined, assign the same env:ENV_VAR_NAME
                            //so that it will given hit to user that ENV_VAR_NAME to be
                            //defined.
                            value = splEntry;
                        }
                    } else {
                        value = splEntry;
                    }
            }

            result += line.substring(currentIdx, idxS) + value;
            currentIdx = idxE + 1;
        }

        return result;
    }

    public static String replaceLineFromInputParameters(String line, Map<String, OnapCommandParameter> params)
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
            try {
                resultsProcessed.put(key, replaceLineFromOutputResults(resultMap.get(key), resultHttp));
            } catch(OnapCommandResultEmpty e) {
                // pass // NOSONAR
            }
        }

        return resultsProcessed;
    }

    /**
     * Populate result from input parameters.
     *
     * @param resultMap
     *            map
     * @param params
     *            Map<String, OnapCommandParameter>
     * @return map
     * @throws OnapCommandHttpHeaderNotFound
     *             header not found exception
     * @throws OnapCommandHttpInvalidResponseBody
     *             invalid response body exception
     * @throws OnapCommandResultMapProcessingFailed
     *             map processing failed exception
     */
    public static Map<String, ArrayList<String>> populateOutputsFromInputParameters(
            Map<String, ArrayList<String>> resultMap,
            Map<String, OnapCommandParameter> params)
            throws OnapCommandException {
        Map<String, ArrayList<String>> resultsProcessed = new HashMap<>();

        for (Entry<String, ArrayList<String>> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            resultsProcessed.put(key, new ArrayList<>());
            for (String value: entry.getValue()) {
                try {
                    value = replaceLineFromInputParameters(value, params);
                } catch(OnapCommandResultEmpty e) {
                    // pass // NOSONAR
                }
                resultsProcessed.get(key).add(value);
            }
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
    public static List<SchemaInfo> discoverSchemas() throws OnapCommandException {
        List<SchemaInfo> extSchemas = new ArrayList<>();
        try {
            Resource[] res = findResources(SCHEMA_PATH_PATERN);
            if (res != null && res.length > 0) {
                Map<String, ?> resourceMap;

                for (Resource resource : res) {
                    try {
                        resourceMap = loadSchema(resource);
                    } catch (OnapCommandException e) {
                        LOG.error("Invalid schema " + resource.getURI().toString(), e);
                        continue;
                    }

                    if (resourceMap != null && resourceMap.size() > 0) {
                        SchemaInfo schema = new SchemaInfo();

                        schema.setSchemaURI(resource.getURI().toString());

                        Object obj = resourceMap.get(OPEN_CLI_SCHEMA_VERSION);
                        schema.setVersion(obj.toString());

                        if (!schema.getVersion().equalsIgnoreCase(Constants.OPEN_CLI_SCHEMA_VERSION_VALUE_1_0)) {
                            LOG.info("Unsupported Schema version found " + schema.getSchemaURI());
                            continue;
                        }

                        schema.setSchemaName(resource.getFilename());
                        schema.setCmdName((String) resourceMap.get(NAME));

                        Map<String, ?> infoMap = (Map<String, ?>) resourceMap.get(Constants.INFO);
                        if (infoMap != null && infoMap.get(Constants.INFO_TYPE) != null) {
                            schema.setType(infoMap.get(Constants.INFO_TYPE).toString());
                        }

                        if (infoMap != null && infoMap.get(Constants.INFO_PRODUCT) != null) {
                            schema.setProduct(infoMap.get(Constants.INFO_PRODUCT).toString());
                        }

                        schema.setSchemaProfile(identitySchemaProfileType(resourceMap));

                        extSchemas.add(schema);
                    }
                }
            }
        } catch (IOException e) {
            throw new OnapCommandDiscoveryFailed(SCHEMA_DIRECTORY, e);
        }

        return extSchemas;
    }

    private static String identitySchemaProfileType(Map<String, ?> schemaYamlMap) {

        for (String schemeType : OnapCommandConfg.getSchemaAttrInfo(Constants.SCHEMA_TYPES_SUPPORTED)) {
            if (schemaYamlMap.get(schemeType) != null) {
                return schemeType;
            }
        }

        return Constants.BASIC_SCHEMA_PROFILE;
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
    public static Resource[] findResources(String pattern) throws IOException {
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
    public static Resource findResource(String fileName, String pattern) throws IOException {
        Resource[] resources = findResources(pattern);
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
    public static Map<String, ?> loadSchema(Resource resource) throws OnapCommandInvalidSchema {
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
    public static void persistSchemaInfo(List<SchemaInfo> schemas) throws OnapCommandDiscoveryFailed {
        if (schemas != null) {
            try {
                Resource[] resources = findResources(DATA_DIRECTORY);
                if (resources != null && resources.length == 1) {
                    String path = resources[0].getURI().getPath();
                    File file = new File(path + File.separator + DISCOVERY_FILE);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, schemas);
                }
            } catch (IOException e1) {
                throw new OnapCommandDiscoveryFailed(DATA_DIRECTORY,
                        DISCOVERY_FILE, e1);
            }
        }
    }

    public static void persistProfile(List<Param> params, String profileName) throws OnapCommandPersistProfileFailed {
        if (params != null) {
            try {
                Resource[] resources = findResources(DATA_DIRECTORY);
                if (resources != null && resources.length == 1) {
                    String path = resources[0].getURI().getPath();
                    File file = new File(path + File.separator + profileName + ".json");
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, params);
                }
            } catch (IOException e1) {
                throw new OnapCommandPersistProfileFailed(e1);
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
    public static boolean isAlreadyDiscovered() throws OnapCommandDiscoveryFailed {
        Resource resource = null;
        try {
            resource = findResource(DISCOVERY_FILE,
                    DATA_PATH_JSON_PATTERN);
            if (resource != null) {
                return true;
            }
        } catch (IOException e) {
            throw new OnapCommandDiscoveryFailed(DATA_DIRECTORY,
                    DISCOVERY_FILE, e);
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
    public static List<SchemaInfo> discoverOrLoadSchemas() throws OnapCommandException {
        List<SchemaInfo> schemas = new ArrayList<>();
        if (OnapCommandConfg.isDiscoverAlways() || !isAlreadyDiscovered()) {
            schemas = discoverSchemas();
            if (!schemas.isEmpty()) {
                persistSchemaInfo(schemas);
            }
        } else {
            try {
                Resource resource = findResource(DISCOVERY_FILE,
                        DATA_PATH_JSON_PATTERN);
                if (resource != null) {
                    File file = new File(resource.getURI().getPath());
                    ObjectMapper mapper = new ObjectMapper();
                    SchemaInfo[] list = mapper.readValue(file, SchemaInfo[].class);
                    schemas.addAll(Arrays.asList(list));
                }
            } catch (IOException e) {
                throw new OnapCommandDiscoveryFailed(DATA_DIRECTORY,
                        DISCOVERY_FILE, e);
            }
        }

        return schemas;
    }

    public static List<Param> loadParamFromCache(String profileName) throws OnapCommandLoadProfileFailed {
        List<Param> params = new ArrayList<>();

        try {
            Resource resource = findResource(profileName + ".json",
                    DATA_PATH_JSON_PATTERN);
            if (resource != null) {
                File file = new File(resource.getURI().getPath());
                ObjectMapper mapper = new ObjectMapper();
                Param[] list = mapper.readValue(file, Param[].class);
                params.addAll(Arrays.asList(list));
            }
        } catch (IOException e) {
            throw new OnapCommandLoadProfileFailed(e);
        }

        return params;
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
    public static SchemaInfo getSchemaInfo(String cmd, String version) throws OnapCommandException {
        List<SchemaInfo> list = discoverOrLoadSchemas();
        SchemaInfo schemaInfo = null;
        if (list != null) {
            for (SchemaInfo schema : list) {
                if (cmd.equals(schema.getCmdName()) && version.equals(schema.getProduct())) {
                    schemaInfo = schema;
                    break;
                }
            }
        }
        return schemaInfo;
    }

    /**
     * Copy the parameters across the commands, mainly used for catalog, login and logout commands
     *
     * @throws OnapCommandInvalidParameterValue
     */
    public static void copyParamsFrom(OnapHttpCommand from, OnapCommand to) throws OnapCommandInvalidParameterValue {
        for (OnapCommandParameter param: to.getParameters()) {

            OnapCommandParameter fromParam = from.getParametersMap().get(param.getName());

            if (fromParam != null) {
                param.setValue(fromParam.getValue());
                param.setDefaultValue(fromParam.getDefaultValue());
            } else if (param.getName().equalsIgnoreCase(Constants.CATALOG_SERVICE_NAME)) { // for catalog cmd
                param.setValue(from.getService().getName());
            } else if (param.getName().equalsIgnoreCase(Constants.CATALOG_SERVICE_VERSION)) {  // for catalog cmd
                param.setValue(from.getService().getVersion());
            }
        }
    }

    /**
     * Returns the build time from manifest.mf
     */
    public static String findLastBuildTime() {
        String impBuildDate = "";
        try
        {
            String path = OnapCommandUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            JarFile jar = new JarFile(path);
            Manifest manifest = jar.getManifest();
            jar.close();

            Attributes attributes = manifest.getMainAttributes();

            impBuildDate = attributes.getValue("Build-Time");
        }
        catch (IOException e)
        {
            //Ignore it as it will never occur
        }

        return impBuildDate;
    }
}

