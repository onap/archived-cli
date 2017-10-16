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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.onap.cli.fw.conf.OnapCommandConfg;
import org.onap.cli.fw.error.OnapCommandFailedMocoGenerate;
import org.onap.cli.fw.http.HttpInput;
import org.onap.cli.fw.http.HttpResult;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class MockJsonGenerator {
    public static void generateMocking(HttpInput httpInput, HttpResult httpResult,
            String jsonFileName) throws OnapCommandFailedMocoGenerate {

        MockRequest mockRequest = new MockRequest();
        mockRequest.setMethod(httpInput.getMethod());
        mockRequest.setUri(httpInput.getUri());
        mockRequest.setHeaders(httpInput.getReqHeaders());
        mockRequest.setJson(httpInput.getBody());

        MockResponse mockResponse = new MockResponse();
        mockResponse.setStatus(httpResult.getStatus());
        mockResponse.setJson(httpResult.getBody());

        MockObject mockObject = new MockObject();
        mockObject.setRequest(mockRequest);
        mockObject.setResponse(mockResponse);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            writer.writeValue(new File(OnapCommandConfg.getMocoTargetFolder() + "/" + jsonFileName + "-" + timeStamp + "-moco.json"),
                    Arrays.asList(mockObject));
        } catch (IOException error ) {
            throw new OnapCommandFailedMocoGenerate(jsonFileName, error);
        }
    }
}
