package com.fakecorp.invoicing.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fakecorp.invoicing.api.exception.ResourceNotFoundException;
import com.fakecorp.invoicing.api.model.Invoice;
import com.fakecorp.invoicing.api.service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController extends BaseController {

	private static final String INVOICE_NOT_FOUND = "No se ha podido encontrar ninguna factura con el identificador ";
	private static final String INVOICES_FIND_ERROR = "No se ha podido recuperar la factura por un error interno: ";
	private static final String INVOICES_DELETE_ERROR = "No se ha podido eliminar la factura por un error interno: ";

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getOneInvoice(@PathVariable String id) {
		
		try {
			Invoice invoice = invoiceService.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(INVOICE_NOT_FOUND.concat(id)));
			return ResponseEntity.ok(invoice);
		} catch (DataAccessException e) {
			return manageDbError(INVOICES_FIND_ERROR, e);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {

		invoiceService.findById(id).orElseThrow(() -> new ResourceNotFoundException(INVOICE_NOT_FOUND.concat(id)));

		try {
			invoiceService.delete(id);
		} catch (DataAccessException e) {
			return manageDbError(INVOICES_DELETE_ERROR, e);
		}

		return getDeletedResponse();
	}

}
