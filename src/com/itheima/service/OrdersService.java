package com.itheima.service;

import java.util.List;

import com.itheima.domain.Orders;
import com.itheima.utils.PageBean;

public interface OrdersService {

	boolean saveOrders(Orders o);

	void update(String r6_Order);

	List<Orders> getAllOrdersByCustomerId(String id);

	Orders getOrdersByOrdernum(String ordernum);

	void getOrdersByMultiSelect(PageBean pb, String username, String ordernum,
			String status);

	
}
