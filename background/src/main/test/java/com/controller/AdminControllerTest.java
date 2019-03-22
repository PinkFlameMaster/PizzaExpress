package com.controller;

import baseUnitTest.BaseUnitTest;
import com.pojo.Admin;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class AdminControllerTest extends BaseUnitTest {
    @Autowired
    AdminController controller;
    @Test
    public void checkLogin() {
        Admin admin =new Admin();
        admin.setUsername("admin");
        admin.setPassword("123456");
//        String ret = controller.checkLogin(admin);
//        Assert.assertEquals("success",ret);
    }
}