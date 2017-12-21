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

import org.onap.cli.fw.cmd.OnapCommand;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.schema.OnapCommandSchema;
import org.onap.cli.fw.snmp.conf.OnapCommandSnmpConstants;
import org.onap.cli.fw.snmp.exception.OnapSnmpErrorResponse;
import org.onap.cli.fw.snmp.schema.OnapCommandSchemaSnmpLoader;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.*;

/**
 * Oclip snmp Command.
 *
 */
@OnapCommandSchema(type = OnapCommandSnmpConstants.SNMP_SCHEMA_PROFILE)
public class OnapSnmpCommand extends OnapCommand {

    private List<Map<String, String>> resultMap;

    private String version;

    private String command;

    private static final int RETRY_COUNT = 2;
    private static final int TIMEOUT = 1500;

    private String getAgent() throws OnapCommandException {
        OnapCommandParameter onapCommandParameter = this.getParametersMap().get(OnapCommandSnmpConstants.SNMP_AGENT);
        return (String) onapCommandParameter.getValue();
    }

    private PDU getPDU(Integer commandType, String[] oids) {
        PDU pdu = new PDU();
        for (String oid: oids) {
            pdu.add(new VariableBinding(new OID(oid)));
        }

        pdu.setType(commandType);
        return pdu;
    }

    private Target getTarget() throws OnapCommandException {
        Address targetAddress = GenericAddress.parse(this.getAgent());  //udp:127.0.0.1/161
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(OnapCommandSnmpConstants.SNMP_COMMNUNITY_STRING));
        target.setAddress(targetAddress);
        target.setRetries(RETRY_COUNT);
        target.setTimeout(TIMEOUT);
        target.setVersion(SnmpConstants.version1);
        return target;
    }

    @Override
    protected void run() throws OnapCommandException {
        try {

            // set JVM constant to avoid delay by snmp4j
            System.setProperty("java.security.egd", "file:data/./urandom");

            DefaultUdpTransportMapping defaultUdpTransportMapping = new DefaultUdpTransportMapping();
            defaultUdpTransportMapping.listen();
            Snmp snmp = new Snmp(defaultUdpTransportMapping);

            List<String> oids = new ArrayList<>();
            for (Map<String, String> map: resultMap) {
                oids.addAll(map.values());
            }

            String[] oidStrArr = oids.toArray(new String[oids.size()]);

            switch (this.command) {

                case OnapCommandSnmpConstants.SNMP_CMD_GET:
                    ResponseEvent responseEvent = snmp.send(getPDU(PDU.GET, oidStrArr), getTarget(), null);
                    if ( responseEvent != null || responseEvent.getResponse().getErrorStatus() == PDU.noError) {
                        Vector<? extends VariableBinding> variableBindings = responseEvent.getResponse().getVariableBindings();
                        variableBindings.stream().forEach(varBinding -> {
                            String key = getKeyForValue(varBinding.getOid().toString());
                            this.getResult().getRecordsMap().get(key).getValues().add(varBinding.getVariable().toString());
                        });
                    } else {
                        throw new OnapSnmpErrorResponse("Error Response from SNMP agent",
                                responseEvent.getResponse().getErrorStatus());
                    }
                    break;

                case OnapCommandSnmpConstants.SNMP_CMD_SET:
                    break;

                default:
                    break;
            }
            if (this.command.equalsIgnoreCase(OnapCommandSnmpConstants.SNMP_CMD_GET)) {

            }

            snmp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getKeyForValue(String value) {
        Optional<Map<String, String>> mapOptional = resultMap.stream().filter(map -> map.values().contains(value)).findFirst();
        if (!mapOptional.isPresent()) {
            return null;
        }
        return mapOptional.get().keySet().iterator().next();
    }

    @Override
    protected List<String> initializeProfileSchema(boolean validate) throws OnapCommandException {
        return OnapCommandSchemaSnmpLoader.loadSnmpSchema(this, this.getSchemaName(), true, validate);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }


    public List<Map<String, String>> getResultMap() {
        return resultMap;
    }

    public void setResultMap(List<Map<String, String>> resultMap) {
        this.resultMap = resultMap;
    }

}
