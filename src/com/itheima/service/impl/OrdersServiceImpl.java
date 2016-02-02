package com.itheima.service.impl;

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

}
