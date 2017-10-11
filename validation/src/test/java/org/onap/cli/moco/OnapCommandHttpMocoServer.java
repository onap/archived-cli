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

import static com.github.dreamhead.moco.MocoJsonRunner.jsonHttpServer;
import static com.github.dreamhead.moco.Runner.runner;
import static com.github.dreamhead.moco.Moco.pathResource;
import static com.github.dreamhead.moco.Moco.file;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.onap.cli.fw.OnapCommandRegistrar;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidSample;
import org.onap.cli.fw.utils.OnapCommandUtils;
import org.onap.cli.main.OnapCli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;

public class OnapCommandHttpMocoServer {

    public static final String SAMPLE_PATTERN = "onap-cli-sample/**/";

    public static final String SAMPLE_VERSION = "onap_cli_sample_version";
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

    private List<Resource> dicoverSampleYamls() {
        Resource[] resources = new Resource [] {};
        try {
            resources = OnapCommandUtils.getExternalResources(SAMPLE_PATTERN + this.samplesToTest);
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

    private List<OnapCommandSample> loadSamples(Resource file) throws OnapCommandInvalidSample {

        List<OnapCommandSample> samples = new ArrayList<>();
        Map<String, ?> values = null;
        try {
            values = (Map<String, ?>) new Yaml().load(file.getInputStream());
        } catch (Exception e) {
            throw new OnapCommandInvalidSample(file.getFilename(), e);
        }

        OnapCommandSample sample = new OnapCommandSample();

        if (!this.getValue(values, SAMPLE_VERSION).equals(SAMPLE_VERSION_1_0)) {
            throw new OnapCommandInvalidSample(file.getFilename(), "Invalid sample version " + this.getValue(values, SAMPLE_VERSION));
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

        //mrkanag uncomment following lines once moco server setup is done
        //assert cli.getExitCode() == 0;

        //assert sample.getOutput().equals(output);
    }

    public void verifySamples() throws OnapCommandException {
        for (Resource rsc : this.dicoverSampleYamls()) {
              for(OnapCommandSample sample: this.loadSamples(rsc)) {

                  if (!sample.getMoco().isEmpty()) {
                      HttpServer server = jsonHttpServer(this.getPort(), pathResource(sample.getMoco()));
                      Runner r = runner(server);
                        r.start();

                        this.verifySample(sample);

                        r.stop();
                  }
              }
        }
    }
}