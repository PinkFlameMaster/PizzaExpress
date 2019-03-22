package com.controller;

import com.dto.UserDto;
import com.pojo.User;
import com.vo.ReturnMsg;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {



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

        //查询 user信息
        //当查询到数据时，mock数据
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto("15800111111","小王","上海","2001-01-01", false));
        users.add(new UserDto("15800222222","小李","上海","2001-01-01", false));
        users.add(new UserDto("15800333333","小韩","上海","2001-01-01",false));

        ret.setData(users);
        ret.setStatus("success");
        //end mock

        //当查询遇到错误时
//        ret.setStatus("failure");
//        ret.setErrorMsg("这是一条错误信息");

//        User user = (User) JSONObject.toBean(JSONObject.fromObject(request.getParameter("user")), User.class);

        return ret;
    }

    @RequestMapping("/deleteByPhoneNum")
    @ResponseBody
    public ReturnMsg deleteByPhoneNum(Model model, String[] phoneNums){

        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");

        //查询 user信息
        //成功删除的用户
        List<String> deletedUsers = new ArrayList<>();
        deletedUsers.add("15800111111");
        deletedUsers.add("15800222222");

        //未成功删除的用户
        List<String> undeletedUsers = new ArrayList<>();
        undeletedUsers.add("15800333333");
        undeletedUsers.add("15800444444");

        /*  组织data，returnMsg数据格式：
            {
                data:[
                    ["15800111111","15800222222"],   //成功删除
                    ["15800333333","15800444444"]   //未成功删除
                ],
                ...
            }

         */
        List<List<String>> data = new ArrayList<>();
        data.add(deletedUsers);
        data.add(undeletedUsers);

        ret.setData(data);
        ret.setStatus("success");
        //end mock

        //当查询遇到错误时
//        ret.setStatus("failure");
//        ret.setErrorMsg("这是一条错误信息");

//        User user = (User) JSONObject.toBean(JSONObject.fromObject(request.getParameter("user")), User.class);

        return ret;
    }
}
