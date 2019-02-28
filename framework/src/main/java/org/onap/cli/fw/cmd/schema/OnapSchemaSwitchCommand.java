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

package org.onap.cli.fw.cmd.schema;

import java.util.List;
import java.util.Map;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.schema.OnapCommandSchemaInfo;
import org.onap.cli.fw.utils.OnapCommandDiscoveryUtils;

/**
 * Validate schema command.
 */
@OnapCommandSchema(schema = "schema-switch.yaml")
public class OnapSchemaSwitchCommand extends OnapCommand {

    @Override
    protected void run() throws OnapCommandException {
        Map<String, OnapCommandParameter> paramMap = getParametersMap();

        OnapCommandParameter nameParam = paramMap.get("name");
        String name = String.valueOf(nameParam.getValue());

        OnapCommandParameter productParam = paramMap.get("product");
        String product = String.valueOf(productParam.getValue());

        List<OnapCommandSchemaInfo> list = OnapCommandDiscoveryUtils.loadSchemas();
        for (OnapCommandSchemaInfo info: list) {
            if (info.getProduct().equals(product) && info.getCmdName().equals(name)) {
                info.setIgnore(!info.isIgnore());
                break;
            }
        }

        OnapCommandDiscoveryUtils.persistSchemaInfo(list);
        OnapCommandRegistrar.getRegistrar().resync();
    }

}
