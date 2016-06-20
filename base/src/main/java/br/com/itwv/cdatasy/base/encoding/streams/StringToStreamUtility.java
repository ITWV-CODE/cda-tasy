package br.com.itwv.cdatasy.base.encoding.streams;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class StringToStreamUtility {
	private static StringToStreamUtility instance = null;

	protected StringToStreamUtility() {
	}

	public static StringToStreamUtility getInstance() {
		if (instance == null) {
			instance = new StringToStreamUtility();
		}
		return instance;
	}

	public InputStream convert(String str) throws UnsupportedEncodingException {
		return new ByteArrayInputStream(str.getBytes("UTF-8"));
	}
}
