package com.dao;

import com.pojo.Order;

import java.util.List;

public interface OrderDao {
    List<Order> findOrderByAddress(int AddressId);
}
