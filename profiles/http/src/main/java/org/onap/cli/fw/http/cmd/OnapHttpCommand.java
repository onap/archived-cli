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

package org.onap.cli.fw.http.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.cmd.OnapCommandType;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandExecutionFailed;
import org.onap.cli.fw.http.auth.OnapCommandHttpAuthClient;
import org.onap.cli.fw.http.auth.OnapCommandHttpService;
import org.onap.cli.fw.http.conf.OnapCommandHttpConstants;
import org.onap.cli.fw.http.connect.HttpInput;
import org.onap.cli.fw.http.connect.HttpResult;
import org.onap.cli.fw.http.error.OnapCommandFailedMocoGenerate;
import org.onap.cli.fw.http.mock.MocoServer;
import org.onap.cli.fw.http.schema.OnapCommandSchemaHttpLoader;
import org.onap.cli.fw.http.utils.OnapCommandHttpUtils;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.store.OnapCommandExecutionStore;
import org.onap.cli.fw.utils.OnapCommandUtils;
import org.onap.cli.http.mock.MockJsonGenerator;
import org.onap.cli.http.mock.MockRequest;
import org.onap.cli.http.mock.MockResponse;

/**
 * Oclip http Command.
 *
 */
@OnapCommandSchema(type = OnapCommandHttpConstants.HTTP_SCHEMA_PROFILE)
public class OnapHttpCommand extends OnapCommand {

    private HttpInput input = new HttpInput();

    private HttpResult output = new HttpResult();

    private List<Integer> successStatusCodes = new ArrayList<>();

    private Map<String, String> resultMap = new HashMap<>();

    protected OnapCommandHttpAuthClient authClient;

    private OnapCommandHttpService oclipService = new OnapCommandHttpService();

    private MocoServer mocoServer = null;

    boolean shouldVerify = false;

    boolean mockingEnabled;

    public OnapHttpCommand() {
        super.addDefaultSchemas(OnapCommandHttpConstants.DEFAULT_PARAMETER_HTTP_FILE_NAME);
    }

    public void setInput(HttpInput input) {
        this.input = input;
    }

