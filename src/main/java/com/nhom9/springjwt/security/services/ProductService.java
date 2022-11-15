package com.nhom9.springjwt.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.nhom9.springjwt.models.Product;
import com.nhom9.springjwt.payload.request.ProductRequest;
import com.nhom9.springjwt.payload.response.MessageResponse;
import com.nhom9.springjwt.payload.response.ProductResponse;

@Component
public interface ProductService {
	ProductResponse createProduct(ProductRequest productRequest);

    Optional<Product> updateProduct(Long productId, ProductRequest productRequest);
	

    void deleteProduct(Long productId);
	

    Product getASingleProduct(Long productId);
	

    List<Product> getAllProducts();
}
