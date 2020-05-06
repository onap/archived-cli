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

package org.onap.cli.fw.store;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.onap.cli.fw.cmd.execution.OnapCommandExceutionListCommandTest;
import org.onap.cli.fw.error.OnapCommandExecutionFailed;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import java.io.IOException;

public class OnapCommandExecutionStoreTest {
    OnapCommandExecutionStore executionStore;

    @Before
    public void setUp() throws Exception {
        executionStore = OnapCommandExecutionStore.getStore();
    }

    @Test
    public void storeExectutionStartTest() {
        assertNotNull(executionStore.storeExectutionStart("requestId", "product", "service", "cmd", "profile", "input"));
    }

    @Test
    public void listExecutionsTest() throws OnapCommandExecutionFailed {
        Map<String, String> search = new HashMap<>();
        search.put("startTime", "12");
        search.put("endTime", "12");
        search.put("execution-id", "abc");
        search.put("request-id", "abc");
        assertNotNull(executionStore.listExecutions(search));
    }

    @Test
    public void storeExectutionEndTest() {
        OnapCommandExecutionStore.ExecutionStoreContext store = new OnapCommandExecutionStore.ExecutionStoreContext();
        store.setExecutionId("abc");
        store.setRequestId("abc");
        store.setStorePath("abc");
        executionStore.storeExectutionEnd(store, "abc", "abc", "abc", true);
        assertTrue(new File(System.getProperty("user.dir") + File.separator + "abc").exists());
    }
    @Test
    public void storeExectutionEndDeleteTest() throws IOException {
        new File("in-progress").createNewFile();
        OnapCommandExecutionStore.ExecutionStoreContext store = new OnapCommandExecutionStore.ExecutionStoreContext();
        store.setExecutionId("abc");
        store.setRequestId("abc");
        store.setStorePath("target/");
        executionStore.storeExectutionEnd(store, "abc", "abc", "abc", true);
        assertFalse(new File(System.getProperty("user.dir") + File.separator + "abc").exists());
    }

    @Test
    public void storeExectutionProgressTest() {
        OnapCommandExecutionStore.ExecutionStoreContext store = new OnapCommandExecutionStore.ExecutionStoreContext();
        store.setExecutionId("abc");
        store.setRequestId("abc");
        store.setStorePath("abc");
        executionStore.storeExectutionProgress(store, "abc", "abc", "abc");
        assertTrue(new File(System.getProperty("user.dir") + File.separator + "abc").exists());
    }

    @Test
    public void storeExectutionDebugTest() {
        OnapCommandExecutionStore.ExecutionStoreContext store = new OnapCommandExecutionStore.ExecutionStoreContext();
        store.setExecutionId("abc");
        store.setRequestId("abc");
        store.setStorePath("abc");
        executionStore.storeExectutionDebug(store, "abc");
        assertTrue(new File(System.getProperty("user.dir") + File.separator + "abc").exists());
    }

    @Test
    public void storeExectutionOutputTest() {
        OnapCommandExecutionStore.ExecutionStoreContext store = new OnapCommandExecutionStore.ExecutionStoreContext();
        store.setExecutionId("abc");
        store.setRequestId("abc");
        store.setStorePath("abc");
        executionStore.storeExectutionOutput(store, "abc");
        assertTrue(new File(System.getProperty("user.dir") + File.separator + "abc").exists());
    }



    @AfterClass
    public static void tearDown() throws Exception {
        String dirPath = System.getProperty("user.dir") + File.separator + "abc";
        String dirPathForExecutions = System.getProperty("user.dir") + File.separator + "data/executions";
        File file = new File(dirPath);
        File executionsDir = new File(dirPathForExecutions);
        assertTrue(OnapCommandExceutionListCommandTest.deleteDirectory(file));
        assertTrue(OnapCommandExceutionListCommandTest.deleteDirectory(executionsDir));
    }

}