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

package org.onap.cli.fw.input;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidParameterValue;

public class OnapCommandParameterTest {

    @Test
    public void parameterObjTest() throws OnapCommandInvalidParameterValue {
        OnapCommandParameter param = new OnapCommandParameter();
        param.setDefaultValue("defaultValue");
        param.setDescription("description");
        param.setLongOption("longOption");
        param.setName("name");
        param.setOptional(true);
        param.setParameterType(OnapCommandParameterType.JSON);
        param.setSecured(false);
        param.setShortOption("shortOption");
        param.setValue("value");

        assertTrue(param.getDefaultValue().equals("defaultValue") && param.getDescription().equals("description")
                && param.getLongOption().equals("longOption") && param.getName().equals("name")
                && param.getShortOption().equals("shortOption") && param.getValue().equals("value")
                && param.isOptional() && !param.isSecured()
                && param.getParameterType().equals(OnapCommandParameterType.JSON));

        assertTrue("value".equals(param.getValue()));

        param.setParameterType(OnapCommandParameterType.ARRAY);
        List<String> list = Arrays.asList("1", "2", "3");
        param.setValue(list);
        assertTrue(((List)param.getValue()).containsAll(list));

        param.setRawDefaultValue("[\"1\", \"2\", \"3\", \"4\"]");
        assertTrue(((List<String>)param.getDefaultValue())
                .containsAll(Arrays.asList("1", "2", "3", "4")));

        param.setParameterType(OnapCommandParameterType.MAP);
        Map<String, String> map = new HashMap<>();
        map.put("One", "1");
        map.put("Two", "2");
        map.put("Three", "3");
        param.setValue(map);
        HashMap<String, String> value = (HashMap<String, String>) param.getValue();
        assertTrue(value.keySet().containsAll(Arrays.asList("One", "Two", "Three")));
        assertTrue(value.values().containsAll(Arrays.asList("1", "2", "3")));

        param.setRawDefaultValue("{\"key1\":\"$s{env:defaultValue}\"}");
        assertTrue(((Map<String, String>)param.getDefaultValue()).values().containsAll(
                Arrays.asList("env:defaultValue")
        ));
    }

    @Test
    public void parameterEnvDefaultValueObjTest() throws OnapCommandInvalidParameterValue {
        OnapCommandParameter param = new OnapCommandParameter();
        param.setRawDefaultValue("$s{env:DAFAULT_VALUE}");
        boolean isDefaultValueAnEnv = param.isRawDefaultValueAnEnv();
        assertTrue(isDefaultValueAnEnv);

        String envValue = param.getEnvVarNameFromrRawDefaultValue();

        assertTrue("DAFAULT_VALUE".equals(envValue));
    }

    @Test
    public void parameterValidateTest() throws OnapCommandInvalidParameterValue {
        OnapCommandParameter param = new OnapCommandParameter();
        param.setOptional(false);
        param.setValue("");
        param.setDefaultValue("");
        param.setParameterType(OnapCommandParameterType.STRING);
        try {
            param.validate();
        } catch (OnapCommandException e) {
            assertTrue("0x7003::Parameter null is mandatory".equals(e.getMessage()));
        }
    }

    @Test(expected = OnapCommandInvalidParameterValue.class)
    public void oclipCommandInvalidParameterValueArrayExeceptionTest() throws OnapCommandInvalidParameterValue {
        OnapCommandParameter param = new OnapCommandParameter();
        param.setName("name");
        param.setParameterType(OnapCommandParameterType.ARRAY);
        param.setValue("value");
        assertTrue("[\"1\",\"2\",\"3\"]".equals(param.getValue()));

    }

    @Test(expected = OnapCommandInvalidParameterValue.class)
    public void oclipCommandInvalidParameterValueMapExeceptionTest() throws OnapCommandInvalidParameterValue {
        OnapCommandParameter param = new OnapCommandParameter();
        param.setName("name");
        param.setParameterType(OnapCommandParameterType.MAP);
        param.setValue("value");
        assertTrue("{\"One\":\"1\",\"Two\":\"2\",\"Three\":\"3\"}".equals(param.getValue()));
    }

    @Test(expected = OnapCommandInvalidParameterValue.class)
    public void oclipCommandInvalidParameterValueBinaryExeceptionTest() throws OnapCommandException {
        OnapCommandParameter param = new OnapCommandParameter();
        param.setName("name");
        param.setParameterType(OnapCommandParameterType.BINARY);
        param.setValue("value");
        param.validate();
    }
    @Test
    public void parameterObjTestForGson() throws OnapCommandInvalidParameterValue {
        OnapCommandParameter param = new OnapCommandParameter();
        param.setDefaultValue("defaultValue");
        param.setDescription("description");
        param.setLongOption("longOption");
        param.setName("name");
        param.setOptional(true);
        param.setParameterType(OnapCommandParameterType.JSON);
        param.setSecured(false);
        param.setShortOption("shortOption");
        param.setValue("value");

        param.setParameterType(OnapCommandParameterType.ARRAY);
        List<String> list = Arrays.asList("1", "2", "3");
        param.setValue(list);
        assertTrue(((List)param.getValue()).containsAll(list));

        param.setRawDefaultValue("[\"1\", \"2\", \"3\", \"4\"]");
        assertTrue(((List<String>)param.getDefaultValue())
                .containsAll(Arrays.asList("1", "2", "3", "4")));

        param.setParameterType(OnapCommandParameterType.MAP);
        param.setRawDefaultValue("{\"testKey\":\"testValue\"}");
        assertTrue(((Map<String, String>)param.getDefaultValue()).values().containsAll(
                Arrays.asList("testValue")
        ));
    }

}
