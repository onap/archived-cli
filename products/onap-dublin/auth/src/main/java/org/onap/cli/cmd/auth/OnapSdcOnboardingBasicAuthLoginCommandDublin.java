/*
 * Copyright © Intel Corporation 2019
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

package org.onap.cli.cmd.auth;

import org.onap.cli.fw.http.cmd.BasicAuthLoginCommand;
import org.onap.cli.fw.schema.OnapCommandSchema;

@OnapCommandSchema(schema = "basic-login-onap-sdc-onboarding-dublin.yaml")
public class OnapSdcOnboardingBasicAuthLoginCommandDublin extends BasicAuthLoginCommand {
}
