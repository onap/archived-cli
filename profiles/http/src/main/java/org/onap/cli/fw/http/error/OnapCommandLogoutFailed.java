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

package org.onap.cli.fw.http.error;

import org.onap.cli.fw.error.OnapCommandException;

/**
 * Logout failed.
 *
 */
public class OnapCommandLogoutFailed extends OnapCommandException {

    private static final long serialVersionUID = 1150649507734289032L;
    private static final String ERROR_CODE = "0x4002";
    private static final String ERROR_MESSAGE1 = "Logout failed";

    public OnapCommandLogoutFailed(String error) {
        super(ERROR_CODE, ERROR_MESSAGE1 +", " + error);
    }

    public OnapCommandLogoutFailed(Throwable throwable) {
        super(ERROR_CODE, ERROR_MESSAGE1, throwable);
    }

    public OnapCommandLogoutFailed(int statusCode) {
        super(ERROR_CODE, ERROR_MESSAGE1, statusCode);
    }
}
