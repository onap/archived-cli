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

package org.onap.cli.fw.info;

import org.onap.cli.fw.cmd.OnapCommandType;

/**
 * Oclip Command info like product version, service, contact, etc.
 */
public class OnapCommandInfo {

    private String product;

    private String service;

    private String author;

    private OnapCommandType type = OnapCommandType.CMD;

    private OnapCommandState state = OnapCommandState.STABLE;

    private boolean ignore = false;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String email) {
        this.author = email;
    }

    public OnapCommandType getCommandType() {
        return type;
    }

    public void setCommandType(OnapCommandType type) {
        this.type = type;
    }

    public boolean isIgnore() {
        return ignore;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

    public OnapCommandState getState() {
        return state;
    }

    public void setState(OnapCommandState state) {
        this.state = state;
    }

}