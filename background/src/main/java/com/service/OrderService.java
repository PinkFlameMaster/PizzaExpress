package com.service;

import com.dto.OrderItemDto;
import com.pojo.Ingredient;
import com.pojo.OrderItem;
import com.pojo.ReceiverAddress;
import com.vo.OrderVo;

import java.util.List;

public interface OrderService {
    List<OrderVo> findOrder(String name, String phone, String factory);
    OrderVo findOrderById(int id);
    List<OrderItemDto> getOderItemList(int id);
    void refund(int id);
}
