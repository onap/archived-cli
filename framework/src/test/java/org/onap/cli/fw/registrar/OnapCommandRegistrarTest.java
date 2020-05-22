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

package org.onap.cli.fw.registrar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandNotFound;
import org.onap.cli.fw.schema.OnapCommandSchema;

public class OnapCommandRegistrarTest {

    OnapCommandRegistrar registerar;

    @Before
    public void setup() throws OnapCommandException {
        registerar = OnapCommandRegistrar.getRegistrar();
        createDir();
    }

    private void createDir() {
        URL url = OnapCommandRegistrarTest.class.getClassLoader().getResource("open-cli-schema");
        if (url != null) {
            String path = url.getPath();
            path = path.replaceFirst("open-cli-schema", "data");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            } else {
                File f1 = new File(path + "/cli-schema.json");
                f1.delete();
            }
        }
    }

    @Test
    public void oclipCommandNotFoundTest() throws OnapCommandException {
        registerar = OnapCommandRegistrar.getRegistrar();
        try {
            registerar.get("Test1");
            fail("This should have thrown an exception");
        } catch (OnapCommandNotFound e) {
            //pass  // NOSONAR
        } catch (Exception e) {
            fail("This should have thrown an OnapCommandNotFound exception");
        }
    }

    @Test
    public void helpTest() throws OnapCommandException {
        String help = registerar.getHelp();
        assertNotNull(help);
    }

    @Test
    public void versionTest() throws OnapCommandHelpFailed {
        String version = registerar.getVersion();
        assertNotNull(version);
    }

    @Test
    public void listTest() {
        registerar.listCommands();
    }

    @Test
    public void test() throws OnapCommandException {
        OnapCommandRegistrar registrar = OnapCommandRegistrar.getRegistrar();
        OnapCommand cmd = registrar.get("sample-test");
        cmd.printVersion();
        registrar.listCommands();
    }
}

@OnapCommandSchema(schema = "sample-test-schema.yaml")
final class OnapCommandTest extends OnapCommand {

    public OnapCommandTest() {

    }

    public static final String CMD_NAME = "test";

    protected void run() throws OnapCommandException {

    }

}

@OnapCommandSchema(schema = "test-schema.yaml")
final class OnapCommandTest1 extends OnapCommand {

    public OnapCommandTest1() {

    }

    public static final String CMD_NAME = "test1";

    protected void run() throws OnapCommandException {

    }

}
