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

import org.junit.Ignore;
import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandOutputPrintingFailed;
import org.onap.cli.fw.output.OnapCommandPrintDirection;

public class OnapCommandPrintTest {

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

    @Test
    public void printTableTest() throws OnapCommandOutputPrintingFailed {
        OnapCommandPrint pr = new OnapCommandPrint();

        pr.setDirection(OnapCommandPrintDirection.LANDSCAPE);
        pr.setPrintTitle(true);
        pr.addColumn("name2", new ArrayList<String>(Arrays.asList(new String[] { "value2" })));
        String exp = "+--------+\n|name2   |\n+--------+\n|value2  |\n+--------+\n";
        String result = pr.printTable(true);
        assertEquals(exp, result);
    }

    @Test
    public void printTableNullColumnHeaderTest() throws OnapCommandOutputPrintingFailed {
        OnapCommandPrint pr = new OnapCommandPrint();
        pr.setDirection(OnapCommandPrintDirection.LANDSCAPE);
        pr.setPrintTitle(true);
        pr.addColumn("name2", new ArrayList<String>(Arrays.asList(new String[] { "value2" })));
        String exp = "+--------+\n|name2   |\n+--------+\n|value2  |\n+--------+\n";
        String result = pr.printTable(true);
        assertEquals(exp, result);
    }

    @Test
    public void printTableEmptyColumnValuesTest() throws OnapCommandOutputPrintingFailed {
        OnapCommandPrint pr = new OnapCommandPrint();
        pr.setDirection(OnapCommandPrintDirection.LANDSCAPE);
        pr.setPrintTitle(true);
        pr.addColumn("name2", new ArrayList<String>(Arrays.asList(new String[] { "" })));
        String exp = "+--------+\n|name2   |\n+--------+\n|        |\n+--------+\n";
        String result = pr.printTable(true);
        assertEquals(exp, result);
    }
}
