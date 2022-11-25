package com.nhom9.springjwt.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nhom9.springjwt.models.CartItem;
import com.nhom9.springjwt.payload.request.CartItemRequest;

@Component
public interface CartItemService {
	CartItem createCartItem(CartItemRequest cartItemRequest);

	Optional<CartItem> updateCartItem(long cartItemId, CartItemRequest cartItem);

	void deteleCartItem(long cartItemId);

	List<CartItem> getCart(long userId);

}
