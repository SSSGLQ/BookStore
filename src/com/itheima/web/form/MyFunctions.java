package com.itheima.web.form;

import com.itheima.domain.Category;
import com.itheima.service.CategoryService;
import com.itheima.service.impl.CategoryServiceImpl;

public class MyFunctions {

	/**
	 * 给listBooks.jsp中的自定义标签<%-- <td nowrap="nowrap">${myfn:getCategoryName(b.categoryid)}</td> --%>
	 * 提供方法
	 * 在WEB-INF下新建一个自定义的文件：myfun.tld文件
	 * 代码模板可以去tomcat里面去找*.tld文件，模板
	 * @param categoryId
	 * @return
	 */
	public static String getCategoryName(String categoryId){
		CategoryService cs = new CategoryServiceImpl();
		Category c = cs.getCategory(categoryId);
		return c.getName();
	}
}
