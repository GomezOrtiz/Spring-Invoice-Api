package com.fakecorp.invoicing.api.service;

import java.util.List;
import java.util.Optional;

import com.fakecorp.invoicing.api.model.Invoice;
import com.fakecorp.invoicing.api.model.InvoiceItem;

public interface InvoiceService {
		
	public Optional<Invoice> findById(String id);
	
	public List<Invoice> findByClient(String clientId);
	
	public Optional<Invoice> create(Invoice invoice);

	public List<InvoiceItem> findItems(String id);
	
	public void delete(String id);

	public void addItem(InvoiceItem item);
}
