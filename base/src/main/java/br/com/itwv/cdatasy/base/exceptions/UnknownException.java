package br.com.itwv.cdatasy.base.exceptions;


import br.com.itwv.cdatasy.base.exceptions.facades.LoggingDataSchemaFacade;
import br.com.itwv.cdatasy.base.logging.LoggingSeverity;
import br.com.itwv.cdatasy.base.logging.wrappers.LoggingWrapper;
import org.apache.commons.lang3.StringUtils;

public class UnknownException extends GenericException implements
        IGeneralExceptionInterface {

    private static final long serialVersionUID = 5333569125199719291L;
    private static UnknownException instance = null;

    protected UnknownException() {
        super("UnknownException", "Unknown exception.");
    }

    public static UnknownException getInstance() {
        if (instance == null) {
            instance = new UnknownException();
        }
        return instance;
    }

    public Exception definesException(String executionPoint, String message,
                                      boolean resumable, LoggingSeverity severity) {

        if (StringUtils.isNotEmpty(message))
            super.setExceptionMessage(message);

        this.setLoggingDataSchema(LoggingDataSchemaFacade.getInstance()
                .createLoggingDataSchema(
                        new LoggingWrapper.Wrap(this.exceptionMessage,
                                this.exceptionName, executionPoint, message,
                                resumable, severity)));

        return this;
    }
}
