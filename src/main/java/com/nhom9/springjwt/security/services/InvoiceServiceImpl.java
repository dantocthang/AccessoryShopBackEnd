package com.nhom9.springjwt.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import com.nhom9.springjwt.models.Address;
import com.nhom9.springjwt.models.CartItem;
import com.nhom9.springjwt.models.Invoice;
import com.nhom9.springjwt.models.User;
import com.nhom9.springjwt.payload.request.InvoiceRequest;
import com.nhom9.springjwt.payload.request.PaymentRequest;
import com.nhom9.springjwt.repository.AddressRepository;
import com.nhom9.springjwt.repository.CartItemRepository;
import com.nhom9.springjwt.repository.InvoiceRepository;
import com.nhom9.springjwt.repository.UserRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	CartItemService cartItemService;
	@Autowired
	CartItemRepository cartItemReponsitory;

	double totalPrice = 0;

	@Override
	public Invoice creatInvoice(InvoiceRequest invoiceRequest) {

		User user = userRepository.findById(invoiceRequest.getUser_id()).orElseThrow();
		Address address = addressRepository.findById(invoiceRequest.getAddress_id()).orElseThrow();
		List<CartItem> cartItems = cartItemService.getCart(invoiceRequest.getUser_id());

		for (CartItem cartItem : cartItems) {
			totalPrice += cartItem.getQuantity() * cartItem.getProduct().getPrice();
		}
		
		Invoice invoice = new Invoice(user, "", "", totalPrice, false, cartItems, address);
		//timeCreate & paymentMethod gán "" vì chưa thanh toán thành công nên chưa thể tạo.
		
		invoiceRepository.save(invoice);
		totalPrice = 0;

		for (CartItem cartItem : cartItems) {
			cartItem.setInvoice(invoice);
			cartItemReponsitory.save(cartItem);
		}

		return invoice;
	}

	@Override
	public Invoice getInvoice(long invoiceId) {
		return invoiceRepository.findById(invoiceId).orElseThrow();
	}

	@Override
	public List<Invoice> getAllInvoices() {
		return invoiceRepository.findAll();
	}

	@Override
	public List<Invoice> getAllUserInvoices(long userId) {
		return invoiceRepository.findByUser_Id(userId);
	}
	
	@Override
	public List<Invoice> getAllInvoicesPaySuccessByUser(long userId) {
		List<Invoice> list = invoiceRepository.findByUser_Id(userId);
		List<Invoice> list2 = new ArrayList<>();
		
		for (Invoice invoice : list) {
			if(invoice.isWasPay())
				list2.add(invoice);
		}
		
		return list2;
	}

	@Override
	public void deleteInvoiceById(Long invoiceId) {
		// TODO Auto-generated method stub
		if (invoiceRepository.findById(invoiceId).get().getInvoiceId().equals(invoiceId)) {
			invoiceRepository.deleteById(invoiceId);
		} else
			throw new InvalidConfigurationPropertyValueException("invoiceId", invoiceId, "Not Found");
	}

	@Override
	public Optional<Invoice> updateProductsInInvoice(Invoice invoice, long cartItemsId) {
		List<CartItem> listCartItem = cartItemService.getCart(cartItemsId);

		for (CartItem cartItem : listCartItem) {
			cartItem.setInvoice(invoice);
			totalPrice += cartItem.getQuantity() * cartItem.getProduct().getPrice();
			cartItemReponsitory.save(cartItem);
		}
		invoice.setTotalPrice(totalPrice);
		totalPrice = 0;
		return Optional.of(invoice);
	}

	@Override
	public Optional<Invoice> setPaymentSuccess(Invoice invoice, String paymentMethod, Long userId) {
		List<CartItem> listCartItem = cartItemService.getCart(userId);
		
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		int hour = now.getHour();
		int minute = now.getMinute();
		String timeString = day + "-" + month + "-" + year + " " + hour + ":" + minute;
		
		invoice.setPaymentMethod(paymentMethod);
		invoice.setWasPay(true);
		invoice.setTimeCreate(timeString);
		invoiceRepository.save(invoice);
		
		for (CartItem cartItem : listCartItem) {
			cartItem.setStatus(1);
			cartItemReponsitory.save(cartItem);
		}
		
		return Optional.of(invoice);
	}
}
