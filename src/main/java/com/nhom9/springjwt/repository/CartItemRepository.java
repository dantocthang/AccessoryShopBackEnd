package com.nhom9.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom9.springjwt.models.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByUser_Id(long userId);

	CartItem findByUser_IdAndProduct_IdAndStatus(long user_id, long product_id, int status);
}
