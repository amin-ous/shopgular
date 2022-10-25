package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;

public interface RegulationServiceInt {

	Regulation addRegulation(RegulationModel regulationModel);

	List<Regulation> getAllRegulations();

	Regulation getRegulation(Long regulationId);

	List<Regulation> getInvoiceRegulations(Long invoiceId);

	double getPeriodicIncomes(Date startDate, Date endDate);

	Regulation updateRegulation(RegulationModel regulationModel);

	void deleteRegulation(Long regulationId);

}
