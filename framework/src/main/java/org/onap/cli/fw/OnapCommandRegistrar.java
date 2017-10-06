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

package org.onap.cli.fw;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.onap.cli.fw.cmd.OnapHttpCommand;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.conf.OnapCommandConfg;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandInvalidRegistration;
import org.onap.cli.fw.error.OnapCommandNotFound;
import org.onap.cli.fw.error.OnapCommandProductVersionInvalid;
import org.onap.cli.fw.error.OnapCommandRegistrationFailed;
import org.onap.cli.fw.error.OnapCommandRegistrationProductInfoMissing;
import org.onap.cli.fw.error.OnapUnsupportedSchemaProfile;
import org.onap.cli.fw.input.cache.OnapCommandParameterCache;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.output.OnapCommandResultAttributeScope;
import org.onap.cli.fw.output.PrintDirection;
import org.onap.cli.fw.output.ResultType;
import org.onap.cli.fw.utils.OnapCommandUtils;
import org.onap.cli.fw.utils.SchemaInfo;


/**
 * Onap Command registrar provides a common place, where every command would get registered automatically when its
 * loaded into JVM.
 *
 */
public class OnapCommandRegistrar {
    private Map<String, Class<? extends OnapCommand>> registry = new HashMap<>();

    private Set<String> availableProductVersions = new HashSet<>();

    private String enabledProductVersion = OnapCommandConfg.getEnabledProductVersion();

    private boolean isInteractiveMode = false;

    private OnapCommandParameterCache paramCache = OnapCommandParameterCache.getInstance();

    public boolean isInteractiveMode() {
        return isInteractiveMode;
    }

    public void setInteractiveMode(boolean isInteractiveMode) {
        this.isInteractiveMode = isInteractiveMode;
    }

    public Map<String, String> getParamCache() {
        return paramCache.getParams(this.getEnabledProductVersion());
    }

    public void addParamCache(String paramName, String paramValue) {
        paramCache.add(this.getEnabledProductVersion(), paramName, paramValue);
    }

    public void removeParamCache(String paramName) {
        paramCache.remove(this.getEnabledProductVersion(), paramName);
    }

    public void setProfile(String profileName) {
        this.paramCache.setProfile(profileName);
    }

    private static OnapCommandRegistrar registrar = null;

    /**
     * Register the command into registrar and throws OnapInvalidCommandRegistration for invalid command.
     *
     * @param name
     *            Command Name
     * @param cmd
     *            Command Class
     * @throws OnapCommandInvalidRegistration
     *             Invalid registration exception
     * @throws OnapCommandRegistrationProductInfoMissing
     */
    public void register(String name, String version, Class<? extends OnapCommand> cmd) throws OnapCommandInvalidRegistration, OnapCommandRegistrationProductInfoMissing {
        if (version == null || version.isEmpty()) {
            throw new OnapCommandRegistrationProductInfoMissing(name);
        }

        this.registry.put(name + ":" + version, cmd);
        this.availableProductVersions.add(version);

    }

