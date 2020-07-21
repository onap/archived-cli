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

package org.onap.cli.fw.http.auth;

import java.util.HashMap;
import java.util.Map;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.http.cmd.OnapHttpCommand;
import org.onap.cli.fw.http.conf.OnapCommandHttpConstants;
import org.onap.cli.fw.http.connect.HttpInput;
import org.onap.cli.fw.http.connect.HttpResult;
import org.onap.cli.fw.http.connect.OnapHttpConnection;
import org.onap.cli.fw.http.error.OnapCommandHttpFailure;
import org.onap.cli.fw.http.schema.OnapCommandSchemaHttpLoader;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;
import org.onap.cli.fw.utils.OnapCommandUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

/**
 * Oclip Auth client helps to do login and logout.
 *
 */
public class OnapCommandHttpAuthClient {

    private static Logger logger = LoggerFactory.getLogger(OnapCommandHttpAuthClient.class);
    private OnapHttpCommand cmd = null;

    private OnapHttpConnection http = null;

    private Map<String, String> loginCache = new HashMap<>();

    public OnapCommandHttpAuthClient(OnapHttpCommand cmd) {
        this.cmd = cmd;
        this.http = new OnapHttpConnection();
    }

    /**
     * Login.
     *
     * @throws OnapCommandException
     *             exception
     */
    public void login() throws OnapCommandException {

        // For development purpose, its introduced and is not supported for production
        if (Boolean.parseBoolean(OnapCommandConfig.getPropertyValue(OnapCommandHttpConstants.OPEN_IGNORE_AUTH))) {
            return;
        }

        OnapCommand login = OnapCommandSchemaHttpLoader.findAuthCommand(this.cmd, "login");

        OnapCommandUtils.copyParamsFrom(this.cmd, login);
        login.execute();

        //It is safely assumed that all outputs are considered as common http headers.
        for (OnapCommandResultAttribute    attr: login.getResult().getRecords()) {
            String headerValue = attr.getValues().get(0);
            if (headerValue != null && !headerValue.isEmpty()) {
                this.loginCache.put(attr.getName(), attr.getValues().get(0));
            }
        }

        this.http.setCommonHeaders(this.loginCache);
    }

    /**
     * Logout.
     *
     * @throws OnapCommandException
     *             exception
     */
    public void logout() throws OnapCommandException {
        // For development purpose, its introduced and is not supported for production
        if (Boolean.parseBoolean(OnapCommandConfig.getPropertyValue(OnapCommandHttpConstants.OPEN_IGNORE_AUTH))) {
            return;
        }

        OnapCommand logout = OnapCommandSchemaHttpLoader.findAuthCommand(this.cmd, "logout");

        //for logout, share the login cache, which would be used for logout such a token
        OnapCommandUtils.copyParamsFrom(this.cmd, logout, this.loginCache);

        logout.execute();

        try {
            this.http.close();
        } catch (IOException e) {
            logger.error("Exception when closing httpclient {}", e);
        }
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
        if (cmd.getService().isModeDirect() ||
            Boolean.parseBoolean(cmd.getParametersMap().get(OnapCommandHttpConstants.DEFAULT_PARAMETER_NO_CATALOG).getValue().toString())){
            return cmd.getParametersMap().get(OnapCommandHttpConstants.DEAFULT_PARAMETER_HOST_URL).getValue().toString();
        } else { //Catalog mode
            OnapCommand catalog = OnapCommandRegistrar.getRegistrar().get("catalog", cmd.getInfo().getProduct());

            Map<String, String> paramsOverrides = new HashMap<>();
            paramsOverrides.put(OnapCommandHttpConstants.CATALOG_SERVICE_NAME, cmd.getService().getName());
            paramsOverrides.put(OnapCommandHttpConstants.CATALOG_SERVICE_VERSION, cmd.getService().getVersion());

            OnapCommandUtils.copyParamsFrom(cmd, catalog, paramsOverrides);

            catalog.execute();

            String hostUrl = catalog.getResult().getRecordsMap().get(OnapCommandHttpConstants.CATALOG_SERVICE_HOST_URL).getValues().get(0);
            hostUrl = hostUrl.trim();
            if (hostUrl.endsWith("/")) {
                hostUrl = hostUrl.substring(0, hostUrl.length()-1);
            }

            String basePath = catalog.getResult().getRecordsMap().get(OnapCommandHttpConstants.CATALOG_SERVICE_BASE_PATH).getValues().get(0);
            basePath = basePath.trim();
            if (basePath.startsWith("/")) {
                basePath = basePath.substring(1);
            }

            return hostUrl + "/" + basePath;
        }
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
}
