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
			message.setSubject("����������ƷСվ��һ�⼤���ʼ�");
			message.setText("��ӭ��ע��Ϊ��ƷСվ��Ա���������ӽ��м�����޷�����븴�����ӵ���ַ�����м���:<a href='http//localhost:8080/bookstore/servlet/ClientServlet?op=actived&c"+c.getCode()+"'>����</a>");
			Transport tp = session.getTransport();
			tp.connect("faith_yee","yw2633275");			
			tp.sendMessage(message, message.getAllRecipients());
			tp.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
