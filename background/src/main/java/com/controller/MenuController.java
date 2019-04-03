package com.controller;

import com.pojo.MenuItem;
import com.pojo.User;
import com.service.AdminService;
import com.service.MenuItemService;
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
}
