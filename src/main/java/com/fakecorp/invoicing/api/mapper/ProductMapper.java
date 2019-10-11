package com.fakecorp.invoicing.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fakecorp.invoicing.api.model.Product;

@Mapper
public interface ProductMapper {
	
	public List<Product> findAll();
	
	public Product findById(@Param("id") String id);
	
	public List<Product> findByName(@Param("name") String name);
	
	public void insert(@Param("product") Product product);
	
	public void update(@Param("product") Product product);
		
	public void delete(@Param("id") String id);

	void changeDiscontinued(@Param("id") String id);
}
