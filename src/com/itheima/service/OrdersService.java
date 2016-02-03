package com.itheima.service;

import java.util.List;

import com.itheima.domain.Orders;

public interface OrdersService {

	boolean saveOrders(Orders o);

	void update(String r6_Order);

	List<Orders> getAllOrdersByCustomerId(String id);

	Orders getOrdersByOrdernum(String ordernum);

	
}
