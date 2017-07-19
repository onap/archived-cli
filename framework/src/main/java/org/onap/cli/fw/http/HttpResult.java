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

import java.util.HashMap;
import java.util.Map;

/**
 * Captures HTTP response status, body and headers. <br>
 *
 */
public class HttpResult {

    private int status;

    private String resBody;

    private Map<String, String> respHeaders = new HashMap<>();

    private Map<String, String> respCookies = new HashMap<>();

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBody() {
        return this.resBody;
    }

    public void setBody(String body) {
        this.resBody = body;
    }

    public void setRespHeaders(Map<String, String> respHeaders) {
        this.respHeaders = respHeaders;
    }

    public Map<String, String> getRespHeaders() {
        return this.respHeaders;
    }

    public Map<String, String> getRespCookies() {
        return respCookies;
    }

    public void setRespCookies(Map<String, String> respCookies) {
        this.respCookies = respCookies;
    }

    public boolean isSuccess() {
        return this.getStatus() >= 200 && this.getStatus() <= 300;
    }

    @Override
    public String toString() {
        return "\nHTTP Status: " + this.getStatus() + "\nResponse Body: " + this.getBody() + "\nResponse Headers: "
                + this.getRespHeaders() + "\nResponse Cookies: " + this.getRespCookies();
    }
}