    @Override
    public String getSchemaVersion() {
        return OnapCommandConstants.OPEN_CLI_SCHEMA_VERSION_VALUE_1_0;
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
    public OnapCommandHttpService getService() {
        return this.oclipService;
    }

    public void setService(OnapCommandHttpService service) {
        this.oclipService = service;
    }

    @Override
    protected List<String> initializeProfileSchema(Map<String, ?> schemaMap, boolean validate) throws OnapCommandException {
        return OnapCommandSchemaHttpLoader.parseHttpSchema(this, schemaMap, validate);
    }

    @Override
    protected void validate() throws OnapCommandException {
        if (! this.isAuthRequired()) {
            if (this.getParametersMap().containsKey(OnapCommandHttpConstants.DEAFULT_PARAMETER_USERNAME)) {
                this.getParametersMap().get(OnapCommandHttpConstants.DEAFULT_PARAMETER_USERNAME).setOptional(true);
            }
            if (this.getParametersMap().containsKey(OnapCommandHttpConstants.DEAFULT_PARAMETER_PASSWORD)) {
                this.getParametersMap().get(OnapCommandHttpConstants.DEAFULT_PARAMETER_PASSWORD).setOptional(true);
            }
        }

        super.validate();
    }

    private boolean isAuthRequired() {
        return !this.getService().isNoAuth()
                && !(Boolean) (this.getParametersMap().get(OnapCommandHttpConstants.DEFAULT_PARAMETER_NO_AUTH).getValue())
                && (this.getInfo().getCommandType().equals(OnapCommandType.CMD) ||
                        this.getInfo().getCommandType().equals(OnapCommandType.CATALOG));
    }

    private Optional<OnapCommandParameter> findParameterByName(String parameterName) {
        return this.getParameters().stream()
                .filter(e -> e.getName().equals(parameterName))
                .findFirst();
    }

    @Override
    protected void preRun() throws OnapCommandException {
        Optional<OnapCommandParameter> verifyOpt = this.getParameters().stream()
                .filter(e -> "verify".equals(e.getName()))
                .findFirst();
        if(verifyOpt.isPresent()) {
            shouldVerify = (boolean) verifyOpt.get().getValue();
        }

        if (shouldVerify) {
            Optional<OnapCommandParameter> hostUrlParamOpt = findParameterByName(OnapCommandHttpConstants.VERIFY_HOST_PARAMETER_OPT);
            Optional<OnapCommandParameter> noAuthParamOpt = findParameterByName(OnapCommandHttpConstants.VERIFY_NO_AUTH_PARAMETER_OPT);

            if (hostUrlParamOpt.isPresent()) {
                OnapCommandParameter onapCommandParameter = hostUrlParamOpt.get();
                onapCommandParameter.setValue(
                        OnapCommandConfig.getPropertyValue(OnapCommandHttpConstants.VERIFY_MOCO_HOST)
                                + ":" + OnapCommandConfig.getPropertyValue(OnapCommandHttpConstants.VERIFY_MOCO_PORT));
            }

            if (noAuthParamOpt.isPresent()) {
                OnapCommandParameter onapCommandParameter = noAuthParamOpt.get();
                onapCommandParameter.setValue(true);
            }


            Optional<OnapCommandParameter> contextOpt = this.getParameters().stream()
                    .filter(e -> e.getName().equals(OnapCommandConstants.VERIFY_CONTEXT_PARAM))
                    .findFirst();

            if (contextOpt.isPresent()) {
                OnapCommandParameter context = contextOpt.get();
                Map<String, String> map = (Map<String, String>) context.getValue();

                mockingEnabled =  map.containsKey(OnapCommandHttpConstants.VERIFY_DISABLE_MOCKING)
                        && "true".equals(map.get(OnapCommandHttpConstants.VERIFY_DISABLE_MOCKING)) ? false : true;

                if (mockingEnabled) {
                    String mockedFile = ((Map<String, String>) context.getValue()).get(OnapCommandConstants.VERIFY_MOCO);
                    mocoServer = new MocoServer(mockedFile);
                    mocoServer.start();
                }
            }
        }
    }

    @Override
    protected void postRun() throws OnapCommandException {
        if (mocoServer != null) {
            mocoServer.stop();
        }
    }

    @Override
    protected void run() throws OnapCommandException {
        this.authClient = new OnapCommandHttpAuthClient(this);

        try {
            // For auth/catalog type commands, login and logout logic is not required
            boolean isAuthRequired = this.isAuthRequired();
            if (isAuthRequired) {
                this.authClient.login();
            }

            this.processRequest();

            if (isAuthRequired) {
                this.authClient.logout();
            }
        } catch (OnapCommandException e) {
            throw e;
        } finally {
            this.getResult().setDebugInfo(this.input.toString() + "\n" + this.output.toString());
        }
    }

    protected void processRequest() throws OnapCommandException {

        HttpInput httpInput = OnapCommandHttpUtils.populateParameters(this.getParametersMap(), this.getInput());
        if (!httpInput.getUri().startsWith("http")) {
            httpInput.setUri(this.authClient.getServiceUrl() + httpInput.getUri());
        }

        this.setInput(httpInput);

        if (this.getExecutionContext() != null) {
            OnapCommandExecutionStore.getStore().storeExectutionDebug(this.getExecutionContext(), this.getInput().toString());
        } else {
            this.getResult().setDebugInfo(this.getInput().toString());
        }
        this.output = this.authClient.run(this.getInput());

        this.getResult().setOutput(output);
        if (!this.getSuccessStatusCodes().contains(output.getStatus())) {
            throw new OnapCommandExecutionFailed(this.getName(), output.getBody(), output.getStatus());
        }

        //pre-process result map for spl entries and input parameters
        for (Entry<String, String> resultMapEntry : this.getResultMap().entrySet()) {
            String value = OnapCommandUtils.replaceLineForSpecialValues(resultMapEntry.getValue());
            value = OnapCommandUtils.replaceLineFromInputParameters(value, this.getParametersMap());
            this.resultMap.put(resultMapEntry.getKey(), value);
        }

        Map<String, List<String>> results = OnapCommandHttpUtils.populateOutputs(this.getResultMap(), output);
        //results = OnapCommandUtils.populateOutputsFromInputParameters(results, this.getParametersMap());

        for (OnapCommandResultAttribute attr : this.getResult().getRecords()) {
            attr.setValues(results.get(attr.getName()));
        }

        try{
            generateJsonMock(this.getInput(), output, this.getSchemaName());
        } catch (OnapCommandFailedMocoGenerate e) {
            //NO SONAR ignore it
        }
    }

    private void generateJsonMock(HttpInput httpInput, HttpResult httpResult, String schemaName)
            throws OnapCommandFailedMocoGenerate {

        if (Boolean.parseBoolean(OnapCommandConfig.getPropertyValue(OnapCommandConstants.SAMPLE_GEN_ENABLED))) {
            try {
                MockRequest mockRequest = new MockRequest();
                mockRequest.setMethod(httpInput.getMethod());
                mockRequest.setUri(httpInput.getUri());
                mockRequest.setHeaders(httpInput.getReqHeaders());
                mockRequest.setJson(httpInput.getBody());

                MockResponse mockResponse = new MockResponse();
                mockResponse.setStatus(httpResult.getStatus());
                mockResponse.setJson(httpResult.getBody());

                MockJsonGenerator.generateMocking(mockRequest, mockResponse,
                        OnapCommandConfig.getPropertyValue(OnapCommandConstants.SAMPLE_GEN_TARGET_FOLDER)
                        + "/" + schemaName.replace(".yaml", "") + "-moco.json");
            } catch (IOException error) {
                throw new OnapCommandFailedMocoGenerate(schemaName, error);
            }
        }
    }
}
