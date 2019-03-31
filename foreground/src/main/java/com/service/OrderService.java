package com.service;

import com.pojo.Factory;
import com.pojo.Order;
import com.pojo.OrderItem;
import com.pojo.ReceiverAddress;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public interface OrderService {

    int getMaxOrderID();

    boolean commitOrder(Order order);

    boolean insertOrderItem(OrderItem orderItem);

    List<Factory> getAllFactory();

    Map<String,String> getLocationFromAddress(String address) throws MalformedURLException, UnsupportedEncodingException;

    int getDistance(String originalLatitude,String originalLongitude,String desLatitude,String desLongitude) throws MalformedURLException;

    List<Order> getAllOrders(int receiverAddressID);

    List<ReceiverAddress> getAllAddress(String phoneNum);

    List<OrderItem> getAllOrderItemFromOrderID(int orderID);

    Order getOrderByID(int orderID);
}
