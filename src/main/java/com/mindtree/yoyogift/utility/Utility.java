package com.mindtree.yoyogift.utility;

import java.math.BigInteger;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Utility {

	public static String Encryption(String data) throws NoSuchAlgorithmException, InvalidKeySpecException {

		int iterations = 1000;
		char[] chars = data.toCharArray();
		byte[] salt = getSalt();
		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		data = toHex(salt) + ":" + toHex(hash);
		return data;
	}

	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	public static boolean Decryption(String storedPassword, String originalPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] parts = storedPassword.split(":");
		int iterations = 1000;
		byte[] salt = fromHex(parts[0]);
		byte[] hash = fromHex(parts[1]);

		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	public static void main(String[] args) {
	}

	public static String getEmailTemplate() {
		String message = "<div style=\"border-left: 2px solid tomato;\"><h3 style=\"margin-left: 10px;\">"
				+ "Hi <span style=\"color: tomato;\">"
				+ "${to}</span>,</h3></div><div style=\"border-left: 2px solid tomato;\">"
				+ "<p style=\"margin-left: 10px;\">It is a great pleasure for Us to welcome you into the "
				+ "<span style=\"color: tomato;\">YoYoGift</span> Family, you have been invited to Join the Community by"
				+ " <span style=\"color: tomato;\">${from}</span>.<br>Here is your referral Code <span style=\"color: "
				+ "tomato;\">${code}</span>, please use this at our Register Page to gain <span style=\"color: tomato;\">"
				+ "50 YoYo</span> Points immediately.<br>We look forward to seeing you on the Platform.<br> </p> </div> "
				+ "<a href=\"https://agnitra-yoyogift-jan-2020-client-dev.azurewebsites.net/#/profile/signup\" "
				+ "style=\"text-decoration: none; display: inline-block;border-radius: 5px ;color: white; background-color: tomato; padding: 15px 30px;\">"
				+ "Join Now</a> <div style=\"border-left: 2px solid tomato;\"> <h3 style=\"margin-left: 10px;\">"
				+ "Thanks & Regards,<br><span style=\"color: tomato;\">YoYo Gift Team</span></h3></div>";
		return message;
	}

	public static String dateString(Date date) {
		String dateValue = "";
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		dateValue += String.valueOf(calender.get(Calendar.YEAR)) + "/"
				+ String.valueOf(calender.get(Calendar.MONTH) + 1) + "/"
				+ String.valueOf(calender.get(Calendar.DAY_OF_MONTH));
		return dateValue;
	}

}
