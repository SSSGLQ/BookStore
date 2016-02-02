package com.itheima.domain;

import java.io.Serializable;

public class Book implements Serializable {
	private String id;
	private String name;
	private String author;
	private float price;
	private String imageName;//与表单不一样哦
	private String description;
	private String categoryid;
	
	public Book(){
		super();
	}
	
	public Book(String id, String name, String author, float price,
			String imageName, String description, String categoryid) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.imageName = imageName;
		this.description = description;
		this.categoryid = categoryid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author
				+ ", price=" + price + ", imageName=" + imageName
				+ ", description=" + description + ", categoryid=" + categoryid
				+ "]";
	}
	
	
	
}
