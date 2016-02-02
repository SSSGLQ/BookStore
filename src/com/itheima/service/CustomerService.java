package com.itheima.service;

import com.itheima.domain.Customer;

public interface CustomerService {

	Customer login(String username, String password);

	Boolean regist(Customer c);

	
}
