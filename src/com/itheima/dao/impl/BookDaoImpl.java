package com.itheima.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.dao.BookDao;
import com.itheima.domain.Book;
import com.itheima.utils.C3P0Utils;

public class BookDaoImpl implements BookDao{
	private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	
	public void add(Book t) {
		try {
			qr.update("insert into book values(?,?,?,?,?,?,?)",
					t.getId(),
					t.getName(),
					t.getAuthor(),
					t.getPrice(),
					t.getImageName(),
					t.getDescription(),
					t.getCategoryid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Book t) {
		try {
			qr.update("update book set name=?,author=?,price=?,imageName=?,description=?,category=? where id=?)",
					t.getName(),
					t.getAuthor(),
					t.getPrice(),
					t.getImageName(),
					t.getDescription(),
					t.getCategoryid(),
					t.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Serializable id) {
		try {
			qr.update("delete from book where id=?",id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Book findOne(Serializable id) {
		try {
			Book book = qr.query("select * from book where id=?",id, new BeanHandler<Book>(Book.class));
			return book;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Book> findAll() {
		try {
			List<Book> list = qr.query("select * from book", new BeanListHandler<Book>(Book.class));
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int getAllCounts() {
		try {
			int count = ((Long)qr.query("select count(1) from book", new ScalarHandler())).intValue();
			return count;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Book> getBooksByPage(int startIndex, int pageSize) {
		try {
			return qr.query("select * from book limit ?,?",new BeanListHandler<Book>(Book.class),startIndex,pageSize);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int getAllCountsByCategoryId(String categoryId) {
		try {
			int count = ((Long)qr.query("select count(1) from book where categoryid=?", new ScalarHandler(),categoryId)).intValue();
			return count;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Book> getBooksByPageAndCategoryId(int startIndex, int pageSize,
			String categoryId) {
		try {
			return qr.query("select * from book where categoryid=? limit ?,?",new BeanListHandler<Book>(Book.class),categoryId,startIndex,pageSize);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
