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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.aspectj.lang.annotation.After;
import org.junit.Test;
import org.onap.cli.fw.OnapCommandRegistrar;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.utils.ExternalSchema;
import org.onap.cli.main.OnapCli;
import org.onap.cli.moco.OnapCommandHttpMocoServer;

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
    public void validateCommandSchemas() throws IOException, OnapCommandException {
        OnapCommandRegistrar.getRegistrar().setEnabledProductVersion("cli-1.0");
        for (ExternalSchema sch : OnapCommandRegistrar.getRegistrar().listCommandInfo()) {
            System.out.println(
                    "************************* validate '" + sch.getCmdName() + "' *******************************");
            this.handle(new String[] { "schema-validate", "-l", sch.getSchemaName(), "-i"});
        }
    }

    @Test
    public void usageReadTheDocsTest() throws OnapCommandException {
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

    @Test
    public void validateCommands() throws OnapCommandException {
        OnapCommandHttpMocoServer server = new OnapCommandHttpMocoServer();
        server.verifySamples();
    }

    @Test
    public void getAllSampleFiles() {
        System.out.println("test");
        File root = new File("../../");
        try {
            String sampleFileName = "sample.rst";

            FileUtils.forceDelete(new File(sampleFileName));
            File samples = new File(sampleFileName);

            Stream<Path> walk = Files.walk(root.toPath());
            walk.filter(p -> (p.toString().contains("src/main/resources/onap-cli-schema")))
            .filter(p -> p.toString().endsWith(".yaml"))
            .forEach(p -> appendSample(new File(p.toUri()), samples));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendSample(File src, File dest) {
        System.out.println(src.getAbsolutePath());
        try {
            FileUtils.write(dest, FileUtils.readFileToString(src, "UTF-8"), "UTF-8", true);
            FileUtils.write(dest, "----\n", "UTF-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 }
