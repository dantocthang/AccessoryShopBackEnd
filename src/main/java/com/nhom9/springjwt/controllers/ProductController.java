package com.nhom9.springjwt.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhom9.springjwt.models.Category;
import com.nhom9.springjwt.models.Product;
import com.nhom9.springjwt.payload.request.ProductRequest;
import com.nhom9.springjwt.payload.response.MessageResponse;
import com.nhom9.springjwt.repository.CategoryRepository;
import com.nhom9.springjwt.repository.ProductRepository;
import com.nhom9.springjwt.security.services.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping("/")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String title) {
		try {
			List<Product> products = productService.getAllProducts();
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
		Product product = productService.getASingleProduct(id);
		return new ResponseEntity<>(product, HttpStatus.OK);

	}

	@PostMapping(value = "/create", consumes = { "*/*" })
	public ResponseEntity<MessageResponse> createProduct(@RequestBody ProductRequest product, Errors errors) {
		try {
			if (errors.hasErrors()) {
				return new ResponseEntity<>(new MessageResponse("Has errors"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			productService.createProduct(product);
			
			return new ResponseEntity<>(new MessageResponse("New Product created successfully"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/{id}", consumes = { "*/*" })
	public ResponseEntity<MessageResponse> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest product){
		try {
			Optional<Product> updatedProduct = productService.updateProduct(id, product);
			return new ResponseEntity<>(new MessageResponse("New Employee updated successfully"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MessageResponse> deleteProduct(@PathVariable("id") Long id){
		try {
			productService.deleteProduct(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
