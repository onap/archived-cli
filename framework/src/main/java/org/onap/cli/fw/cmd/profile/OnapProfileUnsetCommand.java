/*
 * Copyright 2018 Huawei Technologies Co., Ltd.
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

package org.onap.cli.fw.cmd.profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.input.cache.OnapCommandParamEntity;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.store.OnapCommandProfileStore;

/**
 * Refresh external schema.
 *
 */
@OnapCommandSchema(schema = "profile-unset.yaml")
public class OnapProfileUnsetCommand extends OnapCommand {

    @Override
    protected void run() throws OnapCommandException {
         //mrkanag add feature to remove the profile at product or service or command level
         String product = getParametersMap().get("product").getValue().toString();
         String profile = getParametersMap().get("profile").getValue().toString();
         String service = getParametersMap().get("service").getValue().toString();
         String command = getParametersMap().get("command").getValue().toString();

         List<String> params = (List<String>)getParametersMap().get("parameter").getValue();

         OnapCommandProfileStore cache = OnapCommandProfileStore.getInstance();

         String prefix = "";
         if (service.length() > 0) {
             prefix += service + ":";
         }
         if (command.length() > 0) {
             prefix += command + ":";
         }

         Map<String, OnapCommandParamEntity> map = new HashMap<>();

         for (OnapCommandParamEntity paramsExisting : OnapCommandProfileStore.getInstance().loadParamFromCache(profile)) {
             map.put(paramsExisting.getProduct() + ":" + paramsExisting.getName(), paramsExisting);
         }

         for (String name: params) {
             name = prefix + name;

             if (map.containsKey(product + ":" + name))
                 map.remove(product + ":" + name);
         }

         List<OnapCommandParamEntity> es = new ArrayList<>();
         for (OnapCommandParamEntity e : map.values()) {
             es.add(e);
         }

         cache.persistProfile( es, profile);
    }
}
