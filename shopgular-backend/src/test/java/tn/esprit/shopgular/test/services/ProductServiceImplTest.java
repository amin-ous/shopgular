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
class ProductServiceImplTest {

	@Autowired
	ProductServiceInt productServiceInt;

	File tempFile;
	Integer initialSize;
	Long productId;
	BufferedWriter bufferedWriter;

	@BeforeAll
	void start() throws IOException {
		tempFile = new File("src/test/java/tn/esprit/shopgular/test/services/" + getClass().getSimpleName() + ".txt");
		tempFile.createNewFile();
		bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
		initialSize = productServiceInt.getAllProducts().size();
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
			}
		}
		bufferedReader.close();
	}

	@Test
	@Order(1)
	void testAddProduct() throws IOException {
		ProductModel productModel = new ProductModel("TAP", "Test Add Product", 10.000);
		Product product = productServiceInt.addProduct(productModel);
		bufferedWriter.write("productId = " + product.getId() + "\n");
		bufferedWriter.close();
		assertNotNull(product.getId());
		assertEquals(product.getCode(), productModel.getCode());
		assertEquals(product.getWording(), productModel.getWording());
		assertTrue(product.getListPrice() > 0);
		assertTrue(new Date().getTime() - product.getCreationDate().getTime() <= 600);
		assertTrue(new Date().getTime() - product.getLastModificationDate().getTime() <= 600);
	}

	@Test
	@Order(2)
	void testGetAllProducts() {
		assertEquals(initialSize + 1, productServiceInt.getAllProducts().size());
	}

	@Test
	@Order(3)
	void testUpdateProduct() {
		ProductModel productModel = new ProductModel(productId, "TUP", "Test Update Product", 20.000);
		Product product = productServiceInt.updateProduct(productModel);
		assertEquals(product.getId(), productId);
		assertEquals(product.getCode(), productModel.getCode());
		assertEquals(product.getWording(), productModel.getWording());
		assertTrue(product.getListPrice() > 0);
		assertEquals(product.getCreationDate(), product.getCreationDate());
		assertTrue(new Date().getTime() - product.getLastModificationDate().getTime() <= 600);
	}

	@Test
	@Order(4)
	void testDeleteProduct() {
		productServiceInt.deleteProduct(productId);
		assertNull(productServiceInt.getProduct(productId));
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
