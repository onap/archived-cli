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

import com.jayway.jsonpath.JsonPath;
import org.apache.http.HttpStatus;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.conf.OnapCommandConfg;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandExecutionFailed;
import org.onap.cli.fw.error.OnapCommandHttpFailure;
import org.onap.cli.fw.error.OnapCommandLoginFailed;
import org.onap.cli.fw.error.OnapCommandLogoutFailed;
import org.onap.cli.fw.error.OnapCommandServiceNotFound;
import org.onap.cli.fw.http.HttpInput;
import org.onap.cli.fw.http.HttpResult;
import org.onap.cli.fw.http.OnapHttpConnection;

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

    public OnapAuthClient(OnapCredentials creds, boolean debug) throws OnapCommandHttpFailure {
        this.creds = creds;
        this.http = new OnapHttpConnection(creds.getMsbUrl().startsWith("https"), debug);
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

        HttpInput input = new HttpInput().setUri(this.getAuthUrl() + "/tokens")
                .setBody(String.format(Constants.TOKEN, creds.getUsername(), creds.getPassword()))
                .setMethod("post");

        HttpResult result;
        try {
            result = this.run(input);
        } catch (OnapCommandHttpFailure e) {
            throw new OnapCommandLoginFailed(e);
        }
        if (result.getStatus() != HttpStatus.SC_OK && result.getStatus() != HttpStatus.SC_CREATED) {
            throw new OnapCommandLoginFailed(result.getBody(), result.getStatus());
        }

        if (OnapCommandConfg.isCookiesBasedAuth()) {
            this.http.setAuthToken(result.getRespCookies().get(Constants.X_AUTH_TOKEN));
        } else {
            this.http.setAuthToken(result.getRespHeaders().get(Constants.X_AUTH_TOKEN));
        }
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

        HttpInput input = new HttpInput().setUri(this.getAuthUrl() + "/tokens").setMethod("delete");

        HttpResult result;
        try {
            result = this.run(input);
        } catch (OnapCommandHttpFailure e) {
            throw new OnapCommandLogoutFailed(e);
        }
        if (result.getStatus() != HttpStatus.SC_NO_CONTENT) {
            throw new OnapCommandLogoutFailed(result.getStatus());
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
            return this.getMsbUrl();
        } else if (srv.isModeDirect()){
            return this.creds.getMsbUrl();
        }


        HttpInput input = new HttpInput().setUri(this.creds.getMsbUrl()
                + String.format(Constants.MSB_SERVICE_URI, srv.getName(), srv.getVersion()));
        HttpResult result = this.http.get(input);

        if (result.getStatus() == HttpStatus.SC_NOT_FOUND) {
            throw new OnapCommandServiceNotFound(srv.toString());
        }
        if (!result.isSuccess()) {
            throw new OnapCommandExecutionFailed("Failed to retrive service " + srv.toString());
        }

        try {
            return this.creds.getMsbUrl() + JsonPath.read(result.getBody(), "url");
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

    private String getMsbUrl() {
        return this.creds.getMsbUrl() + Constants.MSB_URI;
    }

    public String getAuthToken() {
        return this.http.getAuthToken();
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
        if (OnapCommandConfg.isCookiesBasedAuth()) {
            input.getReqCookies().put(Constants.X_AUTH_TOKEN, http.getAuthToken());
        }
        return this.http.request(input);
    }
}
