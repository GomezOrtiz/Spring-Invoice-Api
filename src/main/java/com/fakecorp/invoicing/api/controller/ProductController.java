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
import com.fakecorp.invoicing.api.model.Product;
import com.fakecorp.invoicing.api.service.ProductService;
import com.fakecorp.invoicing.api.validator.ProductValidator;

@RestController
@RequestMapping("/api/products")
public class ProductController extends BaseController {

	private static final String PRODUCT_NOT_FOUND = "No se ha podido encontrar ningún producto con el identificador ";
	private static final String PRODUCTS_ALL_ERROR = "No se ha podido recuperar la lista de productos por un error interno: ";
	private static final String PRODUCTS_FIND_ERROR = "No se ha podido recuperar el producto ";
	private static final String PRODUCTS_NEW_ERROR = "No se ha podido crear el producto por un error interno: ";
	private static final String PRODUCTS_EDIT_ERROR = "No se ha podido actualizar el producto ";

	@Autowired
	private ProductService productService;

	private ProductValidator productValidator = new ProductValidator();

	@GetMapping("")
	public ResponseEntity<?> getAllProducts() {

		try {
			return ResponseEntity.ok(productService.findAll());
		} catch (DataAccessException e) {
			return manageDbError(PRODUCTS_ALL_ERROR, e);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOneProduct(@PathVariable String id) {

		try {
			Product product = productService.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND.concat(id)));
			return ResponseEntity.ok(product);
		} catch (DataAccessException e) {
			return manageDbError(PRODUCTS_FIND_ERROR.concat(id).concat(INTERNAL_ERROR), e);
		}

	}

	@GetMapping("/name/{name}")
	public ResponseEntity<?> getByName(@PathVariable String name) {

		try {
			List<Product> products = productService.findByName(name);
			if (null == products || products.isEmpty()) {
				throw new ResourceNotFoundException(PRODUCT_NOT_FOUND.concat(name));
			}
			return ResponseEntity.ok(products);
		} catch (DataAccessException e) {
			return manageDbError(PRODUCTS_FIND_ERROR.concat(name).concat(INTERNAL_ERROR), e);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody Product product) {

		Map<String, Object> errors = productValidator.validate(product);

		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}

		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
		} catch (DataAccessException e) {
			return manageDbError(PRODUCTS_NEW_ERROR, e);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@RequestBody Product product, @PathVariable String id) {

		Map<String, Object> errors = productValidator.validate(product);

		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}

		product.setId(id);

		try {
			return productService.update(product).map(updatedProduct -> ResponseEntity.ok(updatedProduct))
					.orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND.concat(product.getId())));
		} catch (DataAccessException e) {
			return manageDbError(PRODUCTS_EDIT_ERROR.concat(id).concat(INTERNAL_ERROR), e);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> discontinue(@PathVariable String id) {

		productService.findById(id).orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND.concat(id)));

		try {
			productService.changeDiscontinued(id);
		} catch (DataAccessException e) {
			return manageDbError(PRODUCTS_EDIT_ERROR.concat(id).concat(INTERNAL_ERROR), e);
		}

		return getModifiedResponse();
	}
}
