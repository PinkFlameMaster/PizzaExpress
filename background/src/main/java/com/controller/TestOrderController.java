package com.controller;

import com.dto.UserDto;
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

import java.util.ArrayList;
import java.util.List;
import com.dto.OrderDto;
import com.dto.ReceiverAddressDto;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class TestOrderController {


    @RequestMapping("/search")
    @ResponseBody
    public ReturnMsg searchOrders(Model model, String order, String status){
        // json字符串转json对象
        System.out.println(order);

        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");
        List<OrderDto> Orders = new ArrayList<>();

        OrderDto order1=new OrderDto();
        order1.setId(1);
        //order1.setFactoryName("factory1");
        //order1.setUsername("陈聪");
        //order1.setUserId("1600000000");
        ReceiverAddressDto add=new ReceiverAddressDto();
        add.setAddress("add1");
        add.setReceiverName("ReceiveName1");
        add.setReceiverPhoneNum("1801666666");
        order1.setReceiverAddress(add);
        Orders.add(order1);

        OrderDto order2=new OrderDto();
        order2.setId(2);
        //order2.setFactoryName("factory2");
        //order2.setUsername("bjg");
        //order2.setUserId("1600000002");
        ReceiverAddressDto add2=new ReceiverAddressDto();
        add.setAddress("add2");
        add.setReceiverName("ReceiveName2");
        add.setReceiverPhoneNum("1801666667");
        order2.setReceiverAddress(add);
        Orders.add(order2);
        ret.setData(Orders);
        ret.setStatus("success");
        return ret;
    }

    @RequestMapping("/info")
    @ResponseBody
    public ReturnMsg GetInfo(Model model,  @RequestBody String orderId, String status){
        System.out.println(orderId);
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("success");
        OrderDto orderDto=new OrderDto();
        orderDto.setOrderTime("00:00");
        List<OrderDto> orders=new ArrayList<OrderDto>();
        orders.add(orderDto);
        ret.setData(orders);
        return ret;
    }
}
