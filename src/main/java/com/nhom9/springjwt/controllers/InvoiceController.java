package com.nhom9.springjwt.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.nhom9.springjwt.models.Invoice;
import com.nhom9.springjwt.payload.request.InvoiceRequest;
import com.nhom9.springjwt.payload.request.PaymentRequest;
import com.nhom9.springjwt.payload.response.MessageResponse;
import com.nhom9.springjwt.repository.AddressRepository;
import com.nhom9.springjwt.repository.InvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhom9.springjwt.security.services.InvoiceService;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	AddressRepository addressRepository;

	@PostMapping(value = "/create", consumes = { "*/*" })
	public ResponseEntity<Invoice> creatInvoice(@Valid @RequestBody InvoiceRequest invoiceRequest) {
		return new ResponseEntity<>(invoiceService.creatInvoice(invoiceRequest), HttpStatus.CREATED);
	}

//	Lấy ra toàn bộ hóa đơn để Admin xem
	@GetMapping("/getAllInvoices")
//	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Invoice>> getAllInvoices() {
		try {
			List<Invoice> lstInvoices = invoiceService.getAllInvoices();
			return new ResponseEntity<>(lstInvoices, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	Lấy ra toàn bộ hóa đơn của người dùng A đã thanh toán thành công
	@GetMapping("/getAllInvoicesPaySuccessByUser/{userId}")
//	@PreAuthorize("hasRole('USER'))
	public ResponseEntity<List<Invoice>> getInvoicesByUser(@PathVariable("userId") Long userId) {
		try {
			List<Invoice> lstInvoices = invoiceService.getAllInvoicesPaySuccessByUser(userId);
			return new ResponseEntity<>(lstInvoices, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	Lấy ra chỉ 1 hóa đơn của người dùng A (khi người dùng bấm từ giỏ hàng vào thanh toán hoặc khi bấm vào xem chi tiết 1 hóa đơn đã thành công nào đó)
	@GetMapping("/getInvoice/{userId}/{invoiceId}")
	public ResponseEntity<Invoice> getInvoice(@PathVariable("userId") Long userId,
			@PathVariable("invoiceId") Long invoiceId) {
		try {
			boolean check = false;
			List<Invoice> listInvoices = invoiceService.getAllUserInvoices(userId);
			Invoice invoice = new Invoice(); // invoiceService.getInvoice(invoiceId);

			for (Invoice invoice0 : listInvoices) {
				if (invoiceId == invoice0.getInvoiceId()) {
					invoice = invoice0;
					check = true;
				}
			}
			if (check)
				return new ResponseEntity<>(invoice, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	Chỉ MODERATOR hoặc ADMIN mới có quyền xóa
	@DeleteMapping("/deleteInvoice/{id}")
//	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> deleteCartItem(@PathVariable("id") Long invoiceId) {
		try {
			invoiceService.deleteInvoiceById(invoiceId);
			return ResponseEntity.ok(new MessageResponse("Delete Invoice " + invoiceId + " success!"));
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	Cập nhật lại các sản phẩm khi đã chỉnh, thêm, xóa trong giỏ hàng
	@PutMapping(value = "/updateInvoice/{invoiceId}", consumes = { "*/*" })
	public ResponseEntity<Optional<Invoice>> updateProductsInInvoice(@PathVariable("invoiceId") Long invoiceId,
			@Valid @RequestBody InvoiceRequest invoiceRequest) {

		Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow();
		if (invoice.isWasPay() == false)
			return new ResponseEntity<>(invoiceService.updateProductsInInvoice(invoice, invoiceRequest.getCartItems_id()), HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	Sau khi đã chọn phương thức thanh toán => setPaySuccess để gán hóa đơn này đã thanh toán thành công + xóa các sản phẩm trong giỏ hàng hiện tại
	@PutMapping(value = "/setPaySuccess/{invoiceId}", consumes = { "*/*" })
//	@PreAuthorize("hasRole('USER'))
	public ResponseEntity<?> setPaySuccess(@PathVariable("invoiceId") Long invoiceId,
			@Valid @RequestBody PaymentRequest paymentRequest) {
		Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow();
		try {
			invoiceService.setPaymentSuccess(invoice, paymentRequest);
			return ResponseEntity.ok(new MessageResponse("Thanh toán thành công hóa đơn " + invoiceId + "!"));
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
