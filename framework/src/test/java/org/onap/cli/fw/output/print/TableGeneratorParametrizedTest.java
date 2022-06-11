/*-
 * ============LICENSE_START=======================================================
 * ONAP : CCSDK
 * ================================================================================
 * Copyright (C) 2022 Samsung Electronics
 * =============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END=========================================================
 */

package org.onap.cli.fw.output.print;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TableGeneratorParametrizedTest {
    private String name;
    private String col1;
    private String col2;
    private String val1;
    private String val2;
    private String exp;

    public TableGeneratorParametrizedTest(String name, String col1, String col2, String val1, String val2, String exp) {
        this.name = name;
        this.col1 = col1;
        this.col2 = col2;
        this.val1 = val1;
        this.val2 = val2;
        this.exp = exp;
    }

    @Test
    public void printTableWithNullEmptyBreakCellTest() {
        System.out.println(name + "...");
        List<List<Object>> rows = new ArrayList<List<Object>>();
        rows.add(Arrays.asList(new Object[]{col1, col2}));
        rows.add(Arrays.asList(new Object[]{val1, val2}));
        String result = new TableGenerator().generateTable(rows, true);
        System.out.println(result);
        String expected = exp;
        assertEquals(expected, result);
    }

    @Parameterized.Parameters
    public static Collection provideForEmptyNullCellBreakTests() {
        return Arrays.asList(new Object[][]
                {
                        {"printTableNullCellTest", "column1", "column2", "value1", null, "+----------+----------+\n" + "|column1   |column2   |\n" + "+----------+----------+\n"
                            + "|value1    |          |\n" + "+----------+----------+\n"},
                        {"printTableEmptyCellTest", "column1", "column2", "value1", "", "+----------+----------+\n" + "|column1   |column2   |\n" + "+----------+----------+\n"
                            + "|value1    |          |\n" + "+----------+----------+\n"},
                        {"printTableNoCellBreakTest", "column1", "column2", "value1", "value2", "+----------+----------+\n" + "|column1   |column2   |\n" + "+----------+----------+\n"
                            + "|value1    |value2    |\n" + "+----------+----------+\n"}
                });
    }
}
