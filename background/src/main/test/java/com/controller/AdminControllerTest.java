package com.controller;

import baseUnitTest.BaseUnitTest;
import com.pojo.Admin;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;

public class AdminControllerTest extends BaseUnitTest {
    @Autowired
    private AdminController controller;

    @Test
    public void testLogin()
    {
        Assert.assertEquals("login", controller.login());
    }

    @Test
    public void testCheckLogin() {
        String userName = "admin";
        String password = "admin";

        Admin admin = new Admin();
        admin.setUsername(userName);
        admin.setPassword(password);

        HttpSession session = new MockHttpSession();
        Assert.assertEquals("success", controller.checkLogin(admin, session).getStatus());

        Admin result = (Admin)session.getAttribute("admin");
        Assert.assertNotNull(result);
        Assert.assertEquals(userName, result.getUsername());
    }

    @Test
    public void testCheckLoginFailed(){
        String userName = "errorUserName";
        String password = "errorPassword";

        Admin admin = new Admin();
        admin.setUsername(userName);
        admin.setPassword(password);

        HttpSession session = new MockHttpSession();
        Assert.assertEquals("failure", controller.checkLogin(admin, session).getStatus());
    }

    @Test
    public void testOutLogin()
    {
        MockHttpSession session = new MockHttpSession();
        Assert.assertEquals("login", controller.outLogin(session));
        Assert.assertTrue(session.isInvalid());
    }
}