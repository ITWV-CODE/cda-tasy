package br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.exceptions;


import br.com.itwv.cdatasy.base.exceptions.GenericException;
import br.com.itwv.cdatasy.base.exceptions.IGeneralExceptionInterface;
import br.com.itwv.cdatasy.base.exceptions.facades.LoggingDataSchemaFacade;
import br.com.itwv.cdatasy.base.logging.LoggingSeverity;
import br.com.itwv.cdatasy.base.logging.wrappers.LoggingWrapper;
import org.apache.commons.lang3.StringUtils;

public class DuplicateEntryException extends GenericException implements IGeneralExceptionInterface {

    private static final long serialVersionUID = -8734054120019341431L;
    private static DuplicateEntryException instance = null;

    public DuplicateEntryException() {
        super("DuplicateEntryException", "Duplicate entry exception.");
    }

    public static DuplicateEntryException getInstance() {
        if (instance == null) {
            instance = new DuplicateEntryException();
        }
        return instance;
    }

    public Exception definesException(String executionPoint, String message, boolean resumable, LoggingSeverity severity) {

        if (StringUtils.isNotEmpty(message))
            super.setExceptionMessage(message);

        this.setLoggingDataSchema(LoggingDataSchemaFacade.getInstance().createLoggingDataSchema(
                new LoggingWrapper.Wrap(this.exceptionMessage, this.exceptionName, executionPoint, message, resumable, severity)));

        return this;
    }
}
