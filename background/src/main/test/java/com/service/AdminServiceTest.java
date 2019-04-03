package com.service;

import baseUnitTest.BaseUnitTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceTest extends BaseUnitTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void testCheckLoginCorrect()
    {
        Assert.assertNotNull(adminService.checkLogin("admin", "admin"));
    }

    @Test
    public void testCheckLoginError()
    {
        Assert.assertNull(adminService.checkLogin("error", "error"));
    }
}
