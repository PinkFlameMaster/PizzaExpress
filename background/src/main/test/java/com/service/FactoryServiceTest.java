package com.service;

import baseUnitTest.BaseUnitTest;
import com.dto.FactoryDto;
import com.pojo.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FactoryServiceTest extends BaseUnitTest {
    @Autowired
    private FactoryService factoryService;

    @Test
    public void testFindFactoryCorrect()
    {
        String name = "factory2";
        List<FactoryDto> factories = factoryService.getFactory(name);
        for (FactoryDto factory : factories)
             Assert.assertEquals(name, factory.getName());
    }

    @Test
    public void testFindFactoryError()
    {
        String name = "-1";
        List<FactoryDto> factories = factoryService.getFactory(name);
        for (FactoryDto factory : factories)
            Assert.assertNull(factory);
    }

    @Test
    public void testGetAdminFactory()
    {
        int id = 1;
        Factory factory = factoryService.getAdminFactory(id);
        Assert.assertEquals(id, factory.getId());
    }

    @Test
    public void testUpdateFac()
    {
        Factory factory = new Factory();
        factory.setId(1);
        factory.setName("factory1");
        factory.setAddress("factory address1");
        factory.setLatitude(0);
        factory.setLongitude(0);
        factory.setPhoneNum("0010");
        factory.setBusinessTimeFrom("已停业");
        factory.setBusinessTimeTo("--");
        factoryService.updateFac(factory);
    }

    @Test
    public void testCreateFac()
    {
        Factory factory = new Factory();
        factory.setName("Test");
        factory.setAddress("Test address");
        factory.setLatitude(0);
        factory.setLongitude(0);
        factory.setPhoneNum("0010");
        factory.setBusinessTimeFrom("已停业");
        factory.setBusinessTimeTo("--");
        factoryService.createFac(factory);
    }

    @Test
    public void testDeleteFac()
    {
        int id = 1;
        factoryService.deleteFac(id);
    }
}