    /**
     * Get global registrar.
     *
     * @throws OnapCommandException
     *             exception
     */
    public static OnapCommandRegistrar getRegistrar() throws OnapCommandException {
        if (registrar == null) {
            registrar = new OnapCommandRegistrar();
            registrar.autoDiscoverSchemas();
        }

        return registrar;
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

        for (String cmd: this.registry.keySet()) {
            if (cmd.split(":")[1].equalsIgnoreCase(version)) {
                cmds.add(cmd.split(":")[0]);
            }
        }
        return cmds;
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
     * @throws OnapCommandException
     *             exception
     */
    public List<SchemaInfo> listCommandInfo() throws OnapCommandException {
        return OnapCommandUtils.discoverSchemas();
    }

    /**
     * Get the OnapCommand, which CLI main would use to find the command based on the command name.
     *
     * @param cmdName
     *            Name of command
     * @return OnapCommand
     * @throws OnapCommandException
     *             Exception
     */
    public OnapCommand get(String cmdName) throws OnapCommandException {
        return this.get(cmdName, this.getEnabledProductVersion());
    }

    private OnapCommand get(String cmdName, String version) throws OnapCommandException {
        Class<? extends OnapCommand> cls = registry.get(cmdName + ":" + version);
        //mrkanag: Restrict auth/catalog type commands only available during devMode. in production
        //don't expose the auth type and catalog type commands

        if (cls == null) {
            throw new OnapCommandNotFound(cmdName, version);
        }

        OnapCommand cmd;
        try {
            Constructor<?> constr = cls.getConstructor();
            cmd = (OnapCommand) constr.newInstance();

            String schemaName;
            if (cmd.getClass().equals(OnapHttpCommand.class)) { // NOSONAR
                schemaName = OnapCommandUtils.getSchemaInfo(cmdName, version).getSchemaName();
            } else {
                schemaName = this.getSchemaFileName(cls);
            }
            cmd.initializeSchema(schemaName);
        } catch (OnapCommandException | NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new OnapCommandRegistrationFailed(cmdName, e);
        }

        return cmd;
    }

    private Map<String, Class<OnapCommand>> autoDiscoverCommandPlugins() throws OnapCommandException {
        List<Class<OnapCommand>> cmds = OnapCommandUtils.discoverCommandPlugins();
        Map<String, Class<OnapCommand>> map = new HashMap<>();

        for (Class<OnapCommand> cmd : cmds) {
            if (cmd.isAnnotationPresent(OnapCommandSchema.class)) {
                OnapCommandSchema ano = cmd.getAnnotation(OnapCommandSchema.class);
                map.put(ano.schema(), cmd);
            }
        }

        return map;
    }

    private void autoDiscoverSchemas() throws OnapCommandException {
        List<SchemaInfo> schemas = OnapCommandUtils.discoverOrLoadSchemas();

        Map<String, Class<OnapCommand>> plugins = this.autoDiscoverCommandPlugins();

        for (SchemaInfo schema : schemas) {
            if (schema.isHttp()) {
                this.register(schema.getCmdName(), schema.getProduct(), OnapHttpCommand.class);
            } else if (plugins.containsKey(schema.getSchemaName())) {
                this.register(schema.getCmdName(), schema.getProduct(), plugins.get(schema.getSchemaName()));
            } else {
                throw new OnapUnsupportedSchemaProfile(schema.getSchemaURI());
            }
        }
    }

    private String getSchemaFileName(Class<? extends OnapCommand> cmd) {
        OnapCommandSchema ano = (OnapCommandSchema) cmd.getAnnotation(OnapCommandSchema.class);
        return ano.schema();
    }

    /**
     * Helps to find the Onap CLI version, could be used with --version or -v option.
     *
     * @return string
     */
    public String getVersion() {
        String version = this.getClass().getPackage().getImplementationVersion();
        if (version == null) {
            version = OnapCommandConfg.getVersion();
        }

        String configuredProductVersion = this.getEnabledProductVersion();

        String errorNote = "";
        String usageNote = "\n\nTo enable a product version, use one of following methods:"
                + "\n 1. set env variable OPEN_CLI_PRODUCT_IN_USE"
                + "\n 2. set cli.product.version in open-cli.properties"
                + "\n 3. in interactive mode, use the directive 'use <product version>'\n";

        if (!this.availableProductVersions.contains(configuredProductVersion)) {
            errorNote = "** CUATION: Please configure the enabled product version to use one of " + this.availableProductVersions.toString() + ".";

        }
        return "CLI version               : " + version + "\n"
                + "Available product versions: " + this.availableProductVersions.toString() + "\n"
                + "Enabled product version   : " + configuredProductVersion + "\n" +
                errorNote + usageNote;
    }

    /**
     * Provides the help message in tabular format for all commands registered in this registrar.
     *
     * @return string
     * @throws OnapCommandHelpFailed
     *             Help cmd failed
     */
    public String getHelp() throws OnapCommandHelpFailed {
        return this.getHelp(false);
    }

    public String getHelpForEnabledProductVersion() throws OnapCommandHelpFailed {
        return this.getHelp(true);
    }

    private String getHelp(boolean isEnabledProductVersionOnly) throws OnapCommandHelpFailed {
        OnapCommandResult help = new OnapCommandResult();
        help.setType(ResultType.TABLE);
        help.setPrintDirection(PrintDirection.LANDSCAPE);

        OnapCommandResultAttribute attr = new OnapCommandResultAttribute();
        attr.setName(Constants.NAME.toUpperCase());
        attr.setDescription(Constants.DESCRIPTION);
        attr.setScope(OnapCommandResultAttributeScope.SHORT);
        help.getRecords().add(attr);

        OnapCommandResultAttribute attrVer = new OnapCommandResultAttribute();
        if (!isEnabledProductVersionOnly) {
            attrVer.setName(Constants.INFO_PRODUCT.toUpperCase());
            attrVer.setDescription(Constants.DESCRIPTION);
            attrVer.setScope(OnapCommandResultAttributeScope.SHORT);
            help.getRecords().add(attrVer);
        }

        OnapCommandResultAttribute attrSrv = new OnapCommandResultAttribute();
        attrSrv.setName(Constants.SERVICE.toUpperCase());
        attrSrv.setDescription(Constants.SERVICE);
        attrSrv.setScope(OnapCommandResultAttributeScope.SHORT);
        help.getRecords().add(attrSrv);

        OnapCommandResultAttribute attrDesc = new OnapCommandResultAttribute();
        attrDesc.setName(Constants.DESCRIPTION.toUpperCase());
        attrDesc.setDescription(Constants.DESCRIPTION);
        attrDesc.setScope(OnapCommandResultAttributeScope.SHORT);
        help.getRecords().add(attrDesc);

        for (String cmdName : isEnabledProductVersionOnly ? OnapCommandUtils.sort(this.listCommandsForEnabledProductVersion()) : OnapCommandUtils.sort(this.listCommands())) {
            OnapCommand cmd;
            try {
                if (!isEnabledProductVersionOnly) {
                    String []cmdVer = cmdName.split(":");
                    cmd = this.get(cmdVer[0], cmdVer[1]);
                    attr.getValues().add(cmdVer[0]);
                    attrVer.getValues().add(cmdVer[1]);
                } else {
                    cmd = this.get(cmdName);
                    attr.getValues().add(cmdName);
                }

                attrSrv.getValues().add(cmd.printVersion());
                attrDesc.getValues().add(cmd.getDescription());
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
}
