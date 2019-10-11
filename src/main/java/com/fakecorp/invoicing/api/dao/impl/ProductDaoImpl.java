package com.fakecorp.invoicing.api.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fakecorp.invoicing.api.dao.ProductDao;
import com.fakecorp.invoicing.api.mapper.ProductMapper;
import com.fakecorp.invoicing.api.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	@Transactional(readOnly=true)
	public List<Product> findAll() {
		return productMapper.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Product> findById(String id) {
        return Optional.ofNullable(productMapper.findById(id));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Product> findByName(String name) {
        return productMapper.findByName("%" + name + "%");
	}

	@Override
	@Transactional
	public Optional<Product> save(Product product) {
		if (null != productMapper.findById(product.getId())) {
			productMapper.update(product);
        	return findById(product.getId());
        } else {
        	productMapper.insert(product);
            return findById(product.getId());
        }
	}

	@Override
	@Transactional
	public void delete(String id) {
        productMapper.delete(id);
	}

	@Override
	@Transactional
	public void changeDiscontinued(String id) {
		productMapper.changeDiscontinued(id);
	}

}
