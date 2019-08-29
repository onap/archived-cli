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

package org.onap.cli.fw.error;

/**
 * Command sample is invalid.
 *
 */
public class OnapCommandInvalidSample extends OnapCommandWarning {

    private static final long serialVersionUID = -3387652326582792835L;

    private static final String ERROR_CODE = "0xf001";

    private static final String ERROR_MSG = "Invalid command sample ";

    public OnapCommandInvalidSample(String schema, String error) {
        super(ERROR_CODE, ERROR_MSG + schema + ", " + error);
    }

    public OnapCommandInvalidSample(String schema, Throwable throwable) {
        super(ERROR_CODE, ERROR_MSG + schema , throwable);
    }

}
