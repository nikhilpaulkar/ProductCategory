package com.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Where(clause="isactive=true")
@SQLDelete(sql="UPDATE category SET isactive=false WHERE id=?")
@Table(name="category")
public class CategoryEntity
{   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String categoryname;
	private boolean isactive=true;
	@CreationTimestamp
	private Date createdat;
	@UpdateTimestamp
	private Date updatedat;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="category",fetch=FetchType.LAZY)
	private List<ProductEntity> product;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
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
	
	public List<ProductEntity> getProduct() {
		return product;
	}
	public void setProduct(List<ProductEntity> product) {
		this.product = product;
	}
	
	public CategoryEntity(long id, String categoryname, boolean isactive, Date createdat, Date updatedat,
			List<ProductEntity> product) {
		super();
		this.id = id;
		this.categoryname = categoryname;
		this.isactive = isactive;
		this.createdat = createdat;
		this.updatedat = updatedat;
		this.product = product;
	}
	public CategoryEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
