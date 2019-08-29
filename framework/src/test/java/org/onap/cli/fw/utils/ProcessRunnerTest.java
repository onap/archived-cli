/*
 * Copyright 2018 Samsung Electronics Co., Ltd.
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

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProcessRunnerTest {
    ProcessRunner processRunner;

    @Before
    public void setUp() throws Exception {
        processRunner = new ProcessRunner("ls -l");
    }

    @After
    public void tearDown() throws Exception {}

    @Test
    public void testStreamToString() throws IOException {
        InputStream stubInputStream = IOUtils.toInputStream("Test stream", "UTF-8");
        String out = processRunner.streamToString(stubInputStream);
        Assert.assertEquals("Test stream", out.trim());
    }

}
