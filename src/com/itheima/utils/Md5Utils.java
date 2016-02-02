package com.itheima.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

import com.sun.mail.util.BASE64EncoderStream;

public class Md5Utils {
	/**
	 * @param str
	 * @return
	 */
	public static String encode(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] afterStr = md.digest(str.getBytes());
			
			BASE64Encoder b64 = new BASE64Encoder();
			return b64.encode(afterStr);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		String passwodr = encode("123");
		System.out.println(passwodr);
	}
}
