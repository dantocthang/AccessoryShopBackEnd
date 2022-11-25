package com.nhom9.springjwt.payload.request;

import javax.validation.constraints.NotNull;

public class PaymentRequest {
	
	@NotNull
	private Long user_id;
	
	@NotNull
	private String paymentMethod;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
}
