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

import static org.onap.cli.fw.conf.OnapCommandConstants.ATTRIBUTES;
import static org.onap.cli.fw.conf.OnapCommandConstants.DEAFULT_INPUT_PARAMETERS_NAME;
import static org.onap.cli.fw.conf.OnapCommandConstants.DEFAULT_SCHEMA_PATH_PATERN;
import static org.onap.cli.fw.conf.OnapCommandConstants.DESCRIPTION;
import static org.onap.cli.fw.conf.OnapCommandConstants.DISCOVERY_FILE;
import static org.onap.cli.fw.conf.OnapCommandConstants.IS_DEFAULT_PARAM;
import static org.onap.cli.fw.conf.OnapCommandConstants.NAME;
import static org.onap.cli.fw.conf.OnapCommandConstants.OPEN_CLI_SAMPLE_VERSION;
import static org.onap.cli.fw.conf.OnapCommandConstants.OPEN_CLI_SCHEMA_VERSION;
import static org.onap.cli.fw.conf.OnapCommandConstants.PARAMETERS;
import static org.onap.cli.fw.conf.OnapCommandConstants.RESULTS;
import static org.onap.cli.fw.conf.OnapCommandConstants.SCHEMA_DIRECTORY;
import static org.onap.cli.fw.conf.OnapCommandConstants.SCHEMA_PATH_PATERN;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

import org.apache.commons.io.FileUtils;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandDiscoveryFailed;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInstantiationFailed;
import org.onap.cli.fw.error.OnapCommandInvalidSample;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.error.OnapCommandNotFound;
import org.onap.cli.fw.schema.OnapCommandSchemaInfo;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.Writer;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;

public class OnapCommandDiscoveryUtils {
    private static Gson gson = new GsonBuilder().serializeNulls().create();

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

        if (schemaInfo == null)
             throw new OnapCommandNotFound(cmd, version);

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
                //merge the existing RPC schema with discovered ones
                List<OnapCommandSchemaInfo> schemasExisting = OnapCommandDiscoveryUtils.loadSchemas();

                Map<String, OnapCommandSchemaInfo> rpcCommands = new HashMap<>();
                for (OnapCommandSchemaInfo info: schemasExisting) {
                    if (info.isRpc()) {
                        rpcCommands.put(info.getProduct() + ":" + info.getCmdName(), info);
                    }
                }

                //mrkanag: Enable clustering for keeping command in more than one OCLIP engine
                //Remove if already an same command exists with RPC
                for (OnapCommandSchemaInfo info: schemas) {
                    OnapCommandSchemaInfo infoExisting = rpcCommands.get(info.getProduct() + ":" + info.getCmdName());
                    if (infoExisting != null) {
                        rpcCommands.remove(info.getProduct() + ":" + info.getCmdName());
                    }
                }

                //Add all RPC ones
                schemas.addAll(rpcCommands.values());

