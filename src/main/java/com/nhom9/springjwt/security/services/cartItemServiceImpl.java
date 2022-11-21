package com.nhom9.springjwt.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import com.nhom9.springjwt.models.Product;
import com.nhom9.springjwt.models.User;
import com.nhom9.springjwt.models.cartItem;
import com.nhom9.springjwt.payload.request.cartItemRequest;
import com.nhom9.springjwt.repository.ProductRepository;
import com.nhom9.springjwt.repository.UserRepository;
import com.nhom9.springjwt.repository.cartItemReponsitory;

@Service
public class cartItemServiceImpl implements cartItemService {
	@Autowired
	ProductRepository productRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	cartItemReponsitory cartItemRepo;

	@Override
	public cartItem createCartItem(cartItemRequest cartItemRequest) {
		// TODO Auto-generated method stub
		cartItem isInCart = cartItemRepo.findByUser_IdAndProduct_Id(cartItemRequest.getUser_id(),
				cartItemRequest.getProduct_id());
		if (isInCart != null) {
			if (isInCart.getQuantity() + cartItemRequest.getQuantity() <= isInCart.getProduct().getStock())
				isInCart.setQuantity(isInCart.getQuantity() + cartItemRequest.getQuantity());
			else
				isInCart.setQuantity(isInCart.getProduct().getStock());
			return cartItemRepo.save(isInCart);
		} else {
			User user = userRepo.findById(cartItemRequest.getUser_id()).orElseThrow();
			Product product = productRepo.findById(cartItemRequest.getProduct_id()).orElseThrow();
			cartItem cartItem = new cartItem(user, product, cartItemRequest.getQuantity());

			return cartItemRepo.save(cartItem);
		}
	}

	@Override
	public Optional<cartItem> updateCartItem(long cartItemId, cartItemRequest cartItemRequest) {
		// TODO Auto-generated method stub
		Optional<cartItem> cartItem = cartItemRepo.findById(cartItemId);

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
	public List<cartItem> getCart(long userId) {
		// TODO Auto-generated method stub
		// return cartItemRepo.findByUser_Id(userId);
		return cartItemRepo.findByUser_Id(userId);

	}

}
