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
 * Command input parameter options are conflicting.
 *
 */
public class OnapCommandParameterNameConflict extends OnapCommandException {

    private static final long serialVersionUID = -993203017969910920L;

    public OnapCommandParameterNameConflict(String name) {
        super("0x7004", "Parameter name " + name + " is in conflict");

    }
}
