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

import java.util.ArrayList;
import java.util.List;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandExecutionNotFound;
import org.onap.cli.fw.output.OnapCommandResultType;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.store.OnapCommandExecutionStore;
import org.onap.cli.fw.store.OnapCommandExecutionStore.Execution;

/**
 * List available schemas.
 *
 */
@OnapCommandSchema(schema = "execution-show.yaml")
public class OnapCommandExceutionShowCommand extends OnapCommand {

    @Override
    protected void run() throws OnapCommandException {

        String requestId = getParametersMap().get("execution-id").getValue().toString();

        OnapCommandExecutionStore.Execution e= OnapCommandExecutionStore.getStore().getExecution(requestId);

        this.getResult().getRecordsMap().get("product").getValues().add(e.getProduct());
        this.getResult().getRecordsMap().get("service").getValues().add(e.getService());
        this.getResult().getRecordsMap().get("command").getValues().add(e.getCommand());
        this.getResult().getRecordsMap().get("profile").getValues().add(e.getProfile());
        this.getResult().getRecordsMap().get("input").getValues().add(String.valueOf(e.getInput()));
        this.getResult().getRecordsMap().get("request-id").getValues().add(e.getId());
        this.getResult().getRecordsMap().get("status").getValues().add(e.getStatus());
        this.getResult().getRecordsMap().get("start-time").getValues().add(e.getStartTime());
        this.getResult().getRecordsMap().get("end-time").getValues().add(e.getEndTime());
        this.getResult().getRecordsMap().get("output").getValues().add(String.valueOf(e.getOutput()));

        if (this.getResult().getType().equals(OnapCommandResultType.TEXT)) {
            List<Execution> list = new ArrayList<>();
            list.add(e);
            this.getResult().setOutput(this.printExecution(list));
        }
    }


    public String printExecution(List<OnapCommandExecutionStore.Execution> executions) throws OnapCommandExecutionNotFound {
        StringBuilder msg = new StringBuilder("<oclip-execution-list>\n");
        for (OnapCommandExecutionStore.Execution e: executions) {
             msg.append("<oclip-execution>\n");
             if ( e.getId() != null)
                 msg.append("<oclip-request-id>\n" + e.getId() + "</oclip-request-id>\n");
             msg.append("<oclip-request-product>\n" + e.getProduct() + "</oclip-request-product>\n");
             msg.append("<oclip-request-service>\n" + e.getService() + "</oclip-request-service>\n");
             msg.append("<oclip-request-command>\n" + e.getCommand() + "</oclip-request-command>\n");
             if ( e.getProfile() != null)
                 msg.append("<oclip-request-profile>\n" + e.getProfile() + "</oclip-request-profile>\n");
             msg.append("<oclip-request-input>\n" + e.getInput() + "</oclip-request-input>\n");
             if ( e.getOutput() != null)
                 msg.append("<oclip-request-output>\n" + e.getOutput() + "</oclip-request-output>\n");
             msg.append("<oclip-request-start-time>\n" + e.getStartTime() + "</oclip-request-start-time>\n");
             msg.append("<oclip-request-end-time>\n" + e.getEndTime() + "</oclip-request-end-time>\n");
             msg.append("<oclip-request-status>\n" + e.getStatus() + "</oclip-request-status>\n");
             msg.append("</oclip-execution>");
        }
        msg.append("</oclip-execution-list>");

        return msg.toString();
    }
}
