package com.pojo;

public class MenuItem_Ingredient {
    private int menuItemId;

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    public float getIngredientAmount() {
        return ingredientAmount;
    }

    public void setIngredientAmount(float ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    private String ingredientType;
    private float ingredientAmount;
}
