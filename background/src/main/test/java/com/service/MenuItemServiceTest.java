package com.service;

import baseUnitTest.BaseUnitTest;
import com.dto.MenuItemDto;
import com.pojo.Ingredient;
import com.pojo.MenuItem;
import com.pojo.MenuItem_Ingredient;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

    @Test
    public void testGetItemDetail()
    {
        int id = 1;
        for (MenuItem_Ingredient mi : menuItemService.getItemDetail(1))
            Assert.assertEquals(id, mi.getMenuItemId());
    }

    @Test
    public void testCreateMenuItem()
    {
        menuItemService.createMenuItem();
    }

    @Test
    public void testUpdateMenuItem()
    {
        MenuItemDto menuItemDto = new MenuItemDto();
        menuItemDto.setId(1);
        menuItemDto.setIntroduce("This is introduce.");
        menuItemDto.setPrize(12);
        menuItemDto.setName("Test Pizza");
        menuItemDto.setOnSale(true);
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = new Ingredient();
        ingredient.setType("Type A");
        ingredient.setAmount(23.3f);
        ingredients.add(ingredient);
        menuItemDto.setIngredients(ingredients);
        menuItemService.updateMenuItem(menuItemDto);
    }
}
