package com.service;

import com.dto.FactoryDto;
import com.pojo.Factory;

import java.util.List;

public interface FactoryService {
    List<FactoryDto> getFactory(String name);
    Factory getAdminFactory(int id);
    void updateFac(Factory factory);
    void createFac(Factory factory);
    void deleteFac(int id);
}
