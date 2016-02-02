package com.itheima.service;

import com.itheima.domain.Book;
import com.itheima.utils.PageBean;

public interface BookService {

	/**
	 * ���ݷ�ҳ��ѯͼ���б�
	 * @param pb
	 */
	public void findBooksByPageBean(PageBean pb);
	
	/**
	 * ����ָ���ķ��࣬���з�ҳ��ѯͼ��
	 * @param pb
	 * @param categoryId
	 */
	public void findBooksByPageBeanAndCategoryId(PageBean pb,String categoryId);
	
	public Book findOne(String id );
}
