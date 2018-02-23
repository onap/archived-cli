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

package org.onap.cli.fw.http.utils;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidParameterType;
import org.onap.cli.fw.error.OnapCommandInvalidPrintDirection;
import org.onap.cli.fw.error.OnapCommandInvalidResultAttributeScope;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.error.OnapCommandInvalidSchemaVersion;
import org.onap.cli.fw.error.OnapCommandParameterNameConflict;
import org.onap.cli.fw.error.OnapCommandParameterOptionConflict;
import org.onap.cli.fw.error.OnapCommandSchemaNotFound;
import org.onap.cli.fw.http.cmd.OnapHttpCommand;
import org.onap.cli.fw.http.connect.HttpResult;
import org.onap.cli.fw.http.error.OnapCommandHttpHeaderNotFound;
import org.onap.cli.fw.http.error.OnapCommandHttpInvalidResponseBody;
import org.onap.cli.fw.http.schema.OnapCommandSchemaHttpLoader;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.schema.OnapCommandSchemaLoader;
import org.onap.cli.fw.utils.OnapCommandUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OnapCommandUtilsTest {

    @Test(expected = OnapCommandInvalidSchema.class)
    public void oclipCommandUtilsInputStreamNullTest() throws OnapCommandException {
        OnapCommandSchemaLoader.validateSchemaVersion("sample-test1-schema-http1.yaml", "1.0");
    }

    @Test
    public void oclipCommandUtilsInputStreamNotNullTest() throws OnapCommandException {
        Map<String, ?> map = OnapCommandSchemaLoader.validateSchemaVersion("sample-test1-schema-http.yaml", "1.0");
        assertTrue(map != null);
    }

    @Test
    public void loadHttpBasedSchemaTest() throws OnapCommandException {
        OnapHttpCommand cmd = new OnapHttpCommandSample();
        cmd.setName("sample-create-http");
        try {
            OnapCommandSchemaHttpLoader.loadHttpSchema(cmd, "sample-test-schema-http.yaml", true, true);
            assertTrue(cmd.getSuccessStatusCodes().size() == 2);
        } catch (OnapCommandParameterNameConflict | OnapCommandParameterOptionConflict
                | OnapCommandInvalidParameterType | OnapCommandInvalidPrintDirection
                | OnapCommandInvalidResultAttributeScope | OnapCommandSchemaNotFound | OnapCommandInvalidSchema
                | OnapCommandInvalidSchemaVersion e) {
            fail("Test should not have thrown this exception : " + e.getMessage());
        }
    }


    @Test
    public void loadOnapCommandSchemaAuthRequiredTest() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {

            @Override
            protected void run() throws OnapCommandException {
                // TODO Auto-generated method stub

            }
        };
        OnapCommandSchemaLoader.loadSchema(cmd, "sample-test-schema-auth-required.yaml", true, false);
        assertTrue("sample-test".equals(cmd.getName()));

        Map<String, OnapCommandParameter> map = OnapCommandUtils.getInputMap(cmd.getParameters());
        assertTrue(map.size() == 8);
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

        Map<String, ArrayList<String>> input1 = OnapCommandHttpUtils.populateOutputs(params, output);
        assertEquals("{head=[value1], body=[test], key=[value]}", input1.toString());

        params.put("body", "$b{{$.serviceName}");
        try {
            input1 = OnapCommandHttpUtils.populateOutputs(params, output);
        } catch (OnapCommandHttpInvalidResponseBody e) {
            assertEquals(
                    "0x3004::Http response body does not have json entry {$.serviceName, Missing property in path $['{$']",
                    e.getMessage());
        }
        output.setBody("{}");
        input1 = OnapCommandHttpUtils.populateOutputs(params, output);
        params.put("head", "$h{head2}");
        output.setBody("{\"test\"}");
        input1 = OnapCommandHttpUtils.populateOutputs(params, output);
    }

    @OnapCommandSchema(schema = "sample-test-schema-http.yaml")
    class OnapHttpCommandSample extends OnapHttpCommand {

        @Override
        protected void run() throws OnapCommandException {
        }
    }
}
