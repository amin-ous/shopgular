package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;

public interface RegulationServiceInt {

	Regulation addRegulation(Regulation regulation);

	List<Regulation> getAllRegulations();

	Regulation getRegulation(Long regulationId);

	List<Regulation> getInvoiceRegulations(Long invoiceId);

	double getPeriodicIncomes(Date startDate, Date endDate);

	Regulation updateRegulation(Regulation regulation);

	void deleteRegulation(Long regulationId);

}
