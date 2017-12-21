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

package org.onap.cli.fw.snmp.exception;

import org.onap.cli.fw.error.OnapCommandException;

/**
 * Command execution failed.
 *
 */
public class OnapSnmpErrorResponse extends OnapCommandException {
    private static final long serialVersionUID = 488775545433833345L;

    private static final String ERROR_CODE = "0x4001";

    public OnapSnmpErrorResponse(String error, long responseStatus) {
        super(ERROR_CODE, error, responseStatus);
    }

    public OnapSnmpErrorResponse(String error) {
        super(ERROR_CODE, error);
    }

    public OnapSnmpErrorResponse(Throwable throwable) {
        super(ERROR_CODE, throwable);
    }

    public OnapSnmpErrorResponse(Throwable throwable, long responseStatus) {
        super(ERROR_CODE, throwable, responseStatus);
    }

}