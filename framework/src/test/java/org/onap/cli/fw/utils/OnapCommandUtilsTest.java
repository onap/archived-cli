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



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.OnapCommandSchema;
import org.onap.cli.fw.ad.OnapCredentials;
import org.onap.cli.fw.cmd.OnapHttpCommand;
import org.onap.cli.fw.cmd.OnapSwaggerCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandHelpFailed;
import org.onap.cli.fw.error.OnapCommandHttpHeaderNotFound;
import org.onap.cli.fw.error.OnapCommandHttpInvalidResponseBody;
import org.onap.cli.fw.error.OnapCommandInvalidParameterType;
import org.onap.cli.fw.error.OnapCommandInvalidPrintDirection;
import org.onap.cli.fw.error.OnapCommandInvalidResultAttributeScope;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.error.OnapCommandInvalidSchemaVersion;
import org.onap.cli.fw.error.OnapCommandParameterNameConflict;
import org.onap.cli.fw.error.OnapCommandParameterNotFound;
import org.onap.cli.fw.error.OnapCommandParameterOptionConflict;
import org.onap.cli.fw.error.OnapCommandSchemaNotFound;
import org.onap.cli.fw.http.HttpInput;
import org.onap.cli.fw.http.HttpResult;
import org.onap.cli.fw.info.OnapCommandInfo;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.ParameterType;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.run.OnapCommandExecutor;
import org.springframework.core.io.Resource;

