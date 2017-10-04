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

package org.onap.cli.fw.cmd;

import java.util.Map;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.auth.BasicScheme;
import org.onap.cli.fw.OnapCommandSchema;
import org.onap.cli.fw.conf.Constants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.input.OnapCommandParameter;

@OnapCommandSchema(name = "basic-login", version = "open-cli", type = "auth", schema = "basic-login.yaml")
public class BasicAuthLoginCommand extends OnapHttpCommand {

    @Override
    protected void run() throws OnapCommandException {

        //get the input arguments
        Map<String, OnapCommandParameter> paramMap = getParametersMap();
        OnapCommandParameter usernameParam = paramMap.get(Constants.DEAFULT_PARAMETER_USERNAME);
        String username = usernameParam.getValue().toString();
        OnapCommandParameter usernamePassword = paramMap.get(Constants.DEAFULT_PARAMETER_PASSWORD);
        String password = usernamePassword.getValue().toString();

        //Execute the command to get token
        String authToken = BasicScheme.authenticate(new UsernamePasswordCredentials(
                username, password), "UTF-8", false).getValue();

        //Fill out the result part
        this.getResult().getRecordsMap().get(Constants.AUTH_SERVICE_AUTHORIZATION).getValues().add(authToken);
    }
}
