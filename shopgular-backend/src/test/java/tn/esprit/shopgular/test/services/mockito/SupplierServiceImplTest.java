package tn.esprit.shopgular.test.services.mockito;

import java.io.*;
import java.util.*;
import lombok.extern.slf4j.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;
import org.springframework.boot.test.context.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.repositories.*;
import tn.esprit.shopgular.services.*;

@Slf4j
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@TestMethodOrder(OrderAnnotation.class)
class SupplierServiceImplTest {

	@Mock
	SupplierDetailsRepository supplierDetailsRepository;
	@Mock
	SupplierRepository supplierRepository;
	@InjectMocks
	SupplierServiceImpl supplierServiceImpl;

	SupplierDetails supplierDetails = SupplierDetails.builder().email("supplier@shopgular.tn").address("Ben Arous, Tunisia").serialNumber("98TU7654").build();
	Supplier supplier = Supplier.builder().code("TAS").wording("Test Add Supplier").category(SupplierCategory.ORDINARY).details(supplierDetails).build();

	List<Supplier> suppliers = new ArrayList<Supplier>(){
		{
			add(Supplier.builder().code("TS1").wording("Test Supplier 1").category(SupplierCategory.ORDINARY).details(null).build());
			add(Supplier.builder().code("TS2").wording("Test Supplier 2").category(SupplierCategory.ORDINARY).details(null).build());
			add(Supplier.builder().code("TS3").wording("Test Supplier 3").category(SupplierCategory.ORDINARY).details(null).build());
		}
	};

	@Test
	@Order(1)
	void testAddSupplier() throws IOException {
		Mockito.when(supplierDetailsRepository.save(supplierDetails)).thenReturn(supplierDetails);
		Mockito.when(supplierRepository.save(supplier)).thenReturn(supplier);
	    Supplier s = supplierServiceImpl.addSupplier(supplier);
	    Assertions.assertNotNull(s);
		log.info("Supplier has been successfully added");
	}

	@Test
	@Order(2)
	void testGetAllSuppliers() {
		Mockito.when(supplierRepository.findAll()).thenReturn(suppliers);
		List<Supplier> s = supplierServiceImpl.getAllSuppliers();
		Assertions.assertNotNull(s);
		log.info("Suppliers have been successfully retrieved");
	}

	@Test
	@Order(3)
	void testDeleteSupplier() {
		supplierServiceImpl.deleteSupplier(supplier.getId());
		Mockito.verify(supplierRepository).deleteById(supplier.getId());
		log.info("Supplier has been successfully deleted");
	}

}
