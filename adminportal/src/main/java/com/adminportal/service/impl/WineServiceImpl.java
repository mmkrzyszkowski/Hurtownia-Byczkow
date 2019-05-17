package com.adminportal.service.impl;

import com.adminportal.domain.Wine;
import com.adminportal.repository.WineRepository;
import com.adminportal.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WineServiceImpl implements WineService {
    @Autowired
    private WineRepository wineRepository;

    public Wine save(Wine wine){
        return wineRepository.save(wine);
    }

}
