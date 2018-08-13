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
import org.onap.cli.fw.cmd.schema.OnapCommandSchemaCmdLoader;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandExecutionFailed;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.output.OnapCommandResultType;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.utils.OnapCommandUtils;

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
	
	private Map<String, String> envs;

	private String wd = null;
	
	private List<Integer> successStatusCodes = new ArrayList<>();
	
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
        
        //Process command
        String []cmd = this.getCommand().toArray(new String []{});
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
		} catch (InterruptedException | IOException e) {
			throw new OnapCommandExecutionFailed(this.getName(), e);
		}
        
        //Populate outputs
        this.getResult().getRecordsMap().get("output").getValues().add(pr.getOutput());
        this.getResult().getRecordsMap().get("error").getValues().add(pr.getError());
        this.getResult().getRecordsMap().get("exitCode").getValues().add("" + pr.getExitCode());
   }
}
