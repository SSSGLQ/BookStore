package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Book;

public interface BookDao extends Dao<Book> {

//	//��ҳ��ѯͼ��
//	public int getTotalBooks();
//	
//	public List<Book> findBooksByPage(int startIndex,int pageSize);
//	
//	//���ݷ�����Ϣ���ٽ��зֶ���ѯͼ��
//	public int getTotalBooksByCategoryid(String categoryid);
//	
//	public List<Book> findBooksByPageAndCategoryid(int startIndex,int pageSize,String categoryid);
	
	/**
	 * ��ȡbook���е��ܼ�¼��
	 * @return
	 */
	public int getAllCounts();
	
	/**
	 * ���ڷ�ҳ��ѯ
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Book> getBooksByPage(int startIndex,int pageSize);
	
	/**
	 * ���ڸ��ݷ���id��ͳ�Ƴ�������
	 * @return
	 */
	public int getAllCountsByCategoryId(String categoryId);
	
	/**
	 * ���ݷ���id�����з�ҳ��ѯ
	 * @param startIndex
	 * @param pageSize
	 * @param categoryId
	 * @return
	 */
	public List<Book> getBooksByPageAndCategoryId(int startIndex,int pageSize,String categoryId);
	
	
	
}
