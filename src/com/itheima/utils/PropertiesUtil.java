package com.itheima.utils;

import java.util.ResourceBundle;

public class PropertiesUtil {
	public static String getValue(String key){
		ResourceBundle rb = ResourceBundle.getBundle("merchantInfo");
		return rb.getString(key);
	}
}
