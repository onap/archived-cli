/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
 * Copyright 2020 Nokia
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

package org.onap.cli.cmd.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Test;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.cmd.OnapCommandType;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandExecutionFailed;
import org.onap.cli.fw.error.OnapCommandInvalidParameterValue;
import org.onap.cli.fw.error.OnapCommandNotInitialized;
import org.onap.cli.fw.info.OnapCommandInfo;
import org.onap.cli.fw.info.OnapCommandState;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.OnapCommandParameterType;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;

import org.onap.cli.fw.output.OnapCommandResult;
import static org.junit.Assert.assertNotNull;

public class OnapCommandSampleTest {

    private static final String SAMPLE_TEST_COMMAND_NAME = "sample-test";
    private static final int EXPECTED_NUMBER_OF_COMMAND_PARAMETERS = 18;
    private static final int EXPECTED_SIZE_OF_METADATA = 1;

    @Test
    public void sampleTestVersion() {

        try {
            Set<OnapCommandParameter> parameters = new HashSet<>();
            OnapCommandParameter version = new OnapCommandParameter();
            version.setName(OnapCommandConstants.DEFAULT_PARAMETER_VERSION);
            version.setValue(true);
            parameters.add(version);
            OnapCommandParameter hlp = new OnapCommandParameter();
            hlp.setName(OnapCommandConstants.DEFAULT_PARAMETER_HELP);
            hlp.setValue(false);
            parameters.add(hlp);

            OnapCommand sample = givenOnapCommand("sample-test");
            sample.setParameters(parameters);
            sample.execute();
            OnapCommandResult onapCommandResult = sample.execute();
            assertEquals("open-cli::test",onapCommandResult.getOutput());
        } catch (OnapCommandException e) {
            fail();
        }
    }

    @Test
    public void sampleTestHelp() {
        try {
            OnapCommandParameter ver = new OnapCommandParameter();
            ver.setName(OnapCommandConstants.DEFAULT_PARAMETER_HELP);
            ver.setValue(true);
            ver.setParameterType(OnapCommandParameterType.BOOL);

            Set<OnapCommandParameter> parameters = new HashSet<>();
            parameters.add(ver);

            OnapCommandSample sample = new OnapCommandSample();
            sample.setParameters(parameters);
            sample.execute();
            OnapCommandResult onapCommandResult = sample.execute();
            assertNotNull(onapCommandResult);
        } catch (OnapCommandException e) {
            fail();
        }
    }

    @Test
    public void shouldContainValidParametersAfterChangingDefaultValueOfSelectedParameter() throws OnapCommandException {
        // given
        Set<OnapCommandParameter> parameters = new HashSet<>();
        OnapCommandParameter booleanParameter = givenBooleanParameter(true);
        parameters.add(booleanParameter);

        // when
        OnapCommand onapCommand = givenOnapCommand(SAMPLE_TEST_COMMAND_NAME);
        onapCommand.setParameters(parameters);

        // then
        final Set<OnapCommandParameter> params = onapCommand.getParameters();
        assertEquals(EXPECTED_NUMBER_OF_COMMAND_PARAMETERS, params.size());
        assertTrue(params.contains(booleanParameter));
        final List<OnapCommandParameter> stringParams = findParameter(params, "string-param");
        verifyThatParameterHasValue(stringParams, "test");
    }

    @Test
    public void shouldContainParametersWithDefaultValuesWhenUserDoesNotChangeAnyParameter() throws OnapCommandException {
        // given
        OnapCommand onapCommand = givenOnapCommand(SAMPLE_TEST_COMMAND_NAME);

        // when
        final Set<OnapCommandParameter> params = onapCommand.getParameters();

        // then
        assertEquals(EXPECTED_NUMBER_OF_COMMAND_PARAMETERS, params.size());
        final List<OnapCommandParameter> stringParams = findParameter(params, "string-param");
        verifyThatParameterHasValue(stringParams, "test");
    }

    @Test
    public void shouldContainValidInfoState() throws OnapCommandException {
        // given
        OnapCommand onapCommand = givenOnapCommand(SAMPLE_TEST_COMMAND_NAME);

        // when
        final OnapCommandInfo info = onapCommand.getInfo();

        // then
        assertEquals("open-cli", info.getProduct());
        assertEquals("test", info.getService());
        assertEquals(OnapCommandType.CMD, info.getCommandType());
        assertEquals("Kanagaraj Manickam kanagaraj.manickam@huawei.com", info.getAuthor());
        assertEquals(OnapCommandState.EXPERIMENTAL, info.getState());
        final Map<String, String> metadata = info.getMetadata();
        assertEquals(EXPECTED_SIZE_OF_METADATA, metadata.size());
        assertEquals("honolulu", metadata.get("release"));
    }

