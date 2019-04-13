package com.controller;

import com.dto.OrderDto;
import com.dto.OrderItemDto;
import com.pojo.Order;
import com.pojo.OrderItem;
import com.pojo.ReceiverAddress;
import com.service.OrderService;
import com.vo.OrderVo;
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
    @RequestMapping("/info")
    @ResponseBody
    public ReturnMsg orderDetail(Model model, int orderId){
        OrderVo orderDetail = orderService.findOrderById(orderId);
        List<OrderItemDto> orderItems = orderService.getOderItemList(orderId);
        OrderDto orderDto = new OrderDto(orderDetail,orderItems);
        List<OrderDto> result =  new ArrayList<OrderDto>();
        result.add(orderDto);
        ReturnMsg ret = new ReturnMsg();
        ret.setData(result);
        ret.setStatus("success");
        return ret;
    }

}
