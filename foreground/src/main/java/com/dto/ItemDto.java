package com.dto;

import com.pojo.MenuItem;
import com.pojo.OrderItem;

public class ItemDto {
    private OrderItem orderItem;
    private MenuItem menuItem;

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}
