package com.itheima.web.form;

import java.io.Serializable;

import com.itheima.domain.Book;

/**
 * 购物车物品
 * @author yewmf
 *
 */
public class CartItem implements Serializable {

	private Book book;
	private int num;
	private double totalPrice;//小计
	
	public CartItem(){
		super();
	}
	
	public CartItem(Book book, int num) {
		super();
		this.book = book;
		this.num = num;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getTotalPrice() {
		return book.getPrice()*num;//改动了
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
