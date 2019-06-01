package com.hurtownia.service;

import java.util.Set;

import com.hurtownia.domain.User;
import com.hurtownia.domain.UserBilling;
import com.hurtownia.domain.UserPayment;
import com.hurtownia.domain.UserShipping;
import com.hurtownia.domain.security.PasswordResetToken;
import com.hurtownia.domain.security.UserRole;

public interface UserService {

	PasswordResetToken getPasswordResetToken (final String token);

	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername (String username);
	
	User findByEmail (String email);

	User createUser(User user1, Set<UserRole> userRoles) throws Exception;

	User save(User user);
	
	void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);
	
	void updateUserShipping(UserShipping userShipping, User user);
	
	void setUserDefaultPayment(Long userPaymentId, User user);
	
	void setUserDefaultShipping(Long userShippingId, User user);
}

