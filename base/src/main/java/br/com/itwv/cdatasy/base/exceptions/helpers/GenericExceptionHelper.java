package br.com.itwv.cdatasy.base.exceptions.helpers;


import br.com.itwv.cdatasy.base.encoding.xml.GenericMarshaller;
import br.com.itwv.cdatasy.base.exceptions.GenericException;
import br.com.itwv.cdatasy.base.exceptions.SuccessfulCompletionException;
import br.com.itwv.cdatasy.base.exceptions.UnknownException;
import br.com.itwv.cdatasy.base.logging.LoggingDataSchema;
import br.com.itwv.cdatasy.base.logging.LoggingSeverity;

public class GenericExceptionHelper {

    public static String SerializeGenericException(Exception e,
                                                   String methodName) {

        if (e == null)
            return new GenericMarshaller.Marshall<LoggingDataSchema>(
                    new LoggingDataSchema()).marshal(GenericException.class
                    .cast(SuccessfulCompletionException.getInstance()
                            .definesException(methodName, null, true,
                                    LoggingSeverity.INFORMATION))
                    .getLoggingDataSchema());

        try {
            return new GenericMarshaller.Marshall<LoggingDataSchema>(
                    new LoggingDataSchema()).marshal(GenericException.class
                    .cast(e).getLoggingDataSchema());
        } catch (Exception e2) {
            return new GenericMarshaller.Marshall<LoggingDataSchema>(
                    new LoggingDataSchema()).marshal(GenericException.class
                    .cast(UnknownException.getInstance().definesException(
                            methodName, e.getLocalizedMessage(), false,
                            LoggingSeverity.ERROR)).getLoggingDataSchema());
        }
    }
}
