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

package org.onap.cli.sample;

import java.util.Map;

import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.OnapCommandSchema;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.input.OnapCommandParameter;

/**
 * Hello world.
 */
@OnapCommandSchema(name = "hello-world", version = "sample-1.0", schema = "hello-world.yaml")
public class OnapHelloWorldCommand extends OnapCommand {

    @Override
    protected void run() throws OnapCommandException {
        //Read the input arguments
        Map<String, OnapCommandParameter> paramMap = getParametersMap();
        OnapCommandParameter nameP = paramMap.get("name");
        String name = String.valueOf(nameP.getValue());

        //Process command
        String output = "Hello " + name;

        //Populate outputs
        this.getResult().getRecordsMap().get("output").getValues().add(output);
   }
}
