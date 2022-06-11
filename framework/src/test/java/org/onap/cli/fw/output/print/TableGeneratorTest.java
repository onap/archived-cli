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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

public class TableGeneratorTest {
    private TableGenerator table;

    @Before
    public void setUp() throws Exception {
        this.table = new TableGenerator();
    }

    @Test
    public void printTableNoColumnTest() {
        System.out.println("printTableNoColumnTest...");
        List<Object> row = new ArrayList<Object>();
        List<List<Object>> rows = new ArrayList<List<Object>>();
        rows.add(row);
        String result = table.generateTable(rows, true);
        System.out.println(result);
        String expected = "\n\n\n";
        assertEquals(expected, result);
    }

    @Test
    public void printTableNoCellTest() {
        System.out.println("printTableNoCellTest...");
        List<List<Object>> rows = new ArrayList<List<Object>>();
        rows.add(Arrays.asList(new Object[] { "column1", "column2" }));
        String result = table.generateTable(rows, true);
        System.out.println(result);
        String expected = "+----------+----------+\n|column1   |column2   |\n+----------+----------+\n";
        assertEquals(expected, result);
    }

    @Test
    public void printTableCellBreakTest() {
        System.out.println("printTableCellBreakTest...");
        List<List<Object>> rows = new ArrayList<List<Object>>();
        rows.add(Arrays.asList(new Object[] { "column1", "column2" }));
        List<Object> list = new ArrayList<Object>();
        list.add("value1");
        list.add(new ArrayList<Object>(
                Arrays.asList(new Object[] { "1234567891234567891234567891234567891234", "56789" })));
        rows.add(list);
        String result = table.generateTable(rows, true);
        System.out.println(result);
        String expected = "+----------+------------------------------------------+\n"
                + "|column1   |column2                                   |\n"
                + "+----------+------------------------------------------+\n"
                + "|value1    |1234567891234567891234567891234567891234  |\n"
                + "|          |56789                                     |\n"
                + "+----------+------------------------------------------+\n";
        assertEquals(expected, result);
    }

    @Test
    public void printTableTwoCellBreakInSameRowTest() {
        System.out.println("printTableTwoCellBreakInSameRowTest...");
        List<List<Object>> rows = new ArrayList<List<Object>>();
        rows.add(Arrays.asList(new Object[] { "column1", "column2" }));
        List<Object> list = new ArrayList<Object>();
        list.add(new ArrayList<Object>(
                Arrays.asList(new Object[] { "1234567891234567891234567891234567891234", "56789" })));
        list.add(new ArrayList<Object>(
                Arrays.asList(new Object[] { "1234567891234567891234567891234567891234", "56789", "00" })));
        rows.add(list);
        String result = table.generateTable(rows, true);
        System.out.println(result);
        String expected = "+------------------------------------------+------------------------------------------+\n"
                + "|column1                                   |column2                                   |\n"
                + "+------------------------------------------+------------------------------------------+\n"
                + "|1234567891234567891234567891234567891234  |1234567891234567891234567891234567891234  |\n"
                + "|56789                                     |56789                                     |\n"
                + "|                                          |00                                        |\n"
                + "+------------------------------------------+------------------------------------------+\n";
        assertEquals(expected, result);
    }

    @Test
    public void printTableTwoCellBreakInDifferentRowTest() {
        System.out.println("printTableTwoCellBreakInSameRowTest...");
        List<List<Object>> rows = new ArrayList<List<Object>>();
        rows.add(Arrays.asList(new Object[] { "column1", "column2" }));
        List<Object> list1 = new ArrayList<Object>();
        list1.add("value1");
        list1.add(new ArrayList<Object>(
                Arrays.asList(new Object[] { "1234567891234567891234567891234567891234", "56789" })));
        rows.add(list1);
        List<Object> list2 = new ArrayList<Object>();
        list2.add(new ArrayList<Object>(
                Arrays.asList(new Object[] { "1234567891234567891234567891234567891234", "hi" })));

        list2.add("value2");
        rows.add(list2);
        String result = table.generateTable(rows, true);
        System.out.println(result);
        String expected = "+------------------------------------------+------------------------------------------+\n"
                + "|column1                                   |column2                                   |\n"
                + "+------------------------------------------+------------------------------------------+\n"
                + "|value1                                    |1234567891234567891234567891234567891234  |\n"
                + "|                                          |56789                                     |\n"
                + "+------------------------------------------+------------------------------------------+\n"
                + "|1234567891234567891234567891234567891234  |value2                                    |\n"
                + "|hi                                        |                                          |\n"
                + "+------------------------------------------+------------------------------------------+\n";
        assertEquals(expected, result);
    }

    @Test
    public void printTableWithoutPrintSeparatorTest() {
        System.out.println("printTableWithoutPrintSeparatorTest...");
        List<List<Object>> rows = new ArrayList<List<Object>>();
        rows.add(Arrays.asList(new Object[] { "column1", "column2" }));
        rows.add(Arrays.asList(new Object[] { "value1", "value2" }));
        String result = table.generateTable(rows, false);
        System.out.println(result);
        String expected = "column1   column2   \nvalue1    value2    \n";
        assertEquals(expected, result);
    }
}