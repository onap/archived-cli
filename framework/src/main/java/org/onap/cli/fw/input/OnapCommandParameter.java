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

package org.onap.cli.fw.input;

import com.google.gson.Gson;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandInvalidParameterValue;
import org.onap.cli.fw.error.OnapCommandParameterMissing;
import org.onap.cli.fw.utils.JsonUtil;
import org.onap.cli.fw.utils.OnapCommandUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Oclip Command's input parameter.
 *
 */
public class OnapCommandParameter {
    private  static Gson gson= JsonUtil.getGsonInstance();
    /*
     * Name, for positional parameters, the place is decided from schema file definition
     */
    private String cmdName;

    /*
     * Description
     */
    private String cmdDescription = "";

    /*
     * Short Option, like -f, for positional parameters, its not required.
     */
    private String shortOption = null;

    /*
     * Long Option, like --file-path, for positional parameters, its not required.
     */
    private String longOption = null;

    /*
     * Parameter type such as int, json, yaml, string, etc
     */
    private OnapCommandParameterType parameterType = OnapCommandParameterType.STRING;

    /*
     * Default value
     */
    private Object defaultValue;

    /*
     * raw default value, stored with out processing it.
     */
    private String rawDefaultValue = "";

    /*
     * Is optional
     */
    private boolean isOptional = false;

    /*
     * Is secured
     */
    private boolean isSecured = false;

    /*
     * Parameter Value
     */
    private Object value = null;

    /*
     * raw value, get stored as its without processing it.
     * it will have same value as stored in yaml template
     */
    private Object rawValue = null;

    /*
     * to support include concepts of templates, new variable
     * called is_include is introduced and default its always
     * true.
     */
    private boolean isInclude = true;

    /*
     * This param is from The default input parameters file
     */
    private boolean isDefaultParam = false;

    public boolean isDefaultParam() {
        return isDefaultParam;
    }

    public void setDefaultParam(boolean isDefaultParam) {
        this.isDefaultParam = isDefaultParam;
    }

    public String getName() {
        return cmdName;
    }

    public void setName(String name) {
        this.cmdName = name;
    }

    public String getDescription() {
        return cmdDescription;
    }

    public void setDescription(String description) {
        this.cmdDescription = description;
    }

    public String getShortOption() {
        return shortOption;
    }

    public void setShortOption(String shortOption) {
        this.shortOption = shortOption;
    }

    public String getLongOption() {
        return longOption;
    }

    public void setLongOption(String longOption) {
        this.longOption = longOption;
    }

    public OnapCommandParameterType getParameterType() {
        return parameterType;
    }

    public void setParameterType(OnapCommandParameterType parameterType) {
        this.parameterType = parameterType;

        if (defaultValue == null) {

            switch (getParameterType()) {
                case MAP:
                    this.defaultValue = new HashMap<String, String>();
                    break;
                case ARRAY:
                    defaultValue = new ArrayList<String>();
                    break;
                case BOOL:
                    defaultValue = Boolean.FALSE;
                    break;
                case UUID:
                    this.defaultValue = UUID.randomUUID().toString();
                    break;
                case JSON:
                    this.defaultValue = new String("{}");
                    break;
                default:
                    this.defaultValue = new String("");
                    break;
            }
        }
    }

    /**
     * Returns default value.
     *
     * @return string
     */
    public Object getDefaultValue() {
        return defaultValue;
    }

    /**
     * check if the default value is ${ENV_VAR_NAME}.
     *
     * @return boolean
     */
    public boolean isRawDefaultValueAnEnv() {
        return this.rawDefaultValue.trim().startsWith("$s{env:") && this.rawDefaultValue.trim().endsWith("}");
    }

    /**
     * check if the default value is $s{env:ENV_VAR_NAME} and return the ENV_VAR_NAME.
     *
     * @return ENV_VAR_NAME
     */
    public String getEnvVarNameFromrRawDefaultValue() {
        return this.rawDefaultValue.trim().substring(7, this.rawDefaultValue.length() - 1);
    }

    public void setRawDefaultValue(String value) throws OnapCommandInvalidParameterValue {
        this.rawDefaultValue = value;
        String processedValue= OnapCommandUtils.replaceLineForSpecialValues(value);

        switch (parameterType) {
            case MAP:
                defaultValue = JsonUtil.convertJsonStringToClassType(processedValue, Map.class);
                break;

            case ARRAY:
                defaultValue = JsonUtil.convertJsonStringToClassType(processedValue, List.class);
                break;

            case BOOL:
                defaultValue = Boolean.valueOf(processedValue);
                break;

            default:
                defaultValue = processedValue;
                break;
        }
    }

