package com.hurtownia.service;

import java.util.Optional;

import com.hurtownia.domain.UserPayment;

public interface UserPaymentService {
	UserPayment findById(Long id);
	
	void removeById(Long id);
}
