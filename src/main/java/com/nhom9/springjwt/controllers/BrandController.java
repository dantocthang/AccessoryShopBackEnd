package com.nhom9.springjwt.controllers;

import java.net.http.HttpClient.Redirect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.dao.support.DaoSupport;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nhom9.springjwt.models.Brand;
import com.nhom9.springjwt.models.Category;
import com.nhom9.springjwt.models.Product;
import com.nhom9.springjwt.payload.request.BrandRequest;
import com.nhom9.springjwt.payload.response.MessageResponse;
import com.nhom9.springjwt.payload.response.ResourceNotFoundException;
import com.nhom9.springjwt.repository.BrandRepository;
import com.nhom9.springjwt.repository.CategoryRepository;
import com.nhom9.springjwt.repository.ProductRepository;
import com.nhom9.springjwt.repository.WardRepository;
import com.nhom9.springjwt.security.services.BrandService;
import com.nhom9.springjwt.security.services.ProductService;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/brand")
public class BrandController {

	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private ProductRepository productRepository;

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

//	// delete brand rest api
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Map<String, Boolean>> deleteBrand(@PathVariable Long id) {
//		Brand brandModel = brandRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Brand not exist with id :" + id));
//		
//		brandRepository.delete(brandModel);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return ResponseEntity.ok(response);
//	}

//	@DeleteMapping("/{id}")
//	public ResponseEntity<Map<String, Boolean>> deleteBrand(RedirectAttributes attribute, @PathVariable("id") Long id){
//		brandRepository.deleteById(id);
//		attribute.addAttribute("message","The Brand is Deleted succesfully");
//		
//		Map<String> response = new HashMap<>();
//		response.put
//		return ResponseEntity.ok(response);
//	}

	@DeleteMapping(value = "/{id}", consumes = { "*/*" })
	public ResponseEntity<String> deleteBrand(@PathVariable("id") Long id) {
		boolean hasExist = productRepository.existsByBrandId(id);
		// kiểm tra nếu Brand còn sử dụng trong Product sẽ trả về HasExists
//        System.out.println(hasExist);
//        System.out.println("Tồn tại Brand trong Product");
		boolean hasBrandExistsInBrand = brandRepository.existsById(id);
//        System.out.println("Id brand hợp lệ");
//		if (!brandRepository.findById(id).get().getId().equals(id)) 

//		try {
			if (hasBrandExistsInBrand) {
				System.out.println("Id brand hợp lệ");
				if (!hasExist) { // Có tồn tại Brand trong Produc hay không ? nếu không có thì sẽ xóa
					brandRepository.deleteById(id);
					return new ResponseEntity<>("Deleted", HttpStatus.OK);
				} 
				else{
					//Ngược lại, không tồn tại Brand trong Product, không xóa được
					System.out.println("Tồn tại Brand trong Product");
					return new ResponseEntity<>("Still use in product", HttpStatus.BAD_REQUEST);
				}
			}
			else if(!hasBrandExistsInBrand){
				System.out.println("Không tìm thấy ID");
				return new ResponseEntity<>("ID "+id+" not found", HttpStatus.NOT_FOUND);
//				throw new InvalidConfigurationPropertyValueException("id", id, "Brand not found");
			}else {
				System.out.println("Lỗi 500 INTERNAL SERVER ERRROR");
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
//		} catch (Exception e) {
//			System.out.println("Lỗi 500 không xác định");
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
}
