package com.hurtownia.repository;

import org.springframework.data.repository.CrudRepository;

import com.hurtownia.domain.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long>{

}
