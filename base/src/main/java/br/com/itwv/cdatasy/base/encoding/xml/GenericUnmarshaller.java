package br.com.itwv.cdatasy.base.encoding.xml;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;


public abstract class GenericUnmarshaller implements Unmarshaller {
	public static class Unmarshall<T> {
		private T t = null;
		private Unmarshaller unmarshaller = null;

		public Unmarshall(T t) {
			super();
			this.t = t;
			this.instanceContext();
		}

		protected void instanceContext() {
			try {
				JAXBContext jaxbContext = null;
				jaxbContext = JAXBContext.newInstance(new Class[] { this.t
						.getClass() });

				this.unmarshaller = jaxbContext.createUnmarshaller();
			} catch (Exception e) {
				return;
			}
		}


		public T unmarshal(InputStream inputStream) {
			try {
				T t = (T) this.unmarshaller.unmarshal(XMLInputFactory
						.newInstance().createXMLStreamReader(inputStream,
								"UTF-8"));
				return t;
			} catch (Exception e) {
				return null;
			}
		}
	}
}