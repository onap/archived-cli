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

import java.util.Collections;

import org.junit.Test;
import org.onap.cli.fw.input.OnapCommandParameterType;

public class OnapCommandResultAttributeScopeTest {
    @Test
    public void oclipCommandResultAttributeTest() {
        OnapCommandResultAttribute att = new OnapCommandResultAttribute();
        att.setDescription("description");
        att.setName("name");
        att.setScope(OnapCommandResultAttributeScope.LONG);
        att.setSecured(true);
        att.setType(OnapCommandParameterType.DIGIT);
        att.setValues(Collections.emptyList());
        assertTrue("description".equals(att.getDescription()) && "name".equals(att.getName())
                && OnapCommandResultAttributeScope.LONG.equals(att.getScope())
                && OnapCommandParameterType.DIGIT.equals(att.getType()) && att.getValues().isEmpty());
    }

}
