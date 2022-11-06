package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;

public interface ActivitySectorServiceInt {

	ActivitySector addActivitySector(ActivitySector activitySector);

	List<ActivitySector> getAllActivitySectors();

	ActivitySector getActivitySector(Long activitySectorId);

	ActivitySector updateActivitySector(ActivitySector activitySector);

	void deleteActivitySector(Long activitySectorId);

}
