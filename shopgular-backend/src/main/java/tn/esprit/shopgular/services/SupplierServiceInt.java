package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;

public interface SupplierServiceInt {

	Supplier addSupplier(SupplierModel supplierModel);

	List<Supplier> getAllSuppliers();

	Supplier getSupplier(Long supplierId);

	List<Invoice> getSupplierInvoices(Long supplierId);

	Supplier updateSupplier(SupplierModel supplierModel);

	void assignActivitySectorToSupplier(Long activitySectorId, Long supplierId);

	void deleteSupplier(Long supplierId);

}
