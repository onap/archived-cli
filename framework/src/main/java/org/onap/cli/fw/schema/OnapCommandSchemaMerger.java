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

package org.onap.cli.fw.schema;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helps to merge two yaml schemas
 *
 */
public class OnapCommandSchemaMerger {

    static Logger logger = LoggerFactory.getLogger(OnapCommandSchemaMerger.class); // NOSONAR

    private OnapCommandSchemaMerger(){
        //It is made private in order to resolve: Utility classes should not have public constructors
    }

    public static Map<String, Object> mergeSchemas(OnapCommand cmd) throws OnapCommandException {
        Map<String, Object> mergedResult = new LinkedHashMap<>();

        for (String schema: cmd.getSchemas()) {
            Map<String , Object> schemaMap = OnapCommandSchemaLoader.validateSchemaVersion(schema, cmd.getSchemaVersion());
            mergeYamlMap(mergedResult, schemaMap);
        }

        return mergedResult;

    }

    public static void mergeYamlMap(Map<String, Object> mergedResult, Map<String, Object> yamlContents) {
        if (yamlContents == null) return;

        for (Map.Entry<String,Object> entry : yamlContents.entrySet()) {

            String key = entry.getKey();
            Object yamlValue = entry.getValue();
            if (yamlValue == null) {
                mergedResult.put(key, yamlValue);
                continue;
            }

            Object existingValue = mergedResult.get(key);
            if (existingValue != null) {
                if (yamlValue instanceof Map) {
                    if (existingValue instanceof Map) {
                        mergeYamlMap((Map<String, Object>) existingValue, (Map<String, Object>)  yamlValue);
                    } else if (existingValue instanceof String) {
                        throw new IllegalArgumentException("Cannot merge complex element into a simple element: "+key);
                    } else {
                        throw unknownValueType(key, yamlValue);
                    }
                } else if (yamlValue instanceof List) {
                    mergeYamlLists(mergedResult, key, yamlValue);

                } else if (yamlValue instanceof String
                        || yamlValue instanceof Boolean
                        || yamlValue instanceof Double
                        || yamlValue instanceof Integer) {
                    mergedResult.put(key, yamlValue);

                } else {
                    throw unknownValueType(key, yamlValue);
                }

            } else {
                if (yamlValue instanceof Map
                        || yamlValue instanceof List
                        || yamlValue instanceof String
                        || yamlValue instanceof Boolean
                        || yamlValue instanceof Integer
                        || yamlValue instanceof Double) {
                    mergedResult.put(key, yamlValue);
                } else {
                    throw unknownValueType(key, yamlValue);
                }
            }
        }
    }

    private static IllegalArgumentException unknownValueType(String key, Object yamlValue) {
        final String msg = "Cannot merge element of unknown type: " + key + ": " + yamlValue.getClass().getName();
        return new IllegalArgumentException(msg);
    }

    @SuppressWarnings("unchecked")
    private static void mergeYamlLists(Map<String, Object> mergedResult, String key, Object yamlValue) {
        if (! (yamlValue instanceof List && mergedResult.get(key) instanceof List)) {
            throw new IllegalArgumentException("Cannot merge a list with a non-list: "+key);
        }

        List<Object> originalList = (List<Object>) mergedResult.get(key);

        for (Object o: (List<Object>) yamlValue) {
            Map<String, Object> oN = (Map) o;
            String nameN = (String)oN.getOrDefault(OnapCommandConstants.NAME, null);

            //Name should be existing in the map, otherwise continue as don't know how to compare
            if (nameN != null) {

                boolean existing = false;
                for (Object e: originalList) {
                    Map<String, Object> oE = (Map) e;
                    String nameE = (String)oE.getOrDefault(OnapCommandConstants.NAME, null);

                    //Name should be existing in the map, otherwise continue as don't know how to compare
                    if (nameN.equals(nameE)) {
                        for (Entry<String, Object> oNe : oN.entrySet()) {
                               oE.put(oNe.getKey(), oNe.getValue());
                        }
                        existing = true;
                        break;
                    }
                }

                if (!existing) {
                    originalList.add(o);
                }
            }
        }
    }
}

