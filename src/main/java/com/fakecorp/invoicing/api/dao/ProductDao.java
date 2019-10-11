package com.fakecorp.invoicing.api.dao;

import java.util.List;
import java.util.Optional;

import com.fakecorp.invoicing.api.model.Product;

public interface ProductDao {
	
	public List<Product> findAll();
	
	public Optional<Product> findById(String id);
	
	public List<Product> findByName(String name);
	
	public Optional<Product> save(Product product);
		
	public void delete(String id);

	void changeDiscontinued(String id);
}
