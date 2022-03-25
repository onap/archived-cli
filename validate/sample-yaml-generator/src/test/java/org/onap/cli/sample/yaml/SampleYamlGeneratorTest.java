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

package org.onap.cli.sample.yaml;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SampleYamlGeneratorTest {

    @Test
    public void testGenerateSampleYaml() throws IOException {
        assertDoesNotThrow(() -> SampleYamlGenerator.generateSampleYaml("testcmd", Arrays.asList("-a", "argument"),
                "+--------+\n+val     +\n+argument+", "test-version-1.0", "target/test.yaml", false, "sample1"));
    }
    @Test
    public void testGenerateSampleYamlForWriteMultilineKeyValue() throws IOException {
        assertDoesNotThrow(() -> SampleYamlGenerator.generateSampleYaml("testcmd-multiline", Arrays.asList("-a", "argument"),
                "+--------+\n+testval1 +\n+argument1+\n+testval2 +\n+argument2+", "test-version-1.0",
                "target/test-multiline.yaml", true, "sample1"));
    }

}
