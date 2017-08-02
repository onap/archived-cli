package org.onap.cli.fw.utils;

public enum CommandSchemaSectionType {
    ONAP_CMD_SCHEMA_VERSION,
    NAME,
    DEFAULT_PARAMETERS,
    DESCRIPTION,
    SERVICE,
    PARAMETERS,
    RESULTS,
    HTTP;

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

        public enum ResutAttrType {
            NAME,
            DESCRIPTION,
            SCOPE,
            TYPE,
            IS_SECURED;
        }
    }
}
