package com.nhom9.springjwt.controllers;

import java.util.List;

import com.nhom9.springjwt.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhom9.springjwt.security.services.ProductSearchService;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/search")
public class SearchController {
	@Autowired
	ProductSearchService productSearchService;
	
	/*search product case sensitive*/
	@GetMapping("/product/{text}")
	public ResponseEntity<List<Product>> searchProduct(@PathVariable("text") String text)
	{
		try {
			List<Product> result = productSearchService.searchProductByName(text);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
