package br.com.itwv.cdatasy.base.exceptions;


import br.com.itwv.cdatasy.base.logging.LoggingSeverity;

public interface IGeneralExceptionInterface {

    Exception definesException(String executionPoint, String message,
                               boolean resumable, LoggingSeverity severity);
}
