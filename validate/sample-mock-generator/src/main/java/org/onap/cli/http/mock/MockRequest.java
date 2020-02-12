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

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.URL;
import java.util.Map;


public class MockRequest {
    private String method;
    private String uri;
    private Map<String, String> headers;
    private JsonElement json;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String url) throws IOException {
        URL urlt;
        urlt = new URL(url);
        this.uri = urlt.getPath();
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public JsonElement getJson() {
        return json;
    }

    public void setJson(String json) throws IOException {
        Gson gson = new Gson();
        if (!json.isEmpty()) {
            try {
                this.json = gson.fromJson(json,JsonElement.class);
            } catch (Exception e) {
                this.json = gson.fromJson("{}",JsonElement.class);

            }
        }

    }
}
