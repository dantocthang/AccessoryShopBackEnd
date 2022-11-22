package com.nhom9.springjwt.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.nhom9.springjwt.repository.CategoryRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.nhom9.springjwt.payload.response.ResourceNotFoundException;
import com.nhom9.springjwt.models.Category;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
		
	@Autowired
	private CategoryRepository categoryRepository;

	
	// get all categorys
		@GetMapping("/")
		public List<Category> getAlllCategory(){
			return categoryRepository.findAll();
		}	
		
		// create categorys rest api
		@PostMapping("/create")
		public Category createCaterogy(@RequestBody Category category) {
			return categoryRepository.save(category);
		}
		
		// get category by id rest api
		@GetMapping("/category/{id}")
		public ResponseEntity<Category> getEmployeeById(@PathVariable Long id) {
			Category category = categoryRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
			return ResponseEntity.ok(category);
		}
		
		// update category rest api
		
		@PutMapping("/category/{id}")
		public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category caterogyDetails){
			Category category = categoryRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
			category.setName(caterogyDetails.getName());
			
			
			Category updatedEmployee = categoryRepository.save(category);
			return ResponseEntity.ok(updatedEmployee);
		}
		
		
		// delete employee rest api
		@DeleteMapping("/category/{id}")
		public ResponseEntity<Map<String, Boolean>> deletecategory(@PathVariable Long id){
			Category categorymodel = categoryRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
			
			categoryRepository.delete(categorymodel);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
}
