package com.controller;

import com.dto.UserDto;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping("/register")
    @ResponseBody
    public ReturnMsg userSignIn(Model model, String userPhone,String userPwd)
    {
        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");
        List<User> users = new ArrayList<>();

        User user=new User();
        user.setPhoneNum(userPhone);
        user.setPassword(userPwd);

        Boolean result=userService.register(user);
        if(result==true)
        {
            users.add((user));
        }

        if(users != null)
            ret.setData(users);
        else ret.setData(new ArrayList<>());
        ret.setStatus("success");
        return ret;
    }
}
