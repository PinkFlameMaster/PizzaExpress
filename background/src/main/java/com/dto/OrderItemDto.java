package com.dto;

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
    private int num;
    private List<IngredientDto> ingredients;

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

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }
}
