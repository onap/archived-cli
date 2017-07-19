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

package org.onap.cli.fw.run;

import org.junit.Assert;
import org.junit.Test;

public class OnapCommandExecutorTest {

    @Test
    public void commandExecutorTest() {
        OnapCommandExecutor exec = new OnapCommandExecutor();
        exec.setApi("api");
        exec.setClient("client");
        exec.setEntity("entity");
        exec.setException("exception");
        exec.setMethod("method");

        Assert.assertTrue(
                exec.getApi().equals("api") && exec.getClient().equals("client") && exec.getEntity().equals("entity")
                        && exec.getMethod().equals("method") && exec.getException().equals("exception"));
    }

}
