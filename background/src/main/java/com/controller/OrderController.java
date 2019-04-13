package com.controller;

import com.service.OrderService;
import com.vo.OrderVo;
import com.vo.ReturnMsg;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @RequestMapping("/search")
    @ResponseBody
    public ReturnMsg searchOrders(Model model, String order){
        JSONObject json = JSONObject.fromObject(order);
        // json对象转bean对象
        OrderVo orderInfo = (OrderVo)JSONObject.toBean(json, OrderVo.class);
        List<OrderVo> result = orderService.findOrder(orderInfo.getReceiverAddress().getReceiverName(),orderInfo.getUserId(),orderInfo.getFactoryName());
        ReturnMsg ret = new ReturnMsg();
        ret.setStatus("success");
        ret.setData(result);
        return ret;
    }
}
