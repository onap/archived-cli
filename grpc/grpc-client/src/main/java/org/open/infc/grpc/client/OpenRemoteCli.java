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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.open.infc.grpc.Args;
import org.open.infc.grpc.Result;

public class OpenRemoteCli {
    public static final String OCLIP_GRPC_SERVER = "http://localhost:50051";
    public static final String OCLIP_GRPC_SERVER_ENV = "OCLIP_GRPC_SERVER";

    public static Result run (String[] args) throws Exception {
        String oclipHome = System.getenv(OCLIP_GRPC_SERVER_ENV);

        if (oclipHome == null) {
            oclipHome = OCLIP_GRPC_SERVER;
        }

          if (System.getenv("OPEN_CLI_DEBUG") == null) {
              Logger globalLogger = Logger.getLogger(OpenInterfaceGrpcClient.class.getName());
              globalLogger.setLevel(java.util.logging.Level.OFF);
          } else {
              System.out.println(OCLIP_GRPC_SERVER_ENV + "=" + oclipHome);
          }

          if (args.length <= 2 || !args[0].equals("-P")) {
              System.out.println("Usage: oclip -P <product-name> <command-name> <command-arguments");
              System.out.println("NOTE: Set environment variable " + OCLIP_GRPC_SERVER_ENV + " to OCLIP gRPC server. By default its " + OCLIP_GRPC_SERVER);
              System.exit(0);
          }

          List<String> argList = new ArrayList<>();

          for (String arg: args) {
              argList.add(arg);
          }

          //-P
          argList.remove(0);

          //<product-name>
          String product = argList.remove(0);

          URL oclipUrl = new URL(oclipHome);
          OpenInterfaceGrpcClient client = new OpenInterfaceGrpcClient(
                  oclipUrl.getHost(), oclipUrl.getPort());

          try {
              Result result = client.remoteCli(Args.newBuilder().addAllArgs(argList).setProduct(product).build());
              return result;
          } finally {
            client.shutdown();
          }
    }


    public static void main(String[] args) throws Exception {
        int exitCode = 1;
        try {
            Result result = OpenRemoteCli.run(args);
            System.out.println(result.getOutput());
            exitCode = result.getExitCode();
        } finally {
            System.exit(exitCode);
          }
    }
}
