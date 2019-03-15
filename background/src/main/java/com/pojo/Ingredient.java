package com.pojo;

import java.util.Date;

public class Ingredient {
    private int id;
    private String type;
    private String source;
    private Date inportDate;
    private int factoryId;
    private float amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getInportDate() {
        return inportDate;
    }

    public void setInportDate(Date inportDate) {
        this.inportDate = inportDate;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
