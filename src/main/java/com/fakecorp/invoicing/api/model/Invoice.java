package com.fakecorp.invoicing.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="invoices")
public class Invoice implements Serializable {
	
	@Id
	private String id;
	
	private String description;
	
	private String details;
	
	@Column(name="created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	public Invoice() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Invoice(Client client) {
		setClient(client);
		this.id = UUID.randomUUID().toString();
	}

	@PrePersist
	public void prePersist() {
		createdAt = new Date();
	}
	
	@ManyToOne
	private Client client;
	
	@OneToMany(mappedBy="invoice")
	private List<InvoiceItem> items;
		
	private Double total;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	private static final long serialVersionUID = -8504647061495176387L;

}