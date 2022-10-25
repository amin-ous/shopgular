package tn.esprit.shopgular.services;

import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.repositories.*;

@Service
@Transactional
public class ActivitySectorServiceImpl implements ActivitySectorServiceInt {

	@Autowired
	ActivitySectorRepository activitySectorRepository;

	@Override
	public ActivitySector addActivitySector(ActivitySectorModel activitySectorModel) {
		ActivitySector activitySector = new ActivitySector(activitySectorModel.getCode(), activitySectorModel.getWording());
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
	public ActivitySector updateActivitySector(ActivitySectorModel activitySectorModel) {
		ActivitySector activitySector = getActivitySector(activitySectorModel.getId());
		activitySector.setCode(Optional.ofNullable(activitySectorModel.getCode()).orElse(activitySector.getCode()));
		activitySector.setWording(Optional.ofNullable(activitySectorModel.getWording()).orElse(activitySector.getWording()));
		activitySectorRepository.save(activitySector);
		return activitySector;
	}

	@Override
	public void deleteActivitySector(Long activitySectorId) {
		activitySectorRepository.deleteById(activitySectorId);
	}

}
