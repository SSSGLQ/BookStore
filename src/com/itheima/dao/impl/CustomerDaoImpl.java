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
	public void saveCustomer(Customer c) {
		try {
			qr.update("insert into customer values (?,?,?,?,?,?,?,?,?)",c.getId(),c.getUsername(),c.getPassword(),
					c.getPhone(),c.getAddress(),c.getEmail(),c.getActived(),c.getCode(),c.getRole());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public Customer findCustomerByName(String username) {
		try {
			return qr.query("select * from customer where username=?", new BeanHandler<Customer>(Customer.class),username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public void updateCustomer(Customer c) {
		try {
			qr.update("update customer set username=?,password=?,phone=?,address=?" +
					",email=?,actived=?,code=? where id=?"
					,c.getUsername(),c.getPassword(),c.getPhone()
					,c.getAddress(),c.getEmail(),c.getActived()
					,c.getCode(),c.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public Customer findCustomerByCode(String code) {
		try {
			return qr.query("select * from customer where code=?", new BeanHandler<Customer>(Customer.class),code);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
