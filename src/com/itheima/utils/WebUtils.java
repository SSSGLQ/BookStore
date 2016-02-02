package com.itheima.utils;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;


public class WebUtils {
	/**
	 * ���ڽ�request�����еĲ�����װ��ָ����javaBean��,������ָ���Ķ���
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
			throw new RuntimeException("��װ����ʧ��!!!");
		}
	}
}
