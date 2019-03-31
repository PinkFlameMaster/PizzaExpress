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
    public JSONObject userLogin(Model model, @RequestBody Map map,@RequestBody HttpSession session)
    {
        String t=(String)map.get("statusKey");
        String userPhone=(String)map.get("userName");
        String userPwd=(String)map.get("userPwd");
        String code=(String)map.get("yanZhengCode");

        System.out.println(t+" "+userPhone+" "+userPwd+" "+code);
        int type=Integer.valueOf(t);
        //定义返回数据
        String status="success";
        List<User> users = new ArrayList<>();


        if(type ==1)//验证码登录
        {
            String correctCode=session.getAttribute("IDCode").toString();
            if(correctCode.equals(code))//验证码相同
            {
                status="success";
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
    public JSONObject getIDcode(Model model, @RequestBody Map map,@RequestBody HttpSession session) throws Exception {
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
            session.setAttribute("IDCode",IDcode);
            status="success";
        }

        status="{\"status\":\""+status+"\"}";
        System.out.println(JSONObject.fromObject(status));
        return JSONObject.fromObject(status);
    }
}
