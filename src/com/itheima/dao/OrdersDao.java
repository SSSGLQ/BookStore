package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Orders;

public interface OrdersDao {

	boolean saveOrders(Orders o);

	void updateStatus(String r6_Order);

	List<Orders> getAllOrdersByCustomerId(String id);

	Orders getOrdersByOrdernum(String ordernum);

	int getCountByMultiSelect(String username, String ordernum, String status);

	List<Orders> getOrdersByMultiSelectAndPage(int startIndex, int pageSize,
			String username, String ordernum, String status);

}
