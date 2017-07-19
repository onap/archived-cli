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

import org.onap.cli.fw.error.OnapCommandInvalidSchema;

import java.util.List;
import java.util.Set;

/**
 * Schema validate interface.
 *
 */
@FunctionalInterface
public interface SchemaValidate {
    /**
     * Validates HTTP or Basic Schema.
     *
     * @return List of errors
     * @throws OnapCommandInvalidSchema
     *             Failed to load schema file
     */
    public List<String> validate() throws OnapCommandInvalidSchema;

    /**
     * Static interface method.
     *
     * @param section
     *            section
     * @return errorMessage
     */
    public static String defaultYamlSchema(String section) {
        return "The section '" + section + ":' cann't be null or empty";
    }

    /**
     * Static interface method.
     *
     * @param section
     *            string
     * @param attribute
     *            string
     * @param value
     *            string
     * @return string
     */
    public static String invalidBooleanValueMessage(String section, String attribute, String value) {
        return "The value '" + value + "' of '" + attribute + "' present under '" + section + "' should be boolean";
    }

    /**
     * Static interface method.
     *
     * @param section
     *            string
     * @param attribute
     *            string
     * @return string
     */
    public static String emptyValue(String section, String attribute) {
        return "Attribute '" + attribute + "' under '" + section + "' is null or empty";
    }

    /**
     * Static interface method.
     *
     * @param section
     *            string
     * @param attribute
     *            string
     * @param types
     *            list
     * @return string
     */
    public static String invalidType(String section, String attribute, List<String> types) {
        return "Attribute '" + attribute + "' under '" + section + "' is invalid, correct types are "
                + types.toString();
    }

    /**
     * Static interface method.
     *
     * @param subSection
     *            string
     * @param attribute
     *            string
     * @return string
     */
    public static String invalidRequestParam(String subSection, String attribute) {
        return "The http request '" + subSection + "' parameter '" + attribute
                + "' is not declared under 'parameters:' section";
    }

    /**
     * Static interface method.
     *
     * @param section
     *            string
     * @return string
     */
    public static String emptySection(String section) {
        return "The section '" + section + ":' cann't be null or empty";
    }

    /**
     * Static interface method.
     *
     * @param mainSections
     *            list
     * @param http
     *            list
     * @param basic
     *            list
     * @return string
     */
    public static String invalidSections(Set<String> mainSections, List<String> http, List<String> basic) {
        return "No matching schema type found due to extra or missing sections in the file" + mainSections.toString()
                + " , Supported schema sections are http" + http.toString() + " and basic" + basic.toString();
    }

    /**
     * Static interface method.
     *
     * @param name
     *            string
     * @param section
     *            string
     * @return string
     */
    public static String attributeNameExist(String name, String section) {
        return "Attribute name='" + name + "' under '" + section + ":' is already used, Take different one.";
    }

    /**
     * Static interface method.
     *
     * @param name
     *            string
     * @param scopes
     *            list
     * @return string
     */
    public static String invalidAttributeScope(String name, List<String> scopes) {
        return "The attribute '" + name + "' scope is invalid, valid scopes are " + scopes.toString();
    }

    /**
     * Static interface method.
     *
     * @param name
     *            string
     * @return string
     */
    public static String attributeScopeEmpty(String name) {
        return "The attribute '" + name + "' scope is null or empty.";
    }

    /**
     * Static interface method.
     *
     * @param option
     *            string
     * @param attrValue
     *            string
     * @param name
     *            string
     * @return string
     */
    public static String optionExist(String option, String attrValue, String name) {
        return "Attribute " + option + " option '" + attrValue + "' of parameter '" + name
                + "' is already used, Take different one.";
    }

    /**
     * Static interface method.
     *
     * @param option
     *            string
     * @param attrValue
     *            string
     * @param name
     *            string
     * @param list
     *            list
     * @return string
     */
    public static String optionDefaultExist(String option, String attrValue, String name, Set<String> list) {
        return "Attribute " + option + " option '" + attrValue + "' of parameter '" + name
                + "' is already used in default parameters list " + list.toString() + ", Take different one.";
    }

    /**
     * Static interface method.
     *
     * @param name
     *            string
     * @return string
     */
    public static String longOptionExist(String name) {
        return "The attribute '" + name + "' scope is null or empty.";
    }

    /**
     * Static interface method.
     *
     * @param name
     *            string
     * @return string
     */
    public static String shortOptionExist(String name) {
        return "The attribute '" + name + "' scope is null or empty.";
    }

    /**
     * Static interface method.
     *
     * @param name
     *            string
     * @param section
     *            string
     * @return string
     */
    public static String nameExist(String name, String section) {

        return "Attribute name='" + name + "' under '" + section + ":' is already used, Take different one.";
    }

    /**
     * Static interface method.
     *
     * @param name
     *            string
     * @param section
     *            string
     * @param types
     *            list
     * @return string
     */
    public static String invalidAttrType(String name, String section, List<String> types) {
        return "Attribute type of '" + name + "' under '" + section + "' is invalid, correct types are "
                + types.toString();
    }

    /**
     * Static interface method.
     *
     * @param param
     *            string
     * @param section
     *            string
     * @return string
     */
    public static String mandatoryAttrMissing(String param, String section) {

        return "Mandatory attribute '" + param + "' is missing under '" + section + "'";
    }

    /**
     * Static interface method.
     *
     * @param param
     *            string
     * @param section
     *            string
     * @return string
     */
    public static String mandatoryAttrEmpty(String param, String section) {

        return "Mandatory attribute '" + param + "' under '" + section + "' shouldn't be null or empty";
    }

    /**
     * Static interface method.
     *
     * @param attribute
     *            string
     * @return string
     */
    public static String missingInResultMap(String attribute) {

        return "The attribute '" + attribute
                + "' declared under result 'attributes:' section is missing from http 'result_map:'.";
    }

    /**
     * Static interface method.
     *
     * @param attribute
     *            string
     * @return string
     */
    public static String missingInResultAttribute(String attribute) {

        return "Mapped attribute '" + attribute + "' is missing declaration in result attributes section.";
    }

    /**
     * Static interface method.
     *
     * @param declaredParam
     *            string
     * @return string
     */
    public static String parameterNotMapped(String declaredParam) {

        return "The parameter '" + declaredParam
                + "' declared under 'parameters:' section is not mapped into request section.";
    }

}
