package com.hurtownia.service.impl;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hurtownia.domain.ShoppingCart;
import com.hurtownia.domain.User;
import com.hurtownia.domain.security.PasswordResetToken;
import com.hurtownia.domain.security.UserRole;
import com.hurtownia.repository.PasswordResetTokenRepository;
import com.hurtownia.repository.RoleRepository;
import com.hurtownia.repository.UserRepository;
import com.hurtownia.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public PasswordResetToken getPasswordResetToken(final String token) {
		return passwordResetTokenRepository.findByToken(token);
	}
	
	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
//	@Transactional
	public User createUser(User user, Set<UserRole> userRoles) throws Exception{
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			throw new Exception("Użytkownik ma już konto.");
		}
		else {
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
		}
		
		user.getUserRoles().addAll(userRoles);
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUser(user);
		user.setShoppingCart(shoppingCart);
		
//		user.setUserShippingList(new ArrayList<UserShipping>());
		
//		user.setUserPaymentList(new ArrayList<UserPayment>());
		
		localUser = userRepository.save(user);
		
		return localUser;
	}
	
	@Override
	public User save (User user) {
		return userRepository.save(user);
	}
}
