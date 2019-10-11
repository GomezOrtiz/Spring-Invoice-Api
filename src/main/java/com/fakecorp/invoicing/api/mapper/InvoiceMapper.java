package com.fakecorp.invoicing.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fakecorp.invoicing.api.model.Invoice;
import com.fakecorp.invoicing.api.model.InvoiceItem;

@Mapper
public interface InvoiceMapper {
	
	public Invoice findById(@Param("id") String id);
	
	public List<Invoice> findByClient(@Param("id") String id);
	
	public List<InvoiceItem> findItems(@Param("id") String id);

	public void insert(@Param("invoice") Invoice invoice);
	
	public void delete(@Param("id") String id);
	
	public void insertItem(@Param("item") InvoiceItem item);
}
