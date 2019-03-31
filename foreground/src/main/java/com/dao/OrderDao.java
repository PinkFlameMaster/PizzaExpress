package com.dao;

import com.pojo.Factory;
import com.pojo.Order;
import com.pojo.OrderItem;
import com.pojo.ReceiverAddress;

import java.util.List;

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

    /**
     *
     * @return 所有商店信息
     */
    List<Factory> getAllFactory();

    /**
     *
     * @param receiverAddressID 查询的用户手机号
     * @return 返回该用户的所有下单
     */
    List<Order> getAllOrders(int receiverAddressID);

    /**
     *
     * @param phoneNum
     * @return 返回该用户的所有地址
     */
    List<ReceiverAddress> getAllAddress(String phoneNum);

    /**
     *
     * @param orderID
     * @return 返回对应订单号的所有订单内容
     */
    List<OrderItem> getAllOrderItemFromOrderID(int orderID);

    /**
     *
     * @param orderID
     * @return 通过ID查找order
     */
    Order getOrderByID(int orderID);
}
