package com.itheima.web.form;

import com.itheima.domain.Category;
import com.itheima.service.CategoryService;
import com.itheima.service.impl.CategoryServiceImpl;

public class MyFunctions {

	/**
	 * ��listBooks.jsp�е��Զ����ǩ<%-- <td nowrap="nowrap">${myfn:getCategoryName(b.categoryid)}</td> --%>
	 * �ṩ����
	 * ��WEB-INF���½�һ���Զ�����ļ���myfun.tld�ļ�
	 * ����ģ�����ȥtomcat����ȥ��*.tld�ļ���ģ��
	 * @param categoryId
	 * @return
	 */
	public static String getCategoryName(String categoryId){
		CategoryService cs = new CategoryServiceImpl();
		Category c = cs.getCategory(categoryId);
		return c.getName();
	}
}
