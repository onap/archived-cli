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
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandNotFound;
import org.onap.cli.fw.error.OnapCommandRegistrationFailed;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class OnapCommandRegistrarTest {

    OnapCommandRegistrar registerar;

    @Before
    public void setup() throws OnapCommandException {
        registerar = OnapCommandRegistrar.getRegistrar();
        createDir();
    }

    private void createDir() {
        URL url = OnapCommandRegistrarTest.class.getClassLoader().getResource("onap-cli-schema");
        if (url != null) {
            String path = url.getPath();
            path = path.replaceFirst("onap-cli-schema", "data");
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
    public void registerTest() throws OnapCommandException {
        OnapCommand test = new OnapCommandTest();
        Class<OnapCommand> cmd = (Class<OnapCommand>) test.getClass();
        registerar.register("Test", "1.0", cmd);
        OnapCommand cc = registerar.get("Test");
        assertTrue(cmd == cc.getClass());

    }

    @Test
    // For Coverage
    public void cmdTestSchema() throws OnapCommandException {
        OnapCommand test = new OnapCommandTest();
        Class<OnapCommand> cmd = (Class<OnapCommand>) test.getClass();
        registerar.register("Test", "1.0", cmd);
        OnapCommand cc = registerar.get("Test");
    }

    @Test
    public void onapCommandNotFoundTest() throws OnapCommandException {
        try {
            registerar = OnapCommandRegistrar.getRegistrar();
            registerar.get("Test1");
            fail("This should have thrown an exception");
        } catch (OnapCommandNotFound e) {
            //pass  // NOSONAR
        } catch (Exception e) {
            fail("This should have thrown an OnapCommandNotFound exception");
        }
    }

    @Test
    public void onapCommandRegistrationFailedTest() throws OnapCommandException {

        @OnapCommandSchema(name = "Test2", version= "1.0", schema = "sample-test-schema.yaml")
        class Test extends OnapCommand {

            @Override
            protected void run() throws OnapCommandException {

            }

        }

        OnapCommand com = new Test();
        Class<OnapCommand> cmd = (Class<OnapCommand>) com.getClass();
        try {
            registerar.register("Test2", "1.0", cmd);
            registerar.get("Test2");
            fail("This should have thrown an exception");
        } catch (OnapCommandRegistrationFailed e) {
            assertTrue(e.getMessage().contains("0x0018::Command Test2 is failed to register"));
        }
    }

    @Test(expected = OnapCommandHelpFailed.class)
    // For coverage
    public void helpTestException() throws OnapCommandException {
        OnapCommand test = new OnapCommandTest1();
        Class<OnapCommand> cmd = (Class<OnapCommand>) test.getClass();
        registerar = new OnapCommandRegistrar();
        registerar.register("test1", "1.0", cmd);
        String help = registerar.getHelp();
        assertNotNull(help);
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
}

@OnapCommandSchema(name = OnapCommandTest.CMD_NAME, version = "1.0", schema = "sample-test-schema.yaml")
class OnapCommandTest extends OnapCommand {

    public OnapCommandTest() {

    }

    public static final String CMD_NAME = "test";

    protected void run() throws OnapCommandException {

    }

}

@OnapCommandSchema(name = OnapCommandTest1.CMD_NAME, version = "1.0", schema = "test-schema.yaml")
class OnapCommandTest1 extends OnapCommand {

    public OnapCommandTest1() {

    }

    public static final String CMD_NAME = "test1";

    protected void run() throws OnapCommandException {

    }

}
