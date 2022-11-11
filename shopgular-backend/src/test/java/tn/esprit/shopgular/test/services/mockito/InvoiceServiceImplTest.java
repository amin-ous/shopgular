package tn.esprit.shopgular.test.services.mockito;

import java.io.*;
import java.util.*;
import lombok.extern.slf4j.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;
import org.springframework.boot.test.context.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.repositories.*;
import tn.esprit.shopgular.services.*;

@Slf4j
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@TestMethodOrder(OrderAnnotation.class)
class InvoiceServiceImplTest {

	@Mock
	InvoiceRepository invoiceRepository;
	@InjectMocks
	InvoiceServiceImpl invoiceServiceImpl;

	Invoice invoice = Invoice.builder().items(new HashSet<>()).build();

	List<Invoice> invoices = new ArrayList<Invoice>(){};

	@Test
	@Order(1)
	void testAddInvoice() throws IOException {
		Mockito.when(invoiceRepository.save(invoice)).thenReturn(invoice);
	    Invoice s = invoiceServiceImpl.addInvoice(invoice);
	    Assertions.assertNotNull(s);
		log.info("Invoice has been successfully added");
	}

	@Test
	@Order(2)
	void testGetAllInvoices() {
		Mockito.when(invoiceRepository.findAll()).thenReturn(invoices);
		List<Invoice> s = invoiceServiceImpl.getAllInvoices();
		Assertions.assertNotNull(s);
		log.info("Invoices have been successfully retrieved");
	}

	@Test
	@Order(3)
	void testDeleteInvoice() {
		invoiceServiceImpl.deleteInvoice(invoice.getId());
		Mockito.verify(invoiceRepository).deleteById(invoice.getId());
		log.info("Invoice has been successfully deleted");
	}

}
