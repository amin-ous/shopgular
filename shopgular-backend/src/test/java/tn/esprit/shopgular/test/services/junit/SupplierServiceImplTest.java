package tn.esprit.shopgular.test.services.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.services.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SupplierServiceImplTest {

	@Autowired
	SupplierServiceInt supplierServiceInt;

	File tempFile;
	Integer initialSize;
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
			} else if (line.contains("supplierId")) {
				supplierId = Long.parseLong(line.substring(13));
			}
		}
		bufferedReader.close();
	}

	@Test
	@Order(1)
	void testAddSupplier() throws IOException {
		SupplierDetailsModel supplierDetailsModel = new SupplierDetailsModel("add.supplier@shopgular.tn", "Ben Arous, Tunisia", "98TUN7654");
		SupplierModel supplierModel = new SupplierModel("TAS", "Test Add Supplier", SupplierCategory.ORDINARY, supplierDetailsModel);
		Supplier supplier = supplierServiceInt.addSupplier(supplierModel);
		bufferedWriter.write("supplierId = " + supplier.getId() + "\n");
		bufferedWriter.close();
		assertNotNull(supplier.getId());
		assertEquals(supplier.getCode(), supplierModel.getCode());
		assertEquals(supplier.getWording(), supplierModel.getWording());
		assertEquals(supplier.getCategory(), supplierModel.getCategory());
		assertNotNull(supplier.getDetails());
	}

	@Test
	@Order(2)
	void testGetAllSuppliers() {
		assertEquals(initialSize + 1, supplierServiceInt.getAllSuppliers().size());
	}

	@Test
	@Order(3)
	void testUpdateSupplier() {
		SupplierDetailsModel supplierDetailsModel = new SupplierDetailsModel("update.supplier@shopgular.tn", "Ariana, Tunisia", "123TUN4567", new Date());
		SupplierModel supplierModel = new SupplierModel(supplierId, "TUS", "Test Update Supplier", SupplierCategory.AGREED, supplierDetailsModel);
		Supplier supplier = supplierServiceInt.updateSupplier(supplierModel);
		assertEquals(supplier.getId(), supplierId);
		assertEquals(supplier.getCode(), supplierModel.getCode());
		assertEquals(supplier.getWording(), supplierModel.getWording());
		assertEquals(supplier.getCategory(), supplierModel.getCategory());
		assertNotNull(supplier.getDetails());
	}

	@Test
	@Order(4)
	void testDeleteSupplier() {
		supplierServiceInt.deleteSupplier(supplierId);
		assertNull(supplierServiceInt.getSupplier(supplierId));
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
