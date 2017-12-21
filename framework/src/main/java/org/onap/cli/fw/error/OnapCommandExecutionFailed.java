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
 * Command execution failed.
 *
 */
public class OnapCommandExecutionFailed extends OnapCommandException {
    private static final long serialVersionUID = 488775545436993019L;

    private static final String ERROR_CODE = "0x6001";
    private static final String ERROR_MESSAGE1 = "Command ";
    private static final String ERROR_MESSAGE2 = " failed to execute";
    private static final String ERROR_MESSAGE3 = "Failed to retrive service url";

    public OnapCommandExecutionFailed(String cmdName, String  error, long status) {
        super(ERROR_CODE, ERROR_MESSAGE1 + cmdName + ERROR_MESSAGE2 + ", " + error, status);
    }

    public OnapCommandExecutionFailed(String cmdName, Throwable throwable, long status) {
        super(ERROR_CODE, ERROR_MESSAGE1 + cmdName + ERROR_MESSAGE2 ,throwable, status);
    }

    public OnapCommandExecutionFailed(String  error) {
        super(ERROR_CODE, error);
    }

    public OnapCommandExecutionFailed(Throwable throwable, String  details) {
        super(ERROR_CODE, ERROR_MESSAGE3 + ", " +details, throwable);
    }


    public OnapCommandExecutionFailed(String cmdName, String  error) {
        super(ERROR_CODE, ERROR_MESSAGE1 + cmdName + ERROR_MESSAGE2 + ", " + error);
    }

    public OnapCommandExecutionFailed(String cmd, Throwable throwable) {
        super(ERROR_CODE, ERROR_MESSAGE1 + cmd + ERROR_MESSAGE2 , throwable);
    }
}
