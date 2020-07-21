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

package org.onap.cli.fw.http;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;
import org.junit.Before;
import org.junit.Test;
import org.onap.cli.fw.http.connect.HttpInput;
import org.onap.cli.fw.http.connect.HttpResult;
import org.onap.cli.fw.http.connect.OnapHttpConnection;
import org.onap.cli.fw.http.error.OnapCommandHttpFailure;


public class OnapHttpConnectionTest {
    HttpInput inp = null;
    OnapHttpConnection con = null;

    @Before
    public void setup() {
        mockHttpRequest(null);
        inp = new HttpInput();
        inp.setMethod("get");
        inp.setBody("body");
        Map<String, String> map1 = new HashMap<>();
        map1.put("header1", "value1");
        inp.setReqHeaders(map1);
        Map<String, String> map2 = new HashMap<>();
        map2.put("query1", "value1");
        inp.setReqQueries(map2);
        Map<String, String> map = new HashMap<>();
        map.put("cookie1", "value1");
        inp.setReqCookies(map);
        inp.setUri("http://192.168.99.10:80");
    }

    @Test(expected = OnapCommandHttpFailure.class)
    public void httpUnSecuredGetExceptionTest() throws OnapCommandHttpFailure {
        new MockUp<CloseableHttpClient>() {
            @Mock
            public CloseableHttpResponse execute(HttpUriRequest request, HttpContext context)
                    throws IOException, ClientProtocolException {

                throw new IOException("IO Exception");
            }
        };
        inp.setMethod("get");
        con = new OnapHttpConnection();
        con.get(inp);

    }

    @Test(expected = OnapCommandHttpFailure.class)
    public void httpUnSecuredPostExceptionTest() throws OnapCommandHttpFailure {
        new MockUp<CloseableHttpClient>() {
            @Mock
            public CloseableHttpResponse execute(HttpUriRequest request, HttpContext context)
                    throws IOException, ClientProtocolException {

                throw new IOException("IO Exception");
            }
        };

        inp.setMethod("post");
        con = new OnapHttpConnection();
        con.post(inp);
    }


    @Test(expected = OnapCommandHttpFailure.class)
    public void httpUnSecuredPostExceptionTest1() throws OnapCommandHttpFailure {
        new MockUp<CloseableHttpClient>() {
            @Mock
            public CloseableHttpResponse execute(HttpUriRequest request, HttpContext context)
                    throws IOException, ClientProtocolException {

                throw new IOException("IO Exception");
            }
        };

        inp.setMethod("post");
        inp.setBinaryData(true);
        con = new OnapHttpConnection();
        con.post(inp);
    }

    @Test(expected = OnapCommandHttpFailure.class)
    public void httpUnSecuredPutExceptionTest() throws OnapCommandHttpFailure {
        new MockUp<CloseableHttpClient>() {
            @Mock
            public CloseableHttpResponse execute(HttpUriRequest request, HttpContext context)
                    throws IOException, ClientProtocolException {

                throw new IOException("IO Exception");
            }
        };
        inp.setMethod("put");
        con = new OnapHttpConnection();
        con.put(inp);
    }

    @Test(expected = OnapCommandHttpFailure.class)
    public void httpUnSecuredDeleteExceptionTest() throws OnapCommandHttpFailure {
        new MockUp<CloseableHttpClient>() {
            @Mock
            public CloseableHttpResponse execute(HttpUriRequest request, HttpContext context)
                    throws IOException, ClientProtocolException {

                throw new IOException("IO Exception");
            }
        };
        inp.setMethod("delete");
        con = new OnapHttpConnection();
        con.delete(inp);
    }

    @Test(expected = IllegalArgumentException.class)
    public void httpUnSecuredOtherExceptionTest() throws OnapCommandHttpFailure {
        new MockUp<CloseableHttpClient>() {
            @Mock
            public CloseableHttpResponse execute(HttpUriRequest request, HttpContext context)
                    throws IOException, ClientProtocolException {

                throw new IOException("IO Exception");
            }
        };
        inp.setMethod("other");
        con = new OnapHttpConnection();
        con.request(inp);
    }

