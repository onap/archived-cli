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
import org.onap.cli.fw.error.OnapCommandInvalidParameterValue;
import org.onap.cli.fw.error.OnapCommandResultInitialzationFailed;
import org.onap.cli.fw.utils.OnapCommandUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class OnapCreateSwaggerBasedCommand extends OnapSwaggerCommand {

    private <T> T initializeEntity(T obj, List<String> prps) throws OnapCommandResultInitialzationFailed {
        for (int i = 1; i < prps.size(); i++) {
            String paramName = prps.get(i).trim();
            String prpName = paramName;
            // paramName(prpName)
            if (prpName.contains("(")) {
                paramName = prpName.substring(0, prpName.indexOf("("));
                prpName = prpName.substring(prpName.indexOf("(") + 1, prpName.indexOf(")"));
            }
            String methodName = OnapCommandUtils.formMethodNameFromAttributeName(prpName, "set");

            try {
                Method set = obj.getClass().getMethod(methodName, String.class);
                set.invoke(obj, this.getParametersMap().get(paramName).getValue());
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | OnapCommandInvalidParameterValue e) {
                throw new OnapCommandResultInitialzationFailed(this.getName(), e);
            }
        }
        return obj;
    }

    @Override
    protected void run() throws OnapCommandException {
        if (this.getExecutor() == null) {
            throw new OnapCommandExecutorInfoMissing(this.getName());
        }

        try {
            // Initialize client
            Class clientClaz = Class.forName(this.getExecutor().getClient());
            Object client = clientClaz.getConstructor().newInstance();
            this.initializeApiClient(client);

            // Initialize api
            Class apiCls = Class.forName(this.getExecutor().getApi());
            Object api = apiCls.getConstructor(clientClaz).newInstance(client);

            // invoke method
            List<String> methodTokens = Arrays.asList(this.getExecutor().getMethod().split(","));

            List<String> entityTokens = Arrays.asList(this.getExecutor().getEntity().split(","));
            Class entityCls = Class.forName(entityTokens.get(0));
            Object entity = entityCls.newInstance();
            Method method = api.getClass().getMethod(methodTokens.get(0), entityCls);
            Object result = method.invoke(api, this.initializeEntity(entity, entityTokens));

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
