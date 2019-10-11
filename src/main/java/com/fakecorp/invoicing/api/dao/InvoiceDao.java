package com.fakecorp.invoicing.api.dao;

import java.util.List;
import java.util.Optional;

import com.fakecorp.invoicing.api.model.Invoice;
import com.fakecorp.invoicing.api.model.InvoiceItem;

public interface InvoiceDao {
		
	public Optional<Invoice> findById(String id);
	
	public List<Invoice> findByClient(String id);
		
	public Optional<Invoice> save(Invoice invoice);

	public List<InvoiceItem> findItems(String id);

	public void delete(String id);

	public void addItem(InvoiceItem item);
}