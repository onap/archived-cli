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

package org.onap.cli.fw.utils;

import static org.onap.cli.fw.conf.Constants.DATA_DIRECTORY;
import static org.onap.cli.fw.conf.Constants.DATA_PATH_JSON_PATTERN;
import static org.onap.cli.fw.conf.Constants.DISCOVERY_FILE;
import static org.onap.cli.fw.conf.Constants.NAME;
import static org.onap.cli.fw.conf.Constants.OPEN_CLI_SCHEMA_VERSION;
import static org.onap.cli.fw.conf.Constants.SCHEMA_DIRECTORY;
import static org.onap.cli.fw.conf.Constants.SCHEMA_PATH_PATERN;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.conf.OnapCommandConfg;
import org.onap.cli.fw.error.OnapCommandDiscoveryFailed;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInstantiationFailed;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OnapCommandDiscoveryUtils {

    /**
     * Fetch a particular schema details.
     *
     * @param cmd
     *            command name
     * @return ExternalSchema obj
     * @throws OnapCommandInvalidSchema
     *             exception
     * @throws OnapCommandDiscoveryFailed
     *             exception
     */
    public static SchemaInfo getSchemaInfo(String cmd, String version) throws OnapCommandException {
        List<SchemaInfo> list = OnapCommandDiscoveryUtils.discoverOrLoadSchemas(false);
        SchemaInfo schemaInfo = null;
        if (list != null) {
            for (SchemaInfo schema : list) {
                if (cmd.equals(schema.getCmdName()) && version.equals(schema.getProduct())) {
                    schemaInfo = schema;
                    break;
                }
            }
        }
        return schemaInfo;
    }

    /**
     * Load the previous discovered json file.
     *
     * @return list
     * @throws OnapCommandInvalidSchema
     *             exception
     * @throws OnapCommandDiscoveryFailed
     *             exception
     */
    public static List<SchemaInfo> discoverOrLoadSchemas(boolean forceRefresh) throws OnapCommandException {
        List<SchemaInfo> schemas = new ArrayList<>();
        if (forceRefresh || OnapCommandConfg.isDiscoverAlways() || !OnapCommandDiscoveryUtils.isAlreadyDiscovered()) {
            schemas = OnapCommandDiscoveryUtils.discoverSchemas();
            if (!schemas.isEmpty()) {
                OnapCommandDiscoveryUtils.persistSchemaInfo(schemas);
            }
        } else {
            try {
                Resource resource = OnapCommandDiscoveryUtils.findResource(DISCOVERY_FILE,
                        DATA_PATH_JSON_PATTERN);
                if (resource != null) {
                    File file = new File(resource.getURI().getPath());
                    ObjectMapper mapper = new ObjectMapper();
                    SchemaInfo[] list = mapper.readValue(file, SchemaInfo[].class);
                    schemas.addAll(Arrays.asList(list));
                }
            } catch (IOException e) {
                throw new OnapCommandDiscoveryFailed(DATA_DIRECTORY,
                        DISCOVERY_FILE, e);
            }
        }

        return schemas;
    }

    /**
     * Check if json file discovered or not.
     *
     * @return boolean
     * @throws OnapCommandDiscoveryFailed
     *             exception
     */
    public static boolean isAlreadyDiscovered() throws OnapCommandDiscoveryFailed {
        Resource resource = null;
        try {
            resource = OnapCommandDiscoveryUtils.findResource(DISCOVERY_FILE,
                    DATA_PATH_JSON_PATTERN);
            if (resource != null) {
                return true;
            }
        } catch (IOException e) {
            throw new OnapCommandDiscoveryFailed(DATA_DIRECTORY,
                    DISCOVERY_FILE, e);
        }

        return false;
    }

    /**
     * Persist the external schema details.
     *
     * @param schemas
     *            list
     * @throws OnapCommandDiscoveryFailed
     *             exception
     */
    public static void persistSchemaInfo(List<SchemaInfo> schemas) throws OnapCommandDiscoveryFailed {
        if (schemas != null) {
            try {
                Resource[] resources = OnapCommandDiscoveryUtils.findResources(DATA_DIRECTORY);
                if (resources != null && resources.length == 1) {
                    String path = resources[0].getURI().getPath();
                    File file = new File(path + File.separator + DISCOVERY_FILE);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, schemas);
                }
            } catch (IOException e1) {
                throw new OnapCommandDiscoveryFailed(DATA_DIRECTORY,
                        DISCOVERY_FILE, e1);
            }
        }
    }

    /**
     * Get schema map.
     *
     * @param resource
     *            resource obj
     * @return map
     * @throws OnapCommandInvalidSchema
     *             exception
     */
    public static Map<String, ?> loadSchema(Resource resource) throws OnapCommandInvalidSchema {
        Map<String, ?> values = null;
        try {
            values = (Map<String, ?>) new Yaml().load(resource.getInputStream());
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(resource.getFilename(), e);
        }
        return values;
    }

    /**
     * Returns a resource available under certain directory in class-path.
     *
     * @param pattern
     *            search pattern
     * @return found resource
     * @throws IOException
     *             exception
     */
    public static Resource findResource(String fileName, String pattern) throws IOException {
        Resource[] resources = OnapCommandDiscoveryUtils.findResources(pattern);
        if (resources != null && resources.length > 0) {
            for (Resource res : resources) {
                if (res.getFilename().equals(fileName)) {
                    return res;
                }
            }
        }

        return null;
    }

    /**
     * Returns all resources available under certain directory in class-path.
     *
     * @param pattern
     *            search pattern
     * @return resources found resources
     * @throws IOException
     *             exception
     */
    public static Resource[] findResources(String pattern) throws IOException {
        ClassLoader cl = OnapCommandUtils.class.getClassLoader();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
        return resolver.getResources("classpath*:" + pattern);
    }

    public static String identitySchemaProfileType(Map<String, ?> schemaYamlMap) {

        for (String schemeType : OnapCommandConfg.getSchemaAttrInfo(Constants.SCHEMA_TYPES_SUPPORTED)) {
            if (schemaYamlMap.get(schemeType) != null) {
                return schemeType;
            }
        }

        return Constants.BASIC_SCHEMA_PROFILE;
    }

    /**
     * Find external schema files.
     *
     * @return list ExternalSchema
     * @throws OnapCommandDiscoveryFailed
     *             exception
     * @throws OnapCommandInvalidSchema
     *             exception
     */
    public static List<SchemaInfo> discoverSchemas() throws OnapCommandException {
        List<SchemaInfo> extSchemas = new ArrayList<>();
        try {
            Resource[] res = findResources(SCHEMA_PATH_PATERN);
            if (res != null && res.length > 0) {
                Map<String, ?> resourceMap;

                for (Resource resource : res) {
                    try {
                        resourceMap = OnapCommandSchemaLoaderUtils.loadSchema(resource);
                    } catch (OnapCommandException e) {
                        OnapCommandUtils.LOG.error("Ignores invalid schema " + resource.getURI().toString(), e);
                        continue;
                    }

                    if (resourceMap != null && resourceMap.size() > 0) {
                        SchemaInfo schema = new SchemaInfo();

                        schema.setSchemaURI(resource.getURI().toString());

                        Object obj = resourceMap.get(OPEN_CLI_SCHEMA_VERSION);
                        schema.setVersion(obj.toString());

                        if (!schema.getVersion().equalsIgnoreCase(Constants.OPEN_CLI_SCHEMA_VERSION_VALUE_1_0)) {
                            OnapCommandUtils.LOG.info("Unsupported Schema version found " + schema.getSchemaURI());
                            continue;
                        }

                        schema.setSchemaName(resource.getFilename());
                        schema.setCmdName((String) resourceMap.get(NAME));

                        Map<String, ?> infoMap = (Map<String, ?>) resourceMap.get(Constants.INFO);
                        if (infoMap != null && infoMap.get(Constants.INFO_TYPE) != null) {
                            schema.setType(infoMap.get(Constants.INFO_TYPE).toString());
                        }

                        if (infoMap != null && infoMap.get(Constants.INFO_PRODUCT) != null) {
                            schema.setProduct(infoMap.get(Constants.INFO_PRODUCT).toString());
                        }

                        if (infoMap != null && infoMap.get(Constants.INFO_IGNORE) != null) {
                            schema.setIgnore(infoMap.get(Constants.INFO_IGNORE).toString());
                        }
                        schema.setSchemaProfile(identitySchemaProfileType(resourceMap));

                        extSchemas.add(schema);
                    }
                }
            }
        } catch (IOException e) {
            throw new OnapCommandDiscoveryFailed(SCHEMA_DIRECTORY, e);
        }

        return extSchemas;
    }

    /**
     * Discover the Oclip commands.
     *
     * @return list
     */
    public static List<Class<OnapCommand>> discoverCommandPlugins() {
        ServiceLoader<OnapCommand> loader = ServiceLoader.load(OnapCommand.class);
        List<Class<OnapCommand>> clss = new ArrayList<>();
        for (OnapCommand implClass : loader) {
            clss.add((Class<OnapCommand>) implClass.getClass());
        }

        return clss;
    }

    /**
     * Instantiate command plugin
     * @throws OnapCommandInstantiationFailed
     */
    public static OnapCommand loadCommandClass(Class <? extends OnapCommand> cls) throws OnapCommandInstantiationFailed {
        try {
            Constructor<?> constr = cls.getConstructor();
            return (OnapCommand) constr.newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new OnapCommandInstantiationFailed(cls.getName(), e);
        }

    }
}
