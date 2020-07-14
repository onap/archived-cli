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

package org.onap.cli.fw.http;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;
import org.onap.cli.fw.http.connect.HttpInput;
import org.onap.cli.fw.http.connect.HttpResult;
import static org.junit.Assert.assertEquals;

public class HttpInputOutputTest {

    @Test
    public void httpInputTest() {
        HttpInput inp = new HttpInput();
        inp.setBody("body");
        inp.setMethod("method");
        inp.setReqCookies(null);
        inp.setReqHeaders(null);
        inp.setReqQueries(null);
        inp.setUri("uri");

        assertTrue("body".equals(inp.getBody()) && "method".equals(inp.getMethod()) && null == inp.getReqCookies()
                && inp.getReqHeaders().isEmpty() && inp.getReqQueries().isEmpty() && "uri".equals(inp.getUri()));

        inp.setReqCookies(new HashMap<String, String>());
        inp.setReqHeaders(new HashMap<String, String>());
        inp.setReqQueries(new HashMap<String, String>());

        String msg = "\nURL: uri\nMethod: method\nRequest Queries: {}\nRequest Body: body\nRequest Headers: "
                + "{}\nRequest Cookies: {}\nbinaryData=false\nContext={}";
        assertEquals(msg, inp.toString());
    }

    @Test
    public void httpResultTest() {
        HttpResult out = new HttpResult();
        out.setBody("body");
        out.setRespCookies(null);
        out.setRespHeaders(null);
        out.setStatus(205);

        assertTrue("body".equals(out.getBody()) && null == out.getRespCookies() && null == out.getRespHeaders()
                && 205 == out.getStatus());

        out.setRespCookies(new HashMap<String, String>());
        out.setRespHeaders(new HashMap<String, String>());
        out.setStatus(200);
        assertEquals("\nHTTP Status: 200\nResponse Body: body\nResponse Headers: {}\nResponse Cookies: {}"
                , out.toString());
    }

}
