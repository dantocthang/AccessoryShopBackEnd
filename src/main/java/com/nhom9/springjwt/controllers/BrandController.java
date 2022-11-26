package com.nhom9.springjwt.controllers;

import java.util.ArrayList;
import java.util.List;
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
import com.nhom9.springjwt.payload.request.BrandRequest;
import com.nhom9.springjwt.payload.response.MessageResponse;
import com.nhom9.springjwt.security.services.BrandService;
import com.nhom9.springjwt.security.services.ProductService;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
	BrandService brandService;

	@GetMapping("/")
	public ResponseEntity<List<Brand>> getAllBrands(@RequestParam(required = false) String title) {
		try {
			List<Brand> brands = brandService.getAllBrands();
			return new ResponseEntity<>(brands, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//get api Brand by name
	@GetMapping("/{name}")
	public ResponseEntity<Brand> getBrandByName(@PathVariable("name") String name) {
		Brand brand = brandService.getASingleBrand(name);
		return new ResponseEntity<>(brand, HttpStatus.OK);

	}

	@PostMapping(value = "/create", consumes = { "*/*" })
	public ResponseEntity<Brand> createBrand(@Valid @RequestBody BrandRequest brand) {
		return new ResponseEntity<>(brandService.createBrand(brand), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{name}", consumes = { "*/*" })
	public ResponseEntity<Optional<Brand>> updateBrand(@PathVariable("name") String name,
			@RequestBody @Valid BrandRequest brand) {
		return new ResponseEntity<>(brandService.updateBrand(name, brand), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{name}")
	public ResponseEntity<MessageResponse> deleteBrand(@PathVariable("name") String name) {
		try {
			brandService.deleteBrand(name);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}