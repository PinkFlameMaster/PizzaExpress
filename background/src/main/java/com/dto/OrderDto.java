package com.dto;

import com.pojo.Factory;

import java.util.List;

public class OrderDto {
    private int id;
    private Factory factory;
    private ReceiverAddressDto receiverAddress;
    private String state;
    private String orderTime;
    private String receiveTime;
    private List<OrderItemDto> orderItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public ReceiverAddressDto getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(ReceiverAddressDto receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
