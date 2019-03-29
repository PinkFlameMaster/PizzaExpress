package com.dto;

import java.util.Dictionary;

public class MenuItemDto {
    private int id;
    private String name;
    private float prize;
    private String introduce;
    private boolean onSale;
    private Dictionary<String, Float> ingredients;

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

    public float getPrize() {
        return prize;
    }

    public void setPrize(float prize) {
        this.prize = prize;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public Dictionary<String, Float> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Dictionary<String, Float> ingredients) {
        this.ingredients = ingredients;
    }
}
