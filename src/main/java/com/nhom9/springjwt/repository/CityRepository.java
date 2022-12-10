package com.nhom9.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom9.springjwt.models.City;

public interface CityRepository extends JpaRepository<City, Long> {
    
}
