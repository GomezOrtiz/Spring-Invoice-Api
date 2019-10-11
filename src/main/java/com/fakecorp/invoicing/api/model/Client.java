package com.fakecorp.invoicing.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="clients")
public class Client implements Serializable {

	@Id
	private String id;

	private String name;

	private String surname;

	private String email;

	@Column(name = "created_at")
	@Temporal(value = TemporalType.DATE)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(value = TemporalType.DATE)
	private Date updatedAt;
	
	@OneToMany(mappedBy="client")
	private List<Invoice> invoices;

	public Client() {
		this.id = UUID.randomUUID().toString();
		invoices = new ArrayList<Invoice>();
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	private static final long serialVersionUID = -4922087797321652031L;
}
