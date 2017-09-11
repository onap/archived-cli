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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.utils.OnapCommandUtils;

public class OnapCommandParameterCache {

    public Map<String, Map<String, String>> paramCache = new HashMap<>();

    private static OnapCommandParameterCache single = null;

    private String profileName = Constants.PARAM_CACHE_FILE_NAME;

    private OnapCommandParameterCache() {

    }

    public static OnapCommandParameterCache getInstance() {
        if (single == null) {
            single = new OnapCommandParameterCache();
        }

        single.load();
        return single;
    }


    public void add(String productVersion, String paramName, String paramValue) {

        if (!paramCache.containsKey(productVersion)) {
            paramCache.put(productVersion, new HashMap<String, String>());
        }

        paramCache.get(productVersion).put(paramName, paramValue);

        this.persist();
    }

    public void remove(String productVersion, String paramName) {
        if (paramCache.containsKey(productVersion)) {
            if (paramCache.get(productVersion).containsKey(paramName)) {
                paramCache.get(productVersion).remove(paramName);
            }
        }

        this.persist();
    }

    public Map<String, String> getParams(String productVersion) {
        if (paramCache.containsKey(productVersion)) {
            return this.paramCache.get(productVersion);
        } else {
            return new HashMap<String, String>();
        }
    }

    private void persist() {
        List<Param> params = new ArrayList<>();
        for (String p: this.paramCache.keySet()) {
            for (String name: this.paramCache.get(p).keySet()) {

                Param param = new Param();
                param.setProduct(p);
                param.setName(name);
                param.setValue(this.paramCache.get(p).get(name));

                params.add(param);
             }
        }

        OnapCommandUtils.persistParams(params, this.profileName);
    }

    private void load() {
        List<Param> params = OnapCommandUtils.loadParamFromCache(this.profileName);

        for (Param p : params) {
            this.add(p.getProduct(), p.getName(), p.getValue());
        }
    }

    public void setProfile(String profileName) {
        this.profileName = profileName;
        this.load();
    }
}
