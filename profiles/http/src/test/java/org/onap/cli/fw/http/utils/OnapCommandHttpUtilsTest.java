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

package org.onap.cli.fw.http.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.onap.cli.fw.http.conf.OnapCommandHttpConstants;
import org.onap.cli.fw.http.connect.HttpInput;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.OnapCommandParameterType;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.lang.ClassLoader.getSystemResource;
import static java.nio.file.Files.readAllBytes;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.onap.cli.fw.input.OnapCommandParameterType.*;
import static org.onap.cli.fw.input.OnapCommandParameterType.ARRAY;
import static org.onap.cli.fw.input.OnapCommandParameterType.BINARY;
import static org.onap.cli.fw.input.OnapCommandParameterType.BOOL;

public class OnapCommandHttpUtilsTest {

    private static final String MULTIPART_ENTITY_NAME = "multipart_entity_name";

    @Test
    public void populateParameters_shouldSetBinaryDataToTrueWhenAtLeastOneParameterIsBinary() throws Exception {
        Map<String, OnapCommandParameter> parameters = new HashMap<>();
        parameters.put("array", createCommandParameter(ARRAY, asList("1", "2", "3")));
        parameters.put("binary", createCommandParameter(BINARY, asList("1", "2", "3")));

        HttpInput actualHttpInput = OnapCommandHttpUtils.populateParameters(parameters, new HttpInput());

        assertTrue(actualHttpInput.isBinaryData());
    }

    @Test
    public void populateParameters_shouldSetBinaryDataToFalseWhenThereIsNoBinaryParameter() throws Exception {
        Map<String, OnapCommandParameter> parameters = new HashMap<>();
        parameters.put("array", createCommandParameter(ARRAY, asList("1", "2", "3")));
        parameters.put("json", createCommandParameter(JSON, "{}"));

        HttpInput actualHttpInput = OnapCommandHttpUtils.populateParameters(parameters, new HttpInput());

        assertFalse(actualHttpInput.isBinaryData());
    }

    @Test
    public void populateParameters_shouldSetMultipartEntityNameFromOriginalHttpInput() throws Exception {
        HttpInput httpInput = new HttpInput();
        httpInput.setMultipartEntityName(MULTIPART_ENTITY_NAME);

        HttpInput actualHttpInput = OnapCommandHttpUtils.populateParameters(new HashMap<>(), httpInput);

        assertEquals(MULTIPART_ENTITY_NAME, actualHttpInput.getMultipartEntityName());
    }

    @Test
    public void populateParameters_shouldReplaceInputParametersInHttpInputBody() throws Exception {
        Map<String, OnapCommandParameter> parameters = new HashMap<>();
        parameters.put("boolean", createCommandParameter(BOOL, Boolean.FALSE));

        HttpInput httpInput = new HttpInput();
        httpInput.setBody("line ${boolean}");

        HttpInput actualHttpInput = OnapCommandHttpUtils.populateParameters(parameters, httpInput);

        assertEquals("line false", actualHttpInput.getBody());
    }

    @Test
    public void populateParameters_shouldReplaceInputParametersInHttpInputUri() throws Exception {
        Map<String, OnapCommandParameter> parameters = new HashMap<>();
        parameters.put("boolean", createCommandParameter(BOOL, Boolean.FALSE));

        HttpInput httpInput = new HttpInput();
        httpInput.setUri("http://aaa.bb.${boolean}");

        HttpInput actualHttpInput = OnapCommandHttpUtils.populateParameters(parameters, httpInput);

        assertEquals("http://aaa.bb.false", actualHttpInput.getUri());
    }

    @Test
    public void populateParameters_shouldPutMethodInLowerCaseLetters() throws Exception {
        Map<String, OnapCommandParameter> parameters = new HashMap<>();
        parameters.put("boolean", createCommandParameter(BOOL, Boolean.FALSE));

        HttpInput httpInput = new HttpInput();
        httpInput.setMethod("POST");

        HttpInput actualHttpInput = OnapCommandHttpUtils.populateParameters(parameters, httpInput);

        assertEquals("post", actualHttpInput.getMethod());
    }

    @Test
    public void populateParameters_shouldReplaceInputParametersInHttpInputRequestHeaders() throws Exception {
        Map<String, OnapCommandParameter> parameters = new HashMap<>();
        parameters.put("boolean", createCommandParameter(BOOL, Boolean.FALSE));

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("header", "header value ${boolean}");

        HttpInput httpInput = new HttpInput();
        httpInput.setReqHeaders(requestHeaders);

        HttpInput actualHttpInput = OnapCommandHttpUtils.populateParameters(parameters, httpInput);

        assertEquals("header value false", actualHttpInput.getReqHeaders().get("header"));
    }

    @Test
    public void populateParameters_shouldReplaceInputParametersInHttpInputRequestQueries() throws Exception {
        Map<String, OnapCommandParameter> parameters = new HashMap<>();
        parameters.put("boolean", createCommandParameter(BOOL, Boolean.FALSE));

        Map<String, String> requestQueries = new HashMap<>();
        requestQueries.put("query", "query value ${boolean}");

        HttpInput httpInput = new HttpInput();
        httpInput.setReqQueries(requestQueries);

        HttpInput actualHttpInput = OnapCommandHttpUtils.populateParameters(parameters, httpInput);

        assertEquals("query value false", actualHttpInput.getReqQueries().get("query"));
    }

    @Test
    public void populateParameters_shouldRemoveEmptyJsonNodes() throws Exception {
        Map<String, String> context = new HashMap<>();
        context.put(OnapCommandHttpConstants.CONTEXT_REMOVE_EMPTY_JSON_NODES, "true");

        HttpInput httpInput = new HttpInput();
        httpInput.setContext(context);
        httpInput.setBody("{\"request\":\"\"}");

        HttpInput actualHttpInput = OnapCommandHttpUtils.populateParameters(new HashMap<>(), httpInput);

        assertEquals("{}", actualHttpInput.getBody());
    }

    @Test
    public void populateParameters_shouldNotRemoveEmptyJsonNodes() throws Exception {
        HttpInput httpInput = new HttpInput();
        httpInput.setBody("{\"request\":\"\"}");

        HttpInput actualHttpInput = OnapCommandHttpUtils.populateParameters(new HashMap<>(), httpInput);

        assertEquals("{\"request\":\"\"}", actualHttpInput.getBody());
    }

    @Test
    public void normalizeJson_shouldRemoveEmptyNodes() throws Exception {
        String sample = loadFromResources("example.json");
        String expectedResult = loadFromResources("normalized_example.json");

        assertEquals(toOneLineJson(expectedResult), OnapCommandHttpUtils.normalizeJson(sample));
    }

    private String toOneLineJson(String prettyJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(prettyJson, JsonNode.class);
        return jsonNode.toString();
    }

    private String loadFromResources(String fileName) throws Exception {
        return new String(readAllBytes(Paths.get(getSystemResource(fileName).toURI())));
    }

    private OnapCommandParameter createCommandParameter(OnapCommandParameterType type, Object value) throws Exception {
        OnapCommandParameter parameter = new OnapCommandParameter();
        parameter.setParameterType(type);
        parameter.setValue(value);
        return parameter;
    }
}