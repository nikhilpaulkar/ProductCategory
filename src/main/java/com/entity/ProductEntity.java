package com.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Where(clause="isactive=true")
@SQLDelete(sql="UPDATE product SET isactive=false WHERE id=?")
@Table(name="product")
public class ProductEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String productname;
	private boolean isactive=true;
	@CreationTimestamp
	private Date createdat;
	@UpdateTimestamp
	private Date updatedat;
	private double productprize;
	
	@ManyToOne
	private CategoryEntity category;
	
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
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public Date getCreatedat() {
		return createdat;
	}
	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}
	public Date getUpdatedat() {
		return updatedat;
	}
	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}
	public double getProductprize() {
		return productprize;
	}
	public void setProductprize(double productprize) {
		this.productprize = productprize;
	}
	
	
	
	public CategoryEntity getCategory() {
		return category;
	}
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	
	public ProductEntity(long id, String productname, boolean isactive, Date createdat, Date updatedat,
			double productprize, CategoryEntity category) {
		super();
		this.id = id;
		this.productname = productname;
		this.isactive = isactive;
		this.createdat = createdat;
		this.updatedat = updatedat;
		this.productprize = productprize;
		this.category = category;
	}
	public ProductEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
