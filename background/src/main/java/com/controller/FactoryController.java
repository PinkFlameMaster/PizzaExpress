package com.controller;

import com.dto.FactoryDto;
import com.pojo.Factory;
import com.pojo.MenuItem;
import com.service.FactoryService;
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
@RequestMapping("/factory")
public class FactoryController {
    @Autowired
    FactoryService factoryService;


    @RequestMapping("/search")
    @ResponseBody
    public ReturnMsg searchFactory(Model model, String factoryName, String status){
        // json字符串转json对象
        //空对象不传值
        System.out.println(factoryName);

        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");
        List<Factory> factories = new ArrayList<>();
        factories = factoryService.getFactory(factoryName);
        ret.setData(factories);
        ret.setStatus("success");
        return ret;
    }
    @RequestMapping("/modify")
    @ResponseBody
    public ReturnMsg updateFactory(Model model, String factory){
        JSONObject json = JSONObject.fromObject(factory);
        // json对象转bean对象
        Factory fac = (Factory) JSONObject.toBean(json, Factory.class);
        factoryService.updateFac(fac);
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/create")
    @ResponseBody
    public ReturnMsg createFactory(Model model, String factory){
        JSONObject json = JSONObject.fromObject(factory);
        // json对象转bean对象
        Factory fac = (Factory) JSONObject.toBean(json, Factory.class);
        factoryService.createFac(fac);
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public ReturnMsg removeFactory(Model model, int id){
        factoryService.deleteFac(id);
        ReturnMsg ret = new ReturnMsg();
        ret.setStatus("success");
        return ret;
    }

}
