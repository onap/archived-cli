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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidSample;
import org.onap.cli.fw.error.OnapCommandProductVersionInvalid;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;
import org.onap.cli.fw.schema.OnapCommandSchemaInfo;
import org.onap.cli.http.mock.utils.JsonUtil;
import org.onap.cli.main.OnapCli;
import org.onap.cli.main.conf.OnapCliConstants;
import org.onap.cli.moco.OnapCommandSample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;


import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class OnapValidationTest {

    public static final String SAMPLE_VERSION = "open_cli_sample_version";
    public static final String SAMPLE_VERSION_1_0 = "1.0";

    public static final String SAMPLE_COMMAND_NAME = "name";
    public static final String SAMPLE_PRODUCT = "version";
    public static final String SAMPLE_LIST = "samples";
    public static final String SAMPLE_DESCRIPTION = "name";
    public static final String SAMPLE_INPUT = "input";
    public static final String SAMPLE_OUTPUT = "output";
    public static final String SAMPLE_MOCO = "moco";

    OnapCli cli = new OnapCli();

    private static Logger LOG = LoggerFactory.getLogger(OnapValidationTest.class);

    private void handle(String[] args) {
        cli.resetExitCode();
        cli.setArgs(args);
        cli.handle();
    }

    /**
     * Add Test annotation to this method for running the stability and perf testing
     * @throws OnapCommandProductVersionInvalid
     * @throws OnapCommandException
     * @throws InterruptedException
     */
    //@Test
    public void stablitityPerformanceBeijingTest() throws OnapCommandProductVersionInvalid, OnapCommandException {
        System.out.println(new Date());
        while (true) {
            OnapCommandRegistrar.getRegistrar().setEnabledProductVersion("onap-beijing");
            for (String cmd : OnapCommandRegistrar.getRegistrar().listCommandsForEnabledProductVersion()) {
                System.out.println(cmd + ":");
                Date start = new Date();
                this.handle(new String[] { cmd, "-V"});
                Date end = new Date();
                System.out.println("[ Total time " + (end.getTime() - start.getTime()) + " ms ]\n");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(new Date());
                }
            }
        }
    }

    @Test
    public void validateCommandSchemas() throws OnapCommandException {
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
            List<OnapCommandSchemaInfo> cmds = OnapCommandRegistrar.getRegistrar().listCommandInfo();
            Collections.sort(cmds);
            for (OnapCommandSchemaInfo sch : cmds) {
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

    @Test
    public void collectSampleYamlTest() {
        try {
            File root = new File("../../");
            String sampleFileName = "target/sample.rst";

            FileUtils.deleteQuietly(new File(sampleFileName));
            Map<String, List<OnapCommandSample>> discoveredYamls = discoverYamls(root);

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

    public static Map<String, List<OnapCommandSample>> discoverYamls(File path) throws IOException {
        Map<String, List<OnapCommandSample>> cliProductSamples = new HashMap<>();

        Stream<Path> walk = Files.walk(path.toPath());
        walk.filter(p -> (p.toString().contains("src/test/resources/onap-cli-sample")))
                .filter(p -> p.toString().endsWith("sample.yaml"))
                .forEach(p -> {
                    collectSamples(new File(p.toUri()), cliProductSamples);
                });

        return cliProductSamples;
    }

    private static void collectSamples(File file, Map<String, List<OnapCommandSample>> result) {
        List<OnapCommandSample> loadSamples;
        try {
            loadSamples = loadSamples(file);
            loadSamples.stream().forEach(sample -> {
                if (!result.containsKey(sample.getProduct())) {
                    result.put(sample.getProduct(), new ArrayList<>());
                }
                result.get(sample.getProduct()).add(sample);
            });
        } catch (OnapCommandInvalidSample e) {
            LOG.error("Failed to read sample file", e);
        }
    }

    private static List<OnapCommandSample> loadSamples(File file) throws OnapCommandInvalidSample {
        try {
            return loadSamples(new FileInputStream(file), file.getName());
        } catch (FileNotFoundException e) {
            throw new OnapCommandInvalidSample(file.getName(), e);
        }
    }

    private static List<OnapCommandSample> loadSamples(InputStream inputStream, String fileName) throws OnapCommandInvalidSample {
        List<OnapCommandSample> samples = new ArrayList<>();
        Map<String, ?> values = null;
        try {
            values = (Map<String, ?>) new Yaml().load(inputStream);
        } catch (Exception e) {
            throw new OnapCommandInvalidSample(fileName, e);
        }

        OnapCommandSample sample = new OnapCommandSample();

        if (!getValue(values, SAMPLE_VERSION).equals(SAMPLE_VERSION_1_0)) {
            throw new OnapCommandInvalidSample(fileName, "Invalid sample version " + getValue(values, SAMPLE_VERSION));
        }

        sample.setCommandName(getValue(values, SAMPLE_COMMAND_NAME));
        sample.setProduct(getValue(values, SAMPLE_PRODUCT));

        //Retrieve the samples
        values = (Map<String, Map<String, String>>) values.get(SAMPLE_LIST);

        for (String s: values.keySet()) {
            Map<String, ?> sMap = (Map<String, ?>)values.get(s);
            sample.setDescription(getValue(sMap, SAMPLE_DESCRIPTION));
            sample.setInput(getValue(sMap, SAMPLE_INPUT));
            sample.setOutput(getValue(sMap, SAMPLE_OUTPUT));
            sample.setMoco(getValue(sMap, SAMPLE_MOCO));
            samples.add(sample);
        }

        return samples;
    }

    private static String getValue(Map<String, ?> map, String prpName) {
        Object o = map.get(prpName);
        if (o != null) {
            return o.toString();
        }

        return "";
    }

    @Test
    public void testVerify() throws OnapCommandException {
        OnapCommandRegistrar.getRegistrar().setEnabledProductVersion("open-cli");
        OnapCli onapCli = new OnapCli(new String[]{"sample-test-verify", "--verify"});
        onapCli.handle();
        assertEquals(OnapCliConstants.EXIT_SUCCESS, onapCli.getExitCode());
    }

    @Test
    public void testOnapCommandSchemaInfoForUnknownFields(){
        OnapCommandSchemaInfo ocsi = new OnapCommandSchemaInfo();
        String testExp = "{\"schemaName\":\"testSchema\",\"schemaURI\":\"testUri\",\"unknownField\":\"unknown\"}";
        ocsi= (OnapCommandSchemaInfo) JsonUtil.convertJsonStringToClassType(testExp,OnapCommandSchemaInfo.class);
        assertNotNull(ocsi);
    }

}
