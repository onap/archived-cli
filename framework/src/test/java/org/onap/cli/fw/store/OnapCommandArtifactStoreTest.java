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

import org.junit.Before;
import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandArtifactAlreadyExist;
import org.onap.cli.fw.error.OnapCommandArtifactContentChecksumNotMatch;
import org.onap.cli.fw.error.OnapCommandArtifactContentNotExist;
import org.onap.cli.fw.error.OnapCommandArtifactNotFound;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class OnapCommandArtifactStoreTest {
     OnapCommandArtifactStore onapCommandArtifactStore;
    OnapCommandArtifactStore.Artifact artifact;
    @Before
    public void setUp() throws Exception {
        onapCommandArtifactStore= OnapCommandArtifactStore.getStore();
       artifact =new OnapCommandArtifactStore.Artifact();
        Map<String,String> metadata=new HashMap<>();
        metadata.put("name","data");
        artifact.setName("artifact");
        artifact.setCategoty("category");
        artifact.setChecksum("checksum");
        artifact.setPath(System.getProperty("user.dir")+ File.separator+"src/test/resources/sample-cmd-test-help.txt");
        artifact.setCreateAt("12");
        artifact.setDescription("description");
        artifact.setLastUpdatedAt("12");
        artifact.setMetadata(metadata);
        artifact.setSize(12L);
    }
    @Test
    public void createAndDeleteArtifactTest() throws OnapCommandArtifactContentChecksumNotMatch, OnapCommandArtifactAlreadyExist, OnapCommandArtifactContentNotExist, OnapCommandArtifactNotFound {
        assertNotNull(onapCommandArtifactStore.createArtifact(artifact));
        onapCommandArtifactStore.deleteArtifact("artifact","category");
        String artifactPath=System.getProperty("user.dir")+File.separator+"data/artifacts";
        File artifactFile= new File(artifactPath+File.separator+"artifact__category.json");
        assertFalse(artifactFile.exists());

    }
    @Test
    public void updateArtifactTest() throws OnapCommandArtifactNotFound, OnapCommandArtifactAlreadyExist, OnapCommandArtifactContentNotExist, OnapCommandArtifactContentChecksumNotMatch {
        onapCommandArtifactStore.createArtifact(artifact);
        OnapCommandArtifactStore.Artifact artifactForUpdate =new OnapCommandArtifactStore.Artifact();
        Map<String,String> metadata=new HashMap<>();
        metadata.put("name","data");
        artifactForUpdate.setName("artifactForUpdate");
        artifactForUpdate.setCategoty("categoryUpdate");
        artifactForUpdate.setChecksum("checksum");
        artifactForUpdate.setPath(System.getProperty("user.dir")+ File.separator+"src/test/resources/sample-cmd-test-help.txt");
        artifactForUpdate.setCreateAt("12");
        artifactForUpdate.setDescription("description");
        artifactForUpdate.setLastUpdatedAt("12");
        artifactForUpdate.setMetadata(metadata);
        assertNotNull(onapCommandArtifactStore.updateArtifact("artifact","category",artifactForUpdate));
        onapCommandArtifactStore.deleteArtifact("artifactForUpdate","categoryUpdate");
    }
    @Test(expected = OnapCommandArtifactNotFound.class)
    public void getArtifactTest() throws OnapCommandArtifactNotFound {
       onapCommandArtifactStore.getArtifact("abc","abc");
    }
    @Test
    public void listArtifactTest() throws OnapCommandArtifactNotFound {
        assertNotNull(onapCommandArtifactStore.listArtifact("category","namePattern"));
    }
    @Test
    public void createArtifactTestForGson() throws OnapCommandArtifactContentChecksumNotMatch, OnapCommandArtifactAlreadyExist, OnapCommandArtifactContentNotExist, OnapCommandArtifactNotFound {
        assertNotNull(onapCommandArtifactStore.createArtifact(artifact));
        String artifactPath=System.getProperty("user.dir")+File.separator+"data/artifacts";
        File artifactFile= new File(artifactPath+File.separator+"artifact__category.json");
        assertTrue(artifactFile.exists());
        onapCommandArtifactStore.deleteArtifact("artifact","category");

    }

    @Test
    public void listArtifactTestForGson() throws OnapCommandArtifactNotFound {
        String basePath= System.getProperty("user.dir")+File.separator+"data/artifacts/testFile.json";
        File testFile = new File(basePath);
        if (!testFile.exists()) {
            FileWriter fileWriter= null;
            BufferedWriter writer = null;
            try {
                testFile.createNewFile();
                fileWriter = new FileWriter(testFile);
                writer = new BufferedWriter(fileWriter);
                String content = "{\"name\": \"name\",\"description\": \"description\",\"categoty\": \"categoty\"," +
                        "\"checksum\": \"checksum\",\"size\": 100,\"createAt\": \"createAt\"" +
                        ",\"lastUpdatedAt\":\"lastUpdatedAt\",\"path\": \"path\",\"metadata\": {}}";
                writer.write(content);

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                    if (fileWriter != null){
                        fileWriter.close();
                    }
                } catch (IOException e) {
                    //
                }
            }
        }
        assertNotNull(onapCommandArtifactStore.listArtifact("category","namePattern"));

        testFile.delete();
    }
}