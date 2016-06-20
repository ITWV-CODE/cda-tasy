package br.com.itwv.cdatasy.base.logging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoggingDataSchemaItem", propOrder = {
        "loggingDefinition"
})
public class LoggingDataSchemaItem {

    @XmlElement(name = "LoggingDefinition", required = true)
    protected LoggingDefinition loggingDefinition;

    public LoggingDefinition getLoggingDefinition() {
        return loggingDefinition;
    }

    public void setLoggingDefinition(LoggingDefinition value) {
        this.loggingDefinition = value;
    }

}
