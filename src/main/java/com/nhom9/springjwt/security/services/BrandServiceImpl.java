package com.nhom9.springjwt.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import com.nhom9.springjwt.models.Brand;
// import com.nhom9.springjwt.models.Category;
import com.nhom9.springjwt.models.Product;
import com.nhom9.springjwt.payload.request.BrandRequest;
import com.nhom9.springjwt.payload.request.ProductRequest;
import com.nhom9.springjwt.payload.response.MessageResponse;
import com.nhom9.springjwt.payload.response.ProductResponse;
import com.nhom9.springjwt.repository.BrandRepository;
// import com.nhom9.springjwt.repository.CategoryRepository;
import com.nhom9.springjwt.repository.ProductRepository;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	BrandRepository brandRepo;
	@Autowired
	ProductRepository productRepo;

	@Override
	public Brand createBrand(BrandRequest brandRequest) {
//		Product product = productRepo.findById(brandRequest.getProduct_id()).orElseThrow();
		Brand brand = new Brand(brandRequest.getName());
		return brandRepo.save(brand);
	}

	@Override
	public Optional<Brand> updateBrand(Long brandId, BrandRequest brandRequest) {
		// TODO Auto-generated method stub
		Optional<Brand> brand = brandRepo.findById(brandId);
		if (brand.isPresent()) {
//			Product product = productRepo.findById(brandRequest.getProduct_id()).orElseThrow();
			brand.get().setName(brandRequest.getName());
//			brand.get().setName(brandRequest.getName());
	
//			brand.get().setProducts(getAllProducts());
			brandRepo.save(brand.get());
			return brand;
		} else {
			throw new InvalidConfigurationPropertyValueException("brandId", brandId, "Not found");
		}

	}

	private List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public void deleteBrand(Long brandId) {
		// TODO Auto-generated method stub
		if (brandRepo.findById(brandId).get().getId().equals(brandId)) {
			brandRepo.deleteById(brandId);
		} else
			throw new InvalidConfigurationPropertyValueException("brandId", brandId, "Not found");
	}

	@Override
	public Brand getASingleBrand(Long brandId) {
		// TODO Auto-generated method stub
		return brandRepo.findById(brandId)
				.orElseThrow(() -> new InvalidConfigurationPropertyValueException("brandId", brandId, "Not found"));
	}

	@Override
	public List<Brand> getAllBrands() {
		// TODO Auto-generated method stub
		return brandRepo.findAll();
	}

}