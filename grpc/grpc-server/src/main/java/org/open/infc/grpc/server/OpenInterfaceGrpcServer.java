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
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.conf.OnapCommandConfig;
import org.onap.cli.fw.conf.OnapCommandConstants;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.OnapCommandParameterType;
import org.onap.cli.fw.output.OnapCommandResultType;
import org.onap.cli.fw.registrar.OnapCommandRegistrar;
import org.onap.cli.fw.store.OnapCommandExecutionStore;
import org.onap.cli.fw.store.OnapCommandExecutionStore.ExecutionStoreContext;
import org.onap.cli.main.OnapCli;
import org.onap.cli.main.utils.OnapCliArgsParser;
import org.open.infc.grpc.Args;
import org.open.infc.grpc.Input;
import org.open.infc.grpc.OpenInterfaceGrpc;
import org.open.infc.grpc.Output;
import org.open.infc.grpc.Output.Builder;
import org.open.infc.grpc.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonParser;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class OpenInterfaceGrpcServer {

      private static final Logger logger = LoggerFactory.getLogger(OpenInterfaceGrpcServer.class.getName());

      private static final String CONF_FILE = "oclip-grpc-server.properties";
      private static final String CONF_SERVER_PORT = "oclip.grpc_server_port";
      private static final String CONF_SERVER_HOST = "oclip.grpc_server_host";

      static {
          OnapCommandConfig.addProperties(CONF_FILE);
      }
      private Server server;

      private void start(String portArg) throws IOException {
        /* The port on which the server should run */
        int port = Integer.parseInt(portArg == null ? OnapCommandConfig.getPropertyValue(CONF_SERVER_PORT) : portArg);
        String host = OnapCommandConfig.getPropertyValue(CONF_SERVER_HOST);
        if (host == null) {
            host = InetAddress.getLocalHost().getHostAddress().trim();
        }
        server = ServerBuilder.forPort(port)
            .addService(new OpenInterfaceGrpcImpl())
            .build()
            .start();
        logger.info("Server started, listening on {}", port);

        try {
            OnapCommandRegistrar.getRegistrar().setHost(host);
            OnapCommandRegistrar.getRegistrar().setPort(port);
        } catch (OnapCommandException e) {
            //Never Occurs
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
          @Override
          public void run() {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down"); //NOSONAR
            OpenInterfaceGrpcServer.this.stop();
            System.err.println("*** server shut down"); //NOSONAR
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
        server.start(args.length ==1 ? args[0] : null); //NOSONAR
        server.blockUntilShutdown();
      }

      static class OpenRemoteCli extends OnapCli {
          public OpenRemoteCli(String[] args) {
            super(args);
        }

        private String outputs = "";

          @Override
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
            Output output = null;
            logger.info("{}", req);

            String product = req.getOptionsMap().get(OnapCommandConstants.RPC_PRODUCT);
            String format =  req.getOptionsMap().getOrDefault(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_FORMAT, OnapCommandResultType.JSON.name().toLowerCase());
            String commandName = req.getAction();
            String profile = req.getOptionsMap().get(OnapCommandConstants.RPC_PROFILE);
            OnapCommand cmd = null;

            ExecutionStoreContext executionStoreContext = null;
            try {
                cmd = OnapCommandRegistrar.getRegistrar().get(commandName, product);
                cmd.getParametersMap().get(OnapCommandConstants.DEFAULT_PARAMETER_OUTPUT_FORMAT).setValue(format);

                if (!cmd.isRpc()) {
                    if (profile != null) {
                        //Set the profile to current one
                        OnapCommandRegistrar.getRegistrar().setProfile(
                                profile,
                                new ArrayList<>(),
                                new ArrayList<>());

                        //fill from profile
                        Map<String, String> cache= OnapCommandRegistrar.getRegistrar().getParamCache(product);
                        for (OnapCommandParameter param: cmd.getParameters()) {
                            if (cache.containsKey(
                                    cmd.getInfo().getService() + ":" + cmd.getName() + ":" + param.getLongOption())) {
                                param.setValue(cache.get(
                                        cmd.getInfo().getService() + ":" + cmd.getName() + ":" + param.getLongOption()));
                            } else if (cache.containsKey(
                                    cmd.getInfo().getService() + ":" + param.getLongOption())) {
                                param.setValue(cache.get(
                                        cmd.getInfo().getService() + ":" + param.getLongOption()));
                            } else if (cache.containsKey(param.getLongOption())) {
                                param.setValue(cache.get(param.getLongOption()));
                            }
                        }
                    }

                    Set <String> params = cmd.getParametersMap().keySet();
                    for (Entry<String, String> arg: req.getParamsMap().entrySet()) {
                        if (params.contains(arg.getKey()))
                            cmd.getParametersMap().get(arg.getKey()).setValue(arg.getValue());
                    }

                    //fill values from the file, if needed
                    for (OnapCommandParameter param: cmd.getParameters()) {
                        if (param.getParameterType().equals(OnapCommandParameterType.JSON)) {
                            param.setValue(OnapCliArgsParser.readJsonStringFromUrl(param.getValue().toString(), param.getName()));

                        } else if (param.getParameterType().equals(OnapCommandParameterType.TEXT)) {
                            param.setValue(OnapCliArgsParser.readTextStringFromUrl(param.getValue().toString(), param.getName()));

                        } else if (param.getParameterType().equals(OnapCommandParameterType.BYTE)) {
                            param.setValue(OnapCliArgsParser.readBytesFromUrl(param.getValue().toString(), param.getName()));

                        } else if (param.getParameterType().equals(OnapCommandParameterType.YAML)) {
                            param.setValue(OnapCliArgsParser.readYamlStringFromUrl(param.getValue().toString(), param.getName()));

                        }
                    }

                } else {
                    cmd.getParametersMap().get(OnapCommandConstants.INFO_PRODUCT).setValue(product);

                    if (profile != null)
                        cmd.getParametersMap().get(OnapCommandConstants.RPC_PROFILE).setValue(profile);

                    cmd.getParametersMap().get(OnapCommandConstants.RPC_CMD).setValue(commandName);
                    cmd.getParametersMap().get(OnapCommandConstants.RPC_ARGS).setValue(req.getParamsMap());
                    cmd.getParametersMap().get(OnapCommandConstants.RPC_MODE).setValue(OnapCommandConstants.RPC_MODE_RUN_RPC);
                }

                    //Start the execution
                        if (!cmd.isRpc()&&req.getRequestId() != null && !req.getRequestId().isEmpty() && !(cmd.getInfo().getProduct().equalsIgnoreCase("open-cli") &&
                                cmd.getName().equalsIgnoreCase("execution-list"))) {
                        String input = cmd.getArgsJson(true);
                        executionStoreContext = OnapCommandExecutionStore.getStore().storeExectutionStart(
                                req.getRequestId(),
                                cmd.getInfo().getProduct(),
                                cmd.getInfo().getService(),
                                cmd.getName(),
                                profile,
                                input);
                        }
                cmd.setExecutionContext(executionStoreContext);
                cmd.execute();

                if (!cmd.isRpc()) {
                    //Track and/or persist the execution context
                    new OnapCli().handleTracking(cmd);

                    String printOut = cmd.getResult().print();
                    Builder reply = Output.newBuilder();
                    reply.putAttrs(OnapCommandConstants.ERROR, "{}");

                    if (executionStoreContext != null)
                        reply.putAddons("execution-id", executionStoreContext.getExecutionId());

                    // use the status from the plugin.
                    reply.setSuccess(cmd.getResult().isPassed());

                    setOutputAttr(reply, printOut);

                    output = reply.build();
                    logger.info("{}", output);
                } else {
                    //Rpc command will set the output.
                    output = (Output) cmd.getResult().getOutput();
                }

            } catch (OnapCommandException e) {
                logger.info(e.getMessage());

                Builder reply = Output.newBuilder();
                reply.putAttrs(OnapCommandConstants.RESULTS, "{}");
                reply.setSuccess(false);
                reply.putAttrs(OnapCommandConstants.ERROR, e.toJsonString());
                if (executionStoreContext != null) {
                       OnapCommandExecutionStore.getStore().storeExectutionEnd(
                                executionStoreContext,
                                null,
                                e.getMessage(),
                                cmd.getResult().getDebugInfo(),
                                false);
                      reply.putAddons("execution-id", executionStoreContext.getExecutionId());
                }

                output = reply.build();
            }

            responseObserver.onNext(output);
            responseObserver.onCompleted();
        }

        public static void setOutputAttr(Builder reply, String printOut){
            try {
                reply.putAttrs(OnapCommandConstants.RESULTS, JsonParser.parseString(printOut).toString());
            } catch (Exception e) { // NOSONAR
                reply.putAttrs(OnapCommandConstants.RESULTS, printOut);
            }
        }

        @Override
        public void remoteCli(Args req, StreamObserver<Result> responseObserver) {
            logger.info("{}", req);

            List<String> args = new ArrayList<>();
            if (req.getRequestId() != null) {
                args.add(OnapCommandParameter.printLongOption(OnapCommandConstants.RPC_REQID));
                args.add(req.getRequestId());
            }

            args.addAll(req.getArgsList());
            OpenRemoteCli cli = new OpenRemoteCli(args.toArray(new String [] {}));
            cli.handle();
            logger.info(cli.getResult());
            Result reply = Result.newBuilder().setExitCode(cli.getExitCode()).setOutput(cli.getResult()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
      }
}
