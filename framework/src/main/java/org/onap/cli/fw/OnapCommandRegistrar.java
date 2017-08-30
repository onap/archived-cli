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
import org.onap.cli.fw.error.OnapCommandRegistrationVersionMissing;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.output.OnapCommandResultAttributeScope;
import org.onap.cli.fw.output.PrintDirection;
import org.onap.cli.fw.output.ResultType;
import org.onap.cli.fw.utils.ExternalSchema;
import org.onap.cli.fw.utils.OnapCommandUtils;

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

    public boolean isInteractiveMode() {
        return isInteractiveMode;
    }

    public void setInteractiveMode(boolean isInteractiveMode) {
        this.isInteractiveMode = isInteractiveMode;
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
     * @throws OnapCommandRegistrationVersionMissing
     */
    public void register(String name, String version, Class<? extends OnapCommand> cmd) throws OnapCommandInvalidRegistration, OnapCommandRegistrationVersionMissing {
        if (version == null || version.isEmpty()) {
            throw new OnapCommandRegistrationVersionMissing(name);
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
            registrar.autoDiscover();
            registrar.autoDiscoverHttpSchemas();
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
    public List<ExternalSchema> listCommandInfo() throws OnapCommandException {
        return OnapCommandUtils.findAllExternalSchemas();
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
        if (cls == null) {
               throw new OnapCommandNotFound(cmdName, version);
        }

        OnapCommand cmd;
        try {
            Constructor<?> constr = cls.getConstructor();
            cmd = (OnapCommand) constr.newInstance();

            String schemaName;
            if (cmd.getClass().equals(OnapHttpCommand.class)) { // NOSONAR
                schemaName = OnapCommandUtils.loadExternalSchemaFromJson(cmdName, version).getSchemaName();
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

    private void autoDiscover() throws OnapCommandInvalidRegistration, OnapCommandRegistrationVersionMissing {
        List<Class<OnapCommand>> cmds = OnapCommandUtils.findOnapCommands();

        for (Class<OnapCommand> cmd : cmds) {
            if (cmd.isAnnotationPresent(OnapCommandSchema.class)) {
                OnapCommandSchema ano = cmd.getAnnotation(OnapCommandSchema.class);
                this.register(ano.name(), ano.version(), cmd);
            }
        }
    }

    private void autoDiscoverHttpSchemas() throws OnapCommandException {
        List<ExternalSchema> schemas = OnapCommandUtils.loadExternalSchemasFromJson();
        for (ExternalSchema schema : schemas) {
            if (schema.isHttp()) {
                this.register(schema.getCmdName(), schema.getCmdVersion(), OnapHttpCommand.class);
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

        String configuredProductVersion = OnapCommandConfg.getEnabledProductVersion();

        String errorNote = "";
        if (!this.availableProductVersions.contains(configuredProductVersion)) {
            errorNote = "** CUATION: Please configure the enabled product version to use one of " + this.availableProductVersions.toString() +
                        ".\nTo enable a product version, use one of following methods:\n\t 1. set env variable CLI_PRODUCT_VERSION"
                        + "\n\t 2. set cli.product.version in onap.properties \n\t 3. in interactive mode, use the directive 'use <product version>'";
        }
        return "CLI version               : " + version + "\n"
                + "Available product versions: " + this.availableProductVersions.toString() + "\n"
                + "Enabled product version   : " + configuredProductVersion + "\n" +
                errorNote;
    }

    /**
     * Provides the help message in tabular format for all commands registered in this registrar.
     *
     * @return string
     * @throws OnapCommandHelpFailed
     *             Help cmd failed
     */
    public String getHelp() throws OnapCommandHelpFailed {
        OnapCommandResult help = new OnapCommandResult();
        help.setType(ResultType.TABLE);
        help.setPrintDirection(PrintDirection.LANDSCAPE);

        OnapCommandResultAttribute attr = new OnapCommandResultAttribute();
        attr.setName(Constants.NAME.toUpperCase());
        attr.setDescription(Constants.DESCRIPTION);
        attr.setScope(OnapCommandResultAttributeScope.SHORT);
        help.getRecords().add(attr);

        OnapCommandResultAttribute attrVer = new OnapCommandResultAttribute();
        attrVer.setName(Constants.PRODUCT_VERSION.toUpperCase());
        attrVer.setDescription(Constants.DESCRIPTION);
        attrVer.setScope(OnapCommandResultAttributeScope.SHORT);
        help.getRecords().add(attrVer);

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

        for (String cmdName : OnapCommandUtils.sort(this.listCommands())) {
            OnapCommand cmd;
            try {
                String []cmdVer = cmdName.split(":");
                cmd = this.get(cmdVer[0], cmdVer[1]);
                attr.getValues().add(cmdVer[0]);
                attrVer.getValues().add(cmdVer[1]);
                attrSrv.getValues().add(cmd.printVersion());
                attrDesc.getValues().add(cmd.getDescription());
            } catch (OnapCommandException e) {
                throw new OnapCommandHelpFailed(e);
            }
        }

        try {
            return "\n\nOnap sub-commands:\n" + help.print() + "\n" + this.getVersion();
        } catch (OnapCommandException e) {
            throw new OnapCommandHelpFailed(e);
        }
    }
}
