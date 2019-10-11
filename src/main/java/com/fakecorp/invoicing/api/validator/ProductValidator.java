package com.fakecorp.invoicing.api.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.fakecorp.invoicing.api.model.Product;

public class ProductValidator {
	
	public Map<String,Object> validate(Product product) {
		
		Map<String,Object> errors = new HashMap<String, Object>();

		if (null == product.getName() || StringUtils.isEmpty(product.getName().trim())) {
			errors.put("name_empty", "El nombre no puede estar vacío");
		}
		
		if (null == product.getPrice() || product.getPrice() < 0) {
			errors.put("price_negative", "El precio no puede estar vacío ni ser menor que cero");
		}
		
		return errors;
	}
}
