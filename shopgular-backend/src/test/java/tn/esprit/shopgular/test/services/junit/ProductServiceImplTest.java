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
class ProductServiceImplTest {

	@Autowired
	ProductServiceInt productServiceInt;

	File tempFile;
	Integer initialSize;
	Long productId;
	BufferedWriter bufferedWriter;

	@BeforeAll
	void start() throws IOException {
		tempFile = new File("src/test/java/tn/esprit/shopgular/test/services/junit/" + getClass().getSimpleName() + ".txt");
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
		Product product = new Product("TAP", "Test Add Product", 10.000);
		Product addedProduct = productServiceInt.addProduct(product);
		bufferedWriter.write("productId = " + addedProduct.getId() + "\n");
		bufferedWriter.close();
		Assertions.assertNotNull(addedProduct.getId());
		Assertions.assertEquals(product.getCode(), addedProduct.getCode());
		Assertions.assertEquals(product.getWording(), addedProduct.getWording());
		Assertions.assertEquals(product.getListPrice(), addedProduct.getListPrice());
		log.info("Product has been successfully added");
	}

	@Test
	@Order(2)
	void testGetAllProducts() {
		Assertions.assertEquals(initialSize + 1, productServiceInt.getAllProducts().size());
		log.info("Products have been successfully retrieved");
	}

	@Test
	@Order(3)
	void testUpdateProduct() {
		Product product = new Product(productId, "TUP", "Test Update Product", 20.000);
		Product updatedProduct = productServiceInt.updateProduct(product);
		Assertions.assertEquals(productId, updatedProduct.getId());
		Assertions.assertEquals(product.getCode(), updatedProduct.getCode());
		Assertions.assertEquals(product.getWording(), updatedProduct.getWording());
		Assertions.assertEquals(product.getListPrice(), updatedProduct.getListPrice());
		log.info("Product has been successfully updated");
	}

	@Test
	@Order(4)
	void testDeleteProduct() {
		productServiceInt.deleteProduct(productId);
		Assertions.assertNull(productServiceInt.getProduct(productId));
		log.info("Product has been successfully deleted");
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
