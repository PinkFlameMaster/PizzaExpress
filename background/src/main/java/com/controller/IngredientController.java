package com.controller;


import com.dto.ImportDto;
import com.dto.IngredientDto;
import com.service.StockService;
import com.vo.ReturnMsg;
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
    public ReturnMsg StockOverview(Model model, String status){

        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");
        List<IngredientDto> ingredientDtos = stockService.getAllIngredient();
        ret.setData(ingredientDtos);
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/import")
    @ResponseBody
    public ReturnMsg Import(Model model, String _import, String status){
        System.out.println(_import);
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/specificIngredient")
    @ResponseBody
    public ReturnMsg SpecificIngredient(Model model, String type, String status){
        ReturnMsg ret =new ReturnMsg();

        List<IngredientDto> ingredientDtos = new ArrayList<>();
        IngredientDto ingredientDto=new IngredientDto();
        ingredientDto.setAmount(10);
        ingredientDto.setType("Beef");
        ingredientDto.setStatus("缺货");

        ImportDto importDto=new ImportDto();
        importDto.setAmount(10);
        importDto.setType("Beef");
        importDto.setSource("source1");
        importDto.setId(300);
        importDto.setImportDate("1990-2-2 00:00");

        List<ImportDto> _imports=new ArrayList<>();
        _imports.add(importDto);
        ingredientDto.set_imports(_imports);
        ingredientDtos.add(ingredientDto);
        ret.setData(ingredientDtos);
        ret.setStatus("success");
        return ret;
    }

}
