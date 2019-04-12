package com.service.implement;

import com.dao.OrderDao;
import com.dao.ReceiverAddressDao;
import com.service.OrderService;
import com.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ReceiverAddressDao receiverAddressDao;
    @Autowired
    OrderDao orderDao;

    @Override
    public List<OrderVo> findOrder(String name, String phone, String factory) {
        return orderDao.findOrderComplex(name,phone, factory);
    }
}
