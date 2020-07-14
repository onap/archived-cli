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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import static org.junit.Assert.assertEquals;

public class ValidateSchemaTest {

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest1() throws OnapCommandException {

        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandSchemaLoader.loadSchema(cmd, "fdsfds.yaml", true, true);
    }


    @Test
    public void validateTestMerge() throws OnapCommandException {

        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        cmd.initializeSchema("test-command-to-valdiate-merge.yaml", true);
    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest2() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandSchemaLoader.loadSchema(cmd, "fdsfds", true, true);
    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest4() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandSchemaLoader.loadSchema(cmd,
                ValidateSchemaTest.class.getClassLoader().getResource("open-cli.properties").getFile(),
                true, true);
    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest5() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandSchemaLoader.loadSchema(cmd, "schema-invalid-file-null.yaml", true, true);
    }

    @Test
    public void invalidate1Test5() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandSchemaLoader.loadSchema(cmd, "schema-validate-pass.yaml", true, true);

    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void invalidateTest3() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        OnapCommandSchemaLoader.loadSchema(cmd, "schema-invalid-file.yaml", true, true);
    }

    @Test
    public void validateTest() throws OnapCommandException {

        OnapCommand cmd2 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList2 = OnapCommandSchemaLoader.loadSchema(cmd2, "schema-validate-basic.yaml", true, true);
        assertTrue(errorList2.size() > 0);

        OnapCommand cmd3 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList3 = OnapCommandSchemaLoader.loadSchema(cmd2,
            "schema-validate-invalidschematype.yaml", true, true);
        assertTrue(errorList3.size() > 0);


        OnapCommand cmd5 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        List<String> errorList5 = OnapCommandSchemaLoader.loadSchema(cmd5, "schema-validate-pass.yaml", true, true);
        assertEquals(0, errorList5.size());

    }

    @Test
    public void validateAfterRemovingIfElseTest() throws OnapCommandException {
        OnapCommand cmd2 = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {

            }
        };
        List < String > errorList2 = OnapCommandSchemaLoader.loadSchema(cmd2,
            "schema-validate-basic-default-attr.yaml", true, true);
        assertTrue(errorList2.size() > 0);
    }

    @Test
    public void parseSchemaTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        Map<String, Object> values=new HashMap<>();
        List<Map<String, Object>> list=new ArrayList<>();
        Map<String,Object> paraValues=new HashMap<>();
        paraValues.put("is_secured","yes");
        paraValues.put("is_default_param","yes");
        list.add(paraValues);
        values.put("parameters",list);
        assertTrue(OnapCommandSchemaLoader.parseSchema(cmd,values,true).size()==2);

    }
    @Test
    public void parseSchema2Test() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };
        Map<String, Object> values=new HashMap<>();
        List<Map<String, Object>> list=new ArrayList<>();
        Map<String,Object> paraValues=new HashMap<>();
        Map<String, Object> attributesValues=new HashMap<>();
        paraValues.put("is_secured","yes");
        paraValues.put("is_default_attr","yes");
        list.add(paraValues);
        attributesValues.put("attributes",list);
        values.put("results",attributesValues);
        assertTrue(OnapCommandSchemaLoader.parseSchema(cmd,values,true).size()==2);

    }
}
