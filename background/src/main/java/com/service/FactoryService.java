package com.service;

import com.pojo.Factory;

import java.util.List;

public interface FactoryService {
    List<Factory> getFactory(String name);
    Factory getAdminFactory(int id);
    void updateFac(Factory factory);
    void createFac(Factory factory);
    void deleteFac(int id);
}
