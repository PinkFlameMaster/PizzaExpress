package com.controller;
import javax.servlet.http.HttpSession;

import com.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pojo.Admin;
import com.service.AdminService;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin")

public class AdminController {

    @Autowired
    private AdminService adminService;

    //正常访问login页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/checkLogin")
    @ResponseBody
    public ReturnMsg checkLogin(Admin Admin, HttpSession session){
        ReturnMsg ret =new ReturnMsg();
        //调用service方法
        Admin = adminService.checkLogin(Admin.getUsername(), Admin.getPassword());
        //若有Admin则添加到model里并且跳转到成功页面
        if(Admin != null){
            session.setAttribute("admin", Admin);
            ret.setStatus("success");
            ret.setData(new ArrayList());
            ret.getData().add("../../views/HomePage.jsp ");
            return ret;
        }
        ret.setStatus("failure");
        ret.setErrorMsg("用户名或密码错误");
        return ret;
    }
//
//    //测试超链接跳转到另一个页面是否可以取到session值
//    @RequestMapping("/anotherpage")
//    public String hrefpage(){
//        return "anotherpage";
//    }

    //注销方法
    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session){
        //通过session.invalidata()方法来注销当前的session
        session.invalidate();
        return "login";
    }

}