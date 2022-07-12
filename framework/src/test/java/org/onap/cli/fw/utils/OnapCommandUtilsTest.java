/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
 * Copyright (C) 2022 Samsung Electronics
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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.error.OnapCommandInvalidSchemaVersion;
import org.onap.cli.fw.error.OnapCommandParameterNameConflict;
import org.onap.cli.fw.error.OnapCommandParameterNotFound;
import org.onap.cli.fw.error.OnapCommandParameterOptionConflict;
import org.onap.cli.fw.error.OnapCommandSchemaNotFound;
import org.onap.cli.fw.info.OnapCommandInfo;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.OnapCommandParameterType;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.schema.OnapCommandSchemaInfo;
import org.onap.cli.fw.schema.OnapCommandSchemaLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;
import static org.onap.cli.fw.conf.OnapCommandConstants.IS_INCLUDE;
import static org.onap.cli.fw.input.OnapCommandParameterType.ARRAY;
import static org.onap.cli.fw.input.OnapCommandParameterType.BOOL;
import static org.onap.cli.fw.input.OnapCommandParameterType.JSON;
import static org.onap.cli.fw.input.OnapCommandParameterType.MAP;
import static org.onap.cli.fw.input.OnapCommandParameterType.YAML;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OnapCommandUtilsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private static final String MANDATORY_ATT = "mandatory_attribute";
    private static final String SECTION_NAME = "section_name";
    private static final String SIMPLE_JSON = "{\"value\":100,\"messages\":[\"msg 1\"]}";
    private static final String SIMPLE_YAML = "martin: {name: Martin, job: Developer}";

    @Test
    public void validateTags_passingValidation() {
        Map<String, String> yamlMap = new HashMap<>();
        yamlMap.put(MANDATORY_ATT, "true");
        List<String> schemaErrors = new ArrayList<>();

        OnapCommandUtils.validateTags(schemaErrors, yamlMap, singletonList(MANDATORY_ATT), singletonList(MANDATORY_ATT), SECTION_NAME);

        assertTrue(schemaErrors.isEmpty());
    }

    @Test
    public void validateTags_mandatoryAttributeIsMissingInYamlMap() {
        Map<String, String> yamlMap = new HashMap<>();
        yamlMap.put(IS_INCLUDE, "true");
        List<String> schemaErrors = new ArrayList<>();

        OnapCommandUtils.validateTags(schemaErrors, yamlMap, singletonList(MANDATORY_ATT), singletonList(MANDATORY_ATT), SECTION_NAME);

        assertEquals("Mandatory attribute '" + MANDATORY_ATT + "' is missing under '" + SECTION_NAME + "'",
                schemaErrors.iterator().next());
    }

    @Test
    public void validateTags_mandatoryAttributeIsEmptyInYamlMap() {
        Map<String, String> yamlMap = new HashMap<>();
        yamlMap.put(MANDATORY_ATT, "");
        yamlMap.put(IS_INCLUDE, "true");
        List<String> schemaErrors = new ArrayList<>();

        OnapCommandUtils.validateTags(schemaErrors, yamlMap, singletonList(MANDATORY_ATT), singletonList(MANDATORY_ATT), SECTION_NAME);

        assertEquals("Mandatory attribute '" + MANDATORY_ATT + "' under '" + SECTION_NAME + "' shouldn't be null or empty",
                schemaErrors.iterator().next());
    }

    @Test
    public void parseParameters_multipleParameters() {
        Set<String> parsedParamaters = new HashSet<>();

        OnapCommandUtils.parseParameters("line ${paramA} line ${paramB}", parsedParamaters);

        assertTrue(parsedParamaters.contains("paramA"));
        assertTrue(parsedParamaters.contains("paramB"));
    }

    @Test
    public void replaceLineForSpecialValues_noVariables() {
        String replacedLine = OnapCommandUtils.replaceLineForSpecialValues("line");

        assertEquals("line", replacedLine);
    }

    @Test
    public void replaceLineFromResultsTest() {
        String replacedLine = OnapCommandUtils.replaceLineFromResults("line", new HashMap<>());
        assertEquals("line", replacedLine);
        replacedLine = OnapCommandUtils.replaceLineFromResults("${}", new HashMap<>());
        assertEquals("${}", replacedLine);
        replacedLine = OnapCommandUtils.replaceLineFromResults("$r{}", new HashMap<>());
        assertTrue( replacedLine.isEmpty());
        HashMap<String, String> values = new HashMap<>();
        values.put("This is test line","This is test line");
        replacedLine = OnapCommandUtils.replaceLineFromResults("$r{This is test line}", values);
        assertEquals("This is test line", replacedLine);
    }

    @Test
    public void replaceLineForSpecialValues_replacingUuid() {
        String replacedLine = OnapCommandUtils.replaceLineForSpecialValues("$s{uuid}");

        assertTrue(replacedLine.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}"));
    }

    @Test
    public void replaceLineForSpecialValues_missingEnvVariable() {
        String replacedLine = OnapCommandUtils.replaceLineForSpecialValues("$s{env:TEST_PROPERTY}");

        assertEquals("env:TEST_PROPERTY", replacedLine);
    }

    @Test
    public void replaceLineForSpecialValues_multipleVariables() {
        String replacedLine = OnapCommandUtils.replaceLineForSpecialValues("$s{A} $s{B}");

        assertEquals("A B", replacedLine);
    }

    @Test
    public void replaceLineFromInputParameters_noVariables() throws Exception {
        String replacedLine = OnapCommandUtils.replaceLineFromInputParameters("line", new HashMap<>());

        assertEquals("line", replacedLine);
    }

    @Test
    public void replaceLineFromInputParameters_missingParametert() throws Exception {
        Map<String, OnapCommandParameter> parameters = new HashMap<>();
        parameters.put("boolean", createCommandParameter(BOOL, Boolean.FALSE));
        thrown.expect(OnapCommandParameterNotFound.class);

        OnapCommandUtils.replaceLineFromInputParameters("line ${param}", parameters);
    }

    @Test
    public void replaceLineFromInputParameters_booleanParameter() throws Exception {
        Map<String, OnapCommandParameter> parameters = new HashMap<>();
        parameters.put("boolean", createCommandParameter(BOOL, Boolean.FALSE));

        String replacedLine = OnapCommandUtils.replaceLineFromInputParameters("line ${boolean}", parameters);

        assertEquals("line false", replacedLine);
    }

    @Test
    public void replaceLineFromInputParameters_jsonParameter() throws Exception {
        Map<String, OnapCommandParameter> parameters = new HashMap<>();
        parameters.put("json", createCommandParameter(JSON, SIMPLE_JSON));

        String replacedLine = OnapCommandUtils.replaceLineFromInputParameters("line ${json}", parameters);

        assertEquals("line" + SIMPLE_JSON, replacedLine);
    }

    @Test
    public void replaceLineFromInputParameters_arrayParameter() throws Exception {
        Map<String, OnapCommandParameter> parameters = new HashMap<>();
        parameters.put("array", createCommandParameter(ARRAY, Arrays.asList("1", "2", "3")));

        String replacedLine = OnapCommandUtils.replaceLineFromInputParameters("line ${array}", parameters);

        assertEquals("line" + "[1, 2, 3]", replacedLine);
    }

    @Test
    public void replaceLineFromInputParameters_yamlParameter() throws Exception {
        Map<String, OnapCommandParameter> parameters = new HashMap<>();
        parameters.put("yaml", createCommandParameter(YAML, SIMPLE_YAML));

        String replacedLine = OnapCommandUtils.replaceLineFromInputParameters("line ${yaml}", parameters);

        assertEquals("line" + SIMPLE_YAML, replacedLine);
    }

    @Test
    public void replaceLineFromInputParameters_mapParameter() throws Exception {
        Map<String, String> mapExample = new HashMap<>();
        mapExample.put("key1", "value1");
        mapExample.put("key2", "value2");
        Map<String, OnapCommandParameter> parameters = new HashMap<>();
        parameters.put("map", createCommandParameter(MAP, mapExample));

        String replacedLine = OnapCommandUtils.replaceLineFromInputParameters("line ${map}", parameters);

        assertEquals("line{\"key1\":\"value1\",\"key2\":\"value2\"}", replacedLine);
    }

    private OnapCommandParameter createCommandParameter(OnapCommandParameterType type, Object value) throws Exception  {
        OnapCommandParameter paramater = new OnapCommandParameter();
        paramater.setParameterType(type);
        paramater.setValue(value);
        return paramater;
    }

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
    public void invalidSchemaFileTest() throws OnapCommandException { //NOSONAR
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
        assertTrue("sample-test".equals(cmd.getName()) && cmd.getParameters().size() == 10);
    }

    @Test(expected = OnapCommandParameterOptionConflict.class)
    public void loadOnapCommandSchemaWithDuplicateNameTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        try {
            OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-invalid-schema-duplicate-name.yaml", false, false);
        }catch (Exception e) {
            assertEquals(e.getClass(), OnapCommandParameterNameConflict.class);
        }
        cmd = new OnapCommandSample();
        try {
            OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-invalid-schema-duplicate-longoption.yaml", false, false);
        }catch (Exception e) {
            assertEquals(e.getClass(), OnapCommandParameterOptionConflict.class);
        }
        OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-invalid-schema-duplicate-shortoption.yaml", false, false);
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

                case ARRAY:
                    com.setValue(Collections.EMPTY_LIST);
                    break;

                case MAP:
                    com.setValue(new HashMap<String, String>());
                    break;

                case BOOL:
                    com.setValue(true);
                    break;

                case TEXT:
                    com.setValue("value");
                    break;

                case URL:
                    com.setValue("http:localhost/test");
                    break;

                case JSON:
                    com.setValue("json");
                    break;

                default:
                    break;
            }
        }

        Map<String, OnapCommandParameter> map = OnapCommandUtils.getInputMap(cmd.getParameters());
        assertEquals(18, map.size());
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
        assertNotNull(actualResult);
        //mrkanag compare the result
    }

    @Test
    public void findOnapCommandsTest() {
        List<Class<OnapCommand>> cmds = OnapCommandDiscoveryUtils.discoverCommandPlugins();
        assertTrue(cmds.size() >= 1);
    }
    @Test
    public void createTestSuiteTest() throws OnapCommandException {
        List<Map<String, Object>> cmds = OnapCommandDiscoveryUtils.createTestSuite("schema-validate","open-cli");
        assertTrue(cmds.size() >= 1);
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
        List<String> list = asList(new String[] { "{\"menu1\": {\"id\": \"file1\",\"value\": \"File1\"}}" });
        List<String> list1 = OnapCommandUtils.jsonFlatten(list);
        String expected = "[{\"menu1\":{\"id\":\"file1\",\"value\":\"File1\"}}]";
        assertEquals(expected, list1.toString());

    }

    @Test
    public void jsonFlattenExceptionTest() {
        List<String> list = asList(new String[] { "{\"menu1\"::{\"id\":\"file1\",\"value\":\"File1\"}}" });
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
        assertNotNull(info);
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

    @Test
    public void testProcessRunner() {
        try {
            ProcessRunner pr = new ProcessRunner("dir", null);
            pr.run();
            System.out.println(pr.getOutput());
            System.out.println(pr.getError());
            System.out.println(pr.getExitCode());
            assertEquals(0, pr.getExitCode());

            pr = new ProcessRunner(new String [] {"dir", "c:"}, null);
            pr.run();
            System.out.println(pr.getOutput());
            System.out.println(pr.getError());
            System.out.println(pr.getExitCode());

        } catch (Exception e) {
            e.printStackTrace();
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

   @Test
   public void testMd5(){
    assertNotNull(OnapCommandUtils.md5("a"));
    }

   @Test
   public void replaceLineFromResults() {
	   Map<String, String> mapExample = new HashMap<String, String>();
	   mapExample.put("key1", "paramA");
	   mapExample.put("key2", "paramB");
	   OnapCommandUtils.replaceLineFromResults("line $r{paramA} line $r{paramB}", mapExample);
	   assertEquals("paramA", mapExample.get("key1"));
	   assertEquals("paramB", mapExample.get("key2"));
   }

    @Test
    public void loadYamlTest() throws OnapCommandInvalidSchema {
        File file=new File("src/test/resources/sample-test-info.yaml");
        Map<String, Object> values= OnapCommandDiscoveryUtils.loadYaml(file.getAbsolutePath());
        assertEquals("sample-test-info",values.get("name"));
    }
}

