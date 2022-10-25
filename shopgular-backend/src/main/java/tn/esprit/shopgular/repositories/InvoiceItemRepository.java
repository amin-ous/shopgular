package tn.esprit.shopgular.repositories;

import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

	@Query("SELECT SUM(ii.salePrice) FROM InvoiceItem ii WHERE ii.product.id = :productId AND ii.invoice.creationDate BETWEEN :startDate and :endDate AND ii.invoice.archived = true")
	Double getPeriodicProductRevenue(@Param("productId") Long productId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
