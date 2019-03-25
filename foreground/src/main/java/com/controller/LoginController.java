package com.controller;

import com.pojo.User;
import com.service.UserService;
import com.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public ReturnMsg userLogin(Model model, int type, String userPhone,String userPwd,String code,HttpSession session)
    {
        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");
        List<User> users = new ArrayList<>();


        if(type ==1)//验证码登录
        {
            String correctCode=session.getAttribute("IDCode").toString();
            if(correctCode.equals(code))//验证码相同
            {
                ret.setStatus("success");
                users = userService.queryByPhoneNum(userPhone);
            }
            else
            {
                ret.setStatus("failure");
                ret.setErrorMsg("验证码输入错误！");
            }
        }
        else //手机密码登录
        {
            users = userService.queryByPhoneNum(userPhone);
            ret.setStatus("success");
        }

        if(users != null)
            ret.setData(users);
        else ret.setData(new ArrayList<>());
        return ret;
    }

    @RequestMapping("/IDcode")
    @ResponseBody
    public boolean getIDcode(Model model, String userPhone,HttpSession session)
    {
        //定义返回数据
        boolean isSuccess=true;


        String IDcode=userService.generateIDCode(userPhone);
        if(IDcode.equals("error"))
        {
            isSuccess=false;
        }
        else
        {
            session.setAttribute("IDCode",IDcode);
            isSuccess=true;
        }

        return isSuccess;
    }
}
