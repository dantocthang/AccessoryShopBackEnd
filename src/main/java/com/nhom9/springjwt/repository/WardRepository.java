package com.nhom9.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom9.springjwt.models.Ward;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    
}
