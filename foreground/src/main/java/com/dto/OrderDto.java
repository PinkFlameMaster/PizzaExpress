package com.dto;

import com.pojo.Order;
import com.pojo.OrderItem;
import com.pojo.ReceiverAddress;

import java.util.List;

public class OrderDto {
    private String orderTime;
    private String orderStatus;
    private int orderId;
    private float orderTotal;
    private List<ItemDto> goodsList;
    private String factoryLocation;
    private ReceiverAddress receiverAddress;

    public ReceiverAddress getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(ReceiverAddress receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String state) {
        this.orderStatus = state;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderID) {
        this.orderId = orderID;
    }

    public float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(float totalPrice) {
        this.orderTotal = totalPrice;
    }

    public void setGoodsList(List<ItemDto> oderItem) {
        this.goodsList = oderItem;
    }

    public List<ItemDto> getGoodsList() {
        return goodsList;
    }
}
