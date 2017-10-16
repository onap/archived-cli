/*
 * Copyright 2016-17 Huawei Technologies Co., Ltd.
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

import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandFailedMocoGenerate;
import org.onap.cli.fw.http.HttpInput;
import org.onap.cli.fw.http.HttpResult;

public class MockJsonGeneratorTest {

    @Test
    public void mocoGenerateTest() throws OnapCommandFailedMocoGenerate {
        HttpInput httpInput = new HttpInput();
        httpInput.setBody("{\"value\" : \"234sdf-345\"}");
        httpInput.setMethod("get");
        httpInput.setUri("http://1.1.1.1:80/getResource");

        HttpResult httpResult = new HttpResult();
        httpResult.setStatus(200);
        httpResult.setBody("{\"value\" : \"234sdf-345\"}");

        MockJsonGenerator.generateMocking(httpInput, httpResult, "test");
    }

    @Test(expected=OnapCommandFailedMocoGenerate.class)
    public void mocoGenerateFailedInvalidBodyTest() throws OnapCommandFailedMocoGenerate {
        HttpInput httpInput = new HttpInput();
        httpInput.setBody("{\"value\" : \"234sdf-345\"");
        httpInput.setMethod("get");
        httpInput.setUri("http://1.1.1.1:80/getResource");

        HttpResult httpResult = new HttpResult();
        httpResult.setStatus(200);
        httpResult.setBody("{\"value\" : \"234sdf-345\"");

        MockJsonGenerator.generateMocking(httpInput, httpResult, "test");
    }

    @Test(expected=OnapCommandFailedMocoGenerate.class)
    public void mocoGenerateFailedInvalidUrlTest() throws OnapCommandFailedMocoGenerate {
        HttpInput httpInput = new HttpInput();
        httpInput.setBody("{\"value\" : \"234sdf-345\"");
        httpInput.setMethod("get");
        httpInput.setUri("http://1.1.1.1:80:invalid");

        HttpResult httpResult = new HttpResult();
        httpResult.setStatus(200);
        httpResult.setBody("{\"value\" : \"234sdf-345\"");

        MockJsonGenerator.generateMocking(httpInput, httpResult, "test");
    }
}
