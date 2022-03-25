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

package org.onap.cli.main;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.onap.cli.fw.conf.OnapCommandConstants;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * This command helps to test the Command functionalities.
 *
 */
@OnapCommandSchema(schema = "sample-test-schema.yaml")
public class OnapCommandSampleTest extends OnapCommand {

    @Override
    protected void run() throws OnapCommandException {

    }

    @Test
    public void sampleTestVersion() throws OnapCommandException {

        Set < OnapCommandParameter > parameters = new HashSet < > ();
        OnapCommandParameter version = new OnapCommandParameter();
        version.setName(OnapCommandConstants.DEFAULT_PARAMETER_VERSION);
        version.setValue(true);
        parameters.add(version);
        OnapCommandParameter hlp = new OnapCommandParameter();
        hlp.setName(OnapCommandConstants.DEFAULT_PARAMETER_HELP);
        hlp.setValue(false);
        parameters.add(hlp);

        OnapCommand sample = OnapCommandRegistrar.getRegistrar().get("sample-test");
        sample.setParameters(parameters);
        assertDoesNotThrow(() -> sample.execute());
    }

}
