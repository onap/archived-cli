/*
 * Copyright 2022 Samsung Electronics
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

package org.onap.cli.fw.cmd.error;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OnapCommandFailTest {

    @Test
    public void OnapCommandCmdFailureTest() {
        OnapCommandCmdFailure onapCommandCmdFailure = new OnapCommandCmdFailure("Argument value missing");
        assertEquals("0x18001::Argument value missing", onapCommandCmdFailure.getMessage());
    }

    @Test
    public void OnapCommandCmdFailureWithCodeTest() {
        OnapCommandCmdFailure onapCommandCmdFailure = new OnapCommandCmdFailure("Argument value missing",101);
        assertEquals("101::0x18001::Argument value missing", onapCommandCmdFailure.getMessage());
    }

    @Test
    public void OnapCommandCmdFailureThrowableTest() {
        OnapCommandCmdFailure onapCommandCmdFailure = new OnapCommandCmdFailure(new Exception("Argument value missing"));
        assertEquals("0x18001::Argument value missing", onapCommandCmdFailure.getMessage());
    }

    @Test
    public void OnapCommandCmdFailureWithCodeThrowableTest() {
        OnapCommandCmdFailure onapCommandCmdFailure = new OnapCommandCmdFailure(new Exception("Argument value missing"),101);
        assertEquals("101::0x18001::Argument value missing", onapCommandCmdFailure.getMessage());
    }

}
