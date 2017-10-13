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

package org.onap.cli.fw;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Provide command name and schema file location, which is placed in the main resources folder (in classpath). It is
 * recommended to keep the name for schema, in the form of oclip-[command-name]-schema.yaml, considered this format as
 * default if the schema declaration is missing for a command abc-create, schema file name could be
 * abc-create-schema.yaml, corresponding command would like as below
 *
 * @OnapCommandSchema(type="http", schema="onap-abc-create-schema.yaml") public class AbcCreate extends
 *                                        OnapCommand { ... }
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OnapCommandSchema {
    /**
     * Schema file name placed under class path
     *
     * @return
     */
    String schema() default "";

    /**
     * Schema type
     */
    String type() default "";
}
