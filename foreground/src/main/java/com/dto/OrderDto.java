package com.dto;

import com.pojo.Order;
import com.pojo.OrderItem;

import java.util.List;

public class OrderDto {
    private String orderTime;
    private String state;
    private int orderID;
    private float totalPrice;
    private List<OrderItem> oderItem;
    private String factoryLocation;


    public String getFactoryLocation() {
        return factoryLocation;
    }

    public void setFactoryLocation(String factoryLocation) {
        this.factoryLocation = factoryLocation;
    }
    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOderItem(List<OrderItem> oderItem) {
        this.oderItem = oderItem;
    }

    public List<OrderItem> getOderItem() {
        return oderItem;
    }
}
