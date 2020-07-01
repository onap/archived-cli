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

package org.onap.cli.fw.registrar;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.cmd.dummy.OnapCommandDummy;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandNotFound;
import org.onap.cli.fw.error.OnapCommandProductVersionInvalid;
import org.onap.cli.fw.error.OnapCommandRegistrationProductInfoMissing;
import org.onap.cli.fw.error.OnapUnsupportedSchemaProfile;
import org.onap.cli.fw.output.OnapCommandPrintDirection;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.output.OnapCommandResultAttributeScope;
import org.onap.cli.fw.output.OnapCommandResultType;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.schema.OnapCommandSchemaInfo;
import org.onap.cli.fw.store.OnapCommandProfileStore;
import org.onap.cli.fw.utils.OnapCommandDiscoveryUtils;
import org.onap.cli.fw.utils.OnapCommandHelperUtils;
import org.onap.cli.fw.utils.OnapCommandUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Oclip Command registrar provides a common place, where every command would get registered automatically when its
 * loaded into JVM.
 *
 */
public class OnapCommandRegistrar {

    private static Logger log = LoggerFactory.getLogger(OnapCommandRegistrar.class);

    private Map<String, Class<? extends OnapCommand>> registry = new HashMap<>();

    private Map<String, Class<? extends OnapCommand>> registryProfilePlugins = new HashMap<>();

    private Set<String> availableProductVersions = new HashSet<>();

    private String enabledProductVersion = null;

    private boolean isInteractiveMode = false;

    private String host;

    private int port;

    private OnapCommandProfileStore paramCache = OnapCommandProfileStore.getInstance();

    public boolean isInteractiveMode() {
        return isInteractiveMode;
    }

    public void setInteractiveMode(boolean isInteractiveMode) {
        this.isInteractiveMode = isInteractiveMode;
    }

    public Map<String, String> getParamCache() {
        return this.getParamCache(this.getEnabledProductVersion());
    }

    public Map<String, String> getParamCache(String product) {
        return paramCache.getParams(product);
    }

    public void addParamCache(String paramName, String paramValue) {
        paramCache.add(this.getEnabledProductVersion(), paramName, paramValue);
    }

    public void removeParamCache(String paramName) {
        paramCache.remove(this.getEnabledProductVersion(), paramName);
    }

    public void setProfile(String profileName, List<String> includes, List<String> excludes) throws OnapCommandException {
        this.paramCache.setProfile(profileName);

        for (String profile : includes) {
            this.paramCache.includeProfile(profile);
        }

        for (String profile : excludes) {
            this.paramCache.excludeProfile(profile);
        }
    }

    public List<String> getUserProfiles() {
        return paramCache.getProfiles();
    }

    private static OnapCommandRegistrar registrar = null;

    /**
     * Register the command into registrar and throws OnapInvalidCommandRegistration for invalid command.
     *
     * @param name Command Name
     * @param cmd  Command Class
     * @throws OnapCommandRegistrationProductInfoMissing
     */
    private void register(String name, String version, Class<? extends OnapCommand> cmd) throws OnapCommandRegistrationProductInfoMissing {
        if (version == null || version.isEmpty()) {
            throw new OnapCommandRegistrationProductInfoMissing(name);
        }

        this.registry.put(name + ":" + version, cmd);
        log.info("REGISITER : {} : {} = {}", name, version, cmd.getCanonicalName());
        this.availableProductVersions.add(version);

    }

    private void registerProfilePlugin(String profile, Class<? extends OnapCommand> cmd) {
        this.registryProfilePlugins.put(profile, cmd);
    }

    private OnapCommandRegistrar() {
        this.enabledProductVersion = System.getenv(OnapCommandConstants.OPEN_CLI_PRODUCT_IN_USE_ENV_NAME); //NOSONAR
        if (this.enabledProductVersion == null) {
            this.enabledProductVersion = OnapCommandConfig.getPropertyValue(OnapCommandConstants.OPEN_CLI_PRODUCT_NAME);
        }
    }

    /**
     * Get global registrar.
     *
     * @throws OnapCommandException exception
     */
    public static OnapCommandRegistrar getRegistrar() throws OnapCommandException {
        if (registrar == null) {
            registrar = new OnapCommandRegistrar();
            registrar.autoDiscoverSchemas(true);
        }

        return registrar;
    }

    public void resync() throws OnapCommandException {
        registrar.autoDiscoverSchemas(false);
    }

    /**
     * Get the list of discovered commands by registrar.
     *
     * @return set
     */
    public Set<String> listCommands() {
        return this.registry.keySet();
    }

    /**
     * Get the list of discovered commands for a given product version in registrar.
     *
     * @return set
     */
    public Set<String> listCommandsForEnabledProductVersion() {
        String version = this.getEnabledProductVersion();

        Set<String> cmds = new HashSet<>();
        if (!this.availableProductVersions.contains(version)) {
            return cmds;
        }

        for (String cmd : this.registry.keySet()) {
            if (cmd.split(":")[1].equalsIgnoreCase(version)) {
                cmds.add(cmd.split(":")[0]);
            }
        }
        return cmds;
    }

