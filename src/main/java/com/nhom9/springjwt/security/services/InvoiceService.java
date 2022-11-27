package com.nhom9.springjwt.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import com.nhom9.springjwt.models.Invoice;
import com.nhom9.springjwt.payload.request.InvoiceRequest;
import com.nhom9.springjwt.payload.request.PaymentRequest;

@Component
public interface InvoiceService {
	Invoice getInvoice(long invoiceId);

	List<Invoice> getAllInvoices();

	List<Invoice> getAllUserInvoices(long userId);
	
	List<Invoice> getAllInvoicesPaySuccessByUser(long userId);

	Invoice creatInvoice(InvoiceRequest invoiceRequest);

	void deleteInvoiceById(Long invoiceId);

	Optional<Invoice> updateProductsInInvoice(Invoice invoice, long cartItemsId);

	Optional<Invoice> setPaymentSuccess(Invoice invoice, String paymentMethod, Long userId);
}
