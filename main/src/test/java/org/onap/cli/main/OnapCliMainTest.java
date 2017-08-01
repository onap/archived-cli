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

package org.onap.cli.main;

import static org.junit.Assert.assertTrue;

import jline.console.ConsoleReader;
import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;

import org.aspectj.lang.annotation.After;
import org.junit.Ignore;
import org.junit.Test;
import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.OnapCommandRegistrar;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.main.utils.OnapCliUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OnapCliMainTest {

    OnapCli cli = null;

    /**
     * Clean up.
     */
    @After(value = "")
    public void cleanup() {
        if (this.cli != null) {
            if (cli.getExitCode() != 0) {
                // Fail test case
            }
        }
    }

    private void handle(String[] args) {
        cli = new OnapCli(args);
        cli.handle();
    }

    @Test
    public void testHelp() {
        this.handle(new String[] { "--help" });
    }

    @Test
    public void testHelpShort() {
        this.handle(new String[] { "-h" });
    }

    @Test
    public void testVersion() {
        this.handle(new String[] { "--version" });
    }

    @Test
    public void testVersionShort() {
        this.handle(new String[] { "--v" });
    }

    @Test
    public void testHelpSampleCommand() {
        this.handle(new String[] { "sample-test", "--help" });
    }

    @Test
    public void testHelpSampleCreateCommand() throws OnapCommandException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader) cl).getURLs();
        for (URL url : urls) {
            if (url.getPath().contains("main/target/test-classes")) {
                File file = new File(url.getPath() + "data");
                if (!file.exists()) {
                    file.mkdirs();
                }
                break;
            }
        }
        this.handle(new String[] { "sample-create", "--help" });
        OnapCommand cmd = OnapCommandRegistrar.getRegistrar().get("sample-create");
        List<String> args = new ArrayList<>(Arrays.asList(new String[] { "sample-create", "--service-name", "test-service", "-i", "ip1", "-i",
                "ip2", "-o", "port1=value1", "-o", "port2=value2" }));
        OnapCliUtils.populateParams(cmd.getParameters(), args);
    }

    @Test
    public void testHelpSampleCommandShort() {
        this.handle(new String[] { "sample-test", "-h" });
    }

    @Test
     public void validateCommands() throws IOException, OnapCommandException {
        Map<String, String> cmdSchemaMap = OnapCommandRegistrar.getRegistrar().getAllCommandToSchemaMap();
        for (String cmdName : cmdSchemaMap.keySet()) {
            System.out.println(
                    "************************* '" + cmdSchemaMap.get(cmdName) + "' *******************************");
            this.handle(new String[] { "schema-validate", "-l", cmdSchemaMap.get(cmdName), "-i"});
        }
    }

    @Test
    public void commandHelpTest() throws OnapCommandException {
        Set<String> cmds = OnapCommandRegistrar.getRegistrar().listCommands();

        for (String cmdName : cmds) {
            System.out.println("************************* '" + cmdName + "' *******************************");
            this.handle(new String[] { cmdName, "-h" });
        }

    }

    @Test
    public void interactiveTest() {
        cli = new OnapCli(new String[] { "-i" });
        boolean isInter = cli.isInteractive();

        assertTrue(isInter);
        cli = new OnapCli(new String[] { "--interactive" });
        isInter = cli.isInteractive();
        assertTrue(isInter);
        cli.getExitCode();

        mockConsole("exit");
        cli.handleInteractive();

        mockConsole("bye");
        cli.handleInteractive();

        mockConsole("clear");
        try {
            cli.handleInteractive();
        } catch (Exception e) {
        }

        mockConsole("microservice-create -h");

        try {
            cli.handleInteractive();
        } catch (Exception e) {
        }

        mockConsoleReader();
        cli.handleInteractive();

    }

    private static void mockConsoleReader() {
        new MockUp<OnapCli>() {
            @Mock
            public ConsoleReader createConsoleReader() throws IOException {
                throw new IOException("Exception mock");
            }
        };
    }

    private static void mockConsole(String input) {
        new MockUp<ConsoleReader>() {
            boolean isMock = true;

            @Mock
            public String readLine(Invocation inv) throws IOException {
                if (isMock) {
                    isMock = false;
                return input;
                } else {
                    return inv.proceed(input);
                }
            }
        };
    }

}
