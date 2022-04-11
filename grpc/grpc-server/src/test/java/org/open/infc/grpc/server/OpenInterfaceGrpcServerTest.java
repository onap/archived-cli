/*-
 * ============LICENSE_START=======================================================
 * ONAP : APPC
 * ================================================================================
 * Copyright (C) 2022 Samsung Electronics
 * =============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END=========================================================
 */

package org.open.infc.grpc.server;

import org.junit.Test;

import java.io.IOException;

public class OpenInterfaceGrpcServerTest {

    private static final String CONF_FILE = "oclip-grpc-server.properties";
    private static final String CONF_SERVER_PORT = "oclip.grpc_server_port";
    private static final String CONF_SERVER_HOST = "oclip.grpc_server_host";

    @Test(expected=IOException.class)
    public void multiInputArgs() throws IOException, InterruptedException{
        String[] args = {"test1","test2","test3","test4","test5"};
        OpenInterfaceGrpcServer interfaceGrpcServer = new OpenInterfaceGrpcServer();
        OpenInterfaceGrpcServer.main(args);
    }

    @Test(expected=NullPointerException.class)
    public void invalidNullInputArgs() throws IOException, InterruptedException{
        OpenInterfaceGrpcServer interfaceGrpcServer = new OpenInterfaceGrpcServer();
        OpenInterfaceGrpcServer.main(null);
    }

    @Test(expected=NumberFormatException.class)
    public void invalidInputArgs() throws IOException, InterruptedException{
        String[] args = {"test"};
        OpenInterfaceGrpcServer interfaceGrpcServer = new OpenInterfaceGrpcServer();
        OpenInterfaceGrpcServer.main(args);
    }
}
