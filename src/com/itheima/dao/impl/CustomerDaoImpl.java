package com.itheima.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.utils.C3P0Utils;

public class CustomerDaoImpl implements CustomerDao {
	private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	public Customer findCustomerByUseruameAndPassword(String username, String encode) {
		try {
			return qr.query("select * from customer where username=? and password=? and actived=1", new BeanHandler<Customer>(Customer.class),username,encode);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
