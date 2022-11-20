package com.nhom9.springjwt.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nhom9.springjwt.models.cartItem;
import com.nhom9.springjwt.payload.request.cartItemRequest;

@Component
public interface cartItemService {
	cartItem createCartItem(cartItemRequest cartItemRequest);
	
	Optional<cartItem> updateCartItem(long cartItemId, cartItemRequest cartItem);
	
	void deteleCartItem(long cartItemId);
	
	
	List<cartItem> getCart(long userId);
	
}
