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

/**
 * Base command exception.
 *
 */
public class OnapCommandException extends Exception {

    private static final long serialVersionUID = 2833124031431731711L;

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

}
