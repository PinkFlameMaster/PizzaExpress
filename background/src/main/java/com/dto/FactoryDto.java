package com.dto;

import com.pojo.Factory;

public class FactoryDto {
    private String name;
    private String address;
    private float latitude;
    private float longitude;
    private String phoneNum;
    private String businessTimeFrom;
    private String businessTimeTo;
    private String manager;
    public FactoryDto(){}
    public FactoryDto(Factory factory,String manager){
        this.manager = manager;
        this.name = factory.getName();
        this.address = factory.getAddress();
        this.latitude = factory.getLatitude();
        this.longitude = factory.getLongitude();
        this.phoneNum = factory.getPhoneNum();
        this.businessTimeFrom = factory.getBusinessTimeFrom();
        this.businessTimeTo = factory.getBusinessTimeTo();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getBusinessTimeFrom() {
        return businessTimeFrom;
    }

    public void setBusinessTimeFrom(String businessTimeFrom) {
        this.businessTimeFrom = businessTimeFrom;
    }

    public String getBusinessTimeTo() {
        return businessTimeTo;
    }

    public void setBusinessTimeTo(String businessTimeTo) {
        this.businessTimeTo = businessTimeTo;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
