package com.hurtownia.repository;

import org.springframework.data.repository.CrudRepository;

import com.hurtownia.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByName(String name);
	
}
