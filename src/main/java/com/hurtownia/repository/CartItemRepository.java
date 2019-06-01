package com.hurtownia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hurtownia.domain.CartItem;
import com.hurtownia.domain.ShoppingCart;

public interface CartItemRepository extends CrudRepository<CartItem, Long>{
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

}
