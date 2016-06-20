package br.com.itwv.cdatasy.base.encoding.compressing;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Compression {

	public enum StringCompressor {
		;
		public static String compress(String str) throws IOException {
			if (str == null || str.length() == 0) {
				return str;
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes());
			gzip.close();
			return out.toString("ISO-8859-1");
		}

		public static String decompress(String str) throws IOException {
			if (str == null || str.length() == 0) {
				return str;
			}
			GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(
					str.getBytes("ISO-8859-1")));
			BufferedReader bf = new BufferedReader(new InputStreamReader(gis,
					"ISO-8859-1"));
			String outStr = new String();
			String line;
			while ((line = bf.readLine()) != null) {
				outStr += line;
			}
			return outStr;
		}
	}
}
