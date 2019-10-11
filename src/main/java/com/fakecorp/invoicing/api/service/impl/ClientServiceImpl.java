package com.fakecorp.invoicing.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fakecorp.invoicing.api.dao.ClientDao;
import com.fakecorp.invoicing.api.model.Client;
import com.fakecorp.invoicing.api.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;

	@Override
	public List<Client> findAll() {
		return clientDao.findAll();
	}
	
	@Override
	public Optional<Client> findById(String id) {
		return clientDao.findById(id);
	}
	
	@Override
	public List<Client> findByName(String name) {
		return clientDao.findByName(name);
	}
	
	@Override
	public Client create(Client client) {
		client.setCreatedAt(new Date());
		return clientDao.save(client).orElse(null);
	}
		
	@Override
	public Optional<Client> update(Client client) {
		client.setUpdatedAt(new Date());
		return clientDao.save(client);
	}

	@Override
	public void delete(String id) {
		if(!clientDao.findById(id).isEmpty()) {
			clientDao.delete(id);
		}
	}
}