    public Class<? extends OnapCommand> getProfilePlugin(String profile) throws OnapUnsupportedSchemaProfile {
        if (!this.registryProfilePlugins.containsKey(profile)) {
            throw new OnapUnsupportedSchemaProfile(profile);
        }

        return this.registryProfilePlugins.get(profile);
    }

    public Set<String> getAvailableProductVersions() {
        return this.availableProductVersions;
    }

    public void setEnabledProductVersion(String version) throws OnapCommandProductVersionInvalid {
        if (!this.availableProductVersions.contains(version)) {
            throw new OnapCommandProductVersionInvalid(version, availableProductVersions);
        }

        this.enabledProductVersion = version;
    }

    public String getEnabledProductVersion() {
        return this.enabledProductVersion;
    }

    /**
     * Returns command details.
     *
     * @return map
     * @throws OnapCommandException exception
     */
    public List<OnapCommandSchemaInfo> listCommandInfo() throws OnapCommandException {
        return OnapCommandDiscoveryUtils.discoverSchemas();
    }

    /**
     * Get the OnapCommand, which CLI main would use to find the command based on the command name.
     *
     * @param cmdName Name of command
     * @return OnapCommand
     * @throws OnapCommandException Exception
     */
    public OnapCommand get(String cmdName) throws OnapCommandException {
        return this.get(cmdName, this.getEnabledProductVersion());
    }

    /**
     * Get the OnapCommand, which CLI main would use to find the command based on the command name.
     *
     * @param cmdName Name of command
     * @param version product version
     * @return OnapCommand
     * @throws OnapCommandException Exception
     */
    public OnapCommand get(String cmdName, String version) throws OnapCommandException {
        Class<? extends OnapCommand> cls = registry.get(cmdName + ":" + version);
        //mrkanag: Restrict auth/catalog type commands only available during devMode. in production
        //don't expose the auth type and catalog type commands

        if (cls == null) {
            throw new OnapCommandNotFound(cmdName, version);
        }

        OnapCommand cmd = OnapCommandDiscoveryUtils.loadCommandClass(cls);

        OnapCommandSchemaInfo info = OnapCommandDiscoveryUtils.getSchemaInfo(cmdName, version);

        cmd.initializeSchema(info);

        return cmd;
    }

    private Map<String, Class<OnapCommand>> autoDiscoverCommandPlugins() throws OnapCommandException {
        List<Class<OnapCommand>> cmds = OnapCommandDiscoveryUtils.discoverCommandPlugins();
        Map<String, Class<OnapCommand>> map = new HashMap<>();

        for (Class<OnapCommand> cmd : cmds) {
            if (cmd.isAnnotationPresent(OnapCommandSchema.class)) {
                OnapCommandSchema ano = cmd.getAnnotation(OnapCommandSchema.class);
                if (ano.schema() != null && !ano.schema().isEmpty()) {
                    map.put(ano.schema(), cmd);
                } else if (ano.type() != null && !ano.type().isEmpty()) {
                    this.registerProfilePlugin(ano.type(), cmd);
                    map.put(ano.type(), cmd);
                } else {
                    throw new OnapUnsupportedSchemaProfile(ano.schema());
                }
            }
        }

        return map;
    }

    private void autoDiscoverSchemas(boolean refresh) throws OnapCommandException {
        List<OnapCommandSchemaInfo> schemas = OnapCommandDiscoveryUtils.discoverOrLoadSchemas(refresh);

        Map<String, Class<OnapCommand>> plugins = this.autoDiscoverCommandPlugins();

        for (OnapCommandSchemaInfo schema : schemas) {
            if (schema.isIgnore()) {
                log.info("Ignoring schema {}", schema.getSchemaURI());
                continue;
            }

            //First check if there is an specific plug-in exist, otherwise check for profile plug-in
            if (schema.isRpc()) {
                //proxy the schema by using rpc schema, when the schema is marked with rpc
                this.register(schema.getCmdName(), schema.getProduct(), plugins.get("schema-rpc.yaml"));
            } else if (plugins.containsKey(schema.getSchemaName())) {
                this.register(schema.getCmdName(), schema.getProduct(), plugins.get(schema.getSchemaName()));
            } else if (plugins.containsKey(schema.getSchemaProfile())) {
                this.register(schema.getCmdName(), schema.getProduct(), plugins.get(schema.getSchemaProfile()));
            } else {
                this.register(schema.getCmdName(), schema.getProduct(), OnapCommandDummy.class);
            }
        }
    }

    /**
     * Helps to find the Oclip CLI version, could be used with --version or -v option.
     *
     * @return string
     */
    public String getVersion() {
        String version = this.getClass().getPackage().getImplementationVersion();
        if (version == null) {
            version = OnapCommandConfig.getPropertyValue(OnapCommandConstants.OPEN_CLI_VERSION);
        }

        String buildTime = OnapCommandHelperUtils.findLastBuildTime();
        if (buildTime != null && !buildTime.isEmpty()) {
            buildTime = " [" + buildTime + "]";
        } else {
            buildTime = "";
        }

        String configuredProductVersion = this.getEnabledProductVersion();

        String versionInfo = "";
        try {
            versionInfo = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(OnapCommandConstants.VERSION_INFO));
        } catch (IOException e) { // NOSONAR
            //Never occurs
        }

