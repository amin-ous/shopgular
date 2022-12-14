package tn.esprit.shopgular.services;

import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.repositories.*;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceServiceInt {

	@Autowired
	InvoiceItemRepository invoiceItemRepository;
	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	OperatorRepository operatorRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	RegulationRepository regulationRepository;

	@Override
	public Invoice addInvoice(Invoice invoice) {
		Double netPrice = 0.000;
		Double deduction = 0.000;
		Set<InvoiceItem> invoiceItems = invoice.getItems();
		for (InvoiceItem invoiceItem : invoiceItems) {
			Product product = productRepository.getById(invoiceItem.getProduct().getId());
			Double listPrice = invoiceItem.getQuantity() * product.getListPrice();
			Double discount = (listPrice * invoiceItem.getPercentDecrease()) / 100;
			invoiceItem.setDiscount(discount);
			deduction = deduction + discount;
			Double salePrice = listPrice - discount;
			invoiceItem.setSalePrice(salePrice);
			netPrice = netPrice + salePrice;
			invoiceItemRepository.save(invoiceItem);
		}
		invoice.setNetPrice(netPrice);
		invoice.setDeduction(deduction);
		invoice.setCreationDate(new Date());
		invoice.setLastModificationDate(new Date());
		invoice.setArchived(false);
		invoice.setItems(invoiceItems);
		invoiceRepository.save(invoice);
		return invoice;
	}

	@Override
	public List<Invoice> getAllInvoices() {
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice getInvoice(Long invoiceId) {
		return invoiceRepository.findById(invoiceId).orElse(null);
	}

	@Override
	public List<InvoiceItem> getInvoiceItems(Long invoiceId) {
		Invoice invoice = getInvoice(invoiceId);
		ArrayList<InvoiceItem> invoiceItems = new ArrayList<>();
		for (InvoiceItem invoiceItem: invoice.getItems()) {
			invoiceItems.add(invoiceItem);
		}
		return invoiceItems;
	}

	@Override
	public double getPeriodicSalesValue(Date startDate, Date endDate) {
		return invoiceRepository.getPeriodicSalesValue(startDate, endDate);
	}

	@Override
	public double getPeriodicPaymentsCollectionPercentage(Date startDate, Date endDate) {
		Double incomes = regulationRepository.getPeriodicIncomes(startDate, endDate);
		Double salesValue = invoiceRepository.getPeriodicSalesValue(startDate, endDate);
		return (incomes / salesValue) * 100;
	}

	@Override
	public void assignOperatorToInvoice(Long operatorId, Long invoiceId) {
		Operator operator = operatorRepository.getById(operatorId);
		Invoice invoice = getInvoice(invoiceId);
		operator.getInvoices().add(invoice);
		operatorRepository.save(operator);
	}

	@Override
	public Invoice cancelInvoice(Long invoiceId) {
		Invoice targetedActivitySector = getInvoice(invoiceId);
		targetedActivitySector.setLastModificationDate(new Date());
		targetedActivitySector.setArchived(true);
		invoiceRepository.save(targetedActivitySector);
		return targetedActivitySector;
	}

	@Override
	public void deleteInvoice(Long invoiceId) {
		invoiceRepository.deleteById(invoiceId);
	}

}
