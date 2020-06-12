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

package org.onap.cli.fw.http.conf;

/**
 * OnapCommandHttpConstants.
 *
 */
public class OnapCommandHttpConstants {
    public static final String CONF = "open-cli-http.properties";
    public static final String OPEN_IGNORE_AUTH = "cli.ignore_auth";
    public static final String HTTP_API_KEY_USE_COOKIES = "cli.http.api_key_use_cookies";
    public static final String HTTP_SECTIONS = "cli.schema.http.sections";
    public static final String HTTP_MANDATORY_SECTIONS = "cli.schema.http.sections.mandatory";
    public static final String HTTP_REQUEST_PARAMS = "cli.schema.http.request.sections";
    public static final String HTTP_REQUEST_MANDATORY_PARAMS = "cli.schema.http.request.sections.mandatory";
    public static final String HTTP_METHODS = "cli.schema.http.request.method.values";
    //http connection
    public static final String SSLCONTEST_TLS = "TLSV1.2";
    public static final String APPLICATION_JSON = "application/json";
    public static final String DEFAULT_PARAMETER_HTTP_FILE_NAME = "default_input_parameters_http.yaml";
    public static final String DEAFULT_PARAMETER_USERNAME = "host-username";
    public static final String DEAFULT_PARAMETER_PASSWORD = "host-password"; // NOSONAR
    public static final String DEAFULT_PARAMETER_HOST_URL = "host-url";
    public static final String DEFAULT_PARAMETER_NO_AUTH = "no-auth";
    public static final String HTTP_SCHEMA_PROFILE = "http";
    //http
    public static final String HTTP = "http";
    public static final String METHOD = "method";
    public static final String SERVICE = "service";
    public static final String VERSION = "version";
    public static final String BASE_PATH = "base_path";
    public static final String AUTH = "auth";
    public static final String AUTH_NONE = "none";
    public static final String AUTH_BASIC = "basic";
    public static final String MODE = "mode";
    public static final String MODE_DIRECT = "direct";
    public static final String MODE_CATALOG = "catalog";
    public static final String REQUEST = "request";
    public static final String URI = "uri";
    public static final String BODY = "body";
    public static final String METHOD_TYPE = METHOD;
    public static final String POST = "post";
    public static final String GET = "get";
    public static final String DELETE = "delete";
    public static final String PUT = "put";
    public static final String HEAD = "head";
    public static final String HEADERS = "headers";
    public static final String QUERIES = "queries";
    public static final String COOKIES = "cookies";
    public static final String SUCCESS_CODES = "success_codes";
    public static final String RESULT_MAP = "result_map";
    public static final String SAMPLE_RESPONSE = "sample_response";
    public static final String MULTIPART_ENTITY_NAME = "multipart_entity_name";
    public static final String MULTIPART = "multipart";
    public static final String HTTP_SECTION_EMPTY = "Http Section cann't be null or empty";
    public static final String HTTP_BODY_SECTION_EMPTY = "http body section under 'request:' cann't be null or empty";
    public static final String HTTP_BODY_FAILED_PARSING = "The http body json is failed to parse";
    public static final String HTTP_BODY_JSON_EMPTY = "The http body json cann't be null or empty";
    public static final String HTTP_SUCCESS_CODE_INVALID = "Invalid http success code.";
    public static final String HTTP_SAMPLE_RESPONSE_EMPTY = "Sample response cann't be null or empty";
    public static final String HTTP_SAMPLE_RESPONSE_FAILED_PARSING = "The http Sample response json is failed to parse.";
    //auth plugin
    public static final String AUTH_SERVICE_AUTHORIZATION = "Authorization";
    public static final String AUTH_SERVICE_LOGIN = "login";

    //catalog plugin
    public static final String CATALOG_SERVICE_NAME = "catalog-service-name";
    public static final String CATALOG_SERVICE_VERSION = "catalog-service-version";
    public static final String CATALOG_SERVICE_BASE_PATH = "catalog-service-base-path";
    public static final String CATALOG_SERVICE_HOST_URL = "catalog-service-host-url";

    public static final String AUTH_VALUES = "cli.schema.http.service.auth.values";
    public static final String MODE_VALUES = "cli.schema.http.service.mode.values";
    public static final String SERVICE_PARAMS_LIST = "cli.schema.http.service.sections";
    public static final String SERVICE_PARAMS_MANDATORY_LIST = "cli.schema.http.service.sections.mandatory";

    public static final String DEFAULT_PARAMETER_NO_CATALOG = "no-catalog";

    //context param
    public static final String CONTEXT = "context";
    public static final String CONTEXT_REMOVE_EMPTY_JSON_NODES = "remove_empty_node";
    public static final String __BODY__ = "__body__"; //NOSONAR

    // moco server const
    public static final String VERIFY_MOCO_HOST = "cli.verify.host";
    public static final String VERIFY_MOCO_PORT = "cli.verify.port";

    public static final String VERIFY_HOST_PARAMETER_OPT = DEAFULT_PARAMETER_HOST_URL;
    public static final String VERIFY_NO_AUTH_PARAMETER_OPT = DEFAULT_PARAMETER_NO_AUTH;

    public static final String VERIFY_REQUEST_URI = URI;
    public static final String VERIFY_RESPONSE_STATUS = "status";
    public static final String VERIFY_RESPONSE_JSON = "json";
    public static final String VERIFY_REQUEST = REQUEST;
    public static final String VERIFY_RESPONSE = "response";
    public static final String VERIFY_CONTENT_TYPE = "Content-Type";
    public static final String VERIFY_CONTENT_TYPE_VALUE = APPLICATION_JSON;
    public static final String VERIFY_DISABLE_MOCKING = "DISABLE_MOCKING";

    private OnapCommandHttpConstants() {
        //as per coding standard !
    }
}


