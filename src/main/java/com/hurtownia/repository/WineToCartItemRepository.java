package com.hurtownia.repository;

import org.springframework.data.repository.CrudRepository;

import com.hurtownia.domain.WineToCartItem;

public interface WineToCartItemRepository extends CrudRepository <WineToCartItem, Long>{
	
	

}
