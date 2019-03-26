package com.service.implement;

import com.dao.OrderDao;
import com.pojo.Order;
import com.pojo.OrderItem;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDao orderDao;

    @Override
    public int getMaxOrderID() {
        return orderDao.getMaxOrderID();
    }

    @Override
    public boolean commitOrder(Order order) {
        return orderDao.commitOrder(order);
    }

    @Override
    public boolean insertOrderItem(OrderItem orderItem) {
        return orderDao.insertOrderItem(orderItem);
    }
}
