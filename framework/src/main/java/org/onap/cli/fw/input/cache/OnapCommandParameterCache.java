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

package org.onap.cli.fw.input.cache;

import java.util.HashMap;
import java.util.Map;

public class OnapCommandParameterCache {

    public Map<String, Map<String, String>> paramCache = new HashMap<>();

    public void add(String productVersion, String paramName, String paramValue) {

        if (!paramCache.containsKey(productVersion)) {
            paramCache.put(productVersion, new HashMap<String, String>());
        }

        paramCache.get(productVersion).put(paramName, paramValue);
    }

    public void remove(String productVersion, String paramName) {
        if (paramCache.containsKey(productVersion)) {
            if (paramCache.get(productVersion).containsKey(paramName)) {
                paramCache.get(productVersion).remove(paramName);
            }
        }
    }

    public Map<String, String> getParams(String productVersion) {
        if (paramCache.containsKey(productVersion)) {
            return this.paramCache.get(productVersion);
        } else {
            return new HashMap<String, String>();
        }
    }

    public void persist() {
        // mrkana add persistence logic
    }

    public void load() {
        // mrkanag add loading cache from persistence
    }
}
