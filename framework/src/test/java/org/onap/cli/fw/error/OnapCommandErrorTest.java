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

package org.onap.cli.fw.error;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OnapCommandErrorTest {

    @Test
    public void onapCommandDiscoveryFailedTest() {
        OnapCommandDiscoveryFailed failed = new OnapCommandDiscoveryFailed("name");
        assertEquals("0x0010::Failed auto discover schema files from name under class path, ", failed.getMessage());
        failed = new OnapCommandDiscoveryFailed("directory", "name");
        assertEquals("0x0010::Failed auto generate json file 'name' under class path directory 'directory' , ",
                failed.getMessage());
    }

    @Test
    public void onapCommandInvalidParameterValueTest() {
        OnapCommandInvalidParameterValue failed = new OnapCommandInvalidParameterValue("name");
        assertEquals("0x0028::Parameter name value is invalid, ", failed.getMessage());
    }

    @Test
    public void onapCommandResultMapProcessingFailedTest() {
        OnapCommandResultMapProcessingFailed failed = new OnapCommandResultMapProcessingFailed("name",
                new Exception(""));
        assertEquals("0x0028::Failed to process the result map name in http section,  ", failed.getMessage());
    }

    @Test
    public void onapCommandHttpHeaderNotFoundTest() {
        OnapCommandHttpHeaderNotFound failed = new OnapCommandHttpHeaderNotFound("name");
        assertEquals("0x0027::Http header name is not returned from the service", failed.getMessage());
    }

    @Test
    public void onapCommandClientInitialzationFailedTest() {
        OnapCommandClientInitialzationFailed failed = new OnapCommandClientInitialzationFailed("Test",
                new Exception("Test Command Failed"));
        assertEquals("0x0021::API client for the command Test is failed, Test Command Failed", failed.getMessage());
    }

    @Test
    public void onapCommandExceptionTest1() {
        OnapCommandException failed = new OnapCommandException("1", "Test Command Failed");
        assertEquals("1::Test Command Failed", failed.getMessage());
    }

    @Test
    public void onapCommandExceptionTest2() {
        OnapCommandException failed = new OnapCommandException("1", "Test Command Failed", 201);
        assertEquals("201::1::Test Command Failed", failed.getMessage());
    }

    @Test
    public void onapCommandExecutionFailedTest1() {
        OnapCommandExecutionFailed failed = new OnapCommandExecutionFailed("Test", "Test Command Failed", 201);
        assertEquals("201::0x0001::Command Test failed to execute, Test Command Failed", failed.getMessage());
        failed = new OnapCommandExecutionFailed("Test", new Exception("Test Command Failed"), 201);
        assertEquals("201::0x0001::Command Test failed to execute, Test Command Failed", failed.getMessage());
    }

    @Test
    public void onapCommandExecutionFailedTest2() {
        OnapCommandExecutionFailed failed = new OnapCommandExecutionFailed("Test Command Failed");
        assertEquals("0x0001::Test Command Failed", failed.getMessage());
    }

    @Test
    public void onapCommandExecutionFailedTest3() {
        OnapCommandExecutionFailed failed = new OnapCommandExecutionFailed("Test", "Test Command Failed");
        assertEquals("0x0001::Command Test failed to execute, Test Command Failed", failed.getMessage());

        failed = new OnapCommandExecutionFailed("Test", new Exception("Test Command Failed"));
        assertEquals("0x0001::Command Test failed to execute, Test Command Failed", failed.getMessage());
    }

    @Test
    public void onapCommandExecutorInfoMissingTest() {
        OnapCommandExecutorInfoMissing failed = new OnapCommandExecutorInfoMissing("Test");

        assertEquals("0x0023::Command Test excutor info is missing from schema", failed.getMessage());
    }

    @Test
    public void onapCommandHelpFailedTest() {
        OnapCommandHelpFailed failed = new OnapCommandHelpFailed(new Exception("Failed"));

        assertEquals("0x0002::Command failed to print help message, Failed", failed.getMessage());
    }

    @Test
    public void onapCommandHttpFailureTest1() {
        OnapCommandHttpFailure failed = new OnapCommandHttpFailure("Failed");
        assertEquals("0x0025::Failed", failed.getMessage());

        failed = new OnapCommandHttpFailure(new Exception("failed"), 201);
        assertEquals("201::0x0025::failed", failed.getMessage());
    }

    @Test
    public void onapCommandHttpFailureTest2() {
        OnapCommandHttpFailure failed = new OnapCommandHttpFailure("Failed", 203);

        assertEquals("203::0x0025::Failed", failed.getMessage());
    }

    @Test
    public void onapCommandInvalidParameterTypeTest() {
        OnapCommandInvalidParameterType failed = new OnapCommandInvalidParameterType("Failed");

        assertEquals("0x0003::Parameter type Failed is invalid", failed.getMessage());
    }

    @Test
    public void onapCommandInvalidPrintDirectionTest() {
        OnapCommandInvalidPrintDirection failed = new OnapCommandInvalidPrintDirection("Direction");

        assertEquals("0x0004::Print direction Direction is invalid", failed.getMessage());
    }

    @Test
    public void onapCommandInvalidRegistrationTest() {
        OnapCommandInvalidRegistration failed = new OnapCommandInvalidRegistration(OnapCommandErrorTest.class);

        assertEquals("0x0005::Invalid commad class org.onap.cli.fw.error.OnapCommandErrorTest registration, "
                + "it should be derived from org.onap.cli.fw.OnapCommand", failed.getMessage());
    }

    @Test
    public void onapCommandInvalidResultAttributeScopeTest() {
        OnapCommandInvalidResultAttributeScope failed = new OnapCommandInvalidResultAttributeScope("Attribute");

        assertEquals("0x0006::Result atrribute Attribute is invalid", failed.getMessage());
    }

    @Test
    public void onapCommandInvalidSchemaTest() {
        OnapCommandInvalidSchema failed = new OnapCommandInvalidSchema("Schema", "Failed");

        assertEquals("0x0007::Command schema Schema is invalid, Failed", failed.getMessage());
    }

    @Test
    public void onapCommandInvalidSchemaVersionTest() {
        OnapCommandInvalidSchemaVersion failed = new OnapCommandInvalidSchemaVersion("1.0");

        assertEquals("0x0008::Command schema open_cli_schema_version 1.0 is invalid or missing", failed.getMessage());
    }

    @Test
    public void onapCommandLoginFailedTest1() {
        OnapCommandLoginFailed failed = new OnapCommandLoginFailed(new Exception("Failed"));

        assertEquals("0x0009::Login failed, Failed", failed.getMessage());
    }

    @Test
    public void onapCommandLoginFailedTest2() {
        OnapCommandLoginFailed failed = new OnapCommandLoginFailed("Failed", 201);

        assertEquals("201::0x0009::Login failed, Failed", failed.getMessage());
    }

    @Test
    public void onapCommandLogoutFailedTest() {
        OnapCommandLogoutFailed failed = new OnapCommandLogoutFailed(new Exception("Failed"));
        assertEquals("0x0010::Logout failed, Failed", failed.getMessage());

        failed = new OnapCommandLogoutFailed(200);
        assertEquals("200::0x0010::Logout failed, ", failed.getMessage());
    }

    @Test
    public void onapCommandNotFoundTest() {
        OnapCommandNotFound failed = new OnapCommandNotFound("Test", "1.0");

        assertEquals("0x0011::Command Test is not available for product version 1.0."
                + " so please check command name or product version set in env variable CLI_PRODUCT_VERSION or"
                + " cli.product.version in onap.properties", failed.getMessage());
    }

    @Test
    public void onapCommandNotInitializedTest() {
        OnapCommandNotInitialized failed = new OnapCommandNotInitialized("Test");

        assertEquals("0x0012::Command Test is not initialized", failed.getMessage());
    }

    @Test
    public void onapCommandOutputPrintingFailedTest() {
        OnapCommandOutputPrintingFailed failed = new OnapCommandOutputPrintingFailed(new Exception("error"));

        assertEquals("0x0014::Command is failed to print the result, error", failed.getMessage());
    }

    @Test
    public void onapCommandParameterMissingTest() {
        OnapCommandParameterMissing failed = new OnapCommandParameterMissing("paramName");

        assertEquals("0x0015::Parameter paramName is mandatory", failed.getMessage());
    }

    @Test
    public void onapCommandParameterNameConflictTest() {
        OnapCommandParameterNameConflict failed = new OnapCommandParameterNameConflict("paramName");

        assertEquals("0x0016::Parameter name paramName is in conflict", failed.getMessage());
    }

    @Test
    public void onapCommandParameterOptionConflictTest() {
        OnapCommandParameterOptionConflict failed = new OnapCommandParameterOptionConflict("option");

        assertEquals("0x0017::Parameter option option is in conflict, only one option is allowed with given name",
                failed.getMessage());
    }

    @Test
    public void onapCommandRegistrationFailedTest() {
        OnapCommandRegistrationFailed failed = new OnapCommandRegistrationFailed("Test", "error");

        assertEquals("0x0018::Command Test is failed to register, error", failed.getMessage());
    }

    @Test
    public void onapCommandResultInitialzationFailedTest() {
        OnapCommandResultInitialzationFailed failed = new OnapCommandResultInitialzationFailed("Test",
                new Exception("error"));

        assertEquals("0x0022::Command Test result format is failed, error", failed.getMessage());
    }

    @Test
    public void onapCommandSchemaNotFoundTest() {
        OnapCommandSchemaNotFound failed = new OnapCommandSchemaNotFound("Test");

        assertEquals("0x0019::Command schema Test is not found, ", failed.getMessage());
    }

    @Test
    public void onapCommandServiceNotFoundTest() {
        OnapCommandServiceNotFound failed = new OnapCommandServiceNotFound("Service");

        assertEquals("0x0020::Service Service is not found in MSB", failed.getMessage());
    }

    @Test
    public void onapCommandOutputFormatNotsupportedTest() {
        OnapCommandOutputFormatNotsupported failed = new OnapCommandOutputFormatNotsupported("Format");

        assertEquals("0x0013::Command  does not support the output format Format", failed.getMessage());
    }


    @Test
    public void onapProfilePersistTest() {
        OnapCommandPersistProfileFailed failed = new OnapCommandPersistProfileFailed("error");

        assertEquals("0x1302::Failed to persist profile details, error", failed.getMessage());
    }


    @Test
    public void onapProfileLoadTest() {
        OnapCommandLoadProfileFailed failed = new OnapCommandLoadProfileFailed("error");

        assertEquals("0x1301::Failed to load profile details, error", failed.getMessage());
    }
}
