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

package org.onap.cli.fw.ad;

/**
 * Onap Service as reported in Onap MSB like msb v1 or /api/microservice/v1.
 */
public class OnapService {
    /*
     * Onap Service name like gso.
     */
    private String serviceName;

    /*
     * Onap Service API version like v1, v2, etc
     */
    private String serviceVersion;

    private String basePath;

    private boolean noAuth = false;

    public boolean isNoAuth() {
        return noAuth;
    }

    public void setNoAuth(boolean noAuth) {
        this.noAuth = noAuth;
    }

    public String getName() {
        return serviceName;
    }

    public void setName(String name) {
        this.serviceName = name;
    }

    public String getVersion() {
        return serviceVersion;
    }

    public void setVersion(String version) {
        this.serviceVersion = version;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public boolean getNoAuth() {
        return this.noAuth;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getVersion();
    }

}
