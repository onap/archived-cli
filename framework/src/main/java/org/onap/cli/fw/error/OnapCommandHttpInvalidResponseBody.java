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
 * OnapCommandParameterNotFound.
 *
 */
public class OnapCommandHttpInvalidResponseBody extends OnapCommandException {

    private static final long serialVersionUID = 6676137916079057963L;

    private static final String ERROR_CODE = "0x3004";
    private static final String ERR_MSG = "Http response body does not have json entry ";

    public OnapCommandHttpInvalidResponseBody(String name, String error) {
        super(ERROR_CODE, ERR_MSG + name + ", " + error);
    }

    public OnapCommandHttpInvalidResponseBody(String name, Throwable throwable) {
        super(ERROR_CODE, ERR_MSG + name, throwable);
    }
}
