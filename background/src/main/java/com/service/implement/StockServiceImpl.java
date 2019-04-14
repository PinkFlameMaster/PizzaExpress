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
    public List<IngredientDto> getAllIngredient() {
        return ingredientDao.getAllIngredientDto();
    }
}
