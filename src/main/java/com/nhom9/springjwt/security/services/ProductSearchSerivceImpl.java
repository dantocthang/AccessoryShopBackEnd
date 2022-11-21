package com.nhom9.springjwt.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom9.springjwt.models.Product;
import com.nhom9.springjwt.repository.ProductRepository;

@Service
public class ProductSearchSerivceImpl implements ProductSearchService{
	@Autowired
	ProductRepository productRepo;
	
	@Override
	public List<Product> searchProductByName(String text) {
		// TODO Auto-generated method stub
		
		return productRepo.findByNameLike(text);
	}
}
