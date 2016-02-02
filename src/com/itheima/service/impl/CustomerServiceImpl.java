package com.itheima.service.impl;

import com.itheima.dao.CustomerDao;
import com.itheima.dao.impl.CustomerDaoImpl;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import com.itheima.utils.Md5Utils;

public class CustomerServiceImpl implements CustomerService{
	private CustomerDao dao = new CustomerDaoImpl();
	public Customer login(String username, String password) {
		//密码是经过加密后的密码
		Customer c = dao.findCustomerByUseruameAndPassword(username,Md5Utils.encode(password));
		return c;
	}

}
