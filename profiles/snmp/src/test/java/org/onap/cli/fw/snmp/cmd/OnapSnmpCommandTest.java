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

package org.onap.cli.fw.snmp.cmd;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.OnapCommandParameterType;
import org.onap.cli.fw.output.OnapCommandResult;
import org.onap.cli.fw.output.OnapCommandResultAttribute;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;


public class OnapSnmpCommandTest {

//    @Test
//    public void testSnmpGetCommand() throws OnapCommandException {
//
//        new MockUp<Snmp>() {
//            @Mock
//            public ResponseEvent send(PDU pdu, Target target, TransportMapping transport) throws IOException {
//                PDU pdu1 = new PDU();
//                pdu1.setType(-94);
//                VariableBinding variableBinding = new VariableBinding(new OID("1.3.6.1.2.1.1.1.0"));
//                variableBinding.setVariable(new OctetString(new byte[] {'s', 'n', 'm', 'p'}));
//
//                pdu1.setVariableBindings(Arrays.asList(variableBinding));
//                return new ResponseEvent(new Object(), null, pdu, pdu1, null);
//            }
//        };
//
//        OnapSnmpCommand snmpCmd = new OnapSnmpCommand();
//
//        HashMap<String, String> resultMapEntry = new HashMap<>();
//        resultMapEntry.put("system-desc", "1.3.6.1.2.1.1.1.0");
//
//        List<Map<String, String>> resultMap = Arrays.asList(resultMapEntry);
//        snmpCmd.setResultMap(resultMap);
//        snmpCmd.setSnmpVersion("1");
//        snmpCmd.setCommand("get");
//
//        OnapCommandParameter onapCommandParameter = new OnapCommandParameter();
//        onapCommandParameter.setName("agent");
//        onapCommandParameter.setParameterType(OnapCommandParameterType.STRING);
//        onapCommandParameter.setShortOption("x");
//        onapCommandParameter.setOptional(false);
//        onapCommandParameter.setLongOption("agent");
//        onapCommandParameter.setValue("udp:0.0.0.0/161");
//
//        Set<OnapCommandParameter> parameters = new HashSet<>();
//        parameters.add(onapCommandParameter);
//        snmpCmd.setParameters(parameters);
//
//        OnapCommandResultAttribute onapCommandResultAttribute = new OnapCommandResultAttribute();
//        onapCommandResultAttribute.setValues(new ArrayList<>());
//        onapCommandResultAttribute.setName("system-desc");
//
//
//        OnapCommandResult onapCommandResult = new OnapCommandResult();
//        onapCommandResult.setRecords(Arrays.asList(onapCommandResultAttribute));
//        snmpCmd.setResult(onapCommandResult);
//
//        snmpCmd.run();
//
//        OnapCommandResult result = snmpCmd.getResult();
//        assertEquals("snmp", result.getRecordsMap().get("system-desc").getValues().get(0));
//    }
}