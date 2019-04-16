package com.dao;

import com.pojo.Activity;
import com.sun.tracing.dtrace.ProviderAttributes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityDao {
    List<Activity> getActivities();
    void solveOrderActivity(int orderId);
    void solveStoreActivity(String type);
    void newOrderActivity(@Param("affair")int affair, @Param("date")String date,
                     @Param("detail")String detail,@Param("orderId")int orderId);
    void newStockActivity(@Param("affair")int affair, @Param("date")String date,
                          @Param("detail")String detail,@Param("type")String type);
    List<Integer> getNewOrder();
}
