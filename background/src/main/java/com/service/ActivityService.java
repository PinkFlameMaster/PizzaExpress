package com.service;

import com.pojo.Activity;

import java.util.List;

public interface ActivityService {
    public List<Activity> getAllActivities();
    public void handleNewOrder();

}
