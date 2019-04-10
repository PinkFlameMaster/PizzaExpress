package com.service;

import com.pojo.MenuItem;
import com.pojo.MenuItem_Ingredient;

import java.util.List;

public interface MenuItemService {
    List<MenuItem> searchMenuItem(String name);
    MenuItem searchMenuItemById(int id);
    void deleteMenuItem(int id);
    List<MenuItem_Ingredient> getItemDetail(int id);
}
