package br.com.itwv.cdatasy.base.logging.wrappers;

import br.com.itwv.cdatasy.base.logging.LoggingDefinition;
import br.com.itwv.cdatasy.base.logging.LoggingSeverity;

public abstract class LoggingWrapper {

    public static class Wrap extends LoggingDefinition {
        public Wrap(String exceptionMessage, String exceptionName,
                    String executionPoint, String message, Boolean resumable,
                    LoggingSeverity severity) {

            super();

            super.setExceptionMessage(exceptionMessage);
            super.setExceptionName(exceptionName);
            super.setExecutionPoint(executionPoint);
            super.setMessage(message);
            super.setResumable(resumable);
            super.setSeverity(severity);
        }
    }
}