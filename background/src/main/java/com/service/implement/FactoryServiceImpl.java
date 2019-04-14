package com.service.implement;

import com.dao.FactoryDao;
import com.pojo.Factory;
import com.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryServiceImpl implements FactoryService {
    @Autowired
    FactoryDao factoryDao;
    @Override
    public List<Factory> getFactory(String name) {
        return factoryDao.findFactoryByName(name);
    }

    @Override
    public Factory getAdminFactory(int id) {
        return factoryDao.findFactoryById(id);
    }

    @Override
    public void updateFac(Factory factory) {
        factoryDao.updateFactory(factory.getId(),factory.getName(),
                factory.getAddress(),factory.getLatitude(),factory.getLongitude(),
                factory.getPhoneNum(),factory.getBusinessTimeFrom(),factory.getBusinessTimeTo());
    }

    @Override
    public void createFac(Factory factory) {
        int id =factoryDao.count()+1;
        factoryDao.createFactory(id);

        factoryDao.updateFactory(id ,factory.getName(),
                factory.getAddress(),factory.getLatitude(),factory.getLongitude(),
                factory.getPhoneNum(),factory.getBusinessTimeFrom(),factory.getBusinessTimeTo());
    }

    @Override
    public void deleteFac(int id) {
        Factory factory = factoryDao.findFactoryById(id);
        factoryDao.updateFactory(factory.getId(),factory.getName(),
                factory.getAddress(),factory.getLatitude(),factory.getLongitude(),
                factory.getPhoneNum(),"已停业","--");
    }
}
