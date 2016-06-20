package br.com.itwv.cdatasy.base.encoding.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class StreamToStringUtility {
	private static StreamToStringUtility instance = null;

	protected StreamToStringUtility() {
	}

	public static StreamToStringUtility getInstance() {
		if (instance == null) {
			instance = new StreamToStringUtility();
		}
		return instance;
	}
	
	public String convert(InputStream is, boolean closeStream)
			throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			is.mark(0);
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is,
						"UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}

			} finally {
				if (closeStream)
					is.close();
			}
			return writer.toString();

		} else {
			return "";
		}
	}
}
