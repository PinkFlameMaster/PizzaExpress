package com.pojo;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int id;
    private int factoryId;
    private int receiverAddressId;
    private OrderState state;
    private Date orderTime;
    private Date receiveTime;
    private ArrayList<OrderItem> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(int receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }
}
