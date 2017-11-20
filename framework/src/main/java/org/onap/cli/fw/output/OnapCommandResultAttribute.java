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

package org.onap.cli.fw.output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.onap.cli.fw.input.ParameterType;

/**
 * Oclip command output records, helps to define the title and its description while command is defined and during run
 * time, it captures the value of the output as well.
 */
public class OnapCommandResultAttribute {

    /*
     * Output name
     */
    private String outName;

    /*
     * Output description
     */
    private String outDescription;

    /*
     * Output values, in case list out, it holds values for all rows for show output, it will have one value
     */
    private List<String> values = new ArrayList<>();

    /*
     * default value, useful to set when a command want to set the default value for a output attributes.
     */
    private String defaultValue = "";

    /*
     * Output scope
     */
    private OnapCommandResultAttributeScope outScope = OnapCommandResultAttributeScope.SHORT;

    private ParameterType paramType = ParameterType.STRING;

    private boolean isSecured = false;

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getName() {
        return outName;
    }

    public void setName(String name) {
        this.outName = name;
    }

    public String getDescription() {
        return outDescription;
    }

    public void setDescription(String description) {
        this.outDescription = description;
    }

    public List<String> getValues() {
        if (this.values.isEmpty() && !this.defaultValue.isEmpty()) {
            return Arrays.asList(new String [] {this.defaultValue});
        }
        return values;
    }

    public OnapCommandResultAttributeScope getScope() {
        return outScope;
    }

    public void setScope(OnapCommandResultAttributeScope scope) {
        this.outScope = scope;
    }

    public ParameterType getType() {
        return paramType;
    }

    public void setType(ParameterType type) {
        this.paramType = type;
    }

    public boolean isSecured() {
        return isSecured;
    }

    public void setSecured(boolean isSecured) {
        this.isSecured = isSecured;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

}
