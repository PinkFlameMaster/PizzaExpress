package com.dao;

import com.pojo.Ingredient;
import com.pojo.MenuItem;
import com.pojo.MenuItem_Ingredient;

import java.util.List;

public interface MenuItemDao {
    List<MenuItem> findMenuItemByName(String name);
    MenuItem findMenuItemByID(int id);
    void deleteMenuItemById(int id);
    List<MenuItem_Ingredient> findIngredientByMenuItem(int id);
}
