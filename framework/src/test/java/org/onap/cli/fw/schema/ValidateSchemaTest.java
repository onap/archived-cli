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

import org.junit.Test;
import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.utils.OnapCommandUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ValidateSchemaTest {

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest1() throws OnapCommandException {

        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandUtils.loadSchema(cmd, "fdsfds.yaml", true, true);
    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest2() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandUtils.loadSchema(cmd, "fdsfds", true, true);
    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest4() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandUtils.loadSchema(cmd,
                ValidateSchemaTest.class.getClassLoader().getResource("onap.properties").getFile(),
                true, true);
    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest5() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandUtils.loadSchema(cmd, "schema-invalid-file-null.yaml", true, true);
    }

    @Test
    public void invalidate1Test5() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandUtils.loadSchema(cmd, "schema-validate-pass.yaml", true, true);

    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest3() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandUtils.loadSchema(cmd, "schema-invalid-file.yaml", true, true);
    }

    @Test
    public void validateTest() throws OnapCommandException {

        OnapCommand cmd1 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList1 = OnapCommandUtils.loadSchema(cmd1, "schema-validate-http.yaml", true, true);
        assertTrue(errorList1.size() > 0);

        OnapCommand cmd2 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList2 = OnapCommandUtils.loadSchema(cmd2, "schema-validate-basic.yaml", true, true);
        assertTrue(errorList2.size() > 0);

        OnapCommand cmd3 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList3 = OnapCommandUtils.loadSchema(cmd2, "schema-validate-invalidschematype.yaml", true, true);
        assertTrue(errorList3.size() > 0);

        OnapCommand cmd4 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList4 = OnapCommandUtils.loadSchema(cmd2, "schema-validate-invalid.yaml", true, true);
        assertTrue(errorList4.size() > 0);

        OnapCommand cmd5 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList5 = OnapCommandUtils.loadSchema(cmd2, "schema-validate-pass.yaml", true, true);
        assertTrue(errorList5.size() > 0);

    }

//    @Test
//    public void schemaValidateInterfaceTest() throws OnapCommandInvalidSchema {
//        SchemaValidate.attributeNameExist("name", "section");
//        SchemaValidate.emptyValue("section", "attribute");
//        SchemaValidate.defaultYamlSchema("section");
//        SchemaValidate.emptySection("section");
//        SchemaValidate.invalidAttributeScope("name", new ArrayList<String>());
//        SchemaValidate.invalidAttrType("name", "section", new ArrayList<String>());
//        SchemaValidate.invalidBooleanValueMessage("section", "attribute", "value");
//        SchemaValidate.invalidRequestParam("subSection", "attribute");
//        SchemaValidate.invalidSections(new HashSet<String>(), new ArrayList<String>(), new ArrayList<String>());
//        SchemaValidate.attributeScopeEmpty("fsdf");
//        SchemaValidate.invalidType("section", "attribute", new ArrayList<String>());
//        SchemaValidate.longOptionExist("name");
//        SchemaValidate.shortOptionExist("name");
//        SchemaValidate.optionExist("option", "attrValue", "name");
//        SchemaValidate.optionDefaultExist("option", "attrValue", "name", new HashSet<String>());
//        SchemaValidate.nameExist("name", "section");
//        SchemaValidate.mandatoryAttrEmpty("param", "section");
//    }
}
