package com.controller;

import com.dao.ReceiverAddressDao;
import com.pojo.*;
import com.service.MenuService;
import com.service.OrderService;
import com.service.UserService;
import com.service.implement.OrderServiceImpl;
import com.vo.ReturnMsg;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/Home")
public class HomeController {

    @Autowired
    MenuService menuService;

    @Autowired
    OrderService orderService;

    @Autowired
    ReceiverAddressDao receiverAddressDao;

    @RequestMapping("/homePicture")
    @ResponseBody
    public ReturnMsg userSignIn(Model model, String userPhone,String userPwd)
    {
        //定义返回数据
        ReturnMsg ret =new ReturnMsg();
        ret.setStatus("failure");

        ret.setStatus("success");
        return ret;
    }

    @RequestMapping(value = "/menu",method = RequestMethod.GET)
    @ResponseBody
    public  JSONArray getMenuItem(Model model) {
        List<MenuItem> items=new ArrayList<>();

        items=menuService.queryAllOnSale();

        return JSONArray.fromObject(items);
    }

    @RequestMapping(value = "/orderedMenu",method = RequestMethod.POST)
    @ResponseBody
    public  JSONArray getMenuItemByOrder(Model model,@RequestBody Map map)
    {
        int type=Integer.valueOf(map.get("orderType").toString());
        float minPrice=Float.valueOf(map.get("minPrice").toString());
        float maxPrice=Float.valueOf(map.get("maxPrice").toString());
        System.out.println("===================="+map.toString());
        List<MenuItem> items=new ArrayList<>();

        if(type==1)
        {
            items=menuService.queryAllOnSale();
        }
        else if(type==2)
        {
            items=menuService.queryAllOnSaleByPriceAndOrderFromLow(minPrice,maxPrice);
        }
        else
        {
            items=menuService.queryAllOnSaleByPriceAndOrderFromHigh(minPrice,maxPrice);
        }

        return JSONArray.fromObject(items);
    }

    @RequestMapping(value = "/searchItem",method = RequestMethod.POST)
    @ResponseBody
    public  JSONArray searchItem(Model model,@RequestBody Map map)
    {
        String searchInfo=map.get("searchContent").toString();
        System.out.println(map.toString());
        System.out.println(searchInfo);
        List<MenuItem> items=new ArrayList<>();

        items=menuService.queryAllOnSaleByInfo(searchInfo);
        return JSONArray.fromObject(items);
    }

    @RequestMapping(value = "/itemInfo",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject itemInfo(Model model,@RequestBody Map map)
    {
        System.out.println(map);
        //int itemID=Integer.valueOf(id);
        int itemID=Integer.valueOf((String)map.get("id"));
        System.out.println(itemID+"------------------------");
        MenuItem menuItem=menuService.queryItemByID(itemID);

        return JSONObject.fromObject(menuItem);
    }

    @RequestMapping(value = "/orderCommit",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject commitOrder(Model model,@RequestBody Map map) throws MalformedURLException, UnsupportedEncodingException {
        //addressId
        int addressID=Integer.valueOf(map.get("addressId").toString());
        System.out.println(addressID);
        ReceiverAddress receiverAddress=receiverAddressDao.findById(addressID);
        System.out.println(receiverAddress.getAddress());

        JSONArray array=JSONArray.fromObject(map.get("goodsList"));
        System.out.println(array);
        List<OrderItem> itemlist=new ArrayList<>();
        for(int i=0;i<array.size();i++)
        {
            JSONObject obj =JSONObject.fromObject(array.get(i));
            int menuItemId=Integer.valueOf(obj.get("productId").toString());
            float prize=Float.valueOf(obj.get("salePrice").toString());
            int num=Integer.valueOf(obj.get("productNum").toString());
            OrderItem orderItem=new OrderItem();
            orderItem.setMenuItemId(menuItemId);
            orderItem.setNum(num);
            orderItem.setActualUnitPrize(prize);
            itemlist.add(orderItem);
        }
        OrderItem[] orderItems=new OrderItem[itemlist.size()];
        for(int i=0;i<orderItems.length;i++)
        {
            orderItems[i]= itemlist.get(i);
            System.out.println("orderItemID"+" "+orderItems[i].getId()+" "+orderItems[i].getActualUnitPrize()+" "+orderItems[i].getNum());
        }

        boolean isSuccess=true;
        //下单时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String orderTime=df.format(new Date());// new Date()为获取当前系统时间

        System.out.println(orderTime);

        //生成order的id
        int id=orderService.getMaxOrderID();
        id+=1;

        System.out.println(id);

        //获取所有工厂信息
        List<Factory> factories=orderService.getAllFactory();
        String receiverAddressInfo=receiverAddress.getAddress();
        System.out.println(receiverAddressInfo);


        Map<String,String> desLocationMap=orderService.getLocationFromAddress(receiverAddressInfo);
        if(desLocationMap.get("latitude").toString().equals("false"))
        {
            System.out.println("地址信息错误！！！！！！！！");
            String ret=String.valueOf(isSuccess);
            ret="{\"isSuccess\":\""+"false"+"\"}";
            return JSONObject.fromObject(ret);
        }

        System.out.println(desLocationMap.toString());
        Factory selectFactory=new Factory();
        if(factories!=null)
        {
            //查找合适的工厂
            for (int i = 0; i < factories.size(); i++) {
                Factory factory = factories.get(i);
                Map<String, String> originalLocationMap = orderService.getLocationFromAddress(factory.getAddress());
                if(!originalLocationMap.get("latitude").toString().equals("false"))
                {
                    int distance = orderService.getDistance(originalLocationMap.get("latitude"), originalLocationMap.get("longitude"), desLocationMap.get("latitude"), desLocationMap.get("longitude"));
                    System.out.println("***********************"+distance);
                    if (distance <= 10000) {
                        selectFactory = factory;
                        System.out.println("-----------------select factory:"+selectFactory.getId()+" "+selectFactory.getAddress());
                        break;
                    }
                }
            }
        }

        if(selectFactory==null || selectFactory.getId()==0)
        {
            System.out.println("-----------------select factory:is null");
            isSuccess=false;
            String ret=String.valueOf(isSuccess);
            ret="{\"success\":\""+ret+"\"}";
            System.out.println(ret);
            return JSONObject.fromObject(ret);
        }

        //生成Order
        Order order=new Order();
        order.setId(id);
        order.setOrderTime(orderTime);
        order.setReceiverAddressId(receiverAddress.getId());
        order.setState("1");
        order.setFactoryId(selectFactory.getId());


        //新增pizzaOrder
        orderService.commitOrder(order);

        for(int i=0;i<orderItems.length;i++)
        {
            //新增orderItem
            OrderItem orderItem=orderItems[i];
            orderItem.setOrderId(id);
            System.out.println(orderItem.getMenuItemId());
            orderService.insertOrderItem(orderItem);
        }

        if(selectFactory==null)
            isSuccess=false;

        String ret=String.valueOf(isSuccess);
        if(isSuccess==true)
        {
            ret="{\"success\":\""+String.valueOf(id)+"\"}";
        }
        else
        {
            ret = "{\"success\":\"" + ret + "\"}";
        }
        System.out.println(ret);
        return JSONObject.fromObject(ret);
    }

    @RequestMapping(value = "/payment",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject afterPayment(Model model,@RequestBody Map map)
    {
        String result="true";
        int orderID=Integer.valueOf(map.get("orderId").toString());

        orderService.changeOrderStatus(orderID,"2");

        result="{\"success\":\""+result+"\"}";
        return JSONObject.fromObject(result);
    }

}
