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
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandPersistProfileFailed;
import org.onap.cli.fw.input.cache.OnapCommandParamEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import java.io.IOException;

public class OnapCommandProfileStoreTest {
    OnapCommandProfileStore onapCommandProfileStore;

    @Before
    public void setUp() throws Exception {
        onapCommandProfileStore = OnapCommandProfileStore.getInstance();
    }

    @Test
    public void includeProfileTest() throws OnapCommandException {
        onapCommandProfileStore.includeProfile("profiles");
        assertTrue(new File(System.getProperty("user.dir") + File.separator + "data/profiles").exists());
    }

    @Test
    public void persistProfileAndgetProfilesTest() throws OnapCommandPersistProfileFailed {
        OnapCommandParamEntity onapCommandParamEntity = new OnapCommandParamEntity();
        onapCommandParamEntity.setName("schema-list");
        onapCommandParamEntity.setProduct("open-cli");
        onapCommandParamEntity.setValue("value");
        List<OnapCommandParamEntity> paramEntityList = new ArrayList<>();
        paramEntityList.add(onapCommandParamEntity);
        onapCommandProfileStore.persistProfile(paramEntityList, "abc");
        assertTrue(new File(System.getProperty("user.dir") + File.separator + "data/profiles/abc-profile.json").exists());
        assertNotNull(onapCommandProfileStore.getProfiles());
    }

    @Test
    public void removeProfileTest() {
        onapCommandProfileStore.removeProfile("abc");
        assertFalse(new File(System.getProperty("user.dir") + File.separator + "data/profiles/abc-profile.json").exists());
    }
    @Test
    public void removeProfileDeleteTest() throws IOException {
        new File(System.getProperty("user.dir") + File.separator + "data/profiles/abc-profile.json").createNewFile();
        onapCommandProfileStore.removeProfile("abc");
        assertFalse(new File(System.getProperty("user.dir") + File.separator + "data/profiles/abc-profile.json").exists());
    }

    @Test
    public void addTest() {
        onapCommandProfileStore.add("abc", "abc", "abc");
    }

    @Test
    public void getParamsTest() {
        assertNotNull(onapCommandProfileStore.getParams("abc"));
    }



    @AfterClass
    public static void tearDown() throws Exception {
        String dirPathForData = System.getProperty("user.dir") + File.separator + "data";
        File dataDir = new File(dirPathForData);
        assertTrue(OnapCommandExceutionListCommandTest.deleteDirectory(dataDir));
    }
}