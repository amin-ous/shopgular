package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;

public interface InvoiceServiceInt {

	Invoice addInvoice(Invoice invoice);

	List<Invoice> getAllInvoices();

	Invoice getInvoice(Long invoiceId);

	List<InvoiceItem> getInvoiceItems(Long invoiceId);

	double getPeriodicSalesValue(Date startDate, Date endDate);

	double getPeriodicPaymentsCollectionPercentage(Date startDate, Date endDate);

	void assignOperatorToInvoice(Long operatorId, Long invoiceId);

	Invoice cancelInvoice(Long invoiceId);

	void deleteInvoice(Long invoiceId);

}
