package br.com.itwv.cdatasy.base.encoding.xml;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public abstract class GenericMarshaller implements Marshaller {

	public static class Marshall<T> {
		private T t = null;
		private Marshaller marshaller = null;

		public Marshall(T t) {
			super();
			this.t = t;
			this.instanceContext();
		}

		protected void instanceContext() {
			try {
				JAXBContext jaxbContext = null;
				jaxbContext = JAXBContext.newInstance(new Class[] { this.t
						.getClass() });

				this.marshaller = jaxbContext.createMarshaller();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public String marshal(T t) {
			try {
				StringWriter writer = new StringWriter();
				this.marshaller.marshal(t, writer);
				return writer.toString();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}