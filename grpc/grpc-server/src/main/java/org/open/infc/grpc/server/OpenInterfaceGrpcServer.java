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

package org.open.infc.grpc.server;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.output.OnapCommandResultType;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;
import org.onap.cli.main.OnapCli;
import org.open.infc.grpc.Args;
import org.open.infc.grpc.Input;
import org.open.infc.grpc.OpenInterfaceGrpc;
import org.open.infc.grpc.Output;
import org.open.infc.grpc.Output.Builder;
import org.open.infc.grpc.Result;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class OpenInterfaceGrpcServer {

      private static final Logger logger = Logger.getLogger(OpenInterfaceGrpcServer.class.getName());

      private static final String CONF_FILE = "oclip-grpc-server.properties";
      private static final String CONF_SERVER_PORT = "oclip.grpc_server_port";

      static {
          OnapCommandConfig.addProperties(CONF_FILE);
      }
      private Server server;

      private void start() throws IOException {
        /* The port on which the server should run */
        int port = Integer.parseInt(OnapCommandConfig.getPropertyValue(CONF_SERVER_PORT));
        server = ServerBuilder.forPort(port)
            .addService(new OpenInterfaceGrpcImpl())
            .build()
            .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
          @Override
          public void run() {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            OpenInterfaceGrpcServer.this.stop();
            System.err.println("*** server shut down");
          }
        });
      }

      private void stop() {
        if (server != null) {
          server.shutdown();
        }
      }

      /**
       * Await termination on the main thread since the grpc library uses daemon threads.
       */
      private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
          server.awaitTermination();
        }
      }

      /**
       * Main launches the server from the command line.
       */
      public static void main(String[] args) throws IOException, InterruptedException {
        final OpenInterfaceGrpcServer server = new OpenInterfaceGrpcServer();
        server.start();
        server.blockUntilShutdown();
      }

      static class OpenRemoteCli extends OnapCli {
          private String outputs = "";
          public OpenRemoteCli(String product, String[] args) {
            super(product, args);
          }

          public void print(String msg) {
              outputs += msg + "\n";
          }

          public String getResult() {
              return outputs;
          }
      }

      static class OpenInterfaceGrpcImpl extends OpenInterfaceGrpc.OpenInterfaceImplBase {

        @Override
        public void invoke(Input req, StreamObserver<Output> responseObserver) {
            Builder reply = Output.newBuilder();
            logger.info(req.toString());

            String product = req.getOptionsMap().get(OnapCommandConstants.OPEN_CLI_PRODUCT_NAME);
            String format =  req.getOptionsMap().getOrDefault(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_FORMAT, OnapCommandResultType.JSON.name().toLowerCase());
            String command = req.getAction();

            try {
                OnapCommand cmd = OnapCommandRegistrar.getRegistrar().get(command, product);
                cmd.getParametersMap().get(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_FORMAT).setValue(format);
                for (Entry<String, String> arg: req.getParams().entrySet()) {
                    cmd.getParametersMap().get(arg.getKey()).setValue(arg.getValue());
                }
                cmd.execute();

                reply.putAttrs(OnapCommandConstants.RESULTS, cmd.getResult().print());
                reply.setSuccess(true);
                reply.putAttrs(OnapCommandConstants.ERROR, "{}");
            } catch (OnapCommandException e) {
                logger.info(e.getMessage());
                reply.putAttrs(OnapCommandConstants.RESULTS, "{}");
                reply.setSuccess(false);
                reply.putAttrs(OnapCommandConstants.ERROR, e.toJsonString());
            }

            responseObserver.onNext(reply.build());
            responseObserver.onCompleted();
        }

        @Override
        public void remoteCli(Args req, StreamObserver<Result> responseObserver) {
            logger.info(req.toString());

            OpenRemoteCli cli = new OpenRemoteCli(req.getProduct(), req.getArgsList().toArray(new String [] {}));
            cli.handle();

            Result reply = Result.newBuilder().setExitCode(cli.getExitCode()).setOutput(cli.getResult()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
      }
}
