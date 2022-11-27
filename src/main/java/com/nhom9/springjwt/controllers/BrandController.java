package com.nhom9.springjwt.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhom9.springjwt.models.Brand;
import com.nhom9.springjwt.models.Category;
import com.nhom9.springjwt.payload.request.BrandRequest;
import com.nhom9.springjwt.payload.response.MessageResponse;
import com.nhom9.springjwt.payload.response.ResourceNotFoundException;
import com.nhom9.springjwt.repository.BrandRepository;
import com.nhom9.springjwt.repository.CategoryRepository;
import com.nhom9.springjwt.security.services.BrandService;
import com.nhom9.springjwt.security.services.ProductService;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/brand")
public class BrandController {

	@Autowired
	private BrandRepository brandRepository;

	// get all Brand
	@GetMapping("")
	public List<Brand> getAlllBrand() {
		return brandRepository.findAll();
	}

	// create brand rest api
	@PostMapping("/create") // 
	public Brand createBrand(@RequestBody Brand brand) {
		return brandRepository.save(brand);
	}

	// get Brand by id rest api
	@GetMapping("/{id}") //
	public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
		Brand brand = brandRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Brand not exist with id :" + id));
		return ResponseEntity.ok(brand);
	}

	// update Brand rest api
	@PutMapping("/{id}")
	public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand brandDetails) {
		Brand brand = brandRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Brand not exist with id :" + id));
		brand.setName(brandDetails.getName());

		Brand updatedEmployee = brandRepository.save(brand);
		return ResponseEntity.ok(updatedEmployee);
	}


	// delete brand rest api
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBrand(@PathVariable Long id) {
		Brand brandModel = brandRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Brand not exist with id :" + id));

		brandRepository.delete(brandModel);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}