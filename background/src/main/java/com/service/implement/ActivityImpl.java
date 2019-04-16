package com.service.implement;

import com.dao.ActivityDao;
import com.helper.TimeHelper;
import com.pojo.Activity;
import com.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityImpl implements ActivityService {
    @Autowired
    ActivityDao activityDao;

    @Override
    public List<Activity> getAllActivities(){
        return activityDao.getActivities();
    }

    @Override
    public void handleNewOrder() {
        List<Integer> orderList = activityDao.getNewOrder();
        for(Integer s: orderList){
            activityDao.newOrderActivity(1, TimeHelper.getTodayString(),"ID为"+s.toString()+"的订单生成",s);
        }
    }
}
