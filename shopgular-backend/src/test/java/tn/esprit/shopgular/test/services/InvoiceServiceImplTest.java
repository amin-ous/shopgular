package tn.esprit.shopgular.test.services;

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
class InvoiceServiceImplTest {

	@Autowired
	InvoiceServiceInt invoiceServiceInt;
	@Autowired
	ProductServiceInt productServiceInt;

	File tempFile;
	Integer initialSize;
	Long productId;
	Long invoiceId;
	BufferedWriter bufferedWriter;

	@BeforeAll
	void start() throws IOException {
		tempFile = new File("src/test/java/tn/esprit/shopgular/test/services/" + getClass().getSimpleName() + ".txt");
		tempFile.createNewFile();
		bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
		initialSize = invoiceServiceInt.getAllInvoices().size();
		bufferedWriter.write("initialSize = " + initialSize + "\n");
	}

	@BeforeEach
	void init() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("initialSize")) {
				initialSize = Integer.parseInt(line.substring(14));
			} else if (line.contains("productId")) {
				productId = Long.parseLong(line.substring(12));
			} else if (line.contains("invoiceId")) {
				invoiceId = Long.parseLong(line.substring(12));
			}
		}
		bufferedReader.close();
	}

	@Test
	@Order(1)
	void testAddInvoice() throws IOException {
		ProductModel productModel = new ProductModel("TP", "Test Product", 10.000);
		Product product = productServiceInt.addProduct(productModel);
		bufferedWriter.write("productId = " + product.getId() + "\n");
		Set<InvoiceItemModel> invoiceItemsModels = new HashSet<InvoiceItemModel>();
		InvoiceItemModel invoiceItemModel = new InvoiceItemModel(50, 1, product);
		invoiceItemsModels.add(invoiceItemModel);
		InvoiceModel invoiceModel = new InvoiceModel(invoiceItemsModels);
		Invoice invoice = invoiceServiceInt.addInvoice(invoiceModel);
		bufferedWriter.write("invoiceId = " + invoice.getId() + "\n");
		bufferedWriter.close();
		assertNotNull(invoice.getId());
		assertTrue(invoiceItemModel.getPercentDecrease() > 0);
		assertTrue(invoiceItemModel.getPercentDecrease() < 100);
		assertTrue(invoiceItemModel.getQuantity() > 0);
		if (invoiceItemModel.getPercentDecrease() < 50) {
			assertTrue(invoice.getNetPrice() > invoice.getDeduction());
		} else {
			assertFalse(invoice.getNetPrice() > invoice.getDeduction());
		}
		assertTrue(new Date().getTime() - invoice.getCreationDate().getTime() <= 600);
		assertTrue(new Date().getTime() - invoice.getLastModificationDate().getTime() <= 600);
		assertFalse(invoice.getArchived());
		assertEquals(1, invoice.getItems().size());
	}

	@Test
	@Order(2)
	void testGetAllInvoices() {
		assertEquals(initialSize + 1, invoiceServiceInt.getAllInvoices().size());
	}

	@Test
	@Order(3)
	void testCancelInvoice() {
		Invoice invoice = invoiceServiceInt.cancelInvoice(invoiceId);
		assertTrue(new Date().getTime() - invoice.getLastModificationDate().getTime() <= 600);
		assertTrue(invoiceServiceInt.getInvoice(invoiceId).getArchived());
	}

	@Test
	@Order(4)
	void testDeleteInvoice() {
		productServiceInt.deleteProduct(productId);
		assertNull(productServiceInt.getProduct(productId));
		invoiceServiceInt.deleteInvoice(invoiceId);
		assertNull(invoiceServiceInt.getInvoice(productId));
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
