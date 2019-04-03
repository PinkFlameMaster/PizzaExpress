package com.dao;

import baseUnitTest.BaseUnitTest;
import com.pojo.Factory;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class FactoryDaoTest extends BaseUnitTest {
    @Resource
    private FactoryDao factoryDao;

    @Test
    public void testFindFactoryByIdCorrect()
    {
        int id = 1;
        Factory factory = factoryDao.findFactoryById(id);
        Assert.assertEquals(id, factory.getId());
    }

    @Test
    public void testFindFactoryByIdError()
    {
        int id = -1;
        Factory factory = factoryDao.findFactoryById(id);
        Assert.assertNull(factory);
    }
}
