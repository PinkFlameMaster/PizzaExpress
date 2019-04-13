package com.dto;

import com.pojo.Factory;
import com.pojo.OrderItem;
import com.pojo.ReceiverAddress;
import com.vo.OrderVo;

import java.util.List;

public class OrderDto {
    private int id;
    private String factoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public ReceiverAddress getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(ReceiverAddress receiverAddress) {
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

    public float getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(float deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    private ReceiverAddress receiverAddress;
    private String state;
    private String orderTime;
    private String receiveTime;
    private List<OrderItemDto> orderItems;
    private float deliveryFee;
    public OrderDto(OrderVo orderVo, List<OrderItemDto> orderItems){
        this.orderItems = orderItems;
        this.id = orderVo.getId();
        this.factoryName = orderVo.getFactoryName();
        this.receiverAddress = orderVo.getReceiverAddress();
        this.state = orderVo.getState();
        this.orderTime = orderVo.getOrderTime();
        this. receiveTime = orderVo.getReceiveTime();
    }



//    String id;
//    String username;
//    String userId;
//    String factoryName;
//    ReceiverAddress receiverAddress;



}
