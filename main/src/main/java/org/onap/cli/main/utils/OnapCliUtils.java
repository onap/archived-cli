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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidParameterValue;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.ParameterType;
import org.onap.cli.main.error.OnapCliArgumentValueMissing;
import org.onap.cli.main.error.OnapCliInvalidArgument;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Onap CLI utilities.
 *
 */
public class OnapCliUtils {

    /**
     * private Constructor.
     */
    private OnapCliUtils() {

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
    public static void populateParams(List<OnapCommandParameter> params, List<String> args)
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
        // Skip the first args onap cmd name, so start from 1
        for (int i = 1; i < args.size(); i++) {
            // check if short option exist
            // (mrkanag) Optimize the below code to handle short and long options in one iteration
            // now its redundant
            if (shortOptionMap.containsKey(args.get(i))) {
                // end of the list or if its option rather than a value
                if ((i + 1) == args.size() || args.get(i + 1).startsWith("-")) {
                    if (paramMap.get(shortOptionMap.get(args.get(i))).getParameterType().equals(ParameterType.BOOL)) {
                        paramMap.get(shortOptionMap.get(args.get(i))).setValue("true");
                        continue;
                    }
                    throw new OnapCliArgumentValueMissing(args.get(i));
                }

                if (paramMap.get(shortOptionMap.get(args.get(i))).getParameterType().equals(ParameterType.JSON)) {
                    paramMap.get(shortOptionMap.get(args.get(i))).setValue(readJsonStringFromUrl(args.get(i + 1),
                            paramMap.get(shortOptionMap.get(args.get(i))).getName()));
                    i++;
                    continue;
                } else if (paramMap.get(shortOptionMap.get(args.get(i))).getParameterType()
                        .equals(ParameterType.ARRAY)) {
                    Object value = paramMap.get(shortOptionMap.get(args.get(i))).getValue();
                    List<String> list;
                    if (value == "") {
                        list = new ArrayList<>();
                    } else {
                        list = convertJsonToListString(paramMap.get(shortOptionMap.get(args.get(i))).getName(),
                                value.toString());
                    }
                    list.add(args.get(i + 1));
                    paramMap.get(shortOptionMap.get(args.get(i))).setValue(list);
                    i++;
                    continue;
                } else if (paramMap.get(shortOptionMap.get(args.get(i))).getParameterType().equals(ParameterType.MAP)) {
                    Object value = paramMap.get(shortOptionMap.get(args.get(i))).getValue();

                    Map<String, String> map;

                    if (value == "") {
                        map = new HashMap<>();
                    } else {
                        map = convertJsonToMapString(paramMap.get(shortOptionMap.get(args.get(i))).getName(),
                                value.toString());
                    }

                    String arg = args.get(i + 1);
                    String[] argArr = arg.split("=");

                    if (argArr.length != 2) {
                        throw new OnapCliInvalidArgument(paramMap.get(shortOptionMap.get(args.get(i))).getName());
                    }

                    map.put(argArr[0], argArr[1]);
                    paramMap.get(shortOptionMap.get(args.get(i))).setValue(map);
                    i++;
                    continue;
                }

                paramMap.get(shortOptionMap.get(args.get(i))).setValue(args.get(i + 1));

                i++;
                continue;
            }

            // check if long option exist
            if (longOptionMap.containsKey(args.get(i))) {
                // end of the list or if its option rather than a value
                if ((i + 1) == args.size() || args.get(i + 1).startsWith("-")) {
                    if (paramMap.get(longOptionMap.get(args.get(i))).getParameterType().equals(ParameterType.BOOL)) {
                        paramMap.get(longOptionMap.get(args.get(i))).setValue("true");
                        continue;
                    }
                    throw new OnapCliArgumentValueMissing(args.get(i));
                }

                if (paramMap.get(longOptionMap.get(args.get(i))).getParameterType().equals(ParameterType.JSON)) {
                    paramMap.get(longOptionMap.get(args.get(i))).setValue(readJsonStringFromUrl(args.get(i + 1),
                            paramMap.get(longOptionMap.get(args.get(i))).getName()));
                    i++;
                    continue;
                } else if (paramMap.get(longOptionMap.get(args.get(i))).getParameterType()
                        .equals(ParameterType.ARRAY)) {
                    Object value = paramMap.get(longOptionMap.get(args.get(i))).getValue();
                    List<String> list;
                    if (value == "") {
                        list = new ArrayList<>();
                    } else {
                        list = convertJsonToListString(paramMap.get(longOptionMap.get(args.get(i))).getName(),
                                value.toString());
                    }
                    list.add(args.get(i + 1));
                    paramMap.get(longOptionMap.get(args.get(i))).setValue(list);
                    i++;
                    continue;
                } else if (paramMap.get(longOptionMap.get(args.get(i))).getParameterType().equals(ParameterType.MAP)) {

                    Object value = paramMap.get(longOptionMap.get(args.get(i))).getValue();

                    Map<String, String> map;

                    if (value == "") {
                        map = new HashMap<>();
                    } else {
                        map = convertJsonToMapString(paramMap.get(longOptionMap.get(args.get(i))).getName(),
                                value.toString());
                    }

                    String arg = args.get(i + 1);
                    String[] argArr = arg.split("=");

                    if (argArr.length != 2) {
                        throw new OnapCliInvalidArgument(paramMap.get(longOptionMap.get(args.get(i))).getName());
                    }

                    map.put(argArr[0], argArr[1]);
                    paramMap.get(longOptionMap.get(args.get(i))).setValue(map);
                    i++;
                    continue;
                }

                paramMap.get(longOptionMap.get(args.get(i))).setValue(args.get(i + 1));

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
