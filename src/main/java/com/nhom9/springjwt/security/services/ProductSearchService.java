package com.nhom9.springjwt.security.services;

import java.util.List;


import org.springframework.stereotype.Component;

import com.nhom9.springjwt.models.Product;

@Component
public interface ProductSearchService {
	List<Product> searchProductByName(String text); 
}
