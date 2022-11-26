package com.nhom9.springjwt.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Brand {
 

    // @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// private Long id;

	@NotBlank(message = "Name is required")
	private String name;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "brand", orphanRemoval = true)
	private List<Product> products = new ArrayList<>();


    public String getName() {
        return name;
    }

    public Brand(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Brand( @NotBlank(message = "Name is required") String name) {
//        this.id = id;
        this.name = name;
//        this.products = products;
    }


}