import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OnapCommandUtilsTest {

    @Test(expected = OnapCommandInvalidSchema.class)
    public void onapCommandUtilsInputStreamNullTest() throws OnapCommandException {
        OnapCommandUtils.validateSchemaVersion("sample-test1-schema-http1.yaml", "1.0");
    }

    @Test
    public void onapCommandUtilsInputStreamNotNullTest() throws OnapCommandException {
        Map<String, ?> map = OnapCommandUtils.validateSchemaVersion("sample-test1-schema-http.yaml", "1.0");
        assertTrue(map != null);
    }

    @Test
    public void externalSchemaTest() {
        ExternalSchema schema = new ExternalSchema();
        schema.setCmdName("cmdName");
        schema.setSchemaName("schemaName");
        schema.setVersion("version");

        assertTrue("cmdName".equals(schema.getCmdName()) && "schemaName".equals(schema.getSchemaName())
                && "version".equals(schema.getVersion()));
    }

    @Test
    public void schemaFileNotFoundTest() throws OnapCommandException {

        Map<String, ?> map = OnapCommandUtils.validateSchemaVersion("sample-test-schema.yaml", "1.0");
        assertTrue(map.size() > 0);
    }

    @Test
    @Ignore
    public void invalidSchemaFileTest() throws OnapCommandException {
        Map<String, ?> map = null;
        try {
            map = OnapCommandUtils.validateSchemaVersion("sample-test-schema1.yaml", "1.0");
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
            map = OnapCommandUtils.validateSchemaVersion("sample-test-invalid-schema.yaml", "1.0");
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
            map = OnapCommandUtils.validateSchemaVersion("sample-test-schema.yaml", "1.1");
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
        OnapCommandUtils.loadSchema(cmd, "sample-test-schema.yaml", false, false);
        assertTrue("sample-test".equals(cmd.getName()) && cmd.getParameters().size() == 9);
    }

    @Test(expected = OnapCommandParameterNameConflict.class)
    public void loadOnapCommandSchemaWithDuplicateNameTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandUtils.loadSchema(cmd, "sample-test-invalid-schema-duplicate-name.yaml", false, false);
    }

    @Test(expected = OnapCommandParameterOptionConflict.class)
    public void loadOnapCommandSchemaWithDuplicateShortOptionTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandUtils.loadSchema(cmd, "sample-test-invalid-schema-duplicate-shortoption.yaml", false, false);
    }

    @Test(expected = OnapCommandParameterOptionConflict.class)
    public void loadOnapCommandSchemaWithDuplicateLongOptionTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandUtils.loadSchema(cmd, "sample-test-invalid-schema-duplicate-longoption.yaml", false, false);
    }

    @Test
    public void loadOnapCommandSchemaWithDefaultTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandUtils.loadSchema(cmd, "sample-test-schema.yaml", true, false);
        assertTrue("sample-test".equals(cmd.getName()) && cmd.getParameters().size() > 9);

        for (OnapCommandParameter com : cmd.getParameters()) {
            com.setValue("value");
        }

        OnapCredentials cre = OnapCommandUtils.fromParameters(cmd.getParameters());
        assertTrue(cre != null);
        Map<String, OnapCommandParameter> map = OnapCommandUtils.getInputMap(cmd.getParameters());
        assertTrue(map.size() == 16);
    }

    @Test
    public void loadOnapCommandSchemaAuthRequiredTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandUtils.loadSchema(cmd, "sample-test-schema-auth-required.yaml", true, false);
        assertTrue("sample-test".equals(cmd.getName()));

        Map<String, OnapCommandParameter> map = OnapCommandUtils.getInputMap(cmd.getParameters());
        assertTrue(map.size() == 11);
    }

    @Test
    public void loadSwaggerBasedSchemaExceptionTest() throws OnapCommandException {
        OnapSwaggerCommand cmd = new OnapSwaggerBasedCommandSample();
        try {
            OnapCommandUtils.loadSchema(cmd, "sample-test-schema.yaml");
        } catch (OnapCommandInvalidSchema e) {
            assertEquals("0xb001", e.getErrorCode());
        }
    }

    @Test
    public void loadSwaggerBasedSchemaTest() throws OnapCommandException {
        OnapSwaggerCommand cmd = new OnapSwaggerBasedCommandSample();
        try {
            OnapCommandUtils.loadSchema(cmd, "sample-test-schema-swagger.yaml");
            OnapCommandExecutor exe = cmd.getExecutor();
            assertTrue(exe != null);
        } catch (OnapCommandInvalidSchema e) {
            assertTrue(e.getMessage().contains("0xb001::Command schema sample-test-schema.yaml is invalid"));
        }
    }

    @Test
    public void loadHttpBasedSchemaExceptionTest() throws OnapCommandException {
        OnapHttpCommand cmd = new OnapHttpCommandSample();
        cmd.setName("sample-test-http");
        try {
            OnapCommandUtils.loadHTTPSchemaSection(cmd, "sample-test-schema.yaml", false);
        } catch (OnapCommandParameterNameConflict | OnapCommandParameterOptionConflict
                | OnapCommandInvalidParameterType | OnapCommandInvalidPrintDirection
                | OnapCommandInvalidResultAttributeScope | OnapCommandSchemaNotFound | OnapCommandInvalidSchema
                | OnapCommandInvalidSchemaVersion e) {
            assertTrue(e.getMessage().contains("0xb001::Command schema sample-test-schema.yaml is invalid"));
        }
    }

    @Test
    public void loadHttpBasedSchemaTest() throws OnapCommandException {
        OnapHttpCommand cmd = new OnapHttpCommandSample();
        cmd.setName("sample-create-http");
        try {
            OnapCommandUtils.loadHTTPSchemaSection(cmd, "sample-test-schema-http.yaml", true);
            assertTrue(cmd.getSuccessStatusCodes().size() == 2);
        } catch (OnapCommandParameterNameConflict | OnapCommandParameterOptionConflict
                | OnapCommandInvalidParameterType | OnapCommandInvalidPrintDirection
                | OnapCommandInvalidResultAttributeScope | OnapCommandSchemaNotFound | OnapCommandInvalidSchema
                | OnapCommandInvalidSchemaVersion e) {
            fail("Test should not have thrown this exception : " + e.getMessage());
        }
    }

    @Test
    public void helpCommandTest() throws IOException, OnapCommandException {
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandUtils.loadSchema(cmd, "sample-test-schema.yaml", true, false);

        String actualResult = OnapCommandUtils.help(cmd);

        String expectedHelp = FileUtil.loadResource("sample-cmd-test-help.txt");
    }

    @Test
    public void findOnapCommandsTest() {
        List<Class<OnapCommand>> cmds = OnapCommandUtils.findOnapCommands();
        assertTrue(cmds.size() == 6);
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

    @Test
    public void formMethodNameFromAttributeTest() {

        String str = "";
        String name = OnapCommandUtils.formMethodNameFromAttributeName(str, "test");

        assertEquals("", name);

        str = null;
        name = OnapCommandUtils.formMethodNameFromAttributeName(str, "test");

        assertEquals(null, name);

        str = "test-get";
        name = OnapCommandUtils.formMethodNameFromAttributeName(str, "");
        assertEquals("TestGet", name);

    }

    @Test
    public void populateParametersTest() throws OnapCommandException {

        HttpInput input = new HttpInput();
        input.setBody("body");
        input.setMethod("method");
        Map<String, String> mapHead = new HashMap<>();
        mapHead.put("key2", "${value2}");
        input.setReqHeaders(mapHead);
        Map<String, String> query = new HashMap<>();
        query.put("key3", "{${value3}}");
        input.setReqQueries(query);
        input.setUri("uri");

        Map<String, OnapCommandParameter> params = new HashMap<>();
        OnapCommandParameter param = new OnapCommandParameter();
        param.setDefaultValue("defaultValue2");
        param.setParameterType(ParameterType.STRING);
        params.put("value2", param);
        OnapCommandParameter param1 = new OnapCommandParameter();
        param1.setDefaultValue("{\"defaultValue3\"}");
        param1.setParameterType(ParameterType.JSON);
        params.put("value3", param1);

        HttpInput input1 = OnapCommandUtils.populateParameters(params, input);
        String expected = "\nURL: uri\nMethod: method\nRequest Queries: {key3={\"defaultValue3\"}}\n"
                + "Request Body: body\nRequest Headers: {key2=defaultValue2}\nRequest Cookies: {}\nbinaryData=false";
        assertEquals(expected, input1.toString());

        input.setBody("${body}");

        HttpInput input2 = null;
        try {
            input2 = OnapCommandUtils.populateParameters(params, input);
        } catch (OnapCommandParameterNotFound e) {
            assertEquals("0x7005::Command input parameter body is not valid", e.getMessage());
        }

    }

    @Test(expected = OnapCommandHttpHeaderNotFound.class)
    public void populateOutputsTest() throws OnapCommandException {
        HttpResult output = new HttpResult();
        output.setBody(
                "{\"serviceName\":\"test\",\"version\":\"v1\",\"url\":\"/api/test/v1\",\"protocol\":\"REST\",\"visualRange\":\"1\",\"lb_policy\":\"hash\",\"nodes\":[{\"ip\":\"127.0.0.1\",\"port\":\"8012\",\"ttl\":0,\"nodeId\":\"test_127.0.0.1_8012\",\"expiration\":\"2017-02-10T05:33:25Z\",\"created_at\":\"2017-02-10T05:33:25Z\",\"updated_at\":\"2017-02-10T05:33:25Z\"}],\"status\":\"1\"}");
        Map<String, String> mapHead = new HashMap<>();
        mapHead.put("head1", "value1");
        output.setRespHeaders(mapHead);
        output.setStatus(0);

        Map<String, String> params = new HashMap<>();
        params.put("head", "$h{head1}");
        params.put("body", "$b{$.serviceName}");
        params.put("key", "value");

        Map<String, ArrayList<String>> input1 = OnapCommandUtils.populateOutputs(params, output);
        assertEquals("{head=[value1], body=[test], key=[value]}", input1.toString());

        params.put("body", "$b{{$.serviceName}");
        try {
            input1 = OnapCommandUtils.populateOutputs(params, output);
        } catch (OnapCommandHttpInvalidResponseBody e) {
            assertEquals(
                    "0x3004::Http response body does not have json entry {$.serviceName, Missing property in path $['{$']",
                    e.getMessage());
        }
        output.setBody("{}");
        input1 = OnapCommandUtils.populateOutputs(params, output);
        params.put("head", "$h{head2}");
        output.setBody("{\"test\"}");
        input1 = OnapCommandUtils.populateOutputs(params, output);
    }

    @Test(expected = OnapCommandException.class)
    public void zendExceptionTest1() throws OnapCommandException {

        mockExternalResources();
        OnapCommandUtils.loadSchema(new OnapSwaggerBasedCommandSample(), "schemaName");
    }

    @Test(expected = OnapCommandException.class)
    public void zendExceptionTest2() throws OnapCommandException {

        mockExternalResources();
        OnapCommandUtils.loadSchema(new OnapHttpCommandSample(), "schemaName", false, false);
    }

    @Test(expected = OnapCommandException.class)
    public void zendExceptionTest3() throws OnapCommandException {

        mockExternalResources();
        OnapCommandUtils.validateSchemaVersion("schemaName", "version");
    }

    @Test(expected = OnapCommandException.class)
    public void zendExceptionTest4() throws OnapCommandException {

        mockExternalResources();
        OnapCommandUtils.loadExternalSchemasFromJson();
    }

    @Test(expected = OnapCommandException.class)
    public void zendExceptionTest5() throws OnapCommandException {

        mockExternalResources();
        OnapCommandUtils.findAllExternalSchemas();
    }

    @Test(expected = OnapCommandException.class)
    public void zendExceptionTest6() throws OnapCommandException {

        mockExternalResources();
        OnapCommandUtils.persist(new ArrayList<ExternalSchema>());
    }

    @Test(expected = OnapCommandHelpFailed.class)
    public void zendExceptionHelpTest1() throws OnapCommandException {

        mockPrintMethodException();
        OnapCommand cmd = new OnapCommandSample();
        OnapCommandUtils.loadSchema(cmd, "sample-test-schema.yaml", true, false);

        OnapCommandUtils.help(cmd);

    }


    @Test
    public void test() throws OnapCommandException {
        OnapCommandSampleInfo cmd = new OnapCommandSampleInfo();
        OnapCommandUtils.loadSchema(cmd, "sample-test-info.yaml", true, false);
        OnapCommandInfo info = cmd.getInfo();
        assert info != null;
    }

    @OnapCommandSchema(name = "sample-test-info", version = "cli-1.0", schema = "sample-test-info.yaml")
    class OnapCommandSampleInfo extends OnapCommand {
        @Override
        protected void run() throws OnapCommandException {
        }
    }

    @OnapCommandSchema(name = "sample-test", version = "cli-1.0", schema = "sample-test-schema.yaml")
    class OnapCommandSample extends OnapCommand {
        @Override
        protected void run() throws OnapCommandException {
        }
    }

    @OnapCommandSchema(name = "sample-swagger-test", version = "cli-1.0", schema = "sample-test-schema-swagger.yaml")
    class OnapSwaggerBasedCommandSample extends OnapSwaggerCommand {

        @Override
        protected void run() throws OnapCommandException {
        }
    }

    @OnapCommandSchema(name = "sample-http-test", version = "cli-1.0", schema = "sample-test-schema-http.yaml")
    class OnapHttpCommandSample extends OnapHttpCommand {

        @Override
        protected void run() throws OnapCommandException {
        }
    }

    private void mockExternalResources() {
        new MockUp<OnapCommandUtils>() {
            boolean isMock = true;

            @Mock
            public Resource[] getExternalResources(Invocation inv, String pattern) throws IOException {
                if (isMock) {
                    isMock = false;
                    throw new IOException();
                } else {
                    return inv.proceed(pattern);
                }
            }
        };
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
