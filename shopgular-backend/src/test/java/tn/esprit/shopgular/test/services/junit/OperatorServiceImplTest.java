package tn.esprit.shopgular.test.services.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.security.crypto.password.*;
import org.springframework.test.context.junit4.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.services.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OperatorServiceImplTest {

	@Autowired
	OperatorServiceInt operatorServiceInt;
	@Autowired
	PasswordEncoder passwordEncoder;

	File tempFile;
	Integer initialSize;
	Long operatorId;
	BufferedWriter bufferedWriter;

	@BeforeAll
	void start() throws IOException {
		tempFile = new File("src/test/java/tn/esprit/shopgular/test/services/junit/" + getClass().getSimpleName() + ".txt");
		tempFile.createNewFile();
		bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
		initialSize = operatorServiceInt.getAllOperators().size();
		bufferedWriter.write("initialSize = " + initialSize + "\n");
	}

	@BeforeEach
	void init() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("initialSize")) {
				initialSize = Integer.parseInt(line.substring(14));
			} else if (line.contains("operatorId")) {
				operatorId = Long.parseLong(line.substring(13));
			}
		}
		bufferedReader.close();
	}

	@Test
	@Order(1)
	void testAddOperator() throws IOException {
		OperatorModel operatorModel = new OperatorModel("Test Add", "Operator TAO", "Test Add Operator");
		operatorModel.setNewPassword(passwordEncoder.encode(operatorModel.getNewPassword()));
		Operator operator = operatorServiceInt.addOperator(operatorModel);
		bufferedWriter.write("operatorId = " + operator.getId() + "\n");
		bufferedWriter.close();
		assertNotNull(operator.getId());
		assertEquals(operator.getName(), operatorModel.getName());
		assertEquals(operator.getPrename(), operatorModel.getPrename());
		passwordEncoder.matches(passwordEncoder.encode(operatorModel.getNewPassword()), operator.getPassword());
	}

	@Test
	@Order(2)
	void testGetAllOperators() {
		assertEquals(initialSize + 1, operatorServiceInt.getAllOperators().size());
	}

	@Test
	@Order(3)
	void testUpdateOperator() {
		OperatorModel operatorModel = new OperatorModel(operatorId, "Test Update", "Operator TUO", "Test Add Operator", "Test Update Operator");
		Operator operator = operatorServiceInt.getOperator(operatorId);
		if (passwordEncoder.matches(passwordEncoder.encode(operatorModel.getNewPassword()), operator.getPassword())) {
			operator = operatorServiceInt.updateOperator(operatorModel);
			assertEquals(operator.getId(), operatorId);
			assertEquals(operator.getName(), operatorModel.getName());
			assertEquals(operator.getPrename(), operatorModel.getPrename());
		}
	}

	@Test
	@Order(4)
	void testDeleteOperator() {
		operatorServiceInt.deleteOperator(operatorId);
		assertNull(operatorServiceInt.getOperator(operatorId));
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
