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
    public void oclipCommandDiscoveryFailedTest() {
        OnapCommandDiscoveryFailed failed = new OnapCommandDiscoveryFailed("name");
        assertEquals("0x1001::Failed auto discover schema files from name under class path, ", failed.getMessage());
        failed = new OnapCommandDiscoveryFailed("directory", "name");
        assertEquals("0x1001::Failed auto generate json file 'name' under class path directory 'directory'",
                failed.getMessage());
    }

    @Test
    public void oclipCommandInvalidParameterValueTest() {
        OnapCommandInvalidParameterValue failed = new OnapCommandInvalidParameterValue("name");
        assertEquals("0x7002::Parameter name value is invalid, ", failed.getMessage());
    }

    @Test
    public void oclipCommandResultMapProcessingFailedTest() {
        OnapCommandResultMapProcessingFailed failed = new OnapCommandResultMapProcessingFailed("name",
                new Exception("failed"));
        assertEquals("0x3002::Failed to parse the result format of command name, failed", failed.getMessage());
    }

    @Test
    public void oclipCommandHttpHeaderNotFoundTest() {
        OnapCommandHttpHeaderNotFound failed = new OnapCommandHttpHeaderNotFound("name");
        assertEquals("0x3003::Http header name is not returned from the service", failed.getMessage());
    }

    @Test
    public void oclipCommandClientInitialzationFailedTest() {
        OnapCommandClientInitialzationFailed failed = new OnapCommandClientInitialzationFailed("Test",
                new Exception("Test Command Failed"));
        assertEquals("0x5001::API client for the command Test is failed, Test Command Failed", failed.getMessage());
    }

    @Test
    public void oclipCommandExceptionTest1() {
        OnapCommandException failed = new OnapCommandException("1", "Test Command Failed");
        assertEquals("1::Test Command Failed", failed.getMessage());
    }

    @Test
    public void oclipCommandExceptionTest2() {
        OnapCommandException failed = new OnapCommandException("1", "Test Command Failed", 201);
        assertEquals("201::1::Test Command Failed", failed.getMessage());
    }

    @Test
    public void oclipCommandExecutionFailedTest1() {
        OnapCommandExecutionFailed failed = new OnapCommandExecutionFailed("Test", "Test Command Failed", 201);
        assertEquals("201::0x6001::Command Test failed to execute, Test Command Failed", failed.getMessage());
        failed = new OnapCommandExecutionFailed("Test", new Exception("Test Command Failed"), 201);
        assertEquals("201::0x6001::Command Test failed to execute, Test Command Failed", failed.getMessage());
    }

    @Test
    public void oclipCommandExecutionFailedTest2() {
        OnapCommandExecutionFailed failed = new OnapCommandExecutionFailed("Test Command Failed");
        assertEquals("0x6001::Test Command Failed", failed.getMessage());
    }

    @Test
    public void oclipCommandExecutionFailedTest3() {
        OnapCommandExecutionFailed failed = new OnapCommandExecutionFailed("Test", "Test Command Failed");
        assertEquals("0x6001::Command Test failed to execute, Test Command Failed", failed.getMessage());

        failed = new OnapCommandExecutionFailed("Test", new Exception("Test Command Failed"));
        assertEquals("0x6001::Command Test failed to execute, Test Command Failed", failed.getMessage());
    }

    @Test
    public void oclipCommandExecutorInfoMissingTest() {
        OnapCommandExecutorInfoMissing failed = new OnapCommandExecutorInfoMissing("Test");

        assertEquals("0x6002::Command Test excutor info is missing from schema", failed.getMessage());
    }

    @Test
    public void oclipCommandHelpFailedTest() {
        OnapCommandHelpFailed failed = new OnapCommandHelpFailed(new Exception("Failed"));

        assertEquals("0x9001::Command failed to print help message, Failed", failed.getMessage());
    }

    @Test
    public void oclipCommandHttpFailureTest1() {
        OnapCommandHttpFailure failed = new OnapCommandHttpFailure("Failed");
        assertEquals("0x3001::Failed", failed.getMessage());

        failed = new OnapCommandHttpFailure(new Exception("failed"), 201);
        assertEquals("201::0x3001::failed", failed.getMessage());
    }

    @Test
    public void oclipCommandHttpFailureTest2() {
        OnapCommandHttpFailure failed = new OnapCommandHttpFailure("Failed", 203);

        assertEquals("203::0x3001::Failed", failed.getMessage());
    }

    @Test
    public void oclipCommandInvalidParameterTypeTest() {
        OnapCommandInvalidParameterType failed = new OnapCommandInvalidParameterType("Failed");

        assertEquals("0x7001::Parameter type Failed is invalid", failed.getMessage());
    }

    @Test
    public void oclipCommandInvalidPrintDirectionTest() {
        OnapCommandInvalidPrintDirection failed = new OnapCommandInvalidPrintDirection("Direction");

        assertEquals("0x8003::Print direction Direction is invalid", failed.getMessage());
    }

    @Test
    public void oclipCommandInvalidRegistrationTest() {
        OnapCommandInvalidRegistration failed = new OnapCommandInvalidRegistration(OnapCommandErrorTest.class);

        assertEquals("0x2001::Invalid commad class org.onap.cli.fw.error.OnapCommandErrorTest registration, "
                + "it should be derived from org.onap.cli.fw.OnapCommand", failed.getMessage());
    }

    @Test
    public void oclipCommandInvalidResultAttributeScopeTest() {
        OnapCommandInvalidResultAttributeScope failed = new OnapCommandInvalidResultAttributeScope("Attribute");

        assertEquals("0x8002::Result atrribute Attribute is invalid", failed.getMessage());
    }

    @Test
    public void oclipCommandInvalidSchemaTest() {
        OnapCommandInvalidSchema failed = new OnapCommandInvalidSchema("Schema", "Failed");

        assertEquals("0xb001::Invalid command schema Schema, Failed", failed.getMessage());
    }

    @Test
    public void oclipCommandInvalidSchemaVersionTest() {
        OnapCommandInvalidSchemaVersion failed = new OnapCommandInvalidSchemaVersion("1.0");

        assertEquals("0xb003::Command schema open_cli_schema_version 1.0 is invalid or missing", failed.getMessage());
    }

    @Test
    public void oclipCommandLoginFailedTest1() {
        OnapCommandLoginFailed failed = new OnapCommandLoginFailed(new Exception("Failed"));

        assertEquals("0x4001::Login failed, Failed", failed.getMessage());
    }

    @Test
    public void oclipCommandLoginFailedTest2() {
        OnapCommandLoginFailed failed = new OnapCommandLoginFailed("Failed", 201);

        assertEquals("201::0x4001::Login failed, Failed", failed.getMessage());
    }

    @Test
    public void oclipCommandLogoutFailedTest() {
        OnapCommandLogoutFailed failed = new OnapCommandLogoutFailed(new Exception("Failed"));
        assertEquals("0x4002::Logout failed, Failed", failed.getMessage());

        failed = new OnapCommandLogoutFailed(200);
        assertEquals("200::0x4002::Logout failed", failed.getMessage());
    }

    @Test
    public void oclipCommandNotFoundTest() {
        OnapCommandNotFound failed = new OnapCommandNotFound("Test", "1.0");

        assertEquals("0x6003::Command Test is not available for product version 1.0", failed.getMessage());
    }

    @Test
    public void oclipCommandNotInitializedTest() {
        OnapCommandNotInitialized failed = new OnapCommandNotInitialized("Test");

        assertEquals("0x6004::Command Test is not initialized", failed.getMessage());
    }

    @Test
    public void oclipCommandOutputPrintingFailedTest() {
        OnapCommandOutputPrintingFailed failed = new OnapCommandOutputPrintingFailed(new Exception("error"));

        assertEquals("0x8006::Command is failed to print the result, error", failed.getMessage());
    }

    @Test
    public void oclipCommandParameterMissingTest() {
        OnapCommandParameterMissing failed = new OnapCommandParameterMissing("paramName");

        assertEquals("0x7003::Parameter paramName is mandatory", failed.getMessage());
    }

    @Test
    public void oclipCommandParameterNameConflictTest() {
        OnapCommandParameterNameConflict failed = new OnapCommandParameterNameConflict("paramName");

        assertEquals("0x7004::Parameter name paramName is in conflict", failed.getMessage());
    }

    @Test
    public void oclipCommandParameterOptionConflictTest() {
        OnapCommandParameterOptionConflict failed = new OnapCommandParameterOptionConflict("option");

        assertEquals("0x7006::Parameter option option is in conflict, only one option is allowed with given name",
                failed.getMessage());
    }

    @Test
    public void oclipCommandRegistrationFailedTest() {
        OnapCommandRegistrationFailed failed = new OnapCommandRegistrationFailed("Test", "error");

        assertEquals("0x2002::Failed to register the command Test, error", failed.getMessage());
    }

    @Test
    public void oclipCommandResultInitialzationFailedTest() {
        OnapCommandResultInitialzationFailed failed = new OnapCommandResultInitialzationFailed("Test",
                new Exception("error"));

        assertEquals("0x8004::Failed to parse the result format of command Test, error", failed.getMessage());
    }

    @Test
    public void oclipCommandSchemaNotFoundTest() {
        OnapCommandSchemaNotFound failed = new OnapCommandSchemaNotFound("Test");

        assertEquals("0xb002::Command schema is missing for command Test", failed.getMessage());
    }

    @Test
    public void oclipCommandServiceNotFoundTest() {
        OnapCommandServiceNotFound failed = new OnapCommandServiceNotFound("Service");

        assertEquals("0xd001::Service Service is not found in MSB", failed.getMessage());
    }

    @Test
    public void oclipCommandOutputFormatNotsupportedTest() {
        OnapCommandOutputFormatNotsupported failed = new OnapCommandOutputFormatNotsupported("Format");

        assertEquals("0x8005::Command  does not support the output format Format", failed.getMessage());
    }


    @Test
    public void oclipProfilePersistTest() {
        OnapCommandPersistProfileFailed failed = new OnapCommandPersistProfileFailed("error");

        assertEquals("0xc002::Failed to persist profile details, error", failed.getMessage());

        failed = new OnapCommandPersistProfileFailed(new Exception("error"));

        assertEquals("0xc002::Failed to persist profile details, error", failed.getMessage());
    }


    @Test
    public void oclipProfileLoadTest() {
        OnapCommandLoadProfileFailed failed = new OnapCommandLoadProfileFailed("error");

        assertEquals("0xc001::Failed to load profile details, error", failed.getMessage());

        failed = new OnapCommandLoadProfileFailed(new Exception("error"));

        assertEquals("0xc001::Failed to load profile details, error", failed.getMessage());
    }

    @Test
    public void oclipCommandTypeInvalidTest() {
        OnapCommandInvalidCommandType failed = new OnapCommandInvalidCommandType("test");

        assertEquals("0x3003::Command type test is invalid", failed.getMessage());
    }    
}
