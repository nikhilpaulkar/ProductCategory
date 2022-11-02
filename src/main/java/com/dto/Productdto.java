package com.dto;



public class Productdto
{
	private long id;
	private String productname;
	private double productprize;
	private long categoryid;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public double getProductprize() {
		return productprize;
	}
	public void setProductprize(double productprize) {
		this.productprize = productprize;
	}
	
	
	
	public long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}
	
	public Productdto(long id, String productname, double productprize, long categoryid) {
		super();
		this.id = id;
		this.productname = productname;
		this.productprize = productprize;
		this.categoryid = categoryid;
	}
	public Productdto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
