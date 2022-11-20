package com.nhom9.springjwt.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nhom9.springjwt.models.Brand;
import com.nhom9.springjwt.models.Category;
import com.nhom9.springjwt.models.Product;
import com.nhom9.springjwt.payload.request.ProductRequest;
import com.nhom9.springjwt.payload.response.MessageResponse;
import com.nhom9.springjwt.payload.response.ProductResponse;
import com.nhom9.springjwt.repository.BrandRepository;
import com.nhom9.springjwt.repository.CategoryRepository;
import com.nhom9.springjwt.repository.ProductRepository;
import org.springframework.data.domain.Sort;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	BrandRepository brandRepo;
	@Autowired
	ProductRepository productRepo;

	@Override
	public Product createProduct(ProductRequest productRequest) {
		Category category = categoryRepo.findById(productRequest.getCategory_id()).orElseThrow();
		Brand brand = brandRepo.findById(productRequest.getBrand_id()).orElseThrow();
		Product product = new Product(productRequest.getName(), productRequest.getDescription(),
				productRequest.getStock(), productRequest.getPrice(), productRequest.getModelYear(),
				productRequest.getImageUrl(), productRequest.getImagePublicId(), category, brand);
		return productRepo.save(product);
	}

	@Override
	public Optional<Product> updateProduct(Long productId, ProductRequest productRequest) {
		// TODO Auto-generated method stub
		Optional<Product> product = productRepo.findById(productId);
		if (product.isPresent()) {
			Category category = categoryRepo.findById(productRequest.getCategory_id()).orElseThrow();
			Brand brand = brandRepo.findById(productRequest.getBrand_id()).orElseThrow();
			product.get().setName(productRequest.getName());
			product.get().setDescription(productRequest.getDescription());
			product.get().setPrice(productRequest.getPrice());
			product.get().setStock(productRequest.getStock());
			product.get().setModelYear(productRequest.getModelYear());
			product.get().setImageUrl(productRequest.getImageUrl());
			product.get().setImagePublicId(productRequest.getImagePublicId());
			product.get().setCategory(category);
			product.get().setBrand(brand);
			productRepo.save(product.get());
			return product;
		} else {
			throw new InvalidConfigurationPropertyValueException("productId", productId, "Not found");
		}

	}

	@Override
	public void deleteProduct(Long productId) {
		// TODO Auto-generated method stub
		if (productRepo.findById(productId).get().getId().equals(productId)) {
			productRepo.deleteById(productId);
		} else
			throw new InvalidConfigurationPropertyValueException("productId", productId, "Not found");
	}

	@Override
	public Product getASingleProduct(Long productId) {
		// TODO Auto-generated method stub
		return productRepo.findById(productId)
				.orElseThrow(() -> new InvalidConfigurationPropertyValueException("productId", productId, "Not found"));
	}

	@Override
	public Page<Product> getAllProducts(Optional<Integer> page, Optional<String> sortBy) {
		// TODO Auto-generated method stub
		return productRepo.findAll(PageRequest.of(
				page.orElse(0),
				20,
				Sort.Direction.ASC, sortBy.orElse("id")));
	}

}
