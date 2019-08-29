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
 * OnapCommandHttpConstants.
 *
 */
public class OnapCommandConstants {

    //config
    public static final String CONF = "open-cli.properties";
    public static final String OPEN_CLI_VERSION = "cli.version";
    public static final String TOP_LEVEL_PARAMS_LIST = "cli.schema.base.sections";
    public static final String TOP_LEVEL_MANDATORY_LIST = "cli.schema.base.sections.mandatory";
    public static final String SERVICE_PARAMS_LIST = "cli.schema.http.service.sections";
    public static final String SERVICE_PARAMS_MANDATORY_LIST = "cli.schema.http.service.sections.mandatory";
    public static final String INFO_PARAMS_LIST = "cli.schema.base.info.sections";
    public static final String INFO_PARAMS_MANDATORY_LIST = "cli.schema.base.info.sections.mandatory";
    public static final String INPUT_PARAMS_LIST = "cli.schema.base.parameters.sections";
    public static final String INPUT_PARAMS_MANDATORY_LIST = "cli.schema.base.parameters.sections.mandatory";
    public static final String RESULT_PARAMS_LIST = "cli.schema.base.results.sections";
    public static final String RESULT_PARAMS_MANDATORY_LIST = "cli.schema.base.results.sections.mandatory";
    public static final String BOOLEAN_VALUE = "cli.schema.boolean_values";
    public static final String COMMAND_TYPE_VALUES = "cli.schema.command.type";
    public static final String SCHEMA_TYPES_SUPPORTED = "cli.schema.profile.available";
    public static final String OPEN_CLI_PRODUCT_NAME = "cli.product_name";
    public static final String OPEN_CLI_PLUGIN_PRPS = "cli.schema.profile.confs";
    public static final String OPEN_CLI_DATA_DIR = "cli.data.dir";
    public static final String OPEN_CLI_ARTIFACT_DIR = "cli.artifact.dir";
    public static final String OPEN_CLI_GRPC_CLIENT_TIMEOUT = "cli.grpc.client.timeout";
    public static final String OPEN_CLI_EXECUTION_SEARCH_MODE = "cli.execution.search.mode";
    //schema
    public static final String OPEN_CLI_SCHEMA_VERSION = "open_cli_schema_version";
    public static final String OPEN_CLI_SCHEMA_VERSION_VALUE_1_0 = "1.0";

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";

    //Info
    public static final String OPEN_CLI = "open-cli";
    public static final String INFO = "info";
    public static final String INFO_PRODUCT = "product";
    public static final String OPEN_CLI_PRODUCT_IN_USE_ENV_NAME = "OPEN_CLI_PRODUCT_IN_USE";

    public static final String INFO_SERVICE = "service";
    public static final String INFO_TYPE = "type";
    public static final String INFO_AUTHOR = "author";
    public static final String INFO_IGNORE = "ignore";
    public static final String INFO_STATE = "state";
    public static final String INFO_METADATA = "metadata";

    //parameters
    public static final String PARAMETERS = "parameters";
    public static final String SHORT_OPTION = "short_option";
    public static final String LONG_OPTION = "long_option";
    public static final String TYPE = "type";
    public static final String IS_OPTIONAL = "is_optional";
    public static final String DEFAULT_VALUE = "default_value";
    public static final String IS_SECURED = "is_secured";
    public static final String IS_INCLUDE = "is_include";
    public static final String IS_DEFAULT_PARAM = "is_default_param";

    public static final String PARAMETER_TYPE_JSON = "json";
    public static final String PARAMETER_TYPE_YAML = "yaml";
    public static final String PARAMETER_TYPE_STRING = "string";
    public static final String PARAMETER_TYPE_LONG = "long";
    public static final String PARAMETER_TYPE_URL = "url";
    public static final String PARAMETER_TYPE_BOOL = "bool";
    public static final String PARAMETER_TYPE_ARRAY = "array";
    public static final String PARAMETER_TYPE_BINARY = "binary";
    public static final String PARAMETER_TYPE_MAP = "map";

    public static final String DEAFULT_INPUT_PARAMETERS_NAME = "default_input_parameters";
    public static final String DEFAULT_PARAMETER_FILE_NAME = DEAFULT_INPUT_PARAMETERS_NAME + ".yaml";
    public static final String DEFAULT_PARAMETER_HELP = "help";
    public static final String DEFAULT_PARAMETER_VERSION = "version";
    public static final String DEFAULT_PARAMETER_DEBUG = "debug";
    public static final String DEFAULT_PARAMETER_OUTPUT_FORMAT = "format";
    public static final String DEFAULT_PARAMETER_OUTPUT_ATTR_LONG = "long";

    public static final String DEFAULT_PARAMETER_OUTPUT_NO_TITLE = "no-title";

    //results
    public static final String RESULTS = "results";

    public static final String DIRECTION = "direction";
    public static final String DIRECTION_PORTRAIT = "portrait";
    public static final String DIRECTION_LANDSCAPE = "landscape";

    public static final String ATTRIBUTES = "attributes";

    public static final String SCOPE = "scope";
    public static final String RESULT_SCOPE_SHORT = "short";
    public static final String RESULT_SCOPE_LONG = "long";
    public static final String IS_DEFAULT_ATTR = "is_default_attr";

    //print
    public static final String PORTRAINT_COLUMN_NAME_PROPERTY = "property";
    public static final String PORTRAINT_COLUMN_NAME_VALUE = "value";

