package com.pojo;

public class Factory {
    private int id;
    private String name;
    private String address;
    private float latitude;
    private float longitude;
    private String phoneNum;
    private String businessTimeFrom;
    private String businessTimeTo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
