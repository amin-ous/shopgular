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
class ActivitySectorServiceImplTest {

	@Autowired
	ActivitySectorServiceInt activitySectorServiceInt;

	File tempFile;
	Integer initialSize;
	Long activitySectorId;
	BufferedWriter bufferedWriter;

	@BeforeAll
	void start() throws IOException {
		tempFile = new File("src/test/java/tn/esprit/shopgular/test/services/junit/" + getClass().getSimpleName() + ".txt");
		tempFile.createNewFile();
		bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
		initialSize = activitySectorServiceInt.getAllActivitySectors().size();
		bufferedWriter.write("initialSize = " + initialSize + "\n");
	}

	@BeforeEach
	void init() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("initialSize")) {
				initialSize = Integer.parseInt(line.substring(14));
			} else if (line.contains("activitySectorId")) {
				activitySectorId = Long.parseLong(line.substring(19));
			}
		}
		bufferedReader.close();
	}

	@Test
	@Order(1)
	void testAddActivitySector() throws IOException {
		ActivitySectorModel activitySectorModel = new ActivitySectorModel("TAAS", "Test Add Activity Sector");
		ActivitySector activitySector = activitySectorServiceInt.addActivitySector(activitySectorModel);
		bufferedWriter.write("activitySectorId = " + activitySector.getId() + "\n");
		bufferedWriter.close();
		assertNotNull(activitySector.getId());
		assertEquals(activitySector.getCode(), activitySectorModel.getCode());
		assertEquals(activitySector.getWording(), activitySectorModel.getWording());
	}

	@Test
	@Order(2)
	void testGetAllActivitySectors() {
		assertEquals(initialSize + 1, activitySectorServiceInt.getAllActivitySectors().size());
	}

	@Test
	@Order(3)
	void testUpdateActivitySector() {
		ActivitySectorModel activitySectorModel = new ActivitySectorModel(activitySectorId, "TUAS", "Test Update Activity Sector");
		ActivitySector activitySector = activitySectorServiceInt.updateActivitySector(activitySectorModel);
		assertEquals(activitySector.getId(), activitySectorId);
		assertEquals(activitySector.getCode(), activitySectorModel.getCode());
		assertEquals(activitySector.getWording(), activitySectorModel.getWording());
	}

	@Test
	@Order(4)
	void testDeleteActivitySector() {
		activitySectorServiceInt.deleteActivitySector(activitySectorId);
		assertNull(activitySectorServiceInt.getActivitySector(activitySectorId));
	}

	@AfterAll
	void end() {
		tempFile.delete();
	}

}
