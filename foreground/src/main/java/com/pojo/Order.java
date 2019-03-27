package com.pojo;

public class Order {
    private int id;
    private int factoryId;
    private int receiverAddressId;
    private String state;
    private String orderTime;
    private String receiveTime;

    public Order()
    {

    }
    public Order(int id,int factoryId,int receiverAddressId,String state,String orderTime,String receiveTime)
    {
        this.id=id;
        this.factoryId=factoryId;
        this.receiverAddressId=receiverAddressId;
        this.state=state;
        this.orderTime=orderTime;
        this.receiveTime=receiveTime;
    }
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
}
