package com.controller;

import com.pojo.User;
import com.service.UserService;
import com.vo.ReturnMsg;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;

//    @RequestMapping("/register")
//    @ResponseBody
//    public ReturnMsg userSignIn(Model model, String userPhone,String userPwd)
//    {
//        //定义返回数据
//        ReturnMsg ret =new ReturnMsg();
//        ret.setStatus("failure");
//        List<User> users = new ArrayList<>();
//
//        User user=new User();
//        user.setPhoneNum(userPhone);
//        user.setPassword(userPwd);
//
//        Boolean result=userService.register(user);
//        if(result==true)
//        {
//            users.add((user));
//        }
//
//        if(users != null)
//            ret.setData(users);
//        else ret.setData(new ArrayList<>());
//        ret.setStatus("success");
//        return ret;
//    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject userSignIn(Model model,  @RequestBody Map map)
    {
        String userPhone=(String)map.get("userPhone");
        String userPwd=(String)map.get("userPwd");
        System.out.println(userPhone+" "+userPwd);
        //定义返回数据
        String status="success";

        User user=new User();
        user.setPhoneNum(userPhone);
        user.setPassword(userPwd);

        List<User> list=userService.queryByPhoneNum(userPhone);
        if(list==null || list.size()==0)
        {
            userService.register(user);
            status="success";
            System.out.println("注册成功");
        }
        else
        {
            status = "failure";
            System.out.println("注册失败");
        }

        status="{\"status\":\""+status+"\"}";
        System.out.println(JSONObject.fromObject(status));
        return JSONObject.fromObject(status);
    }
}
