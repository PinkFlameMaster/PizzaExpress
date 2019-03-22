package com.controller;

import com.dto.UserDto;
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

    @RequestMapping("/search")
    @ResponseBody
    public ReturnMsg searchUsers(Model model, String user, String status){
        // json字符串转json对象
        JSONObject json = JSONObject.fromObject(user);
        // json对象转bean对象
        User u = (User)JSONObject.toBean(json, User.class);

        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");
        List<User> users = new ArrayList<>();

        if (u.getPhoneNum() == null|| u.getPhoneNum().equals("")) {
            users = userService.queryByNickname(u.getNickname());
        } else if (u.getNickname() == null|| u.getNickname().equals("")){
            users = userService.queryByPhoneNum(u.getPhoneNum());
        }else{
            users = userService.queryByPhoneAndNickname(u.getNickname(), u.getPhoneNum());
        }
        if(users != null)
            ret.setData(users);
        else ret.setData(new ArrayList<>());
        ret.setStatus("success");
        return ret;
    }
}
