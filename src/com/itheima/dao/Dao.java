package com.itheima.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T> {

	public void add(T t);
	
	public void update(T t);
	
	public void delete(Serializable id);
	
	public T findOne(Serializable id);
	
	public List<T> findAll();
}
