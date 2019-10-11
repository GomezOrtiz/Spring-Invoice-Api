package com.fakecorp.invoicing.api.service;

import java.util.List;
import java.util.Optional;

import com.fakecorp.invoicing.api.model.Client;

public interface ClientService {

	public List<Client> findAll();
	
	public Optional<Client> findById(String id);
	
	public List<Client> findByName(String name);

	public Client create(Client client);

	public Optional<Client> update(Client client);
	
	public void delete(String id);


}
