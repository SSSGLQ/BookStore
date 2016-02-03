package com.itheima.service;

import java.util.List;

import com.itheima.domain.Category;

public interface CategoryService {

	public List<Category> findAllCategorys();

	public void addCategory(Category c);

	public Category getCategory(String categoryId);
}
