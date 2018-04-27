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

package org.onap.cli.fw.http.schema;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;
import org.onap.cli.fw.schema.OnapCommandSchemaLoader;
import org.onap.cli.main.OnapCli;

public class ValidateSchemaTest {
    @Test
    public void validateTest() throws OnapCommandException {

        OnapCommand cmd1 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList1 = OnapCommandSchemaLoader.loadSchema(cmd1, "schema-validate-http.yaml", true, true);
        assertTrue(errorList1.size() > 0);
    }

    @Test
    public void testVerify() throws OnapCommandException {
        OnapCommandRegistrar.getRegistrar().setEnabledProductVersion("open-cli");
        OnapCli onapCli = new OnapCli(new String[]{"sample-test-verify", "--verify"});
        onapCli.handle();

        //mrkanag though it pass, when ran alone, It fails during mvn test phase, check it
    }
}
