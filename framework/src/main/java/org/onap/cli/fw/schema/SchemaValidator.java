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

package org.onap.cli.fw.schema;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;

import org.onap.cli.fw.error.OnapCommandInvalidSchema;

import static org.onap.cli.fw.conf.Constants.*;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;

/**
 * Schema Validation impl.
 *
 */
public class SchemaValidator extends AbstractSchemaValidate {

    /**
     * Constructor.
     *
     * @param schemaFile
     *            file
     * @throws OnapCommandInvalidSchema
     *             exception
     */
    public SchemaValidator(File schemaFile) throws OnapCommandInvalidSchema {
        super(schemaFile);
    }

    public SchemaValidator(String schemaFile) throws OnapCommandInvalidSchema {
        super(schemaFile);
    }

    @Override
    protected void validateSpecificSchema(SchemaType type) throws OnapCommandInvalidSchema {
        if (type.equals(SchemaType.HTTP)) {
            validateHttpParameters();
        }
    }

    @SuppressWarnings("unchecked")
    private void validateHttpParameters() {

        Map<String, Object> httpMap = (Map<String, Object>) yamlMap.get(HTTP);

        if (httpMap == null || httpMap.isEmpty()) {
            schemaErrors.add(HTTP_SECTION_EMPTY);
            return;
        }

//        validateMandatoryParams(httpMap, HTTP_SECTIONS, HTTP_MANDATORY_SECTIONS, PARAMETERS);

        Map<String, Object> requestMap = (Map<String, Object>) httpMap.get(REQUEST);

        if (requestMap != null && !requestMap.isEmpty()) {
            validateHttpRequestParams(requestMap);
        } else {
            schemaErrors.add(SchemaValidate.emptySection(REQUEST));
        }

        List<Object> requestSuccessCodes = (List<Object>) httpMap.get(SUCCESS_CODES);
        if (requestSuccessCodes != null && !requestSuccessCodes.isEmpty()) {
            validateHttpSccessCodes(requestSuccessCodes);
        } else {
            schemaErrors.add(SchemaValidate.emptySection(SUCCESS_CODES));
        }

        Map<String, Object> resultMap = (Map<String, Object>) httpMap.get(RESULT_MAP);

        if (resultMap != null && !resultMap.isEmpty()) {
            validateHttpResultMapping(resultMap);
        }

        Object object = httpMap.get(SAMPLE_RESPONSE);
        if (object != null) {
            if (object instanceof String) {
                schemaErrors.add(HTTP_SAMPLE_RESPONSE_FAILED_PARSING);
            } else {
                validateSampleResponse((Map<String, Object>) object);
            }
        }
    }

    private void validateHttpRequestParams(Map<String, Object> requestMap) {

        if (requestMap == null || requestMap.isEmpty()) {
            return;
        }
        // validate mandatory parameters
//        validateMandatoryParams(requestMap, HTTP_REQUEST_PARAMS, HTTP_REQUEST_MANDATORY_PARAMS, REQUEST);

        // Validate method types
        String method = (String) requestMap.get(METHOD);
        if (method != null && !method.isEmpty()) {
            if (!HTTP_METHODS.contains(method.toLowerCase())) {
                schemaErrors.add(SchemaValidate.invalidType(REQUEST, METHOD, HTTP_METHODS));
            }
        } else {
            schemaErrors.add("Http request method cann't be null or empty");
        }

        Set<String> requestParams = getRequestParams();

        // validate uriParams
        Set<String> uriParams = validateHttpUri(requestMap);

        // validate body
        Set<String> bodyParams = validateHttpBody(requestMap);

        // validate header
        Set<String> headerParams = validateHttpHeaders(requestMap);

        // validate queries
        Set<String> queryParams = validateHttpQueries(requestMap);

        for (String declaredParam : requestParams) {
            if (!uriParams.contains(declaredParam) && !bodyParams.contains(declaredParam)
                    && !headerParams.contains(declaredParam) && !queryParams.contains(declaredParam)) {
                schemaErrors.add(SchemaValidate.parameterNotMapped(declaredParam));
            }
        }

        Set<String> totalParams = new HashSet<>();
        totalParams.addAll(uriParams);
        totalParams.addAll(bodyParams);
        totalParams.addAll(queryParams);
        totalParams.addAll(headerParams);

        for (String definedParam : totalParams) {
            if (!requestParams.contains(definedParam)) {
                if (uriParams.contains(definedParam)) {
                    schemaErrors.add(SchemaValidate.invalidRequestParam(URI, definedParam));
                } else if (bodyParams.contains(definedParam)) {
                    schemaErrors.add(SchemaValidate.invalidRequestParam(BODY, definedParam));
                } else if (queryParams.contains(definedParam)) {
                    schemaErrors.add(SchemaValidate.invalidRequestParam(QUERIES, definedParam));
                } else if (headerParams.contains(definedParam)) {
                    schemaErrors.add(SchemaValidate.invalidRequestParam(HEADERS, definedParam));
                }
            }
        }

    }

