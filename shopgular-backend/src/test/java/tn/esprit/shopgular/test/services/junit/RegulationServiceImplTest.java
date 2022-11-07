package tn.esprit.shopgular.test.services.junit;

import java.io.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.services.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegulationServiceImplTest {

	@Autowired
	RegulationServiceInt regulationServiceInt;

	File tempFile;
	Integer initialSize;
	Long regulationId;
	BufferedWriter bufferedWriter;

	@BeforeAll
	void start() throws IOException {
		tempFile = new File("src/test/java/tn/esprit/shopgular/test/services/junit/" + getClass().getSimpleName() + ".txt");
		tempFile.createNewFile();
		bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
		initialSize = regulationServiceInt.getAllRegulations().size();
		bufferedWriter.write("initialSize = " + initialSize + "\n");
	}

	@BeforeEach
	void init() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("initialSize")) {
				initialSize = Integer.parseInt(line.substring(14));
			} else if (line.contains("regulationId")) {
				regulationId = Long.parseLong(line.substring(15));
			}
		}
		bufferedReader.close();
	}

	@Test
	@Order(1)
	void testAddRegulation() throws IOException {
		Regulation regulation = new Regulation(50.000, 50.000);
		Regulation addedRegulation = regulationServiceInt.addRegulation(regulation);
		bufferedWriter.write("regulationId = " + addedRegulation.getId() + "\n");
		bufferedWriter.close();
		Assertions.assertNotNull(addedRegulation.getId());
		Assertions.assertEquals(regulation.getAmountPaid(), addedRegulation.getAmountPaid());
		Assertions.assertEquals(regulation.getAmountRemaining(), addedRegulation.getAmountRemaining());
		if (addedRegulation.getAmountRemaining() != 0) {
			Assertions.assertFalse(addedRegulation.getPaid());
		} else {
			Assertions.assertTrue(addedRegulation.getPaid());
		}
	}

	@Test
	@Order(2)
	void testGetAllRegulations() {
		Assertions.assertEquals(initialSize + 1, regulationServiceInt.getAllRegulations().size());
	}

	@Test
	@Order(3)
	void testUpdateRegulation() {
		Regulation regulation = new Regulation(regulationId, 100.000, 0.000);
		Regulation gottenRegulation = regulationServiceInt.getRegulation(regulationId);
		Regulation updatedRegulation = regulationServiceInt.updateRegulation(regulation);
		Assertions.assertEquals(regulationId, updatedRegulation.getId());
		Assertions.assertEquals(gottenRegulation.getAmountPaid() + gottenRegulation.getAmountRemaining(), updatedRegulation.getAmountPaid() + updatedRegulation.getAmountRemaining());
		if (updatedRegulation.getAmountRemaining() != 0) {
			Assertions.assertFalse(updatedRegulation.getPaid());
		} else {
			Assertions.assertTrue(updatedRegulation.getPaid());
		}
	}

	@Test
	@Order(4)
	void testDeleteRegulation() {
		regulationServiceInt.deleteRegulation(regulationId);
		Assertions.assertNull(regulationServiceInt.getRegulation(regulationId));
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
