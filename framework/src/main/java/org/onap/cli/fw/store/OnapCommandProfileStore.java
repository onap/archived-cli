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

package org.onap.cli.fw.store;

import static org.onap.cli.fw.conf.OnapCommandConstants.DATA_PATH_PROFILE_JSON;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandPersistProfileFailed;
import org.onap.cli.fw.error.OnapCommandProfileLoadFailed;
import org.onap.cli.fw.input.cache.OnapCommandParamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.Writer;
import java.io.Reader;



public class OnapCommandProfileStore {
    private static Logger log = LoggerFactory.getLogger(OnapCommandProfileStore.class);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Map<String, Map<String, String>> paramCache = new HashMap<>();

    private static OnapCommandProfileStore single = null;

    private String profileName = OnapCommandConstants.PARAM_CACHE_FILE_NAME;

    static {
        try {
            FileUtils.forceMkdir(new File(getDataStorePath()));
        } catch (IOException e) {
            log.error("Failed to create the data store profile");
        }
    }
    private OnapCommandProfileStore() {

    }

    public static OnapCommandProfileStore getInstance() {
        if (single == null) {
            single = new OnapCommandProfileStore();
        }

        //single.load();
        return single;
    }

    public void includeProfile(String profile) throws OnapCommandException {
        this.load(profile, true);
    }

    public void excludeProfile(String profile) throws OnapCommandException {
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
            if (paramCache.containsKey(productVersion) && paramCache.get(productVersion).containsKey(paramName)) {
                paramCache.get(productVersion).remove(paramName);
            }

        this.persist();
    }

    public Map<String, String> getParams(String productVersion) {
        //default profile used across products, set in profile-set command
        Map<String, String> map = new HashMap<>();

        if (paramCache.containsKey(OnapCommandConstants.OCLIP_GLOBAL_PROFILE)) {
            map = this.paramCache.get(OnapCommandConstants.OCLIP_GLOBAL_PROFILE);
        }

        if (paramCache.containsKey(productVersion)) {
            map.putAll(this.paramCache.get(productVersion));
        }

        return map;
    }

    private void persist() {
        List<OnapCommandParamEntity> params = new ArrayList<>();

        for (Map.Entry<String, Map<String, String>> p: this.paramCache.entrySet()) {
            for (Map.Entry<String, String> paramEntry: p.getValue().entrySet()) {

                OnapCommandParamEntity param = new OnapCommandParamEntity();
                param.setProduct(p.getKey());
                param.setName(paramEntry.getKey());
                param.setValue(paramEntry.getValue());

                params.add(param);
             }
        }

        try {
            this.persistProfile(params, this.profileName);
        } catch (OnapCommandPersistProfileFailed e) {
            throw new RuntimeException(e);   // NOSONAR
        }
    }

    private void load() throws OnapCommandException {
        this.load(this.profileName, true);
    }

    private void load(String profileName, boolean include) throws OnapCommandException {
        List<OnapCommandParamEntity> params= new ArrayList<>();
        params = this.loadParamFromCache(profileName);

        for (OnapCommandParamEntity p : params) {
            if (include) {
                this.add(p.getProduct(), p.getName(), p.getValue());
            } else {
                this.remove(p.getProduct(), p.getName());
            }
        }
    }

    public void setProfile(String profileName) throws OnapCommandException {
        this.profileName = profileName;
        this.paramCache.clear();
        this.load();
    }

    public static String getDataStorePath() {
        return OnapCommandConfig.getPropertyValue(OnapCommandConstants.OPEN_CLI_DATA_DIR)
                + File.separator + "profiles";
    }

    public void persistProfile(List<OnapCommandParamEntity> params, String profileName) throws OnapCommandPersistProfileFailed {
        if (params != null) {
            String dataDir = getDataStorePath();
                File file = new File(dataDir + File.separator + profileName + DATA_PATH_PROFILE_JSON);
                try (Writer writer = new FileWriter(file)){
                    gson.toJson(params, writer);
                } catch (Exception e1) { // NOSONAR
                throw new OnapCommandPersistProfileFailed(e1);
            }
        }
    }

    public List<OnapCommandParamEntity> loadParamFromCache(String profileName) throws OnapCommandException {
        List<OnapCommandParamEntity> params = new ArrayList<>();
        String dataDir = getDataStorePath();
        File file = new File(dataDir + File.separator + profileName + DATA_PATH_PROFILE_JSON);
        if (file.exists()) {
            try (Reader jsonReader = new FileReader(file)){

                OnapCommandParamEntity[] list = gson.fromJson(jsonReader,
                        OnapCommandParamEntity[].class);
                params.addAll(Arrays.asList(list));
//            } else {
//                throw new OnapCommandProfileNotFound(profileName);
            } catch (Exception e) { // NOSONAR
                throw new OnapCommandProfileLoadFailed(e);
            }
        }

        return params;
    }

    public void removeProfile(String profile) {
         String dataDir = getDataStorePath();
         File file = new File(dataDir + File.separator + profile + DATA_PATH_PROFILE_JSON);
            if(file.exists() && !file.delete()){
                log.error("Failed to delete profile "+file.getAbsolutePath());
            }
    }

    public List<String> getProfiles() {
        List<String> profiles = new ArrayList<>();

        String dataDir = getDataStorePath();
        for (File file: new File(dataDir).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(DATA_PATH_PROFILE_JSON);
            }
        })) {
            String profile = file.getName().substring(0, file.getName().indexOf(DATA_PATH_PROFILE_JSON));
            profiles.add(profile);
        }

        return profiles;
    }
}
