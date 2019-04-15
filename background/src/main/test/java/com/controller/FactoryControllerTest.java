package com.controller;

import baseUnitTest.BaseUnitTest;
import com.pojo.Factory;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FactoryControllerTest extends BaseUnitTest {
    @Autowired
    private FactoryController controller;

    @Test
    public void testSearchFactory()
    {
        Assert.assertEquals("success", controller.searchFactory(null, "factory1", null).getStatus());
    }

    @Test
    public void testUpdateFactory()
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
        JSONObject json = JSONObject.fromObject(factory);
        Assert.assertEquals("success", controller.updateFactory(null, json.toString()).getStatus());
    }

    @Test
    public void testCreateFactory()
    {
        Factory factory = new Factory();
        factory.setName("Test factory");
        factory.setAddress("Test address");
        factory.setLatitude(0);
        factory.setLongitude(0);
        factory.setPhoneNum("0010");
        factory.setBusinessTimeFrom("已停业");
        factory.setBusinessTimeTo("--");
        JSONObject json = JSONObject.fromObject(factory);
        Assert.assertEquals("success", controller.createFactory(null, json.toString()).getStatus());
    }

    @Test
    public void testRemoveFactory()
    {
        int id = 1;
        Assert.assertEquals("success", controller.removeFactory(null, id).getStatus());
    }
}
