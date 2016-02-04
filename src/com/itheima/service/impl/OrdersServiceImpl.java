package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.OrdersDao;
import com.itheima.dao.impl.OrdersDaoImpl;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import com.itheima.utils.PageBean;

public class OrdersServiceImpl implements OrdersService {
	private OrdersDao dao  = new OrdersDaoImpl();
	
	public boolean saveOrders(Orders o) {
		//保存订单
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

	public void getOrdersByMultiSelect(PageBean pb, String username,
			String ordernum, String status) {
		int count = dao.getCountByMultiSelect(username,ordernum,status);//符合三个条件的
		pb.setTotalRecordes(count);
		List<Orders> list = dao.getOrdersByMultiSelectAndPage(pb.getStartIndex(),pb.getPageSize(),username,ordernum,status);
		pb.setRecordes(list);
	}

}
