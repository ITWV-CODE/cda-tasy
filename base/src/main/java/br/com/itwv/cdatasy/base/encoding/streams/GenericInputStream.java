package br.com.itwv.cdatasy.base.encoding.streams;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class GenericInputStream extends BufferedInputStream {

	public GenericInputStream(InputStream is) {
		super(is);
	}

	@Override
	public abstract int read() throws IOException;

	@Override
	public int read(byte b[]) throws IOException {
		return read(b, 0, b.length);
	}

	@Override
	public int read(byte b[], int off, int len) throws IOException {
		int bytesRead = super.read(b, off, len);
		super.mark(0);
		return bytesRead;
	}
}
