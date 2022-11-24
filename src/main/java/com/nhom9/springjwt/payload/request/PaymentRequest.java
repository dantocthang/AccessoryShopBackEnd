package com.nhom9.springjwt.payload.request;

import javax.validation.constraints.NotNull;

public class PaymentRequest {
	
	@NotNull
	private Long cartItems_id;
	
	@NotNull
	private String paymentMethod;

	public Long getCartItems_id() {
		return cartItems_id;
	}

	public void setCartItems_id(Long cartItems_id) {
		this.cartItems_id = cartItems_id;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
}
