package tn.esprit.shopgular.services;

import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.repositories.*;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierServiceInt {

	@Autowired
	ActivitySectorRepository activitySectorRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SupplierDetailsRepository supplierDetailsRepository;
	@Autowired
	SupplierRepository supplierRepository;

	@Override
	public Supplier addSupplier(SupplierModel supplierModel) {
		Supplier supplier = new Supplier(supplierModel.getCode(), supplierModel.getWording(), supplierModel.getCategory());
		SupplierDetailsModel supplierDetailsModel = supplierModel.getDetails();
		SupplierDetails supplierDetails = new SupplierDetails(supplierDetailsModel.getEmail(), supplierDetailsModel.getAddress(),
		supplierDetailsModel.getSerialNumber(), new Date());
		supplierDetailsRepository.save(supplierDetails);
		supplier.setDetails(supplierDetails);
		supplierRepository.save(supplier);
		return supplier;
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return supplierRepository.findAll();
	}

	@Override
	public Supplier getSupplier(Long supplierId) {
		return supplierRepository.findById(supplierId).orElse(null);
	}

	@Override
	public List<Invoice> getSupplierInvoices(Long supplierId) {
		Supplier supplier = supplierRepository.getById(supplierId);
		ArrayList<Invoice> invoices = new ArrayList<>();
		for (Invoice invoice: supplier.getInvoices()) {
			invoices.add(invoice);
		}
		return invoices;
	}

	@Override
	public Supplier updateSupplier(SupplierModel supplierModel) {
		Supplier supplier = getSupplier(supplierModel.getId());
		supplier.setCode(Optional.ofNullable(supplierModel.getCode()).orElse(supplier.getCode()));
		supplier.setWording(Optional.ofNullable(supplierModel.getWording()).orElse(supplier.getWording()));
		supplier.setCategory(Optional.ofNullable(supplierModel.getCategory()).orElse(supplier.getCategory()));
		SupplierDetailsModel supplierDetailsModel = supplierModel.getDetails();
		SupplierDetails supplierDetails = supplier.getDetails();
		supplierDetails.setEmail(supplierDetailsModel.getEmail());
		supplierDetails.setAddress(supplierDetailsModel.getAddress());
		supplierDetails.setSerialNumber(supplierDetailsModel.getSerialNumber());
		supplierDetails.setCollaborationDate(supplierDetailsModel.getCollaborationDate());
		supplierDetailsRepository.save(supplierDetails);
		supplier.setDetails(supplierDetails);
		supplierRepository.save(supplier);
		return supplier;
	}

	@Override
	public void assignActivitySectorToSupplier(Long activitySectorId, Long supplierId) {
		ActivitySector activitySector = activitySectorRepository.getById(activitySectorId);
		Supplier supplier = getSupplier(supplierId);
		supplier.getActivitySectors().add(activitySector);
		supplierRepository.save(supplier);
	}

	@Override
	public void deleteSupplier(Long supplierId) {
		supplierRepository.deleteById(supplierId);
	}

}
