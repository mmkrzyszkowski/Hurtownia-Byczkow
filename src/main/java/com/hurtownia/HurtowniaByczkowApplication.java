package com.hurtownia;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hurtownia.domain.User;
import com.hurtownia.domain.security.Role;
import com.hurtownia.domain.security.UserRole;
import com.hurtownia.service.UserService;
import com.hurtownia.utility.SecurityUtility;

@SpringBootApplication
public class HurtowniaByczkowApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(HurtowniaByczkowApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
//		User user1 = new User();
//		user1.setFirstName("John");
//		user1.setLastName("Adams");
//		user1.setUsername("test1");
//		user1.setPassword(SecurityUtility.passwordEncoder().encode("test1"));
//		user1.setEmail("mig210@tlen.pl");
//		Set<UserRole> userRoles = new HashSet<>();
//		Role role1 = new Role ();
//		role1.setRoleID(2);
//		role1.setName("ROLE_USER");
//		userRoles.add(new UserRole(user1, role1));
//
//		userService.createUser(user1, userRoles);
	}

}
