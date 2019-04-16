package com.service.implement;

import com.dao.ActivityDao;
import com.dao.IngredientDao;
import com.dao.OrderDao;
import com.dto.IngredientDto;
import com.dto.OrderDto;
import com.helper.TimeHelper;
import com.pojo.Activity;
import com.pojo.Ingredient;
import com.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class ActivityImpl implements ActivityService {
    @Autowired
    ActivityDao activityDao;
    @Autowired
    IngredientDao ingredientDao;
    @Autowired
    OrderDao orderDao;
    @Override
    public List<Activity> getAllActivities(){
        return activityDao.getActivities();
    }

    @Override
    public void handleNewOrder() {
        List<Integer> orderList = activityDao.getNewOrder();
        for(Integer s: orderList){
            activityDao.newOrderActivity(1, TimeHelper.getTodayString(),"ID为"+s.toString()+"的订单生成",s);
            int factoryId = orderDao.findOrderById(s);
            List<Integer> orderItemIds = ingredientDao.getOrderItemByOrderId(s);
            for(int orderItemId :orderItemIds){
            List<Ingredient> list =  ingredientDao.setOrderStockValue(orderItemId);//该菜品的所有原料列表,根据menuid查到
            for(Ingredient ingredient: list){
                List<IngredientDto> ingredientOfOneType = ingredientDao.getIngredientByType(ingredient.getType(),factoryId);
                for(IngredientDto stock: ingredientOfOneType){
                    float ingredientAmount = ingredient.getAmount();
                    if(stock.getAmount()>ingredientAmount){//有充足原料
                        float newAmount = stock.getAmount()-ingredientAmount;
                        ingredientDao.setNewAmount(stock.getId(),newAmount);
                        ingredientDao.newOrderIngredient(orderItemId,stock.getId());
                        if(newAmount<stock.getThreshold())
                            activityDao.newStockActivity(0,TimeHelper.getTodayString(),ingredient.getType()+"需要补货",ingredient.getType());
                    }
                }
            }}
        }
    }

    @Override
    public void stockActivity(String type) {
        activityDao.newStockActivity(0, TimeHelper.getTodayString(),type+"需要补货",type);
    }
}
