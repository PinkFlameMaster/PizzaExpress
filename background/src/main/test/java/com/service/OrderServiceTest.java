package com.service;

import baseUnitTest.BaseUnitTest;
import com.dto.OrderItemDto;
import com.pojo.Ingredient;
import com.vo.OrderVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceTest extends BaseUnitTest {
    @Autowired
    private OrderService service;

    @Test
    public void testFindOrder()
    {
        String name = "邱汇石";
        String phone = "18510368172";
        String factory = "factory5";
        List<OrderVo> orders = service.findOrder(name, phone, factory);
        for (OrderVo order : orders)
        {
            Assert.assertTrue(order.getUserId().contains(phone));
            Assert.assertTrue(order.getFactoryName().contains(factory));
        }
    }

    @Test
    public void testFindOrderById()
    {
        int id = 3;
        Assert.assertEquals(id, service.findOrderById(id).getId());
    }

    @Test
    public void testGetOderItemList()
    {
        int id = 3;
        List<OrderItemDto> items = service.getOderItemList(id);
        for (OrderItemDto item : items)
            Assert.assertEquals(id, item.getOrderId());
    }

    @Test
    public void testGetIngredientByOrderId()
    {
        int id = 1;
        List<Ingredient> ingredients = service.getIngredientByOrderId(id);
        Assert.assertNotNull(ingredients);
    }
}
