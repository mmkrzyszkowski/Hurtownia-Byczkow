package com.hurtownia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurtownia.domain.Wine;
import com.hurtownia.repository.HurtowniaRepository;
import com.hurtownia.service.HurtowniaService;

@Service
public class HurtowniaServiceImpl implements HurtowniaService {
	@Autowired
	private HurtowniaRepository hurtowniaRepository;

    public List<Wine> findAll() {
    	return (List<Wine>) hurtowniaRepository.findAll();
	}
	
    public Wine findOne(Long id) {
    	return hurtowniaRepository.findById(id).get();
    	
    }
}
