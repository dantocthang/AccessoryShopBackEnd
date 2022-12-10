package com.nhom9.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom9.springjwt.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
	@Query(value="SELECT * FROM product WHERE name LIKE BINARY CONCAT('%',:text,'%')",nativeQuery = true)
	List<Product> findByNameLike(@Param("text") String text);
	
	boolean existsByCategoryId(Long id);
}
