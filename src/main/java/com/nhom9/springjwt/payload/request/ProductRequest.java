package com.nhom9.springjwt.payload.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductRequest {
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

	@NotNull
	private Long category_id;

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

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

}
