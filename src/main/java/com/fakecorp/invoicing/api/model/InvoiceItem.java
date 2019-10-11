package com.fakecorp.invoicing.api.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="invoice_items")
public class InvoiceItem implements Serializable {
	
	@Id
	private String id;
	
	private Integer amount;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	@ManyToOne
	private Invoice invoice;
	
	public InvoiceItem() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	private static final long serialVersionUID = -2906935108805575026L;
}