package com.controller;

import com.dto.UserDto;
import com.pojo.MenuItem;
import com.pojo.OrderItem;
import com.pojo.ReceiverAddress;
import com.pojo.User;
import com.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.vo.ReturnMsg;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Home")
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping("/homePicture")
    @ResponseBody
    public ReturnMsg userSignIn(Model model, String userPhone,String userPwd)
    {
        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");

        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/menu")
    @ResponseBody
    public ReturnMsg getMenuItem(Model model)
    {
        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");
        List<MenuItem> items=new ArrayList<>();

        MenuItem item1=new MenuItem();
        item1.setId(1);
        item1.setIntroduce("臭披萨");
        item1.setName("鲱鱼罐头披萨");
        item1.setOnSale(true);
        item1.setPrize(100);

        MenuItem item2=new MenuItem();
        item2.setId(2);
        item2.setIntroduce("香披萨");
        item2.setName("大便披萨");
        item2.setOnSale(true);
        item2.setPrize(200);

        items.add(item1);
        items.add(item2);
        if(items!=null)
        {
            ret.setData(items);
        }
        else
        {
            ret.setData(new ArrayList<>());
        }
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/itemInfo")
    @ResponseBody
    public ReturnMsg itemInfo(Model model,int itemID)
    {
        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");
        List<MenuItem> items=new ArrayList<>();

        MenuItem item1=new MenuItem();
        item1.setId(1);
        item1.setIntroduce("臭披萨");
        item1.setName("鲱鱼罐头披萨");
        item1.setOnSale(true);
        item1.setPrize(100);


        items.add(item1);
        if(items!=null)
        {
            ret.setData(items);
        }
        else
        {
            ret.setData(new ArrayList<>());
        }
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/orderCommit")
    @ResponseBody
    public Boolean commitOrder(Model model, User user, ReceiverAddress receiverAddress, ArrayList<OrderItem> orderItems)
    {
        Boolean commitSuccess=true;



        return commitSuccess;
    }
}
