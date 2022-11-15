package com.nhom9.springjwt.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import com.nhom9.springjwt.models.Category;
import com.nhom9.springjwt.models.Product;
import com.nhom9.springjwt.payload.request.ProductRequest;
import com.nhom9.springjwt.payload.response.MessageResponse;
import com.nhom9.springjwt.payload.response.ProductResponse;
import com.nhom9.springjwt.repository.CategoryRepository;
import com.nhom9.springjwt.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	ProductRepository productRepo;

	@Override
	public ProductResponse createProduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(productRequest.getCategory_id()).orElseThrow();
		Product product = new Product(productRequest.getName(), productRequest.getDescription(),
				productRequest.getStock(), productRequest.getPrice(), productRequest.getYear(), category);
		productRepo.save(product);
		return new ProductResponse(product, null,"New product created successfully");
	}

	@Override
	public Optional<Product> updateProduct(Long productId, ProductRequest productRequest) {
		// TODO Auto-generated method stub
		Optional<Product> product = productRepo.findById(productId);
		System.out.println(product.get().getId());
		if (product.isPresent()) {
			Category category = categoryRepo.findById(productRequest.getCategory_id()).orElseThrow();
			product.get().setName(productRequest.getName());
			product.get().setDescription(productRequest.getDescription());
			product.get().setPrice(productRequest.getPrice());
			product.get().setStock(productRequest.getStock());
			product.get().setYear(productRequest.getYear());
			product.get().setCategory(category);
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
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

}
