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
	public Boolean regist(Customer c) {
		Customer customer = dao.findCustomerByName(c.getUsername());//查询用户名是否已经被注册
		if(customer==null){
			c.setPassword(Md5Utils.encode(c.getPassword()));//注册时，数据库中存入的是密文
			dao.saveCustomer(c);
			return true;
		}else{
			return false;
		}
	}
	public boolean active(String code) {
		//1.查询数据库是否有这个激活码的用户
		Customer c = dao.findCustomerByCode(code);
		//2.如果有，激活用户
		if(c!=null){
			//说明该激活码正确，有相应的用户信息
			c.setActived(1);
			dao.updateCustomer(c);//修改激活状态
		}else{
			//激活码有误
			return false;
		}
		return true;
	}

}
