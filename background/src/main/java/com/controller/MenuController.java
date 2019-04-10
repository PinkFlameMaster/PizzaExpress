package com.controller;

import com.dto.IngredientDto;
import com.dto.MenuItemDto;
import com.pojo.Ingredient;
import com.pojo.MenuItem;
import com.pojo.MenuItem_Ingredient;
import com.pojo.User;
import com.service.AdminService;
import com.service.MenuItemService;
import com.vo.ReturnMsg;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuItemService menuItemService;
    @RequestMapping("/searchItems")
    @ResponseBody
    public ReturnMsg searchMenuItems(Model model, String menuItem, String status){
// json字符串转json对象
        JSONObject json = JSONObject.fromObject(menuItem);
        // json对象转bean对象
        MenuItem menuInfo = (MenuItem)JSONObject.toBean(json, MenuItem.class);
        List<MenuItem> menuItems;
        List<MenuItem> menuItemsOnSale = new ArrayList<>();
        List<MenuItem> menuItemWithdrawn = new ArrayList<>();
        menuItems = menuItemService.searchMenuItem(menuInfo.getName());
        for(MenuItem mi: menuItems){
            if(mi.isOnSale()){
                menuItemsOnSale.add(mi);
            }else menuItemWithdrawn.add(mi);
        }
        if(status.equals("在售")){
            menuItems = menuItemsOnSale;
        }else if(status.equals("下架")){
            menuItems = menuItemWithdrawn;
        }
        /*
        *   menuInfo.name -> 菜品名称，""表示全部
        *  status -> “在售” 、 “下架” 、 “全部”
        * */
        ReturnMsg ret = new ReturnMsg();
        ret.setStatus("success");
        ret.setData(menuItems);
        return ret;
    }

    @RequestMapping("/deleteItemsById")
    @ResponseBody
    public ReturnMsg deleteMenuItemsById(Model model, int[] ids){

        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");
        MenuItem queryResultEntity;
        List<Integer> deletedItems = new ArrayList<>();
        List<Integer> notDeletedItems = new ArrayList<>();
        for(int s: ids){
            queryResultEntity = menuItemService.searchMenuItemById(s);
            if(queryResultEntity.isOnSale()){
                menuItemService.deleteMenuItem(s);
                deletedItems.add(queryResultEntity.getId());
            }else{
                notDeletedItems.add(queryResultEntity.getId());
            }
        }

        List<List<Integer>> data = new ArrayList<>();
        data.add(deletedItems);
        data.add(notDeletedItems);
        ret.setData(data);
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/saveItems")
    @ResponseBody
    public ReturnMsg saveMenuItems(Model model, String mode, String menuItemDto) {


        JSONObject jsonObj=JSONObject.fromObject(menuItemDto);
        //获取json对象中,键ingredients的值
        String ingredientsStr = jsonObj.getString("ingredients");
        //将json字符串转成json数组（net.sf.json.JSONArray.fromObject）
        JSONArray jsonArr = JSONArray.fromObject(ingredientsStr);
        @SuppressWarnings("rawtypes")
        Map<String, Class> classMap = new HashMap<String, Class>();
        classMap.put("ingredients", Ingredient.class);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setClassMap(classMap);
        @SuppressWarnings("unchecked")
        MenuItemDto menuInfo = (MenuItemDto)JSONObject.toBean(jsonObj, new MenuItemDto(),jsonConfig);

        ReturnMsg ret = new ReturnMsg();
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/searchItemDetail")
    @ResponseBody
    public ReturnMsg searchMenuItemDetail(Model model, int id){

        ReturnMsg ret = new ReturnMsg();

        List<MenuItem_Ingredient> mi = menuItemService.getItemDetail(id);
        List<MenuItemDto> result = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();
        for(MenuItem_Ingredient m: mi){
            Ingredient in = new Ingredient(m.getIngredientType(),m.getIngredientAmount());
            ingredients.add(in);
        }
        result.add(new MenuItemDto(menuItemService.searchMenuItemById(id),ingredients));
        ret.setStatus("success");

        ret.setData(result);
        return ret;
    }
}
