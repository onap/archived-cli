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
 * Product list.
 *
 */
@OnapCommandSchema(schema = "product-list.yaml")
public class OnapProductsListCommand extends OnapCommand {

    @Override
    protected void run() throws OnapCommandException {
        List<OnapCommandSchemaInfo> schemas = OnapCommandDiscoveryUtils.discoverOrLoadSchemas(false);

        Set<String> rslt = new HashSet<>();

        for (OnapCommandSchemaInfo schema :  schemas) {
            if (schema.isIgnore()) {
                continue;
            }

               rslt.add(schema.getProduct());
        }

        for (String product : rslt) {
            this.getResult().getRecordsMap().get("product").getValues().add(product);

            InputStream stream = this.getClass().getResourceAsStream("/" + OnapCommandConstants.SCHEMA_DIRECTORY +
                    "/" + product + OnapCommandConstants.PRODUCT_REGISTRY_YAML);

            if (stream != null) {
                Map<String, ?> map = null;
                map = OnapCommandDiscoveryUtils.loadYaml(stream);
                Map<String, String> productMap = (Map<String, String>) map.get("product");
                String description = (String) productMap.get(OnapCommandConstants.DESCRIPTION);
                this.getResult().getRecordsMap().get("description").getValues().add(description.trim());
            } else {
                this.getResult().getRecordsMap().get("description").getValues().add("");
            }
        }
    }
}
