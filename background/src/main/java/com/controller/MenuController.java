package com.controller;

import com.pojo.MenuItem;
import com.vo.ReturnMsg;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @RequestMapping("/searchItems")
    @ResponseBody
    public ReturnMsg searchMenuItems(Model model, String menuItem, String status){
// json字符串转json对象
        JSONObject json = JSONObject.fromObject(menuItem);
        // json对象转bean对象
        MenuItem menuInfo = (MenuItem)JSONObject.toBean(json, MenuItem.class);

        /*
        *   menuInfo.name -> 菜品名称，""表示全部
        *  status -> “在售” 、 “下架” 、 “全部”
        * */
        ReturnMsg ret = new ReturnMsg();
        ret.setStatus("success");
        List<MenuItem> items = new ArrayList<>();

        for(int i=0; i<10; i++){
            MenuItem item = new MenuItem();
            item.setId(i);
            item.setName("pizza"+i);
            item.setIntroduce("这是介绍");
            item.setOnSale(true);
            item.setPrize((float)45.80);
            items.add(item);
        }
        ret.setData(items);
        return ret;
    }

    @RequestMapping("/deleteItemsById")
    @ResponseBody
    public ReturnMsg deleteMenuItemsById(Model model, int[] ids){

        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");

        //查询 menuItem信息
        //成功删除的Item
        List<Integer> deletedItems = new ArrayList<>();
        deletedItems.add(1);
        deletedItems.add(2);

        //未成功删除的用户
        List<Integer> undeletedItems = new ArrayList<>();
        undeletedItems.add(3);
        undeletedItems.add(4);

        /*  组织data，returnMsg数据格式：
            {
                data:[
                    [1,2,3,4...],   //成功删除
                    [5,6,7,8....]   //未成功删除
                ],
                ...
            }

         */
        List<List<Integer>> data = new ArrayList<>();
        data.add(deletedItems);
        data.add(undeletedItems);

        ret.setData(data);
        ret.setStatus("success");
        //end mock

        //当查询遇到错误时
//        ret.setStatus("failure");
//        ret.setErrorMsg("这是一条错误信息");

        return ret;
    }
}
