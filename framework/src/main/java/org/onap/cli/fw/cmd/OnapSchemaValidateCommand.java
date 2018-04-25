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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.schema.OnapCommandSchemaLoader;
import org.onap.cli.fw.utils.OnapCommandDiscoveryUtils;

/**
 * Validate schema command.
 */
@OnapCommandSchema(schema = "schema-validate.yaml")
public class OnapSchemaValidateCommand extends OnapCommand {

    @Override
    protected void run() throws OnapCommandException {
        Map<String, OnapCommandParameter> paramMap = getParametersMap();

        OnapCommandParameter locationParam = paramMap.get("schema-location");
        String location = String.valueOf(locationParam.getValue());

        OnapCommandParameter interSchemaParam = paramMap.get("internal-schema");
        boolean isInternalSchema = Boolean.parseBoolean(String.valueOf(interSchemaParam.getValue()));

        if (isInternalSchema && location.startsWith("/")) {
            location = location.substring(1);
        }

        OnapCommandParameter versionParam = paramMap.get("ocs-version");
        String ocsVersion = String.valueOf(versionParam.getValue());

        String type = OnapCommandDiscoveryUtils.identitySchemaProfileType(
                OnapCommandSchemaLoader.validateSchemaVersion(location, ocsVersion));

        OnapCommand cmd = null;
        if (type.equals(OnapCommandConstants.BASIC_SCHEMA_PROFILE)) {
            cmd = new OnapCommand() {
                @Override
                protected void run() throws OnapCommandException {
                    //This is used for enabling the validation, so no run implemented
                }
            };
        } else {
            cmd = OnapCommandDiscoveryUtils.loadCommandClass(OnapCommandRegistrar.getRegistrar().getProfilePlugin(type));
        }

        List<String> error = cmd.initializeSchema(location, true);
        List<String> slNumber = new ArrayList<>();
        for (int i = 1; i <= error.size(); i++) {
            slNumber.add(String.valueOf(i));
        }
        this.getResult().getRecordsMap().get("sl-no").setValues(slNumber);
        this.getResult().getRecordsMap().get("error").setValues(error);
    }

}
