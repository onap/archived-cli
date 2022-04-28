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
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.onap.cli.fw.error.OnapCommandOutputPrintingFailed;
import org.onap.cli.fw.output.OnapCommandPrintDirection;

@RunWith(Parameterized.class)
public class OnapCommandPrintTest {
    private String expected;
    private String colValue1;
    private String colValue2;


    public OnapCommandPrintTest(String expected, String colValue1, String colValue2) {
        this.expected = expected;
        this.colValue1 = colValue1;
        this.colValue2 = colValue2;
    }

    @Parameterized.Parameters
    public static Collection testUtilParams() {
        return Arrays.asList(new Object[][]{ {"+--------+\n|name2   |\n+--------+\n|value2  |\n+--------+\n", "name2", "value2"},
                {"+--------+\n|name2   |\n+--------+\n|value2  |\n+--------+\n", "name2", "value2"},
                {"+--------+\n|name2   |\n+--------+\n|        |\n+--------+\n", "name2", ""} });
    }


    /**
     * Tests involved:
     * 1. printTableTest
     * 2. printTableNullColumnHeaderTest
     * 3. printTableEmptyColumnValuesTest
     * @throws OnapCommandOutputPrintingFailed
     */
    @Test
    public void printableTests() throws OnapCommandOutputPrintingFailed {
        OnapCommandPrint pr = new OnapCommandPrint();
        pr.setDirection(OnapCommandPrintDirection.LANDSCAPE);
        pr.setPrintTitle(true);
        pr.addColumn(this.colValue1, new ArrayList<String>(Arrays.asList(new String[] { this.colValue2 })));
        String exp = this.expected;
        String result = pr.printTable(true);
        assertEquals(exp, result);
    }

    @Test
    @Ignore
    public void printCsvTest() throws OnapCommandOutputPrintingFailed { //NOSONAR
        OnapCommandPrint pr = new OnapCommandPrint();
        pr.setDirection(OnapCommandPrintDirection.LANDSCAPE);
        pr.setPrintTitle(true);
        pr.addColumn("name1", new ArrayList<String>(Arrays.asList(new String[] { "value1" })));
        String exp = "name1\r\n";
        String result = pr.printCsv();
        assertEquals(exp, result);
    }
}
