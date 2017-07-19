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
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.yaml.snakeyaml.scanner.ScannerException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class ValidateSchemaTest {

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest1() throws OnapCommandInvalidSchema {
        new SchemaValidator(new File("fdsfds"));
        new SchemaValidator(new File("fdsfds.yaml"));
    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest2() throws OnapCommandInvalidSchema {
        new SchemaValidator(new File("fdsfds"));
    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest4() throws OnapCommandInvalidSchema {
        new SchemaValidator(
                new File(ValidateSchemaTest.class.getClassLoader().getResource("onap.properties").getFile()));
    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest5() throws OnapCommandInvalidSchema {
        new SchemaValidator(new File(
                ValidateSchemaTest.class.getClassLoader().getResource("schema-invalid-file-null.yaml").getFile()));
    }

    @Test
    public void invalidate1Test5() throws OnapCommandInvalidSchema {
        new SchemaValidator("schema-validate-pass.yaml");
    }

    @Test(expected = ScannerException.class)
    public void invalidateTest3() throws OnapCommandInvalidSchema {
        new SchemaValidator(
                new File(ValidateSchemaTest.class.getClassLoader().getResource("schema-invalid-file.yaml").getFile()));
    }

    @Test
    public void validateTest() throws OnapCommandInvalidSchema {
        new SchemaValidator(
                new File(ValidateSchemaTest.class.getClassLoader().getResource("schema-validate-http.yaml").getFile()))
                        .validate();

        new SchemaValidator(
                new File(ValidateSchemaTest.class.getClassLoader().getResource("schema-validate-basic.yaml").getFile()))
                        .validate();
        new SchemaValidator(new File(ValidateSchemaTest.class.getClassLoader()
                .getResource("schema-validate-invalidschematype.yaml").getFile())).validate();
        new SchemaValidator(
                new File(ValidateSchemaTest.class.getClassLoader().getResource("schema-validate-invalid.yaml").getFile()))
                        .validate();
        new SchemaValidator(
                new File(ValidateSchemaTest.class.getClassLoader().getResource("schema-validate-pass.yaml").getFile()))
                        .validate();

    }

    @Test
    public void schemaValidateInterfaceTest() throws OnapCommandInvalidSchema {
        SchemaValidate.attributeNameExist("name", "section");
        SchemaValidate.emptyValue("section", "attribute");
        SchemaValidate.defaultYamlSchema("section");
        SchemaValidate.emptySection("section");
        SchemaValidate.invalidAttributeScope("name", new ArrayList<String>());
        SchemaValidate.invalidAttrType("name", "section", new ArrayList<String>());
        SchemaValidate.invalidBooleanValueMessage("section", "attribute", "value");
        SchemaValidate.invalidRequestParam("subSection", "attribute");
        SchemaValidate.invalidSections(new HashSet<String>(), new ArrayList<String>(), new ArrayList<String>());
        SchemaValidate.attributeScopeEmpty("fsdf");
        SchemaValidate.invalidType("section", "attribute", new ArrayList<String>());
        SchemaValidate.longOptionExist("name");
        SchemaValidate.shortOptionExist("name");
        SchemaValidate.optionExist("option", "attrValue", "name");
        SchemaValidate.optionDefaultExist("option", "attrValue", "name", new HashSet<String>());
        SchemaValidate.nameExist("name", "section");
        SchemaValidate.mandatoryAttrEmpty("param", "section");
    }
}
