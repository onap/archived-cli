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

package org.onap.cli.fw.snmp.schema;

import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.schema.OnapCommandSchemaLoader;
import org.onap.cli.fw.snmp.cmd.OnapSnmpCommand;
import org.onap.cli.fw.snmp.conf.OnapCommandSnmpConstants;
import org.onap.cli.fw.utils.OnapCommandUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnapCommandSchemaSnmpLoader {

    public static List<String> loadSnmpSchema(OnapSnmpCommand cmd, String schemaName, boolean includeDefault,
                                              boolean validateSchema) throws OnapCommandException {

        List<String> errors = new ArrayList<>();

        if (includeDefault) {
            Map<String, ?> defaultParameterMap = includeDefault ? OnapCommandSchemaLoader.validateSchemaVersion(
                    OnapCommandSnmpConstants.DEFAULT_PARAMETER_SNMP_FILE_NAME, cmd.getSchemaVersion()) : new HashMap<>();

            if (defaultParameterMap.containsKey(OnapCommandConstants.INFO)) {
                defaultParameterMap.remove(OnapCommandConstants.INFO);
            }

            errors.addAll(OnapCommandSchemaLoader.parseSchema(cmd, defaultParameterMap, validateSchema));
        }

        Map<String, List<Map<String, String>>> commandYamlMap =
                (Map<String, List<Map<String, String>>>) OnapCommandSchemaLoader.validateSchemaVersion(schemaName, cmd.getSchemaVersion());

        errors.addAll(parseSnmpSchema(cmd, commandYamlMap, validateSchema));
        return errors;
    }

    private static List<String> validateSnmpSchemaSection(Map<String, Object> values) {
        ArrayList<String> errorList = new ArrayList<>();

        OnapCommandUtils.validateTags(errorList, values,
                OnapCommandConfig.getCommaSeparatedList(OnapCommandSnmpConstants.SNMP_REQUEST_PARAMS),
                OnapCommandConfig.getCommaSeparatedList(OnapCommandSnmpConstants.SNMP_REQUEST_MANDATORY_PARAMS),
                OnapCommandSnmpConstants.SNMP);
        return errorList;
    }

    private static ArrayList<String> parseSnmpSchema(OnapSnmpCommand cmd,
                                                     final Map<String, ?> values,
                                                     boolean validate) throws OnapCommandException {

        ArrayList<String> errorList = new ArrayList<>();
        Map<String, ?> valMap = (Map<String, ?>) values.get(OnapCommandSnmpConstants.SNMP);

        if (valMap!=null) {
            if (validate) {
                errorList.addAll(validateSnmpSchemaSection((Map<String, Object>) valMap));
            }
            for (Map.Entry<String, ?> entry : valMap.entrySet()) {
                switch (entry.getKey()) {
                    case OnapCommandSnmpConstants.SNMP_RESULTMAP:
                        cmd.setResultMap((List<Map<String, String>>) entry.getValue());
                        break;

                    case OnapCommandSnmpConstants.SNMP_VERSION:
                        cmd.setSnmpVersion((String) entry.getValue());
                        break;

                    case OnapCommandSnmpConstants.SNMP_COMMAND:
                        cmd.setCommand((String) entry.getValue());

                    default:
                        break;
                }
            }
        }
        return errorList;
    }
}
