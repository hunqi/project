package cn.homecredit.tsa.util;

import java.security.MessageDigest;

public class HashUtil {
	
	private static final String ALGORITHM_SHA256 = "SHA-256";

	/**
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static String getHashString(byte[] bytes) throws Exception {
		return convertByte(getHash(bytes));
	}

	/**
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static byte[] getHash(byte[] bytes) throws Exception {
		MessageDigest sha = MessageDigest.getInstance(ALGORITHM_SHA256);
		sha.update(bytes);
		return sha.digest();
	}

	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public static String convertByte(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}
}
