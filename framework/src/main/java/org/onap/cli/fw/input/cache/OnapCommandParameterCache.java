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

import static org.onap.cli.fw.conf.OnapCommandConstants.DATA_DIRECTORY;
import static org.onap.cli.fw.conf.OnapCommandConstants.DATA_PATH_JSON_PATTERN;
import static org.onap.cli.fw.conf.OnapCommandConstants.DATA_PATH_PROFILE_JSON;
import static org.onap.cli.fw.conf.OnapCommandConstants.DATA_PATH_PROFILE_JSON_PATTERN;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandLoadProfileFailed;
import org.onap.cli.fw.error.OnapCommandPersistProfileFailed;
import org.onap.cli.fw.utils.OnapCommandDiscoveryUtils;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OnapCommandParameterCache {

    private Map<String, Map<String, String>> paramCache = new HashMap<>();

    private static OnapCommandParameterCache single = null;

    private String profileName = OnapCommandConstants.PARAM_CACHE_FILE_NAME;

    private OnapCommandParameterCache() {

    }

    public static OnapCommandParameterCache getInstance() {
        if (single == null) {
            single = new OnapCommandParameterCache();
        }

        single.load();
        return single;
    }

    public void includeProfile(String profile) {
        this.load(profile, true);
    }

    public void excludeProfile(String profile) {
        this.load(profile, false);
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
        List<OnapCommandParamEntity> params = new ArrayList<>();

        for (String p: this.paramCache.keySet()) {
            for (String name: this.paramCache.get(p).keySet()) {

                OnapCommandParamEntity param = new OnapCommandParamEntity();
                param.setProduct(p);
                param.setName(name);
                param.setValue(this.paramCache.get(p).get(name));

                params.add(param);
             }
        }

        try {
            this.persistProfile(params, this.profileName);
        } catch (OnapCommandPersistProfileFailed e) {
            throw new RuntimeException(e);   // NOSONAR
        }
    }

    private void load() {
        this.load(this.profileName, true);
    }

    private void load(String profileName, boolean include) {
        List<OnapCommandParamEntity> params= new ArrayList<>();
        try {
            params = this.loadParamFromCache(profileName);
        } catch (OnapCommandLoadProfileFailed e) {
            throw new RuntimeException(e);   // NOSONAR
        }

        for (OnapCommandParamEntity p : params) {
            if (include) {
                this.add(p.getProduct(), p.getName(), p.getValue());
            } else {
                this.remove(p.getProduct(), p.getName());
            }
        }
    }

    public void setProfile(String profileName) {
        this.profileName = profileName;
        this.paramCache.clear();
        this.load();
    }

    private void persistProfile(List<OnapCommandParamEntity> params, String profileName) throws OnapCommandPersistProfileFailed {
        if (params != null) {
            try {
                Resource[] resources = OnapCommandDiscoveryUtils.findResources(DATA_DIRECTORY);
                if (resources != null && resources.length == 1) {
                    String path = resources[0].getURI().getPath();
                    File file = new File(path + File.separator + profileName + DATA_PATH_PROFILE_JSON);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, params);
                }
            } catch (IOException e1) {
                throw new OnapCommandPersistProfileFailed(e1);
            }
        }
    }

    private List<OnapCommandParamEntity> loadParamFromCache(String profileName) throws OnapCommandLoadProfileFailed {
        List<OnapCommandParamEntity> params = new ArrayList<>();

        try {
            Resource resource = OnapCommandDiscoveryUtils.findResource(profileName + DATA_PATH_PROFILE_JSON,
                    DATA_PATH_JSON_PATTERN);
            if (resource != null) {
                File file = new File(resource.getURI().getPath());
                ObjectMapper mapper = new ObjectMapper();
                OnapCommandParamEntity[] list = mapper.readValue(file, OnapCommandParamEntity[].class);
                params.addAll(Arrays.asList(list));
            }
        } catch (IOException e) {
            throw new OnapCommandLoadProfileFailed(e);
        }

        return params;
    }

    public List<String> getProfiles() {
        List<String> profiles = new ArrayList<>();

        Resource[] resources;
        try {
            resources = OnapCommandDiscoveryUtils.findResources(DATA_PATH_PROFILE_JSON_PATTERN);
        } catch (IOException e) {
             throw new RuntimeException(e);   // NOSONAR
        }

        if (resources != null && resources.length > 0) {
            for (Resource res : resources) {
                String profile = res.getFilename().substring(0, res.getFilename().indexOf(DATA_PATH_PROFILE_JSON));
                profiles.add(profile);
            }
        }

        return profiles;
    }
}
