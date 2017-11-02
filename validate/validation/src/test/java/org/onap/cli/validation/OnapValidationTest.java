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

package org.onap.cli.validation;

import java.io.IOException;

import org.aspectj.lang.annotation.After;
import org.junit.Test;
import org.onap.cli.fw.OnapCommandRegistrar;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.utils.ExternalSchema;
import org.onap.cli.main.OnapCli;
import org.onap.cli.moco.OnapCommandHttpMocoServer;

public class OnapValidationTest {

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
    public void validateCommandSchemas() throws IOException, OnapCommandException {
        OnapCommandRegistrar.getRegistrar().setEnabledProductVersion("cli-1.0");
        for (ExternalSchema sch : OnapCommandRegistrar.getRegistrar().listCommandInfo()) {
            System.out.println(
                    "************************* validate '" + sch.getCmdName() + "' *******************************");
            this.handle(new String[] { "schema-validate", "-l", sch.getSchemaName(), "-i"});
        }
    }

    @Test
    public void genReadTheDocs() throws OnapCommandException {
        for (String version: OnapCommandRegistrar.getRegistrar().getAvailableProductVersions()) {
            OnapCommandRegistrar.getRegistrar().setEnabledProductVersion(version);
            System.out.println(version);
            System.out.println("==========================\n\n");
            int i = 1;
            for (ExternalSchema sch : OnapCommandRegistrar.getRegistrar().listCommandInfo()) {
                if (sch.getCmdVersion().equals(version)) {
                    System.out.println("[" + i++ + "] " + sch.getCmdName());
                    System.out.println("-----------------------------------------------\n\n");
                    this.handle(new String[] { sch.getCmdName(), "-h"});
                    System.out.println("\n");
                }
            }
        }
    }

    @Ignore
    @Test
    public void validateCommands() throws OnapCommandException {
        OnapCommandHttpMocoServer server = new OnapCommandHttpMocoServer();
        server.verifySamples();
    }

 }
