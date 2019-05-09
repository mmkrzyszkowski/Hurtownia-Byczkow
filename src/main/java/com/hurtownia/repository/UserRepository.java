package com.hurtownia.repository;

import org.springframework.data.repository.CrudRepository;

import com.hurtownia.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);

}
