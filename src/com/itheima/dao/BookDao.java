package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Book;

public interface BookDao extends Dao<Book> {

//	//分页查询图书
//	public int getTotalBooks();
//	
//	public List<Book> findBooksByPage(int startIndex,int pageSize);
//	
//	//根据分类信息，再进行分而查询图书
//	public int getTotalBooksByCategoryid(String categoryid);
//	
//	public List<Book> findBooksByPageAndCategoryid(int startIndex,int pageSize,String categoryid);
	
	/**
	 * 获取book表中的总记录数
	 * @return
	 */
	public int getAllCounts();
	
	/**
	 * 用于分页查询
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Book> getBooksByPage(int startIndex,int pageSize);
	
	/**
	 * 用于根据分类id，统计出总数量
	 * @return
	 */
	public int getAllCountsByCategoryId(String categoryId);
	
	/**
	 * 根据分类id，进行分页查询
	 * @param startIndex
	 * @param pageSize
	 * @param categoryId
	 * @return
	 */
	public List<Book> getBooksByPageAndCategoryId(int startIndex,int pageSize,String categoryId);
	
	
	
}
