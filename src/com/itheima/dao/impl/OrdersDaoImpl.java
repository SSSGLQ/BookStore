package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.dao.OrdersDao;
import com.itheima.domain.Orders;
import com.itheima.domain.OrdersItem;
import com.itheima.utils.C3P0Utils;

public class OrdersDaoImpl implements OrdersDao {
	private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	public boolean saveOrders(Orders o) {
		//添加操作
		try {
			qr.update("insert into orders values(?,?,?,?,?,?)",o.getId(),o.getOrdernum(),o.getNum(),o.getPrice(),o.getStatus(),o.getC().getId());
			
			List<OrdersItem> items = o.getItems();
			if(items!=null && items.size()>0){//说明有订单明细
				for(OrdersItem item : items){
					qr.update("insert into ordersitem values(?,?,?,?,?)",item.getId(),item.getNum(),item.getPrice(),item.getBook().getId(),o.getId());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public void updateStatus(String r6_Order) {
		try {
			qr.update("update orders set status=1 where ordernum=?", r6_Order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public List<Orders> getAllOrdersByCustomerId(String id) {
		try {
			return qr.query("select * from orders where customerid=?",new BeanListHandler<Orders>(Orders.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public Orders getOrdersByOrdernum(String ordernum) {
		try {
			return qr.query("select * from orders where ordernum = ?",new BeanHandler<Orders>(Orders.class),ordernum);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
