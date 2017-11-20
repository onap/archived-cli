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

package org.onap.cli.cmd.sample;

import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.OnapCommandSchema;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandExecutionFailed;

/**
 * This command helps to test the Command functionalities.
 *
 */
@OnapCommandSchema(schema = "sample-test-schema.yaml")
public class OnapCommandSample extends OnapCommand {

    public OnapCommandSample() {
        this(true);
    }

    public OnapCommandSample(boolean isInit) {
        this.isInitialzied = isInit;
    }

    public boolean failCase = false;

    @Override
    protected void run() throws OnapCommandException {
        if (this.failCase)
            throw new OnapCommandExecutionFailed("Test case to fail");
    }

}
