package com.itheima.web.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.itheima.domain.Book;

/**
 * 购物车
 * @author yewmf
 *
 */
public class Cart implements Serializable { 

	private Map<String,CartItem> map = new HashMap<String,CartItem>();//车筐
	private int totleNum;//总数量
	private double totlePrice;//总价格
	
		
	/**
	 * 加多本
	 * 将书放入到购物车中  ？  思考如何一次性添加多个商品到购物车中
	 * @param book
	 * @param num
	 */
	public void addBooks2Cart(Book book , int num){
		//要先判断购物车是否已存在该商品，存在就改数量，不存在就添加一个新元素
		if(map.containsKey(book.getId())){
			CartItem item = map.get(book.getId());
			item.setNum(item.getNum()+num);//这是修改数量
		}else{
			CartItem item  = new CartItem(book,num);
			map.put(book.getId(), item);
		}
	}
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public int getTotleNum() {
		totleNum=0;//清零
		for(Map.Entry<String, CartItem> item:map.entrySet()){
			CartItem ci = item.getValue();
			totleNum+=ci.getNum();//
		}
		return totleNum;
	}
	public void setTotleNum(int totleNum) {
		this.totleNum = totleNum;
	}
	public double getTotlePrice() {
		totlePrice=0;//清零
		for(Map.Entry<String, CartItem> item:map.entrySet()){
			CartItem ci = item.getValue();
			totlePrice+=ci.getTotalPrice();//将各类的数量，求和得到总价格
		}
		return totlePrice;
	}
	public void setTotlePrice(double totlePrice) {
		this.totlePrice = totlePrice;
	}

	@Override
	public String toString() {
		return "Cart [map=" + map + ", totleNum=" + totleNum + ", totlePrice="
				+ totlePrice + "]";
	}
	
	
	
}
