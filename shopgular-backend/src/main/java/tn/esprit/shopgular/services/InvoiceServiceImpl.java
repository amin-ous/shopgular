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
	public Invoice addInvoice(InvoiceModel invoiceModel) {
		Double netPrice = 0.000;
		Double deduction = 0.000;
		Set<InvoiceItemModel> invoiceItemsModels = invoiceModel.getItems();
		Set<InvoiceItem> invoiceItems = new HashSet<>();
		for (InvoiceItemModel invoiceItemModel : invoiceItemsModels) {
			Product product = productRepository.getById(invoiceItemModel.getProduct().getId());
			Double listPrice = invoiceItemModel.getQuantity() * product.getListPrice();
			Double discount = (listPrice * invoiceItemModel.getPercentDecrease()) / 100;
			Double salePrice = listPrice - discount;
			InvoiceItem invoiceItem = new InvoiceItem(salePrice, discount,
			invoiceItemModel.getPercentDecrease(), invoiceItemModel.getQuantity(), invoiceItemModel.getProduct());
			deduction = deduction + discount;
			netPrice = netPrice + salePrice;
			invoiceItems.add(invoiceItem);
			invoiceItemRepository.save(invoiceItem);
		}
		Invoice invoice = new Invoice(netPrice, deduction, new Date(), new Date(), false, invoiceItems);
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
		Invoice invoice = getInvoice(invoiceId);
		invoice.setLastModificationDate(new Date());
		invoice.setArchived(true);
		invoiceRepository.save(invoice);
		return invoice;
	}

	@Override
	public void deleteInvoice(Long invoiceId) {
		invoiceRepository.deleteById(invoiceId);
	}

}
