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

package org.onap.cli.main.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidParameterValue;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.OnapCommandParameterType;
import org.onap.cli.main.error.OnapCliArgumentValueMissing;
import org.onap.cli.main.error.OnapCliInvalidArgument;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

/**
 * Oclip CLI utilities.
 *
 */
public class OnapCliArgsParser {

    /**
     * private Constructor.
     */
    private OnapCliArgsParser() {

    }

    /**
     * It read thru the args and populate the given params for short optional, long option and postional args the idx of
     * positional args, is calculated based on the position at which it present in the params and args.
     *
     * @param params
     *            List of command paramters
     * @param args
     *            Array of arguments
     * @throws OnapCliArgumentValueMissing
     *             ArgumentValueMissing exception
     * @throws OnapCliInvalidArgument
     *             Invalid argument exception
     * @throws OnapCommandInvalidParameterValue
     *             exception
     */
    public static void populateParams(Set<OnapCommandParameter> params, List<String> args)
            throws OnapCommandException {
        Map<String, String> shortOptionMap = new HashMap<>();
        Map<String, String> longOptionMap = new HashMap<>();
        List<String> positionArgs = new ArrayList<>();
        Map<String, OnapCommandParameter> paramMap = new HashMap<>();

        for (OnapCommandParameter param : params) {
            boolean positional = true;
            if (param.getShortOption() != null) {
                shortOptionMap.put(OnapCommandParameter.printShortOption(param.getShortOption()), param.getName());
                positional = false;
            }
            if (param.getLongOption() != null) {
                longOptionMap.put(OnapCommandParameter.printLongOption(param.getLongOption()), param.getName());
                positional = false;
            }

            if (positional) {
                positionArgs.add(param.getName());
            }

            paramMap.put(param.getName(), param);
        }

        int positionalIdx = 0;
        // Skip the first args oclip cmd name, so start from 1
        for (int i = 1; i < args.size(); i++) {
            String paramName = null;
            if (shortOptionMap.containsKey(args.get(i))) {
                paramName = shortOptionMap.get(args.get(i));
            } else if (longOptionMap.containsKey(args.get(i))) {
                paramName = longOptionMap.get(args.get(i));
            }

            if (paramName != null) {
                // end of the list or if its option rather than a value
                if ((i + 1) == args.size() || args.get(i + 1).startsWith("-")) {
                    if (paramMap.get(paramName).getParameterType().equals(OnapCommandParameterType.BOOL)) {
                        paramMap.get(paramName).setValue(true);
                        continue;
                    }
                    throw new OnapCliArgumentValueMissing(args.get(i));
                }

                if (paramMap.get(paramName).getParameterType().equals(OnapCommandParameterType.JSON)) {
                    paramMap.get(paramName).setValue(readJsonStringFromUrl(args.get(i + 1),
                            paramMap.get(paramName).getName()));
                    i++;
                    continue;
                } if (paramMap.get(paramName).getParameterType().equals(OnapCommandParameterType.TEXT)) {
                    paramMap.get(paramName).setValue(readTextStringFromUrl(args.get(i + 1),
                            paramMap.get(paramName).getName()));
                    i++;
                    continue;
                } else if (paramMap.get(paramName).getParameterType()
                        .equals(OnapCommandParameterType.ARRAY)) {
                    Object value = paramMap.get(paramName).getValue();
                    List<String> list = (List<String>) value;

                    list.add(args.get(i + 1));
                    paramMap.get(paramName).setValue(list);
                    i++;
                    continue;
                } else if (paramMap.get(paramName).getParameterType().equals(OnapCommandParameterType.MAP)) {
                    Object value = paramMap.get(paramName).getValue();

                    Map<String, String> map = (Map<String, String>) value;

                    String arg = args.get(i + 1);
                    String[] argArr = arg.split("=");

                    if (argArr.length != 2) {
                        throw new OnapCliInvalidArgument(paramMap.get(paramName).getName());
                    }

                    map.put(argArr[0], argArr[1]);
                    paramMap.get(paramName).setValue(map);
                    i++;
                    continue;
                }

                paramMap.get(paramName).setValue(args.get(i + 1));

                i++;
                continue;
            }

            // it is positional option
            // Positional arg is missing from the params
            if (positionalIdx >= positionArgs.size()) {
                throw new OnapCliInvalidArgument(args.get(i));
            }

            paramMap.get(positionArgs.get(positionalIdx)).setValue(args.get(i));
            positionalIdx++;
        }

        params.clear();
        params.addAll(paramMap.values());
    }

    private static String readJsonStringFromUrl(String input, String argName) throws OnapCliInvalidArgument {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(input);
            if (file.isFile()) {
                return mapper.readValue(file, JSONObject.class).toJSONString();
            } else if (input.startsWith("file:") || input.startsWith("http:") || input.startsWith("ftp:")) {
                URL jsonUrl = new URL(input);
                return mapper.readValue(jsonUrl, JSONObject.class).toJSONString();
            } else {
                return mapper.readValue(input, JSONObject.class).toJSONString();
            }
        } catch (IOException e) {
            throw new OnapCliInvalidArgument(argName, e);
        }
    }

    private static String readTextStringFromUrl(String input, String argName) throws OnapCliInvalidArgument {
        try {
            File file = new File(input);
            if (file.isFile()) {
                return FileUtils.readFileToString(file);
            } else {
                return input;
            }

        } catch (IOException e) {
            throw new OnapCliInvalidArgument(argName, e);
        }
    }

    private static List<String> convertJsonToListString(String arg, String json) throws OnapCliInvalidArgument {
        TypeReference<List<String>> mapType = new TypeReference<List<String>>() {
        };
        try {
            return new ObjectMapper().readValue(json, mapType);
        } catch (IOException e) {
            throw new OnapCliInvalidArgument(arg, e);
        }
    }

    private static Map<String, String> convertJsonToMapString(String arg, String json) throws OnapCliInvalidArgument {
        TypeReference<Map<String, String>> mapType = new TypeReference<Map<String, String>>() {
        };
        try {
            return new ObjectMapper().readValue(json, mapType);
        } catch (IOException e) {
            throw new OnapCliInvalidArgument(arg, e);
        }
    }
}
