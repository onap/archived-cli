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

package org.onap.cli.moco;

import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidSample;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;
import org.onap.cli.fw.utils.OnapCommandDiscoveryUtils;
import org.onap.cli.main.OnapCli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class OnapCommandHttpMocoServer {

    public static final String SAMPLE_PATTERN = "onap-cli-sample/**/";

    public static final String SAMPLE_VERSION = "open_cli_sample_version";
    public static final String SAMPLE_VERSION_1_0 = "1.0";

    public static final String SAMPLE_COMMAND_NAME = "name";
    public static final String SAMPLE_PRODUCT = "version";
    public static final String SAMPLE_LIST = "samples";
    public static final String SAMPLE_DESCRIPTION = "name";
    public static final String SAMPLE_INPUT = "input";
    public static final String SAMPLE_OUTPUT = "output";
    public static final String SAMPLE_MOCO = "moco";

    private static Logger LOG = LoggerFactory.getLogger(OnapCommandHttpMocoServer.class);

    private String samplesToTest = "*.yaml";

    private int port = 8141;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public OnapCommandHttpMocoServer(String samplesToTest) {
        this.samplesToTest = samplesToTest;
    }

    public OnapCommandHttpMocoServer() {
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
        OnapCommandHttpMocoServer onapCommandHttpMocoServer = new OnapCommandHttpMocoServer();
        List<OnapCommandSample> loadSamples;
            try {
                loadSamples = onapCommandHttpMocoServer.loadSamples(file);
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

    private List<Resource> dicoverSampleYamls() {
        Resource[] resources = new Resource [] {};
        try {
            resources = OnapCommandDiscoveryUtils.findResources(SAMPLE_PATTERN + this.samplesToTest);
        } catch (IOException e) {
            LOG.error("Failed to discover the samples", e);
        }

        return Arrays.asList(resources);
    }

    private String getValue(Map<String, ?> map, String prpName) {
         Object o = map.get(prpName);
         if (o != null) {
             return o.toString();
         }

         return "";
    }

    public List<OnapCommandSample> loadSamples(InputStream inputStream, String fileName) throws OnapCommandInvalidSample {
        List<OnapCommandSample> samples = new ArrayList<>();
        Map<String, ?> values = null;
        try {
            values = (Map<String, ?>) new Yaml().load(inputStream);
        } catch (Exception e) {
            throw new OnapCommandInvalidSample(fileName, e);
        }

        OnapCommandSample sample = new OnapCommandSample();

        if (!this.getValue(values, SAMPLE_VERSION).equals(SAMPLE_VERSION_1_0)) {
            throw new OnapCommandInvalidSample(fileName, "Invalid sample version " + this.getValue(values, SAMPLE_VERSION));
        }

        sample.setCommandName(this.getValue(values, SAMPLE_COMMAND_NAME));
        sample.setProduct(this.getValue(values, SAMPLE_PRODUCT));

        //Retrieve the samples
        values = (Map<String, Map<String, String>>) values.get(SAMPLE_LIST);

        for (String s: values.keySet()) {
            Map<String, ?> sMap = (Map<String, ?>)values.get(s);
            sample.setDescription(this.getValue(sMap, SAMPLE_DESCRIPTION));
            sample.setInput(this.getValue(sMap, SAMPLE_INPUT));
            sample.setOutput(this.getValue(sMap, SAMPLE_OUTPUT));
            sample.setMoco(this.getValue(sMap, SAMPLE_MOCO));
            samples.add(sample);
        }

        return samples;
    }

    public List<OnapCommandSample> loadSamples(Resource file) throws OnapCommandInvalidSample {
        try {
            return loadSamples(file.getInputStream(), file.getFilename());
        } catch (IOException e) {
            throw new OnapCommandInvalidSample(file.getFilename(), e);
        }
    }

    public List<OnapCommandSample> loadSamples(File file) throws OnapCommandInvalidSample {
        try {
            return loadSamples(new FileInputStream(file), file.getName());
        } catch (FileNotFoundException e) {
            throw new OnapCommandInvalidSample(file.getName(), e);
        }
    }
    private void verifySample(OnapCommandSample sample) throws OnapCommandException {

        List <String> args = new ArrayList<>();
        args.add(sample.getCommandName());
        args.addAll(Arrays.asList(sample.getInput().split(" ")));

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        OnapCli cli = new OnapCli(args.toArray(new String []{}));
        OnapCommandRegistrar.getRegistrar().setEnabledProductVersion(sample.getProduct());
        cli.handle();

        String output = new String(bo.toByteArray());

        assert cli.getExitCode() == 0;

        assert sample.getOutput().equals(output);
    }
}