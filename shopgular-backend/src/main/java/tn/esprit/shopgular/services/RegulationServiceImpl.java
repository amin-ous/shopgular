package tn.esprit.shopgular.services;

import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.repositories.*;

@Service
@Transactional
public class RegulationServiceImpl implements RegulationServiceInt {

	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	RegulationRepository regulationRepository;

	@Override
	public Regulation addRegulation(Regulation regulation) {
		regulation.setCreationDate(new Date());
		regulation.setPaid(regulation.getAmountRemaining() == 0);
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
	public Regulation updateRegulation(Regulation regulation) {
		Regulation targetedRegulation = getRegulation(regulation.getId());
		targetedRegulation.setAmountPaid(Optional.ofNullable(regulation.getAmountPaid()).orElse(targetedRegulation.getAmountPaid()));
		targetedRegulation.setAmountRemaining(Optional.ofNullable(regulation.getAmountRemaining()).orElse(targetedRegulation.getAmountRemaining()));
		targetedRegulation.setPaid(regulation.getAmountRemaining() == 0);
		regulationRepository.save(targetedRegulation);
		return targetedRegulation;
	}

	@Override
	public void deleteRegulation(Long regulationId) {
		regulationRepository.deleteById(regulationId);
	}

}
