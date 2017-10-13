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

package org.onap.cli.fw.schema;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.cmd.OnapHttpCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.utils.OnapCommandSchemaLoaderUtils;

public class ValidateSchemaTest {

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest1() throws OnapCommandException {

        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandSchemaLoaderUtils.loadSchema(cmd, "fdsfds.yaml", true, true);
    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest2() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandSchemaLoaderUtils.loadSchema(cmd, "fdsfds", true, true);
    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest4() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandSchemaLoaderUtils.loadSchema(cmd,
                ValidateSchemaTest.class.getClassLoader().getResource("open-cli.properties").getFile(),
                true, true);
    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest5() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandSchemaLoaderUtils.loadSchema(cmd, "schema-invalid-file-null.yaml", true, true);
    }

    @Test
    public void invalidate1Test5() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandSchemaLoaderUtils.loadSchema(cmd, "schema-validate-pass.yaml", true, true);

    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest3() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandSchemaLoaderUtils.loadSchema(cmd, "schema-invalid-file.yaml", true, true);
    }

    @Test
    public void validateTest() throws OnapCommandException {

        OnapCommand cmd1 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList1 = OnapCommandSchemaLoaderUtils.loadSchema(cmd1, "schema-validate-http.yaml", true, true);
        assertTrue(errorList1.size() > 0);

        OnapCommand cmd2 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList2 = OnapCommandSchemaLoaderUtils.loadSchema(cmd2, "schema-validate-basic.yaml", true, true);
        assertTrue(errorList2.size() > 0);

        OnapCommand cmd3 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList3 = OnapCommandSchemaLoaderUtils.loadSchema(cmd2, "schema-validate-invalidschematype.yaml", true, true);
        assertTrue(errorList3.size() > 0);

        OnapCommand cmd4 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList4 = OnapCommandSchemaLoaderUtils.loadSchema(cmd2, "schema-validate-invalid.yaml", true, true);

        OnapHttpCommand oclipHttpCommand = new OnapHttpCommand();
        errorList4.addAll(OnapCommandSchemaLoaderUtils.loadHttpSchema(oclipHttpCommand,
                "schema-validate-invalid.yaml", true, true));
        assertTrue(errorList4.size() > 0);

        OnapCommand cmd5 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList5 = OnapCommandSchemaLoaderUtils.loadSchema(cmd5, "schema-validate-pass.yaml", true, true);
        assertTrue(errorList5.size() == 0);

    }
}
