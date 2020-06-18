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

import static org.onap.cli.fw.conf.OnapCommandConstants.BOOLEAN_TRUE;
import static org.onap.cli.fw.conf.OnapCommandConstants.BOOLEAN_VALUE;
import static org.onap.cli.fw.conf.OnapCommandConstants.IS_INCLUDE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidParameterValue;
import org.onap.cli.fw.error.OnapCommandParameterNotFound;
import org.onap.cli.fw.error.OnapCommandResultEmpty;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.OnapCommandParameterType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;


/**
 * Provides helper method to parse Yaml files and produce required objects.
 *
 */
public class OnapCommandUtils {

    static Logger log = LoggerFactory.getLogger(OnapCommandUtils.class);
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    /**
     * Private constructor.
     */
    private OnapCommandUtils() {

    }

    public static void throwOrCollect(OnapCommandException ex, List<String> list, boolean shouldCollectException)
            throws OnapCommandException {
        if (shouldCollectException) {
            list.add(ex.getMessage());
        } else {
            throw ex;
        }
    }

    public static void validateTags(List<String> schemaErrors, Map<String, ?> yamlMap, List<String> totalParams,
            List<String> mandatoryParams, String section) {
        // mrkanag capture invalid entries as well
        for (String param : totalParams) {
            boolean isMandatory = mandatoryParams.contains(param);
            boolean isYamlContains = yamlMap.containsKey(param);
            boolean isInclude = yamlMap.containsKey(IS_INCLUDE) && yamlMap.get(IS_INCLUDE).toString().equals(BOOLEAN_TRUE);
            if (isMandatory) {
                if (!isYamlContains && isInclude) {
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
    public static boolean validateBoolean(String toValidate) {
        return OnapCommandConfig.getCommaSeparatedList(BOOLEAN_VALUE).contains(toValidate.toLowerCase());
    }

    public static String emptySection(String section) {
        return "The section '" + section + ":' cann't be null or empty";
    }

    public static String invalidBooleanValueMessage(String section, String attribute, String value) {
        return "The value '" + value + "' of '" + attribute + "' present under '" + section + "' should be boolean";
    }

    public static void parseParameters(String line, Set<String> paramNames) {

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

    /**
     * Create Dict from list of Parameters.
     *
     * @param inputs
     *            list of parameters
     * @return map
     */
    public static Map<String, OnapCommandParameter> getInputMap(Set<OnapCommandParameter> inputs) {
        Map<String, OnapCommandParameter> map = new HashMap<>();
        for (OnapCommandParameter param : inputs) {
            map.put(param.getName(), param);
        }
        return map;
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
     * There are unique values like uuid is supported, so when input, output (default) values has
     * these special entries, then it will get replaced with it's value
     *
     * @param lineSpl
     * @return
     */
    public static String replaceLineForSpecialValues(String lineSpl) {
        return replaceLineForSpecialValues(lineSpl, new HashMap<String, String>());
    }

    /**
     *
     * @param lineSpl
     * @param values Value for the given entry already known by the caller.
     * @return
     */
    public static String replaceLineFromResults(String line, Map <String, String> values) {
        StringBuilder resultLine = new StringBuilder();

        if (!line.contains("$r{")) {
            return line;
        }

        int currentIdx = 0;
        while (currentIdx < line.length()) {
            int idxS = line.indexOf("$r{", currentIdx);
            if (idxS == -1) {
                resultLine.append(line.substring(currentIdx));
                break;
            }
            int idxE = line.indexOf("}", idxS);
            String attr = line.substring(idxS + 3, idxE);
            attr = attr.trim();

            String value = "";

            if (values.get(attr) != null) {
                value = values.get(attr);
            } else {
                value = attr;
            }

            resultLine.append(line.substring(currentIdx, idxS) + value);
            currentIdx = idxE + 1;
        }

        return resultLine.toString();
    }

    public static String replaceLineForSpecialValues(String lineSpl, Map <String, String> values) {
        StringBuilder resultSpl = new StringBuilder();

        if (!lineSpl.contains("$s{")) {
            return lineSpl;
        }

        int currentIdx = 0;
        while (currentIdx < lineSpl.length()) {
            int idxS = lineSpl.indexOf("$s{", currentIdx);
            if (idxS == -1) {
                resultSpl.append(lineSpl.substring(currentIdx));
                break;
            }
            int idxE = lineSpl.indexOf("}", idxS);
            String splEntry = lineSpl.substring(idxS + 3, idxE);
            splEntry = splEntry.trim();

            String value = "";

            switch (splEntry) {
                case OnapCommandConstants.SPL_ENTRY_UUID:
                    value = UUID.randomUUID().toString();
                    break;

                default:

                    if (splEntry.startsWith(OnapCommandConstants.SPL_ENTRY_ENV)) {
                        //start to read after env:ENV_VAR_NAME
                        String envVarName = splEntry.substring(4);
                        value = System.getenv(envVarName); //NOSONAR
                        if (value == null) {
                            //when env is not defined, assign the same env:ENV_VAR_NAME
                            //so that it will given hit to user that ENV_VAR_NAME to be
                            //defined.
                            value = splEntry;
                        }
                    } else if (splEntry.startsWith(OnapCommandConstants.SPL_ENTRY_FILE)) {
                        //start to read after file:filepath
                        String fileName = splEntry.substring(5);
                        try {
                            value = FileUtils.readFileToString(new File(fileName));
                        } catch (IOException e) {
                            //when file is not found, assign the same file:FILE_PATH
                            //so that it will given hit to user that FILE_PATH to be
                            //exist.
                            value = "";
                        }
                    } else if (splEntry.startsWith(OnapCommandConstants.SPL_ENTRY_MD5)) {
                            //start to read after md5:entryname
                            String entryName = splEntry.substring(4);
                            String content = values.get(entryName);
                            if (content != null)
                                value = OnapCommandUtils.md5(content);
                            else
                                value = splEntry;
                    } else {
                        if (values.get(splEntry) != null) {
                            value = values.get(splEntry);
                        } else {
                            value = splEntry;
                        }
                    }
            }

            resultSpl.append(lineSpl.substring(currentIdx, idxS) + value);
            currentIdx = idxE + 1;
        }

        return resultSpl.toString();
    }

    public static String replaceLineFromInputParameters(String line, Map<String, OnapCommandParameter> params)
            throws OnapCommandException {
        StringBuilder result = new StringBuilder();

        if (!line.contains("${")) {
            return line;
        }

        int currentIdx = 0;
        while (currentIdx < line.length()) {
            int idxS = line.indexOf("${", currentIdx);
            if (idxS == -1) {
                result.append(line.substring(currentIdx));
                break;
            }
            int idxE = line.indexOf("}", idxS);
            String paramName = line.substring(idxS + 2, idxE);
            paramName = paramName.trim();
            if (!params.containsKey(paramName)) {
                throw new OnapCommandParameterNotFound(paramName);
            }

            OnapCommandParameter param = params.get(paramName);
            if (OnapCommandParameterType.ARRAY.equals(param.getParameterType())
                    || OnapCommandParameterType.JSON.equals(param.getParameterType())
                    || OnapCommandParameterType.YAML.equals(param.getParameterType())) {
                // ignore the front and back double quotes in json body
                String va_ = params.get(paramName).getValue().toString();
                if (idxS > 0)
                    result.append(line.substring(currentIdx, idxS - 1) + va_);
                else
                    result.append(va_);
                currentIdx = idxE + 2;
            } else if (OnapCommandParameterType.MAP.equals(param.getParameterType())) {
                try {
                    String value = gson.toJson(params.get(paramName).getValue());
                    if ((idxS == 0) && (currentIdx == 0)) {
                        result.replace(0, result.length(), value);
                    } else {
                        result.append(line.substring(currentIdx, idxS - 1) + value);
                    }
                } catch (Exception e) {  // NOSONAR
                    //never occur as map is coverted to json string here
                }

                currentIdx = idxE + 2;
            }else {
                result.append(line.substring(currentIdx, idxS) + params.get(paramName).getValue().toString());
                currentIdx = idxE + 1;
            }
        }

        return result.toString();
    }

    /**
     * Populate result from input parameters.
     *
     * @param resultMap
     *            map
     * @param params
     *            Map<String, OnapCommandParameter>
     * @return map
     * @throws OnapCommandException
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
                } catch(OnapCommandResultEmpty e) {  // NOSONAR
                    // pass
                }
                resultsProcessed.get(key).add(value);
            }
        }

        return resultsProcessed;
    }

    /**
     * Copy the parameters across the commands, mainly used for catalog, login and logout commands
     *
     * @throws OnapCommandInvalidParameterValue
     */
    public static void copyParamsFrom(OnapCommand from, OnapCommand to, Map<String, String> paramOverride) throws OnapCommandInvalidParameterValue {
        for (OnapCommandParameter param: to.getParameters()) {

            OnapCommandParameter fromParam = from.getParametersMap().get(param.getName());

            if (fromParam != null) {
                param.setValue(fromParam.getValue());
                param.setDefaultValue(fromParam.getDefaultValue());
            }

            if (paramOverride.containsKey(param.getName())) {
                 param.setValue(paramOverride.get(param.getName()));
            }
        }
    }

    public static void copyParamsFrom(OnapCommand from, OnapCommand to) throws OnapCommandInvalidParameterValue {
        copyParamsFrom(from, to, new HashMap<String, String>());
    }

    /**
     * Copy param schema from source command to destination command, useful in adding login command params into command
     * @param from
     * @param to
     * @throws OnapCommandException
     */
    public static void copyParamSchemasFrom(OnapCommand from, OnapCommand to) {
        for (OnapCommandParameter param: from.getParameters()) {
            if (!to.getParametersMap().containsKey(param.getName())) {
                to.getParameters().add(param);
            }
        }
    }

    public static String md5(String content) {
        String md5 = DigestUtils.md5Hex(content); //NOSONAR

        byte[] encodeBase64 = Base64.encodeBase64(md5.getBytes());
        return new String(encodeBase64);
    }
}



