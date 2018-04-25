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

package org.onap.cli.fw.error;

import org.onap.cli.fw.conf.OnapCommandConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base command exception.
discovery 0x100
registration 0x200
http 0x300
login/logout 0x400
swagger 0x500
execution 0x600
input  0x700
output 0x800
help 0x900
default parameter 0xa00
schema 0xb00
profile 0xc00
product/service 0xd00
catalog 0xe00
sample 0xf00
 */
public class OnapCommandException extends Exception {

    private static final long serialVersionUID = 2833124031431731711L;

    private static final Logger log = LoggerFactory.getLogger(OnapCommandException.class);

    /*
     * Command Error Code
     */
    private final String errorCode;

    /*
     * Command error message
     */
    private final String errorMessage;

    /*
     * Command HTTP status code
     */
    private final long httpStatusCode;

    /**
     * OnapCommandException constructor.
     *
     * @param errorCode
     *            error code
     * @param errorMessage
     *            error message
     * @param httpStatusCode
     *            http status code
     */
    public OnapCommandException(String errorCode, String errorMessage, long httpStatusCode) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
    }

    public OnapCommandException(String errorCode, String errorMessage) {
        this(errorCode, errorMessage, -1);
    }

    @Override
    public String getMessage() {
        String message =  this.errorCode + "::" + this.errorMessage;
        if (this.httpStatusCode != -1) {
            message = this.httpStatusCode + "::" + message;
        }

        return message;
    }

    public String toJsonString() {
        return "{\""+ OnapCommandConstants.ERROR + "\": {\""+
                OnapCommandConstants.ERROR_CODE + "\": \"" + this.errorCode + "\", \""+
                OnapCommandConstants.ERROR_MESSAGE + "\": \"" + this.errorMessage + "\"}}";
    }

    public OnapCommandException(String errorCode, String errorMessage, Throwable e) {
        this(errorCode, errorMessage + ", " + e.getMessage());
        log.error(this.getMessage(), e);
    }

    public OnapCommandException(String errorCode, String errorMessage, Throwable e, long httpStatusCode) {
        this(errorCode, errorMessage + ", " + e.getMessage(), httpStatusCode);
        log.error(this.getMessage(), e);
    }

    public OnapCommandException(String errorCode, Throwable e, long httpStatusCode) {
        this(errorCode, e.getMessage(), httpStatusCode);
        log.error(this.getMessage(), e);
    }

    public OnapCommandException(String errorCode, Throwable e) {
        this(errorCode, e.getMessage(), -1);
        log.error(this.getMessage(), e);
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
