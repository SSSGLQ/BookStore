package com.itheima.utils;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;


public class WebUtils {
	/**
	 * 用于将request请求中的参数封装到指定的javaBean中,并返回指定的对象
	 * @param request
	 * @param clz
	 * @return
	 */
	public static <T> T fillBean(HttpServletRequest request,Class<T> clz){
		try {
			T bean = clz.newInstance();
			BeanUtils.populate(bean, request.getParameterMap());
			return bean;
		} catch (Exception e) {
			throw new RuntimeException("封装参数失败!!!");
		}
	}
}
