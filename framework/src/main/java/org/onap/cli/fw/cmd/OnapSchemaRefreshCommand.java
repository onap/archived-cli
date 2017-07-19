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

package org.onap.cli.fw.cmd;

import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.OnapCommandSchema;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.utils.ExternalSchema;
import org.onap.cli.fw.utils.OnapCommandUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Refresh external schema.
 *
 */
@OnapCommandSchema(name = "schema-refresh", schema = "schema-refresh.yaml")
public class OnapSchemaRefreshCommand extends OnapCommand {

    @Override
    protected void run() throws OnapCommandException {

        List<ExternalSchema> schemas = OnapCommandUtils.findAllExternalSchemas();
        // Will override the existing json file
        OnapCommandUtils.persist(schemas);

        List<String> slNumbers = new ArrayList<>();
        List<String> cmdNames = new ArrayList<>();
        List<String> cmdFiles = new ArrayList<>();
        List<String> versions = new ArrayList<>();

        for (int i = 0; i < schemas.size(); i++) {
            ExternalSchema schema = schemas.get(i);
            slNumbers.add(String.valueOf(i + 1));
            cmdNames.add(schema.getCmdName());
            cmdFiles.add(schema.getSchemaName());
            versions.add(schema.getVersion());
        }
        for (OnapCommandResultAttribute attribute : this.getResult().getRecords()) {
            if ("sl-no".equals(attribute.getName())) {
                attribute.setValues(slNumbers);
            } else if ("command".equals(attribute.getName())) {
                attribute.setValues(cmdNames);
            } else if ("schema".equals(attribute.getName())) {
                attribute.setValues(cmdFiles);
            } else if ("version".equals(attribute.getName())) {
                attribute.setValues(versions);
            }
        }
    }

}
