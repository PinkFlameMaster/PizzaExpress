package com.dto;

import com.pojo.Order;
import com.pojo.OrderItem;

public class OrderDto {
    private Order order;
    private OrderItem oderItem;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderItem getOderItem() {
        return oderItem;
    }

    public void setOderItem(OrderItem oderItem) {
        this.oderItem = oderItem;
    }


}
