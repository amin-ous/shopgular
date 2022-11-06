package tn.esprit.shopgular.test.services.junit;

import java.io.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.security.crypto.password.*;
import org.springframework.test.context.junit4.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.services.*;

@lombok.extern.slf4j.Slf4j
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
		Operator operator = new Operator("Test Add", "Operator TAO", "Test Add Operator");
		String rawPassword = operator.getCurrentPassword();
		Operator addedOperator = operatorServiceInt.addOperator(operator);
		bufferedWriter.write("operatorId = " + addedOperator.getId() + "\n");
		bufferedWriter.close();
		Assertions.assertNotNull(addedOperator.getId());
		Assertions.assertEquals(operator.getSurname(), addedOperator.getSurname());
		Assertions.assertEquals(operator.getPrename(), addedOperator.getPrename());
		log.info(operator.getCurrentPassword() + "rawPassword");
		log.info(addedOperator.getCurrentPassword() + "encodedPassword");
		Assertions.assertTrue(passwordEncoder.matches(rawPassword, addedOperator.getCurrentPassword()));
	}

	@Test
	@Order(2)
	void testGetAllOperators() {
		Assertions.assertEquals(initialSize + 1, operatorServiceInt.getAllOperators().size());
	}

	@Test
	@Order(3)
	void testUpdateOperator() {
		Operator operator = new Operator(operatorId, "Test Update", "Operator TUO", "Test Add Operator", "Test Update Operator");
		Operator updatedOperator = operatorServiceInt.updateOperator(operator);
		Assertions.assertEquals(operatorId, updatedOperator.getId());
		Assertions.assertEquals(operator.getSurname(), updatedOperator.getSurname());
		Assertions.assertEquals(operator.getPrename(), updatedOperator.getPrename());
	}

	@Test
	@Order(4)
	void testDeleteOperator() {
		operatorServiceInt.deleteOperator(operatorId);
		Assertions.assertNull(operatorServiceInt.getOperator(operatorId));
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
