package com.fakecorp.invoicing.api.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fakecorp.invoicing.api.dao.ClientDao;
import com.fakecorp.invoicing.api.model.Client;
import com.fakecorp.invoicing.api.mapper.ClientMapper;

@Repository
public class ClientDaoImpl implements ClientDao {
	
	@Autowired
	private ClientMapper clientMapper;

	@Override
	@Transactional(readOnly=true)
	public List<Client> findAll() {
		return clientMapper.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Client> findById(String id) {
        return Optional.ofNullable(clientMapper.findById(id));
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Client> findByName(String name) {
        return clientMapper.findByName("%" + name + "%");
	}

	@Override
	@Transactional
	public Optional<Client> save(Client client) {
        if (null != clientMapper.findById(client.getId())) {
        	clientMapper.update(client);
        	return findById(client.getId());
        } else {
            clientMapper.insert(client);
            return findById(client.getId());
        }
	}

	@Override
	@Transactional
	public void delete(String id) {
        clientMapper.delete(id);
	}

}
