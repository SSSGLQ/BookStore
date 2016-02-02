package com.itheima.web.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.itheima.domain.Book;

/**
 * ���ﳵ
 * @author yewmf
 *
 */
public class Cart implements Serializable { 

	private Map<String,CartItem> map = new HashMap<String,CartItem>();//����
	private int totleNum;//������
	private double totlePrice;//�ܼ۸�
	
		
	/**
	 * �Ӷ౾
	 * ������뵽���ﳵ��  ��  ˼�����һ������Ӷ����Ʒ�����ﳵ��
	 * @param book
	 * @param num
	 */
	public void addBooks2Cart(Book book , int num){
		//Ҫ���жϹ��ﳵ�Ƿ��Ѵ��ڸ���Ʒ�����ھ͸������������ھ����һ����Ԫ��
		if(map.containsKey(book.getId())){
			CartItem item = map.get(book.getId());
			item.setNum(item.getNum()+num);//�����޸�����
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
		totleNum=0;//����
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
		totlePrice=0;//����
		for(Map.Entry<String, CartItem> item:map.entrySet()){
			CartItem ci = item.getValue();
			totlePrice+=ci.getTotalPrice();//���������������͵õ��ܼ۸�
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
