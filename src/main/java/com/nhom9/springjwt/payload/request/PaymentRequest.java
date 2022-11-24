package com.nhom9.springjwt.payload.request;

import javax.validation.constraints.NotNull;

public class PaymentRequest {
	
	@NotNull
	private Long cart_id;
	
	@NotNull
	private String paymentMethod;

	public Long getCart_id() {
		return cart_id;
	}

	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
}
