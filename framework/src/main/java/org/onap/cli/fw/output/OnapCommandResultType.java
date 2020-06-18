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

package org.onap.cli.fw.output;

/**
 * Oclip command result format.
 *
 */
public enum OnapCommandResultType {
    TABLE, CSV, JSON, YAML, TEXT;

    /**
     * Check whether the output to be formatted in tabular format.
     *
     * @param type
     *            output format type
     * @return boolean
     */
    public static boolean isTabularForm(String type) {
        return type.equalsIgnoreCase(TABLE.name());
    }

    /**
     * Get OnapCommandResultType.
     *
     * @param name
     *            format name
     * @return OnapCommandResultType
     */
    public static OnapCommandResultType get(String name) {
        if (TABLE.name().equalsIgnoreCase(name)) {
            return TABLE;
        }
        if (CSV.name().equalsIgnoreCase(name)) {
            return CSV;
        }
        if (JSON.name().equalsIgnoreCase(name)) {
            return JSON;
        }
        if (YAML.name().equalsIgnoreCase(name)) {
            return YAML;
        }
        return TEXT;
    }
}
