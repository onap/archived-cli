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

package org.onap.cli.fw.cmd.schema;

import java.util.List;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.schema.OnapCommandSchemaInfo;
import org.onap.cli.fw.utils.OnapCommandDiscoveryUtils;

/**
 * List available schemas.
 *
 */
@OnapCommandSchema(schema = "schema-list.yaml")
public class OnapSchemaListCommand extends OnapCommand {

    @Override
    protected void run() throws OnapCommandException {

        String product = getParametersMap().get("product").getValue().toString();
        String service = getParametersMap().get("service").getValue().toString();

        List<OnapCommandSchemaInfo> schemas = OnapCommandDiscoveryUtils.discoverOrLoadSchemas(true);
        for (OnapCommandSchemaInfo schema :  schemas) {
            if (schema.getProduct().equalsIgnoreCase(product) && !schema.isIgnore()) {
                if (service.length() > 0 && !service.equalsIgnoreCase(schema.getService())) {
                    continue;
                }
                this.getResult().getRecordsMap().get("product").getValues().add(schema.getProduct());
                this.getResult().getRecordsMap().get("command").getValues().add(schema.getCmdName());
                this.getResult().getRecordsMap().get("schema").getValues().add(schema.getSchemaName());
                this.getResult().getRecordsMap().get("service").getValues().add(schema.getService());
                this.getResult().getRecordsMap().get("ocs-version").getValues().add(schema.getVersion());
                this.getResult().getRecordsMap().get("enabled").getValues().add("" + !Boolean.parseBoolean(schema.getIgnore()));

                String rpc = "";
                if (schema.isRpc()) {
                    rpc = schema.getRpcHost() + ":" + schema.getRpcPort();
                }
                this.getResult().getRecordsMap().get("rpc").getValues().add(rpc);

                this.getResult().getRecordsMap().get("type").getValues().add(schema.getSchemaProfile());
            }
        }
    }

}
