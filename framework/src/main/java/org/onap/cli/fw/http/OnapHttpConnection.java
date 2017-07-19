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

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.error.OnapCommandHttpFailure;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Helps to make http connection.<br>
 */
public class OnapHttpConnection {

    private HttpClient httpClient = null;

    private String xauthToken = null;

    protected boolean debug = false;

    private String debugDetails = "";

    public static class TrustAllX509TrustManager implements X509TrustManager {

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            // No need to implement.
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            // No need to implement.
        }
    }

    /**
     * OnapHttpConnection Constructor.
     *
     * @param isSecured
     *            boolean
     * @param debug
     *            boolean
     * @throws OnapCommandHttpFailure
     *             exception
     */
    public OnapHttpConnection(boolean isSecured, boolean debug) throws OnapCommandHttpFailure {
        try {
            if (isSecured) {
                SSLContext sslContext = SSLContext.getInstance(Constants.SSLCONTEST_TLS);
                sslContext.init(null, new TrustManager[] { new TrustAllX509TrustManager() },
                        new java.security.SecureRandom());
                X509HostnameVerifier hostnameVerifier = new AllowAllHostnameVerifier();
                Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                        .<ConnectionSocketFactory>create()
                        .register("https", new SSLConnectionSocketFactory(sslContext, hostnameVerifier)).build();
                HttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

                this.httpClient = HttpClients.custom().setConnectionManager(connManager)
                        .setRedirectStrategy(new LaxRedirectStrategy()).build();
            } else {
                this.httpClient = HttpClients.createDefault();
            }
        } catch (Exception e) {
            throw new OnapCommandHttpFailure(e);
        }

        this.debug = debug;
    }

    public String getDebugInfo() {
        return this.debugDetails;
    }

    public void setAuthToken(String token) {
        this.xauthToken = token;
    }

    public String getAuthToken() {
        return this.xauthToken;
    }

    private Map<String, String> getHttpHeaders(HttpResponse resp) {
        Map<String, String> result = new HashMap<>();

        Header[] hs = resp.getAllHeaders();
        for (int i = 0; i < hs.length; i++) {
            result.put(hs[i].getName(), hs[i].getValue());
        }

        return result;
    }

    private String getResponseBody(HttpResponse resp) throws OnapCommandHttpFailure {
        if (resp.getEntity() == null) {
            return null;
        }
        try {
            String body = EntityUtils.toString(resp.getEntity(), StandardCharsets.UTF_8);
            EntityUtils.consume(resp.getEntity());
            return body;
        } catch (IOException e) {
            throw new OnapCommandHttpFailure(e);
        }
    }

    private StringEntity getStringEntity(HttpInput input) {
        return new StringEntity(input.getBody(), StandardCharsets.UTF_8);
    }

    /**
     * Post. <br>
     *
     * @param input
     *            HttpInput Obj
     * @return HttpResult
     * @throws OnapCommandHttpFailure
     *             http failure
     */
    public HttpResult post(final HttpInput input) throws OnapCommandHttpFailure {
        input.setMethod("post");
        return this.request(input);
    }

    /**
     * Get. <br>
     *
     * @param input
     *            input request
     * @return HttpResult
     * @throws OnapCommandHttpFailure
     *             excpetion
     */
    public HttpResult get(final HttpInput input) throws OnapCommandHttpFailure {
        input.setMethod("get");
        return this.request(input);
    }

    /**
     * Put. <br>
     *
     * @param input
     *            input request
     * @return HttpResult
     * @throws OnapCommandHttpFailure
     *             Exception
     */
    public HttpResult put(final HttpInput input) throws OnapCommandHttpFailure {
        input.setMethod("put");
        return this.request(input);
    }

    /**
     * Delete. <br>
     *
     * @param input
     *            input request
     * @return HttpResult
     * @throws OnapCommandHttpFailure
     *             exception
     */
    public HttpResult delete(final HttpInput input) throws OnapCommandHttpFailure {
        input.setMethod("delete");
        return this.request(input);
    }

    private void addCommonHeaders(HttpInput input) {
        if (!input.isBinaryData()) {
        input.getReqHeaders().put("Content-Type", Constants.APPLICATION_JSON);
        }
        input.getReqHeaders().put("Accept", Constants.APPLICATION_JSON);
        if (this.xauthToken != null) {
            input.getReqHeaders().put(Constants.X_AUTH_TOKEN, this.xauthToken);
        }
    }

    private void addCommonCookies(CookieStore cookieStore) {
        Cookie cookie = new BasicClientCookie(Constants.X_AUTH_TOKEN, this.xauthToken);
        cookieStore.addCookie(cookie);
    }

    private void updateResultFromCookies(HttpResult result, List<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            result.getRespCookies().put(cookie.getName(), cookie.getValue());
        }
    }

    private String getDomain(String url) {
        try {
            return new URL(url).getHost();
        } catch (MalformedURLException e) {
            // url is always proper !!
            return url;
        }
    }

    private void updateInputFromCookies(HttpInput input, CookieStore cookieStore) {
        addCommonCookies(cookieStore);
        for (String cookieName : input.getReqCookies().keySet()) {
            BasicClientCookie cookie = new BasicClientCookie(cookieName, input.getReqCookies().get(cookieName));
            cookie.setDomain(this.getDomain(input.getUri()));
            cookieStore.addCookie(cookie);
        }

    }

    /**
     * Handles http method requests.
     *
     * @param input
     *            HttpInput
     * @return HttpResult
     * @throws OnapCommandHttpFailure
     *             exception
     */
    public HttpResult request(HttpInput input) throws OnapCommandHttpFailure {
        this.addCommonHeaders(input);

        HttpRequestBase requestBase = null;
        if ("post".equals(input.getMethod())) {
            HttpPost httpPost = new HttpPost();
            if (input.isBinaryData()) {
                httpPost.setEntity(getMultipartEntity(input));
            } else {
            httpPost.setEntity(this.getStringEntity(input));
            }
            requestBase = httpPost;
        } else if ("put".equals(input.getMethod())) {
            HttpPut httpPut = new HttpPut();
            httpPut.setEntity(this.getStringEntity(input));
            requestBase = httpPut;
        } else if ("get".equals(input.getMethod())) {
            requestBase = new HttpGet();
        } else if ("delete".equals(input.getMethod())) {
            requestBase = new HttpDelete();
        } else {
            throw new IllegalArgumentException("Invalid HTTP method");
        }

        requestBase.setURI(URI.create(input.getUri()));

        for (Entry<String, String> h : input.getReqHeaders().entrySet()) {
            requestBase.addHeader(h.getKey(), h.getValue());
        }

        HttpResult result = new HttpResult();

        try {
            this.debugDetails = "";
            CookieStore cookieStore = new BasicCookieStore();
            updateInputFromCookies(input, cookieStore);
            HttpContext localContext = new BasicHttpContext();
            localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

            HttpResponse resp = this.httpClient.execute(requestBase, localContext);
            String respContent = this.getResponseBody(resp);
            result.setBody(respContent);
            result.setStatus(resp.getStatusLine().getStatusCode());
            result.setRespHeaders(this.getHttpHeaders(resp));
            this.updateResultFromCookies(result, cookieStore.getCookies());
        } catch (ParseException | IOException e) {
            throw new OnapCommandHttpFailure(e);
        } finally {
            if (this.debug) {
                this.debugDetails = input + "" + result;
            }
        }

        return result;
    }

    public void close() {
        this.setAuthToken(null);
    }

    private HttpEntity getMultipartEntity(HttpInput input) {
        FileBody fileBody = new FileBody(new File(input.getBody().trim()));
        MultipartEntity multipartEntity = new MultipartEntity();
        multipartEntity.addPart("file", fileBody);
        return multipartEntity;
    }
}
