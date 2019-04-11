package com.service;

import com.dto.MenuItemDto;
import com.pojo.MenuItem;
import com.pojo.MenuItem_Ingredient;

import java.util.List;

public interface MenuItemService {
    List<MenuItem> searchMenuItem(String name);
    MenuItem searchMenuItemById(int id);
    void deleteMenuItem(int id);
    List<MenuItem_Ingredient> getItemDetail(int id);
    int createMenuItem();
    int updateMenuItem(MenuItemDto menuItemDto);
}
