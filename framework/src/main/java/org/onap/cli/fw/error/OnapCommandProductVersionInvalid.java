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

import java.util.Set;

/*
 * Open CLI command product version is invalid
 *
 */
public class OnapCommandProductVersionInvalid extends OnapCommandException {

    private static final long serialVersionUID = 5513297861129088463L;

    public OnapCommandProductVersionInvalid(String invalidVersion, Set<String> validVersions) {
        super("0xd002", "Given product version " + invalidVersion + " is invalid. Please use one of " + validVersions);
    }
}
