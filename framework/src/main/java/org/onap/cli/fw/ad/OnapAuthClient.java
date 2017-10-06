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
import java.util.Map;

import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.OnapCommandRegistrar;
import org.onap.cli.fw.cmd.OnapHttpCommand;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.conf.OnapCommandConfg;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHttpFailure;
import org.onap.cli.fw.error.OnapCommandInvalidParameterValue;
import org.onap.cli.fw.error.OnapCommandNotFound;
import org.onap.cli.fw.http.HttpInput;
import org.onap.cli.fw.http.HttpResult;
import org.onap.cli.fw.http.OnapHttpConnection;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.utils.OnapCommandUtils;

/**
 * Oclip Auth client helps to do login and logout.
 *
 */
public class OnapAuthClient {

    private OnapHttpCommand cmd = null;

    private OnapHttpConnection http = null;

    public OnapAuthClient(OnapHttpCommand cmd, boolean debug) throws OnapCommandHttpFailure, OnapCommandInvalidParameterValue {
        this.cmd = cmd;
        this.http = new OnapHttpConnection(debug);
    }

    /**
     * Login.
     *
     * @throws OnapCommandException
     *             exception
     */
    public void login() throws OnapCommandException {

        // For development purpose, its introduced and is not supported for production
        if (OnapCommandConfg.isAuthIgnored()) {
            return;
        }

        OnapCommand login = this.findAuthCommand("login");

        OnapCommandUtils.copyParamsFrom(this.cmd, login);
        login.execute();

        //It is safely assumed that all outputs are considered as common http headers.
        Map<String, String> headers = new HashMap<>();
        for (OnapCommandResultAttribute    attr: login.getResult().getRecords()) {
            String headerValue = attr.getValues().get(0);
            if (headerValue != null && !headerValue.isEmpty()) {
                headers.put(attr.getName(), attr.getValues().get(0));
            }
        }

        this.http.setCommonHeaders(headers);
    }

    /**
     * Logout.
     *
     * @throws OnapCommandException
     *             exception
     */
    public void logout() throws OnapCommandException {
        // For development purpose, its introduced and is not supported for production
        if (OnapCommandConfg.isAuthIgnored()) {
            return;
        }

        OnapCommand logout = this.findAuthCommand("logout");

        OnapCommandUtils.copyParamsFrom(this.cmd, logout);

        logout.execute();

        this.http.close();
    }

    /**
     * Find given service base path.
     *
     * @throws OnapCommandException
     *             exception
     */
    public String getServiceUrl() throws OnapCommandException {
        return this.getServiceUrl(this.cmd);
    }

    private String getServiceUrl(OnapHttpCommand cmd) throws OnapCommandException {
        if (cmd.getService().isModeDirect()){
            return cmd.getParametersMap().get(Constants.DEAFULT_PARAMETER_HOST_URL).getValue().toString();
        } else { //Catalog mode
            OnapCommand catalog = OnapCommandRegistrar.getRegistrar().get("catalog");

            OnapCommandUtils.copyParamsFrom(cmd, catalog);

            catalog.execute();

            String hostUrl = catalog.getResult().getRecordsMap().get(Constants.CATALOG_SERVICE_HOST_URL).getValues().get(0);
            hostUrl = hostUrl.trim();
            if (hostUrl.endsWith("/")) {
                hostUrl = hostUrl.substring(0, hostUrl.length()-1);
            }

            String basePath = catalog.getResult().getRecordsMap().get(Constants.CATALOG_SERVICE_BASE_PATH).getValues().get(0);
            basePath = basePath.trim();
            if (basePath.startsWith("/")) {
                basePath = basePath.substring(1);
            }

            return hostUrl + "/" + basePath;
        }
    }


    public String getDebugInfo() {
        return this.http.getDebugInfo();
    }

    /**
     * Http call to external service.
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

    /**
     *
     * @param authAction login/logout
     * @return
     * @throws OnapCommandException
     */
    private OnapCommand findAuthCommand(String authAction) throws OnapCommandException {
        OnapCommand auth = null;
        try {
            //Find the auth command for the given service and version under current enabled product
            auth = OnapCommandRegistrar.getRegistrar().get(
                    this.cmd.getInfo().getService() + "-" +
                    this.cmd.getService().getAuthType() + "-" + authAction);
        } catch (OnapCommandNotFound e) {
            //Find the auth command for current enabled product
            auth = OnapCommandRegistrar.getRegistrar().get(
                        this.cmd.getService().getAuthType() + "-" + authAction);
        }

        return auth;
    }
}
