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
package org.onap.cli.fw.http.mock;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Moco;
import com.github.dreamhead.moco.ResponseHandler;
import com.github.dreamhead.moco.Runner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandDiscoveryFailed;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.http.conf.OnapCommandHttpConstants;
import org.onap.cli.fw.utils.OnapCommandDiscoveryUtils;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.dreamhead.moco.Runner.runner;

public class MocoServer {

    private Runner runner;
    private Map<String, Object> mocoServerConfigs = new HashMap();
    private static Gson gson = new GsonBuilder().serializeNulls().create();


    public MocoServer(String mockFile) throws OnapCommandException {
        Resource resource = null;

        try {
            resource = OnapCommandDiscoveryUtils.findResource(mockFile,
                    OnapCommandConstants.VERIFY_SAMPLES_MOCK_PATTERN);
        } catch (IOException e) {
            throw new OnapCommandDiscoveryFailed(mockFile, e);
        }

        List<Map<String, ?>> stringMap = null;
        try {
            stringMap = (List<Map<String, ?>>) new Yaml().load(resource.getInputStream());
        } catch (IOException e) {
            throw new OnapCommandException("Invalid mocking file" + mockFile, e);
        }
        if(!stringMap.isEmpty()) {
            Map<String, ?> jsonConfigs = stringMap.get(0);
            Map<String, String> request = (Map<String, String>) jsonConfigs.get(OnapCommandHttpConstants.VERIFY_REQUEST);
            mocoServerConfigs.put(OnapCommandHttpConstants.VERIFY_REQUEST_URI, request.get(OnapCommandHttpConstants.VERIFY_REQUEST_URI));

            Map<String, String> response = (Map<String, String>) jsonConfigs.get(OnapCommandHttpConstants.VERIFY_RESPONSE);
            mocoServerConfigs.put(OnapCommandHttpConstants.VERIFY_RESPONSE_STATUS, response.get(OnapCommandHttpConstants.VERIFY_RESPONSE_STATUS));

            if(response.get(OnapCommandHttpConstants.VERIFY_RESPONSE_JSON) != null) {
                try {
                    mocoServerConfigs.put(OnapCommandHttpConstants.VERIFY_RESPONSE_JSON,
                            gson.toJson(response.get(OnapCommandHttpConstants.VERIFY_RESPONSE_JSON)));
                } catch (Exception e) { // NOSONAR
                   throw new OnapCommandException("Invalid mocking file" + mockFile, e);
                }
            }
        }
    }

    public void start() {
        HttpServer server = Moco.httpServer(Integer.parseInt(
                OnapCommandConfig.getPropertyValue(OnapCommandHttpConstants.VERIFY_MOCO_PORT)));

        List<ResponseHandler> responseHandlers = new ArrayList<>();

        if (mocoServerConfigs.containsKey(OnapCommandHttpConstants.VERIFY_RESPONSE_JSON)) {
            responseHandlers.add(Moco.with(mocoServerConfigs.get(OnapCommandHttpConstants.VERIFY_RESPONSE_JSON).toString()));
        }
        responseHandlers.add(Moco.status((Integer) mocoServerConfigs.get(OnapCommandHttpConstants.VERIFY_RESPONSE_STATUS)));

        server.request(Moco.by(Moco.uri((String) mocoServerConfigs.get(OnapCommandHttpConstants.VERIFY_REQUEST_URI))))
                .response(Moco.header(OnapCommandHttpConstants.VERIFY_CONTENT_TYPE, OnapCommandHttpConstants.VERIFY_CONTENT_TYPE_VALUE),
                        responseHandlers.toArray(new ResponseHandler[responseHandlers.size()]));

        runner = runner(server);
        runner.start();
    }

    public void stop() {
        runner.stop();
    }
}
