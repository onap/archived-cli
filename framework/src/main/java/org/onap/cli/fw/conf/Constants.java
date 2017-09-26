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

package org.onap.cli.fw.conf;

/**
 * Constants.
 *
 */
public class Constants {

    // schema validation
    public static final String TOP_LEVEL_PARAMS_LIST ="cli.schema.top_level_params_list";
    public static final String TOP_LEVEL_MANDATORY_LIST ="cli.schema.top_level_mandatory_list";
    public static final String SERVICE_PARAMS_LIST ="cli.schema.service_params_list";
    public static final String SERVICE_PARAMS_MANDATORY_LIST ="cli.schema.service_params_mandatory_list";
    public static final String INPUT_PARAMS_LIST ="cli.schema.input_params_list";
    public static final String INPUT_PARAMS_MANDATORY_LIST ="cli.schema.input_params_mandatory_list";
    public static final String RESULT_PARAMS_LIST ="cli.schema.result_params_list";
    public static final String RESULT_PARAMS_MANDATORY_LIST ="cli.schema.result_params_mandatory_list";
    public static final String HTTP_SECTIONS ="cli.schema.http_sections";
    public static final String HTTP_MANDATORY_SECTIONS ="cli.schema.http_mandatory_sections";
    public static final String HTTP_REQUEST_PARAMS ="cli.schema.http_request_params";
    public static final String HTTP_REQUEST_MANDATORY_PARAMS ="cli.schema.http_request_mandatory_params";
    public static final String HTTP_METHODS ="cli.schema.http_methods";
    public static final String BOOLEAN_VALUE ="cli.schema.boolean_values";
    public static final String AUTH_VALUES="cli.schema.auth_values";
    public static final String MODE_VALUES="cli.schema.mode_values";


    public static final String SSLCONTEST_TLS = "TLSV1.2";
    public static final String APPLICATION_JSON = "application/json";

    public static final String SERVICE_NAME = "cli.service_name";

    //http
    public static final String URI = "uri";
    public static final String BODY = "body";
    public static final String METHOD_TYPE = "method";
    public static final String HEADERS = "headers";
    public static final String QUERIES = "queries";
    public static final String COOKIES = "cookies";

    public static final String HTTP = "http";
    public static final String REQUEST = "request";
    public static final String SAMPLE_RESPONSE = "sample_response";
    public static final String SUCCESS_CODES = "success_codes";
    public static final String RESULT_MAP = "result_map";

    //swagger
    public static final String EXECUTOR = "exec";

    public static final String API = "api";
    public static final String CLIENT = "client";
    public static final String ENTITY = "entity";
    public static final String METHOD = "method";
    public static final String MULTIPART_ENTITY_NAME = "multipart_entity_name";
    public static final String EXCEPTION = "exception";

    public static final String SCOPE = "scope";

    public static final String OPEN_CLI_SCHEMA_VERSION_VALUE = "1.0";
    public static final String DESCRIPTION = "description";
    public static final String COMMAND_TYPE = "type";
    public static final String SERVICE = "service";
    public static final String PARAMETERS = "parameters";
    public static final String DEFAULT_PARAMETERS = "default_parameters";
    public static final String DEFAULT_PARAMETERS_INCLUDE = "include";
    public static final String DEFAULT_PARAMETERS_EXCLUDE = "exclude";

    public static final String RESULTS = "results";

    public static final String OPEN_CLI_SCHEMA_VERSION = "open_cli_schema_version";
    public static final String NAME = "name";
    public static final String VERSION = "version";
    public static final String BASE_PATH = "base_path";
    public static final String AUTH = "auth";
    public static final String AUTH_NONE = "none";
    public static final String AUTH_BASIC = "basic";
    public static final String MODE = "mode";
    public static final String MODE_DIRECT = "direct";
    public static final String MODE_CATALOG = "catalog";

    public static final String SHORT_OPTION = "short_option";
    public static final String LONG_OPTION = "long_option";
    public static final String TYPE = "type";
    public static final String IS_OPTIONAL = "is_optional";
    public static final String DEFAULT_VALUE = "default_value";
    public static final String IS_SECURED = "is_secured";

    public static final String DIRECTION = "direction";
    public static final String ATTRIBUTES = "attributes";

    public static final String DEFAULT_PARAMETER_FILE_NAME = "default_input_parameters.yaml";

    // Common parameters used across all commands.
    public static final String DEAFULT_PARAMETER_USERNAME = "host-username";
    public static final String DEAFULT_PARAMETER_PASS_WORD = "host-password";
    public static final String DEAFULT_PARAMETER_HOST_URL = "host-url";
    public static final String DEFAULT_PARAMETER_HELP = "help";
    public static final String DEFAULT_PARAMETER_VERSION = "version";
    public static final String DEFAULT_PARAMETER_DEBUG = "debug";
    public static final String DEFAULT_PARAMETER_OUTPUT_FORMAT = "format";
    public static final String DEFAULT_PARAMETER_OUTPUT_ATTR_LONG = "long";
    public static final String DEFAULT_PARAMETER_OUTPUT_NO_TITLE = "no-title";
    public static final String DEFAULT_PARAMETER_OUTPUT_NO_AUTH = "no-auth";

