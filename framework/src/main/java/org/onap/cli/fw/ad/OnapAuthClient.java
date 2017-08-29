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

package org.onap.cli.fw.ad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.auth.BasicScheme;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.conf.OnapCommandConfg;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandExecutionFailed;
import org.onap.cli.fw.error.OnapCommandHttpFailure;
import org.onap.cli.fw.error.OnapCommandInvalidParameterValue;
import org.onap.cli.fw.error.OnapCommandLoginFailed;
import org.onap.cli.fw.error.OnapCommandLogoutFailed;
import org.onap.cli.fw.error.OnapCommandServiceNotFound;
import org.onap.cli.fw.http.HttpInput;
import org.onap.cli.fw.http.HttpResult;
import org.onap.cli.fw.http.OnapHttpConnection;

import com.jayway.jsonpath.JsonPath;
import org.onap.cli.fw.input.OnapCommandParameter;

/**
 * Onap Auth client helps to do login and logout.
 *
 */
public class OnapAuthClient {

    /*
     * Onap credentials
     */
    private OnapHttpConnection http = null;

    private OnapCredentials creds = null;

    private OnapService service = new OnapService();

    private Map<String, String> paramMap = new HashMap<>();

    public OnapAuthClient(OnapCredentials creds, boolean debug, OnapService service, List<OnapCommandParameter> params) throws OnapCommandHttpFailure, OnapCommandInvalidParameterValue {
        this.creds = creds;
        this.service = service;
        for (OnapCommandParameter param : params) {
            paramMap.put(param.getName(), param.getValue().toString());
        }

        this.http = new OnapHttpConnection(creds.getHostUrl().startsWith("https"), debug);
    }

    /**
     * Login.
     *
     * @throws OnapCommandLoginFailed
     *             LoginFailed Exception
     * @throws OnapCommandHttpFailure
     *             Http request failed
     * @throws OnapCommandExecutionFailed
     *             cmd exec failed
     * @throws OnapCommandServiceNotFound
     *             service not found
     */
    public void login() throws OnapCommandException {

        // For development purpose, its introduced and is not supported for production
        if (OnapCommandConfg.isAuthIgnored()) {
            return;
        }

        if (this.service.getAuthType().equalsIgnoreCase(Constants.AUTH_BASIC)) {
            String authToken = BasicScheme.authenticate(new UsernamePasswordCredentials(
                    creds.getUsername(), creds.getPassword()), "UTF-8", false).getValue();

            Map<String, String> mapHeaders = OnapCommandConfg.getBasicCommonHeaders(this.paramMap);
            mapHeaders.putAll(OnapCommandConfg.getServiceHeaders(this.service.getName(), this.paramMap));
            mapHeaders.put(OnapCommandConfg.getXAuthTokenName(), authToken);
            
            this.http.setCommonHeaders(mapHeaders);
            return;
        }

        //TODO mrkanag add support for aaf here
    }

    /**
     * Logout.
     *
     * @throws OnapCommandExecutionFailed
     *             cmd exec failed
     * @throws OnapCommandServiceNotFound
     *             service not found
     * @throws OnapCommandLogoutFailed
     *             logout failed
     * @throws OnapCommandHttpFailure
     *             exception
     */
    public void logout() throws OnapCommandException {
        // For development purpose, its introduced and is not supported for production
        if (OnapCommandConfg.isAuthIgnored()) {
            return;
        }

        this.http.close();
    }

    /**
     * Find given service base path.
     *
     * @param srv
     *            onap service
     * @return string
     * @throws OnapCommandExecutionFailed
     *             Cmd execution failed exception
     * @throws OnapCommandServiceNotFound
     *             Service not found
     * @throws OnapCommandHttpFailure
     *             http request failed
     */
    public String getServiceBasePath(OnapService srv) throws OnapCommandException {
        if (srv.getName().equals(OnapCommandConfg.getApiGateway())) {
            return this.getApiGatewayUrl();
        } else if (srv.isModeDirect()){
            return this.creds.getHostUrl();
        }


        HttpInput input = new HttpInput().setUri(this.creds.getHostUrl()
                + String.format(Constants.MSB_SERVICE_URI, srv.getName(), srv.getVersion()));
        HttpResult result = this.http.get(input);

        if (result.getStatus() == HttpStatus.SC_NOT_FOUND) {
            throw new OnapCommandServiceNotFound(srv.toString());
        }
        if (!result.isSuccess()) {
            throw new OnapCommandExecutionFailed("Failed to retrive service " + srv.toString());
        }

        try {
            return this.creds.getHostUrl() + JsonPath.read(result.getBody(), "url");
        } catch (Exception e) {
            throw new OnapCommandExecutionFailed(e, srv.toString());
        }
    }

    private String getAuthUrl() throws OnapCommandException {
        OnapService srv = new OnapService();
        srv.setName(Constants.AUTH_SERVICE);
        srv.setVersion(Constants.AUTH_SERVICE_VERSION);
        return this.getServiceBasePath(srv);
    }

    private String getApiGatewayUrl() {
        return this.creds.getHostUrl() + Constants.MSB_URI;
    }

    public String getDebugInfo() {
        return this.http.getDebugInfo();
    }

    /**
     * Http call to auth service.
     *
     * @param input
     *            http input
     * @return http result
     * @throws OnapCommandHttpFailure
     *             exception
     */
    public HttpResult run(HttpInput input) throws OnapCommandHttpFailure {
        return this.http.request(input);
    }
}
