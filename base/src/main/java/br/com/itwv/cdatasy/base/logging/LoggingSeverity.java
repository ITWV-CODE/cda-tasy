package br.com.itwv.cdatasy.base.logging;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "LoggingSeverity")
@XmlEnum
public enum LoggingSeverity {

    @XmlEnumValue("Information")
    INFORMATION("Information"),
    @XmlEnumValue("Warning")
    WARNING("Warning"),
    @XmlEnumValue("Error")
    ERROR("Error");
    private final String value;

    LoggingSeverity(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LoggingSeverity fromValue(String v) {
        for (LoggingSeverity c: LoggingSeverity.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
