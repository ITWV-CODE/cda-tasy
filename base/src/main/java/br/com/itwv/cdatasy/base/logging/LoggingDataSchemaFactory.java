package br.com.itwv.cdatasy.base.logging;

import javax.xml.bind.annotation.XmlRegistry;


@XmlRegistry
public class LoggingDataSchemaFactory {

    public LoggingDataSchemaFactory() {
    }

    public LoggingDataSchema createLoggingDataSchema() {
        return new LoggingDataSchema();
    }


    public LoggingDataSchemaItem createLoggingDataSchemaItem() {
        return new LoggingDataSchemaItem();
    }

    public LoggingDefinition createLoggingDefinition() {
        return new LoggingDefinition();
    }

}
