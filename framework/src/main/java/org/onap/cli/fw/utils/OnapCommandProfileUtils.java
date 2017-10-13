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

package org.onap.cli.fw.utils;

import static org.onap.cli.fw.conf.Constants.DATA_DIRECTORY;
import static org.onap.cli.fw.conf.Constants.DATA_PATH_JSON_PATTERN;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.onap.cli.fw.error.OnapCommandLoadProfileFailed;
import org.onap.cli.fw.error.OnapCommandPersistProfileFailed;
import org.onap.cli.fw.input.cache.Param;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OnapCommandProfileUtils {

    public static List<Param> loadParamFromCache(String profileName) throws OnapCommandLoadProfileFailed {
        List<Param> params = new ArrayList<>();

        try {
            Resource resource = OnapCommandDiscoveryUtils.findResource(profileName + ".json",
                    DATA_PATH_JSON_PATTERN);
            if (resource != null) {
                File file = new File(resource.getURI().getPath());
                ObjectMapper mapper = new ObjectMapper();
                Param[] list = mapper.readValue(file, Param[].class);
                params.addAll(Arrays.asList(list));
            }
        } catch (IOException e) {
            throw new OnapCommandLoadProfileFailed(e);
        }

        return params;
    }

    public static void persistProfile(List<Param> params, String profileName) throws OnapCommandPersistProfileFailed {
        if (params != null) {
            try {
                Resource[] resources = OnapCommandDiscoveryUtils.findResources(DATA_DIRECTORY);
                if (resources != null && resources.length == 1) {
                    String path = resources[0].getURI().getPath();
                    File file = new File(path + File.separator + profileName + ".json");
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, params);
                }
            } catch (IOException e1) {
                throw new OnapCommandPersistProfileFailed(e1);
            }
        }
    }

}
