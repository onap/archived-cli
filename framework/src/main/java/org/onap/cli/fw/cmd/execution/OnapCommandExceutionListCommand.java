/*
 * Copyright 2019 Huawei Technologies Co., Ltd.
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

package org.onap.cli.fw.cmd.execution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.store.OnapCommandExecutionStore;

/**
 * List available schemas.
 *
 */
@OnapCommandSchema(schema = "execution-list.yaml")
public class OnapCommandExceutionListCommand extends OnapCommand {

    @Override
    protected void run() throws OnapCommandException {

        Map<String, String> map = new HashMap<>();
        for (String input: Arrays.asList("request-id", "product", "service", "command", "profile", "start-time", "end-time")) {
            String value = getParametersMap().get(input).getValue().toString();
            if (value != null && !value.isEmpty()) {
                map.put(input, value);
            }
        }

        List<OnapCommandExecutionStore.Execution> executions = OnapCommandExecutionStore.getStore().listExecutions(map);

        for (OnapCommandExecutionStore.Execution e :  executions) {
            this.getResult().getRecordsMap().get("product").getValues().add(e.getProduct());
            this.getResult().getRecordsMap().get("service").getValues().add(e.getService());
            this.getResult().getRecordsMap().get("command").getValues().add(e.getCommand());
            this.getResult().getRecordsMap().get("profile").getValues().add(e.getProfile());
            this.getResult().getRecordsMap().get("execution-id").getValues().add(e.getId());
            this.getResult().getRecordsMap().get("request-id").getValues().add(e.getRequestId());
            this.getResult().getRecordsMap().get("status").getValues().add(e.getStatus());
            this.getResult().getRecordsMap().get("start-time").getValues().add(e.getStartTime());
            this.getResult().getRecordsMap().get("end-time").getValues().add(e.getEndTime());
            this.getResult().getRecordsMap().get("input").getValues().add(e.getInput());
            this.getResult().getRecordsMap().get("output").getValues().add(e.getOutput());
        }
    }

}
