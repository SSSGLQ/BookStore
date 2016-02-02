package com.itheima.utils;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

public class IDGenerator {
	//genId=ea5b4eb5-ac0e-4f93-9d42-ab1741526a79   
	public static String genId(){
		return UUID.randomUUID().toString();
	}
	
	//genCode=LOJWMEQA8RVZSD5KTVAOB8CSSG73DMS4
	public static String genCode(){
		//注意：这是struts2防表单重复提交的源码。<s:tokens>
		return new BigInteger(165,new Random()).toString(36).toUpperCase();
	}
	
	
	public static void main(String[] args) {
		System.out.println("genId="+genId()+"   genCode="+genCode());
		
	}
}
