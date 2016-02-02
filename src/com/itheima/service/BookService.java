package com.itheima.service;

import com.itheima.domain.Book;
import com.itheima.utils.PageBean;

public interface BookService {

	/**
	 * 根据分页查询图书列表
	 * @param pb
	 */
	public void findBooksByPageBean(PageBean pb);
	
	/**
	 * 根据指定的分类，进行分页查询图书
	 * @param pb
	 * @param categoryId
	 */
	public void findBooksByPageBeanAndCategoryId(PageBean pb,String categoryId);
	
	public Book findOne(String id );
}
