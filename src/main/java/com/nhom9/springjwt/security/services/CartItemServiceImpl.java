package com.nhom9.springjwt.security.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import com.nhom9.springjwt.models.Product;
import com.nhom9.springjwt.models.User;
import com.nhom9.springjwt.models.CartItem;
import com.nhom9.springjwt.payload.request.CartItemRequest;
import com.nhom9.springjwt.repository.CartItemRepository;
import com.nhom9.springjwt.repository.ProductRepository;
import com.nhom9.springjwt.repository.UserRepository;

@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	ProductRepository productRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	CartItemRepository cartItemRepo;

	@Override
	public CartItem createCartItem(CartItemRequest cartItemRequest) {
		// TODO Auto-generated method stub
		CartItem isInCart = cartItemRepo.findByUser_IdAndProduct_IdAndStatus(cartItemRequest.getUser_id(), cartItemRequest.getProduct_id(), 0);

		if (isInCart != null) {
			if (isInCart.getQuantity() + cartItemRequest.getQuantity() <= isInCart.getProduct().getStock())
				isInCart.setQuantity(isInCart.getQuantity() + cartItemRequest.getQuantity());
			else
				isInCart.setQuantity(isInCart.getProduct().getStock());
			return cartItemRepo.save(isInCart);
		} else {
			User user = userRepo.findById(cartItemRequest.getUser_id()).orElseThrow();
			Product product = productRepo.findById(cartItemRequest.getProduct_id()).orElseThrow();
			CartItem cartItem = new CartItem(user, product, cartItemRequest.getQuantity());

			return cartItemRepo.save(cartItem);
		}
	}

	@Override
	public Optional<CartItem> updateCartItem(long cartItemId, CartItemRequest cartItemRequest) {
		// TODO Auto-generated method stub
		Optional<CartItem> cartItem = cartItemRepo.findById(cartItemId);

		if (cartItem.isPresent()) {
			cartItem.get().setQuantity(cartItemRequest.getQuantity());

			cartItemRepo.save(cartItem.get());
			return cartItem;
		} else {
			throw new InvalidConfigurationPropertyValueException("cartItemId", cartItemId, "Not found");
		}

	}

	@Override
	public void deteleCartItem(long cartItemId) {
		// TODO Auto-generated method stub
		if (cartItemRepo.findById(cartItemId).get().getId().equals(cartItemId)) {
			cartItemRepo.deleteById(cartItemId);
		} else
			throw new InvalidConfigurationPropertyValueException("cartItemId", cartItemId, "Not Found");

	}

	@Override
	public List<CartItem> getCart(long userId) {
		// TODO Auto-generated method stub
		List<CartItem> list = cartItemRepo.findByUser_Id(userId);
		List<CartItem> list2 = new ArrayList<>();
		
		for (CartItem cartItem : list) {
			if(cartItem.getStatus()==0)
				list2.add(cartItem);
		}
		return list2;
	}

}
