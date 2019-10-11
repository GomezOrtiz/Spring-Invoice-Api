package com.fakecorp.invoicing.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fakecorp.invoicing.api.dao.ProductDao;
import com.fakecorp.invoicing.api.model.Product;
import com.fakecorp.invoicing.api.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}
	
	@Override
	public Optional<Product> findById(String id) {
		return productDao.findById(id);
	}
	
	@Override
	public List<Product> findByName(String name) {
		return productDao.findByName(name);
	}

	@Override
	public Optional<Product> create(Product product) {
		product.setCreatedAt(new Date());
		return productDao.save(product);
	}
	
	@Override
	public Optional<Product> update(Product product) {
		product.setUpdatedAt(new Date());
		return productDao.save(product);	
	}
	
	@Override
	public void changeDiscontinued(String id) {
		if(null != findById(id)) {
			Product foundProduct = findById(id).orElse(null);
			foundProduct.setDiscontinued(!foundProduct.isDiscontinued());
			update(foundProduct);
		} else {
			throw new IllegalArgumentException("No existe ningún producto con esa ID");
		}
	}
	
	@Override
	public void delete(String id) {
		if(null != findById(id)) {
			productDao.delete(id);
		} else {
			throw new IllegalArgumentException("No existe ningún producto con esa ID");
		}
	}
}
