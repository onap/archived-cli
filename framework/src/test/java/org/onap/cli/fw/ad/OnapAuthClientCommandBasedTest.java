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

package org.onap.cli.fw.ad;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.OnapCommandRegistrar;
import org.onap.cli.fw.cmd.OnapHttpCommand;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.conf.OnapCommandConfg;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandProductVersionInvalid;

public class OnapAuthClientCommandBasedTest {

    @Before
    public void setup() throws OnapCommandProductVersionInvalid, OnapCommandException {
        OnapCommandRegistrar.getRegistrar().setEnabledProductVersion(OnapCommandConfg.getProductName());
    }

    @Test
    public void internalCommandTest() {
        try {
            OnapCommand cmd = OnapCommandRegistrar.getRegistrar().get("sample-test");
            cmd.getInfo().setService(OnapCommandConfg.getProductName());

            cmd.execute();
        } catch (OnapCommandException e) {
            fail("Internal command failed to run");
            e.printStackTrace(System.out);
        }
    }

    @Test
    public void yesCatalogYesAuthTest() throws OnapCommandException {
        try {
            OnapHttpCommand cmd = getCommand("sample-test-schema-yes-auth-yes-catalog.yaml");
            cmd.getParametersMap().get(Constants.DEAFULT_PARAMETER_HOST_URL).setValue("http://localhost:8080");
            cmd.getParametersMap().get(Constants.DEAFULT_PARAMETER_USERNAME).setValue("test");
            cmd.getParametersMap().get(Constants.DEAFULT_PARAMETER_PASSWORD).setValue("password");

            cmd.execute();
        } catch (OnapCommandException e) {
            fail("External command Yes Auth Yes Catalog failed to run");
            e.printStackTrace(System.out);
        }
    }

    @Test
    public void yesCatalogNoAuthTest() throws OnapCommandException {
        try {
            OnapHttpCommand cmd = getCommand("sample-test-schema-no-auth-yes-catalog.yaml");
            cmd.getParametersMap().get(Constants.DEAFULT_PARAMETER_HOST_URL).setValue("http://localhost:8080");

            cmd.execute();
        } catch (OnapCommandException e) {
            fail("External command Yes Auth No Catalog failed to run " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    @Test
    public void noCatalogYesAuthTest() throws OnapCommandException {
        try {
            OnapHttpCommand cmd = getCommand("sample-test-schema-yes-auth-no-catalog.yaml");
            cmd.getParametersMap().get(Constants.DEAFULT_PARAMETER_HOST_URL).setValue("http://localhost:8080");
            cmd.getParametersMap().get(Constants.DEAFULT_PARAMETER_USERNAME).setValue("test");
            cmd.getParametersMap().get(Constants.DEAFULT_PARAMETER_PASSWORD).setValue("password");

            cmd.execute();
        } catch (OnapCommandException e) {
            fail("External command Yes Auth No Catalog failed to run");
            e.printStackTrace(System.out);
        }
    }

    @Test
    public void noCatalogYesAuthWithAdditionalParamsTest() throws OnapCommandException {
        try {
            OnapHttpCommand cmd = getCommand("sample-test-schema-yes-auth-with-additional-params-no-catalog.yaml");
            assertTrue(cmd.getParametersMap().containsKey("string-param"));
        } catch (OnapCommandException e) {
            fail("External command Yes Auth No Catalog failed to run");
            e.printStackTrace(System.out);
        }
    }

    @Test
    public void noCatalogNoAuthTest() throws OnapCommandException {
        try {
            OnapHttpCommand cmd = getCommand("sample-test-schema-no-auth-no-catalog.yaml");
            cmd.getParametersMap().get(Constants.DEAFULT_PARAMETER_HOST_URL).setValue("http://localhost:8080");

            cmd.execute();
        } catch (OnapCommandException e) {
            fail("External command No Auth No Catalog failed to run");
            e.printStackTrace(System.out);
        }
    }

    private OnapHttpCommand getCommand(String yaml) throws OnapCommandException {
        OnapHttpCommand cmd = new OnapHttpCommand() {
            @Override
            protected void processRequest() throws OnapCommandException {
                if (!this.getService().isModeDirect()) {
                    String url = this.authClient.getServiceUrl();
                    assert url.equals(this.getParametersMap().get(Constants.DEAFULT_PARAMETER_HOST_URL).getValue() + "/");
                }
            }
        };

        cmd.initializeSchema(yaml);

        return cmd;
    }
 }
