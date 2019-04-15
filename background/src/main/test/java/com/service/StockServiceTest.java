package com.service;

import baseUnitTest.BaseUnitTest;
import com.dto.IngredientDto;
import com.pojo.Ingredient;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StockServiceTest extends BaseUnitTest {
    @Autowired
    private StockService service;

    @Test
    public void testGetAllIngredient()
    {
        int factoryId = 1;
        List<IngredientDto> ingredients = service.getAllIngredient(factoryId);
        Assert.assertNotNull(ingredients);
    }

    @Test
    public void testGetIngredientByType()
    {
        String type = "cheese";
        int factoryId = 1;
        List<IngredientDto> ingredients = service.getIngredientByType(type, factoryId);
        for (IngredientDto ingredient : ingredients) {
            Assert.assertEquals(type, ingredient.getType());
        }
    }

    @Test
    public void testImportIngredient()
    {
        Ingredient ingredient = new Ingredient();
        ingredient.setType("cheese");
        ingredient.setAmount(50);
        ingredient.setFactoryId(1);
        ingredient.setSource("Mars");
        ingredient.setImportDate("2020-02-20");
        service.importIngredient(ingredient);
    }
}
