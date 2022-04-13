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

package org.onap.cli.fw.cmd;

import org.onap.cli.fw.error.OnapCommandInvalidCommandType;
import org.onap.cli.fw.error.OnapCommandInvalidParameterType;

/**
 * Command type supported by Oclip CLI.
 *
 */
public enum OnapCommandType {

    AUTH,
    CATALOG,
    CMD,
    SYSTEM;

    /**
     * Get parameter type.
     *
     * @param name
     *            type name
     * @return type
     * @throws OnapCommandInvalidParameterType
     *             exception
     */
    public static OnapCommandType get(String name) throws OnapCommandInvalidCommandType {
        if (AUTH.name().equalsIgnoreCase(name)) {
            return AUTH;
        } else if (CATALOG.name().equalsIgnoreCase(name)) {
            return CATALOG;
        } else if (CMD.name().equalsIgnoreCase(name)) {
            return CMD;
        } else {
            throw new OnapCommandInvalidCommandType(name);
        }
    }
}
