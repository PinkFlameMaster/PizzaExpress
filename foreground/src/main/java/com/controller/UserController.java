package com.controller;

import com.dto.OrderDto;
import com.pojo.Order;
import com.pojo.OrderItem;
import com.pojo.User;
import com.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

//    @RequestMapping("/search")
//    @ResponseBody
//    public ReturnMsg searchUsers(Model model, String user, String status){
//        // json字符串转json对象
//        JSONObject json = JSONObject.fromObject(user);
//        // json对象转bean对象
//        User u = (User)JSONObject.toBean(json, User.class);
//
//        //定义返回数据
//        ReturnMsg ret =new ReturnMsg();
//        ret.setStatus("failure");
//        List<User> users = new ArrayList<>();
//
//        if (u.getPhoneNum() == null|| u.getPhoneNum().equals("")) {
//            users = userService.queryByNickname(u.getNickname());
//        } else if (u.getNickname() == null|| u.getNickname().equals("")){
//            users = userService.queryByPhoneNum(u.getPhoneNum());
//        }else{
//            users = userService.queryByPhoneAndNickname(u.getNickname(), u.getPhoneNum());
//        }
//        if(users != null)
//            ret.setData(users);
//        else ret.setData(new ArrayList<>());
//        ret.setStatus("success");
//        return ret;
//    }

    @RequestMapping("/orderList")
    @ResponseBody
    public ReturnMsg searchUsers(Model model,User user){
        // json字符串转json对象
        JSONObject json = JSONObject.fromObject(user);
        // json对象转bean对象
        User u = (User)JSONObject.toBean(json, User.class);

        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");
        List<OrderDto> info=new ArrayList<>();

        OrderDto od=new OrderDto();
        Order order=new Order(1,0,0,"未到达","2019/3/26 3:00 pm","");
        OrderItem oi=new OrderItem(1,1,1,10.2f,2);
        od.setOderItem(oi);
        od.setOrder(order);

        OrderDto od2=new OrderDto();
        Order order2=new Order(2,0,0,"未到达","2019/3/26 3:01 pm","");
        OrderItem oi2=new OrderItem(2,2,2,2f,3);
        od2.setOderItem(oi2);
        od2.setOrder(order2);

        info.add(od);
        info.add(od2);

        if(info != null)
            ret.setData(info);
        else ret.setData(new ArrayList<>());
        ret.setStatus("success");
        return ret;
    }
}
