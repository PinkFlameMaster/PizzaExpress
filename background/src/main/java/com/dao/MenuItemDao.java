package com.dao;

import com.pojo.MenuItem;

import java.util.List;

public interface MenuItemDao {
    List<MenuItem> findMenuItemByName(String name);
    MenuItem findMenuItemByID(int id);
    void deleteMenuItemById(int id);
}
