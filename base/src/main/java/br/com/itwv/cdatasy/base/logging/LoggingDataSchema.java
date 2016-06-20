
package br.com.itwv.cdatasy.base.logging;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "loggingDataSchemaItem"
})
@XmlRootElement(name = "LoggingDataSchema", namespace = "http://www.itwv.com.br/CDA-TASY/LoggingDataSchema")
public class LoggingDataSchema {

    @XmlElement(name = "LoggingDataSchemaItem", namespace = "http://www.itwv.com.br/CDA-TASY/LoggingDataSchema", required = true)
    protected LoggingDataSchemaItem loggingDataSchemaItem;


    public LoggingDataSchemaItem getLoggingDataSchemaItem() {
        return loggingDataSchemaItem;
    }

    public void setLoggingDataSchemaItem(LoggingDataSchemaItem value) {
        this.loggingDataSchemaItem = value;
    }

}
