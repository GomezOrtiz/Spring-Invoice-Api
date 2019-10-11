package com.fakecorp.invoicing.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = { "http://localhost:4200" })
public class BaseController {
	
	protected Logger LOGGER = LoggerFactory.getLogger(getClass());
	protected static final String BLANK = " ";
	protected static final String INTERNAL_ERROR = " por un error interno: ";
	
	protected ResponseEntity<?> manageDbError(String message, DataAccessException e) {
		LOGGER.info(message + e.getMessage() + BLANK + e.getMostSpecificCause().getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message + e.getMessage());
	}
	
	protected ResponseEntity<?> getResponse(String status) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put(status, "OK");
		return ResponseEntity.ok(response);
	}
	
	protected ResponseEntity<?> getDeletedResponse() {
		return getResponse("deleted");
	}
	
	protected ResponseEntity<?> getModifiedResponse() {
		return getResponse("modified");
	}
}
