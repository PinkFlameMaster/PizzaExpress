package com.pojo;

import javafx.util.Pair;

import java.util.ArrayList;

public class MenuItem {
    private int id;
    private String name;
    private float prize;
    private String introduce;
    private ArrayList<Pair<String, Float>> ingredients; // 原材料的类型-数量

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

    public ArrayList<Pair<String, Float>> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Pair<String, Float>> ingredients) {
        this.ingredients = ingredients;
    }
}
