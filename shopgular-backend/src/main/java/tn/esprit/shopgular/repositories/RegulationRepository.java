package tn.esprit.shopgular.repositories;

import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;

@Repository
public interface RegulationRepository extends JpaRepository<Regulation, Long> {

	@Query("SELECT SUM(r.amountPaid) FROM Regulation r WHERE r.creationDate BETWEEN :startDate AND :endDate AND r.invoice.archived = false")
	Double getPeriodicIncomes(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
