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

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidParameterValue;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.OnapCommandParameterType;
import org.onap.cli.fw.utils.JsonUtil;
import org.onap.cli.main.error.OnapCliArgumentValueMissing;
import org.onap.cli.main.error.OnapCliInvalidArgument;
import org.yaml.snakeyaml.Yaml;



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
        for (int i = 0; i < args.size(); i++) {
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

                } else if (paramMap.get(paramName).getParameterType().equals(OnapCommandParameterType.TEXT)) {
                    paramMap.get(paramName).setValue(readTextStringFromUrl(args.get(i + 1),
                            paramMap.get(paramName).getName()));
                    i++;
                    continue;

                } else if (paramMap.get(paramName).getParameterType().equals(OnapCommandParameterType.YAML)) {
                    String value = readYamlStringFromUrl(args.get(i + 1),
                            paramMap.get(paramName).getName());
                    paramMap.get(paramName).setValue(value);
                    i++;
                    continue;

                } else if (paramMap.get(paramName).getParameterType().equals(OnapCommandParameterType.BYTE)) {
                    paramMap.get(paramName).setValue(readBytesFromUrl(args.get(i + 1),
                            paramMap.get(paramName).getName()));
                    i++;
                    continue;

                } else if (paramMap.get(paramName).getParameterType()
                        .equals(OnapCommandParameterType.ARRAY)) {
                    Object value = paramMap.get(paramName).getValue();
                    List<String> list = (List<String>) value;

                    list.add(readTextStringFromUrl(args.get(i + 1), paramMap.get(paramName).getName()));
                    paramMap.get(paramName).setValue(list);
                    i++;
                    continue;

                } else if (paramMap.get(paramName).getParameterType()
                        .equals(OnapCommandParameterType.MAP)) {
                    Object value = paramMap.get(paramName).getValue();

                    Map<String, String> map = (Map<String, String>) value;

                    String arg = args.get(i + 1);
                    String[] argArr = arg.split("=", 2);

                    if (argArr.length != 2) {
                        throw new OnapCliInvalidArgument(
                                paramMap.get(paramName).getName(),
                                "it should be in the form of <key>=<value>");
                    }

                    //Make sure to read values from file, in case file path is given.
                    //map.put(argArr[0], readTextStringFromUrl(argArr[1], paramMap.get(paramName).getName()));
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
                throw new OnapCliInvalidArgument(
                        args.get(i),
                        "No positional argument is defined for this one");
            }

            paramMap.get(positionArgs.get(positionalIdx)).setValue(args.get(i));
            positionalIdx++;
        }

        params.clear();
        params.addAll(paramMap.values());
    }

    public static String readJsonStringFromUrl(String input, String argName) throws OnapCliInvalidArgument {
        try {
            File file = new File(input);
            if (file.isFile()) {
                FileReader reader = new FileReader(file);
                return JsonUtil.getGsonInstance().fromJson(reader, JsonElement.class).toString();
            } else if (input.startsWith("file:") || input.startsWith("http:") || input.startsWith("ftp:")) {
                URL jsonUrl = new URL(input);
                Reader reader = new InputStreamReader(jsonUrl.openStream());
                return JsonUtil.getGsonInstance().fromJson(reader, JsonElement.class).toString();
            } else {
                return JsonUtil.getGsonInstance().fromJson(input, JsonElement.class).toString();
            }
        } catch (IOException e) {
            throw new OnapCliInvalidArgument(argName, e);
        }
    }

    public static String readTextStringFromUrl(String input, String argName) throws OnapCliInvalidArgument {
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

    public static String readYamlStringFromUrl(String input, String argName) throws OnapCliInvalidArgument {
        try {
            File file = new File(input);
            if (file.isFile()) {
                String value = FileUtils.readFileToString(file);
                new Yaml().load(value);
                return value;
            } else {
                return input;
            }

        } catch (IOException e) {
            throw new OnapCliInvalidArgument(argName, e);
        }
    }

    public static String readBytesFromUrl(String input, String argName) throws OnapCliInvalidArgument {
        try {
            File file = new File(input);
            if (file.isFile()) {
                byte[] encodeBase64 = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
                return new String(encodeBase64);
            } else {
                byte[] encodeBase64 = Base64.encodeBase64(input.getBytes());
                return new String(encodeBase64);
            }
        } catch (IOException e) {
            throw new OnapCliInvalidArgument(argName, e);
        }
    }

    public static List<String> convertJsonToListString(String arg, String json) throws OnapCliInvalidArgument {
        TypeToken<List<String>> mapType = new TypeToken<List<String>>() {
        };
        return JsonUtil.getGsonInstance().fromJson(json, mapType.getType());
    }

    public static Map<String, String> convertJsonToMapString(String arg, String json) throws OnapCliInvalidArgument {
        TypeToken<Map<String, String>> mapType = new TypeToken<Map<String, String>>() {
        };
        return JsonUtil.getGsonInstance().fromJson(json, mapType.getType());
    }
}
