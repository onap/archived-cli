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

package org.onap.cli.fw.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.http.HttpInput;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.ParameterType;

public class OnapHttpCommandTest {

    @Ignore
    @Test(expected = OnapCommandException.class)
    public void runTest() throws OnapCommandException {
        OnapCommandParameter param1 = new OnapCommandParameter();
        param1.setLongOption("host-username");
        param1.setName("host-username");
        param1.setParameterType(ParameterType.STRING);
        OnapCommandParameter param2 = new OnapCommandParameter();
        param2.setLongOption("host-password");
        param2.setName("host-password");
        param2.setParameterType(ParameterType.STRING);
        OnapCommandParameter param3 = new OnapCommandParameter();
        param3.setLongOption("host-url");
        param3.setName("host-url");
        param3.setParameterType(ParameterType.STRING);
        OnapCommandParameter param4 = new OnapCommandParameter();
        param4.setLongOption("string-param");
        param4.setName("string-param");
        param4.setParameterType(ParameterType.STRING);
        OnapCommandParameter param5 = new OnapCommandParameter();
        param5.setLongOption("long-opt");
        param5.setName("long-opt");
        param5.setParameterType(ParameterType.STRING);

        List<OnapCommandParameter> paramslist = new ArrayList<>();
        paramslist.add(param1);
        paramslist.add(param2);
        paramslist.add(param3);
        paramslist.add(param4);
        paramslist.add(param5);

        HttpInput inp = new HttpInput();
        inp.setBody("body");
        inp.setMethod("method");
        inp.setReqCookies(new HashMap<String, String>());
        inp.setReqHeaders(new HashMap<String, String>());
        inp.setReqQueries(new HashMap<String, String>());
        inp.setUri("uri");

        OnapHttpCommand com = new OnapHttpCommand();
        com.setParameters(paramslist);
        com.getParameters();
        com.getParametersMap();
        com.setInput(inp);
        com.initializeSchema("sample-test-schema.yaml");
        com.execute();

    }

}
