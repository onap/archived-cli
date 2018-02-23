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

package org.onap.cli.fw.utils;


import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.error.OnapCommandInvalidSchemaVersion;
import org.onap.cli.fw.error.OnapCommandParameterNameConflict;
import org.onap.cli.fw.error.OnapCommandParameterOptionConflict;
import org.onap.cli.fw.error.OnapCommandSchemaNotFound;
import org.onap.cli.fw.info.OnapCommandInfo;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.schema.OnapCommandSchemaInfo;
import org.onap.cli.fw.schema.OnapCommandSchemaLoader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OnapCommandUtilsTest {
    @Test
    public void externalSchemaTest() {
        OnapCommandSchemaInfo schema = new OnapCommandSchemaInfo();
        schema.setCmdName("cmdName");
        schema.setSchemaName("schemaName");
        schema.setVersion("version");

        assertTrue("cmdName".equals(schema.getCmdName()) && "schemaName".equals(schema.getSchemaName())
                && "version".equals(schema.getVersion()));
    }

    @Test
    public void schemaFileNotFoundTest() throws OnapCommandException {

        Map<String, ?> map = OnapCommandSchemaLoader.validateSchemaVersion("sample-test-schema.yaml", "1.0");
        assertTrue(map.size() > 0);
    }

    @Test
    @Ignore
    public void invalidSchemaFileTest() throws OnapCommandException {
        Map<String, ?> map = null;
        try {
            map = OnapCommandSchemaLoader.validateSchemaVersion("sample-test-schema1.yaml", "1.0");
        } catch (OnapCommandInvalidSchemaVersion e) {
            fail("Test should not have thrown this exception : " + e.getMessage());
        } catch (OnapCommandInvalidSchema e) {
            fail("Test should not have thrown this exception : " + e.getMessage());
        } catch (OnapCommandSchemaNotFound e) {
            assertEquals("0xb002::Command schema sample-test-schema1.yaml is not found", e.getMessage());
        }
    }

    @Test
    public void validateWrongSchemaVersionTest() throws OnapCommandException {
        Map<String, ?> map = null;
        try {
            map = OnapCommandSchemaLoader.validateSchemaVersion("sample-test-invalid-schema.yaml", "1.0");
        } catch (OnapCommandInvalidSchemaVersion e) {
            fail("Test should not have thrown this exception : " + e.getMessage());
        } catch (OnapCommandInvalidSchema e) {
            assertTrue(e.getMessage().contains("0xb001::Command schema sample-test-invalid-schema.yaml is invalid"));
        } catch (OnapCommandSchemaNotFound e) {
            fail("Test should not have thrown this exception : " + e.getMessage());
        }
    }

    @Test
    public void validateSchemaVersionTest() throws OnapCommandException {
        Map<String, ?> map = null;
        try {
            map = OnapCommandSchemaLoader.validateSchemaVersion("sample-test-schema.yaml", "1.1");
        } catch (OnapCommandInvalidSchemaVersion e) {
            assertEquals("0xb003::Command schema open_cli_schema_version 1.0 is invalid or missing", e.getMessage());
        } catch (OnapCommandInvalidSchema e) {
            fail("Test should not have thrown this exception : " + e.getMessage());
        } catch (OnapCommandSchemaNotFound e) {
            fail("Test should not have thrown this exception : " + e.getMessage());
        }
    }

    @Test
    public void loadOnapCommandSchemaWithOutDefaultTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-schema.yaml", false, false);
        assertTrue("sample-test".equals(cmd.getName()) && cmd.getParameters().size() == 9);
    }

    @Test(expected = OnapCommandParameterNameConflict.class)
    public void loadOnapCommandSchemaWithDuplicateNameTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-invalid-schema-duplicate-name.yaml", false, false);
    }

    @Test(expected = OnapCommandParameterOptionConflict.class)
    public void loadOnapCommandSchemaWithDuplicateShortOptionTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-invalid-schema-duplicate-shortoption.yaml", false, false);
    }

    @Test(expected = OnapCommandParameterOptionConflict.class)
    public void loadOnapCommandSchemaWithDuplicateLongOptionTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-invalid-schema-duplicate-longoption.yaml", false, false);
    }

    @Test
    public void loadOnapCommandSchemaWithDefaultTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-schema.yaml", true, false);
        assertTrue("sample-test".equals(cmd.getName()) && cmd.getParameters().size() > 9);

        for (OnapCommandParameter com : cmd.getParameters()) {
            switch (com.getParameterType()) {
                case STRING:
                    com.setValue("value");
                    break;
                case MAP:
                    com.setValue(new HashMap<String, String>());
                    break;
                default:
                    break;
            }
        }

        Map<String, OnapCommandParameter> map = OnapCommandUtils.getInputMap(cmd.getParameters());
        assertTrue(map.size() == 16);
    }

    @Test
    public void contextParameterTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-schema.yaml", true, false);
        Optional<OnapCommandParameter> contextOpt = cmd.getParameters().stream()
                .filter(e -> e.getName().equals("context"))
                .findFirst();

        if (contextOpt.isPresent()) {
            OnapCommandParameter context = contextOpt.get();
            assertTrue(context.getDefaultValue() instanceof HashMap);
        } else {
            fail("context parameter is not available");
        }
    }

    @Test
    public void contextParameterSetAndGetTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-schema.yaml", true, false);
        Optional<OnapCommandParameter> contextOpt = cmd.getParameters().stream()
                .filter(e -> e.getName().equals("context"))
                .findFirst();

        if (contextOpt.isPresent()) {
            OnapCommandParameter context = contextOpt.get();
            HashMap<String, String> map = new HashMap();
            map.put("a", "b");
            context.setValue(map);

            map = (HashMap<String, String>) context.getValue();
            assertTrue(map.keySet().contains("a"));
            assertTrue(map.values().contains("b"));
        } else {
            fail("context parameter is not available");
        }
    }

    @Test
    public void helpCommandTest() throws IOException, OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-schema.yaml", true, false);

        String actualResult = OnapCommandHelperUtils.help(cmd);

        String expectedHelp = FileUtil.loadResource("sample-cmd-test-help.txt");

        //mrkanag compare the result
    }

    @Test
    public void findOnapCommandsTest() {
        List<Class<OnapCommand>> cmds = OnapCommandDiscoveryUtils.discoverCommandPlugins();
        assertTrue(cmds.size() == 3);
    }

    @Test
    public void sortTest() {
        Set<String> set = new HashSet<String>();
        set.add("dbvc");
        set.add("bbvcb");
        set.add("aaa");
        set.add("c");
        set.add("z");
        List<String> list = OnapCommandUtils.sort(set);
        assertEquals("[aaa, bbvcb, c, dbvc, z]", list.toString());
    }

    @Test
    public void jsonFlattenTest() {
        List<String> list = Arrays.asList(new String[] { "{\"menu1\": {\"id\": \"file1\",\"value\": \"File1\"}}" });
        List<String> list1 = OnapCommandUtils.jsonFlatten(list);
        String expected = "[{\"menu1\":{\"id\":\"file1\",\"value\":\"File1\"}}]";
        assertEquals(expected, list1.toString());

    }

    @Test
    public void jsonFlattenExceptionTest() {
        List<String> list = Arrays.asList(new String[] { "{\"menu1\"::{\"id\":\"file1\",\"value\":\"File1\"}}" });
        List<String> list1 = OnapCommandUtils.jsonFlatten(list);
        String expected = "[{\"menu1\"::{\"id\":\"file1\",\"value\":\"File1\"}}]";
        assertEquals(expected, list1.toString());

    }

    @Test(expected = OnapCommandHelpFailed.class)
    public void zendExceptionHelpTest1() throws OnapCommandException {

        mockPrintMethodException();
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-schema.yaml", true, false);

        OnapCommandHelperUtils.help(cmd);

    }


    @Test
    public void test() throws OnapCommandException {
        OnapCommandSampleInfo cmd = new OnapCommandSampleInfo();
        OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-info.yaml", true, false);
        OnapCommandInfo info = cmd.getInfo();
        assert info != null;
    }

    @OnapCommandSchema(schema = "sample-test-info.yaml")
    class OnapCommandSampleInfo extends OnapCommand {
        @Override
        protected void run() throws OnapCommandException {
        }
    }

    @OnapCommandSchema(schema = "sample-test-schema.yaml")
    class OnapCommandSample extends OnapCommand {
        @Override
        protected void run() throws OnapCommandException {
        }
    }

    private void mockPrintMethodException() {
        new MockUp<OnapCommandResult>() {
            boolean isMock = true;

            @Mock
            public String print(Invocation inv) throws OnapCommandException {
                if (isMock) {
                    isMock = false;
                    throw new OnapCommandException("", "");
                } else {
                    return inv.proceed();
                }
            }
        };
    }
}
