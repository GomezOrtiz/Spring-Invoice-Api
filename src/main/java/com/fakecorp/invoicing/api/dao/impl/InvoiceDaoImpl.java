package com.fakecorp.invoicing.api.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fakecorp.invoicing.api.dao.InvoiceDao;
import com.fakecorp.invoicing.api.mapper.InvoiceMapper;
import com.fakecorp.invoicing.api.model.Invoice;
import com.fakecorp.invoicing.api.model.InvoiceItem;

@Repository
public class InvoiceDaoImpl implements InvoiceDao {
	
	@Autowired
	private InvoiceMapper invoiceMapper;

	@Override
	@Transactional(readOnly=true)
	public Optional<Invoice> findById(String id) {
		return Optional.ofNullable(invoiceMapper.findById(id));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Invoice> findByClient(String id) {
		return invoiceMapper.findByClient(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<InvoiceItem> findItems(String id) {
		return invoiceMapper.findItems(id);
	}

	@Override
	@Transactional
	public Optional<Invoice> save(Invoice invoice) {
		invoiceMapper.insert(invoice);
        return findById(invoice.getId());
	}
	
	@Override
	@Transactional
	public void delete(String id) {
        invoiceMapper.delete(id);
	}
	
	@Override
	@Transactional
	public void addItem(InvoiceItem item) {
		invoiceMapper.insertItem(item);
	}

}
