package tn.esprit.shopgular.repositories;

import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	@Query("SELECT SUM(i.netPrice) FROM Invoice i WHERE i.creationDate BETWEEN :startDate AND :endDate AND i.archived = false")
	Double getPeriodicSalesValue(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
