package com.service.implement;

import com.dao.IngredientDao;
import com.dao.OrderDao;
import com.dao.ReceiverAddressDao;
import com.dto.OrderItemDto;
import com.pojo.Ingredient;
import com.pojo.OrderItem;
import com.pojo.ReceiverAddress;
import com.service.OrderService;
import com.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl  implements OrderService {
    @Autowired
    ReceiverAddressDao receiverAddressDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    IngredientDao ingredientDao;

    @Override
    public List<OrderVo> findOrder(String name, String phone, String factory) {
        return orderDao.findOrderComplex(name,phone, factory);
    }

    @Override
    public OrderVo findOrderById(int id){
        return orderDao.findOderItemById(id);
    }

    @Override
    public List<OrderItemDto> getOderItemList(int id) {
        List<OrderItemDto> result = orderDao.findOrderItemListById(id);
        for(OrderItemDto orderItemDto: result){
            int orderId = orderItemDto.getOrderId();
            List<Ingredient> ingredients = ingredientDao.getIngredientByOrder(orderId);
            orderItemDto.setIngredients(ingredients);
        }
        return result;
    }

    @Override
    public List<Ingredient> getIngredientByOrderId(int orderId) {
        return ingredientDao.getIngredientByOrder(orderId);
    }

    @Override
    public void refund(int id) {
        orderDao.refundOrder(id);
    }


}
