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

package org.onap.cli.fw.http.error;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandResultMapProcessingFailed;

public class OnapCommandErrorTest {
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
    public void oclipCommandLoginFailedTest3() {
        OnapCommandLoginFailed failed = new OnapCommandLoginFailed("Failed");

        assertEquals("0x4001::Login failed, Failed", failed.getMessage());
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
    public void oclipCommandServiceNotFoundTest() {
        OnapCommandServiceNotFound failed = new OnapCommandServiceNotFound("Service");

        assertEquals("0xd001::Service Service is not found in MSB", failed.getMessage());
    }

}
