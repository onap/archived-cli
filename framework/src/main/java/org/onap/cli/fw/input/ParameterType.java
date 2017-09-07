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

package org.onap.cli.fw.input;

import org.onap.cli.fw.error.OnapCommandInvalidParameterType;

/**
 * Parameter type supported by Onap CLI.
 *
 */
public enum ParameterType {
    /**
     * JSON file.
     */
    JSON,
    /**
     * YAML file.
     */
    YAML, STRING, TEXT, LONG,
    /**
     * URL location.
     */
    URL, BOOL, ARRAY, MAP, BINARY, UUID;

    /**
     * Get parameter type.
     *
     * @param name
     *            type name
     * @return type
     * @throws OnapCommandInvalidParameterType
     *             exception
     */
    public static ParameterType get(String name) throws OnapCommandInvalidParameterType {
        if (JSON.name().equalsIgnoreCase(name)) {
            return JSON;
        } else if (YAML.name().equalsIgnoreCase(name)) {
            return YAML;
        } else if (STRING.name().equalsIgnoreCase(name)) {
            return STRING;
        } else if (LONG.name().equalsIgnoreCase(name)) {
            return LONG;
        } else if (URL.name().equalsIgnoreCase(name)) {
            return URL;
        } else if (BOOL.name().equalsIgnoreCase(name)) {
            return BOOL;
        }  else if (ARRAY.name().equalsIgnoreCase(name)) {
            return ARRAY;
        } else if (MAP.name().equalsIgnoreCase(name)) {
            return MAP;
        } else if (BINARY.name().equalsIgnoreCase(name)) {
            return BINARY;
        } else if (UUID.name().equalsIgnoreCase(name)) {
            return UUID;
        } else if (TEXT.name().equalsIgnoreCase(name)) {
            return TEXT;
        } else {
            throw new OnapCommandInvalidParameterType(name);
        }
    }
}
