package com.service;

import com.vo.OrderVo;

import java.util.List;

public interface OrderService {
    List<OrderVo> findOrder(String name, String phone, String factory);
}
