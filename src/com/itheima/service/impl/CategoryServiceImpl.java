package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.CategoryDao;
import com.itheima.dao.impl.CategoryDaoImpl;
import com.itheima.domain.Category;
import com.itheima.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{

	private CategoryDao dao = new CategoryDaoImpl();
	public List<Category> findAllCategorys() {
		return dao.findAll();
	}
	public void addCategory(Category c) {
		dao.add(c);
	}
	public Category getCategory(String categoryId) {
		return dao.findOne(categoryId);
	}

}
