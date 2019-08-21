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
package org.onap.cli.http.mock;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class MockJsonGenerator {
    public static void generateMocking(MockRequest mockRequest, MockResponse mockResponse,
            String jsonFilePath) throws IOException {

        MockObject mockObject = new MockObject();
        mockObject.setRequest(mockRequest);
        mockObject.setResponse(mockResponse);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(jsonFilePath),
                Arrays.asList(mockObject));
    }
}