    public void setDefaultValue(Object defaultValue) throws OnapCommandInvalidParameterValue {
        //check type
        Class<?> clz;
        switch (parameterType) {
            case BOOL:
                clz = Boolean.class;
                break;

            case MAP:
                clz = Map.class;
                break;

            case ARRAY:
                clz = Collection.class;
                break;

            default:
                clz = String.class;
                break;
        }

        if (clz.isInstance(defaultValue)) {
            this.defaultValue = defaultValue;
        } else {
            throw new OnapCommandInvalidParameterValue("Invalid default value for parameter: " + this.getName());
        }
    }

    /**
     * Returns param value.
     *
     * @return value
     * @throws OnapCommandInvalidParameterValue
     *             exception
     */
    public Object getValue()  {
        if (value != null) {
            return value;
        }
        return getDefaultValue();
    }

    /**
     * Returns the raw value
     * @return
     */
    public Object getRawValue() {
        return this.rawValue;
    }

    public void setValue(Object value) throws OnapCommandInvalidParameterValue {
        this.rawValue = value;

        if (OnapCommandParameterType.URL.equals(parameterType) &&
                !value.toString().isEmpty() &&
                !value.toString().startsWith("http") &&
                !value.toString().startsWith("ftp") &&
                !value.toString().startsWith("mqtt") &&
                !value.toString().startsWith("tcp")
                && !value.toString().startsWith("/")) {
            value = "/" + value;
        } else if (OnapCommandParameterType.ARRAY.equals(parameterType)) {
            if (!(value instanceof List)) {
                throw new OnapCommandInvalidParameterValue(this.getName());
            }

        } else if (OnapCommandParameterType.MAP.equals(parameterType)) {
            if (!(value instanceof Map)) {
                throw new OnapCommandInvalidParameterValue(this.getName());
            }
        } else if (OnapCommandParameterType.BOOL.equals(parameterType)) {
            if (value instanceof String) {
                value = Boolean.valueOf((String)value);
            } else if (!(value instanceof Boolean)) {
                throw new OnapCommandInvalidParameterValue(this.getName());
            }
        }
        this.value = value;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean isOptional) {
        this.isOptional = isOptional;
    }

    public boolean isSecured() {
        return isSecured;
    }

    public void setSecured(boolean isSecured) {
        this.isSecured = isSecured;
    }

    public boolean isInclude() {
        return isInclude;
    }

    public void setInclude(boolean isInclude) {
        this.isInclude = isInclude;
    }

    public static String printShortOption(String option) {
        return "-" + option;
    }

    public static String printLongOption(String option) {
        return "-" + printShortOption(option);
    }

    /**
     * Validate parameter value.
     *
     * @throws OnapCommandParameterMissing
     *             exception
     * @throws OnapCommandInvalidParameterValue
     *             exception
     */
    public void validate() throws OnapCommandException {
        // (mrkanag) empty check needs to revisit
        if (!this.isOptional() && (this.getValue() == null || this.getValue().toString().isEmpty())) {
            throw new OnapCommandParameterMissing(this.getName());
        }

        if (!this.isOptional() && OnapCommandParameterType.BINARY.equals(parameterType)) {
            File file = new File(value.toString());
            if (!file.isFile()) {
                throw new OnapCommandInvalidParameterValue(this.getName());
            }
        }

        // (mrkanag) validate for type supported OnapCommandParameterType using constraints
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cmdName == null) ? 0 : cmdName.hashCode());
        result = prime * result + ((longOption == null) ? 0 : longOption.hashCode());
        result = prime * result + ((shortOption == null) ? 0 : shortOption.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OnapCommandParameter other = (OnapCommandParameter) obj;
        if (cmdName == null) {
            if (other.cmdName != null)
                return false;
        } else if (!cmdName.equals(other.cmdName))
            return false;
        if (longOption == null) {
            if (other.longOption != null)
                return false;
        } else if (!longOption.equals(other.longOption))
            return false;
        if (shortOption == null) {
            if (other.shortOption != null)
                return false;
        } else if (!shortOption.equals(other.shortOption))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.getName() + ": " + this.getValue();
    }
}
