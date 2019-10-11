package com.fakecorp.invoicing.api.service;

import java.util.List;
import java.util.Optional;

import com.fakecorp.invoicing.api.model.Product;

public interface ProductService {
	
	public List<Product> findAll();
	
	public Optional<Product> findById(String id);
	
	public List<Product> findByName(String name);
	
	public Optional<Product> create(Product product);
	
	public Optional<Product> update(Product product);
	
	public void delete(String id);

	void changeDiscontinued(String id);
}
