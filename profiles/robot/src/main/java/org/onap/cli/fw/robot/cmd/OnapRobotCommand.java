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

package org.onap.cli.fw.robot.cmd;

import java.util.List;
import java.util.Map;

import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.cmd.cmd.OpenCommandShellCmd;
import org.onap.cli.fw.robot.conf.OnapCommandRobotConstants;
import org.onap.cli.fw.robot.schema.OnapCommandSchemaRobotLoader;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;

/**
 * Oclip robot Command.
 *
 */
@OnapCommandSchema(type = OnapCommandRobotConstants.ROBOT_SCHEMA_PROFILE)
public class OnapRobotCommand extends OpenCommandShellCmd {

  public OnapRobotCommand() throws OnapCommandException {
    super.addDefaultSchemas(OnapCommandRobotConstants.DEFAULT_PARAMETER_ROBOT_FILE_NAME);
    if(Boolean.parseBoolean(OnapCommandConfig.getPropertyValue(OnapCommandRobotConstants.ROBOT_DISCOVER_TESTCASES)))
      this.discover();
  }

  public void discover() throws OnapCommandException {
    OnapCommand cmd = OnapCommandRegistrar.getRegistrar().get("auto-discover-robot-testcases");
    cmd.getParametersMap().get("api-tests-folder-path").setValue(OnapCommandRobotConstants.ROBOT_API_TESTS_FOLDER_PATH);
    cmd.execute();
  }

  @Override
  protected List<String> initializeProfileSchema(Map<String, ?> schemaMap, boolean validate) throws OnapCommandException {
      return OnapCommandSchemaRobotLoader.parseRobotSchema(this, schemaMap, validate);
  }

}