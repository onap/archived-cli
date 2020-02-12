/*
 * Copyright 2020 Huawei Technologies Co., Ltd.
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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.onap.cli.fw.http.error.OnapCommandHttpInvalidRequestBody;

import static org.junit.Assert.*;

public class OnapCommandHttpUtilsTest {
    @Test
    public void normalizeJsonTestWithElement() {
        JsonObject testJson = new JsonObject();
        testJson.addProperty("testField","testValue");
        testJson.addProperty("testEmptyField","");
        testJson.add("testNullField",null);

        JsonArray testArray = new JsonArray();
        JsonObject jobj = new JsonObject();
        testArray.add(jobj);
        testJson.add("testArray",testArray);
        OnapCommandHttpUtils.normalizeJson(testJson);
        assertTrue(true);
    }

    @Test
    public void normalizeJsonTestWithString() throws OnapCommandHttpInvalidRequestBody {
        String expected = "{\"testField\":\"testValue\"}";
        String actual = OnapCommandHttpUtils.normalizeJson(expected);
        assertEquals(expected,actual);
    }


}