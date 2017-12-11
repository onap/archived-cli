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

package org.onap.cli.fw.http.error;

import org.onap.cli.fw.error.OnapCommandException;

/**
 * Command execution failed.
 *
 */
public class OnapCommandHttpFailure extends OnapCommandException {
    private static final long serialVersionUID = 488775545436993345L;

    private static final String ERROR_CODE = "0x3001";

    public OnapCommandHttpFailure(String error, long httpStatus) {
        super(ERROR_CODE, error, httpStatus);
    }

    public OnapCommandHttpFailure(String error) {
        super(ERROR_CODE, error);
    }

    public OnapCommandHttpFailure(Throwable throwable) {
        super(ERROR_CODE, throwable);
    }

    public OnapCommandHttpFailure(Throwable throwable, long httpStatus) {
        super(ERROR_CODE, throwable, httpStatus);
    }

}
