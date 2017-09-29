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

public class OnapCommandDiscoveryFailed extends OnapCommandException {

    private static final long serialVersionUID = 424464582747161435L;

    private static final String ERROR_CODE = "0x0010";
    private static final String ERROR_MESSAGE1 = "Failed auto discover schema files from ";
    private static final String ERROR_MESSAGE2 = " under class path, ";
    private static final String ERROR_MESSAGE3 = "' under class path directory '";
    private static final String ERROR_MESSAGE4 = "Failed auto generate json file '";

    public OnapCommandDiscoveryFailed(String name) {
        this(name, new Exception(""));
    }

    public OnapCommandDiscoveryFailed(String name, Throwable throwable) {
        super(ERROR_CODE, ERROR_MESSAGE1 + name + ERROR_MESSAGE2 + throwable.getMessage());
    }

    public OnapCommandDiscoveryFailed(String directory, String fileName) {
        super(ERROR_CODE, ERROR_MESSAGE4 + fileName + ERROR_MESSAGE3 + directory + "'");
    }

    public OnapCommandDiscoveryFailed(String directory, String fileName, Throwable throwable) {
        super(ERROR_CODE, ERROR_MESSAGE4 + fileName + ERROR_MESSAGE3 + directory + "'", throwable);
    }
}
