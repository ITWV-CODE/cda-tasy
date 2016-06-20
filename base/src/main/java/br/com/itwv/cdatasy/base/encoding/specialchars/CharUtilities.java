package br.com.itwv.cdatasy.base.encoding.specialchars;

public class CharUtilities {

	public static int CharToASCII(final char character) {
		return (int) character;
	}

	public static char ASCIIToChar(final int ascii) {
		return (char) ascii;
	}

	public static String hexToASCII(String hex) {

		hex = hex.replace(CharConstants.HL7_SPECIALCHARS_START_CHAR, "")
				.replace(CharConstants.HL7_SPECIALCHARS_END_CHAR, "");

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < hex.length() - 1; i += 2) {
			String output = hex.substring(i, (i + 2));
			int decimal = Integer.parseInt(output, 16);
			sb.append((char) decimal);
		}
		return sb.toString();
	}

	public static String ASCIIToHexadecimal(final char character) {
		return Integer.toHexString((int) character);
	}

	public static String ASCIIToHL7CharEncoding(final char character) {
		return String.valueOf(CharConstants.HL7_SPECIALCHARS_START_CHAR
				+ ASCIIToHexadecimal(character)
				+ CharConstants.HL7_SPECIALCHARS_END_CHAR);
	}
}
