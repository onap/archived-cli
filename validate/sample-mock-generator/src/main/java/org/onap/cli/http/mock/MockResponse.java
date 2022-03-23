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

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class MockResponse {
    private int status;
    private JsonElement json;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public JsonElement getJson() {
        return json;
    }

    public void setJson(String json) throws IOException { //NOSONAR
        if (json != null && !json.isEmpty()) {
            try {
                this.json = JsonParser.parseString(json);
            } catch (Exception e) {
                this.json = JsonParser.parseString("{}");
            }
        }
    }
}