        versionInfo = versionInfo.replaceAll(OnapCommandConstants.VERSION_INFO_PLACE_HOLDER_ENB_PRD_VER, configuredProductVersion);
        versionInfo = versionInfo.replaceAll(OnapCommandConstants.VERSION_INFO_PLACE_HOLDER_AVL_PRD_VER, this.availableProductVersions.toString());
        versionInfo = versionInfo.replaceAll(OnapCommandConstants.VERSION_INFO_PLACE_HOLDER_VERSION + "", version + buildTime);

        return versionInfo;
    }

    /**
     * Provides the help message in tabular format for all commands registered in this registrar.
     *
     * @return string
     * @throws OnapCommandHelpFailed Help cmd failed
     */
    public String getHelp() throws OnapCommandHelpFailed {
        return this.getHelp(false);
    }

    public String getHelpForEnabledProductVersion() throws OnapCommandHelpFailed {
        return this.getHelp(true);
    }

    private String getHelp(boolean isEnabledProductVersionOnly) throws OnapCommandHelpFailed {
        OnapCommandResult help = new OnapCommandResult();
        help.setType(OnapCommandResultType.TABLE);
        help.setPrintDirection(OnapCommandPrintDirection.LANDSCAPE);

        OnapCommandResultAttribute attr = new OnapCommandResultAttribute();
        attr.setName(OnapCommandConstants.NAME.toUpperCase());
        attr.setDescription(OnapCommandConstants.DESCRIPTION);
        attr.setScope(OnapCommandResultAttributeScope.SHORT);
        help.getRecords().add(attr);

        OnapCommandResultAttribute attrVer = new OnapCommandResultAttribute();
        if (!isEnabledProductVersionOnly) {
            attrVer.setName(OnapCommandConstants.INFO_PRODUCT.toUpperCase());
            attrVer.setDescription(OnapCommandConstants.DESCRIPTION);
            attrVer.setScope(OnapCommandResultAttributeScope.SHORT);
            help.getRecords().add(attrVer);
        }

        OnapCommandResultAttribute attrSrv = new OnapCommandResultAttribute();
        attrSrv.setName(OnapCommandConstants.INFO_SERVICE.toUpperCase());
        attrSrv.setDescription(OnapCommandConstants.INFO_SERVICE);
        attrSrv.setScope(OnapCommandResultAttributeScope.SHORT);
        help.getRecords().add(attrSrv);

        OnapCommandResultAttribute attrState = new OnapCommandResultAttribute();
        attrState.setName(OnapCommandConstants.INFO_STATE.toUpperCase());
        attrState.setDescription(OnapCommandConstants.INFO_STATE);
        attrState.setScope(OnapCommandResultAttributeScope.SHORT);
        help.getRecords().add(attrState);


        OnapCommandResultAttribute attrDesc = new OnapCommandResultAttribute();
        attrDesc.setName(OnapCommandConstants.DESCRIPTION.toUpperCase());
        attrDesc.setDescription(OnapCommandConstants.DESCRIPTION);
        attrDesc.setScope(OnapCommandResultAttributeScope.SHORT);
        help.getRecords().add(attrDesc);

        for (String cmdName : isEnabledProductVersionOnly ?
                OnapCommandUtils.sort(this.listCommandsForEnabledProductVersion()) :
                    OnapCommandUtils.sort(this.listCommands())) {
            OnapCommand cmd;
            try {
                if (!isEnabledProductVersionOnly) {
                    String[] cmdVer = cmdName.split(":");
                    cmd = this.get(cmdVer[0], cmdVer[1]);
                    attr.getValues().add(cmdVer[0]);
                    attrVer.getValues().add(cmdVer[1]);
                } else {
                    cmd = this.get(cmdName);
                    attr.getValues().add(cmdName);
                }

                //don't expose system commands for user usage
                //if (cmd.getInfo().getCommandType().name().equalsIgnoreCase(OnapCommandType.SYSTEM.name())) {
                //    continue;
                //}

                attrSrv.getValues().add(cmd.getInfo().getService());
                attrDesc.getValues().add(cmd.getDescription());
                attrState.getValues().add(cmd.getInfo().getState().name());
            } catch (OnapCommandException e) {
                throw new OnapCommandHelpFailed(e);
            }
        }

        try {
            return "\n\nCommands:\n" + help.print() + (isEnabledProductVersionOnly ? "" : "\n" + this.getVersion());
        } catch (OnapCommandException e) {
            throw new OnapCommandHelpFailed(e);
        }
    }

    public List<Map<String, ?>> getTestSuite(String cmd, String product) throws OnapCommandException {
        return OnapCommandDiscoveryUtils.createTestSuite(cmd, product);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
