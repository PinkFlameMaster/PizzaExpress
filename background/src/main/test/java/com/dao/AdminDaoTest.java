package com.dao;

import baseUnitTest.BaseUnitTest;
import com.pojo.Admin;
import org.junit.Assert;
import org.junit.Test;
import javax.annotation.Resource;

public class AdminDaoTest extends BaseUnitTest {
    @Resource
    private AdminDao adminDao;

    @Test
    public void testFindByUsername()
    {
        String userName = "admin";
        Admin admin = adminDao.findByUsername(userName);
        Assert.assertEquals(userName,admin.getUsername());
    }
}