package com.fakecorp.invoicing.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4425942108207172298L;

	public ResourceNotFoundException() {
	}

	public ResourceNotFoundException(String message) {
    	super(message, null, false, false);
    }
}