    @Test
    public void sampleTest() {

        try {
            Set<OnapCommandParameter> parameters = new HashSet<>();
            OnapCommandParameter ver = new OnapCommandParameter();
            ver.setName(OnapCommandConstants.DEFAULT_PARAMETER_VERSION);
            ver.setValue(false);
            parameters.add(ver);
            OnapCommandParameter hlp = new OnapCommandParameter();
            hlp.setName(OnapCommandConstants.DEFAULT_PARAMETER_HELP);
            hlp.setValue(false);
            parameters.add(hlp);
            OnapCommandParameter fmt = new OnapCommandParameter();
            fmt.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_FORMAT);
            fmt.setValue("table");
            parameters.add(fmt);
            OnapCommandParameter lng = new OnapCommandParameter();
            lng.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_ATTR_LONG);
            lng.setValue(true);
            parameters.add(lng);
            OnapCommandParameter title = new OnapCommandParameter();
            title.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_NO_TITLE);
            title.setValue(true);
            parameters.add(title);
            OnapCommandParameter debug = new OnapCommandParameter();
            debug.setName(OnapCommandConstants.DEFAULT_PARAMETER_DEBUG);
            debug.setValue(true);
            parameters.add(debug);

            OnapCommand sample = givenOnapCommand("sample-test");
            sample.setParameters(parameters);
            sample.execute();

            //validate whether output attributes default value got initialized as part of execute()
            OnapCommandResultAttribute attr = sample.getResult().getRecordsMap().get("output-1");
            String attrValue = attr.getValues().get(0);
            UUID.fromString(attrValue.substring(4));
            attr = sample.getResult().getRecordsMap().get("output-2");
            attrValue = attr.getValues().get(0);
            assertEquals("Hello test !", attrValue);
        } catch (IllegalArgumentException e) {
            fail("Failed to replace the output default value on output-1");
        } catch (OnapCommandException e) {
            fail();
        }
    }

    @Test(expected = OnapCommandExecutionFailed.class)
    public void sampleTestFailure() throws OnapCommandException {
        OnapCommandSample sample = new OnapCommandSample();
        sample.failCase = true;

        Set<OnapCommandParameter> parameters = new HashSet();
        OnapCommandParameter ver = new OnapCommandParameter();
        ver.setName(OnapCommandConstants.DEFAULT_PARAMETER_VERSION);
        ver.setValue(false);
        parameters.add(ver);
        OnapCommandParameter hlp = new OnapCommandParameter();
        hlp.setName(OnapCommandConstants.DEFAULT_PARAMETER_HELP);
        hlp.setValue(false);
        parameters.add(hlp);
        OnapCommandParameter ffmt = new OnapCommandParameter();
        ffmt.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_FORMAT);
        ffmt.setValue("table");
        parameters.add(ffmt);
        OnapCommandParameter lng = new OnapCommandParameter();
        lng.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_ATTR_LONG);
        lng.setValue(true);
        parameters.add(lng);
        OnapCommandParameter titl = new OnapCommandParameter();
        titl.setName(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_NO_TITLE);
        titl.setValue(true);
        parameters.add(titl);
        OnapCommandParameter debug = new OnapCommandParameter();
        debug.setName(OnapCommandConstants.DEFAULT_PARAMETER_DEBUG);
        debug.setValue(true);
        parameters.add(debug);
        sample.setParameters(parameters);
        sample.execute();
    }

    @Test(expected = OnapCommandNotInitialized.class)
    public void sampleTestIsInitialized() throws OnapCommandException {
        OnapCommandSample sample = new OnapCommandSample(false);
        sample.execute();
    }

    private OnapCommand givenOnapCommand(String cmdName) throws OnapCommandException {
        return OnapCommandRegistrar.getRegistrar().get(cmdName);
    }

    private OnapCommandParameter givenBooleanParameter(boolean value) throws OnapCommandInvalidParameterValue {
        OnapCommandParameter booleanParameter = new OnapCommandParameter();
        booleanParameter.setName("bool-param");
        booleanParameter.setLongOption("bool");
        booleanParameter.setShortOption("b");
        booleanParameter.setValue(value);
        return booleanParameter;
    }

    private List<OnapCommandParameter> findParameter(Set<OnapCommandParameter> params, String parameterName) {
        return params.stream().filter(it -> it.getName().equals(parameterName)).collect(Collectors.toList());
    }

    private void verifyThatParameterHasValue(List<OnapCommandParameter> stringParams, String expectedValue) {
        assertEquals(1, stringParams.size());
        assertEquals(expectedValue, stringParams.get(0).getValue());
    }
}
