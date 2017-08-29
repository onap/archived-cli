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

package org.onap.cli.fw.utils;

import org.junit.Before;
import org.junit.Test;
import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.OnapCommandRegistrar;
import org.onap.cli.fw.error.OnapCommandException;


public class OpenCommandRegistrarTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test() throws OnapCommandException {
        OnapCommandRegistrar registrar = OnapCommandRegistrar.getRegistrar();
        OnapCommand cmd = registrar.get("sample-test");
        cmd.printVersion();
        registrar.listCommands();
        
    }

}
