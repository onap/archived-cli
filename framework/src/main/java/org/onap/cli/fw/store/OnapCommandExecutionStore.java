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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
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

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);

    private static String SEPARATOR = "__";

    private enum SearchMode {
        find,
        file //for developer mode


    }

    private static SearchMode SEARCH_MODE = SearchMode.file;
    static {
        String mode = OnapCommandConfig.getPropertyValue(OnapCommandConstants.OPEN_CLI_EXECUTION_SEARCH_MODE);
        if (mode.equalsIgnoreCase(SearchMode.find.name()))
            SEARCH_MODE = SearchMode.find;
    }

    public static class ExecutionStoreContext {
        private String requestId;
        private String executionId;
        private String profile;
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
        public String getRequestId() {
            return requestId;
        }
        public ExecutionStoreContext setRequestId(String requestId) {
            this.requestId = requestId;
             return this;
        }
        public String getProfile() {
            return profile;
        }
        public void setProfile(String profile) {
            this.profile = profile;
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
        this.dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
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

        ExecutionStoreContext context = new ExecutionStoreContext();
        context.setRequestId(requestId);

        String executionId = requestId + "-" + System.currentTimeMillis();
        context.setExecutionId(executionId);

        String storePath = getBasePath() + File.separator + executionId + SEPARATOR + product +
                SEPARATOR + service +
                SEPARATOR + cmd +
                SEPARATOR + (profile != null ? profile : "" );

        try {
            File dir = new File(storePath);
            FileUtils.forceMkdir(dir);
            context.setStorePath(dir.getAbsolutePath());

            if (product != null)
                FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "product"), product);
            if (service != null)
                FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "service"), service);
            if (cmd != null)
                FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "command"), cmd);

            FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "requestId"), requestId);

            FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "executionId"), executionId);

            if (input != null)
                FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "input"), input);
            if (profile != null) {
                context.setProfile(profile);
                FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "profile"), profile);
            }

            FileUtils.touch(new File(context.getStorePath() + File.separator + "stdout"));
            FileUtils.touch(new File(context.getStorePath() + File.separator + "stderr"));
            FileUtils.touch(new File(context.getStorePath() + File.separator + "debug"));

            FileUtils.touch(new File(context.getStorePath() + File.separator + "in-progress"));
        } catch (IOException e) {
            log.error("Failed to store the execution start details " + storePath);
        }

        return context;
    }

    public void storeExectutionEnd(
            ExecutionStoreContext context,
            String output, String error, String debug, boolean passed) {

        try {
            if (output != null)
                FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "output"), output);
            if (error != null)
                FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "error"), error);
            if (debug != null)
                FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "debug"), debug);
            if (passed)
                FileUtils.touch(new File(context.getStorePath() + File.separator + "completed"));
            else
                FileUtils.touch(new File(context.getStorePath() + File.separator + "failed"));

            new File(context.getStorePath() + File.separator + "in-progress").delete();
        } catch (IOException e) {
            log.error("Failed to store the execution end details " + context.storePath);
        }
    }

    public void storeExectutionProgress(
            ExecutionStoreContext context,
            String output, String error, String debug) {

        try {
            if (output != null)
                FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "output"), output);
            if (error != null)
                FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "error"), error);
            if (debug != null)
                FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "debug"), debug);
        } catch (IOException e) {
            log.error("Failed to store the execution end details " + context.storePath);
        }
    }

    public void storeExectutionDebug(
            ExecutionStoreContext context,
            String debug) {

        try {
            if (debug != null) {
                FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "debug"), debug);
            }
        } catch (IOException e) {
            log.error("Failed to store the execution debug details " + context.storePath);
        }
    }

    public void storeExectutionOutput(
            ExecutionStoreContext context,
            String output) {

        try {
            if (output != null) {
                FileUtils.writeStringToFile(new File(context.getStorePath() + File.separator + "output"), output);
            }
        } catch (IOException e) {
            log.error("Failed to store the execution output details " + context.storePath);
        }
    }
    public List<OnapCommandExecutionStore.Execution> listExecutions(Map<String, String> search) throws OnapCommandExecutionFailed {
        List <OnapCommandExecutionStore.Execution> list = new ArrayList<>();

        try {
            List <String> dirs = new ArrayList<>();
            if (System.getProperty("os.name").toLowerCase().startsWith("windows") || SEARCH_MODE.equals(SearchMode.file)) {
                for (File f: new File(getBasePath()).listFiles()) {
                    if(search.containsKey("execution-id")) {
                        if (f.getName().startsWith(search.get("execution-id")))
                                dirs.add(f.getAbsolutePath());

                        continue;
                    }

                    if(search.containsKey("request-id")) {
                        if (f.getName().startsWith(search.get("request-id")))
                                dirs.add(f.getAbsolutePath());

                        continue;
                    }

                    else
                        dirs.add(f.getAbsolutePath());
                }
            } else {
                //find results -type d -newermt '2019-02-11 10:00:00' ! -newermt '2019-02-11 15:10:00' -name "*__*__profile-list*"
                //find 'results' -type d -newermt '2019-02-11T10:00:00.000' ! -newermt '2019-02-11T15:10:00.000' -name "*__*__profile*"

                String searchString = "find " + new File(getBasePath()).getAbsolutePath() + " -type d ";

                String startTime = search.get("startTime");
                if (startTime != null) {
                    searchString += " -newermt " + startTime ;
                }

                String endTime = search.get("endTime");
                if (endTime != null) {
                    searchString += " ! -newermt " + endTime ;
                }

                searchString += " -name \"";

                if(search.containsKey("execution-id")) {
                    searchString += search.get("execution-id");
                } else if(search.containsKey("request-id")) {
                    searchString += search.get("request-id") + "*";
                } else {
                    searchString += "*";
                }

                for (String term: Arrays.asList(new String []{"product", "service", "command", "profile"})) {
                    searchString += "__";
                    if (search.get(term) != null && !search.get(term).isEmpty()) {
                        searchString += search.get(term);
                    } else {
                        searchString += "*";
                    }
                }
                if (!searchString.endsWith("*"))
                    searchString += "*";

                searchString += "\"";

                ProcessRunner pr = new ProcessRunner(new String [] {searchString}, null, ".");
                pr.setTimeout(10000);
                pr.overrideToUnix();
                pr.run();
                if (pr.getExitCode() != 0) {
                    throw new OnapCommandExecutionFailed("System failed to search the executions with error " + pr.getError());
                }

                if (!pr.getOutput().trim().isEmpty())
                    dirs = Arrays.asList(pr.getOutput().split("\\r?\\n"));
            }

            for (String dir: dirs) {
                list.add(this.makeExecution(dir));
            }
        } catch (Exception e) {
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

    private File getExecutionDir(String executionId) throws OnapCommandExecutionNotFound {
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

        return f[0];
    }

    public String showExecutionOut(String executionId) throws OnapCommandExecutionNotFound {
        try {
            return FileUtils.readFileToString(new File (this.getExecutionDir(executionId).getAbsolutePath() + File.separator + "stdout"));
        } catch (IOException e) {
            return "";
        }
    }

    public String showExecutionErr(String executionId) throws OnapCommandExecutionNotFound {
        try {
            return FileUtils.readFileToString(new File (this.getExecutionDir(executionId).getAbsolutePath() + File.separator + "stderr"));
        } catch (IOException e) {
            return "";
        }
    }

    public String showExecutionDebug(String executionId) throws OnapCommandExecutionNotFound {
        try {
            return FileUtils.readFileToString(new File (this.getExecutionDir(executionId).getAbsolutePath() + File.separator + "debug"));
        } catch (IOException e) {
            return "";
        }
    }
    public Execution getExecution(String executionId) throws OnapCommandExecutionNotFound, OnapCommandExecutionFailed {
        try {
            return this.makeExecution(this.getExecutionDir(executionId).getAbsolutePath());
        } catch (IOException e) {
            throw new OnapCommandExecutionFailed(e, "Failed to retrieve the execution");
        }
    }
}