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

package org.onap.cli.fw.run;

/**
 * Oclip Command executor.
 *
 */
public class OnapCommandExecutor {

    private String apiName;
    private String clientName;
    private String entityName;
    private String methodName;
    private String exceptionName;

    public String getApi() {
        return this.apiName;
    }

    public void setApi(String api) {
        this.apiName = api;
    }

    public String getClient() {
        return this.clientName;
    }

    public void setClient(String client) {
        this.clientName = client;
    }

    public String getEntity() {
        return entityName;
    }

    public void setEntity(String entity) {
        this.entityName = entity;
    }

    public String getMethod() {
        return methodName;
    }

    public void setMethod(String method) {
        this.methodName = method;
    }

    public String getException() {
        return exceptionName;
    }

    public void setException(String exception) {
        this.exceptionName = exception;
    }

}
