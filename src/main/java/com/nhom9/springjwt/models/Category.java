package com.nhom9.springjwt.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Name is required")
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category", orphanRemoval = true)
	private List<Product> products = new ArrayList<>();
	
	public Category() {
		
	}

	public Category(Long id, @NotBlank(message = "Name is required") String name, List<Product> producst) {
		super();
		this.id = id;
		this.name = name;
		this.products = producst;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducst() {
		return products;
	}

	public void setProducst(List<Product> producst) {
		this.products = producst;
	}
	
	
}
