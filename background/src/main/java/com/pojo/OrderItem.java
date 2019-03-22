package com.pojo;

import java.util.Map;

public class OrderItem {
    private int id;
    private int orderId;
    private int menuItemId;
    private float actualUnitPrize;
    private int num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public float getActualUnitPrize() {
        return actualUnitPrize;
    }

    public void setActualUnitPrize(float actualUnitPrize) {
        this.actualUnitPrize = actualUnitPrize;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
