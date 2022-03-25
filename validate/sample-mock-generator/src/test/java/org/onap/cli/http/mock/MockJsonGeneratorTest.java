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

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MockJsonGeneratorTest {

    @Test
    public void mocoGenerateTest() throws IOException {
        MockRequest mockRequest = new MockRequest();
        mockRequest.setJson("{\"value\" : \"234sdf-345\"}");
        mockRequest.setMethod("get");
        mockRequest.setUri("http://1.1.1.1:80/getResource");

        MockResponse mockResponse = new MockResponse();
        mockResponse.setStatus(200);
        mockResponse.setJson("{\"value\" : \"234sdf-345\"}");

        assertDoesNotThrow(() -> MockJsonGenerator.generateMocking(mockRequest, mockResponse, "target/test"));
    }
}
