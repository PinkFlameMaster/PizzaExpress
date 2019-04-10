package com.dto;

import com.pojo.Ingredient;
import com.pojo.MenuItem;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class MenuItemDto {
    private int id;
    private String name;
    private float prize;
    private String introduce;
    private boolean onSale;
    private String imgPath;

    private List<Ingredient> ingredients;

    public MenuItemDto(MenuItem menuItem, List<Ingredient> ingredients){
        this.id = menuItem.getId();
        this.name = menuItem.getName();
        this.prize = menuItem.getPrize();
        this.introduce = menuItem.getIntroduce();
        this.onSale = menuItem.isOnSale();
        this.imgPath = menuItem.getImgPath();
        this.ingredients = ingredients;
    }


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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
