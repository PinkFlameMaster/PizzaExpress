package com.service;

import com.pojo.Order;
import com.pojo.User;

import java.util.List;

public interface OrderService {
    List<Order> findOrder(List<User> user, int factoryId);
}
