/*
 * Copyright 2020 Huawei Technologies Co., Ltd.
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

public interface OnapCommandProfileAutoDiscoverRegister {

    /**
     * When profile needs to add the capability where there are multiple operations
     * are available and profile wants to translate those operations in to OCS YAML.
     *
     * Example, swagger HTTP methods to OCS YAML (command)
     *
     * Steps:
     * 1. implement this interface to create OCS YAML for each of the operations
     * and store them under <OPEN_CLI_HOME>/open-cli-schema.
     * NOTE: Make sure the YAML created are proper and valid, otherwise invalid
     * YAML will be ignored by OCOMP registrar.
     *
     * 2. Invoke this interface when profile is instantiated
     *  @OnapCommandSchema(type = 'profile name')
        public class OnapSampleProfile implements OnapCommandProfileAutoDiscoverRegister {
            public OnapHttpCommand() {
                 super.addDefaultSchemas(OnapCommandHttpConstants.DEFAULT_PARAMETER_HTTP_FILE_NAME);
                 this.discover();
            }

            public void discover() {

            }
       }
     *
     * 3. Run command schema-refresh to get auto registered into registrar.
     *
     */
    void discover();
}