    //discovery
    public static final String SCHEMA_DIRECTORY = "open-cli-schema";
    public static final String YAML_PATTERN = "/**/*.yaml";
    public static final String DEFAULT_YAML_PATTERN = "/**/" + DEAFULT_INPUT_PARAMETERS_NAME + "_*.yaml";
    public static final String JSON_PATTERN = "/**/*.json";
    public static final String SCHEMA_PATH_PATERN = SCHEMA_DIRECTORY + YAML_PATTERN;
    public static final String DEFAULT_SCHEMA_PATH_PATERN = SCHEMA_DIRECTORY + DEFAULT_YAML_PATTERN;
    public static final String DATA_DIRECTORY = "data";
    public static final String DISCOVERY_FILE = "schemas.json";
    public static final String DATA_PATH_JSON_PATTERN = DATA_DIRECTORY + JSON_PATTERN;
    public static final String DATA_PATH_PROFILE_JSON = "-profile.json";
    public static final String DATA_PATH_PROFILE_JSON_PATTERN = DATA_DIRECTORY + "/**/*" + DATA_PATH_PROFILE_JSON;
    public static final String DISCOVER_ALWAYS = "discover_always";
    public static final String PARAM_CACHE_FILE_NAME = "default";
    public static final String OCLIP_GLOBAL_PROFILE = "OCLIP-GLOBAL-PROFILE";

    public static final String PRODUCT_REGISTRY_YAML = "-registry.yaml";
    //normal
    public static final String BASIC_SCHEMA_PROFILE = "basic";
    public static final String EXCEPTION = "exception";
    public static final String BOOLEAN_TRUE = "true";
    public static final String BOOLEAN_FALSE = "false";

    // Error message
    public static final String ERROR= "error";
    public static final String ERROR_CODE= "code";
    public static final String ERROR_MESSAGE= "message";

    public static final String SCHEMA_INVALID_DEFAULT_PARAMS_SECTION = "Invalid default_parameter section";
    public static final String SCHEMA_FILE_EMPTY = "The schema file cann't be null or empty";
    public static final String SCHEMA_FILE_WRONG_EXTN = "Schema file should be '.yaml' extension";
    public static final String SCHEMA_FILE_NOT_EXIST = "Schema file doesn't exist";
    public static final String USE_DIRECTIVE = "use";


    public static final String SPL_ENTRY_UUID = "uuid";
    public static final String SPL_ENTRY_ENV = "env:";
    public static final String SPL_ENTRY_FILE = "file:";
    public static final String SPL_ENTRY_MD5 = "md5:";

    public static final String VERSION_INFO = "version.info";
    public static final String VERSION_INFO_PLACE_HOLDER_VERSION = "__VERSION__";
    public static final String VERSION_INFO_PLACE_HOLDER_AVL_PRD_VER = "__AVAILABLE_PRODUCT_VERSIONS__";
    public static final String VERSION_INFO_PLACE_HOLDER_ENB_PRD_VER = "__ENABLED_PRODUCT_VERSIONS__";

    public static final String SAMPLE_GEN_ENABLED = "cli.sample.gen.enable";
    public static final String SAMPLE_GEN_TARGET_FOLDER = "cli.sample.gen.target";

    public static final String OPEN_CLI_SAMPLE_VERSION = "open_cli_sample_version";
    public static final String OPEN_CLI_SAMPLE_VERSION_VALUE_1_0 = "1.0";
    public static final String VERIFY_SAMPLES_DIRECTORY = "open-cli-sample";
    public static final String VERIFY_SAMPLES_FILE_PATTERN = VERIFY_SAMPLES_DIRECTORY + YAML_PATTERN;
    public static final String VERIFY_SAMPLES_MOCK_PATTERN = VERIFY_SAMPLES_DIRECTORY + JSON_PATTERN;
    public static final String VERIFY_SAMPLES = "samples";
    public static final String VERIFY_CMD_NAME = "name";
    public static final String VERIFY_CMD_VERSION = DEFAULT_PARAMETER_VERSION;
    public static final String VERIFY_OUPUT = "output";
    public static final String VERIFY_INPUT = "input";
    public static final String VERIFY_MOCO = "moco";
    public static final String VERIFY_SAMPLE_FILE_ID = "samplefileid";
    public static final String VERIFY_SAMPLE_ID = "sampleid";
    public static final String VERIFY_RESULT_PASS = "pass";
    public static final String VERIFY_RESULT_FAIL = "fail";
    public static final String VERIFY_CONTEXT_PARAM = "context";

    public static final String RPC_HOST = "rpc-host";
    public static final String RPC_PORT = "rpc-port";
    public static final String RPC_PORT_DEFAULT = "50051";
    public static final String RPC_CMD = "command";
    public static final String RPC_REQID = "request-id";
    public static final String RPC_ARGS = "arg";
    public static final String RPC_PRODUCT = "product";
    public static final String RPC_PROFILE = "profile";
    public static final String RPC_SCHEMAS = "commands";
    public static final String RPC_MODE = "mode";
    public static final String RPC_MODE_RUN_CLI = "cli";
    public static final String RPC_MODE_RUN_RPC = "rpc";
    public static final String RPC_MODE_RSYNC_SRC = "rsync_src";
    public static final String RPC_MODE_RSYNC_DST = "rsync_dst";

    public static final String VERIFY_LONG_OPTION = "--verify";
    public static final String VERIFY_SHORT_OPTION = "-V";

    private OnapCommandConstants() {
    }

}




