package com.dao;

import com.dto.IngredientDto;
import com.pojo.Ingredient;

import java.util.List;

public interface IngredientDao {
    List<Ingredient> getIngredientByOrder(int orderId);
    List<IngredientDto> getAllIngredientDto();
}
