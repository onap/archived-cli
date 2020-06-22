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

package org.onap.cli.fw.http.connect;

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

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.onap.cli.fw.http.conf.OnapCommandHttpConstants;
import org.onap.cli.fw.http.error.OnapCommandHttpFailure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helps to make http connection.<br>
 */
public class OnapHttpConnection {

    private static Logger log = LoggerFactory.getLogger(OnapHttpConnection.class);

    private CloseableHttpClient httpClient = null;

    Map<String, String> mapCommonHeaders = new HashMap<> ();

    public static class TrustAllX509TrustManager implements X509TrustManager {

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) { //NOSONAR
            // No need to implement.
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) { //NOSONAR
            // No need to implement.
        }
    }

    private void initHttpClient(boolean isSecured) throws OnapCommandHttpFailure {
        if (this.httpClient == null) {
            try {
                if (isSecured) {
                    SSLContext sslContext = SSLContext.getInstance(OnapCommandHttpConstants.SSLCONTEST_TLS);
                    sslContext.init(null, new TrustManager[] { new TrustAllX509TrustManager() },
                            new java.security.SecureRandom());
                    HostnameVerifier hostnameVerifier = new NoopHostnameVerifier();
                    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                            .<ConnectionSocketFactory>create()
                            .register("https", new SSLConnectionSocketFactory(sslContext, hostnameVerifier)).build();
                    HttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);  // NOSONAR

                    this.httpClient = HttpClients.custom().setConnectionManager(connManager)
                            .setRedirectStrategy(new LaxRedirectStrategy()).build();
                } else {
                    this.httpClient = HttpClients.createDefault();  // NOSONAR
                }
            } catch (Exception e) {
                throw new OnapCommandHttpFailure(e);
            }
        }
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

    public void setCommonHeaders(Map<String, String> headers) {
        this.mapCommonHeaders = headers;
    }

    private void addCommonHeaders(HttpInput input) {
        if (!input.isBinaryData() && !input.getReqHeaders().containsKey("Content-Type")) {
            input.getReqHeaders().put("Content-Type", OnapCommandHttpConstants.APPLICATION_JSON);
        }

        if (!input.getReqHeaders().containsKey("Accept")) {
            input.getReqHeaders().put("Accept", OnapCommandHttpConstants.APPLICATION_JSON);
        }

        for (Entry<String, String> header : this.mapCommonHeaders.entrySet()) {
            if (!input.getReqHeaders().containsKey(header.getKey()))
                input.getReqHeaders().put(header.getKey(), header.getValue());
        }
    }

    private void addCommonCookies(HttpInput input, CookieStore cookieStore) {
         for (Entry<String, String> header : this.mapCommonHeaders.entrySet()) {
             //take care of overriden headers in OCS YAML
                String value = input.getReqHeaders().getOrDefault(header.getKey(),
                         header.getValue());
             Cookie cookie = new BasicClientCookie(header.getKey(), value);
             cookieStore.addCookie(cookie);
         }
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
        addCommonCookies(input, cookieStore);
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
            if (input.isBinaryData() || !(input.getMultiparts().isEmpty())) {
                httpPost.setEntity(getMultipartEntity(input));
            } else {
                httpPost.setEntity(this.getStringEntity(input));
            }
            requestBase = httpPost;
        } else if ("put".equals(input.getMethod())) {
            HttpPut httpPut = new HttpPut();
            httpPut.setEntity(this.getStringEntity(input));
            requestBase = httpPut;
        } else if ("patch".equals(input.getMethod())) {
            HttpPatch httpPatch = new HttpPatch();
            httpPatch.setEntity(this.getStringEntity(input));
            requestBase = httpPatch;
        } else if ("get".equals(input.getMethod())) {
            requestBase = new HttpGet();
        } else if ("delete".equals(input.getMethod())) {
            if (!input.getBody().isEmpty()) {
                HttpDeleteWithBody httpDelete = new HttpDeleteWithBody();
                httpDelete.setEntity(new StringEntity(input.getBody(), ContentType.APPLICATION_JSON));
                requestBase = httpDelete;
            } else {
                requestBase = new HttpDelete();
            }
        } else {
            throw new IllegalArgumentException("Invalid HTTP method");
        }

        requestBase.setURI(URI.create(input.getUri()));
        requestBase.setConfig(RequestConfig.custom()
                .setSocketTimeout(600000).setConnectTimeout(600000).build());

        for (Entry<String, String> h : input.getReqHeaders().entrySet()) {
            requestBase.addHeader(h.getKey(), h.getValue());
        }

        HttpResult result = new HttpResult();

        try {
            CookieStore cookieStore = new BasicCookieStore();
            updateInputFromCookies(input, cookieStore);
            HttpContext localContext = new BasicHttpContext();
            localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);

            this.initHttpClient(input.getUri().startsWith("https"));

            HttpResponse resp = this.httpClient.execute(requestBase, localContext);
            String respContent = this.getResponseBody(resp);
            result.setBody(respContent);
            result.setStatus(resp.getStatusLine().getStatusCode());
            result.setRespHeaders(this.getHttpHeaders(resp));
            this.updateResultFromCookies(result, cookieStore.getCookies());
        } catch (Exception e) {  // NOSONAR
            throw new OnapCommandHttpFailure(e);
        }

        return result;
    }

    public void close() throws IOException {
        this.mapCommonHeaders.clear();
        if (this.httpClient != null) {
            this.httpClient.close();
        }
    }

    private HttpEntity getMultipartEntity(HttpInput input) {
        if (!input.getMultiparts().isEmpty()) {
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            for (HttpInput.Part part: input.getMultiparts()) {
                if (part.isBinary()) {
                     File file = new File(part.getContent());
                     entityBuilder.addBinaryBody(
                            part.getName(),
                            file,
                            ContentType.APPLICATION_OCTET_STREAM,
                            file.getName());
                } else {
                    entityBuilder.addTextBody(part.getName(), part.getContent(), ContentType.APPLICATION_JSON);
                }
            }

            return entityBuilder.build();
        } else {
            String fileTag = (!input.getMultipartEntityName().isEmpty()) ? input.getMultipartEntityName() : "file";
            File file = new File(input.getBody().trim());
            HttpEntity multipartEntity = MultipartEntityBuilder
                    .create()
                    .addBinaryBody(fileTag, file, ContentType.create("application/octet-stream"), file.getName())
                    .build();
            return multipartEntity;
        }
    }

     @Contract(threading = ThreadingBehavior.UNSAFE)
    static class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {

        public HttpDeleteWithBody() {
            super();
        }

        public String getMethod() {
            return OnapCommandHttpConstants.DELETE.toUpperCase();
        }

    }
}
