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

package org.onap.cli.fw.output.print;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.onap.cli.fw.error.OnapCommandOutputPrintingFailed;
import org.onap.cli.fw.output.OnapCommandPrintDirection;

import com.google.gson.JsonParser;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
/**
 * Oclip Command Table print.
 *
 */
public class OnapCommandPrint {


    public static final int MAX_COLUMN_LENGTH = 50;

    private OnapCommandPrintDirection direction;

    private Map<String, List<String>> data = new LinkedHashMap<>();

    private boolean printTitle = true;

    public OnapCommandPrintDirection getDirection() {
        return direction;
    }

    public void setDirection(OnapCommandPrintDirection direction) {
        this.direction = direction;
    }

    public void addColumn(String header, List<String> data) {
        this.data.put(header, data);
    }

    /**
     * Get column.
     *
     * @param header
     *            string
     * @return list
     */
    public List<String> getColumn(String header) {
        return this.data.computeIfAbsent(header, k -> new ArrayList<String>());
    }

    public boolean isPrintTitle() {
        return printTitle;
    }

    public void setPrintTitle(boolean printTitle) {
        this.printTitle = printTitle;
    }

    private int findMaxRows() {
        int max = 1;
        if (!this.isPrintTitle()) {
            max = 0;
        }
        for (List<String> cols : this.data.values()) {
            if (cols != null && max < cols.size()) {
                max = cols.size();
            }
        }

        return max;
    }

    /**
     * Helps to form the rows from columns.
     *
     * @param isNormalize
     *            boolean
     * @return +--------------+-----------+-----------------------------+ | header1 | header 2 | header 3 |
     *         +--------------+-----------+-----------------------------+ | v1 | List[line| v 3 | | | 1, line2]| |
     *         +--------------+-----------+-----------------------------+ | null | yyyyyy 2 | xxxxxx 3 |
     *         +--------------+-----------+-----------------------------+
     */
    private List<List<Object>> formRows(boolean isNormalize) {
        List<List<Object>> rows = new ArrayList<>();

        // add title
        if (this.isPrintTitle()) {
            List<Object> list = new ArrayList<>();
            for (String key : this.data.keySet()) {
                if (isNormalize && key != null && key.length() > MAX_COLUMN_LENGTH) {
                    list.add(splitIntoList(key, MAX_COLUMN_LENGTH));
                } else {
                    list.add(key);
                }
            }
            rows.add(list);
        }

        // form row
        for (int i = 0; i < this.findMaxRows(); i++) {
            List<Object> row = new ArrayList<>();
            for (List<String> cols : this.data.values()) {
                if (cols != null && cols.size() > i) {
                    String value = cols.get(i);
                    // split the cell into multiple sub rows
                    if (isNormalize && value != null && value.length() > MAX_COLUMN_LENGTH) {
                        row.add(splitIntoList(value, MAX_COLUMN_LENGTH));
                    } else {
                        // store as string (one entry)
                        row.add(value);
                    }
                } else {
                    // no value exist for this column
                    row.add(null);
                }
            }
            rows.add(row);
        }

        return rows;
    }

    /**
     * Splits big strings into list of strings based on maxCharInLine size.
     *
     * @param input
     *            input string
     * @param maxCharInLine
     *            max length
     * @return list of strings
     */
    public List<String> splitIntoList(String input, int maxCharInLine) {

        String inp = input;

        if (inp == null || "".equals(inp) || maxCharInLine <= 0) {
            return Collections.emptyList();
        }
        // new line is converted to space char
        if (inp.contains("\n")) {
            inp = inp.replaceAll("\n", "");
        }

        StringTokenizer tok = new StringTokenizer(inp, " ");
        StringBuilder output = new StringBuilder(inp.length());
        int lineLen = 0;
        while (tok.hasMoreTokens()) {
            String word = tok.nextToken();

            while (word.length() >= maxCharInLine) {
                output.append(word.substring(0, maxCharInLine - lineLen) + "\n");
                word = word.substring(maxCharInLine - lineLen);
                lineLen = 0;
            }

            if (lineLen + word.length() >= maxCharInLine) {
                output.append("\n");
                lineLen = 0;
            }
            output.append(word + " ");

            lineLen += word.length() + 1;
        }
        String[] strArray = output.toString().split("\n");

        return Arrays.asList(strArray);
    }

    /**
     * Helps to print table.
     *
     * @param printSeparator
     *            Prints with line separator
     * @return +--------------+-----------+-----------------------------+ | header1 | header 2 | header 3 |
     *         +--------------+-----------+-----------------------------+ | v1 | line 1 | v 3 | | | line 2 | |
     *         +--------------+-----------+-----------------------------+ | | yyyyyy 2 | xxxxxx 3 |
     *         +--------------+-----------+-----------------------------+
     */
    public String printTable(boolean printSeparator) {
        List<List<Object>> rows = this.formRows(true);
        TableGenerator table = new TableGenerator();
        return table.generateTable(rows, printSeparator);
    }

    /**
     * Print output in csv format.
     *
     * @return string
     * @throws OnapCommandOutputPrintingFailed
     *             exception
     */
    public String printCsv() throws OnapCommandOutputPrintingFailed {
        CSVFormat formattor = CSVFormat.DEFAULT.withRecordSeparator(System.getProperty("line.separator"));

        try (StringWriter writer = new StringWriter();
             CSVPrinter printer = new CSVPrinter(writer, formattor);) {

            List<List<Object>> rows = this.formRows(false);

            for (int i = 0; i < this.findMaxRows(); i++) {
                printer.printRecord(rows.get(i));
            }

            return writer.toString();
        } catch (IOException e) {
            throw new OnapCommandOutputPrintingFailed(e);
        }
    }

    public Object getJsonNodeOrString(String value) {
        try {
            return (JSONObject) JSONValue.parse(value);
        } catch (Exception e) {
            return value;
        }
    }

    public String printJson() {
        List<List<Object>> rows = this.formRows(false);

        if (this.direction.equals(OnapCommandPrintDirection.PORTRAIT)) {
            JSONObject result = new JSONObject();
            for (int i=1; i<rows.size(); i++) {
                if (rows.get(i).get(1) != null)
                    result.put(rows.get(i).get(0).toString(), this.getJsonNodeOrString(rows.get(i).get(1).toString()));
            }
            return result.toJSONString();
        } else {
            JSONArray array = new JSONArray();

            //skip first row title
            List<Object> titleRow = rows.get(0);

            for (int i=1; i<rows.size(); i++) {
                JSONObject rowO = new JSONObject();

                for (int j=0; j<titleRow.size(); j++) {
                    if (rows.get(i).get(j) != null)
                        rowO.put(titleRow.get(j).toString(), this.getJsonNodeOrString(rows.get(i).get(j).toString()));
                }

                array.add(rowO);
            }
            try {
                return new JsonParser().parse(array.toJSONString()).toString();
            } catch (Exception e) { // NOSONAR
                // TODO Auto-generated catch block
                return array.toJSONString();
            }

        }
    }

    /*
        required vulnerable fix
        jackson-dataformat-yaml:YAMLMapper is a sub component of jackson-databind
        jackson-databind is replaced with gson
        JIRA: CLI-251
     */
    public String printYaml() throws OnapCommandOutputPrintingFailed {
     /*   try {
            return new YAMLMapper().writeValueAsString(new ObjectMapper().readTree(this.printJson()));
        } catch (IOException  e) {
            throw new OnapCommandOutputPrintingFailed(e);  // NOSONAR
        }
     */
     return "";
    }
}
