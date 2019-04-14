package com.service.implement;

import com.dao.IngredientDao;
import com.dto.IngredientDto;
import com.pojo.Ingredient;
import com.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    IngredientDao ingredientDao;
    @Override
    public List<IngredientDto> getAllIngredient(int factoryId) {
        return ingredientDao.getAllIngredientDto(factoryId);
    }

    @Override
    public List<IngredientDto> getIngredientByType(String type,int factoryId) {
        return ingredientDao.getIngredientByType(type,factoryId); }

    @Override
    public void importIngredient(Ingredient ingredient) {
        ingredientDao.importIngredient(ingredient.getType(),ingredient.getSource()
                ,ingredient.getAmount(),ingredient.getImportDate(),ingredient.getFactoryId());
    }
}
