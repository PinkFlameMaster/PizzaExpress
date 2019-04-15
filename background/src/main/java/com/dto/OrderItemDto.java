package com.dto;

import com.pojo.Ingredient;

import java.util.List;

public class OrderItemDto {
    private MenuItemDto menuItem;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;
    private float actualUnitPrize;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    private int orderId;
    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    private int menuItemId;
    private int num;
    private List<Ingredient> ingredients;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MenuItemDto getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItemDto menuItem) {
        this.menuItem = menuItem;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
