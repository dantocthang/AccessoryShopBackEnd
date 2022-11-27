package com.nhom9.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom9.springjwt.models.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}