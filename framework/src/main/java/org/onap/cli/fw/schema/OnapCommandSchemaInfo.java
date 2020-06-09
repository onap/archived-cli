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

package org.onap.cli.fw.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.onap.cli.fw.cmd.OnapCommandType;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.info.OnapCommandState;


/**
 * OnapCommandSchemaInfo is used in discovery caching.
 *
 */
public class OnapCommandSchemaInfo implements Comparable<OnapCommandSchemaInfo> {

    /**
     * Name of the schema file name
     */
    private String schemaName;

    /**
     * Schema location in complete path
     */
    private String schemaURI;

    private String cmdName;

    private String product;

    private List<String> sampleFiles = new ArrayList<>();

    /**
     * OCS version
     */
    private String version;

    private String type = OnapCommandType.CMD.name();

    private String schemaProfile = OnapCommandConstants.BASIC_SCHEMA_PROFILE;

    private String ignore = OnapCommandConstants.BOOLEAN_FALSE;

    private String state = OnapCommandState.STABLE.name();

    //deployment info
    private String rpcHost;

    private String rpcPort;

    private List<Object> inputs = new ArrayList<>();

    private List<Object> outputs = new ArrayList<>();

    private Map<String, String> metadata = new HashMap<>();

    private String description;

    private String service;

    private String author;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<Object> getInputs() {
        return inputs;
    }

    public void setInputs(List<Object> inputs) {
        this.inputs = inputs;
    }

    public List<Object> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Object> outputs) {
        this.outputs = outputs;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getCmdName() {
        return cmdName;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String cmdVersion) {
        this.product = cmdVersion;
    }

    public String getSchemaURI() {
        return schemaURI;
    }

    public void setSchemaURI(String schemaURI) {
        this.schemaURI = schemaURI;
    }

    public String getSchemaProfile() {
        return schemaProfile;
    }

    public void setSchemaProfile(String internal) {
        this.schemaProfile = internal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIgnore() {
        return OnapCommandConstants.BOOLEAN_TRUE.equalsIgnoreCase(this.getIgnore());
    }

    public String getIgnore() {
        return ignore;
    }

    public void setIgnore(String ignore) {
        this.ignore = ignore;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = Boolean.toString(ignore);
    }

    public List<String> getSampleFiles() {
        return sampleFiles;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int compareTo(OnapCommandSchemaInfo o) {
        return this.cmdName.compareTo(o.getCmdName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        return this.compareTo((OnapCommandSchemaInfo) obj) == 0;
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(this.cmdName);
    }

    public String getRpcHost() {
        return rpcHost;
    }

    public void setRpcHost(String rpcHost) {
        this.rpcHost = rpcHost;
    }

    public String getRpcPort() {
        return rpcPort;
    }

    public void setRpcPort(String rpcPort) {
        this.rpcPort = rpcPort;
    }

    public boolean isRpc() {
        return this.getRpcHost() != null && !this.getRpcHost().isEmpty() &&
                this.getRpcPort() != null && !this.getRpcPort().isEmpty();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
