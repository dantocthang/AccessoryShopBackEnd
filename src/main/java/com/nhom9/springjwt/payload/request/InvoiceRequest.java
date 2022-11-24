package com.nhom9.springjwt.payload.request;

import javax.validation.constraints.NotNull;

public class InvoiceRequest {
	@NotNull
	private Long user_id;
	
	@NotNull
	private Long cart_id;
	
	@NotNull
	private Long address_id;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getCart_id() {
		return cart_id;
	}

	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
	}

	public Long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}
}
