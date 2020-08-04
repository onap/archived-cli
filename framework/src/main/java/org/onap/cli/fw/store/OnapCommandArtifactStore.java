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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FileUtils;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandArtifactAlreadyExist;
import org.onap.cli.fw.error.OnapCommandArtifactContentChecksumNotMatch;
import org.onap.cli.fw.error.OnapCommandArtifactContentNotExist;
import org.onap.cli.fw.error.OnapCommandArtifactNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;

public class OnapCommandArtifactStore {
    private static Logger log = LoggerFactory.getLogger(OnapCommandArtifactStore.class);
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    private static boolean storeReady = false; //NOSONAR

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);

    private static final String SEPARATOR = "__";

    public static class Artifact {
        private String name;
        private String description;
        private String categoty;
        private String checksum;
        private long size;
        private String createAt;
        private String lastUpdatedAt;
        private String path;
        private Map<String, String> metadata = new HashMap<>();
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getChecksum() {
            return checksum;
        }
        public void setChecksum(String checksum) {
            this.checksum = checksum;
        }

        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getCategoty() {
            return categoty;
        }
        public void setCategoty(String categoty) {
            this.categoty = categoty;
        }

        public long getSize() {
            return size;
        }
        public void setSize(long l) {
            this.size = l;
        }
        public String getCreateAt() {
            return createAt;
        }
        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }
        public String getLastUpdatedAt() {
            return lastUpdatedAt;
        }
        public void setLastUpdatedAt(String lastUpdatedAt) {
            this.lastUpdatedAt = lastUpdatedAt;
        }
        public String getPath() {
            return path;
        }
        public void setPath(String path) {
            this.path = path;
        }
        public Map<String, String> getMetadata() {
            return metadata;
        }
        public void setMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
        }

    }
   static {
        try {
            FileUtils.forceMkdir(new File(getBasePath()));
            storeReady = true;
        } catch (IOException e) {
            log.error("Failed to create the data store results");
        }
    }

    private static OnapCommandArtifactStore store = null;

    private OnapCommandArtifactStore() {
        this.dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static OnapCommandArtifactStore getStore() {
        if (store == null) {
            store = new OnapCommandArtifactStore();
        }

        return store;
    }

    private static String getBasePath() {
        return OnapCommandConfig.getPropertyValue(OnapCommandConstants.OPEN_CLI_DATA_DIR) +
                File.separator + "artifacts";
    }

    private static String getArtifactPath(String name, String category) {
        return getBasePath() + File.separator + name + SEPARATOR + category + ".json";
    }

    private String getChecksum(String storePath) throws IOException, NoSuchAlgorithmException {
        byte[] b = Files.readAllBytes(Paths.get(storePath));
        byte[] hash = MessageDigest.getInstance("MD5").digest(b); //NOSONAR
        return DatatypeConverter.printHexBinary(hash);
    }

    public Artifact createArtifact(Artifact artifact) throws OnapCommandArtifactContentNotExist, OnapCommandArtifactAlreadyExist, OnapCommandArtifactContentChecksumNotMatch { //NOSONAR
        if (!new File(artifact.getPath()).exists()) {
            throw new OnapCommandArtifactContentNotExist(artifact.getPath());
        }

        String storePath = getArtifactPath(artifact.getName(), artifact.getCategoty());
        File aFile = new File(storePath);
        if (aFile.exists()) {
            throw new OnapCommandArtifactAlreadyExist(artifact.getName(), artifact.getCategoty());
        }

        try {
            String actual = this.getChecksum(artifact.getPath());
            artifact.setChecksum(actual);

            artifact.setSize(new File(artifact.getPath()).length() / 1024);

            artifact.setCreateAt(dateFormatter.format(new Date()));
            artifact.setLastUpdatedAt(artifact.getCreateAt());

            FileUtils.writeStringToFile(new File(storePath), gson.toJson(artifact));
        } catch (Exception e) { // NOSONAR
            //It is expected that this never occurs
            log.error("Failed to store the artifact at {}", storePath);
        }

        return artifact;
    }

    public Artifact getArtifact(String name, String category) throws OnapCommandArtifactNotFound {
        String storePath = getArtifactPath(name, category);
        File aFile = new File(storePath);
        if (!aFile.exists()) {
            throw new OnapCommandArtifactNotFound(name, category);
        }

        try {
            return gson.fromJson(FileUtils.readFileToString(aFile), Artifact.class);
        } catch (Exception e) { // NOSONAR
            //It is expected that this never occurs
            log.error("Failed to retrieve the artifact at {}", storePath);
        }
        return null;
    }


    public List<Artifact> listArtifact(String category, String namePattern) throws OnapCommandArtifactNotFound { //NOSONAR
        List<Artifact> artifacts = new ArrayList<>();

        String searchPattern = "";
        if (namePattern != null && !namePattern.isEmpty()) {
            searchPattern += namePattern;
        } else {
            searchPattern += "*";
        }

        searchPattern += SEPARATOR;

        if (category != null && !category.isEmpty()) {
            searchPattern += category;
        } else {
            searchPattern += "*";
        }

        searchPattern += ".json";

        final String SP = searchPattern; //NOSONAR

        for (File file: new File(getBasePath()).listFiles(/*new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches(SP);
            }
        }*/)) {
            try (JsonReader jsonReader = new JsonReader(new FileReader(file))){
                artifacts.add(gson.fromJson(jsonReader, Artifact.class));
            } catch (Exception e) { // NOSONAR
                //It is expected that this never occurs
                log.error("While seraching Failed to retrieve the artifact at {}", file.getAbsolutePath());
            }
        }

        return artifacts;
    }

    public void deleteArtifact(String name, String category) throws OnapCommandArtifactNotFound {
        String storePath = getArtifactPath(name, category);
        File aFile = new File(storePath);
        if (!aFile.exists()) {
            throw new OnapCommandArtifactNotFound(name, category);
        }
        try {
            Files.delete(Paths.get(storePath));
        } catch (IOException e) {
            log.error("Failed to delete the artifact {}",  aFile.getAbsolutePath());
        }
    }

    public Artifact updateArtifact(String name, String category, Artifact artifact) throws OnapCommandArtifactNotFound, OnapCommandArtifactContentNotExist, OnapCommandArtifactAlreadyExist {
        Artifact existing = this.getArtifact(name, category);
        String existingStorePath = getArtifactPath(name, category);

        String newStorePath = getArtifactPath(artifact.getName(), artifact.getCategoty());
        if ( !existingStorePath.equalsIgnoreCase(newStorePath) && new File(newStorePath).exists()) {
            throw new OnapCommandArtifactAlreadyExist(artifact.getName(), artifact.getCategoty());
        }

        try {
            if (artifact.getName() == null) {
                artifact.setName(existing.getName());
            }

            if (artifact.getDescription() == null) {
                artifact.setDescription(existing.getDescription());
            }

            if (artifact.getCategoty() == null) {
                artifact.setCategoty(existing.getCategoty());
            }

            if (artifact.getPath()!= null) {
                if (!new File(artifact.getPath()).exists()) {
                    throw new OnapCommandArtifactContentNotExist(artifact.getPath());
                }
                String actual = this.getChecksum(artifact.getPath());
                if (!existing.getChecksum().equals(actual)) {
                    artifact.setChecksum(actual);
                    artifact.setSize(new File(artifact.getPath()).length() / 1024);
                }
            } else {
                artifact.setPath(existing.getPath());
            }

            artifact.setCreateAt(existing.getCreateAt());
            artifact.setLastUpdatedAt(dateFormatter.format(new Date()));
            if (artifact.getMetadata().size() > 0) {
                //update to existing one
                for (Map.Entry<String, String> entry: artifact.getMetadata().entrySet()) {
                    if (entry.getValue() == null || entry.getValue().isEmpty() || entry.getValue().equalsIgnoreCase("null")) {
                        existing.getMetadata().remove(entry.getKey());
                    } else
                        existing.getMetadata().put(entry.getKey(), entry.getValue());
                }

                artifact.setMetadata(existing.getMetadata());
            }

            FileUtils.writeStringToFile(new File(newStorePath), gson.toJson(artifact));

            if (!newStorePath.equalsIgnoreCase(existingStorePath)) {
                this.deleteArtifact(name, category);
            }
        } catch (Exception e) { // NOSONAR
            //It is expected that this never occurs
            log.error("Failed to update the artifact at {}", existingStorePath);
        }
        return artifact;
    }
}
