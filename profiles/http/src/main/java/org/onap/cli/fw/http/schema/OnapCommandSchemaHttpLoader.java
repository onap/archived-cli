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

package org.onap.cli.fw.http.schema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.cmd.OnapCommandType;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidSchema;
import org.onap.cli.fw.error.OnapCommandNotFound;
import org.onap.cli.fw.http.auth.OnapCommandHttpService;
import org.onap.cli.fw.http.cmd.OnapHttpCommand;
import org.onap.cli.fw.http.conf.OnapCommandHttpConstants;
import org.onap.cli.fw.http.connect.HttpInput;
import org.onap.cli.fw.http.error.OnapCommandHttpInvalidResultMap;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;
import org.onap.cli.fw.schema.OnapCommandSchemaLoader;
import org.onap.cli.fw.utils.JsonUtil;
import org.onap.cli.fw.utils.OnapCommandUtils;

public class OnapCommandSchemaHttpLoader {

    private static final String ATTRIBUTE = "Attribute '";

    private OnapCommandSchemaHttpLoader() {
        // to follow standards !
    }

    public static List<String> loadHttpSchema(OnapHttpCommand cmd, String schemaName, boolean includeDefault,
                                          boolean validateSchema) throws OnapCommandException {
        try {
            List<String> errors = new ArrayList<>();
            if (includeDefault) {
                Map<String, ?> defaultParameterMap = OnapCommandSchemaLoader.validateSchemaVersion(OnapCommandHttpConstants.DEFAULT_PARAMETER_HTTP_FILE_NAME, cmd.getSchemaVersion());

                //mrkanag default_parameter is supported only for parameters.
                if (defaultParameterMap.containsKey(OnapCommandConstants.INFO)) {
                    defaultParameterMap.remove(OnapCommandConstants.INFO);
                }

                errors.addAll(OnapCommandSchemaLoader.parseSchema(cmd, defaultParameterMap, validateSchema));
            }

            Map<String, List<Map<String, String>>> commandYamlMap =
                    (Map<String, List<Map<String, String>>>)OnapCommandSchemaLoader.validateSchemaVersion(schemaName, cmd.getSchemaVersion());

            errors.addAll(parseHttpSchema(cmd, commandYamlMap, validateSchema));

            return errors;

        } catch (OnapCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new OnapCommandInvalidSchema(schemaName, e);
        }
    }

