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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.onap.cli.fw.OnapCommand;
import org.onap.cli.fw.error.OnapCommandClientInitialzationFailed;
import org.onap.cli.fw.error.OnapCommandResultInitialzationFailed;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.onap.cli.fw.run.OnapCommandExecutor;
import org.onap.cli.fw.utils.OnapCommandUtils;

public abstract class OnapSwaggerCommand extends OnapCommand {


    private OnapCommandExecutor cmdExecutor;

    public OnapCommandExecutor getExecutor() {
        return cmdExecutor;
    }

    public void setExecutor(OnapCommandExecutor executor) {
        this.cmdExecutor = executor;
    }

    /**
     * Initialize the given ApiClient object with AUTH token and base path.
     *
     * @param client
     *            api client
     * @throws OnapCommandClientInitialzationFailed
     *             client initialization failed
     */
    protected <T> T initializeApiClient(T client) throws OnapCommandClientInitialzationFailed {
        try {
            Method basePath = client.getClass().getMethod("setBasePath", String.class);
            //mrkanag set the basepath
            basePath.invoke(client, "/");

//            if (this.getAuthToken() != null) {
//                Method apiKey = client.getClass().getMethod("setApiKey", String.class);
//                apiKey.invoke(client, this.getAuthToken());
//            }
            return client;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new OnapCommandClientInitialzationFailed(this.getName(), e);
        }
    }

    protected <T> void initializeResult(T obj) throws OnapCommandResultInitialzationFailed {
        this.getResult().setOutput(obj);
        if (obj instanceof List) {
            this.initializeListResult((List) obj);
        } else {
            this.initializeRow(obj);
        }
    }

    private <T> void initializeRow(T obj) throws OnapCommandResultInitialzationFailed {
        for (OnapCommandResultAttribute row : this.getResult().getRecords()) {
            String methodName = OnapCommandUtils.formMethodNameFromAttributeName(row.getName(), "get");
            Method get;
            try {
                get = obj.getClass().getMethod(methodName);
                row.getValues().add(get.invoke(obj).toString());
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                throw new OnapCommandResultInitialzationFailed(this.getName(), e);
            }
        }
    }

    protected <T> void initializeListResult(List<T> rows) throws OnapCommandResultInitialzationFailed {
        this.getResult().setOutput(rows);
        for (T row : rows) {
            this.initializeRow(row);
        }
    }
}
