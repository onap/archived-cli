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
import org.onap.cli.fw.error.OnapCommandInvalidDefaultParameter;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.utils.OnapCommandUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestDefaultParameterSection {
    @Test
    public void checkOnlyInclude() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };

        OnapCommandUtils.loadSchema(cmd, "sample-test-include-param.yaml", true, false);
        List<String> parameters = cmd.getParameters().stream().map(p -> p.getName()).collect(Collectors.toList());
        assertTrue(parameters.contains("onap-username"));
        assertTrue(parameters.contains("onap-password"));
        assertTrue(parameters.contains("host-url"));
    }

    @Test
    public void checkOnlyExclude() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };

        OnapCommandUtils.loadSchema(cmd, "sample-test-exclude-param.yaml", true, false);
        List<String> parameters = cmd.getParameters().stream().map(p -> p.getName()).collect(Collectors.toList());
        assertTrue(parameters.contains("onap-username"));
        assertTrue(parameters.contains("onap-password"));
        assertTrue(parameters.contains("host-url"));
        assertFalse(parameters.contains("long"));
        assertFalse(parameters.contains("format"));
        assertTrue(parameters.contains("debug"));
    }

    @Test
    public void checkBothIncludeAndExclude() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };

        OnapCommandUtils.loadSchema(cmd, "sample-test-include-exclude.yaml", true, false);
        List<String> parameters = cmd.getParameters().stream().map(p -> p.getName()).collect(Collectors.toList());

        assertTrue(parameters.contains("onap-username"));
        assertTrue(parameters.contains("onap-password"));
        assertTrue(parameters.contains("host-url"));
    }

    @Test
    public void checkDefaultSectionAbsent() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };

        OnapCommandUtils.loadSchema(cmd, "onap-test-schema.yaml", true, false);
        List<String> parameters = cmd.getParameters().stream().map(p -> p.getName()).collect(Collectors.toList());

        assertFalse(parameters.contains("onap-username"));
        assertFalse(parameters.contains("onap-password"));
        assertTrue(parameters.contains("host-url"));
        assertTrue(parameters.contains("debug"));
        assertTrue(parameters.contains("long"));
        assertTrue(parameters.contains("format"));
    }

    @Test(expected = OnapCommandInvalidDefaultParameter.class)
    public void checkInvalidDefaultArgument() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };

        OnapCommandUtils.loadSchema(cmd, "sample-test-invalid-default-parameter.yaml", true, false);
    }

    @Test(expected = OnapCommandInvalidDefaultParameter.class)
    public void checkInvalidDefaultArgumentNotExist() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };

        OnapCommandUtils.loadSchema(cmd, "sample-test-invalid-default-params-not-exist.yaml", true, false);
    }

    @Test(expected = OnapCommandInvalidSchema.class)
    public void checkDefaltwithNoExcludeAndInclude() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };

        OnapCommandUtils.loadSchema(cmd, "sample-test-import-def-param-false.yaml", true, false);
    }

    @Test(expected = OnapCommandInvalidDefaultParameter.class)
    public void checkInvalidIncludeNoAuth() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };

        OnapCommandUtils.loadSchema(cmd, "sample-test-invalid-include-noauth.yaml", true, false);
    }

    @Test(expected = OnapCommandInvalidDefaultParameter.class)
    public void checkInvalidExcludeNoAuth() throws OnapCommandException {
        OnapCommand cmd = new OnapCommand() {
            @Override
            protected void run() throws OnapCommandException {}
        };

        OnapCommandUtils.loadSchema(cmd, "sample-test-invalid-exclude-noauth.yaml", true, false);
    }
}
