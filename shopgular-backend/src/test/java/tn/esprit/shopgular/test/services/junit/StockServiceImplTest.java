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
		Stock stock = new Stock("Test Add Stock", 50, 10);
		Stock addedStock = stockServiceInt.addStock(stock);
		bufferedWriter.write("stockId = " + addedStock.getId() + "\n");
		bufferedWriter.close();
		Assertions.assertNotNull(addedStock.getId());
		Assertions.assertEquals(stock.getCurrentQuantity(), addedStock.getCurrentQuantity());
		Assertions.assertEquals(stock.getMinimumQuantity(), addedStock.getMinimumQuantity());
		log.info("Stock has been successfully added");
	}

	@Test
	@Order(2)
	void testGetAllStocks() {
		Assertions.assertEquals(initialSize + 1, stockServiceInt.getAllStocks().size());
		log.info("Stocks have been successfully retrieved");
	}

	@Test
	@Order(3)
	void testUpdateStock() {
		Stock stock = new Stock(stockId, "Test Update Stock", 100, 20);
		Stock updatedStock = stockServiceInt.updateStock(stock);
		Assertions.assertEquals(stockId, updatedStock.getId());
		Assertions.assertEquals(stock.getCurrentQuantity(), updatedStock.getCurrentQuantity());
		Assertions.assertEquals(stock.getMinimumQuantity(), updatedStock.getMinimumQuantity());
		log.info("Stock has been successfully updated");
	}

	@Test
	@Order(4)
	void testDeleteStock() {
		stockServiceInt.deleteStock(stockId);
		Assertions.assertNull(stockServiceInt.getStock(stockId));
		log.info("Stock has been successfully deleted");
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
