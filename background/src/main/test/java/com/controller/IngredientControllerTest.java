package com.controller;

import baseUnitTest.BaseUnitTest;
import com.pojo.Ingredient;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IngredientControllerTest extends BaseUnitTest {
    @Autowired
    private IngredientController controller;

    @Test
    public void testStockOverview()
    {
        int id = 1;
        Assert.assertEquals("success", controller.StockOverview(null, id).getStatus());
    }

    @Test
    public void testImport()
    {
        Ingredient ingredient = new Ingredient();
        ingredient.setType("cheese");
        ingredient.setAmount(50);
        ingredient.setFactoryId(1);
        ingredient.setSource("Mars");
        ingredient.setImportDate("2020-02-20");
        JSONObject json = JSONObject.fromObject(ingredient);
        Assert.assertEquals("success", controller.Import(null, json.toString(), null).getStatus());
    }

    @Test
    public void testSpecificIngredient()
    {
        Ingredient ingredient = new Ingredient();
        ingredient.setType("cheese");
        ingredient.setAmount(50);
        ingredient.setFactoryId(1);
        ingredient.setSource("Mars");
        ingredient.setImportDate("2020-02-20");
        JSONObject json = JSONObject.fromObject(ingredient);
        Assert.assertEquals("success", controller.SpecificIngredient(null, json.toString()).getStatus());
    }
}
