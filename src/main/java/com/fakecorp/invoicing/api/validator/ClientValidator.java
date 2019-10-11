package com.fakecorp.invoicing.api.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.fakecorp.invoicing.api.model.Client;

public class ClientValidator {
	
	@Autowired
	private EmailValidator emailValidator;
	
	public Map<String, Object> validate(Client client) {
		
		Map<String,Object> errors = new HashMap<String, Object>();

		if (null == client.getName() || StringUtils.isEmpty(client.getName().trim())) {
			errors.put("name_empty", "El nombre no puede estar vacío");
		}
		
		if (null == client.getSurname() || StringUtils.isEmpty(client.getSurname().trim())) {
			errors.put("surname_empty", "El apellido no puede estar vacío");
		}
		
		if (null == client.getEmail() || StringUtils.isEmpty(client.getEmail().trim())) {
			errors.put("email_empty", "El email no puede estar vacío");
			
			if(!emailValidator.validate(client.getEmail())) {
				errors.put("email_not_valid", "El email tiene un formato inválido");
			}
		}
		
		return errors;
	}
}
