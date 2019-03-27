package com.dao;

import com.pojo.Order;
import com.pojo.OrderItem;

public interface OrderDao {

    /**
     * 用户下单
     * @param order 提交的订单
     * @return 是否提交成功
     */
    boolean commitOrder(Order order);

    /**
     *
     * @return 最大的orderID
     */
    int getMaxOrderID();

    /**
     *
     * @param orderItem 需要添加的orderItem
     * @return 是否添加成功
     */
    boolean insertOrderItem(OrderItem orderItem);
}
