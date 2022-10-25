package tn.esprit.shopgular.controllers;

import io.swagger.annotations.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.format.annotation.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.services.*;

@RestController
@RequestMapping("/invoice")
@Api(tags = "Invoice Management")
@CrossOrigin(origins = "http://localhost:4200")
public class InvoiceRestController {

	@Autowired
	InvoiceServiceInt invoiceServiceInt;

	@ResponseBody
	@PostMapping("/add-invoice")
	public Invoice addInvoice(@RequestBody InvoiceModel invoiceModel) {
		return invoiceServiceInt.addInvoice(invoiceModel);
	}

	@ResponseBody
	@GetMapping("/get-all-invoices")
	public List<Invoice> getAllInvoices() {
		return invoiceServiceInt.getAllInvoices();
	}

	@ResponseBody
	@GetMapping("/get-invoice/{invoice-id}")
	public Invoice getInvoice(@PathVariable("invoice-id") Long invoiceId) {
		return invoiceServiceInt.getInvoice(invoiceId);
	}

	@ResponseBody
	@GetMapping("/get-invoice-items/{invoice-id}")
	public List<InvoiceItem> getInvoiceItems(@PathVariable("invoice-id") Long invoiceId) {
		return invoiceServiceInt.getInvoiceItems(invoiceId);
	}

	@ResponseBody
	@GetMapping("/get-periodic-sales-value/{start-date}/{end-date}")
	public double getPeriodicSalesValue(
		@PathVariable(name = "start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
		@PathVariable(name = "end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
		return invoiceServiceInt.getPeriodicSalesValue(startDate, endDate);
	}

	@ResponseBody
	@GetMapping("/get-periodic-payments-collection-percentage/{start-date}/{end-date}")
	public double getPeriodicPaymentsCollectionPercentage(
		@PathVariable(name = "start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
		@PathVariable(name = "end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
		return invoiceServiceInt.getPeriodicPaymentsCollectionPercentage(startDate, endDate);
	}

	@ResponseBody
	@PutMapping("/assign-operator-to-invoice/{operator-id}/{invoice-id}")
	public void assignOperatorToInvoice(@PathVariable("operator-id") Long operatorId,
		@PathVariable("invoice-id") Long invoiceId) {
		invoiceServiceInt.assignOperatorToInvoice(operatorId, invoiceId);
	}

	@ResponseBody
	@PutMapping("/cancel-invoice/{invoice-id}")
	public Invoice cancelInvoice(@PathVariable("invoice-id") Long invoiceId) {
		return invoiceServiceInt.cancelInvoice(invoiceId);
	}

	@ResponseBody
	@DeleteMapping("/delete-invoice/{invoice-id}")
	public void deleteInvoice(@PathVariable("invoice-id") Long invoiceId) {
		invoiceServiceInt.deleteInvoice(invoiceId);
	}

}