    /**
     * Load the schema.
     *
     * @param cmd
     *            OnapHttpCommand
     * @param schemaName
     *            schema name
     * @throws OnapCommandException
     *             on error
     */
    public static ArrayList<String> parseHttpSchema(OnapHttpCommand cmd,
                                                    final Map<String, ?> values,
                                                    boolean validate) throws OnapCommandException {
        ArrayList<String> errorList = new ArrayList<>();
        try {
            Map<String, ?> valMap = (Map<String, ?>) values.get(OnapCommandHttpConstants.HTTP);

            if (valMap != null) {
                if (validate) {
                    OnapCommandUtils.validateTags(errorList, valMap, OnapCommandConfig.getCommaSeparatedList(OnapCommandHttpConstants.HTTP_SECTIONS),
                            OnapCommandConfig.getCommaSeparatedList(OnapCommandHttpConstants.HTTP_MANDATORY_SECTIONS), OnapCommandHttpConstants.HTTP);
                    errorList.addAll(validateHttpSchemaSection(values));
                }
                for (Map.Entry<String, ?> entry1 : valMap.entrySet()) {
                    String key1 = entry1.getKey();

                    switch (key1) {
                        case OnapCommandHttpConstants.REQUEST:
                            Map<String, ?> map = (Map<String, ?>) valMap.get(key1);

                            for (Map.Entry<String, ?> entry2 : map.entrySet()) {
                                try {
                                    String key2 = entry2.getKey();

                                    switch (key2) {
                                        case OnapCommandHttpConstants.URI:
                                            Object obj = map.get(key2);
                                            cmd.getInput().setUri(obj.toString());
                                            break;
                                        case OnapCommandHttpConstants.METHOD_TYPE:
                                            Object method = map.get(key2);
                                            cmd.getInput().setMethod(method.toString());
                                            break;
                                        case OnapCommandHttpConstants.BODY:
                                            Object body = map.get(key2);

                                            if (body instanceof String) {
                                                cmd.getInput().setBody(body.toString());
                                            } else {
                                                //check for multipart
                                                Map <String, Object> multipartBody = (Map<String, Object>) body;
                                                List <Object> multiparts = (List<Object>) multipartBody.get(OnapCommandHttpConstants.MULTIPART);

                                                for (Object part: multiparts ) {
                                                    HttpInput.Part partO = new HttpInput.Part();
                                                    Map<String, String> partMap = (Map<String, String>) part;
                                                    partO.setName((String) partMap.get("name"));
                                                    partO.setContent((String)partMap.get("content"));
                                                    if (partMap.get("type") != null && ((String)partMap.get("type")).equalsIgnoreCase("file")) {
                                                        partO.setBinary(true);
                                                    }

                                                    cmd.getInput().getMultiparts().add(partO);
                                                }
                                            }

                                            break;
                                        case OnapCommandHttpConstants.HEADERS:
                                            Map<String, String> head = (Map<String, String>) map.get(key2);
                                            cmd.getInput().setReqHeaders(head);
                                            break;
                                        case OnapCommandHttpConstants.QUERIES:
                                            Map<String, String> query = (Map<String, String>) map.get(key2);

                                            cmd.getInput().setReqQueries(query);
                                            break;
                                        case OnapCommandHttpConstants.CONTEXT:
                                            Map<String, Object> context = (Map<String, Object>) map.get(key2);

                                            for (String key: context.keySet()) {
                                                switch (key) {
                                                    case OnapCommandHttpConstants.CONTEXT_REMOVE_EMPTY_JSON_NODES:
                                                        Boolean flag = (Boolean) context.get(OnapCommandHttpConstants.CONTEXT_REMOVE_EMPTY_JSON_NODES);
                                                        cmd.getInput().getContext().put(OnapCommandHttpConstants.CONTEXT_REMOVE_EMPTY_JSON_NODES, flag.toString());
                                                        break;
                                                }
                                            }


                                            break;
                                        case OnapCommandHttpConstants.MULTIPART_ENTITY_NAME:
                                            Object multipartEntityName = map.get(key2);
                                            cmd.getInput().setMultipartEntityName(multipartEntityName.toString());
                                            break;
                                    }
                                }catch (Exception ex) {
                                    OnapCommandUtils.throwOrCollect(new OnapCommandInvalidSchema(cmd.getSchemaName(), ex), errorList, validate);
                                }
                            }
                            break;

                        case OnapCommandHttpConstants.SERVICE:
                            Map<String, String> serviceMap = (Map<String, String>) valMap.get(key1);

                            if (serviceMap != null) {
                                if (validate) {
                                    OnapCommandUtils.validateTags(errorList, (Map<String, Object>) valMap.get(key1),
                                            OnapCommandConfig.getCommaSeparatedList(OnapCommandHttpConstants.SERVICE_PARAMS_LIST),
                                            OnapCommandConfig.getCommaSeparatedList(OnapCommandHttpConstants.SERVICE_PARAMS_MANDATORY_LIST), OnapCommandHttpConstants.SERVICE);

                                    HashMap<String, String> validationMap = new HashMap<>();
                                    validationMap.put(OnapCommandHttpConstants.AUTH, OnapCommandHttpConstants.AUTH_VALUES);
                                    validationMap.put(OnapCommandHttpConstants.MODE, OnapCommandHttpConstants.MODE_VALUES);

                                    for (Map.Entry<String, String> secKey : validationMap.entrySet()) {
                                        if (serviceMap.containsKey(secKey.getKey())) {
                                            Object obj = serviceMap.get(secKey.getKey());
                                            if (obj == null) {
                                                errorList.add(ATTRIBUTE + secKey.getKey() + "' under '" + OnapCommandHttpConstants.SERVICE + "' is empty");
                                            } else {
                                                String value = String.valueOf(obj);
                                                if (!OnapCommandConfig.getCommaSeparatedList(validationMap.get(secKey.getKey())).contains(value)) {
                                                    errorList.add(ATTRIBUTE + secKey.getKey() + "' contains invalid value. Valide values are "
                                                            + OnapCommandConfig.getCommaSeparatedList(validationMap.get(key1))); //
                                                }
                                            }
                                        }
                                    }
                                }

                                OnapCommandHttpService srv = new OnapCommandHttpService();

                                for (Map.Entry<String, String> entry : serviceMap.entrySet()) {
                                    String key = entry.getKey();

                                    switch (key) {
                                        case OnapCommandConstants.NAME:
                                            srv.setName(serviceMap.get(key));
                                            break;

                                        case OnapCommandHttpConstants.VERSION:
                                            srv.setVersion(serviceMap.get(key));
                                            break;

                                        case OnapCommandHttpConstants.AUTH:
                                            Object obj = serviceMap.get(key);
                                            srv.setAuthType(obj.toString());
                                            break;

                                        case OnapCommandHttpConstants.MODE:
                                            Object mode = serviceMap.get(key);
                                            srv.setMode(mode.toString());
                                            break;
                                    }
                                }

                                cmd.setService(srv);
                            }
                            break;

                        case OnapCommandHttpConstants.SUCCESS_CODES:
                            if (validate) {
                                validateHttpSccessCodes(errorList, (List<Object>) valMap.get(key1));
                            }
                            cmd.setSuccessStatusCodes((ArrayList) valMap.get(key1));
                            break;

                        case OnapCommandHttpConstants.RESULT_MAP:
                            if (validate) {
                                validateHttpResultMap(errorList, values);
                            }
                            cmd.setResultMap((Map<String, String>) valMap.get(key1));
                            break;

                        case OnapCommandHttpConstants.SAMPLE_RESPONSE:
                            // (mrkanag) implement sample response handling
                            break;
                    }
                }
            }
        }catch (OnapCommandException e) {
            OnapCommandUtils.throwOrCollect(e, errorList, validate);
        }

        //Handle the parameters for auth:
        // for commands, copy params from login command to this command
        if (!cmd.getService().isNoAuth()) {
            if (cmd.getInfo().getCommandType().equals(OnapCommandType.AUTH)) {
                OnapCommandUtils.throwOrCollect(new OnapCommandInvalidSchema(
                        cmd.getSchemaName(), "For auth type commands, http->service->auth should be none"),
                        errorList,
                        validate);
            } else {
                OnapCommand login = OnapCommandSchemaHttpLoader.findAuthCommand(cmd, "login");
                OnapCommandUtils.copyParamSchemasFrom(login, cmd);
            }
        } else {
            //with service->auth: none,
            //normal cmd: ignore all auth parms
            if (!cmd.getInfo().getCommandType().equals(OnapCommandType.AUTH)) {
                cmd.getParametersMap().get(OnapCommandHttpConstants.DEAFULT_PARAMETER_USERNAME).setInclude(false);
                cmd.getParametersMap().get(OnapCommandHttpConstants.DEAFULT_PARAMETER_PASSWORD).setInclude(false);
                cmd.getParametersMap().get(OnapCommandHttpConstants.DEFAULT_PARAMETER_NO_AUTH).setInclude(false);
            } else {
                //auth: login and logout commands, ignore no-auth
                cmd.getParametersMap().get(OnapCommandHttpConstants.DEFAULT_PARAMETER_NO_AUTH).setInclude(false);
                //auth: only for logout commands, ignore username and password too
                if (!cmd.getName().endsWith(OnapCommandHttpConstants.AUTH_SERVICE_LOGIN)) {
                    cmd.getParametersMap().get(OnapCommandHttpConstants.DEAFULT_PARAMETER_USERNAME).setInclude(false);
                    cmd.getParametersMap().get(OnapCommandHttpConstants.DEAFULT_PARAMETER_PASSWORD).setInclude(false);
                }
            }
        }

        return errorList;
    }

