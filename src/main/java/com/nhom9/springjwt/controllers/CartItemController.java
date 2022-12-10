package com.nhom9.springjwt.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.nhom9.springjwt.models.CartItem;
import com.nhom9.springjwt.payload.request.CartItemRequest;
import com.nhom9.springjwt.payload.response.CartItemResponse;
import com.nhom9.springjwt.payload.response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhom9.springjwt.security.services.CartItemService;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartItemController {
	@Autowired
	CartItemService cartService;

	/* get list of item in cart of user */
	@GetMapping("/getCart/{id}")
	public ResponseEntity<List<CartItem>> getCart(@PathVariable("id") Long id) {
		try {
			List<CartItem> cartItems = cartService.getCart(id);
			return new ResponseEntity<>(cartItems, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* create cart item */
	@PostMapping(value = "/create", consumes = { "*/*" })
	public CartItemResponse creatCartItem(@Valid @RequestBody CartItemRequest cartItem) {
		return cartService.createCartItem(cartItem);
	}

	/* delete cart item with id */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MessageResponse> deleteCartItem(@PathVariable("id") Long id) {
		try {
			cartService.deteleCartItem(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* update cart item with id */
	@PutMapping(value = "/{id}", consumes = { "*/*" })
	public ResponseEntity<Optional<CartItem>> updateCartItem(@PathVariable("id") Long id,
			@RequestBody @Valid CartItemRequest cartItem) {
		return new ResponseEntity<>(cartService.updateCartItem(id, cartItem), HttpStatus.OK);
	}

}
