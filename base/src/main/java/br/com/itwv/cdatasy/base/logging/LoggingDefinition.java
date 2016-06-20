package br.com.itwv.cdatasy.base.logging;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoggingDefinition", propOrder = {
        "message",
        "exceptionName",
        "exceptionMessage",
        "executionPoint"
})
public class LoggingDefinition {

    @XmlElement(name = "Message", required = true)
    protected String message;
    @XmlElement(name = "ExceptionName")
    protected String exceptionName;
    @XmlElement(name = "ExceptionMessage")
    protected String exceptionMessage;
    @XmlElement(name = "ExecutionPoint")
    protected String executionPoint;
    @XmlAttribute(name = "DateTime", required = true)
    protected XMLGregorianCalendar dateTime;
    @XmlAttribute(name = "Resumable", required = true)
    protected boolean resumable;
    @XmlAttribute(name = "Severity", required = true)
    protected LoggingSeverity severity;

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String value) {
        this.exceptionName = value;
    }


    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String value) {
        this.exceptionMessage = value;
    }

    public String getExecutionPoint() {
        return executionPoint;
    }

    public void setExecutionPoint(String value) {
        this.executionPoint = value;
    }

    public XMLGregorianCalendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(XMLGregorianCalendar value) {
        this.dateTime = value;
    }

    public boolean isResumable() {
        return resumable;
    }

    public void setResumable(boolean value) {
        this.resumable = value;
    }

    public LoggingSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(LoggingSeverity value) {
        this.severity = value;
    }

}
