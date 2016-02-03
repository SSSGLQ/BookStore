package com.itheima.service.impl;

import com.itheima.dao.BookDao;
import com.itheima.dao.impl.BookDaoImpl;
import com.itheima.domain.Book;
import com.itheima.service.BookService;
import com.itheima.utils.PageBean;

public class BookServiceImpl implements BookService{
	private BookDao dao = new BookDaoImpl();

	public void findBooksByPageBean(PageBean pb) {
		pb.setTotalRecordes(dao.getAllCounts());//总记录数
		pb.setRecordes(dao.getBooksByPage(pb.getStartIndex(), pb.getPageSize()));//设置当前页的记录
	}

	public void findBooksByPageBeanAndCategoryId(PageBean pb, String categoryId) {
		pb.setTotalRecordes(dao.getAllCountsByCategoryId(categoryId));//得到指定分类下，共有多少条记录数。
		pb.setRecordes(dao.getBooksByPageAndCategoryId(pb.getStartIndex(), pb.getPageSize(), categoryId));//得到指定分类下当前页中的记录
	}

	public Book findOne(String id) {
		return dao.findOne(id);
	}

	public void addBook(Book book) {
		dao.add(book);
	}

}
