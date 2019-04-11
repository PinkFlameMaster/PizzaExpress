package com.dao;

import com.pojo.Ingredient;
import com.pojo.MenuItem;
import com.pojo.MenuItem_Ingredient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuItemDao {
    List<MenuItem> findMenuItemByName(String name);
    MenuItem findMenuItemByID(int id);
    void deleteMenuItemById(int id);
    List<MenuItem_Ingredient> findIngredientByMenuItem(int id);
    int count();
    void createMenuItem(int id);
    void deleteItemIngredients(int id);
    void insertItemIngredients(@Param("id")int id,@Param("ingredientType") String ingredient, @Param("ingredientA")float ingredientA);
    void updateMenuItem(@Param("id")int id, @Param("name")String name, @Param("prize")float prize, @Param("introduce")String introduce,@Param("imgPath") String imgPath);
}
