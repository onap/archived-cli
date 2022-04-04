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

package org.onap.cli.fw.output;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandOutputFormatNotsupported;
import org.onap.cli.fw.error.OnapCommandOutputPrintingFailed;
import org.onap.cli.fw.output.print.OnapCommandPrint;

/**
 * Oclip Command result holds the final output of the command.
 *
 */
public class OnapCommandResult {

    /*
     * if type=JSON, then JSON response of the command from back-end Oclip service, by default all the command would
     * set this value once the back-end call returns, which would be useful to print the output in JSON format, returned
     * from the back-end service.
     *
     * if type=TEXT, then it holds the result in text format such as help message
     */
    private Object output = "";

    /*
     * Type requested by user
     */
    private OnapCommandResultType type = OnapCommandResultType.TABLE;

    /*
     * Scope requested by user
     */
    private OnapCommandResultAttributeScope scope = OnapCommandResultAttributeScope.SHORT;

    /*
     * if type=TABLE, then List of result records, which could be printed on the CLI console, loaded from schema file
     */
    private List<OnapCommandResultAttribute> records = new ArrayList<>();

    /*
     * Print horizontally or vertically, Mostly for show command, horizontal table while for list commands , it will be
     * vertically printed. Respective command should set appropriately.
     *
     * loaded from schema file
     */
    private OnapCommandPrintDirection printDirection = OnapCommandPrintDirection.LANDSCAPE;

    private String debugInfo = "";

    /**
     * Requested by user.
     */
    private boolean includeTitle = true;

    /**
     * Requested by user.
     */
    private boolean includeSeparator = true;

    /**
     * Requested by user.
     */
    private boolean isDebug = false;

    /**
     * Command passed/failed
     * @return
     */

    private boolean passed = true;

    public OnapCommandPrintDirection getPrintDirection() {
        return printDirection;
    }

    public void setPrintDirection(OnapCommandPrintDirection printDirection) {
        this.printDirection = printDirection;
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }

    public List<OnapCommandResultAttribute> getRecords() {
        return records;
    }

    public void setRecords(List<OnapCommandResultAttribute> records) {
        this.records = records;
    }

    /**
     * Record mapping.
     *
     * @return attributes
     */
    public Map<String, OnapCommandResultAttribute> getRecordsMap() {
        Map<String, OnapCommandResultAttribute> recordMap = new HashMap<>();

        for (OnapCommandResultAttribute data : this.getRecords()) {
            recordMap.put(data.getName(), data);
        }

        return recordMap;
    }

    public OnapCommandResultType getType() {
        return type;
    }

    public void setType(OnapCommandResultType type) {
        this.type = type;
    }

    public OnapCommandResultAttributeScope getScope() {
        return scope;
    }

    public void setScope(OnapCommandResultAttributeScope scope) {
        this.scope = scope;
    }

    public boolean isIncludeTitle() {
        return includeTitle;
    }

    public void setIncludeTitle(boolean includeTitle) {
        this.includeTitle = includeTitle;
    }

    public boolean isIncludeSeparator() {
        return includeSeparator;
    }

    public void setIncludeSeparator(boolean includeSeparator) {
        this.includeSeparator = includeSeparator;
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    public void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }

    public int getNumberOfRows() {
        int noOfRecords = 0;

        for (OnapCommandResultAttribute cols : this.records) {
            if (cols != null && noOfRecords < cols.getValues().size()) {
                noOfRecords = cols.getValues().size();
            }
        }

        return noOfRecords;
    }

    public OnapCommandPrint createAndLoadPrint() {
        OnapCommandPrint print = new OnapCommandPrint();
        print.setPrintTitle(this.isIncludeTitle());
        print.setDirection(this.printDirection);

        if (!this.getRecords().isEmpty()) {
            if (this.getPrintDirection().equals(OnapCommandPrintDirection.LANDSCAPE)) {
                for (OnapCommandResultAttribute data : this.getScopedRecords()) {
                    print.addColumn(data.getName(), data.getValues());
                }
            } else {
                // Add property column
                OnapCommandResultAttribute prp = new OnapCommandResultAttribute();
                prp.setName(OnapCommandConstants.PORTRAINT_COLUMN_NAME_PROPERTY);
                prp.setScope(OnapCommandResultAttributeScope.SHORT);
                // Add value column
                OnapCommandResultAttribute val = new OnapCommandResultAttribute();
                val.setName(OnapCommandConstants.PORTRAINT_COLUMN_NAME_VALUE);
                val.setScope(OnapCommandResultAttributeScope.SHORT);

                for (OnapCommandResultAttribute data : this.getScopedRecords()) {
                    prp.getValues().add(data.getName());
                    if (data.getValues().size() == 1) {
                        val.getValues().add(data.getValues().get(0));
                    } else {
                        val.getValues().add(data.getValues().toString());
                    }
                }

                print.addColumn(prp.getName(), prp.getValues());
                print.addColumn(val.getName(), val.getValues());
            }
        }
        return print;
    }

    /**
     * Helps to print the result based on the type.
     *
     * @return string
     * @throws OnapCommandOutputFormatNotsupported
     *             excpetion
     * @throws OnapCommandOutputPrintingFailed
     *             exception
     */
    public String print() throws OnapCommandException {
        if (this.getType().equals(OnapCommandResultType.TEXT)) {
             return this.getOutput().toString();
        }

        OnapCommandPrint print = createAndLoadPrint();

        if (this.getType().equals(OnapCommandResultType.JSON)) {
            return print.printJson();
        } else if (this.getType().equals(OnapCommandResultType.TABLE)) {
            return print.printTable(this.isIncludeSeparator());
        } else if (this.getType().equals(OnapCommandResultType.CSV)) {
            return print.printCsv();
        } else if (this.getType().equals(OnapCommandResultType.YAML)) {
            return print.printYaml();
        }

        throw new OnapCommandOutputFormatNotsupported(this.getType().name());
    }

    private List<OnapCommandResultAttribute> getScopedRecords() {
        List<OnapCommandResultAttribute> recordList = new ArrayList<>();
        for (OnapCommandResultAttribute data : this.getRecords()) {
            if (data.getScope().ordinal() > this.getScope().ordinal()) {
                continue;
            }
            recordList.add(data);
        }

        return recordList;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
