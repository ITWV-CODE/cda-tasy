package br.com.itwv.cdatasy.base.encoding.streams;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedList;

public class FastByteArrayOutputStream extends OutputStream {
	private static final int DEFAULT_BLOCK_SIZE = 8192;

	private LinkedList<byte[]> buffers;

	private byte[] buffer;

	private boolean closed;
	private int blockSize;
	private int index;
	private int size;

	public FastByteArrayOutputStream() {
		this(DEFAULT_BLOCK_SIZE);
	}

	public FastByteArrayOutputStream(int aSize) {
		blockSize = aSize;
		buffer = new byte[blockSize];
	}

	public int getSize() {
		return size + index;
	}

	public void close() {
		closed = true;
	}

	public byte[] toByteArray() {
		byte[] data = new byte[getSize()];

		int pos = 0;

		if (buffers != null) {
			Iterator<byte[]> iter = buffers.iterator();

			while (iter.hasNext()) {
				byte[] bytes = iter.next();
				System.arraycopy(bytes, 0, data, pos, blockSize);
				pos += blockSize;
			}
		}

		System.arraycopy(buffer, 0, data, pos, index);

		return data;
	}

	public String toString() {
		return new String(toByteArray());
	}

	public void write(int datum) throws IOException {
		if (closed) {
			throw new IOException("Stream closed");
		} else {
			if (index == blockSize) {
				addBuffer();
			}

			buffer[index++] = (byte) datum;
		}
	}

	public void write(byte[] data, int offset, int length) throws IOException {
		if (data == null) {
			throw new NullPointerException();
		} else if ((offset < 0) || ((offset + length) > data.length) || (length < 0)) {
			throw new IndexOutOfBoundsException();
		} else if (closed) {
			throw new IOException("Stream closed");
		} else {
			if ((index + length) > blockSize) {
				int copyLength;

				do {
					if (index == blockSize) {
						addBuffer();
					}

					copyLength = blockSize - index;

					if (length < copyLength) {
						copyLength = length;
					}

					System.arraycopy(data, offset, buffer, index, copyLength);
					offset += copyLength;
					index += copyLength;
					length -= copyLength;
				} while (length > 0);
			} else {
				System.arraycopy(data, offset, buffer, index, length);
				index += length;
			}
		}
	}

	public void writeTo(OutputStream out) throws IOException {
		if (buffers != null) {
			Iterator<byte[]> iter = buffers.iterator();

			while (iter.hasNext()) {
				byte[] bytes = iter.next();
				out.write(bytes, 0, blockSize);
			}
		}

		out.write(buffer, 0, index);
	}

	public void writeTo(RandomAccessFile out) throws IOException {
		if (buffers != null) {
			Iterator<byte[]> iter = buffers.iterator();

			while (iter.hasNext()) {
				byte[] bytes = iter.next();
				out.write(bytes, 0, blockSize);
			}
		}

		out.write(buffer, 0, index);
	}

	public void writeTo(Writer out, String encoding) throws IOException {
		if (buffers != null) {
			writeToViaSmoosh(out, encoding);
		} else {
			writeToViaString(out, encoding);
		}
	}

	void writeToViaString(Writer out, String encoding) throws IOException {
		byte[] bufferToWrite = buffer;
		int bufferToWriteLen = index;
		writeToImpl(out, encoding, bufferToWrite, bufferToWriteLen);
	}

	void writeToViaSmoosh(Writer out, String encoding) throws IOException {
		byte[] bufferToWrite = toByteArray();
		int bufferToWriteLen = bufferToWrite.length;
		writeToImpl(out, encoding, bufferToWrite, bufferToWriteLen);
	}

	private void writeToImpl(Writer out, String encoding, byte[] bufferToWrite, int bufferToWriteLen) throws IOException {
		String writeStr;
		if (encoding != null) {
			writeStr = new String(bufferToWrite, 0, bufferToWriteLen, encoding);
		} else {
			writeStr = new String(bufferToWrite, 0, bufferToWriteLen);
		}
		out.write(writeStr);
	}

	protected void addBuffer() {
		if (buffers == null) {
			buffers = new LinkedList<byte[]>();
		}

		buffers.addLast(buffer);

		buffer = new byte[blockSize];
		size += index;
		index = 0;
	}
}
