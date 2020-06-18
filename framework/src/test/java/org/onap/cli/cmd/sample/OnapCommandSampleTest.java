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

package org.onap.cli.cmd.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandExecutionFailed;
import org.onap.cli.fw.error.OnapCommandNotInitialized;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.OnapCommandParameterType;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;

public class OnapCommandSampleTest {
    @Test
    public void sampleTestVersion() {

        try {
            Set<OnapCommandParameter> parameters = new HashSet();
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
            sample.execute();
        } catch (OnapCommandException e) {
        }
    }

    @Test
    public void sampleTestHelp() {
        try {
            OnapCommandParameter ver = new OnapCommandParameter();
            ver.setName(OnapCommandConstants.DEFAULT_PARAMETER_HELP);
            ver.setValue(true);
            ver.setParameterType(OnapCommandParameterType.BOOL);

            Set<OnapCommandParameter> parameters = new HashSet();
            parameters.add(ver);

            OnapCommandSample sample = new OnapCommandSample();
            sample.setParameters(parameters);
            sample.execute();
        } catch (OnapCommandException e) {
        }
    }

    @Test
    public void sampleTest() {

        try {
            Set<OnapCommandParameter> parameters = new HashSet();
            OnapCommandParameter ver = new OnapCommandParameter();
            ver.setName(OnapCommandConstants.DEFAULT_PARAMETER_VERSION);
            ver.setValue(false);
            parameters.add(ver);
            OnapCommandParameter hlp = new OnapCommandParameter();
            hlp.setName(OnapCommandConstants.DEFAULT_PARAMETER_HELP);
            hlp.setValue(false);
            parameters.add(hlp);
            OnapCommandParameter fmt = new OnapCommandParameter();
            fmt.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_FORMAT);
            fmt.setValue("table");
            parameters.add(fmt);
            OnapCommandParameter lng = new OnapCommandParameter();
            lng.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_ATTR_LONG);
            lng.setValue(true);
            parameters.add(lng);
            OnapCommandParameter title = new OnapCommandParameter();
            title.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_NO_TITLE);
            title.setValue(true);
            parameters.add(title);
            OnapCommandParameter denug = new OnapCommandParameter();
            denug.setName(OnapCommandConstants.DEFAULT_PARAMETER_DEBUG);
            denug.setValue(true);
            parameters.add(denug);

            OnapCommand sample = OnapCommandRegistrar.getRegistrar().get("sample-test");
            sample.setParameters(parameters);
            sample.execute();

            //validate whether output attributes default value got initialized as part of execute()
            OnapCommandResultAttribute attr = sample.getResult().getRecordsMap().get("output-1");
            String attrValue = attr.getValues().get(0);
            UUID.fromString(attrValue.substring(4));
            attr = sample.getResult().getRecordsMap().get("output-2");
            attrValue = attr.getValues().get(0);
            assertEquals("test", attrValue);
        } catch (IllegalArgumentException e) {
            fail("Failed to replace the output default value on output-1");
        } catch (OnapCommandException e) {
        }
    }

    @Test(expected = OnapCommandExecutionFailed.class)
    public void sampleTestFailure() throws OnapCommandException {
        OnapCommandSample sample = new OnapCommandSample();
        sample.failCase = true;

        Set<OnapCommandParameter> parameters = new HashSet();
        OnapCommandParameter ver = new OnapCommandParameter();
        ver.setName(OnapCommandConstants.DEFAULT_PARAMETER_VERSION);
        ver.setValue(false);
        parameters.add(ver);
        OnapCommandParameter hlp = new OnapCommandParameter();
        hlp.setName(OnapCommandConstants.DEFAULT_PARAMETER_HELP);
        hlp.setValue(false);
        parameters.add(hlp);
        OnapCommandParameter ffmt = new OnapCommandParameter();
        ffmt.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_FORMAT);
        ffmt.setValue("table");
        parameters.add(ffmt);
        OnapCommandParameter lng = new OnapCommandParameter();
        lng.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_ATTR_LONG);
        lng.setValue(true);
        parameters.add(lng);
        OnapCommandParameter titl = new OnapCommandParameter();
        titl.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_NO_TITLE);
        titl.setValue(true);
        parameters.add(titl);
        OnapCommandParameter debug = new OnapCommandParameter();
        debug.setName(OnapCommandConstants.DEFAULT_PARAMETER_DEBUG);
        debug.setValue(true);
        parameters.add(debug);
        sample.setParameters(parameters);
        sample.execute();
    }

    @Test(expected = OnapCommandNotInitialized.class)
    public void sampleTestIsInitialized() throws OnapCommandException {
        OnapCommandSample sample = new OnapCommandSample(false);
        sample.execute();
    }
}
