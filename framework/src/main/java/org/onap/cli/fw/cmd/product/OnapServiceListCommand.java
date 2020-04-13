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

package org.onap.cli.fw.cmd.product;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.schema.OnapCommandSchemaInfo;
import org.onap.cli.fw.utils.OnapCommandDiscoveryUtils;
import java.io.IOException;
import java.io.InputStreamReader;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

/**
 * Service list.
 *
 */
@OnapCommandSchema(schema = "service-list.yaml")
public class OnapServiceListCommand extends OnapCommand {

    @Override
    protected void run() throws OnapCommandException {

        String product = getParametersMap().get("product").getValue().toString();

        List<OnapCommandSchemaInfo> schemas = OnapCommandDiscoveryUtils.discoverOrLoadSchemas(false);
        Map<String, Set<String>> rslt = new HashMap<>();

        for (OnapCommandSchemaInfo schema :  schemas) {
            if (schema.isIgnore()) {
                continue;
            }

            if (!rslt.containsKey(schema.getProduct())) {
                rslt.put(schema.getProduct(), new HashSet<String>());
            }
            rslt.get(schema.getProduct()).add(schema.getService());
        }

        InputStream stream = this.getClass().getResourceAsStream("/" +OnapCommandConstants.SCHEMA_DIRECTORY +
                "/" + product + OnapCommandConstants.PRODUCT_REGISTRY_YAML);

        Map<String, String> serviceDescs = new HashMap<>();
        if (stream != null) {
            Map<String, ?> map = null;
            map = OnapCommandDiscoveryUtils.loadYaml(stream);
            if (map.containsKey("services")) {
                List<Map<String, String>> services = (List) map.get("services");

                for (Map<String, String> service: services ) {
                    serviceDescs.put(service.get("name"), service.get("description"));
                }
            }
        }

        for (String service : rslt.getOrDefault(product, new HashSet<String>())) {
            this.getResult().getRecordsMap().get("product").getValues().add(product);
            this.getResult().getRecordsMap().get("service").getValues().add(service);
            this.getResult().getRecordsMap().get("description").getValues().add(
                    serviceDescs.containsKey(service) ? serviceDescs.get(service) : "");
        }
    }
}
