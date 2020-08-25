/*
 * Copyright 2020 Simran Singhal.
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

package org.onap.cli.fw.robot.schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.onap.cli.fw.robot.cmd.OnapRobotCommand;
import org.onap.cli.fw.robot.conf.OnapCommandRobotConstants;
import org.onap.cli.fw.cmd.conf.OnapCommandCmdConstants;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.utils.OnapCommandUtils;
import java.util.stream.Collectors;

public class OnapCommandSchemaRobotLoader {

  private OnapCommandSchemaRobotLoader() {
    // to follow standards !
  }

    /**
     * Load the schema.
     *
     * @param robot
     *            OnapRobotCommand
     * @param schemaName
     *            schema name
     * @throws OnapCommandException
     *             on error
     */
    public static List<String> parseRobotSchema(OnapRobotCommand robot,
                                                    final Map<String, ?> values,
                                                    boolean validate) throws OnapCommandException { //NOSONAR
        ArrayList<String> errorList = new ArrayList<>();
        Map<String, ?> valMap = (Map<String, ?>) values.get(OnapCommandRobotConstants.ROBOT);

        if (valMap != null) {
            if (validate) {
                OnapCommandUtils.validateTags(errorList, valMap, OnapCommandConfig.getCommaSeparatedList(OnapCommandCmdConstants.CMD_SECTIONS),
                        OnapCommandConfig.getCommaSeparatedList(OnapCommandCmdConstants.CMD_MANDATORY_SECTIONS), OnapCommandRobotConstants.ROBOT);
            }
            for (Map.Entry<String, ?> entry1 : valMap.entrySet()) {
                String key1 = entry1.getKey();

                switch (key1) {
                    case OnapCommandCmdConstants.COMMAND:
                        Object o = valMap.get(key1);
                        if (o instanceof List) {
                            robot.setCommand((List<String>) o);
                        } else {
                            robot.setCommand(Arrays.asList((String) o));
                        }
                        break;

                    case OnapCommandCmdConstants.ENVIRONMENT:
                        Map<String, String> envMap = (Map<String, String>) valMap.get(key1);
                        robot.setEnvs(envMap);
                        break;

                    case OnapCommandCmdConstants.WD:
                        robot.setWd((String)valMap.get(key1));
                        break;

                    case OnapCommandCmdConstants.OUTPUT:
                        robot.setOutput((String)valMap.get(key1));
                        break;

                    case OnapCommandCmdConstants.ERROR:
                        robot.setError((String)valMap.get(key1));
                        break;

                    case OnapCommandCmdConstants.RESULT_MAP:
                        robot.setResultMap((Map<String, String>) valMap.get(key1));
                        break;

                    case OnapCommandCmdConstants.SUCCESS_EXIT_CODE:
                        List<String> list = (ArrayList) valMap.get(key1);
                        robot.setSuccessStatusCodes(list.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList()));
                        break;

                    case OnapCommandCmdConstants.PASS_CODE:
                        robot.setPassCodes((ArrayList) valMap.get(key1));
                        break;
                    default : // Do nothing
                }
            }
        }

        return errorList;
    }

}