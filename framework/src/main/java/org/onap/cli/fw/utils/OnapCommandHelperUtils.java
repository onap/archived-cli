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

import static org.onap.cli.fw.conf.OnapCommandConstants.DESCRIPTION;
import static org.onap.cli.fw.conf.OnapCommandConstants.NAME;

import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.OnapCommandParameterType;
import org.onap.cli.fw.output.OnapCommandPrintDirection;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.output.OnapCommandResultAttributeScope;
import org.onap.cli.fw.output.OnapCommandResultType;

public class OnapCommandHelperUtils {

    /**
     * Returns the build time from manifest.mf
     */
    public static String findLastBuildTime() {
        String impBuildDate = "";
        String path = OnapCommandUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        try(JarFile jar = new JarFile(path)){
            Manifest manifest = jar.getManifest();

            Attributes attributes = manifest.getMainAttributes();

            impBuildDate = attributes.getValue("Build-Time");
        }
        catch (IOException e)  // NOSONAR
        {
            //Ignore it as it will never occur
        }

        return impBuildDate;
    }

    /**
     * Returns Help.
     *
     * @param cmd
     *            OnapCommand
     * @return help string
     * @throws OnapCommandHelpFailed
     *             help failed exception
     */
    public static String help(OnapCommand cmd) throws OnapCommandHelpFailed {

        String help = "usage: oclip " + cmd.getName();

        // Add description
        help += "\n\n" + cmd.getDescription();

        // Add info
        help += "\n\nProduct: " + cmd.getInfo().getProduct();
        help += "\nService: " + cmd.getInfo().getService();
        help += "\nAuthor: " + cmd.getInfo().getAuthor();

        // Add whole command
        StringBuilder commandOptions = new StringBuilder();

        // Add parameters
        OnapCommandResult paramTable = new OnapCommandResult();
        paramTable.setPrintDirection(OnapCommandPrintDirection.LANDSCAPE);
        paramTable.setType(OnapCommandResultType.TABLE);
        paramTable.setIncludeTitle(false);
        paramTable.setIncludeSeparator(false);

        OnapCommandResultAttribute attrName = new OnapCommandResultAttribute();
        attrName.setName(NAME);
        attrName.setDescription(NAME);
        attrName.setScope(OnapCommandResultAttributeScope.SHORT);
        paramTable.getRecords().add(attrName);

        OnapCommandResultAttribute attrDescription = new OnapCommandResultAttribute();
        attrDescription.setName(DESCRIPTION);
        attrDescription.setDescription(DESCRIPTION);
        attrDescription.setScope(OnapCommandResultAttributeScope.SHORT);
        paramTable.getRecords().add(attrDescription);

        int newLineOptions = 0;
        for (OnapCommandParameter param : cmd.getParameters()) {
            if (!param.isInclude()) {
                continue;
            }

            // First column Option or positional args
            String optFirstCol;
            if (newLineOptions == 3) {
                newLineOptions = 0;
                commandOptions.append("\n");
            }

            if (param.getShortOption() != null || param.getLongOption() != null) {
                optFirstCol = OnapCommandParameter.printShortOption(param.getShortOption()) + " | "
                        + OnapCommandParameter.printLongOption(param.getLongOption());
                commandOptions.append(" [" + optFirstCol + "]");
            } else {
                optFirstCol = param.getName();
                commandOptions.append(" <" + optFirstCol + ">");
            }

            newLineOptions++;

            attrName.getValues().add(" " + optFirstCol);

            // Second column description
            String optSecondCol = param.getDescription().trim();
            if (!optSecondCol.endsWith(".")) {
                optSecondCol += ".";
            }
            optSecondCol += " It is of type " + param.getParameterType().name() + ".";

            if (param.getParameterType().equals(OnapCommandParameterType.JSON)
                    || param.getParameterType().equals(OnapCommandParameterType.YAML)) {
                optSecondCol += " It's recommended to input the complete path of the file, which is having the value for it.";
            }
            if (param.isOptional()) {
                optSecondCol += " It is optional.";
            }

            String defaultMsg = " By default, it is ";
            if (param.isRawDefaultValueAnEnv()) {
                optSecondCol += defaultMsg + "read from environment variable " + param.getEnvVarNameFromrRawDefaultValue()
                        + ".";
            } else if (param.getDefaultValue() != null && param.getDefaultValue().toString().isEmpty()) {
                optSecondCol += defaultMsg + param.getDefaultValue() + ".";
            }

            if (param.isSecured()) {
                optSecondCol += " Secured.";
            }
            // (mrkanag) Add help msg for reading default value from env
            attrDescription.getValues().add(optSecondCol);
        }

        try {
            help += "\n\nOptions::\n\n" + commandOptions.toString() + "\n\nwhere::\n\n" + paramTable.print();
        } catch (OnapCommandException e) {
            throw new OnapCommandHelpFailed(e);
        }

        // Add results
        OnapCommandResult resultTable = new OnapCommandResult();
        resultTable.setPrintDirection(OnapCommandPrintDirection.PORTRAIT);
        resultTable.setType(OnapCommandResultType.TABLE);
        resultTable.setIncludeTitle(false);
        resultTable.setIncludeSeparator(false);

        for (OnapCommandResultAttribute attr : cmd.getResult().getRecords()) {
            OnapCommandResultAttribute attrHelp = new OnapCommandResultAttribute();
            attrHelp.setName(" " + attr.getName());
            attrHelp.setDescription(attr.getDescription());
            String msg = attr.getDescription() + " and is of type " + attr.getType().name() + ".";
            if (attr.isSecured()) {
                msg += " It is secured.";
            }
            attrHelp.getValues().add(msg);
            attrHelp.setType(attr.getType());
            resultTable.getRecords().add(attrHelp);
        }

        if (cmd.getResult().getRecords().size() > 0) {
            try {
                help += "\n\nResults::\n\n" + resultTable.print();
            } catch (OnapCommandException e) {
                throw new OnapCommandHelpFailed(e);
            }
        }

        // Error
        help += "\n\nError::\n\n On error, it prints <STATUS CODE>::<ERROR CODE>::<ERROR MESSAGE>\n";
        return help;
    }

}
