package com.nhom9.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom9.springjwt.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	boolean existsById(Long id);
}
