package com.controller;

import com.dto.OrderDto;
import com.pojo.*;
import com.service.OrderService;
import com.service.UserService;
import com.vo.ReturnMsg;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @RequestMapping(value="/orderList",method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getOrders(Model model, @RequestBody Map map)
    {
        String phoneNum=map.get("phoneNum").toString();

        //根据手机号，获取对应用户的所有地址信息
        List<ReceiverAddress> addresses=orderService.getAllAddress(phoneNum);
        List<OrderDto> ret=new ArrayList<>();
        for(int i=0;i<addresses.size();i++)
        {
            int addressID=addresses.get(i).getId();
            //根据每个地址信息，获取对应地址信息的order
            List<Order> tmpOrders=orderService.getAllOrders(addressID);
            if(tmpOrders!=null)
            {
                for(int j=0;j<tmpOrders.size();j++)
                {
                    Order order=tmpOrders.get(i);
                    //根据每个order的id获取对应order的所有orderItem
                    List<OrderItem> items=orderService.getAllOrderItemFromOrderID(order.getId());
                    if(items!=null)
                    {
                        //构造返回数据
                        String orderTime = order.getOrderTime();
                        String state = order.getState();
                        int orderID=order.getId();

                        //计算订单总价
                        float totalPrice=0f;
                        for(int k=0;k<items.size();k++)
                        {
                            totalPrice+= items.get(i).getActualUnitPrize()*items.get(i).getNum();
                        }

                        //单个orderItem、ordertime、state构成一条返回数据
                        OrderDto item = new OrderDto();
                        item.setOderItem(items);
                        item.setOrderTime(orderTime);
                        item.setState(state);
                        item.setOrderID(orderID);
                        item.setTotalPrice(totalPrice);

                        ret.add(item);
                    }
                }
            }
        }
        return JSONArray.fromObject(ret);
    }

    @RequestMapping(value="/orderDetail",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject orderDetail(Model model, @RequestBody Map map)
    {
        int orderID=Integer.valueOf(map.get("orderID").toString());

        //通过ID查找order
        Order order=orderService.getOrderByID(orderID);
        OrderDto orderDto=new OrderDto();

        //找到对应的所有item
        List<OrderItem> items=orderService.getAllOrderItemFromOrderID(orderID);
        //计算总价
        float price=0f;
        if(items!=null)
        {
            for (int i = 0; i < items.size(); i++) {
                price += items.get(i).getNum() * items.get(i).getActualUnitPrize();
            }
        }
        else
        {
            items=new ArrayList<>();
        }

        //找到对应的factory地址
        ReceiverAddress receiverAddress=userService.getAddressById(order.getFactoryId());

        orderDto.setOrderID(orderID);
        orderDto.setState(order.getState());
        orderDto.setOrderTime(order.getOrderTime());
        orderDto.setFactoryLocation(receiverAddress.getAddress());
        orderDto.setOderItem(items);
        orderDto.setTotalPrice(price);
        return JSONObject.fromObject(orderDto);
    }

    @RequestMapping(value="/information",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getUserInformation(Model model, @RequestBody Map map)
    {
        String phoneNum=map.get("phoneNum").toString();

        User returnUser=userService.findByUserPhone(phoneNum);

        return JSONObject.fromObject(returnUser);
    }

    @RequestMapping(value="/infoCommit",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject setUserInformation(Model model, @RequestBody Map map)
    {
        String result="true";

        String phoneNum=map.get("phoneNum").toString();

        String password="";
        if(map.get("password")!=null)
            password=map.get("password").toString();

        String nickname="";
        if(map.get("nickname")!=null)
            nickname=map.get("nickname").toString();

        String city="";
        if(map.get("city")!=null)
            city=map.get("city").toString();

        String birthday="";
        if(map.get("birthday")!=null)
            birthday=map.get("birthday").toString();

        User user=new User();
        user.setPhoneNum(phoneNum);
        user.setPassword(password);
        user.setBirthday(birthday);
        user.setNickname(nickname);
        user.setCity(city);

        result="{\"success\":\""+result+"\"}";
        return JSONObject.fromObject(result);
    }

    @RequestMapping(value="/addressList",method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getAddresses(Model model,@RequestBody Map map)
    {
        String phoneNum=(String)map.get("phoneNum");
        System.out.println(phoneNum);
        List<ReceiverAddress> receiverAddresses=userService.getAddressByPhoneNum(phoneNum);


        return JSONArray.fromObject(receiverAddresses);
    }

    @RequestMapping(value="/addressListCommit",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject newAddress(Model model,@RequestBody Map map)
    {
        String result="true";
        System.out.println(map.toString());
        String userPhoneNum=(String)map.get("id");
        String receiverName=(String)map.get("userName");
        String receiverPhoneNum=(String)map.get("tel");
        String address=(String) map.get("streetName");


        //构造地址
        ReceiverAddress receiverAddress=new ReceiverAddress();
        receiverAddress.setAddress(address);
        receiverAddress.setReceiverName(receiverName);
        receiverAddress.setReceiverPhoneNum(receiverPhoneNum);
        receiverAddress.setUserPhoneNum(userPhoneNum);

        //数据库添加新地址
        userService.addAddress(receiverAddress);

        result="{\"success\":\""+result+"\"}";
        return JSONObject.fromObject(result);
    }

    @RequestMapping(value="/addressChange",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject changeAddress(Model model,@RequestBody Map map)
    {
        String result="true";
        System.out.println(map.toString());
        System.out.println(map.get("addressId").toString());
        int addressID=Integer.valueOf(map.get("addressId").toString());
        String userPhoneNum=(String)map.get("id");
        String receiverName=(String)map.get("userName");
        String receiverPhoneNum=(String)map.get("tel");
        String address=(String) map.get("streetName");

        System.out.println(addressID+" "+userPhoneNum+" "+address);

        //构造地址
        ReceiverAddress receiverAddress=new ReceiverAddress();
        receiverAddress.setId(addressID);
        receiverAddress.setAddress(address);
        receiverAddress.setReceiverName(receiverName);
        receiverAddress.setReceiverPhoneNum(receiverPhoneNum);
        receiverAddress.setUserPhoneNum(userPhoneNum);

        //数据库添加新地址
        userService.modifyAddress(receiverAddress);

        result="{\"success\":\""+result+"\"}";
        System.out.println(JSONObject.fromObject(result));
        return JSONObject.fromObject(result);
    }

    @RequestMapping(value="/addressDelete",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteAddress(Model model,@RequestBody Map map)
    {
        String result="true";

        int addressID=Integer.valueOf(map.get("addressId").toString());

        System.out.println(addressID);

        //数据库添加新地址
        userService.deleteAddress(addressID);

        result="{\"success\":\""+result+"\"}";
        return JSONObject.fromObject(result);
    }
}
