package com.nhom9.springjwt.payload.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.nhom9.springjwt.models.Product;
import com.nhom9.springjwt.models.cartItem;

public class carItemResponse {
	@Autowired cartItem items;
	@Autowired Product products;
	public Errors errors;
	public String message;
	
	public carItemResponse(cartItem items,Product products, Errors errors, String message) {
		super();
		this.items = items;
		this.products = products;
		this.errors = errors;
		this.message = message;
	}

	public cartItem getItems() {
		return items;
	}

	public void setItems(cartItem items) {
		this.items = items;
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

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	
	
}