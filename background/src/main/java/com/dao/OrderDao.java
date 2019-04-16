package com.dao;

import com.dto.OrderItemDto;
import com.pojo.Ingredient;
import com.pojo.Order;
import com.pojo.OrderItem;
import com.pojo.ReceiverAddress;
import com.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    List<OrderVo> findOrderComplex(@Param("name")String name, @Param("phone")String phone, @Param("factoryName")String factoryName);
    OrderVo findOderItemById(int id);
    List<OrderItemDto> findOrderItemListById(int oderId);
    void refundOrder(int id);
}
