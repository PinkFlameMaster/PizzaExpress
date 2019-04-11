package com.controller;

import baseUnitTest.BaseUnitTest;
import com.pojo.MenuItem;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuControllerTest extends BaseUnitTest {
    @Autowired
    private MenuController controller;

    @Test
    public void testSearchMenuItems()
    {
        String menuName = "海鲜至尊";

        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuName);
        JSONObject json = JSONObject.fromObject(menuItem);

        List<MenuItem> results = controller.searchMenuItems(null, json.toString(), "在售").getData();
        for (MenuItem result : results)
            Assert.assertEquals(menuName, result.getName());

        results = controller.searchMenuItems(null, json.toString(), "下架").getData();
        for (MenuItem result : results)
            Assert.assertEquals(menuName, result.getName());
    }

    @Test
    public void testDeleteMenuItemsById()
    {
        int[] ids = new int[] {1};
        Assert.assertEquals("success", controller.deleteMenuItemsById(null, ids).getStatus());
    }
}