package com.epam.webshop.utils;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {

	private static Logger logger = Logger.getLogger(PasswordEncoder.class);

	public static String sha2Encode(String plaintext) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-256");
			md.update(plaintext.getBytes());
			byte byteData[] = md.digest();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
		}
		return sb.toString();
	}
}
