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

            OnapCommand sample = OnapCommandRegistrar.getRegistrar().get("sample-test");

            Set<OnapCommandParameter> parameters = new HashSet();
            OnapCommandParameter v = new OnapCommandParameter();
            v.setName(OnapCommandConstants.DEFAULT_PARAMETER_VERSION);
            v.setValue(true);
            parameters.add(v);
            OnapCommandParameter h = new OnapCommandParameter();
            h.setName(OnapCommandConstants.DEFAULT_PARAMETER_HELP);
            h.setValue(false);
            parameters.add(h);
            sample.setParameters(parameters);
            sample.execute();
        } catch (OnapCommandException e) {
        }
    }

    @Test
    public void sampleTestHelp() {
        OnapCommandSample sample = new OnapCommandSample();
        try {
            Set<OnapCommandParameter> parameters = new HashSet();
            OnapCommandParameter v = new OnapCommandParameter();
            v.setName(OnapCommandConstants.DEFAULT_PARAMETER_HELP);
            v.setValue(true);
            v.setParameterType(OnapCommandParameterType.BOOL);
            parameters.add(v);
            sample.setParameters(parameters);
            sample.execute();
        } catch (OnapCommandException e) {
        }
    }

    @Test
    public void sampleTest() {

        try {
            OnapCommand sample = OnapCommandRegistrar.getRegistrar().get("sample-test");
            Set<OnapCommandParameter> parameters = new HashSet();
            OnapCommandParameter v = new OnapCommandParameter();
            v.setName(OnapCommandConstants.DEFAULT_PARAMETER_VERSION);
            v.setValue(false);
            parameters.add(v);
            OnapCommandParameter h = new OnapCommandParameter();
            h.setName(OnapCommandConstants.DEFAULT_PARAMETER_HELP);
            h.setValue(false);
            parameters.add(h);
            OnapCommandParameter f = new OnapCommandParameter();
            f.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_FORMAT);
            f.setValue("table");
            parameters.add(f);
            OnapCommandParameter l = new OnapCommandParameter();
            l.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_ATTR_LONG);
            l.setValue(true);
            parameters.add(l);
            OnapCommandParameter t = new OnapCommandParameter();
            t.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_NO_TITLE);
            t.setValue(true);
            parameters.add(t);
            OnapCommandParameter d = new OnapCommandParameter();
            d.setName(OnapCommandConstants.DEFAULT_PARAMETER_DEBUG);
            d.setValue(true);
            parameters.add(d);

            sample.setParameters(parameters);
            sample.execute();

            //validate whether output attributes default value got initialized as part of execute()
            OnapCommandResultAttribute attr = sample.getResult().getRecordsMap().get("output-1");
            String attrValue = attr.getValues().get(0);
            UUID.fromString(attrValue.substring(4));
            attr = sample.getResult().getRecordsMap().get("output-2");
            attrValue = attr.getValues().get(0);
            assertEquals(attrValue, "test");
        } catch (IllegalArgumentException e){
            fail("Failed to replace the output default value on output-1");
        } catch (OnapCommandException e) {
        }
    }

    @Test(expected = OnapCommandExecutionFailed.class)
    public void sampleTestFailure() throws OnapCommandException {
        OnapCommandSample sample = new OnapCommandSample();
        sample.failCase = true;

        Set<OnapCommandParameter> parameters = new HashSet();
        OnapCommandParameter v = new OnapCommandParameter();
        v.setName(OnapCommandConstants.DEFAULT_PARAMETER_VERSION);
        v.setValue(false);
        parameters.add(v);
        OnapCommandParameter h = new OnapCommandParameter();
        h.setName(OnapCommandConstants.DEFAULT_PARAMETER_HELP);
        h.setValue(false);
        parameters.add(h);
        OnapCommandParameter f = new OnapCommandParameter();
        f.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_FORMAT);
        f.setValue("table");
        parameters.add(f);
        OnapCommandParameter l = new OnapCommandParameter();
        l.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_ATTR_LONG);
        l.setValue(true);
        parameters.add(l);
        OnapCommandParameter t = new OnapCommandParameter();
        t.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_NO_TITLE);
        t.setValue(true);
        parameters.add(t);
        OnapCommandParameter d = new OnapCommandParameter();
        d.setName(OnapCommandConstants.DEFAULT_PARAMETER_DEBUG);
        d.setValue(true);
        parameters.add(d);
        sample.setParameters(parameters);
        sample.execute();
    }

    @Test(expected = OnapCommandNotInitialized.class)
    public void sampleTestIsInitialized() throws OnapCommandException {
        OnapCommandSample sample = new OnapCommandSample(false);
        sample.execute();
    }
}
