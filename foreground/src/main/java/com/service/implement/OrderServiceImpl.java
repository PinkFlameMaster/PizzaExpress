package com.service.implement;

import com.dao.OrderDao;
import com.pojo.Factory;
import com.pojo.Order;
import com.pojo.OrderItem;
import com.pojo.ReceiverAddress;
import com.service.OrderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDao orderDao;

    @Override
    public int getMaxOrderID() {
        return orderDao.getMaxOrderID();
    }

    @Override
    public boolean commitOrder(Order order) {
        return orderDao.commitOrder(order);
    }

    @Override
    public boolean insertOrderItem(OrderItem orderItem) {
        return orderDao.insertOrderItem(orderItem);
    }

    @Override
    public List<Factory> getAllFactory() {
        return orderDao.getAllFactory();
    }

    @Override
    public Map<String, String> getLocationFromAddress(String address) throws MalformedURLException, UnsupportedEncodingException {
        Map<String,String> map=new HashMap<>();
        final Object[] objs = new Object[2];
        System.out.println(address);
        address=URLEncoder.encode(address,"UTF-8");
        String city="上海市";
        city=URLEncoder.encode(city,"UTF-8");

        final URL url=new URL("http://restapi.amap.com/v3/geocode/geo?key=927c014e4e244ee382cf63f33e2ed0e5&address="+address+"&city="+city);

        Thread connectToServe = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection urlConnection;
                        try {
                            urlConnection = (HttpURLConnection) url.openConnection();//打开http连接
                            urlConnection.setConnectTimeout(5000);//连接的超时时间
                            urlConnection.setUseCaches(false);//不使用缓存
                            urlConnection.setInstanceFollowRedirects(true);//是成员函数，仅作用于当前函数,设置这个连接是否可以被重定向
                            urlConnection.setReadTimeout(5000);//响应的超时时间
                            urlConnection.setDoInput(true);//设置这个连接是否可以写入数据
                            urlConnection.setDoOutput(true);//设置这个连接是否可以输出数据
                            urlConnection.setRequestMethod("GET");//设置请求的方式
                            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");//设置消息的类型
                            urlConnection.connect();// 连接，从上述至此的配置必须要在connect之前完成，实际上它只是建立了一个与服务器的TCP连接

                            //得到响应码
                            int responseCode = urlConnection.getResponseCode();
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                //得到响应
                                InputStream inputStream = urlConnection.getInputStream();
                                //获取响应
                                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                                String line;
                                StringBuffer s = new StringBuffer();
                                int i=0;
                                while ((line = reader.readLine()) != null) {
                                    s.append(line);
                                }
                                reader.close();
                                urlConnection.disconnect();

                                JSONObject json = JSONObject.fromObject(s.toString());
                                JSONArray geocodes=JSONArray.fromObject(json.getJSONArray("geocodes"));
                                if(geocodes.size()>0)
                                {
                                    JSONObject geocode = JSONObject.fromObject(geocodes.getJSONObject(0));
                                    String location = (String) geocode.get("location");
                                    String[] strarray = location.split(",");
                                    objs[0] = strarray[0];
                                    objs[1] = strarray[1];
                                }
                                else
                                {
                                    objs[0] = "false";
                                    objs[1] = "false";
                                }
                            }
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        connectToServe.start();
        try
        {
            connectToServe.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String latitude=objs[0].toString();
        String longitude=objs[1].toString();
        System.out.println(latitude+","+longitude);
        map.put("latitude",latitude);
        map.put("longitude",longitude);
        return map;
    }

    @Override
    public int getDistance(String originalLatitude, String originalLongitude, String desLatitude, String desLongitude) throws MalformedURLException
    {
        final URL url=new URL("https://restapi.amap.com/v3/direction/driving?key=927c014e4e244ee382cf63f33e2ed0e5&origin="+originalLatitude+","+originalLongitude+"&destination="+desLatitude+","+desLongitude+"&originid=&destinationid=&extensions=base&strategy=0&waypoints=&avoidpolygons=&avoidroad=");
        final Object[] objs=new Object[1];
        Thread connectToServe = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection urlConnection;
                        try {
                            urlConnection = (HttpURLConnection) url.openConnection();//打开http连接
                            urlConnection.setConnectTimeout(5000);//连接的超时时间
                            urlConnection.setUseCaches(false);//不使用缓存
                            urlConnection.setInstanceFollowRedirects(true);//是成员函数，仅作用于当前函数,设置这个连接是否可以被重定向
                            urlConnection.setReadTimeout(5000);//响应的超时时间
                            urlConnection.setDoInput(true);//设置这个连接是否可以写入数据
                            urlConnection.setDoOutput(true);//设置这个连接是否可以输出数据
                            urlConnection.setRequestMethod("GET");//设置请求的方式
                            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");//设置消息的类型
                            urlConnection.connect();// 连接，从上述至此的配置必须要在connect之前完成，实际上它只是建立了一个与服务器的TCP连接

                            //得到响应码
                            int responseCode = urlConnection.getResponseCode();
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                //得到响应流
                                System.out.println(responseCode);
                                InputStream inputStream = urlConnection.getInputStream();
                                //获取响应
                                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                                String line;
                                StringBuffer s = new StringBuffer();
                                int i=0;
                                while ((line = reader.readLine()) != null) {
                                    s.append(line);
                                }
                                reader.close();
                                urlConnection.disconnect();

                                JSONObject json = JSONObject.fromObject(s.toString());
                                JSONObject route=JSONObject.fromObject(json.getJSONObject("route"));
                                JSONArray paths=JSONArray.fromObject(route.getJSONArray("paths"));
                                JSONObject distance=JSONObject.fromObject(paths.getJSONObject(0));

                                objs[0]=distance.getString("distance");

                            }
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        connectToServe.start();
        try
        {
            connectToServe.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(objs[0].toString());
    }

    @Override
    public List<Order> getAllOrders(int receiverAddressID) {
        return orderDao.getAllOrders(receiverAddressID);
    }

    @Override
    public List<ReceiverAddress> getAllAddress(String phoneNum) {
        return orderDao.getAllAddress(phoneNum);
    }

    @Override
    public List<OrderItem> getAllOrderItemFromOrderID(int orderID) {
        return orderDao.getAllOrderItemFromOrderID(orderID);
    }

    @Override
    public Order getOrderByID(int orderID) {
        return orderDao.getOrderByID(orderID);
    }


}
