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

package org.onap.cli.fw.cmd;

import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.output.OnapCommandResultAttribute;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class OnapSchemaRefreshCommandTest {

    @Test
    public void validateSchemaCommandTest1() throws OnapCommandException {
        OnapSchemaRefreshCommand cmd = new OnapSchemaRefreshCommand();
        cmd.initializeSchema("schema-refresh.yaml");
        cmd.execute();

        List<OnapCommandResultAttribute> onapCommandResultAttribute = cmd.getResult()
                .getRecords();

        String s1Number = onapCommandResultAttribute.get(0).getValues().get(0);
        String cmdName = onapCommandResultAttribute.get(1).getValues().get(0);
        String cmdFile = onapCommandResultAttribute.get(3).getValues().get(0);
        String version = onapCommandResultAttribute.get(4).getValues().get(0);

        assertTrue(s1Number.equalsIgnoreCase("1"));
        assertTrue(cmdName.equalsIgnoreCase("sample-test1"));
        assertTrue(cmdFile.equalsIgnoreCase("sample-test1-schema-http.yaml"));
        assertTrue(version.equalsIgnoreCase("1.0"));

    }
}
