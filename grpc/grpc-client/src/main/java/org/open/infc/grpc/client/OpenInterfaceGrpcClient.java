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

      public OpenInterfaceGrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
            // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
            // needing certificates.
            .usePlaintext(true)
            .build());
      }

      OpenInterfaceGrpcClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = OpenInterfaceGrpc.newBlockingStub(channel);
      }

      public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
      }

      public Output invoke(Input input) {
        logger.info("Input " + input.toString());

        Output result = Output.newBuilder().build();
        try {
            result = blockingStub.invoke(input);
        } catch (StatusRuntimeException e) {
          logger.warn("RPC failed: {0}", e.getStatus());
        }
        logger.info("Output: " + result.toString());
        return result;
      }

      public Result remoteCli(Args args) {
        logger.info(args.toString());

        Result result = Result.newBuilder().setExitCode(1).build();
        try {
            result = blockingStub.remoteCli(args);
        } catch (StatusRuntimeException e) {
          logger.warn("RPC failed: {0}", e.getStatus());
        }

        logger.info("Result: " + result.toString());
        return result;
      }
}
