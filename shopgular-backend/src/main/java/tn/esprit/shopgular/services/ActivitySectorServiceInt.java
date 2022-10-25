package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;

public interface ActivitySectorServiceInt {

	ActivitySector addActivitySector(ActivitySectorModel activitySectorModel);

	List<ActivitySector> getAllActivitySectors();

	ActivitySector getActivitySector(Long activitySectorId);

	ActivitySector updateActivitySector(ActivitySectorModel activitySectorModel);

	void deleteActivitySector(Long activitySectorId);

}
