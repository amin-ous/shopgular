package tn.esprit.shopgular.services;

import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;
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
	public Supplier addSupplier(Supplier supplier) {
		SupplierDetails supplierDetails = supplier.getDetails();
		supplierDetails.setCollaborationDate(new Date());
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
	public Supplier updateSupplier(Supplier supplier) {
		Supplier targetedSupplier = getSupplier(supplier.getId());
		targetedSupplier.setCode(Optional.ofNullable(supplier.getCode()).orElse(targetedSupplier.getCode()));
		targetedSupplier.setWording(Optional.ofNullable(supplier.getWording()).orElse(targetedSupplier.getWording()));
		targetedSupplier.setCategory(Optional.ofNullable(supplier.getCategory()).orElse(targetedSupplier.getCategory()));
		SupplierDetails supplierDetails = supplier.getDetails();
		SupplierDetails targetedSupplierDetails = targetedSupplier.getDetails();
		targetedSupplierDetails.setEmail(Optional.ofNullable(supplierDetails.getEmail()).orElse(targetedSupplierDetails.getEmail()));
		targetedSupplierDetails.setAddress(Optional.ofNullable(supplierDetails.getAddress()).orElse(targetedSupplierDetails.getAddress()));
		targetedSupplierDetails.setSerialNumber(Optional.ofNullable(supplierDetails.getSerialNumber()).orElse(targetedSupplierDetails.getSerialNumber()));
		targetedSupplierDetails.setCollaborationDate(Optional.ofNullable(supplierDetails.getCollaborationDate()).orElse(targetedSupplierDetails.getCollaborationDate()));
		supplierDetailsRepository.save(targetedSupplierDetails);
		targetedSupplier.setDetails(targetedSupplierDetails);
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
