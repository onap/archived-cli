/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
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

package org.onap.cli.fw.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.OnapCommandSchema;
import org.onap.cli.fw.ad.OnapAuthClient;
import org.onap.cli.fw.ad.OnapService;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.conf.OnapCommandConfg;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandExecutionFailed;
import org.onap.cli.fw.error.OnapCommandFailedMocoGenerate;
import org.onap.cli.fw.http.HttpInput;
import org.onap.cli.fw.http.HttpResult;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.utils.OnapCommandSchemaLoaderUtils;
import org.onap.cli.fw.utils.OnapCommandUtils;
import org.onap.cli.http.mock.MockJsonGenerator;
import org.onap.cli.http.mock.MockRequest;
import org.onap.cli.http.mock.MockResponse;

/**
 * Oclip http Command.
 *
 */
@OnapCommandSchema(type = Constants.HTTP_SCHEMA_PROFILE)
public class OnapHttpCommand extends OnapCommand {

    private HttpInput input = new HttpInput();

    private List<Integer> successStatusCodes = new ArrayList<>();

    private Map<String, String> resultMap = new HashMap<>();

    protected OnapAuthClient authClient;

    private OnapService oclipService = new OnapService();

    public void setInput(HttpInput input) {
        this.input = input;
    }

    @Override
    public String getSchemaVersion() {
        return Constants.OPEN_CLI_SCHEMA_VERSION_VALUE_1_0;
    }

    public void setSuccessStatusCodes(List<Integer> successStatusCodes) {
        this.successStatusCodes = successStatusCodes;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public HttpInput getInput() {
        return input;
    }

    public List<Integer> getSuccessStatusCodes() {
        return successStatusCodes;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    /*
     * Oclip service, this command uses to execute it.
     */
    public OnapService getService() {
        return this.oclipService;
    }

    public void setService(OnapService service) {
        this.oclipService = service;
    }

    @Override
    protected List<String> initializeProfileSchema() throws OnapCommandException {
        return OnapCommandSchemaLoaderUtils.loadHttpSchema(this, this.getSchemaName(), true, false);
    }

    @Override
    protected void validate() throws OnapCommandException {
        if (! this.isAuthRequired()) {
            if (this.getParametersMap().containsKey(Constants.DEAFULT_PARAMETER_USERNAME)) {
                this.getParametersMap().get(Constants.DEAFULT_PARAMETER_USERNAME).setOptional(true);
            }
            if (this.getParametersMap().containsKey(Constants.DEAFULT_PARAMETER_PASSWORD)) {
                this.getParametersMap().get(Constants.DEAFULT_PARAMETER_PASSWORD).setOptional(true);
            }
        }

        super.validate();
    }

    private boolean isAuthRequired() {
        return !this.getService().isNoAuth()
                && "false".equals(this.getParametersMap().get(Constants.DEFAULT_PARAMETER_NO_AUTH).getValue())
                && this.getInfo().getCommandType().equals(CommandType.CMD);
    }

    @Override
    protected void run() throws OnapCommandException {
        try {
            // For auth/catalog type commands, login and logout logic is not required
            boolean isAuthRequired = this.isAuthRequired();

            this.authClient = new OnapAuthClient(
                    this,
                    this.getResult().isDebug());

            if (isAuthRequired) {
                this.authClient.login();
            }

            this.processRequest();

            if (isAuthRequired) {
                this.authClient.logout();
            }

            if (this.getResult().isDebug() && authClient != null) {
                this.getResult().setDebugInfo(this.authClient.getDebugInfo());
            }
        } catch (OnapCommandException e) {
            if (this.getResult().isDebug() && authClient != null) {
                this.getResult().setDebugInfo(this.authClient.getDebugInfo());
            }
            throw e;
        }
    }

    protected void processRequest() throws OnapCommandException {

        HttpInput httpInput = OnapCommandUtils.populateParameters(this.getParametersMap(), this.getInput());
        httpInput.setUri(this.authClient.getServiceUrl() + httpInput.getUri());

        HttpResult output = this.authClient.run(httpInput);

        this.getResult().setOutput(output);
        if (!this.getSuccessStatusCodes().contains(output.getStatus())) {
            throw new OnapCommandExecutionFailed(this.getName(), output.getBody(), output.getStatus());
        }

        Map<String, ArrayList<String>> results = OnapCommandUtils.populateOutputs(this.getResultMap(), output);
        results = OnapCommandUtils.populateOutputsFromInputParameters(results, this.getParametersMap());

        for (OnapCommandResultAttribute attr : this.getResult().getRecords()) {
            attr.setValues(results.get(attr.getName()));
        }
        generateJsonMock(httpInput, output, this.getSchemaName());
    }

    private void generateJsonMock(HttpInput httpInput, HttpResult httpResult, String schemaName)
            throws OnapCommandFailedMocoGenerate {

        if (OnapCommandConfg.isSampleGenerateEnabled()) {
            try {
                MockRequest mockRequest = new MockRequest();
                mockRequest.setMethod(httpInput.getMethod());
                mockRequest.setUri(httpInput.getUri());
                mockRequest.setHeaders(httpInput.getReqHeaders());
                mockRequest.setJson(httpInput.getBody());

                MockResponse mockResponse = new MockResponse();
                mockResponse.setStatus(httpResult.getStatus());
                mockResponse.setJson(httpResult.getBody());

                MockJsonGenerator.generateMocking(mockRequest, mockResponse, OnapCommandConfg.getSampleGenerateTargetFolder()
                        + "/" + schemaName.replace(".yaml", "") + "-moco.json");
            } catch (IOException error) {
                throw new OnapCommandFailedMocoGenerate(schemaName, error);
            }
        }
    }
}
