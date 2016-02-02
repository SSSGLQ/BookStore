package com.itheima.dao;

import com.itheima.domain.Customer;

public interface CustomerDao {

	Customer findCustomerByUseruameAndPassword(String username, String encode);

}
