package com.service.implement;

import com.dao.FactoryDao;
import com.pojo.Factory;
import com.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryServiceImpl implements FactoryService {
    @Autowired
    FactoryDao factoryDao;
    @Override
    public Factory getFactory(int i) {
        return factoryDao.findFactoryById(i);
    }
}
