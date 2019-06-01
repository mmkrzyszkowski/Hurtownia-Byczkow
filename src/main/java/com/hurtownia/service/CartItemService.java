package com.hurtownia.service;

import java.util.List;

import com.hurtownia.domain.CartItem;
import com.hurtownia.domain.ShoppingCart;

public interface CartItemService {

	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
}
