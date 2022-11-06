package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;

public interface SupplierServiceInt {

	Supplier addSupplier(Supplier supplier);

	List<Supplier> getAllSuppliers();

	Supplier getSupplier(Long supplierId);

	List<Invoice> getSupplierInvoices(Long supplierId);

	Supplier updateSupplier(Supplier supplier);

	void assignActivitySectorToSupplier(Long activitySectorId, Long supplierId);

	void deleteSupplier(Long supplierId);

}
