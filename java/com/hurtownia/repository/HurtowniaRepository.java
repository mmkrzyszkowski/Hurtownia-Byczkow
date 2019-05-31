package com.hurtownia.repository;

import org.springframework.data.repository.CrudRepository;

import com.hurtownia.domain.Wine;

public interface HurtowniaRepository extends CrudRepository<Wine, Long>{

//	Wine findOne(Long id);

}
