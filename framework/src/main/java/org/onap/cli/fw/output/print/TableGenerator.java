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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Printing Command result in Table format.
 *
 */
public class TableGenerator {

    private static final int PADDING_SIZE = 1;
    private static final String NEW_LINE = "\n";
    private static final String TABLE_JOINT_SYMBOL = "+";
    private static final String TABLE_V_SPLIT_SYMBOL = "|";
    private static final String TABLE_H_SPLIT_SYMBOL = "-";

    /**
     * Generate list of rows into table format.
     *
     * @param rowsList
     *            list of rows
     * @param printSeparator
     *            boolean
     * @return stringBuilder in tabular format
     */
    public String generateTable(List<List<Object>> rowsList, boolean printSeparator) {
        StringBuilder stringBuilder = new StringBuilder();

        if (rowsList.isEmpty()) {
            return stringBuilder.toString();
        }

        Map<Integer, Integer> columnMaxWidthMapping = getMaximumWidhtofColumns(rowsList);
        Map<Integer, Integer> rowMaxWidthMapping = getMaximumWidhtofRows(rowsList);

        if (printSeparator) {
            createRowLine(stringBuilder, rowsList.get(0).size(), columnMaxWidthMapping);
            stringBuilder.append(NEW_LINE);
        }

        for (int rowIndex = 0; rowIndex < rowsList.size(); rowIndex++) {

            List<Object> row = rowsList.get(rowIndex);
            int splitRowSize = rowMaxWidthMapping.get(rowIndex);
            if (splitRowSize > 1) {
                for (int cellIndex = 0; cellIndex < row.size(); cellIndex++) {
                    Object cell = row.get(cellIndex);
                    String finalCell;
                    if (cell != null) {
                        if (cell instanceof String) {
                            finalCell = (String) cell;
                        } else {
                            finalCell = ((List<String>) cell).get(0);
                        }
                        fillCell(stringBuilder, finalCell, cellIndex, columnMaxWidthMapping, printSeparator);
                    }
                }

                stringBuilder.append(NEW_LINE);

                for (int splitCellIndex = 1; splitCellIndex < splitRowSize; splitCellIndex++) {
                    for (int cellIndex = 0; cellIndex < row.size(); cellIndex++) {
                        Object cell = row.get(cellIndex);
                        String finalCell = "";
                        if (cell instanceof List) {
                            List<String> list = (List<String>) cell;
                            if (splitCellIndex < list.size()) {
                                finalCell = list.get(splitCellIndex);
                            }
                        }
                        fillCell(stringBuilder, finalCell, cellIndex, columnMaxWidthMapping, printSeparator);
                    }

                    stringBuilder.append(NEW_LINE);
                }

            } else {
                for (int cellIndex = 0; cellIndex < row.size(); cellIndex++) {
                    fillCell(stringBuilder, (String) row.get(cellIndex), cellIndex, columnMaxWidthMapping,
                            printSeparator);
                }
                stringBuilder.append(NEW_LINE);
            }

            if (printSeparator) {
                createRowLine(stringBuilder, rowsList.get(0).size(), columnMaxWidthMapping);
                stringBuilder.append(NEW_LINE);
            }

        }
        return stringBuilder.toString();
    }

    /**
     * Fill required space.
     *
     * @param stringBuilder
     *            table
     * @param length
     *            No. of spaces
     */
    private void fillSpace(StringBuilder stringBuilder, int length) {
        for (int i = 0; i < length; i++) {
            stringBuilder.append(" ");
        }
    }

    /**
     * Create a new row line.
     *
     * @param stringBuilder
     *            table
     * @param headersListSize
     *            No. of colums
     * @param columnMaxWidthMapping
     *            mapping of columns with cell width
     */
    private void createRowLine(StringBuilder stringBuilder, int headersListSize,
            Map<Integer, Integer> columnMaxWidthMapping) {
        for (int i = 0; i < headersListSize; i++) {
            if (i == 0) {
                stringBuilder.append(TABLE_JOINT_SYMBOL);
            }

            for (int j = 0; j < columnMaxWidthMapping.get(i) + PADDING_SIZE * 2; j++) {
                stringBuilder.append(TABLE_H_SPLIT_SYMBOL);
            }
            stringBuilder.append(TABLE_JOINT_SYMBOL);
        }
    }

