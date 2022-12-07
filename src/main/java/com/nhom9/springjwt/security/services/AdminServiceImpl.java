package com.nhom9.springjwt.security.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nhom9.springjwt.payload.response.DashbroadResponse;
import com.nhom9.springjwt.repository.InvoiceRepository;
import com.nhom9.springjwt.repository.ProductRepository;
import com.nhom9.springjwt.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	ProductRepository productRepo;
	@Autowired
	InvoiceRepository invoiceRepo;
	@Override
	public DashbroadResponse getDashBroad() {
		long totalUser = userRepo.count();
		long totalProduct = productRepo.count();
		long totalInvoice = invoiceRepo.count();
		double totalTurnover = invoiceRepo.totalTurnover();

		
		return new DashbroadResponse(totalUser, totalProduct, totalInvoice, totalTurnover);
	}
}
