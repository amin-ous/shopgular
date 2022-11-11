package tn.esprit.shopgular.test.services.junit;

import java.io.*;
import lombok.extern.slf4j.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.services.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SupplierServiceImplTest {

	@Autowired
	SupplierServiceInt supplierServiceInt;

	File tempFile;
	Integer initialSize;
	Long supplierDetailsId;
	Long supplierId;
	BufferedWriter bufferedWriter;

	@BeforeAll
	void start() throws IOException {
		tempFile = new File("src/test/java/tn/esprit/shopgular/test/services/junit/" + getClass().getSimpleName() + ".txt");
		tempFile.createNewFile();
		bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
		initialSize = supplierServiceInt.getAllSuppliers().size();
		bufferedWriter.write("initialSize = " + initialSize + "\n");
	}

	@BeforeEach
	void init() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("initialSize")) {
				initialSize = Integer.parseInt(line.substring(14));
			} else if (line.contains("supplierDetailsId")) {
				supplierDetailsId = Long.parseLong(line.substring(20));
			} else if (line.contains("supplierId")) {
				supplierId = Long.parseLong(line.substring(13));
			}
		}
		bufferedReader.close();
	}

	@Test
	@Order(1)
	void testAddSupplier() throws IOException {
		SupplierDetails supplierDetails = new SupplierDetails("add.supplier@shopgular.tn", "Ben Arous, Tunisia", "98TU7654");
		Supplier supplier = new Supplier("TAS", "Test Add Supplier", SupplierCategory.ORDINARY, supplierDetails);
		Supplier addedSupplier = supplierServiceInt.addSupplier(supplier);
		bufferedWriter.write("supplierDetailsId = " + addedSupplier.getDetails().getId() + "\n");
		bufferedWriter.write("supplierId = " + addedSupplier.getId() + "\n");
		bufferedWriter.close();
		Assertions.assertNotNull(addedSupplier.getId());
		Assertions.assertEquals(supplier.getCode(), addedSupplier.getCode());
		Assertions.assertEquals(supplier.getWording(), addedSupplier.getWording());
		Assertions.assertEquals(supplier.getCategory(), addedSupplier.getCategory());
		Assertions.assertNotNull(addedSupplier.getDetails());
		log.info("Supplier has been successfully added");
	}

	@Test
	@Order(2)
	void testGetAllSuppliers() {
		Assertions.assertEquals(initialSize + 1, supplierServiceInt.getAllSuppliers().size());
		log.info("Suppliers have been successfully retrieved");
	}

	@Test
	@Order(3)
	void testUpdateSupplier() {
		SupplierDetails supplierDetails = new SupplierDetails(supplierDetailsId, "update.supplier@shopgular.tn", "Ariana, Tunisia", "123TU4567");
		Supplier supplier = new Supplier(supplierId, "TUS", "Test Update Supplier", SupplierCategory.AGREED, supplierDetails);
		Supplier updatedSupplier = supplierServiceInt.updateSupplier(supplier);
		Assertions.assertEquals(supplierId, updatedSupplier.getId());
		Assertions.assertEquals(supplier.getCode(), updatedSupplier.getCode());
		Assertions.assertEquals(supplier.getWording(), updatedSupplier.getWording());
		Assertions.assertEquals(supplier.getCategory(), updatedSupplier.getCategory());
		Assertions.assertEquals(supplierDetailsId, updatedSupplier.getDetails().getId());
		Assertions.assertEquals(supplierDetails.getEmail(), updatedSupplier.getDetails().getEmail());
		Assertions.assertEquals(supplierDetails.getAddress(), updatedSupplier.getDetails().getAddress());
		Assertions.assertEquals(supplierDetails.getSerialNumber(), updatedSupplier.getDetails().getSerialNumber());
		log.info("Supplier has been successfully updated");
	}

	@Test
	@Order(4)
	void testDeleteSupplier() {
		supplierServiceInt.deleteSupplier(supplierId);
		Assertions.assertNull(supplierServiceInt.getSupplier(supplierId));
		log.info("Supplier has been successfully deleted");
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
