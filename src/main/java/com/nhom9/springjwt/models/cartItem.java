package com.nhom9.springjwt.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class cartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@JsonIgnore
	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;
	//@JsonIgnore
	@JoinColumn(name = "product_id")
	@ManyToOne
	private Product product;
	
	@NotNull
	@Min(0)
	private int quantity;
	
	public cartItem()
	{
		super();
	}

	public cartItem(User user, Product product, @NotNull @Min(0) int quantity) {
		super();
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
}
