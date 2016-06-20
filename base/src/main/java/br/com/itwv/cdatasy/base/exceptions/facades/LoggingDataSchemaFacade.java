package br.com.itwv.cdatasy.base.exceptions.facades;

import br.com.itwv.cdatasy.base.logging.LoggingDataSchemaFactory;
import br.com.itwv.cdatasy.base.logging.LoggingDataSchemaItem;
import br.com.itwv.cdatasy.base.logging.LoggingDataSchema;
import br.com.itwv.cdatasy.base.logging.LoggingDefinition;
import br.com.itwv.cdatasy.base.logging.wrappers.LoggingWrapper;

import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;

public class LoggingDataSchemaFacade {

	private static LoggingDataSchemaFacade instance = null;

	protected LoggingDataSchemaFacade() {
	}

	public static LoggingDataSchemaFacade getInstance() {
		if (instance == null) {
			instance = new LoggingDataSchemaFacade();
		}

		return instance;
	}

	public LoggingDataSchema createLoggingDataSchema(
			LoggingWrapper.Wrap loggingWrapper) {

		try {
			LoggingDataSchemaFactory factory = new LoggingDataSchemaFactory();

			LoggingDataSchema loggingDataSchema = factory
					.createLoggingDataSchema();
			LoggingDataSchemaItem loggingDataSchemaItem = factory
					.createLoggingDataSchemaItem();
			LoggingDefinition loggingDefinition = factory
					.createLoggingDefinition();

			loggingDefinition.setExceptionMessage(loggingWrapper
					.getExceptionMessage());
			loggingDefinition.setExceptionName(loggingWrapper
					.getExceptionName());
			loggingDefinition.setExecutionPoint(loggingWrapper
					.getExecutionPoint());
			loggingDefinition.setMessage(loggingWrapper.getMessage());
			loggingDefinition.setResumable(loggingWrapper.isResumable());
			loggingDefinition.setSeverity(loggingWrapper.getSeverity());
			loggingDefinition.setDateTime(loggingWrapper.getDateTime());
			loggingDefinition
					.setDateTime(DatatypeFactory.newInstance()
							.newXMLGregorianCalendar(
									(GregorianCalendar) GregorianCalendar
											.getInstance()));

			loggingDataSchemaItem.setLoggingDefinition(loggingDefinition);
			loggingDataSchema.setLoggingDataSchemaItem(loggingDataSchemaItem);
			return loggingDataSchema;
		} catch (Exception exception) {
			return null;
		}
	}
}