    /**
     * Get max width of columns.
     *
     * @param rowsList
     *            list of rows
     * @return mapping of column and max cell width
     */
    private Map<Integer, Integer> getMaximumWidhtofColumns(List<List<Object>> rowsList) {
        Map<Integer, Integer> columnMaxWidthMapping = new HashMap<>();
        if (rowsList == null || rowsList.isEmpty()) {
            return new HashMap<>();
        }
        for (int columnIndex = 0; columnIndex < rowsList.get(0).size(); columnIndex++) {
            columnMaxWidthMapping.put(columnIndex, 0);
        }

        for (List<Object> row : rowsList) {
            updateColWidth(row, columnMaxWidthMapping);
        }

        for (int columnIndex = 0; columnIndex < rowsList.get(0).size(); columnIndex++) {

            if (columnMaxWidthMapping.get(columnIndex) % 2 != 0) {
                columnMaxWidthMapping.put(columnIndex, columnMaxWidthMapping.get(columnIndex) + 1);
            }
        }

        return columnMaxWidthMapping;
    }

    /**
     * update the column width.
     *
     * @param row
     *            list of values
     * @param columnMaxWidthMapping
     *            mapping of column and max cell width
     */
    private void updateColWidth(List<Object> row, Map<Integer, Integer> columnMaxWidthMapping) {
        for (int columnIndex = 0; columnIndex < row.size(); columnIndex++) {
            Object obj = row.get(columnIndex);
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.length() > columnMaxWidthMapping.get(columnIndex)) {
                    columnMaxWidthMapping.put(columnIndex, str.length());
                }
            } else if (obj instanceof List) {
                List<String> list = (List<String>) obj;

                int maxLength = list.get(0).length();

                for (String str : list) {
                    if (str.length() > maxLength) {
                        maxLength = str.length();
                    }
                }

                if (maxLength > columnMaxWidthMapping.get(columnIndex)) {
                    columnMaxWidthMapping.put(columnIndex, maxLength);
                }

            }

        }
    }

    /**
     * Get max width of rows.
     *
     * @param rowsList
     *            list of rows
     * @return map of rows and max width
     */
    private Map<Integer, Integer> getMaximumWidhtofRows(List<List<Object>> rowsList) {
        Map<Integer, Integer> rowMaxWidthMapping = new HashMap<>();

        for (int rowIndex = 0; rowIndex < rowsList.size(); rowIndex++) {
            rowMaxWidthMapping.put(rowIndex, 1);
        }

        for (int rowIndex = 0; rowIndex < rowsList.size(); rowIndex++) {
            int maxSize = 1;
            for (Object obj : rowsList.get(rowIndex)) {
                if (obj instanceof List && ((List) obj).size() > maxSize) {
                    maxSize = ((List) obj).size();
                }
            }

            if (maxSize > 1) {
                rowMaxWidthMapping.put(rowIndex, maxSize);
            }
        }
        return rowMaxWidthMapping;
    }

    /**
     * Get optimal cell padding.
     *
     * @param cellIndex
     *            cell index
     * @param datalength
     *            data length
     * @param columnMaxWidthMapping
     *            map of column and max cell width
     * @param cellPaddingSize
     *            cell padding size
     * @return cell padding
     */
    private int getOptimumCellPadding(int cellIndex, int datalength, Map<Integer, Integer> columnMaxWidthMapping,
            int cellPaddingSize) {
        int datLen = datalength;
        int paddingSize = cellPaddingSize;
        if (datLen % 2 != 0) {
            datLen++;
        }

        if (datLen < columnMaxWidthMapping.get(cellIndex)) {
            paddingSize = paddingSize + (columnMaxWidthMapping.get(cellIndex) - datLen) / 2;
        }

        return paddingSize;
    }

    /**
     * Fill the cell with required value.
     *
     * @param stringBuilder
     *            table
     * @param cell
     *            table cell value
     * @param cellIndex
     *            cell index
     * @param columnMaxWidthMapping
     *            map of column and max cell width
     */
    private void fillCell(StringBuilder stringBuilder, String cell, int cellIndex,
            Map<Integer, Integer> columnMaxWidthMapping, boolean printSeparator) {

        String filledCell = cell;

        if (filledCell == null) {
            filledCell = "";
        }
        int cellLength = filledCell.length();
        int cellPaddingSize = getOptimumCellPadding(cellIndex, cellLength, columnMaxWidthMapping, PADDING_SIZE);

        if (cellIndex == 0 && printSeparator) {
            stringBuilder.append(TABLE_V_SPLIT_SYMBOL);
        }

        stringBuilder.append(filledCell);
        fillSpace(stringBuilder, cellPaddingSize);

        if (cellLength % 2 != 0) {
            stringBuilder.append(" ");
        }

        fillSpace(stringBuilder, cellPaddingSize);
        if (printSeparator) {
            stringBuilder.append(TABLE_V_SPLIT_SYMBOL);
        }

    }
}
