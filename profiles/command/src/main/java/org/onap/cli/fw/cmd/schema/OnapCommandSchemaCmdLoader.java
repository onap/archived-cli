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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.onap.cli.fw.cmd.cmd.OpenCommandShellCmd;
import org.onap.cli.fw.cmd.conf.OnapCommandCmdConstants;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.utils.OnapCommandUtils;
import java.util.stream.Collectors;

public class OnapCommandSchemaCmdLoader {

    private OnapCommandSchemaCmdLoader() {
        // to follow standards !
    }


    /**
     * Load the schema.
     *
     * @param cmd
     *            OpenCommandShellCmd
     * @param schemaName
     *            schema name
     * @throws OnapCommandException
     *             on error
     */
    public static List<String> parseCmdSchema(OpenCommandShellCmd cmd,
                                                    final Map<String, ?> values,
                                                    boolean validate) throws OnapCommandException { //NOSONAR
        ArrayList<String> errorList = new ArrayList<>();
        Map<String, ?> valMap = (Map<String, ?>) values.get(OnapCommandCmdConstants.CMD);

        if (valMap != null) {
            if (validate) {
                OnapCommandUtils.validateTags(errorList, valMap, OnapCommandConfig.getCommaSeparatedList(OnapCommandCmdConstants.CMD_SECTIONS),
                        OnapCommandConfig.getCommaSeparatedList(OnapCommandCmdConstants.CMD_MANDATORY_SECTIONS), OnapCommandCmdConstants.CMD);
            }
            for (Map.Entry<String, ?> entry1 : valMap.entrySet()) {
                String key1 = entry1.getKey();

                switch (key1) {
                    case OnapCommandCmdConstants.COMMAND:
                        Object o = valMap.get(key1);
                        if (o instanceof List) {
                            cmd.setCommand((List<String>) o);
                        } else {
                            cmd.setCommand(Arrays.asList((String) o));
                        }
                        break;

                    case OnapCommandCmdConstants.ENVIRONMENT:
                        Map<String, String> envMap = (Map<String, String>) valMap.get(key1);
                        cmd.setEnvs(envMap);
                        break;

                    case OnapCommandCmdConstants.WD:
                        cmd.setWd((String)valMap.get(key1));
                        break;

                    case OnapCommandCmdConstants.OUTPUT:
                        cmd.setOutput((String)valMap.get(key1));
                        break;

                    case OnapCommandCmdConstants.ERROR:
                        cmd.setError((String)valMap.get(key1));
                        break;

                    case OnapCommandCmdConstants.RESULT_MAP:
                        cmd.setResultMap((Map<String, String>) valMap.get(key1));
                        break;

                    case OnapCommandCmdConstants.SUCCESS_EXIT_CODE:
                        List<String> list = (ArrayList) valMap.get(key1);
                        cmd.setSuccessStatusCodes(list.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList()));
                        break;

                    case OnapCommandCmdConstants.PASS_CODE:
                        cmd.setPassCodes((ArrayList) valMap.get(key1));
                        break;
                }
            }
        }


        return errorList;
    }
}
