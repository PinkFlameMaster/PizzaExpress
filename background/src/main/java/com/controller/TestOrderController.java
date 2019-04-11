package com.controller;

import com.dto.*;
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
    public ReturnMsg GetInfo(Model model, String orderId, String status){
        System.out.println(orderId);
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("success");
        OrderDto orderDto=new OrderDto();
        orderDto.setOrderTime("00:00");
        //orderDto.setFactoryName("factory");
        //orderDto.setDeliveryFee(6);
        ReceiverAddressDto receiverAddressDto=new ReceiverAddressDto();
        receiverAddressDto.setReceiverPhoneNum("10009000");
        receiverAddressDto.setReceiverName("BJG");
        receiverAddressDto.setAddress("3street");
        orderDto.setReceiverAddress(receiverAddressDto);

        List<OrderItemDto> orderItems=new ArrayList<>();
        OrderItemDto orderItemDto=new OrderItemDto();
        orderItemDto.setNum(10);
        orderItemDto.setActualUnitPrize(3.3f);
        //orderItemDto.setName(name1);
        List<IngredientDto> ingredientDtos=new ArrayList<>();
        IngredientDto ingredient1=new IngredientDto();
        ingredient1.setInportDate("19:19");
        ingredient1.setSource("source1");
        ingredient1.setType("beef");
        //ingredient1.setIdCode();
        IngredientDto ingredient2=new IngredientDto();
        ingredient2.setInportDate("20:19");
        ingredient2.setSource("source2");
        ingredient2.setType("broccoli");
        ingredientDtos.add(ingredient1);
        ingredientDtos.add(ingredient2);
        orderItemDto.setIngredients(ingredientDtos);

        orderItems.add(orderItemDto);
        orderDto.setOrderItems(orderItems);
        List<OrderDto> orders=new ArrayList<>();

        if (orderId.equals("1"))
            orderDto.setState("Arrived");
        else
            orderDto.setState("refunded");
        orders.add(orderDto);
        ret.setData(orders);
        return ret;
    }

    @RequestMapping("/refund")
    @ResponseBody
    public ReturnMsg Refund(Model model, String orderId, String status){
        System.out.println(orderId);
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("success");
        return ret;
    }
}
