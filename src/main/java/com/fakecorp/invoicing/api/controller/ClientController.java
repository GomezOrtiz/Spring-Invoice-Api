package com.fakecorp.invoicing.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fakecorp.invoicing.api.exception.ResourceNotFoundException;
import com.fakecorp.invoicing.api.model.Client;
import com.fakecorp.invoicing.api.service.ClientService;
import com.fakecorp.invoicing.api.validator.ClientValidator;

@RestController
@RequestMapping("/api/clients")
public class ClientController extends BaseController {

	private static final String CLIENT_NOT_FOUND = "No se ha podido encontrar ningún cliente con el identificador ";
	private static final String CLIENTS_ALL_ERROR = "No se ha podido recuperar la lista de clientes por un error interno: ";
	private static final String CLIENTS_FIND_ERROR = "No se ha podido recuperar el cliente ";
	private static final String CLIENTS_NEW_ERROR = "No se ha podido crear el cliente por un error interno: ";
	private static final String CLIENTS_EDIT_ERROR = "No se ha podido actualizar el cliente ";
	private static final String CLIENTS_DELETE_ERROR = "No se ha podido eliminar el cliente ";

	@Autowired
	private ClientService clientService;

	private ClientValidator clientValidator = new ClientValidator();

	@GetMapping("")
	public ResponseEntity<?> getAllClients() {

		try {
			return ResponseEntity.ok(clientService.findAll());
		} catch (DataAccessException e) {
			return manageDbError(CLIENTS_ALL_ERROR, e);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOneClient(@PathVariable String id) {

		try {
			Client client = clientService.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(CLIENT_NOT_FOUND.concat(id)));
			return ResponseEntity.ok(client);
		} catch (DataAccessException e) {
			return manageDbError(CLIENTS_FIND_ERROR.concat(id).concat(INTERNAL_ERROR), e);
		}
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<?> getByName(@PathVariable String name) {

		try {
			List<Client> clients = clientService.findByName(name);
			if (null == clients || clients.isEmpty()) {
				throw new ResourceNotFoundException(CLIENT_NOT_FOUND.concat(name));
			}
			return ResponseEntity.ok(clients);
		} catch (DataAccessException e) {
			return manageDbError(CLIENTS_FIND_ERROR.concat(name).concat(INTERNAL_ERROR), e);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody Client client) {

		Map<String, Object> errors = clientValidator.validate(client);

		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}

		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(client));
		} catch (DataAccessException e) {
			return manageDbError(CLIENTS_NEW_ERROR, e);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@RequestBody Client client, @PathVariable String id) {

		Map<String, Object> errors = clientValidator.validate(client);

		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}

		client.setId(id);

		try {
			return clientService.update(client).map(updatedClient -> ResponseEntity.ok(updatedClient))
					.orElseThrow(() -> new ResourceNotFoundException(CLIENT_NOT_FOUND.concat(client.getId())));
		} catch (DataAccessException e) {
			return manageDbError(CLIENTS_EDIT_ERROR.concat(id).concat(INTERNAL_ERROR), e);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {

		clientService.findById(id).orElseThrow(() -> new ResourceNotFoundException(CLIENT_NOT_FOUND.concat(id)));

		try {
			clientService.delete(id);
		} catch (DataAccessException e) {
			return manageDbError(CLIENTS_DELETE_ERROR.concat(id).concat(INTERNAL_ERROR), e);
		}

		return getDeletedResponse();
	}
}
