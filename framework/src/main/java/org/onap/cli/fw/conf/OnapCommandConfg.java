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
 * Oclip command constants.
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
        if ("true".equals(prps.getProperty(Constants.OPEN_IGNORE_AUTH))) {
            return true;
        }

        return false;
    }

    /**
     * is discovery should do every time.
     *
     * @return boolean
     */
    public static boolean isDiscoverAlways() {
        if ("true".equals(prps.getProperty(Constants.DISCOVER_ALWAYS))) {
            return true;
        }

        return false;
    }

    public static String getVersion() {
        return prps.getProperty(Constants.OPEN_CLI_VERSION);
    }

    public static String getEnabledProductVersion() {
        String version = System.getenv(Constants.OPEN_OPEN_CLI_PRODUCT_IN_USE_ENV_NAME);
        if (version == null) {
            version = prps.getProperty(Constants.OPEN_CLI_PRODUCT_NAME);
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

    public static String getProductName() {
        return prps.getProperty(Constants.OPEN_CLI_PRODUCT_NAME);
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

    //mrkanag move this utils class
    public static List<String> getSchemaAttrInfo(String key) {
        return Arrays.stream(prps.getProperty(key).split(",")).map(String::trim).collect(Collectors.toList());  // NOSONAR
    }

    public static String getSampleGenerateTargetFolder() {
        return prps.getProperty(Constants.SAMPLE_GEN_TARGET_FOLDER, ".");
    }

    public static boolean isSampleGenerateEnabled() {
        return "true".equals(prps.getProperty(Constants.SAMPLE_GEN_ENABLED));
    }
}
