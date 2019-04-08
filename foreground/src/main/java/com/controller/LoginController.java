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

import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject userLogin(Model model, @RequestBody Map map)
    {
        if(map==null)
            System.out.println("map 是空滴");
        System.out.println(map.size());
        System.out.println(map.toString());
        String t=map.get("statusKey").toString();
        String userPhone=map.get("userName").toString();
        String userPwd=map.get("userPwd").toString();
        String code=map.get("yanZhengCode").toString();
        String correctCode=map.get("trueYanZhengCode").toString();

        System.out.println(t+" "+userPhone+" "+userPwd+" "+code+" "+correctCode);
        int type=Integer.valueOf(t);
        //定义返回数据
        String status="success";
        List<User> users = new ArrayList<>();


        if(type ==1)//验证码登录
        {
            if(correctCode.equals(code))//验证码相同
            {
                status="success";
                List<User> list=userService.queryByPhoneNum(userPhone);
                if(list==null || list.size()==0)
                {
                    User user=new User();
                    user.setPhoneNum(userPhone);
                    userService.register(user);
                    status="success";
                    System.out.println("注册成功");
                }
            }
            else
            {
                status="failure";
            }
        }
        else //手机密码登录
        {
            users = userService.queryByPhoneNum(userPhone);
            if(users!=null)
            {
                User user=userService.findByUserPhonePwd(userPhone,userPwd);
                if(user!=null)
                {
                    status="success";
                    System.out.println("正确");
                }
                else
                {
                    status="failure";//密码不正确
                    System.out.println("密码不正确");
                }
            }
            else
            {
                status="failure";//用户未注册
                System.out.println("用户未注册");
            }
        }
;
        System.out.println(status);

        status="{\"status\":\""+status+"\"}";
        System.out.println(JSONObject.fromObject(status));
        return JSONObject.fromObject(status);
    }

    @RequestMapping(value = "/IDcode",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getIDcode(Model model, @RequestBody Map map) throws Exception {
        //定义返回数据
        String status="success";

        String userPhone= map.get("userPhone").toString();


        String IDcode=userService.sendIDCode(userPhone);
        if(IDcode.equals("error"))
        {
            status="failure";
        }
        else
        {
            //session.setAttribute("IDCode",IDcode);
            status=IDcode;
        }

        status="{\"IDcode\":\""+status+"\"}";
        System.out.println(JSONObject.fromObject(status));
        return JSONObject.fromObject(status);
    }
}
