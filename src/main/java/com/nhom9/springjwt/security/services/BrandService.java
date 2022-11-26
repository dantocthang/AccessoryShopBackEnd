package com.nhom9.springjwt.security.services;

import java.util.List;
import java.util.Optional;

// import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.nhom9.springjwt.models.Brand;
import com.nhom9.springjwt.payload.request.BrandRequest;

@Component
public interface BrandService {

    Brand createBrand(BrandRequest brandRequest);

    Optional<Brand> updateBrand(Long brandId, BrandRequest brandRequest);

    void deleteBrand(Long brandId);

    Brand getASingleBrand(Long brandtId);

    List<Brand> getAllBrands();


   
    
}
