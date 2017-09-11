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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public static String getEnabledProductVersion() {
        String version = System.getenv(Constants.ONAP_CLI_PRODUCT_VERSION_ENV_NAME);
        if (version == null) {
            version = prps.getProperty(Constants.ONAP_CLI_PRODUCT_VERSION);
        }
        return version;
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
        return prps.getProperty(Constants.SERVICE_AUTH_BASIC_HTTP_HEADERS + "." + Constants.X_AUTH_TOKEN);
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

    public static String getAuthType() {
        return prps.getProperty(Constants.SERVICE_AUTH, Constants.AUTH_BASIC);
    }

    private static Map<String, String> getHeaderValues(String headerKey, Map<String, String> paramMap) {
        Map<String, String> mapHeaders = new HashMap<String, String> ();
        if (prps.containsKey(headerKey)) {
            Arrays.stream(prps.getProperty(headerKey)  // NOSONAR
                    .split(",")).map(String::trim).forEach(header -> {
                        String headerName = prps.getProperty(headerKey+ "." + header);
                        String headerValue = prps.getProperty(headerKey + "." + header + ".value", null);
                        if (headerValue != null) {
                            headerValue = headerValue.replaceAll("uuid", UUID.randomUUID().toString());
                            if (headerValue.contains("${")) {
                                String param = headerValue.substring(headerValue.indexOf("${")+2 ,headerValue.indexOf("}"));
                                String pattern = "${"+param+"}";
                                headerValue = headerValue.replace(pattern, paramMap.getOrDefault(param, param));
                            }
                        }
                        mapHeaders.put(headerName, headerValue);
                    });
        }
        return mapHeaders;
    }

    public static Map<String, String> getBasicCommonHeaders(Map<String, String> paramMap) {
        return getHeaderValues(Constants.SERVICE_AUTH_BASIC_HTTP_HEADERS, paramMap);
    }

    public static Map<String, String> getServiceHeaders(String serviceName, Map<String, String> paramMap) {
        String serviceHeader = Constants.SERVICE_AUTH_BASIC_HTTP_HEADERS+ "." + serviceName;
        return getHeaderValues(serviceHeader, paramMap);
    }

    public static Set<String> getExcludeParamsForInternalCmd() {
        return Arrays.stream(prps.getProperty(Constants.EXCLUDE_PARAMS_INTERNAL_CMD)  // NOSONAR
                .split(",")).map(String::trim).collect(Collectors.toSet());
    }

    public static Set<String> getIncludeParamsForNoAuthDisableExternalCmd() {
        return Arrays.stream(prps.getProperty(Constants.NO_AUTH_DISABLE_INCLUDE_PARAMS_EXTERNAL_CMD)  // NOSONAR
                .split(",")).map(String::trim).collect(Collectors.toSet());
    }

    public static Set<String> getExcludeParamsForNoAuthEnableExternalCmd() {
        return Arrays.stream(prps.getProperty(Constants.NO_AUTH_ENABLE_EXCLUDE_PARAMS_EXTERNAL_CMD)  // NOSONAR
                .split(",")).map(String::trim).collect(Collectors.toSet());
    }

    public static Set<String> getIncludeParamsForNoAuthEnableExternalCmd() {
        return Arrays.stream(prps.getProperty(Constants.NO_AUTH_ENABLE_INCLUDE_PARAMS_EXTERNAL_CMD)  // NOSONAR
                .split(",")).map(String::trim).collect(Collectors.toSet());
    }

    public static List<String> getSchemaAttrInfo(String key) {
        return Arrays.stream(prps.getProperty(key).split(",")).map(String::trim).collect(Collectors.toList());  // NOSONAR
    }

}
