package com.fakecorp.invoicing.api.dao;

import java.util.List;
import java.util.Optional;

import com.fakecorp.invoicing.api.model.Client;

public interface ClientDao {
	
	public List<Client> findAll();
	
	public Optional<Client> findById(String id);
	
	public List<Client> findByName(String name);
	
	public Optional<Client> save(Client client);
		
	public void delete(String id);
}