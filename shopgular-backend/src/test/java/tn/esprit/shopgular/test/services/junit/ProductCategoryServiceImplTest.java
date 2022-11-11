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
class ProductCategoryServiceImplTest {

	@Autowired
	ProductCategoryServiceInt productCategoryServiceInt;

	File tempFile;
	Integer initialSize;
	Long productCategoryId;
	BufferedWriter bufferedWriter;

	@BeforeAll
	void start() throws IOException {
		tempFile = new File("src/test/java/tn/esprit/shopgular/test/services/junit/" + getClass().getSimpleName() + ".txt");
		tempFile.createNewFile();
		bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
		initialSize = productCategoryServiceInt.getAllProductCategories().size();
		bufferedWriter.write("initialSize = " + initialSize + "\n");
	}

	@BeforeEach
	void init() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("initialSize")) {
				initialSize = Integer.parseInt(line.substring(14));
			} else if (line.contains("productCategoryId")) {
				productCategoryId = Long.parseLong(line.substring(20));
			}
		}
		bufferedReader.close();
	}

	@Test
	@Order(1)
	void testAddProductCategory() throws IOException {
		ProductCategory productCategory = new ProductCategory("TAPC", "Test Add Product Category");
		ProductCategory addedProductCategory = productCategoryServiceInt.addProductCategory(productCategory);
		bufferedWriter.write("productCategoryId = " + addedProductCategory.getId() + "\n");
		bufferedWriter.close();
		Assertions.assertNotNull(addedProductCategory.getId());
		Assertions.assertEquals(productCategory.getCode(), addedProductCategory.getCode());
		Assertions.assertEquals(productCategory.getWording(), addedProductCategory.getWording());
		log.info("Product category has been successfully added");
	}

	@Test
	@Order(2)
	void testGetAllProductCategorys() {
		Assertions.assertEquals(initialSize + 1, productCategoryServiceInt.getAllProductCategories().size());
		log.info("Product categories have been successfully retrieved");
	}

	@Test
	@Order(3)
	void testUpdateProductCategory() {
		ProductCategory productCategory = new ProductCategory(productCategoryId, "TUPC", "Test Update Product Category");
		ProductCategory updatedProductCategory = productCategoryServiceInt.updateProductCategory(productCategory);
		Assertions.assertEquals(productCategoryId, updatedProductCategory.getId());
		Assertions.assertEquals(productCategory.getCode(), updatedProductCategory.getCode());
		Assertions.assertEquals(productCategory.getWording(), updatedProductCategory.getWording());
		log.info("Product has been successfully updated");
	}

	@Test
	@Order(4)
	void testDeleteProductCategory() {
		productCategoryServiceInt.deleteProductCategory(productCategoryId);
		Assertions.assertNull(productCategoryServiceInt.getProductCategory(productCategoryId));
		log.info("Product has been successfully deleted");
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
