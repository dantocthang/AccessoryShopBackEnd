package com.nhom9.springjwt.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Description is required")
	private String description;

	@NotNull
	@Min(0)
	private int stock;

	@NotNull
	@Min(0)
	private int price;

	@NotNull
	@Min(1000)
	@Max(3000)
	private int modelYear;

	@NotBlank(message = "Image is required")
	private String imageUrl;

	@NotBlank(message = "Image is required")
	private String imagePublicId;

	// @JsonIgnore
	@JoinColumn(name = "category_id")
	@ManyToOne
	private Category category;

	// @JsonIgnore
	@JoinColumn(name = "brand_id")
	@ManyToOne
	private Brand brand;

	public Product() {

	}

	public Product(@NotBlank(message = "Name is required") String name,
			@NotBlank(message = "Description is required") String description, @NotNull @Min(0) int stock,
			@NotNull @Min(0) int price, @NotNull @Min(1000) @Max(3000) int modelYear,
			@NotBlank(message = "Image is required") String imageUrl,
			@NotBlank(message = "Image is required") String imagePublicId, Category category, Brand brand) {
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.price = price;
		this.modelYear = modelYear;
		this.imageUrl = imageUrl;
		this.imagePublicId = imagePublicId;
		this.category = category;
		this.brand = brand;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImagePublicId() {
		return imagePublicId;
	}

	public void setImagePublicId(String imagePublicId) {
		this.imagePublicId = imagePublicId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + stock;
		result = prime * result + price;
		result = prime * result + modelYear;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((imagePublicId == null) ? 0 : imagePublicId.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (stock != other.stock)
			return false;
		if (price != other.price)
			return false;
		if (modelYear != other.modelYear)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (imagePublicId == null) {
			if (other.imagePublicId != null)
				return false;
		} else if (!imagePublicId.equals(other.imagePublicId))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		return true;
	}

}
