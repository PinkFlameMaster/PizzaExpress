package com.service.implement;

import com.dao.OrderDao;
import com.dao.ReceiverAddressDao;
import com.pojo.Order;
import com.pojo.User;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Autowired
    ReceiverAddressDao receiverAddressDao;
    @Autowired
    OrderDao orderDao;

    @Override
    public List<Order> findOrder(List<User> users, int factoryId) {
        for(User u:users){
            u.getPhoneNum();
        }
        return null;
    }
}