    private Set<String> validateHttpUri(Map<String, Object> requestMap) {
        Set<String> uriParamNames = new HashSet<>();
        String uri = (String) requestMap.get(URI);
        if (uri == null || uri.isEmpty()) {
            schemaErrors.add(SchemaValidate.emptySection(URI));
            return uriParamNames;
        }
        parseParameters(uri, uriParamNames);
        return uriParamNames;
    }

    @SuppressWarnings("unchecked")
    private Set<String> validateHttpHeaders(Map<String, Object> requestMap) {

        Map<String, Object> headers = (Map<String, Object>) requestMap.get(HEADERS);
        Set<String> headerParamNames = new HashSet<>();
        if (headers != null) {
            for (Entry<String, Object> entry : headers.entrySet()) {
                parseParameters(String.valueOf(entry.getValue()), headerParamNames);
            }
        }
        return headerParamNames;
    }

    @SuppressWarnings("unchecked")
    private Set<String> validateHttpQueries(Map<String, Object> requestMap) {
        Map<String, Object> queries = (Map<String, Object>) requestMap.get(QUERIES);
        Set<String> queryParamNames = new HashSet<>();
        if (queries != null) {
            for (Entry<String, Object> entry : queries.entrySet()) {
                parseParameters(String.valueOf(entry.getValue()), queryParamNames);
            }
        }
        return queryParamNames;
    }

    private Set<String> validateHttpBody(Map<String, Object> requestMap) {
        Set<String> bodyParamNames = new HashSet<>();
        Object bodyString = requestMap.get(BODY);
        if (bodyString == null) {
            return bodyParamNames;
        }

        String body = String.valueOf(bodyString);
        JSONObject obj = null;
        try {
            obj = new ObjectMapper().readValue(body, JSONObject.class);
        } catch (IOException e1) { // NOSONAR
            schemaErrors.add(HTTP_BODY_FAILED_PARSING);
        }
        if (obj == null || "".equals(obj.toString())) {
            schemaErrors.add(HTTP_BODY_JSON_EMPTY);
        }
        parseParameters(body, bodyParamNames);

        return bodyParamNames;
    }

    private void parseParameters(String line, Set<String> paramNames) {

        int currentIdx = 0;
        while (currentIdx < line.length()) {
            int idxS = line.indexOf("${", currentIdx);
            if (idxS == -1) {
                break;
            }
            int idxE = line.indexOf("}", idxS);
            String paramName = line.substring(idxS + 2, idxE);
            paramNames.add(paramName.trim());

            currentIdx = idxE + 1;
        }

    }

    private void validateHttpSccessCodes(List<Object> requestSuccessCodes) {

        for (Object successCode : requestSuccessCodes) {
            Integer code = (Integer) successCode;
            if (code < 200 || code >= 300) {
                schemaErrors.add(HTTP_SUCCESS_CODE_INVALID);
            }
        }

    }

    private void validateHttpResultMapping(Map<String, Object> resultMap) {
//        Set<String> resultAttributes = getResultAttributes();
//
//        // Validate if all result attributes are used in the result mapping
//        for (String attribute : resultAttributes) {
//            if (!resultMap.containsKey(attribute)) {
//                schemaErrors.add(SchemaValidate.missingInResultMap(attribute));
//            }
//        }
//
//        // Validate if all result mapping keys are defined in the result attributes
//        for (Entry<String, Object> entry : resultMap.entrySet()) {
//            if (!resultAttributes.contains(entry.getKey())) {
//                schemaErrors.add(SchemaValidate.missingInResultAttribute(entry.getKey()));
//            }
//        }
    }

    private void validateSampleResponse(Map<String, Object> sampleResponseBodyMap) {

        // validate the json
        Object json = sampleResponseBodyMap.get(BODY);
        if (json == null) {
            schemaErrors.add(HTTP_SAMPLE_RESPONSE_EMPTY);
            return;
        }
        String jsonString = json.toString();
        try {
            if (jsonString.startsWith("[")) {
                new ObjectMapper().readValue(jsonString, JSONObject[].class);
            } else {
                new ObjectMapper().readValue(jsonString, JSONObject.class);
            }
        } catch (IOException e1) { // NOSONAR
            schemaErrors.add(HTTP_SAMPLE_RESPONSE_FAILED_PARSING);
        }
    }

}
