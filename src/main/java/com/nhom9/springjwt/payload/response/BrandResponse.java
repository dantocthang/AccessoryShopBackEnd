package com.nhom9.springjwt.payload.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.nhom9.springjwt.models.Brand;

public class BrandResponse {
	@Autowired Brand brand;
	public Errors errors;
	public String message;
	
	
	public BrandResponse(Brand brand, Errors errors, String message) {
		super();
		this.brand = brand;
		this.errors = errors;
		this.message = message;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
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