    @Test(expected = OnapCommandHttpFailure.class)
    public void testGetMultipartEntityWithoutMultipartEntityName() throws OnapCommandHttpFailure {
        new MockUp<CloseableHttpClient>() {
            @Mock
            public CloseableHttpResponse execute(HttpUriRequest request, HttpContext context)
                    throws IOException, ClientProtocolException {

                throw new IOException("IO Exception");
            }
        };
        new MockUp<HttpInput>() {

            @Mock
            public boolean isBinaryData() {
                return true;
            }
        };
        Map<String, String> reqHeaders = new HashMap<>();
        reqHeaders.put("Content-Disposition","form-data");
        reqHeaders.put("name","upload");
        reqHeaders.put("filename","upload.txt");
        reqHeaders.put("Content-Type","application/octet-stream");
        reqHeaders.put("Content-Transfer-Encoding","binary");
        inp.setReqHeaders(reqHeaders);
        inp.setMethod("post");
        con = new OnapHttpConnection();
        con.request(inp);
    }

    @Test(expected = OnapCommandHttpFailure.class)
    public void testGetMultipartEntityWithMultipartEntityName() throws OnapCommandHttpFailure {
        new MockUp<CloseableHttpClient>() {
            @Mock
            public CloseableHttpResponse execute(HttpUriRequest request, HttpContext context)
                    throws IOException, ClientProtocolException {

                throw new IOException("IO Exception");
            }
        };
        new MockUp<HttpInput>() {

            @Mock
            public boolean isBinaryData() {
                return true;
            }
        };
        Map<String, String> reqHeaders = new HashMap<>();
        reqHeaders.put("Content-Disposition","form-data");
        reqHeaders.put("name","upload");
        reqHeaders.put("filename","upload.txt");
        reqHeaders.put("Content-Type","application/octet-stream");
        reqHeaders.put("Content-Transfer-Encoding","binary");
        inp.setReqHeaders(reqHeaders);
        inp.setMethod("post");
        inp.setMultipartEntityName("test");
        con = new OnapHttpConnection();
        con.request(inp);
    }

    @Test()
    public void httpUnSecuredCloseExceptionTest() throws IOException {
        inp.setMethod("other");
        con = new OnapHttpConnection();
        con.close();
    }

    @Test
    public void httpSecuredGetExceptionTest() {

        // ProtocolVersion p = new ProtocolVersion("http",1,0);
        // HttpResponse hr = DefaultHttpResponseFactory.INSTANCE.newHttpResponse(p, 200 , null) ;

        new MockUp<CloseableHttpClient>() {
            @Mock
            public CloseableHttpResponse execute(HttpUriRequest request, HttpContext context)
                    throws IOException, ClientProtocolException {

                throw new IOException("IO Exception");
            }
        };
        try {
            HttpInput inp = new HttpInput();
            inp.setMethod("get");
            inp.setBody("body");
            inp.setReqHeaders(new HashMap<String, String>());
            inp.setReqQueries(new HashMap<String, String>());
            inp.setUri("https://192.168.99.10:80");
            OnapHttpConnection con = new OnapHttpConnection();
            con.get(inp);
        } catch (OnapCommandHttpFailure e) {
            assertEquals("0x3001::IO Exception", e.getMessage());
        }
    }

    private static void mockHttpRequest(HttpResult result) {
        new MockUp<OnapHttpConnection>() {
            boolean isMock = false;

            @Mock
            public HttpResult request(Invocation inv, HttpInput input) throws OnapCommandHttpFailure {
                if (isMock) {
                    return result;
                } else {
                    return inv.proceed(input);
                }
            }
        };
    }
}
