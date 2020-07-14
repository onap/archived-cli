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
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandInvalidParameterType;

public class ParameterTypeTest {
    @Test
    public void paramTypeGetTest() {

        try {
            assertTrue(OnapCommandParameterType.JSON.equals(OnapCommandParameterType.get("json"))
                    && OnapCommandParameterType.YAML.equals(OnapCommandParameterType.get("yaml"))
                    && OnapCommandParameterType.STRING.equals(OnapCommandParameterType.get("string"))
                    && OnapCommandParameterType.DIGIT.equals(OnapCommandParameterType.get("digit"))
                    && OnapCommandParameterType.URL.equals(OnapCommandParameterType.get("url"))
                    && OnapCommandParameterType.BOOL.equals(OnapCommandParameterType.get("bool"))
                    && OnapCommandParameterType.MAP.equals(OnapCommandParameterType.get("map"))
                    && OnapCommandParameterType.BINARY.equals(OnapCommandParameterType.get("binary"))
                    && OnapCommandParameterType.TEXT.equals(OnapCommandParameterType.get("text"))
                    && OnapCommandParameterType.ARRAY.equals(OnapCommandParameterType.get("array")));
        } catch (OnapCommandInvalidParameterType e) {
            fail("Shouldn't have thrown this exception : " + e.getMessage());
        }

        try {
            OnapCommandParameterType.get("name");
        } catch (OnapCommandInvalidParameterType e) {
        	assertEquals("0x7001::Parameter type name is invalid", e.getMessage());
        }

    }

}
