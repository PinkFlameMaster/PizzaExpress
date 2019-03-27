package com.service;

import com.pojo.Order;
import com.pojo.OrderItem;

public interface OrderService {

    int getMaxOrderID();

    boolean commitOrder(Order order);

    boolean insertOrderItem(OrderItem orderItem);
}
