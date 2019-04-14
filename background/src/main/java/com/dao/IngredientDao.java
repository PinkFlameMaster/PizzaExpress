package com.dao;

import com.pojo.Ingredient;

import java.util.List;

public interface IngredientDao {
    List<Ingredient> getIngredientByOrder(int orderId);
}
