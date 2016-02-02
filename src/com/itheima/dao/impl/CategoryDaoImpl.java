package com.itheima.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.dao.CategoryDao;
import com.itheima.domain.Category;
import com.itheima.utils.C3P0Utils;

public class CategoryDaoImpl implements CategoryDao{
	QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

	/**
	 * 	private String id;
	private String name;
	private String description;
	 */
	public void add(Category t) {
		try {
			qr.update("insert into category value(?,?,?)",t.getId(),t.getName(),t.getDescription());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Category t) {
		try {
			qr.update("update category set name=?,description=? where id=?",t.getName(),t.getDescription(),t.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(Serializable id) {
		try {
			qr.update("delete from category where id=?",id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Category findOne(Serializable id) {
		try {
			Category category = qr.query("select * from category where id=?",new BeanHandler<Category>(Category.class),id);
			return category;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Category> findAll() {
		try {
			List<Category> list = qr.query("select * from category",new BeanListHandler<Category>(Category.class));
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
