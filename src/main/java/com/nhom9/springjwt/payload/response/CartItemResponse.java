package com.nhom9.springjwt.payload.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.nhom9.springjwt.models.Product;
import com.nhom9.springjwt.models.CartItem;

public class CartItemResponse {
	@Autowired
	CartItem items;
	public Errors errors;
	public String message;

	public CartItemResponse(CartItem items, Errors errors, String message) {
		super();
		this.items = items;
		this.errors = errors;
		this.message = message;
	}

	public CartItem getItems() {
		return items;
	}

	public void setItems(CartItem items) {
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


}
