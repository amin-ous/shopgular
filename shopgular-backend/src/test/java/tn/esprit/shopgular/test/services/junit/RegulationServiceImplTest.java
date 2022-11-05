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
		RegulationModel regulationModel = new RegulationModel(50.000, 50.000);
		Regulation regulation = regulationServiceInt.addRegulation(regulationModel);
		bufferedWriter.write("regulationId = " + regulation.getId() + "\n");
		bufferedWriter.close();
		assertNotNull(regulation.getId());
		assertTrue(regulation.getAmountPaid() > 0);
		assertNotNull(regulation.getAmountRemaining());
		assertTrue(new Date().getTime() - regulation.getCreationDate().getTime() <= 600);
		if (regulation.getAmountRemaining() != 0) {
			assertFalse(regulation.getPaid());
		} else {
			assertTrue(regulation.getPaid());
		}
	}

	@Test
	@Order(2)
	void testGetAllRegulations() {
		assertEquals(initialSize + 1, regulationServiceInt.getAllRegulations().size());
	}

	@Test
	@Order(3)
	void testUpdateRegulation() {
		RegulationModel regulationModel = new RegulationModel(regulationId, 100.000, 0.000);
		Regulation regulation = regulationServiceInt.updateRegulation(regulationModel);
		assertEquals(regulation.getId(), regulationId);
		assertEquals(regulation.getAmountPaid() + regulation.getAmountRemaining(),
		regulationServiceInt.getRegulation(regulationId).getAmountPaid() +
		regulationServiceInt.getRegulation(regulationId).getAmountRemaining());
		assertEquals(regulation.getCreationDate(), regulation.getCreationDate());
		if (regulation.getAmountRemaining() != 0) {
			assertFalse(regulation.getPaid());
		} else {
			assertTrue(regulation.getPaid());
		}
	}

	@Test
	@Order(4)
	void testDeleteRegulation() {
		regulationServiceInt.deleteRegulation(regulationId);
		assertNull(regulationServiceInt.getRegulation(regulationId));
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
