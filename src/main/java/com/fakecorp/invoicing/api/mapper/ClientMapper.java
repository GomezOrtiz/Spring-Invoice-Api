package com.fakecorp.invoicing.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fakecorp.invoicing.api.model.Client;

@Mapper
public interface ClientMapper {
	
	public List<Client> findAll();

	public Client findById(@Param("id") String id);
	
	public List<Client> findByName(@Param("name") String name);

	public void insert(@Param("client") Client client);

	public void update(@Param("client") Client client);

	public void delete(@Param("id") String id);
}
