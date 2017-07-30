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

package org.onap.cli.fw.defaultParameter;

import org.junit.Test;
import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandParameterNameConflict;
import org.onap.cli.fw.utils.OnapCommandUtils;
import org.onap.cli.fw.utils.OnapCommandUtilsTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;


public class TestDefaultParameter {
    @Test
    public void checkOnlyInclude() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };

        OnapCommandUtils.loadSchema(cmd, "sample-test-include-param.yaml", true);
        List<String> parameters = cmd.getParameters().stream().map(p -> p.getName()).collect(Collectors.toList());
        assertTrue(parameters.contains("onap-username"));
        assertTrue(parameters.contains("onap-password"));
        assertTrue(parameters.contains("msb-url"));
    }

    @Test
    public void checkOnlyExclude() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };

        OnapCommandUtils.loadSchema(cmd, "sample-test-exclude-param.yaml", true);
        List<String> parameters = cmd.getParameters().stream().map(p -> p.getName()).collect(Collectors.toList());
        assertFalse(parameters.contains("onap-username"));
        assertTrue(parameters.contains("onap-password"));
        assertFalse(parameters.contains("msb-url"));
    }

    @Test
    public void checkBothIncludeAndExclude() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };

        OnapCommandUtils.loadSchema(cmd, "sample-test-include-exclude.yaml", true);
        List<String> parameters = cmd.getParameters().stream().map(p -> p.getName()).collect(Collectors.toList());

        assertTrue(parameters.contains("onap-username"));
        assertFalse(parameters.contains("onap-password"));
        assertFalse(parameters.contains("msb-url"));
    }

    @Test
    public void checkBothIncludeAndExcludeAbsent() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };

        OnapCommandUtils.loadSchema(cmd, "onap-test-schema.yaml", true);
        List<String> parameters = cmd.getParameters().stream().map(p -> p.getName()).collect(Collectors.toList());

        assertTrue(parameters.contains("onap-username"));
        assertTrue(parameters.contains("onap-password"));
        assertTrue(parameters.contains("msb-url"));
    }
}
