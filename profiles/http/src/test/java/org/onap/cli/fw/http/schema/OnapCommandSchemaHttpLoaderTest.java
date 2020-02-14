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
package org.onap.cli.fw.http.schema;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class OnapCommandSchemaHttpLoaderTest {
    private class TestClass{
        private String testField;

        public TestClass(String testField) {
            this.testField = testField;
        }

        public String getTestField() {
            return testField;
        }

        @Override
        public String toString() {
            return "{\"testField\" :"+testField +"}";
        }
    }

    @Test
    public void validateHttpBodyTest(){

        Map<String, Object> requestMap = new HashMap<>();
        TestClass testClass = new TestClass("testValue");
        requestMap.put("body",testClass);
        Set<String> set = OnapCommandSchemaHttpLoader.validateHttpBody(null,requestMap);
        assertEquals(0,set.size());
    }

}