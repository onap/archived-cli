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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.snmp.cmd.OnapSnmpCommand;
import org.onap.cli.fw.snmp.conf.OnapCommandSnmpConstants;
import org.onap.cli.fw.utils.OnapCommandUtils;

public class OnapCommandSchemaSnmpLoader {
    private OnapCommandSchemaSnmpLoader() {
        throw new IllegalStateException("Utility class");
    }
    private static List<String> validateSnmpSchemaSection(Map<String, Object> values) {
        ArrayList<String> errorList = new ArrayList<>();

        OnapCommandUtils.validateTags(errorList, values,
                OnapCommandConfig.getCommaSeparatedList(OnapCommandSnmpConstants.SNMP_REQUEST_PARAMS),
                OnapCommandConfig.getCommaSeparatedList(OnapCommandSnmpConstants.SNMP_REQUEST_MANDATORY_PARAMS),
                OnapCommandSnmpConstants.SNMP);
        return errorList;
    }

    public static List<String> parseSnmpSchema(OnapSnmpCommand cmd,
                                                     final Map<String, ?> values,
                                                     boolean validate) throws OnapCommandException { //NOSONAR

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
                        break;
                    default:
                        break;
                }
            }
        }
        return errorList;
    }
}
