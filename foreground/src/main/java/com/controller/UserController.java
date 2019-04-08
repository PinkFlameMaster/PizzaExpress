package com.controller;

import com.dto.ItemDto;
import com.dto.OrderDto;
import com.pojo.*;
import com.service.FactoryService;
import com.service.MenuService;
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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    MenuService menuService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    FactoryService factoryService;

    @RequestMapping(value="/orderList",method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getOrders(Model model, @RequestBody Map map)
    {
        System.out.println(map.toString());
        String phoneNum=map.get("userId").toString();

        //根据手机号，获取对应用户的所有地址信息
        List<ReceiverAddress> addresses=orderService.getAllAddress(phoneNum);
        System.out.println(addresses.size());
        List<OrderDto> ret=new ArrayList<>();
        for(int i=0;i<addresses.size();i++)
        {
            int addressID=addresses.get(i).getId();
            //根据每个地址信息，获取对应地址信息的order
            List<Order> tmpOrders=orderService.getAllOrders(addressID);
            System.out.println(tmpOrders.size());
            if(tmpOrders!=null)
            {
                for(int j=0;j<tmpOrders.size();j++)
                {
                    Order order=tmpOrders.get(j);
                    //根据每个order的id获取对应order的所有orderItem
                    List<OrderItem> items=orderService.getAllOrderItemFromOrderID(order.getId());
                    List<ItemDto> itemPackage=new ArrayList<>();
                    System.out.println("items "+items.size());
                    if(items!=null)
                    {
                        //构造返回数据
                        String orderTime = order.getOrderTime();
                        String state = order.getState();
                        int orderID=order.getId();

                        //计算订单总价同时包装orderItem与对应的menuItem
                        float totalPrice=0f;
                        for(int k=0;k<items.size();k++)
                        {
                            ItemDto itemDto=new ItemDto();
                            totalPrice+= items.get(k).getActualUnitPrize()*items.get(k).getNum();
                            MenuItem menuItem=menuService.queryItemByID(items.get(k).getMenuItemId());
                            itemDto.setMenuItem(menuItem);
                            itemDto.setOrderItem(items.get(k));
                            itemPackage.add(itemDto);
                        }

                        //单个orderItem、ordertime、state构成一条返回数据
                        OrderDto item = new OrderDto();
                        item.setGoodsList(itemPackage);
                        item.setOrderTime(orderTime);
                        item.setOrderStatus(state);
                        item.setOrderId(orderID);
                        item.setOrderTotal(totalPrice);
                        item.setReceiverAddress(addresses.get(i));

                        ret.add(item);
                    }
                }
            }
        }
        return JSONArray.fromObject(ret);
    }

    @RequestMapping(value="/goodsDetails",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject orderDetail(Model model, @RequestBody Map map)
    {
        int orderID=Integer.valueOf(map.get("orderId").toString());

        //通过ID查找order
        Order order=orderService.getOrderByID(orderID);
        OrderDto orderDto=new OrderDto();

        //找到对应的所有item
        List<OrderItem> items=orderService.getAllOrderItemFromOrderID(orderID);
        List<ItemDto> itemPackage=new ArrayList<>();
        //计算总价
        float price=0f;
        if(items!=null)
        {
            for (int i = 0; i < items.size(); i++) {
                price += items.get(i).getNum() * items.get(i).getActualUnitPrize();
                ItemDto itemDto=new ItemDto();
                MenuItem menuItem=menuService.queryItemByID(items.get(i).getMenuItemId());
                itemDto.setMenuItem(menuItem);
                itemDto.setOrderItem(items.get(i));
                itemPackage.add(itemDto);
            }
        }
        else
        {
            items=new ArrayList<>();
        }

        //收货人地址
        ReceiverAddress receiverAddress=userService.getAddressById(order.getReceiverAddressId());

        //商店地址
        Factory factory=factoryService.getFactoryByID(order.getFactoryId());

        orderDto.setOrderId(orderID);
        orderDto.setOrderStatus(order.getState());
        orderDto.setOrderTime(order.getOrderTime());
        orderDto.setFactoryLocation(factory.getAddress());
        orderDto.setReceiverAddress(receiverAddress);
        orderDto.setGoodsList(itemPackage);
        orderDto.setOrderTotal(price);
        System.out.println(JSONObject.fromObject(orderDto).toString());
        return JSONObject.fromObject(orderDto);
    }

    @RequestMapping(value="/accept",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject goodsAccepted(Model model, @RequestBody Map map)
    {
        String result="true";
        int orderID=Integer.valueOf(map.get("orderId").toString());

        orderService.changeOrderStatus(orderID,"4");

        result="{\"success\":\""+result+"\"}";
        return JSONObject.fromObject(result);
    }

    @RequestMapping(value="/getUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getUserInformation(Model model, @RequestBody Map map)
    {
        String phoneNum=map.get("phoneNum").toString();
        System.out.println(map.toString());

        User returnUser=userService.findByUserPhone(phoneNum);

        return JSONObject.fromObject(returnUser);
    }

    @RequestMapping(value="/setUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject setUserInformation(Model model, @RequestBody Map map)
    {
        String result="true";

        System.out.println(map.toString());

        String phoneNum=map.get("phoneNum").toString();

//        String password="";
//        if(map.get("password")!=null)
//            password=map.get("password").toString();

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
        //user.setPassword(password);
        user.setBirthday(birthday);
        user.setNickname(nickname);
        user.setCity(city);
        System.out.println(JSONObject.fromObject(user).toString());
        userService.modifyInfo(user);

        result="{\"success\":\""+result+"\"}";
        return JSONObject.fromObject(result);
    }

    @RequestMapping(value="/changePwd",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject setUserPassword(Model model, @RequestBody Map map)
    {
        String result="true";
        System.out.println(map.toString());

        String phoneNum=map.get("phoneNum").toString();

        String password="";
        if(map.get("userPwd")!=null)
            password=map.get("userPwd").toString();

        User user=new User();
        user.setPhoneNum(phoneNum);
        user.setPassword(password);

        userService.modifyPassword(user);

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
    public JSONObject newAddress(Model model,@RequestBody Map map) throws UnsupportedEncodingException {
        String result="true";
        System.out.println(map.toString());
        //String userPhoneNum=new String(((String)map.get("id")).getBytes(),"UTF-8");
        String userPhoneNum=userService.unicodeToUtf8((String)map.get("id"));
        //String receiverName=new String(((String)map.get("userName")).getBytes(),"UTF-8");
        String receiverName=userService.unicodeToUtf8((String)map.get("userName"));
        //String receiverPhoneNum=new String(((String)map.get("tel")).getBytes(),"UTF-8");
        String receiverPhoneNum=userService.unicodeToUtf8((String)map.get("tel"));
        //String address=new String(((String) map.get("streetName")).getBytes(),"UTF-8");
        String address=userService.unicodeToUtf8((String)map.get("streetName"));


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
