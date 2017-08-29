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

package org.onap.cli.fw.utils;

public class ExternalSchema {

    private String schemaName;
    private String schemaURI;
    private String cmdName;
    private String cmdVersion;
    private String version;
    private String http = "false";

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

    public String getCmdVersion() {
        return cmdVersion;
    }

    public void setCmdVersion(String cmdVersion) {
        this.cmdVersion = cmdVersion;
    }

	public String getSchemaURI() {
		return schemaURI;
	}

	public void setSchemaURI(String schemaURI) {
		this.schemaURI = schemaURI;
	}

	public String getHttp() {
		return http;
	}

	public void setHttp(String internal) {
		this.http = internal;
	}
	
	public boolean isHttp() {
		return this.getHttp().equals("true");
	}

}
