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

package org.onap.cli.fw.snmp.conf;

/**
 * OnapCommandHttpConstants.
 *
 */
public class OnapCommandSnmpConstants {
    public static final String SNMP_SCHEMA_PROFILE = "snmp";
    public static final String SNMP = "snmp";
    public static final String SNMP_VERSION = "version";
    public static final String SNMP_RESULTMAP = "result_map";
    public static final String SNMP_COMMAND = "command";
    public static final String SNMP_AGENT = "agent";
    public static final String SNMP_COMMNUNITY_STRING = "public";
    public static final String SNMP_CMD_GET = "get";
    public static final String SNMP_CMD_SET = "set";
    public static final String DEFAULT_PARAMETER_SNMP_FILE_NAME = "default_input_parameters_snmp.yaml";
    public static final String SNMP_METHODS = "cli.schema.snmp_methods";
    public static final String SNMP_REQUEST_PARAMS = "cli.schema.snmp_request_params";
    public static final String SNMP_REQUEST_MANDATORY_PARAMS = "cli.schema.snmp_request_mandatory_params";
    public static final int RETRY_COUNT = 2;
    public static final int TIMEOUT = 1500;
}


