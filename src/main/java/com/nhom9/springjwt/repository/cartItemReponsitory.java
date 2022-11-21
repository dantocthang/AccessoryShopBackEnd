package com.nhom9.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom9.springjwt.models.cartItem;

@Repository
public interface cartItemReponsitory extends JpaRepository<cartItem, Long> {
	List<cartItem> findByUser_Id(long userId);

	cartItem findByUser_IdAndProduct_Id(long user_id, long product_id);
}
