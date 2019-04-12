package com.service.implement;

import com.dao.MenuItemDao;
import com.dto.MenuItemDto;
import com.pojo.Ingredient;
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

    @Override
    public int createMenuItem() {
        menuItemDao.createMenuItem(menuItemDao.count()+1);
        return menuItemDao.count();
    }

    @Override
    public int updateMenuItem(MenuItemDto menuItemDto) {
        int id = menuItemDto.getId();
        List<Ingredient> ingredients = menuItemDto.getIngredients();
        menuItemDao.deleteItemIngredients(id);
        if (ingredients != null)
            for(Ingredient in: ingredients){
                System.out.println(in.getType());
                menuItemDao.insertItemIngredients(id, in.getType(),in.getAmount());
            }
        menuItemDao.updateMenuItem(menuItemDto.getId(), menuItemDto.getName(),
                menuItemDto.getPrize(), menuItemDto.getIntroduce(), menuItemDto.getImgPath());
        return 0;
    }
}
