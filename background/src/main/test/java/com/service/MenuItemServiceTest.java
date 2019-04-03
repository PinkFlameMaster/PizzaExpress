package com.service;

import baseUnitTest.BaseUnitTest;
import com.pojo.MenuItem;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuItemServiceTest extends BaseUnitTest {
    @Autowired
    private MenuItemService menuItemService;

    @Test
    public void testSearchMenuItem()
    {
        String name = "海鲜至尊";
        List<MenuItem> items = menuItemService.searchMenuItem(name);
        for (MenuItem item : items)
            Assert.assertEquals(name, item.getName());
    }

    @Test
    public void testSearchMenuItemByIdCorrect()
    {
        int id = 1;
        MenuItem item = menuItemService.searchMenuItemById(id);
        Assert.assertEquals(id, item.getId());
    }

    @Test
    public void testSearchMenuItemByIdError()
    {
        int id = -1;
        MenuItem item = menuItemService.searchMenuItemById(id);
        Assert.assertNull(item);
    }

    @Test
    public void testDelete()
    {
        int id = 1;
        menuItemService.deleteMenuItem(id);
        MenuItem item = menuItemService.searchMenuItemById(id);
        Assert.assertEquals(false, item.isOnSale());
    }
}
