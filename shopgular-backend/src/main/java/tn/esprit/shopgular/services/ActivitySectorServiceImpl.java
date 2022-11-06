package tn.esprit.shopgular.services;

import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.repositories.*;

@Service
@Transactional
public class ActivitySectorServiceImpl implements ActivitySectorServiceInt {

	@Autowired
	ActivitySectorRepository activitySectorRepository;

	@Override
	public ActivitySector addActivitySector(ActivitySector activitySector) {
		activitySectorRepository.save(activitySector);
		return activitySector;
	}

	@Override
	public List<ActivitySector> getAllActivitySectors() {
		return activitySectorRepository.findAll();
	}

	@Override
	public ActivitySector getActivitySector(Long activitySectorId) {
		return activitySectorRepository.findById(activitySectorId).orElse(null);
	}

	@Override
	public ActivitySector updateActivitySector(ActivitySector activitySector) {
		ActivitySector targetedActivitySector = getActivitySector(activitySector.getId());
		targetedActivitySector.setCode(Optional.ofNullable(activitySector.getCode()).orElse(targetedActivitySector.getCode()));
		targetedActivitySector.setWording(Optional.ofNullable(activitySector.getWording()).orElse(targetedActivitySector.getWording()));
		activitySectorRepository.save(targetedActivitySector);
		return targetedActivitySector;
	}

	@Override
	public void deleteActivitySector(Long activitySectorId) {
		activitySectorRepository.deleteById(activitySectorId);
	}

}
