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

package org.onap.cli.fw.output;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandInvalidResultAttributeScope;

public class OnapCommandResultAttributeTest {
    @Test
    public void paramTypeGetTest() {

        try {
            assertTrue(OnapCommandResultAttributeScope.LONG.equals(OnapCommandResultAttributeScope.get("long"))
                    && OnapCommandResultAttributeScope.SHORT.equals(OnapCommandResultAttributeScope.get("short")));
        } catch (OnapCommandInvalidResultAttributeScope e) {
            fail("Shouldn't have thrown this exception : " + e.getMessage());
        }

        try {
            OnapCommandResultAttributeScope.get("name");
        } catch (OnapCommandInvalidResultAttributeScope e) {
        	assertEquals("0x8002::Result atrribute name is invalid", e.getMessage());
        }

    }

}
