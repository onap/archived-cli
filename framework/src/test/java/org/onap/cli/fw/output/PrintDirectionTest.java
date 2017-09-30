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

import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandInvalidPrintDirection;

public class PrintDirectionTest {
    @Test
    public void printDirectionGetTest() {

        try {
            assertTrue(PrintDirection.LANDSCAPE.equals(PrintDirection.get("landscape"))
                    && PrintDirection.PORTRAIT.equals(PrintDirection.get("portrait")));
        } catch (OnapCommandInvalidPrintDirection e) {
            fail("Shouldn't have thrown this exception : " + e.getMessage());
        }

        try {
            PrintDirection.get("name");
        } catch (OnapCommandInvalidPrintDirection e) {
            assertTrue("0x8003::Print direction name is invalid".equals(e.getMessage()));
        }

    }
}
