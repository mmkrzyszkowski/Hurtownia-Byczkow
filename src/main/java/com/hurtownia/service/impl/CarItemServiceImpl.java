package com.hurtownia.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurtownia.domain.CartItem;
import com.hurtownia.domain.ShoppingCart;
import com.hurtownia.domain.User;
import com.hurtownia.domain.Wine;
import com.hurtownia.domain.WineToCartItem;
import com.hurtownia.repository.CartItemRepository;
import com.hurtownia.repository.WineToCartItemRepository;
import com.hurtownia.service.CartItemService;

@Service
public class CarItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private WineToCartItemRepository wineToCartItemRepository;
	
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}
	
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getWine().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
	
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		cartItem.setSubtotal(bigDecimal);
		
		cartItemRepository.save(cartItem);
		
		return cartItem;
	
	
	}
	
	public CartItem addWineToCartItem (Wine wine, User user, int qty) {
		
		List <CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
		
		for (CartItem cartItem : cartItemList) {
			if(wine.getId() == cartItem.getWine().getId()) {
				cartItem.setQty(cartItem.getQty()+qty);
				cartItem.setSubtotal((new BigDecimal(wine.getOurPrice()).multiply(new BigDecimal(qty))));
				cartItemRepository.save(cartItem);
				return cartItem;
			}
		}
	
	
	CartItem cartItem = new CartItem();
	cartItem.setShoppingCart(user.getShoppingCart());
	cartItem.setWine(wine);
	
	cartItem.setQty(qty);
	cartItem.setSubtotal((new BigDecimal(wine.getOurPrice()).multiply(new BigDecimal(qty))));
	cartItem = cartItemRepository.save(cartItem);
	
	WineToCartItem wineToCartItem = new WineToCartItem ();
	wineToCartItem.setWine(wine);
	wineToCartItem.setCartItem(cartItem);
	wineToCartItemRepository.save(wineToCartItem);
	
	return cartItem;
	}
}