    public static List<String> validateHttpSchemaSection(Map<String, ?> values) {
        ArrayList<String> errorList = new ArrayList<>();
        Map<String, ?> map = (Map<String, ?>) values.get(OnapCommandHttpConstants.HTTP);
        Map<String, Object> requestMap = (Map<String, Object>) map.get(OnapCommandHttpConstants.REQUEST);

        if (requestMap != null && !requestMap.isEmpty()) {
            OnapCommandUtils.validateTags(errorList, requestMap, OnapCommandConfig.getCommaSeparatedList(OnapCommandHttpConstants.HTTP_REQUEST_PARAMS),
                    OnapCommandConfig.getCommaSeparatedList(OnapCommandHttpConstants.HTTP_REQUEST_MANDATORY_PARAMS), OnapCommandHttpConstants.REQUEST);
            String method = (String) requestMap.get(OnapCommandHttpConstants.METHOD);
            if (method != null && !method.isEmpty()) {
                if (!OnapCommandConfig.getCommaSeparatedList(OnapCommandHttpConstants.HTTP_METHODS).contains(method.toLowerCase())) {
                    errorList.add(ATTRIBUTE + OnapCommandHttpConstants.METHOD + "' under '" + OnapCommandHttpConstants.REQUEST + "' is invalid, correct types are "
                            + OnapCommandConfig.getCommaSeparatedList(OnapCommandHttpConstants.HTTP_METHODS).toString());
                }
            } else {
                errorList.add("Http request method cann't be null or empty");
            }

            Set<String> requestParams = getRequestParams(values);

            Set<String> uriParams = validateHttpUri(errorList, requestMap);

            Set<String> bodyParams = validateHttpBody(errorList, requestMap);

            Set<String> headerParams = validateHttpHeaders(requestMap);

            Set<String> queryParams = validateHttpQueries(requestMap);

            HashSet<String> totoalParams = new HashSet<>(uriParams);
            totoalParams.addAll(bodyParams);
            totoalParams.addAll(headerParams);
            totoalParams.addAll(queryParams);

            List<String> nonDeclaredParams = totoalParams.stream().filter(param -> !requestParams.contains(param))
                    .collect(Collectors.toList());

            nonDeclaredParams.stream().forEach(p -> errorList.add("The parameter '" + p
                    + "' declared under 'parameters:' section is not mapped into request section."));
        } else {
            errorList.add(OnapCommandUtils.emptySection(OnapCommandHttpConstants.REQUEST));
        }
        return errorList;
    }

