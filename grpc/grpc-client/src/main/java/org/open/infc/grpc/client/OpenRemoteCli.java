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

package org.open.infc.grpc.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.open.infc.grpc.Args;
import org.open.infc.grpc.Input;
import org.open.infc.grpc.Output;
import org.open.infc.grpc.Result;

public class OpenRemoteCli {

    private String host;
    private int port;
    private int timeout;
    private String requestId;

    public OpenRemoteCli(String host, int port, int timeout, String requestId) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.requestId = requestId;

    }

    public Result run (List <String> args) throws Exception { //NOSONAR
        OpenInterfaceGrpcClient client = new OpenInterfaceGrpcClient(
                host, port, timeout);
        try {
            return client.remoteCli(Args.newBuilder().setRequestId(this.requestId).addAllArgs(args).build());
        } finally {
          client.shutdown();
        }
    }

    public Output invoke (String product, String profile, String action, Map <String, String> params) throws Exception { //NOSONAR
        OpenInterfaceGrpcClient client = new OpenInterfaceGrpcClient(
                host, port, timeout);
        try {

            Map <String, String> options = new HashMap<>();
            options.put("product", product);
            if (profile != null && !profile.isEmpty())
                options.put("profile", profile);
            params.put("format", "json");
            Input input = Input.newBuilder().setAction(action).setRequestId(requestId).putAllOptions(options).putAllParams(params).build();

            return client.invoke(input);
        } finally {
          client.shutdown();
        }
    }

    //Absolute the static methods
    /**
     * Runs CLI remotely
     * @param host
     * @param port
     * @param product
     * @param cmd
     * @param args
     * @return
     * @throws Exception
     */
    public static Result run (String host, int port, String reqId, List <String> args) throws Exception { //NOSONAR
          OpenInterfaceGrpcClient client = new OpenInterfaceGrpcClient(
                  host, port);

          try {
              return client.remoteCli(Args.newBuilder().setRequestId(reqId).addAllArgs(args).build());
          } finally {
            client.shutdown();
          }
    }

        /**
     * Runs commands as remote procedure call :)
     * @param host
     * @param port
     * @param product
     * @param action
     * @param reqId
     * @param params
     * @return
     * @throws Exception
     */
    public static Output invoke (String host, int port, String product, String profile, String action, String reqId, Map <String, String> params) throws Exception { //NOSONAR
        OpenInterfaceGrpcClient client = new OpenInterfaceGrpcClient(
                host, port);

        try {

            Map <String, String> options = new HashMap<>();
            options.put("product", product);
            if (profile != null && !profile.isEmpty())
                options.put("profile", profile);
            params.put("format", "json");
            Input input = Input.newBuilder().setAction(action).setRequestId(reqId).putAllOptions(options).putAllParams(params).build();

            return client.invoke(input);
        } finally {
          client.shutdown();
        }
    }
}
