package tn.esprit.shopgular.test.services.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
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
		ProductCategoryModel productCategoryModel = new ProductCategoryModel("TAPC", "Test Add Product Category");
		ProductCategory productCategory = productCategoryServiceInt.addProductCategory(productCategoryModel);
		bufferedWriter.write("productCategoryId = " + productCategory.getId() + "\n");
		bufferedWriter.close();
		assertNotNull(productCategory.getId());
		assertEquals(productCategory.getCode(), productCategoryModel.getCode());
		assertEquals(productCategory.getWording(), productCategoryModel.getWording());
	}

	@Test
	@Order(2)
	void testGetAllProductCategorys() {
		assertEquals(initialSize + 1, productCategoryServiceInt.getAllProductCategories().size());
	}

	@Test
	@Order(3)
	void testUpdateProductCategory() {
		ProductCategoryModel productCategoryModel = new ProductCategoryModel(productCategoryId, "TUPC", "Test Update Product Category");
		ProductCategory productCategory = productCategoryServiceInt.updateProductCategory(productCategoryModel);
		assertEquals(productCategory.getId(), productCategoryId);
		assertEquals(productCategory.getCode(), productCategoryModel.getCode());
		assertEquals(productCategory.getWording(), productCategoryModel.getWording());
	}

	@Test
	@Order(4)
	void testDeleteProductCategory() {
		productCategoryServiceInt.deleteProductCategory(productCategoryId);
		assertNull(productCategoryServiceInt.getProductCategory(productCategoryId));
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
