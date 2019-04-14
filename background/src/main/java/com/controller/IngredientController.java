package com.controller;


import com.dto.ImportDto;
import com.dto.IngredientDto;
import com.helper.TimeHelper;
import com.pojo.Ingredient;
import com.service.StockService;
import com.vo.ReturnMsg;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
    @Autowired
    StockService stockService;

    @RequestMapping("/stockOverview")
    @ResponseBody
    public ReturnMsg StockOverview(Model model, int factoryId){

        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");
        List<IngredientDto> ingredientDtos = stockService.getAllIngredient(factoryId);
        ret.setData(ingredientDtos);
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/import")
    @ResponseBody
    public ReturnMsg Import(Model model, String _import, String status){
        JSONObject json = JSONObject.fromObject(_import);
        // json对象转bean对象
        Ingredient ingredient = (Ingredient) JSONObject.toBean(json, Ingredient.class);
        ingredient.setImportDate(TimeHelper.getTodayString());
        stockService.importIngredient(ingredient);
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/specificIngredient")
    @ResponseBody
    public ReturnMsg SpecificIngredient(Model model, String _import){
        JSONObject json = JSONObject.fromObject(_import);
        // json对象转bean对象
        Ingredient ingredient = (Ingredient) JSONObject.toBean(json, Ingredient.class);
        ReturnMsg ret =new ReturnMsg();
        List<IngredientDto> ingredientDtos = stockService.getIngredientByType(ingredient.getType(),ingredient.getFactoryId());
        float sumAmount = 0;
        for(IngredientDto ingredientDto: ingredientDtos){
            sumAmount=sumAmount+ ingredientDto.getAmount();
        }
        ingredientDtos.get(0).setSumAmount(sumAmount);
        ret.setData(ingredientDtos);
        ret.setStatus("success");
        return ret;
    }

}
