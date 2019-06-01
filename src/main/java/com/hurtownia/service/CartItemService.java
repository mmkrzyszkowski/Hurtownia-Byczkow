package com.hurtownia.service;

import java.util.List;

import com.hurtownia.domain.CartItem;
import com.hurtownia.domain.ShoppingCart;
import com.hurtownia.domain.User;
import com.hurtownia.domain.Wine;

public interface CartItemService {

	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addWineToCartItem (Wine wine, User user, int qty);
}
