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

package org.onap.cli.fw.conf;

import java.io.IOException;
import java.util.Properties;

/**
 * Onap command constants.
 *
 */
public final class OnapCommandConfg {

    private static Properties prps = new Properties();

    /**
     * Private constructor.
     */
    private OnapCommandConfg() {

    }

    static {
        try {
            prps.load(OnapCommandConfg.class.getClassLoader().getResourceAsStream(Constants.CONF));
        } catch (IOException e) {
            throw new RuntimeException(e); // NOSONAR
        }
    }

    /**
     * is auth service ignored.
     *
     * @return boolean
     */
    public static boolean isAuthIgnored() {
        if ("true".equals(prps.getProperty(Constants.ONAP_IGNORE_AUTH))) {
            return true;
        }

        return false;
    }

    public static String getVersion() {
        return prps.getProperty(Constants.ONAP_CLI_VERSION);
    }

    /**
     * checks if cookies based auth.
     *
     * @return boolean
     */
    public static boolean isCookiesBasedAuth() {
        if ("true".equals(prps.getProperty(Constants.HTTP_API_KEY_USE_COOKIES))) {
            return true;
        }

        return false;
    }

    public static String getXAuthTokenName() {
        return prps.getProperty(Constants.HTTP_X_AUTH_TOKEN, "X-Auth-Token");
    }

    public static String getInternalCmd() {
        return prps.getProperty(Constants.SERVICE_NAME);
    }

    public static String getApiGateway() {
        return prps.getProperty(Constants.API_GATEWAY);
    }

    public static String getAuthService() {
        return prps.getProperty(Constants.AUTH_SERVICE);
    }

}
