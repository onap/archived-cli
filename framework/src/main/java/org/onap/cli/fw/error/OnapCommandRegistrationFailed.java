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
 * Command Not registered in Onap Command Registrar.
 *
 */
public class OnapCommandRegistrationFailed extends OnapCommandException {

    private static final long serialVersionUID = 5513297861129088460L;

    private static final String ERROR_CODE = "0x0018";

    private static final String ERROR_MSG = "Failed to register the command ";

    public OnapCommandRegistrationFailed(String cmdName, String error) {
        super(ERROR_CODE,  ERROR_MSG + cmdName + ", " + error);
    }

    public OnapCommandRegistrationFailed(String cmdName, Throwable throwable) {
        super(ERROR_CODE,  ERROR_MSG + cmdName,  throwable);
    }
}
