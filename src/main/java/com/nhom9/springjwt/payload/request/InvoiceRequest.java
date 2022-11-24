package com.nhom9.springjwt.payload.request;

import javax.validation.constraints.NotNull;

public class InvoiceRequest {
	@NotNull
	private Long user_id;
	
	@NotNull
	private Long cartItems_id;
	
	@NotNull
	private Long address_id;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getCartItems_id() {
		return cartItems_id;
	}

	public void setCartItems_id(Long cartItems_id) {
		this.cartItems_id = cartItems_id;
	}

	public Long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}
}
