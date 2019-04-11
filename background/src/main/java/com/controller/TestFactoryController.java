package com.controller;

import com.dto.*;
import com.pojo.Factory;
import com.pojo.ReceiverAddress;
import com.pojo.User;
import com.service.UserService;
import com.vo.ReturnMsg;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/factory")
public class TestFactoryController {


    @RequestMapping("/search")
    @ResponseBody
    public ReturnMsg searchFactory(Model model, String factoryName, String status){
        // json字符串转json对象
        System.out.println(factoryName);

        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");
        List<FactoryDto> factories = new ArrayList<>();


        ret.setData(factories);
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/create")
    @ResponseBody
    public ReturnMsg createFactory(Model model, String factory, String status){
        System.out.println(factory);
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public ReturnMsg Remove(Model model, String factoryId, String status){
        System.out.println(factoryId);
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/modify")
    @ResponseBody
    public ReturnMsg Modify(Model model, String modification, String status){
        System.out.println(modification);
        //modification包括id和一个改动的属性
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("success");
        return ret;
    }
}