                OnapCommandDiscoveryUtils.persistSchemaInfo(schemas);
            }
        } else {
            schemas = OnapCommandDiscoveryUtils.loadSchemas();
        }

        return schemas;
    }

    public static String getDataStorePath() {
        return OnapCommandConfig.getPropertyValue(OnapCommandConstants.OPEN_CLI_DATA_DIR);
    }

    public static List<OnapCommandSchemaInfo> loadSchemas() throws OnapCommandException {
        List<OnapCommandSchemaInfo> schemas = new ArrayList<>();

        if (!OnapCommandDiscoveryUtils.isAlreadyDiscovered()) return schemas;

        String dataDir = OnapCommandDiscoveryUtils.getDataStorePath();
        File file = new File(dataDir + File.separator + DISCOVERY_FILE);
        try (JsonReader jsonReader = new JsonReader(new FileReader(file))){
            OnapCommandSchemaInfo[] list = gson.fromJson(jsonReader, OnapCommandSchemaInfo[].class);
            schemas.addAll(Arrays.asList(list));
        } catch (Exception e) { // NOSONAR
            throw new OnapCommandDiscoveryFailed(dataDir,
                    DISCOVERY_FILE, e);
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
        String dataDir = OnapCommandDiscoveryUtils.getDataStorePath();
        return new File(dataDir + File.separator + DISCOVERY_FILE).exists();
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
            String dataDir = OnapCommandDiscoveryUtils.getDataStorePath();

            try {
                FileUtils.forceMkdir(new File(dataDir));

                File file = new File(dataDir + File.separator + DISCOVERY_FILE);
                try(Writer writer = new FileWriter(file)){
                    gson.toJson(schemas,writer);
                }
            } catch (Exception e1) { // NOSONAR
                throw new OnapCommandDiscoveryFailed(dataDir,
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
            values = loadYaml(resource.getInputStream());
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
            //collect default input parameters for every profile
            Resource[] deafultRres = findResources(DEFAULT_SCHEMA_PATH_PATERN);
            Map <String, List<Object>> defaultInputs = new HashMap<>();

            if (deafultRres != null && deafultRres.length > 0) {
                Map<String, ?> deafultResourceMap;

                for (Resource resource : deafultRres) {
                    try {
                        deafultResourceMap = loadYaml(resource);
                    } catch (OnapCommandException e) {
                        OnapCommandUtils.log.error("Ignores invalid schema " + resource.getURI().toString(), e);
                        continue;
                    }

                    if (deafultResourceMap != null && deafultResourceMap.size() > 0) {
                        //default_input_parameters_http.yaml
                        String profileName = resource.getFilename().substring(
                                DEAFULT_INPUT_PARAMETERS_NAME.length() + 1,
                                resource.getFilename().indexOf('.'));
                        if (deafultResourceMap.containsKey(PARAMETERS)) {
                            List<Object> params = new ArrayList<>();
                            for (Map<String, ?> p: (List<Map<String, ?>>) deafultResourceMap.get(PARAMETERS)) {
                                if (p.keySet().contains(IS_DEFAULT_PARAM) && ! (Boolean.getBoolean(String.valueOf(p.get(IS_DEFAULT_PARAM))))) {
                                    params.add(p);
                                }
                            }

                            defaultInputs.put(profileName, params);
                        }
                    }
                }
            }

            Resource[] res = findResources(SCHEMA_PATH_PATERN);
            if (res != null && res.length > 0) {
                for (Resource resource : res) {
                    Map<String, ?> resourceMap;
                    try {
                        resourceMap = loadYaml(resource);
                    } catch (OnapCommandException e) {
                        OnapCommandUtils.log.error("Ignores invalid schema " + resource.getURI().toString(), e);
                        continue;
                    }

                    if (resourceMap != null && resourceMap.size() > 0) {
                        OnapCommandSchemaInfo schema = new OnapCommandSchemaInfo();

                        schema.setSchemaURI(resource.getURI().toString());

                        Object obj = resourceMap.get(OPEN_CLI_SCHEMA_VERSION);
                        if (obj == null) {
                            OnapCommandUtils.log.info("Invalid Schema yaml " + schema.getSchemaURI());
                            continue;
                        }

                        schema.setVersion(obj.toString());

                        if (!schema.getVersion().equalsIgnoreCase(OnapCommandConstants.OPEN_CLI_SCHEMA_VERSION_VALUE_1_0)) {
                            OnapCommandUtils.log.info("Unsupported Schema version found " + schema.getSchemaURI());
                            continue;
                        }

                        //There are schema like default input parameters and does not have command name
                        if (resourceMap.get(NAME) == null) {
                            continue;
                        }

                        schema.setSchemaName(resource.getFilename());
                        schema.setCmdName((String) resourceMap.get(NAME));

                        schema.setDescription((String) resourceMap.get(DESCRIPTION));

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

                        if (infoMap != null && infoMap.get(OnapCommandConstants.INFO_STATE) != null) {
                            schema.setState(infoMap.get(OnapCommandConstants.INFO_STATE).toString());
                        }

                        if (infoMap != null && infoMap.get(OnapCommandConstants.INFO_SERVICE) != null) {
                            schema.setService(infoMap.get(OnapCommandConstants.INFO_SERVICE).toString());
                        }

                        if (infoMap != null && infoMap.get(OnapCommandConstants.INFO_AUTHOR) != null) {
                            schema.setAuthor(infoMap.get(OnapCommandConstants.INFO_AUTHOR).toString());
                        }

                        if (infoMap != null && infoMap.get(OnapCommandConstants.INFO_METADATA) != null) {
                            schema.setMetadata((Map<String, String>)infoMap.get(OnapCommandConstants.INFO_METADATA));
                        }

                        schema.setSchemaProfile(identitySchemaProfileType(resourceMap));

                        if (resourceMap.containsKey(PARAMETERS)) {
                            schema.setInputs((List<Object>)resourceMap.get(PARAMETERS));
                            if (defaultInputs.get(schema.getSchemaProfile()) != null) {
                                schema.getInputs().addAll(defaultInputs.get(schema.getSchemaProfile()));
                            }
                        }

                        if (resourceMap.containsKey(RESULTS)) {
                            schema.setOutputs((List<Object>)((Map<String, Object>)resourceMap.get(RESULTS)).get(ATTRIBUTES));
                        }

                        extSchemas.add(schema);
                    }
                }
            }
        } catch (IOException e) {
            throw new OnapCommandDiscoveryFailed(SCHEMA_DIRECTORY, e);
        }

        try {
            Resource[] samples = findResources(OnapCommandConstants.VERIFY_SAMPLES_FILE_PATTERN);
            for (Resource sample : samples) {
                    updateSchemaInfoWithSample(sample, extSchemas);
            }
        } catch (IOException e) {
            throw new OnapCommandDiscoveryFailed(OnapCommandConstants.VERIFY_SAMPLES_DIRECTORY, e);
        }

        return extSchemas;
    }

    private static void updateSchemaInfoWithSample(Resource sampleResourse,
                                                      List<OnapCommandSchemaInfo> schemaInfos) throws OnapCommandInvalidSchema, IOException {
        Map<String, ?> infoMap = loadSchema(sampleResourse);

        if (infoMap == null) {
            return;
        }

        Object sampleVersion = infoMap.get(OPEN_CLI_SAMPLE_VERSION);
        if (sampleVersion == null) {
            OnapCommandUtils.log.info("Invalid Sample yaml " + sampleResourse.getURI().toString());
            return;
        }

        if (!sampleVersion.toString().equalsIgnoreCase(OnapCommandConstants.OPEN_CLI_SAMPLE_VERSION_VALUE_1_0)) {
            OnapCommandUtils.log.info("Unsupported Sample version found " + sampleResourse.getURI().toString());
            return;
        }

        String cmdName = (String) infoMap.get(OnapCommandConstants.VERIFY_CMD_NAME);
        String version = (String) infoMap.get(OnapCommandConstants.VERIFY_CMD_VERSION);

        Optional<OnapCommandSchemaInfo> optSchemaInfo = schemaInfos.stream()
                .filter(e -> e.getCmdName().equals(cmdName) && e.getProduct().equals(version))
                .findFirst();

        if (optSchemaInfo.isPresent()) {
            OnapCommandSchemaInfo onapCommandSchemaInfo = optSchemaInfo.get();
            onapCommandSchemaInfo.getSampleFiles().add(sampleResourse.getFilename());
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

    public static List<Map<String, ?>> createTestSuite(String cmd, String version) throws OnapCommandException {

        ArrayList<Map<String, ?>> testSamples = new ArrayList();

        List<Resource> resources = new ArrayList();
        OnapCommandSchemaInfo schemaInfo =  getSchemaInfo(cmd, version);

        List<String> sampleFiles = new ArrayList();
        if (schemaInfo != null && !schemaInfo.getSampleFiles().isEmpty()) {
            sampleFiles.addAll(schemaInfo.getSampleFiles());
        }

        for (String sampleFile : sampleFiles) {
            try {
                Resource resource = OnapCommandDiscoveryUtils.findResource(sampleFile,
                        OnapCommandConstants.VERIFY_SAMPLES_FILE_PATTERN);
                resources.add(resource);
            } catch (IOException e) {
                throw new OnapCommandInvalidSample("Sample file does not exist : " + sampleFile , e);
            }
        }

        for (Resource resource : resources) {

            Map<String, ?> stringMap = OnapCommandDiscoveryUtils.loadYaml(resource);
            Map<String, Map<String, String>> samples = (Map<String, Map<String, String>>) stringMap
                    .get(OnapCommandConstants.VERIFY_SAMPLES);

            for (String sampleId : samples.keySet()) {

                Map<String, String> sample = samples.get(sampleId);

                List<String> inputArgs = new ArrayList();
                if (sample.get(OnapCommandConstants.VERIFY_INPUT) != null) {
                    inputArgs.addAll(Arrays.asList(sample.get(OnapCommandConstants.VERIFY_INPUT).trim().split(" ")));
                }
                inputArgs.add(OnapCommandConstants.VERIFY_LONG_OPTION);

                HashMap map = new HashMap();
                map.put(OnapCommandConstants.VERIFY_INPUT, inputArgs);
                map.put(OnapCommandConstants.VERIFY_OUPUT, sample.get(OnapCommandConstants.VERIFY_OUPUT));
                map.put(OnapCommandConstants.VERIFY_MOCO, sample.get(OnapCommandConstants.VERIFY_MOCO));
                map.put(OnapCommandConstants.VERIFY_SAMPLE_FILE_ID, resource.getFilename());
                map.put(OnapCommandConstants.VERIFY_SAMPLE_ID, sampleId);
                testSamples.add(map);
            }
        }
        return testSamples;
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
    public static Map<String, ?> loadYaml(Resource resource) throws OnapCommandInvalidSchema {
        Map<String, ?> values = null;
        try {
            values = loadYaml(resource.getInputStream());
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(resource.getFilename(), e);
        }
        return values;
    }

    /**
     * Get schema map.
     *
     * @param filePath
     * @return map
     * @throws OnapCommandInvalidSchema
     *             exception
     */
    public static Map<String, ?> loadYaml(String filePath) throws OnapCommandInvalidSchema {
        Map<String, ?> values = null;
        try {
            values = loadYaml(new FileInputStream(new File(filePath)));
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(filePath, e);
        }
        return values;
    }


    /**
     * Get schema map.
     *
     * @param inputStream
     * @return map
     * @throws OnapCommandInvalidSchema
     *             exception
     */
    public static Map<String, ?> loadYaml(InputStream inputStream) throws OnapCommandInvalidSchema {
        Map<String, ?> values = null;
        try(InputStreamReader inputStreamReader = new InputStreamReader(inputStream);){
            YamlReader reader = new YamlReader(inputStreamReader);
            values = (Map<String, ?>) reader.read();
            } catch (YamlException e) {
                throw new OnapCommandInvalidSchema(inputStream.getClass().getName(),e.getMessage());
            } catch (IOException e) {
                throw new OnapCommandInvalidSchema(inputStream.getClass().getName(),e.getMessage());
            }
        return values;
    }
}

