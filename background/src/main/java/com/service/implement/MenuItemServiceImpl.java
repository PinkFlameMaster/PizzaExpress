package com.service.implement;

import com.dao.MenuItemDao;
import com.pojo.MenuItem;
import com.pojo.MenuItem_Ingredient;
import com.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuItemServiceImpl implements MenuItemService {
    @Autowired
    MenuItemDao menuItemDao;
    @Override
    public List<MenuItem> searchMenuItem(String name) {
        return  menuItemDao.findMenuItemByName(name);
    }

    @Override
    public MenuItem searchMenuItemById(int id) {
        return menuItemDao.findMenuItemByID(id);
    }

    @Override
    public void deleteMenuItem(int id) {
        menuItemDao.deleteMenuItemById(id);
    }

    @Override
    public List<MenuItem_Ingredient> getItemDetail(int id) {
        return menuItemDao.findIngredientByMenuItem(id);
    }
}
