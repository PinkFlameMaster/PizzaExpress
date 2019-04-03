package com.dao;

import baseUnitTest.BaseUnitTest;
import com.pojo.MenuItem;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class MenuItemDaoTest extends BaseUnitTest {
    @Resource
    private MenuItemDao menuItemDao;

    @Test
    public void testFindMenuItemByID()
    {
        int id = 1;
        MenuItem item = menuItemDao.findMenuItemByID(id);
        Assert.assertEquals(id, item.getId());
    }

    @Test
    public void testFindMenuItemByName()
    {
        String name = "海鲜至尊";
        List<MenuItem> items = menuItemDao.findMenuItemByName(name);
        for (MenuItem item : items)
            Assert.assertEquals(name, item.getName());
    }

    @Test
    public void testDeleteMenuItemById()
    {
        int id = 1;
        menuItemDao.deleteMenuItemById(1);
        MenuItem item = menuItemDao.findMenuItemByID(id);
        Assert.assertEquals(false, item.isOnSale());
    }
}
