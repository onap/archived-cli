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

package org.onap.cli.cmd.auth;

import org.onap.cli.fw.OnapCommandSchema;
import org.onap.cli.fw.cmd.BasicAuthLoginCommand;
import org.onap.cli.fw.error.OnapCommandException;

@OnapCommandSchema(name = "sdc-basic-login", version = "onap-1.0", type = "auth", schema = "basic-login-onap-sdc-1-0.yaml")
public class OnapSdcBasicAuthLoginCommand extends BasicAuthLoginCommand {

    @Override
    protected void run() throws OnapCommandException {
    	super.run();
    }
}
