package com.dto;

public class Categorydto 
{
	private long id;
	private String categoryname;

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Categorydto(long id, String categoryname) {
		super();
		this.id = id;
		this.categoryname = categoryname;
	}

	public Categorydto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
