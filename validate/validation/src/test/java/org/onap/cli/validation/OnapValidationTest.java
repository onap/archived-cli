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

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;
import org.onap.cli.fw.schema.OnapCommandSchemaInfo;
import org.onap.cli.main.OnapCli;
import org.onap.cli.moco.OnapCommandHttpMocoServer;
import org.onap.cli.moco.OnapCommandSample;

public class OnapValidationTest {

    OnapCli cli = null;

    private void handle(String[] args) {
        cli = new OnapCli(args);
        cli.handle();
    }

    @Test
    public void validateCommandSchemas() throws IOException, OnapCommandException {
        for (String version: OnapCommandRegistrar.getRegistrar().getAvailableProductVersions()) {
            OnapCommandRegistrar.getRegistrar().setEnabledProductVersion(version);
            System.out.println(version);
            System.out.println("==========================\n\n");
            for (OnapCommandSchemaInfo sch : OnapCommandRegistrar.getRegistrar().listCommandInfo()) {
                if (sch.isIgnore()) {
                    continue;
                }
                if (sch.getProduct() != null && sch.getProduct().equals(version)) {
                    System.out.println(
                    "************************* validate '" + sch.getCmdName() + "' *******************************");
                    OnapCommandRegistrar.getRegistrar().setEnabledProductVersion("open-cli");
                    this.handle(new String[] { "schema-validate", "-l", sch.getSchemaName(), "-i"});
                }
            }
        }
    }

    @Test
    public void genReadTheDocs() throws OnapCommandException {
        for (String version: OnapCommandRegistrar.getRegistrar().getAvailableProductVersions()) {
            OnapCommandRegistrar.getRegistrar().setEnabledProductVersion(version);
            System.out.println(version);
            System.out.println("==========================\n\n");
            int i = 1;
            for (OnapCommandSchemaInfo sch : OnapCommandRegistrar.getRegistrar().listCommandInfo()) {
                if (sch.isIgnore()) {
                    continue;
                }
                if (sch.getProduct() != null && sch.getProduct().equals(version)) {
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
    public void verifyCommandResults() throws OnapCommandException {
        OnapCommandHttpMocoServer server = new OnapCommandHttpMocoServer();
        server.verifySamples();
    }

    @Test
    public void collectSampleYamlTest() {
        try {
            File root = new File("../../");
            String sampleFileName = "target/sample.rst";

            FileUtils.deleteQuietly(new File(sampleFileName));
            Map<String, List<OnapCommandSample>> discoveredYamls = OnapCommandHttpMocoServer.discoverYamls(root);

            writeSamples(new File(sampleFileName), discoveredYamls);
        } catch (IOException e) {
            fail();
        }
    }

    private void writeSamples(File dest, Map<String, List<OnapCommandSample>> cliProductSamples) throws IOException {

        for (String product: cliProductSamples.keySet()) {
            FileUtils.write(dest, "\n" + product, "UTF-8", true);
            FileUtils.write(dest, "\n========\n\n", "UTF-8", true);

            for(OnapCommandSample sample: cliProductSamples.get(product)) {
                FileUtils.write(dest, "\n\n" + sample.getCommandName(), "UTF-8", true);
                FileUtils.write(dest, "\n" + String.join("", Collections.nCopies(sample.getCommandName().length(), "-"))+ "\n", "UTF-8", true);

                if (!sample.getInput().isEmpty()) {
                    FileUtils.write(dest, "\ninput::\n\n " + sample.getInput() + "\n", "UTF-8", true);
                }

                if (!sample.getOutput().isEmpty()) {
                    FileUtils.write(dest, "\noutput::\n\n " + sample.getOutput().replaceAll("\n", "\n ").trim(), "UTF-8", true);
                }
            }
        }
    }

 }
