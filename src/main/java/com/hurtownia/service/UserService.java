package com.hurtownia.service;

import java.util.Set;

import com.hurtownia.domain.User;
import com.hurtownia.domain.security.PasswordResetToken;
import com.hurtownia.domain.security.UserRole;

public interface UserService {

	PasswordResetToken getPasswordResetToken (final String token);

	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername (String username);
	
	User findByEmail (String email);

	User createUser(User user1, Set<UserRole> userRoles) throws Exception;

	User save(User user);
}

