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

import java.util.HashMap;
import java.util.Map;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandNotFound;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.schema.OnapCommandSchemaInfo;
import org.onap.cli.fw.utils.OnapCommandDiscoveryUtils;

import net.minidev.json.JSONObject;

/**
 * Refresh external schema.
 *
 */
@OnapCommandSchema(schema = "schema-show.yaml")
public class OnapSchemaShowCommand extends OnapCommand {

    @Override
    protected void run() throws OnapCommandException {

        String cmd = getParametersMap().get("command").getValue().toString();
        String product = getParametersMap().get("product").getValue().toString();
        String service = getParametersMap().get("service").getValue().toString();

        OnapCommandSchemaInfo info = OnapCommandDiscoveryUtils.getSchemaInfo(cmd, product);
        if (!service.isEmpty() && !info.getService().equalsIgnoreCase(service)) {
            throw new OnapCommandNotFound(cmd, product, service);
        }

        Map <String, Object> ioMap = new HashMap<>();
        ioMap.put("name", info.getCmdName());
        ioMap.put("author", info.getAuthor());
        ioMap.put("description", info.getDescription());
        ioMap.put("service", info.getService());
        ioMap.put("inputs", info.getInputs());
        ioMap.put("outputs", info.getOutputs());

        String schema = new JSONObject(ioMap).toString();
        this.getResult().setOutput(schema);
        this.getResult().getRecordsMap().get("schema").getValues().add(schema);
    }
}
