package tn.esprit.shopgular.test.services.junit;

import java.io.*;
import java.util.*;
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
		tempFile = new File("src/test/java/tn/esprit/shopgular/test/services/junit/" + getClass().getSimpleName() + ".txt");
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
		Product product = new Product("TP", "Test Product", 10.000);
		Product addedProduct = productServiceInt.addProduct(product);
		bufferedWriter.write("productId = " + addedProduct.getId() + "\n");
		Set<InvoiceItem> invoiceItems = new HashSet<InvoiceItem>();
		InvoiceItem invoiceItem = new InvoiceItem(50, 1, product);
		invoiceItems.add(invoiceItem);
		Invoice invoice = new Invoice(invoiceItems);
		Invoice addedInvoice = invoiceServiceInt.addInvoice(invoice);
		bufferedWriter.write("invoiceId = " + addedInvoice.getId() + "\n");
		bufferedWriter.close();
		Assertions.assertNotNull(addedInvoice.getId());
		if (invoiceItem.getPercentDecrease() <= 50) {
			Assertions.assertTrue(addedInvoice.getNetPrice() >= addedInvoice.getDeduction());
		} else {
			Assertions.assertTrue(addedInvoice.getDeduction() > addedInvoice.getNetPrice());
		}
		Assertions.assertFalse(addedInvoice.getArchived());
		Assertions.assertEquals(1, addedInvoice.getItems().size());
		log.info("Invoice has been successfully added");
	}

	@Test
	@Order(2)
	void testGetAllInvoices() {
		Assertions.assertEquals(initialSize + 1, invoiceServiceInt.getAllInvoices().size());
		log.info("Invoices have been successfully retrieved");

	}

	@Test
	@Order(3)
	void testCancelInvoice() {
		Invoice canceledInvoice = invoiceServiceInt.cancelInvoice(invoiceId);
		Assertions.assertTrue(canceledInvoice.getArchived());
		log.info("Invoice has been successfully canceled");
	}

	@Test
	@Order(4)
	void testDeleteInvoice() {
		productServiceInt.deleteProduct(productId);
		Assertions.assertNull(productServiceInt.getProduct(productId));
		invoiceServiceInt.deleteInvoice(invoiceId);
		Assertions.assertNull(invoiceServiceInt.getInvoice(productId));
		log.info("Invoice has been successfully deleted");
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
