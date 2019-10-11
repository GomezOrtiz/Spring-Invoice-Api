package com.fakecorp.invoicing.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fakecorp.invoicing.api.dao.InvoiceDao;
import com.fakecorp.invoicing.api.model.Invoice;
import com.fakecorp.invoicing.api.model.InvoiceItem;
import com.fakecorp.invoicing.api.model.Product;
import com.fakecorp.invoicing.api.service.InvoiceService;
import com.fakecorp.invoicing.api.service.ProductService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Autowired
	private ProductService productService;

	@Override
	public Optional<Invoice> findById(String id) {
		return invoiceDao.findById(id);
	}
	
	@Override
	public List<Invoice> findByClient(String id) {
		return invoiceDao.findByClient(id);
	}
	
	@Override
	public Optional<Invoice> create(Invoice invoice) {
		invoice.setCreatedAt(new Date());
		invoice.setTotal(getTotal(invoice));
		return invoiceDao.save(invoice);
	}
	
	@Override 
	public List<InvoiceItem> findItems (String id) {
		return invoiceDao.findItems(id);
	}
	
	@Override
	public void delete(String id) {
		if(!invoiceDao.findById(id).isEmpty()) {
			invoiceDao.delete(id);
		}
	}
	
	@Override
	public void addItem (InvoiceItem item) {
		invoiceDao.addItem(item);
	}
	
	private Double getTotal(Invoice invoice) {
		Double total = 0.0;
		
		for (InvoiceItem item : findItems(invoice.getId())) {
			Product product = productService.findById(item.getProduct().getId()).orElse(null);
			if (null != product) {
				total += item.getAmount().doubleValue() * product.getPrice();
			}
		}
		
		return total;
	}
}