    // Configuration properties
    public static final String CONF = "open-cli.properties";
    public static final String OPEN_IGNORE_AUTH = "cli.ignore_auth";
    public static final String OPEN_CLI_VERSION = "cli.version";
    public static final String OPEN_CLI_PRODUCT_VERSION = "cli.product.version";
    public static final String OPEN_CLI_PRODUCT_VERSION_ENV_NAME = "CLI_PRODUCT_VERSION";
    public static final String HTTP_API_KEY_USE_COOKIES = "cli.http.api_key_use_cookies";
    public static final String EXCLUDE_PARAMS_INTERNAL_CMD = "cli.exclude_params_internal_cmd";
    public static final String NO_AUTH_DISABLE_INCLUDE_PARAMS_EXTERNAL_CMD = "cli.no_auth_disable_include_params_external_cmd";
    public static final String NO_AUTH_ENABLE_EXCLUDE_PARAMS_EXTERNAL_CMD = "cli.no_auth_enable_exclude_params_external_cmd";
    public static final String NO_AUTH_ENABLE_INCLUDE_PARAMS_EXTERNAL_CMD = "cli.no_auth_enable_include_params_external_cmd";
    public static final String SERVICE_AUTH = "cli.service.auth";
    public static final String SERVICE_AUTH_BASIC_HTTP_HEADERS = "cli.http.basic.common_headers";

    // Used while printing the column name during PORTRAIT mode print
    public static final String PORTRAINT_COLUMN_NAME_PROPERTY = "property";
    public static final String PORTRAINT_COLUMN_NAME_VALUE = "value";

    public static final String EXTERNAL_SCHEMA_DIRECTORY = "open-cli-schema";
    public static final String EXTERNAL_YAML_PATTERN = "/**/*.yaml";
    public static final String EXTERNAL_JSON_PATTERN = "/**/*.json";
    public static final String EXTERNAL_SCHEMA_PATH_PATERN = EXTERNAL_SCHEMA_DIRECTORY + EXTERNAL_YAML_PATTERN;
    public static final String DATA_DIRECTORY = "data";
    public static final String EXTERNAL_DISCOVERY_FILE = "cli-schema.json";
    public static final String DATA_DIRECTORY_JSON_PATTERN = DATA_DIRECTORY
            + EXTERNAL_JSON_PATTERN;

    public static final String PARAMETER_TYPE_JSON = "json";
    public static final String PARAMETER_TYPE_YAML = "yaml";
    public static final String PARAMETER_TYPE_STRING = "string";
    public static final String PARAMETER_TYPE_LONG = "long";
    public static final String PARAMETER_TYPE_URL = "url";
    public static final String PARAMETER_TYPE_BOOL = "bool";
    public static final String PARAMETER_TYPE_ARRAY = "array";
    public static final String PARAMETER_TYPE_BINARY = "binary";
    public static final String PARAMETER_TYPE_MAP = "map";

    public static final String BOOLEAN_TRUE = "true";
    public static final String BOOLEAN_FALSE = "false";

    public static final String DIRECTION_PORTRAIT = "portrait";
    public static final String DIRECTION_LANDSCAPE = "landscape";

    public static final String RESULT_SCOPE_SHORT = "short";
    public static final String RESULT_SCOPE_LONG = "long";

    public static final String POST = "post";
    public static final String GET = "get";
    public static final String DELETE = "delete";
    public static final String PUT = "put";
    public static final String HEAD = "delete";

    public static final String DEFAULT_SCHEMA_FILE_NAME = "default_input_parameters.yaml";

    public static final String PRODUCT_VERSION = "product version";
    // Error message
    public static final String SCHEMA_INVALID_DEFAULT_PARAMS_SECTION = "Invalid default_parameter section";
    public static final String SCHEMA_FILE_EMPTY = "The schema file cann't be null or empty";
    public static final String SCHEMA_FILE_WRONG_EXTN = "Schema file should be '.yaml' extension";
    public static final String SCHEMA_FILE_NOT_EXIST = "Schema file doesn't exist";
    public static final String HTTP_SECTION_EMPTY = "Http Section cann't be null or empty";
    public static final String HTTP_BODY_SECTION_EMPTY = "http body section under 'request:' cann't be null or empty";
    public static final String HTTP_BODY_FAILED_PARSING = "The http body json is failed to parse";
    public static final String HTTP_BODY_JSON_EMPTY = "The http body json cann't be null or empty";
    public static final String HTTP_SUCCESS_CODE_INVALID = "Invalid http success code.";
    public static final String HTTP_SAMPLE_RESPONSE_EMPTY = "Sample response cann't be null or empty";
    public static final String HTTP_SAMPLE_RESPONSE_FAILED_PARSING = "The http Sample response json is failed to parse.";
    public static final String USE_DIRECTIVE = "use";

    public static final String PARAM_CACHE_FILE_NAME = "global-profile";

    public static final String DISCOVER_ALWAYS = "discover_always";

    public static final String SAMPLE_GEN_ENABLED = "cli.sample.gen.enable";
    public static final String SAMPLE_GEN_TARGET_FOLDER = "cli.sample.gen.target";

    public static final String SPL_ENTRY_UUID = "uuid";
    public static final String SPL_ENTRY_ENV = "env:";

    public static final String AUTH_SERVICE_AUTHORIZATION = "Authorization";

    public static final String CATALOG_SERVICE_NAME = "catalog-service-name";

    public static final String CATALOG_SERVICE_VERSION = "catalog-service-version";

    public static final String CATALOG_SERVICE_BASE_PATH = "catalog-service-base-path";

    public static final String CATALOG_SERVICE_HOST_URL = "catalog-service-host-url";

    private Constants() {
    }

}


