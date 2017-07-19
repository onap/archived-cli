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

import org.onap.cli.fw.cmd.OnapHttpCommand;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.conf.OnapCommandConfg;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandInvalidRegistration;
import org.onap.cli.fw.error.OnapCommandNotFound;
import org.onap.cli.fw.error.OnapCommandRegistrationFailed;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.output.OnapCommandResultAttributeScope;
import org.onap.cli.fw.output.PrintDirection;
import org.onap.cli.fw.output.ResultType;
import org.onap.cli.fw.utils.ExternalSchema;
import org.onap.cli.fw.utils.OnapCommandUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Onap Command registrar provides a common place, where every command would get registered automatically when its
 * loaded into JVM.
 *
 */
public class OnapCommandRegistrar {
    /*
     * static { //Start the AOP for logging new OnapCommandLogger(); }
     */
    private Map<String, Class<? extends OnapCommand>> registry = new HashMap<>();

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
     */
    public void register(String name, Class<? extends OnapCommand> cmd) throws OnapCommandInvalidRegistration {
        this.registry.put(name, cmd);
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
     * Returns map of command to schema.
     *
     * @return map
     * @throws OnapCommandException
     *             exception
     */
    public Map<String, String> getAllCommandToSchemaMap() throws OnapCommandException {
        Map<String, String> map = new HashMap<>();
        List<ExternalSchema> schemas = OnapCommandUtils.findAllExternalSchemas();
        if (schemas != null) {
            for (ExternalSchema schema : schemas) {
                map.put(schema.getCmdName(), schema.getSchemaName());
            }
        }
        if (this.registry != null) {
            for (String cmd : this.registry.keySet()) {
                if (!map.containsKey(cmd) && registry.get(cmd) != null) {
                    map.put(cmd, this.getSchemaFileName(registry.get(cmd)));
                }
            }
        }

        return map;
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
        OnapCommand cmd;
        Class<? extends OnapCommand> cls = registry.get(cmdName);
        if (cls == null) {
            throw new OnapCommandNotFound(cmdName);
        }

        try {
            Constructor<?> constr = cls.getConstructor();
            cmd = (OnapCommand) constr.newInstance();

            String schemaName;
            if (cmd.getClass().equals(OnapHttpCommand.class)) { // NOSONAR
                schemaName = OnapCommandUtils.loadExternalSchemaFromJson(cmdName).getSchemaName();
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

    private void autoDiscover() throws OnapCommandInvalidRegistration {
        List<Class<OnapCommand>> cmds = OnapCommandUtils.findOnapCommands();

        for (Class<OnapCommand> cmd : cmds) {
            if (cmd.isAnnotationPresent(OnapCommandSchema.class)) {
                OnapCommandSchema ano = cmd.getAnnotation(OnapCommandSchema.class);
                this.register(ano.name(), cmd);
            }
        }
    }

    private void autoDiscoverHttpSchemas() throws OnapCommandException {
        List<ExternalSchema> schemas = OnapCommandUtils.loadExternalSchemasFromJson();
        for (ExternalSchema schema : schemas) {
            this.register(schema.getCmdName(), OnapHttpCommand.class);
        }
    }

    private String getSchemaFileName(Class<? extends OnapCommand> cmd) {
        OnapCommandSchema ano = (OnapCommandSchema) cmd.getAnnotation(OnapCommandSchema.class);
        if (ano.schema().isEmpty()) {
            return "onap-" + ano.name() + "-schema.yaml";
        }
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
        return version;
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
                cmd = this.get(cmdName);
            } catch (OnapCommandException e) {
                throw new OnapCommandHelpFailed(e);
            }

            attr.getValues().add(cmd.getName());
            attrSrv.getValues().add(cmd.printVersion());
            attrDesc.getValues().add(cmd.getDescription());
        }

        try {
            return "\n\nOnap sub-commands:\n" + help.print();
        } catch (OnapCommandException e) {
            throw new OnapCommandHelpFailed(e);
        }
    }
}
