/*
 * Copyright 2018 Huawei Technologies Co., Ltd.
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

package org.onap.cli.fw.cmd.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.cmd.conf.OnapCommandCmdConstants;
import org.onap.cli.fw.cmd.error.OnapCommandCmdFailure;
import org.onap.cli.fw.cmd.schema.OnapCommandSchemaCmdLoader;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandExecutionFailed;
import org.onap.cli.fw.error.OnapCommandResultEmpty;
import org.onap.cli.fw.error.OnapCommandResultMapProcessingFailed;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.utils.OnapCommandUtils;
import org.onap.cli.fw.utils.ProcessRunner;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import net.minidev.json.JSONArray;

/**
 * Hello world.
 */
@OnapCommandSchema(type = "cmd")
public class OpenCommandShellCmd extends OnapCommand {

    public OpenCommandShellCmd() {
        super.addDefaultSchemas(OnapCommandCmdConstants.DEFAULT_PARAMETER_CMD_FILE_NAME);
    }

    private Map<String, String> resultMap = new HashMap<>();

    private List<String> command;

    private Map<String, String> envs = new HashMap<>();

    private String wd = null;

    private List<Integer> successStatusCodes = new ArrayList<>();

    private List<Integer> passCodes = new ArrayList<>();

    private String output = "$stdout";

    private String error = "$stderr";

    public List<Integer> getSuccessStatusCodes() {
        return successStatusCodes;
    }

    public void setSuccessStatusCodes(ArrayList<Integer> successStatusCodes) {
        this.successStatusCodes = successStatusCodes;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public Map<String, String> getEnvs() {
        return envs;
    }

    public void setEnvs(Map<String, String> envs) {
        this.envs = envs;
    }


    public List<String> getCommand() {
        return command;
    }

    public void setCommand(List<String> command) {
        this.command = command;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    protected List<String> initializeProfileSchema(Map<String, ?> schemaMap, boolean validate) throws OnapCommandException {
        return OnapCommandSchemaCmdLoader.parseCmdSchema(this, schemaMap, validate);
    }

    @Override
    protected void run() throws OnapCommandException {
        //Read the input arguments
        Map<String, OnapCommandParameter> paramMap = this.getParametersMap();

        List<String> commandLine = new ArrayList<>();
        for (String cmdTkn: this.getCommand()) {
            commandLine.add(OnapCommandUtils.replaceLineFromInputParameters(cmdTkn, paramMap));
        }

        //Process command
        String []cmd = commandLine.toArray(new String []{});
        String cwd = this.getWd();
        List <String> envs = new ArrayList<>();

        for (String env: this.getEnvs().keySet()) {
            envs.add(env + "=" + this.getEnvs().get(env));
        }

        ProcessRunner pr = new ProcessRunner(
                cmd,
                (envs.size() > 0) ? envs.toArray(new String []{}) : null,
                cwd);
        try {
            pr.run();
        } catch (Exception e) {
            throw new OnapCommandExecutionFailed(this.getName(), e);
        }

        if (!this.successStatusCodes.contains(pr.getExitCode())) {
            throw new OnapCommandExecutionFailed(this.getName(), pr.getError(), pr.getExitCode());
        }

        String outputValue = "";

        if (this.output.equals("$stdout")) {
            outputValue = pr.getOutput();
        } else {
            outputValue = OnapCommandUtils.replaceLineFromInputParameters(this.output, paramMap);
            outputValue = OnapCommandUtils.replaceLineForSpecialValues(outputValue);
        }

        this.getResult().setOutput(outputValue);

        //populate results
        for (Entry<String, String> resultMapEntry : this.getResultMap().entrySet()) {
            String value = OnapCommandUtils.replaceLineFromInputParameters(resultMapEntry.getValue(), paramMap);
            value = OnapCommandUtils.replaceLineForSpecialValues(value);
            this.getResult().getRecordsMap().get(resultMapEntry.getKey()).setValues(
                    this.replaceLineFromOutputResults(value, outputValue));
        }

        //check for pass/failure
        if (!this.passCodes.contains(pr.getExitCode())) {
            this.getResult().setPassed(false);
        }
   }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

   private ArrayList<String> replaceLineFromOutputResults(String line, String output)
            throws OnapCommandException {


        ArrayList<String> result = new ArrayList<>();
        if (!line.contains("$o{")) {
            result.add(line);
            return result;
        }

        /**
         * In case of empty output [] or {}
         **/
        if (output.length() <= 2) {
            return result;
        }

        int currentIdx = 0;

        // Process  jsonpath macros
        List<Object> values = new ArrayList<>();
        String processedPattern = "";
        currentIdx = 0;
        int maxRows = 1; // in normal case, only one row will be there
        while (currentIdx < line.length()) {
            int idxS = line.indexOf("$o{", currentIdx); //check for output stream
            if (idxS == -1) {
                idxS = line.indexOf("$e{", currentIdx); //check for error stream
                if (idxS == -1) {
                    processedPattern += line.substring(currentIdx);
                    break;
                }
            }
            int idxE = line.indexOf("}", idxS);
            String jsonPath = line.substring(idxS + 3, idxE);
            jsonPath = jsonPath.trim();
            Object value = new Object();
            try {
                // JSONArray or String
                value = JsonPath.read(output, jsonPath);
            } catch (PathNotFoundException e1) { // NOSONAR
                //set to blank for those entries which are missing from the output json
                value = "";
            } catch (Exception e) {
                throw new OnapCommandCmdFailure("Invalid json format in command output");
            }

            if (value instanceof JSONArray) {
                JSONArray arr = (JSONArray) value;
                if (arr.size() > maxRows) {
                    maxRows = arr.size();
                }
            }
            processedPattern += line.substring(currentIdx, idxS) + "%s";
            values.add(value);
            currentIdx = idxE + 1;
        }

        if (processedPattern.isEmpty()) {
            result.add(line);
            return result;
        } else {
            for (int i = 0; i < maxRows; i++) {
                currentIdx = 0;
                String bodyProcessedLine = "";
                int positionalIdx = 0; // %s positional idx
                while (currentIdx < processedPattern.length()) {
                    int idxS = processedPattern.indexOf("%s", currentIdx);
                    if (idxS == -1) {
                        bodyProcessedLine += processedPattern.substring(currentIdx);
                        break;
                    }

                    int idxEnd = idxS + 2; // %s

                    try {
                        Object val = values.get(positionalIdx);
                        String valStr = String.valueOf(val);

                        if (val instanceof JSONArray) {
                            JSONArray aJson = (JSONArray) val;

                            if (!aJson.isEmpty()) {
                                valStr = aJson.get(i).toString();
                            } else {
                                throw new OnapCommandResultEmpty();
                            }
                        }

                        bodyProcessedLine += processedPattern.substring(currentIdx, idxS) + valStr;
                        currentIdx = idxEnd;
                        positionalIdx++;
                    } catch (OnapCommandResultEmpty e) {
                        throw e;
                    } catch (Exception e) {
                        throw new OnapCommandResultMapProcessingFailed(line, e);
                    }
                }
                result.add(bodyProcessedLine);
            }

            return result;
        }
    }

public String getError() {
    return error;
}

public void setError(String error) {
    this.error = error;
}

public List<Integer> getPassCodes() {
    return passCodes;
}

public void setPassCodes(List<Integer> passCodes) {
    this.passCodes = passCodes;
}

}
