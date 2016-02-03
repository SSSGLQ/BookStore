package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.OrdersDao;
import com.itheima.dao.impl.OrdersDaoImpl;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;

public class OrdersServiceImpl implements OrdersService {
	private OrdersDao dao  = new OrdersDaoImpl();
	
	public boolean saveOrders(Orders o) {
		//±£´æ¶©µ¥
		boolean flag = dao.saveOrders(o);
		
		return flag;
	}

	public void update(String r6_Order) {
		dao.updateStatus(r6_Order);
	}

	public List<Orders> getAllOrdersByCustomerId(String id) {
		
		return dao.getAllOrdersByCustomerId(id);
	}

	public Orders getOrdersByOrdernum(String ordernum) {
		return dao.getOrdersByOrdernum(ordernum);
	}

}
