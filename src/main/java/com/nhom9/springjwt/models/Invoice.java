package com.nhom9.springjwt.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Invoice {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoiceId;
	
	@JoinColumn(name = "user_id")
	@ManyToOne()
	private User user;
	
	@NotNull
	private String timeCreate;
	
	@NotNull
	private String paymentMethod;
	
	@NotNull
	@Min(0)
	private double totalPrice;
	
	@NotNull
	private boolean wasPay;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice", orphanRemoval = true)
	private List<CartItem> cartItems = new ArrayList<>();
	
	@JoinColumn(name = "address_id")
	@ManyToOne
	private Address address;

	public Invoice() {
	}
	
	public Invoice(User user, @NotNull String timeCreate, @NotNull String paymentMethod,
			@NotNull @Min(0) double totalPrice, @NotNull boolean wasPay, List<CartItem> cartItems, Address address) {
		super();
		this.user = user;
		this.timeCreate = timeCreate;
		this.paymentMethod = paymentMethod;
		this.totalPrice = totalPrice;
		this.wasPay = wasPay;
		this.cartItems = cartItems;
		this.address = address;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(String timeCreate) {
		this.timeCreate = timeCreate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isWasPay() {
		return wasPay;
	}

	public void setWasPay(boolean wasPay) {
		this.wasPay = wasPay;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
