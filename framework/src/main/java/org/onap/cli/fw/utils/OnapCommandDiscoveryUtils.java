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

import static org.onap.cli.fw.conf.OnapCommandConstants.DATA_DIRECTORY;
import static org.onap.cli.fw.conf.OnapCommandConstants.DATA_PATH_JSON_PATTERN;
import static org.onap.cli.fw.conf.OnapCommandConstants.DISCOVERY_FILE;
import static org.onap.cli.fw.conf.OnapCommandConstants.JSON_PATTERN;
import static org.onap.cli.fw.conf.OnapCommandConstants.NAME;
import static org.onap.cli.fw.conf.OnapCommandConstants.OPEN_CLI_SCHEMA_VERSION;
import static org.onap.cli.fw.conf.OnapCommandConstants.SCHEMA_DIRECTORY;
import static org.onap.cli.fw.conf.OnapCommandConstants.SCHEMA_PATH_PATERN;
import static org.onap.cli.fw.conf.OnapCommandConstants.VERIFY_SAMPLES_DIRECTORY;
import static org.onap.cli.fw.conf.OnapCommandConstants.YAML_PATTERN;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandDiscoveryFailed;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInstantiationFailed;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.schema.OnapCommandSchemaInfo;
import org.onap.cli.fw.schema.OnapCommandSchemaLoader;
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
    public static OnapCommandSchemaInfo getSchemaInfo(String cmd, String version) throws OnapCommandException {
        List<OnapCommandSchemaInfo> list = OnapCommandDiscoveryUtils.discoverOrLoadSchemas(false);
        OnapCommandSchemaInfo schemaInfo = null;
        if (list != null) {
            for (OnapCommandSchemaInfo schema : list) {
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
    public static List<OnapCommandSchemaInfo> discoverOrLoadSchemas(boolean forceRefresh) throws OnapCommandException {
        List<OnapCommandSchemaInfo> schemas = new ArrayList<>();
        if (forceRefresh || Boolean.parseBoolean(OnapCommandConfig.getPropertyValue(OnapCommandConstants.DISCOVER_ALWAYS))
                || !OnapCommandDiscoveryUtils.isAlreadyDiscovered()) {
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
                    OnapCommandSchemaInfo[] list = mapper.readValue(file, OnapCommandSchemaInfo[].class);
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
    public static void persistSchemaInfo(List<OnapCommandSchemaInfo> schemas) throws OnapCommandDiscoveryFailed {
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

        for (String schemeType : OnapCommandConfig.getCommaSeparatedList(OnapCommandConstants.SCHEMA_TYPES_SUPPORTED)) {
            if (schemaYamlMap.get(schemeType) != null) {
                return schemeType;
            }
        }

        return OnapCommandConstants.BASIC_SCHEMA_PROFILE;
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
    public static List<OnapCommandSchemaInfo> discoverSchemas() throws OnapCommandException {
        List<OnapCommandSchemaInfo> extSchemas = new ArrayList<>();
        try {
            Resource[] res = findResources(SCHEMA_PATH_PATERN);
            if (res != null && res.length > 0) {
                Map<String, ?> resourceMap;

                for (Resource resource : res) {
                    try {
                        resourceMap = OnapCommandSchemaLoader.loadSchema(resource);
                    } catch (OnapCommandException e) {
                        OnapCommandUtils.LOG.error("Ignores invalid schema " + resource.getURI().toString(), e);
                        continue;
                    }

                    if (resourceMap != null && resourceMap.size() > 0) {
                        OnapCommandSchemaInfo schema = new OnapCommandSchemaInfo();

                        schema.setSchemaURI(resource.getURI().toString());

                        Object obj = resourceMap.get(OPEN_CLI_SCHEMA_VERSION);
                        schema.setVersion(obj.toString());

                        if (!schema.getVersion().equalsIgnoreCase(OnapCommandConstants.OPEN_CLI_SCHEMA_VERSION_VALUE_1_0)) {
                            OnapCommandUtils.LOG.info("Unsupported Schema version found " + schema.getSchemaURI());
                            continue;
                        }

                        //There are schema like default input parameters and does not have command name
                        if (resourceMap.get(NAME) == null) {
                            continue;
                        }

                        schema.setSchemaName(resource.getFilename());
                        schema.setCmdName((String) resourceMap.get(NAME));

                        Map<String, ?> infoMap = (Map<String, ?>) resourceMap.get(OnapCommandConstants.INFO);
                        if (infoMap != null && infoMap.get(OnapCommandConstants.INFO_TYPE) != null) {
                            schema.setType(infoMap.get(OnapCommandConstants.INFO_TYPE).toString());
                        }

                        if (infoMap != null && infoMap.get(OnapCommandConstants.INFO_PRODUCT) != null) {
                            schema.setProduct(infoMap.get(OnapCommandConstants.INFO_PRODUCT).toString());
                        }

                        if (infoMap != null && infoMap.get(OnapCommandConstants.INFO_IGNORE) != null) {
                            schema.setIgnore(infoMap.get(OnapCommandConstants.INFO_IGNORE).toString());
                        }
                        schema.setSchemaProfile(identitySchemaProfileType(resourceMap));

                        extSchemas.add(schema);
                    }
                }
            }
        } catch (IOException e) {
            throw new OnapCommandDiscoveryFailed(SCHEMA_DIRECTORY, e);
        }

        try {
            Resource[] samples = findResources(OnapCommandConstants.VERIFY_SAMPLES_DIRECTORY + YAML_PATTERN);
            for (Resource sample : samples) {
                updateSchemaInfoWithSampleInfo(sample, extSchemas);
            }
        } catch (IOException e) {
            throw new OnapCommandDiscoveryFailed(VERIFY_SAMPLES_DIRECTORY, e);
        }

        return extSchemas;
    }

    private static void updateSchemaInfoWithSampleInfo(Resource sampleResourse,
                                                      List<OnapCommandSchemaInfo> schemaInfos) throws OnapCommandInvalidSchema, IOException {
        Map<String, ?> infoMap = loadSchema(sampleResourse);
        String cmdName = (String) infoMap.get("name");
        String version = (String) infoMap.get("version");
        Map<String, Map<String, String>> samples = (Map<String, Map<String, String>>) infoMap.get("samples");
        String mockedFile = samples.get("sample1").get("moco");
        Resource mockingResource = findResource(mockedFile,
                OnapCommandConstants.VERIFY_SAMPLES_DIRECTORY + JSON_PATTERN);

        Optional<OnapCommandSchemaInfo> optSchemaInfo = schemaInfos.stream()
                .filter(e -> e.getCmdName().equals(cmdName) && e.getProduct().equals(version))
                .findFirst();

        if (optSchemaInfo.isPresent() && mockingResource != null && sampleResourse != null) {
            OnapCommandSchemaInfo onapCommandSchemaInfo = optSchemaInfo.get();
            onapCommandSchemaInfo.setMockingFile(mockingResource.getFilename());
            onapCommandSchemaInfo.setSampleFile(sampleResourse.getFilename());
        }
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
