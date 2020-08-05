/*
 * Copyright 2018 Huawei Technologies Co., Ltd.
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

package org.onap.cli.fw.cmd.profile;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.input.cache.OnapCommandParamEntity;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.store.OnapCommandProfileStore;

/**
 * Refresh external schema.
 *
 */
@OnapCommandSchema(schema = "profile-show.yaml")
public class OnapProfileShowCommand extends OnapCommand {

    @Override
    protected void run() throws OnapCommandException {
        String profile = getParametersMap().get("profile").getValue().toString();

        for (OnapCommandParamEntity param :  OnapCommandProfileStore.getInstance().loadParamFromCache(profile)) {

            String product = param.getProduct();
            if (product.equals(OnapCommandConstants.OCLIP_GLOBAL_PROFILE)) {
                //profile param is global
                product = "*";
            }
            this.getResult().getRecordsMap().get("product").getValues().add(product);

            String []name = param.getName().split(":"); //service:command:param-name
            String paramName = name[name.length-1]; //always last entry is param name
            this.getResult().getRecordsMap().get("parameter").getValues().add(paramName);
            this.getResult().getRecordsMap().get("value").getValues().add(param.getValue());
            String service = "*";
            String command = "*";
            if (name.length == 3) {
                service = name[0];
                command = name[1];
            } else if (name.length == 2) {
                service = name[0];
            }
            this.getResult().getRecordsMap().get("service").getValues().add(service);
            this.getResult().getRecordsMap().get("command").getValues().add(command);
        }
    }

}