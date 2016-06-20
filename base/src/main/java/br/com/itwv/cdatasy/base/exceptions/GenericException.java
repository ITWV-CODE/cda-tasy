package br.com.itwv.cdatasy.base.exceptions;

import br.com.itwv.cdatasy.base.logging.LoggingDataSchema;

public abstract class GenericException extends Exception {

	private static final long serialVersionUID = 7734364188422380044L;

	protected String exceptionName = null;
	protected String exceptionMessage = null;
	protected LoggingDataSchema logging = null;

	public GenericException(Exception e) {
		super(e.getMessage());
	}

	public GenericException(String exceptionName, String exceptionMessage) {
		super(exceptionMessage);
		this.setExceptionName(exceptionName);
		this.setExceptionMessage(exceptionMessage);
	}

	public String getExceptionName() {
		return exceptionName;
	}

	protected void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	protected void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public LoggingDataSchema getLoggingDataSchema() {
		return this.logging;
	}

	protected void setLoggingDataSchema(LoggingDataSchema logging) {
		this.logging = logging;
	}
}
