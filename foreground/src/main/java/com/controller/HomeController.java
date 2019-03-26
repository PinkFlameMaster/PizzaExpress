package com.controller;

import com.pojo.*;
import com.service.MenuService;
import com.service.OrderService;
import com.service.UserService;
import com.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Home")
public class HomeController {

    @Autowired
    MenuService menuService;

    @Autowired
    OrderService orderService;

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

            items=menuService.queryAllOnSale();

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

        MenuItem menuItem=menuService.queryItemByID(itemID);
        if(menuItem==null)
        {
            ret.setData(new ArrayList<>());
        }
        else
        {
            items.add(menuItem);
            ret.setData(items);
        }

        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/orderCommit")
    @ResponseBody
    public ReturnMsg commitOrder(Model model, User user, ReceiverAddress receiverAddress, ArrayList<OrderItem> orderItems)
    {
        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("success");
        ret.setData(new ArrayList<>());

        //下单时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String orderTime=df.format(new Date());// new Date()为获取当前系统时间

        //生成order的id
        int id=orderService.getMaxOrderID();
        id+=1;

        Order order=new Order();
        order.setId(id);
        order.setOrderTime(orderTime);
        order.setReceiverAddressId(receiverAddress.getId());
        order.setState("Ordered");

        //新增pizzaOrder
        orderService.commitOrder(order);

        for(int i=0;i<orderItems.size();i++)
        {
            //新增orderItem
            OrderItem orderItem=orderItems.get(i);
            orderItem.setOrderId(id);
            orderService.insertOrderItem(orderItem);
        }

        return ret;
    }
}
