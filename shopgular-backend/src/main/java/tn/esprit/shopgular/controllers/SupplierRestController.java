package tn.esprit.shopgular.controllers;

import io.swagger.annotations.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.services.*;

@RestController
@RequestMapping("/supplier")
@Api(tags = "Supplier Management")
@CrossOrigin(origins = "http://localhost:4200")
public class SupplierRestController {

	@Autowired
	SupplierServiceInt supplierServiceInt;

	@ResponseBody
	@PostMapping("/add-supplier")
	public Supplier addSupplier(@RequestBody SupplierModel supplierModel) {
		return supplierServiceInt.addSupplier(supplierModel);
	}

	@ResponseBody
	@GetMapping("/get-all-suppliers")
	public List<Supplier> getAllSuppliers() {
		return supplierServiceInt.getAllSuppliers();
	}

	@ResponseBody
	@GetMapping("/get-supplier/{supplier-id}")
	public Supplier getSupplier(@PathVariable("supplier-id") Long supplierId) {
		return supplierServiceInt.getSupplier(supplierId);
	}

	@ResponseBody
	@GetMapping("/get-supplier-invoices/{supplier-id}")
	public List<Invoice> getSupplierInvoices(@PathVariable("supplier-id") Long supplierId) {
		return supplierServiceInt.getSupplierInvoices(supplierId);
	}

	@ResponseBody
	@PutMapping("/update-supplier")
	public Supplier updateSupplier(@RequestBody SupplierModel supplierModel) {
		return supplierServiceInt.updateSupplier(supplierModel);
	}

	@ResponseBody
	@PutMapping("/assign-activity-sector-to-supplier/{activity-sector-id}/{supplier-id}")
	public void assignActivitySectorToSupplier(@PathVariable("activity-sector-id") Long activitySectorId,
		@PathVariable("supplier-id") Long supplierId) {
		supplierServiceInt.assignActivitySectorToSupplier(activitySectorId, supplierId);
	}

	@ResponseBody
	@DeleteMapping("/delete-supplier/{supplier-id}")
	public void deleteSupplier(@PathVariable("supplier-id") Long supplierId) {
		supplierServiceInt.deleteSupplier(supplierId);
	}

}
