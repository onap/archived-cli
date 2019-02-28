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
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandExecutionFailed;
import org.onap.cli.fw.error.OnapCommandExecutionNotFound;
import org.onap.cli.fw.utils.ProcessRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnapCommandExecutionStore {
    private static Logger log = LoggerFactory.getLogger(OnapCommandExecutionStore.class);

    private static boolean storeReady = false;

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);

    static {
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private static String SEPARATOR = "__";

    private enum SearchMode {
        find,
        file //for developer mode
    }
    private static SearchMode SEARCH_MODE = SearchMode.file;

    public static class ExecutionStoreContext {

        private String executionId;
        private String storePath;
        public String getExecutionId() {
            return executionId;
        }
        public ExecutionStoreContext setExecutionId(String executionId) {
            this.executionId = executionId;
            return this;
        }
        public String getStorePath() {
            return storePath;
        }
        public ExecutionStoreContext setStorePath(String storePath) {
            this.storePath = storePath;
            return this;
        }
    }

    public static class Execution {
        private String id;
        private String requestId;
        private String status;
        private String startTime;
        private String endTime;
        private String input;
        private String output;
        private String profile;
        private String command;
        private String product;
        private String service;

        public String getInput() {
            return input;
        }
        public void setInput(String input) {
            this.input = input;
        }
        public String getOutput() {
            return output;
        }
        public void setOutput(String output) {
            this.output = output;
        }
        public String getProfile() {
            return profile;
        }
        public void setProfile(String profile) {
            this.profile = profile;
        }
        public String getCommand() {
            return command;
        }
        public void setCommand(String command) {
            this.command = command;
        }
        public String getProduct() {
            return product;
        }
        public void setProduct(String product) {
            this.product = product;
        }
        public String getService() {
            return service;
        }
        public void setService(String service) {
            this.service = service;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getEndTime() {
            return endTime;
        }
        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
        public void setStartTime(String timeOfExecution) {
            this.startTime = timeOfExecution;
        }
        public String getStartTime() {
            return startTime;
        }
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        public String getRequestId() {
            return requestId;
        }
        public void setRequestId(String requestId) {
            this.requestId = requestId;
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

    private static OnapCommandExecutionStore store = null;

    private OnapCommandExecutionStore() {

    }

    public static OnapCommandExecutionStore getStore() {
        if (store == null) {
            store = new OnapCommandExecutionStore();
        }

        return store;
    }

    private static String getBasePath() {
        return OnapCommandConfig.getPropertyValue(OnapCommandConstants.OPEN_CLI_DATA_DIR) +
                File.separator + "executions";
    }

    public ExecutionStoreContext storeExectutionStart(
            String requestId, String product, String service, String cmd, String profile, String input) {

        String executionId = requestId + "-" + System.currentTimeMillis();

        String storePath = getBasePath() + File.separator + executionId + SEPARATOR + product +
                SEPARATOR + service +
                SEPARATOR + cmd +
                (profile != null ? (SEPARATOR + profile) : "" );
        try {
            File dir = new File(storePath);
            FileUtils.forceMkdir(dir);

            if (product != null)
                FileUtils.writeStringToFile(new File(dir.getAbsolutePath() + File.separator + "product"), product);
            if (service != null)
                FileUtils.writeStringToFile(new File(dir.getAbsolutePath() + File.separator + "service"), service);
            if (cmd != null)
                FileUtils.writeStringToFile(new File(dir.getAbsolutePath() + File.separator + "command"), cmd);

            FileUtils.writeStringToFile(new File(dir.getAbsolutePath() + File.separator + "requestId"), requestId);

            FileUtils.writeStringToFile(new File(dir.getAbsolutePath() + File.separator + "executionId"), executionId);

            if (input != null)
                FileUtils.writeStringToFile(new File(dir.getAbsolutePath() + File.separator + "input"), input);
            if (profile != null)
                FileUtils.writeStringToFile(new File(dir.getAbsolutePath() + File.separator + "profile"), profile);
            FileUtils.touch(new File(dir.getAbsolutePath() + File.separator + "in-progress"));
        } catch (IOException e) {
            log.error("Failed to store the execution start details " + storePath);
        }

        return new ExecutionStoreContext().setExecutionId(executionId).setStorePath(storePath);
    }

    public void storeExectutionEnd(
            ExecutionStoreContext execContext,
            String output, String error, boolean passed) {

        try {
            File dir = new File(execContext.getStorePath());
            FileUtils.forceMkdir(dir);

            if (output != null)
                FileUtils.writeStringToFile(new File(dir.getAbsolutePath() + File.separator + "output"), output);
            if (error != null)
                FileUtils.writeStringToFile(new File(dir.getAbsolutePath() + File.separator + "error"), error);

            if (passed)
                FileUtils.touch(new File(dir.getAbsolutePath() + File.separator + "completed"));
            else
                FileUtils.touch(new File(dir.getAbsolutePath() + File.separator + "failed"));

            new File(dir.getAbsolutePath() + File.separator + "in-progress").delete();
        } catch (IOException e) {
            log.error("Failed to store the execution end details " + execContext.storePath);
        }
    }

    public List<OnapCommandExecutionStore.Execution> listExecutions(Map<String, String> search) throws OnapCommandExecutionFailed {
        List <OnapCommandExecutionStore.Execution> list = new ArrayList<>();

        try {
            List <String> dirs = new ArrayList<>();
            if (SEARCH_MODE.equals(SearchMode.file)) {
                for (File f: new File(getBasePath()).listFiles())
                    dirs.add(f.getAbsolutePath());
            } else {
                //find results -type d -newermt '2019-02-11 10:00:00' ! -newermt '2019-02-11 15:10:00' -name "*__*__profile-list*"
                //find 'results' -type d -newermt '2019-02-11T10:00:00.000' ! -newermt '2019-02-11T15:10:00.000' -name "*__*__profile*"

                String searchString = "find '" + new File(getBasePath()).getAbsolutePath() + "' -type d ";

                String startTime = search.get("startTime");
                if (startTime != null) {
                    searchString += " -newermt " + startTime ;
                }

                String endTime = search.get("endTime");
                if (endTime != null) {
                    searchString += " ! -newermt " + endTime ;
                }

                searchString += " -name '";

                if(search.containsKey("execution-id")) {
                    searchString += search.get("execution-id");
                } else if(search.containsKey("request-id")) {
                    searchString += search.get("request-id") + "*";
                } else {
                    searchString += "*";
                }

                for (String term: Arrays.asList(new String []{"product", "service", "command", "profile"})) {
                    searchString += "__";
                    if (search.get(term) != null) {
                        searchString += search.get(term);
                    } else {
                        searchString += "*";
                    }
                }
                if (!searchString.endsWith("*"))
                    searchString += "*";

                searchString += "'";

                ProcessRunner pr = new ProcessRunner(new String [] {searchString}, null, ".");
                pr.overrideToUnix();
                pr.run();
                if (pr.getExitCode() != 0) {
                    throw new OnapCommandExecutionFailed("System failed to search the executions with error " + pr.getError());
                }

                dirs = Arrays.asList(pr.getOutput().split("\\r?\\n"));
            }

            for (String dir: dirs) {
                list.add(this.makeExecution(dir));
            }
        } catch (IOException e) {
            throw new OnapCommandExecutionFailed(e, "Failed to search the executions");
        } catch (InterruptedException e) {
            throw new OnapCommandExecutionFailed(e, "Failed to search the executions");
        }

        return list;
    }

    private Execution makeExecution(String executionStorePath) throws IOException {
        OnapCommandExecutionStore.Execution exectuion = new OnapCommandExecutionStore.Execution();
        if (new File(executionStorePath + File.separator + "requestId").exists())
            exectuion.setRequestId(FileUtils.readFileToString(new File(executionStorePath + File.separator + "requestId")));
        if (new File(executionStorePath + File.separator + "executionId").exists())
            exectuion.setId(FileUtils.readFileToString(new File(executionStorePath + File.separator + "executionId")));
        exectuion.setProduct(FileUtils.readFileToString(new File(executionStorePath + File.separator + "product")));
        exectuion.setService(FileUtils.readFileToString(new File(executionStorePath + File.separator + "service")));
        exectuion.setCommand(FileUtils.readFileToString(new File(executionStorePath + File.separator + "command")));
        if (new File(executionStorePath + File.separator + "profile").exists())
            exectuion.setProfile(FileUtils.readFileToString(new File(executionStorePath + File.separator + "profile")));

        exectuion.setInput(FileUtils.readFileToString(new File(executionStorePath + File.separator + "input")));
        exectuion.setStartTime(dateFormatter.format(new File(executionStorePath + File.separator + "input").lastModified()));

        if (new File(executionStorePath + File.separator + "in-progress").exists()) {
            exectuion.setStatus("in-progress");
        } else if (new File(executionStorePath + File.separator + "completed").exists()) {
            exectuion.setStatus("completed");
            if (new File(executionStorePath + File.separator + "output").exists()) {
                exectuion.setOutput(FileUtils.readFileToString(new File(executionStorePath + File.separator + "output")));
                exectuion.setEndTime(dateFormatter.format(new File(executionStorePath + File.separator + "output").lastModified()));
            }
        } else if (new File(executionStorePath + File.separator + "failed").exists()) {
            exectuion.setStatus("failed");
            if (new File(executionStorePath + File.separator + "error").exists()) {
                exectuion.setOutput(FileUtils.readFileToString(new File(executionStorePath + File.separator + "error")));
                exectuion.setEndTime(dateFormatter.format(new File(executionStorePath + File.separator + "error").lastModified()));
            }
        }

        return exectuion;
    }
    public Execution getExecution(String executionId) throws OnapCommandExecutionNotFound, OnapCommandExecutionFailed {
        File []f =  new File(getBasePath()).listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if (name.startsWith(executionId)) return true;
                return false;
            }
        });

        if (f.length == 0) {
            throw new OnapCommandExecutionNotFound(executionId);
        }

        try {
            return this.makeExecution(f[0].getAbsolutePath());
        } catch (IOException e) {
            throw new OnapCommandExecutionFailed(e, "Failed to retrieve the execution");
        }
    }
}
