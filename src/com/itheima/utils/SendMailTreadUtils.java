package com.itheima.utils;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.itheima.domain.Customer;

public class SendMailTreadUtils extends Thread{
	
	private Customer c;
	
	public SendMailTreadUtils(Customer c){
		super();
		this.c = c;
	}
	
	@Override
	public void run() {
		sendMail();
		super.run();
	}
	
	public void sendMail(){
		Session session = SessionUtils.getSession();
		
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("faith_yee@163.com"));
			message.setRecipients(RecipientType.TO,c.getEmail());
			message.setSubject("这是来自商品小站的一封激活邮件");
			message.setText("欢迎您注册为商品小站会员，请点击链接进行激活，如无法点击请复制链接到地址栏进行激活:<a href='http//localhost:8080/bookstore/servlet/ClientServlet?op=actived&c"+c.getCode()+"'>激活</a>");
			Transport tp = session.getTransport();
			tp.connect("faith_yee","yw2633275");			
			tp.sendMessage(message, message.getAllRecipients());
			tp.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
