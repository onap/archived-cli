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

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Oclip command constants.
 *
 */
public final class OnapCommandConfig {

    private static Properties prps = new Properties();

    /**
     * Private constructor.
     */
    private OnapCommandConfig() {

    }

    static {
        loadProperties(prps, OnapCommandConstants.CONF);
        for (String prpFile: getCommaSeparatedList(OnapCommandConstants.OPEN_CLI_PLUGIN_PRPS)) {
            addProperties(prpFile);
        }
    }

    private static void loadProperties(Properties prps, String fileName) {
        try {
            prps.load(OnapCommandConfig.class.getClassLoader().getResourceAsStream(fileName));
        } catch (Exception e) {  // NOSONAR
            throw new RuntimeException(e); // NOSONAR
        }
    }

    public static void addProperties(String fileName) {
        Properties ps = new Properties();
        loadProperties(ps, fileName);

        for (Entry<Object, Object> entry: ps.entrySet()) {
            prps.put(entry.getKey(), entry.getValue());
        }
    }

    public static String getPropertyValue(String propertyName) {
        return prps.getProperty(propertyName);
    }

    public static List<String> getCommaSeparatedList(String key) {
        return Arrays.stream(getPropertyValue(key).split(",")).map(String::trim).collect(Collectors.toList());  // NOSONAR
    }
}
