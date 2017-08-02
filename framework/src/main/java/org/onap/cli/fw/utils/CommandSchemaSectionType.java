package org.onap.cli.fw.utils;

/**
 * Created by subh on 03/08/17.
 */
public enum CommandSchemaSectionType {
    ONAP_CMD_SCHEMA_VERSION,
    NAME,
    DESCRIPTION,
    SERVICE,
    PARAMETERS,
    RESULTS,
    HTTP,
    EXEC;

    public enum ServiceAttr {
        NAME,
        VERSION,
        NO_AUTH;
    }

    public enum ParameterAttr {
        NAME,
        DESCRIPTION,
        SHORT_OPTION,
        LONG_OPTION,
        DEFAULT_VALUE,
        TYPE,
        IS_OPTIONAL,
        IS_SECURED;
    }

    public enum ResultAttr {
        DIRECTION,
        ATTRIBUTES;
    }
}
