package com.fakecorp.invoicing.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "products")
public class Product implements Serializable {
	
	@Id
	private String id;
	
	private String name;
	
	private Double price;
	
	private boolean discontinued;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name="created_at")
	private Date createdAt;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name="updated_at")
	private Date updatedAt;
	
	public Product () {
		this.id = UUID.randomUUID().toString();
	}
	
	public Product (String name, Double price) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.price = price;
		this.discontinued = false;
	}
	
	@PrePersist
	public void prePersist() {
		createdAt = new Date();
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}
	
	public boolean isDiscontinued() {
		return discontinued;
	}
	
	private static final long serialVersionUID = 6482360997899102103L;
}
