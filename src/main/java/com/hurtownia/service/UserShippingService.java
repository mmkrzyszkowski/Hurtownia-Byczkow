package com.hurtownia.service;

import java.util.Optional;

import com.hurtownia.domain.UserShipping;

public interface UserShippingService {
	UserShipping findById(Long id);
	
	void removeById(Long id);
}
