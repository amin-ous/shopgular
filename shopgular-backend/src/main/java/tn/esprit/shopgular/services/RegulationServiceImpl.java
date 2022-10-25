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
public class RegulationServiceImpl implements RegulationServiceInt {

	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	RegulationRepository regulationRepository;

	@Override
	public Regulation addRegulation(RegulationModel regulationModel) {
		Regulation regulation = new Regulation(regulationModel.getAmountPaid(), regulationModel.getAmountRemaining(), new Date(), regulationModel.getAmountRemaining() == 0);
		regulationRepository.save(regulation);
		return regulation;
	}

	@Override
	public List<Regulation> getAllRegulations() {
		return regulationRepository.findAll();
	}

	@Override
	public Regulation getRegulation(Long regulationId) {
		return regulationRepository.findById(regulationId).orElse(null);
	}

	@Override
	public List<Regulation> getInvoiceRegulations(Long invoiceId) {
		Invoice invoice = invoiceRepository.getById(invoiceId);
		ArrayList<Regulation> regulations = new ArrayList<>();
		for (Regulation regulation: invoice.getRegulations()) {
			regulations.add(regulation);
		}
		return regulations;
	}

	@Override
	public double getPeriodicIncomes(Date startDate, Date endDate) {
		return regulationRepository.getPeriodicIncomes(startDate, endDate);
	}

	@Override
	public Regulation updateRegulation(RegulationModel regulationModel) {
		Regulation regulation = getRegulation(regulationModel.getId());
		regulation.setAmountPaid(Optional.ofNullable(regulationModel.getAmountPaid()).orElse(regulation.getAmountPaid()));
		regulation.setAmountRemaining(Optional.ofNullable(regulationModel.getAmountRemaining()).orElse(regulation.getAmountRemaining()));
		regulation.setPaid(regulationModel.getAmountRemaining() == 0);
		regulationRepository.save(regulation);
		return regulation;
	}

	@Override
	public void deleteRegulation(Long regulationId) {
		regulationRepository.deleteById(regulationId);
	}

}
