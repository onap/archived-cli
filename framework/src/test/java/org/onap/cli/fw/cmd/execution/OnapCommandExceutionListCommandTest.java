/*
 * Copyright 2019 Huawei Technologies Co., Ltd.
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

package org.onap.cli.fw.cmd.execution;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.store.OnapCommandExecutionStoreTest;
import org.onap.cli.fw.utils.FileUtil;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class OnapCommandExceutionListCommandTest {
    static OnapCommandExecutionStoreTest executionStoreTest;

    @BeforeClass
    public static void setUp() throws Exception {
        executionStoreTest = new OnapCommandExecutionStoreTest();
        executionStoreTest.setUp();
        executionStoreTest.storeExectutionStartTest();
    }

    @Test
    public void runTest() throws OnapCommandException {
        OnapCommandExceutionListCommand cmd = new OnapCommandExceutionListCommand();
        cmd.initializeSchema("execution-list.yaml");
        cmd.execute();
        List<OnapCommandResultAttribute> oclipCommandResultAttributes = cmd.getResult()
                .getRecords();
        assertTrue(oclipCommandResultAttributes.size() > 1);

    }
    public static boolean deleteDirectory(File file) {
        if (!file.exists()) {
            return true;
        }
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                deleteDirectory(f);
            }
        }
        return file.delete();
    }
@AfterClass
    public static void tearDown() throws Exception {
        String dirPathForExecutions = System.getProperty("user.dir") + File.separator + "data/executions";
        File executionsFile = new File(dirPathForExecutions);
        assertTrue(deleteDirectory(executionsFile));
    }
}