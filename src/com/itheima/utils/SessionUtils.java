package com.itheima.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Session;

public class SessionUtils {

	public static Session getSession(){
		InputStream is = SessionUtils.class.getClassLoader().getResourceAsStream("mail.properties");
		Properties p = new Properties();
//		//����Э��
//		p.setProperty("mail.transport.protocol", "smtp");
//		//��������
//		p.setProperty("mail.host", "smtp.163.com");
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return Session.getInstance(p);
	}
}
