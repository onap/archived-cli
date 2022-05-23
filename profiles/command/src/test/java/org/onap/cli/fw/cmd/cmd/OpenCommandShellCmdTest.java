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

package org.onap.cli.fw.cmd.cmd;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class OpenCommandShellCmdTest {
    List<String> command = new ArrayList<>();
    Map<String, String> envVariable = new HashMap<String, String>();
    Map<String, String> resultMap = new IdentityHashMap<>();

    @Test
    public void setCmdTest() {
        OpenCommandShellCmd cmdShell = new OpenCommandShellCmd();
        command.add("test");
        cmdShell.setCommand(command);
        Assert.assertEquals("test", cmdShell.getCommand().get(0));
    }

    @Test
    public void setCmdEnvVariableTest() {
        OpenCommandShellCmd cmdShell = new OpenCommandShellCmd();
        envVariable.put("variableOne","test");
        cmdShell.setEnvs(envVariable);
        Assert.assertEquals("test", cmdShell.getEnvs().get("variableOne"));
    }

    @Test
    public void setCmdErrorTest() {
        OpenCommandShellCmd cmdShell = new OpenCommandShellCmd();
        cmdShell.setError("Timeout");
        Assert.assertEquals("Timeout", cmdShell.getError());
    }

    @Test
    public void setResultMapTest() {
        OpenCommandShellCmd cmdShell = new OpenCommandShellCmd();
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("var1","text1");
        cmdShell.setResultMap(resultMap);
        Assert.assertEquals("text1", cmdShell.getResultMap().get("var1"));
    }

    @Test
    public void setCmdOutputTest() {
        OpenCommandShellCmd cmdShell = new OpenCommandShellCmd();
        cmdShell.setOutput("Timeout");
        Assert.assertEquals("Timeout", cmdShell.getOutput());
    }

    @Test
    public void setPassCodesTest() {
        OpenCommandShellCmd cmdShell = new OpenCommandShellCmd();
        List<Integer> resultMap = new ArrayList<Integer>();
        resultMap.add(200);
        cmdShell.setPassCodes(resultMap);
        Assert.assertEquals(Optional.ofNullable(200), Optional.ofNullable(cmdShell.getPassCodes().get(0)));
    }
}

