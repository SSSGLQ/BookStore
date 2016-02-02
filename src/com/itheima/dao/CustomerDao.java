package com.itheima.dao;

import com.itheima.domain.Customer;

public interface CustomerDao {

	Customer findCustomerByUseruameAndPassword(String username, String encode);

	void saveCustomer(Customer c);

	Customer findCustomerByName(String username);

	void updateCustomer(Customer c);

	Customer findCustomerByCode(String code);

}
