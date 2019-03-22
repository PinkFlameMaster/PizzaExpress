package com.controller;

import com.dto.UserDto;
import com.pojo.User;
import com.vo.RetrunMsg;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {



    @RequestMapping("/search")
    @ResponseBody
    public RetrunMsg searchUsers(Model model, User user){
        RetrunMsg ret =new RetrunMsg();
        ret.setSuccess(false);

        //查询 user信息
        //当查询到数据时，mock数据
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto("15800111111","小王","上海","2001-01-01", false));
        users.add(new UserDto("15800222222","小李","上海","2001-01-01", false));
        users.add(new UserDto("15800333333","小韩","上海","2001-01-01",false));

        ret.setData(users);
        ret.setSuccess(true);
        //end mock

        //当查询遇到错误时
//        ret.setSuccess(false);
//        ret.setErrorMsg("这是一条错误信息");



        return ret;
    }
}
