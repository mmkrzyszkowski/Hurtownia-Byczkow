package com.hurtownia.service;

import java.util.List;

import com.hurtownia.domain.Wine;

public interface HurtowniaService {
	List<Wine> findAll ();

	Wine findOne(Long id);
}
