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
class StockServiceImplTest {

	@Autowired
	StockServiceInt stockServiceInt;

	File tempFile;
	Integer initialSize;
	Long stockId;
	BufferedWriter bufferedWriter;

	@BeforeAll
	void start() throws IOException {
		tempFile = new File("src/test/java/tn/esprit/shopgular/test/services/junit/" + getClass().getSimpleName() + ".txt");
		tempFile.createNewFile();
		bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
		initialSize = stockServiceInt.getAllStocks().size();
		bufferedWriter.write("initialSize = " + initialSize + "\n");
	}

	@BeforeEach
	void init() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("initialSize")) {
				initialSize = Integer.parseInt(line.substring(14));
			} else if (line.contains("stockId")) {
				stockId = Long.parseLong(line.substring(10));
			}
		}
		bufferedReader.close();
	}

	@Test
	@Order(1)
	void testAddStock() throws IOException {
		StockModel stockModel = new StockModel("Test Add Stock", 50, 10);
		Stock stock = stockServiceInt.addStock(stockModel);
		bufferedWriter.write("stockId = " + stock.getId() + "\n");
		bufferedWriter.close();
		assertNotNull(stock.getId());
		assertTrue(stock.getCurrentQuantity() > stock.getMinimumQuantity());
		assertTrue(stock.getMinimumQuantity() > 0);
	}

	@Test
	@Order(2)
	void testGetAllStocks() {
		assertEquals(initialSize + 1, stockServiceInt.getAllStocks().size());
	}

	@Test
	@Order(3)
	void testUpdateStock() {
		StockModel stockModel = new StockModel(stockId, "Test Update Stock", 100, 20);
		Stock stock = stockServiceInt.updateStock(stockModel);
		assertEquals(stock.getId(), stockId);
		assertTrue(stock.getCurrentQuantity() > 0);
		assertTrue(stock.getMinimumQuantity() > 0);
	}

	@Test
	@Order(4)
	void testDeleteStock() {
		stockServiceInt.deleteStock(stockId);
		assertNull(stockServiceInt.getStock(stockId));
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
