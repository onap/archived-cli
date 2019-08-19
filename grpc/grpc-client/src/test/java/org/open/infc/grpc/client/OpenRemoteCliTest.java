/*
 * Copyright 2019 Huawei Technologies Co., Ltd.
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

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class OpenRemoteCliTest {
    OpenRemoteCli openRemoteCli;
    @Before
    public void setUp() throws Exception {
        openRemoteCli= new OpenRemoteCli("localhost",8080,123,"abc");
    }
    @Test(expected = OpenInterfaceGrpcClient.OpenInterfaceGrpcTimeoutExecption.class)
    public void runTest() throws Exception {
        List<String > list =new ArrayList<>();
        list.add("abc");
        openRemoteCli.run(list);
    }
    @Test(expected = OpenInterfaceGrpcClient.OpenInterfaceGrpcTimeoutExecption.class)
    public void runTest2() throws Exception {
        List<String > list =new ArrayList<>();
        list.add("abc");
        openRemoteCli.run("localhost",8080,"abc",list);
    }
    @Test(expected = OpenInterfaceGrpcClient.OpenInterfaceGrpcTimeoutExecption.class)
    public void invokeTest() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("product", "product");
        openRemoteCli.invoke("localhost",8080,"product","profile","action","abc123",map);
    }
}