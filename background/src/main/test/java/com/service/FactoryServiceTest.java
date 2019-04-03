package com.service;

import baseUnitTest.BaseUnitTest;
import com.pojo.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FactoryServiceTest extends BaseUnitTest {
    @Autowired
    private FactoryService factoryService;

    @Test
    public void testFindFactoryCorrect()
    {
        int id = 1;
        Factory factory = factoryService.getFactory(id);
        Assert.assertEquals(id, factory.getId());
    }

    @Test
    public void testFindFactoryError()
    {
        int id = -1;
        Factory factory = factoryService.getFactory(id);
        Assert.assertNull(factory);
    }
}
