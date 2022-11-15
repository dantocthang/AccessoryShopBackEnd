package com.nhom9.springjwt.payload.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.nhom9.springjwt.models.Product;

public class ProductResponse {
	@Autowired Product product;
	public Errors errors;
	public String message;
	
	
	public ProductResponse(Product product, Errors errors, String message) {
		super();
		this.product = product;
		this.errors = errors;
		this.message = message;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Errors getErrors() {
		return errors;
	}
	public void setErrors(Errors errors) {
		this.errors = errors;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
