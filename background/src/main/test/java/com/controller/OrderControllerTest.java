package com.controller;

import baseUnitTest.BaseUnitTest;
import com.pojo.ReceiverAddress;
import com.vo.OrderVo;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderControllerTest extends BaseUnitTest {
    @Autowired
    private OrderController controller;

    @Test
    public void testSearchOrders()
    {
        OrderVo order = new OrderVo();
        order.setAddress("华东师范大学中山北路校区第五宿舍309A");
        order.setFactoryName("factory5");
        order.setId(3);
        order.setOrderTime("2019-04-03 14:17:57");
        order.setReceiverAddress(new ReceiverAddress());
        JSONObject json = JSONObject.fromObject(order);
        Assert.assertEquals("success", controller.searchOrders(null, json.toString()).getStatus());
    }

    @Test
    public void testOrderDetail()
    {
        int id = 3;
        Assert.assertEquals("success", controller.orderDetail(null, id).getStatus());
    }
}
