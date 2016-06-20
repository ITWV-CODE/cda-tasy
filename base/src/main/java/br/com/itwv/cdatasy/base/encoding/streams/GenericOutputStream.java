package br.com.itwv.cdatasy.base.encoding.streams;

import java.io.IOException;
import java.io.OutputStream;

public class GenericOutputStream extends OutputStream {

	protected StringBuffer buffer = new StringBuffer();

	public void close() {
		try {
			super.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void flush() {
		buffer.delete(0, buffer.length());
	}

	public void write(byte[] b) {
		String str = new String(b);
		this.buffer.append(str);
	}

	public void write(byte[] b, int off, int len) {
		String str = new String(b, off, len);
		this.buffer.append(str);
	}

	public void write(int b) {
		String str = Integer.toString(b);
		this.buffer.append(str);
	}

	public String toString() {
		return buffer.toString();
	}
}