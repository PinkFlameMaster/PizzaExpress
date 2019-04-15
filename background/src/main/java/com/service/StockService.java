package com.service;

import com.dto.IngredientDto;
import com.pojo.Ingredient;

import java.util.List;

public interface StockService {
    List<IngredientDto> getAllIngredient(int factoryId);
    List<IngredientDto> getIngredientByType(String type,int factoryId);
    void importIngredient(Ingredient ingredient);
}
