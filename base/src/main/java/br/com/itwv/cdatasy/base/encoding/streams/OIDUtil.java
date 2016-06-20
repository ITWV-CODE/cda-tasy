package br.com.itwv.cdatasy.base.encoding.streams;

import java.math.BigInteger;
import java.util.UUID;

import br.com.itwv.cdatasy.base.encoding.strings.Base64;

public class OIDUtil {

	protected static final String OID_PREFIX = "2.25";
	protected static final String OID_PREFIX_REMOVAL_REGEX = "^" + OID_PREFIX + ".";

	protected UUID uuid;
	protected String oid;

	public OIDUtil() {
		uuid = UUID.randomUUID();
		oid = createOIDFromUUIDCanonicalHexString(uuid.toString());
	}

	public OIDUtil(String oid) throws IllegalArgumentException, NumberFormatException {
		this.oid = oid;
		uuid = parseUUIDFromOID(oid);
	}

	public String getOID(boolean base64) {
		if (base64)
			return Base64.encodeToString(oid);
		return oid;
	}

	public UUID getUUID() {
		return uuid;
	}

	public String getUUIDString(boolean base64) {
		if (base64)
			return Base64.encodeToString(uuid.toString());
		return uuid.toString();
	}

	public static UUID parseUUIDFromOID(String oid) throws IllegalArgumentException, NumberFormatException {
		if (oid == null || !oid.startsWith(OID_PREFIX)) {
			throw new IllegalArgumentException("OID " + oid + " does not start with " + OID_PREFIX);
		}
		String decimalString = oid.replaceFirst(OID_PREFIX_REMOVAL_REGEX, "");
		return parseUUIDFromDecimalString(decimalString);
	}

	public static UUID parseUUIDFromDecimalString(String decimalString) throws NumberFormatException {
		BigInteger decimalValue = new BigInteger(decimalString);
		long leastSignificantBits = decimalValue.longValue();
		long mostSignificantBits = decimalValue.shiftRight(64).longValue();
		return new UUID(mostSignificantBits, leastSignificantBits);
	}

	public static BigInteger makeBigIntegerFromUnsignedLong(long unsignedLongValue) {

		BigInteger bigValue;
		if (unsignedLongValue < 0) {
			unsignedLongValue = unsignedLongValue & Long.MAX_VALUE;
			bigValue = BigInteger.valueOf(unsignedLongValue);
			bigValue = bigValue.setBit(63);
		} else {
			bigValue = BigInteger.valueOf(unsignedLongValue);
		}
		return bigValue;
	}

	public static String createOIDFromUUIDCanonicalHexString(String hexString) throws IllegalArgumentException {
		UUID uuid = UUID.fromString(hexString);
		long leastSignificantBits = uuid.getLeastSignificantBits();
		long mostSignificantBits = uuid.getMostSignificantBits();
		BigInteger decimalValue = makeBigIntegerFromUnsignedLong(mostSignificantBits);
		decimalValue = decimalValue.shiftLeft(64);
		BigInteger bigValueOfLeastSignificantBits = makeBigIntegerFromUnsignedLong(leastSignificantBits);
		decimalValue = decimalValue.or(bigValueOfLeastSignificantBits);
		return OID_PREFIX + "." + decimalValue.toString();
	}
}