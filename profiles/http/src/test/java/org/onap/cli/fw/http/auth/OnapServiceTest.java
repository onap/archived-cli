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

package org.onap.cli.fw.http.auth;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.onap.cli.fw.http.conf.OnapCommandHttpConstants;

public class OnapServiceTest {

    @Test
    public void serviceTest() {
        OnapCommandHttpService ser = new OnapCommandHttpService();
        ser.setName("name");
        ser.setVersion("1.0");
        ser.setBasePath("basePath");
        ser.setAuthType(OnapCommandHttpConstants.AUTH_NONE);
        assertTrue(ser.getName().equals("name") && ser.getVersion().equals("1.0")
                && ser.getBasePath().equals("basePath") && ser.isNoAuth());

    }

}
