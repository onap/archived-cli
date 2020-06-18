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
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Vector;

/**
 * Oclip snmp Command.
 *
 */
@OnapCommandSchema(type = OnapCommandSnmpConstants.SNMP_SCHEMA_PROFILE)
public class OnapSnmpCommand extends OnapCommand {

    private List<Map<String, String>> resultMap;

    private String version;

    private String command;

    public OnapSnmpCommand() {
        super.addDefaultSchemas(OnapCommandSnmpConstants.DEFAULT_PARAMETER_SNMP_FILE_NAME);
    }

    private String getAgent() {
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

    private Target getTarget() throws OnapCommandException { //NOSONAR
        Address targetAddress = GenericAddress.parse(this.getAgent());  //udp:127.0.0.1/161
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(OnapCommandSnmpConstants.SNMP_COMMNUNITY_STRING));
        target.setAddress(targetAddress);
        target.setRetries(OnapCommandSnmpConstants.RETRY_COUNT);
        target.setTimeout(OnapCommandSnmpConstants.TIMEOUT);

        switch (this.getSnmpVersion()) {
            case OnapCommandSnmpConstants.SNMP_VERSION_V1:
                target.setVersion(SnmpConstants.version1);
                break;

            case OnapCommandSnmpConstants.SNMP_VERSION_V2C:
                target.setVersion(SnmpConstants.version2c);
                break;

            default:
                break;
        }
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
                    if ( responseEvent != null) {
                        if (responseEvent.getResponse().getErrorStatus() == PDU.noError) {
                            Vector<? extends VariableBinding> variableBindings = responseEvent.
                                    getResponse().getVariableBindings();
                            variableBindings.stream().forEach(varBinding -> { //NOSONAR
                                String key = getKeyForValue(varBinding.getOid().toString());
                                if (key != null) {
                                    this.getResult().getRecordsMap().get(key).getValues().add(
                                            varBinding.getVariable().toString());
                                }
                            });
                        } else {
                            throw new OnapSnmpErrorResponse("Error response from SNMP agent",
                                responseEvent.getResponse().getErrorStatus());
                        }
                    } else {
                        throw new OnapSnmpErrorResponse("Failed to send SNMP GET command");
                    }
                    break;

                default:
                    break;
            }
            snmp.close();
        } catch (IOException ex) {
            throw new OnapSnmpErrorResponse(ex);
        }
    }

    private String getKeyForValue(String value) {
        Optional<Map<String, String>> mapOptional = resultMap.stream().filter(map -> map.values().contains(value)).findFirst(); //NOSONAR
        if (!mapOptional.isPresent()) {
            return null;
        }
        return mapOptional.get().keySet().iterator().next();
    }

    @Override
    protected List<String> initializeProfileSchema(Map<String, ?> schemaMap, boolean validate) throws OnapCommandException {
        return OnapCommandSchemaSnmpLoader.parseSnmpSchema(this, schemaMap, validate);
    }

    public String getSnmpVersion() {
        return version;
    }

    public void setSnmpVersion(String version) {
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
