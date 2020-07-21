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

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.open.infc.grpc.Args;
import org.open.infc.grpc.Input;
import org.open.infc.grpc.OpenInterfaceGrpc;
import org.open.infc.grpc.Output;
import org.open.infc.grpc.Result;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class OpenInterfaceGrpcClient {
      private static final Logger logger = LoggerFactory.getLogger(OpenInterfaceGrpcClient.class.getName());

      private final ManagedChannel channel;
      private final OpenInterfaceGrpc.OpenInterfaceBlockingStub blockingStub;

      //10 seconds
      private int timeout = 60000;

      public static class OpenInterfaceGrpcExecption extends Exception {
          private static final long serialVersionUID = -8755636432217894246L;

          private int errorCode = -1; //NOSONAR

          public OpenInterfaceGrpcExecption(int errorCode, String message) {
              super(message);
              this.errorCode = errorCode;
          }
      }

      public static class OpenInterfaceGrpcTimeoutExecption extends OpenInterfaceGrpcExecption {
          private static int errorCode = 1;

          public OpenInterfaceGrpcTimeoutExecption(String message) {
              super(errorCode, message);
          }
      }

      public OpenInterfaceGrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
            // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
            // needing certificates.
            .usePlaintext(true)
            .build());
      }

      public OpenInterfaceGrpcClient(String host, int port, int timeout) {
          this(host, port);
          this.timeout = timeout;

      }
      OpenInterfaceGrpcClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = OpenInterfaceGrpc.newBlockingStub(channel);
      }

      public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
      }

      public Output invoke(Input input) throws OpenInterfaceGrpcTimeoutExecption {
        logger.info("Input {}", input);

        Output result = Output.newBuilder().build();
        try {
            result = blockingStub.withDeadlineAfter(timeout, TimeUnit.MILLISECONDS).invoke(input);
        } catch (StatusRuntimeException e) {
          logger.warn("RPC failed: {}", e.getStatus());
          //Status{code=DEADLINE_EXCEEDED}
          throw new OpenInterfaceGrpcTimeoutExecption(e.getMessage());
        }
        logger.info("Output: {}", result);
        return result;
      }

      public Result remoteCli(Args args) throws OpenInterfaceGrpcTimeoutExecption {
        logger.info("{}", args);

        Result result = Result.newBuilder().setExitCode(1).build();
        try {
            result = blockingStub.withDeadlineAfter(timeout, TimeUnit.MILLISECONDS).remoteCli(args);
        } catch (StatusRuntimeException e) {
          logger.warn("RPC failed: {}", e.getStatus());
          //Status{code=DEADLINE_EXCEEDED}
          throw new OpenInterfaceGrpcTimeoutExecption(e.getMessage());
        }

        logger.info("Result: {}", result);
        return result;
      }
}
