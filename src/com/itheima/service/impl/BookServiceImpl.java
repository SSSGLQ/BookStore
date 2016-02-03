package com.itheima.service.impl;

import com.itheima.dao.BookDao;
import com.itheima.dao.impl.BookDaoImpl;
import com.itheima.domain.Book;
import com.itheima.service.BookService;
import com.itheima.utils.PageBean;

public class BookServiceImpl implements BookService{
	private BookDao dao = new BookDaoImpl();

	public void findBooksByPageBean(PageBean pb) {
		pb.setTotalRecordes(dao.getAllCounts());//�ܼ�¼��
		pb.setRecordes(dao.getBooksByPage(pb.getStartIndex(), pb.getPageSize()));//���õ�ǰҳ�ļ�¼
	}

	public void findBooksByPageBeanAndCategoryId(PageBean pb, String categoryId) {
		pb.setTotalRecordes(dao.getAllCountsByCategoryId(categoryId));//�õ�ָ�������£����ж�������¼����
		pb.setRecordes(dao.getBooksByPageAndCategoryId(pb.getStartIndex(), pb.getPageSize(), categoryId));//�õ�ָ�������µ�ǰҳ�еļ�¼
	}

	public Book findOne(String id) {
		return dao.findOne(id);
	}

	public void addBook(Book book) {
		dao.add(book);
	}

}
