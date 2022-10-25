package tn.esprit.shopgular.controllers;

import io.swagger.annotations.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.services.*;

@RestController
@RequestMapping("/activity-sector")
@Api(tags = "Activity Sector Management")
@CrossOrigin(origins = "http://localhost:4200")
public class ActivitySectorController {

	@Autowired
	ActivitySectorServiceInt activitySectorServiceInt;

	@ResponseBody
	@PostMapping("/add-activity-sector")
	public ActivitySector addActivitySector(@RequestBody ActivitySectorModel activitySectorModel) {
		return activitySectorServiceInt.addActivitySector(activitySectorModel);
	}

	@ResponseBody
	@GetMapping("/get-all-activitiy-sectors")
	public List<ActivitySector> getAllActivitySectors() {
		return activitySectorServiceInt.getAllActivitySectors();
	}

	@ResponseBody
	@GetMapping("/get-activity-sector/{activity-sector-id}")
	public ActivitySector getActivitySector(@PathVariable("activity-sector-id") Long activitySectorId) {
		return activitySectorServiceInt.getActivitySector(activitySectorId);
	}

	@ResponseBody
	@PutMapping("/update-activity-sector")
	public ActivitySector updateActivitySector(@RequestBody ActivitySectorModel activitySectorModel) {
		return activitySectorServiceInt.updateActivitySector(activitySectorModel);
	}

	@ResponseBody
	@DeleteMapping("/delete-activity-sector/{activity-sector-id}")
	public void deleteActivitySector(@PathVariable("activity-sector-id") Long activitySectorId) {
		activitySectorServiceInt.deleteActivitySector(activitySectorId);
	}

}
