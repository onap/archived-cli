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

import org.junit.Test;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandExecutionFailed;
import org.onap.cli.fw.error.OnapCommandNotInitialized;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.ParameterType;

import java.util.ArrayList;
import java.util.List;

public class OnapCommandSampleTest {
    @Test
    public void sampleTestVersion() {
        OnapCommandSample sample = new OnapCommandSample();

        try {
            List<OnapCommandParameter> parameters = new ArrayList();
            OnapCommandParameter v = new OnapCommandParameter();
            v.setName(Constants.DEFAULT_PARAMETER_VERSION);
            v.setValue("true");
            parameters.add(v);
            OnapCommandParameter h = new OnapCommandParameter();
            h.setName(Constants.DEFAULT_PARAMETER_HELP);
            h.setValue("false");
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
            List<OnapCommandParameter> parameters = new ArrayList();
            OnapCommandParameter v = new OnapCommandParameter();
            v.setName(Constants.DEFAULT_PARAMETER_HELP);
            v.setValue("true");
            v.setParameterType(ParameterType.BOOL);
            parameters.add(v);
            sample.setParameters(parameters);
            sample.execute();
        } catch (OnapCommandException e) {
        }
    }

    @Test
    public void sampleTest() {
        OnapCommandSample sample = new OnapCommandSample();
        try {
            List<OnapCommandParameter> parameters = new ArrayList();
            OnapCommandParameter v = new OnapCommandParameter();
            v.setName(Constants.DEFAULT_PARAMETER_VERSION);
            v.setValue("false");
            parameters.add(v);
            OnapCommandParameter h = new OnapCommandParameter();
            h.setName(Constants.DEFAULT_PARAMETER_HELP);
            h.setValue("false");
            parameters.add(h);
            OnapCommandParameter f = new OnapCommandParameter();
            f.setName(Constants.DEFAULT_PARAMETER_OUTPUT_FORMAT);
            f.setValue("table");
            parameters.add(f);
            OnapCommandParameter l = new OnapCommandParameter();
            l.setName(Constants.DEFAULT_PARAMETER_OUTPUT_ATTR_LONG);
            l.setValue("true");
            parameters.add(l);
            OnapCommandParameter t = new OnapCommandParameter();
            t.setName(Constants.DEFAULT_PARAMETER_OUTPUT_NO_TITLE);
            t.setValue("true");
            parameters.add(t);
            OnapCommandParameter a = new OnapCommandParameter();
            a.setName(Constants.DEFAULT_PARAMETER_OUTPUT_NO_AUTH);
            a.setValue("true");
            parameters.add(a);
            OnapCommandParameter d = new OnapCommandParameter();
            d.setName(Constants.DEFAULT_PARAMETER_DEBUG);
            d.setValue("true");
            parameters.add(d);
            OnapCommandParameter m = new OnapCommandParameter();
            m.setName(Constants.DEAFULT_PARAMETER_HOST_URL);
            m.setValue("http://localhost");
            parameters.add(m);
            sample.setParameters(parameters);
            sample.execute();
        } catch (OnapCommandException e) {
        }
    }

    @Test(expected = OnapCommandExecutionFailed.class)
    public void sampleTestFailure() throws OnapCommandException {
        OnapCommandSample sample = new OnapCommandSample();
        sample.failCase = true;

        List<OnapCommandParameter> parameters = new ArrayList();
        OnapCommandParameter v = new OnapCommandParameter();
        v.setName(Constants.DEFAULT_PARAMETER_VERSION);
        v.setValue("false");
        parameters.add(v);
        OnapCommandParameter h = new OnapCommandParameter();
        h.setName(Constants.DEFAULT_PARAMETER_HELP);
        h.setValue("false");
        parameters.add(h);
        OnapCommandParameter f = new OnapCommandParameter();
        f.setName(Constants.DEFAULT_PARAMETER_OUTPUT_FORMAT);
        f.setValue("table");
        parameters.add(f);
        OnapCommandParameter l = new OnapCommandParameter();
        l.setName(Constants.DEFAULT_PARAMETER_OUTPUT_ATTR_LONG);
        l.setValue("true");
        parameters.add(l);
        OnapCommandParameter t = new OnapCommandParameter();
        t.setName(Constants.DEFAULT_PARAMETER_OUTPUT_NO_TITLE);
        t.setValue("true");
        parameters.add(t);
        OnapCommandParameter a = new OnapCommandParameter();
        a.setName(Constants.DEFAULT_PARAMETER_OUTPUT_NO_AUTH);
        a.setValue("true");
        parameters.add(a);
        OnapCommandParameter d = new OnapCommandParameter();
        d.setName(Constants.DEFAULT_PARAMETER_DEBUG);
        d.setValue("true");
        parameters.add(d);
        OnapCommandParameter m = new OnapCommandParameter();
        m.setName(Constants.DEAFULT_PARAMETER_HOST_URL);
        m.setValue("http://localhost");
        parameters.add(m);
        sample.setParameters(parameters);
        sample.execute();
    }

    @Test(expected = OnapCommandNotInitialized.class)
    public void sampleTestIsInitialized() throws OnapCommandException {
        OnapCommandSample sample = new OnapCommandSample(false);
        sample.execute();
    }
}