    public static void validateHttpSccessCodes(List<String> errorList, List<Object> requestSuccessCodes) {

        if (requestSuccessCodes == null || requestSuccessCodes.isEmpty()) {
            errorList.add(OnapCommandHttpConstants.HTTP_SUCCESS_CODE_INVALID);
            return;
        }

        for (Object successCode : requestSuccessCodes) {
            Integer code = (Integer) successCode;
            if (code < 200 || code >= 300) {
                if ( code != 404) {
                    errorList.add(OnapCommandHttpConstants.HTTP_SUCCESS_CODE_INVALID);
                }
            }
        }

    }

    public static void validateHttpResultMap(List<String> errorList, Map<String, ?> values) throws OnapCommandException {
        Map<String, ?> valMap = (Map<String, ?>) values.get(OnapCommandHttpConstants.HTTP);
        List<Map<String, String>> attributes = (List<Map<String, String>>) ((Map<String, ?>)values.get(OnapCommandConstants.RESULTS)).get(OnapCommandConstants.ATTRIBUTES);
        Set<String> resultMapParams = ((Map<String, String>) valMap.get(OnapCommandHttpConstants.RESULT_MAP)).keySet();

        Set<String> resultAttNames = attributes.stream().map(map -> map.get(OnapCommandConstants.NAME))
                .collect(Collectors.toSet());

        List<String> invaliResultMapParams = resultMapParams.stream()
                .filter(p -> !resultAttNames.contains(p)).collect(Collectors.toList());
        List<String> attributesMissing = resultAttNames.stream()
                .filter(p -> !resultMapParams.contains(p)).collect(Collectors.toList());
        invaliResultMapParams.addAll(attributesMissing);

        if (!invaliResultMapParams.isEmpty()) {
            OnapCommandUtils.throwOrCollect(new OnapCommandHttpInvalidResultMap(invaliResultMapParams), errorList, true);
        }
    }

