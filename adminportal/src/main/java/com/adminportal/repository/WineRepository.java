package com.adminportal.repository;

import com.adminportal.domain.Wine;
import org.springframework.data.repository.CrudRepository;

public interface WineRepository extends CrudRepository<Wine, Long> {

}
