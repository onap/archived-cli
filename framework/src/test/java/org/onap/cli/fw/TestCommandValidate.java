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

package org.onap.cli.fw;

import org.junit.Before;
import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandParameterMissing;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.utils.OnapCommandUtils;

public class TestCommandValidate {

    OnapCommand cmd;

    @Before
    public void before() {
        cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
    }

    @Test
    public void testNoAuthArgumentTrue() throws OnapCommandException {

        OnapCommandUtils.loadSchema(cmd, "sample-test-include-param.yaml", true);

        OnapCommandParameter noAuthParam = cmd.getParameters().stream().filter(p -> p.getName().equalsIgnoreCase("no-auth")).findFirst().get();
        noAuthParam.setValue(true);
        OnapCommandParameter msbParam = cmd.getParameters().stream().filter(p -> p.getName().equalsIgnoreCase("msb-url")).findFirst().get();
        msbParam.setValue("localhost://msbip:msb:port");
        cmd.validate();
    }

    @Test(expected = OnapCommandParameterMissing.class)
    public void testNoAuthArgFalse() throws OnapCommandException {
        OnapCommandUtils.loadSchema(cmd, "sample-test-include-param.yaml", true);
        cmd.validate();
    }
}
