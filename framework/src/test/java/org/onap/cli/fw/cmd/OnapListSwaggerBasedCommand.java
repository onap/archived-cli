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

import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.error.OnapCommandExecutionFailed;
import org.onap.cli.fw.error.OnapCommandExecutorInfoMissing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Helps to make list rest calls on top of swagger generated java client. For example following one shows how MSB
 * service list is achived here exec: api:
 * org.onap.common_services.microservice_bus.apiroute_service.client.api.MSBServiceResourceApi client:
 * org.onap.common_services.microservice_bus.apiroute_service.client.invoker.ApiClient method: getMicroService
 * exception: org.onap.common_services.microservice_bus.apiroute_service.client.invoker.ApiException
 *
 */
public class OnapListSwaggerBasedCommand extends OnapSwaggerCommand {

    @Override
    protected void run() throws OnapCommandException {
        if (this.getExecutor() == null) {
            throw new OnapCommandExecutorInfoMissing(this.getName());
        }

        try {
            // Initialize client
            Class clientCls = Class.forName(this.getExecutor().getClient());
            Object client = clientCls.getConstructor().newInstance();
            this.initializeApiClient(client);

            // Initialize api
            Class apiCls = Class.forName(this.getExecutor().getApi());
            Object api = apiCls.getConstructor(clientCls).newInstance(client);

            // invoke method
            Method method = api.getClass().getMethod(this.getExecutor().getMethod());
            Object result = method.invoke(api);

            // initialize result
            this.initializeResult(result);

        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            throw new OnapCommandExecutionFailed(this.getName(), e);
        } catch (OnapCommandException e) {
            throw e;
        } catch (Exception e) {
            try {
                Class execCls = Class.forName(this.getExecutor().getException());
                Method execMethod = execCls.getClass().getMethod("getCode");
                if (execCls.isInstance(e)) {
                    throw new OnapCommandExecutionFailed(this.getName(), e, (Integer) execMethod.invoke(e));
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                    | ClassNotFoundException | NoSuchMethodException | SecurityException e1) {
                throw new OnapCommandExecutionFailed(this.getName(), e1.getMessage());
            }
            throw new OnapCommandExecutionFailed(this.getName(), e.getMessage());
        }
    }
}
