package com.service;
import com.pojo.Admin;


public Interface AdminService {
    Admin checkLogin(String username,String password);
}