    public static Set<String> validateHttpQueries(Map<String, Object> requestMap) {
        Map<String, Object> queries = (Map<String, Object>) requestMap.get(OnapCommandHttpConstants.QUERIES);
        Set<String> queryParamNames = new HashSet<>();
        if (queries != null) {
            for (Entry<String, Object> entry : queries.entrySet()) {
                OnapCommandUtils.parseParameters(String.valueOf(entry.getValue()), queryParamNames);
            }
        }
        return queryParamNames;
    }

    public static Set<String> validateHttpHeaders(Map<String, Object> requestMap) {

        Map<String, Object> headers = (Map<String, Object>) requestMap.get(OnapCommandHttpConstants.HEADERS);
        Set<String> headerParamNames = new HashSet<>();
        if (headers != null) {
            for (Entry<String, Object> entry : headers.entrySet()) {
                OnapCommandUtils.parseParameters(String.valueOf(entry.getValue()), headerParamNames);
            }
        }
        return headerParamNames;
    }

    public static Set<String> validateHttpBody(List<String> errorList, Map<String, Object> requestMap) {
        Set<String> bodyParamNames = new HashSet<>();
        Object bodyString = requestMap.get(OnapCommandHttpConstants.BODY);
        if (bodyString == null) {
            return bodyParamNames;
        }

        String body = String.valueOf(bodyString);

        if (body == null || "".equals(body)) {
            errorList.add(OnapCommandHttpConstants.HTTP_BODY_JSON_EMPTY);
        } else {
            JsonUtil.convertJsonStringToClassType(body, JsonObject.class);
        }

        OnapCommandUtils.parseParameters(body, bodyParamNames);

        return bodyParamNames;
    }

    public static Set<String> validateHttpUri(List<String> errorList, Map<String, Object> requestMap) {
        Set<String> uriParamNames = new HashSet<>();
        String uri = (String) requestMap.get(OnapCommandHttpConstants.URI);
        if (uri == null || uri.isEmpty()) {
            errorList.add(OnapCommandUtils.emptySection(OnapCommandHttpConstants.URI));
            return uriParamNames;
        }
        OnapCommandUtils.parseParameters(uri, uriParamNames);
        return uriParamNames;
    }

    public static Set<String> getRequestParams(Map<String, ?> yamlMap) {

        Set<String> set = new HashSet<>();

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> inputParams = (List<Map<String, Object>>) yamlMap.get(OnapCommandConstants.PARAMETERS);

        if (inputParams != null) {
            for (Map<String, Object> map : inputParams) {
                for (Entry<String, Object> entry : map.entrySet()) {
                    Object key = entry.getKey();

                    if (OnapCommandConstants.NAME.equals(key)) {
                        set.add(String.valueOf(entry.getValue()));
                        break;
                    }
                }
            }
        }

        return set;
    }

    /**
        *
        * @param authAction login/logout
        * @return
        * @throws OnapCommandException
        */
       public static OnapCommand findAuthCommand(OnapHttpCommand forCmd, String authAction) throws OnapCommandException {
           OnapCommand auth = null;
           try {
               //mrkanag: fix this to discover the auth command by matching info->product & service
               auth = OnapCommandRegistrar.getRegistrar().get(
                       forCmd.getInfo().getService() + "-" +
                       forCmd.getService().getAuthType() + "-" + authAction,
                       forCmd.getInfo().getProduct());
           } catch (OnapCommandNotFound e) {  // NOSONAR
               auth = OnapCommandRegistrar.getRegistrar().get(
                       forCmd.getService().getAuthType() + "-" + authAction,
                       forCmd.getInfo().getProduct());
           }

           return auth;
       }


}
