package com.nhom9.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom9.springjwt.models.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long>{
    
}
