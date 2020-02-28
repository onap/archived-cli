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

import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;


public class MockJsonGenerator {
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    public static void generateMocking(MockRequest mockRequest, MockResponse mockResponse,
                                       String jsonFilePath) throws IOException {

        MockObject mockObject = new MockObject();
        mockObject.setRequest(mockRequest);
        mockObject.setResponse(mockResponse);

        try(FileWriter writer = new FileWriter(jsonFilePath)){
            gson.toJson(Arrays.asList(mockObject), writer);
        }catch (Exception e){ // NOSONAR
            //
        }
    }
}
